# **M3 UD2 Lezione 3 - Distribuzione della media campionaria**

### **1. Introduzione**

In questa lezione analizziamo una delle grandezze più importanti della statistica: la **media campionaria**, o **media empirica**.  
Si tratta del valore medio calcolato su un campione finito estratto da una popolazione.

Poiché ogni campione è diverso dagli altri, anche la sua media cambia da caso a caso.  
La **media campionaria** è quindi una **variabile aleatoria** e possiede una **sua distribuzione di probabilità**.

---

### **2. Ambito dello studio**

Quando estraiamo un campione di $n$ osservazioni da una popolazione nota, ci interessa conoscere il comportamento delle principali grandezze statistiche del campione:

- il **minimo**,
    
- il **massimo**,
    
- la **media**.

Dopo aver studiato minimo e massimo, ora ci concentriamo sulla **distribuzione della media aritmetica** del campione.

---

### **3. Esempio illustrativo**

Consideriamo una **popolazione uniforme** tra $a$ e $b$.  
Estraiamo due valori $x_1$ e $x_2$ e calcoliamo la loro media aritmetica:

$$  
m = \frac{x_1 + x_2}{2}  
$$

Il punto medio $m$ **non cadrà sempre** sulla media vera $(a+b)/2$, ma varierà da campione a campione.  
Ripetendo molte estrazioni, otteniamo una **distribuzione di valori di $m$** centrata attorno alla media vera.

👉 La **media empirica** è quindi una **variabile aleatoria** con una **densità specifica**.

---

### **4. Media empirica come variabile aleatoria**

La media aritmetica dei valori del campione:

$$  
m = \frac{1}{n}\sum_{i=1}^{n}x_i  
$$

è una variabile aleatoria, perché:

- ogni $x_i$ è una variabile casuale,
    
- la loro somma è una nuova variabile aleatoria,
    
- e la divisione per $n$ non cambia la natura aleatoria, ma **contrae la distribuzione**.

Per determinare la densità della media $m$, useremo il risultato noto per la **somma di variabili aleatorie** e studieremo come la densità cambia quando la si **moltiplica per una costante**.

---

### **5. Contrazione della densità**

Sia $z$ una variabile aleatoria con densità $f_Z(z)$.  
Se definiamo una nuova variabile:

$$  
m = \frac{z}{n}  
$$

allora la densità di $m$ risulta **contratta lungo l’asse delle ascisse** di un fattore $n$ e **moltiplicata in altezza** per lo stesso fattore, in modo da mantenere l’area totale pari a 1:

$$  
f_M(m) = n f_Z(nm)  
$$

Questo effetto è detto **contrazione della densità**.

---

### **6. Esempio: popolazione uniforme (caso continuo)**

Supponiamo di estrarre **due valori casuali uniformi** tra 0 e 1 e di calcolarne la media $m$.

1. La densità della **somma** $z = x_1 + x_2$ è una triangolare tra 0 e 2:  
    $$  
    f_Z(z) =  
    \begin{cases}  
    z, & 0 \le z \le 1 \  
    2 - z, & 1 < z \le 2  
    \end{cases}  
    $$
    
2. La media è $m = z/2$, quindi la densità della media è:  
    $$  
    f_M(m) = 2 f_Z(2m)  
    $$

→ La forma è la stessa della distribuzione della somma, ma **contratta di un fattore 2** e **più alta di un fattore 2**.

---

### **7. Esempio: popolazione Bernoulliana (caso discreto)**

Consideriamo una **popolazione Bernoulliana** con parametro $p$:

$$  
P(i = 1) = p, \quad P(i = 0) = q = 1 - p  
$$

Estraiamo **due valori** ($n=2$) e calcoliamo la media aritmetica:

$$  
m = \frac{i + j}{2}  
$$

#### **Passaggi**

1. La distribuzione della **somma** $k = i + j$ è una **Binomiale**:  
    $$  
    B(k | n=2, p)  
    $$
    
