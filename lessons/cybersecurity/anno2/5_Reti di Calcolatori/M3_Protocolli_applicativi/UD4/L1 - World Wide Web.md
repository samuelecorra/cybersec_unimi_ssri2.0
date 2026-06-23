# **Lezione 1: World Wide Web**

---

### **1. Il Web come sistema client/server**

Il **World Wide Web** riveste un interesse del tutto particolare per la sua estensione e pervasività: è il **sistema client/server più diffuso al mondo**.

La comunicazione avviene attraverso un paradigma di **scambio di messaggi di tipo pull**, secondo il modello **richiesta–risposta**. In questo paradigma il **client TCP è anche il client a livello di protocollo applicativo**: è lui a parlare per primo nell'interscambio tra le due parti, inviando la richiesta.

---

### **2. L'operazione fondamentale: la URL**

Dal punto di vista dell'utente, l'operazione fondamentale è selezionare — digitandola o recuperandola dai preferiti — una forma estesa di indirizzo: la **URL (Uniform Resource Locator)**.

Una volta inserita la URL, il browser compie una serie di operazioni per richiedere la risorsa corrispondente al server. Il server risponde con un messaggio di cui interessano:

- il **contenuto**, che il browser visualizzerà;
- soprattutto l'**intestazione**, che contiene campi descrittivi — in primo luogo il **Content-Type** (in analogia con quanto appreso sui protocolli di posta elettronica e MIME) — che consentono al browser di gestire correttamente le informazioni ricevute.

Se la risorsa è collegata ad altre risorse (immagini, script, CSS…), queste andranno trasferite con **connessioni separate**.

---

### **3. L'idea originale del Web: Tim Berners-Lee al CERN**

![](imgs/Pasted%20image%2020260225163022.png)

Il Web fu inventato da **Tim Berners-Lee** presso il laboratorio **CERN** di Ginevra. L'idea originale era semplice:

- qualunque utente con un client può **cliccare su un collegamento** all'interno di una pagina già ricevuta, oppure **digitare direttamente** l'indirizzo di una pagina nuova;
- entrambe le operazioni producono lo stesso effetto: una **richiesta dal client al server** appropriato, poiché la URL specifica anche la macchina su cui si trovano i dati;
- il server — che non è altro che un **grande file system** — ricerca la risorsa richiesta (un file di qualche tipo nelle proprie directory) e la rimanda al client.

---

### **4. Il protocollo HTTP**

Il formato delle richieste e delle risposte è definito da un protocollo a livello applicativo: **HTTP (HyperText Transfer Protocol)**. HTTP è:

- **orientato agli oggetti**: è un protocollo per il trasferimento di oggetti/risorse in generale;
- **generico**: non è pensato per trasferire un tipo specifico di risorsa, ma può trasferirne molti;
- **stateless**: ogni richiesta fa storia a sé; la risposta a una data richiesta HTTP è sempre la stessa se la richiesta è formulata in modo identico, indipendentemente da quelle precedenti.

---

### **5. La porta 80 e la sicurezza**

Dal punto di vista della sicurezza, un elemento fondamentale di HTTP è che **non si può chiudere la porta 80**: HTTP viaggia su una porta universalmente utilizzata su Internet, e con pochissime eccezioni quasi nessuna organizzazione può permettersi di filtrare questo traffico. HTTP gode quindi di un'**elevata uniformità di raggiungibilità**: da qualunque punto di Internet a qualunque altro, i pacchetti sulla porta 80 riescono in genere a viaggiare attraverso tutti gli schermi e i filtri di pacchetti presenti.

> ⚠️ Questa caratteristica è un'arma a doppio taglio: garantisce massima accessibilità al Web, ma rende HTTP un canale difficilmente bloccabile anche per traffico potenzialmente indesiderato.

---

### **6. Stateless: vantaggi e problemi**

Il fatto di essere **stateless** presenta due facce.

**Vantaggio**: consente la **ridondanza dei server**. Poiché ogni richiesta fa storia a sé, se due server hanno lo stesso contenuto non fa differenza a quale dei due venga indirizzata la richiesta. Questo rende HTTP naturalmente adatto al bilanciamento del carico e alla replicazione.

**Problema**: se si vogliono gestire transazioni articolate in più passi — tipiche dell'**e-commerce** — HTTP da solo non basta. Consideriamo un modulo di acquisto in sette passi: un client che invia solo il terzo modulo deve essere trattato diversamente da un client che li sta inviando tutti in successione, perché solo quest'ultimo sta completando la transazione. Ma HTTP non ha memoria delle richieste precedenti e non può fare questa distinzione da solo.

> 📌 Per risolvere questo problema occorrono apposite tecniche che si **aggiungono a HTTP** e supportano le sessioni, rendendo il protocollo **stateful** per le applicazioni che lo richiedono. Questo aspetto sarà approfondito nelle prossime lezioni.

---

### **7. HTTP/1.0**

La prima versione diffusa, **HTTP/1.0**, mappava direttamente ogni richiesta applicativa su una singola connessione TCP:

- ogni richiesta e ogni risposta apriva una **nuova connessione TCP**;
- una pagina con molti elementi (immagini, script…) richiedeva altrettante connessioni TCP separate.

Una grande quantità di connessioni TCP, ciascuna delle quali trasporta poche informazioni, tende a essere **meno efficiente** di una connessione unica: l'overhead di apertura/chiusura si ripete per ogni singola risorsa, e i parametri di throughput non riescono a essere ottimizzati.

---

### **8. HTTP/1.1: connessioni persistenti e pipelining**

Con **HTTP/1.1** fu adottata una prospettiva diversa: la prima richiesta a un server apre una **connessione TCP persistente** che rimane aperta finché le due parti non decidono di chiuderla o scade un timeout. Su quella connessione vengono trasportate tutte le richieste e risposte successive.

Vantaggi:

- **Pipelining**: è possibile inviare **più richieste senza aspettare la risposta a ciascuna**, sfruttando il **parallelismo interno del server**. Ad esempio, se il server tiene le immagini su un disco e i file di testo su un altro, il client può richiedere in parallelo testi e immagini, permettendo al server di accedere contemporaneamente a entrambi i sottosistemi.
- **Ottimizzazione del throughput**: una singola connessione che trasporta più dati consente di calibrare meglio i parametri TCP di trasmissione.

---

### **9. Conclusione**

Il **World Wide Web** è costruito interamente su **HTTP**, un protocollo semplice, scalabile e indipendente dallo stato. La sua architettura client/server e il modello richiesta–risposta rendono possibile ogni interazione tra browser e server. Il fatto di essere stateless — con i vantaggi e i limiti che ne conseguono — è la caratteristica fondamentale da tenere presente per comprendere le evoluzioni successive del protocollo, che aggiungeranno meccanismi di gestione dello stato per supportare le applicazioni moderne.
