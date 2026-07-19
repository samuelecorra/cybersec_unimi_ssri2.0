# **M2 UD2 Lezione 4 - Composizione**

### **1. Introduzione**

La composizione di funzioni descrive l’operazione “applico prima una funzione e poi un’altra”.

Se:

$$
f:A\to B
$$

e:

$$
g:B\to C
$$

allora possiamo costruire:

$$
g\circ f:A\to C
$$

definita da:

$$
(g\circ f)(x)=g(f(x))
$$

> 📌 Nella composizione $g\circ f$ si applica prima $f$ e poi $g$.

---

### **2. Condizione di compatibilità**

La composizione:

$$
g\circ f
$$

ha senso se i valori prodotti da $f$ sono ammessi come input di $g$.

Formalmente, se:

$$
f:A\to B
$$

e:

$$
g:B\to C
$$

allora la composizione è automaticamente definita.

Più in generale serve:

$$
f(A)\subseteq D_g
$$

dove $D_g$ è il dominio di $g$.

---

### **3. Esempio semplice**

Siano:

$$
f(x)=x^2
$$

e:

$$
g(x)=x+1
$$

Allora:

$$
(g\circ f)(x)=g(f(x))=g(x^2)=x^2+1
$$

Invece:

$$
(f\circ g)(x)=f(g(x))=f(x+1)=(x+1)^2
$$

Quindi:

$$
g\circ f\neq f\circ g
$$

in generale.

---

### **4. La composizione non è commutativa**

L’esempio precedente mostra che l’ordine conta.

Infatti:

$$
x^2+1
$$

non coincide con:

$$
(x+1)^2=x^2+2x+1
$$

La composizione è una operazione ordinata: cambiare l’ordine cambia il risultato.

> ⚠️ In $g\circ f$ la funzione più vicina alla variabile è quella applicata per prima.

---

### **5. Dominio della funzione composta**

Se le funzioni sono date da formule reali, bisogna controllare il dominio.

Esempio:

$$
f(x)=x-1
$$

e:

$$
g(x)=\sqrt{x}
$$

Allora:

$$
(g\circ f)(x)=\sqrt{x-1}
$$

Il dominio è:

$$
x-1\ge 0
$$

cioè:

$$
[1,+\infty)
$$

Invece:

$$
(f\circ g)(x)=\sqrt{x}-1
$$

ha dominio:

$$
[0,+\infty)
$$

---

### **6. Associatività**

La composizione è associativa quando tutte le composizioni hanno senso:

$$
h\circ(g\circ f)=(h\circ g)\circ f
$$

Entrambe danno:

$$
h(g(f(x)))
$$

Questo permette di scrivere senza ambiguità:

$$
h\circ g\circ f
$$

ricordando che si applica prima $f$, poi $g$, poi $h$.

---

### **7. Identità**

Su un insieme $A$ si definisce la funzione identità:

$$
\operatorname{id}_A:A\to A
$$

data da:

$$
\operatorname{id}_A(x)=x
$$

Per ogni funzione $f:A\to B$ valgono:

$$
f\circ\operatorname{id}_A=f
$$

e:

$$
\operatorname{id}_B\circ f=f
$$

L’identità è la funzione che non modifica l’elemento.

---

### **8. Inversa e composizione**

Se $f:A\to B$ è biiettiva, allora:

$$
f^{-1}\circ f=\operatorname{id}_A
$$

e:

$$
f\circ f^{-1}=\operatorname{id}_B
$$

Questa è la forma astratta delle identità:

$$
f^{-1}(f(x))=x
$$

e:

$$
f(f^{-1}(y))=y
$$

---

### **9. Esempio con tre funzioni**

Siano:

$$
f(x)=x+2,\quad g(x)=x^2,\quad h(x)=\sqrt{x}
$$

Calcoliamo:

$$
(h\circ g\circ f)(x)
$$

Prima:

$$
f(x)=x+2
$$

poi:

$$
g(f(x))=(x+2)^2
$$

infine:

$$
h(g(f(x)))=\sqrt{(x+2)^2}=|x+2|
$$

Il valore assoluto compare perché:

$$
\sqrt{u^2}=|u|
$$

---

### **10. Esercizi rappresentativi**

1. Siano:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2-1
$$

e:

$$
g:[-1,+\infty)\to[0,+\infty),\quad g(x)=\sqrt{x+1}
$$

Stabilire il dominio naturale di $g\circ f$ come funzione reale.

Serve:

$$
f(x)\in[-1,+\infty)
$$

cioè:

$$
x^2-1\ge -1
$$

che vale per ogni $x\in\mathbb{R}$. Quindi:

$$
D_{g\circ f}=\mathbb{R}
$$

e:

$$
(g\circ f)(x)=\sqrt{x^2}=|x|
$$

2. Trovare un esempio in cui $f(E\cap F)\subsetneq f(E)\cap f(F)$.

Con $f(x)=x^2$, $E=(-\infty,0)$ e $F=(0,+\infty)$:

$$
f(E\cap F)=\varnothing
$$

ma:

$$
f(E)\cap f(F)=(0,+\infty)
$$

3. Verificare se $f:\mathbb{R}\to[0,+\infty)$, $f(x)=x^2$, ammette inversa destra.

Sì: per esempio:

$$
h:[0,+\infty)\to\mathbb{R},\quad h(y)=\sqrt{y}
$$

soddisfa:

$$
f(h(y))=y
$$

per ogni $y\ge 0$. Non è però una inversa bilatera, perché $f$ non è iniettiva.

---

### **11. Riepilogo**

> ✅ La composizione combina funzioni applicandole in sequenza.

Punti essenziali:

- $(g\circ f)(x)=g(f(x))$;
- in $g\circ f$ si applica prima $f$;
- la composizione non è commutativa;
- bisogna controllare il dominio della composta;
- la composizione è associativa;
- l’identità è l’elemento neutro;
- l’inversa annulla la funzione tramite composizione.
