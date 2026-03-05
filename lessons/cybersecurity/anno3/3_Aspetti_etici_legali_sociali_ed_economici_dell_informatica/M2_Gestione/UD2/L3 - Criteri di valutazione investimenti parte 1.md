# **M2 UD2 L3 - Criteri di valutazione investimenti parte 1**

### **3. Criteri di valutazione degli investimenti in condizioni deterministiche**

I criteri di valutazione degli investimenti in condizioni deterministiche possono essere classificati sulla base dell’utilizzo o meno di **valori attualizzati**. In particolare, è possibile distinguere tra:

![](imgs/Pasted%20image%2020260209222952.png)

- **criteri basati sui flussi di cassa scontati (Discounted Cash Flow – DCF)**, che tengono conto del valore economico del tempo;
- **criteri non basati sui flussi di cassa scontati (Non Discounted Cash Flow – Non DCF)**, che trascurano l’attualizzazione.

Tra i criteri **DCF** rientrano:

- il **Valore Attuale Netto** (_Net Present Value – NPV_ o _VAN_);
- l’**Indice di Profittabilità** (_Profitability Index – PI_);
- il **Tasso Interno di Rendimento** (_Internal Rate of Return – IRR_).

Tra i criteri **Non DCF** rientrano:

- il **Payback Period (PB)**;
- il **Return on Investment (ROI)**.

---

### **3.1 Metodi a flusso di cassa scontato (DCF)**

#### **3.1.1 Valore Attuale Netto (NPV o VAN)**

Con riferimento al tasso barriera ( \sigma ), il criterio primario per la valutazione degli investimenti (supponendo di avere più investimenti in competizione) consiste nello **scartare gli investimenti che presentano una profittabilità inferiore a ( \sigma )**. Può accadere che nessun investimento soddisfi tale vincolo; in tal caso, l’impresa può decidere di ridurre il valore dell’HRR oppure di rinunciare all’investimento.

Un primo metodo di analisi è rappresentato dal **Valore Attuale Netto (VAN o NPV)**, definito come:

$$
NPV = \sum_{t=1}^{N} \frac{FF(t)}{(1+\sigma)^t} - I_0
$$

dove:

- $FF(t)$ rappresenta i **flussi di cassa** generati dall’investimento al tempo $t$, al lordo dell’ammortamento;
- $I_0$ è l’**investimento iniziale** sostenuto all’istante zero. Qualora l’investimento sia effettuato in più momenti successivi, il suo valore deve essere considerato come somma di importi attualizzati.

Il NPV rappresenta la **differenza tra il valore attuale dei profitti lordi, scontati al tasso barriera, e il capitale investito**.

Il criterio decisionale può essere così esplicitato:

- $NPV = 0$: l’investimento rende esattamente il tasso barriera;
- $NPV > 0$: l’investimento rende più del tasso barriera;
- $NPV < 0$: l’investimento rende meno del tasso barriera.

Per il calcolo del NPV è quindi indispensabile conoscere il valore di $\sigma$. In assenza di tale informazione, è necessario determinarlo preliminarmente.

---

#### **3.1.2 Tasso Interno di Rendimento (IRR)**

Se si considera l’equazione del NPV ponendola uguale a zero e si risolve rispetto all’incognita $\sigma$, la soluzione ottenuta prende il nome di **Tasso Interno di Rendimento (IRR)**.

Si ha dunque:

$$
NPV = \sum_{t=1}^{N} \frac{FF(t)}{(1+IRR)^t} - I_0 = 0
$$

Il criterio decisionale associato all’IRR è il seguente:

- $IRR = \sigma$: l’investimento rende esattamente il tasso barriera;
- $IRR > \sigma$: l’investimento rende più del tasso barriera;
- $IRR < \sigma$: l’investimento rende meno del tasso barriera.

È importante sottolineare alcuni aspetti critici:

- non sempre è possibile calcolare un IRR; l’equazione può non avere soluzioni reali oppure ammetterne più di una. In tal caso, l’IRR è utilizzabile solo se esiste un’unica soluzione reale;
- il criterio dell’IRR può essere utilizzato anche senza conoscere $\sigma$: il tasso barriera serve solo a stabilire se l’investimento è profittevole, mentre il confronto tra gli IRR di investimenti alternativi consente di individuare quello con rendimento interno maggiore, anche se ciò non implica necessariamente una maggiore profittabilità assoluta.

---

