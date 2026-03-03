# **M3 UD3 Lezione 2 - Stima della media**

### **1. Introduzione**

Nella lezione precedente abbiamo visto come, tramite il **Teorema di Bayes**, sia possibile aggiornare la distribuzione delle **ipotesi** a partire dai dati osservati, passando da una **prior** a una **posterior**.

Avevamo trattato il caso della **moneta sbilanciata**, in cui il parametro incognito $p$ della Bernoulliana rappresentava la **media vera della popolazione**.  
Aggiornando la distribuzione di probabilità sulle ipotesi $h$ relative a $p$, stavamo in realtà eseguendo una **stima della media della popolazione**.

In questa lezione generalizziamo quel procedimento, affrontando il problema della **stima della media di una popolazione qualunque** a partire da un **campione**.

---

### **2. Formulazione del problema**

Vogliamo stimare la **media vera** $\mu$ di una popolazione, nota solo attraverso un **campione di $n$ eventi indipendenti**.  
Assumiamo di conoscere la **varianza vera** $\sigma^2$ della popolazione e che $n$ sia **moderatamente grande** ($n > 20$).

Dal campione possiamo calcolare la **media empirica** $m$.  
Il nostro obiettivo è determinare **quanto è plausibile che la media vera $\mu$ si trovi in una certa posizione $h$**, data la media empirica osservata.

Formalmente, cerchiamo la **densità a posteriori delle ipotesi $h$** sulla posizione della media vera:

$$  
f(h ,|, m, \sigma^2, n)  
$$

---

### **3. Elementi del problema**

Per applicare il Teorema di Bayes, ci servono tre elementi:

1. **Prior** $f(h)$ → conoscenza a priori sulla posizione della media.
    
    - Si assume **uniforme**, cioè tutte le ipotesi $h$ sono ugualmente probabili.
    
2. **Likelihood** $L(m|h,\sigma^2,n)$ → probabilità di ottenere una media empirica $m$ dato un valore di media vera $h$.
    
    - È il modello generativo della distribuzione della media campionaria.
    
3. **Evidence** $P(m)$ → costante di normalizzazione (non dipende da $h$).

Applicando Bayes:

$$  
f(h|m,\sigma^2,n) = \frac{f(h) , L(m|h,\sigma^2,n)}{P(m)}  
$$

Poiché la prior e l’evidence sono costanti rispetto a $h$, si può scrivere:

$$  
f(h|m,\sigma^2,n) \propto L(m|h,\sigma^2,n)  
$$

👉 La **posterior è proporzionale alla likelihood**.

---

### **4. Likelihood della media campionaria**

Dall’unità precedente sappiamo che, per un campione di dimensione $n$ estratto da una popolazione con media $h$ e varianza finita $\sigma^2$, la **distribuzione della media empirica $m$** è approssimativamente **gaussiana**:

$$  
L(m|h,\sigma^2,n) = N(m|h, \sigma^2/n)  
$$

ossia:

$$  
L(m|h,\sigma^2,n) = \frac{1}{\sqrt{2\pi(\sigma^2/n)}}  
\exp!\left[-\frac{(m-h)^2}{2(\sigma^2/n)}\right]  
$$

---

### **5. Densità a posteriori**

Poiché $f(h|m,\sigma^2,n) \propto L(m|h,\sigma^2,n)$, anche la **posterior** ha forma gaussiana:

$$  
f(h|m,\sigma^2,n) \propto  
\exp!\left[-\frac{1}{2} \frac{(m-h)^2}{(\sigma^2/n)}\right]  
$$

La funzione gaussiana è **simmetrica** rispetto agli argomenti $m$ e $h$, quindi:

$$  
N(m|h, \sigma^2/n) = N(h|m, \sigma^2/n)  
$$

Da ciò segue direttamente:

$$  
\boxed{f(h|m,\sigma^2,n) = N(h|m, \sigma^2/n)}  
$$

---

### **6. Interpretazione**

La **posterior** delle ipotesi $h$ sulla media vera è quindi una **Gaussiana**:

- **media:** $m$ (la media empirica del campione);
    
- **varianza:** $\sigma^2 / n$.

Ha quindi densità massima in $h = m$ e deviazione standard $\sigma / \sqrt{n}$.

---

### **7. Stima della media e intervalli di confidenza**

La **stima della media vera** si ottiene prendendo il valore di $h$ più probabile, cioè il punto di massimo della posterior:

