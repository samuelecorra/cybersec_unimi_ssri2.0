# **M1 UD2 Lezione 1 - Potenze e loro proprietà (esponente intero)**

### **1. Introduzione: perché le potenze sono strutturali**

Le **potenze** non sono solo una notazione compatta: sono uno strumento concettuale fondamentale per descrivere **crescita**, **decrescita**, **ordini di grandezza** e **comportamenti asintotici**.  
In Analisi 1, le potenze sono ovunque:

- nei **polinomi**;
    
- nei **limiti**;
    
- nelle **serie**;
    
- nel confronto di funzioni;
    
- nella definizione di **esponenziali** e **logaritmi** (che verranno dopo).
    

In questa lezione ci concentriamo **solo sugli esponenti interi**, positivi e negativi, e sul ruolo della **parità** (pari/dispari).  
Gli esponenti reciproci, razionali e reali verranno affrontati nelle lezioni successive, come hai giustamente pianificato.

---

### **2. Definizione di potenza con esponente intero positivo**

Sia $a \in \mathbb{R}$ e $n \in \mathbb{N}$ con $n \ge 1$.

La potenza di base $a$ ed esponente $n$ è definita come:

$$  
a^n = \underbrace{a \cdot a \cdot \dots \cdot a}_{n \text{ volte}}  
$$

Esempi:

- $2^3 = 2\cdot2\cdot2 = 8$
    
- $(-3)^2 = (-3)\cdot(-3) = 9$
    
- $(-2)^3 = -8$
    

**Osservazione fondamentale:**  
la potenza è un’operazione **ripetitiva**, non una moltiplicazione “strana”. Tutte le proprietà derivano da questa definizione.

---

### **3. Il caso $n = 0$**

Per ogni $a \neq 0$ si definisce:

$$  
a^0 = 1  
$$

Questa non è una convenzione arbitraria: è l’unica scelta coerente con le proprietà delle potenze.

Infatti, dalla regola (che vedremo tra poco):

$$  
\frac{a^n}{a^n} = a^{n-n} = a^0  
$$

ma il primo membro vale 1, quindi **necessariamente**:

$$  
a^0 = 1 \quad (a \neq 0)  
$$

---

### **4. Esponente negativo: significato strutturale**

Per $a \neq 0$ e $n \in \mathbb{N}$, si definisce:

$$  
a^{-n} = \frac{1}{a^n}  
$$

Quindi:

- $2^{-3} = \frac{1}{2^3} = \frac{1}{8}$
    
- $(-2)^{-2} = \frac{1}{(-2)^2} = \frac{1}{4}$
    

**Interpretazione importante:**  
l’esponente negativo **non cambia il tipo di numero**, ma ne inverte il ruolo moltiplicativo.

---

### **5. Riassunto delle definizioni fondamentali**

Per $a\ne0$:

- $a^n$ con $n>0$ → prodotto ripetuto
    
- $a^0 = 1$
    
- $a^{-n} = \dfrac{1}{a^n}$
    

Queste tre definizioni sono **la base di tutto**.

Per la base nulla bisogna invece distinguere: $0^n=0$ per ogni intero $n>0$, mentre $0^0$ non viene definito in questo contesto e $0^{-n}$ non esiste perché richiederebbe una divisione per zero.

---

### **6. Proprietà fondamentali delle potenze**

Tutte le proprietà che seguono valgono per **esponenti interi**, e derivano direttamente dalla definizione.

---

#### **6.1 Prodotto di potenze con la stessa base**

$$  
a^m \cdot a^n = a^{m+n}  
$$

Esempio:

$$  
a^3 \cdot a^2 = (a\cdot a\cdot a)(a\cdot a) = a^5  
$$

Questa proprietà vale **anche con esponenti negativi**, purché $a \neq 0$.

---

#### **6.2 Quoziente di potenze con la stessa base**

$$  
\frac{a^m}{a^n} = a^{m-n}  
$$

con $a \neq 0$.

Esempio:

$$  
\frac{a^5}{a^2} = a^3,  
\quad  
\frac{a^2}{a^5} = a^{-3}  
$$

---

#### **6.3 Potenza di una potenza**

$$  
(a^m)^n = a^{mn}  
$$

Esempio:

$$  
(a^2)^3 = a^{6}  
$$

Questa proprietà è spesso fonte di errori se non si capisce che **gli esponenti si moltiplicano**, non si sommano.

