## **Lezione 7: Sostituzione, quando usare $dy$ o quando ricavare $dx$**

---
### **1. I due metodi sono equivalenti**

Hai un integrale e fai una sostituzione:

$$

y=g(x)

$$

Da qui puoi procedere in due modi.

---
#### **Metodo 1: ricavo $dy$**

Derivo rispetto a $x$:
$$

dy=g’(x),dx

$$

Poi cerco dentro l’integrale un pezzo uguale a $g’(x),dx$ (o lo faccio comparire con fattori).

---
#### **Metodo 2: ricavo $x$ e poi $dx$**

Inverto la sostituzione (se possibile):
$$

x=g^{-1}(y)

$$
Poi derivo rispetto a $y$:
$$

dx=\frac{d}{dy}(g^{-1}(y)),dy

$$
Questo metodo spesso è comodo quando **nell’integrale non si “vede” bene il $dy$**.

---
### **2. Quale conviene?**

- **Se il pezzo $g’(x),dx$ è evidente dentro l’integrale** → **Metodo 1** (meno passaggi, meno rischio di errori).
    
- **Se il pezzo $g’(x),dx$ non è evidente, o ci sono radici “in giro”** → spesso conviene **Metodo 2** (ricavi $dx$ subito pulito).

---
### **3. Esempio “di confronto” (quello dell’esponenziale)**

Integrale tipico:

$$

\int \frac{e^x}{1+e^{2x}},dx

$$

Sostituzione naturale:
$$

y=e^x

$$

- Metodo 1: $dy=e^x dx$ e diventa $\int \frac{1}{1+y^2}dy$
    
- Metodo 2: $x=\ln y \Rightarrow dx=\frac{1}{y}dy$, poi semplifichi e arrivi **allo stesso identico integrale**.

Risultato:

$$

\arctan(e^x)+C

$$

---

## **4. Esempi avanzati della lezione**

---

### **4.1 $\int \sin^4 x ,\cos^3 x,dx$**

Qui conviene separare un $\cos x$ per costruire il $dy$:

$$

\cos^3 x=\cos^2 x\cdot \cos x

$$

Sostituzione:
$$

y=\sin x

\qquad\Rightarrow\qquad

dy=\cos x,dx

$$

Allora:

$$
\int \sin^4 x \cos^3 x,dx =

\int \sin^4 x;\cos^2 x;(\cos x,dx) =

\int y^4;\cos^2 x;dy
$$

Uso identità:
$$

\cos^2 x = 1-\sin^2 x = 1-y^2

$$

Quindi:

$$
\int y^4(1-y^2),dy =

\int (y^4-y^6),dy =

\frac{y^5}{5}-\frac{y^7}{7}+C
$$

Ritorno a $x$:
$$

\frac{\sin^5 x}{5}-\frac{\sin^7 x}{7}+C

$$

---
### **4.2 $\int \frac{x-1}{1+\sqrt{x}},dx$**

Qui la radice è l’oggetto “pesante” e il $dy$ non è comodo da riconoscere dentro l’integrale, quindi **Metodo 2**.

Sostituzione:
$$

y=\sqrt{x}

\qquad\Rightarrow\qquad

x=y^2,\quad dx=2y,dy

$$

Sostituisco:

$$
\int \frac{x-1}{1+\sqrt{x}},dx =

\int \frac{y^2-1}{1+y};2y,dy
$$

Scomposizione utile:
$$

y^2-1=(y-1)(y+1)

$$
Allora:

$$

\frac{y^2-1}{1+y}=\frac{(y-1)(y+1)}{y+1}=y-1

$$

Quindi:

$$
\int 2y(y-1),dy =  

\int (2y^2-2y),dy =  

\frac{2}{3}y^3-y^2+C
$$

Ritorno a $x$ con $y=\sqrt{x}$:

$$
\frac{2}{3}(\sqrt{x})^3-(\sqrt{x})^2+C =  

\frac{2}{3}x\sqrt{x}-x+C
$$

_(Nota della lezione: se prima semplificavi l’integranda, potevi vedere direttamente che era $\sqrt{x}-1$ e arrivare allo stesso risultato.)_

---
### **4.3 $\int \frac{x}{\sqrt{x-1}},dx$**
  
Qui il $dy$ **si vede** bene, quindi va benissimo **Metodo 1**.

Sostituzione:
$$

y=\sqrt{x-1}

$$
Derivo:
$$

y=\sqrt{x-1}\Rightarrow dy=\frac{1}{2\sqrt{x-1}},dx

\Rightarrow \frac{dx}{\sqrt{x-1}}=2,dy

$$

Perfetto: nell’integrale compare proprio $\frac{dx}{\sqrt{x-1}}$.
Mi serve anche $x$ in funzione di $y$:

$$

y^2=x-1 \Rightarrow x=y^2+1

$$  

Ora sostituisco tutto:
$$
\int \frac{x}{\sqrt{x-1}},dx = 
\int x\left(\frac{dx}{\sqrt{x-1}}\right) =
\int (y^2+1)\cdot 2,dy =

2\int (y^2+1),dy
$$

Integro:  

$$
2\left(\frac{y^3}{3}+y\right)+C =
\frac{2}{3}y^3+2y+C
$$

Ritorno a $x$:
$$

\frac{2}{3}(x-1)^{3/2}+2\sqrt{x-1}+C

$$

---
## **5. Regola pratica da tenere in testa**

### **Quando ci sono radici**

Molto spesso conviene:
$$

y=\sqrt[n]{\text{qualcosa}}

\quad\Rightarrow\quad

x=\text{potenza di }y

\quad\Rightarrow\quad

dx=\dots dy

$$

Perché ti “ripulisce” l’integrale e ti evita di inseguire pezzi tipo $\frac{1}{2\sqrt{x}}dx$ nascosti.