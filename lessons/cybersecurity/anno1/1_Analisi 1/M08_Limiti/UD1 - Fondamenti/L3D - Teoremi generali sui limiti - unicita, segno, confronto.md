# **M8 UD1 Lezione 3D - Teoremi generali sui limiti: unicità, segno, confronto**

### **1. Obiettivo della lezione**

Con la definizione ben capita (Lezioni 3-3C), dimostriamo i teoremi strutturali che valgono per **ogni** limite, prima ancora di qualunque tecnica di calcolo: unicità, carattere locale, limitatezza locale, permanenza del segno, confronto e carabinieri. In tutta la lezione $f,g,h:A\to\mathbb{R}$ e $x_0$ è un punto di accumulazione di $A$; salvo avviso, tutti gli enunciati valgono identici per limiti laterali e per $x\to\pm\infty$ (basta sostituire il tipo di intorno, come nella Lezione 3C), e useremo la locuzione "**definitivamente** per $x\to x_0$" per dire "per ogni $x\in A$ in un opportuno intorno puntato di $x_0$".

---
### **2. Unicità del limite**

**Teorema.** Se $\displaystyle\lim_{x\to x_0}f(x)=L$ e $\displaystyle\lim_{x\to x_0}f(x)=M$ con $L,M\in\mathbb{R}$, allora $L=M$.

*Dimostrazione.* Supponiamo per assurdo $L\neq M$ e scegliamo

$$
\varepsilon = \frac{|L-M|}{3} > 0
$$

