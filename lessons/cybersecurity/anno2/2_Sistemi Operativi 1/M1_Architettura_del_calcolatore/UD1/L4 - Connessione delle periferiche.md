# **M1 UD1 Lezione 4 - Connessione delle periferiche**

### **1. Introduzione**

#### **1.1. Il ruolo delle periferiche nel sistema di elaborazione**

Un calcolatore, per essere utile, deve **interagire con l’esterno**.  
Questa interazione avviene attraverso dispositivi chiamati **periferiche**, che permettono di:

- **inserire dati** (input),
    
- **visualizzare o salvare risultati** (output),
    
- e **scambiare informazioni** con altri sistemi (comunicazione).

Le periferiche comprendono tastiere, schermi, stampanti, dischi, reti, sensori, attuatori, e così via.  
Per farle comunicare con il processore, serve una **connessione logica e fisica** che consenta il trasferimento ordinato dei dati.  
Tale connessione è realizzata tramite un **canale di comunicazione**, intermediato da una **interfaccia di controllo**.

---
### **2. Canale di comunicazione**

#### **2.1. Struttura generale**

Il **canale di comunicazione** è l’insieme dei **collegamenti hardware e protocolli logici** che permettono lo scambio di dati tra la **CPU** e i **dispositivi di I/O**.

Esso comprende:

- le **linee fisiche** (bus dati, bus indirizzi, bus di controllo),
    
- e i **registri di interfaccia** che consentono alla CPU di leggere o scrivere informazioni verso la periferica.

La comunicazione avviene attraverso un dispositivo chiamato **interfaccia** (o controller), che traduce i segnali del processore in comandi comprensibili dalla periferica, e viceversa.

---
### **3. Interfaccia nell’unità centrale**

#### **3.1. Funzione dell’interfaccia**

L’interfaccia di I/O è un componente elettronico che si occupa di:

- ricevere i comandi della CPU e trasmetterli alla periferica;
    
- segnalare lo stato della periferica al processore;
    
- sincronizzare il flusso dei dati;
    
- e gestire eventuali errori di comunicazione.

Può essere **integrata nella scheda madre** o **separata**, come nel caso delle schede di rete, schede video o controller esterni.

---
### **4. Controllo dell’interfaccia**

#### **4.1. Attesa attiva**

La modalità più semplice di controllo è l’**attesa attiva** (_busy waiting_).  
In questo caso, la CPU **interroga continuamente** l’interfaccia per sapere se la periferica è pronta a ricevere o fornire dati.

Il flusso è il seguente:

$$  
\begin{cases}  
\text{1.}~ \text{La CPU invia un comando alla periferica.} \\\\  
\text{2.}~ \text{La CPU controlla ciclicamente un bit di stato dell’interfaccia.} \\\\  
\text{3.}~ \text{Quando il bit segnala che la periferica è pronta, la CPU effettua il trasferimento.} \\\\  
\text{4.}~ \text{Il ciclo si ripete fino al completamento dell’operazione.}  
\end{cases}  
$$

Questo metodo è molto semplice da implementare, ma **inefficiente**: il processore resta bloccato in attesa, sprecando tempo che potrebbe dedicare ad altre elaborazioni.

---
#### **4.2. Controllo tramite interruzioni**

Per migliorare l’efficienza, si adotta un meccanismo basato sulle **interruzioni (interrupt)**.  
In questo caso, la CPU invia un comando alla periferica e poi **prosegue l’esecuzione di altri processi**.  
Quando la periferica è pronta, **invia un segnale di interrupt** al processore, che interrompe temporaneamente ciò che stava facendo per eseguire una **routine di servizio**.

Il comportamento può essere rappresentato così:

$$  
\begin{cases}  
\text{1.}~ \text{La CPU avvia l’operazione di I/O.} \\\\  
\text{2.}~ \text{La CPU continua altre elaborazioni.} \\\\  
\text{3.}~ \text{La periferica genera un interrupt quando l’operazione è completata.} \\\\  
\text{4.}~ \text{La CPU salva il contesto e attiva la routine di gestione dell’interruzione.} \\\\  
\text{5.}~ \text{Terminata la gestione, la CPU riprende l’esecuzione originale.}  
\end{cases}  
$$

Questo metodo consente una **gestione asincrona e parallela delle operazioni di I/O**, liberando il processore dall’attesa attiva.

