# **M3 UD2 Lezione 3 - Realizzazione dei file - Gestione dell'astrazione dei file (parte 1)**

---

### **1. Introduzione**

Questa lezione introduce la **realizzazione dei file** dal punto di vista dell'astrazione fornita dal sistema operativo.

L'obiettivo e' capire come un file, visto dall'utente come una sequenza di **record logici**, possa essere memorizzato su una memoria di massa che invece lavora con **blocchi fisici**.

Il problema centrale e' quindi il **mappaggio** tra:

- la visione logica del file, definita dal programma applicativo;
- la visione fisica del supporto di memoria di massa;
- una visione intermedia omogenea, detta **byte stream**.

> 📌 Il file system deve nascondere i dettagli fisici del disco e mostrare al programma una sequenza logica di informazioni.

---

### **2. Visione logica del file**

Dal punto di vista del programma, un file è una **sequenza di elementi omogenei** detti **record logici**.

Il numero dei record non è noto a priori e dipende dal contenuto effettivo del file.

La sequenza può essere rappresentata come:

$$
\text{file logico} = \{r_0, r_1, r_2, \dots, r_N\}
$$

![](imgs/Pasted%20image%2020260601110420.png)

Ogni record logico ha un tipo base definito dal programma applicativo.

Se ogni record ha dimensione pari a $L$ byte:

$$
|r_i| = L \text{ byte}
$$

Il sistema operativo non deve imporre vincoli sulla scelta di questo tipo base: il programmatore deve poter definire record piccoli, grandi o strutturati senza dipendere direttamente dalla dimensione dei blocchi fisici del disco.

---

### **3. Visione fisica del file**

La memoria di massa non memorizza direttamente record logici tipizzati. Dal punto di vista fisico, il disco è organizzato in **blocchi fisici**.

Ogni blocco fisico ha dimensione fissa $F$, determinata dalle caratteristiche del supporto e del sistema:

$$
|b_j| = F \text{ byte}
$$

Un file memorizzato su disco è quindi rappresentato come una sequenza ordinata di blocchi fisici:

$$
\text{file fisico} = \{b_0, b_1, b_2, \dots, b_M\}
$$

Questi blocchi devono essere letti o scritti nell'ordine corretto per ricostruire il contenuto del file.

![](imgs/Pasted%20image%2020260601110858.png)

> ⚠️ La visione logica e la visione fisica non coincidono: i record logici dipendono dal programma, i blocchi fisici dipendono dal dispositivo.

---

### **4. Problema del mappaggio**

Il problema da risolvere è come mappare la sequenza di record logici sulla sequenza di blocchi fisici.

I parametri fondamentali sono:

- $L$: dimensione del record logico;
- $F$: dimensione del blocco fisico.

Una soluzione ingenua consiste nel provare a collocare direttamente i record logici nei blocchi fisici.

---

### **5. Un record logico per blocco fisico**

La prima soluzione possibile è memorizzare **un record logico in un blocco fisico**.

Se il record logico è più piccolo del blocco fisico:

$$
L < F
$$

il record occupa solo una parte del blocco. La porzione rimanente resta inutilizzata e produce **sfrido**.

$$
\text{sfrido} = F - L
$$

Piu' il record logico e' piccolo rispetto al blocco fisico, maggiore e' lo spreco di spazio su disco.

![](imgs/Pasted%20image%2020260601111748.png)

Se invece il record logico e' piu' grande del blocco fisico:

$$
L > F
$$

il record non puo' essere contenuto in un singolo blocco.

Questa soluzione diventa quindi inaccettabile, perche' farebbe dipendere la definizione del tipo base del file da un limite fisico del dispositivo.

> ⚠️ Un file system non deve esporre al programmatore il vincolo fisico della dimensione del blocco.

---

### **6. Piu' record logici per blocco fisico**

Una seconda soluzione consiste nel memorizzare **più record logici nello stesso blocco fisico**.

Se ogni record occupa $L$ byte, il numero di record memorizzabili in un blocco è:

$$
K \cdot L \le F
$$

cioè:

$$
K = \left\lfloor \frac{F}{L} \right\rfloor
$$

dove il simbolo di floor indica che si arrotonda per difetto al numero intero più grande.

In questo modo si riduce lo spreco rispetto alla soluzione precedente.

Resta però possibile uno sfrido finale:

$$
\text{sfrido} = F - K \cdot L
$$

Inoltre, il problema principale non viene risolto: se $L > F$, nessun record logico può comunque essere contenuto in un blocco fisico.

> 📌 Inserire piu' record in un blocco migliora l'uso dello spazio, ma non elimina il vincolo tra record logico e blocco fisico.

---

### **7. Visione intermedia: byte stream**

Per superare questi limiti si introduce una visione logica omogenea del file: il **byte stream**, cioe' il **flusso di byte**.

In questa rappresentazione, qualunque file viene visto come una sequenza di byte:

$$
S = \{c_0, c_1, c_2, \dots, c_Q\}
$$

Il tipo base non e' piu' il record logico definito dal programma, ma sempre e solo il **byte**.

