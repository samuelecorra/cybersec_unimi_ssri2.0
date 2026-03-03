# **M3 UD3 Lezione 1 - Introduzione alla statistica inferenziale**

### **1. Introduzione generale**

La **statistica inferenziale** si occupa di risalire, partendo dai **dati sperimentali**, alla **plausibilità delle ipotesi** che possono spiegare quei dati.  
In altre parole, il suo obiettivo è **dedurre la teoria più probabile** a partire dalle osservazioni.

Questa disciplina si fonda sulla **probabilità inversa**, che abbiamo già incontrato nel Modulo 1, e che si basa sul **Teorema di Bayes**.

- In **probabilità diretta**, noto il modello, calcolo la probabilità di un certo dato.
    
- In **probabilità inversa**, noto il dato, calcolo la probabilità delle diverse ipotesi che potrebbero averlo generato.

---

### **2. Dati e teorie**

#### **Due prospettive fondamentali**

- **ONE THEORY → MANY EXPERIMENTS**  
    Una stessa teoria probabilistica può dare origine a risultati sperimentali diversi con probabilità differenti.
    
- **MANY THEORIES → ONE EXPERIMENT**  
    Un singolo risultato sperimentale può essere compatibile, in misura diversa, con più teorie alternative.

👉 La statistica inferenziale si occupa di **valutare la plausibilità relativa delle teorie** alla luce di un esperimento.

---

### **3. Quando le ipotesi sono numeri**

In precedenza abbiamo considerato **ipotesi categoriche** (es. moneta A, B o C).  
Ora introduciamo il caso in cui le **ipotesi assumono valori numerici**.

#### **Esempio concettuale**

Quando l’ipotesi è numerica, ad esempio:

- il grado di sbilanciamento di una moneta,
    
- o la media di una distribuzione ignota,

possiamo associare a ciascun valore **una probabilità** e organizzare tali ipotesi in una **distribuzione di probabilità**.

---

### **4. Distribuzioni delle ipotesi**

Quando le ipotesi sono numeriche, non parliamo più di “una probabilità per ciascuna ipotesi”, ma di una **distribuzione di probabilità sulle ipotesi**.

- La **prior distribution** $P(h)$ rappresenta la nostra conoscenza (o ignoranza) _prima_ di osservare i dati.
    
- La **likelihood** $P(T|h)$ rappresenta quanto un dato $T$ è compatibile con una certa ipotesi $h$.
    
- La **posterior distribution** $P(h|T)$ rappresenta ciò che sappiamo _dopo_ aver osservato il dato.

Il Teorema di Bayes lega questi elementi:

$$  
P(h|T) = \frac{P(h) , P(T|h)}{P(T)}  
$$

dove:

- $P(h)$ = prior,
    
- $P(T|h)$ = verosimiglianza,
    
- $P(T)$ = evidence (costante di normalizzazione).

---

### **5. Esempio: le tre monete**

Immaginiamo un’urna con tre monete:

- **A:** dà sempre Testa $(h=1)$
    
- **B:** bilanciata $(h=1/2)$
    
- **C:** dà sempre Croce $(h=0)$

Prima del lancio, le ipotesi sono equiprobabili:

$$  
P(A) = P(B) = P(C) = \frac{1}{3}  
$$

cioè:

$$  
P(h=0) = P(h=1/2) = P(h=1) = \frac{1}{3}  
$$

---

### **6. Calcolo della likelihood**

La probabilità di ottenere **Testa** dato ciascun valore di $h$ è:

$$  
P(T|h=1) = 1, \quad P(T|h=1/2) = \frac{1}{2}, \quad P(T|h=0) = 0  
$$

---

### **7. Calcolo della evidence**

La **probabilità dell’evidenza** (ottenere Testa) è data da:

$$  
P(T) = \sum_h P(T|h) P(h) = \frac{1}{3}(1 + \frac{1}{2} + 0) = \frac{1}{2}  
$$

---

### **8. Calcolo della posterior**

Applicando Bayes:

$$  
P(h|T) = \frac{P(h) P(T|h)}{P(T)}  
$$

otteniamo:

