## **Lezione 16: Ordine di infinitesimo e parte principale**

### **1. Definizione**

Sia $f(x)$ una funzione tale che, per $x \to 0$, valga una equivalenza asintotica del tipo:

$$

f(x) \sim kx^\alpha

$$
dove:

- $k \in \mathbb{R}$,
    
- $\alpha \in \mathbb{R}$ con $\alpha > 0$.

Allora diciamo che:

- $f(x)$ è un **infinitesimo di ordine $\alpha$**;
    
- $kx^\alpha$ è la **parte principale** di $f(x)$.

---
### **2. Esempi immediati**

#### **2.1 $f(x)=\sin x$**

Sappiamo che:

$$

\sin x \sim x \quad (x \to 0)

$$
Quindi:

- **ordine di infinitesimo**: $\alpha = 1$
    
- **parte principale**: $x$

---
#### **2.2 $f(x)=\cos x - 1$**

Sappiamo che:
$$

\cos x - 1 \sim -\frac{1}{2}x^2 \quad (x \to 0)

$$
Quindi:

- **ordine di infinitesimo**: $\alpha = 2$
    
- **parte principale**: $-\frac{1}{2}x^2$

---
### **3. Collegamento con il piccolo-o**

Ricordiamo il legame fondamentale:

$$

f(x) \sim kx^\alpha \quad \Longleftrightarrow \quad f(x)=kx^\alpha + o(x^\alpha)

$$

Questa equivalenza è cruciale perché ci dice che:

- trovare **parte principale** e **ordine** significa trovare il **primo termine non nullo** nello sviluppo,
    
- e tutto il resto viene assorbito nel termine $o(x^\alpha)$.

---
### **4. Metodo operativo con Taylor**

Se non hai l’equivalenza “pronta”, puoi usare gli **sviluppi di Taylor**:

1. sviluppi $f(x)$ attorno a $0$;
    
2. cerchi il **primo termine non nullo**;
    
3. quello è la **parte principale**;
    
4. l’esponente del monomio è l’**ordine dell’infinitesimo**.

---
### **5. Esempio completo**

Determinare **ordine di infinitesimo** e **parte principale** di:

$$

\sqrt{1+x}-\sqrt{1-x}-x

$$
#### **5.1 Sviluppo di $\sqrt{1+x}$**

Usiamo lo sviluppo di $(1+x)^\alpha$ con $\alpha=\frac{1}{2}$, fino al terzo ordine:

$$
\sqrt{1+x} =

1+\frac{1}{2}x-\frac{1}{8}x^2+\frac{1}{16}x^3+o(x^3)
$$
#### **5.2 Sviluppo di $\sqrt{1-x}$**

Si ottiene sostituendo $x \mapsto -x$:

- i termini di grado dispari cambiano segno,
    
- quelli di grado pari restano invariati.

$$
\sqrt{1-x} =

1-\frac{1}{2}x-\frac{1}{8}x^2-\frac{1}{16}x^3+o(x^3)
$$
#### **5.3 Sottrazione e semplificazioni**

Calcoliamo:

$$

\sqrt{1+x}-\sqrt{1-x}

$$
$$
\left(1+\frac{1}{2}x-\frac{1}{8}x^2+\frac{1}{16}x^3+o(x^3)\right) =

\left(1-\frac{1}{2}x-\frac{1}{8}x^2-\frac{1}{16}x^3+o(x^3)\right)

$$

I termini costanti si eliminano, i termini in $x^2$ si eliminano, e rimane:

$$
\sqrt{1+x}-\sqrt{1-x} =

x+\frac{1}{8}x^3+o(x^3)
$$
Ora sottraiamo $x$:

$$
\sqrt{1+x}-\sqrt{1-x}-x =

\frac{1}{8}x^3+o(x^3)
$$
#### **5.4 Conclusione**

Da:
$$
\sqrt{1+x}-\sqrt{1-x}-x =

\frac{1}{8}x^3+o(x^3)

$$
segue che:

- **ordine di infinitesimo**: $\alpha = 3$
    
- **parte principale**: $\frac{1}{8}x^3$

---
### **6. Perché sviluppare fino al terzo ordine**

- Se ti fermi prima, tutti i termini si cancellano e ti resta solo un $o(\cdot)$, quindi non capisci l’ordine.
    
- Se vai oltre, aggiungi termini **trascurabili** rispetto al primo non nullo, quindi inutili.

---
### **7. Uso pratico 1: eliminare infinitesimi di ordine superiore nei rapporti**

In un rapporto tra infinitesimi, **aggiungere o togliere termini di ordine superiore non cambia il limite**.

Esempio tipico: se in una somma hai termini con ordini diversi, puoi tenere solo quelli **dominanti**.

Caso descritto nel video:

- $\sin x$ è ordine $1$ perché $\sin x \sim x$
    
- $\sin(x^2)$ è ordine $2$ perché $\sin(x^2)\sim x^2$

Quindi, in un limite, un termine di ordine $2$ è **trascurabile** rispetto a uno di ordine $1$.

Il ragionamento porta a ridurre il limite a quello notevole:

$$

\lim_{x \to 0}\frac{\sin x}{x}=1

$$

---
### **8. Uso pratico 2: grafico qualitativo tramite parte principale**

La parte principale permette di capire **come appare il grafico** vicino a $0$ senza fare uno studio completo.

Se:
$$

f(x) \sim \frac{x}{2} \quad (x \to 0)

$$

allora vicino a $0$ il grafico di $f(x)$ sarà molto simile a quello della retta:

$$

y=\frac{x}{2}

$$

Quindi la funzione “si comporta come” quella retta in prima approssimazione.

---
### **9. Estensioni**

Il concetto di parte principale e ordine:

- non vale solo per $x \to 0$,
    
- ma si può definire anche per:
$$

x \to x_0 \neq 0

$$

e anche per:
$$

x \to +\infty

$$

---
### **10. Idea chiave da portare a casa**

- **parte principale** = primo termine non nullo nello sviluppo
    
- **ordine** = esponente di quel termine
    
- nei limiti, i termini di ordine superiore sono **trascurabili**
    
- la parte principale aiuta anche nel grafico qualitativo locale