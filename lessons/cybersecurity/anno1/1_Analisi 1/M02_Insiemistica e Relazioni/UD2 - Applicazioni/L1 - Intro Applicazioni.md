# **M2 UD2 Lezione 1 - Intro Applicazioni**

### **1. Introduzione**

Le applicazioni, o funzioni, sono uno degli oggetti centrali dell’Analisi. Formalmente una funzione non è solo una formula: è una corrispondenza tra due insiemi con una regola precisa.

Scrivere:

$$
f:A\to B
$$

significa che $f$ associa a ogni elemento di $A$ uno e un solo elemento di $B$.

> 📌 Una funzione è determinata da dominio, codominio e legge di associazione.

---

### **2. Definizione di applicazione**

Siano $A$ e $B$ due insiemi. Una **applicazione** da $A$ in $B$ è una regola che associa a ogni $a\in A$ un unico elemento $b\in B$.

Si scrive:

$$
f:A\to B
$$

e:

$$
f(a)=b
$$

L’insieme $A$ si chiama **dominio**; l’insieme $B$ si chiama **codominio**.

---

### **3. Immagine di un elemento**

Se:

$$
f(a)=b
$$

allora $b$ si chiama **immagine** di $a$ tramite $f$.

L’elemento $a$ si chiama anche **controimmagine** o **preimmagine** di $b$, se si guarda la relazione al contrario.

Esempio:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

Allora:

$$
f(3)=9
$$

e:

$$
f(-3)=9
$$

Quindi lo stesso valore del codominio può avere più preimmagini.

---

### **4. Una sola immagine per ogni elemento**

La condizione essenziale è:

> ogni elemento del dominio deve avere esattamente una immagine.

Non è una funzione una corrispondenza che associa a uno stesso elemento due valori diversi.

Per esempio, la regola:

$$
x\mapsto \pm\sqrt{x}
$$

non è una funzione da $[0,+\infty)$ in $\mathbb{R}$, perché a $x=4$ assocerebbe sia $2$ sia $-2$.

Diventa una funzione se si sceglie il ramo principale:

$$
f(x)=\sqrt{x}
$$

che associa a $4$ solo $2$.

---

### **5. Immagine di una funzione**

L’**immagine** di $f$ è l’insieme dei valori effettivamente assunti:

$$
f(A)=\{f(a):a\in A\}
$$

Si ha sempre:

$$
f(A)\subseteq B
$$

ma non necessariamente:

$$
f(A)=B
$$

Esempio:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

ha immagine:

$$
f(\mathbb{R})=[0,+\infty)
$$

che è un sottoinsieme proprio di $\mathbb{R}$.

---

### **6. Grafico di una funzione reale**

Se:

$$
f:A\subseteq\mathbb{R}\to\mathbb{R}
$$

il grafico di $f$ è:

$$
G_f=\{(x,f(x)):x\in A\}
$$

È un sottoinsieme del piano:

$$
\mathbb{R}^2
$$

Il grafico rappresenta tutte le coppie input-output della funzione.

<!-- TODO FIGURA: grafico di una funzione reale con dominio A sull'asse x, codominio B sull'asse y e immagine f(A) evidenziata; obiettivo didattico: distinguere codominio dichiarato, immagine effettiva e grafico come insieme di coppie. -->

---

### **7. Funzioni uguali**

Due funzioni sono uguali se hanno:

- lo stesso dominio;
- lo stesso codominio;
- la stessa legge di associazione su ogni elemento del dominio.

Per esempio:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

e:

$$
g:[0,+\infty)\to\mathbb{R},\quad g(x)=x^2
$$

non sono la stessa funzione, perché hanno domini diversi.

---

### **8. Controimmagine di un sottoinsieme**

Se $C\subseteq B$, la **controimmagine** di $C$ tramite $f:A\to B$ è:

$$
f^{-1}(C)=\{x\in A:f(x)\in C\}
$$

Questa notazione non richiede che $f$ sia invertibile: indica l’insieme degli input che finiscono in $C$.

Esempio:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

Allora:

$$
f^{-1}([1,4])=[-2,-1]\cup[1,2]
$$

perché $1\le x^2\le 4$ equivale a $1\le |x|\le 2$.

> ⚠️ $f^{-1}(C)$ come controimmagine di un insieme non è la stessa cosa della funzione inversa: esiste sempre per ogni funzione, anche non biiettiva.

---

### **9. Restrizione, estensione, identità e costante**

Se $f:A\to B$ e $S\subseteq A$, la **restrizione** di $f$ a $S$ è:

$$
f_{\mid S}:S\to B,\qquad f_{\mid S}(x)=f(x)
$$

La legge resta la stessa, ma il dominio diventa più piccolo.

Una **estensione** di $f$ è invece una funzione $g:C\to B$, con $A\subseteq C$, tale che:

$$
g_{\mid A}=f
$$

Due funzioni elementari ricorrono continuamente:

$$
\operatorname{id}_A:A\to A,\qquad \operatorname{id}_A(x)=x
$$

e, fissato $b_0\in B$:

$$
c:A\to B,\qquad c(x)=b_0
$$

La prima è la funzione identità, la seconda è una funzione costante.

---

### **10. Immagini e controimmagini rispetto alle operazioni**

Per $E,F\subseteq A$ valgono sempre:

$$
f(E\cup F)=f(E)\cup f(F)
$$

e:

$$
f(E\cap F)\subseteq f(E)\cap f(F)
$$

L’ultima può essere un’inclusione propria. Con $f(x)=x^2$, $E=(-\infty,0)$ e $F=(0,+\infty)$, si ha:

$$
E\cap F=\varnothing
$$

ma:

$$
f(E)\cap f(F)=(0,+\infty)
$$

Per le controimmagini, invece, le operazioni si conservano esattamente. Se $C,D\subseteq B$:

$$
f^{-1}(C\cup D)=f^{-1}(C)\cup f^{-1}(D)
$$

$$
f^{-1}(C\cap D)=f^{-1}(C)\cap f^{-1}(D)
$$

$$
f^{-1}(B\setminus C)=A\setminus f^{-1}(C)
$$

> 📌 Le controimmagini sono più regolari delle immagini: preservano unioni, intersezioni e complementari senza ipotesi aggiuntive.

---

### **11. Riepilogo**

> ✅ Una applicazione $f:A\to B$ associa a ogni elemento del dominio $A$ uno e un solo elemento del codominio $B$.

Punti essenziali:

- il dominio è l’insieme degli input ammessi;
- il codominio è l’insieme dichiarato dei possibili output;
- l’immagine è l’insieme dei valori realmente assunti;
- la controimmagine di un sottoinsieme raccoglie gli input che finiscono in quel sottoinsieme;
- restrizione ed estensione modificano il dominio mantenendo coerente la legge;
- una funzione non può associare due immagini diverse allo stesso input;
- dominio e codominio fanno parte della definizione della funzione.