$$  
P(h=0|T) = 0, \quad P(h=1/2|T) = \frac{1}{3}, \quad P(h=1|T) = \frac{2}{3}  
$$

La posterior è quindi concentrata verso le ipotesi più “orientate a Testa”.

---

### **9. Media e moda della posterior**

- **Moda:** $h=1$ (ipotesi più probabile).
    
- **Media:** valore medio ponderato delle ipotesi, più basso della moda.

La distribuzione a posteriori $P(h|T)$ contiene **tutta l’informazione disponibile dopo il lancio**.

---

### **10. Esempio con quattro monete**

Supponiamo ora di avere quattro monete con probabilità di dare Testa pari a:

$$  
h = 0, \ \frac{1}{3}, \ \frac{2}{3}, \ 1  
$$

e ipotesi a priori equiprobabili:

$$  
P(h) = \frac{1}{4} \quad \forall h  
$$

Dopo un lancio in cui esce **Testa**, otteniamo la posterior:

|    h     |  0   | 1/3 | 2/3 |  1  |
| :------: | :--: | :-: | :-: | :-: |
| **P(h)** | 1/4  | 1/4 | 1/4 | 1/4 |
|  **P(h   | T)** |  0  | 1/6 | 1/3 |

---

### **11. Analisi della posterior**

Domande tipiche:

1. **Qual è l’ipotesi più probabile?**  
    → $h = 1$, cioè la moneta che dà sempre Testa (moda della distribuzione).
    
2. **Qual è la probabilità che la moneta sia sbilanciata verso Testa?**  
    → $P(h > 0.5) = P(h=2/3) + P(h=1) = \frac{2}{6} + \frac{3}{6} = \frac{5}{6}$

---

### **12. Ipotesi numeriche ≠ variabili aleatorie**

È importante notare che le ipotesi non sono variabili aleatorie nel senso fisico:

- la moneta ha **un preciso sbilanciamento reale**, solo che non lo conosciamo;
    
- trattiamo le ipotesi come distribuite probabilisticamente per **rappresentare la nostra incertezza soggettiva**.

In sintesi:

- nella **probabilità diretta**, è incerto il risultato del lancio;
    
- nella **probabilità inversa**, è incerto il valore del parametro (es. $h$).

---

### **13. Dal discreto al continuo**

Se non conosciamo affatto il valore di $h$ e possiamo ipotizzare qualunque sbilanciamento tra 0 e 1:

- le ipotesi diventano **continue**;
    
- la **prior** $P(h)$ è uniforme su $[0,1]$;
    
- la **likelihood** è $P(T|h)=h$;
    
- la **posterior** si ottiene come:

$$  
P(h|T) \propto P(h) , P(T|h) = h  
$$

rinormalizzata sull’intervallo $[0,1]$.

In questo caso, la densità a posteriori è **proporzionale a $h$**, cioè cresce linearmente.

---

### **14. Probabilità di sbilanciamento**

Dopo un risultato “Testa”, le ipotesi di **sbilanciamento verso Testa** aumentano di plausibilità.  
L’area sotto la curva per $h > 0.5$ è pari a **3/4**, quindi:

$$  
P(h > 0.5 | T) = \frac{3}{4}  
$$

---

### **15. Aggiornamento reiterato**

Il processo bayesiano può essere **ripetuto** ogni volta che arrivano nuovi dati:

1. Dopo il primo lancio → calcolo la **posterior**.
    
2. Tale posterior diventa la **nuova prior**.
    
3. Dopo un nuovo dato, calcolo una **nuova posterior**, e così via.

Il meccanismo di aggiornamento è quindi **iterativo** e permette di incorporare progressivamente nuova evidenza senza ricominciare da zero.

---

### **16. Osservazioni finali**

- Il **Teorema di Bayes** fornisce una struttura coerente per aggiornare la conoscenza.
    
- Nel caso di **ipotesi numeriche**, possiamo rappresentare la nostra incertezza tramite **distribuzioni e densità**.
    
- A ogni nuova evidenza, la posterior si sposta verso i valori più compatibili con i dati.
    
- Questo approccio prepara il terreno alla prossima lezione, in cui si studieranno **distribuzioni e stime dei parametri** (media, varianza, ecc.) di una popolazione a partire da un campione.