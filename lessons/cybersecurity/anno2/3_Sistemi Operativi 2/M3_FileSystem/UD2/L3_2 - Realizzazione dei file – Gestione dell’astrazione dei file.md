# **M3 UD2 Lezione 3 - Realizzazione dei file - Gestione dell'astrazione dei file (parte 2)**

---

### **1. Introduzione**

Questa seconda parte analizza come il sistema operativo gestisce concretamente l'**astrazione dei file** attraverso le operazioni fondamentali:

- apertura;
- lettura;
- scrittura;
- posizionamento su un record logico;
- chiusura.

Queste operazioni vengono interpretate attraverso il modello introdotto nella lezione precedente:

- il programma vede un file come sequenza di **record logici**;
- il file system usa una visione intermedia come **byte stream**;
- la memoria di massa conserva il contenuto in **blocchi fisici**.

> 📌 Ogni operazione sui file puo' essere letta come uno spostamento o una modifica della finestra di interpretazione sul byte stream.

---

### **2. Modello di riferimento**

Si consideri un file logico composto da record di dimensione \(L\) byte:

$$
\text{file logico} = \{r_0, r_1, r_2, \dots, r_N\}
$$

Il file system traduce questa visione in un byte stream:

$$
S = \{c_0, c_1, c_2, \dots, c_Q\}
$$

e memorizza il byte stream in blocchi fisici di dimensione \(F\):

$$
\text{file fisico} = \{b_0, b_1, b_2, \dots, b_M\}
$$

La corrispondenza tra record logico e byte stream e':

$$
r_i \longleftrightarrow S[iL, (i+1)L - 1]
$$

La corrispondenza tra byte stream e blocchi fisici e':

$$
b_j \longleftrightarrow S[jF, (j+1)F - 1]
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Apertura del file**

L'operazione di **apertura** prepara l'accesso al file.

Dal punto di vista del file logico, il processo non ha ancora letto o scritto alcun record: si sta semplicemente posizionando sul primo elemento logico.

Il puntatore al record corrente viene inizializzato a:

$$
i = 0
$$

Dal punto di vista del byte stream, il sistema si prepara a interpretare il file a partire dal byte zero:

$$
\text{posizione iniziale nel byte stream} = 0
$$

La finestra di interpretazione di ampiezza \(L\) viene quindi posta sui primi \(L\) byte del byte stream:

$$
\text{finestra}_0 = S[0, L - 1]
$$

Dal punto di vista fisico, l'apertura richiede di conoscere:

- l'elenco dei blocchi fisici che compongono il file;
- la loro posizione sul disco;
- l'ordine con cui devono essere letti o scritti.

In particolare, il sistema deve sapere qual e' il primo blocco fisico da considerare.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ Aprire un file significa preparare le strutture necessarie per iniziare da record logico 0, byte 0 e primo blocco fisico della sequenza.

---

### **4. Lettura**

L'operazione di **lettura** restituisce al programma il record logico corrente.

Supponiamo che il file sia appena stato aperto. La finestra e' posizionata sul primo gruppo di \(L\) byte, ma il byte stream non e' ancora materializzato in memoria centrale.

Quando il processo richiede la lettura:

1. il sistema controlla se i byte della finestra sono gia' disponibili in memoria centrale;
2. se non lo sono, richiama il livello basso del file system;
3. il livello basso legge dal disco il blocco fisico necessario;
4. i byte letti riempiono la porzione corrispondente del byte stream;
5. gli \(L\) byte nella finestra vengono interpretati come record logico;
6. il record viene restituito al programma.

Per la prima lettura:

$$
r_0 = S[0, L - 1]
$$

Se questi byte si trovano nel primo blocco fisico, il sistema carica \(b_0\) in memoria centrale e rende disponibile la prima porzione del byte stream.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Al termine della lettura:

- il puntatore al record logico corrente avanza;
- la finestra sul byte stream viene spostata di \(L\) byte.

$$
i \leftarrow i + 1
$$

$$
\text{finestra}_{i+1} = S[(i+1)L, (i+2)L - 1]
$$

> 📌 La lettura non lavora direttamente sui record fisici: carica blocchi fisici, ricostruisce byte del byte stream e poi interpreta la finestra come record logico.

---

### **5. Letture successive**

Una lettura successiva parte dalla nuova posizione della finestra.

Se tutti i byte necessari al record corrente sono gia' presenti in memoria centrale, l'operazione puo' essere completata senza nuovi accessi al disco.

Se invece la finestra attraversa una porzione del byte stream non ancora caricata, il sistema deve leggere un altro blocco fisico.

Per esempio, se il secondo record logico richiede byte che si trovano in parte nel blocco gia' caricato e in parte nel blocco successivo, il sistema:

- usa i byte gia' disponibili;
- carica il blocco fisico successivo;
- completa la finestra;
- restituisce il record logico.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 Una singola lettura logica puo' richiedere zero, uno o piu' accessi fisici, a seconda di quali byte siano gia' presenti in memoria centrale.

---

### **6. Scrittura**

La **scrittura** segue una logica analoga, ma in direzione opposta.

Il programma fornisce un record logico da scrivere. Il sistema lo interpreta come una sequenza di \(L\) byte e li colloca nella finestra corrente del byte stream.

Subito dopo l'apertura, la prima scrittura riempie:

$$
S[0, L - 1]
$$

cioe' la porzione del byte stream corrispondente al record logico \(r_0\).

