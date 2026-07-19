# **M8 UD5 Lezione 3 - Classificazione delle discontinuità ed estensione per continuità**

### **1. Obiettivo della lezione**

Quando la catena "valore-limite-uguaglianza" della continuità si spezza, si parla di **discontinuità**. Le classifichiamo in base a **come** si spezza, con una tassonomia dichiarata (i nomi variano tra i testi: fissiamo la nostra convenzione e la confrontiamo con le altre), e studiamo quando il difetto è riparabile tramite l'**estensione per continuità**.

**Convenzione di perimetro.** Classifichiamo il comportamento di $f$ in un punto $x_0$ che sia di accumulazione per il dominio e in cui $f$ sia definita **oppure no**: nel secondo caso (es. il "buco" di $\frac{\sin x}{x}$ in $0$) parliamo comunque di "discontinuità eliminabile" in senso esteso, segnalando che alcuni testi riservano la parola "discontinuità" ai soli punti del dominio. Ciò che conta all'esame è descrivere correttamente laterali, limite e valore.

---
### **2. La classificazione**

Sia $x_0$ di accumulazione (da entrambi i lati, salvo dire) per il dominio di $f$.

#### **2.1 Discontinuità eliminabile**

Il limite bilaterale **esiste finito**,

$$
\lim_{x\to x_0}f(x)=L\in\mathbb{R}
$$

ma $f(x_0)\neq L$ oppure $f(x_0)$ non è definito.

Esempi:

- $f(x)=\dfrac{\sin x}{x}$ in $x_0=0$: limite $1$, punto fuori dominio;
- $f(x)=\begin{cases}x+1, & x\neq 1\\ 5, & x=1\end{cases}$ in $x_0=1$: limite $2$, valore $5$.

È il difetto "minimo": si ripara cambiando (o assegnando) **un solo valore** — punto 3.

#### **2.2 Discontinuità a salto (prima specie)**

I limiti laterali esistono **entrambi finiti ma diversi**:

$$
f(x_0^-)=\lim_{x\to x_0^-}f(x)\neq\lim_{x\to x_0^+}f(x)=f(x_0^+), \qquad f(x_0^\pm)\in\mathbb{R}
$$

La differenza $f(x_0^+)-f(x_0^-)$ si chiama **salto**. Il valore $f(x_0)$, se esiste, può coincidere con uno dei due laterali o con nessuno: la classificazione non cambia.

Esempi: la funzione segno in $0$ (salto $2$); la parte intera $\lfloor x\rfloor$ in ogni intero (salto $1$); il gradino di Heaviside.

#### **2.3 Discontinuità infinita**

Almeno un limite laterale è **infinito** ($\pm\infty$). Esempi: $\frac{1}{x}$ in $0$ (laterali $\mp\infty$... precisamente $-\infty$ a sinistra e $+\infty$ a destra), $\frac{1}{x^2}$ in $0$ (entrambi $+\infty$), $e^{1/x}$ in $0$ (sinistro $0$, destro $+\infty$: basta un lato infinito). Geometricamente corrisponde a un asintoto verticale (UD2/L5).

#### **2.4 Discontinuità oscillatoria**

Almeno un limite laterale **non esiste nemmeno esteso**, per oscillazione. Esempio canonico: $\sin\frac{1}{x}$ in $0$ (UD1/L3E): nessun laterale, oscillazione limitata; oppure $\frac{1}{x}\sin\frac 1x$, con oscillazione illimitata.

> 📌 **Nota terminologica.** Molti testi raggruppano 2.3 e 2.4 come "**seconda specie**" (almeno un laterale infinito o inesistente) e chiamano "**terza specie**" l'eliminabile; altri numerano eliminabile/salto/infinita come prima/seconda/terza. Le etichette non sono standard: in un compito conviene **descrivere** i laterali e il valore, e usare i nomi solo come riassunto, dichiarando la convenzione se il contesto è ambiguo. La nostra: eliminabile, a salto (prima specie), infinita, oscillatoria (le ultime due insieme: seconda specie).

**Schema decisionale**: calcola $f(x_0^-)$ e $f(x_0^+)$.

1. Entrambi finiti e uguali $=L$: se $f(x_0)=L$ continuità; altrimenti **eliminabile**;
2. entrambi finiti e diversi: **salto**;
3. almeno uno $\pm\infty$: **infinita**;
4. almeno uno inesistente: **oscillatoria**.

