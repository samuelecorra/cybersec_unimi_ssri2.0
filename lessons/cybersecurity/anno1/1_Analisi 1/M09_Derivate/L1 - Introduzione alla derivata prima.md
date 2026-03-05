## **Lezione 1: Introduzione alla derivata prima**

### **1. Il problema di partenza: la retta tangente**

Consideriamo una funzione reale di variabile reale

$$

y = f(x)

$$

definita su un certo intervallo dei numeri reali.

Fissiamo un punto del dominio, che indichiamo con $x_0$.

Il nostro obiettivo è **determinare l’equazione della retta tangente al grafico della funzione nel punto di ascissa $x_0$**.

Il punto di tangenza ha coordinate:

$$

(x_0\ , \ f(x_0)\ )

$$

Una qualunque retta passante per questo punto ha equazione:

$$

y - f(x_0) = m(x - x_0)

$$

dove $m$ è il **coefficiente angolare** della retta.

Tutto il problema si riduce quindi a questo punto cruciale:

> **come determinare il valore di $m$ conoscendo solo la funzione $f(x)$ e il punto $x_0$?**

---
### **2. Un problema più semplice: la retta secante**

Per avvicinarci al problema della tangente, introduciamo un secondo punto sul grafico della funzione.

Scegliamo un numero reale $h \neq 0$ e consideriamo il punto di ascissa:

$$

x_0 + h

$$

Poiché il punto appartiene al grafico della funzione, la sua ordinata sarà:

$$

f(x_0 + h)

$$

Abbiamo quindi due punti sul grafico:

$$

(x_0,\ f(x_0)\ ) \qquad (x_0 + h,\ f(x_0 + h)\ )

$$

La retta che passa per questi due punti è una **retta secante** al grafico di $f$.

---
### **3. Il coefficiente angolare della secante**

Il coefficiente angolare di una retta passante per due punti si calcola come rapporto tra la variazione delle ordinate e la variazione delle ascisse.

Applicando la formula otteniamo:

$$

m_{\text{sec}} =

\frac{f(x_0 + h) - f(x_0)}{(x_0 + h) - x_0}

$$

Semplificando il denominatore:

$$

m_{\text{sec}} =

\frac{f(x_0 + h) - f(x_0)}{h}

$$

Questa quantità prende il nome di **rapporto incrementale**.

È fondamentale che $h \neq 0$, altrimenti i due punti coinciderebbero e la secante non sarebbe definita.

---
### **4. Dalla secante alla tangente: l’idea chiave**

Il collegamento tra secante e tangente è concettualmente semplice ma potentissimo.

Se il secondo punto si avvicina sempre di più al primo, cioè se:

$$

h \to 0

$$

allora la retta secante **tende a coincidere con la retta tangente**.

Geometricamente:

- quando i due punti sono lontani → retta secante
    
- quando il secondo punto si avvicina al primo → la secante “ruota”
    
- nel limite → otteniamo la tangente

L’idea va quindi tradotta in linguaggio matematico usando un **limite**.

---
### **5. Definizione di derivata prima**

Il coefficiente angolare della retta tangente nel punto di ascissa $x_0$ è definito come:

$$

\lim_{h \to 0}

\frac{f(x_0 + h) - f(x_0)}{h}

$$

Se questo limite **esiste ed è finito**, allora:

- la funzione $f$ si dice **derivabile** nel punto $x_0$
    
- il valore del limite si chiama **derivata di $f$ in $x_0$**

---
### **6. Notazioni della derivata**

La derivata della funzione $f$ nel punto $x_0$ può essere indicata in diversi modi equivalenti:

$$

f’(x_0)

$$

$$

\frac{dy}{dx}\Big|_{x = x_0}

$$

$$

\left.\frac{df}{dx}\right|_{x = x_0}

$$

Tutte queste notazioni indicano **lo stesso numero**, cioè il coefficiente angolare della tangente nel punto considerato.

---
### **7. Risoluzione del problema iniziale**

A questo punto il problema di partenza è risolto.

Il coefficiente angolare della retta tangente al grafico di $y = f(x)$ nel punto $x_0$ è:

$$

m = f’(x_0)

$$

Di conseguenza, l’equazione della retta tangente è:

$$

y - f(x_0) = f’(x_0),(x - x_0)

$$

---
### **8. Perché studiare le derivate**

Le derivate non servono solo a tracciare rette tangenti.

Conoscere l’andamento della derivata permette di:

- stabilire dove una funzione è **crescente**
    
- stabilire dove una funzione è **decrescente**
    
- individuare i **punti di massimo e di minimo**
    
- analizzare il comportamento locale della funzione

Tutti questi aspetti verranno sviluppati nei prossimi video.

---
### **9. Uno sguardo avanti**

La definizione tramite limite è fondamentale dal punto di vista teorico, ma **non è sempre il metodo più pratico per calcolare le derivate**.

Impareremo presto che, conoscendo le derivate di alcune funzioni fondamentali e alcune regole di calcolo, sarà possibile derivare funzioni complesse in modo rapido ed efficiente.

👉 **Nella prossima lezione:** calcolo concreto delle derivate delle funzioni fondamentali.

---