Non e' detto pero' che il sistema scriva immediatamente su disco. Se il blocco fisico ha dimensione \(F\), puo' essere inefficiente scrivere un blocco quando e' stata riempita solo una sua parte.

Per questo il sistema puo' mantenere temporaneamente i byte in memoria centrale e rimandare la scrittura fisica fino a quando e' disponibile un gruppo completo di \(F\) byte.

> ⚠️ Scrivere subito ogni record logico sul disco puo' produrre troppi accessi fisici, soprattutto quando piu' record condividono lo stesso blocco fisico.

---

### **7. Scrittura differita dei blocchi**

La scrittura effettiva su disco avviene quando il byte stream contiene abbastanza byte per riempire un blocco fisico.

Quando sono disponibili i primi \(F\) byte:

$$
S[0, F - 1]
$$

il sistema puo' scrivere il primo blocco fisico:

$$
b_0 \leftarrow S[0, F - 1]
$$

Se una scrittura logica supera il limite del blocco fisico, i byte eccedenti non vengono scritti nello stesso blocco, ma appartengono al blocco successivo.

Per esempio:

- una parte del record puo' completare \(b_0\);
- la parte restante puo' iniziare \(b_1\).

La porzione eccedente resta quindi in memoria finche' non ci sono abbastanza byte per scrivere il blocco fisico successivo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Il sistema ragiona per blocchi fisici quando scrive sul disco, anche se il programma ragiona per record logici.

---

### **8. Avanzamento dopo la scrittura**

Dopo ogni scrittura logica:

- il puntatore al record logico corrente avanza;
- la finestra sul byte stream si sposta di \(L\) byte;
- il sistema verifica se e' possibile scrivere uno o piu' blocchi fisici completi.

Formalmente:

$$
i \leftarrow i + 1
$$

$$
\text{finestra}_{i+1} = S[(i+1)L, (i+2)L - 1]
$$

Se dopo alcune scritture il secondo gruppo di \(F\) byte e' completo, il sistema puo' scrivere:

$$
b_1 \leftarrow S[F, 2F - 1]
$$

Se invece resta una porzione finale minore di \(F\), questa non puo' costituire un blocco fisico completo e viene mantenuta temporaneamente fino a nuove scritture o fino alla chiusura del file.

> ⚠️ L'ultima porzione incompleta del byte stream deve essere gestita con attenzione, perche' potrebbe dover essere salvata esplicitamente alla chiusura.

---

### **9. Posizionamento**

L'operazione di **posizionamento** permette di spostarsi su un record logico specifico senza leggere o scrivere tutti i record precedenti.

Supponiamo di essere posizionati sul record logico 1 e di voler eseguire un seek sul record logico 3.

Dal punto di vista logico:

$$
i \leftarrow 3
$$

Dal punto di vista del byte stream, la finestra viene spostata al byte:

$$
3L
$$

La nuova finestra e':

$$
\text{finestra}_3 = S[3L, 4L - 1]
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se l'operazione successiva e' una lettura, il sistema dovra' caricare dai blocchi fisici i byte necessari a riempire quella finestra.

Se l'operazione successiva e' una scrittura, il sistema collochera' nella finestra i byte del nuovo record logico e poi li salvera' nei blocchi fisici corrispondenti.

> 💡 Il seek cambia solo la posizione corrente; il trasferimento fisico dei dati avviene con la successiva lettura o scrittura.

---

### **10. Chiusura**

L'operazione di **chiusura** termina l'uso del file da parte del processo.

Se il file e' stato usato solo in lettura, la chiusura libera le strutture dati impiegate per la gestione:

- descrittori aperti;
- buffer;
- informazioni sulla posizione corrente;
- riferimenti ai blocchi caricati.

Se il file e' stato usato in scrittura, la chiusura deve anche salvare su disco eventuali byte del byte stream non ancora scritti.

In particolare, se esiste una porzione finale incompleta:

$$
|S_{\text{residuo}}| < F
$$

il sistema deve comunque forzarne la scrittura nel blocco fisico corrispondente, aggiornando anche le strutture dati del file system.

> ✅ La chiusura garantisce che le modifiche rimaste temporaneamente in memoria centrale vengano rese persistenti su memoria di massa.

---

### **11. Sintesi operativa**

| Operazione | Livello logico | Byte stream | Livello fisico |
|---|---|---|---|
| **Apertura** | Posizione sul primo record | Finestra sul byte 0 | Individuazione del primo blocco |
| **Lettura** | Restituzione del record corrente | Riempimento e interpretazione della finestra | Lettura dei blocchi necessari |
| **Scrittura** | Scrittura del record corrente | Inserimento dei byte nella finestra | Scrittura dei blocchi completi |
| **Posizionamento** | Spostamento al record scelto | Spostamento della finestra a \(iL\) | Preparazione dei blocchi corrispondenti |
| **Chiusura** | Termine dell'uso del file | Flush dell'eventuale residuo | Salvataggio e rilascio strutture |

---

### **12. Conclusione**

La gestione dell'astrazione dei file consiste nel mostrare al programma una sequenza ordinata di record logici, mentre internamente il sistema operativo lavora su byte stream e blocchi fisici.

Le operazioni di apertura, lettura, scrittura, posizionamento e chiusura permettono di mantenere coerente questa astrazione:

- la finestra sul byte stream rende visibile il record logico corrente;
- i blocchi fisici vengono caricati o scritti solo quando necessario;
- la chiusura salva eventuali dati residui e libera le strutture di gestione.

In questo modo la sequenza ordinata dei blocchi fisici viene resa visibile come una sequenza ordinata di elementi logici del file.
