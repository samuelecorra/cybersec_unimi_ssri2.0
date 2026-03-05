# **M1 UD2 Lezione 2 - Potenze con esponente reciproco**

### **1. Introduzione: perché servono gli esponenti reciproci**

Dopo aver definito le potenze con **esponente intero**, nasce una domanda naturale e inevitabile:

> Che significato ha scrivere $a^{1/n}$?

Questa lezione risponde a questa domanda in modo **rigoroso**, ma con una visione già **da Analisi 1**, non da calcolo meccanico.  
Gli esponenti reciproci sono il **ponte concettuale** tra:

- potenze,
    
- **radici**,
    
- dominio delle funzioni,
    
- continuità,
    
- futuri esponenti razionali e reali.
    

---

## **2. Motivazione strutturale**

Vogliamo che le **proprietà delle potenze** restino valide anche per nuovi esponenti.

In particolare vogliamo che continui a valere:

$$  
(a^m)^n = a^{mn}  
$$

Se scegliamo $m=\frac{1}{n}$, allora dovrebbe valere:

$$  
\left(a^{1/n}\right)^n = a  
$$

Questa non è una scelta arbitraria: è **l’unica definizione coerente**.

---

## **3. Definizione di potenza con esponente reciproco**

Sia $n \in \mathbb{N}$ con $n \ge 2$.

Per **$a \ge 0$**, si definisce:

$$  
a^{1/n} = \sqrt[n]{a}  
$$

cioè:

> $a^{1/n}$ è il **numero non negativo** che elevato alla $n$ dà $a$.

Formalmente:

$$  
\left(a^{1/n}\right)^n = a  
$$

---

## **4. Perché si richiede $a \ge 0$ (punto cruciale)**

Il vincolo sul segno di $a$ **non è una scelta didattica**, ma una necessità matematica.

---

### **4.1 Caso $n$ pari**

Se $n$ è **pari**, allora per ogni $x \in \mathbb{R}$:

$$  
x^n \ge 0  
$$

Quindi l’equazione:

$$  
x^n = a  
$$

- **non ha soluzioni reali** se $a<0$;
    
- ha **una sola soluzione reale non negativa** se $a \ge 0$.
    

Esempio:

- $\sqrt{4} = 2$ (non $-2$)
    
- $\sqrt{-1}$ **non esiste** in $\mathbb{R}$
    

Per questo:

$$  
a^{1/n} \quad \text{è definito solo per } a \ge 0 \text{ se } n \text{ è pari}.  
$$

---

### **4.2 Caso $n$ dispari**

Se $n$ è **dispari**, allora:

- $x^n$ può essere positivo, nullo o negativo;
    
- l’equazione $x^n = a$ ha **sempre una soluzione reale unica** per ogni $a \in \mathbb{R}$.
    

Esempi:

- $\sqrt[3]{8} = 2$
    
- $\sqrt[3]{-8} = -2$
    

Tuttavia, **per uniformità di definizione delle potenze**, in Analisi 1 si introduce comunque:

$$  
a^{1/n} = \sqrt[n]{a}  
$$

con la consapevolezza che:

- per $n$ dispari la definizione è estendibile a tutto $\mathbb{R}$;
    
- per $n$ pari il dominio resta $a \ge 0$.
    

---

## **5. Proprietà fondamentali (con attenzione al dominio)**

Le proprietà delle potenze **continuano a valere**, ma **solo quando hanno senso**.

---

### **5.1 Potenza di una potenza**

Per $a \ge 0$:

$$  
\left(a^{1/n}\right)^n = a  
$$

e anche:

$$  
\left(a^n\right)^{1/n} = a \quad \text{solo se } a \ge 0  
$$

Questo è un punto delicato:

$$  
\sqrt{a^2} = |a|  
$$

non $a$.

---

### **5.2 Prodotto di potenze con lo stesso esponente reciproco**

Se $a \ge 0$, $b \ge 0$:

$$  
a^{1/n} \cdot b^{1/n} = (ab)^{1/n}  
$$

Esempio:

$$  
\sqrt{2}\cdot\sqrt{8} = \sqrt{16} = 4  
$$

---

### **5.3 Quoziente**

Se $a \ge 0$, $b > 0$:

$$  
\frac{a^{1/n}}{b^{1/n}} = \left(\frac{a}{b}\right)^{1/n}  
$$

---

## **6. Collegamento con le radici**

In pratica:

- $a^{1/2} = \sqrt{a}$
    
- $a^{1/3} = \sqrt[3]{a}$
    
- $a^{1/n} = \sqrt[n]{a}$
    

Ma la **notazione con esponente** è concettualmente più potente perché:

- si generalizza agli esponenti razionali;
    
- mantiene valide le leggi delle potenze;
    
- è quella usata in Analisi.
    

---

## **7. Interpretazione grafica**

La funzione:

$$  
f(x) = x^{1/n}  
$$

(per $n$ pari) è definita per $x \ge 0$ e:

- è crescente;
    
- passa per $(0,0)$ e $(1,1)$;
    
- “appiattisce” rispetto alla retta $y=x$.
    

Queste osservazioni qualitative torneranno nello studio:

- della monotonia;
    
- dei limiti;
    
- delle funzioni inverse.
    

---

## **8. Errori concettuali comuni (da evitare)**

1. Scrivere $\sqrt{a^2} = a$ invece di $|a|$
    
2. Applicare proprietà senza controllare il dominio
    
3. Dimenticare che $a^{1/n}$ **è per definizione non negativo** se $n$ è pari
    
4. Confondere $a^{1/n}$ con $a/n$ (errore gravissimo)
    

---

## **9. Collegamento con il dominio delle funzioni**

Ogni volta che compare un’espressione del tipo:

$$  
(x - \alpha)^{1/n}  
$$

con $n$ pari, devi imporre:

$$  
x - \alpha \ge 0  
$$

Questo sarà centrale quando studieremo:

- domini;
    
- funzioni irrazionali;
    
- limiti con radicali.
    

---

## **10. Preparazione alla prossima lezione**

Questa lezione prepara direttamente:

- **esponenti razionali** ($a^{p/q}$);
    
- semplificazioni avanzate;
    
- studio dei segni;
    
- continuità delle funzioni potenza.
    

---

## **11. Checklist finale (da Analisi 1)**

Devi saper fare:

1. Definire $a^{1/n}$ in modo coerente
    
2. Capire il ruolo della parità di $n$
    
3. Collegare potenze e radici
    
4. Applicare le proprietà **solo se il dominio lo consente**
    
5. Riconoscere immediatamente i vincoli $a \ge 0$
    
6. Usare $a^{1/n}$ come base per esponenti più generali
    

---

### **12. Ponte naturale**

Nella prossima lezione (**UD2 L3**) introdurremo:

- potenze con **esponente razionale** $a^{p/q}$;
    
- collegamento definitivo tra potenze e radici;
    
- dominio e segno in forma generale;
    
- semplificazioni strutturate.