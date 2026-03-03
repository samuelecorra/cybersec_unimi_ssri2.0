# **M2 UD2 Lezione 3 - Caratteristiche dell’esponenziale**

### **1. Richiamo generale**

La **densità esponenziale negativa** (o semplicemente _esponenziale_) è definita da:

$$  
f(t) = \lambda e^{-\lambda t} \quad t \ge 0  
$$

e possiede le funzioni associate:

$$  
\begin{cases}  
F(t) = 1 - e^{-\lambda t} \\\\  
S(t) = e^{-\lambda t}  
\end{cases}  
$$

Essa costituisce una **famiglia di densità parametrica**, dipendente dal solo parametro $\lambda > 0$, detto **tasso di evento** (o **tasso di guasto** in ambito di affidabilità).

---

### **2. Ruolo del parametro λ**

Al variare di $\lambda$ cambia la forma della distribuzione:

- **$\lambda$ grande → decrescita rapida**, eventi frequenti e attese brevi.
    
- **$\lambda$ piccolo → decrescita lenta**, eventi rari e attese lunghe.

Il parametro $\lambda$ gioca per l’esponenziale **lo stesso ruolo** che il parametro $p$ aveva nella **distribuzione geometrica**.

---

### **3. Moda**

Osserviamo che la densità $f(t) = \lambda e^{-\lambda t}$ assume il suo **massimo** per $t = 0$.

$$  
t_{\text{moda}} = 0  
$$

Infatti, $f'(t) = -\lambda^2 e^{-\lambda t}$ si annulla solo in $t = 0$, e $f(0) = \lambda$.

**Valore alla moda:**

$$  
f(t_{\text{moda}}) = f(0) = \lambda  
$$

Quindi la curva parte da $\lambda$ e decresce esponenzialmente.

---

### **4. Media**

La **media (valore atteso)** dell’esponenziale negativa è pari a:

$$  
\mu = \int_0^{+\infty} t f(t)\ dt = \frac{1}{\lambda}  
$$

Spesso indicata con la lettera greca $\tau$, che in ambito di affidabilità rappresenta la **vita media del sistema** (tempo medio tra accensione e primo guasto).

$$  
\tau = \frac{1}{\lambda}  
$$

---

### **5. Varianza e deviazione standard**

Calcolando l’integrale per parti di:

$$  
\sigma^2 = \int_0^{+\infty} (t - \mu)^2 f(t) \ dt  
$$

si ottiene:

$$  
\sigma^2 = \frac{1}{\lambda^2}  
$$

da cui segue che la **deviazione standard** è:

$$  
\sigma = \frac{1}{\lambda} = \tau  
$$

Quindi, per la densità esponenziale, **media e deviazione standard coincidono**.

---

### **6. Esempio**

Il tempo tra due richieste successive a un server segue la densità:

$$  
f(t) = 2e^{-2t}  
$$

Determinare:

- **Tempo medio tra le richieste:**

$$  
\lambda = 2 \quad \Rightarrow \quad \tau = \frac{1}{\lambda} = \frac{1}{2} \ \text{secondi}  
$$

- **Deviazione standard:**

$$  
\sigma = \tau = 0.5 \ \text{secondi}  
$$

---

### **7. Funzioni di fallibilità e sopravvivenza**

Le funzioni principali della densità esponenziale sono:

$$  
\begin{cases}  
f(t) = \lambda e^{-\lambda t} \\\\  
F(t) = 1 - e^{-\lambda t} \\\\  
S(t) = e^{-\lambda t}  
\end{cases}  
$$

- **$F(t)$** rappresenta la probabilità che l’evento **si sia già verificato** entro il tempo $t$.
    
- **$S(t)$** rappresenta la probabilità che **non si sia ancora verificato** al tempo $t$.

Queste due funzioni soddisfano la relazione:

$$  
F(t) + S(t) = 1  
$$

---

### **8. Esempio: il gatto di Schrödinger**

Supponiamo che il tasso di decadimento radioattivo sia di **una frammentazione all’ora**:  
$$  
\lambda = \frac{1}{60} \ \text{(minuti}^{-1}\text{)}  
$$

