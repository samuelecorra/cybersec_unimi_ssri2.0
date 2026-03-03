# **M2 UD1 Lezione 3 - Generazione e avvio di un sistema operativo**

### **1. Introduzione**

Ogni sistema operativo nasce da un processo complesso che ne definisce **la configurazione, la generazione e l’avviamento**.  
Questa lezione descrive come un sistema operativo viene:

1. **progettato e personalizzato** per un determinato ambiente di elaborazione;
    
2. **installato e generato** come codice eseguibile;
    
3. **avviato automaticamente** al momento dell’accensione della macchina (bootstrap).

Comprendere queste fasi è fondamentale per capire **come un computer passa dallo stato “spento” alla piena operatività**.

---
### **2. Generazione del sistema operativo**

La **generazione** è la fase in cui si adatta il sistema operativo a un particolare ambiente hardware e applicativo.  
Non si tratta solo di installarlo, ma di **costruire una versione del sistema ottimizzata** per quella specifica macchina e per il tipo di carichi di lavoro che dovrà gestire.

---
#### **2.1. Identificazione delle caratteristiche dell’ambiente operativo**

Il primo passo consiste nel raccogliere informazioni sulle **applicazioni**, sugli **utenti** e sulle **risorse disponibili**.

$$  
\begin{cases}  
\text{Analisi delle applicazioni:}~ & \text{quali programmi saranno eseguiti, con che frequenza e priorità.} \\\\  
\text{Analisi dell’ambiente operativo:}~ & \text{numero di utenti, dispositivi, rete, sicurezza richiesta.} \\\\  
\text{Valutazione del carico di lavoro:}~ & \text{studio statistico delle richieste di CPU, memoria e I/O.}  
\end{cases}  
$$

La raccolta di queste informazioni può avvenire in tre modi:

- **manuale**, tramite analisi diretta dei requisiti;
    
- **automatica simulata**, in un ambiente operativo di test;
    
- **automatica reale**, monitorando un sistema già in funzione.

---
#### **2.2. Definizione dei parametri**

Sulla base dei dati raccolti, si definiscono i **parametri di configurazione** del sistema operativo.  
Questo passaggio può essere manuale o automatizzato.

$$  
\begin{cases}  
\text{Manuale (esperienza):}~ & \text{parametri scelti dall’amministratore in base alle conoscenze.} \\\\  
\text{Manuale (statistico):}~ & \text{basato su analisi dei carichi di lavoro.} \\\\  
\text{Automatica (casi predefiniti):}~ & \text{selezione di profili standard.} \\\\  
\text{Automatica (regole esperte):}~ & \text{sistema esperto che imposta i parametri ottimali.}  
\end{cases}  
$$

Esempi di parametri: numero massimo di processi, dimensione delle code, politica di scheduling, tipo di file system, moduli caricabili.

---
#### **2.3. Applicazione dei parametri e generazione dell’eseguibile**

Una volta scelti i parametri, essi vengono applicati al sistema operativo tramite **modifica dei file di configurazione** e **rigenerazione dei moduli affetti** dalle modifiche.

$$  
\begin{cases}  
\text{Modifica dei file di configurazione;} \\\\  
\text{Compilazione dei moduli aggiornati;} \\\\  
\text{Collegamento (linking) del codice eseguibile finale;} \\\\  
\text{Creazione dei programmi di sistema associati.}  
\end{cases}  
$$

Il risultato è un **eseguibile del sistema operativo** personalizzato, pronto per essere caricato in memoria e utilizzato.

---
#### **2.4. Aggiornamento del sistema**

Dopo la generazione, il nuovo sistema operativo e i programmi di sistema vengono **memorizzati permanentemente** nel sistema di elaborazione.  
Infine, il SO viene **caricato in memoria centrale** e reso operativo.

$$  
\begin{cases}  
\text{Memorizzazione della nuova versione;} \\\\  
\text{Aggiornamento dei file di sistema;} \\\\  
\text{Caricamento del kernel in memoria.}  
\end{cases}  
$$

---
### **3. Avviamento del sistema operativo (Bootstrap)**

