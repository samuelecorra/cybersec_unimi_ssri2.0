# **M11 Lezione 12 - Esercizi svolti di Analisi 1 sugli integrali**

### **1. Integrale definito con simmetria e sostituzione**

Calcoliamo

$$
\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}\left[x^5\cos x+3\cos(2x)\right]\,dx.
$$

L'integrale contiene due addendi molto diversi. Il primo sembra richiedere più integrazioni per parti; il secondo è immediato con una sostituzione. Prima di iniziare calcoli lunghi conviene però osservare la simmetria dell'intervallo.

#### **1.1 Linearità dell'integrale**

Per linearità:

$$
\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}\left[x^5\cos x+3\cos(2x)\right]\,dx
=
\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}x^5\cos x\,dx
+3\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}\cos(2x)\,dx.
$$

#### **1.2 Il primo integrale è nullo**

La funzione $x^5$ è dispari, mentre $\cos x$ è pari. Il prodotto tra una funzione dispari e una funzione pari è dispari:

$$
x^5\cos x \text{ è dispari.}
$$

Infatti:

$$
(-x)^5\cos(-x)=-x^5\cos x.
$$

L'intervallo di integrazione è simmetrico rispetto all'origine:

$$
\left[-\frac{\pi}{4},\frac{\pi}{4}\right].
$$

Quindi:

$$
\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}x^5\cos x\,dx=0.
$$

> 📌 Prima di applicare tecniche lunghe, controllare sempre simmetria dell'intervallo e parità/disparità dell'integranda.

#### **1.3 Calcolo del secondo integrale**

Resta:

$$
3\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}\cos(2x)\,dx.
$$

Poniamo

$$
y=2x.
$$

Allora:

$$
dy=2\,dx,
\qquad
dx=\frac{1}{2}\,dy.
$$

Aggiorniamo gli estremi:

$$
x=-\frac{\pi}{4}\Rightarrow y=-\frac{\pi}{2},
\qquad
x=\frac{\pi}{4}\Rightarrow y=\frac{\pi}{2}.
$$

Quindi:

$$
3\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}\cos(2x)\,dx
=
\frac{3}{2}\int_{-\frac{\pi}{2}}^{\frac{\pi}{2}}\cos y\,dy.
$$

Calcoliamo:

$$
\frac{3}{2}\int_{-\frac{\pi}{2}}^{\frac{\pi}{2}}\cos y\,dy
=
\frac{3}{2}\left[\sin y\right]_{-\frac{\pi}{2}}^{\frac{\pi}{2}}.
$$

Sostituendo gli estremi:

$$
\frac{3}{2}\left[\sin\left(\frac{\pi}{2}\right)-\sin\left(-\frac{\pi}{2}\right)\right]
=
\frac{3}{2}\left[1-(-1)\right]
=3.
$$

#### **1.4 Risultato**

Pertanto:

$$
\int_{-\frac{\pi}{4}}^{\frac{\pi}{4}}\left[x^5\cos x+3\cos(2x)\right]\,dx=3.
$$

#### **1.5 Controllo: primitiva di $x^5\cos x$**

Negli screenshot è mostrato anche il calcolo della primitiva

$$
\int x^5\cos x\,dx.
$$

Usando ripetutamente l'integrazione per parti si ottiene:

$$
\int x^5\cos x\,dx
=
x^5\sin x-\int 5x^4\sin x\,dx.
$$

Il secondo integrale richiede ancora parti. Proseguendo fino a ridurre la potenza di $x$, si arriva a:

$$
\int x^5\cos x\,dx
=
x^5\sin x+5x^4\cos x-20x^3\sin x-60x^2\cos x+120x\sin x+120\cos x+C.
$$

Raccogliendo seno e coseno:

$$
\int x^5\cos x\,dx
=
(x^5-20x^3+120x)\sin x+(5x^4-60x^2+120)\cos x+C.
$$

Questo calcolo è corretto, ma nel definito dell'esercizio non serve: la simmetria annulla direttamente il contributo.

