# **M2 UD1 Lezione 2 - Cumulativa e Anticumulativa**

### **1. Richiamo: distribuzioni e densità**

Una **distribuzione di probabilità** è l’insieme dei valori $P(k)$ associati a una variabile aleatoria discreta $k = 1, 2, 3, \dots$

$$  
\text{Distribuzione: } {P(k)}, \quad \sum_k P(k) = 1  
$$

Quando invece la variabile aleatoria assume valori **continui**, la probabilità è rappresentata da una **curva continua** detta **densità di probabilità**:

$$  
f(t) \ge 0, \quad \int_{-\infty}^{+\infty} f(t),dt = 1  
$$

---

### **2. Domande tipiche sulle distribuzioni**

Esempio: data la distribuzione $P(k) = \left( \tfrac{1}{2} \right)^k$ per $k \ge 1$, possiamo chiedere:

![](imgs/Pasted%20image%2020260618145632.png)

- $P(k = 2)$
    
- $P(k \le 3)$
    
- $P(k > 3)$

Calcoli:

$$  
\begin{cases}  
P(k=1) = \frac{1}{2} = 0.5 \\\\  
P(k=2) = \frac{1}{4} = 0.25 \\\\  
P(k=3) = \frac{1}{8} = 0.125  
\end{cases}  
$$

$$  
P(k \le 3) = \sum_{k=1}^{3} P(k) = \frac{7}{8} = 0.875  
$$

$$  
P(k > 3) = 1 - P(k \le 3) = 0.125  
$$

---

### **3. Domande tipiche sulle densità**

Esempio: data la densità

$$  
f(t) = e^{-t}, \quad t > 0  
$$

![](imgs/Pasted%20image%2020260618145605.png)

possiamo chiedere:

- $P(t \le 3)$ → probabilità che un componente si guasti **entro** 3 ore.
    
- $P(t > 3)$ → probabilità che si guasti **dopo** 3 ore.

Queste probabilità si ottengono calcolando **l’integrale** della densità su intervalli specifici.

#### **3.1. Generalizzazione: domande per soglia arbitraria $x$**

Naturalmente le domande $P(t \le 3)$ e $P(t > 3)$ poste riguardo alla soglia $x = 3$ si possono ripetere per **qualunque soglia** $x$. Le domande avranno la forma generale:

- $P(t \le x)$
- $P(t > x)$

rispettivamente, mentre le risposte, nel caso continuo della densità $f(t) = e^{-t}$, avranno la forma:

$$
P(t \le x) = \int_0^x e^{-t}\,dt = \Big[-e^{-t}\Big]_0^x
= \left(-e^{-x}\right) - \left(-e^{0}\right) = 1 - e^{-x}
$$

$$
P(t > x) = 1 - P(t \le x) = 1 - \left(1 - e^{-x}\right) = e^{-x}
$$

> 📌 Le due quantità sono complementari: $P(t \le x) + P(t > x) = 1$ per ogni $x$.

---

### **4. La funzione cumulativa**

Nel caso continuo, la **funzione cumulativa** $F(x)$ rappresenta la probabilità **accumulata** fino al valore $x$:

$$  
F(x) = P(t \le x) = \int_{-\infty}^{x} f(t) \ dt  
$$

Essa indica **quanta parte della probabilità totale è compresa** tra l’origine e il punto $x$.

Nel discreto, la definizione è analoga ma con una **somma**:

$$  
F(r) = \sum_{k \le r} P(k)  
$$

**Interpretazione:**  
$F(x)$ indica la probabilità che l’evento si verifichi **prima o al più in corrispondenza di $x$**.

---

### **5. Proprietà della funzione cumulativa**

- È sempre **non decrescente**.
    
- Se la densità è definita su $[a, b]$, allora:

$$  
F(a) = 0, \quad F(b) = 1  
$$

- Se $f(t)$ tende asintoticamente a 0, allora anche $F(x)$ tende asintoticamente a 1.

![](imgs/Pasted%20image%2020260618153806.png)

- La normalizzazione non si applica a $F(x)$, perché **non è una densità**, ma una funzione derivata da essa.

In altri casi non bisogna nemmeno parlare di asintoti:

![](imgs/Pasted%20image%2020260618154009.png)

Un banale esempio sul discreto:

![](imgs/Pasted%20image%2020260618154408.png)

---

### **6. Esempi di funzioni cumulative**

#### **a) Bernoulliana (discreta)**

Distribuzione:

$$  
P(0) = 1 - p, \quad P(1) = p  
$$

