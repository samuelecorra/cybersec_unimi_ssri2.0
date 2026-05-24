# **M2 UD1 Lezione 4 - Interfacce dei sistemi operativi**

---

### **1. Introduzione: perché parlare di interfacce**

In questa lezione analizziamo un aspetto generale ma fondamentale dei sistemi operativi: le **interfacce**.

Lo studio delle interfacce permette di comprendere **come vengono richieste e utilizzate le funzioni offerte dal sistema operativo**, sia da parte degli utenti sia da parte dei programmi.

Un sistema operativo, infatti, non è mai utilizzato direttamente: esso si interpone tra:

- l’utente e l’hardware,
    
- i programmi applicativi e le risorse di sistema.

Per svolgere questo ruolo, il sistema operativo mette a disposizione **interfacce ben definite**, che regolano ogni forma di interazione.

---
### **2. Tipologie di interfacce del sistema operativo**

Le interfacce del sistema operativo si distinguono in due grandi categorie:

1. **Interfaccia utente**, rivolta agli utenti umani.
    
2. **Interfaccia programmativa**, rivolta ai programmi applicativi.

Questa distinzione è cruciale perché i **meccanismi di interazione**, i **livelli di protezione** e gli **obiettivi** sono profondamente diversi.

---
### **3. Interfaccia utente: la Shell**
#### **3.1 Definizione**

L’interfaccia utente è realizzata tramite un **interprete dei comandi**, detto **Shell**.

La Shell è un programma che funge da intermediario tra l’utente e il sistema operativo.

Il suo comportamento fondamentale è ciclico:

- resta in attesa di un comando,
    
- acquisisce il comando digitato dall’utente,
    
- lo analizza e lo verifica,
    
- ne attiva l’esecuzione,
    
- torna in attesa di un nuovo comando.

---
#### **3.2 Fasi di funzionamento della Shell**

Il funzionamento dell’interprete dei comandi può essere descritto attraverso tre fasi principali:

1. **Fetch (acquisizione)**
    
    La Shell rimane in **attesa infinita** che l’utente digiti una sequenza di caratteri tramite tastiera.
    
    L’acquisizione termina **solo quando l'utente segnala la fine del comando**, tipicamente premendo il tasto *Return* (carriage return).
    
2. **Decode (decodifica)**
    
    Il comando viene analizzato per essere certi che sia un **comando ammissibile** per il sistema:
    
    - si verifica che il nome simbolico del comando **esista**,
        
    - si controlla la correttezza sintattica,
        
    - si validano i parametri forniti.
    
3. **Execute (esecuzione)**
    
    Se il comando è ammissibile, la Shell ne avvia l’esecuzione creando un **processo ausiliario** che esegue il comando richiesto.

Al termine dell’esecuzione del processo ausiliario, il **controllo ritorna alla Shell**, che si rimette in attesa ciclica di un nuovo comando dall'utente.

Questo schema è concettualmente **molto simile al ciclo fetch–decode–execute della macchina di Von Neumann**, applicato però ai comandi dell’utente anziché alle istruzioni macchina.

---
#### **3.3 Esempio: comando ls in Unix**

In un sistema operativo Unix, la visualizzazione del contenuto di una directory avviene tramite il comando:

```
ls
```

Se l’utente desidera visualizzare anche i dettagli dei file, utilizza il parametro -l:

```
ls -l
```

La Shell:

- verifica l’esistenza del comando ls,
    
- crea un processo che esegue il programma associato,
    
- passa il parametro -l,
    
- attende il termine del processo,
    
- riprende il controllo.

---
### **4. Modalità di interazione con l’utente**

L’interazione utente–sistema operativo può avvenire in due modalità principali.

---
#### **4.1 Interfaccia testuale (CLI)**

Nell’interfaccia testuale:

- l’utente digita manualmente il comando,
    
- specifica i parametri,
    
- termina l’input con un simbolo di fine comando.

Questo approccio è potente e flessibile, ma presenta svantaggi:

- alta probabilità di errori di digitazione,
    
- errori sintattici,
    
- comandi non ammissibili o parametri errati.

---
#### **4.2 Interfaccia grafica (GUI)**

L’interfaccia grafica nasce per **ridurre gli errori e semplificare l’interazione**.

Le operazioni vengono eseguite tramite:

- icone,
    
- menu,
    
- finestre,
    
- selezioni visuali.

In questo modello:

- l’utente seleziona il comando cliccando su un’icona,
    
- i parametri vengono scelti da menu predefiniti,
    
- la digitazione è limitata ai soli dati indispensabili.

Se una risorsa è già presente nel sistema, la selezione visuale sostituisce completamente la digitazione, riducendo drasticamente la probabilità di errore.

---
### **5. Interfaccia programmativa**

#### **5.1 Definizione**

L’interfaccia programmativa è l’interfaccia attraverso cui i **programmi applicativi richiedono servizi al sistema operativo**.

Queste richieste prendono il nome di:

- **chiamate di sistema** (*system call*),
    
- **chiamate supervisore** (*supervisor call*),
    
- **chiamate monitor** (*monitor call*).

---
#### **5.2 Obiettivi dell’interfaccia programmativa**

Le chiamate di sistema hanno obiettivi fondamentali:

- proteggere l’integrità del sistema operativo,
    
- impedire accessi diretti non controllati alle risorse,
    
- garantire l’esecuzione completa delle procedure,
    
- assicurare che tutti i controlli di sicurezza vengano eseguiti.

Una chiamata di sistema **assomiglia a una chiamata di procedura**, ma **non può essere una normale chiamata di procedura**, perché deve offrire garanzie aggiuntive.