$$  
\mu_{\text{stima}} = m  
$$

L’incertezza associata alla stima si esprime tramite **intervalli di confidenza**, proporzionali alla deviazione standard della posterior:

$$  
\mu_{\text{stima}} = m \pm \frac{\sigma}{\sqrt{n}}  
$$

---

### **8. Intervalli di confidenza**

Nella distribuzione normale valgono le regole empiriche:

|Confidenza|Intervallo|
|:--|:--|
|68%|$[m - \sigma/\sqrt{n}, , m + \sigma/\sqrt{n}]$|
|95%|$[m - 2\sigma/\sqrt{n}, , m + 2\sigma/\sqrt{n}]$|
|99.7%|$[m - 3\sigma/\sqrt{n}, , m + 3\sigma/\sqrt{n}]$|

---

### **9. Esempio**

Si consideri una popolazione di **tempi di vita** di componenti elettronici, con:

- varianza nota $\sigma^2 = 10000$,
    
- campione di $n = 100$ elementi,
    
- media empirica $m = 550$.

Calcoliamo la stima della media vera:

$$  
\mu_{\text{stima}} = 550 \pm \frac{\sqrt{10000}}{\sqrt{100}} = 550 \pm 10  
$$

|Confidenza|Intervallo per $\mu$|
|:--|:--|
|68%|[540, 560]|
|95%|[530, 570]|
|99.7%|[520, 580]|

---

### **10. Caso di varianza non nota**

Se la **varianza vera $\sigma^2$** non è nota, la si sostituisce con la **varianza empirica del campione** $s^2$:

$$  
\mu_{\text{stima}} = m \pm \frac{s}{\sqrt{n}}  
$$

dove:

$$  
s^2 = \frac{\sum (x_i - m)^2}{n-1}  
$$

(Il denominatore $n-1$ serve a rendere la stima non distorta, ma la spiegazione dettagliata di questo fattore esula dagli obiettivi di questa lezione.)

---

### **11. Stima di una probabilità**

La stima di una **probabilità** di un evento (es. “la moneta dà Testa”) è un caso particolare della stima di una media:

- La popolazione segue una **Bernoulliana** con parametro $p$.
    
- Tale parametro $p$ è anche la **media** della Bernoulliana.

Quindi, nelle espressioni precedenti:

- $\mu$ → $p$ (media vera),
    
- $m$ → $k/n$ (frequenza empirica di successi).

---

### **12. Formula di stima della probabilità**

Poiché la varianza della Bernoulliana è $\sigma^2 = p(1-p)$, e viene spesso approssimata con $\sigma^2 \approx p$,  
l’incertezza associata alla stima è:

$$  
p_{\text{stima}} = \frac{k}{n} \pm \frac{\sqrt{k}}{n}  
$$

---

### **13. Esempio 1 – Stima di una probabilità**

In una popolazione molto ampia, vogliamo stimare la **probabilità $p$ di daltonismo**.  
Estraggo un campione di $n=100$ persone e trovo $k=16$ daltonici.

$$  
p_{\text{stima}} = \frac{16}{100} \pm \frac{\sqrt{16}}{100} = 0.16 \pm 0.04  
$$

|Confidenza|Intervallo per $p$|
|:--|:--|
|68%|[0.12, 0.20]|
|95%|[0.08, 0.24]|
|99.7%|[0.04, 0.28]|

---

### **14. Esempio 2 – Moneta di sbilanciamento ignoto**

Lanciamo una moneta di sbilanciamento ignoto $n = 100$ volte e osserviamo $k = 16$ Teste.

$$  
p_{\text{stima}} = \frac{16}{100} \pm \frac{\sqrt{16}}{100} = 0.16 \pm 0.04  
$$

→ L’intervallo di confidenza per la probabilità vera $p$ è identico a quello dell’esempio precedente.

---

### **15. Sintesi finale**

|Caso|Stima|Deviazione standard|
|:--|:--|:--|
|**Media di popolazione generica**|$\mu_{\text{stima}} = m \pm \sigma/\sqrt{n}$|$\sigma/\sqrt{n}$|
|**Media con varianza ignota**|$\mu_{\text{stima}} = m \pm s/\sqrt{n}$|$s/\sqrt{n}$|
|**Probabilità (Bernoulliana)**|$p_{\text{stima}} = (k/n) \pm \sqrt{k}/n$|$\sqrt{k}/n$|
