## **Lezione 2: Primitive, integrali indefiniti e prime proprieta**

---
### **1. Come si calcola un integrale definito nella pratica**

Per calcolare
$$

\int_a^b f(x),dx

$$

di solito non si usa la definizione “a rettangolini”, ma si procede cosi:

1. si trova una funzione $F(x)$ tale che
    
$$

F’(x)=f(x)

$$

2. si calcola $F(b)$ e $F(a)$
    
3. si fa la differenza

$$

\int_a^b f(x),dx = F(b)-F(a)

$$

La parte difficile e quasi sempre il punto 1: **trovare una primitiva**.

---
### **2. Esistenza delle primitive**

Si puo dimostrare che:

- se $f(x)$ e **continua** su $[a,b]$
    
- allora **ammette almeno una primitiva** su $[a,b]$

Quindi, se la funzione integranda e continua nell’intervallo di integrazione, la procedura precedente e sempre applicabile.

---
### **3. Perche si dice “una primitiva” e non “la primitiva”**

Se $F(x)$ e una primitiva di $f(x)$, allora anche

$$

F(x)+C

$$

e una primitiva, per ogni costante reale $C$, perche:

$$

(F(x)+C)’ = F’(x)+0 = f(x)

$$

Quindi le primitive sono **infinite**.

Inoltre, se $F_1$ e $F_2$ sono due primitive della stessa funzione nello stesso intervallo, allora:

$$

F_1(x)-F_2(x)=C

$$

cioe differiscono sempre per una costante.

---
### **4. Integrale indefinito**

Per indicare una primitiva (o, secondo alcuni testi, l’intera famiglia delle primitive) si usa l’**integrale indefinito**:
$$

\int f(x),dx

$$

La differenza rispetto all’integrale definito e che **non ci sono gli estremi** $a,b$.

---
### **5. Primitive elementari**

Le “primitive elementari” sono quelle che trovi direttamente ricordando le derivate.

Esempi base:

- poiche $(\sin x)’=\cos x$ allora

$$

\int \cos x,dx = \sin x + C

$$

- poiche $(\cos x)’=-\sin x$ allora

$$

\int \sin x,dx = -\cos x + C

$$

- poiche $(e^x)’=e^x$ allora

$$

\int e^x,dx = e^x + C

$$

- per le potenze:

$$

\int x^n,dx = \frac{x^{n+1}}{n+1}+C \quad \text{con } n\ne -1

$$

---
### **6. Esempio 1 (integrale definito)**

Calcolare:
$$

\int_0^{\pi/2}\sin x,dx

$$

Una primitiva di $\sin x$ e $-\cos x$.

Quindi:

$$

\left[-\cos x\right]_0^{\pi/2} = -\cos\left(\frac{\pi}{2}\right) - \left(-\cos(0)\right)

$$

Sapendo che $\cos(\pi/2)=0$ e $\cos(0)=1$:

$$

0+1=1

$$

---
### **7. Esempio 2 (integrale definito su potenze)**

Calcolare:
$$

\int_1^2 x^3,dx

$$

Primitiva:
$$

F(x)=\frac{x^4}{4}

$$

Quindi:
$$

\left[\frac{x^4}{4}\right]_1^2 = \frac{2^4}{4}-\frac{1^4}{4}=\frac{16}{4}-\frac{1}{4}=\frac{15}{4}

$$

Nota importante: aggiungere una costante $C$ alla primitiva non cambia nulla, perche in $F(b)-F(a)$ si cancella.

---
### **8. Linearita dell’integrale (proprieta piu usate)**

Se $f,g$ sono integrabili:

$$

\int (f(x)\pm g(x)),dx = \int f(x),dx \pm \int g(x),dx

$$

Se $k$ e una costante reale:

$$

\int k f(x),dx = k\int f(x),dx

$$

---
### **9. Esempio rapido di linearita**

Calcolare:
$$

\int (3x+\cos x),dx

$$
Spezzo e porto fuori il 3:
$$

\int 3x,dx + \int \cos x,dx

= 3\int x,dx + \sin x

$$
Ora:
$$

\int x,dx = \frac{x^2}{2}

$$

Quindi:
$$

\frac{3x^2}{2}+\sin x + C

$$

---
### **10. Proprieta di “spezzamento” dell’integrale definito**

Per un punto $c$ qualunque:
$$

\int_a^b f(x),dx = \int_a^c f(x),dx + \int_c^b f(x),dx

$$

Interpretazione geometrica: stai spezzando l’area totale in due aree.

Questa proprieta torna utilissima con valori assoluti e funzioni a tratti.

---
### **11. Disuguaglianza utile**

Per integrali definiti vale:

$$

\left|\int_a^b f(x),dx\right|\le \int_a^b |f(x)|,dx

$$

Serve soprattutto per stimare e fare maggiorazioni.

---
## **Cosa arriva nella Lezione 3**

Il punto vero da dominare adesso e questo:

> **come si trovano le primitive quando non sono “elementari”**

E la direzione che avete anticipato e:

- riconoscere integrandi che sono **derivate di funzioni composte**
    
- quindi usare il pattern tipo “chain rule al contrario”