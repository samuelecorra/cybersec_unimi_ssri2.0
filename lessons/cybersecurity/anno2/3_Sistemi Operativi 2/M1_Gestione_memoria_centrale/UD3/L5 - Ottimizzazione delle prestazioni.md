# **M1 UD3 Lezione 5 - Ottimizzazione delle prestazioni**

### **1. Introduzione**

Dopo aver analizzato le principali tecniche di gestione della memoria virtuale, questa lezione affronta le **strategie di ottimizzazione** adottate dai sistemi operativi per migliorare l’efficienza complessiva dell’uso della memoria centrale.  
Le prestazioni dipendono fortemente da fattori come la **dimensione delle pagine**, la **struttura del programma**, e l’**efficienza della traduzione degli indirizzi** da virtuali a fisici.

Le tecniche trattate includono:

- **Prepaginazione**
    
- **Scelta della dimensione ottimale delle pagine**
    
- **Translation Look-aside Buffer (TLB)**
    
- **Tabelle invertite delle pagine**
    
- **Strutturazione del programma**
    
- **Pagine residenti per dispositivi di I/O e per processi real-time**

---

### **2. Prepaginazione**

#### **Concetto**

La **prepaginazione** consiste nel **caricare in anticipo** in memoria centrale alcune pagine che il processo probabilmente userà a breve, invece di attendere che si verifichi un _page fault_.

#### **Obiettivo**

Ridurre il numero di _page fault_ durante:

- l’attivazione iniziale del processo;
    
- o la sua **riattivazione** dopo un periodo di sospensione (ad esempio dopo uno _swap-in_).

#### **Vantaggi**

- Migliore **temporal locality**, poiché le pagine appartenenti alla località attuale vengono caricate insieme.
    
- Riduzione dei _fault_ iniziali, che normalmente si verificano all’avvio del processo.

Nel **modello del Working Set**, vengono caricate **tutte le pagine appartenenti al working set corrente**, garantendo così una ripresa più fluida dell’esecuzione.

---

### **3. Dimensione della pagina**

La **dimensione della pagina** è un parametro critico che influenza direttamente il numero di _page fault_, l’efficienza della tabella delle pagine e la quantità di memoria sprecata per frammentazione.

#### **Pagine grandi**

**Vantaggi:**

- Meno pagine totali da gestire → tabella delle pagine più piccola.
    
- Tempo di I/O minore per caricamento/scrittura di ciascuna pagina.

**Svantaggi:**

- Maggiore **frammentazione interna**.
    
- Parte della memoria caricata può restare inutilizzata.
    
- Minor livello di dettaglio nella gestione della località.
    
- Possibile **aumento dei page fault**, perché una singola pagina grande può contenere dati poco correlati.

#### **Pagine piccole**

**Vantaggi:**

- Maggiore **risoluzione** della memoria virtuale.
    
- Minore frammentazione interna.
    
- Migliore sfruttamento della località spaziale.

**Svantaggi:**

- Tabella delle pagine più grande.
    
- Tempo di I/O maggiore per caricamento/scrittura di più pagine.
    
- Maggior overhead di gestione da parte del sistema operativo.

#### **Conclusione**

La dimensione ottimale è un **compromesso** tra:

- riduzione del numero di pagine,
    
- contenimento della frammentazione,
    
- e minimizzazione del tempo medio di accesso alla memoria.


---

### **4. Translation Look-aside Buffer (TLB)**

#### **Definizione**

La **TLB (Translation Look-aside Buffer)** è una **cache associativa** ad alta velocità che memorizza un sottoinsieme delle corrispondenze più recenti tra **indirizzi virtuali e fisici**.

#### **Estensione della TLB**

La capacità effettiva della TLB è data da:

$$  
\text{Estensione} = (\text{Numero di voci TLB}) \times (\text{Dimensione pagina})  
$$

#### **Effetto sulle prestazioni**

- Maggiore estensione → minori _page fault_.
    
- Riduzione del tempo medio di accesso alla memoria, grazie alla traduzione immediata degli indirizzi più frequenti.