#### **3.1.3 Indice di Profittabilità (PI)**

Un ulteriore criterio DCF è rappresentato dall’**Indice di Profittabilità (Profitability Index – PI)**, definito come:

# $$

PI = \frac{\sum\_{t=1}^{N} \frac{FF(t)}{(1+\sigma)^t}}{I_0}

$$

Il criterio decisionale è il seguente:

- $PI = 1$: l’investimento rende esattamente il tasso barriera;

- $PI > 1$: l’investimento rende più del tasso barriera;

- $PI < 1$: l’investimento rende meno del tasso barriera.


In questo caso è necessario conoscere il valore del tasso barriera $\sigma$.

---

#### **3.1.4 Relazione tra NPV e PI**

Il **NPV** è un indicatore **assoluto** e tende a privilegiare investimenti caratterizzati da un maggiore fabbisogno di capitale iniziale. Il **PI**, invece, è un indicatore **relativo** e tende a privilegiare investimenti che richiedono un esborso iniziale inferiore.

Se NPV e PI sono calcolati sullo stesso investimento, forniscono la stessa indicazione decisionale:


$$

NPV > 0 \iff PI > 1

$$

Tuttavia, NPV e PI possono fornire indicazioni opposte quando si confrontano investimenti differenti. È possibile, ad esempio, che:


$$

NPV(1) > NPV(2) \quad \text{e contemporaneamente} \quad PI(1) < PI(2)

$$

---

#### **3.1.5 Relazione tra NPV e IRR**

Considerando il NPV come funzione del tasso di sconto $\sigma$, è possibile rappresentare graficamente il confronto tra due investimenti alternativi. Anche assumendo lo stesso orizzonte temporale $T$ e lo stesso investimento iniziale $I_0$, i profili di NPV possono essere molto diversi.

![](imgs/Pasted%20image%2020260209223509.png)

In tali casi può verificarsi una discordanza tra i criteri NPV e IRR:

- secondo il criterio NPV, se $\sigma < P$ si preferisce l’investimento 1, mentre se $\sigma > P$ si preferisce l’investimento 2;

- secondo il criterio IRR, se $IRR_2 > IRR_1$ si sceglierebbe sempre l’investimento 2.


Il fenomeno è interpretabile osservando che l’investimento 1 è più sensibile al tasso di sconto, poiché presenta ritorni più lontani nel tempo. Tali ritorni sono quindi maggiormente penalizzati all’aumentare di $\sigma$, con conseguente IRR inferiore. Tuttavia, in termini assoluti, l’investimento 1 può rendere di più, come evidenziato dalla maggiore intercetta sull’asse verticale del grafico (ossia per $\sigma = 0$).

---

#### **3.1.6 Relazione tra NPV e IRR: dimostrazione**

Introducendo un presunto tasso di reinvestimento $i$, è possibile definire il concetto di **Valore Terminale (Terminal Value – TV)**:


$$

TV = \sum\_{t=1}^{T} FF(t) \cdot (1+i)^{T-t}

$$

Il valore terminale rappresenta il capitale che si ottiene reinvestendo tutti i flussi di cassa annuali $FF(t)$ al tasso $i$ fino al tempo $T$.

Se si attualizza tale valore al tempo zero e si sottrae l’investimento iniziale, si ottiene un **NPV modificato**:


$$

NPV^* = \frac{TV}{(1+\sigma)^T} - I*0  
= \sum*{t=1}^{T} FF(t) \cdot \frac{(1+i)^{T-t}}{(1+\sigma)^T} - I_0

$$

Questo valore esprime il valore attuale dell’investimento ipotizzando che i flussi siano sistematicamente reinvestiti al tasso $i$.

Si osserva che:

- se $i = \sigma$, allora $NPV^* = NPV$, il che implica che il NPV assume implicitamente il reinvestimento dei flussi al tasso $\sigma$;

- ponendo $NPV^* = 0$ e risolvendo rispetto a $\sigma$, si ottiene il valore $IRR^*$, che coincide con l’IRR.


Ne consegue che:

- il criterio IRR assume implicitamente che i flussi siano reinvestiti al tasso IRR;

- il criterio NPV assume implicitamente che i flussi siano reinvestiti al tasso $\sigma$.


Pertanto, l’IRR tende a premiare investimenti con ritorni rapidi e concentrati nel breve periodo, mentre il NPV è più favorevole a investimenti con ritorni elevati ma distribuiti su un orizzonte temporale più lungo.