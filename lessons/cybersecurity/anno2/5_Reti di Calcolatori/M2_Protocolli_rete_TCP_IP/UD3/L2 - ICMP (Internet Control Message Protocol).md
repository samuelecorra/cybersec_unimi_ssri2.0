# **Lezione 2: ICMP (Internet Control Message Protocol)**

---

### **1. Introduzione generale**

Quando un **router** o un **host di destinazione** deve segnalare al mittente un **errore di rete** o un **evento particolare** avvenuto durante l’inoltro di un pacchetto IP, utilizza il **protocollo ICMP (Internet Control Message Protocol)**.

ICMP è dunque un **protocollo di servizio interno a IP**, progettato per fornire:

- messaggi di errore;
    
- segnalazioni di controllo;
    
- e strumenti di diagnostica.
    

> Gli avvisi ICMP **non vengono mai generati** per pacchetti ICMP stessi, per evitare **cicli infiniti di messaggi**.

---

### **2. Ruolo di ICMP nel modello TCP/IP**

Anche se ICMP è tecnicamente un protocollo separato, **fa parte integrante di IP**.  
Chi implementa il protocollo IP **deve supportare anche ICMP**.

#### **Nel pacchetto IP**

Quando un pacchetto IP trasporta un messaggio ICMP:

- il campo **Protocol** dell’header IP è impostato a **1**;
    
- il campo **TOS (Type of Service)** è impostato a **0**.
    

Il campo **Type** dell’header ICMP specifica il **tipo di messaggio** trasportato (errore, echo, redirect, ecc.).

---

### **3. Funzioni principali di ICMP**

ICMP è utile sia **ai router** sia **agli host**, con ruoli diversi:

#### **Per i router**

- Scambiare **informazioni di servizio** (_router-to-router_);
    
- Controllare e ottimizzare i flussi di traffico, rallentando o reindirizzando pacchetti (messaggi _router-to-host_);
    
- Segnalare condizioni di errore nella rete.
    

#### **Per gli host**

- Scambiarsi informazioni sullo **stato della rete** (_host-to-host_);
    
- Ottenere **informazioni di routing** da altri router;
    
- Rilevare la **disponibilità e i tempi di risposta** di un nodo remoto (come fa il comando `ping`).
    

---

### **4. Caratteristiche operative di ICMP**

- I messaggi ICMP **hanno priorità di instradamento più alta** rispetto ai pacchetti IP ordinari.
    
- Nei pacchetti IP frammentati, i messaggi ICMP si riferiscono **solo al frammento 0** (il primo).
    
- ICMP **non risponde mai** a:
    
    - pacchetti IP con indirizzi speciali (es. `0.0.0.0`, `127.0.0.1`, broadcast);
        
    - altri messaggi di **errore ICMP**.
        
- Tuttavia, può rispondere a **messaggi di interrogazione ICMP** (come “echo request”).
    

---

### **5. Tipi di messaggi ICMP**

![](imgs/Pasted%20image%2020260225005514.png)

| **N°** | **Mittente → Destinatario** | **Significato / Descrizione**                                         |
| ------ | --------------------------- | --------------------------------------------------------------------- |
| **0**  | Host → Host                 | **Echo Reply** – Risposta a una richiesta di eco                      |
| **3**  | Router → Host               | **Destination Unreachable** – Destinazione irraggiungibile            |
| **4**  | Router → Host               | **Source Quench** – Il mittente deve rallentare l’invio dei pacchetti |
| **5**  | Router → Host               | **Redirect** – Il pacchetto va inoltrato verso un altro gateway       |
| **8**  | Host → Host                 | **Echo Request** – Richiesta di eco (usata da `ping`)                 |
| **9**  | Router → Router/Host        | **Router Advertisement** – Annuncio di nuove rotte                    |
| **10** | Router → Router/Host        | **Router Solicitation** – Richiesta di rotte                          |
| **11** | Router → Host               | **Time Exceeded** – Pacchetto eliminato perché il TTL è scaduto       |
| **12** | Router → Host               | **Parameter Problem** – Errore in un parametro dell’header IP         |
| **13** | Host → Router               | **Timestamp Request** – Richiesta di misurazione tempo di transito    |
| **14** | Router → Host               | **Timestamp Reply** – Risposta con tempo di transito                  |
| **17** | Router ↔ Host               | **Address Mask Request** – Richiesta di maschera di sottorete         |
| **18** | Router ↔ Host               | **Address Mask Reply** – Risposta con maschera di sottorete           |

