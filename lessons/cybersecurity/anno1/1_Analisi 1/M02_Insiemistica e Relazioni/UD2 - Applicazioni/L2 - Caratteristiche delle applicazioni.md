# **M2 UD2 Lezione 2 - Caratteristiche delle applicazioni**

### **1. Introduzione**

Le applicazioni possono essere classificate in base a come collegano dominio e codominio. Le tre proprietà fondamentali sono:

- iniettività;
- suriettività;
- biiettività.

Queste proprietà stabiliscono se gli elementi del codominio vengono raggiunti, se vengono raggiunti una sola volta, e se la funzione è invertibile.

---

### **2. Funzione iniettiva**

Una funzione:

$$
f:A\to B
$$

è **iniettiva** se elementi distinti del dominio hanno immagini distinte.

Formalmente:

$$
\forall x_1,x_2\in A,\ f(x_1)=f(x_2)\Rightarrow x_1=x_2
$$

Equivalentemente:

$$
x_1\neq x_2\Rightarrow f(x_1)\neq f(x_2)
$$

> 📌 Una funzione iniettiva non “collassa” due input diversi nello stesso output.

---

### **3. Esempio di iniettività**

Consideriamo:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=2x+1
$$

Supponiamo:

$$
f(x_1)=f(x_2)
$$

Allora:

$$
2x_1+1=2x_2+1
$$

quindi:

$$
2x_1=2x_2
$$

e:

$$
x_1=x_2
$$

Dunque $f$ è iniettiva.

---

### **4. Funzione non iniettiva**

La funzione:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

non è iniettiva, perché:

$$
f(1)=1
$$

e:

$$
f(-1)=1
$$

ma:

$$
1\neq -1
$$

Se però restringiamo il dominio a $[0,+\infty)$, la stessa legge diventa iniettiva.

---

### **5. Funzione suriettiva**

Una funzione:

$$
f:A\to B
$$

è **suriettiva** se ogni elemento del codominio è immagine di almeno un elemento del dominio.

Formalmente:

$$
\forall y\in B,\ \exists x\in A: f(x)=y
$$

Equivalentemente:

$$
f(A)=B
$$

> 📌 Una funzione suriettiva raggiunge tutto il codominio dichiarato.

---

### **6. Esempio di suriettività**

Consideriamo:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^3
$$

Dato qualunque $y\in\mathbb{R}$, scegliamo:

$$
x=\sqrt[3]{y}
$$

Allora:

$$
f(x)=x^3=y
$$

Quindi $f$ è suriettiva.

---

### **7. Funzione non suriettiva**

La funzione:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=x^2
$$

non è suriettiva, perché nessun numero reale $x$ soddisfa:

$$
x^2=-1
$$

Quindi $-1$ appartiene al codominio, ma non è raggiunto dalla funzione.

Se invece consideriamo:

$$
f:\mathbb{R}\to[0,+\infty),\quad f(x)=x^2
$$

allora $f$ diventa suriettiva.

---

### **8. Funzione biiettiva**

Una funzione è **biiettiva** se è sia iniettiva sia suriettiva.

Quindi:

- ogni elemento del codominio viene raggiunto;
- ogni elemento del codominio viene raggiunto una sola volta.

Formalmente, per ogni $y\in B$ esiste un unico $x\in A$ tale che:

$$
f(x)=y
$$

La biiettività è la proprietà che permette di definire una funzione inversa.

---

### **9. Esempio di biiettività**

La funzione:

$$
f:\mathbb{R}\to\mathbb{R},\quad f(x)=2x+1
$$

è iniettiva, come visto prima.

È anche suriettiva: dato $y\in\mathbb{R}$, dobbiamo risolvere:

$$
2x+1=y
$$

Otteniamo:

$$
x=\frac{y-1}{2}
$$

che è un numero reale. Dunque ogni $y$ viene raggiunto.

Quindi $f$ è biiettiva.

---

### **10. Ruolo di dominio e codominio**

La legge $f(x)=x^2$ cambia proprietà a seconda di dominio e codominio:

$$
f:\mathbb{R}\to\mathbb{R}
$$

non è iniettiva e non è suriettiva.

$$
f:[0,+\infty)\to\mathbb{R}
$$

è iniettiva ma non suriettiva.

$$
f:\mathbb{R}\to[0,+\infty)
$$

è suriettiva ma non iniettiva.

$$
f:[0,+\infty)\to[0,+\infty)
$$

è biiettiva.

> ⚠️ Iniettività e suriettività non dipendono solo dalla formula, ma anche da dominio e codominio.

---

### **11. Riepilogo**

> ✅ Le proprietà di una applicazione descrivono come dominio e codominio vengono collegati.

Punti essenziali:

- iniettiva: input diversi producono output diversi;
- suriettiva: ogni elemento del codominio è raggiunto;
- biiettiva: ogni elemento del codominio è raggiunto una e una sola volta;
- una funzione biiettiva ammette inversa;
- dominio e codominio sono parte essenziale del problema.
