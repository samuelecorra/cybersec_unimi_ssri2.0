# **M1 UD3 Lezione 2 - Razionali Fratte**

### **1. Introduzione**

Una disequazione razionale fratta contiene un rapporto tra polinomi:

$$
\frac{P(x)}{Q(x)}\ \square\ 0
$$

dove:

$$
\square\in\{>,\ge,<,\le\}
$$

e $P(x)$, $Q(x)$ sono polinomi reali.

Rispetto alle disequazioni polinomiali compare una difficoltà nuova:

> 📌 Il denominatore non può mai essere zero.

La fattorizzazione completa sviluppata in `L0E - Radici, molteplicità e fattorizzazione completa.md` permette di individuare sistematicamente zeri del numeratore, punti esclusi e fattori comuni, conservando sempre il dominio originario.

Questa è una condizione strutturale, non un dettaglio formale. Anche quando la disequazione contiene $\ge$ o $\le$, i valori che annullano il denominatore restano esclusi.

---

### **2. Dominio della frazione razionale**

Una frazione razionale:

$$
\frac{P(x)}{Q(x)}
$$

è definita solo per:

$$
Q(x)\neq 0
$$

Il primo passo è quindi trovare gli zeri del denominatore ed escluderli.

Esempio:

$$
\frac{x+1}{x-3}
$$

è definita per:

$$
x-3\neq 0
$$

cioè:

$$
x\neq 3
$$

Il numero $3$ non potrà mai appartenere alla soluzione di una disequazione che contiene questa frazione.

#### **2.1 Equazioni razionali**

Lo stesso dominio governa le equazioni razionali. Consideriamo:

$$
\frac{2}{x-1}-\frac{1}{x+1}=1
$$

Le condizioni di esistenza sono $x\ne1$ e $x\ne-1$. Nel dominio possiamo moltiplicare per il minimo comune denominatore $(x-1)(x+1)$:

$$
2(x+1)-(x-1)=x^2-1
$$

Riducendo:

$$
x+3=x^2-1
$$

e quindi:

$$
x^2-x-4=0
$$

I candidati sono:

$$
x=\frac{1\pm\sqrt{17}}{2}
$$

Nessuno dei due coincide con $\pm1$, quindi entrambi appartengono al dominio e sono soluzioni. Moltiplicare per il denominatore è un passaggio equivalente soltanto dopo aver escluso i suoi zeri.

Se si semplifica un fattore comune, le esclusioni originarie restano valide. In particolare:

$$
\frac{x^2-1}{x-1}=2
$$

ha dominio $x\ne1$ e si riduce a $x+1=2$ soltanto in tale dominio. Il candidato $x=1$ viene quindi escluso e l'equazione non ha soluzioni.

---

### **3. Metodo generale**

Per risolvere:

$$
\frac{P(x)}{Q(x)}\ \square\ 0
$$

si procede così:

1. portare tutto a primo membro;
2. ridurre a un’unica frazione;
3. imporre il dominio, cioè $Q(x)\neq 0$;
4. fattorizzare numeratore e denominatore;
5. individuare zeri del numeratore e zeri del denominatore;
6. studiare il segno del rapporto;
7. scegliere gli intervalli compatibili con il verso;
8. includere solo gli zeri del numeratore quando il verso lo permette.

> ⚠️ Gli zeri del denominatore sono sempre esclusi, anche con $\ge$ e $\le$.

---

### **4. Segno di un quoziente**

Il segno di:

$$
\frac{A}{B}
$$

segue le stesse regole del prodotto:

- positivo se $A$ e $B$ hanno lo stesso segno;
- negativo se $A$ e $B$ hanno segno opposto;
- zero se $A=0$ e $B\neq 0$;
- non definito se $B=0$.

Per questo lo studio del segno di una frazione razionale è molto simile allo studio del segno di un prodotto, ma con attenzione speciale ai punti esclusi.

---

### **5. Esempio base**

Risolviamo:

$$
\frac{x-2}{x+1}>0
$$

Dominio:

$$
x+1\neq 0
$$

quindi:

$$
x\neq -1
$$

I punti critici sono:

$$
x=-1,\quad x=2
$$

