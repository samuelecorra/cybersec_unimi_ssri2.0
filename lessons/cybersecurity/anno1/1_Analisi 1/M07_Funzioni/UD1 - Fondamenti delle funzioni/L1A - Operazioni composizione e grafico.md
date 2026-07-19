# **M7 UD1 Lezione 1A - Operazioni, composizione e grafico**

### **1. Perché il dominio fa parte del calcolo**

Le operazioni tra formule non bastano a definire un'operazione tra funzioni. Occorre stabilire dove tutti i valori coinvolti esistono. Siano

$$
f:A\to\mathbb{R},
\qquad
g:B\to\mathbb{R}.
$$

Le funzioni somma, differenza e prodotto sono definite su $A\cap B$:

$$
(f+g)(x)=f(x)+g(x),
$$

$$
(f-g)(x)=f(x)-g(x),
$$

$$
(fg)(x)=f(x)g(x).
$$

Il quoziente ha dominio

$$
D_{f/g}=\{x\in A\cap B:g(x)\neq 0\}
$$

e legge

$$
\left(\frac{f}{g}\right)(x)=\frac{f(x)}{g(x)}.
$$

> 📌 Il dominio dell'operazione è parte della sua definizione, non un controllo accessorio.

#### **1.1 Dimostrazione delle formule di dominio**

**Ipotesi.** $f$ è definita esattamente su $A$ e $g$ esattamente su $B$.

**Tesi.** Somma, differenza e prodotto sono definiti esattamente su $A\cap B$; il quoziente richiede inoltre $g(x)\neq0$.

**Dimostrazione.** Se $x\in A\cap B$, esistono entrambi i numeri reali $f(x)$ e $g(x)$; pertanto sono definite somma, differenza e prodotto. Se $x\notin A\cap B$, almeno uno dei due valori non esiste e nessuna di queste tre leggi può essere valutata come operazione tra le funzioni assegnate. Per il quoziente occorre e basta aggiungere la condizione $g(x)\neq0$. $\square$

Una semplificazione successiva non restituisce i punti persi. Per esempio, se

$$
f(x)=\sqrt{x-1},
\qquad
g(x)=\sqrt{x-1},
$$

allora il quoziente $f/g$ vale $1$ ma ha dominio $(1,+\infty)$, non $\mathbb{R}$ e neppure $[1,+\infty)$.

### **2. Composizione e ordine di lettura**

Siano

$$
f:A\to B,
\qquad
g:C\to D.
$$

La composizione $g\circ f$ applica prima $f$ e poi $g$:

$$
(g\circ f)(x)=g(f(x)).
$$

Perché il secondo passaggio sia possibile, il valore $f(x)$ deve appartenere al dominio $C$ di $g$. Quindi

$$
D_{g\circ f}=\{x\in A:f(x)\in C\}=A\cap f^{-1}(C).
$$

Se le funzioni sono presentate mediante espressioni,

$$
\operatorname{Dom}(g\circ f)
=\{x\in\operatorname{Dom}(f):f(x)\in\operatorname{Dom}(g)\}.
$$

#### **2.1 Dimostrazione**

**Ipotesi.** $x$ è un possibile ingresso di $g\circ f$.

**Tesi.** $x\in A$ e $f(x)\in C$, e queste due condizioni sono anche sufficienti.

**Dimostrazione.** Per calcolare $f(x)$ è necessario $x\in A$. Ottenuto $f(x)$, per calcolare $g(f(x))$ è necessario $f(x)\in C$. Viceversa, se entrambe le condizioni valgono, i due passaggi sono definiti e producono un unico elemento di $D$. $\square$

> ⚠️ In generale $\operatorname{Dom}(g\circ f)$ non è $\operatorname{Dom}(f)\cap\operatorname{Dom}(g)$: i due domini descrivono variabili poste a livelli diversi.

Esempio fondamentale:

$$
f(x)=x^2,
\qquad
g(t)=\sqrt{t-1}.
$$

Si ha $D_f=\mathbb{R}$ e $D_g=[1,+\infty)$, ma

$$
(g\circ f)(x)=\sqrt{x^2-1}
$$

ha dominio

$$
(-\infty,-1]\cup[1,+\infty),
$$

diverso da $D_f\cap D_g=[1,+\infty)$.

Invece

$$
(f\circ g)(x)=\left(\sqrt{x-1}\right)^2=x-1
$$

conserva il dominio $[1,+\infty)$ dell'espressione originaria. Di regola $g\circ f\neq f\circ g$.

<!-- TODO FIGURA:
Diagramma a tre insiemi A, C e D: frecce di f da A verso C, sottoinsieme Dom(g) evidenziato in C e frecce di g verso D. In A evidenziare in colore la controimmagine f^{-1}(Dom(g)), lasciando fuori gli ingressi che f manda oltre il dominio di g; indicare l'ordine prima f, poi g.
Obiettivo didattico: rappresentare Dom(g composta f) come insieme degli x ammessi da f la cui immagine è ammessa da g, non come intersezione dei due domini.
-->

### **3. Associatività e funzione identità**

Quando tutte le composizioni sono definite,

$$
h\circ(g\circ f)=(h\circ g)\circ f.
$$

Infatti, per ogni ingresso ammesso $x$,

$$
\bigl(h\circ(g\circ f)\bigr)(x)=h(g(f(x)))
=\bigl((h\circ g)\circ f\bigr)(x).
$$

La funzione identità su $A$ è

$$
\operatorname{id}_A:A\to A,
\qquad
\operatorname{id}_A(x)=x,
$$

e soddisfa

$$
f\circ\operatorname{id}_A=f,
\qquad
\operatorname{id}_B\circ f=f
$$

