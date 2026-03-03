# **Lezione 10: I router**

### **1. Introduzione**

Dopo aver studiato hub, bridge e switch — dispositivi che operano nei livelli più bassi del modello ISO/OSI — arriviamo ora al **router**, il primo apparato che lavora a un **livello superiore**, ossia il **livello di rete (livello 3)**.

Il router rappresenta un punto di svolta nella progettazione delle reti:  
mentre hub e switch si limitano a gestire collegamenti all’interno di una **singola LAN**, il router permette di **collegare reti differenti**, anche basate su **protocolli eterogenei**.

In altre parole:

> Il router non unisce segmenti della stessa rete, ma **interconnette sottoreti autonome**, dando vita a vere e proprie **reti di reti**: da qui nasce Internet.

---

### **2. Le finalità di un router**

Il compito principale di un router è **connettere più LAN**, anche se **funzionano con protocolli differenti**.  
Per questo motivo, le LAN collegate da un router vengono definite **sottoreti (subnet)** piuttosto che semplici segmenti.

In una rete complessa:

- ogni **sottorete locale** può utilizzare il proprio **standard Ethernet**, Token Ring o Wi-Fi;
    
- il router si occupa di **far dialogare** questi mondi diversi, gestendo gli **indirizzi IP**, i **percorsi logici** e la **conversione dei protocolli** quando necessario.
    

---

### **3. Caratteristiche fondamentali**

Un router è un **vero dispositivo di collegamento tra reti** (internetworking device).  
Le sue caratteristiche principali sono:

- **Connette sottoreti diverse**, ciascuna con il proprio protocollo e formato di frame.
    
- **Stabilisce percorsi logici** di comunicazione tra reti, determinando la strada più efficiente che i pacchetti devono percorrere.
    
- **Supporta la modularità**: grazie ai router, una rete complessa può essere **divisa in sezioni indipendenti**, più facili da gestire e da proteggere.
    
- **Ottimizza l’uso delle risorse di rete**, evitando congestionamenti e ridondanze.
    

In sintesi, un router **organizza il traffico dati a livello globale**, mentre switch e hub si occupano solo di ciò che accade “dentro casa”.

---

### **4. Differenze tra hub, switch e router**

Per comprendere meglio la posizione del router nella gerarchia dei dispositivi, osserviamo il confronto seguente:

![](imgs/Pasted%20image%2020260213034144.png)

|**Dispositivo**|**Livello OSI**|**Funzione principale**|**Analisi pacchetti**|**Modifica pacchetti**|**Conversione di protocollo**|
|:--|:-:|:--|:-:|:-:|:-:|
|**Hub**|Livello 1 (Fisico)|Connessione elettrica tra nodi|❌|❌|❌|
|**Switch**|Livello 2 (Data Link)|Smistamento dei frame all’interno della LAN|✅ (indirizzo MAC)|❌|❌|
|**Router**|Livello 3 (Rete)|Interconnessione tra reti differenti|✅ (indirizzo IP)|✅|✅|

Questa tabella mette in evidenza la **crescente intelligenza dei dispositivi**:

- l’hub è **passivo** e si limita a trasmettere segnali;
    
- lo switch è **intelligente a livello locale** e smista i frame all’interno di una LAN;
    
- il router è **intelligente a livello globale**, capace di scegliere percorsi logici e di tradurre protocolli.

![](imgs/Pasted%20image%2020260213034654.png)

---

### **5. Il livello di rete e l’elaborazione dei pacchetti**

L’operatività del router rientra pienamente nel **livello di rete del modello ISO/OSI**.  
A questo livello, i dati non sono più frame Ethernet ma **pacchetti IP**, cioè unità di informazione dotate di:

- **indirizzo IP sorgente**,
    
- **indirizzo IP di destinazione**,
    
- e varie **informazioni di controllo** (TTL, checksum, header, ecc.).
    

Il router:

1. **analizza l’intestazione IP**,
    
2. **sceglie il percorso** ottimale per il pacchetto,
    
3. **inoltra il pacchetto** verso il router successivo o la rete di destinazione.
    

Questo processo è detto **routing**, e avviene grazie a **tabelle di instradamento (routing tables)**, aggiornate staticamente o dinamicamente tramite protocolli dedicati (es. RIP, OSPF, BGP).

---

### **6. Requisiti hardware e prestazionali**

Rispetto a bridge e switch, i router richiedono **molta più potenza di calcolo**.  
Questo perché devono:

- **interpretare pacchetti complessi**,
    
- **modificare gli header IP**,
    
- e **decidere in tempo reale** quale sia il percorso più efficiente tra centinaia di opzioni.
    

Per questo motivo, i router sono dotati di:

- **CPU dedicate al routing**,
    
- **memorie cache** per le tabelle IP,
    
- **interfacce multiple** per gestire contemporaneamente diverse reti (LAN, WAN, MAN, Wi-Fi, ecc.).
    

L’elaborazione dei pacchetti può essere **hardware-based** (nei router professionali) o **software-based** (nei router domestici).

---

### **7. Router come interfaccia tra livelli**

Il router rappresenta l’**interfaccia naturale tra il livello 2 (Data Link)** e il **livello 3 (Rete)** del modello ISO/OSI.  

![](imgs/Pasted%20image%2020260213034738.png)

In ogni porta del router, infatti, può cambiare:

- il **mezzo trasmissivo** (es. da Ethernet a fibra ottica o Wi-Fi),
    
- il **formato del frame**,
    
- e perfino il **protocollo di trasporto**.
    

Il router si occupa quindi di **riconfezionare i dati** nel formato corretto per la rete di destinazione, preservandone il contenuto logico.  
È questa capacità che lo rende un vero **dispositivo di internetworking**.

---

### **8. Esempio pratico**

Immaginiamo un’azienda con due sedi:

![](imgs/Pasted%20image%2020260213034954.png)

- la **sede A** usa Ethernet e indirizzi 192.168.0.x,
    
- la **sede B** usa Wi-Fi e indirizzi 192.168.1.x.
    

Le due LAN non possono comunicare direttamente, poiché si trovano in **sottoreti IP diverse**.  
Un **router** collegato a entrambe le reti permette la comunicazione tra loro, **instradando i pacchetti IP** da una sottorete all’altra in base alle tabelle di routing.

---

### **9. Sintesi concettuale**

- Il **router** è un dispositivo di **livello 3 (rete)** che collega sottoreti diverse, anche con protocolli differenti.
    
- Stabilisce **percorsi logici di comunicazione** e **decide come inoltrare i pacchetti IP**.
    
- Richiede **maggiore capacità di elaborazione** rispetto a hub e switch.
    
- Opera come **interfaccia tra livelli**, ricreando i frame corretti per ogni sottorete.
    
- È il componente chiave per la **modularità, l’efficienza e la scalabilità** delle reti moderne.
    

In sintesi:  
$$  
\text{Router} = \text{intelligenza di rete} + \text{instradamento logico} + \text{conversione di protocollo}  
$$

---

