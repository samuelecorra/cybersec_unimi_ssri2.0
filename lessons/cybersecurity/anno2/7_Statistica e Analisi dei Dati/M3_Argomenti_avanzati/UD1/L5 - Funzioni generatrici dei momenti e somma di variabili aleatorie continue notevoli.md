# **M3 UD1 Lezione 5 - Funzioni generatrici dei momenti e somma di variabili aleatorie continue notevoli**

### **1. Introduzione**

Nelle lezioni precedenti abbiamo introdotto le **funzioni generatrici delle probabilità (PGF)**, strumenti utili per trattare le **variabili aleatorie discrete** e per ricavare la distribuzione della **somma di variabili indipendenti**.

In questa lezione estendiamo il concetto alle **variabili continue**, introducendo le **funzioni generatrici dei momenti (MGF)**.

---

### **2. Dalle PGF alle MGF**

#### **Funzioni generatrici della probabilità (PGF)**

Per una variabile discreta $k$ distribuita secondo $X(k)$, la **funzione generatrice della probabilità** è:

$$  
G_X(u) = \sum_k X(k)u^k  
$$

Essa permette, tramite il **prodotto delle PGF**, di ottenere la funzione generatrice della **somma di variabili**:

$$  
G_Z(u) = G_X(u) \cdot G_Y(u)  
$$

---

#### **Oltre le PGF**

È possibile definire un analogo strumento per le **variabili continue**: la **funzione generatrice dei momenti (MGF)**.  
Le MGF permettono di:

- ricavare i **momenti** (media, varianza, ecc.) tramite derivazione;
    
- studiare la **somma di variabili continue** (come Esponenziali o Gaussiane);
    
- evitare di ricorrere al calcolo diretto della **convoluzione**.

---

### **3. Definizione di funzione generatrice dei momenti (MGF)**

La MGF di una variabile aleatoria $X$ si definisce come:

$$  
M_X(w) = \mathbb{E}[e^{wX}]  
$$

Ovvero:

- per una **variabile discreta**:  
    $$  
    M_X(w) = \sum_k X(k)e^{wk}  
    $$
    
- per una **variabile continua**:  
    $$  
    M_X(w) = \int_{-\infty}^{+\infty} f_X(x) e^{wx} \ dx  
    $$

dove $f_X(x)$ è la **densità di probabilità**.

---

### **4. Perché “genera” i momenti**

Derivando la MGF e ponendo $w = 0$, si ottengono i **momenti** della distribuzione:

$$  
M_X^{(n)}(0) = \mathbb{E}[X^n]  
$$

In particolare:

- **Momento primo (media):**  
    $$  
    \mu = M'_X(0)  
    $$
    
- **Momento secondo:**  
    $$  
    \mathbb{E}[X^2] = M''_X(0)  
    $$
    
