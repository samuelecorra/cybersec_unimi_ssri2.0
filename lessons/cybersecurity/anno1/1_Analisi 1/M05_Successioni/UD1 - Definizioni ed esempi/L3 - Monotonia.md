# **M5 UD1 Lezione 3 - Monotonia**

### **1. Definizioni**

Una successione è:

$$
\text{crescente}\iff \forall n\ a_{n+1}\ge a_n,
$$

$$
\text{strettamente crescente}\iff \forall n\ a_{n+1}>a_n,
$$

$$
\text{decrescente}\iff \forall n\ a_{n+1}\le a_n,
$$

$$
\text{strettamente decrescente}\iff \forall n\ a_{n+1}<a_n.
$$

"Monotona" significa crescente oppure decrescente. Una successione costante è sia crescente sia decrescente nel senso largo.

È **definitivamente crescente** se esiste $N$ tale che $a_{n+1}\ge a_n$ per ogni $n\ge N$; analogamente negli altri casi.

---

### **2. Metodo della differenza**

Il segno di

$$
a_{n+1}-a_n
$$

fornisce il criterio più generale.

Per

$$
a_n=\frac{n}{n+1}
$$

si ha

$$
a_{n+1}-a_n
=
\frac{1}{(n+1)(n+2)}>0,
$$

quindi $(a_n)$ è strettamente crescente.

Per

$$
b_n=n^2-5n
$$

si ottiene

$$
b_{n+1}-b_n=2n-4.
$$

La successione non è crescente dall'inizio, ma lo è per $n\ge2$.

---

### **3. Metodo del rapporto**

Se $a_n>0$ e $a_n\ne0$, allora

$$
\frac{a_{n+1}}{a_n}\ge1\iff a_{n+1}\ge a_n.
$$

Il metodo è utile per fattoriali e prodotti, ma richiede positività. Se i termini sono negativi, moltiplicare per $a_n$ inverte l'ordine; se un termine è nullo, il rapporto non è definito.

Esempio: per $a_n=n!/3^n$, $n\ge1$,

$$
\frac{a_{n+1}}{a_n}=\frac{n+1}{3}.
$$

La successione è decrescente per $n=1$, costante nel passaggio $2\to3$ e crescente da $n\ge3$.

---

### **4. Estensione reale e induzione**

Se $a_n=f(n)$ e si sa che $f$ è crescente su un intervallo contenente gli indici, allora $(a_n)$ è crescente. L'uso della derivata sarà formalizzato nei moduli successivi; qui è solo un metodo anticipato.

Per ricorrenze, la monotonia si prova spesso per induzione dopo avere trovato un intervallo invariante. Non basta osservare i primi termini.

---

### **5. Monotonia, oscillazione e periodicità**

Una successione periodica non costante non può essere monotona: in ogni periodo torna a un valore precedente. La successione $(-1)^n$ è oscillante e non monotona, mentre $(-1)^n/(n+1)$ oscilla con ampiezza decrescente.

<!-- TODO FIGURA: tre pannelli discreti con successione crescente limitata n/(n+1), crescente non limitata n e oscillante (-1)^n; assi n e a_n, quota 1 nel primo; obiettivo didattico: distinguere monotonia, limitatezza e oscillazione. -->

<!-- TODO FIGURA: grafico discreto di n^2-5n con tratto iniziale decrescente e coda crescente, soglia N=2 evidenziata; obiettivo didattico: visualizzare la monotonia definitiva rispetto a quella globale. -->

---

### **6. Prime conseguenze d'ordine**

Se $(a_n)$ è crescente, allora

$$
a_n\ge a_0
$$

per ogni $n$. Se è decrescente, $a_n\le a_0$. Una successione crescente limitata superiormente possiede supremo; la completezza di $\mathbb R$ consentirà di dimostrare che converge proprio a quel supremo.

Monotonia da sola non implica convergenza reale: $a_n=n$ è crescente e diverge a $+\infty$.

---

### **7. Errori frequenti**

1. Confondere crescente con strettamente crescente.
2. Usare il rapporto senza controllare segno e denominatore.
3. Dedurre monotonia da pochi termini.
4. Ignorare l'indice iniziale.
5. Concludere convergenza dalla sola monotonia.
6. Confondere monotonia definitiva e globale.

---

### **8. Esercizi**

1. Studia con la differenza $a_n=(2n+1)/(n+1)$.
2. Determina da quale indice $n^2-9n+3$ è crescente.
3. Studia $a_n=n!/5^n$ con il rapporto.
4. Mostra che $\sqrt{n+1}-\sqrt n$ è decrescente razionalizzando.
5. Trova $\alpha$ affinché $(n+\alpha)/(n+1)$ sia crescente.
6. Costruisci una successione definitivamente crescente ma non crescente.
7. Dimostra che una successione periodica e monotona è costante.
8. Spiega perché il rapporto consecutivo che tende a $1$ non determina monotonia né convergenza, usando $a_n=n$ e $b_n=1/n$.

---

### **9. Riepilogo**

> ✅ La monotonia è una proprietà d'ordine globale o definitiva; differenza e rapporto sono criteri da applicare con ipotesi esplicite.