Il punto $-1$ annulla il denominatore, mentre $2$ annulla il numeratore.

Studiamo gli intervalli:

$$
(-\infty,-1),\quad (-1,2),\quad (2,+\infty)
$$

Per $x<-1$:

$$
x-2<0,\quad x+1<0
$$

quindi:

$$
\frac{x-2}{x+1}>0
$$

Per $-1<x<2$:

$$
x-2<0,\quad x+1>0
$$

quindi:

$$
\frac{x-2}{x+1}<0
$$

Per $x>2$:

$$
x-2>0,\quad x+1>0
$$

quindi:

$$
\frac{x-2}{x+1}>0
$$

La disequazione è stretta, quindi $x=2$ non si include. Il punto $x=-1$ è comunque escluso.

La soluzione è:

$$
x\in(-\infty,-1)\cup(2,+\infty)
$$

---

### **6. Disequazioni con $\ge$ e $\le$**

Risolviamo:

$$
\frac{x-4}{x+2}\le 0
$$

Dominio:

$$
x\neq -2
$$

Punti critici:

$$
x=-2,\quad x=4
$$

Studiamo il segno:

- per $x<-2$, numeratore negativo e denominatore negativo, quindi rapporto positivo;
- per $-2<x<4$, numeratore negativo e denominatore positivo, quindi rapporto negativo;
- per $x>4$, numeratore positivo e denominatore positivo, quindi rapporto positivo.

La disequazione richiede $\le 0$, quindi prendiamo l’intervallo dove il rapporto è negativo e includiamo lo zero del numeratore:

$$
x\in(-2,4]
$$

Il punto $-2$ non è incluso perché annulla il denominatore.

---

### **7. Portare tutto a una sola frazione**

Molte disequazioni razionali non sono già nella forma:

$$
\frac{P(x)}{Q(x)}\ \square\ 0
$$

Per esempio:

$$
\frac{x+1}{x-2}\ge 1
$$

Non si risolve moltiplicando direttamente per $x-2$, perché il segno di $x-2$ dipende da $x$.

Portiamo tutto a sinistra:

$$
\frac{x+1}{x-2}-1\ge 0
$$

Riduciamo a denominatore comune:

$$
\frac{x+1-(x-2)}{x-2}\ge 0
$$

Semplifichiamo il numeratore:

$$
\frac{3}{x-2}\ge 0
$$

Dominio:

$$
x\neq 2
$$

Poiché il numeratore è positivo, il segno della frazione dipende solo dal denominatore:

$$
x-2>0
$$

quindi:

$$
x>2
$$

La soluzione è:

$$
(2,+\infty)
$$

---

### **8. Fattori comuni: semplificare senza perdere il dominio**

Consideriamo:

$$
\frac{(x-1)(x+2)}{x-1}\ge 0
$$

Il dominio originale impone:

$$
x-1\neq 0
$$

cioè:

$$
x\neq 1
$$

Per $x\neq 1$ possiamo semplificare:

$$
\frac{(x-1)(x+2)}{x-1}=x+2
$$

ma la disequazione non diventa semplicemente:

$$
x+2\ge 0
$$

senza memoria del dominio. Bisogna conservare l’esclusione:

$$
\begin{cases}
x+2\ge 0 \\
x\neq 1
\end{cases}
$$

Da $x+2\ge 0$ otteniamo:

$$
x\ge -2
$$

Escludendo $x=1$:

$$
x\in[-2,1)\cup(1,+\infty)
$$

> ⚠️ Semplificare un fattore comune è lecito solo se si conserva il dominio dell’espressione originale.

Geometricamente, un fattore cancellato può produrre un punto mancante nel grafico della formula semplificata. Questa interpretazione verrà formalizzata nei moduli sui limiti e sulla continuità.

---

### **9. Esempio completo con più fattori**

Risolviamo:

$$
\frac{(x+3)(x-1)}{(x-2)(x+4)}\ge 0
$$

Gli zeri del numeratore sono:

$$
x=-3,\quad x=1
$$

Gli zeri del denominatore sono:

$$
x=-4,\quad x=2
$$

Il dominio è:

$$
x\neq -4,\quad x\neq 2
$$

