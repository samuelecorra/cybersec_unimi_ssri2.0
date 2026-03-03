# **M2 UD1 Lezione 3 - Indicatori di posizione**

### **1. Sintesi delle distribuzioni: posizione e ampiezza**

Nello studio delle variabili aleatorie, non è sempre pratico lavorare con l’intera **distribuzione di probabilità** o **densità**.  
Spesso è sufficiente una **descrizione sintetica**, che ne riassuma le caratteristiche principali.

Le proprietà fondamentali che descrivono una distribuzione sono:

- **Posizione** → dove si concentra la maggior parte della probabilità.
    
- **Ampiezza** → quanto la distribuzione è “larga” o dispersa.

In questa lezione ci concentriamo sugli **indicatori di posizione**, che servono a individuare un valore rappresentativo della distribuzione.

---

### **2. Indicatori di posizione**

Per caratterizzare la posizione di una densità $f(x)$ o di una distribuzione $P(k)$ si utilizzano tre indicatori fondamentali:

1. **Moda ($x_{\text{moda}}$)** – punto in cui la probabilità è massima.
    
2. **Mediana ($x_{\text{mediana}}$)** – punto che divide la distribuzione in due metà uguali.
    
3. **Media ($x_{\text{media}}$ o $\mu$)** – punto di equilibrio o baricentro della distribuzione.

$$  
x_{\text{moda}} \quad < \quad x_{\text{mediana}} \quad < \quad x_{\text{media}}  
$$

---

### **3. La Moda**

La **moda** è il valore di $x$ per cui la distribuzione o densità assume il suo **massimo**.

$$  
x_{\text{moda}} = \operatorname*{argmax}_x f(x)  
$$

Se la funzione è derivabile:

$$  
\frac{df(x)}{dx} = 0 \quad \text{e} \quad \frac{d^2f(x)}{dx^2} < 0  
$$

**Osservazioni:**

- Non tutte le densità hanno una moda (alcune sono piatte, come l’uniforme).
    
- Alcune distribuzioni sono **multimodali**, cioè con più punti di massimo.
    
- Talvolta il massimo si trova **agli estremi** dell’intervallo di definizione.

---

### **4. La Mediana**

La **mediana** è il punto che divide la distribuzione in due parti uguali, cioè il valore per cui la **funzione cumulativa** vale $0.5$:

$$  
F(x_{\text{mediana}}) = \frac{1}{2}  
$$

Oppure, in termini di funzioni complementari:

$$  
F(x_{\text{mediana}}) = S(x_{\text{mediana}})  
$$

Interpretazione:

- La metà della probabilità totale è **a sinistra** di $x_{\text{mediana}}$.
    
- L’altra metà è **a destra**.

Esempio per una **densità uniforme**:

$$  
x_{\text{mediana}} = a + \frac{b - a}{2}  
$$

---

### **5. La Media**

La **media** (o valore atteso, o _speranza matematica_) è la **coordinata del baricentro** della distribuzione.

È il valore che rappresenta il **punto di equilibrio** della massa di probabilità.

#### **Definizione generale**

- **Caso discreto:**

$$  
\mu = \sum_k k , P(k)  
$$

- **Caso continuo:**

$$  
\mu = \int_{-\infty}^{+\infty} x , f(x),dx  
$$

Si indica comunemente con $\mu$, $\langle x \rangle$ o $x_{\text{media}}$.

---

### **6. Indicatori per la densità uniforme**

La densità uniforme è definita da:

$$  
f(t) =  
\begin{cases}  
\frac{1}{b - a}, & a \le t \le b \  
0, & \text{altrove}  
\end{cases}  
$$

- **Moda:** assente (tutti i valori hanno la stessa probabilità).
    
- **Mediana:** punto medio dell’intervallo

$$  
x_{\text{mediana}} = a + \frac{b - a}{2}  
$$

- **Media:** coincide per simmetria con la mediana

$$  
x_{\text{media}} = a + \frac{b - a}{2}  
$$

Quindi per la distribuzione uniforme:  
$$x_{\text{media}} = x_{\text{mediana}}$$

---

### **7. Indicatori per la Bernoulliana**

La **distribuzione di Bernoulli** è definita come:

$$  
P(0) = 1 - p, \quad P(1) = p  
$$

#### **Moda:**

Dipende dal valore di $p$:

- Se $p > \tfrac{1}{2}$, allora $k_{\text{moda}} = 1$
    
- Se $p < \tfrac{1}{2}$, allora $k_{\text{moda}} = 0$

#### **Mediana:**

Segue la stessa regola della moda:

- Se $p > \tfrac{1}{2}$, allora $k_{\text{mediana}} = 1$
    
- Se $p < \tfrac{1}{2}$, allora $k_{\text{mediana}} = 0$

Per la Bernoulliana, **moda e mediana coincidono**.

#### **Media:**

$$  
\langle k \rangle = 0 \cdot (1 - p) + 1 \cdot p = p  
$$

La media può non coincidere con un valore discreto della distribuzione.

---

### **8. Sensibilità degli indicatori**

Ogni indicatore reagisce in modo diverso ai **cambiamenti nella distribuzione**:

|**Indicatore**|**Sensibilità**|**Caratteristiche principali**|
|---|---|---|
|**Moda**|Bassa|Dipende solo dal punto di massimo; utile per distribuzioni unimodali|
|**Mediana**|Media|Dipende solo dall’ordine dei valori; non cambia se si deformano le code|
|**Media**|Alta|Dipende dalle coordinate di tutti i punti; molto sensibile agli estremi|

#### **Esempio visivo (concettuale)**

- Due distribuzioni possono avere **stessa moda**, ma medie diverse.
    
- Due distribuzioni possono avere **stessa mediana**, ma medie diverse.

---

### **9. Definizione ed esistenza**

- La **moda** non è sempre definita (assenza di massimo → es. uniforme).
    
- La **mediana** è **sempre definita**, poiché $F(x)$ passa sempre da $1/2$.
    
- La **media** può non essere definita quando l’integrale $\int x f(x),dx$ diverge  
    (esempio: $f(x) = \tfrac{1}{x^2}$).

---

### **10. Coincidenza degli indicatori**

Quando una distribuzione è **simmetrica**, gli indicatori coincidono:

$$  
\text{media} = \text{mediana} = \text{moda}  
$$

Esempio:

- Densità uniforme
    
- Densità gaussiana centrata

---

### **11. Riepilogo finale**

|**Distribuzione / Densità**|**Moda**|**Mediana**|**Media**|
|---|---|---|---|
|Bernoulliana ($p$)|0 o 1 (a seconda di $p$)|0 o 1 (a seconda di $p$)|$p$|
|Uniforme ($[a,b]$)|Nessuna|$a + \tfrac{b - a}{2}$|$a + \tfrac{b - a}{2}$|
|Simmetrica (generica)|Coincidono|Coincidono|Coincidono|

---

### **12. Considerazioni finali**

- La **moda** fornisce un valore tipico, ma non sempre rappresentativo.
    
- La **mediana** è robusta rispetto agli estremi e alle code.
    
- La **media** è utile per analisi matematiche e statistiche, ma sensibile agli outlier.

In sintesi, **la scelta dell’indicatore dipende dal contesto** e dal tipo di distribuzione analizzata.