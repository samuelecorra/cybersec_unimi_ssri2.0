# **Lezione 6: Calcolo del timeout TCP**

---

### **1. Richiamo: pieno utilizzo del collegamento**

Per utilizzare **completamente** un collegamento TCP occorre che la **finestra di trasmissione** sia **abbastanza grande** da riempire l’intera pipeline di rete.

$$  
\text{Finestra} \geq \text{Larghezza di banda} \times \text{RTT}  
$$

- Una finestra troppo piccola non riesce a saturare il canale.
    
- Una finestra adeguata garantisce il massimo throughput, sfruttando interamente la capacità del collegamento.
    

---

### **2. Scambio interattivo vs. Bulk Transfer**

#### **Scambio interattivo**

- Tipico di Telnet, SSH, Rlogin.
    
- Mira a ridurre la latenza accumulando pochi dati:
    
    - **ACK ritardato**
        
    - **Algoritmo di Nagle**
        

#### **Bulk transfer**

- Trasferimento massivo di dati (es. FTP, backup).
    
- Mira a saturare il canale utilizzando:
    
    - **Advertised Window** del destinatario
        
    - **Persist timer**
        

---

### **3. Timeout e ritrasmissione**

TCP è un protocollo **affidabile**, quindi ogni segmento inviato deve essere **confermato** dal destinatario.  
Il mittente, per ogni segmento, imposta un **timer di ritrasmissione**:

- Se riceve l’ACK entro il tempo previsto → tutto regolare.
    
- Se il timer scade prima dell’ACK → **ritrasmissione automatica** del segmento.
    

> Il valore del timeout non è fisso: deve adattarsi dinamicamente alle condizioni della rete.

---

### **4. Ritrasmissione adattativa**

Il **tempo di round trip (RTT)** può variare nel tempo per effetto di:

- congestione momentanea,
    
- variazioni di percorso,
    
- ritardi nei router.
    

Per questo motivo il **timeout TCP** viene calcolato come **funzione del RTT medio e della sua varianza**, così da adattarsi alle condizioni correnti.

---

### **5. Scenari di ritrasmissione**

#### **Scenario 1 – Perdita del segmento**

Il mittente invia un pacchetto, ma questo viene perso.  
Non riceve l’ACK → scatta il **timeout**, e il pacchetto viene ritrasmesso.

![](imgs/Pasted%20image%2020260225144748.png)

#### **Scenario 2 – Perdita dell’ACK**

Il pacchetto arriva correttamente, ma l’ACK viene perso.  
Il mittente non riceve conferma e **ritrasmette lo stesso segmento**, anche se il destinatario lo aveva già ricevuto.

> TCP gestisce questo caso grazie alla **tolleranza ai duplicati**: il ricevente riconosce i segmenti già ricevuti e li scarta.

![](imgs/Pasted%20image%2020260225144832.png)

#### **Scenario 3 – Timeout prematuro**

Se il timeout è **troppo breve**, il mittente ritrasmette **prima** che arrivi l’ACK, generando **duplicati inutili**.

#### **Scenario 4 – Ritrasmissione veloce**

In presenza di **ACK duplicati (≥3)**, TCP attiva una **ritrasmissione rapida (Fast Retransmit)**, anticipando il timeout.

---

### **6. Impostazione del timeout TCP**

Il **valore del timeout** deve:

- essere **maggiore del RTT medio**,
    
- ma non **troppo grande**, per reagire rapidamente alle perdite.
    

#### **Errori comuni**

- **Timeout troppo breve** → ritrasmissioni inutili.
    
- **Timeout troppo lungo** → reazione lenta in caso di perdita reale.
    

---

### **7. Stima del Round Trip Time (RTT)**

TCP calcola il tempo di andata e ritorno di un segmento con:

$$  
RTT = \text{currentTime} - \text{sentTime}  
$$

Tuttavia:

- non sempre c’è corrispondenza 1:1 tra segmenti inviati e ACK (gli ACK sono cumulativi);
    
- le ritrasmissioni rendono ambigua la misura del RTT;
    
- per evitare oscillazioni, TCP usa una **media mobile esponenziale** dei campioni recenti.
    

