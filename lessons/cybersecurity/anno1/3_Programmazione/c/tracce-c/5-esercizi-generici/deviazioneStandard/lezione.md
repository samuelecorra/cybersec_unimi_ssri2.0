## **Lezione: Media, Varianza e Deviazione Standard**

---

### **1. Perché servono queste misure**

Quando abbiamo un insieme di numeri — per esempio i voti di una classe, le temperature di una settimana o i tempi di esecuzione di un programma — vogliamo capire **come si comportano nel complesso**.

Ci servono due informazioni fondamentali:

1. **Dove si concentra la maggior parte dei dati** → misura di **posizione centrale** → **media**.
2. **Quanto si allontanano i dati da quella posizione** → misura di **dispersione** → **varianza** e **deviazione standard**.

---

### **2. L’insieme dei dati**

Supponiamo di avere un insieme di valori (che chiameremo “popolazione”):

$$
x_1, x_2, x_3, \dots, x_N
$$

dove **N** è il numero totale di valori.

Esempio:
$$
x_1 = 2, \quad x_2 = 3, \quad x_3 = 7, \quad x_4 = 8, \quad x_5 = 10
$$

---

### **3. La media aritmetica**

La **media aritmetica**, detta anche **valore medio** o **media semplice**, è il **punto di equilibrio** dei dati.

La formula generale è:

$$
\mu = \frac{1}{N} \sum_{i=1}^{N} x_i
$$

Si legge: *mu uguale alla somma di tutti i valori divisa per il numero di valori.*

#### **Esempio pratico**

$$
\mu = \frac{2 + 3 + 7 + 8 + 10}{5} = \frac{30}{5} = 6
$$

Interpretazione: la “posizione centrale” dei dati è **6**.

---

### **4. Gli scarti dalla media**

Ogni valore si discosta in modo diverso dalla media.

Definiamo lo **scarto** del valore $x_i$ come:

$$
\text{scarto}_i = x_i - \mu
$$

È la **distanza** (positiva o negativa) tra il singolo dato e la media.

#### **Esempio**

| Valore $x_i$ | Scarto $x_i - \mu$ |
| ------------ | ------------------ |
| 2            | -4                 |
| 3            | -3                 |
| 7            | +1                 |
| 8            | +2                 |
| 10           | +4                 |

**Nota:**
La somma di tutti gli scarti è **sempre zero**, perché la media è proprio il punto di equilibrio dei dati.

$$
(-4) + (-3) + 1 + 2 + 4 = 0
$$

---

### **5. Gli scarti quadratici**

Per studiare quanto sono **grandi** gli scarti (non se sono positivi o negativi), si elevano al **quadrato**:

$$
(x_i - \mu)^2
$$

Questo elimina i segni meno e **dà più peso agli scarti grandi**.

| Valore $x_i$ | Scarto $x_i - \mu$ | Scarto² $(x_i - \mu)^2$ |
| ------------ | ------------------ | ----------------------- |
| 2            | -4                 | 16                      |
| 3            | -3                 | 9                       |
| 7            | +1                 | 1                       |
| 8            | +2                 | 4                       |
| 10           | +4                 | 16                      |

---

### **6. La varianza**

Ora possiamo calcolare la **varianza**, che è la **media** di tutti questi scarti quadratici:

$$
\sigma^2 = \frac{1}{N} \sum_{i=1}^{N} (x_i - \mu)^2
$$

Si legge: *sigma al quadrato uguale alla media degli scarti al quadrato.*

#### **Esempio**

$$
\sigma^2 = \frac{16 + 9 + 1 + 4 + 16}{5} = \frac{46}{5} = 9.2
$$

Interpretazione: in media, **gli scarti quadratici valgono 9.2**.
È una misura di **dispersione**, ma non è ancora “intuitiva” perché è espressa **in unità al quadrato** (se i dati sono in “euro”, la varianza è in “euro²”).

---

### **7. La deviazione standard**

Per tornare alle **stesse unità** dei dati, si fa la **radice quadrata della varianza**.

$$
\sigma = \sqrt{\sigma^2} = \sqrt{\frac{1}{N} \sum_{i=1}^{N} (x_i - \mu)^2}
$$

#### **Esempio**

$$
\sigma = \sqrt{9.2} \approx 3.03
$$

**Interpretazione:**
in media, ogni valore si discosta di **circa 3 unità** dalla media.

---

### **8. Come interpretarla nella pratica**

* Se $\sigma$ è **piccola**, i dati sono **molto simili** fra loro (poco dispersi).
* Se $\sigma$ è **grande**, i dati sono **molto diversi** fra loro (molta variabilità).

Esempi:

* Voti = {7, 7, 6, 7, 6} → σ piccola → classe omogenea.
* Voti = {3, 6, 8, 9, 10} → σ grande → classe disomogenea.

---

### **9. Popolazione e campione**

Se analizzi **tutti** i dati (popolazione intera), usi **N** nel denominatore.
Se invece analizzi **solo un campione**, usi **N − 1** per correggere il bias di sottostima:

$$
s = \sqrt{\frac{1}{N-1} \sum_{i=1}^{N} (x_i - \bar{x})^2}
$$

Il concetto resta identico, cambia solo il denominatore.

---

### **10. Riepilogo visivo**

| Concetto      | Formula                                   | Significato                         |
| ------------- | ----------------------------------------- | ----------------------------------- |
| Media         | $\mu = \frac{1}{N}\sum x_i$               | Centro dei dati                     |
| Scarto        | $x_i - \mu$                               | Distanza del valore dalla media     |
| Varianza      | $\sigma^2 = \frac{1}{N}\sum(x_i - \mu)^2$ | Media degli scarti quadratici       |
| Dev. standard | $\sigma = \sqrt{\sigma^2}$                | Dispersione media (unità originali) |

---

### **11. Legge empirica (distribuzione normale)**

Quando i dati sono distribuiti “a campana” (curva normale):

* Circa **68%** dei valori è compreso tra $\mu - \sigma$ e $\mu + \sigma$
* Circa **95%** tra $\mu - 2\sigma$ e $\mu + 2\sigma$
* Circa **99.7%** tra $\mu - 3\sigma$ e $\mu + 3\sigma$

---

### **12. Riassunto intuitivo finale**

* La **media** dice *dove* stanno i dati.
* Gli **scarti** dicono *di quanto* ogni dato si allontana.
* La **varianza** misura *quanto sono ampie* queste distanze (ma in unità al quadrato).
* La **deviazione standard** è la **media effettiva degli scarti**, espressa nelle **stesse unità** dei dati.

---
