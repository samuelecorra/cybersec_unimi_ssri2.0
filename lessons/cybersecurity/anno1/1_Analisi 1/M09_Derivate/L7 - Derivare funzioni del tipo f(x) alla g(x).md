## **Lezione 7: Derivare funzioni del tipo** $f(x)^{g(x)}$

### **Esponenziale–logaritmo come strategia universale**

### **1. Obiettivo della lezione**

In questa lezione impariamo a derivare funzioni in cui **sia la base sia l’esponente dipendono da** x, cioè funzioni del tipo:

$$

y = f(x)^{g(x)}

$$

Esempi tipici sono:

- $x^x$
    
- $(\sin x)^{\cos x}$

La regola delle potenze **non è applicabile**, perché funziona solo quando l’esponente è **costante**. Serve quindi una strategia diversa.

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

f(x)^{g(x)} = e^{,g(x)\ln f(x)}

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

y’ = e^{x\ln x}(\ln x + 1)

$$

Ricordando che $e^{x\ln x} = x^x$:

$$

\boxed{y’ = x^x(\ln x + 1)}

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

y’ = (\sin x)^{\cos x}

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

y’ = e^{x^{1/3}x} \cdot \frac{d}{dx}(x^{1/3}x)

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

y’ = e^{x^{1/3}x}\cdot \frac{4}{3}x^{1/3}

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

\frac{p’(x)q(x) - p(x)q’(x)}{q(x)^2}

$$

**Risultato finale**

$$

\boxed{

y’ =

\cos\left(\frac{p(x)}{q(x)}\right)

\cdot

\frac{p’(x)q(x) - p(x)q’(x)}{q(x)^2}

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