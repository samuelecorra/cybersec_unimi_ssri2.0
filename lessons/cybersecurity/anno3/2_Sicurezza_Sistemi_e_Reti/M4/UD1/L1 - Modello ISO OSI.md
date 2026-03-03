Questo modulo introduce lo studio dei **protocolli di rete TCP/IP** con particolare attenzione agli aspetti di **sicurezza del livello di trasporto**.  
Dopo un rapido riepilogo del **modello ISO/OSI** e della sua corrispondenza con la suite TCP/IP, verranno analizzate le **principali vulnerabilità** dei protocolli di rete, le **tecniche di scansione** utilizzate per individuare host e servizi attivi, e le **contromisure difensive** per mitigare tali minacce.  
L’obiettivo è comprendere a fondo **come funziona TCP**, quali sono i **punti deboli della sua implementazione** e in che modo questi possono essere **sfruttati o protetti** in ambito di cybersecurity.


---

# **UD1 – TCP/IP**

Questa unità didattica introduce le **basi teoriche della comunicazione in rete**, illustrando il **modello ISO/OSI** e la sua relazione con la **suite di protocolli TCP/IP**, oggi alla base di Internet.  
Verranno descritte le **funzioni principali dei livelli di rete**, le modalità con cui i dati vengono **incapsulati, trasmessi e indirizzati**, e le differenze operative tra il modello **teorico OSI** e la sua **implementazione pratica** nei protocolli TCP/IP.  
L’obiettivo è comprendere come le informazioni viaggiano in una rete e come i protocolli cooperano per garantire **affidabilità, ordinamento e controllo della comunicazione**.


---

## **Lezione 1: Modello ISO/OSI**

### **1. Comunicazione host-to-host**

In una rete di comunicazione, i dati vengono trasmessi tra due **host** attraverso una serie di passaggi strutturati.  
Un’applicazione che invia messaggi deve:

1. **Accettare** il messaggio generato dall’applicazione.
    
2. **Dividerlo in pacchetti** di dimensioni più piccole.
    
3. **Trasmettere** i pacchetti nella rete di accesso.
    
4. **Instradare** i pacchetti da un router al successivo fino alla **destinazione finale**.
    

Ogni router lungo il percorso riceve il pacchetto, legge l’**indirizzo di destinazione** nell’header, e lo inoltra in base alla propria **tabella di instradamento**.

---

### **2. Operazioni fondamentali in una rete**

Le principali operazioni che avvengono all’interno di una rete sono:

- **Packet sending and delivery** → invio e consegna dei pacchetti.
    
- **Packet routing** → determinazione del percorso dei pacchetti attraverso i nodi intermedi.
    
- **Address discovery** → individuazione degli indirizzi dei dispositivi connessi alla rete.
    

Ogni pacchetto contiene nel proprio **header** l’indirizzo di destinazione, che viene confrontato con la **tabella di instradamento** locale per decidere su quale **link di uscita** inoltrarlo.

---

### **3. Protocolli di comunicazione**

Un **protocollo** è un insieme formale di **regole e convenzioni** che governano lo scambio di messaggi tra due o più entità comunicanti.

#### **Definizione**

> Un protocollo definisce il **formato** e l’**ordine dei messaggi** scambiati tra entità comunicanti, nonché le **azioni intraprese** alla trasmissione o ricezione di tali messaggi.

In altri termini, il protocollo stabilisce **come** i computer comunicano, **quali messaggi** inviano e **come reagiscono** agli eventi di rete.

Esempi di protocolli:

- **HTTP** (HyperText Transfer Protocol) per il web.
    
- **FTP** (File Transfer Protocol) per il trasferimento di file.
    
- **SMTP** (Simple Mail Transfer Protocol) per l’invio di e-mail.
    

---

### **4. Architettura a livelli (Layering)**

Per gestire la complessità delle comunicazioni di rete, le funzioni sono organizzate in **livelli (layers)**, ciascuno dei quali svolge un compito specifico.

#### **Vantaggi della stratificazione**

- La struttura esplicita consente di **identificare e isolare** le diverse funzioni del sistema.
    
- La **modularità** facilita manutenzione e aggiornamento.
    
- Il cambiamento di un livello (es. accesso fisico) non influisce sugli altri.
    

Ogni livello:

- riceve dati dal livello superiore,
    
- aggiunge le proprie **informazioni di controllo (header)**,
    
- e li passa al livello inferiore (**data encapsulation**).
    

---

### **5. Architettura a livelli: incapsulamento dei dati**

