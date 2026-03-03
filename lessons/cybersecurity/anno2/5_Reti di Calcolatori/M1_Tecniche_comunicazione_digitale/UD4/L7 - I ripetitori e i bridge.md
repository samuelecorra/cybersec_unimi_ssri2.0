# **Lezione 7: I ripetitori e i bridge**

### **1. Introduzione**

In questa lezione analizziamo due apparati fondamentali per l’interconnessione di reti locali:  
il **ripetitore (repeater)** e il **bridge**.  
Entrambi servono ad **estendere o collegare segmenti di rete**, ma operano a **livelli diversi del modello ISO/OSI**:

- il **ripetitore** agisce al **livello fisico**,
    
- il **bridge** opera al **livello Data Link (livello 2)**.
    

Comprendere questa distinzione è essenziale per capire come si passa da una **rete elettricamente continua** a una **rete logicamente strutturata** e priva di interferenze.

---

### **2. I ripetitori**

#### **a) Funzione principale**

Il **ripetitore** viene utilizzato per **aumentare la portata fisica** di una rete locale, cioè la **distanza massima** coperta da un collegamento senza degradare il segnale.  
Serve, in pratica, per **rigenerare** il segnale elettrico o ottico quando questo si indebolisce a causa della distanza o del rumore.

> **Esempio:**  
> Nello standard **10Base5** (Ethernet su cavo coassiale spesso), la lunghezza massima di un segmento è di **500 metri**.  
> Inserendo un ripetitore, è possibile **collegare due segmenti da 500 m**, estendendo la portata complessiva della rete a **1 km**.

![](imgs/Pasted%20image%2020260213025935.png)

![](imgs/Pasted%20image%2020260213030049.png)

---

#### **b) Motivazione tecnica**

Ogni rete ha una **distanza massima di progetto (span)**, determinata da:

- attenuazione del segnale;
    
- ritardi di propagazione;
    
- interferenze e riflessioni;
    
- vincoli legati al **tempo di rilevamento delle collisioni** (in Ethernet CSMA/CD).
    

Il ripetitore **ricostruisce il segnale** in ingresso e lo **ritrasmette come nuovo segnale**, senza alterarne il contenuto logico.

---

#### **c) Posizione nel modello ISO/OSI**

Il ripetitore opera al **livello fisico (Livello 1)** del modello ISO/OSI.  
Ciò significa che:

- non interpreta i bit come dati,
    
- non riconosce gli indirizzi MAC,
    
- e non modifica il frame Ethernet.

![](imgs/Pasted%20image%2020260213030209.png)

Si limita a **rigenerare il segnale** per mantenerlo integro su distanze maggiori.  
È quindi **trasparente ai livelli superiori**.

---

#### **d) Espansione dello span**

I ripetitori possono essere utilizzati in due modi principali:

1. **Espansione della rete (orizzontale)**  
    → collegano tra loro più segmenti Ethernet, aumentando la distanza complessiva.
    
2. **Collegamento al backbone (verticale)**  
    → estendono la distanza tra il **backbone principale** e i **nodi terminali** di rete.
    

In entrambi i casi, il ripetitore **non introduce ritardi significativi**, poiché il suo compito è puramente fisico.

---

### **3. I bridge**

#### **a) Funzione generale**

Il **bridge** è un dispositivo che collega **due reti locali (LAN)** che utilizzano **lo stesso protocollo di livello 2**, tipicamente Ethernet.  
Il suo scopo è **separare i domini di collisione**, mantenendo però la **continuità logica** della rete.

A differenza del ripetitore, il bridge **interpreta i frame Ethernet**:  
analizza gli **indirizzi MAC sorgente e destinazione** e decide se **inoltrare** o **filtrare** i pacchetti.

---

#### **b) Livello operativo**

Il bridge opera al **livello Data Link (livello 2)** del modello ISO/OSI.  

![](imgs/Pasted%20image%2020260213030334.png)

In particolare, lavora nella sottosezione **MAC (Medium Access Control)**, responsabile della gestione dell’accesso al mezzo.

Questo gli consente di:

- riconoscere i dispositivi connessi a ciascun lato;
    
- memorizzare in una **tabella di forwarding** le associazioni MAC ↔ porta;
    
- evitare la trasmissione inutile di frame su segmenti non interessati.
    

---

#### **c) Bridge e Spanning Tree**

Il bridge è uno dei protagonisti del **protocollo Spanning Tree (STP)**, già studiato nella lezione precedente.  
Attraverso STP, più bridge collegati tra loro possono:

- **eliminare i loop logici**,
    
- **bloccare i collegamenti ridondanti**,
    
- e **mantenere un solo percorso attivo** tra ogni coppia di reti.
    

Questo garantisce **affidabilità** e **assenza di cicli infiniti**, pur mantenendo la **ridondanza fisica**.

---

#### **d) Bridge locali e remoti**

I bridge possono essere classificati in due categorie:

1. **Bridge locali**
    
    - Collegano due LAN fisicamente vicine (es. due uffici nello stesso edificio).
        
    - Forniscono una separazione logica pur condividendo la stessa infrastruttura.
        
2. **Bridge remoti**
    
    - Collegano LAN geograficamente distanti, ad esempio due sedi aziendali.
        
    - Utilizzano una **connessione WAN** o **linee dedicate** come canale di comunicazione.
        
    - Consentono **accesso remoto** a una rete locale, mantenendo trasparente la connessione per gli utenti.
        

---

### **4. Esempio di bridge operativo**

Immaginiamo due reti Ethernet distinte, A e B, collegate da un bridge:

- Quando un computer in A invia un frame verso un destinatario in B, il bridge:
    
    1. riceve il frame sulla porta di A,
        
    2. analizza l’indirizzo MAC di destinazione,
        
    3. riconosce che il destinatario è nella rete B,
        
    4. inoltra il frame solo sulla porta collegata a B.
        
- Se invece il destinatario è anch’esso in A, il bridge **non inoltra** il frame su B, **filtrando il traffico inutile**.

![](imgs/Pasted%20image%2020260213030721.png)

Questo comportamento riduce il **carico globale sulla rete** e isola **collisioni e broadcast** all’interno di ciascun dominio locale.

---

### **5. Bridge e dispositivi successivi**

Il concetto di bridge è alla base della tecnologia **switching**.  
Infatti, uno **switch moderno** può essere visto come un **bridge multiporta**, capace di gestire contemporaneamente:

- decine o centinaia di connessioni;
    
- tabelle MAC dinamiche;
    
- e segmentazione automatica del traffico.
    

In sintesi:  
$$  
\text{Switch} = \text{Bridge a più porte con commutazione parallela}  
$$

---

### **6. Sintesi concettuale**

- I **ripetitori** operano a **livello fisico**, rigenerando i segnali per aumentare la portata della rete.
    
- Servono per estendere i collegamenti e mantenere l’integrità del segnale su distanze più lunghe.
    
- I **bridge** operano a **livello 2 (Data Link)** e collegano due LAN simili, **filtrando e instradando i frame**.
    
- Esistono **bridge locali** (nello stesso edificio) e **bridge remoti** (tra sedi distanti).
    
- Il bridge è il **precursore degli switch moderni**, e parte integrante del **protocollo Spanning Tree (STP)**.
    
- Insieme, ripetitori e bridge rappresentano i **mattoni fondamentali** dell’interconnessione tra reti locali.