# **M1 UD3 Lezione 0 - Valore assoluto e distanza**

### **1. Introduzione**

Il valore assoluto è uno degli strumenti più ricorrenti in Analisi 1. Compare nelle equazioni e disequazioni elementari, ma soprattutto nelle definizioni rigorose di limite, continuità, convergenza di successioni e convergenza assoluta di serie e integrali.

L'idea intuitiva è semplice: il valore assoluto misura quanto un numero è lontano da $0$, senza tenere conto del verso. Per esempio $5$ e $-5$ sono numeri diversi, ma entrambi distano $5$ dall'origine.

> 📌 Il valore assoluto trasforma un numero reale nella sua distanza da $0$.

Questa lezione ha un ruolo propedeutico: costruisce il linguaggio necessario per trattare con precisione distanze, intervalli, stime e disequazioni.

### **2. Definizione formale**

Sia $x\in\mathbb{R}$. Il **valore assoluto** di $x$ è il numero reale non negativo indicato con $|x|$ e definito da:

$$
|x|=
\begin{cases}
x & \text{se } x\ge 0 \\
-x & \text{se } x<0
\end{cases}
$$

Questa definizione dice che:

- se $x$ è positivo o nullo, il valore assoluto non cambia il numero;
- se $x$ è negativo, il valore assoluto cambia il segno.

Esempi:

$$
|7|=7,\quad |0|=0,\quad |-7|=7.
$$

> ⚠️ Il valore assoluto non significa “mettere sempre un più davanti”. Significa applicare una definizione a tratti.

### **3. Interpretazione geometrica: distanza da zero**

Sulla retta reale, $|x|$ rappresenta la distanza tra il punto $x$ e l'origine.

<!-- TODO FIGURA:
Retta reale con origine 0, punto x=4 a destra e punto x=-4 a sinistra.
Evidenziare che entrambi hanno distanza 4 da 0 e quindi valore assoluto uguale a 4.
Obiettivo didattico: interpretare il valore assoluto come distanza dall'origine.
-->

Per questo:

$$
|x|\ge 0
$$

per ogni $x\in\mathbb{R}$, e

$$
|x|=0 \Longleftrightarrow x=0.
$$

La proprietà precedente è fondamentale: l'unico numero a distanza nulla da $0$ è $0$ stesso.

### **4. Distanza tra due numeri reali**

Il valore assoluto permette di esprimere anche la distanza tra due numeri reali qualsiasi.

Se $a,b\in\mathbb{R}$, la distanza tra $a$ e $b$ è:

$$
d(a,b)=|a-b|.
$$

La formula funziona indipendentemente da quale numero sia più grande.

Per esempio, la distanza tra $7$ e $2$ è:

$$
|7-2|=|5|=5.
$$

La distanza tra $2$ e $7$ è:

$$
|2-7|=|-5|=5.
$$

Quindi:

$$
|a-b|=|b-a|.
$$

> 📌 In Analisi, scrivere $|x-a|<\varepsilon$ significa dire che $x$ dista da $a$ meno di $\varepsilon$.

Questa notazione sarà centrale nelle definizioni formali di limite e continuità.

### **5. Grafico di $y=|x|$**

La funzione valore assoluto è:

$$
f:\mathbb{R}\to[0,+\infty),\quad f(x)=|x|.
$$

Usando la definizione a tratti:

$$
f(x)=
\begin{cases}
x & \text{se } x\ge 0 \\
-x & \text{se } x<0
\end{cases}
$$

Il grafico è formato da due semirette:

- per $x\ge 0$, coincide con la retta $y=x$;
- per $x<0$, coincide con la retta $y=-x$.

<!-- TODO FIGURA:
Grafico di y=|x|, con evidenza della simmetria rispetto all'asse y,
del vertice nell'origine e della definizione a tratti: y=x per x>=0,
y=-x per x<0.
Obiettivo didattico: collegare definizione a tratti, non negatività e simmetria pari.
-->

Il punto $(0,0)$ è il vertice del grafico. La funzione è pari, perché:

$$
|-x|=|x|
$$

per ogni $x\in\mathbb{R}$.

### **6. Proprietà fondamentali del valore assoluto**

Per ogni $x,y\in\mathbb{R}$ valgono le seguenti proprietà:

$$
|x|\ge 0
$$

$$
|x|=0 \Longleftrightarrow x=0
$$

$$
|-x|=|x|
$$

$$
|xy|=|x|\cdot|y|
$$

$$
\left|\frac{x}{y}\right|=\frac{|x|}{|y|}\quad \text{se } y\neq 0
$$

$$
|x^n|=|x|^n\quad \text{per ogni } n\in\mathbb{N}
$$

