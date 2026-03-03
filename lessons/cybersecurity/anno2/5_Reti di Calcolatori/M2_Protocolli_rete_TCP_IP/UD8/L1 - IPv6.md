# **Lezione 1: IPv6**

---

### **1. Introduzione e disponibilità**

IPv6 è la **nuova versione del protocollo IP** progettata per risolvere i limiti di **scalabilità e flessibilità** di IPv4.  
Oggi è **ampiamente disponibile** nei principali sistemi operativi e dispositivi di rete:

- **Unix/BSD**, **Linux (da versione 2.2)**, **Solaris 8** e successivi;
    
- **Windows 2000/XP Server** e tutte le versioni moderne;
    
- La maggior parte dei **router di fascia media e alta** supporta già IPv6 nativamente.
    

> IPv6 non è più un esperimento accademico: è ormai parte integrante delle reti contemporanee, anche se coesiste ancora con IPv4 tramite meccanismi di transizione.

---

### **2. Problemi di progettazione risolti da IPv6**

Il progetto di IPv6 è stato guidato dalla necessità di **superare le limitazioni strutturali di IPv4**, introducendo nuove capacità e maggiore efficienza.

**Obiettivi principali del design:**

1. **Scalabilità:** risolvere la carenza di indirizzi di IPv4 (solo $2^{32}$ indirizzi ≈ 4,3 miliardi).  
    → IPv6 estende lo spazio a $2^{128}$ indirizzi (≈ $3.4 \times 10^{38}$ combinazioni).
    
2. **Transizione graduale:** introdurre **meccanismi flessibili** per convivere con IPv4 (dual stack, tunneling, traduzione).
    
3. **Nuove capacità d’instradamento:** miglior gestione delle gerarchie e aggregazione delle rotte.
    
4. **Qualità del servizio (QoS):** priorità e flussi dedicati per traffico multimediale o sensibile.
    
5. **Sicurezza integrata:** supporto nativo per **autenticazione e cifratura** tramite IPsec.
    
6. **Espandibilità futura:** progettazione modulare, per permettere di **aggiungere nuove funzioni** senza modificare il protocollo base.
    

> IPv6 è stato costruito come un protocollo “a prova di futuro”: semplice, scalabile e adattabile.

---

### **3. Struttura dell’intestazione IPv6**

IPv6 introduce un’intestazione **più semplice e più veloce da elaborare** rispetto a quella IPv4.

![](imgs/Pasted%20image%2020260225154735.png)

![](imgs/Pasted%20image%2020260225154749.png)

| Caratteristica         | IPv4                       | IPv6                             |
| ---------------------- | -------------------------- | -------------------------------- |
| Lunghezza intestazione | Variabile (20–60 byte)     | Fissa (40 byte)                  |
| Campi opzionali        | Inclusi                    | Rimossi (spostati in estensioni) |
| Frammentazione         | Permessa da tutti i router | Solo dall’origine                |
| Checksum               | Presente                   | Assente                          |
| Tipo successivo        | “Protocol”                 | “Next Header”                    |

> La semplificazione riduce il tempo di elaborazione dei pacchetti da parte dei router, migliorando la velocità complessiva dell’instradamento.

---

### **4. Campi principali dell’intestazione IPv6**

L’intestazione IPv6 base è lunga **40 byte** e contiene i seguenti campi fondamentali:

#### **Campi principali (1)**

- **Versione:** indica la versione del protocollo, ovvero `6`.
    
- **Priorità:** serve per la **gestione della congestione** e per indicare l’importanza del pacchetto.
    
- **Etichetta del flusso (Flow Label):** opzionale; consente di identificare pacchetti appartenenti allo stesso **flusso di dati**, utile per QoS o streaming.
    

#### **Campi principali (2)**

- **Lunghezza del carico (Payload Length):** indica il numero di byte dopo l’intestazione base (oppure `0` per pacchetti molto grandi, detti _Jumbogram_).
    
- **Intestazione successiva (Next Header):** specifica quale tipo di intestazione o protocollo segue (es. TCP, UDP, o un’_extension header_).
    
- **Limite in hop (Hop Limit):** equivalente al **TTL** di IPv4, impedisce ai pacchetti di circolare indefinitamente nella rete.
    

---

### **5. Intestazioni di estensione**

IPv6 non prevede più campi opzionali “rigidi” come IPv4, ma utilizza **intestazioni aggiuntive modulari**, dette _Extension Headers_, che si concatenano solo quando servono.

|Tipo di intestazione di estensione|Funzione|
|---|---|
|**Routing Header**|Permette l’instradamento di origine (source routing).|
|**Fragment Header**|Gestisce la frammentazione (solo lato sorgente, non nei router).|
|**Authentication Header (AH)**|Fornisce autenticazione e integrità dei dati.|
|**Encapsulating Security Payload (ESP)**|Cifra i dati per la sicurezza end-to-end.|

> Grazie a questo approccio modulare, IPv6 può crescere nel tempo, aggiungendo nuove funzionalità senza dover ridisegnare l’intero protocollo.

---

### **6. Vantaggi generali di IPv6 rispetto a IPv4**

- **Spazio di indirizzi quasi illimitato** → supporta miliardi di dispositivi connessi.
    
- **Routing più efficiente** → tabelle di instradamento più compatte e organizzate gerarchicamente.
    
- **Elaborazione più veloce** → intestazione semplificata.
    
- **Sicurezza integrata** → IPsec obbligatorio come parte dello standard.
    
- **Supporto nativo per QoS** → gestione dei flussi multimediali e delle priorità.
    
- **Espandibilità** → struttura modulare con intestazioni di estensione.
    

> IPv6 rappresenta non solo un “nuovo IP”, ma un **nuovo modo di concepire l’instradamento**, basato su semplicità, sicurezza e scalabilità.

---

### **7. Conclusione**

IPv6 nasce per risolvere le carenze storiche di IPv4, ma anche per **preparare Internet alle esigenze future**:  
più dispositivi, più traffico, più sicurezza e maggiore flessibilità.

> In breve: **IPv4 è cresciuto con Internet**, mentre **IPv6 è nato per farla continuare a crescere**.

