# **M7 UD2 Lezione 4 - Funzioni con modulo**

### **1. Introduzione**

Il valore assoluto modifica il comportamento di una funzione perché rende non negative le quantità a cui viene applicato.

La definizione fondamentale è:

$$
|x|=
\begin{cases}
x & \text{se } x\ge 0 \\
-x & \text{se } x<0
\end{cases}
$$

> 📌 Il modulo si studia distinguendo i casi in cui l’argomento è positivo o negativo.

---

### **2. Dominio del valore assoluto**

La funzione:

$$
f(x)=|x|
$$

è definita per ogni numero reale:

$$
D_f=\mathbb{R}
$$

Più in generale:

$$
|g(x)|
$$

è definito ogni volta che $g(x)$ è definita.

Il modulo non introduce da solo restrizioni di dominio.

---

### **3. Esempio di dominio**

Consideriamo:

$$
f(x)=|\sqrt{x-1}|
$$

Il modulo non crea vincoli nuovi. Il vincolo viene dalla radice:

$$
x-1\ge 0
$$

quindi:

$$
D_f=[1,+\infty)
$$

---

### **4. Funzioni definite a tratti**

Per studiare una funzione con modulo, si trova dove l’argomento del modulo cambia segno.

Esempio:

$$
f(x)=|x-2|
$$

L’argomento cambia segno in:

$$
x=2
$$

Quindi:

$$
|x-2|=
\begin{cases}
x-2 & \text{se } x\ge 2 \\
2-x & \text{se } x<2
\end{cases}
$$

---

### **5. Modulo di una funzione**

Se:

$$
f(x)=|g(x)|
$$

allora:

$$
|g(x)|=
\begin{cases}
g(x) & \text{se } g(x)\ge 0 \\
-g(x) & \text{se } g(x)<0
\end{cases}
$$

Quindi bisogna studiare il segno di $g(x)$.

---

### **6. Esempio con polinomio**

Consideriamo:

$$
f(x)=|x^2-4|
$$

Studiamo il segno:

$$
x^2-4=(x-2)(x+2)
$$

Il polinomio è positivo per:

$$
x\le -2\quad \text{oppure}\quad x\ge 2
$$

ed è negativo per:

$$
-2<x<2
$$

Quindi:

$$
|x^2-4|=
\begin{cases}
x^2-4 & \text{se } x\le -2 \text{ oppure } x\ge 2 \\
4-x^2 & \text{se } -2<x<2
\end{cases}
$$

---

### **7. Equazioni con modulo**

L’equazione:

$$
|g(x)|=a
$$

ha soluzioni solo se:

$$
a\ge 0
$$

Se $a\ge 0$, equivale a:

$$
g(x)=a
$$

oppure:

$$
g(x)=-a
$$

Esempio:

$$
|x-3|=2
$$

dà:

$$
x-3=2
$$

oppure:

$$
x-3=-2
$$

quindi:

$$
x=5\quad \text{oppure}\quad x=1
$$

---

### **8. Disequazioni con modulo**

Per $a>0$:

$$
|g(x)|<a
$$

equivale a:

$$
-a<g(x)<a
$$

Invece:

$$
|g(x)|>a
$$

equivale a:

$$
g(x)<-a\quad \text{oppure}\quad g(x)>a
$$

Queste regole derivano dall’interpretazione del modulo come distanza da $0$.

Per completare i casi degeneri:

- se $a<0$, $|g(x)|<a$ non ha soluzioni e $|g(x)|>a$ vale su tutto il dominio di $g$;
- se $a=0$, $|g(x)|<0$ non ha soluzioni, mentre $|g(x)|>0$ equivale a $g(x)\neq0$;
- $|g(x)|\le0$ equivale a $g(x)=0$.

### **9. Modulo esterno e interno**

La funzione $|g(x)|$ riflette sopra l'asse $x$ i valori negativi di $g$. La funzione $g(|x|)$ usa invece solo ingressi non negativi e rende il risultato pari. Per $g(x)=x-2$:

$$
|g(x)|=|x-2|,
$$

$$
g(|x|)=|x|-2.
$$

I rispettivi insiemi degli zeri sono $\{2\}$ e $\{-2,2\}$.

### **10. Esercizi**

1. Scrivi a tratti $|2x-3|$ e $|x^2-1|$.
2. Determina dominio e immagine di $1/(1+|x|)$.
3. Risolvi $|x-1|\le|x+2|$ interpretando i moduli come distanze.
4. Confronta zeri, parità e immagine di $|x^2-4x|$ e $(|x|)^2-4|x|$.
5. Determina il dominio di $\sqrt{|x|-2}/(|x|-3)$.
6. Studia al variare di $a$ l'equazione $|x-1|=a$.
7. Disegna qualitativamente $|\sin x|$ e determina un periodo fondamentale.

---

### **11. Riepilogo**

> ✅ Il modulo non restringe il dominio da solo, ma obbliga a studiare il segno del suo argomento.

Punti essenziali:

- $|g(x)|$ è definito quando $g(x)$ è definita;
- il modulo si elimina distinguendo $g(x)\ge 0$ e $g(x)<0$;
- $|g(x)|=a$ richiede $a\ge 0$;
- $|g(x)|<a$ significa $-a<g(x)<a$;
- $|g(x)|>a$ produce una unione di due casi.
