# **Lezione 2: Introduzione alla configurazione IP**

---

### **1. Parametri di configurazione di un host IP**

Ogni dispositivo connesso a una rete basata su **TCP/IP** deve disporre di alcuni **parametri minimi di configurazione** per poter comunicare correttamente.

#### **Parametri fondamentali**

1. **Indirizzo IP dell’host**  
    → Identifica univocamente l’interfaccia nella rete.
    
2. **Maschera di sottorete (Subnet Mask)**  
    → Permette di ricavare il **prefisso di rete** tramite l’operazione logica:  
    $$  
    \text{Prefisso} = \text{IP }  \text{ AND }  \text{ Maschera}  
    $$
    
3. **Gateway predefinito (Default Gateway)**  
    → È l’indirizzo IP del router a cui vengono inviati **tutti i pacchetti diretti fuori dalla subnet**.  
    Se il gateway è assente, la comunicazione rimane **limitata alla sola rete locale**.
    

#### **Parametri opzionali**

- Indirizzo IP del **server DNS**, necessario per risolvere i nomi simbolici (es. `www.unimi.it`) in indirizzi IP.
    

---

### **2. Attribuzione degli indirizzi IP**

#### **2.1 Assegnazione tradizionale**

In origine, l’attribuzione degli indirizzi IP seguiva uno schema centralizzato:

1. Si richiedeva all’autorità di registrazione (oggi IANA o i suoi delegati) un **net_ID** di classe adeguata alla dimensione della rete.  
    Esempio: per una piccola rete, un net_ID di **classe C** come `196.70.20.x`.
    
2. Si assegnavano progressivamente gli **host_ID** disponibili agli host collegati:  
    $$  
    196.70.20.1, \; 196.70.20.2, \; \ldots, \; 196.70.20.254  
    $$
    

#### **2.2 Problema: duplicati**

Se due host ricevono **lo stesso indirizzo IP**, possono verificarsi **collisioni logiche**, e uno dei due smetterà di comunicare correttamente.

#### **2.3 Spreco di indirizzi**

Assegnare interi blocchi di classe A, B o C anche a reti con pochi host porta a **sprechi enormi** di spazio d’indirizzamento.  
Da qui nasce l’esigenza di tecniche più flessibili come **subnetting, supernetting** e **indirizzamento dinamico**.

---

### **3. Statico o dinamico?**

Per un’efficiente gestione dell’indirizzamento IP, ci si deve porre due domande:

1. **Gli indirizzi devono essere permanenti o temporanei?**  
    → Determina la scelta tra indirizzamento **statico** e **dinamico**.
    
2. **Gli host devono essere visibili da Internet?**  
    → Distingue tra indirizzi **pubblici (registrati)** e **privati (locali)**.
    

---

### **4. Indirizzi dinamici**

Quando non è necessario che ogni host mantenga un indirizzo fisso, si usa un sistema di **attribuzione dinamica**.

#### **Funzionamento**

- Gli host, all’accensione, **richiedono un indirizzo IP temporaneo** a un **server DHCP (Dynamic Host Configuration Protocol)**.
    
- Il server mantiene un **pool di indirizzi disponibili** e assegna un indirizzo libero all’host che lo richiede.
    
- L’indirizzo è **valido solo per un intervallo di tempo (lease)**.
    

#### **Vantaggi**

- Nessun rischio di conflitti.
    
- Uso efficiente degli indirizzi, soprattutto se pubblici (limitati e costosi).
    
- Facilità di gestione: non serve configurare manualmente ogni host.
    

#### **Esempio pratico**

I provider Internet usano questo metodo per assegnare **indirizzi temporanei** ai clienti che si collegano via modem o connessioni domestiche:  
ogni volta che ci si collega, il server assegna **un nuovo IP pubblico** valido solo per quella sessione.

---

### **5. Indirizzi pubblici**

