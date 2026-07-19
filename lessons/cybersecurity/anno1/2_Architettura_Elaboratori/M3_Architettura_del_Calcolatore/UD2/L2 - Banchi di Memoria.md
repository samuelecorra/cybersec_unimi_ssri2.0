## ***Lezione 2: Banchi di memoria***

---

> 📌 Questa lezione rielabora integralmente le pagine 19–30 di `M3doc.pdf`, ricostruendo passo per passo il banco LC-2 da $4\text{K}\times16$.

### **1. Dal chip al banco**

Un **banco di memoria** è un insieme di chip che occupa una porzione dello spazio di indirizzamento della CPU. Ogni cella del banco dovrebbe essere larga quanto il bus dati, così che una parola completa venga trasferita in parallelo. La profondità è normalmente una potenza di due, in modo da utilizzare integralmente un certo numero di linee di indirizzo.

Il banco dovrebbe apparire come una sequenza di indirizzi contigui. Non deve però riempire necessariamente tutto lo spazio indirizzabile: zone prive di memoria fisica possono essere lasciate vuote per ragioni di costo o riservate ad altri dispositivi.

### **2. Specifiche dell’esempio**

La CPU LC-2 dispone di:

- bus indirizzi da 16 bit, quindi $2^{16}=65536=64\text{K}$ indirizzi;
- bus dati da 16 bit, quindi parole da 16 bit.

Sono disponibili chip RAM $1\text{K}\times8$, cioè $1024=2^{10}$ parole da 8 bit. Si vuole costruire un banco $4\text{K}\times16$, formato da $4096=2^{12}$ parole da 16 bit, collocato a partire da `x0000`.

> ⚠️ Nel manoscritto compare una volta “$2^{12}=4056$”: è un refuso. Il valore corretto, usato dall’intero progetto del banco, è $4096$.

### **3. Primo problema: ottenere parole da 16 bit**

Un chip fornisce soltanto 8 bit. Si accoppiano quindi due chip che ricevono lo stesso indirizzo e gli stessi controlli:

- il chip sinistro è collegato a `DB15–DB8` e conserva gli 8 bit più significativi;
- il chip destro è collegato a `DB7–DB0` e conserva gli 8 bit meno significativi.

Le due celle omologhe formano una parola logica da 16 bit. Una coppia realizza dunque un blocco $1\text{K}\times16$.

Esempio: se alla posizione 37 il chip sinistro contiene `10110110` e il destro `00101101`, la parola letta dalla CPU è `1011011000101101`.

### **4. Secondo problema: ottenere 4096 parole**

Per quadruplicare la profondità servono quattro coppie, cioè otto chip fisici:

$$
4\cdot(1\text{K}\times16)=4\text{K}\times16.
$$

Le linee `AB0–AB9` sono collegate in parallelo a tutte le coppie e selezionano la posizione interna da 0 a 1023. Le due linee successive, `AB10–AB11`, entrano in un decoder $2\rightarrow4$; ciascuna uscita pilota il `CS` di una coppia.

| `AB11 AB10` | Coppia selezionata | Offset nel banco |
| --- | ---: | --- |
| `00` | 0 | `x000–x3FF` |
| `01` | 1 | `x400–x7FF` |
| `10` | 2 | `x800–xBFF` |
| `11` | 3 | `xC00–xFFF` |

> ⚠️ Una riga del PDF nomina `AB12` e `AB11`; lo schema e il conteggio corretto impongono invece `AB10` e `AB11`, perché i dieci bit interni sono `AB0–AB9`.

L’indirizzo a 12 bit si scompone quindi come:

$$
\underbrace{AB_{11}AB_{10}}_{\text{coppia}}
\ \underbrace{AB_9\ldots AB_0}_{\text{cella nel chip}}.
$$

### **5. Terzo problema: collocare il banco nello spazio LC-2**

Restano `AB15–AB12`. Essi selezionano una fra sedici pagine da $4\text{K}$ nello spazio complessivo da $64\text{K}$. Poiché il banco deve iniziare da `x0000`, deve rispondere soltanto quando:

$$
AB_{15}AB_{14}AB_{13}AB_{12}=0000.
$$

Una NOR a quattro ingressi produce `1` esattamente in questo caso. La sua uscita abilita il decoder di coppia: è la **selezione di banco**.

| Intervallo | Bit alti | Esito |
| --- | --- | --- |
| `x0000–x0FFF` | `0000` | banco selezionato |
| `x1000–xFFFF` | diversi da `0000` | banco non selezionato |

La profondità e l’indirizzo iniziale sono così entrambi rispettati.

### **6. Distribuzione di dati e controlli**

Il bus dati è diviso verticalmente tra i due chip di ogni coppia, ma è condiviso orizzontalmente tra le quattro coppie. Soltanto la coppia con `CS` attivo può pilotarlo.

I segnali di lettura e scrittura devono raggiungere tutti i chip; sarà `CS` a rendere operativa soltanto la coppia scelta. Un eventuale segnale `Ready` del banco va restituito alla CPU. In un banco ROM il comando di scrittura non ha invece significato e deve essere omesso o reso inattivo.

### **7. Perché l’indirizzo non basta ad abilitare il banco**

Il primo schema del PDF porta direttamente il risultato del riconoscimento dei bit alti al decoder. Nella realtà, durante il passaggio fra due indirizzi i bit non cambiano tutti nello stesso istante. Per pochi nanosecondi può apparire una configurazione transitoria che seleziona il banco sbagliato. Una lettura spuria è indesiderata; una scrittura spuria può corrompere memoria.

Occorre quindi qualificare la selezione con un segnale che indichi una vera operazione. Nel caso con linee separate `R` e `W`:

$$
BankEnable=AddressMatch\land(R\lor W).
$$

Il decoder viene abilitato soltanto quando l’indirizzo è valido e il bus di controllo dichiara esplicitamente una lettura o una scrittura.

Un’altra organizzazione usa:

- un segnale `R/W` per il senso (`1` lettura, `0` scrittura);
- un segnale `MemAccess` che certifica che è in corso un accesso.

In tal caso `MemAccess` qualifica la selezione del banco, mentre `R/W` viene distribuito ai chip. Se la polarità richiesta dal chip è opposta, si inserisce un negatore.

> 📌 Il comando di operazione non è un dettaglio accessorio: rende affidabile nel tempo la decodifica dell’indirizzo e impedisce che transitori del bus diventino accessi reali.

### **8. Procedura generale di progetto**

Per costruire un banco da chip più piccoli:

1. affiancare abbastanza chip per raggiungere la larghezza del bus dati;
2. replicare le coppie o righe per raggiungere il numero di parole;
3. portare i bit meno significativi dell’indirizzo a tutti i chip;
4. decodificare i bit successivi per scegliere una riga di chip;
5. decodificare i bit più significativi per collocare il banco nello spazio globale;
6. qualificare il riconoscimento con il segnale di accesso;
7. distribuire correttamente lettura, scrittura, `CS` ed eventuale `Ready`.

> ✅ Nell’esempio LC-2, otto chip $1\text{K}\times8$ formano un banco $4\text{K}\times16$: due chip danno la larghezza, quattro coppie danno la profondità e la decodifica dei 16 bit determina cella, coppia e banco.
