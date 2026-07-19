# **M8 UD4 Lezione 3 - Il piccolo-o (e il grande-O)**

### **1. Idea di base**

In questa lezione introduciamo il concetto di **piccolo-o** e vediamo come esso descriva in modo preciso **quanto una funzione sia trascurabile rispetto a un’altra** quando ci si avvicina a un certo punto.

Il piccolo-o è strettamente collegato al concetto di **equivalenza asintotica** visto nella lezione precedente e diventerà uno strumento fondamentale nello studio degli **sviluppi di Taylor**.

---
### **2. Definizione di piccolo-o**

Siano $f(x)$ e $g(x)$ due funzioni definite in un intorno puntato di un punto $x_0$ (anche $x_0=\pm\infty$), con $g(x)\neq 0$ in tale intorno puntato.

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
f(x)\,g(x) = o(x^{n+m})
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
### **15. Il grande-$O$: controllo senza annullamento**

Accanto al piccolo-$o$ esiste un simbolo "fratello", il **grande-$O$**, che esprime un controllo più debole: non "trascurabile rispetto a", ma "**al più dello stesso ordine di**".

**Definizione.** Si scrive

$$
f(x) = O\left(g(x)\right) \quad \text{per } x \to x_0
$$

se esistono una costante $C>0$ e un $\delta>0$ tali che

$$
|f(x)| \le C\,|g(x)| \qquad \text{per ogni } x \text{ con } 0<|x-x_0|<\delta
$$

(per $x\to+\infty$ la condizione diventa: esiste $R$ tale che la maggiorazione valga per ogni $x>R$).

#### **15.1 Differenza tra $o$ e $O$**

- $f=o(g)$: il rapporto $f/g$ **tende a $0$** — $f$ è **strettamente più piccola**;
- $f=O(g)$: il rapporto $f/g$ resta **limitato** — $f$ **non supera l'ordine** di $g$, ma può essere dello stesso ordine.

Relazioni tra i simboli:

- se $f=o(g)$ allora $f=O(g)$ (una quantità che tende a $0$ è in particolare limitata vicino al punto, per la limitatezza locale della Lezione 3D di UD1); il viceversa è **falso**;
- se $f\sim g$ allora $f=O(g)$ **e** $g=O(f)$ (rapporti che tendono a $1$);
- $f=O(1)$ significa "$f$ è **localmente limitata**" e $f=o(1)$ significa "$f$ è **infinitesima**".

**Esempio che separa i due simboli.** Per $x\to 0$:

$$
x\sin\frac{1}{x} = O(x) \qquad \text{ma} \qquad x\sin\frac{1}{x} \neq o(x)
$$

Infatti $\left|x\sin\frac{1}{x}\right|\le|x|$ (quindi $O(x)$ con $C=1$), ma il rapporto $\sin\frac{1}{x}$ oscilla tra $-1$ e $1$ senza tendere a $0$; si noti che non vale nemmeno $x\sin\frac 1x \sim x$, perché il rapporto non tende a $1$.

#### **15.2 Proprietà algebriche essenziali**

- $O(g)+O(g)=O(g)$ e $o(g)+O(g)=O(g)$;
- $O(x^n)\cdot O(x^m)=O(x^{n+m})$, e analogamente misto: $o(x^n)\cdot O(x^m)=o(x^{n+m})$;
- $f\sim g \iff f = g + o(g)$, e in tal caso $f = O(g)$.

> 💡 In informatica il grande-$O$ è lo stesso oggetto usato per la **complessità degli algoritmi** ($T(n)=O(n\log n)$, per $n\to+\infty$): un limite superiore d'ordine di crescita, non una stima esatta. Il contesto asintotico è $n\to+\infty$ invece di $x\to x_0$, ma la definizione è identica.

---
### **16. Idea chiave da portare a casa**

- Il piccolo-o misura **quanto una funzione è trascurabile**
    
- Il grande-O misura **un tetto sull'ordine di grandezza** (limitatezza del rapporto, non annullamento)

- Le potenze più alte **spariscono** rispetto a quelle più basse
    
- Il piccolo-o è la **base tecnica degli sviluppi di Taylor**
    
- Tutto diventa **meccanico con l’esercizio**
    

---

👉 **Ora siamo prontissimi per la Formula di Taylor**.