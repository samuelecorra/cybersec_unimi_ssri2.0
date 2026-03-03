# **M2 UD1 Lezione 4 - Indicatori d’ampiezza**

### **1. Dalla posizione all’ampiezza**

Due distribuzioni possono avere la **stessa media** ma una **diversa ampiezza**, cioè una diversa **dispersione** dei valori intorno al centro.  
L’ampiezza descrive **quanto i dati sono sparsi** rispetto alla posizione centrale.

Esempio:

$$  
\begin{cases}  
P(-1) = P(1) = 0.5 \\\\  
P(-2) = P(2) = 0.5  
\end{cases}  
$$

Entrambe le distribuzioni hanno **media = 0**, ma la seconda è **più ampia**.

---

### **2. Indicatori d’ampiezza principali**

I due principali indicatori dell’ampiezza di una distribuzione o densità sono:

1. **Intervallo interquartile (Δ)** → basato sulla **mediana**
    
2. **Varianza e Deviazione standard (σ² e σ)** → basate sulla **media**

**Interpretazioni intuitive:**

- L’intervallo interquartile misura **la metà centrale della massa di probabilità**.
    
- La deviazione standard misura **la distanza media delle masse dal baricentro**.

---

### **3. Dai quartili alla misura di ampiezza**

Nella lezione precedente abbiamo definito la **mediana** come il punto $x$ tale che:

$$  
F(x_{\text{mediana}}) = \frac{1}{2}  
$$

Allo stesso modo, possiamo dividere la distribuzione in **quattro parti uguali**, ottenendo i **quartili**.

---

### **4. Quartili**

I **quartili** sono tre punti che dividono la distribuzione in quattro parti uguali:

1. **Primo quartile $Q_1$:**  
    $$F(Q_1) = \frac{1}{4}$$
    
2. **Secondo quartile $Q_2$:**  
    $$F(Q_2) = \frac{2}{4} = \frac{1}{2}$$ (coincide con la **mediana**)
    
3. **Terzo quartile $Q_3$:**  
    $$F(Q_3) = \frac{3}{4}$$

---

### **5. Intervallo interquartile**

L’intervallo interquartile rappresenta **la metà centrale della massa** della distribuzione.

Poiché:

$$  
F(Q_3) - F(Q_1) = \frac{3}{4} - \frac{1}{4} = \frac{1}{2}  
$$

l’intervallo $(Q_1, Q_3)$ contiene esattamente **il 50% della probabilità totale**.

**Definizione:**

$$  
\Delta = Q_3 - Q_1  
$$

---

### **6. Quartili per la densità uniforme**

Per una densità uniforme su $[a, b]$:

$$  
f(t) =  
\begin{cases}  
\frac{1}{b-a}, & a \le t \le b \  
0, & \text{altrove}  
\end{cases}  
$$

I quartili risultano:

$$  
\begin{cases}  
Q_1 = a + \frac{b - a}{4} \  
Q_2 = a + \frac{b - a}{2} \  
Q_3 = a + \frac{3(b - a)}{4}  
\end{cases}  
$$

Da cui:

$$  
\Delta = Q_3 - Q_1 = \frac{b - a}{2}  
$$

---

### **7. Quartili, percentili e quantili**

Si possono definire suddivisioni più fini della distribuzione:

|**Suddivisione**|**Numero di parti**|**Esempio**|
|---|---|---|
|**Quintili**|5 parti|$F(x) = 0.2, 0.4, 0.6, 0.8$|
|**Decili**|10 parti|$F(x) = 0.1, 0.2, ..., 0.9$|
|**Percentili**|100 parti|$F(x) = 0.01, ..., 0.99$|
|**Quantile di ordine p**|$F(x) = p$|valore $x$ corrispondente al livello di probabilità $p$|

Esempio:  
Il **99° percentile** è il punto $x$ tale che $F(x) = 0.99$.

---

### **8. Altre misure di ampiezza**

È possibile costruire altri intervalli centrali utilizzando diversi quantili.  
Esempio: intervallo che contiene il **90% della probabilità**:

$$  
[x_{0.05}, x_{0.95}]  
$$

Tuttavia, la misura più comune resta l’**intervallo interquartile** $(Q_1, Q_3)$.

---

### **9. Dai momenti alla varianza**

La **media** rappresenta il **momento di ordine 1**:

$$  
\langle x \rangle = \sum_k k P(k) \quad \text{o} \quad \int x f(x),dx  
$$

Se invece si considerano potenze superiori ($x^2$, $x^3$, …) si ottengono i **momenti di ordine superiore**, che descrivono la forma della distribuzione.