- **Varianza:**  
    $$  
    \sigma^2 = M''_X(0) - [M'_X(0)]^2  
    $$

---

### **5. Esempio discreto: distribuzione di Bernoulli**

Sia $X$ una variabile di Bernoulli con probabilità di successo $p$:

$$  
X(0) = q = 1-p, \quad X(1) = p  
$$

La sua MGF è:

$$  
M_X(w) = q + p e^w  
$$

#### **Momento primo**

$$  
M'_X(w) = p e^w \quad \Rightarrow \quad M'_X(0) = p  
$$

→ $\mu = p$

#### **Momento secondo e varianza**

$$  
M''_X(w) = p e^w \quad \Rightarrow \quad M''_X(0) = p  
$$

$$  
\sigma^2 = M''_X(0) - [M'_X(0)]^2 = p - p^2 = p(1-p)  
$$

---

### **6. Esempio continuo: distribuzione esponenziale**

Sia $X$ una variabile aleatoria esponenziale con parametro $\lambda > 0$:

$$  
f_X(x) = \lambda e^{-\lambda x}, \quad x \ge 0  
$$

#### **Calcolo della MGF**

$$  
M_X(w) = \int_0^{+\infty} \lambda e^{-\lambda x} e^{wx} \ dx  
= \lambda \int_0^{+\infty} e^{-(\lambda - w)x} \ dx  
$$

$$  
M_X(w) = \frac{\lambda}{\lambda - w}, \quad \text{valida per } w < \lambda  
$$

---

#### **Momento primo**

$$  
M'_X(w) = \frac{\lambda}{(\lambda - w)^2} \quad \Rightarrow \quad M'_X(0) = \frac{1}{\lambda}  
$$

#### **Momento secondo**

$$  
M''_X(w) = \frac{2\lambda}{(\lambda - w)^3} \quad \Rightarrow \quad M''_X(0) = \frac{2}{\lambda^2}  
$$

#### **Varianza**

$$  
\sigma^2 = M''_X(0) - [M'_X(0)]^2 = \frac{2}{\lambda^2} - \frac{1}{\lambda^2} = \frac{1}{\lambda^2}  
$$

---

### **7. Proprietà fondamentale: somma di variabili**

Analogamente alle PGF, anche per le MGF vale che **il prodotto delle MGF corrisponde alla somma delle variabili**:

$$  
M_Z(w) = M_X(w) \cdot M_Y(w)  
$$

Questo significa che la **convoluzione** tra distribuzioni o densità corrisponde al **prodotto delle MGF**.

---

### **8. Somma di variabili esponenziali**

Per $X, Y \sim \text{Exp}(\lambda)$ indipendenti:

$$  
M_X(w) = \frac{\lambda}{\lambda - w}  
$$

La MGF della somma $Z = X + Y$ è:

$$  
M_Z(w) = [M_X(w)]^2 = \left(\frac{\lambda}{\lambda - w}\right)^2  
$$

→ Questa è la **MGF della distribuzione Erlang con $r=2$**.

In generale, per $r$ variabili esponenziali identiche:

$$  
M_{\text{Erlang}(r,\lambda)}(w) = \left(\frac{\lambda}{\lambda - w}\right)^r  
$$

---

### **9. Somma di densità Erlang**

Se sommiamo due distribuzioni **Erlang** con stessi $\lambda$ ma parametri $r$ e $s$:

$$  
M_{Erlang(r,\lambda)}(w) = \left(\frac{\lambda}{\lambda - w}\right)^r  
$$  
$$  
M_{Erlang(s,\lambda)}(w) = \left(\frac{\lambda}{\lambda - w}\right)^s  
$$

Allora:

$$  
M_Z(w) = \left(\frac{\lambda}{\lambda - w}\right)^{r+s}  
$$

→ La somma di due Erlang è ancora una **Erlang**, con parametro $r+s$.

---

### **10. Funzione generatrice dei momenti della Normale**

Applicando la definizione di MGF alla distribuzione **Normale** $X \sim N(\mu, \sigma^2)$, si ottiene:

$$  
M_X(w) = \exp\left(\mu w + \frac{1}{2}\sigma^2 w^2\right)  
$$

Questa è una forma esponenziale **molto semplice e simmetrica**.

---

### **11. Somma di variabili Normali**

Siano $X \sim N(\mu_X, \sigma_X^2)$ e $Y \sim N(\mu_Y, \sigma_Y^2)$ indipendenti.  
Le loro MGF sono:

$$  
M_X(w) = e^{\mu_X w + \frac{1}{2}\sigma_X^2 w^2}, \quad  
M_Y(w) = e^{\mu_Y w + \frac{1}{2}\sigma_Y^2 w^2}  
$$

Il prodotto è:

$$  
M_Z(w) = M_X(w) M_Y(w) = e^{(\mu_X+\mu_Y)w + \frac{1}{2}(\sigma_X^2 + \sigma_Y^2)w^2}  
$$

→ **La somma di due Gaussiane è ancora Gaussiana**, con:

$$  
\mu_Z = \mu_X + \mu_Y, \quad \sigma_Z^2 = \sigma_X^2 + \sigma_Y^2  
$$

---

### **12. Sintesi finale**

|Caso|Distribuzioni addendo|Distribuzione risultante|Parametri risultanti|
|:--|:--|:--|:--|
|Bernoulli|Ber($p$) + Ber($p$)|Binomiale|$(n=2, p)$|
|Esponenziale|Exp($\lambda$) + Exp($\lambda$)|Erlang|$(r=2, \lambda)$|
|Erlang|Erlang($r,\lambda$) + Erlang($s,\lambda$)|Erlang|$(r+s,\lambda)$|
|Normale|$N(\mu_1,\sigma_1^2)$ + $N(\mu_2,\sigma_2^2)$|Normale|$(\mu_1+\mu_2,\sigma_1^2+\sigma_2^2)$|

---

### **13. Conclusioni**

- Le **funzioni generatrici dei momenti (MGF)** sono strumenti generali, applicabili a **variabili continue e discrete**.
    
- La **somma di variabili indipendenti** corrisponde al **prodotto delle MGF**.
    
- Le MGF permettono di **ricavare media, varianza e altri momenti** in modo diretto.
    
- Famiglie stabili per somma:
    
    - **Erlang → Erlang**
        
    - **Gaussiana → Gaussiana**