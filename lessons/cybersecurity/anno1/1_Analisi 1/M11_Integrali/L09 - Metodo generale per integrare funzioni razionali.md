## **Lezione 9: Metodo generale per integrare funzioni razionali**

---
### **1. Funzione razionale**

Una funzione razionale e una funzione del tipo:
$$

\frac{P(x)}{Q(x)}

$$

dove $P(x)$ e $Q(x)$ sono polinomi.

L’idea della lezione: esiste una procedura “standard” (quasi sempre) in **4 passaggi**.

---
## **2. Procedura in 4 passaggi**

### **Passaggio 1: divisione tra polinomi (solo se serve)**

Lo fai **solo se**:
$$

\deg(P)\ge \deg(Q)

$$
Cioe se il numeratore ha grado maggiore o uguale al denominatore.

Allora:
$$

\frac{P(x)}{Q(x)}=S(x)+\frac{R(x)}{Q(x)}

$$
dove:

- $S(x)$ e il quoziente (polinomio)
    
- $R(x)$ e il resto (con grado **minore** di $Q$)    

---
### **Passaggio 2: fattorizza il denominatore**

Prendi la frazione rimasta:
$$

\frac{R(x)}{Q(x)}

$$
e riscrivi $Q(x)$ come prodotto di fattori “semplici”:

- fattori di **primo grado**: $(x-a)$
    
- fattori di **secondo grado irriducibili**: $(ax^2+bx+c)$ con $\Delta<0$
    
- eventualmente potenze: $(x-a)^2, (x-a)^3, \dots$

---
### **Passaggio 3: decomposizione in fratti semplici**

Scrivi la frazione come **somma** di frazioni piu semplici.  

Nel caso piu base:
$$

\frac{1}{(x-a)(x-b)}=\frac{A}{x-a}+\frac{B}{x-b}

$$
Poi:

- fai denominatore comune
    
- confronti i numeratori
    
- risolvi un **sistema lineare** per trovare $A,B,\dots$

---
### **Passaggio 4: integra pezzo per pezzo**

Dopo la decomposizione, integri ogni termine:

- polinomi → immediate
    
- $\frac{1}{x-a}$ → logaritmo
    
- (nel prossimo video: potenze, quadratici irriducibili, ecc.)

---
# **3. Esempio 1 completo**

Calcolare:
$$

\int \frac{x^3-3x-1}{x^2-x-2},dx

$$

---
## **3.1 Passaggio 1: divisione**

Qui:

$$

\deg(\text{numeratore})=3,\quad \deg(\text{denominatore})=2

$$

quindi **si divide**.

Risultato della divisione (come nella lezione):  

$$

\frac{x^3-3x-1}{x^2-x-2}=x+1+\frac{1}{x^2-x-2}

$$

---
## **3.2 Passaggio 2: fattorizza il denominatore**

$$

x^2-x-2=(x-2)(x+1)

$$
Quindi:
$$

\frac{1}{x^2-x-2}=\frac{1}{(x-2)(x+1)}

$$

---
## **3.3 Passaggio 3: fratti semplici**

Cerco:
$$

\frac{1}{(x-2)(x+1)}=\frac{A}{x-2}+\frac{B}{x+1}

$$
Denominatore comune:
$$

1=A(x+1)+B(x-2)

$$
Raggruppo:

$$

1=(A+B)x+(A-2B)

$$
Quindi sistema:

$$

\begin{cases}

A+B=0\

A-2B=1

\end{cases}

$$

Soluzione:
$$

B=-\frac13,\qquad A=\frac13

$$
Quindi:
$$

\frac{1}{(x-2)(x+1)}=\frac{1}{3}\frac{1}{x-2}-\frac{1}{3}\frac{1}{x+1}

$$

---
## **3.4 Passaggio 4: integra**

Ora:

$$
\int \frac{x^3-3x-1}{x^2-x-2},dx =

\int\left(x+1+\frac{1}{3}\frac{1}{x-2}-\frac{1}{3}\frac{1}{x+1}\right),dx
$$

Integrando:
$$

\frac{x^2}{2}+x+\frac13\ln|x-2|-\frac13\ln|x+1|+C

$$  

Se vuoi compattare i logaritmi:
$$

\frac{x^2}{2}+x+\frac13\ln\left|\frac{x-2}{x+1}\right|+C

$$

---
# **4. Esempio 2 completo**

Calcolare:
$$

\int \frac{1}{x^2-1},dx

$$

---
## **4.1 Passaggio 1: non serve**

Perche:
$$

\deg(\text{numeratore})=0<2=\deg(\text{denominatore})

$$

---
## **4.2 Passaggio 2: fattorizza**

Differenza di quadrati:
$$

x^2-1=(x-1)(x+1)

$$

---
## **4.3 Passaggio 3: fratti semplici**

Cerco:
$$

\frac{1}{(x-1)(x+1)}=\frac{A}{x+1}+\frac{B}{x-1}

$$
Denominatore comune:

$$

1=A(x-1)+B(x+1)

$$
Raggruppo:

$$

1=(A+B)x+(B-A)

$$
Sistema:
$$

\begin{cases}

A+B=0\

B-A=1

\end{cases}

$$
Soluzione:
$$

A=-\frac12,\qquad B=\frac12

$$
Quindi:
$$

\frac{1}{x^2-1}=-\frac12\frac{1}{x+1}+\frac12\frac{1}{x-1}

$$

---
## **4.4 Passaggio 4: integra**

$$
\int \frac{1}{x^2-1},dx =

-\frac12\int \frac{1}{x+1},dx+\frac12\int \frac{1}{x-1},dx
$$

Risultato:
$$

-\frac12\ln|x+1|+\frac12\ln|x-1|+C

$$
Compattando:
$$

\frac12\ln\left|\frac{x-1}{x+1}\right|+C

$$

---
## **5. Cosa arriva nella prossima lezione**

Qui hai visto i casi “puliti” con fattori lineari semplici.  

Prossimamente:

- fattori ripetuti: $(x-a)^2, (x-a)^3$
    
- fattori quadratici irriducibili: $ax^2+bx+c$ con $\Delta<0$
    
- numeratori che richiedono forme tipo $Ax+B$