La proprietà del prodotto è particolarmente utile. Per esempio:

$$
|(-3)\cdot 5|=|-15|=15
$$

e

$$
|-3|\cdot|5|=3\cdot 5=15.
$$

### **7. Dimostrazione della proprietà del prodotto**

Dimostriamo che:

$$
|xy|=|x|\cdot|y|.
$$

Osserviamo che $|x|$ è $x$ se $x\ge 0$ e $-x$ se $x<0$. Consideriamo i possibili segni di $x$ e $y$.

Se $x\ge 0$ e $y\ge 0$, allora $xy\ge 0$, quindi:

$$
|xy|=xy=|x|\cdot|y|.
$$

Se $x\ge 0$ e $y<0$, allora $xy<0$, quindi:

$$
|xy|=-(xy)=x(-y)=|x|\cdot|y|.
$$

Se $x<0$ e $y\ge 0$, allora $xy<0$, quindi:

$$
|xy|=-(xy)=(-x)y=|x|\cdot|y|.
$$

Se $x<0$ e $y<0$, allora $xy>0$, quindi:

$$
|xy|=xy=(-x)(-y)=|x|\cdot|y|.
$$

In tutti i casi si ottiene la stessa identità.

### **8. Disuguaglianza triangolare**

Una proprietà centrale del valore assoluto è la **disuguaglianza triangolare**:

$$
|x+y|\le |x|+|y|
$$

per ogni $x,y\in\mathbb{R}$.

Il nome viene dalla geometria: in un triangolo, la lunghezza di un lato è sempre minore o uguale alla somma delle lunghezze degli altri due. Sulla retta reale, la stessa idea dice che andare da $0$ a $x+y$ direttamente non può essere più lungo che andare prima da $0$ a $x$ e poi aggiungere lo spostamento $y$.

<!-- TODO FIGURA:
Rappresentazione sulla retta reale della disuguaglianza triangolare.
Mostrare uno spostamento da 0 a x e poi da x a x+y, confrontato con la distanza diretta da 0 a x+y.
Obiettivo didattico: visualizzare perché la distanza diretta non supera la somma dei due spostamenti.
-->

#### **8.1 Dimostrazione**

Dimostriamo la disuguaglianza triangolare usando i quadrati. Per ogni numero reale $t$ vale:

$$
t\le |t|.
$$

Quindi:

$$
xy\le |xy|=|x|\cdot|y|.
$$

Ora calcoliamo:

$$
|x+y|^2=(x+y)^2=x^2+2xy+y^2.
$$

Usando $xy\le |x|\cdot|y|$, otteniamo:

$$
x^2+2xy+y^2\le x^2+2|x|\cdot|y|+y^2.
$$

Poiché $x^2=|x|^2$ e $y^2=|y|^2$, il secondo membro diventa:

$$
|x|^2+2|x|\cdot|y|+|y|^2=(|x|+|y|)^2.
$$

Dunque:

$$
|x+y|^2\le (|x|+|y|)^2.
$$

Entrambi i membri sono non negativi, quindi possiamo estrarre la radice quadrata preservando il verso:

$$
|x+y|\le |x|+|y|.
$$

#### **8.2 Quando vale l'uguaglianza**

L'uguaglianza:

$$
|x+y|=|x|+|y|
$$

vale quando $x$ e $y$ hanno lo stesso segno, oppure quando almeno uno dei due è nullo.

Per esempio:

$$
|3+5|=8=|3|+|5|.
$$

Invece:

$$
|3+(-5)|=|-2|=2<8=|3|+|-5|.
$$

### **9. Disuguaglianza triangolare inversa**

Dalla disuguaglianza triangolare segue anche:

$$
\big||x|-|y|\big|\le |x-y|.
$$

Questa proprietà è detta **disuguaglianza triangolare inversa**. Sarà utile nei limiti e nelle stime, perché permette di controllare la differenza tra due valori assoluti.

Una dimostrazione rapida si ottiene così. Dalla disuguaglianza triangolare:

$$
|x|=|(x-y)+y|\le |x-y|+|y|.
$$

Quindi:

$$
|x|-|y|\le |x-y|.
$$

Scambiando $x$ e $y$:

$$
|y|-|x|\le |x-y|.
$$

Le due disuguaglianze insieme equivalgono a:

$$
\big||x|-|y|\big|\le |x-y|.
$$

### **10. Equazioni elementari con valore assoluto**

Studiamo ora le equazioni del tipo:

$$
|A(x)|=c.
$$

Poiché un valore assoluto è sempre non negativo, ci sono tre casi.

Se $c<0$, l'equazione non ha soluzioni:

