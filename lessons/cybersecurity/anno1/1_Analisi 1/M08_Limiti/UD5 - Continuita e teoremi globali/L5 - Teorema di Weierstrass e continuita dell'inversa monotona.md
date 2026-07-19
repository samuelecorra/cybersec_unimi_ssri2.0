# **M8 UD5 Lezione 5 - Teorema di Weierstrass e continuità dell'inversa monotona**

### **1. Obiettivo della lezione**

Secondo blocco di teoremi globali: sul dominio giusto — un intervallo **chiuso e limitato** $[a,b]$ — la continuità produce limitatezza e l'esistenza di massimo e minimo (**Weierstrass**), e su un intervallo qualunque la stretta monotonia continua si inverte in una funzione ancora continua (**teorema dell'inversa**), il tassello che mancava per completare la Lezione 2 (logaritmo, inverse trigonometriche e iperboliche). Le dimostrazioni usano la macchina sequenziale: Bolzano-Weierstrass e i criteri della Lezione 1.

---
### **2. Teorema di Weierstrass**

**Teorema.** Sia $f:[a,b]\to\mathbb{R}$ continua su un intervallo chiuso e limitato. Allora:

1. $f$ è **limitata** su $[a,b]$;
2. $f$ **assume massimo e minimo**: esistono $x_m,x_M\in[a,b]$ tali che

$$
f(x_m)\le f(x)\le f(x_M) \qquad \text{per ogni } x\in[a,b]
$$

*Dimostrazione.*

**Passo 1 (limitatezza).** Per assurdo, $f$ non sia limitata superiormente: per ogni $n\in\mathbb{N}$ esiste $x_n\in[a,b]$ con

$$
f(x_n)>n
$$

La successione $(x_n)$ vive in $[a,b]$, quindi è limitata: per il **teorema di Bolzano-Weierstrass** (M05) ammette una sottosuccessione convergente $x_{n_k}\to c$, e $c\in[a,b]$ perché le disuguaglianze larghe $a\le x_{n_k}\le b$ passano al limite. Per il criterio sequenziale di continuità (Lezione 1 — le successioni possono anche toccare $c$):

$$
f(x_{n_k}) \to f(c)\in\mathbb{R}
$$

Ma $f(x_{n_k})>n_k\to+\infty$: una successione convergente non può divergere. Assurdo. Simmetricamente per la limitatezza inferiore.

**Passo 2 (il sup è assunto).** Per il passo 1 l'insieme dei valori $f([a,b])$ è non vuoto e limitato: sia

$$
S=\sup_{x\in[a,b]}f(x)\in\mathbb{R}
$$

Per la caratterizzazione del sup (M02/M05), per ogni $n\ge 1$ esiste $y_n\in[a,b]$ con

$$
S-\frac{1}{n} < f(y_n) \le S
$$

quindi $f(y_n)\to S$ (carabinieri). Di nuovo Bolzano-Weierstrass: $y_{n_k}\to x_M\in[a,b]$, e per continuità $f(y_{n_k})\to f(x_M)$. Ma $f(y_{n_k})$ è una sottosuccessione di $f(y_n)$, quindi tende a $S$: per unicità del limite

$$
f(x_M)=S
$$

il sup è un **massimo**. Per il minimo si applica il risultato a $-f$. $\square$

<!-- TODO FIGURA:
Grafico di una funzione continua su [a,b] con evidenziati il punto di massimo (x_M, f(x_M)) e di minimo (x_m, f(x_m)) con tangenti orizzontali tratteggiate ai livelli S=max e s=min; fascia orizzontale [s,S] che racchiude l'intero grafico.
Obiettivo didattico: visualizzare la tesi di Weierstrass: il grafico intero sta in una fascia e tocca entrambi i bordi della fascia.
-->

#### **2.1 Le ipotesi sono tutte essenziali**

| Ipotesi violata | Controesempio | Cosa fallisce |
|---|---|---|
| Intervallo non chiuso | $f(x)=x$ su $(0,1)$ | limitata, ma $\sup=1$ e $\inf=0$ **non assunti** |
| Intervallo non chiuso | $f(x)=\frac{1}{x}$ su $(0,1]$ | continua ma **illimitata** |
| Intervallo illimitato | $f(x)=x$ su $[0,+\infty)$ | illimitata |
| Intervallo illimitato | $f(x)=\arctan x$ su $\mathbb{R}$ | limitata, ma $\sup=\frac{\pi}{2}$ non assunto |
| Continuità assente | $f(x)=\frac 1x$ per $x\in(0,1]$, $f(0)=0$, su $[0,1]$ | dominio giusto ma illimitata |

