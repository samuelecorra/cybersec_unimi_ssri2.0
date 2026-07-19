# **M1 UD1 Lezione 5 - Circonferenza nel piano cartesiano**

### **1. Perché studiare la circonferenza**

La circonferenza collega algebra e geometria in modo particolarmente trasparente: una condizione di distanza diventa un'equazione di secondo grado in due variabili. Questo passaggio prepara la lettura dei grafici, lo studio di regioni del piano, la nozione di intorno e la distinzione tra una relazione implicita e il grafico di una funzione.

In questa lezione useremo continuamente tre strumenti già introdotti: la distanza tra due punti, il completamento del quadrato e la distanza di un punto da una retta.

### **2. Definizione geometrica ed equazione canonica**

Siano $C=(\alpha,\beta)$ un punto fissato e $r>0$ un numero reale. La **circonferenza** di centro $C$ e raggio $r$ è il luogo geometrico dei punti $P=(x,y)$ la cui distanza da $C$ è uguale a $r$:

$$
d(P,C)=r.
$$

Usando la formula della distanza:

$$
\sqrt{(x-\alpha)^2+(y-\beta)^2}=r.
$$

Entrambi i membri sono non negativi, quindi possiamo elevarli al quadrato senza introdurre soluzioni estranee:

$$
(x-\alpha)^2+(y-\beta)^2=r^2.
$$

Questa è l'**equazione canonica** della circonferenza. Il centro si legge con segno opposto rispetto ai termini nelle parentesi, mentre il raggio è la radice quadrata del secondo membro.

> 📌 Nell'equazione $(x-\alpha)^2+(y-\beta)^2=r^2$, il centro è $(\alpha,\beta)$ e il raggio è $r>0$.

<!-- TODO FIGURA:
Piano cartesiano con circonferenza di centro C=(alpha,beta) e raggio r.
Evidenziare un punto P=(x,y), il segmento CP e la relazione
(x-alpha)^2+(y-beta)^2=r^2.
Obiettivo didattico: derivazione geometrica dell'equazione canonica.
-->

### **3. Circonferenza, disco e disequazioni**

La circonferenza è soltanto il bordo. Il **disco** comprende invece una regione del piano.

Il disco aperto di centro $C$ e raggio $r$ è:

$$
D(C,r)=\left\{(x,y)\in\mathbb{R}^2:(x-\alpha)^2+(y-\beta)^2<r^2\right\}.
$$

Il disco chiuso è:

$$
\overline{D}(C,r)=\left\{(x,y)\in\mathbb{R}^2:(x-\alpha)^2+(y-\beta)^2\le r^2\right\}.
$$

La disuguaglianza stretta esclude il bordo; quella non stretta include la circonferenza. Analogamente:

$$
(x-\alpha)^2+(y-\beta)^2>r^2
$$

descrive i punti esterni, escluso il bordo, mentre:

$$
(x-\alpha)^2+(y-\beta)^2\ge r^2
$$

descrive l'esterno insieme alla circonferenza.

<!-- TODO FIGURA:
Confronto affiancato tra circonferenza, disco aperto e disco chiuso.
Usare lo stesso centro C e lo stesso raggio r; distinguere bordo escluso e incluso.
Obiettivo didattico: separare il luogo di uguaglianza dalla regione definita da una disequazione.
-->

Questi insiemi saranno ripresi nel linguaggio degli intorni: un disco aperto rappresenta l'insieme dei punti che distano meno di $r$ dal centro.

### **4. Lettura dell'equazione canonica**

La forma canonica consente di ricavare subito centro, raggio, simmetrie e possibili intersezioni con gli assi.

#### **4.1 Centro nell'origine**

Se $C=(0,0)$, l'equazione diventa:

$$
x^2+y^2=r^2.
$$

La figura è simmetrica rispetto all'asse $x$, all'asse $y$ e all'origine. Infatti l'equazione non cambia sostituendo $x$ con $-x$, $y$ con $-y$, oppure entrambi.

#### **4.2 Centro traslato**

L'equazione:

$$
(x-3)^2+(y+2)^2=25
$$

rappresenta la circonferenza di centro:

$$
C=(3,-2)
$$

e raggio:

$$
r=5.
$$

Il segno della seconda coordinata è $-2$, non $+2$, perché $y+2=y-(-2)$.

