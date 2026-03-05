## **Lezione 3: Definizione formale di limite**

### **1. Perché serve una definizione formale**

Nelle due lezioni precedenti abbiamo capito, tramite esempi e grafici, che calcolare un limite significa descrivere **che cosa accade ai valori della funzione** quando:

- $x$ si avvicina a un certo numero reale $x_0$,
    
- oppure quando $x$ “scappa” verso $+\infty$ o verso $-\infty$.

L’idea intuitiva, quindi, è chiara: guardiamo **come si comporta la $y=f(x)$** mentre **ci avviciniamo** al punto.

![](../imgs/Pasted%20image%2020251218112942.png)

A questo punto però sorge un problema naturale: l’intuizione non basta, perché in matematica dobbiamo essere in grado di dire le cose in modo:

- **preciso**,
    
- **senza ambiguità**,
    
- **valido in ogni caso**, anche quando il grafico non lo abbiamo davanti.

Per questo introduciamo la **definizione formale di limite**.

Per arrivarci, però, ci serve prima un concetto preliminare: **l’intorno**.

---
### **2. Intorno di un numero reale: definizione**

Sia $x_0 \in \mathbb{R}$.

Chiamiamo **intorno sferico** (o più semplicemente **intorno**) di centro $x_0$ e raggio $\delta$ l’intervallo aperto:

$$

U_\delta(x_0) = (x_0 - \delta \ , \ x_0 + \delta)

$$

Qui:

- $x_0$ è il **centro**,
    
- $\delta>0$ è il **raggio** (cioè quanto “larga” è la zona che stiamo prendendo attorno a $x_0$),
    
- l’intervallo è **aperto**: non include gli estremi.

![](../imgs/Pasted%20image%2020251218113159.png)

Se lo visualizzi sul grafico, $U_\delta(x_0)$ è semplicemente una “fascia” sull’asse $x$ attorno a $x_0$.

Più $\delta$ è piccolo, più l’intorno è “stretto”, cioè più rappresenta una zona **vicinissima** a $x_0$.

---
### **3. Intorni anche sull’asse $y$**

Lo stesso concetto vale anche per un valore $L$ sull’asse $y$.

Dato $L \in \mathbb{R}$ e un raggio $\varepsilon>0$, un intorno di $L$ è:

$$

V_\varepsilon(L) = (L - \varepsilon,; L + \varepsilon)

$$

Quindi:

- l’intorno su $x$ misura “quanto vicino voglio stare a $x_0$”,
    
- l’intorno su $y$ misura “quanto vicino voglio stare a $L$”.

Questa è già l’idea di fondo del limite: **vicinanza su $x$ produce vicinanza su $y$**.

---
### **4. Intorni di $-\infty$ e $+\infty$**

Per parlare di limiti all’infinito, dobbiamo anche definire che cosa significa “intorno di infinito”.

**Intorno di $-\infty$**: un intervallo del tipo

$$

(-\infty,; K)

$$
con $K \in \mathbb{R}$.

È l’insieme dei reali tali che:

$$

x < K

$$

![](../imgs/Pasted%20image%2020251218113307.png)

Graficamente lo immagini come una **semiretta verso sinistra**.

**Intorno di $+\infty$**: un intervallo del tipo

$$

(K,; +\infty)

$$
con $K \in \mathbb{R}$.

È l’insieme dei reali tali che:

$$

x > K

$$

![](../imgs/Pasted%20image%2020251218113326.png)

Graficamente lo immagini come una **semiretta verso destra**.

---
### **5. Punto di accumulazione: quando ha senso fare un limite**

Sia $f: A \to \mathbb{R}$, con $A \subseteq \mathbb{R}$.

Per poter parlare del limite per $x \to x_0$, il punto $x_0$ deve essere un **punto di accumulazione** per $A$.

Intuitivamente significa:

> attorno a $x_0$ ci sono punti del dominio arbitrariamente vicini a $x_0$.

Cioè posso avvicinarmi quanto voglio a $x_0$ restando dentro al dominio (anche se magari $x_0$ stesso non appartiene al dominio).

---
### **6. Definizione formale di limite con gli intorni**

Supponiamo che:

- $f: A \to \mathbb{R}$,
    
- $x_0$ sia un punto di accumulazione di $A$,
    
- $L \in \mathbb{R}$.

Si dice che:

$$

\lim_{x \to x_0} f(x) = L

$$

se vale questa condizione:

> per ogni intorno di $L$ esiste un intorno di $x_0$ tale che, scegliendo $x$ nel dominio dentro quell’intorno (ma diverso da $x_0$), il valore $f(x)$ cade dentro l’intorno di $L$.

Scritta in modo preciso:

$$
\forall \varepsilon > 0 \;\; \exists \delta > 0 \;
\mid \; \forall{x} \in A,
\;
0 < |x - x_0| < \delta
\;\Rightarrow\;
|f(x) - L| < \varepsilon
$$

Questa è la classica forma $\varepsilon$–$\delta$, ma è esattamente la stessa idea degli intorni.
L'originale da cui ho attinto è:

![](../imgs/Pasted%20image%2020251218114103.png)

La riscrivo qui sotto in markdown affinché sia copiabile:
Sia $f : A \subseteq \mathbb{R} \to \mathbb{R}$ una funzione e sia $x_0 \in \mathbb{R}$ un punto di accumulazione di A.

Si dice che
$$\lim_{x \to x_0} f(x) = L \in \mathbb{R}$$

se per ogni intorno $V_\varepsilon(L)$ di L esiste un intorno $U_\delta(x_0)$ di $x_0$ tale che

$$x \in U_\delta(x_0) \cap A \setminus \{x_0\} \;\Rightarrow\; f(x) \in V_\varepsilon(L)$$

Questa definizione è equivalente alla seguente formulazione $ε–δ$:

Il punto centrale della definizione con gli intorni appena vista è descrivere il limite come una **relazione di vicinanza**.

Dire che il limite di una funzione in un punto è $L$ significa dire questo:

> ogni volta che io prendo un valore **molto vicino a** $L$ sull’asse delle ordinate, posso sempre trovare una zona **sufficientemente vicina a** $x_0$ sull’asse delle ascisse tale che, scegliendo $x$ in quella zona (escluso il punto $x_0$), i valori della funzione cadono **dentro quella zona vicina a** $L$.

Il focus quindi **non è il punto**, ma **l’intorno** del punto.
Non si guarda cosa fa la funzione **in** $x_0$, ma cosa fa **arbitrariamente vicino** a $x_0$.

Questa definizione mette in evidenza un’idea molto generale e astratta: **la funzione trasforma punti vicini a** $x_0$ **in valori vicini a** $L$.


La definizione $ε–δ$ è la stessa idea, ma resa **quantitativa**.

Il focus qui è il seguente:

> per quanto io voglia che i valori della funzione siano vicini a L, esiste sempre un modo per imporre quanto devo avvicinarmi a x_0 affinché questo accada.

In altre parole:

- ε rappresenta **la precisione richiesta sul valore della funzione**;
    
- δ rappresenta **la precisione richiesta sull’argomento**.

La definizione afferma che:

- ogni richiesta di precisione su $y (ε)$
    
- può essere soddisfatta scegliendo una precisione adeguata su $x (δ)$.

Il focus quindi è il **controllo**: controllo dell’errore sull’uscita tramite il controllo sull’ingresso.

---
Le due definizioni dicono **esattamente la stessa cosa**, ma da due punti di vista diversi:

- la definizione con intorni è **geometrica e concettuale**;
    
- la definizione ε–δ è **operativa e quantitativa**.

La prima è ideale per **capire** che cos’è un limite.
La seconda è ideale per **dimostrare** che un limite vale davvero.

---
#### **Idea chiave da portarsi all’esame**

Il limite non è:

- il valore della funzione in un punto;
    
- una sostituzione.

Il limite è:

> una relazione tra **vicinanza sull’asse x** e **vicinanza sull’asse y**, valida qualunque sia il livello di precisione richiesto.

---
### **7. Traduzione “umana” della definizione**

Questa definizione sembra pesante, ma l’idea è semplice se la leggi così:

- io scelgo quanto voglio che $f(x)$ stia vicino a $L$ → scelgo $\varepsilon$ (un intorno molto stretto di $L$),
    
- tu mi devi garantire che esiste un modo per prendere $x$ abbastanza vicino a $x_0$ → scegliendo un $\delta$,
    
- e allora, ogni volta che $x$ sta entro quella distanza $\delta$ da $x_0$ (senza essere $x_0$),
    
- automaticamente $f(x)$ finisce entro distanza $\varepsilon$ da $L$.

In breve:

> **quanto vuoi vicino a $L$?**

> io ti dico **quanto devi essere vicino a $x_0$** per ottenerlo.

---
### **8. Interpretazione grafica: perché serve “togliere $x_0$”**

Graficamente succede questo:

![](../imgs/Pasted%20image%2020251218115624.png)

- scelgo una “fascia orizzontale” attorno a $y=L$ (intorno rosso),
    
- allora devo poter trovare una “fascia verticale” attorno a $x=x_0$ (intorno verde),
    
- tale che tutti i punti del grafico che hanno $x$ nella fascia verde (escluso eventualmente $x_0$) abbiano $y$ dentro la fascia rossa.

Il dettaglio cruciale è proprio questo:

$$

x \in U_\delta(x_0)\setminus{x_0}

$$

Perché? Perché **il limite non dipende dal valore della funzione nel punto**, ma da come si comporta **vicino** al punto.

---
### **9. Prima osservazione fondamentale: $f(x_0)$ non conta**

Dal punto di vista del limite:

- la funzione può essere definita in $x_0$ e valere $L$,
    
- può essere definita in $x_0$ e valere un numero diverso da $L$,
    
- oppure può non essere definita in $x_0$.

In tutti e tre i casi, **il limite può comunque essere $L$**.

Quello che conta è soltanto:

> come si comporta $f(x)$ quando $x$ si avvicina a $x_0$ senza essere $x_0$.

---
### **10. Seconda osservazione fondamentale: l’intorno di $x_0$ non è unico**

La definizione dice:

> esiste un intorno di $x_0$…

Quindi non ce n’è uno solo.

Se uno funziona, allora funzionano anche intorni più piccoli.

![](../imgs/Pasted%20image%2020251218115744.png)

Graficamente: se la fascia verde “grande” va bene, anche una fascia più stretta (gialla) andrà bene.

---
### **11. Come si estende la definizione ai casi con infinito**

Ora dobbiamo coprire tutti gli altri casi che abbiamo visto:

- $x \to +\infty$ o $x \to -\infty$,
    
- limite uguale a $+\infty$ o $-\infty$.

La chiave è introdurre la **retta reale estesa**:

$$

\overline{\mathbb{R}} = \mathbb{R}\cup{-\infty,+\infty}

$$

Cosa significa?

Che adesso permettiamo a:

- $x_0$ di essere un reale oppure $\pm\infty$,
    
- $L$ di essere un reale oppure $\pm\infty$.

La definizione “con gli intorni” resta la stessa, cambia solo il tipo di intorno da usare (intorno finito oppure semiretta).

---
### **12. Esempio: $\lim_{x\to +\infty} f(x) = +\infty$ (forma esplicita)**

Prendiamo il caso:

- $x \to +\infty$,
    
- e il limite “esplode” a $+\infty$.

Allora la definizione formale diventa:

$$

\lim_{x\to +\infty} f(x)=+\infty

$$
se e solo se:

$$

\forall M>0 \; \exists K>0 \; \mid \; x\in A, \; x>K \; \text{allora } f(x)>M

$$

Questa è la versione con $M$ e $K$ che, a livello intuitivo, avevamo già anticipato nelle lezioni precedenti.

---
### **13. Interpretazione grafica dell’esempio $+\infty$**

Questa definizione dice:

- scegli una quota $M$ sull’asse $y$ anche altissima,
    
- io devo poterti trovare un punto $K$ sull’asse $x$,
    
- tale che per ogni $x$ più a destra di $K$ il grafico sta sempre sopra $M$.

![](../imgs/Pasted%20image%2020251218115914.png)

L’intorno di $x_0=+\infty$ è la semiretta:
$$

(K, \ +\infty)

$$
e l’intorno di $+\infty$ su $y$ è:
$$

(M,\ +\infty)

$$

![](../imgs/Pasted%20image%2020251218115946.png)

Quindi la definizione con intorni “traduce” in modo automatico tutte le casistiche.

---
### **14. Conclusione**

Con questa lezione abbiamo completato l’introduzione ai limiti, passando:

- dall’interpretazione grafica intuitiva,
    
- alla formalizzazione rigorosa tramite intorni,
    
- fino alla forma $\varepsilon$–$\delta$ (e alle varianti con $M$ e $K$ per l’infinito).
  
È normale che questa definizione sembri una delle più difficili di Analisi 1: richiede di tenere in testa contemporaneamente:

- cosa scelgo io ($\varepsilon$ o $M$),
    
- cosa deve esistere ($\delta$ o $K$),
    
- e il legame logico “se… allora…”.


Nelle prossime lezioni, però, inizieremo a calcolare limiti concretamente e vedrai che, anche se la definizione è pesante, le tecniche di calcolo diventano via via molto più naturali.