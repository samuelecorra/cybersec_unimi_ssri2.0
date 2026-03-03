# **Lezione 2: Caratteristiche del DNS**

---

### **1. Introduzione**

Il **Domain Name System (DNS)** è un sistema distribuito e gerarchico che permette di tradurre nomi simbolici in indirizzi IP.  
Per garantire un funzionamento affidabile su scala mondiale, deve possedere alcune **caratteristiche fondamentali**, che assicurano **scalabilità, coerenza e affidabilità** nonostante l’enorme quantità di dati gestiti ogni secondo.

---

### **2. Distribuzione globale**

Il DNS non è un unico database centralizzato: è un **sistema distribuito**.  
Ogni organizzazione gestisce la propria **parte di namespace** (detta _zona_), ma tutte le zone cooperano per formare l’intero sistema mondiale.

Questo approccio consente:

- di **evitare colli di bottiglia**, poiché nessun server deve conoscere tutto lo spazio dei nomi;
    
- di **ridurre i tempi di risposta**, grazie al **caching** delle informazioni più richieste.
    

In pratica, quando un server risolve un nome di dominio, **memorizza il risultato nella cache** per un tempo limitato (definito dal _Time To Live_, TTL).  
In questo modo, le richieste successive per lo stesso nome vengono risolte **molto più velocemente**, senza dover ripercorrere l’intera catena di query fino ai root server.

---

### **3. Coerenza**

Il DNS garantisce la **consistenza dei dati** attraverso un sistema di **repliche controllate**.

Ogni zona ha:

- un **master server** (o _primary_), che contiene la versione ufficiale e aggiornata dei dati;
    
- uno o più **slave server** (o _secondary_), che ne mantengono una **copia sincronizzata**.
    

Ogni volta che la zona viene modificata, il master incrementa un **numero seriale** nel record SOA (_Start of Authority_).  
Gli slave lo confrontano periodicamente con la propria copia: se il numero è diverso, scaricano automaticamente l’aggiornamento.  
Questo meccanismo garantisce che tutte le repliche rimangano **coerenti** nel tempo.

---

### **4. Scalabilità**

Il DNS è stato progettato per essere **scalabile**, cioè per crescere con Internet senza perdere efficienza.  
Questo è possibile grazie a:

- la **suddivisione gerarchica** dello spazio dei nomi in **livelli (domini e sottodomini)**;
    
- la possibilità per ogni dominio di **delegare la gestione** dei sottodomini inferiori.
    

Ad esempio, il dominio `.it` può delegare a `unimi.it` la gestione dei nomi interni dell’università di Milano, e `unimi.it` può a sua volta delegare ad altri sottodomini come `dis.unimi.it` o `studenti.unimi.it`.

Questo modello di delega evita la saturazione di un singolo punto e rende l’intero sistema **modulare e distribuito**.

---

### **5. Affidabilità**

Per garantire la **disponibilità continua del servizio**, il DNS adotta varie strategie:

- **ridondanza**: ogni zona è servita da più DNS server, dislocati in luoghi geografici diversi;
    
- **fault tolerance**: se un server non risponde, le richieste vengono automaticamente inoltrate agli altri;
    
- **repliche multiple**: le copie dei dati garantiscono che nessuna informazione vada persa in caso di guasto.
    

Inoltre, l’uso di **meccanismi di caching** e di **root server globali** riduce notevolmente il rischio di blocchi o rallentamenti anche durante picchi di traffico.

---

### **6. Dinamicità**

Un altro aspetto cruciale è la **capacità del DNS di aggiornarsi dinamicamente**.  
I record non restano statici: possono essere **modificati, aggiunti o rimossi** senza interrompere il servizio.

Grazie al protocollo **Dynamic DNS (DDNS)**, un dispositivo con indirizzo IP variabile (ad esempio, un modem domestico o un server in cloud) può **aggiornare automaticamente il proprio record DNS**, mantenendo sempre attiva la corrispondenza tra nome e indirizzo.

---

### **7. Conclusione**

Il **Domain Name System** è uno dei pilastri invisibili di Internet:  
funziona grazie alla **distribuzione**, alla **replica**, al **caching** e alla **delega gerarchica**, che insieme permettono di gestire miliardi di nomi e richieste al secondo in modo **affidabile, coerente e scalabile**.