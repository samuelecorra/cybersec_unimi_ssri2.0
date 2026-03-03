# **Lezione 3: Da RIP a OSPF**

---

### **1. Rotte attive e rotte passive**

Nel contesto dell’instradamento dinamico, le **rotte** possono essere di due tipi:

- **Rotte attive:**  
    Vengono **aggiornate dinamicamente** dal protocollo (es. RIP).  
    Il router che partecipa attivamente all’instradamento si chiama **gateway attivo**.  
    Se un gateway attivo **smette di inviare aggiornamenti**, viene considerato inattivo e la sua rotta viene **rimossa**.
    
- **Rotte passive:**  
    Sono **rotte statiche permanenti**, non modificate automaticamente dal protocollo.  
    Rimangono nella tabella finché non vengono eliminate manualmente.
    

> In sintesi: le rotte attive “vivono e cambiano” con la rete; le passive “rimangono ferme” e non reagiscono ai guasti o alle variazioni topologiche.

---

### **2. Limiti del protocollo RIP**

RIP, basato su **Distance Vector**, presenta diversi **problemi strutturali**:

1. **Diametro di rete limitato**  
    Il numero massimo di hop è **15**, quindi RIP non è adatto a **reti di grandi dimensioni** o a **interreti complesse**.
    
2. **Convergenza lenta**  
    L’aggiornamento delle tabelle avviene in modo **iterativo (multiround)**, e i cambiamenti si propagano lentamente.
    
3. **Instradamento con classi**  
    RIP utilizza un **approccio classful**, cioè riconosce solo le classi di indirizzi (A, B, C).  
    Non supporta il **CIDR (Classless Inter-Domain Routing)** e quindi non gestisce bene subnetting e supernetting moderni.
    

---

### **3. Miglioramenti introdotti in RIP2**

Per superare alcuni limiti del RIP originale, il protocollo è stato aggiornato alla versione **RIP2**, che introduce:

- **Split Horizon**  
    Un router **non pubblica** su un’interfaccia le rotte apprese da quella stessa interfaccia, evitando cicli.
    
- **Poison Reverse**  
    Invece di tacere, il router **pubblica la rotta con distanza infinita** sul collegamento da cui l’ha ricevuta, così da impedire confusione nei vicini.
    
- **Aggiornamento immediato**  
    Gli aggiornamenti non attendono più il ciclo periodico, ma vengono inviati **subito dopo un cambiamento** (triggered update).
    
- **Estensione dei pacchetti**  
    RIP2 aggiunge la **maschera di rete** e l’**indirizzo del next hop**, rendendo il protocollo compatibile con **indirizzamento classless (CIDR)**.
    

> Con RIP2 la rete resta piccola, ma almeno “parla il linguaggio moderno” dell’IP classless.

---

### **4. Dalla logica Distance Vector a quella Link State**

Per superare definitivamente i limiti del Distance Vector, si passa a un nuovo paradigma: l’**instradamento basato sullo stato dei collegamenti (Link State)**.  
Il principale protocollo di questo tipo è **OSPF (Open Shortest Path First)**.

---

### **5. OSPF: principi generali**

OSPF funziona in modo molto diverso da RIP:

- Ogni router non conosce solo i vicini, ma **l’intera topologia della rete**.
    
- Tutti i router condividono tra loro una copia **identica del database dei collegamenti (Link State Database)**.
    
- Con queste informazioni, ciascun router **calcola autonomamente** i percorsi più brevi verso ogni destinazione, usando l’**algoritmo di Dijkstra**.
    

> OSPF è come una mappa completa condivisa tra tutti i router: ognuno sa dove si trova ogni strada, e sceglie il percorso più corto in autonomia.

---

### **6. Struttura gerarchica di OSPF**

Per aumentare l’efficienza, OSPF organizza la rete in **aree gerarchiche**, riunite in un **sistema autonomo (AS)**.  
Le principali tipologie di aree sono:

- **Stub (pozzo):**  
    Tutti i pacchetti in ingresso restano all’interno del dominio.  
    Non si instrada verso l’esterno.
    
- **Transito:**  
    Alcuni pacchetti attraversano l’area per raggiungere **altri domini**.
    
- **Backbone:**  
    È l’area centrale (di solito Area 0) che collega tutte le altre aree.  
    Tutti i pacchetti diretti fuori da un’area passano dal backbone.
    

> La struttura a più livelli riduce il carico di aggiornamento e rende OSPF scalabile a reti di grandi dimensioni.

---

### **7. Fasi operative del protocollo OSPF**

OSPF opera attraverso tre fasi principali:

1. **Scoperta dei vicini (Hello Protocol):**  
    Ogni router invia periodicamente pacchetti **HELLO** per identificare i router adiacenti.
    
2. **Scambio di informazioni sullo stato dei collegamenti (Flooding):**  
    I router inviano pacchetti **LSA (Link State Advertisement)** per annunciare le proprie connessioni e mantenerle aggiornate.  
    Il flooding è **affidabile**: ogni annuncio viene confermato e diffuso a tutti.
    
3. **Calcolo dei cammini più brevi:**  
    Una volta aggiornato il database, ogni router esegue l’**algoritmo di Dijkstra** per determinare i percorsi di costo minimo verso tutte le destinazioni note.
    

> Così la rete “converge” rapidamente e tutti i router possiedono una visione coerente e aggiornata della topologia.

---

### **8. Efficienza e implementazioni**

Il database OSPF può diventare grande nelle reti estese.  
Per questo si utilizzano:

- **router ad alte prestazioni** (es. Cisco),
    
- oppure implementazioni software come il **package Zebra** su Linux, che consente di gestire OSPF anche su sistemi di laboratorio.
    

---

### **9. Conclusione**

Il passaggio da **RIP a OSPF** rappresenta un salto generazionale:

|**Caratteristica**|**RIP (Distance Vector)**|**OSPF (Link State)**|
|---|---|---|
|Tipo di informazione|Distanza e next hop|Stato dei collegamenti|
|Conoscenza della rete|Solo dei vicini|Topologia completa|
|Algoritmo|Bellman-Ford|Dijkstra|
|Convergenza|Lenta|Rapida|
|Scalabilità|Limitata (≤15 hop)|Elevata|
|Supporto CIDR|Parziale (RIP2)|Completo|
|Applicazione ideale|Reti piccole|Reti medio-grandi|

> OSPF è oggi lo standard per l’instradamento dinamico **in reti aziendali e backbone IP**, grazie alla sua efficienza e stabilità.


---


![](imgs/Pasted%20image%2020251125072948.png)

