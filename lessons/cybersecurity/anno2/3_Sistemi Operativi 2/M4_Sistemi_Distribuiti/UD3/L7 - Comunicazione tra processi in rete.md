# **M4 UD3 Lezione 7 - Comunicazione tra processi in rete**

---

### **1. Introduzione**

In questa lezione viene studiato come supportare la comunicazione tra processi in un **ambiente distribuito**.

L'obiettivo e' estendere al caso di piu' calcolatori connessi in rete le tecniche gia' viste per processi residenti su una singola macchina.

Le tecniche considerate sono:

- scambio di messaggi;
- mailbox;
- file;
- socket.

La differenza fondamentale rispetto al caso locale e' che in un ambiente distribuito **non esiste una memoria centrale condivisa** tra tutte le macchine.

Quindi le informazioni non possono essere semplicemente depositate in una zona comune di memoria: devono essere trasferite dal sistema operativo della macchina del mittente al sistema operativo della macchina del ricevente.

---

### **2. Comunicazione locale e comunicazione distribuita**

Nel caso di una singola macchina, due processi possono comunicare usando strutture mantenute dal sistema operativo nella memoria centrale della stessa macchina.

In ambiente distribuito, invece:

- il processo mittente puo' trovarsi su una macchina $M_1$;
- il processo ricevente puo' trovarsi su una macchina $M_2$;
- i buffer, le mailbox o i file possono trovarsi su $M_1$, su $M_2$ o su una terza macchina.

Il compito del sistema operativo e' rendere questa differenza il piu' possibile trasparente.

> 📌 La comunicazione distribuita e' una comunicazione locale estesa con il trasferimento fisico delle informazioni attraverso la rete.

---

### **3. Scambio di messaggi**

Lo scambio di messaggi in ambiente distribuito e' concettualmente analogo a quello su una singola macchina.

Un processo $P$ invia un messaggio a un processo $Q$.

La differenza e' che i buffer usati per depositare i messaggi possono trovarsi su macchine diverse.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Buffer sulla macchina del mittente**

Supponiamo che:

- il processo $P$ sia sulla macchina $M_1$;
- il processo $Q$ sia sulla macchina $M_2$.

In una prima soluzione, il messaggio viene depositato in un buffer del sistema operativo della macchina $M_1$.

Quando $Q$ vuole leggere il messaggio:

1. il sistema operativo trasferisce il messaggio da $M_1$ a $M_2$;
2. il messaggio viene reso disponibile sulla macchina del ricevente;
3. $Q$ puo' prelevarlo.

Questa soluzione mantiene il deposito iniziale vicino al mittente, ma richiede un trasferimento al momento della ricezione.

---

### **5. Buffer sulla macchina del ricevente**

In una seconda soluzione, il processo $P$ invia il messaggio usando una primitiva che deposita direttamente il messaggio in un buffer della macchina $M_2$.

In questo caso:

- l'invio include gia' il trasferimento remoto;
- il messaggio viene collocato sulla macchina del ricevente;
- $Q$ lo legge localmente dal buffer di $M_2$.

Questa soluzione rende piu' semplice la ricezione, perche' il messaggio e' gia' vicino al destinatario.

---

### **6. Mailbox in ambiente distribuito**

La mailbox e' un canale di comunicazione indiretto.

Il processo mittente non comunica direttamente con il ricevente, ma deposita il messaggio in una casella logica.

In ambiente distribuito la mailbox puo' essere collocata:

- sulla macchina del mittente;
- sulla macchina del ricevente;
- su una macchina di servizio.

---

### **7. Mailbox sulla macchina del mittente**

Supponiamo che:

- $P$ si trovi su $M_1$;
- $Q$ si trovi su $M_2$;
- la mailbox $M$ sia allocata su $M_1$.

Il processo $P$ invia il messaggio alla mailbox locale.

Quando $Q$ vuole ricevere il messaggio:

1. accede alla mailbox remota;
2. il sistema operativo trasferisce il messaggio da $M_1$ a $M_2$;
3. il messaggio viene consegnato a $Q$.

Il trasferimento remoto avviene in modo trasparente rispetto ai processi.

---

### **8. Mailbox sulla macchina del ricevente**

La mailbox puo' anche essere collocata sulla macchina del ricevente.

In questo caso:

- $P$ invia il messaggio a una mailbox remota;
- il sistema operativo deposita il messaggio nella mailbox presente su $M_2$;
- $Q$ legge localmente il messaggio dalla mailbox.

Questa soluzione sposta il costo della comunicazione sull'operazione di invio.

> 💡 La scelta della posizione della mailbox incide su quale operazione diventa remota: invio o ricezione.

