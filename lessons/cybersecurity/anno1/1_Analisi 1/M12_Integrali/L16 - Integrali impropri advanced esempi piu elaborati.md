# **M11 Lezione 16 - Integrali impropri advanced: esempi più elaborati**

### **1. Quando un integrale improprio ha più aspetti da controllare**

Negli integrali impropri più semplici c'è un solo problema: oppure l'intervallo è illimitato, oppure la funzione esplode in un estremo. Negli esercizi più elaborati possono comparire più difficoltà contemporaneamente.

Il metodo corretto è sempre lo stesso:

1. isolare ogni punto problematico;
2. spezzare l'integrale in una somma di integrali impropri con un solo problema ciascuno;
3. studiare separatamente ogni pezzo;
4. ricomporre il risultato solo se tutti i pezzi sono ben definiti.

> 📌 Non si può "compensare" automaticamente un infinito positivo con un infinito negativo. Prima bisogna verificare la convergenza dei singoli pezzi.

---
### **2. Esempio 1: sostituzione in un integrale improprio**

Calcoliamo

$$
\int_2^{+\infty}\frac{1}{x\ln^2 x}\,dx.
$$

L'intervallo è illimitato, quindi l'integrale va definito tramite limite:

$$
\int_2^{+\infty}\frac{1}{x\ln^2 x}\,dx
=
\lim_{M\to+\infty}\int_2^M\frac{1}{x\ln^2 x}\,dx.
$$

#### **2.1 Sostituzione**

Poniamo

$$
y=\ln x.
$$

Allora

$$
dy=\frac{1}{x}\,dx.
$$

Gli estremi cambiano così:

$$
x=2 \Rightarrow y=\ln 2,
$$

$$
x=M \Rightarrow y=\ln M.
$$

Poiché $\ln^2 x=y^2$, si ha

$$
\int_2^{+\infty}\frac{1}{x\ln^2 x}\,dx
=
\lim_{M\to+\infty}\int_{\ln 2}^{\ln M}\frac{1}{y^2}\,dy.
$$

#### **2.2 Calcolo**

Calcoliamo:

$$
\lim_{M\to+\infty}\int_{\ln 2}^{\ln M}\frac{1}{y^2}\,dy
=
\lim_{M\to+\infty}\left[-\frac{1}{y}\right]_{\ln 2}^{\ln M}.
$$

Sostituendo:

$$
\lim_{M\to+\infty}\left(-\frac{1}{\ln M}+\frac{1}{\ln 2}\right).
$$

Poiché

$$
\lim_{M\to+\infty}\frac{1}{\ln M}=0,
$$

si ottiene:

$$
\int_2^{+\infty}\frac{1}{x\ln^2 x}\,dx
=
\frac{1}{\ln 2}.
$$

#### **2.3 Risultato**

$$
\int_2^{+\infty}\frac{1}{x\ln^2 x}\,dx=\frac{1}{\ln 2}.
$$

---
### **3. Esempio 2: integrale su tutto $\mathbb{R}$**

Calcoliamo

$$
\int_{-\infty}^{+\infty}\frac{1}{1+x^2}\,dx.
$$

L'intervallo è illimitato sia a sinistra sia a destra. Bisogna quindi spezzare l'integrale in due parti. Possiamo scegliere un punto qualsiasi, per esempio $3$:

$$
\int_{-\infty}^{+\infty}\frac{1}{1+x^2}\,dx
=
\int_{-\infty}^{3}\frac{1}{1+x^2}\,dx
+
\int_3^{+\infty}\frac{1}{1+x^2}\,dx.
$$

#### **3.1 Definizione tramite limiti**

Il primo pezzo è:

$$
\int_{-\infty}^{3}\frac{1}{1+x^2}\,dx
=
\lim_{M\to-\infty}\int_M^3\frac{1}{1+x^2}\,dx.
$$

Il secondo pezzo è:

$$
\int_3^{+\infty}\frac{1}{1+x^2}\,dx
=
\lim_{M\to+\infty}\int_3^M\frac{1}{1+x^2}\,dx.
$$

Una primitiva è

$$
\arctan x.
$$

Quindi:

$$
\int_{-\infty}^{+\infty}\frac{1}{1+x^2}\,dx
=
\lim_{M\to-\infty}\left[\arctan x\right]_M^3
+
\lim_{M\to+\infty}\left[\arctan x\right]_3^M.
$$

Sostituendo:

$$
=
\lim_{M\to-\infty}\left(\arctan 3-\arctan M\right)
+
\lim_{M\to+\infty}\left(\arctan M-\arctan 3\right).
$$

Poiché

$$
\lim_{M\to-\infty}\arctan M=-\frac{\pi}{2},
$$

e