---

### **6. ICMP Redirect**

Un router può usare un messaggio **ICMP Redirect** per informare un host che un determinato pacchetto IP dovrebbe essere inviato **a un altro router**, più appropriato per quella destinazione.

![](imgs/Pasted%20image%2020260225005553.png)

#### **Funzionamento**

1. L’host invia un pacchetto IP al proprio gateway.
    
2. Il router riceve il pacchetto, ma riconosce che esiste un altro router sulla stessa rete con una **rotta migliore**.
    
3. Il router inoltra il pacchetto al router corretto e invia un **messaggio ICMP Redirect** all’host mittente.
    
4. L’host aggiorna la propria tabella di routing, utilizzando da quel momento il **router corretto** per quella destinazione.
    

---

### **7. Il comando PING**

Il comando **PING (Packet INternet Groper)** sfrutta proprio **ICMP** per testare la **raggiungibilità IP** di un host remoto.

#### **Meccanismo**

- Il client invia un messaggio **Echo Request** (ICMP tipo 8).
    
- Il destinatario, se raggiungibile, risponde con **Echo Reply** (ICMP tipo 0).
    
- Se il pacchetto non riceve risposta, significa che:
    
    - l’host non è raggiungibile;
        
    - oppure **blocca i pacchetti ICMP** per motivi di sicurezza (caso comune nei firewall moderni).
        

#### **Misura dei tempi**

PING misura anche il **RTT (Round Trip Time)**, cioè il **tempo totale** di andata e ritorno del pacchetto.

$$  
RTT = t_{ritorno} - t_{invio}  
$$

#### **Collegamento con TRACERT**

PING può impostare il valore di **TTL (Time To Live)** nell’header IP.  
Usando più ping con **TTL progressivi**, si può determinare **il percorso dei router attraversati** — principio alla base del comando `TRACERT`.

---

### **8. Limiti dell’instradamento statico**

L’instradamento statico, anche se semplice, presenta diversi svantaggi:

1. Le **tabelle d’instradamento devono essere configurate manualmente** da un amministratore.
    
2. Se l’inter-rete è grande e composta da molte sottoreti, la gestione diventa **onerosa** e soggetta a errori.
    
3. Ogni modifica topologica (aggiunta di nuove reti o router) **richiede aggiornamenti manuali** su tutte le tabelle coinvolte.
    
4. Questa modalità è **insostenibile su larga scala**, come su Internet, dove le rotte cambiano continuamente.
    

---

### **9. Introduzione all’instradamento dinamico**

Per superare questi limiti si utilizzano i **router dinamici**, dispositivi in grado di **costruire e aggiornare automaticamente le proprie tabelle** usando protocolli dedicati, come:

- **RIP (Routing Information Protocol)**
    
- **OSPF (Open Shortest Path First)**
    

Un host collegato a una rete con un **gateway dinamico** beneficia automaticamente del routing dinamico, anche senza configurazioni speciali.

> Se invece il gateway predefinito è statico, può comunque instradare pacchetti verso un router dinamico remoto, purché sia configurata una rotta statica verso quest’ultimo.

---

### **10. Conclusione**

Il protocollo **ICMP** è uno strumento fondamentale per la **gestione, il controllo e la diagnostica delle reti IP**.  
Permette a host e router di:

- segnalare errori e condizioni particolari;
    
- ottimizzare il traffico;
    
- verificare la connettività;
    
- e supportare protocolli di routing più evoluti.
    

Allo stesso tempo, la lezione evidenzia i limiti dell’instradamento statico e introduce la necessità di **sistemi dinamici** in grado di adattarsi automaticamente ai cambiamenti della rete.