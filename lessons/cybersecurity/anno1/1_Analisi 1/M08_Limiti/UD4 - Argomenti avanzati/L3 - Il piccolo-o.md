## **Lezione 13: Il piccolo-o**

### **1. Idea di base**

In questa lezione introduciamo il concetto di **piccolo-o** e vediamo come esso descriva in modo preciso **quanto una funzione sia trascurabile rispetto a un’altra** quando ci si avvicina a un certo punto.

Il piccolo-o è strettamente collegato al concetto di **equivalenza asintotica** visto nella lezione precedente e diventerà uno strumento fondamentale nello studio degli **sviluppi di Taylor**.

---
### **2. Definizione di piccolo-o**

Siano $f(x)$ e $g(x)$ due funzioni definite in un intorno di un punto $x_0$.

Si dice che **$f(x)$ è un piccolo-o di $g(x)$ per $x \to x_0$** se:

$$

\lim_{x \to x_0} \frac{f(x)}{g(x)} = 0

$$
Si scrive:

$$

f(x) = o\left(g(x)\right) \quad \text{per } x \to x_0

$$

---
### **3. Significato intuitivo**

Dire che:
$$

f(x) = o(g(x))

$$

significa che **$f(x)$ è infinitamente più piccola di $g(x)$** quando $x$ si avvicina a $x_0$.

In altre parole, **$f(x)$ è trascurabile rispetto a $g(x)$** nel limite considerato.

---
### **4. Esempio fondamentale**

Consideriamo:
$$

x^2 \quad \text{e} \quad x

$$
Calcoliamo:
$$

\lim_{x \to 0} \frac{x^2}{x} = \lim_{x \to 0} x = 0

$$
Quindi:
$$

x^2 = o(x) \quad \text{per } x \to 0

$$
**Interpretazione:**

vicino a $0$, $x^2$ assume valori **molto più piccoli** di $x$.

---
### **5. Altri esempi**

Per $x \to 0$ valgono:
$$

x^3 = o(x)

$$
$$

8x^4 = o(x)

$$
$$

\sin^2 x = o(x)

$$

Tutte queste funzioni, pur essendo molto diverse tra loro, sono **piccolo-o di $x$**.

---
### **6. Attenzione sul significato di $o(x)$**

La scrittura:
$$

o(x)

$$
❗ **non indica una funzione specifica**.

Indica **qualunque funzione** il cui rapporto con $x$ tende a zero:

$$

\lim_{x \to 0} \frac{o(x)}{x} = 0

$$

Lo stesso vale per $o(g(x))$: è un **insieme di funzioni**, non una singola funzione.

---
### **7. Proprietà algebriche del piccolo-o**

#### **Somma e differenza**

Se:
$$

f(x) = o(x), \quad g(x) = o(x)

$$
allora:
$$

f(x) \pm g(x) = o(x)

$$

---
#### **Generalizzazione alle potenze**

Per ogni $n \in \mathbb{N}$:
$$

o(x^n) \pm o(x^n) = o(x^n)

$$

---
#### **Prodotto di piccoli-o**

Se:
$$

f(x) = o(x^n), \quad g(x) = o(x^m)

$$
allora:
$$

f(x),g(x) = o(x^{n+m})

$$

---
#### **Prodotto con una potenza**

Se:
$$

f(x) = o(x^m)

$$
allora:
$$

x^n f(x) = o(x^{n+m})

$$

---
### **8. Confronto tra potenze**

Per $x \to 0$ vale:
$$

x^n = o(x^m) \quad \text{se } n > m

$$
**Significato:**

le potenze con esponente maggiore sono **infinitamente più piccole** di quelle con esponente minore.

Esempi:
$$

x^3 = o(x)

$$
$$

x^2 = o(x)

$$

---
### **9. Somma di potenze diverse**

Se sommiamo termini di ordine diverso, **sopravvive il termine di ordine minore**:

$$

x^3 + x^5 = x^3 + o(x^3)

$$
I termini con esponente maggiore vengono **assorbiti** perché trascurabili.

---
### **10. Collegamento con l’equivalenza asintotica**

Vale il seguente fatto fondamentale:

$$

f(x) \sim g(x) \quad \text{per } x \to x_0

$$
**se e solo se**
$$

f(x) = g(x) + o(g(x))

$$

**Interpretazione:**

due funzioni sono asintoticamente equivalenti se **differiscono per un termine trascurabile**.

---
### **11. Riscrittura delle equivalenze notevoli**

Esempi per $x \to 0$:
$$

\sin x \sim x \quad \Longleftrightarrow \quad \sin x = x + o(x)

$$

$$

1 - \cos x \sim \frac{1}{2}x^2 \quad \Longleftrightarrow \quad

1 - \cos x = \frac{1}{2}x^2 + o(x^2)

$$

---
### **12. Irrilevanza delle costanti**

Le costanti moltiplicative **non contano** nel piccolo-o:

$$

o(kx^n) = o(x^n)

$$
per ogni $k \neq 0$.

---
### **13. Composizione con funzioni infinitesime**

Se:
$$

\varphi(x) \to 0

$$

tutti gli sviluppi restano validi sostituendo $x$ con $\varphi(x)$.

Esempio:
$$

\sin(x^3) = x^3 + o(x^3)

$$
$$

\ln(1 + 2x^5) = 2x^5 + o(x^5)

$$

---
### **14. Esempio di semplificazione**

Consideriamo:
$$

\sin(x^3) + \ln(1 + 2x^5)

$$
Usando gli sviluppi:
$$

= x^3 + o(x^3) + 2x^5 + o(x^5)

$$
Poiché:
$$

x^5 = o(x^3)

$$
otteniamo:
$$

x^3 + o(x^3)

$$

---
### **15. Idea chiave da portare a casa**

- Il piccolo-o misura **quanto una funzione è trascurabile**
    
- Le potenze più alte **spariscono** rispetto a quelle più basse
    
- Il piccolo-o è la **base tecnica degli sviluppi di Taylor**
    
- Tutto diventa **meccanico con l’esercizio**
    

---

👉 **Ora siamo prontissimi per la Formula di Taylor**.