# **M2 UD2 Lezione 3 - Applicazione inversa**

### **1. Introduzione**

Invertire una funzione significa scambiare il ruolo di input e output. Se:

$$
f(x)=y
$$

l’inversa dovrebbe permettere di recuperare:

$$
x
$$

a partire da $y$.

Questo è possibile in modo funzionale solo quando ogni valore del codominio è raggiunto da uno e un solo elemento del dominio.

> 📌 Una funzione ammette inversa se e solo se è biiettiva.

---

### **2. Definizione di inversa**

Sia:

$$
f:A\to B
$$

una funzione biiettiva.

L’applicazione inversa è:

$$
f^{-1}:B\to A
$$

definita dalla regola:

$$
f^{-1}(y)=x
$$

se e solo se:

$$
f(x)=y
$$

Il dominio di $f^{-1}$ è il codominio di $f$; il codominio di $f^{-1}$ è il dominio di $f$.

---

### **3. Composizioni con l’inversa**

Se $f:A\to B$ è biiettiva, allora:

$$
f^{-1}(f(x))=x
$$

per ogni $x\in A$, e:

$$
f(f^{-1}(y))=y
$$

per ogni $y\in B$.

Queste identità esprimono il fatto che applicare una funzione e poi la sua inversa riporta al punto di partenza.

---

### **4. Esempio lineare**

Consideriamo:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=2x+3
$$

Per trovare l’inversa poniamo:

$$
y=2x+3
$$

Risolviamo rispetto a $x$:

$$
y-3=2x
$$

quindi:

$$
x=\frac{y-3}{2}
$$

Allora:

$$
f^{-1}(y)=\frac{y-3}{2}
$$

Rinominando la variabile:

$$
f^{-1}(x)=\frac{x-3}{2}
$$

---

### **5. Verifica dell’inversa**

Verifichiamo:

$$
f^{-1}(f(x))=\frac{(2x+3)-3}{2}=x
$$

e:

$$
f(f^{-1}(x))=2\cdot\frac{x-3}{2}+3=x
$$

Quindi la formula trovata è corretta.

---

### **6. Quando l’inversa non esiste**

La funzione:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

non ha inversa su tutto $\mathbb{R}$, perché non è iniettiva:

$$
f(2)=4
$$

e:

$$
f(-2)=4
$$

Se provassimo a invertire, al valore $4$ dovremmo associare sia $2$ sia $-2$, ma una funzione non può associare due immagini allo stesso input.

---

### **7. Restringere il dominio**

La stessa legge:

$$
f(x)=x^2
$$

diventa invertibile se scegliamo:

$$
f:[0,+\infty)\to[0,+\infty)
$$

Allora:

$$
f^{-1}(x)=\sqrt{x}
$$

Infatti, il dominio ristretto elimina il ramo negativo.

> 💡 Le inverse goniometriche nascono esattamente da questa idea: si restringe il dominio a un ramo monotono.

---

### **8. Grafico dell’inversa**

Il grafico di $f^{-1}$ si ottiene riflettendo il grafico di $f$ rispetto alla retta:

$$
y=x
$$

Questo avviene perché le coppie:

$$
(x,y)
$$

di $f$ diventano:

$$
(y,x)
$$

per $f^{-1}$.

<!-- TODO FIGURA: grafici di una funzione biiettiva e della sua inversa riflessi rispetto alla retta y=x; obiettivo didattico: mostrare lo scambio delle coppie (x,y) e (y,x) senza confondere inversa con reciproco. -->

---

### **9. Notazione da non confondere**

La scrittura:

$$
f^{-1}
$$

indica la funzione inversa, non il reciproco della funzione.

In generale:

$$
f^{-1}(x)\neq \frac{1}{f(x)}
$$

Per esempio, se:

$$
f(x)=2x+3
$$

allora:

$$
f^{-1}(x)=\frac{x-3}{2}
$$

mentre:

$$
\frac{1}{f(x)}=\frac{1}{2x+3}
$$

sono due espressioni completamente diverse.

---

### **10. Perché invertibile significa biiettiva**

Dimostriamo il criterio:

$$
f:A\to B \text{ è invertibile}\iff f \text{ è biiettiva}
$$

Se $f$ è invertibile, esiste $g:B\to A$ tale che:

$$
g\circ f=\operatorname{id}_A,\qquad f\circ g=\operatorname{id}_B
$$

La prima identità implica l’iniettività: se $f(x_1)=f(x_2)$, applicando $g$ otteniamo:

$$
g(f(x_1))=g(f(x_2))
$$

quindi:

$$
x_1=x_2
$$

La seconda identità implica la suriettività: per ogni $y\in B$, scegliendo $x=g(y)$ si ha:

$$
f(x)=f(g(y))=y
$$

Dunque $f$ è biiettiva.

Viceversa, se $f$ è biiettiva, per ogni $y\in B$ esiste un unico $x\in A$ tale che $f(x)=y$. Possiamo allora definire:

$$
f^{-1}(y)=x
$$

L’esistenza garantisce che la definizione copra tutto $B$; l’unicità garantisce che $f^{-1}$ sia una funzione.

---

### **11. Inverse sinistre e inverse destre**

Per una funzione $f:A\to B$, una funzione $g:B\to A$ è una **inversa sinistra** se:

$$
g\circ f=\operatorname{id}_A
$$

Questo basta a garantire che $f$ sia iniettiva.

Una funzione $h:B\to A$ è una **inversa destra** se:

$$
f\circ h=\operatorname{id}_B
$$

Questo basta a garantire che $f$ sia suriettiva.

Quando una stessa funzione è sia inversa sinistra sia inversa destra, allora è la vera funzione inversa e $f$ è biiettiva.

> 💡 Le inverse sinistre recuperano gli input; le inverse destre garantiscono che ogni output dichiarato venga raggiunto.

---

### **12. Riepilogo**

> ✅ L’applicazione inversa esiste quando una funzione è biiettiva e permette di recuperare l’input dall’output.

Punti essenziali:

- $f^{-1}:B\to A$ se $f:A\to B$;
- $f^{-1}(f(x))=x$;
- $f(f^{-1}(y))=y$;
- una funzione non iniettiva non può avere inversa;
- una inversa sinistra corrisponde all’iniettività, una inversa destra alla suriettività;
- spesso si restringe il dominio per ottenere una inversa;
- $f^{-1}$ non significa $\frac{1}{f}$.
