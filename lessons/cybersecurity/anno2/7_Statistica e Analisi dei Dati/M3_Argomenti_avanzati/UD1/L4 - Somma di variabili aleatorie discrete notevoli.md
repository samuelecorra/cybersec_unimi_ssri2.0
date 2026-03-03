# **M3 UD1 Lezione 4 - Somma di variabili aleatorie discrete notevoli**

## **1. Introduzione**

In questa lezione si analizzano alcuni **casi notevoli di somma di variabili aleatorie discrete**, cioè distribuzioni per cui la somma produce una distribuzione appartenente alla **stessa famiglia**.  
Vedremo in particolare:

- Somma di **Bernoulliane e Binomiali**
    
- Somma di **Poissoniane**
    
- **Regole generali** su **media** e **varianza** della somma

---

### **2. Richiamo: funzione generatrice e somma**

Per due distribuzioni discrete $X(i)$ e $Y(j)$, la **funzione generatrice delle probabilità (FGP)** si costruisce come:

$$  
G_X(u) = \sum_i X(i)u^i, \quad G_Y(u) = \sum_j Y(j)u^j  
$$

La distribuzione della **somma** $k = i + j$ ha funzione generatrice:

$$  
G_Z(u) = G_X(u) \cdot G_Y(u)  
$$

e i coefficienti di $G_Z(u)$ forniscono le **probabilità $Z(k)$**.

---

### **3. Distribuzione di Bernoulli**

La distribuzione di **Bernoulli**, denotata $X(i) = \text{Ber}(i ,|, p)$, è definita da:

$$  
X(0) = q = 1 - p, \quad X(1) = p  
$$

La sua funzione generatrice è:

$$  
G_X(u) = q + pu  
$$

Se consideriamo due Bernoulliane identiche e indipendenti, avremo anche:

$$  
G_Y(u) = q + pu  
$$

---

### **4. Somma di due Bernoulliane identiche**

La distribuzione della variabile somma $k = i + j$ ha funzione generatrice:

$$  
	G_Z(u) = (q + pu)^2 = q^2 + 2pq \cdot u + p^2u^2  
$$

Da cui le probabilità:

$$  
Z(0) = q^2, \quad Z(1) = 2pq, \quad Z(2) = p^2  
$$

Questi coefficienti coincidono con quelli della **distribuzione Binomiale $B(k|n=2,p)$**.  
→ La **somma di due Bernoulliane** è una **Binomiale con $n=2$ e probabilità $p$**.

---

### **5. Somma di tre Bernoulliane identiche**

Analogamente:

$$  
G_Z(u) = (q + pu)^3 = q^3 + 3q^2p \cdot u + 3qp^2u^2 + p^3u^3  
$$

Quindi:

$$  
Z(0)=q^3, \quad Z(1)=3q^2p, \quad Z(2)=3qp^2, \quad Z(3)=p^3  
$$

Si riconosce la **Binomiale $B(k|n=3,p)$**.  
→ La **somma di tre Bernoulliane** è una **Binomiale con $n=3$**.

---

### **6. Somma di n Bernoulliane identiche**

In generale, per $n$ Bernoulliane indipendenti e identiche:

$$  
G_Z(u) = (q + pu)^n  
$$

I coefficienti del polinomio sviluppato rappresentano la **distribuzione Binomiale $B(k|n,p)$**:

$$  
Z(k) = \binom{n}{k} p^k q^{n-k}  
$$

**Conclusione:**  
La **somma di $n$ Bernoulliane** indipendenti è una **Binomiale con parametri $(n,p)$**.

---

### **7. Funzione generatrice della Binomiale**

Dalla relazione precedente, la **funzione generatrice** della distribuzione Binomiale è:

$$  
G_{\text{Bin}}(u) = (q + pu)^n  
$$

Questa forma è molto più **compatta** rispetto alla scrittura del termine generale della Binomiale, ma ne racchiude completamente la struttura.

---

### **8. Somma di due Binomiali**

Consideriamo due variabili indipendenti:

- $X \sim \text{Bin}(n,p)$
    
- $Y \sim \text{Bin}(m,p)$

Le loro funzioni generatrici sono:

$$  
G_X(u) = (q + pu)^n, \quad G_Y(u) = (q + pu)^m  
$$

La somma ha funzione generatrice:

$$  
G_Z(u) = G_X(u) \cdot G_Y(u) = (q + pu)^{n+m}  
$$