---

### **9. Comunicazione tramite file**

Anche i file possono essere usati come mezzo di comunicazione tra processi distribuiti.

Il processo mittente scrive nel file le informazioni da comunicare.

Il processo ricevente legge dal file le informazioni scritte.

Questo modello e' analogo al caso della singola macchina, ma il file puo' trovarsi su una macchina diversa da quella di uno o entrambi i processi.

---

### **10. File sulla macchina del mittente**

Supponiamo che:

- $P$ sia sulla macchina $M_1$;
- $Q$ sia sulla macchina $M_2$;
- il file sia memorizzato su $M_1$.

Il processo $P$ scrive il messaggio nel file locale.

Il processo $Q$ effettua una lettura remota del file, ottenendo il messaggio.

La lettura puo' essere realizzata:

- tramite operazioni esplicite su file remoto;
- tramite montaggio del file system remoto;
- tramite un file system distribuito che rende il file visibile come se fosse locale.

---

### **11. File sulla macchina del ricevente o su server terzo**

Il file puo' trovarsi anche sulla macchina del ricevente.

In questo caso:

- $P$ effettua una scrittura remota;
- $Q$ effettua una lettura locale.

Una terza possibilita' e' usare un server dedicato $S$.

In questo scenario:

- $P$ opera su $M_1$;
- $Q$ opera su $M_2$;
- il file usato per la comunicazione si trova su una macchina di servizio $S$.

Il processo $P$ scrive sul file posto sul server.

Il processo $Q$ legge dallo stesso file, sempre sul server.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Questa soluzione e' tipica dei sistemi in cui si usa una risorsa condivisa stabile per scambiare informazioni tra processi distribuiti.

---

### **12. Socket**

I socket sono un meccanismo di comunicazione basato su una **porta virtuale**.

Nel caso locale, due processi sulla stessa macchina possono usare un socket come canale di comunicazione:

- il mittente scrive sulla porta;
- il ricevente legge dalla stessa porta.

In ambiente distribuito il concetto e' esteso alla rete.

Il processo $P$ sulla macchina $M_1$ scrive sul socket.

Il sistema operativo trasferisce il messaggio alla controparte del socket sulla macchina $M_2$.

Il processo $Q$ legge dalla porta del socket sulla propria macchina.

---

### **13. Comunicazione tramite socket distribuiti**

Nel caso distribuito, il socket collega due estremi:

- un estremo sulla macchina del mittente;
- un estremo sulla macchina del ricevente.

Il sistema operativo si occupa di:

- trasferire i dati attraverso la rete;
- mantenere l'ordine dei messaggi quando il protocollo lo prevede;
- consegnare i messaggi al processo corretto;
- rendere uniforme l'interfaccia di comunicazione.

Il processo ricevente legge i messaggi dalla porta del socket man mano che arrivano.

> ✅ I socket sono il meccanismo piu' diretto per costruire comunicazioni client/server tra processi su macchine diverse.

---

### **14. Ruolo del sistema operativo**

La realizzazione della comunicazione distribuita e' responsabilita' del sistema operativo.

Il sistema operativo deve:

- remotizzare le operazioni di invio e ricezione;
- trasferire fisicamente le informazioni tra macchine;
- gestire buffer, mailbox, file o socket;
- rendere omogenea la comunicazione tra macchine potenzialmente diverse;
- nascondere ai processi i dettagli della rete quando possibile.

In particolare, deve garantire che mittente e ricevente possano interpretare correttamente le informazioni scambiate anche quando le macchine hanno architetture o rappresentazioni interne diverse.

---

### **15. Sintesi**

| Tecnica | Dove si trova il mezzo di comunicazione | Caratteristica |
|---|---|---|
| Messaggi | Buffer su mittente o ricevente | Comunicazione diretta tramite deposito e prelievo |
| Mailbox | Macchina del mittente, ricevente o servizio | Comunicazione indiretta tramite casella |
| File | Mittente, ricevente o server condiviso | Comunicazione persistente tramite lettura/scrittura |
| Socket | Porte sui due estremi della comunicazione | Comunicazione diretta attraverso la rete |

---

### **16. Conclusione**

Le tecniche di comunicazione tra processi possono essere estese all'ambiente distribuito quando non richiedono una memoria centrale condivisa.

Messaggi, mailbox, file e socket permettono di trasferire informazioni tra processi residenti su macchine diverse.

Il sistema operativo si occupa della remotizzazione delle operazioni e del trasferimento dei dati, cercando di fornire ai processi una visione il piu' possibile uniforme rispetto al caso locale.
