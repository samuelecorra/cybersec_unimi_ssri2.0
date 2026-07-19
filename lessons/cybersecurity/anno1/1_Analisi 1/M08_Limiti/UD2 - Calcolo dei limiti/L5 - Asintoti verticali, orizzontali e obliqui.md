# **M8 UD2 Lezione 5 - Asintoti verticali, orizzontali e obliqui**

### **1. Che cos'è un asintoto**

Un **asintoto** è una retta a cui il grafico di una funzione si avvicina indefinitamente in una delle direzioni ammesse dal dominio. Il concetto è interamente costruito sui limiti: ogni tipo di asintoto è la traduzione geometrica di un limite specifico. In questa lezione formalizziamo le tre famiglie — verticali, orizzontali, obliqui — con definizioni, criteri, dimostrazioni ed errori tipici.

---
### **2. Asintoto verticale**

**Definizione.** La retta $x=x_0$ è un **asintoto verticale** per $f$ se **almeno uno** dei due limiti laterali è infinito:

$$
\lim_{x\to x_0^+}f(x)=\pm\infty
\qquad\text{oppure}\qquad
\lim_{x\to x_0^-}f(x)=\pm\infty
$$

(purché il lato considerato abbia senso, cioè $x_0$ sia di accumulazione da quel lato per il dominio).

Osservazioni:

- basta **un lato**: $f(x)=\ln x$ ha l'asintoto $x=0$ con il solo limite destro ($\ln x\to-\infty$ per $x\to 0^+$; a sinistra il dominio non c'è);
- i due lati possono avere **segni diversi**: $f(x)=\frac{1}{x}$ in $x=0$ ($+\infty$ a destra, $-\infty$ a sinistra) — l'asintoto c'è comunque;
- una funzione può avere **infiniti** asintoti verticali: $\tan x$ ne ha uno in ogni $x=\frac{\pi}{2}+k\pi$, con $\tan x\to+\infty$ da sinistra e $\to-\infty$ da destra;
- il grafico **non attraversa mai** un asintoto verticale nei pressi del punto (la funzione non è definita in $x_0$ o è definita con un valore isolato), a differenza di quanto può accadere con orizzontali e obliqui.

> ⚠️ **Zero del denominatore $\neq$ asintoto verticale.** L'asintoto va **certificato con i limiti laterali**. Controesempio: $f(x)=\dfrac{x^2-1}{x-1}$ ha denominatore nullo in $x=1$, ma il limite vale $2$ (cancellazione): in $x=1$ c'è un "buco", non un asintoto. Il denominatore nullo è solo un **candidato** da esaminare.

**Dove cercarli**: nei punti di accumulazione del dominio esclusi dal dominio (zeri del denominatore non cancellati, bordi come $0$ per $\ln$), mai nei punti interni di continuità.

<!-- TODO FIGURA:
Tre pannelli: (a) grafico di 1/(x-2) con asintoto verticale x=2 tratteggiato e rami di segno opposto; (b) grafico di ln(x) con asintoto x=0 raggiunto dal solo lato destro; (c) grafico di (x^2-1)/(x-1), cioè la retta y=x+1 con cerchietto vuoto in (1,2), e nessun asintoto, a dispetto del denominatore nullo.
Obiettivo didattico: contrapporre asintoto bilaterale, asintoto da un solo lato e falso candidato con cancellazione.
-->

---
### **3. Asintoto orizzontale**

**Definizione.** La retta $y=L$ è un **asintoto orizzontale** per $f$ per $x\to+\infty$ se

$$
\lim_{x\to+\infty}f(x)=L\in\mathbb{R}
$$

e analogamente per $x\to-\infty$. La direzione va sempre dichiarata.

Osservazioni:

- i due lati dell'infinito sono **indipendenti**: una funzione può avere due asintoti orizzontali diversi. Esempi: $\tanh x$ (asintoti $y=1$ e $y=-1$, UD2/L3) e $\arctan x$ (asintoti $y=\frac{\pi}{2}$ e $y=-\frac{\pi}{2}$);
- può esserci asintoto da un lato solo: $e^x$ ha $y=0$ solo per $x\to-\infty$ (per $x\to+\infty$ diverge);
- il grafico **può attraversare** un asintoto orizzontale, anche infinite volte: $f(x)=\frac{\sin x}{x}$ per $x\to+\infty$ tende a $0$ oscillando attorno a $y=0$ (carabinieri: $|f(x)|\le\frac 1x$). "Asintoto" non significa "barriera";
- se $\lim_{x\to+\infty}f(x)=\pm\infty$ o non esiste, per quel lato non c'è asintoto orizzontale (e si passa a cercare l'obliquo).

---
### **4. Asintoto obliquo**

**Definizione.** La retta $y=mx+q$ con $m\neq 0$ è un **asintoto obliquo** per $f$ per $x\to+\infty$ se

