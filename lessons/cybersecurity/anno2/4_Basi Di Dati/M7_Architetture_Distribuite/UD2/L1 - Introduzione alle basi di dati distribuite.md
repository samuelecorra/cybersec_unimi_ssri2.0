# **M7 UD2 Lezione 1 - Introduzione alle basi di dati distribuite**

### **1. Definizione generale**

Una **base di dati distribuita** è un sistema nel quale **almeno un client interagisce con più server** per l’esecuzione di una stessa applicazione.  
Ciò significa che i dati e le operazioni non risiedono in un unico punto, ma sono **ripartiti su più nodi della rete**, ciascuno dotato di proprie risorse e autonomia operativa.

L’obiettivo principale è **coordinare più basi di dati locali** in modo che l’utente o il programma applicativo possa interagire con esse **come se si trattasse di un unico sistema integrato**.

---

### **2. Vantaggi delle basi di dati distribuite**

Le basi di dati distribuite offrono numerosi vantaggi che ne giustificano la diffusione nei sistemi informativi moderni:

- **Adeguatezza ai requisiti applicativi**  
    Le **organizzazioni complesse** sono spesso distribuite a livello geografico o funzionale.  
    Di conseguenza, anche i dati devono essere **collocati dove vengono generati e utilizzati**, rendendo naturale una distribuzione su più nodi.
    
- **Flessibilità e modularità**  
    Le architetture distribuite consentono **aggiunte, rimozioni e modifiche progressive** dei componenti senza dover ricostruire l’intero sistema.  
    Questo le rende particolarmente adatte a contesti in continua evoluzione.
    
- **Affidabilità e tolleranza ai guasti**  
    In caso di malfunzionamento di un nodo o di un collegamento, il sistema può continuare a funzionare con **prestazioni ridotte**, evitando **blocchi completi** dell’intero servizio.  
    La ridondanza e la replica dei dati permettono di mantenere la disponibilità anche in presenza di guasti.

---

### **3. Classificazione delle basi di dati distribuite**

Le basi di dati distribuite possono essere classificate in base a due criteri principali: **il tipo di DBMS** e **il tipo di rete**.

#### **a) In base al tipo di DBMS**

- **DDB omogenea**  
    Tutti i server utilizzano **lo stesso DBMS** e quindi condividono la stessa struttura e gli stessi linguaggi di accesso ai dati.  
    Ciò semplifica l’integrazione e la gestione complessiva.
    
- **DDB eterogenea**  
    I server utilizzano **DBMS diversi**, con differenze nei linguaggi, nei formati o nei protocolli di comunicazione.  
    Questo tipo di distribuzione richiede meccanismi aggiuntivi di **integrazione e traduzione** tra i sistemi.

#### **b) In base al tipo di rete**

- **LAN (Local Area Network)**  
    Reti locali che collegano i server in un’area geografica ristretta (es. sede aziendale).  
    Tipiche applicazioni: **sistemi gestionali e finanziari interni**.
    
- **WAN (Wide Area Network)**  
    Reti geograficamente estese che collegano server in sedi diverse o in nazioni differenti.  
    Tipiche applicazioni: **sistemi di prenotazione integrati**, **reti interbancarie**, **sistemi finanziari globali**.

#### **c) Esempio di combinazioni**

|Tipo di DBMS|Tipo di rete|Esempi di applicazione|
|---|---|---|
|Omogeneo|LAN|Applicazioni gestionali e finanziarie locali|
|Omogeneo|WAN|Sistemi di prenotazione e applicazioni finanziarie distribuite|
|Eterogeneo|LAN|Applicazioni gestionali interfunzionali|
|Eterogeneo|WAN|Sistemi di prenotazione integrati, sistemi interbancari|

---

### **4. Autonomia locale e cooperazione**

Una base di dati distribuita può essere vista, dal punto di vista logico, **come un’unica base di dati integrata**, anche se fisicamente è suddivisa tra più server.

Tuttavia, la progettazione deve tenere conto del **bilanciamento tra autonomia locale e cooperazione**:

- ogni server deve poter **funzionare in modo autonomo** per le operazioni locali;
    
- al tempo stesso, il sistema deve garantire la **collaborazione efficiente** tra nodi per eseguire operazioni che coinvolgono più sedi.

Per ridurre i costi e migliorare le prestazioni, è buona pratica **progettare le applicazioni** in modo che **vengano eseguite preferibilmente su un singolo server**, limitando:

- le necessità di **interazione tra sistemi**;
    
- il **trasferimento di grandi volumi di dati** tra i nodi della rete.

---

### **5. Sintesi finale**

In questa lezione abbiamo introdotto:

- il concetto di **base di dati distribuita**;
    
- i suoi **vantaggi principali** (flessibilità, affidabilità, adattabilità);
    
- la **classificazione** in base al tipo di DBMS e alla rete;
    
- la necessità di un **equilibrio tra autonomia locale e cooperazione** per garantire efficienza e coerenza del sistema.

In sintesi, una base di dati distribuita rappresenta una soluzione efficace per le organizzazioni moderne, permettendo di **distribuire le informazioni dove servono**, mantenendo al contempo **un controllo coordinato e coerente** a livello globale.

---


![](imgs/Pasted%20image%2020251125052802.png)

