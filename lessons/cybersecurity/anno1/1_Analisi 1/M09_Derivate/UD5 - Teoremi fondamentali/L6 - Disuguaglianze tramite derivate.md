# **M9 UD5 Lezione 6 - Disuguaglianze tramite derivate**

### **1. Obiettivo della lezione**

Il metodo più elegante per dimostrare disuguaglianze tra funzioni: **studiare la funzione differenza con la monotonia**. È l'applicazione principe delle conseguenze di Lagrange (Lezione 4), e produce in poche righe risultati che in M08 richiedevano costruzioni ad hoc — o che erano stati semplicemente rinviati.

---
### **2. Il metodo**

Per dimostrare $f(x)\le g(x)$ su un intervallo $[a,b)$ o $[a,+\infty)$:

1. definisci la **funzione differenza** $\varphi(x)=g(x)-f(x)$;
2. calcola $\varphi$ in un **punto di ancoraggio** (tipicamente il bordo $a$): serve $\varphi(a)\ge 0$, meglio se $=0$;
3. studia il **segno di $\varphi'$** sull'intervallo: se $\varphi'\ge 0$, $\varphi$ è crescente (Lezione 4), quindi $\varphi(x)\ge\varphi(a)\ge 0$ per ogni $x\ge a$;
4. concludi $g\ge f$; con $\varphi'>0$ per $x>a$ la disuguaglianza è **stretta** per $x>a$.

Se anche il segno di $\varphi'$ non è evidente, si itera: si studia $\varphi''$ per dedurre la monotonia di $\varphi'$, ancorandola in $a$ — una "cascata di monotonie".

---
### **3. I quattro classici**

#### **3.1 $\sin x < x$ per $x>0$**

$\varphi(x)=x-\sin x$: $\varphi(0)=0$ e $\varphi'(x)=1-\cos x\ge 0$, con zeri isolati ($x=2k\pi$): $\varphi$ è strettamente crescente (criterio esatto, Lezione 4), quindi $\varphi(x)>\varphi(0)=0$ per $x>0$. $\square$ — La disuguaglianza che in M08/UD3 era geometrica (aree nel cerchio, e valida solo su $(0,\frac{\pi}{2})$) qui è analitica e vale su **tutto** $(0,+\infty)$.

#### **3.2 $e^x\ge 1+x$ per ogni $x\in\mathbb{R}$**

$\varphi(x)=e^x-1-x$: $\varphi(0)=0$, $\varphi'(x)=e^x-1$, che è $<0$ per $x<0$ e $>0$ per $x>0$: $\varphi$ decresce fino a $0$ e poi cresce — $0$ è **minimo globale** (criterio della derivata prima, Lezione 4) con valore $0$. Quindi $\varphi\ge 0$ ovunque, con uguaglianza solo in $0$. $\square$ — In M05/M08 questa disuguaglianza era importata dalla costruzione di $e$; ora è un teorema autonomo, e la tangente $y=1+x$ in $0$ (UD1/L3) ne è la lettura geometrica: **il grafico sta sopra la tangente** (anticipazione della convessità, M10/UD4).

#### **3.3 $\ln x\le x-1$ per $x>0$**

$\varphi(x)=x-1-\ln x$: $\varphi(1)=0$, $\varphi'(x)=1-\frac 1x$, negativa su $(0,1)$ e positiva su $(1,+\infty)$: minimo globale in $1$ con valore $0$: $\varphi\ge 0$. $\square$ (Equivalente a 3.2 via $x=e^t$, ma il metodo diretto non richiede accorgersene.)

#### **3.4 $\dfrac{x}{1+x}\le\ln(1+x)\le x$ per $x>-1$**

Lato destro: $\varphi(x)=x-\ln(1+x)$, $\varphi(0)=0$, $\varphi'(x)=1-\frac{1}{1+x}=\frac{x}{1+x}$: negativa su $(-1,0)$, positiva su $(0,+\infty)$: minimo in $0$, $\varphi\ge 0$. Lato sinistro: $\psi(x)=\ln(1+x)-\frac{x}{1+x}$, $\psi(0)=0$,

$$
\psi'(x) = \frac{1}{1+x}-\frac{(1+x)-x}{(1+x)^2} = \frac{1}{1+x}-\frac{1}{(1+x)^2} = \frac{x}{(1+x)^2}
$$

stesso schema: minimo in $0$, $\psi\ge 0$. $\square$ — La doppia stima "incornicia" il logaritmo tra due razionali: è la versione quantitativa dell'equivalenza $\ln(1+x)\sim x$ (M08/UD4) e tornerà utile nelle stime integrali di M12.

