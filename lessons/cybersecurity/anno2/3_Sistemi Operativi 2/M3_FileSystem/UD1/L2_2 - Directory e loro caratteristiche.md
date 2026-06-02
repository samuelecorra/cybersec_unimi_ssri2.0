# **M3 UD1 Lezione 2 - Directory e loro caratteristiche (parte 2)**

---

### **1. Introduzione**

In questa seconda parte viene analizzato come strutturare in file system grandi quantità di memoria di massa.

Nei sistemi di elaborazione più grandi, la memoria di massa può essere costituita da più dischi fisici o da più dischi logici. Il problema diventa quindi creare una visione logica unica, indipendente dal numero di dischi o partizioni presenti nel sistema.

Gli aspetti trattati sono:

- file system separati su dischi diversi;
- partizionamento dei dischi;
- file system creati sulle partizioni;
- montaggio dei file system;
- costruzione di un unico file system astratto.

> 📌 Partizionamento e montaggio permettono di passare da una visione fisica, disco per disco, a una visione logica unica dell'intera memoria di massa.

---

### **2. File system su più dischi**

Una memoria di massa può essere costituita da più dischi.

Se ogni disco viene gestito separatamente, ciascun disco contiene un proprio file system indipendente. Per esempio, se i file system sono organizzati ad albero, ogni disco possiede un proprio albero separato dagli altri.

In questa situazione:

- il disco 1 ha il proprio file system;
- il disco 2 ha il proprio file system;
- il disco 3 ha il proprio file system;
- l'utente o il programma deve sapere su quale disco si trova il file desiderato.

![](imgs/Pasted%20image%2020260601071900.png)

Non esiste quindi una visione unica di tutta la memoria di massa. Il reperimento logico avviene solo all'interno del singolo disco, mantenendo visibile all'utente una parte della struttura fisica del sistema.

> ⚠️ Se i file system restano separati disco per disco, l'utente deve conoscere la collocazione fisica del file, perdendo parte dell'astrazione offerta dal file system.

---

### **3. Partizioni**

Quando i dischi sono molto grandi, spesso non è pratico gestirli come un'unica entità.

Si suddivide allora il disco in porzioni dette **partizioni**, ciascuna gestibile in modo più semplice ed efficiente.

![](imgs/Pasted%20image%2020260601072038.png)

#### **3.1. Definizione**

Una **partizione** è l'astrazione di un dispositivo fisico di memoria di massa.

Un disco può essere organizzato in modi diversi:

- una sola partizione sull'intero disco;
- più partizioni sullo stesso disco;
- una partizione logica distribuita su più dischi, vista come un unico insieme.

![](imgs/Pasted%20image%2020260601072058.png)

#### **3.2. File system nelle partizioni**

In ciascuna partizione può essere creato un proprio file system.

Quindi:

- un disco diviso in più partizioni può contenere più file system;
- una partizione distribuita su più dischi può contenere un unico file system per l'intero insieme dei dischi.

> 💡 La partizione separa il concetto di disco fisico dal concetto di volume logico utilizzabile dal file system.

---

### **4. Esigenza di una visione unica**

All'interno del sistema di elaborazione si vuole spesso vedere un unico file system, cioè un'unica struttura logica, indipendentemente da:

- quanti dischi fisici sono presenti;
- quante partizioni esistono;
- quanti volumi logici sono stati creati.

L'obiettivo è scegliere un'unica radice e collegare a essa i file system presenti su altri dischi o partizioni, creando un solo albero astratto.

Questa unificazione avviene tramite il **montaggio dei file system**.

---

### **5. Montaggio del file system**

Il **montaggio** è l'operazione con cui un file system presente su una partizione o su un altro volume logico viene agganciato a un file system principale.

Si sceglie un file system primario, la cui radice diventa la radice dell'intero file system globale. Poi si associa una directory del file system primario alla radice del file system che si vuole montare.

Quella directory diventa il **punto di montaggio**.

![](imgs/Pasted%20image%2020260601083113.png)

> 📌 Il punto di montaggio è il punto dell'albero principale in cui viene collegata la radice di un altro file system.

#### **5.1. Accesso a file nel file system principale**

Se il file desiderato si trova nel disco che contiene la radice complessiva, la ricerca segue normalmente il percorso interno al file system principale fino al file.

#### **5.2. Accesso a file nel file system montato**

Se il file desiderato si trova in un altro file system montato:

1. la ricerca parte dalla radice complessiva;
2. scende nell'albero del file system principale;
3. raggiunge la directory usata come punto di montaggio;
4. da lì passa alla radice del file system montato;
5. continua la discesa nell'albero montato fino al file desiderato.

In questo modo, file collocati fisicamente su dischi o partizioni diverse vengono raggiunti tramite un unico percorso logico.

---

### **6. Tabella dei punti di montaggio**

Per gestire il montaggio, il sistema operativo conserva una tabella dei punti di montaggio e dei file system montati.

Questa tabella permette di sapere che una certa directory del file system principale non deve essere interpretata come directory ordinaria, ma come collegamento alla radice di un altro file system.

La tabella contiene informazioni come:

- punto di montaggio;
- dispositivo o partizione montata;
- radice del file system montato;
- stato del montaggio;
- eventuali opzioni di accesso.

> ✅ La tabella dei punti di montaggio rende trasparente agli utenti l'aggregazione di più file system in un unico albero logico.

---

### **7. Visione astratta complessiva**

Il risultato del montaggio è un unico file system astratto per tutto il sistema di elaborazione.

Anche se fisicamente i file sono distribuiti su più dischi, partizioni o volumi logici, l'utente vede un solo albero.

$$
\text{File system globale}
=
\text{file system principale}
+ \sum_i \text{file system montati}
$$

Questa astrazione consente di:

- nascondere la distribuzione fisica dei dati;
- semplificare l'accesso per utenti e applicazioni;
- integrare nuovi volumi senza cambiare il modello logico generale;
- organizzare la memoria di massa secondo criteri logici.

---

### **8. Sintesi finale**

> ✅ Partizionamento e montaggio permettono di costruire un unico file system logico a partire da più dischi, partizioni o volumi.

In questa lezione sono stati analizzati:

- organizzazione separata dei file system su dischi diversi;
- limite della visione disco per disco;
- partizione come astrazione di memoria di massa;
- creazione di file system separati su partizioni;
- montaggio di file system secondari su un file system principale;
- punto di montaggio;
- tabella dei punti di montaggio;
- costruzione di una visione unica e trasparente dell'intera memoria di massa.

Il file system globale nasce quindi dall'unione logica di più file system fisicamente distinti.
