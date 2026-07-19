# **M1 UD0 Lezione 2 - Fattoriale e coefficienti binomiali**

### **1. Introduzione**

Il fattoriale e i coefficienti binomiali sono strumenti discreti fondamentali. Compaiono in combinatoria, probabilità, sviluppi di potenze, serie, successioni con termini ricorsivi e formule di Taylor.

Il loro ruolo in Analisi 1 è duplice:

- permettono di scrivere formule compatte;
- permettono di riconoscere strutture ricorrenti, soprattutto nei rapporti tra termini consecutivi.

### **2. Definizione di fattoriale**

Per $n\in\mathbb{N}$ con $n\ge 1$, il **fattoriale** di $n$ è:

$$
n!=1\cdot 2\cdot 3\cdots n.
$$

In notazione di produttoria:

$$
n!=\prod_{k=1}^{n} k.
$$

Esempi:

$$
1!=1,\quad 2!=2,\quad 3!=6,\quad 4!=24,\quad 5!=120.
$$

### **3. La convenzione $0!=1$**

Per convenzione:

$$
0!=1.
$$

Questa scelta non è arbitraria: rende coerenti molte formule. Per esempio, la relazione ricorsiva:

$$
n!=n\cdot(n-1)!
$$

deve valere anche per $n=1$. Infatti:

$$
1!=1\cdot 0!.
$$

Poiché $1!=1$, deve essere:

$$
0!=1.
$$

> ⚠️ $0!$ non vale $0$. Vale $1$.

### **4. Relazione ricorsiva**

Per ogni $n\ge 1$ vale:

$$
n!=n\cdot(n-1)!.
$$

Per esempio:

$$
6!=6\cdot 5!.
$$

Questa forma è spesso più utile della definizione estesa, perché permette di semplificare rapporti tra fattoriali.

### **5. Interpretazione combinatoria**

Il numero $n!$ conta in quanti modi si possono ordinare $n$ oggetti distinti.

Per esempio, con tre oggetti $A,B,C$ ci sono:

$$
3!=6
$$

ordinamenti:

$$
ABC,\quad ACB,\quad BAC,\quad BCA,\quad CAB,\quad CBA.
$$

Il ragionamento è:

- per il primo posto ci sono $3$ scelte;
- per il secondo posto restano $2$ scelte;
- per il terzo posto resta $1$ scelta.

Quindi:

$$
3\cdot 2\cdot 1=3!.
$$

In generale:

$$
n\cdot(n-1)\cdot\dots\cdot 2\cdot 1=n!.
$$

### **6. Crescita del fattoriale**

Il fattoriale cresce molto rapidamente:

$$
5!=120,\quad 10!=3628800.
$$

In Analisi 1 questa crescita diventa importante nello studio di successioni e serie. Per esempio, termini come:

$$
\frac{n!}{n^n}
$$

oppure:

$$
\frac{x^n}{n!}
$$

richiedono di capire come il fattoriale si confronta con potenze ed esponenziali. Le stime più raffinate, come la formula di Stirling, saranno eventualmente trattate come approfondimento; qui è sufficiente saper manipolare correttamente i fattoriali.

### **7. Semplificazioni tra fattoriali**

I fattoriali si semplificano espandendo solo la parte necessaria.

Esempio:

$$
\frac{n!}{(n-2)!}
$$

con $n\ge 2$. Scriviamo:

$$
n!=n(n-1)(n-2)!.
$$

Quindi:

$$
\frac{n!}{(n-2)!}
=
\frac{n(n-1)(n-2)!}{(n-2)!}
=
n(n-1).
$$

Altro esempio:

$$
\frac{(n+3)!}{n!}.
$$

Poiché:

$$
(n+3)!=(n+3)(n+2)(n+1)n!,
$$

si ottiene:

$$
\frac{(n+3)!}{n!}=(n+3)(n+2)(n+1).
$$

> ⚠️ Non si sviluppa mai tutto il fattoriale se basta isolare i fattori che non si cancellano.

