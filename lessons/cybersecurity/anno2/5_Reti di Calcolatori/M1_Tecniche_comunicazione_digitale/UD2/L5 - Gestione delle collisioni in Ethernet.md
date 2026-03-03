# **Lezione 5: Gestione delle collisioni in Ethernet**

### **1. Il fenomeno della collisione**

Nelle reti Ethernet tradizionali, più dispositivi condividono lo stesso mezzo fisico (cavo o hub).  
Una **collisione** si verifica quando **due interfacce di rete trasmettono contemporaneamente** sullo stesso canale.

In un mondo ideale, due nodi non trasmetterebbero mai insieme, ma nella pratica ciò accade perché:

- i segnali elettrici o ottici **si propagano a velocità finita**,
    
- quindi un nodo può “credere” che la linea sia libera mentre il segnale di un altro nodo non è ancora arrivato.
    

Il risultato è una **sovrapposizione di segnali**, e quindi un **frame corrotto** che nessuno dei due destinatari può interpretare correttamente.

---

### **2. Come si rileva una collisione**

Durante la trasmissione, ogni scheda di rete Ethernet **ascolta il canale** e confronta **il segnale che legge** con **quello che sta inviando**.

- Se i due segnali coincidono → la trasmissione procede regolarmente.
    
- Se differiscono → è avvenuta una **collisione**.
    

Questo meccanismo di confronto è la base del **CD (Collision Detection)** del protocollo **CSMA/CD**.

---

### **3. Il problema della rilevazione completa**

Supponiamo che due nodi, A e B, siano agli estremi opposti del cavo.

![](imgs/Pasted%20image%2020260212150449.png)

- Il messaggio di **A** raggiunge **B** dopo un tempo $T$.
    
- Se anche **B** inizia a trasmettere, il suo segnale raggiungerà **A** dopo un ulteriore tempo $T$.  
    → quindi, **A scoprirà la collisione dopo un tempo pari a $2T$**.
    

Perché la collisione venga rilevata da tutti i nodi, **A deve essere ancora in trasmissione al tempo $2T$**.  
In altre parole, **la lunghezza minima del frame** deve essere tale da coprire questo intervallo.

---

### **4. Il vincolo temporale imposto dallo standard**

Lo standard **IEEE 802.3** definisce che il tempo massimo di andata e ritorno ($2T$) in una rete Ethernet classica non deve superare **51,2 μs**, che corrisponde a una distanza massima di **2500 metri** tra due host.

A **10 Mbps**, trasmettere un singolo bit richiede $0,1\ \mu s$, quindi per trasmettere un frame minimo di **512 bit (64 byte)** servono esattamente **51,2 μs**.

$$  
t_{frame\_min} = 512\ \text{bit} \times 0.1\ \mu s/\text{bit} = 51.2\ \mu s  
$$

👉 **Conclusione:**  
per garantire che una collisione sia sempre rilevata, **un frame Ethernet deve essere lungo almeno 64 byte**.

---

### **5. Composizione del frame minimo**

Vediamo come si arriva alla lunghezza minima di 64 byte:

|Campo|Dimensione (byte)|
|---|---|
|Intestazione (MAC + tipo)|14|
|Dati minimi (payload)|46|
|CRC (controllo)|4|
|**Totale minimo**|**64 byte**|

Se i dati da trasmettere sono meno di 46 byte, la scheda aggiunge **byte di riempimento (padding)** fino a raggiungere la lunghezza minima.

---

### **6. Il segnale di jam**

Quando una collisione viene rilevata, i nodi coinvolti **interrompono la trasmissione** e inviano un **jam signal** — un breve segnale artificiale di disturbo lungo **48 byte** — per garantire che **tutti i nodi della rete** si accorgano dell’evento.

Solo dopo aver trasmesso questo segnale, la scheda di rete può tentare una nuova trasmissione.

---

### **7. L’attesa casuale e il problema delle collisioni ripetute**

Dopo una collisione, entrambe le stazioni devono **attendere un intervallo casuale** prima di ritentare la trasmissione.  
Tuttavia, se il numero di valori possibili per l’attesa è troppo piccolo, c’è il rischio che i due nodi scelgano di nuovo lo stesso intervallo, **collidendo ancora**.

Per ridurre questa probabilità, Ethernet utilizza un meccanismo detto **backoff esponenziale binario**.

---

### **8. L’algoritmo di backoff esponenziale binario**

L’idea è che dopo ogni collisione il tempo massimo di attesa possibile **raddoppi**.

#### **Funzionamento:**

- Dopo la **1ª collisione**:  
    si sceglie un valore $K$ casuale tra ${0, 1}$  
    e si attende $K \times 51,2\ \mu s$.
    
- Dopo la **2ª collisione**:  
    si sceglie $K$ tra ${0, 1, 2, 3}$  
    → attesa $K \times 51,2\ \mu s$.
    
- Dopo la **n-esima collisione**:  
    $$  
    \text{Delay} = K \times 51.2\ \mu s \quad \text{per } K = 0, 1, 2, \dots, 2^n - 1  
    $$
    

Ogni tentativo fallito **raddoppia lo spazio dei possibili ritardi**, riducendo drasticamente la probabilità di nuove collisioni simultanee.

---

### **9. Limiti del backoff**

Il valore massimo di $K$ previsto dallo standard è **1023**, che corrisponde al 10º tentativo (collisione n=10).  
Dopo **16 tentativi falliti**, l’interfaccia di rete **interrompe definitivamente** la trasmissione e **notifica l’errore all’host**, che potrà eventualmente ritentare più tardi.

---

### **10. Sintesi concettuale**

- Le **collisioni** avvengono quando due nodi trasmettono contemporaneamente.
    
- Devono essere **rilevate entro 51,2 μs**, tempo massimo di propagazione su Ethernet.
    
- Da ciò deriva la **lunghezza minima del frame (64 byte)**.
    
- Dopo una collisione, viene inviato un **jam signal** per informare tutti i nodi.
    
- Il **backoff esponenziale binario** regola i tempi di ritrasmissione per evitare collisioni ripetute.
    
- Dopo 16 tentativi falliti, la scheda **abbandona la trasmissione** e segnala l’errore.