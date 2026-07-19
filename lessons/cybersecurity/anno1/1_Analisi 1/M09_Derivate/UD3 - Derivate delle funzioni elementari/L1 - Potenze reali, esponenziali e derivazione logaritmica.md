# **M9 UD3 Lezione 1 - Potenze reali, esponenziali e derivazione logaritmica**

### **Esponenziale–logaritmo come strategia universale**

### **1. Obiettivo della lezione**

Questa lezione chiude tre debiti lasciati aperti dalla UD2 con un'unica idea — riscrivere tutto tramite $e^{(\cdot)}$ e $\ln$ e usare la regola della catena:

1. la derivata di $x^\alpha$ per **ogni** esponente reale (annunciata in UD2/L1 e finora dimostrata solo per esponenti naturali, $-1$ e $\frac12$);
2. la derivata di $a^x$ e di $\log_a x$ per base qualunque;
3. le funzioni con **base ed esponente entrambi variabili**, $y=f(x)^{g(x)}$ (derivazione logaritmica).

---
### **1bis. Potenze con esponente reale: la dimostrazione generale**

**Teorema.** Per ogni $\alpha\in\mathbb{R}$ e ogni $x>0$:

$$
\frac{d}{dx}\,x^\alpha = \alpha\,x^{\alpha-1}
$$

*Dimostrazione.* Per $x>0$ la potenza reale è definita (M01) come $x^\alpha = e^{\alpha\ln x}$. È una composizione di funzioni derivabili (UD2/L2), quindi per la regola della catena:

$$
\frac{d}{dx}\,e^{\alpha\ln x} = e^{\alpha\ln x}\cdot\frac{\alpha}{x} = x^\alpha\cdot\frac{\alpha}{x} = \alpha\,x^{\alpha-1} \qquad\square
$$

**Discussione dei domini**, caso per caso:

- $\alpha\in\mathbb{N}$: la formula vale su tutto $\mathbb{R}$ (dimostrazione col binomio, UD2/L1);
- $\alpha$ intero negativo: vale su $\mathbb{R}\setminus\{0\}$ (potenza naturale composta col reciproco);
- $\alpha=\frac{p}{q}$ razionale con $q$ dispari: $x^{\alpha}$ è definita anche per $x<0$ e la formula vale dove entrambe le espressioni hanno senso; in $x=0$ la derivata esiste solo se $\alpha\ge 1$ (per $0<\alpha<1$ il rapporto incrementale diverge: tangente verticale, come $\sqrt[3]{x}$ — UD4/L1);
- $\alpha\in\mathbb{R}$ generico: **solo** $x>0$; scrivere $(x^{\pi})'$ in $x=-2$ non ha significato.

> ⚠️ La formula "$\alpha x^{\alpha-1}$ vale sempre" senza specificare il dominio è uno degli errori più penalizzati: la validità dipende da $\alpha$, e nei punti di bordo ($x=0$) la formula per la derivata può non implicare la derivabilità.

---
### **1ter. Esponenziali e logaritmi in base qualunque**

Con la stessa tecnica, per $a>0$:

$$
\frac{d}{dx}\,a^x = \frac{d}{dx}\,e^{x\ln a} = e^{x\ln a}\cdot\ln a = a^x\ln a
$$

(per $a=e$ si ritrova $(e^x)'=e^x$: il fattore $\ln e=1$ è ciò che rende la base $e$ "naturale"). E per $a>0$, $a\neq 1$, $x>0$, dal cambiamento di base $\log_a x=\frac{\ln x}{\ln a}$:

$$
\frac{d}{dx}\,\log_a x = \frac{1}{x\ln a}
$$

---
### **1quater. Funzioni con base ed esponente variabili**

Restano le funzioni del tipo:

$$
y = f(x)^{g(x)}
$$

Esempi tipici sono:

- $x^x$
    
- $(\sin x)^{\cos x}$

La regola delle potenze **non è applicabile**, perché funziona solo quando l'esponente è **costante**; e la regola degli esponenziali richiede base costante. Serve la strategia esponenziale-logaritmo, con la condizione di realtà $f(x)>0$ (senza la quale la scrittura $f^g$ con esponente non intero non è definita nei reali).

**Derivazione logaritmica (forma generale).** Se $y=u(x)^{v(x)}$ con $u(x)>0$ e $u,v$ derivabili, da $\ln y = v\ln u$ derivando entrambi i membri (catena a sinistra: $(\ln y)'=\frac{y'}{y}$):

