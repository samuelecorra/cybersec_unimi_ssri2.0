# **M3 UD1 Lezione 1 - Insiemi finiti**

### **1. Perché formalizzare il conteggio**

Contare un insieme non significa soltanto pronunciarne gli elementi in un certo ordine: significa associare a ciascun elemento una e una sola posizione. La nozione di biiezione studiata in [M2 UD2 Lezione 2](../../M02_Insiemistica%20e%20Relazioni/UD2%20-%20Applicazioni/L2%20-%20Caratteristiche%20delle%20applicazioni.md) rende questa idea indipendente dal modo in cui gli elementi sono scritti.

Adotteremo in tutto il modulo la convenzione:

$$
\mathbb{N}=\{0,1,2,\ldots\}.
$$

Per $n\in\mathbb{N}$ poniamo:

$$
I_n=\{0,1,\ldots,n-1\},
$$

con $I_0=\varnothing$.

> 📌 Usare $I_n$ permette di trattare l'insieme vuoto senza eccezioni: $I_n$ contiene esattamente $n$ elementi anche per $n=0$.

### **2. Definizione formale e cardinalità**

Un insieme $A$ è **finito** se esiste $n\in\mathbb{N}$ e una biiezione:

$$
f:I_n\to A.
$$

Il naturale $n$ è unico. Si chiama **cardinalità** di $A$ e si scrive principalmente:

$$
|A|=n.
$$

Sono diffuse anche le notazioni:

$$
\#A=n,
\qquad
\operatorname{card}(A)=n.
$$

In questo corso useremo $|A|$; il contesto distinguerà la cardinalità dal valore assoluto di un numero.

#### **2.1 Perché il numero è unico**

Usiamo il lemma finito seguente: se $m<n$, non esiste un'iniezione $I_n\to I_m$.

Il lemma si dimostra per induzione su $m$. Per $m=0$ non esiste neppure una funzione da $I_n\neq\varnothing$ a $I_0=\varnothing$. Supponiamo il risultato vero per $m$ e consideriamo un'ipotetica iniezione $f:I_n\to I_{m+1}$ con $n>m+1$. Se $m$ non appartiene all'immagine, $f$ avrebbe codominio effettivo in $I_m$, contro l'ipotesi induttiva. Se $f(x)=m$ per un unico $x$, rimuovendo $x$ otteniamo un'iniezione da $I_n\setminus\{x\}$ in $I_m$. Il primo insieme è equipotente a $I_{n-1}$ mediante $u:I_{n-1}\to I_n\setminus\{x\}$, con $u(t)=t$ per $t<x$ e $u(t)=t+1$ per $t\geq x$; ma $n-1>m$, ancora contro l'ipotesi induttiva.

Supponiamo ora che $A$ sia in biiezione sia con $I_m$ sia con $I_n$. Componendo una biiezione con l'inversa dell'altra si ottiene una biiezione $I_m\to I_n$. Se $m<n$, la sua inversa sarebbe un'iniezione $I_n\to I_m$, impossibile per il lemma; analogamente non può valere $n<m$. Deve quindi essere:

$$
m=n.
$$

La cardinalità non dipende dunque dall'ordine scelto per contare.

### **3. Casi fondamentali**

Si ha:

$$
|\varnothing|=0,
\qquad
|\{a\}|=1.
$$

Se:

$$
A=\{2,4,6\},
$$

la funzione $f:I_3\to A$ definita da $f(0)=2$, $f(1)=4$, $f(2)=6$ è biiettiva; pertanto $|A|=3$.

In un insieme ordine e ripetizioni non contano:

$$
\{a,b,c\}=\{c,a,b\},
$$

e:

$$
\{a,a,b,b,c\}=\{a,b,c\}.
$$

Entrambi gli insiemi hanno cardinalità $3$. Diversamente, la lista $(a,a,b,b,c)$ ha cinque posizioni e la successione $(a_n)_{n\in\mathbb N}$ ha un insieme infinito di indici, anche se l'insieme dei suoi valori può essere finito.

> ⚠️ Non si contano i simboli scritti: si contano gli elementi distinti dell'insieme.

### **4. Equipotenza nel caso finito**

Due insiemi $A$ e $B$ sono **equipotenti**, e si scrive:

$$
A\sim B,
$$

se esiste una biiezione $A\to B$. Per insiemi finiti vale:

$$
A\sim B
\iff
|A|=|B|.
$$

**Dimostrazione.** Se $|A|=|B|=n$, scegliamo biiezioni $u:I_n\to A$ e $v:I_n\to B$. La composizione $v\circ u^{-1}:A\to B$ è biiettiva. Viceversa, se $h:A\to B$ è biiettiva e $u:I_n\to A$ enumera $A$, allora $h\circ u:I_n\to B$ è una biiezione; per l'unicità della cardinalità, $|B|=n=|A|$. $\square$

### **5. Sottoinsiemi di un insieme finito**

Se $A$ è finito e $B\subseteq A$, allora $B$ è finito e:

$$
|B|\leq |A|.
$$

Se inoltre $B\subsetneq A$, allora:

$$
|B|<|A|.
$$

**Idea della dimostrazione.** Trasportando $B$ attraverso una biiezione $I_n\to A$, si ottiene un sottoinsieme di $I_n$. Un'induzione su $n$ mostra che tale sottoinsieme contiene al più $n$ elementi; se è proprio, ne contiene al più $n-1$.

Questa proprietà caratterizza il comportamento finito e fallirà per molti insiemi infiniti.

### **6. Operazioni finite: primi risultati**

Se $A$ e $B$ sono finiti, allora sono finiti anche $A\cup B$, $A\cap B$, $A\setminus B$ e $A\times B$. In particolare:

$$
|A\times B|=|A|\cdot|B|,
$$

e:

$$
|A\cup B|=|A|+|B|-|A\cap B|.
$$

Se $A\cap B=\varnothing$, la seconda formula diventa:

$$
|A\cup B|=|A|+|B|.
$$

Le dimostrazioni combinatorie, l'estensione a tre insiemi, il conteggio delle funzioni e il principio dei cassetti sono sviluppati nella [lezione successiva](L1A%20-%20Principi%20di%20conteggio%20e%20principio%20dei%20cassetti.md).

### **7. Esempio trasversale**

Siano:

$$
A=\{1,2,3,4,5\},
\qquad
B=\{2,4,6\}.
$$

Allora:

$$
A\cap B=\{2,4\},
\qquad
A\cup B=\{1,2,3,4,5,6\}.
$$

La formula di inclusione-esclusione fornisce:

$$
|A\cup B|=5+3-2=6.
$$

Inoltre:

$$
|A\times B|=5\cdot3=15.
$$

### **8. Errori frequenti**

- Scrivere $|\{1,1,2,3\}|=4$: le ripetizioni non creano nuovi elementi.
- Dedurre $A=B$ da $|A|=|B|$: la cardinalità uguale implica equipotenza, non uguaglianza insiemistica.
- Confondere $A\in\mathcal P(B)$ con $A\subseteq B$: sono equivalenti, ma $A\in B$ è un'affermazione diversa.
- Usare $|A|\leq|B|$ come semplice confronto numerico prima di aver stabilito che gli insiemi sono finiti; per cardinalità arbitrarie la relazione sarà definita mediante iniezioni.

### **9. Esercizi**

1. Determinare la cardinalità di $\{0,0,\{0\},\varnothing,\{\varnothing\}\}$, distinguendo con cura gli elementi uguali.
2. Costruire due biiezioni diverse tra $I_4$ e $\{a,b,c,d\}$ e spiegare perché producono la stessa cardinalità.
3. Dimostrare che, per $A$ finito e $a\notin A$, vale $|A\cup\{a\}|=|A|+1$.
4. Siano $|A|=12$, $|B|=9$ e $|A\cap B|=4$. Calcolare $|A\cup B|$, $|A\setminus B|$ e $|A\times B|$.
5. Stabilire se può esistere una funzione iniettiva $I_7\to I_5$ e motivare la risposta senza limitarsi a un disegno.

### **10. Riepilogo**

> ✅ Un insieme è finito quando è in biiezione con un unico $I_n$; quel naturale è la sua cardinalità.

- $I_0=\varnothing$ rende uniforme la definizione;
- la cardinalità è indipendente dall'ordine e ignora le ripetizioni;
- nel caso finito equipotenza e uguaglianza della cardinalità coincidono;
- un sottoinsieme proprio di un insieme finito ha cardinalità strettamente minore;
- unioni e prodotti finiti preparano i principi generali di conteggio.
