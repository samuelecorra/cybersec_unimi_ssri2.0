## ***Lezione 2: Assembler LC-2 e processo di debugging***

---

> 📌 Questa lezione rielabora integralmente le pagine 63–75 di `M2doc.pdf`. Il nome storico del file è conservato per non interrompere i collegamenti esistenti.

### **1. Dal sorgente al file eseguibile**

Il PDF usa **LC2Edit** per scrivere il sorgente e invocare l’assembler. La traduzione deve risolvere non soltanto opcode e registri, ma anche label la cui posizione può essere nota solo dopo aver letto l’intero programma.

Per esempio:

```asm
        LEA R0, narray
        ; ...
narray  .blkw #4
```

quando incontra `LEA`, l’assembler non conosce ancora l’indirizzo di `narray`. Per questo esegue due passate.

### **2. Prima passata: indirizzi e symbol table**

Nella prima passata l’assembler:

1. legge la direttiva `.orig` e inizializza il location counter;
2. controlla la struttura sintattica delle righe;
3. determina quante parole produce ogni istruzione o direttiva;
4. associa a ogni label l’indirizzo corrente;
5. costruisce la **symbol table**;
6. rileva label duplicate e altri errori già decidibili.

Nella schermata di LC2Edit il vettore è chiamato `narray` (lo stesso ruolo svolto da `table` nella versione della lezione precedente). Una parte della tabella è:

| Simbolo | Indirizzo |
| --- | --- |
| `loop` | `x3002` |
| `finish` | `x3007` |
| `narray` | `x3008` |
| `result` | `x300D` |

Le direttive modificano il location counter in modo diverso: un’istruzione o `.fill` occupano una parola; `.blkw #4` ne riserva quattro; `.stringz` occupa una parola per carattere più il terminatore nullo.

### **3. Seconda passata: risoluzione e codifica**

Nella seconda passata l’assembler rilegge il sorgente e:

1. sostituisce ogni simbolo con l’indirizzo trovato;
2. verifica che l’indirizzo sia rappresentabile nel formato richiesto;
3. codifica opcode, registri e immediati;
4. emette le parole dell’**object file**.

La riga `AND R2,R2,#0` poteva essere codificata già al primo incontro; `LEA R0,narray` diventa traducibile soltanto dopo che `narray` è entrato nella symbol table.

Se la traduzione termina correttamente, l’ambiente produce almeno:

- un file oggetto, tipicamente `.obj`, da caricare nel simulatore;
- un file dei simboli, tipicamente `.sym`, utile per associare label e indirizzi.

### **4. Classi di errore**

| Errore | Esempio | Quando può emergere |
| --- | --- | --- |
| sintattico | opcode inesistente, operando mal formato | analisi del sorgente |
| simbolo duplicato | due righe con la stessa label | prima passata |
| simbolo indefinito | `BRZ finsih` anziché `finish` | risoluzione della seconda passata |
| valore non rappresentabile | immediato fuori dal campo disponibile | codifica |
| semantico | programma corretto formalmente ma con algoritmo errato | esecuzione e debugging |

Il PDF usa la distinzione didattica “compile-time” per gli errori sintattici e “link-time” per i simboli mancanti. In un assembler compatto entrambe le verifiche possono essere fasi dello stesso comando: ciò che conta è capire **quali informazioni sono necessarie** per rilevarle.

> ⚠️ L’assembler non può sapere che una somma, un salto o una cella scelta dal programmatore sia logicamente sbagliata se la codifica è valida.

### **5. Caricamento nel simulatore**

Il simulatore LC-2 carica il file `.obj` in memoria e permette di osservare:

- celle di memoria e loro interpretazione simbolica;
- `PC`, `IR`, registri generali e `CC`;
- avanzamento dell’esecuzione e modifiche prodotte da ogni istruzione.

Le celle a zero possono essere visualizzate come `NOP` o come parole non operative. Prima di una prova è buona pratica azzerare i registri e ripristinare uno stato noto, così i residui di un’esecuzione precedente non mascherano gli errori.

Il materiale mostra sia l’interfaccia storica dei programmi Windows `LC2Edit` e `Simulate`, sia una versione successiva distribuita tramite la piattaforma didattica SSR online. I comandi e le scorciatoie possono quindi differire, ma modello della CPU, object file e operazioni di debugging restano gli stessi.

### **6. Esecuzione controllata**

#### **6.1 Step Into**

Esegue una sola istruzione e si ferma. È utile per seguire in dettaglio registri, `CC`, PC e accessi a memoria. Nelle versioni mostrate dal PDF il vecchio tasto rapido `F8` risulta rimosso o variato: conviene usare il comando visibile nell’interfaccia installata.

#### **6.2 Step Over**

Se l’istruzione corrente chiama una routine con `JSR`, `JSRR` o `TRAP`, esegue l’intera routine e si ferma al punto di ripresa. È preferibile quando la routine è già affidabile e interessa il comportamento del chiamante.

#### **6.3 Step Out**

Completa la routine in corso e torna al chiamante. Serve quando si è entrati per errore troppo in profondità o si è già verificata la parte rilevante del sottoprogramma.

Procedere sempre con Step Into è accurato ma molto lento, soprattutto nelle routine di sistema o nei cicli di attesa dell’I/O.

### **7. Modifica dello stato e breakpoint**

Il simulatore consente di cambiare registri o celle durante una sessione, nel PDF tramite **Set Value** (`F4`). Si sceglie la locazione, si inserisce il valore — nell’esempio in decimale — e si conferma con `Apply` e poi `OK`. Questa funzione è utile per predisporre i dati di test senza riassemblare il sorgente.

Per il programma di somma vengono inseriti nel vettore:

```text
19, 11, 8, 3, 0
```

Il risultato atteso è:

$$
19+11+8+3=41=x0029.
$$

Un **breakpoint** posto a `x3007`, sulla `ST` finale, consente di eseguire rapidamente il ciclo e fermarsi **prima** della memorizzazione. Nel PDF il breakpoint è attivato con `Ctrl+B` e appare con un indicatore rosso. Al momento dell’arresto `R2` deve già contenere `x0029`; eseguendo poi la `ST`, anche la cella `result` assume quel valore.

### **8. Metodo di debugging verificabile**

1. definire input e risultato atteso;
2. inizializzare macchina e memoria;
3. mettere un breakpoint prima del punto da osservare;
4. eseguire fino al breakpoint;
5. confrontare registri, `CC`, puntatori e memoria con la traccia prevista;
6. usare Step Into nella prima istruzione divergente;
7. correggere il sorgente, riassemblare e ricaricare l’oggetto.

> ✅ Assembler e debugger risolvono problemi complementari: il primo traduce e rifiuta forme non codificabili; il secondo rende osservabili gli errori di significato che emergono solo durante l’esecuzione.
