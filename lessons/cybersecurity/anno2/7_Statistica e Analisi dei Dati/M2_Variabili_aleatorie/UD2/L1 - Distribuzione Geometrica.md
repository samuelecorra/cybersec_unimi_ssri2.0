# **M1 UD2 Lezione 1 - Distribuzione Geometrica**

### **1. Genesi della distribuzione**

Consideriamo l’esperimento seguente:  
si lancia una **moneta equilibrata o sbilanciata**, che può dare **Testa (successo)** con probabilità $p$ e **Croce (fallimento)** con probabilità $q = 1 - p$.  
I lanci sono **indipendenti**, e ci si **ferma al primo successo**.

La **variabile aleatoria discreta $i$** rappresenta **il numero del tentativo in cui si verifica il primo successo**.

Esempi:

- $i = 1$ → testa al primo lancio
    
- $i = 2$ → prima croce, poi testa
    
- $i = 3$ → due croci e poi testa

Lo **spazio dei casi** è quindi $S = \mathbb{N}$.

---

### **2. Calcolo delle probabilità**

#### **Primi casi**

- $P(i = 1) = p$
    
- $P(i = 2) = qp$
    
- $P(i = 3) = q^2p$

#### **Termine generale**

Poiché ottenere il primo successo al tentativo $i$ implica avere $(i-1)$ fallimenti seguiti da un successo:

$$  
P(i) = q^{i-1} p  
$$

---

### **3. Definizione analitica**

La **distribuzione geometrica** è definita da:

$$  
G(i \mid p) = q^{i-1} p  
$$

- $p$: probabilità di successo ad ogni tentativo
    
- $q = 1 - p$: probabilità di insuccesso
    
- $i$: numero di tentativi fino al primo successo

Al variare di $p$, cambia la forma della distribuzione.  
Per questo si parla di **famiglia di distribuzioni geometriche**.

Esempio:

- per $p = 0.75$, la distribuzione decresce rapidamente
    
- per $p = 0.25$, decresce più lentamente

---

### **4. Normalizzazione**

Verifichiamo che la distribuzione sia **normalizzata**:

$$  
\sum_{i=1}^{\infty} P(i) = \sum_{i=1}^{\infty} q^{i-1}p = p \sum_{j=0}^{\infty} q^j = p \cdot \frac{1}{1 - q} = 1  
$$

Poiché $p + q = 1$, la condizione è soddisfatta.  
La **distribuzione geometrica è già normalizzata**.

---

### **5. Media**

La **media (valore atteso)** rappresenta il numero medio di tentativi necessari per ottenere il primo successo.

$$  
\mu = \langle i \rangle = \sum_{i=1}^{\infty} i , q^{i-1}p = \frac{1}{p}  
$$

**Interpretazione:**  
in media, occorrono $1/p$ tentativi per ottenere un successo.

#### **Esempi pratici**

- Lotto: $p = \frac{5}{90} = \frac{1}{18} \Rightarrow \mu = 18$ estrazioni medie per un numero.
    
- Superenalotto: $p = \frac{1}{6.23 \times 10^8} \Rightarrow \mu = 6.23 \times 10^8$ estrazioni ≈ **6.23 milioni d’anni** (con 100 estrazioni l’anno).
    
- Roulette russa: $p = \frac{1}{6} \Rightarrow \mu = 6$ tentativi medi di sopravvivenza.

---

### **6. Varianza e deviazione standard**

La **varianza** misura la dispersione della distribuzione rispetto alla media.

$$  
\sigma^2 = \frac{q}{p^2}  
$$

La **deviazione standard** è:

$$  
\sigma = \frac{\sqrt{q}}{p}  
$$

**Esempio (roulette russa):**  

$p = \frac{1}{6}$,

$q = \frac{5}{6}$

$$  
\sigma^2 = \frac{5/6}{(1/6)^2} = 30, \quad \sigma = \sqrt{30} \approx 5.48  
$$

---

### **7. Funzioni cumulativa e anticumulativa**

Le funzioni cumulative della geometrica si definiscono come:

$$  
\begin{cases}  
F(i) = P(t \le i) = 1 - q^i \\\\  
S(i) = P(t > i) = q^i  
\end{cases}  
$$

Dove:

- $F(i)$ è la **funzione di fallibilità** → probabilità che l’evento sia già accaduto.
    
- $S(i)$ è la **funzione di sopravvivenza** → probabilità che l’evento **non sia ancora accaduto** al tempo $i$.

**Relazione fondamentale:**  
$$  
F(i) + S(i) = 1  
$$

---

### **8. Esempi di applicazione**

#### **Esempio 1 – Roulette russa, 3° duello**

$$  
S(3) = q^3 = \left( \frac{5}{6} \right)^3 = 0.5787  
$$

Il giocatore ha circa **il 57.9% di probabilità** di sopravvivere almeno 3 duelli.

#### **Esempio 2 – Roulette russa, 6° duello**

$$  
S(6) = \left( \frac{5}{6} \right)^6 = 0.3349  
$$

Quindi ha **1/3 di probabilità** di sopravvivere 6 duelli e **2/3 di probabilità** di morire entro tale punto.

---

### **9. Mediana**

La **mediana** è definita come il valore $i$ per cui $F(i) = \tfrac{1}{2}$ oppure $S(i) = \tfrac{1}{2}$.

$$  
S(i_{\text{mediana}}) = q^{i_{\text{mediana}}} = \frac{1}{2}  
$$

Ricavando:

$$  
i_{\text{mediana}} = -\frac{\log 2}{\log q}  
$$

**Esempio:**  

$$q = \frac{5}{6}$ → $\log_2 q = -0.26303$$

$$  
i_{\text{mediana}} = \frac{1}{0.26303} \approx 3.8 \Rightarrow i_{\text{mediana}} \approx 4  
$$

---

### **10. Quartili e intervallo interquartile**

Analogamente:

$$  
\begin{cases}  
F(i_{Q_1}) = \frac{1}{4} \\\\  
F(i_{Q_3}) = \frac{3}{4}  
\end{cases}  
$$

Da cui:

$$  
\Delta = i_{Q_3} - i_{Q_1}  
$$

Tuttavia, poiché $i$ è **discreto**, i quartili non risultano numeri interi e devono essere **approssimati**.

---

### **11. Moda**

Il valore di $i$ per cui $P(i)$ è massimo è sempre $i = 1$.  
Quindi:

$$  
i_{\text{moda}} = 1  
$$

---

### **12. Interpretazione e rilevanza**

La **distribuzione geometrica** è il modello ideale quando si studiano **tempi d’attesa discreti** in esperimenti **indipendenti**, come:

- estrazioni con reinserimento (urne, lanci di moneta);
    
- fenomeni ripetuti nel tempo con probabilità costante di successo.

È ampiamente utilizzata in:

- **teoria dell’affidabilità** (tempi di guasto discreti);
    
- **teoria dei giochi e simulazioni**;
    
- **analisi di rischio**.

---

### **13. Riepilogo finale**

|**Parametro / Funzione**|**Formula**|**Significato**|
|---|---|---|
|**Distribuzione**|$G(i \mid p) = q^{i-1}p$|Probabilità di primo successo al tentativo $i$|
|**Media**|$\mu = \frac{1}{p}$|Numero medio di tentativi per il primo successo|
|**Varianza**|$\sigma^2 = \frac{q}{p^2}$|Dispersione della distribuzione|
|**Deviazione standard**|$\sigma = \frac{\sqrt{q}}{p}$|Distanza media dalla media|
|**Funzione di sopravvivenza**|$S(i) = q^i$|Probabilità di non aver ancora avuto successo|
|**Funzione di fallibilità**|$F(i) = 1 - q^i$|Probabilità che il successo si sia già verificato|
|**Mediana**|$i_{\text{mediana}} = -\frac{\log 2}{\log q}$|Tempo in cui $F(i) = 0.5$|
|**Moda**|$i_{\text{moda}} = 1$|Primo tentativo: massimo di probabilità|
