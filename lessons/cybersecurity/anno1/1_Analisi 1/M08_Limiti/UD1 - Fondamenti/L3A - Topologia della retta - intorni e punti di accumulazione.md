# **M8 UD1 Lezione 3A - Topologia della retta: intorni e punti di accumulazione**

### **1. Perché serve un minimo di topologia**

La definizione di limite della Lezione 3 poggia su due parole: **intorno** e **punto di accumulazione**. In questa lezione mettiamo a fuoco queste nozioni con la precisione necessaria per il resto del modulo, classificando i punti della retta rispetto a un insieme $A\subseteq\mathbb{R}$.

L'obiettivo è **strumentale**: non sviluppiamo una teoria topologica generale, ma esattamente il vocabolario che serve per capire quando un limite ha senso, dove si può fare un limite laterale e perché certi punti del dominio non ammettono alcun limite.

---
### **2. Richiami: intorno e intorno puntato**

Dalla Lezione 3, per $x_0\in\mathbb{R}$ e $\delta>0$:

$$
U_\delta(x_0) = (x_0-\delta,\ x_0+\delta),
\qquad
U_\delta^\ast(x_0) = U_\delta(x_0)\setminus\{x_0\}
$$

- $U_\delta(x_0)$ è l'**intorno** di centro $x_0$ e raggio $\delta$: intervallo aperto e simmetrico attorno a $x_0$;
- $U_\delta^\ast(x_0)$ è l'**intorno puntato**: lo stesso intervallo **senza il centro**;
- gli **intorni laterali** sono $(x_0,\ x_0+\delta)$ (destro) e $(x_0-\delta,\ x_0)$ (sinistro).

Ogni proprietà che vale "in un intorno di $x_0$" per almeno un raggio $\delta$ si dice **proprietà locale**. Il limite è l'esempio principe di proprietà locale: dipende solo dal comportamento della funzione in intorni puntati arbitrariamente piccoli, e rimpicciolire il raggio non la distrugge (se un $\delta$ funziona, ogni $\delta'<\delta$ funziona).

<!-- TODO FIGURA:
Asse reale orizzontale con punto x_0 evidenziato. Sopra: parentesi tonde che delimitano (x_0-delta, x_0+delta) con la fascia colorata; sotto, la stessa fascia con un "buco" bianco nel solo punto x_0 a rappresentare l'intorno puntato. Etichette U_delta(x_0) e U*_delta(x_0), frecce sul raggio delta da x_0 verso i due estremi.
Obiettivo didattico: distinguere visivamente intorno e intorno puntato e mostrare che differiscono per un solo punto, il centro.
-->

---
### **3. Classificazione dei punti rispetto a un insieme**

Sia $A\subseteq\mathbb{R}$ e sia $x_0\in\mathbb{R}$ (non necessariamente appartenente ad $A$). Si danno le definizioni seguenti.

#### **3.1 Punto interno**

$x_0$ è **interno** ad $A$ se esiste $\delta>0$ tale che

$$
U_\delta(x_0)\subseteq A
$$

cioè: $x_0$ sta in $A$ **con tutto un intorno**. Un punto interno appartiene sempre ad $A$.

#### **3.2 Punto esterno**

$x_0$ è **esterno** ad $A$ se è interno al complementare, cioè se esiste $\delta>0$ tale che

$$
U_\delta(x_0)\cap A = \varnothing
$$

Un punto esterno non appartiene mai ad $A$ e ha un intorno che non tocca $A$.

#### **3.3 Punto di frontiera**

$x_0$ è **di frontiera** per $A$ se non è né interno né esterno: **ogni** intorno di $x_0$ contiene sia punti di $A$ sia punti del complementare:

$$
\forall\delta>0:\quad U_\delta(x_0)\cap A\neq\varnothing \ \text{ e }\ U_\delta(x_0)\cap(\mathbb{R}\setminus A)\neq\varnothing
$$

Un punto di frontiera può appartenere o non appartenere ad $A$.

#### **3.4 Punto di accumulazione**

$x_0$ è **di accumulazione** per $A$ se ogni intorno **puntato** di $x_0$ contiene punti di $A$:

$$
\forall\delta>0:\quad U_\delta^\ast(x_0)\cap A\neq\varnothing
$$

Equivalentemente: in ogni intorno di $x_0$ cadono **infiniti** punti di $A$ (se ne cadesse un numero finito, il più vicino a $x_0$ fornirebbe un raggio che li esclude tutti). L'insieme dei punti di accumulazione di $A$ si chiama **derivato** di $A$ e si indica con $A'$.

#### **3.5 Punto isolato**

