# **M9 UD3 Lezione 5 - Derivate successive e classi $C^k$**

### **1. Obiettivo della lezione**

Iterando la derivazione nascono le derivate di ordine superiore e la **scala di regolarità** $C^0\supsetneq C^1\supsetneq\dots\supsetneq C^\infty$, con i controesempi che rendono strette le inclusioni. Chiudiamo con la formula di Leibniz per $(fg)^{(n)}$ e i pattern delle derivate $n$-esime.

---
### **2. Derivate successive**

**Definizione.** Se $f'$ è a sua volta derivabile in $x_0$, il valore $(f')'(x_0)$ si chiama **derivata seconda** $f''(x_0)$; per ricorrenza,

$$
f^{(0)}=f,\qquad f^{(n)} = \left(f^{(n-1)}\right)' \quad (n\ge 1)
$$

con le notazioni $f''$, $f'''$ (o $f^{(3)}$), $f^{(n)}$, ovvero $\frac{d^n f}{dx^n}$, $D^n f$. Il **dominio** di $f^{(n)}$ è l'insieme dei punti in cui l'iterazione sopravvive: può restringersi a ogni passo (esempio del punto 4). Interpretazioni già disponibili: $f''$ è il tasso di variazione della pendenza (accelerazione, se $f$ è una posizione: UD1/L3); la lettura geometrica completa ($f''$ e concavità) appartiene a M10/UD4.

---
### **3. Le classi di regolarità**

**Definizione.** Su un intervallo $I$:

- $C^0(I)$: funzioni **continue** su $I$;
- $C^k(I)$ ($k\ge 1$): funzioni derivabili $k$ volte su $I$ **con $f^{(k)}$ continua**;
- $C^\infty(I)$: derivabili infinite volte ("lisce").

La catena di inclusioni, **tutte strette**:

$$
C^\infty \subsetneq \dots \subsetneq C^2 \subsetneq C^1 \subsetneq \{\text{derivabili}\} \subsetneq C^0
$$

> 📌 Il punto sottile è il gradino "$\{$derivabili$\}\supsetneq C^1$": **esistenza** della derivata e **continuità** della derivata sono proprietà diverse. Il separatore canonico è il protagonista della UD4/L3:
>
> $$
> f(x)=\begin{cases}x^2\sin\frac 1x, & x\neq 0\\ 0, & x=0\end{cases}
> $$
>
> derivabile ovunque (anche in $0$, con $f'(0)=0$ dal rapporto incrementale), ma $f'(x)=2x\sin\frac 1x-\cos\frac 1x$ non ha limite in $0$: $f'$ esiste ed è **discontinua** — $f\in\{\text{derivabili}\}\setminus C^1$. La discontinuità di $f'$ è oscillatoria, e non poteva essere altrimenti: per il teorema di **Darboux** (UD5/L5) una derivata non può avere discontinuità a salto.

Gli altri separatori: $|x|\in C^0\setminus\{\text{derivabili}\}$; $x|x|\in C^1\setminus C^2$ (la sua derivata $2|x|$ è continua ma non derivabile in $0$: UD1/L4, esercizio 3); in generale $x^{k}|x|\in C^{k}\setminus C^{k+1}$. Esempi di $C^\infty$: polinomi, $e^x$, $\sin$, $\cos$ su $\mathbb{R}$; razionali sul loro dominio.

> ⚠️ Non introduciamo qui le funzioni **analitiche** (quelle sviluppabili in serie di potenze): la distinzione $C^\infty$/analitiche richiede Taylor e le serie, e appartiene a M10 e oltre. Basti l'avviso che $C^\infty$ **non** è il gradino massimo della scala.

---
### **4. Il dominio può restringersi a ogni derivazione**

$f(x)=x^{5/2}=x^2\sqrt{x}$ su $[0,+\infty)$: $f'=\frac{5}{2}x^{3/2}$ (derivabile anche in $0$), $f''=\frac{15}{4}x^{1/2}$ (ancora continua in $0$), $f'''=\frac{15}{8}x^{-1/2}$ — **non definita** in $0$: $f\in C^2([0,+\infty))$ ma non $C^3$. Ogni derivazione può consumare mezzo grado di regolarità nei punti critici: la regolarità va dichiarata sempre **su un insieme**, non in astratto.

---
### **5. Formula di Leibniz (approfondimento)**

**Teorema.** Se $f,g$ sono derivabili $n$ volte in $x$, allora

$$
(fg)^{(n)}(x) = \sum_{k=0}^{n}\binom{n}{k} f^{(k)}(x)\,g^{(n-k)}(x)
$$

*Dimostrazione (induzione su $n$).* Base $n=1$: è la regola del prodotto. Passo: derivando la formula per $n$ e riapplicando il prodotto a ciascun addendo,

$$
(fg)^{(n+1)} = \sum_{k=0}^{n}\binom{n}{k}\left[f^{(k+1)}g^{(n-k)} + f^{(k)}g^{(n-k+1)}\right]
$$

Reindicizzando la prima somma ($j=k+1$) e raccogliendo i termini con gli stessi ordini, i coefficienti si combinano con la **relazione di Pascal** $\binom{n}{j-1}+\binom{n}{j}=\binom{n+1}{j}$ (M01/UD0), esattamente come nella prova del binomio di Newton: si ottiene la formula per $n+1$. $\square$