---
### **5. Trasferimento dei dati**

#### **5.1. Tipologie di trasferimento**

Il trasferimento dei dati tra la CPU e la periferica può avvenire in due modi principali:

$$  
\begin{cases}  
\textbf{A parole}~ & \text{(word-at-a-time): i dati vengono trasferiti uno per volta, sotto il controllo diretto della CPU.} \\\\  
\textbf{A blocchi}~ & \text{(block transfer): i dati vengono trasferiti in gruppi, in modo automatico tramite il DMA.}  
\end{cases}  
$$

---
#### **5.2. Il ruolo del DMA (Direct Memory Access)**

Nel trasferimento a blocchi, interviene un dispositivo chiamato **DMA controller**, che permette a una periferica di leggere o scrivere direttamente in memoria **senza passare dalla CPU**.  
Questo riduce il carico del processore e accelera i trasferimenti di grandi quantità di dati (ad esempio tra disco e memoria).

Il ciclo logico è il seguente:

$$  
\begin{cases}  
\text{1.}~ \text{La CPU programma il controller DMA con l’indirizzo di memoria e la quantità di dati.} \\\\  
\text{2.}~ \text{Il DMA avvia autonomamente il trasferimento.} \\\\  
\text{3.}~ \text{Durante il trasferimento, la CPU può continuare altre attività.} \\\\  
\text{4.}~ \text{A operazione conclusa, il DMA genera un interrupt per segnalare la fine.}  
\end{cases}  
$$

Grazie al DMA, il processore diventa **supervisore** del trasferimento, ma non il suo esecutore diretto.

---
#### **5.3. Periferica mappata in memoria**

In molti sistemi, le periferiche sono **mappate nella memoria** (Memory-Mapped I/O):  
ogni dispositivo ha un **intervallo di indirizzi riservato** nello spazio degli indirizzi della CPU.  
Ciò permette di utilizzare le **stesse istruzioni di lettura e scrittura della memoria** per comunicare con le periferiche.

---
### **6. Gestione del canale di comunicazione**

#### **6.1. Tipologie di controllo**

Il canale di comunicazione può essere gestito in due modi:

$$  
\begin{cases}  
\textbf{Controllo nell’interfaccia dell’unità centrale:}~ & \text{la CPU gestisce direttamente il trasferimento dei dati.} \\\\  
\textbf{Controllo nella periferica:}~ & \text{la periferica dispone di un proprio processore dedicato (controller intelligente).}  
\end{cases}  
$$

Le periferiche moderne (come dischi o schede di rete) includono spesso microprocessori che eseguono autonomamente le proprie operazioni, segnalando solo gli eventi importanti al sistema operativo.

---
### **7. Gestione della periferica**

#### **7.1. Controllo del dispositivo fisico**

Il **sistema operativo** deve coordinare tutte le periferiche per garantire un uso ordinato delle risorse.  
Questo include:

- l’inizializzazione e la configurazione del dispositivo,
    
- la gestione delle code di richieste,
    
- la sincronizzazione degli accessi concorrenti,
    
- la gestione degli errori e delle eccezioni hardware.

Ogni dispositivo è gestito da un **driver**, un modulo software che traduce le chiamate del sistema operativo in comandi comprensibili dall’hardware.

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Connessione CPU-periferiche:}~ & \text{realizzata tramite canale di comunicazione e interfacce dedicate.} \\\\  
\textbf{Controllo dell’interfaccia:}~ & \text{può avvenire per attesa attiva o tramite interrupt.} \\\\ 
\textbf{Trasferimento dati:}~ & \text{a parole (CPU diretta) o a blocchi (DMA).} \\  
\textbf{Gestione del canale:}~ & \text{CPU o periferica possono avere il controllo.} \\\\  
\textbf{Gestione della periferica:}~ & \text{affidata al sistema operativo e ai driver.}  
\end{cases}  
$$

---
### **9. Collegamento con i sistemi operativi**

Il sistema operativo agisce come **intermediario tra hardware e processi**:

- controlla le interfacce di I/O,
    
- gestisce gli interrupt e i DMA,
    
- garantisce la sicurezza e la priorità delle operazioni di input/output.
    

Questo garantisce che le periferiche possano operare in modo **asincrono**, **sicuro** e **indipendente**, pur restando pienamente coordinate con la CPU e la memoria.