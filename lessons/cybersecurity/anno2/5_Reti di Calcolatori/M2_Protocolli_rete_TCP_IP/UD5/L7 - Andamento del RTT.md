# **Lezione 7: Andamento del RTT**

---

### **1. Introduzione**

In questa lezione analizziamo **sperimentalmente** il comportamento del **Round Trip Time (RTT)** e del relativo **timeout TCP**, confrontando:

- il **metodo originale** di calcolo del timeout, basato solo sulla media del RTT;
    
- il **metodo di Jacobson e Karels**, che introduce anche la **varianza (deviazione)** come fattore di adattamento dinamico.
    

L’obiettivo è capire **come varia il timeout** in presenza di modifiche improvvise del RTT — sia verso l’alto (ritardo crescente) sia verso il basso (rete che si alleggerisce) — e valutare la **stabilità e la reattività** dei due algoritmi.

---

### **2. Richiamo sul metodo di Jacobson/Karels**

Il timeout calcolato con Jacobson e Karels tiene conto **sia della media sia della deviazione** del RTT misurato:

$$  
\begin{cases}  
EstimatedRTT = (1 - \alpha) \cdot EstimatedRTT + \alpha \cdot SampleRTT \\\\  
Error = |SampleRTT - EstimatedRTT| \\\\  
Deviation = Deviation + \beta \cdot (Error - Deviation) \\\\  
Timeout = EstimatedRTT + 4 \cdot Deviation  
\end{cases}  
$$

Dove:

- $\alpha = 0.125$ → peso della media;
    
- $\beta = 0.25$ → peso della deviazione;
    
- il coefficiente “4” rappresenta un **margine di sicurezza** (≈ 4 deviazioni standard).
    

> La **deviazione media** viene considerata una buona approssimazione della deviazione standard statistica.

---

### **3. Caso 1 – RTT costante che aumenta (da 1 a 5)**

Nel primo esperimento, il RTT rimane stabile a 1 per un certo tempo, poi **cresce improvvisamente** fino a un nuovo valore costante 5.

![](imgs/Pasted%20image%2020260225145217.png)

#### **Osservazioni**

- Il **metodo originale** (solo media) risponde troppo lentamente → tende a impostare il timeout a circa **2 × RTT**.
    
- Questo porta a un **valore eccessivo**, con conseguente inefficienza e ritardo nel riconoscimento delle perdite.
    
- Il **metodo Jacobson/Karels** invece si adatta più rapidamente e tende al nuovo valore effettivo del RTT (5) **senza overshoot**.
    

> In sintesi: il metodo moderno **segue correttamente la crescita** del RTT, mantenendo il timeout vicino al valore ottimale.

---

### **4. Caso 2 – RTT costante che diminuisce (da 4 a 1)**

Nel secondo esperimento, il RTT inizia con un valore alto (4) e poi **diminuisce bruscamente** fino a 1.

![](imgs/Pasted%20image%2020260225145253.png)

#### **Osservazioni**

- Anche in questo caso, il **metodo originale** tende a un valore troppo grande (≈ 2 × RTT).
    
- Il **metodo Jacobson/Karels** reagisce correttamente, ma mostra un **piccolo sussulto verso l’alto** quando il RTT diminuisce rapidamente.
    
    - Questo accade perché la deviazione aumenta temporaneamente: il sistema “sospetta” un cambiamento anomalo e preferisce mantenere un margine di sicurezza più ampio.
        
- Dopo alcune iterazioni, il timeout si stabilizza esattamente sul nuovo valore di RTT.
    

> Quindi il metodo Jacobson/Karels è **stabile ma prudente**: anche di fronte a miglioramenti della rete, evita di ridurre troppo rapidamente il timeout per non generare falsi allarmi.

---

### **5. Caso 3 – RTT con sussulti periodici verso l’alto**

Nel terzo scenario, il RTT rimane costante a 1, ma **ogni N misurazioni (N = 4)** si verifica un **picco di ritardo (RTT = 4)**.

![](imgs/Pasted%20image%2020260225145318.png)

#### **Andamento osservato**

- Il **metodo originale** produce timeout molto **variabili**, seguendo in modo diretto ogni oscillazione del RTT.
    
- Il **metodo Jacobson/Karels**, invece, è più stabile e mantiene una curva più “liscia”, **distante dai picchi** del RTT reale.
    

> In pratica, Jacobson/Karels “filtra” i sussulti casuali verso l’alto, mantenendo un comportamento regolare e prevedibile.

---

### **6. Caso 4 – RTT con sussulti periodici verso il basso**

Infine, consideriamo un RTT che vale normalmente **4**, ma che **ogni N misurazioni (N = 4)** scende improvvisamente a **1**.

![](imgs/Pasted%20image%2020260225145402.png)

#### **Andamento osservato**

- Entrambi i metodi (originale e Jacobson/Karels) mantengono il timeout **ben al di sopra** dei valori di RTT.
    
- Nessuno dei due rischia di generare timeout prematuri, poiché i picchi verso il basso non compromettono l’affidabilità.
    
- Tuttavia, il metodo Jacobson/Karels mostra ancora **una curva più regolare e meno nervosa**.
    

> In sostanza, i sussulti verso il basso non sono un problema per TCP, ma la **stabilità del timeout** resta fondamentale per evitare reazioni errate del protocollo.

---

### **7. Analisi comparativa complessiva**

|Scenario|Metodo Originale|Jacobson/Karels|Esito|
|---|---|---|---|
|RTT ↑ (1→5)|Timeout eccessivo (≈ 2×RTT)|Adattamento rapido|✅ Jacobson/Karels migliore|
|RTT ↓ (4→1)|Timeout ancora alto|Breve sussulto → stabilità|✅ Jacobson/Karels migliore|
|RTT oscillante ↑|Timeout molto variabile|Timeout stabile|✅ Jacobson/Karels migliore|
|RTT oscillante ↓|Entrambi sicuri|Jacobson più regolare|✅ Jacobson/Karels più efficiente|

> Jacobson/Karels fornisce **timeout adattivi, regolari e robusti**, riducendo sia i falsi positivi (timeout prematuri) sia i ritardi ingiustificati.

---

### **8. Conclusione**

L’approccio **Jacobson/Karels** rappresenta un **miglioramento sostanziale** rispetto al metodo classico di stima del timeout:

- integra **media e deviazione** del RTT;
    
- filtra oscillazioni casuali senza perdere reattività;
    
- evita sia **sottostima** (timeout prematuro) sia **sovrastima** (ritrasmissione tardiva).
    

> In sintesi: il metodo Jacobson/Karels consente a TCP di reagire **in modo intelligente e dinamico** ai cambiamenti reali delle condizioni di rete, assicurando la **massima affidabilità con il minimo overhead**.