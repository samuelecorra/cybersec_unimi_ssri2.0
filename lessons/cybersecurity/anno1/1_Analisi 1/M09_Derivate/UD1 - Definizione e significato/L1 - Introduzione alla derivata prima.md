# **M9 UD1 Lezione 1 - Introduzione alla derivata prima**

### **0. I due problemi che generano la derivata**

Prima della geometria, un problema fisico. Un punto si muove lungo una retta e $s(t)$ è la sua posizione all'istante $t$. La **velocità media** tra $t_0$ e $t_0+h$ è

$$
v_m = \frac{s(t_0+h)-s(t_0)}{h}
$$

cioè "spazio percorso diviso tempo impiegato". Ma il tachimetro non mostra una media: mostra la **velocità istantanea**, l'oggetto a cui la media si avvicina quando l'intervallo di osservazione si stringe, $h\to 0$. Lo stesso schema — rapporto tra incremento dell'uscita e incremento dell'ingresso, poi limite — risolve anche il problema geometrico della **retta tangente**, ed è questa doppia natura (tasso istantaneo di variazione, pendenza locale) a rendere la derivata il concetto più usato di tutta l'Analisi.

---
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

> 📌 Glossario da tenere separato fin da subito: $h$ (o $\Delta x$) è l'**incremento della variabile**; $f(x_0+h)-f(x_0)$ (o $\Delta y$) è l'**incremento della funzione**; il loro quoziente è il **rapporto incrementale** (pendenza della **secante**); il suo limite per $h\to 0$, quando esiste finito, è la **derivata** (pendenza della **tangente**). Cinque oggetti diversi, spesso confusi negli scritti.

Esiste una seconda scrittura equivalente del rapporto incrementale: ponendo $x = x_0+h$ (cioè $h = x-x_0$; quando $h\to 0$ si ha $x\to x_0$ e viceversa),

$$
\frac{f(x_0+h)-f(x_0)}{h} = \frac{f(x)-f(x_0)}{x-x_0} \qquad (x\neq x_0)
$$

Le due forme sono intercambiabili: la prima ("con $h$") è comoda nei conti algebrici, la seconda ("con $x$") nei collegamenti con i limiti di M08. Il cambio di variabile che le collega è una traslazione, quindi legittimo per il teorema di composizione (M08).

<!-- TODO FIGURA:
Grafico di una funzione con punto P=(x_0, f(x_0)) fissato e tre secanti per P corrispondenti a h grande, h medio, h piccolo (punti Q1, Q2, Q3 che scivolano verso P), più la tangente in P come posizione limite. Etichette: h sull'asse x, incremento f(x_0+h)-f(x_0) sull'asse y per la prima secante, coefficienti m_sec che tendono a m_tan.
Obiettivo didattico: visualizzare la secante che ruota verso la tangente al tendere di h a zero e il rapporto incrementale come pendenza della secante.
-->


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
f'(x_0)
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
m = f'(x_0)
$$

Di conseguenza, l’equazione della retta tangente è:

$$
y - f(x_0) = f'(x_0)\,(x - x_0)
$$

---
### **8. Perché studiare le derivate**

Le derivate non servono solo a tracciare rette tangenti.

Conoscere l’andamento della derivata permette di:

- stabilire dove una funzione è **crescente**
    
- stabilire dove una funzione è **decrescente**
    
- individuare i **punti di massimo e di minimo**
    
- analizzare il comportamento locale della funzione

Tutti questi aspetti verranno sviluppati nelle prossime lezioni.

---
### **9. Uno sguardo avanti**

La definizione tramite limite è fondamentale dal punto di vista teorico, ma **non è sempre il metodo più pratico per calcolare le derivate**.

Impareremo presto che, conoscendo le derivate di alcune funzioni fondamentali e alcune regole di calcolo, sarà possibile derivare funzioni complesse in modo rapido ed efficiente.

> ✅ Mappa del modulo: la UD1 completa il fondamento (definizione rigorosa e derivate laterali in L2, tangente/normale e lettura fisica in L3, il teorema "derivabile $\Rightarrow$ continua" in L4, l'approssimazione lineare in L5); la UD2 dimostra le regole di calcolo; la UD3 completa il catalogo delle funzioni elementari; la UD4 studia i punti di non derivabilità e i raccordi; la UD5 sviluppa i teoremi fondamentali (Fermat, Rolle, Lagrange, Cauchy, Darboux) con le loro conseguenze; la UD6 applica tutto all'ottimizzazione e al calcolo numerico.
