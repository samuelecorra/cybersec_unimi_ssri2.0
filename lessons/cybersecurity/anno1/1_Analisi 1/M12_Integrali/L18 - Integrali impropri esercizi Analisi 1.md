# **M11 Lezione 18 - Integrali impropri: esercizi Analisi 1**

Nelle lezioni precedenti abbiamo introdotto gli integrali impropri, il loro significato come limite di integrali propri e i criteri più usati per stabilire la convergenza. In questa lezione raccogliamo esercizi tipici di Analisi 1 in cui non basta applicare meccanicamente una formula: bisogna prima capire dove si trova il problema, scegliere il confronto corretto e poi combinare le conclusioni locali.

Il punto centrale è sempre lo stesso: un integrale improprio può avere un problema in un estremo finito, all'infinito, oppure in entrambi. Quando i problemi sono più di uno, l'integrale va spezzato in integrali impropri con un solo problema ciascuno.

> 📌 Un integrale improprio converge solo se convergono tutti i pezzi in cui viene spezzato. Se anche un solo pezzo diverge, l'integrale complessivo diverge o non è convergente nel senso richiesto.

### **1. Esercizio 1: confronto asintotico vicino a zero**

Vogliamo stabilire se converge l'integrale

$$
\int_0^1 \frac{\ln(1+\sqrt{x})}{2\sin x}\,dx.
$$

L'integranda è definita e positiva per ogni $x\in(0,1]$: infatti $\sqrt{x}>0$, quindi $\ln(1+\sqrt{x})>0$, e inoltre $\sin x>0$ per $x\in(0,1]$. Il problema non è dunque il segno, ma il comportamento vicino a $0$, perché al denominatore compare $\sin x$ e $\sin x\to 0$ per $x\to 0^+$.

Per decidere la convergenza usiamo gli equivalenti notevoli:

$$
\ln(1+t)\sim t \quad \text{per } t\to 0,
$$

e

$$
\sin x\sim x \quad \text{per } x\to 0.
$$

Nel nostro caso $t=\sqrt{x}$, quindi per $x\to 0^+$ si ha

$$
\ln(1+\sqrt{x})\sim \sqrt{x}.
$$

Pertanto

$$
\frac{\ln(1+\sqrt{x})}{2\sin x}
\sim
\frac{\sqrt{x}}{2x}
=
\frac{1}{2\sqrt{x}}
=
\frac{1}{2}\cdot\frac{1}{x^{1/2}}.
$$

L'integrale modello da confrontare è quindi

$$
\int_0^1 \frac{1}{x^{1/2}}\,dx.
$$

Questo è un integrale improprio in $0$ della forma

$$
\int_0^1 \frac{1}{x^\alpha}\,dx,
$$

che converge se e solo se $\alpha<1$. Qui $\alpha=\frac{1}{2}$, dunque converge. Infatti:

$$
\int_0^1 \frac{1}{\sqrt{x}}\,dx
=
\lim_{\varepsilon\to 0^+}\int_\varepsilon^1 x^{-1/2}\,dx
=
\lim_{\varepsilon\to 0^+}\left[2\sqrt{x}\right]_\varepsilon^1
=
2.
$$

Poiché il fattore $\frac{1}{2}$ non cambia la convergenza, anche

$$
\int_0^1 \frac{1}{2\sqrt{x}}\,dx
$$

converge. Per il criterio del confronto asintotico, l'integrale iniziale converge.

> ✅ L'integrale $\int_0^1 \frac{\ln(1+\sqrt{x})}{2\sin x}\,dx$ converge, perché vicino a $0$ si comporta come $\frac{1}{2\sqrt{x}}$.

### **2. Esercizio 2: parametro reale e due zone problematiche**

Consideriamo ora il problema:

$$
\text{per quali valori di } k\in\mathbb{R} \text{ converge } \int_0^{+\infty}\frac{1}{x^k\ln^3(1+x)}\left(\frac{e^x-1}{e^x+1}\right)^6\,dx?
$$

