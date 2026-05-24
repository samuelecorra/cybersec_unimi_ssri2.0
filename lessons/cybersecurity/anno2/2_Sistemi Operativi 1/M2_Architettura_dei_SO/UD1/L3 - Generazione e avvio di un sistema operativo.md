# **M2 UD1 Lezione 3 - Generazione e avvio di un sistema operativo**

---

### **1. Introduzione**

Ogni sistema operativo nasce da un processo complesso che ne definisce **la configurazione, la generazione e l’avviamento**.  
Questa lezione descrive come un sistema operativo viene:

1. **progettato e personalizzato** per un determinato ambiente di elaborazione;
    
2. **installato e generato** come codice eseguibile;
    
3. **avviato automaticamente** al momento dell’accensione della macchina (bootstrap).

Più precisamente, due termini chiave da distinguere fin da subito:

$$
\begin{cases}
\textbf{Generare il SO:}~ & \text{trovare la configurazione del sistema e andarla ad applicare,} \\\\
& \text{producendo un eseguibile del SO adattato a quella specifica installazione.} \\\\
\textbf{Avviare il SO:}~ & \text{caricarlo in memoria centrale all'accensione del sistema} \\\\
& \text{e fargli prendere il controllo dell'architettura di elaborazione.}
\end{cases}
$$

Comprendere queste fasi è fondamentale per capire **come un computer passa dallo stato “spento” alla piena operatività**.

---
### **2. Generazione del sistema operativo**

La **generazione** è la fase in cui si adatta il sistema operativo a un particolare ambiente hardware e applicativo.  
Non si tratta solo di installarlo, ma di **costruire una versione del sistema ottimizzata** per quella specifica macchina e per il tipo di carichi di lavoro che dovrà gestire.

L'obiettivo è duplice:

- definire i **parametri ottimali** del SO per **gestire in modo efficiente le risorse**;
- garantire una **equa ripartizione** dell'uso delle risorse tra le varie **tipologie di utenti** che operano sul sistema.

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

##### **Costruzione dello scenario di carico**

Mettere insieme tutte le informazioni raccolte sulle abitudini d'uso e sul funzionamento degli applicativi consente di costruire uno **scenario** (o **modello**) che descrive il **carico di richieste** per le singole risorse — fisiche o informative — del sistema. Questo modello permette poi di valutare non solo le caratteristiche dell'hardware, ma anche come il **sistema operativo deve rispondere** nel modo più efficiente possibile alle richieste reali degli utenti e delle applicazioni.

##### **Modalità di raccolta**

La raccolta di queste informazioni può avvenire in tre modi:

- **manuale**, tramite analisi diretta dei requisiti;
    
- **automatica simulata**, monitorando il comportamento degli utenti in un **ambiente di test simulato**;
    
- **automatica reale**, ponendosi nell'ambiente reale — tipicamente con la **versione corrente del sistema operativo** — e osservando come gli utenti effettivamente lavorano, collezionando informazioni sull'uso delle varie risorse.

##### **Valutazione**

La valutazione delle caratteristiche dello scenario operativo può essere effettuata in base all'**esperienza** dell'amministratore o su **basi statistiche** derivate dai dati raccolti.

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

Possono infine essere individuate **regole** (di esperienza o automatiche) che permettono di **guidare il computo dell'insieme dei parametri migliori** per il sistema, automatizzando ulteriormente la scelta.

---
#### **2.3. Applicazione dei parametri e generazione dell’eseguibile**

Una volta scelti i parametri, essi vengono applicati al sistema operativo tramite **modifica dei file di configurazione** in cui si memorizzano i nuovi valori, e successivamente attivando le procedure che generano il nuovo codice eseguibile. A seconda dell'entità delle modifiche è possibile:

- rigenerare **solo i moduli affetti** dalla modifica dei parametri;
- oppure rigenerare il **codice complessivo dell'intero sistema operativo** e di tutti i **programmi di sistema** ad esso associati.

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
Infine, il SO viene **caricato in memoria centrale** e reso operativo, **cedendo il controllo** dell'architettura al sistema operativo con i **nuovi parametri applicati**.

$$  
\begin{cases}  
\text{Memorizzazione della nuova versione;} \\\\  
\text{Aggiornamento dei file di sistema;} \\\\  
\text{Caricamento del kernel in memoria;} \\\\
\text{Cessione del controllo al nuovo SO configurato.}
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

I metodi di avviamento si distinguono per **numero di passi** e **grado di complessità**, e questa scelta dipende dal diverso modo di memorizzare tutto o parte del SO nei vari componenti di memoria. La scelta del metodo permette di ottenere **diversi gradi di modificabilità** del SO o, all'opposto, **diversi gradi di efficienza** nell'accesso alle funzioni di sistema in fase di esecuzione e all'avvio.

##### **Esempi di trade-off**

- Nei **sistemi embedded** è utile avere un **rapido accesso alle funzioni** del SO, perché si vuole una **forte capacità di risposta in tempi brevi** alle richieste dell'ambiente e dell'utente.
- Nei **sistemi interattivi** (personal computer, mainframe) diventa invece utile avere la possibilità di **modificare il sistema operativo** per aggiungere nuove funzionalità in base all'evolvere delle esigenze degli utenti e ai mutati carichi di lavoro — riconfigurando i parametri.

---
### **4. Metodi di avviamento**

#### **4.1. Avviamento in singolo passo**

##### **Architettura della memoria centrale**

Nel modello a singolo passo, la memoria centrale è suddivisa in due porzioni fisicamente distinte:

- una porzione di **RAM** (*Random Access Memory*), in cui è possibile effettuare le usuali operazioni di **lettura e scrittura**;
- una porzione di **ROM** (*Read-Only Memory*), una **memoria a sola lettura** che **conserva i valori memorizzati anche a spegnimento del sistema**.

Alla riaccensione, la ROM continua quindi a contenere sempre gli stessi valori — l'**ideale per memorizzare il sistema operativo**.

##### **Meccanismo di avvio**

L'architettura hardware del processore viene configurata in modo tale da caricare nel **Program Counter** il **primo indirizzo del SO** (uno degli indirizzi posti nella ROM), da cui parte direttamente l'esecuzione del sistema operativo.

In senso stretto, non c'è quindi **"nessun caricamento"** in fase di avvio: il SO è **già presente in memoria centrale**, e i tempi di accesso alle sue funzioni sono i normali tempi di accesso alla memoria centrale.

$$  
\begin{cases}  
\textbf{SO in ROM:}~ & \text{tutto il sistema operativo risiede nella memoria ROM.} \\\\  
\textbf{Bootstrap primario:}~ & \text{la CPU esegue direttamente il kernel.} \\\\  
\textbf{Vantaggi:}~ & \text{caricamento rapidissimo e alta affidabilità.} \\\\  
\textbf{Svantaggi:}~ & \text{il sistema non è modificabile o aggiornabile.}  
\end{cases}  
$$

##### **Limite: modificabilità**

Il problema principale è la totale **assenza di modificabilità** del SO: per aggiornarlo è necessario **sostituire fisicamente il dispositivo ROM** in cui il sistema è memorizzato, cosa non sempre fattibile né facilmente realizzabile.

Usato nei sistemi **embedded** o nei dispositivi **dedicati**, dove la stabilità è più importante della flessibilità.

---
#### **4.2. Avviamento in due passi**

##### **Motivazione**

L'avviamento in due passi è pensato per **ridurre la complessità dell'aggiornamento** del SO e permettere una **modificabilità più semplice**: invece di memorizzare in modo fisso l'intero SO in ROM, si mantiene in ROM **soltanto un caricatore**, mentre il SO vero e proprio risiede su una memoria di massa modificabile.

##### **Architettura**

- La memoria centrale mantiene la suddivisione RAM + ROM.
- Nella **ROM** non viene più memorizzato tutto il SO, ma **soltanto il caricatore** (*loader*): quella porzione di SO che viene attivata all'accensione.
- Il SO vero e proprio è memorizzato in una **posizione predeterminata** su un dispositivo di memoria di massa facilmente modificabile, ad esempio su un disco magnetico — tipicamente alla locazione fissa **disco 0, traccia 0, settore 0**.

##### **Sequenza di avvio (bootstrap primario + secondario)**

1. All'accensione la CPU esegue il **caricatore in ROM** (*bootstrap primario*), che effettua una **minima inizializzazione** dell'architettura hardware (processore e periferiche essenziali).
2. Il caricatore accede al **disco rigido** in posizione predeterminata e legge da lì il **sistema operativo**, portandolo in RAM (*bootstrap secondario*).
3. Una volta completato il caricamento, il caricatore **cede il controllo del calcolatore** al SO presente in RAM, che diventa abilitato a gestire l'architettura.

$$  
\begin{cases}  
\textbf{Primo passo:}~ & \text{il bootstrap primario (in ROM) inizializza l'HW e legge il SO da disco.} \\\\  
\textbf{Secondo passo:}~ & \text{il SO caricato in RAM prende il controllo della macchina.}  
\end{cases}  
$$

##### **Modificabilità**

Il **caricatore non è modificabile** (sta in ROM), ma il **SO è modificabile**: basta cambiare il contenuto di quella porzione di disco su cui è memorizzato per vedere caricata, alla riattivazione successiva, una nuova versione del SO.

È il metodo più diffuso nei PC e server moderni (BIOS → bootloader → kernel).

---

#### **4.3. Avviamento in tre passi**

##### **Motivazione: superare il limite del singolo settore**

L'avviamento in più di due passi nasce per **superare il limite** imposto dal modello precedente: nel caso a due passi, il SO doveva stare in **un solo settore** del disco (quello indicato dal caricatore in ROM), il che ne **limita la dimensione** e lo costringe in una posizione fisica specifica.

##### **Architettura a tre livelli di caricatori**

- In **ROM** è presente un **caricatore di base** molto piccolo, che mantiene il riferimento a una **porzione fissa del disco** (sempre in posizione nota, ad esempio disco 0, traccia 0, settore 0).
- Quella porzione del disco non contiene più il SO, bensì il **caricatore vero e proprio**, completo e di dimensioni maggiori.
- Il **caricatore complesso** conosce le **posizioni delle varie porzioni** del SO sul disco (che possono essere distribuite su settori diversi).

##### **Sequenza di avvio**

1. **Primo passo**: il **caricatore di base** in ROM viene eseguito, individua sul disco la porzione fissa che contiene il caricatore complesso e la **trasferisce in una porzione di RAM**.
2. **Secondo passo**: il **caricatore complesso** (ora in RAM) localizza le varie porzioni del SO sul disco e le carica in memoria centrale.
3. **Terzo passo**: una volta completato il caricamento del SO, il **caricatore in RAM può essere abbandonato**; in memoria centrale rimane il **sistema operativo vero e proprio**, che assume il controllo del calcolatore.

$$  
\begin{cases}  
\textbf{1° passo:}~ & \text{caricatore di base (ROM) → carica il caricatore complesso in RAM.} \\\\  
\textbf{2° passo:}~ & \text{caricatore complesso (RAM) → localizza e carica il SO da più settori.} \\\\  
\textbf{3° passo:}~ & \text{il SO prende il controllo; il caricatore complesso viene abbandonato.}  
\end{cases}  
$$

Questo modello consente di superare il vincolo dimensionale e di gestire **aggiornamenti e moduli dinamici**, ma aumenta **la complessità e il tempo di avvio**.

---

#### **4.4. Avviamento in passi multipli (caricamento on-demand)**

È un'estensione del modello a tre passi in cui **non tutto il SO viene caricato in un colpo solo** all'attivazione del sistema, ma i **singoli moduli vengono caricati solo quando richiesti** dagli ambienti applicativi che li necessitano per eseguire le specifiche funzioni invocate.

##### **Trade-off**

Il sistema diventa massimamente **flessibile e modificabile**, ma con conseguenze precise:

- l'**avvio** del SO è più **lento e complesso**;
- l'**accessibilità** alle funzioni è **buona** per i moduli già caricati in memoria centrale, ma **ridotta** per quelli ancora da caricare on-demand dalla memoria di massa (con i relativi tempi elettromeccanici di accesso).

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

In termini simbolici, è il passaggio da *materia a logica*:  
dalla macchina fisica alla macchina pensante.