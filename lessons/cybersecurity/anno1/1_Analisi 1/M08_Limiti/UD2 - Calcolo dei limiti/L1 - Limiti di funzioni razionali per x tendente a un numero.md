## **Lezione 5: Limiti di funzioni razionali per** $x \to x_0$
### **1. Di che funzioni stiamo parlando**

In questa lezione studiamo i **limiti delle funzioni razionali**, cioè funzioni del tipo

$$f(x) = \frac{P(x)}{Q(x)}$$

dove **numeratore e denominatore sono polinomi**.

Ci concentriamo sul caso in cui la variabile x **tende a un numero finito** $x_0$.

---
### **2. L’idea generale: prima si prova a sostituire**

Quando dobbiamo calcolare un limite del tipo

$$
\lim_{x \to x_0} \frac{P(x)}{Q(x)}
$$
  
la **prima cosa da fare sempre** è tentare la **sostituzione diretta** di $x_0$.

A seconda di cosa succede a numeratore e denominatore, possono verificarsi **quattro casi distinti**.

---
### **CASO 1 – Numeratore ≠ 0 e denominatore ≠ 0**

### **3. Sostituzione regolare (caso più semplice)**

Se sostituendo $x_0$:

- il numeratore **non si annulla**
    
- il denominatore **non si annulla**

allora **il limite si calcola direttamente**.

Esempio:

$$
\lim_{x \to 2} \frac{x+1}{x-1}
$$
  
Sostituendo $x=2$:

- numeratore → 3
    
- denominatore → 1

quindi

$$
\lim_{x \to 2} \frac{x+1}{x-1} = 3
$$

Questo accade perché la funzione è **continua** in $x=2$.

👉 **Regola fondamentale**

Se il denominatore non si annulla, **hai già vinto**: il limite coincide con il valore della funzione.

---
### **CASO 2 – Numeratore = 0 e denominatore ≠ 0**
### **4. Il limite vale zero**

Se sostituendo $x_0$:

- il numeratore si annulla
    
- il denominatore **no**

il limite vale semplicemente **zero**.

Esempio:

$$
\lim_{x \to 1} \frac{x^2 - x}{x+2}
$$
  
Sostituendo $x=1$:

- numeratore → 0
    
- denominatore → 3

quindi

$$
\lim_{x \to 1} \frac{x^2 - x}{x+2} = 0
$$

Anche in questo caso la funzione è **continua** in $x_0$.

👉 **Conclusione parziale importantissima**

Se il denominatore **non si annulla**, il limite si calcola sempre per sostituzione.

---
### **CASO 3 – Denominatore = 0 e numeratore ≠ 0**
### **5. Qui la situazione cambia: infinito o non esiste**

Se sostituendo $x_0$:

- il denominatore si annulla
    
- il numeratore **no**

la sostituzione diretta fallisce: compare una divisione per zero.

Esempio:

$$
\lim_{x \to 2} \frac{x+1}{2-x}
$$
Sostituendo $x=2$:

- numeratore → 3
    
- denominatore → 0

Qui dobbiamo **separare il limite destro e sinistro**.

---
### **6. Limite destro**

Consideriamo valori **leggermente maggiori di 2**.

- il numeratore resta positivo
    
- il denominatore diventa una quantità **piccolissima e negativa**

Risultato: rapporto tra positivo e negativo → **meno infinito**

$$
\lim_{x \to 2^+} \frac{x+1}{2-x} = -\infty
$$

---

### **7. Limite sinistro**

Consideriamo valori **leggermente minori di 2**.

- il numeratore resta positivo
    
- il denominatore diventa una quantità **piccolissima e positiva**

Risultato: rapporto tra positivo e positivo → **più infinito**

$$
\lim_{x \to 2^-} \frac{x+1}{2-x} = +\infty
$$

---
### **8. Conclusione del caso 3**

Poiché limite destro e sinistro sono diversi,

$$
\lim_{x \to 2} \frac{x+1}{2-x} \text{ non esiste}
$$

In termini grafici questo significa che la retta $x=2$ è un **asintoto verticale**.

![](../imgs/Pasted%20image%2020251218133243.png)

---
### **9. Variante: denominatore al quadrato**

Se il denominatore è al quadrato, ad esempio:

$$
\frac{x+1}{(2-x)^2}
$$
allora:

- il denominatore tende a zero **ma resta sempre positivo**
    
- sia da destra che da sinistra  

Quindi:

$$
\lim_{x \to 2^-} = \lim_{x \to 2^+} = +\infty
$$

e il limite complessivo è  

$$
\lim_{x \to 2} \frac{x+1}{(2-x)^2} = +\infty
$$

Graficamente:

![](../imgs/Pasted%20image%2020251218133308.png)

---
### **CASO 4 – Numeratore = 0 e denominatore = 0 (forma indeterminata)**
### **10. Forma indeterminata** $\frac{0}{0}$

Questo è il caso più importante e più frequente.

Esempio:  

$$
\lim_{x \to 1} \frac{x^2 - 3x + 2}{x^2 - 1}
$$
Sostituendo x=1:

- numeratore → 0
    
- denominatore → 0

Abbiamo una **forma indeterminata** $0/0$.

---
### **11. Strategia: scomporre e semplificare**

Scomponiamo:

- numeratore
    
    $x^2 - 3x + 2 = (x-1)(x-2)$
    
- denominatore
    
    $x^2 - 1 = (x-1)(x+1)$

Il fattore $x-1$ è il **responsabile dell’annullamento**.  

Semplificando:

$$
\frac{(x-1)(x-2)}{(x-1)(x+1)} = \frac{x-2}{x+1}
$$
---
### **12. Ora il limite si può calcolare**

Calcoliamo il limite della funzione semplificata:  

$$
\lim_{x \to 1} \frac{x-2}{x+1}
$$
Sostituendo:

$$
\frac{1-2}{1+1} = -\frac{1}{2}
$$

---
### **13. Interpretazione grafica**

La funzione originale **non è definita in** $x=1$ (punto escluso dal dominio).

![](../imgs/Pasted%20image%2020251218133411.png)

Ma in prossimità di $x=1$, i valori della funzione **si avvicinano a** $-\frac{1}{2}$.

Graficamente:

- c’è un **buco**
    
- la funzione “passa vicino” a quota $-\frac{1}{2}$

---
## **14. Riassunto operativo finale**

Quando calcoli un limite di una funzione razionale per $x \to x_0$:

1. **Prova sempre la sostituzione**
    
2. Se il denominatore ≠ 0 → sostituzione diretta
    
3. Se il denominatore = 0 e il numeratore ≠ 0 → studia limite destro e sinistro
    
4. Se ottieni 0/0 → scomponi e semplifica    

---