#### **Strategie di ottimizzazione**

- **Aumentare la dimensione della TLB.**
    
- **Aumentare la dimensione della pagina.**
    
- **Usare pagine di dimensione eterogenea**, per bilanciare efficienza e granularità.

---

### **5. Tabella invertita delle pagine**

#### **Concetto**

Invece di mantenere una tabella delle pagine separata per ciascun processo, il sistema può utilizzare una **tabella unica** che elenca tutte le **pagine fisiche** attualmente presenti in memoria.

Ogni voce della tabella contiene la coppia:

$$  
\text{TabellaInvertita[PaginaFisica]} = (\text{Processo}, \text{PaginaLogica})  
$$

#### **Vantaggi**

- Riduzione significativa della **memoria fisica necessaria** per mantenere le tabelle.
    
- Migliore **scalabilità** nei sistemi con molti processi.
    
- Le **tabelle esterne** vengono consultate solo in caso di _page fault_.

---

### **6. Strutturazione del programma**

Una **buona strutturazione del codice** riduce il numero di _page fault_ e la dimensione del working set.

#### **Buone pratiche**

- Suddividere il codice in **moduli coesi**, ciascuno con alta località interna.
    
- Utilizzare **strutture dati** che migliorino la contiguità in memoria.
    
- Organizzare il flusso del programma in modo che le **pagine correlate vengano richiamate insieme**.

#### **Ruolo di compilatori e linker**

Gli strumenti di compilazione cercano di **preservare la località**:

- dispongono il codice in memoria in modo da **evitare salti frequenti** tra pagine lontane;
    
- minimizzano il rischio di accessi disordinati che causerebbero _page fault_ ricorrenti.

---

### **7. Pagine residenti per dispositivi di I/O**

Per i **dispositivi di input/output**, i _page fault_ possono compromettere la corretta gestione dei buffer di comunicazione.  
Per evitarli, si mantengono **pagine residenti** (non sostituibili) dedicate ai buffer I/O.

#### **Soluzioni possibili**

1. **Buffer nello spazio di indirizzi del sistema operativo**  
    → I dati vengono copiati dal buffer OS alle variabili del processo.
    
2. **Buffer nello spazio di indirizzi del processo**  
    → Le pagine dei buffer vengono rese **residenti permanentemente** in memoria centrale.

Questo garantisce trasferimenti di dati **continui e senza interruzioni**, evitando che un _page fault_ interrompa un’operazione I/O critica.

---

### **8. Pagine residenti per processi in tempo reale**

I **processi in tempo reale** devono rispettare **vincoli temporali stringenti**.  
L’uso della memoria virtuale, con possibili _page fault_, può rendere impossibile garantire tali vincoli.

#### **Soluzione**

- Mantenere **residenti in memoria** le pagine **critiche** del processo.
    
- In questo modo, non sarà necessario sostituire pagine durante l’esecuzione, evitando ritardi imprevisti.

#### **Esempio**

Nei sistemi embedded o di controllo industriale, il caricamento da disco di una pagina mancante potrebbe introdurre ritardi di millisecondi — inaccettabili per processi con _deadlines_ precise.

---

### **9. Sintesi finale**

Per ottimizzare le prestazioni della memoria virtuale, il sistema operativo adotta una combinazione di strategie:

1. **Prepaginazione** → riduce i _page fault_ iniziali.
    
2. **Scelta della dimensione ottimale delle pagine** → equilibrio tra frammentazione, I/O e gestione.
    
3. **Uso efficiente della TLB** → riduce i tempi di traduzione degli indirizzi.
    
4. **Tabelle invertite** → riducono lo spazio di memoria per le strutture di gestione.
    
5. **Strutturazione del programma** → favorisce la località e minimizza i _page fault_.
    
6. **Pagine residenti** → garantiscono stabilità nei processi I/O e real-time.

Insieme, queste tecniche consentono di ottenere un sistema **stabile, veloce e prevedibile**, anche in condizioni di carico elevato.