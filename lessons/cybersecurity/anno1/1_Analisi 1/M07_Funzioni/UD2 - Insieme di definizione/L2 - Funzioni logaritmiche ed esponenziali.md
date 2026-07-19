# **M7 UD2 Lezione 2 - Funzioni logaritmiche ed esponenziali**

### **1. Introduzione**

Le funzioni logaritmiche ed esponenziali hanno regole di dominio molto diverse.

L’esponenziale:

$$
a^{g(x)}
$$

con $a>0$ è definito ogni volta che l’esponente $g(x)$ è definito. La condizione $a\neq1$ serve quando si intende la funzione esponenziale non costante e quando si definisce il logaritmo inverso.

Il logaritmo:

$$
\log_a(g(x))
$$

richiede invece:

$$
g(x)>0
$$

> 📌 Il logaritmo impone sempre positività dell’argomento.

---

### **2. Funzioni esponenziali**

La funzione:

$$
f(x)=a^x
$$

con:

$$
a>0,\quad a\neq 1
$$

ha dominio:

$$
\mathbb{R}
$$

e immagine:

$$
(0,+\infty)
$$

Per $a=1$ l'espressione $1^x$ è comunque definita su $\mathbb{R}$, ma produce la funzione costante $1$ e non ammette logaritmo inverso.

Se l’esponente è una funzione $g(x)$, il dominio è quello di $g$.

Esempio:

$$
f(x)=2^{\sqrt{x}}
$$

richiede:

$$
x\ge 0
$$

perché l’esponente $\sqrt{x}$ deve essere definito.

---

### **3. Funzioni logaritmiche**

La funzione:

$$
f(x)=\log_a x
$$

con:

$$
a>0,\quad a\neq 1
$$

ha dominio:

$$
(0,+\infty)
$$

Se l’argomento è $g(x)$:

$$
f(x)=\log_a(g(x))
$$

serve:

$$
g(x)>0
$$

oltre alle eventuali condizioni necessarie per definire $g(x)$.

---

### **4. Esempio logaritmico**

Consideriamo:

$$
f(x)=\log(x^2-4)
$$

Serve:

$$
x^2-4>0
$$

Fattorizziamo:

$$
(x-2)(x+2)>0
$$

Il prodotto è positivo fuori dalle radici:

$$
x<-2\quad \text{oppure}\quad x>2
$$

Quindi:

$$
D_f=(-\infty,-2)\cup(2,+\infty)
$$

---

### **5. Logaritmo di una frazione**

Consideriamo:

$$
f(x)=\log\left(\frac{x+1}{x-3}\right)
$$

Serve:

$$
\frac{x+1}{x-3}>0
$$

con:

$$
x\neq 3
$$

Studiamo il segno della frazione. I punti critici sono:

$$
x=-1,\quad x=3
$$

La frazione è positiva per:

$$
x<-1
$$

oppure:

$$
x>3
$$

Quindi:

$$
D_f=(-\infty,-1)\cup(3,+\infty)
$$

---

### **6. Composizioni esponenziale-logaritmo**

La funzione:

$$
f(x)=e^{\log(x-1)}
$$

richiede:

$$
x-1>0
$$

quindi:

$$
D_f=(1,+\infty)
$$

Anche se formalmente:

$$
e^{\log(x-1)}=x-1
$$

il dominio resta quello dell’espressione originale.

---

### **7. Basi variabili**

Se compare:

$$
\log_{b(x)}(g(x))
$$

bisogna imporre:

$$
b(x)>0
$$

$$
b(x)\neq 1
$$

e:

$$
g(x)>0
$$

Questi casi sono più delicati, perché anche la base dipende da $x$.

Per una potenza con base ed esponente variabili,

$$
f(x)=u(x)^{v(x)},
$$

la convenzione elementare generale usa

$$
f(x)=e^{v(x)\ln u(x)}
$$

e richiede $u(x)>0$ oltre al dominio di $v$. Per esempio,

$$
x^x
$$

ha dominio naturale $(0,+\infty)$ in questa interpretazione globale, anche se singoli valori negativi possono ammettere potenze razionali reali.

