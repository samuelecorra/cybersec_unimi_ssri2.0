# **Lezione 11: Fast Retransmit e Fast Recovery**

---

### **1. Introduzione**

Negli episodi precedenti abbiamo visto che il **timeout** è un meccanismo di “ultima istanza” per rilevare la perdita di pacchetti.  
Tuttavia, **attendere la scadenza del timer** è spesso **inefficiente**, poiché implica la perdita di almeno **due interi RTT** prima di poter riprendere la trasmissione.

> TCP introduce quindi un meccanismo più rapido per reagire alle perdite: il **Fast Retransmit**, seguito dal **Fast Recovery**, che consente di ripristinare rapidamente il flusso dati senza ricominciare da capo.

---

### **2. Il principio del Fast Retransmit**

TCP sfrutta un **indizio implicito di perdita**: la **ricezione di ACK duplicati**.

- Ogni volta che un pacchetto arriva al destinatario, quest’ultimo invia un **ACK cumulativo** per l’ultimo byte ricevuto in sequenza.
    
- Se il destinatario **riceve pacchetti successivi ma manca un segmento intermedio**, continuerà a inviare **ACK ripetuti** per lo stesso byte mancante.
    

> Quindi, **3 o più ACK identici consecutivi** per lo stesso numero di sequenza sono considerati **un forte indizio di perdita di pacchetto**.

---

### **3. Perché non basta un singolo ACK duplicato**

Un singolo ACK duplicato può essere causato da:

- riordino temporaneo dei pacchetti,
    
- duplicazione accidentale in un nodo intermedio.
    

> Per questo motivo, TCP **non reagisce subito**: aspetta **almeno 3 ACK duplicati** per essere sicuro che si tratti di una vera perdita.

---

### **4. Comportamento del mittente**

Appena vengono ricevuti **3 ACK duplicati**, il mittente:

1. **Ritrasmette immediatamente** il pacchetto mancante, **senza aspettare il timeout**.
    
2. **Aggiorna i parametri di congestione**:
    
    - `Threshold = CongWin / 2`
        
    - `CongWin = Threshold + 3 × MSS`
        
3. **Incrementa CongWin** di 1 MSS per ogni ACK duplicato aggiuntivo ricevuto (oltre i primi 3).
    

> In pratica, TCP “simula” che la rete stia ancora trasmettendo nuovi dati: gonfia la finestra artificiosamente per compensare i pacchetti che probabilmente hanno già lasciato la rete.

---

### **5. Esempio con finestra di 5 segmenti**

Consideriamo una finestra di 5 segmenti MSS:

1. Il mittente invia i segmenti **1, 2, 3, 4, 5**.
    
2. Il **segmento 1 va perso**, ma i successivi arrivano correttamente.
    
3. Il ricevente, non potendo confermare il pacchetto mancante, invia **ACK duplicati** per “1” dopo ogni pacchetto ricevuto:
    
    - ACK(1), ACK(1), ACK(1), ACK(1)…
        
4. Dopo il **terzo ACK duplicato**, il mittente **ritrasmette subito** il pacchetto 1.
    

> In questo modo la perdita viene recuperata in circa **½ RTT** anziché dopo due RTT come nel caso del timeout.

---

### **6. Passaggio al Fast Recovery**

Dopo il **Fast Retransmit**, il TCP entra nella fase di **Fast Recovery**, progettata per evitare che la finestra torni a 1.

#### **Regole del Fast Recovery**

1. **Threshold = CongWin / 2**
    
2. **CongWin = Threshold + 3 × MSS** (per tener conto dei pacchetti “in volo”)
    
3. Ogni nuovo ACK duplicato → CongWin = CongWin + 1 MSS
    
4. Quando arriva un **ACK per nuovi dati (non duplicato)** →
    
    - **CongWin = Threshold**
        
    - **Fine del Fast Recovery** → ritorno alla fase di **Congestion Avoidance**
        

> In sintesi: il Fast Recovery mantiene la finestra “gonfiata” per non interrompere il flusso e torna gradualmente alla normalità appena la perdita è recuperata.

---

### **7. Esempio numerico del Fast Recovery**

Supponiamo che **CongWin = 16 MSS** al momento della perdita.

1. Threshold = 16 / 2 = **8 MSS**
    
2. CongWin = 8 + 3 = **11 MSS** (Fast Recovery attivo)
    
3. Ricevo altri 2 ACK duplicati → CongWin = 13 MSS
    
4. Appena arriva l’ACK per nuovi dati → CongWin = Threshold = 8 MSS
    

> La trasmissione riprende **senza ripartire da 1**: la connessione è molto più fluida.

---

### **8. Schema riassuntivo del comportamento TCP**

|**Evento**|**Reazione TCP**|**Aggiornamento CongWin**|**Stato successivo**|
|---|---|---|---|
|Timeout|Ritrasmissione dopo timer|CongWin = 1|Slow Start|
|3 ACK duplicati|Fast Retransmit|CongWin = Threshold + 3 MSS|Fast Recovery|
|ACK nuovi (dopo Fast Recovery)|Fine del recupero|CongWin = Threshold|Congestion Avoidance|

---

### **9. Riepilogo concettuale**

|**Meccanismo**|**Funzione**|**Vantaggio principale**|
|---|---|---|
|**Timeout**|Rileva perdite lente|Sicuro, ma inefficiente|
|**Fast Retransmit**|Rileva perdite rapidamente (3 ACK duplicati)|Riduce i tempi di recupero|
|**Fast Recovery**|Evita di tornare a CongWin=1|Mantiene il throughput elevato|

> Insieme, questi due meccanismi completano il ciclo del **controllo della congestione TCP**, permettendo al protocollo di reagire **velocemente ma con prudenza**.

---

### **10. Conclusione**

Il **Fast Retransmit** e il **Fast Recovery** rappresentano la naturale evoluzione del TCP, che da un protocollo puramente reattivo diventa **proattivo**:

- **non aspetta il timeout**, ma reagisce in base ai segnali della rete;
    
- **non azzera la finestra**, ma si adatta gradualmente;
    
- **non perde throughput**, ma mantiene la connessione stabile.
    

> Questi algoritmi — introdotti da **Van Jacobson** alla fine degli anni ’80 — sono tuttora alla base delle versioni moderne del TCP (Tahoe, Reno, New Reno, Cubic) e rimangono uno dei capisaldi dell’efficienza di Internet.


---


![](imgs/Pasted%20image%2020251125071749.png)


---

![](imgs/Pasted%20image%2020251125071808.png)

![](imgs/Pasted%20image%2020251125071916.png)