Il processo di **incapsulamento** prevede che, man mano che i dati passano attraverso i vari livelli del modello di rete, ciascun livello aggiunga un **header** con le proprie informazioni di gestione.

|**Livello**|**Unità di dati**|**Aggiunta**|
|---|---|---|
|Applicazione|Messaggio|—|
|Trasporto|Segmento|Header TCP/UDP|
|Rete|Pacchetto|Header IP|
|Collegamento dati|Frame|Header + Trailer Ethernet|
|Fisico|Bit|Trasmissione su mezzo fisico|

---

### **6. Stack dei protocolli di rete**

Lo **stack di rete** (network stack) organizza i protocolli secondo i diversi livelli funzionali:

```
Application Layer
Transport Layer
Network Layer
Link Layer
```

Ogni livello ha protocolli dedicati:

- **Applicazione:** HTTP, SMTP, FTP
    
- **Trasporto:** TCP, UDP
    
- **Rete:** IP, ARP, ICMP
    
- **Collegamento:** Ethernet, Wi-Fi (IEEE 802.3 / 802.11)
    

---

### **7. Modello ISO/OSI vs. TCP/IP**

Il modello **ISO/OSI** e il modello **TCP/IP** condividono la stessa logica a livelli, ma differiscono nel numero e nell’aggregazione delle funzioni.

|**Modello ISO/OSI**|**Modello TCP/IP**|
|---|---|
|Applicazione|Applicazione|
|Presentazione|— (inclusa nell’Applicazione)|
|Sessione|— (inclusa nell’Applicazione)|
|Trasporto|Trasporto|
|Rete|Internet|
|Collegamento dati|Accesso di rete|
|Fisico|— (incluso nel livello di accesso)|

Il modello TCP/IP è una **semplificazione pratica** del modello OSI, ottimizzata per l’implementazione reale nelle reti Internet.

---

### **8. Dettaglio dei livelli TCP/IP**

|**Livello**|**Nome**|**Funzione principale**|**Protocolli tipici**|
|---|---|---|---|
|**4**|Application Layer|Fornisce servizi agli utenti e applicazioni.|HTTP, FTP, SMTP, POP|
|**3**|Transport Layer|Gestisce la comunicazione host-to-host e la segmentazione dei dati.|TCP, UDP|
|**2**|Internet Layer|Si occupa dell’instradamento e dell’indirizzamento dei pacchetti.|IP, ARP, RARP, ICMP|
|**1**|Network Access Layer|Gestisce la trasmissione fisica dei dati sulla rete.|Ethernet (802.3), Wi-Fi (802.11b/g)|

---

### **9. Formati dei dati nei diversi livelli**

Durante il passaggio attraverso i livelli, il formato dei dati cambia:

|**Livello**|**Unità di informazione**|**Esempio di struttura**|
|---|---|---|
|Applicazione|Messaggio|Dati utente|
|Trasporto|Segmento|`TCP header + data`|
|Rete|Pacchetto|`IP header + TCP segment`|
|Collegamento|Frame|`Ethernet header + IP packet + trailer`|

Ogni livello incapsula quello superiore, formando una **catena di header** che viene rimossa progressivamente al ricevimento.

---

### **10. Principio end-to-end**

Il **principio end-to-end** stabilisce che le funzionalità specifiche dell’applicazione devono risiedere **nei nodi finali** della comunicazione, piuttosto che nei nodi intermedi (router, gateway).

#### **Motivazioni**

- Migliora la **flessibilità e l’affidabilità** della rete.
    
- I nodi intermedi devono solo **instradare i pacchetti**, non interpretarli.
    
- Garantisce che un’applicazione fallisca **solo se fallisce l’end-point**.
    

Questo principio è alla base del concetto di **neutralità della rete (net neutrality)**, secondo cui i dati devono essere trattati in modo **neutro**, senza discriminazioni da parte dei provider.

---

### **11. Sintesi finale**

Il **modello ISO/OSI** fornisce una visione teorica stratificata della comunicazione di rete, mentre il **modello TCP/IP** rappresenta la sua **realizzazione concreta**.  
L’organizzazione a livelli consente:

- **modularità**,
    
- **indipendenza tra protocolli**,
    
- **scalabilità** e **interoperabilità** tra dispositivi.
    

> In sintesi: la stratificazione, l’incapsulamento e il principio end-to-end costituiscono i **fondamenti dell’architettura di Internet**, e sono la base di tutti i protocolli di rete moderni.


---