# **M2 UD1 Lezione 1 - Distribuzioni e densità di variabili aleatorie**

### **1. Eventi numerici e categorici**

Fino ad ora abbiamo considerato due tipi di eventi:

- **Eventi categorici**, cioè simbolici, come:
    
    - Testa e Croce nel lancio di una moneta.
        
    - Il colore di una pallina estratta da un’urna.
    
- **Eventi numerici**, in cui l’esito è espresso da un numero, come:
    
    - Il numero che esce da un dado.
        
    - Il numero di richieste ricevute da un server in 24 ore.
        
    - Il tempo impiegato da un messaggio per arrivare a destinazione in rete.

I **numeri** si collocano su una **struttura ordinata** (discreta o continua), che permette di utilizzare gli strumenti dell’**analisi matematica**, come derivate e integrali.

---

### **2. Variabili aleatorie discrete**

Quando lo spazio dei casi possibili è **discreto**, rappresentiamo le probabilità con **diagrammi a barre** o “a pettine”.

Per ogni evento numerico $k$, si associa la probabilità $P(k)$.

Esempio di notazione:

- $P(k = 3)$ → probabilità che esca 3.
    
- $P(k > 3)$ → probabilità che esca un valore maggiore di 3.
    
- $P(k \le 3)$ → probabilità che esca un valore minore o uguale a 3.

L’insieme dei valori ${ P(k) \mid k = 1,2,\dots }$ costituisce la **distribuzione di probabilità** della variabile aleatoria $k$.

---

### **3. Variabili aleatorie continue**

Se i valori possibili sono **continui**, la probabilità si rappresenta con una **curva** continua chiamata **densità di probabilità** $f(t)$.

Nel discreto, $P(x)$ è la probabilità associata a un singolo valore (o a un intervallo unitario):

$$  
P(x) = P(x \le k < x+1)  
$$

Nel continuo invece, la probabilità di un singolo valore è **infinitesima**:

$$  
f(x) = P(x \le k < x + dx)  
$$

In pratica:

- Sul **discreto** si chiedono probabilità sia su valori che su intervalli.
    
- Sul **continuo**, solo su **intervalli** (non su valori esatti).

---

### **4. Calcolo di probabilità nel discreto**

Esempio di distribuzione discreta:

$$  
P(k) = \left(\frac{1}{2}\right)^k, \quad k \ge 1  
$$

Domande possibili:

- $P(k = 1)$, $P(k = 2)$, $P(k = 3)$
    
- $P(k \le 3)$
    
- $P(k > 3)$

Calcoli:

$$  
P(k = 1) = \frac{1}{2}, \quad P(k = 2) = \frac{1}{4}, \quad P(k = 3) = \frac{1}{8}  
$$

$$  
P(k \le 3) = \sum_{k=1}^{3} P(k) = \frac{1}{2} + \frac{1}{4} + \frac{1}{8} = \frac{7}{8} = 0.875  
$$

$$  
P(k > 3) = 1 - P(k \le 3) = 0.125  
$$


![](imgs/Pasted%20image%2020251229180832.png)

---

### **5. Calcolo di probabilità nel continuo**

Esempio di densità:

$$  
f(t) = e^{-t}, \quad t > 0  
$$


![](imgs/Pasted%20image%2020251229180851.png)

Domande tipiche:

- $P(t \le 3)$
    
- $P(t > 3)$
    

In questo caso, le risposte si ottengono tramite **integrazione** della densità.


![](imgs/Pasted%20image%2020251229181039.png)

---

### **6. Normalizzazione**

Ogni distribuzione o densità deve rispettare il **principio di normalizzazione**, cioè la **somma totale delle probabilità deve essere 1**:

- Nel **discreto**:

$$  
\sum_{k} P(k) = 1  
$$

- Nel **continuo**:

$$  
\int_{-\infty}^{+\infty} f(t) \ dt = 1  
$$

Esempi:

- Per $P(k) = (1/2)^k$, la somma è una **serie geometrica** di somma totale 1.
    
- Per $f(t) = e^{-t}$, l’integrale da $0$ a $+\infty$ vale 1.


Se non è normalizzata, si può rendere tale dividendo per la somma o l’integrale totale $N$:

$$  
f(t) = \frac{g(t)}{N}  
$$

---

### **7. Definizione generale di variabile aleatoria**

Ogni volta che a un evento di un esperimento si associa un numero, si ottiene una **variabile aleatoria**.  
Si distinguono:

- **Distribuzioni** → per il caso discreto
    
- **Densità** → per il caso continuo

Spesso si usa l’abbreviazione **p.d.f.** per _probability density/distribution function_.

---

### **8. Distribuzioni notevoli**

Nel seguito del corso si studieranno varie famiglie di distribuzioni fondamentali:

|**Distribuzioni (discrete)**|**Densità (continue)**|
|---|---|
|Bernoulliana|Uniforme|
|Geometrica|Esponenziale|
|Binomiale|Gaussiana|
|Poissoniana|—|

---

### **9. Caso notevole: Distribuzione di Bernoulli**

Se si associa **1 a Testa** e **0 a Croce**, con $P(T) = p$ e $P(C) = 1-p$, si ottiene:

$$  
P(0) = 1 - p, \quad P(1) = p  
$$

È una **distribuzione di Bernoulli** (o _Bernoulliana_), cioè una famiglia di distribuzioni con **un solo parametro $p$**.

È già **normalizzata**:

$$  
P(0) + P(1) = (1-p) + p = 1  
$$

---

### **10. Caso notevole: Distribuzione e densità uniforme**

- **Distribuzione uniforme discreta**: tutti gli eventi hanno la stessa probabilità.  
    Esempio: dado bilanciato

$$  
P(k) = \frac{1}{6}, \quad k = 1,2,3,4,5,6  
$$

- **Densità uniforme continua**: la probabilità è costante in un intervallo $[a,b]$

$$  
f(t) =  
\begin{cases}  
\frac{1}{b-a}, & a \le t \le b \\\\  
0, & \text{altrove}  
\end{cases}  
$$

---

### **11. Riepilogo finale**

- Le **variabili aleatorie discrete** si descrivono con distribuzioni $P(k)$.
    
- Le **variabili aleatorie continue** si descrivono con densità $f(t)$.
    
- Le probabilità totali devono essere **normalizzate a 1**.
    
- Le **distribuzioni fondamentali** studiate sono Bernoulli, Uniforme, Geometrica, Esponenziale, Binomiale, Poisson e Gaussiana.