# **Lezione 1: Indirizzi pubblici e privati**

---

### **1. Reti private**

Una **rete privata IP** è una rete che **non è direttamente connessa a Internet**.  
Questo significa che i dispositivi al suo interno possono comunicare tra loro liberamente, ma **non possono essere raggiunti dall’esterno** senza un meccanismo di traduzione o inoltro.

All’interno di una rete privata, gli indirizzi IP possono essere **assegnati in modo arbitrario**, poiché:

- non è necessario registrarli presso alcuna autorità (come l’IANA o il RIPE);
    
- non è garantita la loro **unicità globale** (due reti diverse possono usare gli stessi indirizzi privati).
    

#### **Esempio**

Una rete aziendale o domestica può tranquillamente usare gli stessi indirizzi IP che un’altra organizzazione usa internamente, senza alcun conflitto, finché restano **non pubblici**.

---

### **2. Intervalli di indirizzi privati (RFC 1918)**

Per evitare confusione, la **RFC 1918** ha riservato tre blocchi di indirizzi IP che **non devono mai essere instradati su Internet**.  
Questi sono detti **indirizzi privati** o **indirizzi non instradabili**.

|**Classe**|**Intervallo IP**|**Numero massimo di host**|**Descrizione**|
|---|---|---|---|
|A|10.0.0.0 – 10.255.255.255|~16 milioni|Reti molto grandi|
|B|172.16.0.0 – 172.31.255.255|~1 milione|Reti di medie dimensioni|
|C|192.168.0.0 – 192.168.255.255|~65.000|Reti locali domestiche o aziendali|

> Tutti gli indirizzi che rientrano in questi intervalli non possono essere instradati su Internet e **devono restare confinati in ambiti LAN o VPN**.

---

### **3. Indirizzi pubblici**

Gli **indirizzi pubblici** sono invece **univoci e globalmente instradabili su Internet**.  
Sono assegnati da organizzazioni internazionali di registrazione (come IANA o i registri regionali RIPE, ARIN, APNIC, ecc.) a **provider e organizzazioni autorizzate**.

Ogni indirizzo pubblico deve essere:

- **unico a livello mondiale**;
    
- **registrato ufficialmente**;
    
- e associato a una rete connessa direttamente o indirettamente a Internet.
    

#### **Esempio**

Un server con IP pubblico `151.100.25.6` può essere raggiunto da qualunque dispositivo nel mondo, purché non sia filtrato da firewall o regole di sicurezza.

---

### **4. NAT – Network Address Translation**

Il **NAT (Network Address Translation)** è la tecnologia che permette ai dispositivi con **indirizzi privati** di **comunicare con Internet pubblica**.  
È una funzione implementata nei **router o gateway** che collegano la rete privata a Internet.

#### **4.1 Funzione di base**

Quando un pacchetto IP lascia la rete privata:

- il router **sostituisce** l’indirizzo IP sorgente privato con **un indirizzo pubblico**;
    
- registra la corrispondenza nella propria **tabella di traduzione**.
    

Quando il pacchetto di risposta ritorna da Internet:

- il router effettua la **traduzione inversa**, rimappando l’indirizzo pubblico verso quello privato originale.
    

In questo modo, i dispositivi interni **restano invisibili** a Internet ma possono comunicare verso l’esterno.

---

### **5. Funzionamento della traduzione NAT**

Il router NAT mantiene una **tabella di associazioni** chiamata **tabella di traduzione degli indirizzi**, in cui memorizza:

- l’indirizzo IP privato e la porta di origine;
    
- l’indirizzo IP pubblico e la porta esterna corrispondente.
    

![](imgs/Pasted%20image%2020251211161659.png)

#### **Esempio**

|IP privato|Porta sorgente|IP pubblico|Porta tradotta|
|---|---|---|---|
|192.168.1.10|51432|151.100.20.5|61001|
|192.168.1.11|51433|151.100.20.5|61002|

Quando il router riceve un pacchetto di ritorno da Internet con destinazione `151.100.20.5:61002`, lo riconosce nella tabella e lo inoltra a `192.168.1.11:51433`.

Questo meccanismo è detto **PAT (Port Address Translation)** o **NAT Overload**, perché consente di usare **un solo indirizzo pubblico** per **più dispositivi privati** sfruttando le porte.

---

### **6. Vantaggi del NAT**

- **Risparmio di indirizzi pubblici** → più host privati possono condividere un unico IP pubblico.
    
- **Sicurezza** → le reti interne non sono direttamente raggiungibili dall’esterno.
    
- **Flessibilità** → permette l’uso libero degli indirizzi privati senza coordinamento globale.
    
- **Trasparenza** → i dispositivi interni non devono conoscere i dettagli di traduzione.
    

---

### **7. Svantaggi del NAT**

- Alcuni protocolli (es. FTP, SIP, VPN IPsec) **non funzionano correttamente** dietro NAT, perché includono indirizzi IP nei dati applicativi.
    
- Complica la **tracciabilità delle connessioni**, rendendo difficile l’identificazione di un singolo host interno.
    
- Può **interferire con applicazioni peer-to-peer**, che richiedono connessioni dirette tra dispositivi.
    
- È una soluzione **temporanea**, pensata per estendere l’uso di IPv4 in attesa dell’adozione di **IPv6**, che offre indirizzi globali sufficienti per tutti.
    

---

### **8. Conclusione**

In questa lezione abbiamo distinto:

- le **reti private**, basate su indirizzi riservati non instradabili;
    
- le **reti pubbliche**, con indirizzi univoci e registrati;
    
- e la funzione del **NAT**, che traduce tra i due mondi.
    

Il NAT rappresenta oggi una delle tecnologie più diffuse nella connettività domestica e aziendale, perché consente di **collegare reti private a Internet in modo sicuro ed economico**, pur mantenendo il controllo sull’indirizzamento interno.