### **8. Funzioni iperboliche e inverse**

Le funzioni $\sinh x$, $\cosh x$ e $\tanh x$ sono definite su $\mathbb{R}$. Le inverse principali hanno domini uguali alle immagini dei rami invertiti:

$$
D_{\operatorname{arsinh}}=\mathbb{R},
$$

$$
D_{\operatorname{arcosh}}=[1,+\infty),
$$

$$
D_{\operatorname{artanh}}=(-1,1).
$$

Quindi

$$
\operatorname{arcosh}(2x-1)
$$

richiede $2x-1\ge1$, ossia $x\ge1$, mentre

$$
\operatorname{artanh}(x^2-2)
$$

richiede

$$
-1<x^2-2<1,
$$

da cui

$$
1<x^2<3
$$

e dunque

$$
D=(-\sqrt3,-1)\cup(1,\sqrt3).
$$

Le immagini fondamentali sono

$$
\operatorname{Im}(\sinh)=\mathbb{R},
$$

$$
\operatorname{Im}(\cosh)=[1,+\infty),
$$

$$
\operatorname{Im}(\tanh)=(-1,1).
$$

Per $t=e^x>0$ si ha

$$
\sinh x=\frac{t-t^{-1}}{2},
\qquad
\cosh x=\frac{t+t^{-1}}{2},
\qquad
\tanh x=\frac{t^2-1}{t^2+1}.
$$

Da $t+t^{-1}\ge2$ segue $\cosh x\ge1$; inoltre $|t^2-1|<t^2+1$ implica $|\tanh x|<1$. Le formule delle inverse mostrano che tutti i valori dichiarati nelle tre immagini sono effettivamente raggiunti.

### **9. Immagini e monotonia algebrica**

Per $a>1$, $a^x$ è strettamente crescente e ha immagine $(0,+\infty)$; per $0<a<1$ è strettamente decrescente con la stessa immagine. Il logaritmo $\log_a x$ è l'inversa e ha immagine $\mathbb{R}$.

Queste proprietà sono state motivate algebricamente in M01; le prove analitiche complete tramite continuità saranno coordinate con M08. Per una trasformazione

$$
g(x)=A a^{B(x-h)}+k,
$$

l'immagine si ricava da $a^t>0$: se $A>0$,

$$
\operatorname{Im}(g)=(k,+\infty),
$$

mentre se $A<0$,

$$
\operatorname{Im}(g)=(-\infty,k).
$$

### **10. Esercizi**

1. Determina il dominio di $\ln(\sqrt{x-1}/(x+2))$.
2. Determina il dominio di $\log_{x-1}(4-x^2)$.
3. Studia $D$ e immagine di $3-2e^{x+1}$.
4. Determina il dominio di $\ln(\ln(x-1))$.
5. Determina il dominio di $\operatorname{arcosh}(x^2-1)$.
6. Risolvi il sistema di condizioni per $\operatorname{artanh}((x-1)/(x+1))$.
7. Spiega perché $\ln(e^x)=x$ vale su tutto $\mathbb{R}$, mentre $e^{\ln x}=x$ conserva il dominio $x>0$.

Le identità algebriche e le tecniche risolutive di base sono sviluppate in [M01 UD5 L1](../../M01_Matematica Liceale/UD5 - Logaritmi ed Esponenziali/L1 - Definizioni e proprietà dei logaritmi.md) e nelle lezioni adiacenti; qui vengono organizzate come proprietà di funzioni.

---

### **11. Riepilogo**

> ✅ Gli esponenziali richiedono che l’esponente sia definito; i logaritmi richiedono argomento positivo.

Punti essenziali:

- $a^x$ ha dominio $\mathbb{R}$ per $a>0$, $a\neq 1$;
- $a^{g(x)}$ ha il dominio di $g$;
- $\log_a(g(x))$ richiede $g(x)>0$;
- le semplificazioni non eliminano i vincoli originali;
- se la base del logaritmo varia, servono base positiva, diversa da $1$, e argomento positivo.
- le inverse iperboliche impongono i vincoli derivati dalle immagini dei rami principali.
