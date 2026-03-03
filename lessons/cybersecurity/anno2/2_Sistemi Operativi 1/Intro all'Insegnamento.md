Ogni computer moderno, dal laptop a un server in cloud, è basato su una **collaborazione costante tra hardware e software**.  
L’hardware fornisce le **risorse fisiche** (processore, memoria, dispositivi di input/output), mentre il **sistema operativo** — o **SO** — agisce come un **mediatore intelligente** che controlla, gestisce e coordina l’uso di tali risorse da parte dei programmi applicativi.

In altre parole, il sistema operativo è un **software di controllo** che:

- astrarre le complessità dell’hardware, fornendo un’interfaccia semplice e uniforme agli utenti e ai programmatori;
    
- assicura che più programmi possano coesistere, eseguendosi in modo ordinato e sicuro;
    
- garantisce l’uso efficiente e corretto delle risorse del sistema.

L’obiettivo generale di questo corso è comprendere **come un sistema operativo trasforma un insieme di circuiti elettronici in una macchina virtuale programmabile**.  
Studieremo i meccanismi fondamentali che rendono tutto ciò possibile: processi, schedulazione, concorrenza, sincronizzazione, comunicazione e gestione del tempo reale.

---
### 1. Architettura generale di un sistema operativo

Un sistema operativo è un **insieme strutturato di componenti software** che collaborano per gestire le risorse fisiche e logiche del calcolatore.  
Le sue funzioni principali possono essere organizzate in quattro grandi aree:

1. **Gestione del processore (CPU management):** controllo dell’esecuzione dei processi, pianificazione (schedulazione) e sincronizzazione.
    
2. **Gestione della memoria (memory management):** allocazione e protezione delle aree di memoria (questa parte è approfondita in _Sistemi Operativi 2_).
    
3. **Gestione dei dispositivi (I/O management):** comunicazione con periferiche, driver, buffer, interrupt.
    
4. **Gestione del file system:** organizzazione e accesso ai dati persistenti (anch’essa parte del corso _Sistemi Operativi 2_).

In _Sistemi Operativi 1_ ci concentreremo **sulla virtualizzazione del processore**, ossia su come il sistema operativo permette a più programmi di condividere la CPU **come se ciascuno avesse un processore tutto per sé**.

---
### 2. Meccanismi e politiche

Nel progettare un sistema operativo, si distingue sempre tra:

- **Meccanismo:** _come_ una funzione viene realizzata.  
    Esempio: la modalità con cui si blocca un processo o si acquisisce un semaforo.
    
- **Politica:** _cosa_ si decide di fare in una certa situazione.  
    Esempio: quale processo far eseguire per primo, quale priorità assegnare.

Questa distinzione è fondamentale perché consente di **modificare le strategie di gestione** (le politiche) senza dover riscrivere i meccanismi interni.  
È un principio chiave di progettazione dei sistemi flessibili e modulari.

---
### 3. Virtualizzazione del processore

Uno degli obiettivi fondamentali del sistema operativo è **virtualizzare il processore**.  
Ciò significa far sembrare che ogni programma disponga di una CPU dedicata, anche se nella realtà più programmi condividono la stessa unità di calcolo.

Per ottenere questa illusione, il sistema operativo:

- suddivide il tempo della CPU in **intervalli (time slice)**;
    
- assegna ogni intervallo a un **processo** in esecuzione;
    
- decide **quando sospendere e riprendere** ciascun processo secondo politiche di **schedulazione**;
    
- garantisce che i processi **non interferiscano** tra loro, proteggendo memoria e risorse.

Questa gestione si basa su **strutture dati e algoritmi complessi** che analizzeremo nel dettaglio: code dei processi, semafori, mutex, priorità, thread, e così via.

---
### 4. Concorrenza e sincronizzazione

Quando più processi o thread vengono eseguiti simultaneamente, nascono problemi di **concorrenza**: accessi contemporanei alle stesse risorse, modifiche incoerenti, race condition.

