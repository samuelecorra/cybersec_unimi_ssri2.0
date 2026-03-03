# **M2 UD4 Lezione 1 - Densità Gaussiana o Normale**

### **1. Dalla Binomiale alla forma continua**

La **distribuzione Binomiale** descrive la probabilità di ottenere esattamente $k$ successi su $n$ tentativi con probabilità $p$ costante.  
Tuttavia, quando $n$ diventa molto grande, la forma analitica della Binomiale diventa **impraticabile** da calcolare.

- Se $p$ è molto piccolo, si può usare l’**approssimazione Poissoniana**.
    
- Se $p$ non è piccolo e $n$ è grande, la distribuzione tende a una **curva continua a campana**.

Quando $p = 0.5$, la distribuzione è simmetrica; quando $p$ si allontana da 0.5, la distribuzione si “sbilancia” verso uno dei due estremi.

---

### **2. Nascita della Gaussiana**

Per valori grandi di $n$ e qualunque $p$ non estremo ($p$ non troppo vicino a 0 o 1), la **Binomiale** si approssima a una **curva liscia e simmetrica**, detta **curva di Gauss** o **densità Normale**.

Questa curva:

- è **continua** (non più discreta),
    
- è **simmetrica** rispetto alla media $\mu$,
    
- è **a campana**, cioè tende a zero all’infinito ma senza mai annullarsi.

In questo limite, la variabile discreta $k$ diventa una variabile continua $x$.

---

### **3. Forma analitica generale**

La forma analitica della **densità Gaussiana** è:

$$  
f(x) = \frac{1}{\sqrt{2\pi},\sigma} \cdot e^{-\frac{(x - \mu)^2}{2\sigma^2}}  
$$

dove:

- $\mu$ è la **media**, cioè il valore centrale della distribuzione;
    
- $\sigma$ è la **deviazione standard**, che ne controlla l’ampiezza.

La **varianza** è $\sigma^2$.

---

### **4. Interpretazione dei parametri**

- **$\mu$ (media)**: controlla la **posizione** del centro della campana.
    
- **$\sigma$ (deviazione standard)**: controlla **quanto la curva è larga o stretta**.

Ogni densità Gaussiana è completamente determinata da **due soli parametri**: $\mu$ e $\sigma$.

$$  
N(x|\mu\sigma) \quad \text{indica la Gaussiana con media } \mu \text{ e deviazione standard } \sigma.  
$$

---

### **5. Normalizzazione**

La Gaussiana è normalizzata, cioè l’integrale totale della densità su tutta la retta reale vale 1:

$$  
\int_{-\infty}^{+\infty} f(x) \ dx = 1  
$$

Questo garantisce che la somma (integrale) di tutte le probabilità sia unitaria.

---

### **6. Proprietà di forma**

- A parità di $\sigma$, tutte le Gaussiane hanno **la stessa forma**: differiscono solo per la posizione della media $\mu$.
    
- Se invece varia $\sigma$, la campana si **stringe** (per $\sigma$ piccolo) o si **allarga** (per $\sigma$ grande).
    

Le Gaussiane con la stessa $\sigma$ sono traslazioni orizzontali l’una dell’altra.

---

### **7. Cumulativa e anticumulativa**

Per la Gaussiana non esiste una **primitiva in forma chiusa**, cioè non si può calcolare analiticamente l’integrale della densità.

Le funzioni:

- **Cumulativa:** $F(x) = P(X \le x)$
    
- **Anticumulativa:** $S(x) = P(X > x)$

non hanno espressione analitica esplicita, ma possono essere calcolate:

- con **metodi numerici**,
    
- mediante **tabelle precompilate**,
    
- o usando **software di calcolo**.

---

### **8. Problema pratico e soluzione**

Poiché la Gaussiana dipende da $\mu$ e $\sigma$, in teoria servirebbe una tabella diversa per ogni coppia di parametri.  
Fortunatamente, la Gaussiana gode di una proprietà speciale:

