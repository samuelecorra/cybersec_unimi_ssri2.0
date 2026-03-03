
# **Lezione 1: Introduzione al livello Data Link**

### **1. Perché servono gli standard**

Le reti di calcolatori esistono solo grazie all’esistenza di **standard comuni**: regole condivise che permettono a dispositivi e software di produttori diversi di comunicare senza problemi.  
Senza standard, ogni azienda userebbe protocolli proprietari, creando “isole digitali” incapaci di interagire.

I vantaggi degli standard sono chiari:

- **interoperabilità** tra dispositivi di produttori diversi;
    
- **mercato più ampio** per hardware e software;
    
- **riduzione dei costi** grazie alla compatibilità universale.
    

Gli svantaggi, invece, sono principalmente due:

- una volta approvati, gli standard tendono a **congelare la tecnologia**, rallentando l’innovazione;
    
- possono nascere **più standard per la stessa funzione**, causando confusione (come accadde con Ethernet, Token Ring, FDDI, ecc.).
    

---

### **2. Principali organizzazioni di standardizzazione**

Gli standard di rete non vengono imposti da un singolo ente, ma sono il risultato del lavoro di più organizzazioni internazionali:

- **IETF (Internet Engineering Task Force)** → parte della Internet Society, responsabile dei protocolli Internet (TCP/IP, HTTP, DNS, ecc.);
    
- **ISO (International Organization for Standardization)** → definisce il modello **OSI** e altri standard internazionali;
    
- **ITU-T (International Telecommunication Union – Telecommunication Standardization Sector)**, erede del **CCITT**, che si occupa delle telecomunicazioni;
    
- **ATM Forum**, focalizzato sugli standard per le reti **Asynchronous Transfer Mode**.
    

---

### **3. Il livello Data Link (Livello 2 del modello OSI)**

Il **livello data link** (livello 2 del modello ISO/OSI) è responsabile della **trasmissione affidabile dei dati tra due nodi adiacenti** in una rete fisica.  

![](imgs/Pasted%20image%2020251128064923.png)

È suddiviso in due sottolivelli:

1. **LLC (Logical Link Control)** – gestisce la logica di comunicazione e la sincronizzazione tra mittente e destinatario;
    
2. **MAC (Medium Access Control)** – controlla l’accesso al mezzo trasmissivo condiviso (cavo, fibra, aria, ecc.).
    

Il livello data link si occupa quindi di:

- **multiplexing dei flussi di dati** (più comunicazioni sullo stesso canale);
    
- **rilevazione dei frame** (delimitazione delle unità di trasmissione);
    
- **controllo dell’accesso al mezzo** (chi trasmette e quando);
    
- **controllo degli errori** (rilevazione e, in certi casi, correzione).
    

Garantisce così **connessioni affidabili** sia **punto-punto** (tra due nodi) sia **punto-multipunto** (tra più stazioni sulla stessa rete).

---

### **4. Protocolli MAC “collision free”**

Quando più dispositivi condividono un canale, il problema principale è **decidere chi può trasmettere e quando**.  
Se due dispositivi trasmettono insieme, si crea una **collisione** e i dati vengono persi.  
I protocolli **collision free** sono progettati proprio per evitare che ciò accada.

#### **a) Allocazione statica dei canali**

In questi schemi, il canale è diviso rigidamente tra gli utenti, come abbiamo già visto nella Lezione sul multiplexing:

- **TDMA (Time Division Multiple Access)** → ogni stazione trasmette a turno, in slot temporali preassegnati;
    
- **FDMA (Frequency Division Multiple Access)** → ogni stazione usa una porzione distinta di frequenze;
    
- **CDMA (Code Division Multiple Access)** → tutte le stazioni trasmettono contemporaneamente ma con **codici diversi**, così da distinguere i segnali sovrapposti.
    

#### **b) Allocazione dinamica “collision free”**

Qui non c’è divisione fissa del canale, ma una **prenotazione coordinata**:

- **Protocollo bitmap (round robin hand-rising)** → ogni stazione segnala la volontà di trasmettere a turno, seguendo un ordine circolare;
    
- **Protocollo binario count-down** → tutte le stazioni chiedono di trasmettere simultaneamente, ma il bit più significativo determina chi vince la “gara”.
    

Questi metodi sono efficienti sotto carico elevato, ma richiedono più coordinamento e complessità nella gestione.

---

### **5. Protocolli MAC a contesa**

Nei protocolli a **contesa**, le stazioni trasmettono **quando ne hanno bisogno**, senza prenotazione preventiva.  
Ciò riduce la complessità ma introduce la possibilità di **collisioni**.

#### **a) ALOHA e Slotted ALOHA**

Il protocollo **ALOHA**, sviluppato all’Università delle Hawaii, è il più semplice: ogni stazione trasmette liberamente e, se non riceve conferma (ACK), ritenta dopo un **tempo casuale (random)**.

Lo **Slotted ALOHA** migliora l’efficienza sincronizzando le trasmissioni in “slot” temporali, riducendo la probabilità di sovrapposizione.

#### **b) CSMA/CD (Carrier Sense Multiple Access / Collision Detection)**

Usato da **Ethernet**, è un’evoluzione dei protocolli a contesa.  
Ogni stazione **ascolta il canale prima di trasmettere**:

- se il canale è libero, trasmette;
    
- se è occupato, attende o ritenta più tardi.
    

Quando si verifica una collisione, viene **rilevata** e il frame è ritrasmesso dopo un tempo random (backoff).

Varianti principali:

- **1-persistent:** la stazione trasmette subito appena il canale è libero;
    
- **p-persistent:** trasmette con probabilità $p$ quando il canale è libero;
    
- **non-persistent:** attende un intervallo casuale prima di ritentare, riducendo ulteriormente il rischio di collisioni.
    

---

### **6. Quando usare i protocolli MAC**

La scelta del protocollo dipende dal **carico di rete**:

| Condizione di carico | Protocollo più efficiente             | Motivazione                                |
| -------------------- | ------------------------------------- | ------------------------------------------ |
| **Basso carico**     | **ALOHA / CSMA/CD**                   | Minimo overhead, semplicità e flessibilità |
| **Alto carico**      | **Collision-Free (TDMA, FDMA, CDMA)** | Maggiore efficienza e prevedibilità        |

In sintesi:

- quando pochi utenti trasmettono sporadicamente → meglio protocolli a contesa;
    
- quando molti utenti devono trasmettere costantemente → meglio protocolli con controllo centralizzato o allocazione fissa.
    

---

### **7. Sintesi concettuale**

- Gli **standard di rete** garantiscono interoperabilità e compatibilità globale.
    
- Il **livello data link** gestisce l’affidabilità e il controllo d’accesso al mezzo.
    
- I protocolli **MAC collision free** (TDMA, FDMA, CDMA) evitano sovrapposizioni, mentre i protocolli **a contesa** (ALOHA, CSMA/CD) accettano collisioni ma sono più flessibili.
    
- L’**efficienza** del protocollo dipende dal **carico** e dalla **topologia** della rete.