Indichiamo l'integranda con

$$
g_k(x)=\frac{1}{x^k\ln^3(1+x)}\left(\frac{e^x-1}{e^x+1}\right)^6.
$$

Per $x>0$ l'integranda è positiva: $x^k>0$, $\ln(1+x)>0$ e il rapporto $\frac{e^x-1}{e^x+1}$ è positivo. Questo permette di usare senza ambiguità i criteri di confronto per funzioni positive.

Ci sono però due possibili problemi:

- vicino a $0$, perché compaiono $x^k$ e $\ln(1+x)$;
- per $x\to+\infty$, perché l'intervallo di integrazione è illimitato.

Spezziamo allora l'integrale in due parti, scegliendo ad esempio il punto $5$:

$$
\int_0^{+\infty} g_k(x)\,dx
=
\int_0^5 g_k(x)\,dx
+
\int_5^{+\infty} g_k(x)\,dx.
$$

Il valore $5$ non ha un significato speciale: serve solo a separare il problema vicino a $0$ dal problema all'infinito.

#### **2.1 Comportamento per $x\to 0^+$**

Per $x\to 0^+$ usiamo gli equivalenti:

$$
\ln(1+x)\sim x,
$$

$$
e^x-1\sim x,
$$

e

$$
e^x+1\sim 2.
$$

Quindi

$$
\frac{e^x-1}{e^x+1}\sim \frac{x}{2}.
$$

Sostituendo questi equivalenti nell'integranda si ottiene

$$
\frac{1}{x^k\ln^3(1+x)}\left(\frac{e^x-1}{e^x+1}\right)^6
\sim
\frac{1}{x^k\cdot x^3}\left(\frac{x}{2}\right)^6.
$$

Semplificando:

$$
\frac{1}{x^k\cdot x^3}\left(\frac{x}{2}\right)^6
=
\frac{1}{x^{k+3}}\cdot\frac{x^6}{64}
=
\frac{1}{64}\cdot\frac{1}{x^{k-3}}.
$$

Vicino a $0$ il problema è quindi equivalente a

$$
\int_0^5 \frac{1}{x^{k-3}}\,dx.
$$

Un integrale del tipo $\int_0^a \frac{1}{x^\alpha}\,dx$ converge se e solo se $\alpha<1$. Qui $\alpha=k-3$, quindi la prima condizione è

$$
k-3<1.
$$

Da cui:

$$
k<4.
$$

#### **2.2 Comportamento per $x\to+\infty$**

Per $x\to+\infty$ valgono gli equivalenti

$$
\ln(1+x)\sim \ln x
$$

e

$$
\frac{e^x-1}{e^x+1}\to 1.
$$

L'integranda si comporta quindi come

$$
\frac{1}{x^k\ln^3 x}.
$$

Usiamo l'integrale modello

$$
\int_a^{+\infty}\frac{1}{x^\alpha(\ln x)^\beta}\,dx.
$$

Nel nostro caso $\alpha=k$ e $\beta=3$. Per $a>1$, questo integrale converge se:

- $\alpha>1$;
- oppure $\alpha=1$ e $\beta>1$.

Dunque la coda all'infinito converge se e solo se

$$
k>1
$$

oppure

$$
k=1.
$$

In forma compatta:

$$
k\ge 1.
$$

#### **2.3 Conclusione sul parametro**

Perché l'integrale iniziale converga, devono valere entrambe le condizioni:

$$
\begin{cases}
k<4 \\
k\ge 1
\end{cases}
$$

Quindi

$$
1\le k<4.
$$

> ✅ L'integrale converge esattamente per $k\in[1,4)$.

> ⚠️ Il caso $k=1$ non va escluso: all'infinito compare il modello $\frac{1}{x(\ln x)^3}$, che converge perché la potenza del logaritmo è $3>1$.

### **3. Criterio di assoluta convergenza**

Quando l'integranda può cambiare segno, i criteri per funzioni positive non si applicano direttamente. Un modo molto efficace per concludere la convergenza è studiare il valore assoluto.