> ⚠️ La linearità permette di separare gli addendi, ma non bisogna perdere di vista scorciatoie strutturali come l'integrale di una funzione dispari su un intervallo simmetrico.

---
### **2. Integrale con radicali e razionalizzazione**

Calcoliamo

$$
\int_0^{15}\frac{72}{\sqrt{x+49}+\sqrt{x+1}}\,dx.
$$

L'integranda contiene una somma di radicali al denominatore. La tecnica naturale è razionalizzare moltiplicando per il coniugato.

#### **2.1 Razionalizzazione**

Poniamo idealmente:

$$
A=\sqrt{x+49},
\qquad
B=\sqrt{x+1}.
$$

Allora:

$$
\frac{72}{\sqrt{x+49}+\sqrt{x+1}}
=
\frac{72}{A+B}\cdot\frac{A-B}{A-B}.
$$

Il denominatore diventa:

$$
(A+B)(A-B)=A^2-B^2.
$$

Sostituendo:

$$
A^2-B^2=(x+49)-(x+1)=48.
$$

Quindi:

$$
\frac{72}{\sqrt{x+49}+\sqrt{x+1}}
=
\frac{72\left(\sqrt{x+49}-\sqrt{x+1}\right)}{48}
=
\frac{3}{2}\left(\sqrt{x+49}-\sqrt{x+1}\right).
$$

#### **2.2 Calcolo della primitiva**

Ora:

$$
\int \frac{72}{\sqrt{x+49}+\sqrt{x+1}}\,dx
=
\frac{3}{2}\int\left(\sqrt{x+49}-\sqrt{x+1}\right)\,dx.
$$

Scriviamo i radicali come potenze:

$$
\sqrt{x+49}=(x+49)^{1/2},
\qquad
\sqrt{x+1}=(x+1)^{1/2}.
$$

Poiché

$$
\int (x+a)^{1/2}\,dx=\frac{(x+a)^{3/2}}{3/2},
$$

otteniamo:

$$
\frac{3}{2}\cdot\frac{(x+49)^{3/2}}{3/2}
-
\frac{3}{2}\cdot\frac{(x+1)^{3/2}}{3/2}
+C.
$$

Quindi una primitiva è:

$$
F(x)=(x+49)^{3/2}-(x+1)^{3/2}.
$$

Equivalentemente:

$$
F(x)=\sqrt{(x+49)^3}-\sqrt{(x+1)^3}.
$$

#### **2.3 Valutazione agli estremi**

Calcoliamo:

$$
\int_0^{15}\frac{72}{\sqrt{x+49}+\sqrt{x+1}}\,dx
=
\left[(x+49)^{3/2}-(x+1)^{3/2}\right]_0^{15}.
$$

Per $x=15$:

$$
(15+49)^{3/2}-(15+1)^{3/2}
=
64^{3/2}-16^{3/2}
=
512-64
=448.
$$

Per $x=0$:

$$
(0+49)^{3/2}-(0+1)^{3/2}
=
49^{3/2}-1^{3/2}
=
343-1
=342.
$$

La differenza è:

$$
448-342=106.
$$

#### **2.4 Risultato**

$$
\int_0^{15}\frac{72}{\sqrt{x+49}+\sqrt{x+1}}\,dx=106.
$$

---
### **3. Esercizio 2 modificato**

Consideriamo ora la variante:

$$
\int_0^{15}\frac{72}{\sqrt{x+49}-\sqrt{x+1}}\,dx.
$$

La struttura è la stessa, ma cambia il segno tra i radicali. Razionalizziamo moltiplicando per il coniugato con il segno opposto:

$$
\frac{72}{\sqrt{x+49}-\sqrt{x+1}}
=
\frac{72}{A-B}\cdot\frac{A+B}{A+B}.
$$

Il denominatore è ancora:

$$
(A-B)(A+B)=A^2-B^2=48.
$$

Quindi:

