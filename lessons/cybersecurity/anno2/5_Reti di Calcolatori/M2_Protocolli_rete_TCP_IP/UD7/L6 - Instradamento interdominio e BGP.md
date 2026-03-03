# **Lezione 6: Instradamento interdominio e BGP**

---

### **1. Introduzione**

Finora abbiamo studiato i protocolli di **instradamento interno (IGP)**, come RIP e OSPF, che gestiscono la comunicazione **all’interno di un dominio amministrativo**.  
Quando però la rete cresce e collega **più domini autonomi (AS, Autonomous Systems)**, serve un protocollo capace di **scambiare informazioni di instradamento tra sistemi diversi**.

> Questi protocolli sono detti **EGP – Exterior Gateway Protocols**, e operano su scala “Internet”.

---

### **2. Exterior Gateway Protocol (EGP)**

Il **vecchio protocollo EGP** (oggi sostituito da BGP) era progettato per consentire ai gateway principali di **scambiarsi informazioni di raggiungibilità** tra sistemi autonomi.

#### **Funzionamento di base**

1. Un router EGP **stabilisce un dialogo** con un altro gateway tramite messaggi **HELLO** e **I-HEARD-YOU**, per confermare la connessione.
    
2. Invia una **richiesta di informazioni di instradamento (poll)**.
    
3. Riceve un **pacchetto di aggiornamento (update)** con la lista delle reti raggiungibili.
    

#### **Limiti**

- EGP **non sceglie il percorso migliore**: si limita a dire _quali reti sono raggiungibili_, senza calcolare metriche.
    
- Si assume che i **gateway principali** conoscano già i cammini ottimali per l’esterno.
    

> In sostanza, EGP era più un sistema di segnalazione che un vero protocollo di routing intelligente.

---

### **3. Border Gateway Protocol (BGP)**

Per superare i limiti di EGP nasce il **BGP (Border Gateway Protocol)**, che oggi è il **protocollo standard dell’Internet globale**.

#### **Caratteristiche principali**

- Supporta **l’instradamento basato su politiche**, non solo su metriche.
    
- Le decisioni di instradamento possono dipendere da:
    
    - **accordi bilaterali** tra provider,
        
    - **vincoli organizzativi o politici**,
        
    - **criteri di sicurezza o protezione del traffico**.
        

> BGP non sceglie sempre il percorso più corto, ma quello che **rispetta le politiche di rete definite dagli amministratori**.

---

### **4. Politiche di instradamento**

Le politiche non fanno parte del protocollo stesso, ma vengono definite **dall’amministratore** e fornite a BGP come **informazioni di configurazione**.

BGP usa tali regole per:

- **controllare quali rotte annunciare** agli altri sistemi autonomi;
    
- **controllare quali rotte accettare** dagli altri.
    

> In questo modo, ogni organizzazione mantiene il controllo sul traffico che entra o esce dal proprio dominio.

---

### **5. Architettura e funzionamento**

- BGP è **implementato su TCP**, **porta 179**.
    
- Ogni router BGP stabilisce una **connessione TCP stabile** con i peer esterni (detti _BGP neighbors_).
    
- Le informazioni scambiate sono **sequenze ordinate di AS** attraversati per raggiungere una destinazione.
    

#### **Approccio Path Vector**

BGP utilizza un meccanismo detto **Path Vector**:

- Ogni rotta è accompagnata dalla **lista degli Autonomous Systems attraversati**.
    
- In questo modo si **elimina il rischio di cicli** e il problema del **count-to-infinity** tipico dei protocolli Distance Vector.
    

> Grazie al Path Vector, BGP sa sempre da dove proviene una rotta e può evitare di reinstradarla nello stesso AS.

---

### **6. Dove si usa BGP**

La maggior parte dei router di rete **non esegue protocolli interdominio**, poiché opera solo all’interno di un AS (usando RIP o OSPF).  
BGP viene invece utilizzato **solo nei router di backbone**, ovvero quelli che collegano:

- un **Autonomous System (AS)** a un altro,
    
- oppure un **provider ISP** a un altro provider.
    

> In breve, BGP è la “lingua comune” che permette ai diversi sistemi autonomi di cooperare su scala globale.

---

### **7. Scelta del protocollo di instradamento**

|**Dimensione della rete**|**Protocollo consigliato**|
|---|---|
|Reti piccole|**RIP**|
|Reti medio-grandi|**OSPF**|
|Reti interconnesse tra domini|**BGP**|

In generale:

- **RIP** è semplice ma limitato (≤15 hop).
    
- **OSPF** è scalabile e preciso, ideale per reti aziendali.
    
- **BGP** è pensato per **Internet e reti multi-provider**, dove la politica di instradamento è importante quanto la metrica.
    

---

### **8. Gateway Daemon (GATED)**

Molti sistemi Unix includono solo il supporto per **RIP**, ma possono diventare router completi grazie al **Gateway Daemon (GATED)**.

#### **Funzioni principali**

- Unifica **diversi protocolli di instradamento** in un unico pacchetto software.
    
- Permette di eseguire **più protocolli contemporaneamente** (es. RIP + OSPF + BGP).
    
- Le rotte apprese da un protocollo interno possono essere **annunciate tramite un protocollo esterno**.
    
- Le **politiche di instradamento** vengono applicate in modo centralizzato.
    
- Tutta la configurazione è contenuta nel file:
    

```bash
/etc/gated.conf
```

> GATED permette di usare un sistema Unix come router dinamico, trasformandolo in un nodo capace di parlare sia con reti interne che con reti esterne.

---

### **9. Conclusione**

L’instradamento interdominio è la base del funzionamento di Internet.  
Con **BGP**, le reti autonome possono cooperare rispettando **politiche di instradamento**, **accordi commerciali** e **criteri di sicurezza**.

|**Protocollo**|**Livello**|**Funzione principale**|**Applicazione**|
|---|---|---|---|
|**RIP**|IGP (intradominio)|Distance Vector|Reti piccole|
|**OSPF**|IGP (intradominio)|Link State|Reti aziendali|
|**BGP**|EGP (interdominio)|Path Vector|Internet / ISP|

> In sintesi:  
> **RIP** è locale,  
> **OSPF** è interno,  
> **BGP** è globale — il cervello distribuito dell’Internet moderno.


---

![](imgs/Pasted%20image%2020251125073246.png)