Il sistema operativo deve quindi **coordinare l’esecuzione dei processi concorrenti** tramite:

- **meccanismi di sincronizzazione**, come semafori, monitor, barriere;
    
- **primitive di comunicazione**, come messaggi o memoria condivisa.

Questi strumenti permettono di evitare condizioni di errore come il **deadlock**, dove più processi si bloccano a vicenda aspettando risorse che non arriveranno mai, o la **starvation**, in cui un processo resta indefinitamente in attesa.

---
### 5. Tempo reale e tolleranza ai guasti

In alcuni sistemi — per esempio nei controlli industriali, aeronautici o medici — le operazioni devono rispettare **vincoli temporali precisi**.  
Il sistema operativo in questi casi è detto **real-time** e deve garantire che certe azioni vengano completate entro un tempo massimo noto.

Allo stesso modo, in sistemi critici è essenziale la **tolleranza ai guasti**, cioè la capacità di:

- rilevare errori,
    
- isolare componenti difettosi,
    
- ripristinare il corretto funzionamento senza compromettere l’intero sistema.

---
### 6. Obiettivi del corso

Alla fine di _Sistemi Operativi 1_, dovrai essere in grado di:

1. Descrivere le **architetture e funzioni principali** di un sistema operativo.
    
2. Comprendere **come un SO gestisce processi e thread** in esecuzione concorrente.
    
3. Analizzare e confrontare **politiche di schedulazione** differenti.
    
4. Spiegare i **meccanismi di sincronizzazione** e rilevare situazioni di deadlock o starvation.
    
5. Comprendere le basi della **programmazione concorrente e del tempo reale**.

---
### 7. Struttura del corso e modalità d’esame

- Il corso ha un valore di **6 CFU** e costituisce la prima metà dell’insegnamento complessivo di _Sistemi Operativi (I + II)_.
    
- L’esame è **scritto** e prevede **3 domande aperte**, di natura teorica, da svolgere in **1 ora e 45 minuti**.
    
- Il materiale di riferimento principale è il testo di **Silberschatz, Galvin e Gagne**, affiancato da **Tanenbaum** per la parte concettuale.

---
### 8. Filosofia di studio

Per comprendere davvero i sistemi operativi, non basta imparare le definizioni: occorre **capire il “perché” dietro ogni meccanismo**.  
Un buon approccio — quello che seguiremo in tutte le lezioni — è chiedersi continuamente:

> “Se fossi io a dover costruire un sistema operativo da zero, come farei a garantire che tutto funzioni correttamente e in modo efficiente?”

Con questo spirito, il corso diventerà una sorta di **laboratorio mentale di progettazione di sistemi**, in cui ogni lezione aggiungerà un nuovo strato di astrazione sopra l’hardware.

---
### 9. Schema del percorso didattico

|Lezione|Argomento principale|Tema centrale|
|---|---|---|
|1|Introduzione ai sistemi operativi|Architetture e funzioni fondamentali|
|2|Processi e stati|Struttura logica dell’esecuzione|
|3|Schedulazione|Gestione e pianificazione della CPU|
|4|Thread|Parallelismo leggero e vantaggi|
|5|Sincronizzazione|Coordinamento di processi concorrenti|
|6|Deadlock e starvation|Rilevazione, prevenzione, gestione|
|7|Comunicazione tra processi|IPC e cooperazione|
|8|Tempo reale e tolleranza ai guasti|Sistemi critici e affidabilità|

---
### 10. Conclusione

Il sistema operativo è il **cuore invisibile** del computer: lavora costantemente dietro le quinte per rendere tutto il resto possibile.  
Studiare come funziona significa comprendere le **fondamenta dell’informatica moderna** — da Linux e Windows fino ai sistemi embedded, ai server cloud e agli smartphone.

Nel corso di _Sistemi Operativi 1_ impareremo a guardare “sotto il cofano” di un calcolatore e a capire **come l’ordine emerge dal caos** di miliardi di istruzioni che competono per le stesse risorse.
