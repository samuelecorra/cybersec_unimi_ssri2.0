# **M7 UD2 Lezione 1 - Funzioni razionali e irrazionali**

### **1. Introduzione**

L’insieme di definizione di una funzione è il primo elemento da determinare. Per le funzioni razionali e irrazionali, le restrizioni principali vengono da denominatori e radici.

> 📌 Il dominio è l’insieme dei valori reali per cui tutte le operazioni nella formula hanno senso.

---

### **2. Funzioni polinomiali**

Una funzione polinomiale ha forma:

$$
f(x)=a_nx^n+\dots+a_1x+a_0
$$

I polinomi sono definiti per ogni numero reale.

Quindi:

$$
D_f=\mathbb{R}
$$

Esempio:

$$
f(x)=x^4-3x+1
$$

ha dominio $\mathbb{R}$.

---

### **3. Funzioni razionali fratte**

Una funzione razionale fratta ha forma:

$$
f(x)=\frac{P(x)}{Q(x)}
$$

dove $P$ e $Q$ sono polinomi.

Il vincolo è:

$$
Q(x)\neq 0
$$

Quindi:

$$
D_f=\{x\in\mathbb{R}:Q(x)\neq 0\}
$$

---

### **4. Esempio razionale**

Consideriamo:

$$
f(x)=\frac{x+1}{x^2-4}
$$

Serve:

$$
x^2-4\neq 0
$$

Fattorizziamo:

$$
(x-2)(x+2)\neq 0
$$

Quindi:

$$
x\neq 2,\quad x\neq -2
$$

Il dominio è:

$$
\mathbb{R}\setminus\{-2,2\}
$$

---

### **5. Radici di indice pari**

Una radice di indice pari:

$$
\sqrt[2k]{A(x)}
$$

è definita nei reali se:

$$
A(x)\ge 0
$$

Esempio:

$$
f(x)=\sqrt{x-3}
$$

richiede:

$$
x-3\ge 0
$$

quindi:

$$
D_f=[3,+\infty)
$$

---

### **6. Radici di indice dispari**

Una radice di indice dispari:

$$
\sqrt[2k+1]{A(x)}
$$

è definita per ogni valore reale del radicando.

Se $A(x)$ è un polinomio, non introduce restrizioni.

Esempio:

$$
f(x)=\sqrt[3]{x^2-5}
$$

ha dominio:

$$
\mathbb{R}
$$

---

### **7. Combinare più condizioni**

Se nella stessa funzione compaiono più vincoli, il dominio è l’intersezione delle condizioni.

Esempio:

$$
f(x)=\frac{\sqrt{x-1}}{x-4}
$$

La radice richiede:

$$
x-1\ge 0
$$

Il denominatore richiede:

$$
x-4\neq 0
$$

Quindi:

$$
x\ge 1,\quad x\neq 4
$$

Il dominio è:

$$
[1,4)\cup(4,+\infty)
$$

### **8. Radici pari al denominatore**

Se una radice di indice pari compare al denominatore, il radicando deve essere strettamente positivo:

$$
\frac{1}{\sqrt[2k]{R(x)}}
\quad\Longrightarrow\quad
R(x)>0.
$$

La condizione $R(x)\ge0$ garantirebbe l'esistenza della radice, ma non la non nullità del denominatore. Per esempio,

$$
f(x)=\frac{1}{\sqrt{x^2-1}}
$$

ha dominio

$$
(-\infty,-1)\cup(1,+\infty).
$$

### **9. Funzioni potenza**

Per esponenti interi:

$$
x^n\quad(n\ge0)
$$

ha dominio $\mathbb{R}$, mentre

$$
x^{-n}=\frac1{x^n}\quad(n>0)
$$

esclude $0$.

Per $x^{p/q}$ con $p/q$ ridotta ai minimi termini e $q>0$:

- se $q$ è pari, serve $x\ge0$;
- se $q$ è dispari, sono ammesse basi reali negative;
- se l'esponente è negativo, si esclude inoltre $x=0$.

Per un'espressione generale $u(x)^{v(x)}$ con esponente reale variabile, la definizione elementare globale

$$
u(x)^{v(x)}=e^{v(x)\ln u(x)}
$$

richiede

$$
u(x)>0
$$

e che $v(x)$ sia definita. Basi negative possono essere ammesse solo in casi speciali dell'esponente e non producono, in generale, una funzione reale definita su un intervallo.

### **10. Procedura generale**

Per determinare il dominio naturale:

1. si conserva l'espressione originaria;
2. si scrive una condizione per ogni denominatore, radice pari, logaritmo e funzione esterna;
3. si aggiungono le condizioni necessarie alle espressioni interne;
4. si risolve il sistema simultaneo;
5. si verifica che nessuna semplificazione abbia reinserito punti esclusi.