$$
\lim_{M\to+\infty}\arctan M=\frac{\pi}{2},
$$

otteniamo:

$$
\left(\arctan 3+\frac{\pi}{2}\right)
+
\left(\frac{\pi}{2}-\arctan 3\right)
=\pi.
$$

#### **3.2 Scorciatoia con funzione pari**

La funzione

$$
f(x)=\frac{1}{1+x^2}
$$

è pari, perché

$$
f(-x)=f(x).
$$

Quindi si può anche scrivere direttamente:

$$
\int_{-\infty}^{+\infty}\frac{1}{1+x^2}\,dx
=
2\int_0^{+\infty}\frac{1}{1+x^2}\,dx.
$$

Calcolando:

$$
2\lim_{M\to+\infty}\left[\arctan x\right]_0^M
=
2\left(\frac{\pi}{2}-0\right)
=\pi.
$$

#### **3.3 Risultato**

$$
\int_{-\infty}^{+\infty}\frac{1}{1+x^2}\,dx=\pi.
$$

---
### **4. Esempio 3: più problemi nello stesso integrale**

Calcoliamo

$$
\int_0^{+\infty}\frac{1}{x^2}\,dx.
$$

Questo integrale presenta due problemi:

- la funzione è illimitata in $0$, perché

$$
\lim_{x\to 0^+}\frac{1}{x^2}=+\infty;
$$

- l'intervallo è illimitato verso $+\infty$.

Per questo non si può introdurre un solo limite. Bisogna spezzare l'integrale in due parti, scegliendo un punto intermedio, per esempio $5$:

$$
\int_0^{+\infty}\frac{1}{x^2}\,dx
=
\int_0^5\frac{1}{x^2}\,dx
+
\int_5^{+\infty}\frac{1}{x^2}\,dx.
$$

#### **4.1 Primo pezzo: improprietà in $0$**

Il primo integrale è:

$$
\int_0^5\frac{1}{x^2}\,dx
=
\lim_{\varepsilon\to 0^+}\int_{\varepsilon}^{5}\frac{1}{x^2}\,dx.
$$

Una primitiva è

$$
-\frac{1}{x}.
$$

Quindi:

$$
\lim_{\varepsilon\to 0^+}\left[-\frac{1}{x}\right]_{\varepsilon}^{5}
=
\lim_{\varepsilon\to 0^+}\left(-\frac{1}{5}+\frac{1}{\varepsilon}\right).
$$

Poiché

$$
\lim_{\varepsilon\to 0^+}\frac{1}{\varepsilon}=+\infty,
$$

il primo pezzo diverge a $+\infty$.

#### **4.2 Secondo pezzo: intervallo illimitato**

Il secondo integrale è:

$$
\int_5^{+\infty}\frac{1}{x^2}\,dx
=
\lim_{M\to+\infty}\int_5^M\frac{1}{x^2}\,dx.
$$

Calcoliamo:

$$
\lim_{M\to+\infty}\left[-\frac{1}{x}\right]_5^M
=
\lim_{M\to+\infty}\left(-\frac{1}{M}+\frac{1}{5}\right)
=\frac{1}{5}.
$$

Il secondo pezzo converge, ma il primo diverge.

#### **4.3 Risultato**

Poiché almeno un pezzo diverge a $+\infty$, l'integrale globale diverge:

$$
\int_0^{+\infty}\frac{1}{x^2}\,dx=+\infty.
$$

### **5. Regola generale per integrali con più problemi**

Se un integrale improprio presenta più di un problema, per risolverlo bisogna:

1. spezzarlo nella somma di più integrali impropri aventi un singolo problema;
2. risolvere separatamente ogni integrale;
3. dedurre il comportamento globale sommando i risultati dei vari pezzi.

Se tutti i pezzi convergono, allora l'integrale di partenza converge e vale la somma dei valori ottenuti. Se almeno un pezzo diverge a $+\infty$ o a $-\infty$ e gli altri pezzi sono finiti o divergono nello stesso verso, l'integrale diverge nello stesso verso.

Se invece almeno un pezzo diverge a $+\infty$ e almeno un altro diverge a $-\infty$, l'integrale di partenza si dichiara **indeterminato**.

> ⚠️ Non si somma $+\infty$ con $-\infty$. In quel caso l'integrale improprio non ha un valore determinato.

### **6. Riepilogo**

Gli integrali impropri avanzati richiedono soprattutto disciplina nel metodo. Ogni punto problematico deve essere isolato. Solo dopo aver studiato separatamente tutti i pezzi si può stabilire se l'integrale converge, diverge o non è determinato. Le simmetrie, come nel caso di funzioni pari, possono semplificare il calcolo, ma non sostituiscono la verifica della convergenza.
