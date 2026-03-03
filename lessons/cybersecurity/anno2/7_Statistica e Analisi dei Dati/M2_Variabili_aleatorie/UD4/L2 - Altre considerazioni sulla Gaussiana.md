# **M2 UD4 Lezione 2 - Altre considerazioni sulla Gaussiana**

### **1. Richiamo: legge dei tre sigma**

La **legge dei tre sigma** afferma che, per una variabile distribuita secondo la **Gaussiana** $N(\mu, \sigma)$, le aree comprese entro 1, 2 e 3 deviazioni standard dalla media contengono rispettivamente:

| **Intervallo intorno alla media** | **Probabilità contenuta** |
| --------------------------------- | ------------------------- |
| $[\mu - \sigma, \mu + \sigma]$    | 68.27 %                   |
| $[\mu - 2\sigma, \mu + 2\sigma]$  | 95.45 %                   |
| $[\mu - 3\sigma, \mu + 3\sigma]$  | 99.73 %                   |

Fuori da ±3σ resta solo **lo 0.27%** della probabilità totale.

---

### **2. Esempio: 10.000 lanci di moneta**

Consideriamo di lanciare **10.000 monete bilanciate**.  
Sia $k$ il numero di teste ottenute.

Per la **Binomiale**:

$$  
\mu = np = 10000 \times 0.5 = 5000  
$$

$$  
\sigma^2 = npq = 10000 \times 0.5 \times 0.5 = 2500  
$$

$$  
\sigma = \sqrt{2500} = 50  
$$

Applicando la legge dei tre sigma:

|**Intervallo**|**Valori di $k$**|**Probabilità**|
|---|---|---|
|$\mu \pm 1\sigma$|$[4950, 5050]$|68.27 %|
|$\mu \pm 2\sigma$|$[4900, 5100]$|95.45 %|
|$\mu \pm 3\sigma$|$[4850, 5150]$|99.73 %|

Ci sono quindi **solo 3 possibilità su 1000** di ottenere meno di 4850 o più di 5150 teste.

---

### **3. Legame con la legge dei grandi numeri**

Questo esempio permette di comprendere il **fondamento teorico** della **legge dei grandi numeri** per il conteggio.

Essa afferma che:

> Se un evento ha probabilità $p$, allora per un numero di prove $n$ molto grande, il numero di occorrenze $k$ sarà _circa uguale_ a $np$.

Con la Gaussiana possiamo quantificare il significato di “circa”:

$$  
P\left( |k - np| > 3\sqrt{npq} \right) \approx 0.003  
$$

In altre parole, **“quasi sicuramente”** $k$ cade entro l’intervallo:

$$  
(np - 3\sqrt{npq}) \le k \le (np + 3\sqrt{npq})  
$$

---

### **4. Frazione di conteggio e probabilità**

La legge dei grandi numeri può essere espressa anche come:

$$  
\frac{k}{n} \approx p  
$$

per $n$ grande.

L’errore relativo della stima diminuisce con l’aumentare di $n$.

Ricordando che $\sigma = \sqrt{npq}$, possiamo scrivere:

$$  
\frac{\sigma}{n} = \sqrt{\frac{pq}{n}} \approx \frac{p}{\sqrt{n}}  
$$

per $p \approx q$ (caso peggiore).

---

### **5. Stima di $p$ con intervallo di confidenza**

L’intervallo entro cui cade quasi sicuramente la frazione osservata $\frac{k}{n}$ è:

$$  
p(1 - \frac{3}{\sqrt{n}}) \le \frac{k}{n} \le p(1 + \frac{3}{\sqrt{n}})  
$$

da cui si ottiene un **errore percentuale**:

$$  
\text{Errore relativo} = \frac{3}{\sqrt{n}}  
$$

|**Numero di prove $n$**|**Errore su 1**|
|---|---|
|100|±0.3|
|10.000|±0.03|
|1.000.000|±0.003|