---

#### **6.4 Potenza di un prodotto**

$$  
(ab)^n = a^n b^n  
$$

Esempio:

$$  
(2x)^3 = 2^3 x^3 = 8x^3  
$$

---

#### **6.5 Potenza di un quoziente**

$$  
\left(\frac{a}{b}\right)^n = \frac{a^n}{b^n}  
\quad (b \neq 0)  
$$

---

### **7. Ruolo del segno della base**

Il segno della base interagisce con la **parità dell’esponente**.

---

#### **7.1 Esponente pari**

Se $n$ è **pari**, allora:

$$  
(-a)^n = a^n  
$$

quindi il risultato è **non negativo**.

Esempi:

- $(-3)^2 = 9$
    
- $(-2)^4 = 16$
    

---

#### **7.2 Esponente dispari**

Se $n$ è **dispari**, allora:

$$  
(-a)^n = -a^n  
$$

Esempi:

- $(-2)^3 = -8$
    
- $(-5)^5 = -3125$
    

---

#### **7.3 Sintesi fondamentale**

- esponente **pari** → il segno si perde
    
- esponente **dispari** → il segno si conserva
    

Questo fatto è **cruciale** per:

- studio del segno;
    
- disequazioni;
    
- limiti;
    
- comportamento delle funzioni.
    

---

### **8. Attenzione alle parentesi (errore tipico)**

Un errore frequentissimo è confondere:

$$  
-2^2 \quad \text{e} \quad (-2)^2  
$$

Infatti:

- $-2^2 = -(2^2) = -4$
    
- $(-2)^2 = 4$
    

**Regola d’oro:**  
l’esponente agisce **solo su ciò che è tra parentesi**.

---

### **9. Potenze e confronto tra numeri**

Se $a>1$:

- $a^n$ cresce all’aumentare di $n$
    

Se $0<a<1$:

- $a^n$ decresce all’aumentare di $n$
    

Se $a<0$:

- il comportamento dipende dalla **parità di $n$**
    

Questi concetti verranno formalizzati quando parleremo di:

- limiti;
    
- successioni;
    
- esponenziali.
    

---

### **10. Collegamento con polinomi**

Ogni **polinomio** è una combinazione lineare di potenze di $x$:

$$  
P(x) = a_n x^n + a_{n-1}x^{n-1} + \dots + a_1 x + a_0  
$$

Quindi:

- capire le potenze significa capire la **struttura dei polinomi**;
    
- il grado di un polinomio è determinato dalla **massima potenza**.
    

---

### **11. Collegamento con lo studio del segno**

Il segno di $x^n$ dipende da:

- segno di $x$
    
- parità di $n$
    

Questo sarà la base per:

- disequazioni polinomiali;
    
- razionali;
    
- studio dei segni in Analisi.
    

---

### **12. False proprietà da riconoscere**

Le leggi delle potenze riguardano prodotti e quozienti, non somme. In generale:

$$
(a+b)^n\ne a^n+b^n
$$

Per esempio, $(1+1)^2=4$ mentre $1^2+1^2=2$. Analogamente:

$$
a^{m+n}=a^m\cdot a^n\ne a^m+a^n
$$

e il segno richiede le parentesi:

$$
(-a)^n=
\begin{cases}
a^n & n\text{ pari} \\
-a^n & n\text{ dispari}
\end{cases}
$$

Non è quindi vero che $(-a)^n=-a^n$ per ogni $n$.

### **13. Checklist finale (da Analisi 1)**

Devi saper fare **senza esitazione**:

1. Definire $a^n$ per $n$ intero
    
2. Gestire $a^0$ e $a^{-n}$
    
3. Usare correttamente:
    
    - $a^m a^n = a^{m+n}$
        
    - $\frac{a^m}{a^n} = a^{m-n}$
        
    - $(a^m)^n = a^{mn}$
        
4. Capire il ruolo della parità dell’esponente
    
5. Evitare errori di parentesi
    
6. Usare le potenze nello studio del segno
    
7. Collegare potenze, polinomi e comportamento globale
    

---

### **14. Ponte verso la prossima lezione**

Nella **UD2 L2** introdurremo:

- potenze con **esponente reciproco** ($a^{1/n}$);
    
- il collegamento con le **radici**;
    
- i problemi di dominio e di segno;
    
- il primo vero contatto con la **continuità**.
