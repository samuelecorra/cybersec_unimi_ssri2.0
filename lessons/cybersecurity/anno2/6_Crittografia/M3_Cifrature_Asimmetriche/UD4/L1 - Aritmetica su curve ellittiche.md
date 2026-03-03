
# **Lezione 1: Aritmetica su curve ellittiche**

### **1. Introduzione storica**

I **crittosistemi su curve ellittiche (ECC)** furono proposti **indipendentemente** da:

- **Victor Miller (1986)**
    
- **Neal Koblitz (1987)**
    

L’idea alla base è quella di costruire un **analogo dei crittosistemi modulari classici** (come El-Gamal o RSA), sostituendo l’aritmetica modulare con le **operazioni sui punti di una curva ellittica**.

#### **Vantaggi principali**

- Gli algoritmi noti per risolvere l’analogo del **logaritmo discreto su curve ellittiche (ECDLP)** sono **molto più lenti** rispetto al caso classico.
    
- È quindi possibile ottenere la **stessa sicurezza con chiavi molto più corte**.
    

---

### **2. Definizione di curva ellittica**

Una **curva ellittica** è l’insieme dei punti $(x, y)$ che soddisfano un’equazione cubica del tipo:

$$  
y^2 + axy + by = x^3 + cx^2 + dx + e  
$$

A essa si aggiunge un **punto speciale all’infinito**, detto **punto zero** o **punto neutro**, indicato con $O$.

---

### **3. Esempi di curve ellittiche reali**

Due esempi tipici di curve reali sono:

$$  
y^2 = x^3 + x + 1  
$$

e

$$  
y^2 = x^3 - x  
$$

Queste rappresentano le **curve di base** su cui si studiano graficamente le operazioni di gruppo.

---

### **4. Operazioni sui punti di una curva**

Le curve ellittiche formano un **gruppo abeliano** con l’operazione di **addizione di punti**.

#### **Caso generale**

Se $P_1$ e $P_2$ sono due punti sulla curva, la loro somma è un punto $R = P_1 + P_2$.

#### **Caso particolare**

Se i due punti hanno **stessa ascissa** ($x_1 = x_2$) ma **ordinate opposte** ($y_1 = -y_2$),  
allora:

$$  
P_1 + P_2 = O  
$$

(cioè il punto all’infinito agisce come **identità additiva**).

---

### **5. Identità additiva e moltiplicazione scalare**

Le principali proprietà del gruppo sono:

- **Elemento neutro**:  
    $$  
    P + O = O + P = P  
    $$
    
- **Inverso additivo**:  
    $$  
    -P = (x, -y)  
    $$
    
- **Duplicazione di un punto**:  
    $$  
    2P = P + P  
    $$
    
- **Moltiplicazione scalare** (ripetuta somma):  
    $$  
    kP = \underbrace{P + P + \dots + P}_{k \text{ volte}}  
    $$
    

---

### **6. Curve ellittiche su campi finiti $\mathbb{Z}_p$**

Una **curva ellittica su $\mathbb{Z}_p$** (con $p$ primo e $p > 3$) è definita dall’equazione:

$$  
y^2 = x^3 + a x + b \pmod{p}  
$$

con le condizioni:

- $a, b \in \mathbb{Z}_p$
    
- $4a^3 + 27b^2 \not\equiv 0 \pmod{p}$  
    → garantisce che la curva **non abbia punti singolari** (cioè tre radici distinte).
    

Si include sempre anche il **punto all’infinito $O$**.

---

### **7. Addizione di punti in $\mathbb{Z}_p$**

Data una curva $E_p(a, b)$ e due punti:

$$  
P_1 = (x_1, y_1), \quad P_2 = (x_2, y_2)  
$$

l’addizione $P_3 = P_1 + P_2$ è definita come:

$$  
\begin{cases}  
P_3 = O, & \text{se } x_1 = x_2 \text{ e } y_1 = -y_2 \\\\  
\lambda = \dfrac{y_2 - y_1}{x_2 - x_1} \pmod{p}, & \text{se } P_1 \ne P_2 \\\\  
\lambda = \dfrac{3x_1^2 + a}{2y_1} \pmod{p}, & \text{se } P_1 = P_2  
\end{cases}  
$$

e i nuovi punti risultano:

$$  
\begin{aligned}  
x_3 &= \lambda^2 - x_1 - x_2 \pmod{p} \  
\end{aligned}  
$$

