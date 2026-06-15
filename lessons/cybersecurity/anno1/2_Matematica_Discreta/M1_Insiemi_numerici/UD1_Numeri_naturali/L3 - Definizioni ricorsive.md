# **M1 UD1 Lezione 3 - Definizioni ricorsive**

### **1. Che cosa è una definizione ricorsiva**

Una **definizione ricorsiva** (o **induttiva**) definisce un oggetto matematico in termini di sé stesso, applicato a valori più piccoli o più semplici. Ha sempre due componenti:

1. **Caso base**: definisce il valore dell'oggetto per il caso più semplice (di solito $n = 0$ o $n = 1$)
2. **Passo ricorsivo**: esprime il valore per $n$ in funzione del valore per valori precedenti

> 📌 Ogni definizione ricorsiva corrisponde strutturalmente a una prova per induzione: il caso base "avvia" la sequenza, il passo ricorsivo "propaga" la definizione a tutti i naturali.

---

### **2. La potenza**

La potenza $a^n$ (con $a \in \mathbb{N}$, $n \in \mathbb{N}$) si definisce ricorsivamente come:

$$\begin{cases} a^0 = 1 \\ a^n = a^{n-1} \cdot a \end{cases}$$

**Esempi:**
- $a^1 = a^0 \cdot a = 1 \cdot a = a$
- $a^2 = a^1 \cdot a = a \cdot a$
- $a^3 = a^2 \cdot a = a \cdot a \cdot a$

> 💡 La definizione ricorsiva della potenza è più precisa della definizione "informale" $a^n = a \cdot a \cdots a$ ($n$ volte), che non specifica il caso $n=0$ e lascia ambigua la notazione.

---

### **3. Il fattoriale**

#### **3.1. Origine: gli anagrammi**

Quanti sono gli anagrammi di una parola di $n$ lettere tutte distinte? Se ho $n$ posizioni da riempire:
- Per la prima posizione ho $n$ scelte
- Per la seconda ne ho $n-1$ (una lettera è già usata)
- ...
- Per l'ultima ho $1$ scelta

Il totale è $n \cdot (n-1) \cdots 2 \cdot 1$.

Questo porta alla definizione del **fattoriale**:

$$\begin{cases} 0! = 1 \\ n! = (n-1)! \cdot n \end{cases}$$

**Valori:**

| $n$ | $n!$ |
|-----|------|
| 0 | 1 |
| 1 | 1 |
| 2 | 2 |
| 3 | 6 |
| 4 | 24 |
| 5 | 120 |
| 6 | 720 |

> ⚠️ La scelta $0! = 1$ non è arbitraria: è l'unica definizione coerente con la formula combinatoria $\binom{n}{k} = \dfrac{n!}{k!(n-k)!}$ e con la stessa ricorrenza.

---

### **4. La successione di Fibonacci**

La **successione di Fibonacci** è una delle successioni ricorsive più celebri della matematica:

$$\begin{cases} a_1 = 1 \\ a_2 = 1 \\ a_n = a_{n-1} + a_{n-2} \quad \text{per } n \geq 3 \end{cases}$$

I primi valori sono: $1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, \ldots$

> 💡 La successione di Fibonacci appare in natura con sorprendente frequenza: spirali di conchiglie, disposizione dei semi di girasole, ramificazioni degli alberi. Questa è una definizione ricorsiva con **due** casi base ($a_1$ e $a_2$), perché il passo ricorsivo utilizza i due termini precedenti.

> ⚠️ Poiché il passo ricorsivo richiede i due valori precedenti, occorrono **due** casi base. Con un solo caso base la definizione sarebbe incompleta.

---

### **5. Il triangolo di Tartaglia e il coefficiente binomiale**

Il **triangolo di Tartaglia** (detto anche triangolo di Pascal) fornisce una definizione ricorsiva dei **coefficienti binomiali** $\binom{n}{k}$:

$$\begin{cases} \binom{n}{0} = 1 & \text{per ogni } n \geq 0 \\ \binom{n}{n} = 1 & \text{per ogni } n \geq 0 \\ \binom{n}{k} = \binom{n-1}{k-1} + \binom{n-1}{k} & \text{per } 0 < k < n \end{cases}$$

Il triangolo si costruisce riga per riga: ogni elemento è la somma dei due elementi direttamente sopra di lui.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

I coefficienti binomiali appaiono nell'**espansione del binomio di Newton**:

$$(a + b)^n = \sum_{k=0}^{n} \binom{n}{k} a^{n-k} b^k$$

> 📌 Il coefficiente $\binom{n}{k}$ conta il numero di modi per scegliere $k$ oggetti da $n$ senza considerare l'ordine. La formula esplicita è $\binom{n}{k} = \dfrac{n!}{k!(n-k)!}$.

---

### **6. Definizione ricorsiva del MCD**

Una definizione ricorsiva insolita, ma molto importante, è quella del **Massimo Comune Divisore**:

$$\begin{cases} \text{MCD}(a, 0) = a \\ \text{MCD}(a, b) = \text{MCD}(b,\, a \bmod b) \quad \text{per } b \neq 0 \end{cases}$$

Questo è esattamente l'**algoritmo euclideo**: ad ogni passo il secondo argomento diminuisce strettamente (poiché $a \bmod b < b$), quindi la ricorsione termina sempre.

> 💡 La definizione ricorsiva del MCD non è solo elegante — è anche un algoritmo efficiente. Verrà approfondita nella lezione dedicata al MCD e all'algoritmo euclideo.

---

> ✅ Le definizioni ricorsive specificano un oggetto tramite caso base e passo ricorsivo. Gli esempi principali in $\mathbb{N}$ sono: potenza ($a^0=1$, $a^n = a^{n-1} \cdot a$), fattoriale ($0!=1$, $n! = (n-1)! \cdot n$), Fibonacci (due casi base, passo $a_n = a_{n-1}+a_{n-2}$), triangolo di Tartaglia (regola di Pascal per i coefficienti binomiali) e MCD (base dell'algoritmo euclideo).