2. La distribuzione della **media** si ottiene **contraendo** la Binomiale di un fattore 2:  
    $$  
    P(m) = B(2m | n=2, p)  
    $$

Questa nuova distribuzione è definita sugli **interi e sui mezzi** (0, 0.5, 1), invece che solo sugli interi.

📌 Sul discreto non è necessaria alcuna rinormalizzazione, poiché la somma delle probabilità resta 1.

---

### **8. Baricentro (media) e varianza della media campionaria**

Ora vogliamo calcolare:

- il **baricentro** (media) della distribuzione della media campionaria,
    
- la sua **varianza**.

---

### **9. Baricentro della distribuzione della media campionaria**

Per una popolazione con media $\mu_X$, la **somma** di $n$ variabili indipendenti ha media:

$$  
\mu_Z = n \mu_X  
$$

Poiché la media campionaria è $m = Z/n$, la sua media è:

$$  
\mu_m = \frac{\mu_Z}{n} = \frac{n \mu_X}{n} = \mu_X  
$$

👉 **Il baricentro (media) della distribuzione della media campionaria coincide sempre con la media della popolazione.**

---

### **10. Varianza della media campionaria**

Dalla proprietà della varianza:

$$  
\sigma^2(aX) = a^2 \sigma^2(X)  
$$

e sapendo che la varianza della somma di $n$ variabili indipendenti è la somma delle varianze:

$$  
\sigma_Z^2 = n \sigma_X^2  
$$

segue che la varianza della media è:

$$  
\sigma_m^2 = \frac{\sigma_Z^2}{n^2} = \frac{n\sigma_X^2}{n^2} = \frac{\sigma_X^2}{n}  
$$

Quindi la **deviazione standard della media campionaria** è:

$$  
\sigma_m = \frac{\sigma_X}{\sqrt{n}}  
$$

---

### **11. Conseguenze**

- **Il baricentro** della distribuzione della media campionaria **non cambia** rispetto a quello della popolazione.
    
- **La varianza** della distribuzione della media **si riduce di un fattore $1/n$**.
    
- Quindi, aumentando la dimensione del campione, la media empirica **oscilla sempre meno** attorno alla media vera.
    

---

### **12. Approssimazione Gaussiana**

Per $n$ **moderatamente grande**, la **densità della media campionaria** tende a essere **gaussiana**:

$$  
f_M(m) \approx N(m ,|, \mu, , \sigma^2/n)  
$$

Questa è una conseguenza diretta del **Teorema del Limite Centrale**:

> la somma (e quindi la media) di molte variabili indipendenti con varianza finita è approssimabile con una distribuzione normale.

---

### **13. Significato pratico**

Poiché:  
$$  
\sigma_m = \frac{\sigma_X}{\sqrt{n}}  
$$

- se aumentiamo il campione di un fattore 100,  
    → l’incertezza sulla media si riduce di un fattore 10.

Questo principio è alla base del miglioramento dell’**accuratezza statistica** con campioni grandi.

---

### **14. Legame con la Legge dei Grandi Numeri**

Per $n$ molto grande:

$$  
m \to \mu  
$$

ovvero la **media campionaria tende alla media vera** della popolazione.  
Le fluttuazioni relative si riducono come $1/\sqrt{n}$, in perfetto accordo con il Teorema del Limite Centrale.

---

### **15. Sintesi finale**

|Proprietà|Espressione|Significato|
|:--|:--|:--|
|**Media della media campionaria**|$\mu_m = \mu_X$|La media empirica è centrata sulla media vera|
|**Varianza della media campionaria**|$\sigma_m^2 = \sigma_X^2 / n$|La dispersione si riduce con $n$|
|**Forma per $n$ grande**|$N(m|\mu, \sigma^2/n)$|
|**Legge dei grandi numeri**|$m \to \mu$|La media campionaria converge alla media della popolazione|