$$
\lim_{x\to+\infty}\big[f(x)-(mx+q)\big]=0
$$

(idem per $x\to-\infty$). La condizione dice che la **distanza verticale** tra grafico e retta si annulla all'infinito.

#### **4.1 Le formule per $m$ e $q$**

**Teorema.** Se $y=mx+q$ è asintoto obliquo per $x\to+\infty$, allora necessariamente

$$
m=\lim_{x\to+\infty}\frac{f(x)}{x},
\qquad
q=\lim_{x\to+\infty}\big[f(x)-mx\big]
$$

Viceversa, se questi due limiti esistono **entrambi finiti** (con $m\neq 0$), la retta $y=mx+q$ è asintoto obliquo.

*Dimostrazione.* ($\Rightarrow$) Posto $r(x)=f(x)-mx-q\to 0$:

$$
\frac{f(x)}{x} = m+\frac{q}{x}+\frac{r(x)}{x} \longrightarrow m+0+0 = m
$$

(algebra dei limiti: $\frac{q}{x}\to 0$ e $\frac{r(x)}{x}\to 0$ perché $r\to 0$ e $\frac 1x\to 0$). Poi $f(x)-mx = q+r(x)\to q$. ($\Leftarrow$) Se i due limiti esistono finiti, allora $f(x)-mx-q\to q-q=0$ per definizione di $q$. $\square$

> 📌 La procedura operativa è quindi in **due passi obbligati**: prima $m$ (limite di $\frac{f(x)}{x}$: deve esistere finito e non nullo), poi $q$ (limite di $f(x)-mx$: deve esistere finito). **Entrambi** i limiti devono esistere: se $m$ esiste ma $q$ no, l'asintoto non c'è.

#### **4.2 Esempio completo**

$$
f(x)=\frac{x^2+1}{x-1} \qquad (x\to+\infty)
$$

Passo 0: $f(x)\to+\infty$, niente orizzontale. Passo 1:

$$
m=\lim_{x\to+\infty}\frac{x^2+1}{x(x-1)} = \lim_{x\to+\infty}\frac{x^2+1}{x^2-x} = 1
$$

Passo 2:

$$
q=\lim_{x\to+\infty}\left[\frac{x^2+1}{x-1}-x\right] = \lim_{x\to+\infty}\frac{x^2+1-x^2+x}{x-1} = \lim_{x\to+\infty}\frac{x+1}{x-1} = 1
$$

Asintoto obliquo: $y=x+1$ (stesso risultato per $x\to-\infty$, con conti identici). In alternativa, per le razionali la divisione euclidea (M01) dà subito $\frac{x^2+1}{x-1} = x+1+\frac{2}{x-1}$: la parte lineare è l'asintoto e il resto $\frac{2}{x-1}\to 0$.

#### **4.3 Quando l'asintoto obliquo non c'è**

- $f(x)=\sqrt{x}$: $\frac{f(x)}{x}=\frac{1}{\sqrt{x}}\to 0=m$, ma $f(x)-0\cdot x=\sqrt{x}\to+\infty$: né obliquo ($m$ deve essere $\neq 0$) né orizzontale. Crescita "sublineare" senza asintoto;
- $f(x)=x^2$: $\frac{f(x)}{x}=x\to+\infty$: nessun $m$ finito, nessun asintoto (crescita superlineare);
- $f(x)=x+\ln x$: $m=\lim\frac{x+\ln x}{x}=1$, ma $q=\lim(x+\ln x-x)=\lim\ln x=+\infty$: $m$ esiste, $q$ no, **niente asintoto** — l'esempio da citare quando si spiega perché servono entrambi i limiti;
- $f(x)=x+\frac{\sin x}{\ln x}$... si può complicare a piacere: la coppia $(m,q)$ è un filtro severo.

I due versi dell'infinito sono indipendenti anche qui: $f(x)=\sqrt{x^2+x}=|x|\sqrt{1+\frac 1x}$ ha asintoto $y=x+\frac{1}{2}$ per $x\to+\infty$ e $y=-x-\frac{1}{2}$ per $x\to-\infty$ (esercizio 3).

> 💡 **Approfondimento (asintoti polinomiali).** Se $f(x)-p(x)\to 0$ per un polinomio $p$ di grado $\ge 2$ si parla di "asintoto curvilineo" (esempio: $\frac{x^3+1}{x}=x^2+\frac 1x$ è asintotica alla parabola $y=x^2$). Non è materia standard d'esame: la segnaliamo solo perché la logica — separare la parte polinomiale da un resto infinitesimo — è identica.

<!-- TODO FIGURA:
Grafico di f(x) = (x^2+1)/(x-1) con i suoi due rami, l'asintoto verticale x=1 tratteggiato e l'asintoto obliquo y=x+1 tratteggiato; evidenziare con una doppia freccia la distanza verticale tra grafico e retta obliqua che si schiaccia a zero per x -> ±infinito.
Obiettivo didattico: mostrare la coesistenza di asintoto verticale e obliquo sulla stessa funzione e il significato geometrico della condizione f(x)-(mx+q) -> 0.
-->

