# **Lezione 8: Gli switch**

### **1. Definizione e finalità**

Uno **switch** è un dispositivo di rete che consente di **suddividere una LAN in segmenti** distinti, mantenendo però **lo stesso protocollo di comunicazione** all’interno dell’intera rete.  
Il suo compito principale è **migliorare le prestazioni e l’affidabilità** complessiva, gestendo in modo più efficiente il traffico e riducendo le collisioni.

In altre parole, uno switch:

- crea **connessioni dirette temporanee** tra mittente e destinatario,
    
- **segmenta** il traffico per evitare interferenze tra dispositivi,
    
- e **ottimizza la gestione delle risorse** all’interno della LAN.
    

Il risultato è una rete più veloce, più stabile e più facilmente gestibile.

---

### **2. Differenze tra switch e bridge**

Sebbene switch e bridge condividano concetti simili, esistono differenze fondamentali nel loro funzionamento e nelle prestazioni.

|**Caratteristica**|**Bridge**|**Switch**|
|:--|:--|:--|
|**Livello OSI**|Livello 2 (Data Link)|Livello 2 (Data Link), ma con architettura interna più complessa|
|**Numero di porte**|2 o poche|Da decine a centinaia|
|**Scopo principale**|Collegare due segmenti di rete|Segmentare un’intera LAN in più domini|
|**Velocità di elaborazione**|Bassa, software-based|Alta, hardware-based (ASIC dedicati)|
|**Modalità di inoltro**|Serializzata|Parallela, simultanea|
|**Gestione protocolli**|Può collegare reti con protocolli diversi|Opera tipicamente su reti omogenee|

In sintesi:

- Il **bridge** collega **due reti diverse** o due segmenti che possono usare protocolli differenti.
    
- Lo **switch**, invece, **suddivide una singola LAN** in segmenti logici più piccoli, mantenendo **lo stesso protocollo Ethernet** e aumentando l’efficienza del traffico interno.
    

---

### **3. Miglioramento delle prestazioni**

L’introduzione degli switch ha rivoluzionato il funzionamento delle LAN, in particolare delle **reti a bus**.

In una rete tradizionale a bus, tutti i dispositivi condividono lo stesso mezzo fisico: ciò comporta **collisioni frequenti** e una **banda effettiva ridotta**.  
Con l’uso degli switch:

- ogni segmento è **isolato elettricamente** dagli altri;
    
- le collisioni vengono **eliminate**, perché ogni porta rappresenta **un dominio di collisione indipendente**;
    
- la **larghezza di banda complessiva** aumenta, poiché più comunicazioni possono avvenire in parallelo.
    

Inoltre, se un segmento della rete si guasta, gli altri continuano a funzionare regolarmente.  
Questo migliora notevolmente **l’affidabilità** e la **manutenibilità** del sistema.

---

### **4. Segmentazione e gestione del traffico**

Dividere una rete in segmenti con l’aiuto degli switch permette di:

- **localizzare rapidamente i guasti**, poiché ogni segmento è monitorabile separatamente;
    
- **isolare il traffico locale**, evitando che si propaghi inutilmente;
    
- **ridurre il carico di broadcast**, migliorando la reattività delle applicazioni.
    

In pratica, lo switch funziona come un **centro di smistamento intelligente**:

- riceve un frame Ethernet;
    
- legge l’**indirizzo MAC di destinazione**;
    
- decide **su quale porta inoltrarlo** (anziché inviarlo a tutte, come farebbe un hub).
    

Questo processo è gestito da **circuiti logici dedicati (ASIC)**, che permettono di operare **in tempo reale** anche su centinaia di connessioni simultanee.

---

### **5. Switch Ethernet e Token Ring**

Originariamente, gli switch furono progettati per le **LAN Ethernet** a bus, con l’obiettivo di **suddividerle in segmenti indipendenti**.  
Fisicamente, la rete risultante assumeva una **topologia a stella**, dove lo switch rappresentava il punto centrale di collegamento.

In seguito, il concetto di switching è stato esteso anche alle **reti Token Ring**, dove gli switch gestivano la circolazione del token tra segmenti distinti, migliorando le prestazioni anche in architetture ad anello logico.

Oggi, lo **switching Ethernet** è diventato **lo standard universale** per le LAN, mentre le Token Ring appartengono ormai alla storia dell’informatica di rete.

![](imgs/Pasted%20image%2020260213031554.png)

![](imgs/Pasted%20image%2020260213031612.png)

---

### **6. Larghezza di banda e scalabilità**

Uno dei maggiori vantaggi degli switch è la possibilità di **incrementare la larghezza di banda complessiva** della rete.  

![](imgs/Pasted%20image%2020260213031631.png)

Poiché le comunicazioni tra due nodi avvengono in canali dedicati, **più coppie di host possono scambiarsi dati simultaneamente** senza interferenze.

Inoltre:

- gli switch moderni supportano **porte a 1, 10 o 40 Gbps**;
    
- possono essere **impilati (stacked)** o collegati a dorsali ad alta velocità;
    
- integrano funzioni di **Quality of Service (QoS)** per dare priorità ai pacchetti più importanti (es. voce, video o controllo).
    

Questo rende gli switch **scalabili e adattabili** a reti di qualsiasi dimensione, dalle piccole LAN aziendali ai data center.

---

### **7. Sintesi concettuale**

- Gli **switch** dividono una LAN in segmenti per migliorare **prestazioni, affidabilità e gestione**.
    
- A differenza dei **bridge**, operano in modo **più veloce e parallelo**, su numerose porte.
    
- La segmentazione consente di **eliminare collisioni** e **aumentare la banda disponibile**.
    
- Gli switch Ethernet nascono per reti a bus ma si sono estesi anche alle Token Ring.
    
- Rappresentano oggi **il cuore fisico delle reti moderne**, integrando funzioni avanzate come **QoS**, **stacking** e **monitoraggio remoto**.