per ogni $f:A\to B$.

### **4. Immagini e controimmagini operative**

Per $E\subseteq A$ e $F\subseteq B$ si definiscono

$$
f(E)=\{f(x):x\in E\},
$$

$$
f^{-1}(F)=\{x\in A:f(x)\in F\}.
$$

La notazione $f^{-1}(F)$ indica una **controimmagine** ed è sempre lecita; non richiede che esista la funzione inversa.

Le controimmagini preservano esattamente unioni, intersezioni e complementari:

$$
f^{-1}\left(\bigcup_{i\in I}F_i\right)=\bigcup_{i\in I}f^{-1}(F_i),
$$

$$
f^{-1}\left(\bigcap_{i\in I}F_i\right)=\bigcap_{i\in I}f^{-1}(F_i),
$$

$$
f^{-1}(B\setminus F)=A\setminus f^{-1}(F).
$$

Per le immagini,

$$
f\left(\bigcup_{i\in I}E_i\right)=\bigcup_{i\in I}f(E_i),
$$

ma in generale vale soltanto

$$
f(E_1\cap E_2)\subseteq f(E_1)\cap f(E_2).
$$

L'uguaglianza nell'ultima formula vale per ogni $E_1,E_2$ se $f$ è iniettiva. Con $f(x)=x^2$, $E_1=\{-1\}$ ed $E_2=\{1\}$, l'intersezione a sinistra è vuota, mentre quella a destra è $\{1\}$.

#### **4.1 Dimostrazione campione sulle controimmagini**

Per ogni $x\in A$,

$$
\begin{aligned}
x\in f^{-1}(F_1\cap F_2)
&\iff f(x)\in F_1\cap F_2\\
&\iff f(x)\in F_1\ \text{e}\ f(x)\in F_2\\
&\iff x\in f^{-1}(F_1)\cap f^{-1}(F_2).
\end{aligned}
$$

L'equivalenza elemento per elemento dimostra l'uguaglianza dei due insiemi. $\square$

### **5. Grafico e test della retta verticale**

Il grafico di $f:A\to B$ è la relazione

$$
G_f=\{(x,y)\in A\times B:y=f(x)\}.
$$

Una relazione $G\subseteq A\times B$ è il grafico di una funzione definita su tutto $A$ se e solo se per ogni $x\in A$ esiste un unico $y\in B$ con $(x,y)\in G$.

Nel piano questo diventa il **test della retta verticale**: ogni retta $x=c$ con $c\in A$ deve incontrare il grafico esattamente una volta. L'esistenza esclude rette senza intersezioni nel dominio dichiarato; l'unicità esclude due ordinate diverse per la stessa ascissa.

<!-- TODO FIGURA:
Piano cartesiano con, a sinistra, il grafico di y=x^2 superato dal test verticale e, a destra, la circonferenza x^2+y^2=1 tagliata in due punti da una retta x=c. Evidenziare le intersezioni e indicare il dominio proiettato sull'asse x.
Obiettivo didattico: distinguere una funzione dal grafico di una relazione mediante esistenza e unicità del valore associato.
-->

Dal grafico si possono leggere dominio e immagine come proiezioni sugli assi, zeri come intersezioni con l'asse $x$, segno, simmetrie, intervalli di crescita e valori estremi. Queste letture sono osservazioni sul grafico, non dimostrazioni automatiche per una formula.

### **6. Restrizione ed estensione**

Se $f:A\to B$ e $C\subseteq A$, la restrizione è

$$
f|_C:C\to B,
\qquad
f|_C(x)=f(x).
$$

Una funzione $F:E\to B$ con $A\subseteq E$ è un'estensione di $f$ se

$$
F|_A=f.
$$

La funzione $x\mapsto x^2$ non è iniettiva su $\mathbb{R}$, ma la sua restrizione a $[0,+\infty)$ lo è. Invece

$$
f:\mathbb{R}\setminus\{1\}\to\mathbb{R},
\qquad
f(x)=\frac{x^2-1}{x-1},
$$

ammette l'estensione $F(x)=x+1$ su $\mathbb{R}$, con $F(1)=2$.

<!-- TODO FIGURA:
Diagramma a frecce A verso B con un sottoinsieme C evidenziato e la restrizione f|_C; accanto, parabola y=x^2 con il ramo x>=0 colorato e il ramo x<0 attenuato.
Obiettivo didattico: mostrare che restringere il dominio può cambiare l'iniettività senza cambiare la legge sui punti conservati.
-->

### **7. Esercizi ragionati**

1. Determina i domini di $f+g$, $fg$ e $f/g$ per $f(x)=\sqrt{x+1}$ e $g(x)=x-2$.
2. Con $f(x)=1/(x-1)$ e $g(x)=\sqrt{x}$, calcola i domini di $g\circ f$ e $f\circ g$.
3. Dimostra elemento per elemento la formula del complementare per le controimmagini.
4. Trova $E_1,E_2\subseteq\mathbb{R}$ per cui l'inclusione $f(E_1\cap E_2)\subseteq f(E_1)\cap f(E_2)$ è stretta con $f(x)=|x|$.
5. Stabilisci se $x^2+y^2=4$, $y^2=x$ e $y=\sqrt{4-x^2}$ definiscono $y$ come funzione di $x$, dichiarando il dominio.
6. Descrivi tutte le estensioni a $\mathbb{R}$ della funzione costante $f:\mathbb{R}\setminus\{0\}\to\mathbb{R}$, $f(x)=1$.

### **8. Riepilogo**

> ✅ Operazioni e composizioni si definiscono solo dopo aver controllato i domini; il grafico traduce l'esistenza e l'unicità in un test geometrico.
