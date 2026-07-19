# **M2 UD1 Lezione 3 - Operazioni insiemistiche**

### **1. Introduzione**

Le operazioni tra insiemi permettono di costruire nuovi insiemi a partire da insiemi dati. In Analisi sono usate per descrivere domini, soluzioni di sistemi di condizioni, intervalli esclusi e unioni di casi.

Le operazioni fondamentali sono:

- unione;
- intersezione;
- differenza;
- complementare;
- differenza simmetrica;
- unioni e intersezioni indicizzate;
- prodotto cartesiano.

---

### **2. Unione**

L’unione di due insiemi $A$ e $B$ è l’insieme degli elementi che appartengono ad almeno uno dei due insiemi:

$$
A\cup B=\{x: x\in A \text{ oppure } x\in B\}
$$

Esempio:

$$
\{1,2,3\}\cup\{3,4\}=\{1,2,3,4\}
$$

Con intervalli:

$$
(0,2)\cup(1,3)=(0,3)
$$

---

### **3. Intersezione**

L’intersezione di $A$ e $B$ è l’insieme degli elementi che appartengono a entrambi:

$$
A\cap B=\{x: x\in A \text{ e } x\in B\}
$$

Esempio:

$$
\{1,2,3\}\cap\{3,4\}=\{3\}
$$

Con intervalli:

$$
(0,2)\cap(1,3)=(1,2)
$$

> 📌 L’intersezione corrisponde al soddisfare simultaneamente due condizioni.

---

### **4. Insiemi disgiunti**

Due insiemi $A$ e $B$ sono **disgiunti** se:

$$
A\cap B=\varnothing
$$

Esempio:

$$
(0,1)\cap(2,3)=\varnothing
$$

Gli insiemi disgiunti non hanno elementi in comune.

---

### **5. Differenza**

La differenza tra $A$ e $B$ è l’insieme degli elementi che appartengono ad $A$ ma non a $B$:

$$
A\setminus B=\{x: x\in A \text{ e } x\notin B\}
$$

Esempio:

$$
\{1,2,3,4\}\setminus\{2,4\}=\{1,3\}
$$

Con intervalli:

$$
[0,3]\setminus\{1\}=[0,1)\cup(1,3]
$$

---

### **6. Complementare**

Se si fissa un insieme universo $U$, il complementare di $A$ è:

$$
A^c=U\setminus A
$$

Per esempio, se l’universo è $\mathbb{R}$ e:

$$
A=(0,+\infty)
$$

allora:

$$
A^c=(-\infty,0]
$$

Il complementare dipende dall’universo scelto.

> ⚠️ Senza specificare l’universo, il complementare può essere ambiguo.

---

### **7. Proprietà principali**

Le operazioni soddisfano proprietà analoghe alle operazioni logiche.

Commutatività:

$$
A\cup B=B\cup A
$$

$$
A\cap B=B\cap A
$$

Associatività:

$$
(A\cup B)\cup C=A\cup(B\cup C)
$$

$$
(A\cap B)\cap C=A\cap(B\cap C)
$$

Distributività:

$$
A\cap(B\cup C)=(A\cap B)\cup(A\cap C)
$$

$$
A\cup(B\cap C)=(A\cup B)\cap(A\cup C)
$$

---

### **8. Leggi di De Morgan**

Le leggi di De Morgan collegano complementare, unione e intersezione:

$$
(A\cup B)^c=A^c\cap B^c
$$

$$
(A\cap B)^c=A^c\cup B^c
$$

La prima dice: non appartenere ad $A\cup B$ significa non appartenere ad $A$ e non appartenere a $B$.

La seconda dice: non appartenere ad $A\cap B$ significa non appartenere ad almeno uno tra $A$ e $B$.

---

### **9. Differenza simmetrica**

La **differenza simmetrica** di $A$ e $B$ è l’insieme degli elementi che appartengono a uno solo dei due insiemi:

$$
A\triangle B=(A\setminus B)\cup(B\setminus A)
$$

Equivalentemente:

$$
A\triangle B=(A\cup B)\setminus(A\cap B)
$$

Esempio:

$$
\{1,2,3\}\triangle\{3,4,5\}=\{1,2,4,5\}
$$

L’elemento $3$ non compare perché appartiene a entrambi gli insiemi.

---

### **10. Famiglie indicizzate**

Quando si lavora con molti insiemi, invece di scrivere $A_1,A_2,A_3,\dots$ informalmente si introduce una **famiglia indicizzata**:

$$
(A_i)_{i\in I}
$$

dove $I$ è l’insieme degli indici.

L’unione indicizzata è:

$$
\bigcup_{i\in I}A_i=\{x:\exists i\in I \text{ tale che } x\in A_i\}
$$

L’intersezione indicizzata è:

$$
\bigcap_{i\in I}A_i=\{x:\forall i\in I,\ x\in A_i\}
$$

Esempio:

$$
A_n=\left(0,\frac{1}{n}\right),\qquad n\in\mathbb{N}^+
$$

Allora:

$$
\bigcup_{n\ge 1}A_n=(0,1)
$$