$$  
\begin{aligned}  
y_3 &= \lambda(x_1 - x_3) - y_1 \pmod{p}  
\end{aligned}  
$$

---

### **8. Proprietà del gruppo $(E_p(a,b), +)$**

- $P + O = P$
    
- $O = -O$
    
- $-(x_1, y_1) = (x_1, -y_1)$
    
- $(E_p(a,b), +)$ è un **gruppo abeliano**
    

---

### **9. Esempio: curva $E_{23}(1,1)$**

Curva:

$$  
y^2 = x^3 + x + 1 \pmod{23}  
$$

Condizione di validità:

$$  
4a^3 + 27b^2 = 4\cdot 1^3 + 27\cdot 1^2 = 31 \equiv 8 \ne 0 \pmod{23}  
$$

✅ La curva è quindi **valida**.

#### **Punti appartenenti a $E_{23}(1,1)$**

$(0,1)$, $(0,22)$, $(1,7)$, $(1,16)$, $(3,10)$, $(3,13)$, $(4,0)$, $(5,4)$, $(5,19)$,  
$(6,4)$, $(6,19)$, $(7,11)$, $(7,12)$, $(9,7)$, $(9,16)$, $(11,3)$, $(11,20)$, $(12,4)$,  
$(12,19)$, $(13,7)$, $(13,16)$, $(17,3)$, $(17,20)$, $(18,3)$, $(18,20)$, $(19,5)$, $(19,18)$.

---

### **10. Costruzione dei punti**

Per ogni $x \in {0, 1, \dots, 22}$ si calcola:  
$$  
f(x) = x^3 + x + 1 \pmod{23}  
$$

Se $f(x)$ è un **quadrato in $\mathbb{Z}_{23}$**, allora:  
$$  
y^2 \equiv f(x) \pmod{23}  
$$

e si ottengono **due soluzioni** $(x, y_1)$ e $(x, y_2 = -y_1)$.

---

### **11. Esempio di addizione in $E_{23}(1,1)$**

Sommiamo i punti:

$$  
P_1 = (3,10), \quad P_2 = (9,7)  
$$

Calcoliamo:

$$  
\begin{aligned}  
\lambda &= \frac{7 - 10}{9 - 3} = \frac{-3}{6} = 11 \pmod{23} \\\\  
x_3 &= 11^2 - 3 - 9 = 109 \equiv 17 \pmod{23} \\\\  
y_3 &= 11(3 - 17) - 10 = -154 - 10 = -164 \equiv 20 \pmod{23}  
\end{aligned}  
$$

Risultato:  
$$  
P_1 + P_2 = (17, 20)  
$$

---

### **12. Esempio di moltiplicazione in $E_{23}(1,1)$**

Calcoliamo la duplicazione di $(17, 20)$:

$$  
2P_1 = (17,20) + (17,20)  
$$

$$  
\begin{aligned}  
\lambda &= \frac{3(17^2) + 1}{2 \cdot 20} = \frac{868}{40} \equiv 6 \pmod{23} \\\\  
x_3 &= 6^2 - 3 - 3 = 30 \equiv 7 \pmod{23} \\\\  
y_3 &= 6(3 - 7) - 10 = -34 \equiv 12 \pmod{23}  
\end{aligned}  
$$

Risultato:  
$$  
2(17,20) = (7,12)  
$$

---

### **13. Numero di punti su una curva ellittica**

Il numero di punti $|E_p(a,b)|$ su una curva ellittica $E_p(a,b)$ soddisfa il **teorema di Hasse**:

$$  
p + 1 - 2\sqrt{p} \le |E_p(a,b)| \le p + 1 + 2\sqrt{p}  
$$

Il conteggio può essere effettuato con l’**algoritmo di Schoof**,  
di complessità $O((\log p)^8)$ operazioni su bit.

---

### **14. Sintesi finale**

- Le **curve ellittiche** sono descritte da equazioni cubiche e includono un **punto all’infinito**.
    
- Le operazioni di **addizione** e **moltiplicazione scalare** definiscono una **struttura di gruppo abeliano**.
    
- La curva su un campo finito $E_p(a,b)$ fornisce la base per la **crittografia a curva ellittica (ECC)**.
    
- A **parità di sicurezza**, ECC consente l’uso di **chiavi molto più corte** rispetto a RSA o El-Gamal.