# **M4 UD2 Lezione 1 - Forma trigonometrica**

### **1. Coordinate polari nel piano complesso**

La forma algebrica $a+bi$ è naturale per somme e sottrazioni. Per prodotti, quozienti, potenze e radici conviene descrivere un complesso non nullo mediante:

- il modulo $\rho=|z|>0$;
- una direzione, rappresentata da un argomento $\theta$.

Se $z=a+bi\ne0$, allora

$$
\cos\theta=\frac a\rho,\qquad \sin\theta=\frac b\rho.
$$

Poiché

$$
\left(\frac a\rho\right)^2+\left(\frac b\rho\right)^2=1,
$$

esiste un angolo reale $\theta$ con tali seno e coseno.

---

### **2. Forma trigonometrica e sua unicità**

Da $a=\rho\cos\theta$ e $b=\rho\sin\theta$ segue

$$
z=\rho(\cos\theta+i\sin\theta).
$$

Questa è la **forma trigonometrica** o **polare**.

Il modulo $\rho$ è unico. L'argomento non lo è:

$$
\arg z=\{\theta+2k\pi:k\in\mathbb Z\}.
$$

Adottiamo

$$
\operatorname{Arg}(z)\in(-\pi,\pi].
$$

Pertanto $\operatorname{Arg}(-1)=\pi$, non $-\pi$.

Due forme polari con modulo positivo rappresentano lo stesso numero se e solo se

$$
\rho_1=\rho_2
$$

e

$$
\theta_1-\theta_2\in2\pi\mathbb Z.
$$

> ⚠️ Per $z=0$ il modulo è $0$, ma l'argomento non è definito. La scrittura $0(\cos\theta+i\sin\theta)$ vale algebricamente per ogni $\theta$, ma non assegna un argomento a $0$.

<!-- TODO FIGURA: coordinate polari di z con triangolo rettangolo, componenti rho cos theta e rho sin theta, più giri theta+2kpi; obiettivo didattico: derivare la forma trigonometrica e visualizzare la non unicità dell'argomento. -->

---

### **3. Determinazione dell'argomento**

Il rapporto $b/a$ determina la tangente solo quando $a\ne0$ e non identifica da solo il quadrante. Un metodo affidabile è:

1. collocare $(a,b)$ nel quadrante o su un asse;
2. trovare l'angolo di riferimento;
3. scegliere il rappresentante in $(-\pi,\pi]$.

Per $a\ne0$ si può usare $\arctan(b/a)$ correggendo il quadrante:

$$
\operatorname{Arg}(a+bi)=
\begin{cases}
\arctan(b/a), & a>0,\\
\arctan(b/a)+\pi, & a<0,\ b\ge0,\\
\arctan(b/a)-\pi, & a<0,\ b<0.
\end{cases}
$$

Sull'asse immaginario,

$$
\operatorname{Arg}(bi)=
\begin{cases}
\pi/2, & b>0,\\
-\pi/2, & b<0.
\end{cases}
$$

Nei linguaggi di programmazione questa gestione dei quadranti è svolta tipicamente dalla funzione `atan2(b,a)`.

<!-- TODO FIGURA: quattro quadranti con segni di a e b, angolo di riferimento e valori principali ottenuti dalle correzioni di arctan; obiettivo didattico: evitare l'errore di quadrante nel calcolo dell'Arg. -->

---

### **4. Conversioni svolte**

#### **4.1 Dal cartesiano al polare**

Per $z=-\sqrt3+i$,

$$
\rho=\sqrt{3+1}=2.
$$

Il punto è nel secondo quadrante e l'angolo di riferimento è $\pi/6$, dunque

$$
\operatorname{Arg}(z)=\frac{5\pi}{6}.
$$

Quindi

$$
z=2\left(\cos\frac{5\pi}{6}+i\sin\frac{5\pi}{6}\right).
$$

#### **4.2 Dal polare al cartesiano**

Per

$$
w=4\left(\cos\frac{7\pi}{6}+i\sin\frac{7\pi}{6}\right),
$$

si ottiene

$$
w=4\left(-\frac{\sqrt3}{2}-\frac12i\right)=-2\sqrt3-2i.
$$

L'argomento principale è $-5\pi/6$, equivalente a $7\pi/6$ modulo $2\pi$.

---

### **5. Prodotto in forma trigonometrica**

Siano

$$
z_1=\rho_1(\cos\theta_1+i\sin\theta_1),
$$

$$
z_2=\rho_2(\cos\theta_2+i\sin\theta_2).
$$

Moltiplicando,

$$
z_1z_2=\rho_1\rho_2
\bigl[(\cos\theta_1\cos\theta_2-\sin\theta_1\sin\theta_2)
+i(\sin\theta_1\cos\theta_2+\cos\theta_1\sin\theta_2)\bigr].
$$

Le formule di addizione danno

$$
z_1z_2=\rho_1\rho_2
[\cos(\theta_1+\theta_2)+i\sin(\theta_1+\theta_2)].
$$

Pertanto