> 📌 Se
> $$
> \int_a^{+\infty}|g(x)|\,dx
> $$
> converge, allora converge anche
> $$
> \int_a^{+\infty}g(x)\,dx.
> $$

Questa proprietà prende il nome di criterio di assoluta convergenza. La convergenza dell'integrale del valore assoluto è più forte della convergenza dell'integrale originale.

> ⚠️ Il criterio funziona in una sola direzione: se l'integrale assoluto converge, allora l'integrale converge. Se l'integrale assoluto diverge, non possiamo concludere automaticamente che l'integrale originale diverga.

### **4. Esercizio 3: integrale oscillante**

Studiamo la convergenza di

$$
\int_1^{+\infty}\frac{\sin x}{x^2}\,dx.
$$

L'integranda non è sempre positiva, perché $\sin x$ cambia segno. Per questo conviene passare al valore assoluto:

$$
\left|\frac{\sin x}{x^2}\right|
=
\frac{|\sin x|}{x^2}.
$$

Poiché $|\sin x|\le 1$ per ogni $x\in\mathbb{R}$, per ogni $x\in[1,+\infty)$ vale

$$
\left|\frac{\sin x}{x^2}\right|
\le
\frac{1}{x^2}.
$$

Ora sappiamo che

$$
\int_1^{+\infty}\frac{1}{x^2}\,dx
$$

converge, perché è un integrale improprio del tipo $\int_1^{+\infty}\frac{1}{x^\alpha}\,dx$ con $\alpha=2>1$. Calcolandolo esplicitamente:

$$
\int_1^{+\infty}\frac{1}{x^2}\,dx
=
\lim_{M\to+\infty}\int_1^M x^{-2}\,dx
=
\lim_{M\to+\infty}\left[-\frac{1}{x}\right]_1^M
=
1.
$$

Per il criterio del confronto,

$$
\int_1^{+\infty}\left|\frac{\sin x}{x^2}\right|\,dx
$$

converge. Di conseguenza, per il criterio di assoluta convergenza, converge anche

$$
\int_1^{+\infty}\frac{\sin x}{x^2}\,dx.
$$

> ✅ L'integrale $\int_1^{+\infty}\frac{\sin x}{x^2}\,dx$ converge assolutamente, quindi converge.

### **5. Errori tipici**

Il primo errore è usare il confronto asintotico senza separare i problemi. Nell'esercizio con parametro $k$, il comportamento vicino a $0$ e quello per $x\to+\infty$ producono due condizioni diverse; considerarne una sola dà una risposta incompleta.

Il secondo errore è dimenticare il caso logaritmico. Un integrale come

$$
\int_a^{+\infty}\frac{1}{x(\ln x)^\beta}\,dx
$$

non si decide con il solo criterio delle potenze $\frac{1}{x^\alpha}$: quando $\alpha=1$ entra in gioco la potenza del logaritmo. Converge se $\beta>1$ e diverge se $\beta\le 1$.

Il terzo errore è applicare direttamente il criterio del confronto a funzioni che cambiano segno. Per funzioni come $\frac{\sin x}{x^2}$ bisogna prima passare al valore assoluto, oppure usare criteri specifici per integrali oscillanti.

### **6. Riepilogo**

In questa lezione abbiamo visto tre esercizi rappresentativi sugli integrali impropri:

- un integrale con problema in $0$, risolto tramite confronto asintotico con $\frac{1}{\sqrt{x}}$;
- un integrale dipendente da un parametro, in cui la convergenza richiede simultaneamente $k<4$ vicino a $0$ e $k\ge 1$ all'infinito;
- un integrale oscillante, risolto dimostrando la convergenza assoluta tramite il confronto con $\frac{1}{x^2}$.

> 📌 La strategia operativa è: individuare i punti problematici, spezzare l'integrale se necessario, sostituire l'integranda con un modello asintotico corretto e applicare il criterio adatto.