$$
\frac{72}{\sqrt{x+49}-\sqrt{x+1}}
=
\frac{3}{2}\left(\sqrt{x+49}+\sqrt{x+1}\right).
$$

Una primitiva è:

$$
G(x)=(x+49)^{3/2}+(x+1)^{3/2}.
$$

Valutiamo:

$$
\int_0^{15}\frac{72}{\sqrt{x+49}-\sqrt{x+1}}\,dx
=
\left[(x+49)^{3/2}+(x+1)^{3/2}\right]_0^{15}.
$$

Per $x=15$:

$$
64^{3/2}+16^{3/2}=512+64=576.
$$

Per $x=0$:

$$
49^{3/2}+1^{3/2}=343+1=344.
$$

Dunque:

$$
576-344=232.
$$

#### **3.1 Risultato**

$$
\int_0^{15}\frac{72}{\sqrt{x+49}-\sqrt{x+1}}\,dx=232.
$$

> 📌 Nei due esercizi la razionalizzazione produce sempre il denominatore $48$; cambia solo il segno tra i radicali nel numeratore finale.

---
### **4. Trovare una primitiva con sostituzione**

Troviamo una primitiva di

$$
g(x)=\frac{\sqrt{x+8}}{\sqrt{x+8}+x+8}+x^{2019}.
$$

L'integrale si separa per linearità:

$$
\int g(x)\,dx
=
\int \frac{\sqrt{x+8}}{\sqrt{x+8}+x+8}\,dx
+
\int x^{2019}\,dx.
$$

Il secondo integrale è immediato:

$$
\int x^{2019}\,dx=\frac{x^{2020}}{2020}.
$$

#### **4.1 Sostituzione nel termine con radicale**

Per il primo integrale poniamo:

$$
t=\sqrt{x+8}.
$$

Allora:

$$
t^2=x+8.
$$

Derivando:

$$
2t\,dt=dx.
$$

Quindi:

$$
dx=2t\,dt.
$$

Inoltre:

$$
\sqrt{x+8}+x+8=t+t^2=t(t+1).
$$

Pertanto:

$$
\int \frac{\sqrt{x+8}}{\sqrt{x+8}+x+8}\,dx
=
\int \frac{t}{t+t^2}\cdot 2t\,dt.
$$

Poiché

$$
t+t^2=t(t+1),
$$

si ottiene:

$$
\int \frac{t}{t+t^2}\cdot 2t\,dt
=
\int \frac{2t^2}{t(t+1)}\,dt
=
2\int \frac{t}{t+1}\,dt.
$$

Scriviamo:

$$
\frac{t}{t+1}=\frac{t+1-1}{t+1}=1-\frac{1}{t+1}.
$$

Quindi:

$$
2\int \frac{t}{t+1}\,dt
=
2\int 1\,dt-2\int \frac{1}{t+1}\,dt.
$$

Calcolando:

$$
2\int 1\,dt-2\int \frac{1}{t+1}\,dt
=
2t-2\ln|t+1|+C.
$$

Tornando alla variabile $x$:

$$
2t-2\ln|t+1|
=
2\sqrt{x+8}-2\ln(\sqrt{x+8}+1).
$$

Nel dominio reale dell'espressione, $x\ge -8$, quindi $\sqrt{x+8}+1>0$ e il valore assoluto non è necessario.

#### **4.2 Risultato**

Una primitiva di $g$ è:

$$
2\sqrt{x+8}-2\ln(\sqrt{x+8}+1)+\frac{x^{2020}}{2020}+C.
$$

### **5. Riepilogo**

Gli esercizi mostrano tre idee ricorrenti sugli integrali:

- usare la simmetria per annullare integrali di funzioni dispari su intervalli simmetrici;
- razionalizzare denominatori con radicali prima di integrare;
- scegliere sostituzioni che trasformano radicali e potenze in espressioni razionali più semplici.

La parte più importante non è solo conoscere le tecniche, ma riconoscere quale tecnica evita calcoli inutilmente lunghi.