---

### **8. Media esponenziale pesata**

La stima del RTT viene aggiornata con la formula:

$$  
EstimatedRTT = (1 - \alpha) \cdot EstimatedRTT + \alpha \cdot SampleRTT  
$$

L'idea di questa stima è prendere in considerazione anche il valore campionato più recentemente, il sample e anche il valore precedente della stima, a sua volta basato sui valori precedenti di campionamento.

- $\alpha$ è un **fattore di peso** tra 0 e 1.
    
- Valore tipico: $\alpha = 0.1$
    

> Il valore basso di $\alpha$ dà più peso alla media storica (stabilità), mentre un valore più alto rende la stima più reattiva ma anche più instabile. 
> 
> E' esponenziale nel senso che l'influenza dei vecchi campioni diminuisce molto velocemente!

---

### **9. Timeout con margine di sicurezza**

Per tenere conto delle fluttuazioni del RTT, il timeout deve includere un **fattore di sicurezza** proporzionale alla varianza del ritardo:

$$  
Timeout = EstimatedRTT \times DelayVarianceFactor  
$$

Dove, tipicamente:

$$  
DelayVarianceFactor = 2  
$$

---

### **10. Limiti di questa stima**

Se il RTT oscilla molto (reti instabili, congestione intermittente), la sola media esponenziale non basta.  
È necessario tenere conto anche della **varianza** delle misure per evitare errori di previsione.

---

### **11. Metodo Jacobson/Karels**

Per una stima più affidabile, TCP utilizza l’**algoritmo di Jacobson e Karels (1988)**, che tiene conto **sia della media che della deviazione** delle misurazioni.

#### **Formule**

$$  
\begin{cases}  
EstimatedRTT = (1 - \alpha) \cdot EstimatedRTT + \alpha \cdot SampleRTT \\\\  
Error = |SampleRTT - EstimatedRTT| \\\\  
Deviation = Deviation + \beta \cdot (Error - Deviation) \\\\  
Timeout = EstimatedRTT + 4 \cdot Deviation  
\end{cases}  
$$

dove:

- $\alpha$ = 0.125 (peso medio più rapido rispetto alla formula originale)
    
- $\beta$ = 0.25 (coefficiente di aggiornamento della deviazione)
    

---

### **12. Interpretazione del modello**

- La media **segue lentamente** l’andamento del RTT reale.
    
- La deviazione **misura quanto il RTT fluttua**.
    
- Il moltiplicatore “4” nella formula del timeout rappresenta il **margine di sicurezza** (≈ 4 deviazioni standard).
    

> Così, se la rete diventa improvvisamente più lenta, il timeout aumenta automaticamente; se la rete si stabilizza, il timeout torna a valori più stretti.

---

### **13. Sintesi dei parametri consigliati**

|Parametro|Simbolo|Valore tipico|Effetto|
|---|---|---|---|
|Peso della media|$\alpha$|0.125|Più alto → risposta più rapida|
|Peso della deviazione|$\beta$|0.25|Stabilizza la variazione|
|Fattore di sicurezza|—|4|Aumenta la tolleranza alle variazioni|
|Timeout finale|—|$EstimatedRTT + 4 \cdot Deviation$|Adattivo, bilanciato|

---

### **14. Conclusione**

Il **calcolo del timeout TCP** è un compromesso tra:

- **reattività** → reagire presto alle perdite reali;
    
- **stabilità** → evitare ritrasmissioni inutili dovute a ritardi momentanei.
    

Il modello di **Jacobson/Karels** ha risolto il problema classico delle prime versioni TCP:  
un timeout **troppo rigido o troppo instabile** che portava a congestioni artificiali.

> In definitiva, l’attuale gestione del timeout TCP è uno dei motivi per cui la rete Internet riesce ad adattarsi fluidamente a condizioni dinamiche, mantenendo l’affidabilità del trasporto anche su connessioni estremamente variabili.


---


![](imgs/Pasted%20image%2020251125071140.png)

![](imgs/Pasted%20image%2020251125071148.png)


---


![](imgs/Pasted%20image%2020251125071206.png)

