# **M1 UD1 Lezione 2 - Il principio di induzione matematica**

### **1. Perché serve l'induzione**

L'insieme $\mathbb{N}$ ha due caratteristiche strutturali fondamentali che rendono necessario un metodo di dimostrazione speciale:

- È **infinito**: non si può verificare una proprietà per ogni singolo elemento uno per uno
- È **discreto**: tra $n$ e $n+1$ non ci sono altri naturali — questa caratteristica è proprio ciò che rende l'induzione possibile

> 💡 L'idea chiave: se una proprietà vale per $0$ e, ogni volta che vale per $n$, vale anche per $n+1$, allora vale per tutti i naturali. È come una fila di domino: basta abbattere il primo e sapere che ogni tessera fa cadere quella successiva.

---

### **2. Induzione empirica ≠ dimostrazione matematica**

Trovare una regolarità su molti casi non costituisce una dimostrazione. Esistono celebri controesempi:

**Controesempio 1:** La formula $f(n) = n^2 + n + 41$

Per $n = 0, 1, 2, \ldots, 39$ il valore $f(n)$ è sempre primo. Ma per $n = 40$:

$$f(40) = 40^2 + 40 + 41 = 40(40 + 1) + 41 = 40 \cdot 41 + 41 = 41 \cdot 41 = 41^2$$

che è chiaramente composto.

**Controesempio 2:** La congettura che $2^n + 1$ sia sempre primo.

- $n = 0$: $2$, primo ✓
- $n = 1$: $3$, primo ✓
- $n = 2$: $5$, primo ✓
- $n = 3$: $9 = 3^2$, **composto** ✗

> ⚠️ L'induzione empirica (osservare che qualcosa funziona per molti casi) non è una dimostrazione matematica. Servono prove formali per ogni $n$.

---

### **3. Prima forma del principio di induzione**

> 📌 **Primo principio di induzione matematica:** Sia $P(n)$ una proprietà degli interi positivi. Se:
> 1. **Caso base:** $P(0)$ è vera (o $P(1)$ se si ragiona su $\mathbb{N}_0$)
> 2. **Passo induttivo:** Per ogni $n \geq 0$, $P(n) \Rightarrow P(n+1)$
>
> allora $P(n)$ è vera per ogni $n \in \mathbb{N}$.

La struttura formale di una dimostrazione per induzione è sempre:

1. Enunciare la proprietà $P(n)$
2. Dimostrare $P(0)$ (o $P(1)$) direttamente
3. Assumere $P(n)$ (**ipotesi induttiva**) e dimostrare $P(n+1)$

---

### **4. Esempi di dimostrazioni per induzione**

#### **4.1. Somma dei primi $n$ numeri dispari**

**Proprietà** $P(n)$: $1 + 3 + 5 + \cdots + (2n-1) = n^2$, cioè:

$$\sum_{k=1}^{n} (2k-1) = n^2$$

**Caso base** ($n=1$): $2 \cdot 1 - 1 = 1 = 1^2$. ✓

**Passo induttivo**: supponiamo $P(n)$ vera, cioè $\sum_{k=1}^{n} (2k-1) = n^2$. Allora:

$$\sum_{k=1}^{n+1} (2k-1) = \sum_{k=1}^{n} (2k-1) + (2(n+1)-1) = n^2 + 2n + 1 = (n+1)^2$$

Dunque $P(n+1)$ è vera. $\square$

#### **4.2. Somma $1 + 2 + \cdots + n$**

**Proprietà** $P(n)$:

$$\sum_{k=1}^{n} k = \frac{n(n+1)}{2}$$

**Caso base** ($n=1$): $1 = \frac{1 \cdot 2}{2} = 1$. ✓

**Passo induttivo**: assumendo $P(n)$:

$$\sum_{k=1}^{n+1} k = \sum_{k=1}^{n} k + (n+1) = \frac{n(n+1)}{2} + (n+1) = (n+1)\left(\frac{n}{2}+1\right) = \frac{(n+1)(n+2)}{2}$$

Dunque $P(n+1)$ è vera. $\square$

#### **4.3. Rette che dividono il piano**