> 📌 Il ruolo di "$[a,b]$ chiuso e limitato" è esattamente quello che rende applicabile Bolzano-Weierstrass **con limite dentro il dominio**: limitatezza per estrarre la sottosuccessione, chiusura per non far scappare $c$ fuori da $[a,b]$. (Nel linguaggio dei corsi successivi: $[a,b]$ è compatto; qui non serve la parola, serve il meccanismo.)

**Corollario.** Combinando Weierstrass e valori intermedi (Lezione 4): l'immagine di $[a,b]$ tramite una funzione continua è **esattamente** l'intervallo chiuso e limitato $\left[\min f,\ \max f\right]$.

---
### **3. Continuità dell'inversa di una funzione monotona**

Serve l'ultimo anello della catena della Lezione 2: le inverse ($\ln$, $\arcsin$, $\arccos$, $\arctan$, radici, inverse iperboliche) sono continue.

**Teorema (dell'inversa continua).** Sia $I$ un intervallo e $f:I\to\mathbb{R}$ continua e **strettamente monotona**. Allora:

1. $f$ è iniettiva e $J=f(I)$ è un intervallo;
2. $f^{-1}:J\to I$ è strettamente monotona, **nello stesso verso** di $f$;
3. $f^{-1}$ è **continua** su $J$.

*Dimostrazione.* Sia $f$ strettamente crescente (il caso decrescente è simmetrico).

**(1)** L'iniettività è immediata ($x_1<x_2\Rightarrow f(x_1)<f(x_2)$); $J$ è un intervallo per il corollario dei valori intermedi (Lezione 4).

**(2)** Siano $y_1<y_2$ in $J$, $y_i=f(x_i)$. Se fosse $x_1\ge x_2$, la crescenza darebbe $y_1\ge y_2$: assurdo. Quindi $f^{-1}(y_1)=x_1<x_2=f^{-1}(y_2)$.

**(3)** Sia $y_0\in J$, $x_0=f^{-1}(y_0)$, e fissiamo $\varepsilon>0$. Trattiamo il caso in cui $x_0$ è interno ad $I$ (agli estremi si lavora con il solo lato disponibile, stessa stima). Riducendo $\varepsilon$ se serve, possiamo supporre $[x_0-\varepsilon,\ x_0+\varepsilon]\subseteq I$. Poniamo

$$
y_- = f(x_0-\varepsilon) < y_0 < f(x_0+\varepsilon) = y_+
$$

(disuguaglianze strette per stretta crescenza) e scegliamo

$$
\delta=\min\{\,y_0-y_-,\ y_+-y_0\,\}>0
$$

Se $y\in J$ e $|y-y_0|<\delta$, allora $y_-<y<y_+$; applicando la crescente $f^{-1}$ (punto 2):

$$
x_0-\varepsilon = f^{-1}(y_-) < f^{-1}(y) < f^{-1}(y_+) = x_0+\varepsilon
$$

cioè $|f^{-1}(y)-x_0|<\varepsilon$: continuità di $f^{-1}$ in $y_0$. $\square$

> 💡 Il punto sottile: la continuità di $f^{-1}$ **non** si ottiene "leggendo al contrario" la continuità di $f$ (l'inversa di una funzione continua qualunque può essere discontinua, se il dominio non è un intervallo: esercizio 4). Sono la **monotonia** e la struttura di intervallo a comprimere $f^{-1}$ tra i valori $y_\mp$.

#### **3.1 Il raccolto**

Applicazioni immediate, che chiudono i debiti della Lezione 2:

- $\exp:\mathbb{R}\to(0,+\infty)$ continua e strettamente crescente $\Rightarrow$ $\ln$ continua su $(0,+\infty)$;
- $\sin:[-\frac{\pi}{2},\frac{\pi}{2}]\to[-1,1]$ $\Rightarrow$ $\arcsin$ continua su $[-1,1]$; analoghi $\arccos$ (da $[0,\pi]$) e $\arctan$ (da $(-\frac{\pi}{2},\frac{\pi}{2})$, immagine $\mathbb{R}$);
- $x\mapsto x^n$ su $[0,+\infty)$ ($n$ pari) o $\mathbb{R}$ ($n$ dispari) $\Rightarrow$ continuità di $\sqrt[n]{x}$ (seconda strada, alternativa alla stima diretta);
- $\sinh:\mathbb{R}\to\mathbb{R}$ e $\tanh:\mathbb{R}\to(-1,1)$ $\Rightarrow$ inverse iperboliche continue (coerente con le formule logaritmiche di M01).

Vale anche un viceversa parziale, utile all'orale: una funzione **continua e iniettiva su un intervallo è necessariamente strettamente monotona** (se non lo fosse, tre punti disposti "a zig-zag" e i valori intermedi produrrebbero due punti con lo stesso valore, contro l'iniettività) — è la promessa fatta in M07 (UD1/L2A) sul legame iniettività-monotonia sotto continuità.

---
### **4. Errori frequenti**

- ❌ Invocare Weierstrass su $(a,b)$, $[a,+\infty)$ o su domini bucati: ogni riga della tabella 2.1 è un errore d'esame realmente commesso.
- ❌ Confondere $\sup$ con $\max$: Weierstrass dice che sotto le sue ipotesi il sup **è** un max; fuori dalle ipotesi la distinzione (M02/M07) torna essenziale.
- ❌ Dedurre la continuità dell'inversa dalla sola biiettività continua (serve l'intervallo: esercizio 4).
- ❌ Dimenticare che l'inversa di una crescente è crescente (non decrescente!), e che i domini si scambiano: $f^{-1}$ vive su $J=f(I)$.
- ❌ Citare "compattezza" come parola magica senza saper indicare dove si usano limitatezza e chiusura nella dimostrazione (estrazione B-W + passaggio al limite delle disuguaglianze).