<!-- TODO FIGURA:
Grafici sovrapposti di y=e^x e della sua tangente y=1+x in 0 (curva sopra la retta, tangenza nell'unico punto di contatto); secondo pannello con y=ln(1+x) stretta tra y=x (sopra) e y=x/(1+x) (sotto), tutte e tre tangenti tra loro in 0.
Obiettivo didattico: visualizzare le disuguaglianze come confronti grafico-tangente e grafico-cornice, con il punto di ancoraggio come punto di tangenza comune.
-->

---
### **4. Un esempio con cascata di monotonie**

**Dimostrare**: $\cos x \ge 1-\dfrac{x^2}{2}$ per ogni $x\in\mathbb{R}$.

$\varphi(x)=\cos x-1+\frac{x^2}{2}$, pari (basta $x\ge 0$), $\varphi(0)=0$. $\varphi'(x)=-\sin x+x$: il suo segno per $x>0$ è **esattamente il classico 3.1** ($x>\sin x$): $\varphi'>0$ su $(0,+\infty)$. Quindi $\varphi$ cresce da $\varphi(0)=0$: $\varphi\ge 0$. $\square$ — Iterando ancora si ottiene $\sin x\ge x-\frac{x^3}{6}$ per $x\ge 0$ (esercizio 2): la scala di disuguaglianze che, portata al limite, **è** lo sviluppo di Taylor di seno e coseno (M10/UD3) — il metodo delle differenze è Taylor in embrione.

---
### **5. Stime dell'incremento con Lagrange**

Variante del metodo quando il confronto è tra **incrementi**: dalla forma $f(x)-f(y)=f'(c)(x-y)$ (Lezione 3),

$$
|e^x-e^y| \le e^{M}\,|x-y| \qquad \text{per } x,y\in(-\infty,M]
$$

(la derivata $e^t$ è limitata da $e^M$ sull'intervallo: attenzione, su tutto $\mathbb{R}$ l'esponenziale **non** è Lipschitz). E già visto: $|\sin x-\sin y|\le|x-y|$, $|\arctan x-\arctan y|\le|x-y|$. Schema: maggiorare $|f'|$ sull'intervallo che contiene i punti, mai su uno più grande del necessario.

---
### **6. Errori frequenti**

- ❌ Dimenticare il **punto di ancoraggio**: il segno di $\varphi'$ da solo non basta, serve $\varphi\ge 0$ in un punto da cui partire.
- ❌ Ancorare nel punto sbagliato (per $x>a$ si ancora in $a$, non in un punto interno qualunque).
- ❌ Usare la monotonia fuori dall'intervallo dove il segno di $\varphi'$ è stato davvero stabilito.
- ❌ Concludere la disuguaglianza **stretta** dove c'è uguaglianza (in $3.2$, $e^x=1+x$ proprio in $x=0$).
- ❌ Invocare Taylor per queste disuguaglianze in M09: qui si usano solo monotonia e Lagrange; Taylor le raffinerà in M10, e sarà un'alternativa, non un prerequisito.
- ❌ Stimare $|f'|$ su un intervallo illimitato dove non è limitata ($e^x$ su $\mathbb{R}$).

---
### **7. Esercizi**

**Esercizio 1.** Dimostra che $\arctan x\le x$ per $x\ge 0$, con uguaglianza solo in $0$.

*Soluzione.* $\varphi(x)=x-\arctan x$: $\varphi(0)=0$, $\varphi'(x)=1-\frac{1}{1+x^2}=\frac{x^2}{1+x^2}\ge 0$ con zero isolato in $0$: strettamente crescente, $\varphi>0$ per $x>0$.

**Esercizio 2.** Dimostra che $\sin x\ge x-\dfrac{x^3}{6}$ per $x\ge 0$.

*Soluzione.* $\varphi(x)=\sin x-x+\frac{x^3}{6}$: $\varphi(0)=0$, $\varphi'(x)=\cos x-1+\frac{x^2}{2}\ge 0$ per il punto 4: $\varphi$ crescente da $0$. (Cascata di lunghezza due: 3.1 $\to$ punto 4 $\to$ questa.)

**Esercizio 3.** Dimostra che per $0<a<b$: $\ \dfrac{b-a}{b} < \ln\dfrac{b}{a} < \dfrac{b-a}{a}$.

*Soluzione.* Lagrange su $[a,b]$ per $\ln$: $\ln b-\ln a=\frac{b-a}{c}$ con $a<c<b$, e $\frac{1}{b}<\frac{1}{c}<\frac{1}{a}$ (reciproci su positivi). (Doppia stima logaritmica classica; equivale al 3.4 con $x=\frac{b-a}{a}$.)

**Esercizio 4.** Dimostra che $x^e \le e^x$ per ogni $x>0$, con uguaglianza solo in $x=e$.

*Soluzione.* Equivale (logaritmi, leciti su positivi) a $e\ln x\le x$, cioè $\frac{\ln x}{x}\le\frac 1e$. Studia $g(x)=\frac{\ln x}{x}$: $g'(x)=\frac{1-\ln x}{x^2}$, positiva su $(0,e)$, negativa su $(e,+\infty)$: massimo globale in $x=e$ con $g(e)=\frac 1e$. $\square$ (Corollario famoso: $e^\pi>\pi^e$.)

---
### **8. Riepilogo**

> ✅ Metodo della funzione differenza: ancoraggio in un punto + segno della derivata + monotonia $\Rightarrow$ disuguaglianza sull'intervallo; con cascate di derivate per i casi a più piani ($\cos x\ge 1-\frac{x^2}{2}$ e oltre — Taylor in embrione, rinviato a M10). Classici dimostrati: $\sin x<x$, $e^x\ge 1+x$ (grafico sopra la tangente), $\ln x\le x-1$, cornice $\frac{x}{1+x}\le\ln(1+x)\le x$; variante Lagrange per gli incrementi con $|f'|$ limitata sull'intervallo giusto. Errori chiave: niente ancoraggio, intervalli sbagliati, strette dove c'è tangenza.