**Proprietà** $P(n)$: $n$ rette in posizione generale (nessun parallelismo, nessun punto comune a tre rette) dividono il piano in $\dfrac{n^2 + n + 2}{2}$ regioni.

**Caso base** ($n=0$): il piano intero è 1 regione, e $\dfrac{0+0+2}{2} = 1$. ✓

**Passo induttivo**: la $(n+1)$-esima retta, in posizione generale rispetto alle precedenti $n$, interseca ciascuna di esse in un punto distinto. Questi $n$ punti dividono la nuova retta in $n+1$ segmenti (inclusi i semipiani), ciascuno dei quali divide in due una regione preesistente. Il numero di regioni aumenta di $n+1$:

$$\frac{n^2+n+2}{2} + (n+1) = \frac{n^2+n+2+2n+2}{2} = \frac{(n+1)^2+(n+1)+2}{2}$$

Dunque $P(n+1)$ è vera. $\square$

---

### **5. Errori tipici nel passo induttivo**

**L'esempio dei bambini dagli occhi dello stesso colore:**

Enunciato falso: "Tutti i bambini di qualsiasi gruppo hanno gli occhi dello stesso colore."

Dimostrazione (scorretta):

- $P(1)$: un bambino ha gli occhi dello stesso colore. ✓
- Passo induttivo: presi $n+1$ bambini $\{b_1, b_2, \ldots, b_{n+1}\}$, per ipotesi induttiva $\{b_1, \ldots, b_n\}$ hanno tutti lo stesso colore e $\{b_2, \ldots, b_{n+1}\}$ anche. Dunque tutti hanno lo stesso colore.

> ⚠️ **Errore**: il passo da $n=1$ a $n=2$ è fallace. Con 2 bambini, i due insiemi $\{b_1\}$ e $\{b_2\}$ sono disgiunti — non c'è un elemento comune che garantisca lo stesso colore. L'ipotesi induttiva richiede che i due sottoinsiemi si sovrappongano, ma ciò accade solo per $n \geq 2$.

---

### **6. Seconda forma del principio di induzione (induzione forte)**

> 📌 **Secondo principio di induzione (induzione forte o completa):** Se:
> 1. $P(0)$ è vera
> 2. Per ogni $n \geq 1$: $\left(P(0) \wedge P(1) \wedge \cdots \wedge P(n-1)\right) \Rightarrow P(n)$
>
> allora $P(n)$ è vera per ogni $n \in \mathbb{N}$.

La differenza con la prima forma è che nel passo induttivo si può usare la verità di $P$ per **tutti** i valori precedenti a $n$, non solo per $n-1$.

> 💡 L'induzione forte è particolarmente utile per proprietà che dipendono da valori "lontani", come la successione di Fibonacci o la fattorizzazione in primi.

---

### **7. Principio del buon ordinamento**

> 📌 **Principio del buon ordinamento:** Ogni sottoinsieme non vuoto di $\mathbb{N}$ ha un elemento minimo.

Formalmente: se $S \subseteq \mathbb{N}$ e $S \neq \emptyset$, allora $\exists m \in S$ tale che $m \leq s$ per ogni $s \in S$.

> ⚠️ Questo principio **non vale** per $\mathbb{Z}$: l'insieme degli interi negativi non ha minimo. Né vale per $\mathbb{Q}$: $(0, 1) \cap \mathbb{Q}$ non ha minimo.

---

### **8. Equivalenza tra i tre principi**

I tre principi seguenti sono logicamente equivalenti (ciascuno implica gli altri due):

1. **Prima forma del principio di induzione**
2. **Seconda forma del principio di induzione** (induzione forte)
3. **Principio del buon ordinamento**

> 💡 Nella pratica matematica si usa la forma più comoda per la dimostrazione specifica. Il principio del buon ordinamento è spesso usato nelle dimostrazioni per assurdo (si assume l'esistenza di un controesempio e si trova un controesempio più piccolo — contraddizione).

---

> ✅ Il principio di induzione matematica è lo strumento fondamentale per dimostrare proprietà su tutti i naturali. Esiste in due forme (standard e forte) ed è equivalente al principio del buon ordinamento. L'errore più comune consiste nel verificare il passo induttivo in modo non rigoroso (specialmente per $n=1 \to n=2$).