Un esempio con radicali è:

$$
(x+2)^2+(y-\sqrt{3})^2=7.
$$

Il centro è $(-2,\sqrt{3})$ e il raggio è $\sqrt{7}$.

#### **4.3 Intersezioni con gli assi**

Per trovare le intersezioni con l'asse $x$ si pone $y=0$; per quelle con l'asse $y$ si pone $x=0$.

Consideriamo:

$$
(x-1)^2+(y-2)^2=9.
$$

Sull'asse $x$:

$$
(x-1)^2+4=9,
$$

quindi:

$$
(x-1)^2=5
$$

e i punti sono $(1-\sqrt{5},0)$ e $(1+\sqrt{5},0)$.

Sull'asse $y$:

$$
1+(y-2)^2=9,
$$

da cui $y=2\pm2\sqrt{2}$.

### **5. Posizione di un punto**

Dato $P=(x_0,y_0)$, poniamo:

$$
s=(x_0-\alpha)^2+(y_0-\beta)^2.
$$

Il confronto con $r^2$ determina la posizione:

- se $s<r^2$, il punto è interno;
- se $s=r^2$, il punto appartiene alla circonferenza;
- se $s>r^2$, il punto è esterno.

Per la circonferenza $(x-1)^2+(y+1)^2=10$, il punto $P=(4,0)$ appartiene perché:

$$
(4-1)^2+(0+1)^2=9+1=10.
$$

Il confronto usa i quadrati delle distanze, evitando radici non necessarie.

### **6. Equazione generale e completamento dei quadrati**

Una circonferenza può presentarsi nella forma:

$$
x^2+y^2+ax+by+c=0.
$$

I coefficienti di $x^2$ e $y^2$ devono essere uguali e non devono comparire termini $xy$. Dopo aver eventualmente diviso l'intera equazione per il coefficiente comune, completiamo i quadrati:

$$
x^2+ax=\left(x+\frac{a}{2}\right)^2-\frac{a^2}{4},
$$

$$
y^2+by=\left(y+\frac{b}{2}\right)^2-\frac{b^2}{4}.
$$

Sostituendo nell'equazione generale:

$$
\left(x+\frac{a}{2}\right)^2
+
\left(y+\frac{b}{2}\right)^2
=
\frac{a^2+b^2}{4}-c.
$$

Quindi:

$$
C=\left(-\frac{a}{2},-\frac{b}{2}\right),
\qquad
r^2=\frac{a^2+b^2}{4}-c.
$$

#### **6.1 I tre casi possibili**

Poniamo $\rho^2=\frac{a^2+b^2}{4}-c$.

- Se $\rho^2>0$, l'equazione rappresenta una circonferenza reale ordinaria di raggio $r=\sqrt{\rho^2}$.
- Se $\rho^2=0$, l'unico punto è il centro: si parla di circonferenza degenere.
- Se $\rho^2<0$, non esistono punti reali, perché una somma di quadrati non può essere negativa.

> ⚠️ Un valore negativo di $r^2$ non produce un raggio reale: l'equazione non rappresenta alcun punto del piano reale.

#### **6.2 Esempio completo**

Portiamo in forma canonica:

$$
x^2+y^2-6x+4y-12=0.
$$

Raggruppiamo i termini:

$$
(x^2-6x)+(y^2+4y)=12.
$$

Completiamo i quadrati aggiungendo $9$ e $4$ a entrambi i membri:

$$
(x^2-6x+9)+(y^2+4y+4)=12+9+4.
$$

Otteniamo:

$$
(x-3)^2+(y+2)^2=25.
$$

Il centro è $(3,-2)$ e il raggio è $5$.

Invece:

$$
x^2+y^2-2x+4y+5=0
$$

diventa $(x-1)^2+(y+2)^2=0$ e rappresenta il solo punto $(1,-2)$. L'equazione:

$$
x^2+y^2+2x-4y+10=0
$$

diventa $(x+1)^2+(y-2)^2=-5$ e non ha punti reali.

### **7. Determinare una circonferenza**

La forma generale contiene tre parametri $a,b,c$. In assenza di informazioni sul centro o sul raggio servono quindi, in generale, tre condizioni indipendenti.

#### **7.1 Centro e raggio**

Centro $C=(-1,3)$ e raggio $2$ danno direttamente:

$$
(x+1)^2+(y-3)^2=4.
$$

#### **7.2 Centro e un punto**

Se $C=(2,-1)$ e la circonferenza passa per $P=(5,3)$, allora:

$$
r^2=(5-2)^2+(3+1)^2=25.
$$

L'equazione è:

$$
(x-2)^2+(y+1)^2=25.
$$

#### **7.3 Estremi di un diametro**

Siano $A=(-1,2)$ e $B=(5,4)$. Il centro è il punto medio:

$$
C=\left(2,3\right).
$$

Poiché $AB=2\sqrt{10}$, il raggio è $\sqrt{10}$. Quindi:

$$
(x-2)^2+(y-3)^2=10.
$$

#### **7.4 Tre punti non allineati**

Cerchiamo la circonferenza passante per $(0,0)$, $(4,0)$ e $(0,2)$. Sostituiamo i punti in:

$$
x^2+y^2+ax+by+c=0.
$$

Otteniamo:

$$
\begin{cases}
c=0 \\
16+4a+c=0 \\
4+2b+c=0
\end{cases}
$$

e dunque $a=-4$, $b=-2$, $c=0$. L'equazione è:

$$
x^2+y^2-4x-2y=0,
$$

ossia:

$$
(x-2)^2+(y-1)^2=5.
$$

Tre punti allineati non determinano una circonferenza: non esiste una circonferenza ordinaria che li contenga tutti.

#### **7.5 Centro vincolato a una retta**

Supponiamo che il centro appartenga a $y=x$ e che la circonferenza passi per $A=(0,0)$ e $B=(4,0)$. Il centro deve essere equidistante da $A$ e $B$, quindi appartiene all'asse del segmento $AB$, cioè a $x=2$. Intersecando $x=2$ con $y=x$ troviamo $C=(2,2)$. Il raggio soddisfa:

$$
r^2=(2-0)^2+(2-0)^2=8,
$$

perciò:

$$
(x-2)^2+(y-2)^2=8.
$$

#### **7.6 Una condizione di tangenza**

Una circonferenza con centro nell'origine tangente alla retta $3x+4y-20=0$ ha raggio uguale alla distanza del centro dalla retta:

$$
r=\frac{|-20|}{\sqrt{3^2+4^2}}=4.
$$

La sua equazione è $x^2+y^2=16$.

### **8. Retta e circonferenza: metodo algebrico**

Per studiare le intersezioni si risolve il sistema formato dalle due equazioni. Dopo la sostituzione si ottiene un'equazione di secondo grado:

- $\Delta>0$: due punti, retta secante;
- $\Delta=0$: un punto doppio, retta tangente;
- $\Delta<0$: nessun punto reale, retta esterna.

Consideriamo $x^2+y^2=25$.

La retta $y=0$ dà:

$$
x^2=25,
$$

quindi incontra la circonferenza in $(-5,0)$ e $(5,0)$: è secante.

La retta verticale $x=5$ dà:

$$
25+y^2=25,
$$

quindi $y=0$ è una soluzione doppia: la retta è tangente in $(5,0)$.

La retta $y=6$ dà:

$$
x^2+36=25,
$$

che non ha soluzioni reali: la retta è esterna.

### **9. Retta e circonferenza: criterio geometrico**

Sia $d=d(C,\ell)$ la distanza del centro dalla retta. Il segmento perpendicolare dal centro alla retta è il più corto fra tutti i segmenti che collegano il centro a un punto della retta. Di conseguenza:

$$
\begin{cases}
d<r & \text{retta secante}, \\
d=r & \text{retta tangente}, \\
d>r & \text{retta esterna}.
\end{cases}
$$

Nel primo caso il piede della perpendicolare è interno alla circonferenza e la retta attraversa il bordo due volte. Nel secondo appartiene alla circonferenza ed è l'unico punto comune. Nel terzo ogni punto della retta è esterno.

Questo criterio e quello del discriminante descrivono lo stesso fenomeno: il primo confronta distanze, il secondo conta le soluzioni del sistema.

<!-- TODO FIGURA:
Una stessa circonferenza con tre rette: una secante in due punti, una tangente in un punto
e una esterna. Tracciare dal centro le perpendicolari alle rette e confrontare le distanze
con il raggio r. Obiettivo didattico: visualizzare i casi d<r, d=r e d>r.
-->

