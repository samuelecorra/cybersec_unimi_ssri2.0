## **Lezione 3: Address Resolution Protocol (ARP)**

### **1. Introduzione ad ARP**

L’**ARP (Address Resolution Protocol)** è il meccanismo che consente di **tradurre un indirizzo IP in un indirizzo fisico Ethernet (MAC)** all’interno di una rete locale.  
Viene utilizzato ogni volta che un host deve inviare un pacchetto IP a un altro host **della stessa subnet**, ma **conosce solo l’indirizzo IP** e non il corrispondente indirizzo MAC.

> ARP **non assegna indirizzi IP** — questo è compito del **DHCP**.  
> Il suo ruolo è **puramente di risoluzione**, ossia di traduzione tra livelli del modello TCP/IP.

Ogni host mantiene in memoria una **cache ARP**, una tabella che memorizza le associazioni IP ↔ MAC già note.  
Quando un indirizzo non è presente in cache, viene generata una **richiesta ARP (ARP Request)** in broadcast sulla rete.

La specifica ufficiale di ARP è definita nella **RFC 826** dell’IETF (_Internet Engineering Task Force_).

---

### **1.1 ARP e RARP: due direzioni complementari**

|Protocollo|Funzione|Direzione di traduzione|Utilizzo|
|---|---|---|---|
|**ARP**|Address Resolution Protocol|Da IP → MAC|Traduzione normale per invio pacchetti|
|**RARP**|Reverse Address Resolution Protocol|Da MAC → IP|Utilizzato da host **diskless** (senza disco) per ottenere il proprio IP|

Il **RARP**, oggi sostituito da **BOOTP/DHCP**, era pensato per computer che conoscevano solo il proprio indirizzo hardware (MAC) ma dovevano **richiedere a un server il loro indirizzo IP**.  
Il formato dei pacchetti ARP e RARP è identico: cambia solo il tipo di operazione (forward o reverse).

---

### **1.2 Funzionamento di ARP**

Vediamo come opera ARP passo per passo:

1. **Verifica della cache locale**  
    Se l’indirizzo IP di destinazione è già presente nella cache ARP, l’host prende il corrispondente MAC e invia direttamente il frame Ethernet.
    
2. **Richiesta broadcast (ARP Request)**  
    Se l’associazione IP ↔ MAC non è nota, l’host invia un **pacchetto broadcast** sulla rete (`FF:FF:FF:FF:FF:FF`).  
    Il pacchetto contiene:
    
    - indirizzo IP del destinatario da risolvere;
        
    - indirizzo IP e MAC del mittente.
        
3. **Analisi della richiesta da parte degli host**  
    Tutti gli host della rete ricevono la richiesta ARP e confrontano l’indirizzo IP richiesto con il proprio.
    
    - Se **non coincide**, ignorano il messaggio.
        
    - Se **coincide**, generano una **risposta ARP** (ARP Reply).
        
4. **Risposta ARP (unicast)**  
    L’host destinatario invia una risposta unicast contenente il proprio **indirizzo MAC**, direttamente all’host richiedente.
    
5. **Aggiornamento della cache**  
    Il mittente registra nella cache ARP la coppia IP ↔ MAC ricevuta e invia i pacchetti IP successivi incapsulati nel frame Ethernet corretto.

![](imgs/Pasted%20image%2020260225010652.png)

---

### **1.3 Incapsulamento ARP/RARP**

ARP e RARP **non utilizzano il protocollo IP**:  
i loro messaggi sono **incapsulati direttamente in frame Ethernet**.

Nell’header Ethernet, il campo **Type (16 bit)** specifica il protocollo di livello 3 da consegnare:

|Tipo (hex)|Significato|
|---|---|
|`0800`|IP|
|`0806`|ARP|
|`8035`|RARP|

#### **Schema semplificato del frame Ethernet**

|Campo|Lunghezza|Descrizione|
|---|---|---|
|**Dest. address**|48 bit|MAC del destinatario|
|**Source address**|48 bit|MAC del mittente|
|**Type**|16 bit|Indica se il contenuto è IP, ARP o RARP|
|**Data**|variabile|Pacchetto ARP/IP/RARP|
|**FCS**|32 bit|Controllo d’errore|

> Poiché ARP opera a livello di collegamento, **non può attraversare router**: è quindi utilizzabile **solo in reti locali (LAN)**. Ricordiamo la struttura del frame ethernet:

![](imgs/Pasted%20image%2020260225010924.png)

---

### **1.4 Formato di un pacchetto ARP**

Il messaggio ARP è molto compatto e segue questa struttura logica:

|Campo|Significato|
|---|---|
|**Hardware Type (HTYPE)**|Tipo di rete (es. 1 = Ethernet)|
|**Protocol Type (PTYPE)**|Tipo di protocollo di livello superiore (es. 0x0800 per IP)|
|**Hardware Size (HLEN)**|Lunghezza dell’indirizzo MAC (6 byte)|
|**Protocol Size (PLEN)**|Lunghezza dell’indirizzo IP (4 byte)|
|**Operation**|1 = richiesta, 2 = risposta|
|**Sender Hardware Address**|MAC del mittente|
|**Sender Protocol Address**|IP del mittente|
|**Target Hardware Address**|MAC del destinatario (vuoto nella richiesta)|
|**Target Protocol Address**|IP del destinatario|

---

### **1.5 Un interscambio ARP**

#### **Scenario tipico**

Un host A vuole inviare un pacchetto IP all’host B nella stessa rete:

- A conosce solo l’indirizzo IP di B;
    
- non conosce il suo MAC.
    

**Sequenza:**

1. A invia una **richiesta ARP in broadcast** (`FF:FF:FF:FF:FF:FF`) specificando l’IP di B.
    
2. Tutti gli host confrontano l’IP richiesto col proprio.
    
3. Solo B risponde con un **ARP Reply** in unicast, contenente il suo MAC.
    
4. A registra la coppia nella cache ARP e inoltra il pacchetto IP incapsulato nel frame Ethernet corretto.
    

#### **Nota**

Sebbene sarebbe possibile trasmettere anche la **risposta in broadcast**, questo genererebbe troppo traffico inutile.  
Per questo le risposte ARP sono **unicast**.

![](imgs/Pasted%20image%2020260225011240.png)

---

### **1.6 Cache ARP nei sistemi operativi**

Ogni sistema operativo mantiene una **tabella ARP**.  
Vediamone l’esempio in Unix e Linux:

![](imgs/Pasted%20image%2020260225011503.png)

#### **BSD Unix**

```bash
arp -a
? (150.24.37.66) at <incomplete> on eth0
? (150.24.37.1)  at 00:00:0C:09:92:9C [ether] on eth0
? (150.24.37.30) at 00:A0:C9:3D:8C:23 [ether] on eth0
```

#### **Linux**

```bash
arp -e
Address          HWtype    HWaddress        Flags Mask    Iface
150.24.37.66     (incomplete)                              eth0
150.24.37.1       ether   00:00:0C:09:92:9C   C            eth0
150.24.37.30      ether   00:A0:C9:3D:8C:23   C            eth0
```

La colonna **HWaddress** mostra il MAC associato a ogni IP.  
Le voci incomplete vengono aggiornate automaticamente quando arriva una risposta ARP.

---

### **1.7 Relazione tra ARP e ICMP (ping)**

Quando si invia un **ping (ICMP Echo Request)** a un nuovo host, la prima richiesta IP genera automaticamente una **richiesta ARP** se l’indirizzo MAC non è noto.

#### **Esempio**

```bash
ping 152.20.36.14
```

All’inizio la cache ARP non contiene l’indirizzo.  

![](imgs/Pasted%20image%2020260225011644.png)

Dopo il primo pacchetto, il sistema riceve la risposta e **aggiorna la tabella ARP**.  
Le risposte successive risultano quindi **più veloci**, perché l’associazione IP–MAC è già nota.

---

### **2. L’interfaccia di loopback**

L’indirizzo **127.0.0.1**, detto **loopback** o **localhost**, è un indirizzo speciale che rappresenta **l’host stesso**.  
Tutti i pacchetti inviati a questo indirizzo **non vengono trasmessi sulla rete fisica**, ma **ritornano immediatamente** allo stack TCP/IP locale.

![](imgs/Pasted%20image%2020260225011732.png)

#### **Funzione**

- Verifica del corretto funzionamento dello stack TCP/IP interno;
    
- Test locali di applicazioni client/server senza generare traffico reale.
    

#### **Esempio di ping alla loopback**

```bash
ping localhost
```

Output:

```
Risposta da 127.0.0.1: byte=32 durata<1ms TTL=128
```

![](imgs/Pasted%20image%2020260225011816.png)

Tutti i pacchetti restano interni al sistema, passando virtualmente per il **driver di loopback**, che simula una connessione di rete interna.

---

### **3. Conclusione**

Il protocollo **ARP** costituisce un ponte fondamentale tra il **livello di rete (IP)** e il **livello di collegamento (Ethernet)**.  
Permette agli host di scoprire gli indirizzi fisici necessari per trasmettere pacchetti IP, garantendo il funzionamento di qualsiasi LAN.

La sua controparte **RARP** è oggi superata, ma storicamente importante per i primi sistemi _diskless_.  
Infine, l’interfaccia **loopback (127.0.0.1)** consente di testare localmente l’intero stack TCP/IP, assicurando che la comunicazione interna funzioni anche senza rete esterna.

Se volessimo vedere il flusso completo...

![](imgs/Pasted%20image%2020260225011850.png)

---

PDF originale:

![](imgs/DAMNO_M2_U3_L3_D.pdf)