$x_0$ è **isolato** in $A$ se appartiene ad $A$ ma esiste un intorno che di $A$ contiene solo lui:

$$
x_0\in A \quad\text{e}\quad \exists\delta>0:\ U_\delta(x_0)\cap A=\{x_0\}
$$

> 📌 Ogni punto di $A$ è **o isolato o di accumulazione** per $A$, mai entrambe le cose: le due condizioni ($U_\delta^\ast\cap A$ sempre non vuoto contro $U_\delta^\ast\cap A$ vuoto per qualche $\delta$) sono una la negazione dell'altra per i punti di $A$. La **chiusura** di $A$ è $\overline{A}=A\cup A'$: un punto "appartiene alla chiusura" se sta in $A$ oppure vi si accumula; appartenere ad $A$ e appartenere a $\overline{A}$ non sono la stessa cosa.

<!-- TODO FIGURA:
Asse reale con l'insieme A = (0,1) unito {2} disegnato con tratto pieno su (0,1) e un punto isolato in 2. Cinque punti campione con etichette: 0.5 (interno), 0 e 1 (di frontiera e di accumulazione, cerchietti vuoti), 2 (isolato, di frontiera), 3 (esterno). Piccoli intorni tratteggiati attorno a ciascun punto che illustrano la rispettiva definizione.
Obiettivo didattico: mostrare su un unico esempio tutte le categorie di punti e il fatto che i punti di accumulazione 0 e 1 non appartengono all'insieme.
-->

---
### **4. Esempio guida completo**

Sia

$$
A=(0,1)\cup\{2\}
$$

Classifichiamo i punti notevoli:

| Punto | Interno? | Di frontiera? | Isolato? | Di accumulazione? | In $A$? |
|---|---|---|---|---|---|
| $0{,}5$ | sì | no | no | sì | sì |
| $0$ | no | sì | no | **sì** | **no** |
| $1$ | no | sì | no | **sì** | **no** |
| $2$ | no | sì | **sì** | no | sì |
| $3$ | no (esterno) | no | no | no | no |

Le due righe evidenziate sono il cuore della questione:

- $0$ e $1$ **non appartengono** ad $A$ ma sono di accumulazione: ha perfettamente senso calcolare $\lim_{x\to 0}f(x)$ o $\lim_{x\to 1}f(x)$ per una funzione definita su $A$;
- $2$ **appartiene** ad $A$ ma è isolato: la scrittura $\lim_{x\to 2}f(x)$ non ha significato, perché non esistono punti del dominio vicini a $2$ e diversi da $2$ su cui osservare la funzione.

Il derivato è $A'=[0,1]$: si noti che $2\notin A'$ e che $0,1\in A'\setminus A$, mentre la chiusura è $\overline A = [0,1]\cup\{2\}$.

Altri esempi utili:

- $A=\mathbb{N}$ (dentro $\mathbb{R}$): **tutti** i punti sono isolati, $A'=\varnothing$. Coerenza con M05: per le successioni non si fa il limite "in un punto" ma per $n\to\infty$, l'unica direzione di accumulazione disponibile;
- $A=\left\{\frac{1}{n} : n\in\mathbb{N},\ n\ge 1\right\}$: ogni $\frac{1}{n}$ è isolato, ma $0$ è punto di accumulazione (e $0\notin A$): $A'=\{0\}$;
- $A=\mathbb{Q}$: ogni reale è di accumulazione ($\mathbb{Q}$ è denso in $\mathbb{R}$, M02), quindi $\mathbb{Q}'=\mathbb{R}$.

---
### **5. Accumulazione laterale**

Per i limiti laterali (Lezione 3C) serve la versione unilaterale:

- $x_0$ è di accumulazione **da destra** per $A$ se ogni intorno destro interseca $A$: $\forall\delta>0,\ (x_0,\ x_0+\delta)\cap A\neq\varnothing$;
- $x_0$ è di accumulazione **da sinistra** per $A$ se ogni intorno sinistro interseca $A$: $\forall\delta>0,\ (x_0-\delta,\ x_0)\cap A\neq\varnothing$.

$x_0$ è di accumulazione per $A$ se e solo se lo è da destra **oppure** da sinistra (basta un lato). Esempio: per $A=[0,1]$, il punto $0$ è di accumulazione solo da destra: per $x\to 0$ ha senso soltanto il limite destro, ed è per questo che scritture come $\lim_{x\to 0^+}\sqrt{x}$ sono complete così, senza il lato sinistro.

---
### **6. Perché il limite richiede un punto di accumulazione**

Rileggiamo la definizione di limite: "$\forall\varepsilon>0\ \exists\delta>0\ \forall x\in A:\ 0<|x-x_0|<\delta \Rightarrow |f(x)-L|<\varepsilon$".

