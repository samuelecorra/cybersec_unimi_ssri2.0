## **Lezione 5: Attacchi TCP – SYN flooding**

### **1. Introduzione**

Il **SYN flooding** è un attacco DoS che sfrutta l'asimmetria nel protocollo TCP durante l'apertura di una connessione. L'attaccante invia un grande numero di pacchetti SYN (spesso con indirizzi sorgente contraffatti), inducendo il server a creare stati parziali di connessione (half-open). Questi stati occupano risorse limitate sul server e, se la coda di backlog si riempie, il server rifiuta connessioni legittime.

### **2. Meccanismo operativo**

Quando un server riceve un pacchetto SYN avvia la procedura di handshake allocando memoria per il Transmission Control Block (TCB) e rispondendo con un SYN/ACK. Se il client non completa l'handshake inviando l'ACK finale (per esempio perché l'IP sorgente era spoofato e non esiste risposta), il server mantiene lo stato in SYN-RECEIVED fino al timeout. Ripetendo questa operazione molte volte con SYN spoofati si esauriscono le voci della backlog queue e si impedisce l'accettazione di nuove connessioni legittime.

### **3. Perché è efficace (asimmetria risorse / costo)**

L'attaccante spende pochissimi byte per ogni SYN (tipicamente 40 byte a livello IP: 20 IP + 20 TCP), mentre il server deve allocare centinaia di byte (TCB) per ogni richiesta. Questa asimmetria rende l'attacco molto economico per l'aggressore e pesante per la vittima: con un backlog anche piccolo (es. 128 voci) basta inviare pochi kilobyte per saturarlo in modo ripetuto. Il tempo di mantenimento dello stato (timeout SYN-RECEIVED) amplifica ulteriormente l'effetto.

### **4. Esempi storici e impatto**

Un caso noto è l'attacco contro il servizio Panix nel 1996, dove SYN flood massivi causarono la chiusura di servizi di posta, web e news. Un altro esempio è il comportamento osservato con il worm MS Blaster che generava SYN flood verso `windowsupdate.com`. Infine, attacchi coordinati su larga scala (es. contro strutture governative come nel caso dell'Estonia) hanno mostrato che volumi elevati di SYN/ICMP floods possono saturare banda e risorse, costringendo anche gli ISP a filtrare o bloccare traffico esterno.

### **5. Parametri dell’attacco e fattori da conoscere**

Per pianificare un attacco (o per capire la vulnerabilità), è importante conoscere il sistema della vittima: dimensione della backlog, durata del timeout per SYN-RECEIVED, formato del TCB e altre specifiche configurazioni. Molti sistemi hanno backlog predefiniti piccoli (128 o meno) e timeout che possono arrivare a centinaia di secondi (es. ~189 s nelle slide). Con questi parametri l'attaccante può calcolare esattamente la banda minima necessaria per mantenere il backlog pieno.

### **6. Contromisure lato end-host**

Le difese che possono essere implementate sul server includono:

- aumentare la dimensione della TCP backlog per gestire più half-open connections;
    
- ridurre la durata del timeout dello stato SYN-RECEIVED;
    
- usare strutture alternative come le SYN cache che occupano meno risorse per ogni richiesta;
    
- implementare **SYN cookies**, una tecnica che evita di allocare stato fino a che il client non dimostra di esistere rispondendo correttamente al SYN/ACK.
    

### **7. Dettaglio: SYN cookies (principio)**

I SYN cookies rispondono al SYN senza memorizzare lo stato; invece il server costruisce un valore (cookie) basato su informazioni della connessione (indirizzi e porte, un contatore temporale e una chiave segreta) e lo mette nel numero di sequenza del SYN/ACK. Quando il client risponde con l'ACK, il server ricava dal cookie i parametri originali e verifica la validità senza aver mai allocato uno TCB permanente. Se il cookie è valido, il server allora alloca lo stato completo. I cookie devono essere costruiti in modo non falsificabile (es. MAC/crypto hash con segreto del server) e prevedono limitazioni (perdita di alcune opzioni TCP fino alla conferma).

### **8. Ulteriori contromisure pratiche**

Oltre ai SYN cookies, soluzioni semplici ma efficaci includono: cancellare casualmente una voce della SYN queue quando è piena (aumentando le probabilità che connessioni legittime restino possibili) e usare servizi di mitigazione esterni (proxy reverse o scrubbing services) che terminano le connessioni SYN e inoltrano solo traffico già stabilito al sito reale. Un esempio commerciale è Prolexic (ora parte di Akamai) che filtra/terminale traffico e riduce l'impatto sul sito originario.

### **9. Sintesi finale**

Il SYN flooding è un classico DoS che sfrutta l'allocazione di stato del TCP e la difficoltà del server di distinguere SYN legittimi da SYN spoofati. Le difese richiedono un mix di tecniche locali (dimensionamento backlog, timeout, SYN cache, SYN cookies) e soluzioni infrastrutturali (filtraggio ISP, proxy di mitigazione). L'adozione di SYN cookies è storicamente una delle contromisure più efficaci e compatibili con lo stack TCP standard.