Riconosciamo la **forma della Binomiale** con parametri $(n+m, p)$.  
→ La **somma di due Binomiali con stesso $p$** è una **Binomiale con $n+m$**.

---

### **9. Funzione generatrice della Poissoniana**

La distribuzione **Poissoniana** è definita da:

$$  
P(k|\mu) = \frac{e^{-\mu}\mu^k}{k!}  
$$

La sua funzione generatrice delle probabilità è:

$$  
G_P(u) = \sum_{k=0}^{\infty} P(k|\mu) u^k  
= e^{-\mu} \sum_{k=0}^{\infty} \frac{(\mu u)^k}{k!}  
= e^{-\mu} e^{\mu u}  
= e^{\mu(u-1)}  
$$

---

### **10. Somma di due Poissoniane**

Consideriamo due variabili indipendenti:

- $X \sim \text{Pois}(\mu)$
    
- $Y \sim \text{Pois}(\nu)$

Le loro funzioni generatrici sono:

$$  
G_X(u) = e^{\mu(u-1)}, \quad G_Y(u) = e^{\nu(u-1)}  
$$

Il prodotto è:

$$  
G_Z(u) = G_X(u)G_Y(u) = e^{(\mu+\nu)(u-1)}  
$$

→ La **somma di due Poissoniane** è una **Poissoniana con media $\mu + \nu$**.

**Osservazione:**  
Ogni volta che il prodotto delle funzioni generatrici **preserva la stessa forma funzionale**, la **somma delle distribuzioni** appartiene **alla stessa famiglia**.

---

### **11. Quadro riassuntivo**

|Variabili addendo|Distribuzione risultante|Parametri risultanti|
|:--|:--|:-:|
|$n$ Bernoulliane Ber($p$)|Binomiale Bin($n,p$)|$(n,p)$|
|Binomiale Bin($n,p$) + Bin($m,p$)|Binomiale Bin($n+m,p$)|$(n+m,p)$|
|Poisson($\mu$) + Poisson($\nu$)|Poisson($\mu+\nu$)|$(\mu+\nu)$|

---

### **12. Media della somma**

#### **Binomiali**

- Media di $X \sim \text{Bin}(n,p)$ → $\mathbb{E}[X] = np$
    
- Media di $Y \sim \text{Bin}(m,p)$ → $\mathbb{E}[Y] = mp$
    
- Media della somma $Z = X + Y$:

$$  
\mathbb{E}[Z] = (n+m)p = \mathbb{E}[X] + \mathbb{E}[Y]  
$$

#### **Poissoniane**

- Media di $X \sim \text{Pois}(\mu)$ → $\mathbb{E}[X] = \mu$
    
- Media di $Y \sim \text{Pois}(\nu)$ → $\mathbb{E}[Y] = \nu$
    
- Media della somma:

$$  
\mathbb{E}[Z] = \mu + \nu = \mathbb{E}[X] + \mathbb{E}[Y]  
$$

📌 **Regola generale:**

$$  
\mathbb{E}[X + Y] = \mathbb{E}[X] + \mathbb{E}[Y]  
$$

Questa vale **anche per variabili dipendenti**, sia discrete che continue.

---

### **13. Varianza della somma**

#### **Binomiali**

- Varianza di $X \sim \text{Bin}(n,p)$ → $\text{Var}(X) = npq$
    
- Varianza di $Y \sim \text{Bin}(m,p)$ → $\text{Var}(Y) = mpq$
    
- Varianza della somma:

$$  
\text{Var}(Z) = (n+m)pq = \text{Var}(X) + \text{Var}(Y)  
$$

#### **Poissoniane**

- Varianza di $X \sim \text{Pois}(\mu)$ → $\text{Var}(X) = \mu$
    
- Varianza di $Y \sim \text{Pois}(\nu)$ → $\text{Var}(Y) = \nu$
    
- Varianza della somma:

$$  
\text{Var}(Z) = \mu + \nu = \text{Var}(X) + \text{Var}(Y)  
$$

📌 **Regola generale (solo per variabili indipendenti):**

$$  
\text{Var}(X + Y) = \text{Var}(X) + \text{Var}(Y)  
$$

---

### **14. Sintesi finale**

- La **somma di Bernoulliane** è una **Binomiale**.
    
- La **somma di Binomiali** è ancora **Binomiale**.
    
- La **somma di Poissoniane** è ancora **Poissoniana**.
    
- La **media della somma** è sempre la **somma delle medie**.
    
- La **varianza della somma** è la **somma delle varianze** se le variabili sono **indipendenti**.