Esempio:

$$
F(x)=\sqrt{\frac{x-1}{x+2}}+\frac{1}{\sqrt{3-x}}.
$$

Occorrono

$$
\begin{cases}
\dfrac{x-1}{x+2}\ge0,\\
x\neq-2,\\
3-x>0.
\end{cases}
$$

La prima disequazione dà $(-\infty,-2)\cup[1,+\infty)$; intersecando con $x<3$ si ottiene

$$
D_F=(-\infty,-2)\cup[1,3).
$$

<!-- TODO FIGURA:
Retta reale con tre righe sovrapposte per le condizioni (x-1)/(x+2)>=0, x!= -2 e x<3, seguite dalla riga finale dell'intersezione. Usare pallini pieni/vuoti e tratteggi distinti.
Obiettivo didattico: visualizzare il dominio come intersezione di condizioni simultanee, inclusi punti esclusi e radici al denominatore.
-->

---

### **11. Errore tipico**

Se una frazione si semplifica, il dominio originale va conservato.

Esempio:

$$
f(x)=\frac{x^2-1}{x-1}
$$

La formula si semplifica:

$$
\frac{(x-1)(x+1)}{x-1}=x+1
$$

ma il dominio resta:

$$
\mathbb{R}\setminus\{1\}
$$

perché la funzione originale non è definita in $x=1$.

<!-- TODO FIGURA:
Grafico della retta y=x+1 con un punto vuoto in (1,2), confrontato con la retta completa in un secondo pannello. Etichettare i domini R senza {1} e R.
Obiettivo didattico: mostrare che una cancellazione algebrica lascia un punto escluso e non produce la stessa funzione estesa.
-->

### **12. Immagini senza derivate**

Per trovare l'immagine si pone $y=f(x)$ e si chiede per quali $y$ l'equazione ammette almeno un $x$ nel dominio.

Per

$$
f(x)=x^2-4x+1=(x-2)^2-3
$$

si ha

$$
\operatorname{Im}(f)=[-3,+\infty).
$$

Per

$$
g(x)=\frac{x-1}{x+2},
$$

da $y=(x-1)/(x+2)$ segue

$$
x=-\frac{1+2y}{y-1},
$$

che esiste per ogni $y\neq1$. Quindi

$$
\operatorname{Im}(g)=\mathbb{R}\setminus\{1\}.
$$

Per $h(x)=\sqrt{x+2}$, il dominio è $[-2,+\infty)$ e l'immagine è $[0,+\infty)$.

Per le potenze intere, parità e segno dell'esponente danno direttamente:

$$
\operatorname{Im}(x^{2n})=[0,+\infty)
\quad(n\ge1),
$$

$$
\operatorname{Im}(x^{2n+1})=\mathbb{R}
\quad(n\ge0),
$$

$$
\operatorname{Im}(x^{-2n})=(0,+\infty)
\quad(n\ge1),
$$

$$
\operatorname{Im}(x^{-(2n+1)})=\mathbb{R}\setminus\{0\}
\quad(n\ge0).
$$

Le radici principali pari hanno immagine $[0,+\infty)$; le radici dispari hanno immagine $\mathbb{R}$. Queste conclusioni seguono dall'esistenza delle radici corrispondenti, non da un argomento differenziale.

### **13. Esercizi**

1. Determina il dominio di $\sqrt{x+1}/\sqrt{x-2}$.
2. Determina il dominio di $\sqrt{(x-1)/(x+3)}$.
3. Studia il dominio di $(x-1)^{\sqrt{x+2}}$ usando la convenzione esponenziale reale globale.
4. Determina dominio e immagine di $(2x+1)/(x-3)$.
5. Completa il quadrato per trovare l'immagine di $-2x^2+4x+3$.
6. Trova il dominio naturale di $\sqrt{(x^2-1)/(4-x^2)}+1/\sqrt{x+3}$.
7. Spiega perché $\sqrt{x^2}=x$ non è un'identità su $\mathbb{R}$.

---

### **14. Riepilogo**

> ✅ Per funzioni razionali e irrazionali il dominio si ottiene imponendo denominatori non nulli e radicandi pari non negativi.

Punti essenziali:

- polinomi: dominio $\mathbb{R}$;
- frazioni: denominatore diverso da zero;
- radici pari: radicando $\ge 0$;
- radici dispari: nessun vincolo se il radicando è sempre definito;
- radici pari al denominatore: radicando strettamente positivo;
- potenze reali a esponente variabile: base positiva nella definizione globale;
- più condizioni si intersecano;
- le semplificazioni non cancellano i vincoli originali.