### **8. Errori comuni con i fattoriali**

Il fattoriale non è lineare. In generale:

$$
(n+m)!\neq n!+m!.
$$

Per esempio:

$$
(2+3)!=5!=120,
$$

mentre:

$$
2!+3!=2+6=8.
$$

Inoltre:

$$
(2n)!\neq 2n!.
$$

Per esempio:

$$
(2\cdot 3)!=6!=720,
$$

mentre:

$$
2\cdot 3!=12.
$$

### **9. Coefficienti binomiali**

Per $n,k\in\mathbb{N}$ con $0\le k\le n$, il **coefficiente binomiale** si definisce come:

$$
\binom{n}{k}=\frac{n!}{k!(n-k)!}.
$$

Si legge “$n$ su $k$”.

Il dominio naturale della definizione è:

$$
0\le k\le n.
$$

In questa repository useremo anche la convenzione:

$$
\binom{n}{k}=0
\quad \text{se } k<0 \text{ oppure } k>n,
$$

quando questa convenzione rende più compatte alcune formule.

> 📌 $\binom{n}{k}$ conta quanti sottoinsiemi di $k$ elementi si possono scegliere da un insieme di $n$ elementi.

### **10. Interpretazione combinatoria**

Supponiamo di avere $n$ oggetti distinti e di voler scegliere $k$ oggetti senza considerare l'ordine.

Se l'ordine contasse, avremmo:

$$
n(n-1)\cdots(n-k+1)
$$

scelte ordinate. Questa quantità si può scrivere come:

$$
\frac{n!}{(n-k)!}.
$$

Ma ogni gruppo di $k$ oggetti viene contato $k!$ volte, una per ogni ordinamento possibile dei suoi elementi. Quindi il numero di scelte non ordinate è:

$$
\frac{1}{k!}\cdot\frac{n!}{(n-k)!}
=
\frac{n!}{k!(n-k)!}
=
\binom{n}{k}.
$$

### **11. Valori ai bordi**

Per ogni $n\in\mathbb{N}$:

$$
\binom{n}{0}=1
$$

e

$$
\binom{n}{n}=1.
$$

Infatti c'è un solo modo di scegliere zero elementi: scegliere l'insieme vuoto. C'è anche un solo modo di scegliere tutti gli $n$ elementi: prenderli tutti.

Inoltre:

$$
\binom{n}{1}=n
$$

e

$$
\binom{n}{n-1}=n.
$$

### **12. Simmetria**

Per ogni $0\le k\le n$ vale:

$$
\binom{n}{k}=\binom{n}{n-k}.
$$

Dimostrazione algebrica:

$$
\binom{n}{n-k}
=
\frac{n!}{(n-k)!(n-(n-k))!}
=
\frac{n!}{(n-k)!k!}
=
\binom{n}{k}.
$$

Interpretazione combinatoria: scegliere $k$ elementi da tenere equivale a scegliere gli $n-k$ elementi da escludere.

### **13. Relazione di Pascal**

Per $1\le k\le n$ vale:

$$
\binom{n+1}{k}
=
\binom{n}{k-1}+\binom{n}{k}.
$$

Questa è la **relazione di Pascal**.

Dimostrazione algebrica:

$$
\binom{n}{k-1}+\binom{n}{k}
=
\frac{n!}{(k-1)!(n-k+1)!}+\frac{n!}{k!(n-k)!}.
$$

Portiamo a denominatore comune $k!(n-k+1)!$:

$$
\frac{kn!}{k!(n-k+1)!}+\frac{(n-k+1)n!}{k!(n-k+1)!}.
$$

Sommiamo i numeratori:

$$
\frac{(k+n-k+1)n!}{k!(n-k+1)!}
=
\frac{(n+1)n!}{k!(n-k+1)!}.
$$

Poiché $(n+1)n!=(n+1)!$, otteniamo:

$$
\frac{(n+1)!}{k!((n+1)-k)!}
=
\binom{n+1}{k}.
$$

### **14. Triangolo di Pascal**

La relazione di Pascal permette di costruire il triangolo dei coefficienti binomiali.

<!-- TODO FIGURA:
Triangolo di Pascal con righe da n=0 a n=6.
Evidenziare che ogni elemento interno è la somma dei due elementi sopra di lui
e che la riga n contiene i coefficienti dello sviluppo di (a+b)^n.
Obiettivo didattico: collegare relazione di Pascal, coefficienti binomiali e sviluppi di Newton.
-->

Le prime righe sono:

$$
1
$$

$$
1\quad 1
$$

$$
1\quad 2\quad 1
$$

$$
1\quad 3\quad 3\quad 1
$$

$$
1\quad 4\quad 6\quad 4\quad 1.
$$

### **15. Somma di una riga**

Per ogni $n\in\mathbb{N}$ vale:

$$
\sum_{k=0}^{n}\binom{n}{k}=2^n.
$$

Interpretazione combinatoria: un insieme con $n$ elementi ha $2^n$ sottoinsiemi. A sinistra contiamo gli stessi sottoinsiemi raggruppandoli in base al numero $k$ di elementi:

$$
\binom{n}{0}+\binom{n}{1}+\dots+\binom{n}{n}.
$$

Quindi:

$$
\sum_{k=0}^{n}\binom{n}{k}=2^n.
$$

Questa identità si ritroverà anche come caso particolare del binomio di Newton ponendo $a=1$ e $b=1$.

### **16. Esempi di calcolo**

Calcoliamo:

$$
\binom{6}{2}.
$$

Per definizione:

$$
\binom{6}{2}
=
\frac{6!}{2!4!}
=
\frac{6\cdot 5\cdot 4!}{2\cdot 1\cdot 4!}
=
15.
$$

Calcoliamo:

$$
\binom{8}{5}.
$$

Usiamo la simmetria:

$$
\binom{8}{5}=\binom{8}{3}.
$$

Quindi:

$$
\binom{8}{3}
=
\frac{8!}{3!5!}
=
\frac{8\cdot 7\cdot 6}{3\cdot 2\cdot 1}
=
56.
$$

### **17. Errori frequenti**

#### **17.1 Trattare $\binom{n}{k}$ come una frazione**

Il simbolo:

$$
\binom{n}{k}
$$

non è una frazione $\frac{n}{k}$. È un numero intero definito tramite fattoriali.

Per esempio:

$$
\binom{6}{2}=15,
$$

mentre:

$$
\frac{6}{2}=3.
$$

#### **17.2 Dimenticare i vincoli sugli indici**

La formula:

$$
\binom{n}{k}=\frac{n!}{k!(n-k)!}
$$

richiede $0\le k\le n$. Se $k>n$, il fattoriale $(n-k)!$ non ha senso nei naturali. Per questo, quando serve, adottiamo la convenzione:

$$
\binom{n}{k}=0
\quad \text{se } k>n.
$$

#### **17.3 Dimenticare $0!=1$**

Senza la convenzione $0!=1$, non avremmo:

$$
\binom{n}{0}=1
$$

e

$$
\binom{n}{n}=1.
$$

### **18. Riepilogo**

> ✅ Il fattoriale conta ordinamenti; il coefficiente binomiale conta scelte non ordinate.

Punti fondamentali:

- $n!=1\cdot 2\cdots n$;
- $0!=1$;
- $n!=n(n-1)!$;
- $\binom{n}{k}=\frac{n!}{k!(n-k)!}$ per $0\le k\le n$;
- $\binom{n}{k}=\binom{n}{n-k}$;
- $\binom{n+1}{k}=\binom{n}{k-1}+\binom{n}{k}$;
- $\sum_{k=0}^{n}\binom{n}{k}=2^n$;
- i coefficienti binomiali saranno i coefficienti dello sviluppo di $(a+b)^n$.