![](imgs/Pasted%20image%2020260618154607.png)

Cumulativa:

$$  
F(r) =  
\begin{cases}  
0, & r < 0 \\\\  
1 - p, & 0 \le r < 1 \\\\  
1, & r \ge 1  
\end{cases}  
$$

Essendoci solo due canali, dopo due gradini è già "completa" - pari a 1.

#### **b) Densità uniforme (continua)**

Densità:

$$  
f(t) =  
\begin{cases}  
\frac{1}{b - a}, & a \le t \le b \\\\
0, & \text{altrove}  
\end{cases}  
$$

![](imgs/Pasted%20image%2020260618155044.png)

Cumulativa:

$$  
F(x) =  
\begin{cases}  
0, & x < a \\\\  
\frac{x - a}{b - a}, & a \le x \le b \\\\  
1, & x > b  
\end{cases}  
$$

Mediante il seguente grafico mettiamo in evidenza i dettagli della "costruzione".

In pratica prendiamo la densità uniforme ed integriamo. Ad ogni valore di $x$ della soglia otteniamo un particolare valore dell'integrale, trasformato in un valore della cumulativa. Essendo la uniforme una funzione costante, l'integrale sarà proporzionale al valore $x-a$ cioè la distanza tra $x$ ed $a$ ed è proprio per questo che avremo una crescita lineare!

![](imgs/Pasted%20image%2020260618155533.png)

---

### **7. La funzione anticumulativa (o di sopravvivenza)**

La **funzione anticumulativa**, detta anche **complementare**, esprime la probabilità **complementare** alla cumulativa:

$$  
S(x) = 1 - F(x)  
$$

Rappresenta la probabilità che l’evento **non si sia ancora verificato** al tempo $x$:

$$  
S(x) = P(t > x)  
$$

#### **Sinonimi comuni**

- Cumulativa complementare
    
- Upper distribution function
    
- Funzione residua
    
- Funzione di sopravvivenza
    
- Funzione di affidabilità, indicata anche con $R(x)$

---

### **8. Definizioni esplicite**

Sul **continuo**:

$$  
S(x) = \int_{x}^{+\infty} f(t),dt  
$$

Sul **discreto**:

$$  
S(r) = \sum_{k > r} P(k)  
$$

Le due funzioni sono legate dalla relazione fondamentale:

$$  
F(x) + S(x) = 1  
$$

Un esempio di anticumulativa può essere il seguente:

![](imgs/Pasted%20image%2020260618182705.png)

Mentre mettendole a confronto:

![](imgs/Pasted%20image%2020260618182720.png)

Sul discreto avremo stavolta sempre una funzione a gradini ma discendenti:

![](imgs/Pasted%20image%2020260618182756.png)

Infatti sul discreto la differenza è palese:

![](imgs/Pasted%20image%2020260618182828.png)

---

### **9. Interpretazione: fallibilità e sopravvivenza**

Nel contesto dell’**affidabilità dei sistemi**:

- La **funzione di fallibilità** $F(x)$ rappresenta la probabilità che il sistema **fallisca entro** l’istante $x$:

$$  
F(x) = P(t \le x)  
$$

- La **funzione di sopravvivenza** $S(x)$ rappresenta la probabilità che il sistema **continui a funzionare dopo** l’istante $x$:

$$  
S(x) = P(t > x)  
$$

Talvolta si indica con $R(x)$ per “Reliability”.

---

### **10. Relazione con derivata e primitiva**

La funzione cumulativa è la **primitiva** della densità:

$$  
F(t) = \int f(t) \ dt  
$$

Per derivazione quindi abbiamo:

$$  
f(t) = \frac{d}{dt} F(t)  
$$

Inoltre, poiché $S(t) = 1 - F(t)$ e $1$ costante scompare derivando, vale anche:

$$  
f(t) = -\frac{d}{dt} S(t)  
$$

---

### **11. Riepilogo finale**

|**Concetto**|**Simbolo / Formula**|**Significato**|
|---|---|---|
|Densità|$f(t)$|Distribuzione continua delle probabilità|
|Cumulativa|$F(x) = \int_{-\infty}^{x} f(t),dt$|Probabilità che $t \le x$|
|Anticumulativa|$S(x) = 1 - F(x) = \int_{x}^{+\infty} f(t),dt$|Probabilità che $t > x$|
|Relazione fondamentale|$F(x) + S(x) = 1$|Complementarità tra fallibilità e sopravvivenza|
|Derivazione|$f(x) = F'(x) = -S'(x)$|Legame analitico tra le tre funzioni|
