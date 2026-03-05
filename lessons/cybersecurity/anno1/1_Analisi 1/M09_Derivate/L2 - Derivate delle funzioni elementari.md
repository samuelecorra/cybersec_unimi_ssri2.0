## **Lezione 2: Derivate delle funzioni elementari**

### **Costanti, potenze e radici**

### **1. Dalla derivata in un punto alla funzione derivata**

Nella lezione precedente abbiamo introdotto la **derivata di una funzione in un punto** $x_0$, cioè il numero reale che rappresenta il coefficiente angolare della retta tangente nel punto di ascissa $x_0$.

Capita però molto spesso che una funzione non sia derivabile solo in un singolo punto, ma **in tutti i punti di un certo insieme** $A$ del suo dominio.

In questo caso si dice che:

> la funzione è **derivabile nell’insieme $A$**

Quando ciò accade, possiamo associare a ogni punto $x \in A$ il valore della derivata in quel punto.

Nasce così una **nuova funzione**, definita da:

$$

x \longmapsto f’(x)

$$

Questa funzione si chiama **funzione derivata prima** della funzione $f$ e si indica con:

$$

y = f’(x)

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

f’(x) = \lim_{h \to 0} \frac{f(x+h) - f(x)}{h}

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

f’(x) = 0

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

f’(x) = \lim_{h \to 0} \frac{(x+h)^2 - x^2}{h}

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

f’(x) = 2x

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

f’(x) = 3x^2

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
### **8. Derivata di $x^n$ con $n \in \mathbb{N}$**

Si può dimostrare che per ogni numero naturale $n$ vale:

$$

\frac{d}{dx}\left(x^n\right) = n,x^{n-1}

$$

La dimostrazione sfrutta lo sviluppo della potenza del binomio $(x+h)^n$.

Nel rapporto incrementale:

- il termine $x^n$ si semplifica
    
- il termine $n x^{n-1} h$ è l’unico che sopravvive al limite
    
- tutti gli altri termini contengono potenze di $h$ e quindi si annullano per $h \to 0$

---
### **9. Generalizzazione agli esponenti reali**

La regola può essere ulteriormente estesa.

Se:

$$

f(x) = x^\alpha

$$

con $\alpha \in \mathbb{R}$, allora:

$$

f’(x) = \alpha,x^{\alpha - 1}

$$

La dimostrazione richiede strumenti diversi a seconda del tipo di esponente, ma **il risultato finale resta lo stesso**.

---
### **10. Derivata delle funzioni radice** 

Le funzioni radice possono essere viste come **potenze a esponente frazionario**.

Ad esempio:
$$

\sqrt{x} = x^{\frac{1}{2}}

$$

Applicando la regola generale:
$$

f’(x) = \frac{1}{2}x^{\frac{1}{2}-1}

$$

cioè:
$$

f’(x) = \frac{1}{2}x^{-\frac{1}{2}}

$$
che equivale a:
$$

f’(x) = \frac{1}{2\sqrt{x}}

$$

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