<!-- TODO FIGURA:
Quattro pannelli affiancati, uno per tipo: (1) eliminabile: retta con cerchietto vuoto in x_0 e pallino pieno spostato in alto; (2) salto: due rami a quote diverse con pallino pieno su uno; (3) infinita: ramo che esplode a +infinito con asintoto verticale tratteggiato; (4) oscillatoria: sin(1/x) compresso vicino a x_0. Sotto ogni pannello l'etichetta con la condizione sui laterali.
Obiettivo didattico: dare la tassonomia visiva completa delle discontinuità collegando ciascun disegno alla condizione formale sui limiti laterali.
-->

---
### **3. Estensione per continuità**

**Definizione/Teorema.** Sia $x_0$ di accumulazione per $A$, $x_0\notin A$ (o $f(x_0)\neq L$), e supponiamo che

$$
\lim_{x\to x_0}f(x)=L\in\mathbb{R}
$$

La funzione

$$
\widetilde{f}(x) = \begin{cases} f(x), & x\in A\setminus\{x_0\} \\ L, & x=x_0 \end{cases}
$$

(detta **estensione per continuità** o prolungamento continuo di $f$ a $x_0$) è continua in $x_0$.

*Dimostrazione.* $\widetilde f$ coincide con $f$ nell'intorno puntato, quindi per il carattere locale (UD1/L3D) $\lim_{x\to x_0}\widetilde f(x)=\lim_{x\to x_0}f(x)=L=\widetilde f(x_0)$: è la definizione di continuità. $\square$

Esempi:

- $\dfrac{\sin x}{x}$ si prolunga a $0$ con il valore $1$: la funzione estesa è continua su $\mathbb{R}$;
- $x\sin\dfrac{1}{x}$ si prolunga a $0$ con $0$;
- $\dfrac{x^2-1}{x-1}$ si prolunga a $1$ con $2$ — e l'estensione **coincide** con la funzione $x+1$ su tutto $\mathbb{R}$;
- $\dfrac{1}{x}$ e $\sin\dfrac 1x$ **non** si prolungano a $0$: nessun valore può funzionare, perché il limite finito non esiste. L'estensione è possibile **se e solo se** il limite bilaterale esiste finito.

> ⚠️ Distinzioni da tenere nitide (già incontrate in UD1/L3D e UD2/L1):
>
> - **semplificazione algebrica** ($\frac{x^2-1}{x-1}=x+1$ per $x\neq 1$): identità tra espressioni su un dominio puntato, lecita nel calcolo del limite;
> - **funzione originaria**: resta quella, con il suo dominio bucato;
> - **estensione continua** $\widetilde f$: è una **funzione diversa**, con dominio più grande; scrivere $f=\widetilde f$ è un abuso. L'esame chiede spesso proprio "si può prolungare con continuità?": la risposta è il limite.

---
### **4. Le funzioni monotone non fanno cose strane**

**Teorema (discontinuità delle monotone).** Sia $f$ **monotona** su un intervallo $I$. Allora in ogni punto interno $x_0$ i limiti laterali esistono finiti, con (caso crescente)

$$
f(x_0^-)=\sup_{x<x_0}f(x) \ \le\ f(x_0)\ \le\ \inf_{x>x_0}f(x)=f(x_0^+)
$$

quindi le uniche discontinuità possibili sono **a salto**. Inoltre i punti di discontinuità sono al più un'infinità **numerabile**.

*Dimostrazione.* Sia $f$ crescente e $x_0$ interno. L'insieme $\{f(x): x\in I,\ x<x_0\}$ è non vuoto e maggiorato da $f(x_0)$: sia $s$ il suo sup (M02/M05). Mostriamo $f(x_0^-)=s$: dato $\varepsilon>0$, per la caratterizzazione del sup esiste $x_1<x_0$ con $f(x_1)>s-\varepsilon$; per monotonia, per ogni $x\in(x_1,x_0)$ vale $s-\varepsilon<f(x_1)\le f(x)\le s$: con $\delta=x_0-x_1$ la definizione di limite sinistro è soddisfatta. Simmetricamente per l'inf a destra. Niente laterali infiniti o oscillanti: restano solo continuità o salto. Per la numerabilità: a ogni punto di salto $x_0$ si associa un intervallo aperto non vuoto $\big(f(x_0^-),f(x_0^+)\big)$; per monotonia questi intervalli sono a due a due disgiunti, e ognuno contiene un razionale (densità, M02): salti distinti, razionali distinti, quindi i salti sono al più quanti i razionali, numerabili (M03). $\square$

