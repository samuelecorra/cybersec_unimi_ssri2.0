## **Lezione 12: Equivalenze asintotiche**

### **1. Idea di base**

Molti limiti notevoli ci dicono che, **quando** $x$ **si avvicina a un certo valore**, alcune funzioni **si comportano come funzioni più semplici**.

Esempio classico:

$$
\lim_{x \to 0} \frac{\sin x}{x} = 1
$$

Questo significa che $\sin x$ **e** $x$, vicino a 0, **si comportano allo stesso modo**.

Non sono uguali, ma **il loro rapporto tende a 1**.

Questa idea viene formalizzata con il concetto di **equivalenza asintotica**.

---
### **2. Definizione di equivalenza asintotica**

Siano $f(x)$ e $g(x)$ due funzioni.

Si dice che $f(x)$ **è asintoticamente equivalente a** $g(x)$ **per** $x \to x_0$ se:

$$
\lim_{x \to x_0} \frac{f(x)}{g(x)} = 1
$$
Si scrive:

$$
f(x) \sim g(x) \quad \text{per } x \to x_0
$$

**Significato intuitivo:**

vicino a $x_0$, $f(x)$ e $g(x)$ hanno **lo stesso comportamento dominante**.

---
### **3. Equivalenze asintotiche fondamentali (per** x \to 0**)**

Le più importanti da **sapere a memoria**:

$$
\begin{aligned} \sin x &\sim x \\ \tan x &\sim x \\ 1 - \cos x &\sim \frac{1}{2}x^2 \\ e^x - 1 &\sim x \\ \ln(1+x) &\sim x \\ (1+x)^\alpha - 1 &\sim \alpha x \quad (\alpha \in \mathbb{R}) \end{aligned}
$$
---
### **4. Sostituzione di una funzione infinitesima**

Le equivalenze restano valide se **al posto di** x metti una funzione \varphi(x) tale che:

$$
\varphi(x) \to 0
$$
Esempi:

$$
\sin(5x) \sim 5x \quad (x \to 0)
$$
  
$$
e^{1/x} - 1 \sim \frac{1}{x} \quad (x \to +\infty)
$$
  
Il criterio è uno solo: **l’argomento deve tendere a zero**.

---
### **5. Proprietà operative fondamentali**

Siano:

$$
f_1 \sim g_1, \quad f_2 \sim g_2 \quad (x \to x_0)
$$
#### **Prodotto**

$$
f_1 f_2 \sim g_1 g_2
$$
#### **Quoziente**

$$
\frac{f_1}{f_2} \sim \frac{g_1}{g_2}
$$
#### **Potenze**

Per ogni $\alpha \in \mathbb{R}$:

$$
(f(x))^\alpha \sim (g(x))^\alpha
$$

⚠️ **Attenzione**: queste proprietà valgono **solo per prodotti e quozienti**, non per somme.

---
### **6. Esempio 1 – Limite con prodotto e quoziente**

Calcolare:

$$
\lim_{x \to 0} \frac{(e^{3x}-1)\sin(4x)}{\tan^2(2x)}
$$
#### **Passo 1 – Sostituzioni asintotiche**

$$
\begin{aligned} e^{3x}-1 &\sim 3x \\ \sin(4x) &\sim 4x \\ \tan(2x) &\sim 2x \end{aligned}
$$
Quindi:

$$
\tan^2(2x) \sim (2x)^2 = 4x^2
$$
#### **Passo 2 – Riscrittura del limite**

$$
\frac{3x \cdot 4x}{4x^2} = \frac{12x^2}{4x^2} = 3
$$
#### **Conclusione**

$$
\boxed{3}
$$

---
### **7. Esempio 2 – Limite con radice**

Calcolare:

$$
\lim_{x \to 0^-} \frac{\sqrt{1-\cos(x^2)}}{\ln(1+2x)}
$$
#### **Numeratore**

$$
1 - \cos(x^2) \sim \frac{1}{2}x^4
$$
Radice quadrata:

$$
\sqrt{1-\cos(x^2)} \sim \sqrt{\frac{1}{2}x^4} = \frac{x^2}{\sqrt{2}}
$$
#### **Denominatore**

$$
\ln(1+2x) \sim 2x
$$
#### **Rapporto**

$$
\frac{\frac{x^2}{\sqrt{2}}}{2x} = \frac{x}{2\sqrt{2}}
$$
#### **Limite finale**

$$
\lim_{x \to 0^-} \frac{x}{2\sqrt{2}} = 0
$$
$$
\boxed{0}
$$
---
### **8. Errori da NON fare**  

#### **1. Mai scrivere**

$$
f(x) \sim 0
$$
Non ha senso: l’equivalenza richiede **un rapporto che tenda a 1**, e non si divide per zero.

---
#### **2. Le somme NON funzionano**

Anche se:

$$
\sin x \sim x, \quad \ln(1+x) \sim x
$$
  
❌ **Non puoi scrivere**:

$$
\sin x - \ln(1+x) \sim x - x
$$
  
Questo annulla il termine dominante.
In questi casi serve uno strumento più raffinato: **gli sviluppi di Taylor**.

---
### **9. Idea chiave da portare a casa**

- Le equivalenze asintotiche **semplificano i limiti**
    
- Funzionano benissimo per **prodotti, quozienti e potenze**
    
- **Non sono uguaglianze**
    
- Quando il primo ordine non basta → **Taylor**