$$
|A(x)|=c<0 \quad \Longrightarrow \quad S=\varnothing.
$$

Se $c=0$, allora:

$$
|A(x)|=0 \Longleftrightarrow A(x)=0.
$$

Se $c>0$, allora:

$$
|A(x)|=c \Longleftrightarrow A(x)=c \quad \text{oppure} \quad A(x)=-c.
$$

> 📌 Il valore assoluto uguale a una costante positiva genera due casi simmetrici.

#### **10.1 Esempio: equazione lineare**

Risolviamo:

$$
|2x-3|=5.
$$

Poiché $5>0$, scriviamo:

$$
2x-3=5 \quad \text{oppure} \quad 2x-3=-5.
$$

Dal primo caso:

$$
2x=8
$$

quindi:

$$
x=4.
$$

Dal secondo caso:

$$
2x=-2
$$

quindi:

$$
x=-1.
$$

La soluzione è:

$$
S=\{-1,4\}.
$$

#### **10.2 Esempio: equazione con valore assoluto da entrambi i lati**

Risolviamo:

$$
|x-1|=|2x+3|.
$$

Questa equazione significa che $x$ ha la stessa distanza da $1$ e da $-\frac{3}{2}$, ma possiamo risolverla algebricamente elevando al quadrato entrambi i membri. L'elevamento è lecito perché entrambi i membri sono non negativi:

$$
(x-1)^2=(2x+3)^2.
$$

Sviluppiamo:

$$
x^2-2x+1=4x^2+12x+9.
$$

Portiamo tutto a destra:

$$
0=3x^2+14x+8.
$$

Fattorizziamo:

$$
3x^2+14x+8=(3x+2)(x+4).
$$

Quindi:

$$
x=-\frac{2}{3}\quad \text{oppure} \quad x=-4.
$$

Entrambe le soluzioni sono valide perché l'equazione ottenuta dal quadrato è equivalente in questo caso.

### **11. Disequazioni del tipo $|A(x)|<c$**

Consideriamo:

$$
|A(x)|<c.
$$

Se $c\le 0$, non ci sono soluzioni, perché $|A(x)|\ge 0$ per ogni $x$.

Se $c>0$, allora:

$$
|A(x)|<c \Longleftrightarrow -c<A(x)<c.
$$

Analogamente:

$$
|A(x)|\le c \Longleftrightarrow -c\le A(x)\le c
$$

per $c\ge 0$.

Queste equivalenze esprimono il fatto che $A(x)$ deve stare a distanza minore di $c$ da $0$.

#### **11.1 Esempio**

Risolviamo:

$$
|3x-2|<7.
$$

Scriviamo la doppia disequazione:

$$
-7<3x-2<7.
$$

Aggiungiamo $2$ a tutti i membri:

$$
-5<3x<9.
$$

Dividiamo per $3$:

$$
-\frac{5}{3}<x<3.
$$

La soluzione è:

$$
S=\left(-\frac{5}{3},3\right).
$$

### **12. Disequazioni del tipo $|A(x)|>c$**

Consideriamo:

$$
|A(x)|>c.
$$

Se $c<0$, la disequazione è vera per ogni valore del dominio di $A(x)$, perché un valore assoluto è sempre $\ge 0$ e quindi certamente maggiore di una costante negativa.

Se $c=0$, allora:

$$
|A(x)|>0 \Longleftrightarrow A(x)\neq 0.
$$

Se $c>0$, allora:

$$
|A(x)|>c \Longleftrightarrow A(x)<-c \quad \text{oppure} \quad A(x)>c.
$$

Analogamente:

$$
|A(x)|\ge c \Longleftrightarrow A(x)\le -c \quad \text{oppure} \quad A(x)\ge c
$$

per $c>0$.

> ⚠️ La disequazione $|A(x)|<c$ produce un'intersezione di condizioni; la disequazione $|A(x)|>c$ produce un'unione di condizioni.

#### **12.1 Esempio**

Risolviamo:

$$
|2x+1|\ge 5.
$$

Poiché $5>0$:

$$
2x+1\le -5 \quad \text{oppure} \quad 2x+1\ge 5.
$$

Dal primo caso:

$$
2x\le -6
$$

quindi:

$$
x\le -3.
$$

Dal secondo caso:

$$
2x\ge 4
$$

quindi:

$$
x\ge 2.
$$

La soluzione è:

$$
S=(-\infty,-3]\cup[2,+\infty).
$$

### **13. Metodo a tratti**

Quando il valore assoluto contiene un'espressione più complessa, spesso conviene usare direttamente la definizione a tratti.

Per esempio, per studiare:

$$
|x-2|
$$

bisogna distinguere:

$$
x-2\ge 0 \Longleftrightarrow x\ge 2
$$

e

$$
x-2<0 \Longleftrightarrow x<2.
$$

Quindi:

$$
|x-2|=
\begin{cases}
x-2 & \text{se } x\ge 2 \\
-x+2 & \text{se } x<2
\end{cases}
$$

Il metodo a tratti è più lungo, ma è molto robusto: funziona anche quando compaiono più valori assoluti o quando il termine a destra non è una costante positiva.

#### **13.1 Esempio con due valori assoluti**

Risolviamo:

$$
|x-1|+|x+2|=5.
$$

I punti in cui cambiano i segni degli argomenti sono:

$$
x=1,\quad x=-2.
$$

Essi dividono la retta in tre intervalli:

$$
(-\infty,-2),\quad [-2,1),\quad [1,+\infty).
$$

Studiamo i tre casi.

Per $x<-2$:

$$
|x-1|=-(x-1)=-x+1
$$

e

$$
|x+2|=-(x+2)=-x-2.
$$

L'equazione diventa:

$$
-x+1-x-2=5.
$$

Quindi:

$$
-2x-1=5
$$

e:

$$
x=-3.
$$

Il valore $x=-3$ appartiene all'intervallo $(-\infty,-2)$, quindi è accettato.

Per $-2\le x<1$:

$$
|x-1|=-x+1
$$

e

$$
|x+2|=x+2.
$$

L'equazione diventa:

$$
-x+1+x+2=5.
$$

Quindi:

$$
3=5.
$$

Questo è impossibile, dunque non ci sono soluzioni in questo intervallo.

Per $x\ge 1$:

$$
|x-1|=x-1
$$

e

$$
|x+2|=x+2.
$$

L'equazione diventa:

$$
x-1+x+2=5.
$$

Quindi:

$$
2x+1=5
$$

e:

$$
x=2.
$$

Il valore $x=2$ appartiene all'intervallo $[1,+\infty)$, quindi è accettato.

La soluzione è:

$$
S=\{-3,2\}.
$$

### **14. Valore assoluto e frazioni razionali**

Quando il valore assoluto contiene una frazione razionale, il dominio va imposto prima di qualunque trasformazione.

Consideriamo:

$$
\left|\frac{x-1}{x+2}\right|\le 3.
$$

Il dominio è:

$$
x+2\neq 0
$$

cioè:

$$
x\neq -2.
$$

Poiché $3>0$, la disequazione equivale a:

$$
-3\le \frac{x-1}{x+2}\le 3.
$$

Questa doppia disequazione si risolve come sistema:

$$
\begin{cases}
\frac{x-1}{x+2}\ge -3 \\
\frac{x-1}{x+2}\le 3 \\
x\neq -2
\end{cases}
$$

Studiamo la prima disequazione:

$$
\frac{x-1}{x+2}+3\ge 0.
$$

Riduciamo a denominatore comune:

$$
\frac{x-1+3x+6}{x+2}\ge 0.
$$

Quindi:

$$
\frac{4x+5}{x+2}\ge 0.
$$

I punti critici sono:

$$
x=-2,\quad x=-\frac{5}{4}.
$$

Lo studio del segno dà:

$$
x\in(-\infty,-2)\cup\left[-\frac{5}{4},+\infty\right).
$$

Studiamo la seconda disequazione:

$$
\frac{x-1}{x+2}-3\le 0.
$$

Riduciamo a denominatore comune:

$$
\frac{x-1-3x-6}{x+2}\le 0.
$$

Quindi:

$$
\frac{-2x-7}{x+2}\le 0.
$$

I punti critici sono:

$$
x=-2,\quad x=-\frac{7}{2}.
$$

Lo studio del segno dà:

$$
x\in\left(-\infty,-\frac{7}{2}\right]\cup(-2,+\infty).
$$

Intersechiamo le condizioni:

$$
\left[(-\infty,-2)\cup\left[-\frac{5}{4},+\infty\right)\right]\cap\left[\left(-\infty,-\frac{7}{2}\right]\cup(-2,+\infty)\right].
$$

Otteniamo:

$$
S=\left(-\infty,-\frac{7}{2}\right]\cup\left[-\frac{5}{4},+\infty\right).
$$

> ⚠️ Il punto $x=-2$ non appartiene mai alla soluzione, anche se può scomparire durante qualche passaggio algebrico: annulla il denominatore dell'espressione iniziale.

### **15. Metodo alternativo: quadrato del valore assoluto**

Per disequazioni del tipo:

$$
|A(x)|\le c
$$

