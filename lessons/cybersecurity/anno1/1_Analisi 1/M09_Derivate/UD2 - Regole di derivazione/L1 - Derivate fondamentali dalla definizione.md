# **M9 UD2 Lezione 1 - Derivate fondamentali dalla definizione**

### **Costanti, potenze e radici**

### **1. Dalla derivata in un punto alla funzione derivata**

Nella lezione precedente abbiamo introdotto la **derivata di una funzione in un punto** $x_0$, cioè il numero reale che rappresenta il coefficiente angolare della retta tangente nel punto di ascissa $x_0$.

Capita però molto spesso che una funzione non sia derivabile solo in un singolo punto, ma **in tutti i punti di un certo insieme** $A$ del suo dominio.

In questo caso si dice che:

> la funzione è **derivabile nell’insieme $A$**

Quando ciò accade, possiamo associare a ogni punto $x \in A$ il valore della derivata in quel punto.

Nasce così una **nuova funzione**, definita da:

$$
x \longmapsto f'(x)
$$

Questa funzione si chiama **funzione derivata prima** della funzione $f$ e si indica con:

$$
y = f'(x)
$$

---
### **2. Obiettivo della lezione**

In questa lezione iniziamo a calcolare **concretamente** le derivate delle funzioni più semplici e frequenti, dette **funzioni elementari**.

Ci occuperemo in particolare di:

- funzioni **costanti**
    
- funzioni **potenza**
    
- funzioni **radice**

---
### **3. Derivata di una funzione costante**

Consideriamo una funzione costante:

$$
f(x) = k
$$

dove $k$ è un numero reale fissato.

Per definizione, la derivata si calcola come:

$$
f'(x) = \lim_{h \to 0} \frac{f(x+h) - f(x)}{h}
$$
Nel nostro caso:
$$
f(x+h) = k \qquad f(x) = k
$$

quindi il rapporto incrementale diventa:

$$
\frac{k - k}{h} = 0
$$

Il limite di una funzione costantemente nulla è zero, dunque:

$$
f'(x) = 0
$$
per ogni $x$.

**Conclusione**
La derivata di una funzione costante è la funzione identicamente nulla.

---
### **4. Interpretazione geometrica**

Questo risultato è perfettamente coerente con il significato geometrico della derivata.

Il grafico di una funzione costante è una **retta orizzontale**.

La retta tangente in qualunque punto coincide con la retta stessa e ha quindi **coefficiente angolare nullo**.

---
### **5. Derivata della funzione $f(x) = x^2$**

Calcoliamo ora la derivata della funzione:

$$
f(x) = x^2
$$
Per definizione:

$$
f'(x) = \lim_{h \to 0} \frac{(x+h)^2 - x^2}{h}
$$

Sviluppiamo il quadrato del binomio:

$$
(x+h)^2 = x^2 + 2xh + h^2
$$

Sostituendo:

$$
\frac{x^2 + 2xh + h^2 - x^2}{h}
$$

Si semplifica $x^2$ e rimane:

$$
\frac{2xh + h^2}{h}
$$

Raccogliendo $h$ al numeratore:

$$
\frac{h(2x + h)}{h}
$$

Semplificando:

$$
2x + h
$$

Passando al limite per $h \to 0$ otteniamo:

$$
f'(x) = 2x
$$

---
### **6. Derivata della funzione $f(x) = x^3$**

Ripetiamo lo stesso procedimento per:

$$
f(x) = x^3
$$

Il rapporto incrementale è:

$$
\frac{(x+h)^3 - x^3}{h}
$$

Sviluppiamo il cubo del binomio:

$$
(x+h)^3 = x^3 + 3x^2h + 3xh^2 + h^3
$$

Sostituendo e semplificando $x^3$:

$$
\frac{3x^2h + 3xh^2 + h^3}{h}
$$

Raccogliendo $h$:

$$
\frac{h(3x^2 + 3xh + h^2)}{h}
$$

Semplificando e passando al limite:

$$
f'(x) = 3x^2
$$

---
### **7. Osservazione chiave sulle potenze**

Guardiamo i risultati ottenuti:

- da $x^2$ otteniamo $2x$
    
- da $x^3$ otteniamo $3x^2$
  
In entrambi i casi:

- **l’esponente scende davanti**
    
- **l’esponente della $x$ diminuisce di uno**

Questo schema si ripete sempre.

---
### **8. Derivata di $x^n$ con $n \in \mathbb{N}$: dimostrazione completa**

**Teorema.** Per ogni numero naturale $n\ge 1$ vale:

$$
\frac{d}{dx}\left(x^n\right) = n\,x^{n-1}
$$

