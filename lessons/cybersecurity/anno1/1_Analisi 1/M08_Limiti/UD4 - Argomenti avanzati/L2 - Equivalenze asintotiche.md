# **M8 UD4 Lezione 2 - Equivalenze asintotiche**

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

Siano $f(x)$ e $g(x)$ due funzioni definite in un intorno puntato di $x_0$ (con $x_0\in\overline{\mathbb{R}}$: la definizione vale identica per $x\to\pm\infty$), con $g(x)\neq 0$ in tale intorno puntato così che il rapporto abbia senso.

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

> 📌 L'equivalenza asintotica è una **relazione di equivalenza** (nel senso di M02) sulle funzioni non nulle vicino a $x_0$:
>
> - **riflessiva**: $f\sim f$, perché $\frac{f}{f}=1\to 1$;
> - **simmetrica**: se $\frac{f}{g}\to 1$ allora $\frac{g}{f}=\frac{1}{f/g}\to\frac{1}{1}=1$ (quoziente con denominatore di limite $1\neq 0$);
> - **transitiva**: se $\frac{f}{g}\to 1$ e $\frac{g}{h}\to 1$ allora $\frac{f}{h}=\frac{f}{g}\cdot\frac{g}{h}\to 1\cdot 1=1$.
>
> Inoltre $\sim$ **non è un'uguaglianza**: $\sin x\sim x$ non dice che $\sin x = x$, dice che il loro rapporto tende a $1$. Due funzioni equivalenti possono differire moltissimo lontano da $x_0$.

---
### **3. Equivalenze asintotiche fondamentali (per $x \to 0$)**

Le più importanti da **sapere a memoria**:

$$
\begin{aligned} \sin x &\sim x \\ \tan x &\sim x \\ 1 - \cos x &\sim \frac{1}{2}x^2 \\ e^x - 1 &\sim x \\ \ln(1+x) &\sim x \\ (1+x)^\alpha - 1 &\sim \alpha x \quad (\alpha \neq 0) \end{aligned}
$$

Non sono formule piovute dal cielo: ognuna è la **riscrittura di un limite notevole dimostrato nella UD3** (l'ultima viene dalla Lezione 2 della UD3, §8). Nel caso $\alpha = 0$ il primo membro è identicamente nullo e l'equivalenza con $0\cdot x$ non ha senso (vedi punto 8.1). Alla lista si aggiungono le iperboliche, che discendono dall'esponenziale: $\sinh x \sim x$ e $\cosh x - 1 \sim \frac{1}{2}x^2$.

---
### **4. Sostituzione di una funzione infinitesima**

Le equivalenze restano valide se **al posto di** $x$ metti una funzione $\varphi(x)$ tale che:

$$
\varphi(x) \to 0
$$

con $\varphi(x)\neq 0$ in un intorno puntato del punto considerato (è il teorema di composizione dei limiti, Lezione 3F della UD1, applicato al rapporto).
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

*Dimostrazione*: $\dfrac{f_1 f_2}{g_1 g_2} = \dfrac{f_1}{g_1}\cdot\dfrac{f_2}{g_2} \to 1\cdot 1 = 1$ per l'algebra dei limiti. $\square$

#### **Quoziente**

$$
\frac{f_1}{f_2} \sim \frac{g_1}{g_2}
$$

*Dimostrazione*: $\dfrac{f_1/f_2}{g_1/g_2} = \dfrac{f_1}{g_1}\cdot\dfrac{g_2}{f_2} \to 1\cdot 1 = 1$, dove $\dfrac{g_2}{f_2}\to 1$ per la simmetria di $\sim$ (punto 2). $\square$

#### **Potenze**

Se $f\sim g$ e le due funzioni sono **positive** in un intorno puntato (condizione necessaria perché la potenza reale abbia senso), per ogni $\alpha \in \mathbb{R}$:

$$
(f(x))^\alpha \sim (g(x))^\alpha
$$

*Dimostrazione*: $\dfrac{f^\alpha}{g^\alpha} = \left(\dfrac{f}{g}\right)^{\alpha}$ e la funzione $t\mapsto t^\alpha$ è continua in $t=1$ con $1^\alpha=1$: si conclude con il teorema di composizione. $\square$

⚠️ **Attenzione**: queste proprietà valgono **solo per prodotti, quozienti e potenze**, non per somme e differenze.

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

**Controesempio completamente svolto.** Calcoliamo

$$
\lim_{x\to 0}\frac{\sin x - x}{x^3}
$$

Ragionamento **sbagliato**: "poiché $\sin x\sim x$, il numeratore è $\sim x-x=0$, quindi il limite è $0$". Risultato **falso**: lo sviluppo $\sin x = x-\frac{x^3}{6}+o(x^3)$ (Lezione 4) dà

$$
\frac{\sin x - x}{x^3} = \frac{-\frac{x^3}{6}+o(x^3)}{x^3} \to -\frac{1}{6} \neq 0
$$

Che cosa è andato storto? L'equivalenza $\sin x\sim x$ controlla solo il **termine dominante** $x$; nella differenza $\sin x - x$ i termini dominanti **si cancellano** e il risultato dipende dai termini successivi ($-\frac{x^3}{6}$), che l'equivalenza al primo ordine non vede.

#### **3. Procedura corretta in presenza di somme**

1. individua il termine dominante di ciascun addendo;
2. controlla se, nella somma o differenza, i termini dominanti **si cancellano**;
3. se **non** si cancellano: raccogli il dominante e usa le equivalenze sui singoli **fattori** dell'espressione raccolta;
4. se si cancellano: le equivalenze al primo ordine **non bastano**; servono gli sviluppi con il piccolo-o (Lezioni 3-5) fino al primo termine superstite.

---
### **9. Idea chiave da portare a casa**

- Le equivalenze asintotiche **semplificano i limiti**
    
- Funzionano benissimo per **prodotti, quozienti e potenze**
    
- **Non sono uguaglianze**
    
- Quando il primo ordine non basta → **Taylor**