$$
|z_1z_2|=|z_1|\,|z_2|
$$

e, come congruenza di angoli,

$$
\arg(z_1z_2)\equiv\arg z_1+\arg z_2\pmod{2\pi}.
$$

Non sempre vale l'uguaglianza letterale tra argomenti principali. Per esempio,

$$
\operatorname{Arg}((-1)(-1))=\operatorname{Arg}(1)=0,
$$

mentre

$$
\operatorname{Arg}(-1)+\operatorname{Arg}(-1)=2\pi.
$$

---

### **6. Inverso e quoziente**

Se $z=\rho(\cos\theta+i\sin\theta)\ne0$, allora

$$
z^{-1}=\frac1\rho[\cos(-\theta)+i\sin(-\theta)].
$$

Infatti il prodotto ha modulo $1$ e argomento $0$ modulo $2\pi$.

Per $z_2\ne0$,

$$
\frac{z_1}{z_2}
=
\frac{\rho_1}{\rho_2}
[\cos(\theta_1-\theta_2)+i\sin(\theta_1-\theta_2)].
$$

Quindi

$$
\arg\left(\frac{z_1}{z_2}\right)
\equiv\arg z_1-\arg z_2\pmod{2\pi}.
$$

---

### **7. Interpretazione geometrica del prodotto**

Moltiplicare per

$$
a=r(\cos\alpha+i\sin\alpha)
$$

significa:

- dilatare tutte le distanze dall'origine del fattore $r$;
- ruotare tutti gli angoli di $\alpha$.

Se $r=1$, il prodotto per $a$ è una rotazione. In particolare:

$$
iz
$$

ruota $z$ di $\pi/2$ in senso antiorario, mentre

$$
-iz
$$

lo ruota di $-\pi/2$.

<!-- TODO FIGURA: punto z, immagine az, arco di rotazione alpha e raggi |z| e |a||z|, con caso speciale iz; obiettivo didattico: interpretare il prodotto come omotetia composta con rotazione. -->

---

### **8. Esempio completo di prodotto e quoziente**

Siano

$$
z_1=2\left(\cos\frac{3\pi}{4}+i\sin\frac{3\pi}{4}\right),
$$

$$
z_2=3\left(\cos\frac{2\pi}{3}+i\sin\frac{2\pi}{3}\right).
$$

Allora

$$
z_1z_2=6\left(\cos\frac{17\pi}{12}+i\sin\frac{17\pi}{12}\right).
$$

Un argomento principale è

$$
\frac{17\pi}{12}-2\pi=-\frac{7\pi}{12}.
$$

Inoltre,

$$
\frac{z_1}{z_2}
=
\frac23\left(\cos\frac{\pi}{12}+i\sin\frac{\pi}{12}\right).
$$

---

### **9. Errori frequenti**

1. Usare $\arctan(b/a)$ senza controllare quadrante e assi.
2. Scrivere $\operatorname{Arg}(zw)=\operatorname{Arg}z+\operatorname{Arg}w$ senza ridurre nel ramo scelto.
3. Attribuire a $0$ un argomento.
4. Usare un modulo negativo nella forma polare: per convenzione $\rho>0$ se $z\ne0$.
5. Sottrarre i moduli nel quoziente invece di dividerli.
6. Confondere l'insieme $\arg z$ con il singolo valore $\operatorname{Arg}z$.

---

### **10. Esercizi**

1. Converti in forma polare $1-i$, $-2i$, $-1+\sqrt3i$ e $-3-3i$ usando il ramo principale.
2. Converti in forma algebrica $5(\cos(4\pi/3)+i\sin(4\pi/3))$.
3. Calcola prodotto e quoziente di $1+i$ e $\sqrt3-i$ in forma polare, poi verifica in forma algebrica.
4. Trova modulo e argomento principale di $z=(1+i)^2(\sqrt3-i)$.
5. Fornisci un secondo controesempio all'uguaglianza letterale $\operatorname{Arg}(zw)=\operatorname{Arg}z+\operatorname{Arg}w$.
6. Determina tutti i $z$ tali che $|z|=2$ e $\operatorname{Arg}(iz)=\pi$.
7. Spiega geometricamente l'effetto delle mappe $z\mapsto(1+i)z$ e $z\mapsto z/(1+i)$.
8. Dimostra il criterio di uguaglianza di due forme polari con modulo positivo.

---

### **11. Riepilogo**

> ✅ Ogni complesso non nullo ha forma $z=\rho(\cos\theta+i\sin\theta)$ con $\rho=|z|>0$ e $\theta$ definito modulo $2\pi$.

$$
|z_1z_2|=|z_1|\,|z_2|,
\qquad
\arg(z_1z_2)\equiv\arg z_1+\arg z_2\pmod{2\pi},
$$

$$
\left|\frac{z_1}{z_2}\right|=\frac{|z_1|}{|z_2|},
\qquad
\arg\left(\frac{z_1}{z_2}\right)\equiv\arg z_1-\arg z_2\pmod{2\pi}.
$$
