## **Lezione 5: Integrazione per parti — trucchetto del “per 1” e integrali ciclici**

---
### **1. Il trucchetto della moltiplicazione per 1**

A volte l’integrale **non sembra un prodotto**, quindi “non puoi” fare parti… ma puoi **crearti il prodotto** senza cambiare nulla:

$$

\int f(x),dx = \int f(x)\cdot 1,dx

$$

Così puoi applicare:

$$

\int f(x),g’(x),dx = f(x)g(x)-\int f’(x)g(x),dx

$$

Di solito scegli:

- $f(x)$ = la parte **facile da derivare** (log, arctan, …)
    
- $g’(x)=1$ così $g(x)=x$
    

---
#### **1.1 Esempio: $\int \ln x,dx$**

Scrivo:
$$

\int \ln x,dx = \int \ln x \cdot 1,dx

$$

Scelte:
$$

f(x)=\ln x \Rightarrow f’(x)=\frac{1}{x}

$$

$$

g’(x)=1 \Rightarrow g(x)=x

$$

Applico la formula:

$$

\int \ln x,dx = x\ln x - \int \frac{1}{x}\cdot x,dx

$$

Semplifico dentro:
$$

\int \frac{1}{x}\cdot x,dx = \int 1,dx = x

$$
Quindi:
$$

\int \ln x,dx = x\ln x - x + C

$$

Forma raccolta:
$$

\int \ln x,dx = x(\ln x - 1) + C

$$

---
#### **1.2 Esempio: $\int \arctan x,dx$**

Scrivo:
$$

\int \arctan x,dx=\int \arctan x \cdot 1,dx

$$
Scelte:
$$

f(x)=\arctan x \Rightarrow f’(x)=\frac{1}{1+x^2}

$$
$$

g’(x)=1 \Rightarrow g(x)=x

$$

Formula:
$$

\int \arctan x,dx = x\arctan x - \int x\cdot \frac{1}{1+x^2},dx

$$

Il secondo integrale diventa “da logaritmo” se faccio comparire $2x$:

$$

\int \frac{x}{1+x^2},dx = \frac{1}{2}\int \frac{2x}{1+x^2},dx

= \frac{1}{2}\ln(1+x^2)

$$

Quindi:
$$

\int \arctan x,dx = x\arctan x - \frac{1}{2}\ln(1+x^2) + C

$$

---
### **2. Integrali ciclici**

Un integrale è **ciclico** quando, dopo uno o due passaggi per parti, **ricompare lo stesso integrale** che stavi cercando (di solito con segno “meno”).

Schema tipico:

$$

I = \text{(qualcosa)} - I

\quad\Rightarrow\quad

2I=\text{(qualcosa)}

\quad\Rightarrow\quad

I=\frac{\text{(qualcosa)}}{2}

$$

---
#### **2.1 Mini-esempio “didattico”: $\int x,dx$ fatto per parti**

È inutile farlo così, ma serve per capire il meccanismo.

Scrivo:
$$

\int x,dx = \int x\cdot 1,dx

$$

Scelte:

$$

f(x)=x \Rightarrow f’(x)=1

\qquad

g’(x)=1 \Rightarrow g(x)=x

$$

Formula:
$$

\int x,dx = x\cdot x - \int 1\cdot x,dx

$$

Cioè:
$$

\int x,dx = x^2 - \int x,dx

$$
Porto a sinistra:
$$

2\int x,dx = x^2

$$

Divido per 2:
$$

\int x,dx = \frac{x^2}{2} + C

$$

---
#### **2.2 Esempio: $\int \cos^2 x,dx$ (ciclico)**

Scrivo:
$$

\int \cos^2 x,dx = \int \cos x \cdot \cos x,dx

$$
Scelte:
$$

f(x)=\cos x \Rightarrow f’(x)=-\sin x

\qquad

g’(x)=\cos x \Rightarrow g(x)=\sin x

$$
Formula:
$$

\int \cos^2 x,dx = \cos x\sin x - \int (-\sin x)\sin x,dx

$$
Quindi:
$$

\int \cos^2 x,dx = \sin x\cos x + \int \sin^2 x,dx

$$
Ora uso:
$$

\sin^2 x = 1-\cos^2 x

$$
Allora:
$$

\int \cos^2 x,dx = \sin x\cos x + \int (1-\cos^2 x),dx

$$

Spacco:
$$

\int \cos^2 x,dx = \sin x\cos x + \int 1,dx - \int \cos^2 x,dx

$$
Cioè:
$$

\int \cos^2 x,dx = \sin x\cos x + x - \int \cos^2 x,dx

$$

Porto a sinistra:
$$

2\int \cos^2 x,dx = \sin x\cos x + x

$$
Divido per 2:
$$

\int \cos^2 x,dx = \frac{1}{2}\left(\sin x\cos x + x\right) + C

$$

---
#### **2.3 Esempio classico: $\int e^x\sin x,dx$ (ciclico a 2 passi)**

Definisco:
$$

I=\int e^x\sin x,dx

$$

**Primo passaggio per parti**

Scelte:
$$

f(x)=e^x \Rightarrow f’(x)=e^x

\qquad

g’(x)=\sin x \Rightarrow g(x)=-\cos x

$$
Formula:
$$

I = e^x(-\cos x) - \int e^x(-\cos x),dx

$$
Quindi:
$$

I = -e^x\cos x + \int e^x\cos x,dx

$$
Ora chiamo:
$$

J=\int e^x\cos x,dx

$$

**Secondo passaggio per parti su $J$**

Mantengo $f(x)=e^x$ (questa è la scelta “giusta” per non tornare indietro):

$$

f(x)=e^x \Rightarrow f’(x)=e^x

\qquad

g’(x)=\cos x \Rightarrow g(x)=\sin x

$$
Allora:
$$

J = e^x\sin x - \int e^x\sin x,dx

$$

Ma $\int e^x\sin x,dx$ è proprio $I$, quindi:

$$

J = e^x\sin x - I

$$

Torno in:
$$

I = -e^x\cos x + J

$$
Sostituisco $J$:
$$

I = -e^x\cos x + (e^x\sin x - I)

$$
Quindi:
$$

I = e^x(\sin x - \cos x) - I

$$

Porto $-I$ a sinistra:
$$

2I = e^x(\sin x - \cos x)

$$
Divido per 2:
$$

\int e^x\sin x,dx = \frac{1}{2}e^x(\sin x - \cos x) + C

$$

---
### **3. Regole d’oro da portarsi via**

- Se l’integrale **non è un prodotto**, prova a scriverlo come $f(x)\cdot 1$.
    
- Se dopo parti ti ricompare **lo stesso integrale**, fermati: è **ciclico**.
    
- Negli integrali tipo $e^x\sin x$, $e^x\cos x$ devi fare **due passaggi** e poi “chiudere” portando l’integrale dall’altra parte.