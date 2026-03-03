Dopo aver compreso nel Modulo 1 i concetti generali di comunicazione in rete e i modelli di riferimento (come la pila ISO/OSI e la sua relazione con gli standard IEEE), questo secondo modulo entra nel cuore della **comunicazione su Internet**, cioè l’insieme dei **protocolli TCP/IP**.

Questa famiglia di protocolli costituisce la **spina dorsale del traffico dati mondiale**, ed è progettata per permettere che informazioni eterogenee (file, pagine web, video, email) vengano trasmesse in modo **affidabile, scalabile e interoperabile** tra dispositivi diversi.

Nel corso del modulo vedremo come i protocolli si suddividono in **livelli funzionali**, ciascuno con un ruolo specifico:

- **IP (Internet Protocol)** gestisce l’indirizzamento e l’instradamento dei pacchetti;
    
- **TCP (Transmission Control Protocol)** si occupa del controllo del flusso e dell’affidabilità delle comunicazioni;
    
- **UDP (User Datagram Protocol)** rappresenta un’alternativa più leggera e veloce, senza garanzie di consegna.
    

Impareremo anche a **progettare piani di indirizzamento IP**, comprendendo come dividere una rete in sottoreti e come configurare manualmente le connessioni tra apparati. In seguito, affronteremo le **tecniche dinamiche di interconnessione**, che permettono ai router di aggiornarsi automaticamente tramite protocolli di routing come RIP o OSPF.

Un’attenzione particolare verrà data alle **tendenze evolutive del protocollo IP**, con il passaggio da IPv4 a IPv6, e ai **principi di sicurezza** applicabili ai protocolli TCP/IP — una competenza fondamentale in ambito Cybersecurity.

In sintesi, al termine del modulo dovrai essere in grado di:

- comprendere la logica interna della suite TCP/IP;
    
- configurare e analizzare semplici reti IP;
    
- prevedere il comportamento dei protocolli in condizioni reali (ritardi, perdite, congestioni);
    
- riconoscere le principali vulnerabilità e contromisure legate alla trasmissione dei dati in rete.
    

---

# **Lezione 1: Introduzione al livello di rete**

### **1. Livello di rete e protocolli locali**

I protocolli di rete locale, come **Ethernet**, forniscono un **servizio di recapito di frame** tra schede di rete appartenenti **alla stessa rete locale (LAN)**.  
Ciò significa che Ethernet permette la comunicazione **solo tra dispositivi fisicamente connessi alla stessa infrastruttura**, ma non tra reti diverse.

Per utilizzare questo servizio, il **sistema operativo** di ogni host deve includere un **software di rete** capace di:

- **Ricevere i dati** dalle applicazioni locali;
    
- **Trasmetterli** verso altri dispositivi tramite la scheda di rete.
    

Oggi, questo software è parte integrante di tutti i principali sistemi operativi (Windows, Linux, macOS, Unix, ecc.).

---

### **2. Software di rete e modello ISO/OSI**

Dal punto di vista **del sistemista**, il software di rete è un **servizio permanente** del sistema operativo (in ambiente Unix viene chiamato _daemon_).  
Dal punto di vista **del programmatore**, invece, è una **libreria di funzioni** che consente di inviare e ricevere dati attraverso la rete.

Questo software si occupa **di tutte le funzioni dei livelli 3-6** dello stack **ISO/OSI**, ossia:

![](imgs/Pasted%20image%2020260213035314.png)

$$  
\text{Livello 7: Applicazione} \  
\text{Livello 6: Presentazione} \  
\text{Livello 5: Sessione} \  
\text{Livello 4: Trasporto} \  
\text{Livello 3: Rete}  
$$

In pratica, il software di rete si trova **a cavallo** tra i livelli logici e l’interfaccia fisica, fungendo da **ponte** tra le applicazioni e l’hardware della scheda di rete.

---

### **3. Pacchetti e frame**

Quando un’applicazione deve trasmettere un dato, lo **passa al software di rete**, che compie due operazioni fondamentali:

1. **Aggiunge un’intestazione (header)** al dato, contenente informazioni di servizio: indirizzi, numeri di sequenza, controlli, ecc.  
    → Il risultato è un **pacchetto**.
    
2. **Inoltra il pacchetto alla scheda di rete**, che lo incapsula in un **frame Ethernet** (aggiungendo un header e un trailer propri) e lo spedisce sulla rete locale.

![](imgs/Pasted%20image%2020260213035434.png)

In forma schematica:

$$  
\text{Frame Ethernet} =  
\text{Header Ethernet} + \text{Header Protocollo di rete} + \text{Dati} + \text{Trailer Ethernet}  
$$