L'analogia col binomio non è casuale: la derivazione del prodotto "distribuisce" le $n$ derivate sui due fattori in tutti i modi possibili. Esempio d'uso: $(x^2 e^x)^{(10)} = \sum_k\binom{10}{k}(x^2)^{(k)}(e^x)^{(10-k)} = e^x\left(x^2+20x+90\right)$ — solo tre addendi sopravvivono, perché $(x^2)^{(k)}=0$ per $k\ge 3$.

---
### **6. Pattern delle derivate $n$-esime**

Da riconoscere (e dimostrare per induzione quando richiesto):

$$
\frac{d^n}{dx^n}e^{ax} = a^n e^{ax}
$$

$$
\frac{d^n}{dx^n}\sin(ax+b) = a^n\sin\left(ax+b+n\frac{\pi}{2}\right)
$$

(ogni derivazione ruota la fase di $\frac{\pi}{2}$ e moltiplica per $a$: il ciclo $\sin\to\cos\to-\sin\to-\cos\to\sin$ ha **periodo 4**; identico per il coseno);

$$
\frac{d^n}{dx^n}x^m = \begin{cases} m(m-1)\cdots(m-n+1)\,x^{m-n} = \dfrac{m!}{(m-n)!}x^{m-n}, & n\le m\\[4pt] 0, & n>m \end{cases}
\qquad (m\in\mathbb{N})
$$

$$
\frac{d^n}{dx^n}\ln x = \frac{(-1)^{n-1}(n-1)!}{x^n}, \qquad \frac{d^n}{dx^n}\frac{1}{x} = \frac{(-1)^n\,n!}{x^{n+1}}
$$

Questi pattern sono il carburante degli sviluppi di Taylor (M10/UD3): calcolare $f^{(n)}(0)$ in blocco è ciò che rende scrivibili le serie di $e^x$, $\sin$, $\cos$.

---
### **7. Errori frequenti**

- ❌ Confondere $f^{(n)}$ (derivata $n$-esima) con $f^n$ (potenza $n$-esima) o $(f')^n$.
- ❌ "Derivabile due volte" detto senza dominio, quando la seconda derivata esiste solo su un sottoinsieme.
- ❌ Identificare "derivabile" con "$C^1$" (il separatore $x^2\sin\frac 1x$).
- ❌ Nella formula di Leibniz, dimenticare i binomiali o usare i segni alterni (non è la regola del quoziente!).
- ❌ Nel pattern di $\sin$: sbagliare il periodo 4 del ciclo (es. dire che la derivata quarta è $-\sin$).

---
### **8. Esercizi**

**Esercizio 1.** Calcola $f^{(100)}(x)$ per $f(x)=\sin(2x)$.

*Soluzione.* $2^{100}\sin\left(2x+100\cdot\frac{\pi}{2}\right)=2^{100}\sin(2x+50\pi)=2^{100}\sin(2x)$ ($100\equiv 0\bmod 4$).

**Esercizio 2.** Con Leibniz, calcola $\left(x\,e^{3x}\right)^{(5)}$.

*Soluzione.* Sopravvivono $k=0,1$: $\binom{5}{0}x\cdot 3^5 e^{3x}+\binom{5}{1}\cdot 1\cdot 3^4 e^{3x} = e^{3x}\left(243x+405\right)$.

**Esercizio 3.** Stabilisci la classe di regolarità di $f(x)=\begin{cases}x^3, & x\ge 0\\ -x^3, & x<0\end{cases} = |x|^3$ su $\mathbb{R}$.

*Soluzione.* $f'=3x|x|$ (continua), $f''=6|x|$ (continua), $f'''$ non esiste in $0$ (laterali $\pm 6$): $f\in C^2(\mathbb{R})\setminus C^3(\mathbb{R})$.

**Esercizio 4.** Dimostra per induzione il pattern $\frac{d^n}{dx^n}\frac 1x=\frac{(-1)^n n!}{x^{n+1}}$.

*Soluzione.* Base $n=1$: $-\frac{1}{x^2}$ ✓ (UD2/L1). Passo: derivando $\frac{(-1)^n n!}{x^{n+1}} = (-1)^n n!\,x^{-(n+1)}$ si ottiene $(-1)^n n!\cdot\left(-(n+1)\right)x^{-(n+2)} = \frac{(-1)^{n+1}(n+1)!}{x^{n+2}}$ ✓.

---
### **9. Riepilogo**

> ✅ Derivate successive per ricorrenza, con dominio che può restringersi a ogni passo ($x^{5/2}$); classi $C^k$ = derivabile $k$ volte **con derivata $k$-esima continua**; scala $C^\infty\subsetneq\dots\subsetneq C^1\subsetneq\{\text{derivabili}\}\subsetneq C^0$ con separatori espliciti ($x^2\sin\frac 1x$ per il gradino derivabile/$C^1$; $x^k|x|$ per i successivi); Leibniz $(fg)^{(n)}=\sum\binom{n}{k}f^{(k)}g^{(n-k)}$ per induzione con Pascal; pattern $n$-esimi di $e^{ax}$, $\sin/\cos$ (ciclo di periodo 4), potenze, $\ln$ — il carburante di Taylor in M10.