---
### **5. Schema riassuntivo di ricerca degli asintoti**

Dato il dominio di $f$:

1. **verticali**: per ogni punto di accumulazione escluso dal dominio (o bordo), calcola i limiti laterali; se almeno uno è $\pm\infty$, asintoto verticale;
2. **orizzontali**: per ogni direzione di infinito ammessa dal dominio, calcola $\lim f(x)$; se finito $=L$, asintoto $y=L$ per quel lato (e per quel lato hai finito);
3. **obliqui**: solo dove il limite all'infinito è $\pm\infty$, calcola $m$ e poi $q$; se entrambi finiti con $m\neq 0$, asintoto $y=mx+q$.

Per lo stesso lato dell'infinito, orizzontale e obliquo si **escludono a vicenda** (se $f\to L$ finito allora $\frac fx\to 0$); lati diversi sono storie diverse.

---
### **6. Errori frequenti**

- ❌ Dichiarare l'asintoto verticale dal solo denominatore nullo, senza limiti laterali (cancellazioni!).
- ❌ Dimenticare che basta un lato per il verticale, o pretendere che i due lati abbiano lo stesso segno.
- ❌ Credere che il grafico non possa toccare un asintoto orizzontale/obliquo ($\frac{\sin x}{x}$ taglia $y=0$ infinite volte).
- ❌ Fermarsi a $m$ senza calcolare $q$ ($x+\ln x$ insegna).
- ❌ Cercare l'obliquo dove c'è già l'orizzontale, o su un lato dove il dominio non è illimitato.
- ❌ Unificare i due infiniti: $m$ e $q$ vanno calcolati separatamente per $x\to+\infty$ e $x\to-\infty$ (i moduli dai radicali cambiano segno!).

---
### **7. Esercizi**

**Esercizio 1.** Trova tutti gli asintoti di $f(x)=\dfrac{2x^2-3x+1}{x-2}$.

*Soluzione.* Dominio $x\neq 2$. In $2$: numeratore $\to 3\neq 0$, laterali $\pm\infty$ (studio del segno: $+\infty$ da destra, $-\infty$ da sinistra): asintoto verticale $x=2$. All'infinito: divisione euclidea $f(x)=2x+1+\frac{3}{x-2}$: asintoto obliquo $y=2x+1$ su entrambi i lati.

**Esercizio 2.** Trova gli asintoti di $f(x)=\dfrac{x}{\sqrt{x^2+1}}$.

*Soluzione.* Dominio $\mathbb{R}$, nessun verticale. $\sqrt{x^2+1}=|x|\sqrt{1+\frac{1}{x^2}}$: per $x\to+\infty$ il rapporto $\to 1$, per $x\to-\infty$ $\to -1$: due asintoti orizzontali, $y=1$ e $y=-1$.

**Esercizio 3.** Dimostra che $f(x)=\sqrt{x^2+x}$ ha asintoto $y=-x-\frac{1}{2}$ per $x\to-\infty$.

*Soluzione.* $m=\lim_{x\to-\infty}\frac{\sqrt{x^2+x}}{x}$: con $\sqrt{x^2+x}=|x|\sqrt{1+\frac 1x}=-x\sqrt{1+\frac 1x}$ (qui $|x|=-x$), $m=\lim(-\sqrt{1+\frac 1x})=-1$. Poi $q=\lim_{x\to-\infty}\left[\sqrt{x^2+x}+x\right]$: razionalizzando, $\frac{x}{\sqrt{x^2+x}-x}=\frac{x}{-x\sqrt{1+1/x}-x}=\frac{1}{-\sqrt{1+1/x}-1}\to-\frac{1}{2}$. Asintoto $y=-x-\frac 12$.

**Esercizio 4.** Vero o falso: "se $f$ è definita su $\mathbb{R}$ e continua, non può avere asintoti verticali". 

*Soluzione.* Vero: un asintoto verticale in $x_0$ richiede un limite laterale infinito, incompatibile con la continuità in $x_0$ (che impone limite finito $=f(x_0)$; la teoria nella UD5).

---
### **8. Riepilogo**

> ✅ Verticale: $x=x_0$ con almeno un laterale $\pm\infty$ — certificato dai limiti, non dal denominatore. Orizzontale: $y=L$ se $f\to L$ finito, per direzione dichiarata; i due infiniti sono indipendenti e il grafico può attraversarlo. Obliquo: $y=mx+q$ con $f-(mx+q)\to 0$; formule $m=\lim\frac fx$, $q=\lim(f-mx)$, **entrambi** finiti e $m\neq 0$ (dimostrate al punto 4.1). Ricerca in tre passi: verticali sui punti esclusi, poi orizzontali, poi obliqui solo dove serve.