perché l’intervallo più grande è già $A_1=(0,1)$, mentre:

$$
\bigcap_{n\ge 1}A_n=\varnothing
$$

perché nessun $x>0$ è minore di $\frac{1}{n}$ per ogni $n$ naturale positivo.

> 💡 Le famiglie indicizzate preparano il linguaggio di successioni di insiemi, coperture e intervalli annidati.

---

### **11. Prodotto cartesiano**

Il prodotto cartesiano di $A$ e $B$ è l’insieme delle coppie ordinate:

$$
A\times B=\{(a,b): a\in A,\ b\in B\}
$$

Esempio:

$$
\{1,2\}\times\{a,b\}=\{(1,a),(1,b),(2,a),(2,b)\}
$$

In Analisi, il piano cartesiano è:

$$
\mathbb{R}^2=\mathbb{R}\times\mathbb{R}
$$

e lo spazio tridimensionale è:

$$
\mathbb{R}^3=\mathbb{R}\times\mathbb{R}\times\mathbb{R}
$$

---

### **12. Dimostrare identità elemento per elemento**

Le identità insiemistiche non si giustificano solo con un disegno: si dimostrano prendendo un elemento generico $x$ e traducendo l’appartenenza in proposizioni logiche.

Dimostriamo la prima legge di De Morgan:

$$
(A\cup B)^c=A^c\cap B^c
$$

Fissiamo un universo $U$ e prendiamo $x\in U$. Allora:

$$
x\in(A\cup B)^c
$$

equivale a:

$$
x\notin A\cup B
$$

che equivale a:

$$
\neg(x\in A \vee x\in B)
$$

Per De Morgan logico:

$$
\neg(x\in A)\wedge\neg(x\in B)
$$

cioè:

$$
x\in A^c \wedge x\in B^c
$$

quindi:

$$
x\in A^c\cap B^c
$$

Poiché le due condizioni sono equivalenti per ogni $x\in U$, gli insiemi sono uguali.

<!-- TODO FIGURA: diagramma di Venn con due insiemi A e B dentro l'universo U, evidenziando separatamente unione, intersezione, differenza, complementare e differenza simmetrica; obiettivo didattico: collegare le formule insiemistiche alla regione visuale senza sostituire la dimostrazione elemento per elemento. -->

---

### **13. Esempio: dominio con più condizioni**

Consideriamo:

$$
\sqrt{x-1}+\log(5-x)
$$

La radice richiede:

$$
x-1\ge 0
$$

quindi:

$$
x\in[1,+\infty)
$$

Il logaritmo richiede:

$$
5-x>0
$$

quindi:

$$
x\in(-\infty,5)
$$

Il dominio è l’intersezione:

$$
[1,+\infty)\cap(-\infty,5)=[1,5)
$$

---

### **14. Esercizi rappresentativi**

1. Siano:

$$
A=\{1,2,3\},\qquad B=\{3,4\}
$$

Calcolare $A\cup B$, $A\cap B$, $A\setminus B$, $B\setminus A$ e $A\triangle B$.

Soluzione:

$$
A\cup B=\{1,2,3,4\}
$$

$$
A\cap B=\{3\}
$$

$$
A\setminus B=\{1,2\},\qquad B\setminus A=\{4\}
$$

$$
A\triangle B=\{1,2,4\}
$$

2. Se $A=\{a,b\}$, determinare $\mathcal{P}(A)$ e poi $\mathcal{P}(A)\times A$.

Si ha:

$$
\mathcal{P}(A)=\{\varnothing,\{a\},\{b\},\{a,b\}\}
$$

Quindi $\mathcal{P}(A)\times A$ contiene $8$ coppie ordinate, perché:

$$
|\mathcal{P}(A)|\cdot |A|=4\cdot 2=8
$$

3. Dimostrare:

$$
A\setminus(B\cup C)=(A\setminus B)\cap(A\setminus C)
$$

Prendiamo $x$ generico. Allora:

$$
x\in A\setminus(B\cup C)
$$

equivale a:

$$
x\in A \wedge x\notin B\cup C
$$

cioè:

$$
x\in A \wedge x\notin B \wedge x\notin C
$$

equivalentemente:

$$
x\in A\setminus B \wedge x\in A\setminus C
$$

quindi:

$$
x\in(A\setminus B)\cap(A\setminus C)
$$

La catena è reversibile, perciò gli insiemi sono uguali.

---

### **15. Riepilogo**

> ✅ Le operazioni insiemistiche traducono in linguaggio formale le parole “oppure”, “e”, “tranne”, “fuori da”.

Punti essenziali:

- $A\cup B$ raccoglie gli elementi che stanno in almeno uno dei due insiemi;
- $A\cap B$ raccoglie gli elementi comuni;
- $A\setminus B$ rimuove da $A$ gli elementi di $B$;
- il complementare dipende dall’universo;
- $A\triangle B$ conserva gli elementi che appartengono a uno solo tra $A$ e $B$;
- $\bigcup_{i\in I}A_i$ usa un quantificatore esistenziale, $\bigcap_{i\in I}A_i$ usa un quantificatore universale;
- $A\times B$ contiene coppie ordinate.
