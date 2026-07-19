# **M8 UD1 Lezione 3 - Definizione formale di limite**

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

Per il limite serve anche una seconda nozione: l’**intorno puntato** (o intorno bucato) di $x_0$, cioè l’intorno **privato del centro**:

$$
U_\delta^\ast(x_0) = U_\delta(x_0)\setminus\{x_0\} = (x_0-\delta,\ x_0)\cup(x_0,\ x_0+\delta)
$$

> 📌 L’intorno puntato è l’insieme dei punti "vicini a $x_0$ ma diversi da $x_0$". È l’oggetto giusto per il limite, perché il limite osserva la funzione **attorno** al punto e mai **nel** punto. Definiamo infine gli **intorni laterali**: l’intorno destro $(x_0,\ x_0+\delta)$ e l’intorno sinistro $(x_0-\delta,\ x_0)$, che useremo per i limiti laterali (Lezione 3C).

---
### **3. Intorni anche sull’asse $y$**

Lo stesso concetto vale anche per un valore $L$ sull’asse $y$.

Dato $L \in \mathbb{R}$ e un raggio $\varepsilon>0$, un intorno di $L$ è:

$$
V_\varepsilon(L) = (L - \varepsilon,\ L + \varepsilon)
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
(-\infty,\ K)
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
(K,\ +\infty)
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

**Definizione.** $x_0\in\mathbb{R}$ è punto di accumulazione di $A$ se **ogni** intorno puntato di $x_0$ contiene almeno un punto di $A$:

$$
\forall \delta>0:\quad U_\delta^\ast(x_0)\cap A \neq \varnothing
$$

Intuitivamente significa:

> attorno a $x_0$ ci sono punti del dominio arbitrariamente vicini a $x_0$, **diversi da $x_0$**.

Cioè posso avvicinarmi quanto voglio a $x_0$ restando dentro al dominio (anche se magari $x_0$ stesso non appartiene al dominio).

> 📌 Due fatti da non confondere:
>
> - un punto di accumulazione di $A$ **può non appartenere** ad $A$: per $A=(0,1)$ i punti $0$ e $1$ sono di accumulazione ma non stanno in $A$;
> - un punto che appartiene ad $A$ **può non essere** di accumulazione: se attorno a lui non ci sono altri punti di $A$ si chiama **punto isolato**, e lì la definizione di limite non ha senso (non ci sono $x\neq x_0$ su cui verificarla, quindi la condizione sarebbe vera "a vuoto" per qualunque $L$: adottiamo lo standard analitico di **non definire** il limite nei punti isolati).
>
> La classificazione completa dei punti rispetto a un insieme (interni, esterni, di frontiera, isolati, di accumulazione) è nella Lezione 3A.

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
\forall \varepsilon > 0 \;\; \exists \delta > 0 \;\;
\forall x \in A:
\;
0 < |x - x_0| < \delta
\;\Rightarrow\;
|f(x) - L| < \varepsilon
$$

Questa è la classica forma $\varepsilon$–$\delta$, ma è esattamente la stessa idea degli intorni.

> ⚠️ L'**ordine dei quantificatori** è essenziale e non si può scambiare:
>
> - prima si fissa $\varepsilon$ (la precisione richiesta su $y$), **poi** si trova $\delta$: quindi $\delta$ **può dipendere da $\varepsilon$** (e in generale dipende anche da $x_0$), e lo si sottolinea scrivendo $\delta=\delta(\varepsilon)$;
> - $\delta$ **non può dipendere da $x$**: il quantificatore $\forall x$ viene **dopo** $\exists\delta$, quindi lo stesso $\delta$ deve funzionare per **tutti** gli $x$ ammessi;
> - la condizione $0<|x-x_0|$ esclude esattamente il punto $x_0$: senza di essa staremmo imponendo anche $|f(x_0)-L|<\varepsilon$, cioè un vincolo sul **valore** della funzione nel punto, che con il limite non c'entra.
L'originale da cui ho attinto è:

![](../imgs/Pasted%20image%2020251218114103.png)

La riscrivo qui sotto in markdown affinché sia copiabile:
Sia $f : A \subseteq \mathbb{R} \to \mathbb{R}$ una funzione e sia $x_0 \in \mathbb{R}$ un punto di accumulazione di A.

Si dice che
$$\lim_{x \to x_0} f(x) = L \in \mathbb{R}$$

se per ogni intorno $V_\varepsilon(L)$ di L esiste un intorno $U_\delta(x_0)$ di $x_0$ tale che

