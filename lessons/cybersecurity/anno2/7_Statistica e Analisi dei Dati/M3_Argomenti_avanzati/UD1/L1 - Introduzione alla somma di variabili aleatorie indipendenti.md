# **M3 UD1 Lezione 1 - Introduzione alla somma di variabili aleatorie indipendenti**

### **1. Introduzione**

Talvolta una **variabile aleatoria** nasce come **somma di altre due variabili aleatorie**.  
Un esempio classico è il lancio di due dadi e la somma dei risultati.

Un altro esempio pratico è quello di **due componenti elettronici**:

- ciascuno ha una **durata di vita aleatoria**;
    
- il primo lavora fino al guasto, poi viene sostituito dal secondo (in **stand-by**);
    
- il **tempo totale di funzionamento** è la **somma** dei due tempi individuali.

Poiché entrambi i tempi sono casuali, anche la **somma** è una **variabile aleatoria**.

---

### **2. Il problema da risolvere**

Dato:

- una variabile aleatoria $x$ distribuita secondo $X(x)$;
    
- una variabile aleatoria $y$ distribuita secondo $Y(y)$;

si definisce una nuova variabile:

$$  
z = x + y  
$$

L’obiettivo è **determinare la distribuzione** della variabile somma $Z(z)$, nota la distribuzione delle variabili addendo.  
In generale, la **distribuzione di $z$** non coincide con quella di $x$ o di $y$.

---

### **3. Esempio illustrativo: la somma di due dadi**

Per illustrare il concetto, consideriamo due **dadi onesti e identici** (non truccati).  
Le due variabili casuali associate, $x$ e $y$, sono quindi **indipendenti e identicamente distribuite** (i.i.d.).

Per semplificare l’esempio, supponiamo che ogni dado possa fornire solo i tre esiti ${1, 2, 3}$.

- Lanciando il primo dado otteniamo $x$ → variabile **discreta uniforme**.
    
- Lanciando il secondo dado otteniamo $y$ → anch’essa **discreta uniforme**.

---

### **4. Lo spazio dei casi**

Lo spazio dei casi è il **prodotto cartesiano**:

$$  
S = {1,2,3} \times {1,2,3}  
$$

che contiene $3 \times 3 = 9$ coppie equiprobabili.  
Ogni coppia $(x, y)$ ha probabilità:

$$  
P(x, y) = P(x) \cdot P(y) = \frac{1}{3} \cdot \frac{1}{3} = \frac{1}{9}  
$$

|y \ x|1|2|3|
|:-:|:-:|:-:|:-:|
|3|(1,3)|(2,3)|(3,3)|
|2|(1,2)|(2,2)|(3,2)|
|1|(1,1)|(2,1)|(3,1)|

---

### **5. Calcolo delle somme**

Per ciascuna coppia calcoliamo $z = x + y$.

|y \ x|1|2|3|
|:-:|:-:|:-:|:-:|
|3|4|5|6|
|2|3|4|5|
|1|2|3|4|

I valori possibili di $z$ sono ${2, 3, 4, 5, 6}$.

---

### **6. Distribuzione della somma**

Raggruppando i casi con lo stesso valore di $z$ e sommando le probabilità corrispondenti:

|z|Casi favorevoli|Probabilità $P(z)$|
|:-:|:-:|:-:|
|2|(1,1)|$\tfrac{1}{9}$|
|3|(1,2), (2,1)|$\tfrac{2}{9}$|
|4|(1,3), (2,2), (3,1)|$\tfrac{3}{9}$|
|5|(2,3), (3,2)|$\tfrac{2}{9}$|
|6|(3,3)|$\tfrac{1}{9}$|

---

### **7. Forma della distribuzione**

Rappresentando graficamente le probabilità:

$$
\begin{cases}
P(z=2)=\tfrac{1}{9} \\\\
P(z=3)=\tfrac{2}{9} \\\\
P(z=4)=\tfrac{3}{9} \\\\
P(z=5)=\tfrac{2}{9} \\\\
P(z=6)=\tfrac{1}{9}
\end{cases}
$$


La distribuzione assume una **forma triangolare**: i valori centrali sono più probabili di quelli estremi.

---

### **8. Interpretazione grafica**

La forma triangolare si può giustificare anche graficamente:

- nella tabella delle somme, i valori uguali si dispongono **lungo diagonali**;
    
- il numero di coppie che danno lo stesso $z$ cresce fino al centro e poi decresce simmetricamente.

Questo comportamento è tipico della **somma di variabili uniformi**.

---

### **9. Generalizzazione**

#### **Caso discreto (dadi a sei facce)**

Anche per due dadi reali con esiti da 1 a 6, la distribuzione della somma è triangolare:

$$  
z \in {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}  
$$

$$  
P(z) = \frac{\#(z)}{36}  
$$

dove $\#(z)$ è il numero di coppie $(x, y)$ che producono quella somma.

---

#### **Caso continuo**

Per due variabili **uniformi continue** su $[0,1]$:

$$  
X(x) = 1 \quad \text{per } 0 \le x \le 1  
$$  
$$  
Y(y) = 1 \quad \text{per } 0 \le y \le 1  
$$

la densità della somma $Z(z)$ è triangolare su $[0,2]$:

$$  
Z(z) =  
\begin{cases}  
z, & 0 \le z \le 1 \\\\  
2 - z, & 1 < z \le 2  
\end{cases}  
$$

---

### **10. Ricapitolazione del procedimento**

1. **Costruire la tabella** delle coppie $(x, y)$ e calcolare $P(x, y) = P(x)P(y)$.
    
2. **Sommarne le probabilità** per tutte le coppie che producono lo stesso valore di $z$.
    
3. **Ottenere la distribuzione** di $z = x + y$.

---

### **11. Conclusioni**

- La somma di variabili aleatorie è **una nuova variabile aleatoria**.
    
- La sua distribuzione **non coincide** con quella delle variabili originali.
    
- In particolare, **la somma di due variabili uniformi** produce una **distribuzione triangolare**.