L’errore percentuale **decresce come $1/\sqrt{n}$**, quindi aumenta la precisione della stima.

---

### **6. Legge dei percentili**

Dalla legge dei tre sigma derivano altre relazioni più precise, basate sui **percentili** della distribuzione.

|**Percentile**|**Intervallo centrato su $\mu$**|**Probabilità contenuta**|
|---|---|---|
|95°|$\mu \pm 1.96\sigma$|95 %|
|99°|$\mu \pm 2.576\sigma$|99 %|

- Nella **legge dei tre sigma** si fissa l’intervallo e si calcola la probabilità.
    
- Nella **legge dei percentili**, invece, si fissa la probabilità e si calcola l’intervallo corrispondente.

---

### **7. Uso delle tabelle della Normale standard**

Le tabelle della Normale standard forniscono i valori di:

$$  
F(z) = P(Z \le z)  
$$

per la variabile standardizzata:

$$  
z = \frac{x - \mu}{\sigma}  
$$

La tabella è costruita solo per $z \ge 0$, poiché la Gaussiana è simmetrica.

La probabilità di avere valori **maggiori** di $x$ (cioè l’anticumulativa) è:

$$  
S(z) = 1 - F(z)  
$$

---

### **8. Esempio numerico**

Sia $X$ una variabile distribuita come $N(5, 9)$, cioè $\mu = 5$, $\sigma = 3$.

**Domanda 1:** Qual è la probabilità che $X > 12$?

$$  
z = \frac{12 - 5}{3} = 2.33  
$$

Dalla tabella, $S(2.33) = 0.0099$ → **Probabilità 0.99%**

**Domanda 2:** Qual è la probabilità che $X$ sia tra $-2$ e $12$?

$$  
z_1 = \frac{-2 - 5}{3} = -2.33, \quad z_2 = 2.33  
$$

Per simmetria:  
$$  
P(X < -2) = P(X > 12) = 0.0099  
$$

Quindi:  
$$  
P(-2 \le X \le 12) = 1 - 2(0.0099) = 0.9802  
$$

**Risultato:** 98.02 % dei valori di $X$ sono compresi tra $-2$ e $12$.

---

### **9. Calcolo con strumenti software**

Oltre alle tabelle, è possibile calcolare direttamente i valori delle aree tramite software o calcolatori scientifici.

Il procedimento resta lo stesso:

1. **Standardizzare la variabile:**  
    $$  
    z = \frac{x - \mu}{\sigma}  
    $$
    
2. **Usare la funzione di distribuzione cumulativa** della Normale standard, indicata come $\Phi(z)$.

Esempi di comandi:

- In Excel / LibreOffice:  
    `=NORM.DIST(x; μ; σ; TRUE)`  
    `=NORM.S.DIST(z; TRUE)` per la standardizzata.
    
- In Python (libreria `scipy.stats`):
    
    ```python
    from scipy.stats import norm
    norm.cdf(z)
    ```

---

### **10. Sintesi finale**

|**Concetto**|**Formula / Significato**|
|---|---|
|**Legge dei tre sigma**|68.27%, 95.45%, 99.73%|
|**Legge dei percentili**|95% → ±1.96σ, 99% → ±2.576σ|
|**Errore relativo sulla stima di p**|$\dfrac{3}{\sqrt{n}}$|
|**Standardizzazione**|$z = \dfrac{x - \mu}{\sigma}$|
|**Probabilità complementare**|$S(z) = 1 - F(z)$|
|**Esempio pratico**|$P(-2 \le X \le 12) = 0.9802$|
|**Calcolo automatico**|$\Phi(z)$ via tabelle o software|

---

### **11. Conclusione**

Questa lezione mostra come la **legge dei tre sigma**, la **legge dei grandi numeri** e l’uso delle **tabelle della Gaussiana** permettano di quantificare la probabilità degli eventi e l’errore di stima in modo preciso, rendendo la distribuzione normale lo strumento base dell’intera analisi statistica.