---
### **6. Perché non basta una chiamata di procedura**

#### **6.1. Lo scenario in memoria centrale**

Immaginiamo la memoria centrale del nostro calcolatore con:

- il **programma applicativo** caricato in una sua zona, che desidera chiamare una procedura di sistema;
- il **sistema operativo** caricato nella **zona di memoria centrale a lui riservata**;
- la **funzione F** del SO che vogliamo invocare, posta a un **certo indirizzo** all'interno della zona occupata dal sistema operativo.

#### **6.2. Il problema dei controlli bypassabili**

Supponiamo che la funzione di sistema F sia composta da:

- una prima parte di **controllo** (verifica dell'ammissibilità della chiamata da parte di quel particolare processo / utente),
    
- una seconda parte di **esecuzione effettiva** del comando desiderato.

Se un programma applicativo potesse chiamare direttamente F come una normale chiamata di procedura, potrebbe capitare che un **"programmatore sveglio"**:

- individui l'**indirizzo** dove si trova la procedura F in memoria centrale,
- **bypassi la parte di controllo** saltando direttamente all'indirizzo della fase di esecuzione,
- attivi così la procedura di sistema **anche nei casi in cui ciò non sia lecito**.

Questo renderebbe impossibile garantire la sicurezza del sistema: serve dunque un **meccanismo diverso** per supportare la chiamata di sistema ed evitare situazioni di questo tipo — un meccanismo che **forzi l'accesso e lo controlli**.

---
### **7. Il meccanismo della trap (interruzione software)**

#### **7.1 Concetto generale**

Per evitare accessi diretti alle funzioni di sistema, si utilizza un meccanismo basato su una **interruzione attivata via software**, detta **trap**.

La trap:

- non chiama direttamente la funzione di sistema,
    
- genera un’interruzione gestita dal sistema operativo,
    
- forza il passaggio di controllo al sistema.

Dal punto di vista del processore, **è come se fosse arrivata un'interruzione da una periferica**: in realtà è il sistema stesso che attiva al suo interno l'interruzione. A tale interruzione il SO **associa una risposta specifica**, predisposta proprio per gestire le chiamate di sistema.

---
#### **7.2 Funzionamento dettagliato**

Il meccanismo avviene nel seguente modo:

1. Il programma applicativo carica nei **registri del processore**:
    
    - le **informazioni che identificano** la procedura di sistema desiderata (es. un numero identificativo);
        
    - i **parametri** necessari all'esecuzione della procedura.

2. Il programma esegue un'**istruzione di trap** (chiamata dell'interruzione generata da software).
    
3. Il processore attiva un'**interruzione software**, esattamente come gestirebbe un interrupt hardware da periferica.
    
4. Il sistema operativo intercetta l'interruzione e attiva la **routine di risposta alla trap** — il vero e proprio **"interprete dei comandi" delle system call** (l'analogo, lato programmi, della Shell lato utenti).
    
5. Questa routine:
    
    - consulta una **tabella interna del SO** in cui sono elencate **tutte le funzioni di sistema** e i loro **indirizzi** di memorizzazione nello spazio riservato al sistema;
        
    - individua la funzione richiesta dal numero identificativo;
        
    - ne verifica l'ammissibilità;
        
    - **invoca la funzione di sistema F**.
    
6. Sequenza di ritorno (tre livelli):
    
    - la funzione F termina e restituisce il controllo alla **routine della trap** (l'interprete delle system call);
        
    - la routine della trap termina e restituisce il controllo all'**istruzione di trap** del programma;
        
    - il **programma applicativo** riprende l'esecuzione dall'istruzione successiva alla trap.

---
### **8. Vantaggio fondamentale del disaccoppiamento**

Il punto chiave è che:

- **non è il programma applicativo a chiamare la funzione di sistema**,
    
- **è il sistema operativo stesso a chiamarla**, in risposta alla trap.

Questo disaccoppiamento garantisce che il programma applicativo possa **solo "dire il nome"** della funzione desiderata, e **mai accedere direttamente alle istruzioni** che la implementano, se non per il tramite obbligato dell'**interprete dei comandi della trap** previsto dal sistema operativo.

Le conseguenze pratiche sono:

- impedisce l'accesso diretto alle istruzioni di sistema (nessun salto a indirizzi interni del SO);
    
- garantisce l'esecuzione di **tutti i controlli** previsti dalla funzione F (non sono bypassabili);
    
- protegge l'**integrità** del sistema operativo.

#### **8.1 Simmetria con la Shell**

Vale la pena notare la **simmetria architetturale** tra le due interfacce:

$$
\begin{cases}
\textbf{Shell:}~ & \text{interprete dei comandi degli utenti, esegue Fetch-Decode-Execute sui comandi testuali/grafici.} \\\\
\textbf{Routine di trap:}~ & \text{interprete dei comandi dei programmi (system call),} \\\\
& \text{esegue verifica e dispatch tramite la tabella delle funzioni di sistema.}
\end{cases}
$$

---
### **9. Riepilogo finale**

In questa lezione abbiamo analizzato:

- l’**interfaccia utente**, realizzata tramite la Shell,
    
- l’interazione **testuale** e **grafica**,
    
- l’**interfaccia programmativa**,
    
- il concetto di **chiamata di sistema**,
    
- il meccanismo di protezione basato sulla **trap**.

Le interfacce rappresentano il **punto di contatto controllato** tra utenti, programmi e sistema operativo, e sono uno dei pilastri della sicurezza e dell’affidabilità di un sistema moderno.