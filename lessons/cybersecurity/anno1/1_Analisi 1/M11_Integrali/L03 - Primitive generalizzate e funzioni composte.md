## **Lezione 3: Primitive generalizzate e funzioni composte**

---
### **1. L’idea chiave: leggere la regola della catena al contrario**

Ricordiamo la **regola di derivazione delle funzioni composte**:

Se
$$

y = F(G(x))

$$
allora

$$

y’ = F’(G(x)) \cdot G’(x)

$$

Ora facciamo la mossa concettuale fondamentale del capitolo sugli integrali:

> **leggiamo questa formula al contrario**

Se vediamo dentro un integrale qualcosa della forma

$$

F’(G(x)) \cdot G’(x)

$$

allora una primitiva è semplicemente

$$

F(G(x))

$$

cioe:
$$

\int F’(G(x)) \cdot G’(x),dx = F(G(x)) + C

$$

Questa è **la formula madre** di tutte le primitive “per riconoscimento”.

---
### **2. Primo esempio: seno di una funzione**

Calcoliamo:

$$

\int 3x^2 \sin(x^3),dx

$$

Osservazione strutturale:

- funzione esterna: $\sin(,\cdot,)$
    
- funzione interna: $G(x)=x^3$
    
- derivata della funzione interna:

$$

G’(x)=3x^2

$$

Ed è **esattamente** quello che compare davanti.

Quindi siamo nel caso:
$$

\int F’(G(x)) \cdot G’(x),dx

$$

Una primitiva del seno è $-\cos$, quindi:

$$

\int 3x^2 \sin(x^3),dx = -\cos(x^3) + C

$$

---
### **3. Secondo esempio: esponenziale di una funzione**

Calcoliamo:
$$

\int e^{\sin x}\cos x,dx

$$

Struttura:

- funzione esterna: $e^{(\cdot)}$
    
- funzione interna: $G(x)=\sin x$
    
- derivata della funzione interna:  

$$

G’(x)=\cos x

$$

Ancora una volta: **c’è la derivata della funzione interna moltiplicata fuori**.

Poiche una primitiva di $e^x$ è $e^x$ stesso, otteniamo:

$$

\int e^{\sin x}\cos x,dx = e^{\sin x} + C

$$

---
### **4. Terzo esempio: potenza di una funzione (con aggiustamento)**

Calcoliamo:
$$

\int x (x^2 - 1)^{2014},dx

$$

Struttura:

- funzione interna:
$$

G(x)=x^2-1

$$
- funzione esterna: potenza $2014$
  
Derivata della funzione interna:

$$

G’(x)=2x

$$

Ma nell’integrale abbiamo solo $x$, non $2x$.

**Strategia standard**: moltiplico e divido per 2.

$$

\int x (x^2 - 1)^{2014},dx

= \frac{1}{2} \int 2x (x^2 - 1)^{2014},dx

$$

Ora riconosciamo la struttura giusta.

Una primitiva di $(\cdot)^{2014}$ è:

$$

\frac{(\cdot)^{2015}}{2015}

$$

Quindi:

$$

\frac{1}{2} \cdot \frac{(x^2-1)^{2015}}{2015} + C

= \frac{(x^2-1)^{2015}}{4030} + C

$$

---
### **5. Generalizzazione delle primitive elementari**

Tutte le primitive “da tabella” si **generalizzano automaticamente**.

Esempi chiave:
$$

\int \sin(f(x)) f’(x),dx = -\cos(f(x)) + C

$$
$$

\int e^{f(x)} f’(x),dx = e^{f(x)} + C

$$

$$

\int (f(x))^n f’(x),dx = \frac{(f(x))^{n+1}}{n+1} + C \quad (n \ne -1)

$$

$$

\int \frac{f’(x)}{f(x)},dx = \ln |f(x)| + C

$$

---
### **6. Esempio finale: integrale della cotangente**

Calcoliamo:
$$

\int \frac{\cos x}{\sin x},dx

$$

Rilettura strutturale:
$$

\frac{\cos x}{\sin x} = \frac{f’(x)}{f(x)}

\quad \text{con } f(x)=\sin x

$$

Quindi direttamente:

$$

\int \frac{\cos x}{\sin x},dx = \ln|\sin x| + C

$$

---
### **7. Due osservazioni fondamentali**

#### **Osservazione 1**

Non tutti gli integrali sono riconducibili a primitive elementari o generalizzate.

Esistono integrali che richiedono:

- integrazione per sostituzione
    
- integrazione per parti
    
- integrazione delle funzioni razionali
    
- altre tecniche

#### **Osservazione 2**

Tutti gli esempi di questa lezione **si potrebbero** risolvere con la sostituzione.

Ma:

> **riconoscere subito la primitiva generalizzata è molto più veloce**

È una questione di allenamento visivo.

---
### **8. Regola d’oro (da incidere nella testa)**

Quando vedi un integrale:

1. cerca **una funzione dentro un’altra**
    
2. controlla se **fuori compare la derivata della funzione interna**
    
3. se sì → **primitiva immediata**
    
4. se no → prova ad aggiustare con costanti

---
### **Cosa viene dopo**

Nella prossima lezione iniziamo le **tecniche di integrazione vere e proprie**, partendo dalla più importante:

## **integrazione per parti**
