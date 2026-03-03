## **Lezione 1: Introduzione agli integrali definiti**

---
### **1. Idea intuitiva di integrale**

Sia data una funzione $f(x)$ definita su un intervallo $[a,b]$.

L’**integrale definito** di $f(x)$ tra $a$ e $b$ si interpreta **geometricamente** come l’**area con segno** della regione di piano compresa tra:

- il grafico di $f(x)$
    
- l’asse $x$
    
- le rette verticali $x=a$ e $x=b$

e si indica con:

$$

\int_a^b f(x),dx

$$

---
### **2. Perché “area con segno”**

- Se $f(x) \ge 0$ in $[a,b]$, l’integrale coincide con l’area geometrica.
    
- Se $f(x) \le 0$ in $[a,b]$, l’integrale restituisce **l’area cambiata di segno**.
    
- Se $f(x)$ cambia segno, l’integrale somma **algebricamente** le aree sopra e sotto l’asse $x$.

👉 L’integrale **non è sempre un’area geometrica**, ma **un’area orientata**.

---
### **3. Terminologia della notazione**

Nella scrittura

$$

\int_a^b f(x),dx

$$

- $[a,b]$ → **intervallo (o dominio) di integrazione**
    
- $f(x)$ → **funzione integranda**
    
- $dx$ → indica la **variabile rispetto a cui si integra**

---
### **4. Integrale di una funzione costante**

Sia $f(x)=k$ costante su $[a,b]$.

L’integrale è definito come:

$$

\int_a^b k,dx = k(b-a)

$$

Geometricamente:

- $b-a$ è la base
    
- $k$ è l’altezza (con segno)

👉 è l’area con segno di un rettangolo.

---
### **5. Funzioni a scala (costanti a tratti)**

Una **funzione a scala** è una funzione che:

- assume valori costanti $k_1, k_2, \dots, k_n$
    
- su sottointervalli consecutivi di $[a,b]$

Suddividendo l’intervallo:
$$

a=x_0 < x_1 < x_2 < \dots < x_n=b

$$
l’integrale è definito come:

$$

\int_a^b f(x),dx = \sum_{i=1}^{n} k_i (x_i - x_{i-1})

$$

cioè **somma algebrica delle aree dei rettangoli**.

---
### **6. Il problema generale: funzioni non costanti**

Se $f(x)$ **non è costante**, il sottografico non è un rettangolo ma una regione “curva”.

Non esiste una formula diretta per l’area → serve una **definizione limite**.

---
### **7. Approssimazione per eccesso (integrale superiore)**

Si considerano funzioni a scala $h(x)$ tali che:

$$

h(x) \ge f(x) \quad \forall x \in [a,b]

$$
Ogni integrale:

$$

\int_a^b h(x),dx

$$

fornisce una **sovrastima** dell’area reale.

L’insieme di tutte queste sovrastime ha un **estremo inferiore**.

---
### **8. Approssimazione per difetto (integrale inferiore)**

Si considerano funzioni a scala $g(x)$ tali che:

$$

g(x) \le f(x) \quad \forall x \in [a,b]

$$

Ogni integrale:
$$

\int_a^b g(x),dx

$$

fornisce una **sottostima** dell’area reale.

L’insieme di tutte queste sottostime ha un **estremo superiore**.

---
### **9. Definizione di funzione integrabile**

Se:
$$
\sup\left\{\int_a^b g(x)\,dx \;:\; g \le f \right\}
=
\inf\left\{\int_a^b h(x)\,dx \;:\; h \ge f \right\}.
$$

allora la funzione $f(x)$ è **integrabile** su $[a,b]$

e il valore comune è:
$$

\int_a^b f(x),dx

$$

Questa è la **definizione rigorosa (di Riemann)** di integrale definito.

---
### **10. Calcolo pratico degli integrali**

Come per le derivate, **non si usa quasi mai la definizione**.

Si usa il **Teorema Fondamentale del Calcolo Integrale**:

> Se $F(x)$ è una primitiva di $f(x)$ su $[a,b]$, allora:
> $$
\int_a^b f(x),dx = F(b) - F(a)
$$

---
### **11. Procedura operativa standard**

1. Trovare una **primitiva** $F(x)$ di $f(x)$
    
2. Calcolare $F(b)$
    
3. Calcolare $F(a)$
    
4. Sottrarre: $F(b)-F(a)$
---

### **12. Esempio**

Calcolare:

$$

\int_0^5 3x^2,dx

$$

Primitiva:

$$

F(x)=x^3

$$

Valutazione agli estremi:

$$

\left[ x^3 \right]_0^5 = 5^3 - 0^3 = 125

$$

---
### **13. Punto chiave da fissare**

- L’integrale **nasce come area**
    
- Si **calcola tramite primitive**
    
- Il vero problema tecnico è **trovare la primitiva**

👉 ed è **esattamente** ciò che faremo nel prossimo modulo.