Questo teorema è il ponte verso la continuità dell'inversa (Lezione 5) e spiega perché le patologie oscillatorie richiedono funzioni non monotone in ogni intorno.

---
### **5. Errori frequenti**

- ❌ Classificare guardando il grafico "a occhio" senza calcolare i laterali: la definizione è sui limiti.
- ❌ Chiamare "eliminabile" un salto piccolo: eliminabile richiede laterali **uguali**.
- ❌ Dire che $\frac 1x$ ha una discontinuità eliminabile "perché basta togliere il punto": nessun valore in $0$ rende continua la funzione.
- ❌ Dimenticare che nella discontinuità a salto il valore $f(x_0)$ può essere qualunque (o mancare): la specie dipende dai laterali.
- ❌ Usare i nomi "prima/seconda/terza specie" senza dichiarare la convenzione, in contesti dove il lettore può usarne un'altra.
- ❌ Credere che una funzione monotona possa avere discontinuità oscillatorie o infinite nei punti interni: il teorema del punto 4 lo esclude.

---
### **6. Esercizi**

**Esercizio 1.** Classifica le discontinuità (o la continuità) in $x_0=0$ per: (a) $\dfrac{e^x-1}{x}$; (b) $\dfrac{|x|}{x}$; (c) $\dfrac{1}{x^2}$; (d) $\cos\dfrac 1x$; (e) $x^2\sin\dfrac 1x$ (non definita in $0$).

*Soluzione.* (a) limite $1$ finito, punto fuori dominio: eliminabile (prolungamento con $1$). (b) laterali $\pm 1$: salto. (c) entrambi $+\infty$: infinita. (d) laterali inesistenti: oscillatoria. (e) limite $0$: eliminabile con valore $0$.

**Esercizio 2.** Determina $k$ affinché $f(x)=\begin{cases}\dfrac{\ln(1+3x)}{x}, & x\neq 0\\ k, & x=0\end{cases}$ sia continua in $0$; in mancanza del $k$ giusto, classifica.

*Soluzione.* Limite $=3$ (notevole scalato): $k=3$ dà continuità; ogni altro $k$ dà discontinuità eliminabile.

**Esercizio 3.** Stabilisci se $f(x)=\lfloor x\rfloor + (x-\lfloor x\rfloor)^2$ è continua o discontinua negli interi (attenzione alle apparenze).

*Soluzione.* In $n$: da destra $\lfloor x\rfloor=n$ e la parte frazionaria tende a $0$, quindi $f\to n=f(n)$; da sinistra $\lfloor x\rfloor=n-1$ e $(x-\lfloor x\rfloor)^2\to 1$, quindi $f(n^-)=(n-1)+1=n$. I laterali coincidono col valore: $f$ è **continua** negli interi, nonostante contenga $\lfloor x\rfloor$ — il quadrato della parte frazionaria ricuce esattamente il salto. Morale: la diagnosi si fa con i laterali, non con la presenza di pezzi "sospetti".

**Esercizio 4.** Esibisci una funzione con discontinuità a salto in ogni intero e continua altrove, e una con un'unica discontinuità, oscillatoria, in $0$.

*Soluzione.* $\lfloor x\rfloor$ per la prima; $g(x)=\sin\frac 1x$ per $x\neq 0$, $g(0)=0$ per la seconda.

---
### **7. Riepilogo**

> ✅ Diagnosi sempre dai laterali: uguali e finiti ma $\neq$ valore (o valore assente) $=$ **eliminabile**; finiti e diversi $=$ **salto** (prima specie); almeno uno infinito $=$ **infinita**; almeno uno inesistente $=$ **oscillatoria** (con "seconda specie" per le ultime due — convenzione dichiarata). L'estensione per continuità esiste se e solo se il limite bilaterale è finito, e consiste nell'assegnare quel valore: funzione **nuova**, non un ritocco della vecchia. Le monotone ammettono solo salti, al più numerabili (dimostrato con sup/inf e densità dei razionali).
