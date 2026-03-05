# **M1 UD1 Lezione 3 - Parabole**

### **1. Introduzione concettuale**

La **parabola** è il primo vero esempio di **oggetto non lineare** che incontri in Analisi.  
È il grafico di una **funzione quadratica**, quindi introduce concetti fondamentali che torneranno ovunque:

- **curvatura**
    
- **massimi e minimi**
    
- **studio del segno**
    
- **intersezioni**
    
- **ottimizzazione**
    
- **tangenti** (più avanti)
    

Dal punto di vista ingegneristico, la parabola è il **modello minimo di comportamento non lineare**.

---

## **2. Equazione generale della parabola**

Una parabola nel piano cartesiano ha equazione:

$$  
y = ax^2 + bx + c  
$$

con:

- $a,b,c \in \mathbb{R}$
    
- $a \neq 0$
    

È detta **funzione quadratica**.

---

### **2.1 Ruolo dei coefficienti**

- $a$ → **curvatura e concavità**
    
- $b$ → **inclinazione dell’asse di simmetria**
    
- $c$ → **intercetta sull’asse $y$**
    

In particolare:

- se $a>0$ → parabola **concava verso l’alto**
    
- se $a<0$ → parabola **concava verso il basso**
    

---

## **3. Concavità e comportamento globale**

Il segno di $a$ determina completamente il comportamento “globale”:

- $a>0$: la parabola ha un **minimo assoluto**
    
- $a<0$: la parabola ha un **massimo assoluto**
    

Questo è il primo esempio concreto di **estremo globale** di una funzione.

---

## **4. Vertice della parabola**

Il **vertice** è il punto in cui la parabola raggiunge il massimo o il minimo.

### **4.1 Coordinata $x$ del vertice**

Si ottiene con:

$$  
x_V = -\frac{b}{2a}  
$$

Questa formula è **fondamentale**: tornerà con le derivate.

---

### **4.2 Coordinata $y$ del vertice**

Sostituendo:

$$  
y_V = f(x_V) = a\left(-\frac{b}{2a}\right)^2 + b\left(-\frac{b}{2a}\right) + c  
$$

che si può scrivere come:

$$  
y_V = -\frac{b^2}{4a} + c  
$$

---

## **5. Forma canonica (o forma del vertice)**

Riscrivendo la funzione quadratica si ottiene:

$$  
y = a(x - x_V)^2 + y_V  
$$

cioè:

$$  
y = a\left(x + \frac{b}{2a}\right)^2 - \frac{b^2}{4a} + c  
$$

Questa forma è **molto più informativa** della forma generale perché:

- il vertice è immediato;
    
- la concavità è immediata;
    
- lo studio qualitativo è istantaneo.
    

---

## **6. Asse di simmetria**

La parabola è simmetrica rispetto alla retta verticale:

$$  
x = -\frac{b}{2a}  
$$

Questa è la **retta asse della parabola**.

Ogni punto a sinistra ha un punto simmetrico a destra con la stessa ordinata.

---

## **7. Intersezioni con gli assi**

### **7.1 Intersezione con l’asse $y$**

Si ottiene ponendo $x=0$:

$$  
y = c  
$$

Quindi il punto è:

$$  
(0,c)  
$$

---

### **7.2 Intersezioni con l’asse $x$ (zeri)**

Si risolve:

$$  
ax^2 + bx + c = 0  
$$

È l’equazione di secondo grado associata.

---

## **8. Discriminante e casi possibili**

Definiamo il **discriminante**:

$$  
\Delta = b^2 - 4ac  
$$

- $\Delta > 0$ → **due zeri reali distinti**
    
- $\Delta = 0$ → **uno zero reale doppio** (tangenza)
    
- $\Delta < 0$ → **nessuno zero reale**
    

Interpretazione geometrica:

- $\Delta > 0$ → la parabola **taglia** l’asse $x$
    
- $\Delta = 0$ → la parabola **tocca** l’asse $x$
    
- $\Delta < 0$ → la parabola **non incontra** l’asse $x$
    

---

## **9. Formula risolutiva**

Quando $\Delta \ge 0$:

$$  
x_{1,2} = \frac{-b \pm \sqrt{\Delta}}{2a}  
$$

Questi sono gli **zeri della funzione**.

---

## **10. Studio del segno della parabola**

Lo studio del segno dipende da:

- segno di $a$
    
- numero di zeri
    

Caso tipico con $\Delta>0$:

- se $a>0$:  
    la parabola è **positiva fuori** dagli zeri e **negativa tra** gli zeri
    
- se $a<0$:  
    la parabola è **negativa fuori** e **positiva tra** gli zeri
    

Questo schema è **fondamentale** per:

- disequazioni di secondo grado
    
- studio dei segni
    
- domini di funzioni razionali
    

---

## **11. Parabola come luogo geometrico**

Dal punto di vista geometrico classico, la parabola è:

> il luogo dei punti equidistanti da un punto fisso (fuoco) e da una retta fissa (direttrice).

Questa visione è meno usata in Analisi, ma è utile per:

- comprendere il significato geometrico della curvatura;
    
- applicazioni in ottica, antenne, riflessione.
    

---

## **12. Collegamento con le rette**

### **12.1 Intersezione retta–parabola**

Data una retta:

$$  
y = mx + q  
$$

e una parabola:

$$  
y = ax^2 + bx + c  
$$

il sistema diventa:

$$  
ax^2 + (b-m)x + (c-q) = 0  
$$

Il numero di intersezioni dipende dal discriminante.

Questo è il primo esempio concreto di:

- **sistema non lineare**
    
- **tangenza** (caso $\Delta=0$)
    

---

## **13. Interpretazione ingegneristica**

Le parabole descrivono:

- traiettorie (moto uniformemente accelerato);
    
- energia potenziale;
    
- costi quadratici;
    
- errori quadratici (least squares);
    
- profili di ottimizzazione.
    

Il vertice rappresenta:

- minimo di energia,
    
- minimo di costo,
    
- massimo rendimento.
    

---

## **14. Ponte verso l’Analisi vera**

Questa lezione prepara direttamente:

- lo **studio delle funzioni**
    
- il concetto di **derivata come pendenza**
    
- i **massimi e minimi**
    
- l’idea di **approssimazione locale**
    

Quando più avanti calcolerai:

$$  
f'(x) = 2ax + b  
$$

capirai che il vertice è il punto in cui la derivata si annulla.

---

## **15. Checklist finale (livello ingegneria)**

Devi saper fare:

1. Passare da forma generale a canonica
    
2. Calcolare vertice e asse
    
3. Determinare concavità
    
4. Calcolare zeri e discriminante
    
5. Studiare il segno
    
6. Capire intersezioni con rette
    
7. Interpretare il vertice come estremo globale