> 🔹 **Nota Bene:**  
> L’applicazione si limita a generare il flusso di pacchetti, **senza occuparsi del recapito dei frame**.  
> La responsabilità della consegna spetta ai livelli sottostanti (rete e collegamento dati).

---

### **4. Modalità di connessione**

Le reti possono operare in **due modalità principali di trasmissione**:

#### **a. Reliable Flow (flusso affidabile)**

- Il **mittente** verifica che il destinatario sia disponibile, inviandogli uno o più **pacchetti di controllo** composti solo dall’header (senza dati).
    
- Il **destinatario** conferma la ricezione di ogni pacchetto (anche di quelli con dati) inviando un **acknowledgement**.
    

Questa modalità garantisce **affidabilità e controllo del flusso**, ma introduce **latenza** e **overhead**.

#### **b. Best Effort (miglior sforzo)**

- I pacchetti vengono inviati **senza controlli** e **senza conferme**.
    
- Il mittente non sa se il pacchetto è arrivato a destinazione, ma la comunicazione risulta più **rapida** e **leggera**.
    

In sintesi:

|Modalità|Controllo|Conferme|Affidabilità|Velocità|
|---|---|---|---|---|
|Reliable Flow|Sì|Sì|Alta|Minore|
|Best Effort|No|No|Bassa|Maggiore|

---

### **5. Header e protocolli**

Ogni protocollo definisce **un proprio formato standard di header**, cioè l’insieme delle informazioni di servizio che vengono aggiunte ai dati per garantire la comunicazione.

Un **protocollo** specifica:

1. La **struttura dell’header** (campi, lunghezze, significato).
    
2. Le **modalità di comunicazione** (sequenze di scambio, controlli, ritrasmissioni, ecc.).
    

Il **software di rete** implementa un protocollo in uno specifico ambiente operativo (es. Windows o Linux).  
Ogni piattaforma può avere implementazioni diverse dello stesso protocollo, ma tutte devono **rispettarne le regole** per poter comunicare tra loro.

---

### **6. Architettura del software di rete**

La **velocità di generazione dei pacchetti** da parte delle applicazioni e la **velocità di trasmissione dei frame** da parte della scheda di rete **possono differire** notevolmente.

Esempi:

- In una **chat**, la generazione dei pacchetti dipende dalla velocità dell’utente, quindi è **lenta** rispetto alla capacità della scheda.
    
- In un **server web**, invece, i pacchetti vengono generati **molto più rapidamente** di quanto la scheda riesca a trasmettere.
    

Per gestire questa asincronia, il software di rete **non usa chiamate di funzione dirette**, ma un **meccanismo bufferizzato** di comunicazione tra processi:

$$  
\text{Applicazione} \xleftrightarrow[]{\text{Buffer}} \text{Software di rete} \xleftrightarrow[]{\text{Buffer}} \text{Driver di scheda}  
$$

![](imgs/Pasted%20image%2020260213035918.png)

Sono processi indipendenti asincroni sincronizzati mediante dei buffer gestiti con politica LIFO (dunque code).

Regole operative:

- **Buffer pieno →** sospendi il **produttore** (es. applicazione).
    
- **Buffer vuoto →** sospendi il **consumatore** (es. scheda di rete).
    

Questo schema garantisce **sincronizzazione e stabilità** anche in condizioni di carico elevato.

---

### **7. Tipi di header: fisso vs. concatenato**

I protocolli possono gestire gli header in due modi principali:

#### **a. Header fisso**

- Tutti i pacchetti hanno lo **stesso formato di header**.
    
- Il software di rete deve solo riempire i campi con i valori corretti.
    
- È un approccio **semplice ma rigido**: per modificare la struttura dell’header occorre **aggiornare il software**.
    

#### **b. Header chain (catena di header)**

- Esiste un **repertorio di header** ammessi, che possono essere **concatenati** in base alle esigenze.
    
- Il software di rete sceglie, per ogni pacchetto, **quali header aggiungere** (ad esempio, per cifratura o compressione).
    
- È un approccio **più complesso ma estensibile**, che permette di **aggiungere nuove funzionalità** senza modificare i protocolli preesistenti.
    

Esempio schematico:

$$  
\text{Header IP} \rightarrow \text{Header TCP} \rightarrow \text{Header SSL/TLS} \rightarrow \text{Dati}  
$$

---

### **8. Conclusione**

Questa lezione introduce i **principi fondamentali del livello di rete**:  
come i dati vengono incapsulati, trasmessi e gestiti dal software di rete attraverso protocolli strutturati.

Abbiamo visto che il flusso di comunicazione si basa su un sistema stratificato, in cui ogni livello aggiunge le proprie informazioni di controllo, e che la distinzione tra **reliable** e **best-effort** è alla base delle differenze tra protocolli come **TCP** e **UDP**, che approfondiremo nelle lezioni successive.