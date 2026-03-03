# **Lezione 9: Flusso e congestione TCP**

---

### **1. Introduzione**

Il protocollo **TCP (Transmission Control Protocol)** implementa due meccanismi distinti ma strettamente correlati:

- il **controllo di flusso**, che impedisce di sovraccaricare il ricevente;
    
- il **controllo della congestione**, che impedisce di sovraccaricare la rete.
    

Entrambi agiscono **regolando dinamicamente la finestra di trasmissione**, ma rispondono a stimoli differenti:

- il **flusso** dipende dallo stato del buffer del destinatario,
    
- la **congestione** dipende dalle condizioni della rete (ritardi e perdite).
    

---

### **2. Controllo di flusso TCP**

L’obiettivo del controllo di flusso è **evitare che il mittente inondi di dati il buffer del destinatario**.

![](imgs/Pasted%20image%2020260225150023.png)

#### **2.1 Parametri fondamentali**

- **RcvBuffer** → capacità totale del buffer di ricezione del destinatario.
    
- **RcvWindow** → porzione libera disponibile nel buffer in un dato momento.
    

> Il destinatario comunica al mittente la quantità di spazio disponibile tramite il campo **RcvWindow** nell’intestazione TCP.

#### **2.2 Funzionamento**

- Ad ogni ACK, il ricevente **aggiorna la dimensione della finestra disponibile**, che può aumentare o diminuire dinamicamente.
    
- Il mittente **mantiene nel proprio buffer** tutti i dati trasmessi ma non ancora confermati, assicurandosi di **non superare la finestra più recente ricevuta**.
    

> In questo modo, il mittente invia sempre la quantità di dati che il destinatario può effettivamente ricevere.

---

### **3. Controllo della congestione TCP**

Il **controllo della congestione** non riguarda il singolo destinatario, ma l’intera rete.  
TCP non riceve alcun **feedback esplicito** dal livello IP: deve quindi dedurre la presenza di congestione **in modo implicito**, osservando:

- **ritardi crescenti** (RTT che aumenta),
    
- **perdite di pacchetti** (ACK mancanti o timeout scaduti).
    

> TCP interpreta questi sintomi come un segnale di congestione e riduce la propria velocità di invio.

---

### **4. Le due finestre di controllo**

Nel TCP convivono **due finestre indipendenti**:

1. **Advertised Window (Receiver Window)** → definita dal destinatario, serve al **controllo di flusso**.
    
2. **Congestion Window (CongWin)** → definita dal mittente, serve al **controllo della congestione**.
    

Il numero effettivo di dati che il mittente può inviare senza ACK è:

$$  
ActualWindow = \min(\text{ReceiverAdvertisedWindow}, \text{CongestionWindow})  
$$

> L’“actual window” è quindi il limite reale: tiene conto sia del ricevente, sia della rete.

---

### **5. Tecniche di controllo della congestione TCP**

Il protocollo TCP utilizza due principali strategie combinate:

![](imgs/Pasted%20image%2020260225150236.png)

#### **5.1 Slow Start (Incremento moltiplicativo)**

- All’inizio di una connessione, la **finestra di congestione** è molto piccola (1 o 2 segmenti).
    
- Per ogni **ACK ricevuto**, TCP **raddoppia** la dimensione della finestra → crescita **esponenziale**.
    
![](imgs/Pasted%20image%2020260225150308.png)

> La rete viene “testata” gradualmente, per individuare il punto in cui inizia la congestione.

Quando la finestra raggiunge un valore soglia chiamato **Threshold**, si passa al meccanismo successivo: **Congestion Avoidance**.

---

#### **5.2 Congestion Avoidance (Incremento additivo)**

- Oltre la soglia (**Threshold**), TCP **aumenta la finestra più lentamente**, in modo **additivo**.
    
- Per ogni ciclo di RTT completo, la finestra cresce di **1 MSS (Maximum Segment Size)**.

![](imgs/Pasted%20image%2020260225150333.png)

In formule:

$$  
\text{CongWin}_{new} = \text{CongWin}_{old} + \frac{\text{MSS}^2}{\text{CongWin}_{old}}  
$$

ma, semplificando concettualmente:

> “Durante la slow start si raddoppia, durante la congestion avoidance si cresce a piccoli passi.”

---

### **6. Le due variabili chiave: CongWin e Threshold**

Durante la vita di una connessione TCP, due parametri fondamentali variano continuamente:

- **CongWin (Congestion Window)** → rappresenta la finestra di congestione corrente.
    
- **Threshold (Soglia)** → delimita la transizione tra fase di crescita esponenziale e crescita additiva.
    

|**Condizione**|**Comportamento**|
|---|---|
|CongWin < Threshold|Slow Start (incremento moltiplicativo)|
|CongWin ≥ Threshold|Congestion Avoidance (incremento additivo)|

> Queste due fasi si alternano automaticamente durante la connessione, adattando la trasmissione allo stato della rete.

---

### **7. Evoluzione dinamica**

Durante la slow start:

- la finestra cresce molto rapidamente finché non compare una **perdita di pacchetto** (segno di congestione).
    

Quando ciò accade:

1. TCP **riduce bruscamente** CongWin;
    
2. aggiorna la **Threshold** (solitamente a metà della finestra precedente);
    
3. riparte in slow start.
    

> Il ciclo di espansione e contrazione consente al protocollo di “sondare” costantemente il limite della rete, senza saturarla definitivamente.

---

### **8. Incremento additivo: limiti**

L’incremento additivo non può proseguire all’infinito:

- Aumentando CongWin, prima o poi la rete raggiunge il punto di **bordo della congestione**, dove iniziano le perdite.
    
- Quando il mittente rileva una perdita:
    
    - **dimezza** la finestra di congestione;
        
    - riavvia la fase di crescita;
        
    - stabilisce una nuova soglia.
        

In pratica:

> TCP oscilla costantemente attorno al massimo throughput possibile, cercando di **utilizzare tutta la banda disponibile senza causare congestione**.

---

### **9. Schema riassuntivo del ciclo TCP**

```text
      +----------------------+
      |     Slow Start       |
      | (CongWin < Threshold)|
      +----------+-----------+
                 |
        Perdite o timeout?
                 |
                 v
      +----------------------+
      | Congestion Avoidance |
      | (Crescita additiva)  |
      +----------+-----------+
                 |
        CongWin troppo grande?
                 |
                 v
      +----------------------+
      |  Riduzione CongWin   |
      |  Aggiorna Threshold  |
      +----------+-----------+
                 |
                 v
            Riparte Slow Start
```

---

### **10. Conclusione**

Il comportamento del TCP nel gestire flusso e congestione è **auto-regolato e dinamico**:

- usa il **controllo di flusso** per rispettare i limiti del destinatario;
    
- usa il **controllo di congestione** per adattarsi alla rete;
    
- alterna **fasi di crescita e riduzione** per mantenere la connessione efficiente e stabile.
    

> Grazie a questi algoritmi, TCP riesce a massimizzare la velocità di trasferimento **senza compromettere la stabilità globale della rete Internet**.


---


![](imgs/Pasted%20image%2020251125071512.png)


---


![](imgs/Pasted%20image%2020251125071530.png)

![](imgs/Pasted%20image%2020251125071537.png)