con $c\ge 0$, si può anche usare:

$$
|A(x)|\le c \Longleftrightarrow A(x)^2\le c^2.
$$

Questa trasformazione è lecita perché entrambi i membri sono non negativi.

Per esempio:

$$
|x-4|\le 2
$$

equivale a:

$$
(x-4)^2\le 4.
$$

Portiamo tutto a sinistra:

$$
x^2-8x+12\le 0.
$$

Fattorizziamo:

$$
(x-2)(x-6)\le 0.
$$

Quindi:

$$
x\in[2,6].
$$

Lo stesso risultato si ottiene dalla doppia disequazione:

$$
-2\le x-4\le 2.
$$

> 💡 Il metodo del quadrato è utile quando il confronto è tra due valori assoluti o quando la forma ottenuta è facilmente fattorizzabile.

### **16. Esercizio guidato d'esame**

Risolviamo:

$$
\left|\frac{x+1}{x-1}\right|>2.
$$

Prima di tutto imponiamo il dominio:

$$
x\neq 1.
$$

Poiché $2>0$, la disequazione equivale a:

$$
\frac{x+1}{x-1}<-2
\quad \text{oppure} \quad
\frac{x+1}{x-1}>2.
$$

Prima disequazione:

$$
\frac{x+1}{x-1}+2<0.
$$

Riduciamo:

$$
\frac{x+1+2x-2}{x-1}<0.
$$

Quindi:

$$
\frac{3x-1}{x-1}<0.
$$

I punti critici sono:

$$
x=\frac{1}{3},\quad x=1.
$$

Lo studio del segno dà:

$$
x\in\left(\frac{1}{3},1\right).
$$

Seconda disequazione:

$$
\frac{x+1}{x-1}-2>0.
$$

Riduciamo:

$$
\frac{x+1-2x+2}{x-1}>0.
$$

Quindi:

$$
\frac{-x+3}{x-1}>0.
$$

I punti critici sono:

$$
x=1,\quad x=3.
$$

Lo studio del segno dà:

$$
x\in(1,3).
$$

Unendo i due casi:

$$
S=\left(\frac{1}{3},1\right)\cup(1,3).
$$

### **17. Errori frequenti**

#### **17.1 Eliminare il valore assoluto senza distinguere i casi**

Scrivere:

$$
|x-2|=x-2
$$

per ogni $x$ è falso. Vale solo per $x\ge 2$. Per $x<2$ vale:

$$
|x-2|=-x+2.
$$

#### **17.2 Dimenticare che $|A(x)|$ non è mai negativo**

L'equazione:

$$
|x^2+1|=-3
$$

non ha soluzioni, senza bisogno di calcoli, perché il primo membro è sempre non negativo.

#### **17.3 Confondere intervallo interno ed esterno**

Le disequazioni:

$$
|x-a|<r
$$

e

$$
|x-a|>r
$$

hanno comportamenti opposti:

$$
|x-a|<r \Longleftrightarrow a-r<x<a+r
$$

mentre:

$$
|x-a|>r \Longleftrightarrow x<a-r \quad \text{oppure} \quad x>a+r.
$$

#### **17.4 Perdere il dominio nelle frazioni**

In:

$$
\left|\frac{x-1}{x+2}\right|\le 3
$$

il punto $x=-2$ è escluso già dall'inizio. Nessun passaggio successivo può reintrodurlo.

### **18. Collegamento con i moduli successivi**

Il valore assoluto tornerà in modo sistematico:

- negli intorni, con scritture del tipo $|x-a|<\delta$;
- nei limiti di funzioni, con $|f(x)-L|<\varepsilon$;
- nelle successioni, con $|a_n-L|<\varepsilon$;
- nelle serie, con la convergenza assoluta;
- negli integrali impropri, con criteri di confronto e convergenza assoluta.

Per questo è importante non considerarlo solo come uno strumento per risolvere equazioni: è il linguaggio matematico della distanza e della stima.

### **19. Riepilogo**

> ✅ Il valore assoluto misura una distanza e si studia tramite una definizione a tratti.

Punti fondamentali:

- $|x|$ è sempre non negativo;
- $|x|=0$ se e solo se $x=0$;
- $|a-b|$ è la distanza tra $a$ e $b$;
- $|x+y|\le |x|+|y|$ è la disuguaglianza triangolare;
- $|A(x)|<c$ con $c>0$ equivale a $-c<A(x)<c$;
- $|A(x)|>c$ con $c>0$ equivale a $A(x)<-c$ oppure $A(x)>c$;
- nelle espressioni razionali il dominio va sempre imposto prima;
- il metodo a tratti è lo strumento più generale.