Per la prima ipotesi esiste $\delta_1>0$ tale che $0<|x-x_0|<\delta_1 \Rightarrow |f(x)-L|<\varepsilon$; per la seconda esiste $\delta_2>0$ tale che $0<|x-x_0|<\delta_2 \Rightarrow |f(x)-M|<\varepsilon$. Sia $\delta=\min\{\delta_1,\delta_2\}$. Poiché $x_0$ è di accumulazione per $A$, esiste **almeno un** $x^\ast\in A$ con $0<|x^\ast-x_0|<\delta$ (è qui che l'ipotesi di accumulazione è indispensabile). Per quel punto valgono entrambe le stime, e per la disuguaglianza triangolare:

$$
|L-M| \le |L-f(x^\ast)|+|f(x^\ast)-M| < 2\varepsilon = \frac{2}{3}|L-M|
$$

cioè $|L-M|<\frac{2}{3}|L-M|$: assurdo perché $|L-M|>0$. Quindi $L=M$. $\square$

Geometricamente: con $\varepsilon=\frac{|L-M|}{3}$ gli intorni $V_\varepsilon(L)$ e $V_\varepsilon(M)$ sono **disgiunti**, e $f(x^\ast)$ non può stare in entrambi. L'unicità si estende ai limiti estesi: un intorno di $+\infty$ e uno di un $L$ finito (o di $-\infty$) si possono sempre rendere disgiunti, quindi anche in $\overline{\mathbb{R}}$ il limite, se esiste, è unico. È l'unicità che autorizza la scrittura funzionale "$\lim_{x\to x_0} f(x) = \dots$".

<!-- TODO FIGURA:
Asse y con due candidati L e M distinti, fasce orizzontali di semiampiezza |L-M|/3 attorno a ciascuno, visibilmente disgiunte con un varco tra loro. Un punto f(x*) con due frecce contraddittorie verso entrambe le fasce e un simbolo di assurdo.
Obiettivo didattico: mostrare la scelta epsilon = |L-M|/3 che rende disgiunti gli intorni e rende impossibile l'appartenenza simultanea.
-->

---
### **3. Carattere locale del limite**

**Teorema.** Siano $f,g$ definite su $A$ e coincidenti in un intorno puntato di $x_0$: esiste $r>0$ tale che $f(x)=g(x)$ per ogni $x\in U_r^\ast(x_0)\cap A$. Allora, se $\displaystyle\lim_{x\to x_0}f(x)=L$ (anche $L=\pm\infty$), anche $\displaystyle\lim_{x\to x_0}g(x)=L$.

*Dimostrazione.* Fissato $\varepsilon>0$, l'ipotesi su $f$ dà $\delta_1$ con $0<|x-x_0|<\delta_1\Rightarrow|f(x)-L|<\varepsilon$. Posto $\delta=\min\{\delta_1,r\}$, per $0<|x-x_0|<\delta$ vale sia la stima sia $g(x)=f(x)$, quindi $|g(x)-L|<\varepsilon$. Il caso $L=\pm\infty$ è identico con $f(x)>M$ al posto della stima. $\square$

**Conseguenze** (già usate in pratica nella UD2):

1. modificare o definire arbitrariamente $f$ **nel solo punto** $x_0$ non altera nessun limite in $x_0$;
2. semplificare un'espressione algebrica valida per $x\neq x_0$ è lecito nel calcolo del limite: per

$$
f(x)=\frac{x^2-1}{x-1} \quad (x\neq 1)
$$

si ha $f(x)=x+1$ su tutto $U_r^\ast(1)$, quindi $\lim_{x\to 1}f(x)=\lim_{x\to 1}(x+1)=2$;

3. la funzione originaria però **non cambia**: resta non definita in $1$ (il suo dominio è un dato, non un'opinione); coincide con $x+1$ solo fuori da $1$. Distinzione che tornerà nell'estensione per continuità (UD5, Lezione 3).

---
### **4. Limite finito implica limitatezza locale**

**Teorema.** Se $\displaystyle\lim_{x\to x_0}f(x)=L\in\mathbb{R}$, allora $f$ è **limitata in un intorno puntato** di $x_0$: esistono $\delta>0$ e $C>0$ tali che $|f(x)|\le C$ per ogni $x\in A$ con $0<|x-x_0|<\delta$.

*Dimostrazione.* Applichiamo la definizione con la scelta concreta $\varepsilon=1$: esiste $\delta>0$ tale che $0<|x-x_0|<\delta\Rightarrow|f(x)-L|<1$. Per la disuguaglianza triangolare, in quell'intorno puntato:

$$
|f(x)| \le |f(x)-L|+|L| < 1+|L|
$$

Basta $C=1+|L|$. $\square$

Tre distinzioni da tenere separate:

- la limitatezza è **locale e puntata**: nulla vieta a $f$ di essere illimitata lontano da $x_0$, o addirittura nel punto stesso se ridefinita lì;
- **non vale il viceversa**: $\sin\frac{1}{x}$ è limitata vicino a $0$ ma senza limite — la limitatezza locale è necessaria, non sufficiente;
- il teorema **fallisce** per limiti infiniti: se $f\to+\infty$, $f$ è per definizione illimitata in ogni intorno puntato.

---
### **5. Permanenza del segno**

**Teorema (permanenza del segno).** Se $\displaystyle\lim_{x\to x_0}f(x)=L>0$, allora esiste $\delta>0$ tale che

$$
f(x)>\frac{L}{2}>0 \qquad \text{per ogni } x\in A \text{ con } 0<|x-x_0|<\delta
$$

In particolare $f$ è definitivamente positiva **e separata da zero**.

*Dimostrazione.* Scegliamo $\varepsilon=\dfrac{L}{2}>0$. La definizione fornisce $\delta$ tale che, nell'intorno puntato, $|f(x)-L|<\frac{L}{2}$, cioè

$$
L-\frac{L}{2} < f(x) < L+\frac{L}{2}
\quad\Longrightarrow\quad
f(x) > \frac{L}{2} \qquad \square
$$

Enunciati gemelli, con la stessa prova:

- se $L<0$, definitivamente $f(x)<\frac{L}{2}<0$;
- se $L=+\infty$, definitivamente $f(x)>M$ per qualunque quota (direttamente dalla definizione);
- **confronto con una costante**: se $L>c$, scegliendo $\varepsilon=L-c$ si ottiene $f(x)>c$ definitivamente (il caso precedente è $c=0$... e la separazione dal denominatore nullo della Lezione 3F sarà il caso $|f|>\frac{|L|}{2}$).

> ⚠️ **Il caso $L=0$ non dice nulla sul segno**: $f(x)=x$ cambia segno in ogni intorno di $0$ pur avendo limite $0$. E il viceversa del teorema va indebolito: da $f(x)>0$ definitivamente segue solo $L\ge 0$, non $L>0$ (esempio: $f(x)=x^2>0$ per $x\neq 0$, limite $0$). Il passaggio al limite **indebolisce le disuguaglianze strette**.

---
### **6. Teorema del confronto**

**Teorema.** Se definitivamente per $x\to x_0$ vale $f(x)\le g(x)$, e se $f\to L$ e $g\to M$ (con $L,M\in\overline{\mathbb{R}}$), allora

$$
L \le M
$$

*Dimostrazione* (caso $L,M\in\mathbb{R}$)*.* Per assurdo sia $L>M$. La quantità $L-M$ è positiva: scegliamo $\varepsilon=\dfrac{L-M}{2}$. Definitivamente valgono contemporaneamente (minimo dei tre raggi: quello della disuguaglianza, quello di $f$, quello di $g$):

$$
f(x)>L-\varepsilon = \frac{L+M}{2} = M+\varepsilon > g(x)
$$

Poiché $x_0$ è di accumulazione, un punto $x^\ast$ in cui ciò accade esiste davvero: $f(x^\ast)>g(x^\ast)$ contraddice l'ipotesi $f\le g$. $\square$ (I casi con $\pm\infty$ sono immediati: ad esempio se $L=+\infty$ e $M\in\mathbb{R}$, definitivamente $f>M+1\ge g$ dà lo stesso assurdo.)

**Corollario utile**: se $f\le g$ definitivamente e $f\to+\infty$, allora $g\to+\infty$ (la minorante che esplode trascina la maggiorante); simmetricamente se $g\to-\infty$ allora $f\to-\infty$.

> ⚠️ Da $f(x)<g(x)$ **stretta** non segue $L<M$. Controesempio: $f(x)=0$ e $g(x)=x^2$ per $x\neq 0$: $f<g$ ovunque nel puntato, ma i limiti in $0$ sono entrambi $0$. Al limite sopravvive solo $\le$.

---
### **7. Teorema dei carabinieri**

**Teorema (dei due carabinieri).** Se definitivamente per $x\to x_0$

$$
f(x)\le g(x)\le h(x)
$$

e se $\displaystyle\lim_{x\to x_0}f(x)=\lim_{x\to x_0}h(x)=L\in\mathbb{R}$, allora anche

$$
\lim_{x\to x_0}g(x)=L
$$

*Dimostrazione.* Fissato $\varepsilon>0$, esistono $\delta_1,\delta_2,\delta_3$ tali che nell'intorno puntato di raggio $\delta=\min\{\delta_1,\delta_2,\delta_3\}$ valgono insieme: la doppia disuguaglianza, $L-\varepsilon<f(x)<L+\varepsilon$ e $L-\varepsilon<h(x)<L+\varepsilon$. Allora, per ogni $x\in A$ con $0<|x-x_0|<\delta$:

$$
L-\varepsilon < f(x) \le g(x) \le h(x) < L+\varepsilon
$$

cioè $|g(x)-L|<\varepsilon$. $\square$

Nota che il teorema **fornisce anche l'esistenza** del limite di $g$, non solo il suo valore: è questo a renderlo prezioso per funzioni oscillanti, delle quali nessun altro teorema saprebbe dire nulla.

**Applicazioni tipiche** (le prime due già usate operativamente in UD2/L4 e UD3/L1):

1. **fattore limitato per infinitesimo**: se $|g(x)|\le C$ definitivamente e $\varphi(x)\to 0$, allora $g(x)\varphi(x)\to 0$, perché $-C|\varphi(x)|\le g(x)\varphi(x)\le C|\varphi(x)|$. Esempio: $x\sin\frac{1}{x}\to 0$ per $x\to 0$;
2. **stime trigonometriche**: da $\cos x\le\frac{\sin x}{x}\le 1$ segue il limite fondamentale (UD3/L1); da $0\le 1-\cos x\le\frac{x^2}{2}$ segue $\cos x\to 1$;
3. **valori assoluti**: $-|f(x)|\le f(x)\le|f(x)|$, quindi se $|f(x)|\to 0$ allora $f(x)\to 0$ (attenzione: solo con limite $0$ il modulo controlla la funzione);
4. **oscillazioni smorzate**: $-e^{-x}\le e^{-x}\sin x\le e^{-x}$ per $x\to+\infty$ dà limite $0$.

<!-- TODO FIGURA:
Grafico con tre curve: h(x) sopra, f(x) sotto, entrambe convergenti allo stesso valore L in x_0 (fascia L±epsilon evidenziata), e g(x) oscillante schiacciata tra le due; tipico esempio x^2*sin(1/x) tra -x^2 e x^2 vicino a 0 con L=0.
Obiettivo didattico: visualizzare i due carabinieri che costringono la funzione centrale nella stessa fascia epsilon, inclusa l'esistenza del limite per una funzione oscillante.
-->

---
### **8. Errori frequenti**

- ❌ Usare l'unicità al contrario: "ho trovato un candidato, quindi è il limite". L'unicità dice che di limiti ce n'è al più uno, non che il tuo candidato lo sia.
- ❌ Dedurre dal limite positivo che $f>0$ **ovunque**: la permanenza del segno è locale e puntata.
- ❌ Dedurre il segno quando $L=0$, o conservare le disuguaglianze strette al limite.
- ❌ Applicare i carabinieri con i due estremi che tendono a **limiti diversi**: il teorema non dice nulla, e $g$ può fare qualunque cosa nel mezzo.
- ❌ Dimenticare che "definitivamente" richiede l'**intersezione** degli intorni: ogni ipotesi ha il suo raggio, e si lavora nel minimo (è il passaggio $\delta=\min\{\cdot\}$ ripetuto in ogni prova).
- ❌ Citare la limitatezza locale come criterio di esistenza: è solo una condizione necessaria.

---
### **9. Esercizi**

**Esercizio 1.** Dimostra che $\displaystyle\lim_{x\to 0}x^2\sin\frac{1}{x}=0$ e spiega perché l'algebra dei limiti da sola non basta.

*Soluzione.* $-x^2\le x^2\sin\frac 1x\le x^2$ e i carabinieri concludono. L'algebra non basta perché $\sin\frac 1x$ **non ha limite** in $0$: il teorema sul prodotto richiederebbe l'esistenza dei limiti dei fattori.

**Esercizio 2.** Sia $f$ tale che $\lim_{x\to 3}f(x)=7$. Dimostra che esiste un intorno puntato di $3$ in cui $f(x)>6$.

*Soluzione.* Permanenza del segno nella forma "confronto con una costante": $\varepsilon=1$ dà $6<f(x)<8$ definitivamente.

**Esercizio 3.** Trova due funzioni $f<g$ ovunque su $\mathbb{R}\setminus\{0\}$ con lo stesso limite in $0$, e due funzioni con $f\le g$ ma $\lim f>\lim g$ in nessun punto (spiega perché quest'ultimo caso è impossibile).

*Soluzione.* Primo caso: $f=-x^2$, $g=x^2$. Secondo caso: impossibile per il teorema del confronto — è proprio il suo contenuto.

**Esercizio 4.** Usando il corollario del confronto, dimostra che $\displaystyle\lim_{x\to+\infty}\left(x+\sin x\right)=+\infty$.

*Soluzione.* $x+\sin x\ge x-1$ per ogni $x$, e $x-1\to+\infty$: la minorante trascina.

---
### **10. Riepilogo**

> ✅ Cinque teoremi con cinque scelte-chiave: unicità ($\varepsilon=\frac{|L-M|}{3}$, intorni disgiunti), carattere locale ($\delta=\min\{\delta_1,r\}$: il limite vede solo un intorno puntato), limitatezza locale ($\varepsilon=1$), permanenza del segno ($\varepsilon=\frac{L}{2}$, con separazione da zero), confronto ($\le$ sopravvive al limite, $<$ no) e carabinieri ($\delta$ minimo di tre raggi, e l'esistenza è parte della tesi). Tutto vale per laterali e all'infinito sostituendo gli intorni. Questi risultati sono i mattoni delle dimostrazioni della Lezione 3F.
