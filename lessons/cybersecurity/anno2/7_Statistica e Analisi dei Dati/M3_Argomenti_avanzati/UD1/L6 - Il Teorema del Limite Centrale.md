# **M3 UD1 Lezione 6 - Il Teorema del Limite Centrale**

### **1. Introduzione**

Nelle lezioni precedenti abbiamo visto come, sommando due o più **variabili aleatorie indipendenti**, si ottiene una nuova distribuzione che può essere ricavata:

- tramite **convoluzione**,
    
- oppure tramite **funzioni generatrici** (PGF o MGF).

Ora ci chiediamo cosa accade quando il **numero di variabili sommate** diventa **molto grande**.

---

### **2. La domanda fondamentale**

Sia $S_n = X_1 + X_2 + \dots + X_n$ la somma di $n$ variabili aleatorie indipendenti e identicamente distribuite (i.i.d.).

👉 **Cosa succede alla distribuzione di $S_n$ quando $n$ cresce molto?**

---

### **3. Risultato generale**

Per valori grandi di $n$, la risposta è sorprendentemente semplice:

> **Sotto ipotesi molto generali, la distribuzione della somma tende a una distribuzione Gaussiana.**

Questa affermazione costituisce il **Teorema del Limite Centrale (TLC)**, uno dei pilastri della statistica e della probabilità.

---

### **4. Intuizione grafica**

#### **Esempio illustrativo**

- $n=1$: distribuzione di partenza (es. uniforme, esponenziale, discreta, ecc.)
    
- $n=2$: la somma mostra una forma più “morbida”
    
- $n=3,4,5$: la forma tende sempre più a una **campana simmetrica**

In generale, aumentando $n$, la distribuzione della somma si **“arrotonda”** e si avvicina sempre più a una **Gaussiana**.

---

### **5. Enunciato del Teorema del Limite Centrale**

> **Teorema del Limite Centrale (TLC):**  
> Sommando un numero grande di **variabili aleatorie indipendenti**, non necessariamente distribuite nello stesso modo, ma con **media finita** e **varianza finita**,  
> la distribuzione normalizzata della somma tende a una **distribuzione Normale**.

Formalmente:

$$  
Z = \frac{S_n - n\mu}{\sigma\sqrt{n}} \xrightarrow{n \to \infty} N(0,1)  
$$

dove:

- $\mu = \mathbb{E}[X_i]$ è la media delle singole variabili,
    
- $\sigma^2 = \text{Var}(X_i)$ è la loro varianza.

---

### **6. Interpretazione intuitiva**

Sommando variabili indipendenti:

- gli effetti casuali **si compensano**;
    
- i valori estremi diventano **sempre più rari**;
    
- la forma della distribuzione tende a concentrarsi attorno alla **media** con **simmetria crescente**.

Il risultato è una curva a forma di **campana di Gauss**.

---

### **7. Esempio: distribuzioni uniformi**

La somma di variabili **uniformi indipendenti** mostra progressivamente una forma **triangolare**, poi **parabolica**, e infine tende a una **Gaussiana**.

Questo è il modo più immediato per “vedere” il Teorema del Limite Centrale in azione.

---

### **8. Esempio: distribuzioni Binomiali**

Nel caso **Binomiale**, conosciamo già il risultato:  
per $n$ grande e $p$ non troppo vicino a 0 o 1, la Binomiale $B(n,p)$ si può approssimare con una **Normale**:

$$  
B(n,p) \approx N(np, , np(1-p))  
$$

---

### **9. Esempio: somma di Esponenziali (Erlang)**

Quando si sommano variabili **esponenziali identiche**, si ottiene una **Erlang** di parametro $r$:

$$  
f(x) = \frac{\lambda^r x^{r-1} e^{-\lambda x}}{(r-1)!}  
$$

Al crescere di $r$, la densità Erlang **tende a una Gaussiana**, con:

$$  
\mu = \frac{r}{\lambda}, \quad \sigma^2 = \frac{r}{\lambda^2}  
$$

Questa convergenza visiva mostra perfettamente il contenuto del TLC.

---

### **10. Legge empirica delle 3σ**

Dal Teorema del Limite Centrale segue che, per grandi $n$,  
la somma (o media) delle variabili può essere approssimata da una **Normale**, e quindi vale la **Legge delle 3 sigma**:

- circa **68%** dei valori entro ±1σ dalla media
    
- circa **95%** entro ±2σ
    
- circa **99,7%** entro ±3σ

Questa legge è valida con **buona approssimazione** anche per somme di variabili non gaussiane, purché $n$ sia abbastanza grande.

---

### **11. Verso il Teorema del Limite Centrale Generalizzato**

#### **Domanda:**

Cosa succede se le variabili **non hanno varianza finita**?

- Esistono distribuzioni con **varianza infinita** (o non definita).
    
- In questi casi, la convergenza non è verso una Gaussiana, ma verso un’altra famiglia di distribuzioni.

---

### **12. Densità con varianza non finita**

Un esempio classico è la **distribuzione di Cauchy (o Lorentziana)**:

$$  
f(x) = \frac{1}{\pi} \cdot \frac{1}{1+x^2}  
$$

Essa **decade come $1/x^2$**, quindi:

- non ha momento primo (media non definita),
    
- non ha momento secondo (varianza infinita).

👉 Per tali distribuzioni, il **TLC classico non vale**.

---

### **13. Teorema del Limite Centrale Generalizzato**

> **TLC generalizzato:**  
> Per somme di variabili indipendenti con **varianza infinita**, la distribuzione limite non è una Gaussiana, ma una **stabile non Gaussiana**, come la **Cauchy**.

Queste distribuzioni appartengono alla famiglia delle **“fat-tail distributions”** (distribuzioni a coda pesante), caratterizzate da decadimento troppo lento per rendere finiti i momenti.

---

### **14. Distribuzioni a coda grossa**

Le distribuzioni a **coda grossa (fat tails)** descrivono fenomeni in cui:

- esistono **eventi estremi**, rari ma **molto intensi**;
    
- la probabilità di tali eventi **non è trascurabile**.

Esempi reali:

- fluttuazioni finanziarie,
    
- intensità di terremoti,
    
- tempi di risposta di reti informatiche.

---

#### **Indicatori alternativi**

Per queste distribuzioni, poiché **media e varianza non esistono**, si usano:

- la **mediana** (posizione),
    
- l’**intervallo interquartile (IQR)** (dispersione),
    
- e, quando possibile, la **moda**.

---

### **15. Sintesi finale**

|Caso|Tipo di distribuzione|Comportamento della somma|
|:--|:--|:--|
|Varianza finita|Qualsiasi (Uniforme, Binomiale, Esponenziale, ecc.)|Tende a una **Gaussiana**|
|Varianza infinita|“Fat tail” (es. Cauchy, Lorentz)|Tende a una **Cauchy / stabile non gaussiana**|

---

### **16. Conclusioni**

- Il **Teorema del Limite Centrale** spiega perché la **Gaussiana** appare ovunque in natura e in statistica.
    
- Sommando molti contributi indipendenti, anche di natura diversa, il risultato tende sempre a una **distribuzione normale**.
    
- Se le variabili hanno **varianza infinita**, la convergenza è invece verso una **distribuzione stabile non gaussiana** (es. Cauchy).
    
- Per grandi $n$, la **legge delle 3σ** diventa un’approssimazione universale per la probabilità cumulativa.