Il **momento di ordine $r$** è definito da:

- **Caso discreto:**  
    $$  
    \langle k^r \rangle = \sum_k k^r P(k)  
    $$
    
- **Caso continuo:**  
    $$  
    \langle x^r \rangle = \int x^r f(x),dx  
    $$

---

### **10. Momento secondo e ampiezza**

Il **momento di ordine 2** misura quanto la distribuzione è ampia rispetto all’origine.

Esempio:

$$  
\begin{cases}  
P(-1) = P(1) = 0.5 \Rightarrow \langle k^2 \rangle = 1 \\\\  
P(-2) = P(2) = 0.5 \Rightarrow \langle k^2 \rangle = 4  
\end{cases}  
$$

Il momento secondo cresce con l’**ampiezza** della distribuzione.  
Se la media è zero, $\sqrt{\langle k^2 \rangle}$ rappresenta la **distanza media dall’origine**.

---

### **11. Momenti centrali**

Per eliminare la dipendenza dalla posizione della media, si definiscono i **momenti centrali**:

$$  
\langle (x - \langle x \rangle)^r \rangle  
$$

- Per $r=1$ → 0
    
- Per $r=2$ → **Varianza**

---

### **12. Varianza e deviazione standard**

La **varianza** è il **momento centrale di ordine 2**:

- **Discreto:**  
    $$  
    \sigma^2 = \sum_k (k - \langle k \rangle)^2 P(k)  
    $$
    
- **Continuo:**  
    $$  
    \sigma^2 = \int (x - \langle x \rangle)^2 f(x),dx  
    $$

La **deviazione standard** è la **radice quadrata della varianza**:

$$  
\sigma = \sqrt{\sigma^2}  
$$

Formula utile per il calcolo:

$$  
\sigma^2 = \langle x^2 \rangle - \langle x \rangle^2  
$$

---

### **13. Varianza di distribuzioni notevoli**

#### **a) Bernoulliana**

$$  
P(0) = 1 - p, \quad P(1) = p  
$$

$$  
\langle k \rangle = p, \quad \langle k^2 \rangle = p  
$$

$$  
\sigma^2 = \langle k^2 \rangle - \langle k \rangle^2 = p - p^2 = p(1 - p)  
$$

$$  
\sigma = \sqrt{p(1 - p)}  
$$

#### **b) Uniforme**

$$  
f(x) = \frac{1}{b - a}, \quad a \le x \le b  
$$

$$  
\sigma^2 = \frac{(b - a)^2}{12}, \quad \sigma = \frac{b - a}{\sqrt{12}}  
$$

---

### **14. Teorema di Tchebichev**

Il **teorema di Tchebichev** fornisce una stima generale della probabilità che una variabile si discosti dalla media.

**Enunciato:**

> Entro **2 deviazioni standard** dalla media è contenuto **almeno il 50%** della probabilità totale.

In generale, per una costante $k > 0$:

$$  
P(|x - \mu| < k\sigma) \ge 1 - \frac{1}{k^2}  
$$

Esempio:

- Per $k = 2$ → almeno il 50%
    
- Per $k = 3$ → almeno l’88.9%

Per una **distribuzione gaussiana**, invece, $2\sigma$ copre circa **il 68%** della massa.

---

### **15. Momenti centrali di ordine superiore**

Si possono definire momenti centrali anche per $r > 2$:

- $r = 3$ → misura la **simmetria** della distribuzione (_asimmetria_).
    
- $r = 4$ → misura la **concentrazione** delle masse intorno alla media (_curtosi_).

---

### **16. Riepilogo finale**

| **Indicatore**               | **Simbolo / Formula**                                  | **Significato**                                 |
| ---------------------------- | ------------------------------------------------------ | ----------------------------------------------- |
| **Primo quartile**           | $F(Q_1) = 0.25$                                        | Inizio del 50% centrale                         |
| **Terzo quartile**           | $F(Q_3) = 0.75$                                        | Fine del 50% centrale                           |
| **Intervallo interquartile** | $\Delta = Q_3 - Q_1$                                   | Ampiezza del 50% centrale                       |
| **Varianza**                 | $\sigma^2 = \langle x^2 \rangle - \langle x \rangle^2$ | Dispersione media quadratica                    |
| **Deviazione standard**      | $\sigma = \sqrt{\sigma^2}$                             | Dispersione media lineare                       |
| **Tchebichev**               | $P(\|x - \mu\|< k\sigma) \ge 1 - \frac{1}{k^2}$        | Stima generale della concentrazione della massa |
