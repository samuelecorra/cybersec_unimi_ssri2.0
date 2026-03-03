# **Lezione 5: Flusso TCP (Parte I)**

---

### **1. Tipi di flusso di dati in TCP**

Il protocollo TCP deve adattarsi a diverse **tipologie di flusso di dati**, ciascuna con proprie esigenze di efficienza e latenza.  
Possiamo distinguere due grandi categorie:

1. **Flusso interattivo** → scambio di piccoli pacchetti (es. Telnet, SSH, Rlogin).
    
2. **Flusso massivo (Bulk Transfer)** → invio di grandi quantità di dati (es. trasferimenti FTP, download, backup).
    

Inoltre, TCP impiega due principali **tipi di controllo**:

- **Controllo di flusso** → per evitare overflow nei buffer del destinatario.
    
- **Controllo di congestione** → per evitare sovraccarichi nella rete (approfondito nella parte II).
    

---

### **2. Flusso interattivo**

Un flusso interattivo è caratterizzato da:

- pacchetti **molto piccoli**, spesso con un solo byte di dati (es. un tasto premuto);
    
- **alta sensibilità alla latenza**, poiché l’utente si aspetta una risposta immediata.
    

#### **Esempio: Connessione Telnet o Rlogin**

Un terminale remoto invia ogni carattere digitato come **pacchetto TCP separato**.  
Il server riceve il carattere e lo rimanda indietro al client come **eco**, in modo che venga visualizzato sullo schermo locale.

In pratica:

- ogni pacchetto contiene **1 byte di dati**,
    
- **20 byte** di intestazione TCP + **20 byte** di intestazione IP,
    
- per un totale di **41 byte trasmessi per 1 byte utile** → **efficienza < 3%**.
    

> La maggior parte del traffico è costituita da **informazioni di controllo**, non dai dati reali.

---

### **3. Trasporto di dati in piggyback**

Per ottimizzare la trasmissione nei flussi interattivi, TCP utilizza la tecnica del **piggybacking**, già vista nel controllo di errore.

#### **Funzionamento**

- Ogni pacchetto TCP inviato in una direzione può **trasportare anche un ACK** relativo ai dati ricevuti nella direzione opposta.
    
- In questo modo si evita di inviare pacchetti di sola conferma.
    
![](imgs/Pasted%20image%2020260225144125.png)

#### **Esempio pratico**

1. L’utente digita `'C'`.
    
2. Il terminale invia `'C'` al server in un pacchetto TCP.
    
3. Il server, dopo aver ricevuto `'C'`, invia un pacchetto di eco contenente:
    
    - il carattere `'C'` come dato, e
        
    - l’**ACK** per la ricezione del pacchetto precedente.
        

Questo riduce il numero di pacchetti e aumenta leggermente l’efficienza del flusso.

---

### **4. ACK ritardati**

In TCP, l’ACK **non viene sempre inviato immediatamente**.  
Il ricevente può **ritardare leggermente** la conferma in attesa di nuovi dati da trasmettere, così da poter **includere l’ACK nel prossimo pacchetto in uscita**.

> In pratica, si “aspetta un po’” sperando di poter fare piggyback.

![](imgs/Pasted%20image%2020260225144226.png)

#### **Esempio – Scenario Telnet**

- L’utente digita `'C'`.
    
- Il server riceve il pacchetto e genera un ACK, ma **lo ritarda** per alcuni millisecondi.
    
- Se nel frattempo deve inviare un altro pacchetto (es. l’eco del carattere `'C'`), include l’ACK al suo interno.
    

In questo modo si evita un pacchetto separato solo per la conferma, risparmiando **tempo e banda**.

---

### **5. L’algoritmo di Nagle**

Il **Nagle Algorithm** è stato introdotto per **ottimizzare i flussi interattivi** che generano molti pacchetti piccoli, riducendo l’overhead di rete.

#### **Principio**

> _“Non inviare un nuovo pacchetto finché non è stato ricevuto l’ACK per quello precedente.”_

#### **Regola operativa**

- Se una connessione TCP ha **dati in sospeso** per i quali non è ancora arrivato un ACK,  
    → il mittente **accumula i nuovi dati** nel buffer e **non invia subito** un nuovo segmento.
    
- Quando riceve l’ACK, invia **tutti i dati accumulati** in un unico segmento.
    
- Se il buffer si riempie o passa troppo tempo, i dati vengono comunque inviati (timeout interno).
    

---

#### **6. Adattamento dinamico**

L’algoritmo si adatta alle **condizioni della rete**:

- Su **LAN veloci**, dove gli ACK arrivano in tempi brevissimi, i dati vengono inviati quasi subito → la latenza è minima.
    
- Su **WAN o reti geografiche lente**, gli ACK impiegano più tempo ad arrivare → i dati si accumulano e vengono inviati in blocco.
    

> In questo modo TCP regola automaticamente il ritmo di trasmissione, bilanciando **efficienza e interattività**.

---

### **7. Esempio operativo dell’algoritmo di Nagle**

#### **Scenario Telnet**

![](imgs/Pasted%20image%2020260225144257.png)

1. L’utente digita `'C'`.
    
    - Il terminale invia un pacchetto TCP contenente `'C'`.
        
2. L’utente digita `'A'`.
    
    - Il software TCP **non invia subito** `'A'`, perché è in attesa dell’ACK per `'C'`.
        
3. L’utente digita `'T'`.
    
    - I caratteri `'A'` e `'T'` vengono **accumulati**.
        
4. Quando arriva l’ACK per `'C'`, il software invia un **unico segmento TCP** contenente `'A'` e `'T'`.
    

> Il destinatario riceve i caratteri “AT” insieme, riducendo il numero di pacchetti e migliorando la resa del canale.

---

### **8. Vantaggi dell’algoritmo di Nagle**

✅ **Riduzione dell’overhead di rete:** meno pacchetti piccoli, quindi minore occupazione della banda.  
✅ **Migliore efficienza nelle linee lente o congestionate.**  
✅ **Ottimizzazione automatica:** si adatta dinamicamente alla velocità degli ACK.

---

### **9. Svantaggi**

⚠️ **Lieve aumento della latenza percepita** nei flussi molto interattivi (es. digitazione carattere per carattere).  
⚠️ **Non adatto a sistemi real-time** o applicazioni che richiedono risposta immediata.

> Per questo motivo, l’algoritmo di Nagle può essere **disattivato** nelle applicazioni che necessitano di bassa latenza (es. giochi online, streaming in tempo reale).

---

### **10. Conclusione**

Il TCP non invia i dati “alla cieca”, ma **li modula in base al contesto e alle condizioni della rete**:

- nei flussi **interattivi**, sfrutta il **piggybacking** e l’**algoritmo di Nagle** per ridurre il numero di pacchetti e migliorare l’efficienza;
    
- nei flussi **massivi**, utilizzerà (nella parte II) algoritmi di **gestione della congestione** come **Slow Start** e **Congestion Avoidance**.
    

> In sintesi: TCP è un protocollo “intelligente” che cerca costantemente l’equilibrio tra **reattività** e **efficienza**.