La tipizzazione del file viene rimossa a livello del file system:

- il programma continua a interpretare gruppi di byte come record logici;
- il sistema operativo gestisce solo una sequenza uniforme di byte;
- il disco conserva gruppi di byte dentro blocchi fisici.

> 💡 Il byte stream separa l'interpretazione dei dati dalla loro memorizzazione fisica.

---

### **8. Dalla visione logica al byte stream**

Il primo passaggio consiste nel trasformare il file logico in un byte stream.

Ogni record logico viene visto come la sequenza dei byte che lo compongono.

Se ogni record ha dimensione $L$, allora:

- il record $r_0$ corrisponde ai primi $L$ byte del byte stream;
- il record $r_1$ corrisponde ai successivi $L$ byte;
- il record $r_2$ corrisponde ai successivi $L$ byte;
- e così via.

Formalmente:

$$
r_i \longleftrightarrow (c_{iL}, c_{iL+1}, \dots, c_{(i+1)L - 1})
$$

La sequenza dei record logici viene quindi linearizzata in una sequenza continua di byte.

![](imgs/Pasted%20image%2020260601120021.png)

> ✅ Rimuovere la tipizzazione è sempre possibile, perché ogni struttura dati è comunque rappresentata in memoria come una sequenza di byte.

---

### **9. Dal byte stream ai blocchi fisici**

Il secondo passaggio consiste nel memorizzare il byte stream nei blocchi fisici del disco.

Il byte stream viene diviso in gruppi di $F$ byte, dove $F$ è la dimensione del blocco fisico.

- i primi $F$ byte vengono memorizzati nel blocco fisico $b_0$;
- i successivi $F$ byte vengono memorizzati nel blocco fisico $b_1$;
- i successivi $F$ byte vengono memorizzati nel blocco fisico $b_2$;
- l'ultimo gruppo viene memorizzato nell'ultimo blocco del file.

Formalmente:

$$
b_j \longleftrightarrow S[jF, (j+1)F - 1]
$$

Questa operazione è sempre possibile, perché il byte è l'unità elementare con cui si compone il blocco fisico.

![](imgs/Pasted%20image%2020260601120034.png)

> 📌 Il file system non deve piu' mappare record logici su blocchi fisici, ma byte stream su blocchi fisici.

---

### **10. Ricostruzione dei record logici**

Resta da capire come far riapparire al programma i record logici a partire dal byte stream.

Il meccanismo concettuale è una **finestra** di dimensione $L$ byte sovrapposta al byte stream.

La finestra mostra solo $L$ byte alla volta:

$$
\text{finestra}_i = S[iL, (i+1)L - 1]
$$

Questi byte vengono interpretati dal programma come il record logico $r_i$.

Spostando la finestra di $L$ byte si ottiene il record successivo.

![](imgs/Pasted%20image%2020260601123130.png)

> 💡 La finestra non cambia il contenuto del byte stream: cambia solo il gruppo di byte che viene interpretato come record logico corrente.

---

### **11. Ricostruzione del byte stream dai blocchi fisici**

Per ricostruire il byte stream a partire dalla memoria di massa, il sistema operativo deve conoscere:

- quali blocchi fisici appartengono al file;
- dove si trovano sul disco;
- in quale ordine devono essere letti.

L'ordine dei blocchi e' essenziale, perche' definisce l'ordine dei byte nel byte stream.

Se il sistema legge i blocchi nell'ordine sbagliato, il byte stream ricostruito non corrisponde al file logico originario.

> ⚠️ La gestione dei file richiede quindi strutture dati capaci di ricordare la sequenza dei blocchi fisici associati a ciascun file.

---

### **12. Sintesi**

La realizzazione dell'astrazione dei file richiede tre livelli:

| Livello                | Visione                                | Unita' gestita        |
| ---------------------- | -------------------------------------- | --------------------- |
| **Logico applicativo** | File come sequenza di record logici    | Record di \(L\) byte  |
| **Logico omogeneo**    | File come byte stream                  | Byte                  |
| **Fisico**             | File come sequenza di blocchi su disco | Blocchi di \(F\) byte |

Il byte stream e' il livello intermedio che consente al sistema operativo di:

- nascondere la struttura fisica del disco;
- evitare vincoli diretti tra record logico e blocco fisico;
- rappresentare in modo uniforme file di tipo diverso;
- ricostruire i record logici tramite finestre di interpretazione;
- ricostruire il byte stream leggendo i blocchi fisici nell'ordine corretto.

---

### **13. Conclusione**

La gestione dell'astrazione dei file si basa sulla separazione tra **interpretazione logica** e **memorizzazione fisica**.

Il programma vede record logici tipizzati, il sistema operativo gestisce un byte stream e il disco conserva blocchi fisici.

Il problema operativo diventa quindi ricordare **quali blocchi fisici compongono il file** e **in quale ordine devono essere letti o scritti**. Questo introduce il tema della gestione dell'allocazione dei blocchi, che viene approfondito nelle lezioni successive.