### **10. La retta tangente in un punto**

Sia $P$ un punto della circonferenza. La tangente in $P$ è perpendicolare al raggio $CP$.

Per giustificarlo, supponiamo che la tangente non sia perpendicolare al raggio. Il piede $H$ della perpendicolare condotta da $C$ alla retta sarebbe diverso da $P$ e soddisferebbe $CH<CP=r$. Quindi $H$ sarebbe interno al disco e la retta, attraversando il disco, incontrerebbe la circonferenza in due punti. Questo contraddice il fatto che sia tangente. Pertanto $CP$ è perpendicolare alla tangente.

#### **10.1 Metodo con il coefficiente angolare**

Per $x^2+y^2=25$ e $P=(3,4)$, il raggio $OP$ ha coefficiente angolare $4/3$. La tangente ha quindi coefficiente $-3/4$:

$$
y-4=-\frac{3}{4}(x-3).
$$

Equivalentemente:

$$
3x+4y=25.
$$

Se il raggio è orizzontale, la tangente è verticale; se il raggio è verticale, la tangente è orizzontale. Questi casi non devono essere forzati nella formula $m_1m_2=-1$.

#### **10.2 Formula della tangente**

Per la circonferenza:

$$
(x-\alpha)^2+(y-\beta)^2=r^2
$$

e un punto $P=(x_0,y_0)$ appartenente, il vettore normale alla tangente è il raggio:

$$
(x_0-\alpha,y_0-\beta).
$$

La retta passante per $P$ con tale normale è:

$$
(x_0-\alpha)(x-x_0)+(y_0-\beta)(y-y_0)=0.
$$

La formula non è da memorizzare isolatamente: deriva dalla perpendicolarità fra raggio e tangente.

#### **10.3 Tangenti da un punto esterno**

Da un punto interno non passa alcuna tangente reale; da un punto della circonferenza ne passa una; da un punto esterno ne passano due.

Per esempio, cerchiamo le tangenti a $x^2+y^2=25$ passanti per $Q=(13,0)$. Le rette non verticali per $Q$ hanno equazione:

$$
y=m(x-13),
$$

ossia $mx-y-13m=0$. Imponiamo che la distanza dall'origine sia $5$:

$$
\frac{|13m|}{\sqrt{m^2+1}}=5.
$$

Elevando al quadrato:

$$
169m^2=25(m^2+1),
$$

da cui $m=\pm\frac{5}{12}$. Le due tangenti sono:

$$
y=\frac{5}{12}(x-13)
$$

e:

$$
y=-\frac{5}{12}(x-13).
$$

### **11. Posizione reciproca di due circonferenze**

Siano $d$ la distanza tra i centri e $r_1,r_2$ i raggi.

- se $d>r_1+r_2$, sono esterne disgiunte;
- se $d=r_1+r_2$, sono tangenti esternamente;
- se $|r_1-r_2|<d<r_1+r_2$, sono secanti;
- se $d=|r_1-r_2|>0$, sono tangenti internamente;
- se $0<d<|r_1-r_2|$, una è interna all'altra senza contatto;
- se $d=0$ e $r_1\ne r_2$, sono concentriche;
- se $d=0$ e $r_1=r_2$, sono coincidenti.

<!-- TODO FIGURA:
Schema delle posizioni reciproche di due circonferenze: esterne disgiunte, tangenti
esternamente, secanti, tangenti internamente, una interna senza contatto, concentriche
e coincidenti. Indicare d, r1 e r2. Obiettivo didattico: associare ogni figura al confronto
fra d, r1+r2 e |r1-r2|.
-->

### **12. La circonferenza come relazione implicita**

L'equazione:

$$
x^2+y^2=r^2
$$

non rappresenta globalmente il grafico di una funzione $y=f(x)$. Per ogni $x$ con $-r<x<r$ esistono infatti due ordinate:

$$
y=\sqrt{r^2-x^2}
$$

e:

$$
y=-\sqrt{r^2-x^2}.
$$

Una funzione deve associare a ogni elemento del dominio una sola immagine. L'intera circonferenza fallisce quindi il test della retta verticale.

La semicirconferenza superiore:

$$
f(x)=\sqrt{r^2-x^2}
$$

ha dominio $[-r,r]$ e immagine $[0,r]$. La semicirconferenza inferiore:

$$
g(x)=-\sqrt{r^2-x^2}
$$

ha lo stesso dominio e immagine $[-r,0]$. Ciascun ramo, preso separatamente, è il grafico di una funzione.

Nel caso traslato:

$$
(x-\alpha)^2+(y-\beta)^2=r^2,
$$

i due rami sono:

$$
y=\beta\pm\sqrt{r^2-(x-\alpha)^2},
$$

con dominio $[\alpha-r,\alpha+r]$.

> ⚠️ Quando si isola $y$, dimenticare il segno negativo elimina metà della circonferenza.

<!-- TODO FIGURA:
Circonferenza x^2+y^2=r^2 divisa nei grafici y=sqrt(r^2-x^2) e
y=-sqrt(r^2-x^2). Aggiungere alcune rette verticali che incontrano l'unione in due punti.
Obiettivo didattico: mostrare perché i singoli rami sono funzioni e la circonferenza intera no.
-->

### **13. Esercizio misto**

Data la circonferenza:

$$
x^2+y^2-4x+6y-12=0,
$$

determiniamo centro, raggio e posizione della retta $x=5$.

Completiamo i quadrati:

$$
(x-2)^2+(y+3)^2=25.
$$

Quindi $C=(2,-3)$ e $r=5$. La distanza del centro dalla retta verticale $x=5$ è:

$$
d=|2-5|=3<5.
$$

La retta è secante. Sostituendo $x=5$:

$$
9+(y+3)^2=25,
$$

da cui:

$$
y+3=\pm4.
$$

I punti di intersezione sono $(5,1)$ e $(5,-7)$. Il metodo geometrico e quello algebrico concordano.

### **14. Errori frequenti**

Gli errori seguenti derivano quasi sempre dalla perdita del significato geometrico dell'equazione.

#### **14.1 Confondere circonferenza e disco**

L'uguaglianza descrive il bordo; una disequazione descrive una regione interna o esterna.

#### **14.2 Leggere il centro con il segno sbagliato**

In $(x+2)^2+(y-3)^2=9$ il centro è $(-2,3)$.

#### **14.3 Accettare un raggio quadrato negativo**

Se il completamento dei quadrati produce una somma di quadrati uguale a un numero negativo, non esistono punti reali.

#### **14.4 Dividere solo alcuni termini**

Se i coefficienti di $x^2$ e $y^2$ sono entrambi $2$, bisogna dividere **tutta** l'equazione per $2$, compresi termini lineari e termine noto.

#### **14.5 Trattare l'intera circonferenza come funzione**

La relazione implicita assegna in genere due valori di $y$ allo stesso $x$; solo i due rami separati sono funzioni.

#### **14.6 Ignorare le rette verticali**

Una retta $x=k$ non ha coefficiente angolare finito. Nei sistemi e nei problemi di tangenza va trattata direttamente.

#### **14.7 Confondere tangenza e intersezione**

Una retta tangente ha un solo punto comune, corrispondente a una radice doppia. Una secante ha due punti distinti.

### **15. Collegamenti con l'Analisi**

La circonferenza prepara diversi concetti successivi:

- il disco aperto anticipa gli intorni e la descrizione metrica della vicinanza;
- le semicirconferenze mostrano come il dominio derivi da una condizione di esistenza della radice;
- l'equazione completa è una relazione implicita, mentre i rami sono funzioni;
- le traslazioni e le simmetrie aiutano a leggere grafici senza rifare ogni calcolo;
- secanti e tangenti preparano la tangente a una curva e il passaggio al limite;
- il confronto fra distanze traduce un problema geometrico in una disequazione.

Ellisse e iperbole non sono necessarie per chiudere questo blocco: verranno introdotte solo se emergerà un uso concreto nei moduli successivi.

### **16. Riepilogo operativo**

> ✅ Per affrontare un problema sulla circonferenza:

1. riconosci la forma canonica o completa i quadrati;
2. controlla che $r^2\ge0$ e ricorda che una circonferenza ordinaria richiede $r>0$;
3. traduci appartenenza e posizione in un confronto di distanze;
4. per una retta usa il sistema oppure confronta $d(C,\ell)$ con $r$;
5. per una tangente sfrutta la perpendicolarità al raggio;
6. quando isoli $y$, considera entrambi i rami e specifica il dominio.