Se $x_0$ **non** è di accumulazione per $A$, esiste un $\delta_0$ tale che nessun $x\in A$ soddisfa $0<|x-x_0|<\delta_0$. Allora, per ogni $\varepsilon$, scegliendo quel $\delta_0$ l'implicazione è **vera a vuoto** (non c'è nessun $x$ da controllare): la definizione risulterebbe soddisfatta da **qualunque** $L\in\mathbb{R}$ contemporaneamente. Un "limite" che vale $5$ e anche $-3$ e anche $\pi$ non trasporta alcuna informazione e distruggerebbe il teorema di unicità (Lezione 3D).

> 📌 Per questo lo standard analitico adottato in tutto il corso è: **il limite si definisce solo nei punti di accumulazione del dominio**. Alcuni testi ammettono formalmente il limite "vacuo" nei punti isolati: è una convenzione alternativa che rinunciamo ad usare, e va dichiarata se la si incontra altrove.

Nota bene la separazione dei ruoli:

- $x_0$ deve essere di accumulazione per $A$ — ma può non stare in $A$;
- $f(x_0)$ può non esistere, o esistere con qualunque valore: il limite non lo guarda;
- il limite osserva $f$ **solo** su $U_\delta^\ast(x_0)\cap A$.

---
### **7. Errori frequenti**

- ❌ "Se $f$ non è definita in $x_0$, il limite per $x\to x_0$ non esiste." Falso: serve solo che $x_0$ sia di accumulazione per il dominio. $\lim_{x\to 0}\frac{\sin x}{x}=1$ con $0$ fuori dal dominio.
- ❌ "Se $x_0\in A$, allora il limite ha senso." Falso nei punti isolati: $x_0\in A$ non basta.
- ❌ Confondere punto di frontiera e punto di accumulazione: $2$ nell'esempio guida è di frontiera ma non di accumulazione; ogni punto di $[0,1]$ interno è di accumulazione ma non di frontiera.
- ❌ Dire che un insieme infinito ha sempre punti di accumulazione: $\mathbb{N}$ è infinito e non ne ha nessuno (in $\mathbb{R}$). Il teorema di Bolzano-Weierstrass (M05) garantisce punti di accumulazione solo per insiemi **infiniti e limitati**.

---
### **8. Esercizi**

**Esercizio 1.** Per $A=\left(0,1\right]\cup\{3\}\cup\left\{2-\frac{1}{n}: n\ge 1\right\}$ determina interni, isolati, di accumulazione e la chiusura.

*Soluzione.* Interni: $(0,1)$. Isolati: $3$ e tutti i punti $2-\frac{1}{n}$ con $n\ge 2$ tranne quelli che cadono in $(0,1]$: attenzione, $2-\frac{1}{n}\in(1,2)$ per $n\ge 2$ — il punto $2-\frac{1}{1}=1$ appartiene già a $(0,1]$. Di accumulazione: $[0,1]\cup\{2\}$ (il punto $2$ è limite della successione $2-\frac{1}{n}$ e non appartiene ad $A$). Chiusura: $[0,1]\cup\left\{2-\frac{1}{n}\right\}\cup\{2\}\cup\{3\}$.

**Esercizio 2.** Stabilisci per quali punti ha senso $\lim_{x\to x_0} f(x)$ se $f$ ha dominio $A=\mathbb{R}\setminus\{1\}$.

*Soluzione.* $A'=\mathbb{R}$: ha senso per ogni $x_0$ reale, **compreso** $x_0=1$ (che non sta nel dominio ma vi si accumula da entrambi i lati), oltre ai limiti per $x\to\pm\infty$.

**Esercizio 3.** Vero o falso: "se $x_0$ è di accumulazione per $A$ e per $B$, lo è per $A\cap B$".

*Soluzione.* Falso: $A=(0,1)$, $B=(1,2)$, $x_0=1$ è di accumulazione per entrambi ma $A\cap B=\varnothing$ non ha punti di accumulazione.

---
### **9. Riepilogo**

> ✅ Intorno $=$ intervallo aperto centrato; intorno puntato $=$ intorno senza il centro; proprietà locale $=$ vera in qualche intorno. Rispetto ad $A$, un punto è interno/esterno/di frontiera (tricotomia) e, separatamente, di accumulazione o meno; i punti di $A$ si dividono in isolati e di accumulazione. Il limite si definisce **solo** nei punti di accumulazione del dominio, che possono non appartenere al dominio; nei punti isolati il limite non si definisce. L'accumulazione laterale governa i limiti laterali.