> Se le coordinate vengono espresse in **unità di deviazione standard** $\sigma$, la forma della curva è universale.

Definendo la variabile standardizzata:

$$  
z = \frac{x - \mu}{\sigma}  
$$

si ottiene la **Gaussiana standard**, centrata in 0 e con $\sigma = 1$:

$$  
f(z) = \frac{1}{\sqrt{2\pi}} \cdot e^{-\frac{z^2}{2}}  
$$

---

### **9. Un’unica tabella universale**

Grazie alla standardizzazione:

- tutte le Gaussiane diventano **una sola forma**;
    
- serve **una sola tabella** per calcolare le aree (cioè le probabilità cumulative) sotto la curva standardizzata.

La tabella della Gaussiana standard fornisce:  
$$  
F(z) = P(Z \le z)  
$$  
per vari valori di $z$.

---

### **10. Legge empirica dei tre sigma**

La **legge dei tre sigma** fornisce valori approssimati ma molto utili dell’area sotto la curva entro determinati intervalli dalla media.

| **Intervallo intorno alla media** | **Probabilità inclusa** |
|---|---|
| $[\mu - \sigma,\, \mu + \sigma]$ | 68.27% |
| $[\mu - 2\sigma,\, \mu + 2\sigma]$ | 95.45% |
| $[\mu - 3\sigma,\, \mu + 3\sigma]$ | 99.73% |


Fuori da questi intervalli resta una probabilità molto piccola:

|**Intervallo esterno**|**Probabilità residua**|
|---|---|
|Fuori da $1\sigma$|$0.317$ (31.7‰)|
|Fuori da $2\sigma$|$0.055$ (55‰)|
|Fuori da $3\sigma$|$0.003$ (3‰)|

---

### **11. Esempio – Lancio di 10.000 monete**

Supponiamo di lanciare **10.000 monete bilanciate** ($p=0.5$).  
La variabile $k$ rappresenta il numero di teste ottenute.

Distribuzione:  
$$  
B(k|n=10000, p=0.5)  
$$

Calcoliamo:  
$$  
\mu = np = 5000, \quad \sigma^2 = npq = 10000 \times 0.5 \times 0.5 = 2500, \quad \sigma = 50  
$$

Grazie alla legge dei tre sigma possiamo stimare:

|**Intervallo di $k$**|**Probabilità**|
|---|---|
|$[4950, 5050]$|≈ 68.27%|
|$[4900, 5100]$|≈ 95.45%|
|$[4850, 5150]$|≈ 99.73%|

Quindi:

- c’è circa **0.3% di probabilità** di avere meno di 4850 o più di 5150 teste;
    
- non è necessario calcolare ogni singolo valore della Binomiale: la **Gaussiana approssima perfettamente** la distribuzione complessiva.

---

### **12. Interpretazione finale**

La **densità Gaussiana** rappresenta la **forma limite della Binomiale** quando il numero di prove è grande.  
È il modello fondamentale di moltissimi fenomeni naturali e sperimentali, come:

- errori di misura,
    
- fluttuazioni casuali,
    
- variazioni biologiche o fisiche attorno a un valore medio.

---

### **13. Riepilogo finale**

|**Concetto**|**Formula / Significato**|
|---|---|
|**Forma generale**|$f(x) = \frac{1}{\sqrt{2\pi}\sigma} e^{-\frac{(x - \mu)^2}{2\sigma^2}}$|
|**Media**|$\mu$|
|**Varianza**|$\sigma^2$|
|**Deviazione standard**|$\sigma$|
|**Normalizzazione**|$\int_{-\infty}^{+\infty} f(x),dx = 1$|
|**Standardizzazione**|$z = \frac{x - \mu}{\sigma}$|
|**Legge dei tre sigma**|68.27%, 95.45%, 99.73%|
|**Proprietà**|Simmetrica, continua, con media = moda = mediana|
|**Applicazioni**|Errori sperimentali, affidabilità, fenomeni casuali|