Vogliamo conoscere la probabilità che il **gatto sia ancora vivo dopo mezz’ora**.

$$  
S(30) = e^{-\lambda t} = e^{-\frac{30}{60}} = e^{-1/2} \approx 0.6065  
$$

Quindi c’è una probabilità del **60.65%** che il gatto sia ancora vivo dopo 30 minuti.  
La probabilità complementare, cioè che sia già morto, è:

$$  
F(30) = 1 - S(30) = 0.3935  
$$

La **vita media del gatto** (tempo medio di sopravvivenza) è:

$$  
\tau = \frac{1}{\lambda} = 60 \ \text{minuti}  
$$

---

### **9. Mediana**

La **mediana** è il punto $t$ per cui $F(t) = \tfrac{1}{2}$, ovvero:

$$  
e^{-\lambda t_{\text{mediana}}} = \frac{1}{2}  
$$

Da cui:

$$  
t_{\text{mediana}} = \frac{\ln 2}{\lambda} = \tau \ln 2  
$$

Poiché $\ln 2 \approx 0.6931$, la mediana è **inferiore alla media**:

$$  
t_{\text{mediana}} \approx 0.6931 , \tau  
$$

---

### **10. Quartili e intervallo interquartile**

Si definiscono come:

$$  
\begin{cases}  
F(t_{Q_1}) = \frac{1}{4} \\\\  
F(t_{Q_3}) = \frac{3}{4}  
\end{cases}  
$$

cioè:

$$  
t_{Q_1} = \frac{\ln 4/3}{\lambda}, \quad t_{Q_3} = \frac{\ln 4}{\lambda}  
$$

L’**intervallo interquartile** risulta quindi:

$$  
\Delta = t_{Q_3} - t_{Q_1}  
$$

Anche se calcolabile, questi valori **non sono spesso usati**, perché l’esponenziale è facilmente gestibile analiticamente e si preferisce calcolare direttamente le probabilità per gli intervalli di interesse.

Esempi:

- 80° percentile → $t_{80} = \frac{5}{3}\tau = 1.667\tau$
    
- 90° percentile → $t_{90} = \frac{7}{3}\tau = 2.333\tau$
    
- 95° percentile → $t_{95} = 3\tau$

---

### **11. Rilevanza**

La **densità esponenziale negativa** è il modello di riferimento per i **tempi d’attesa** in cui:

- gli eventi avvengono in **tempi continui**,
    
- i fenomeni sono **indipendenti** o **approssimabili come tali**,
    
- il **tasso di evento $\lambda$ è costante** nel tempo.

È quindi largamente usata in:

- **teoria dell’affidabilità** (tempo al guasto),
    
- **processi di arrivo** (code, traffico di rete),
    
- **fisica dei decadimenti**.

---

### **12. Riepilogo finale**

|**Proprietà**|**Formula / Valore**|**Significato**|
|---|---|---|
|**Densità**|$f(t) = \lambda e^{-\lambda t}$|Distribuzione dei tempi d’attesa|
|**Funzione di fallibilità**|$F(t) = 1 - e^{-\lambda t}$|Probabilità che l’evento sia già accaduto|
|**Funzione di sopravvivenza**|$S(t) = e^{-\lambda t}$|Probabilità che non sia ancora accaduto|
|**Moda**|$t_{\text{moda}} = 0$|Massimo della densità|
|**Media**|$\mu = \frac{1}{\lambda} = \tau$|Tempo medio di attesa / vita media|
|**Varianza**|$\sigma^2 = \frac{1}{\lambda^2}$|Dispersione temporale|
|**Deviazione standard**|$\sigma = \frac{1}{\lambda}$|Ampiezza media, coincide con $\mu$|
|**Mediana**|$t_{\text{mediana}} = \tau \ln 2$|Tempo per cui $F(t) = 0.5$|
|**Percentili**|$t_p = -\frac{\ln(1-p)}{\lambda}$|Tempo al quale si è verificato il $p$% dei casi|