$$
\frac{y'}{y} = v'(x)\ln u(x) + v(x)\frac{u'(x)}{u(x)}
\qquad\Longrightarrow\qquad
y' = u(x)^{v(x)}\left[v'(x)\ln u(x) + v(x)\frac{u'(x)}{u(x)}\right]
$$

È la stessa formula che si ottiene derivando $e^{v\ln u}$ con la catena: due strade, un risultato. Per potenze **intere** dell'esponente la scrittura ha senso anche con base di segno variabile, ma in quel caso conviene derivare con prodotto/catena ordinari, senza logaritmi.

---
### **2. L’idea chiave: riscrivere con esponenziale e logaritmo**

Il trucco fondamentale è questo:

$$
f(x)^{g(x)} = e^{\ln\left(f(x)^{g(x)}\right)}
$$

Poiché esponenziale e logaritmo sono funzioni inverse, non stiamo cambiando la funzione, solo la sua **forma**.

Usando le proprietà dei logaritmi:

$$
\ln\left(f(x)^{g(x)}\right) = g(x) \cdot \ln f(x)
$$

Quindi:
$$
f(x)^{g(x)} = e^{\,g(x)\ln f(x)}
$$

A questo punto abbiamo:

- una **funzione composta** (esponenziale)
    
- che contiene una **funzione interna**
    
- che spesso è un **prodotto** o un’altra funzione composta

Tutto si risolve con **regola della catena + regole già note**.

---
### **3. Primo esempio:** $y = x^x$

Qui:

- base variabile
    
- esponente variabile

Riscriviamo:
$$
x^x = e^{x\ln x}
$$

Ora deriviamo.

**Derivata dell’esponenziale (funzione esterna)**

$$
\frac{d}{dx}\big(e^{x\ln x}\big) = e^{x\ln x}\cdot \frac{d}{dx}(x\ln x)
$$

**Derivata della funzione interna** x\ln x

È un prodotto:
$$
\frac{d}{dx}(x\ln x) = 1\cdot \ln x + x\cdot \frac{1}{x}
$$

cioè:
$$
\ln x + 1
$$

**Risultato finale**

$$
y' = e^{x\ln x}(\ln x + 1)
$$

Ricordando che $e^{x\ln x} = x^x$:

$$
\boxed{y' = x^x(\ln x + 1)}
$$

---
### **4. Secondo esempio:** $y = (\sin x)^{\cos x}$

Anche qui abbiamo una funzione elevata a un’altra funzione.

**Riscrittura**

$$
(\sin x)^{\cos x} = e^{\cos x \ln(\sin x)}
$$

Ora deriviamo.

**Derivata dell’esponenziale**

$$
\frac{d}{dx} = e^{\cos x \cdot \ln(\sin x)} \cdot \frac{d}{dx}\big(\cos x \cdot \ln(\sin x)\big)
$$

La funzione interna è un **prodotto**.

**Derivata del prodotto**

$$
\frac{d}{dx}(\cos x \cdot \ln(\sin x)) =
(-\sin x) \cdot \ln(\sin x)
+
\cos x \cdot \frac{d}{dx}(\ln(\sin x))
$$

Ma:
$$
\frac{d}{dx}(\ln(\sin x)) = \frac{1}{\sin x}\cdot \cos x
$$

Quindi:
$$
(-\sin x)\ln(\sin x) + \frac{\cos^2 x}{\sin x}
$$

**Risultato finale**

Sostituendo di nuovo $e^{\cos x \ln(\sin x)} = (\sin x)^{\cos x}$:

$$
\boxed{
y' = (\sin x)^{\cos x}
\left[
-\sin x \cdot \ln(\sin x)
+
\frac{\cos^2 x}{\sin x}
\right]
}
$$

Volendo, il secondo termine può essere riscritto come $\cos x \cdot \cot x$.

---
### **5. Terzo esempio: funzione composta con prodotto interno**

Consideriamo:
$$
y = e^{\sqrt[3]{x} \cdot x}
$$
Qui:

- funzione esterna: esponenziale
    
- funzione interna: prodotto

Riscriviamo la radice come potenza:
$$
\sqrt[3]{x} = x^{1/3}
$$

**Derivata dell’esponenziale**

$$
y' = e^{x^{1/3}x} \cdot \frac{d}{dx}(x^{1/3}x)
$$
**Derivata del prodotto**

$$
\frac{d}{dx}(x^{1/3}\cdot x) =
\frac{1}{3}x^{-2/3}\cdot x
+
x^{1/3}\cdot 1
$$

Semplificando:

$$
\frac{1}{3}x^{1/3} + x^{1/3} =
\frac{4}{3}x^{1/3}
$$

**Risultato finale**

$$
\boxed{
y' = e^{x^{1/3}x}\cdot \frac{4}{3}x^{1/3}
}
$$

---
### **6. Quarto esempio: funzione composta con quoziente interno**

Consideriamo una funzione del tipo:

$$
y = \sin\left(\frac{p(x)}{q(x)}\right)
$$

Qui dobbiamo usare **due regole in sequenza**:

1. regola della catena
    
2. regola del quoziente

**Derivata della funzione esterna**

$$
\cos\left(\frac{p(x)}{q(x)}\right)
$$

**Derivata della funzione interna (quoziente)**

$$
\frac{p'(x)q(x) - p(x)q'(x)}{q(x)^2}
$$

**Risultato finale**

$$
\boxed{
y' =
\cos\left(\frac{p(x)}{q(x)}\right)
\cdot
\frac{p'(x)q(x) - p(x)q'(x)}{q(x)^2}
}
$$

---
### **7. Schema mentale da ricordare**

Quando incontri una funzione del tipo:

$$
f(x)^{g(x)}
$$

procedi sempre così:

1. **Riscrivi** come $e^{g(x)\ln f(x)}$

2. **Deriva l’esponenziale** (catena)
    
3. **Deriva l’esponente** (prodotto, quoziente, catena…)
    
4. **Sostituisci**, se vuoi, l’esponenziale con la funzione di partenza

Non è una nuova regola:

è **un uso intelligente delle regole che già conosci**.

---
### **8. Anticipazione**

👉 **Nel prossimo video** vedremo esempi di funzioni **non derivabili** e capiremo **perché** la derivata può non esistere in certi punti.