Ordiniamo tutti i punti critici:

$$
-4<-3<1<2
$$

Studiamo il segno sui cinque intervalli:

$$
(-\infty,-4),\quad (-4,-3),\quad (-3,1),\quad (1,2),\quad (2,+\infty)
$$

Scegliendo punti di prova:

- per $x=-5$, il rapporto è positivo;
- per $x=-3.5$, il rapporto è negativo;
- per $x=0$, il rapporto è positivo;
- per $x=1.5$, il rapporto è negativo;
- per $x=3$, il rapporto è positivo.

La disequazione richiede $\ge 0$, quindi prendiamo gli intervalli positivi e includiamo gli zeri del numeratore:

$$
x\in(-\infty,-4)\cup[-3,1]\cup(2,+\infty)
$$

I punti $-4$ e $2$ sono esclusi perché annullano il denominatore.

---

### **10. Molteplicità anche al denominatore**

Anche nei rapporti conta la molteplicità dei fattori.

Consideriamo:

$$
\frac{x-1}{(x+2)^2}>0
$$

Il dominio è:

$$
x\neq -2
$$

Il denominatore:

$$
(x+2)^2
$$

è positivo per ogni $x\neq -2$ e vale zero solo in $x=-2$, dove la frazione non è definita.

Quindi il segno dipende solo dal numeratore:

$$
x-1>0
$$

cioè:

$$
x>1
$$

La soluzione è:

$$
(1,+\infty)
$$

Il punto $-2$ non compare nella soluzione perché non appartiene comunque all’intervallo $x>1$, ma resta un punto escluso del dominio.

---

### **11. Disequazioni razionali e intervalli vuoti**

Può capitare che una disequazione razionale non abbia soluzioni.

Esempio:

$$
\frac{x^2+1}{(x-3)^2}<0
$$

Il numeratore:

$$
x^2+1
$$

è sempre positivo.

Il denominatore:

$$
(x-3)^2
$$

è positivo per ogni $x\neq 3$.

Quindi la frazione è sempre positiva nel suo dominio:

$$
\frac{x^2+1}{(x-3)^2}>0
$$

per ogni $x\neq 3$.

La disequazione:

$$
\frac{x^2+1}{(x-3)^2}<0
$$

non ha soluzioni:

$$
\varnothing
$$

---

### **12. Parametri ed errore tipico sul denominatore**

#### **12.1 Un parametro cambia l'ordine dei punti critici**

Studiamo:

$$
\frac{x-m}{x-1}\ge0
$$

Il dominio esclude sempre $x=1$, mentre $x=m$ è uno zero incluso. L'ordine dei due punti cambia con $m$:

$$
S_m=
\begin{cases}
(-\infty,m]\cup(1,+\infty) & m<1 \\
\mathbb{R}\setminus\{1\} & m=1 \\
(-\infty,1)\cup[m,+\infty) & m>1
\end{cases}
$$

Nel caso $m=1$ i fattori si semplificano, ma il punto $1$ resta escluso dal dominio originario.

#### **12.2 Perché non si elimina il denominatore alla cieca**

Dato:

$$
\frac{x+1}{x-2}>0
$$

non si può scrivere direttamente:

$$
x+1>0
$$

moltiplicando per $x-2$ senza discuterne il segno.

Infatti:

- se $x-2>0$, il verso resta lo stesso;
- se $x-2<0$, il verso si inverte;
- se $x-2=0$, l’espressione non è definita.

Il metodo corretto è lo studio del segno di numeratore e denominatore.

---

### **13. Riepilogo**

> ✅ Una disequazione razionale fratta si risolve come uno studio del segno, ma con una regola in più: gli zeri del denominatore sono sempre esclusi.

Schema operativo:

- portare tutto a sinistra;
- costruire una sola frazione;
- imporre il dominio;
- fattorizzare numeratore e denominatore;
- ordinare zeri e punti esclusi;
- studiare il segno;
- includere gli zeri del numeratore solo se il verso è largo;
- non includere mai gli zeri del denominatore.

Questo metodo sarà essenziale anche nello studio dei domini, dei limiti e degli asintoti delle funzioni razionali.