*Dimostrazione.* Per il binomio di Newton (M01, UD0):

$$
(x+h)^n = \sum_{k=0}^{n}\binom{n}{k}x^{\,n-k}h^{k}
= x^n + n\,x^{\,n-1}h + \binom{n}{2}x^{\,n-2}h^2 + \dots + h^n
$$

Nel rapporto incrementale il termine $x^n$ si cancella e si può raccogliere $h$:

$$
\frac{(x+h)^n - x^n}{h}
= n\,x^{\,n-1} + h\left[\binom{n}{2}x^{\,n-2} + \binom{n}{3}x^{\,n-3}h + \dots + h^{\,n-2}\right]
$$

Il contenuto della parentesi quadra è un polinomio in $h$, quindi ha limite finito per $h\to 0$; moltiplicato per $h\to 0$ svanisce (algebra dei limiti, M08). Resta:

$$
\lim_{h\to 0}\frac{(x+h)^n - x^n}{h} = n\,x^{\,n-1} \qquad \square
$$

In alternativa, con la seconda forma del rapporto incrementale e la fattorizzazione $x^n-x_0^n=(x-x_0)(x^{\,n-1}+x^{\,n-2}x_0+\dots+x_0^{\,n-1})$, il quoziente vale la somma di $n$ addendi che tendono ciascuno a $x_0^{\,n-1}$: stessa conclusione.

---
### **8bis. Derivata del reciproco $\frac{1}{x}$ dalla definizione**

Per $x\neq 0$:

$$
\frac{\frac{1}{x+h}-\frac{1}{x}}{h}
= \frac{x-(x+h)}{h\,x(x+h)}
= \frac{-h}{h\,x(x+h)}
= -\frac{1}{x(x+h)}
\xrightarrow[h\to 0]{} -\frac{1}{x^2}
$$

(il passaggio al limite usa la continuità di $x(x+h)$ in $h=0$ e il quoziente con denominatore non nullo, M08). Quindi:

$$
\frac{d}{dx}\left(\frac{1}{x}\right) = -\frac{1}{x^2} \qquad (x\neq 0)
$$

coerente con la formula delle potenze per $\alpha=-1$: $(x^{-1})'=-x^{-2}$.

---
### **9. Generalizzazione agli esponenti reali (anticipazione)**

La regola si estende a ogni esponente:

$$
\frac{d}{dx}\,x^\alpha = \alpha\,x^{\alpha - 1} \qquad (\alpha\in\mathbb{R},\ x>0)
$$

> ⚠️ Per ora questa formula è **solo un annuncio**: per $\alpha$ non naturale la dimostrazione richiede strumenti che ancora non abbiamo (catena, esponenziali e logaritmi). La derivazione rigorosa, con la discussione completa dei domini ($\alpha$ intero negativo, razionale, reale; il comportamento in $x=0$; le basi negative nei casi razionali compatibili), è nella UD3, Lezione 1. Qui evitiamo di usare la regola generale prima di averla dimostrata.

---
### **10. Derivata di $\sqrt{x}$ dalla definizione**

Per la radice non serve aspettare la regola generale: la definizione basta, con la razionalizzazione (stessa tecnica di M08). Per $x>0$:

$$
\frac{\sqrt{x+h}-\sqrt{x}}{h}
= \frac{(x+h)-x}{h\left(\sqrt{x+h}+\sqrt{x}\right)}
= \frac{1}{\sqrt{x+h}+\sqrt{x}}
\xrightarrow[h\to 0]{} \frac{1}{2\sqrt{x}}
$$

Quindi:

$$
\frac{d}{dx}\sqrt{x} = \frac{1}{2\sqrt{x}} \qquad (x>0)
$$

Il risultato coincide con la formula delle potenze per $\alpha=\frac12$ ($\frac{1}{2}x^{-1/2}$), come deve. In $x=0$ (bordo del dominio) il rapporto incrementale destro è $\frac{\sqrt h}{h}=\frac{1}{\sqrt h}\to+\infty$: **niente derivata** in $0$, ma tangente verticale — il tema della UD4.

---
### **11. Riepilogo della lezione**

Abbiamo visto che:

- la derivata di una **costante** è zero
    
- la derivata di una **potenza** si ottiene facendo scendere l’esponente e diminuendolo di uno
    
- le **radici** si derivano trattandole come potenze con esponente frazionario

---
### **12. Anticipazione**

Nel prossimo video studieremo:

- la derivata di **seno e coseno**
    
- la derivata della **funzione esponenziale**
    
- la derivata del **logaritmo**

che completeranno il quadro delle derivate fondamentali.