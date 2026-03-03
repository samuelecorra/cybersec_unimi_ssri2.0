## **Lezione 10: Funzioni razionali con fattori “speciali” al denominatore**

In questa lezione completiamo il metodo dei **fratti semplici** nei casi in cui, fattorizzando il denominatore, compaiono:

1. un **fattore di secondo grado non scomponibile** (tipo $x^2+1$),
    
2. una **potenza di un fattore lineare** (tipo $(x-1)^2$, $(x-3)^n$).  

La procedura generale resta la stessa (4 step), ma cambia **lo step 3**, cioe **come scrivi la decomposizione**.

---
# **1. Caso A: fattore di 2 grado irriducibile**

### **Esempio 1**

$$

\int \frac{1}{x^3+x},dx

$$

---
## **1.1 Step 1: divisione**

Non serve, perche:
$$

\deg(\text{num})=0<3=\deg(\text{den})

$$

---
## **1.2 Step 2: fattorizzazione**

$$

x^3+x=x(x^2+1)

$$
Con $x^2+1$ **irriducibile** su $\mathbb{R}$.

---
## **1.3 Step 3: decomposizione corretta**

Qui la regola e:

- sopra al fattore lineare $x$ metti una costante: $\frac{A}{x}$
    
- sopra al fattore quadratico irriducibile $x^2+1$ metti un **polinomio di 1 grado**: $\frac{Bx+C}{x^2+1}$  

Quindi:
$$

\frac{1}{x(x^2+1)}=\frac{A}{x}+\frac{Bx+C}{x^2+1}

$$
Denominatore comune:
$$

1=A(x^2+1)+x(Bx+C)

$$
Sviluppo:
$$

1=Ax^2+A+Bx^2+Cx

$$
Raggruppo:
$$

1=(A+B)x^2+Cx+A

$$

Confronto coefficienti con $1=0\cdot x^2+0\cdot x+1$:

$$

\begin{cases}

A+B=0\

C=0\

A=1

\end{cases}

$$
Quindi:
$$

A=1,\quad B=-1,\quad C=0

$$
Decomposizione finale:
$$

\frac{1}{x(x^2+1)}=\frac{1}{x}-\frac{x}{x^2+1}

$$

---
## **1.4 Step 4: integrazione**

$$

\int \frac{1}{x(x^2+1)},dx=\int\frac{1}{x},dx-\int\frac{x}{x^2+1},dx

$$
Primo pezzo:
$$

\int\frac{1}{x},dx=\ln|x|

$$

Secondo pezzo: manca la derivata del denominatore $2x$, quindi:

$$

\int\frac{x}{x^2+1},dx=\frac12\int\frac{2x}{x^2+1},dx=\frac12\ln(x^2+1)

$$  
Risultato:
$$

\ln|x|-\frac12\ln(x^2+1)+C

$$
Forma compatta (solo estetica):
$$

\ln\left|\frac{x}{\sqrt{x^2+1}}\right|+C

$$

---
# **2. Caso B: fattore lineare ripetuto $(x-a)^n$**

### **Regola chiave dello step 3**

Se nel denominatore hai:
$$

(x-a)^n

$$

allora nella decomposizione devi mettere **n frazioni**, una per ogni potenza:

$$

\frac{A_1}{x-a}+\frac{A_2}{(x-a)^2}+ \dots +\frac{A_n}{(x-a)^n}

$$

---
## **Esempio 2**

$$

\int \frac{x+1}{x^3-2x^2+x},dx

$$

---
## **2.1 Step 1: divisione**

Non serve, perche:  

$$

\deg(\text{num})=1<3=\deg(\text{den})

$$

---
## **2.2 Step 2: fattorizzazione**
  
$$

x^3-2x^2+x=x(x^2-2x+1)=x(x-1)^2

$$

---
## **2.3 Step 3: decomposizione corretta**

Abbiamo i fattori: $x$ e $(x-1)^2$.

Quindi:

- per $x$ metti $\frac{A}{x}$
    
- per $(x-1)^2$ metti **due** termini:

$$

\frac{B}{x-1}+\frac{C}{(x-1)^2}

$$
Totale:
$$

\frac{x+1}{x(x-1)^2}=\frac{A}{x}+\frac{B}{x-1}+\frac{C}{(x-1)^2}

$$

Denominatore comune $x(x-1)^2$:
$$

x+1=A(x-1)^2+Bx(x-1)+Cx

$$

Sviluppando e raccogliendo (come nella lezione) si ottiene:

$$

x+1=(A+B)x^2+(C-2A-B)x+A

$$

Confronto coefficienti con $x+1=0\cdot x^2+1\cdot x+1$:

$$

\begin{cases}

A+B=0\

C-2A-B=1\

A=1

\end{cases}

$$

Da $A=1$:
$$

B=-1

$$

Poi nella seconda:
$$

C-2(1)-(-1)=1 ;\Rightarrow; C-2+1=1 ;\Rightarrow; C=2

$$

Quindi:  
$$

\frac{x+1}{x(x-1)^2}=\frac{1}{x}-\frac{1}{x-1}+\frac{2}{(x-1)^2}

$$

---
## **2.4 Step 4: integrazione**

$$

\int\left(\frac{1}{x}-\frac{1}{x-1}+\frac{2}{(x-1)^2}\right),dx

$$
Primi due:
$$

\int\frac{1}{x},dx=\ln|x|

$$

$$

\int\frac{1}{x-1},dx=\ln|x-1|

$$
Terzo:

$$
\int\frac{2}{(x-1)^2},dx =

2\int (x-1)^{-2},dx =

2\cdot\frac{(x-1)^{-1}}{-1} =

-\frac{2}{x-1}
$$

Risultato finale:
$$

\ln|x|-\ln|x-1|-\frac{2}{x-1}+C

$$
Compattando i logaritmi:
$$

\ln\left|\frac{x}{x-1}\right|-\frac{2}{x-1}+C

$$

---
# **3. Regole operative da ricordare**

### **3.1 Se compare un quadratico irriducibile**

Se nel denominatore hai $(ax^2+bx+c)$ con $\Delta<0$, allora nel fratto semplice metti:

$$

\frac{Ax+B}{ax^2+bx+c}

$$

non una costante.

---
### **3.2 Se compare una potenza $(x-a)^n$**

Devi scrivere:
$$

\frac{A_1}{x-a}+\frac{A_2}{(x-a)^2}+\dots+\frac{A_n}{(x-a)^n}

$$

---
## **4. Mini-check mentale per non sbagliare lo step 3**

Quando decomponi, chiediti:

**“Il denominatore e un fattore lineare semplice?”**
→ numeratore: costante.

**“E un fattore lineare ripetuto?”**
→ piu frazioni, una per ogni potenza.

**“E un fattore quadratico irriducibile?”**
→ numeratore: polinomio di primo grado.

---