$$x \in U_\delta(x_0) \cap A \setminus \{x_0\} \;\Rightarrow\; f(x) \in V_\varepsilon(L)$$

Questa definizione con gli intorni è **equivalente** alla formulazione $\varepsilon$–$\delta$ scritta sopra: dire $f(x)\in V_\varepsilon(L)$ è esattamente dire $|f(x)-L|<\varepsilon$, e dire $x\in U_\delta(x_0)\setminus\{x_0\}$ è esattamente dire $0<|x-x_0|<\delta$. Mettiamo a confronto i due punti di vista.

Il punto centrale della definizione con gli intorni appena vista è descrivere il limite come una **relazione di vicinanza**.

Dire che il limite di una funzione in un punto è $L$ significa dire questo:

> ogni volta che io prendo un valore **molto vicino a** $L$ sull’asse delle ordinate, posso sempre trovare una zona **sufficientemente vicina a** $x_0$ sull’asse delle ascisse tale che, scegliendo $x$ in quella zona (escluso il punto $x_0$), i valori della funzione cadono **dentro quella zona vicina a** $L$.

Il focus quindi **non è il punto**, ma **l’intorno** del punto.
Non si guarda cosa fa la funzione **in** $x_0$, ma cosa fa **arbitrariamente vicino** a $x_0$.

Questa definizione mette in evidenza un’idea molto generale e astratta: **la funzione trasforma punti vicini a** $x_0$ **in valori vicini a** $L$.


La definizione $\varepsilon$–$\delta$ è la stessa idea, ma resa **quantitativa**.

Il focus qui è il seguente:

> per quanto io voglia che i valori della funzione siano vicini a $L$, esiste sempre un modo per imporre quanto devo avvicinarmi a $x_0$ affinché questo accada.

In altre parole:

- $\varepsilon$ rappresenta **la precisione richiesta sul valore della funzione**;
    
- $\delta$ rappresenta **la precisione richiesta sull’argomento**.

La definizione afferma che:

- ogni richiesta di precisione su $y$ (cioè $\varepsilon$)
    
- può essere soddisfatta scegliendo una precisione adeguata su $x$ (cioè $\delta$).

Il focus quindi è il **controllo**: controllo dell’errore sull’uscita tramite il controllo sull’ingresso.

---
Le due definizioni dicono **esattamente la stessa cosa**, ma da due punti di vista diversi:

- la definizione con intorni è **geometrica e concettuale**;
    
- la definizione $\varepsilon$–$\delta$ è **operativa e quantitativa**.

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
x \in U_\delta(x_0)\setminus\{x_0\}
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
\overline{\mathbb{R}} = \mathbb{R}\cup\{-\infty,+\infty\}
$$

Cosa significa?

Che adesso permettiamo a:

- $x_0$ di essere un reale oppure $\pm\infty$,
    
- $L$ di essere un reale oppure $\pm\infty$.

La definizione “con gli intorni” resta la stessa, cambia solo il tipo di intorno da usare (intorno finito oppure semiretta).

> ⚠️ $+\infty$ e $-\infty$ **non sono numeri reali**: sono due simboli aggiunti a $\mathbb{R}$ con una convenzione d'ordine ($-\infty < x < +\infty$ per ogni $x\in\mathbb{R}$). Scrivere $\lim f(x)=+\infty$ **non** significa che il limite "esiste come numero": significa che $f$ cresce oltre ogni soglia. Le operazioni con $\pm\infty$ non sono tutte definite (ad esempio $\infty-\infty$ e $0\cdot\infty$ non hanno alcun valore): la retta reale estesa e le sue regole sono trattate nella Lezione 3C.

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
\forall M>0 \;\; \exists K>0 \;\; \forall x\in A: \; x>K \;\Rightarrow\; f(x)>M
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

> ✅ La definizione appena data è il punto di partenza del blocco teorico dell'unità: la Lezione 3A classifica i punti della retta (interni, isolati, di accumulazione); la 3B mostra come **si usa** la definizione nelle dimostrazioni e come si **nega**; la 3C formalizza limiti laterali, limiti infiniti e retta reale estesa; la 3D dimostra i teoremi generali (unicità, permanenza del segno, confronto, carabinieri); la 3E collega i limiti di funzione ai limiti di successione di M05; la 3F stabilisce l'algebra dei limiti e il teorema di composizione. La Lezione 4 introduce invece la continuità, sviluppata a fondo nella UD5.