---
### **5. Esercizi**

**Esercizio 1.** La funzione $f(x)=x^3-3x$ ammette massimo e minimo su $[-2,3]$? E su $(0,2)$?

*Soluzione.* Su $[-2,3]$: sì, Weierstrass (continua su chiuso e limitato) — senza bisogno di calcolarli. Su $(0,2)$: Weierstrass non si applica; di fatto $\inf$ e $\sup$ sui bordi non sono assunti e il minimo interno esiste ($x=1$): l'esistenza va discussa a mano — il teorema tace, non nega.

**Esercizio 2.** Sia $f:[0,+\infty)\to\mathbb{R}$ continua con $\lim_{x\to+\infty}f(x)=0$ e $f(0)>0$. Dimostra che $f$ assume massimo.

*Soluzione.* Per il limite, esiste $R$ tale che $f(x)<f(0)$ per $x>R$. Su $[0,R]$ Weierstrass dà un massimo $f(x_M)\ge f(0)$; per $x>R$ i valori sono $<f(0)\le f(x_M)$: $x_M$ è massimo globale. (Schema standard "Weierstrass + code controllate dal limite".)

**Esercizio 3.** Dimostra che $f(x)=x^5+x$ è invertibile su $\mathbb{R}$ e che $f^{-1}$ è continua, senza tentare di scrivere $f^{-1}$.

*Soluzione.* Somma di strettamente crescenti è strettamente crescente; $f$ continua (polinomio); immagine $\mathbb{R}$ (limiti $\pm\infty$ e valori intermedi). Il teorema dell'inversa dà $f^{-1}:\mathbb{R}\to\mathbb{R}$ continua e crescente.

**Esercizio 4 (controesempio all'inversa continua senza intervallo).** Sia $f:[0,1)\cup[2,3]\to[0,2]$ definita da $f(x)=x$ su $[0,1)$ e $f(x)=x-1$ su $[2,3]$. Mostra che $f$ è continua, strettamente crescente e biiettiva, ma $f^{-1}$ è discontinua.

*Soluzione.* Sui due pezzi $f$ è affine; la crescenza globale e la biiettività su $[0,2]$ si verificano direttamente ($[0,1)\to[0,1)$, $[2,3]\to[1,2]$). L'inversa vale $y$ su $[0,1)$ e $y+1$ su $[1,2]$: in $y_0=1$ ha laterali $1$ e $2$, un salto. Il dominio di $f$ non era un intervallo: l'ipotesi del teorema non è decorativa.

---
### **6. Riepilogo**

> ✅ Weierstrass: continua su $[a,b]$ $\Rightarrow$ limitata e dotata di max e min; dimostrazione in due estrazioni con Bolzano-Weierstrass + criterio sequenziale + caratterizzazione del sup; ogni ipotesi ha il suo controesempio ($x$ su $(0,1)$, $\frac 1x$ su $(0,1]$, $\arctan$ su $\mathbb{R}$, salto su $[0,1]$). Inversa: continua + strettamente monotona su un intervallo $\Rightarrow$ inversa strettamente monotona nello stesso verso e continua (stima con $y_\mp=f(x_0\mp\varepsilon)$); da qui $\ln$, $\arcsin$, $\arccos$, $\arctan$, radici e inverse iperboliche sono continue, e su un intervallo "continua + iniettiva $\Rightarrow$ strettamente monotona".