Quando accendiamo un computer, non c’è ancora nessun sistema operativo in esecuzione.  
Serve quindi un meccanismo che **carichi il SO in memoria e lo avvii automaticamente**: questo meccanismo è detto **bootstrap**.

---
#### **3.1. Principio del bootstrap**

Il bootstrap è una sequenza di istruzioni che:

1. risiede in una memoria non volatile (ROM o EEPROM, Electrically Erasable Programmable Read-Only Memory);
    
2. viene eseguita automaticamente dalla CPU all’accensione;
    
3. si occupa di **caricare il sistema operativo** nella memoria principale e **trasferirgli il controllo**.

Esistono diversi metodi di bootstrap, che si distinguono per **numero di passi** e **grado di complessità**.

---
### **4. Metodi di avviamento**

#### **4.1. Avviamento in singolo passo**

$$  
\begin{cases}  
\textbf{SO in ROM:}~ & \text{tutto il sistema operativo risiede nella memoria ROM.} \\\\  
\textbf{Bootstrap primario:}~ & \text{la CPU esegue direttamente il kernel.} \\\\  
\textbf{Vantaggi:}~ & \text{caricamento rapidissimo e alta affidabilità.} \\\\  
\textbf{Svantaggi:}~ & \text{il sistema non è modificabile o aggiornabile.}  
\end{cases}  
$$

Usato nei sistemi **embedded** o nei dispositivi **dedicati**, dove la stabilità è più importante della flessibilità.

---
#### **4.2. Avviamento in due passi**

Diviso in **bootstrap primario** e **bootstrap secondario**.

$$  
\begin{cases}  
\textbf{Primo passo:}~ & \text{il bootstrap primario (in ROM) carica un piccolo programma detto caricatore.} \\\\  
\textbf{Secondo passo:}~ & \text{il caricatore (in RAM) carica il sistema operativo da memoria di massa.}  
\end{cases}  
$$

Il sistema operativo è quindi memorizzato su disco in una posizione **fissa e nota a priori**.  
È il metodo più diffuso nei PC e server moderni (BIOS → bootloader → kernel).

---
#### **4.3. Avviamento in tre passi**

In questo caso l’avvio è più articolato e consente maggiore **modularità**:

$$  
\begin{cases}  
\textbf{1° passo:}~ & \text{caricatore elementare (in ROM) → avvia il caricatore complesso.} \\\\  
\textbf{2° passo:}~ & \text{caricatore complesso (in memoria) → localizza e carica il SO.} \\\\  
\textbf{3° passo:}~ & \text{caricamento del sistema operativo e dei moduli aggiuntivi (su richiesta).}  
\end{cases}  
$$

Questo modello consente **aggiornamenti e moduli dinamici**, ma aumenta **la complessità e il tempo di avvio**.

---
#### **4.4. Avviamento in passi multipli**

È un’estensione del modello a tre passi, dove **moduli o driver** vengono caricati **in fasi successive o on demand**.  
Il sistema è più **flessibile e modificabile**, ma:

- l’avvio è più **lento**,
    
- e l’**accesso iniziale alle funzioni** può essere limitato.

---
### **5. Sintesi finale**

$$  
\begin{cases}  
\textbf{Generazione:}~ & \text{identificazione, configurazione e compilazione del SO.} \\\\  
\textbf{Aggiornamento:}~ & \text{installazione e caricamento del nuovo sistema.} \\\\  
\textbf{Avviamento:}~ & \text{caricamento automatico tramite bootstrap.} \\\\  
\textbf{Modalità:}~ & \text{1 passo (ROM), 2 passi (loader + SO), 3+ passi (modulare).}  
\end{cases}  
$$

---
### **6. Conclusione**

Il processo di **generazione e avvio** di un sistema operativo è ciò che trasforma un insieme di circuiti elettronici in una **macchina funzionante**.  
Dal primo impulso elettrico del **bootstrap primario**, fino al caricamento completo del kernel, il sistema operativo prende il controllo e organizza l’hardware in un ambiente coerente, pronto a eseguire i processi dell’utente.

In termini simbolici, è il passaggio da _materia a logica_:  
dalla macchina fisica alla macchina pensante.