Un **indirizzo IP pubblico** (o _registrato_) è **unico in tutto il mondo**.  
Nessun altro host su Internet può possedere lo stesso indirizzo.

#### **Condizioni**

- Deve essere **richiesto** a un’autorità di registrazione (IANA, RIPE, ARIN, ecc.).
    
- Può essere **acquistato** tramite un **Internet Service Provider (ISP)**.
    

Questa unicità garantisce che ogni host pubblico sia **raggiungibile da qualunque punto della rete globale**.

---

### **6. Indirizzi privati**

Gli **indirizzi privati** sono riservati per l’uso **all’interno di reti locali** e **non sono unici su scala globale**.  
Non possono essere instradati su Internet.

#### **Classi di indirizzi privati**

|Classe|Intervallo privato|Dimensione tipica|
|---|---|---|
|**A**|10.0.0.0 – 10.255.255.255|Grandi reti private|
|**B**|172.16.0.0 – 172.31.255.255|Reti medie|
|**C**|192.168.0.0 – 192.168.255.255|Piccole reti domestiche / aziendali|

#### **Loopback**

La rete **127.0.0.0** (classe A) è riservata al **loopback**, usato per testare la connessione locale.  
Il più noto è:

$$  
127.0.0.1 \;\Rightarrow\; \text{localhost}  
$$

I pacchetti inviati a questo indirizzo **non escono mai dalla macchina**, ma vengono “riciclati” verso l’interfaccia di ingresso.

---

### **7. Perché usare indirizzi privati**

#### **Motivazioni principali**

1. **Sicurezza**
    
    - Le macchine con IP privato **non sono visibili da Internet**, quindi non possono essere raggiunte o attaccate direttamente.
        
    - L’accesso dall’esterno può avvenire solo tramite un dispositivo di traduzione (es. **NAT**, Network Address Translation).
        
2. **Flessibilità e abbondanza**
    
    - Gli indirizzi privati permettono di usare liberamente sia **assegnazioni statiche** (IP fissi) sia **dinamiche** (tramite DHCP).
        
    - Essendo illimitati in ambito locale, si possono creare anche **interfacce multiple** per uno stesso host senza rischiare conflitti.
        

---

### **8. Caratteristiche operative degli indirizzi privati**

- Gli indirizzi privati **funzionano liberamente all’interno della rete locale**, ma:
    
    - **non possono accedere direttamente a Internet**;
        
    - **non possono essere raggiunti da Internet**.
        
- I router collegati a Internet **scartano automaticamente** (drop) qualsiasi pacchetto diretto a un indirizzo privato.
    

Questo comportamento evita che **errori di configurazione** possano propagarsi oltre la LAN.

---

### **9. Esempio pratico**

Immaginiamo una rete locale con **21 computer in dual-boot** (Windows e Linux).  
A ciascun computer viene assegnato un numero identificativo $k$, con $k = 1, 2, \dots, 21$.

Si può scegliere di attribuire indirizzi IP **diversi a seconda del sistema operativo avviato**, ad esempio:

$$  
\begin{cases}  
\text{Windows: } 10.95.18.(64 + k) \\\\  
\text{Linux: } 10.95.18.(128 + k)  
\end{cases}  
$$

Questo permette, ad esempio, di usare il comando `ping` per capire **quale sistema operativo** è attualmente in esecuzione sull’host.

---

### **10. Conclusione**

In questa lezione abbiamo imparato come si **configura un host IP** e come si **attribuiscono gli indirizzi** in modo corretto e sicuro.

Abbiamo distinto:

- **indirizzi statici** e **dinamici** (a seconda della permanenza);
    
- **indirizzi pubblici** e **privati** (a seconda della visibilità su Internet).
    

Il corretto uso delle **classi di indirizzi privati**, unito alla configurazione del **gateway** e del **DHCP**, è essenziale per realizzare una rete IP efficiente, sicura e ben strutturata.