# Soluzione dell'esercitazione — 21 novembre 2025

Correzione dei tre esercizi assegnati per casa nell'[esercitazione del 17 novembre](Soluzione_Esercitazione_17_11_2025.md): la stessa famiglia di problemi — le **ricorrenze** — affrontata con i tre metodi risolutivi del corso, uno per esercizio.

> **Riferimenti di teoria:** [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M01 — Complessità per induzione](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L3_Complessita_per_induzione.md), [M01 — Efficienza e complessità](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L2_2_Efficienza_e_complessita.md).

---

### **1. I tre metodi a confronto**

| Metodo | Quando si usa | Che cosa produce |
|---|---|---|
| **Esperto** (Master Theorem) | ricorrenze $a\,T(n/b)+f(n)$ con sottoproblemi di uguale dimensione | risultato immediato, $\Theta$ esatto |
| **Sostituzione** | qualunque ricorrenza, purché si disponga di una **congettura** | conferma o smentita della congettura, con le costanti |
| **Albero di ricorsione** | quando serve capire **dove** si concentra il lavoro, o quando le dimensioni dei sottoproblemi differiscono | una congettura (poi verificabile per sostituzione) o direttamente il risultato |

> 💡 I tre metodi non sono alternativi ma complementari: l'albero di ricorsione **produce** la congettura che la sostituzione **dimostra**, e il metodo esperto è una scorciatoia che vale solo in una forma particolare — ma molto frequente — di ricorrenza.

---

### **2. Esercizio 1 — Metodo esperto: $T(n) = T(n/3) + 5n$**

#### **2.1. Traccia**

Risolvere con il metodo esperto

$$
T(n) = T\!\left(\frac{n}{3}\right) + 5n .
$$

#### **2.2. Identificazione dei parametri**

Confrontando con la forma canonica $T(n) = a\,T(n/b) + f(n)$:

$$
a = 1, \qquad b = 3, \qquad f(n) = 5n .
$$

Il coefficiente $a$ vale $1$ perché c'è **una sola** chiamata ricorsiva.

#### **2.3. Calcolo della funzione spartiacque**

$$
n^{\log_b a} = n^{\log_3 1} = n^{0} = 1 .
$$

Il passaggio che decide tutto è $\log_3 1 = 0$: il logaritmo di $1$ in qualsiasi base vale zero, perché $3^{0}=1$. La funzione spartiacque è quindi la **costante** $1$, e il confronto con $f(n)=5n$ è immediato.

#### **2.4. Verifica della prima condizione del caso 3**

Occorre che $f(n)$ superi polinomialmente la spartiacque:

$$
f(n) = 5n = 5n^{1} = \Omega\!\left(n^{0 + \varepsilon}\right) .
$$

La condizione è soddisfatta per qualunque $\varepsilon \le 1$; negli appunti si sceglie $\varepsilon = 0.5$, per cui $5n = \Omega\!\left(n^{0.5}\right)$, il che è evidentemente vero.

#### **2.5. Verifica della condizione di regolarità**

$$
a\,f\!\left(\frac{n}{b}\right) \le c\,f(n) \quad \text{per qualche } c < 1 .
$$

Sostituendo $a=1$, $b=3$, $f(n)=5n$:

$$
1 \cdot 5\cdot\frac{n}{3} \;\le\; c \cdot 5n .
$$

Semplificando il fattore $5n$ comune ai due membri:

$$
\frac{1}{3} \le c .
$$

Basta scegliere, ad esempio, $c = 1/3$, che è minore di $1$: entrambe le ipotesi del caso 3 sono verificate.

#### **2.6. Conclusione**

$$
\boxed{T(n) = \Theta\bigl(f(n)\bigr) = \Theta(n)}
$$

> 📌 La costante moltiplicativa $5$ è del tutto irrilevante: si semplifica nella condizione di regolarità e scompare nella notazione $\Theta$. È utile trascinarla nei calcoli solo per abitudine al rigore, non perché cambi il risultato.

> 💡 Lettura intuitiva: il lavoro alla radice è $5n$, al livello successivo $5n/3$, poi $5n/9$, e così via. La somma è una serie geometrica di ragione $1/3$, che converge a $5n \cdot \frac{1}{1-1/3} = \frac{15}{2}n = \Theta(n)$: il lavoro complessivo è solo una costante di volte quello della radice.

---

### **3. Esercizio 2 — Metodo di sostituzione: $T(n) = T(n/2) + \log n$**

#### **3.1. Traccia**

Verificare, con il metodo di sostituzione, l'ipotesi

$$
T(n) = T\!\left(\frac{n}{2}\right) + \log n \;\overset{?}{=}\; O\!\left((\log n)^{2}\right).
$$

#### **3.2. Impostazione dell'induzione**

**Congettura:** esiste una costante $c > 0$ tale che

$$
T(n) \le c\,(\log n)^{2} \qquad \text{per ogni } n \text{ sufficientemente grande.}
$$

**Ipotesi induttiva:** la congettura è assunta vera per il sottoproblema, cioè per $n/2$:

$$
T\!\left(\frac{n}{2}\right) \le c\left(\log \frac{n}{2}\right)^{2} .
$$

> ⚠️ Il metodo di sostituzione non "scopre" la soluzione: la **verifica**. Se la congettura fosse sbagliata, il passo induttivo non si chiuderebbe per nessuna scelta di $c$; questo è un segnale utile, ma non dice quale sia la forma corretta — per quella serve l'albero di ricorsione.

#### **3.3. Passo induttivo**

Si sostituisce l'ipotesi nella ricorrenza:

$$
T(n) = T\!\left(\frac{n}{2}\right) + \log n \;\le\; c\left(\log \frac{n}{2}\right)^{2} + \log n .
$$

Si sviluppa il logaritmo del quoziente, ricordando che $\log_2 2 = 1$:

$$
\log \frac{n}{2} = \log_2 n - \log_2 2 = \log n - 1 .
$$

Quindi:

$$
T(n) \;\le\; c\,(\log n - 1)^{2} + \log n .
$$

Si sviluppa il quadrato del binomio, $(x-1)^2 = x^2 - 2x + 1$ con $x = \log n$:

$$
T(n) \;\le\; c\left(\log^{2} n - 2\log n + 1\right) + \log n
= c\log^{2} n - 2c\log n + c + \log n .
$$

#### **3.4. Determinazione della costante**

Perché l'induzione si chiuda serve

$$
c\log^{2} n - 2c\log n + c + \log n \;\le\; c\log^{2} n .
$$

Elidendo $c\log^{2} n$ da entrambi i membri:

$$
-2c\log n + c + \log n \le 0 .
$$

Si raccoglie $\log n$:

$$
\log n\,(1 - 2c) + c \le 0
\quad\Longleftrightarrow\quad
(2c - 1)\log n - c \ge 0 .
$$

Per $n > 2$ si ha $\log n > 1$, dunque una condizione **sufficiente** è che la disuguaglianza valga già sostituendo $\log n$ con il suo valore minimo $1$:

$$
(2c-1)\cdot 1 - c \ge 0
\quad\Longleftrightarrow\quad
2c - 1 - c \ge 0
\quad\Longleftrightarrow\quad
c \ge 1 .
$$

Si sceglie ad esempio $c = 2$, che soddisfa la condizione con margine e permette di assorbire agevolmente il caso base.

$$
\boxed{T(n) \le 2\,(\log n)^{2} \;\Longrightarrow\; T(n) = O\!\left((\log n)^{2}\right)}
$$

#### **3.5. Perché la sostituzione di $\log n$ con $1$ è lecita**

Il coefficiente $(2c-1)$ è **positivo** non appena $c > 1/2$; moltiplicando un coefficiente positivo per una quantità $\log n \ge 1$ si ottiene un valore non inferiore a quello che si otterrebbe con $\log n = 1$. Formalmente:

$$
(2c-1)\log n - c \;\ge\; (2c-1)\cdot 1 - c = c - 1 \;\ge\; 0 \quad \text{per } c \ge 1 .
$$

Si è quindi dimostrata la disuguaglianza nel caso più sfavorevole, e a maggior ragione essa vale per tutti gli $n > 2$.

> 📌 Il limite trovato è **stretto**. Sviluppando la ricorrenza si ottiene infatti

$$
T(n) = \sum_{i=0}^{\log n} \log \frac{n}{2^{i}} = \sum_{i=0}^{\log n}(\log n - i) = \frac{\log n\,(\log n + 1)}{2} = \Theta\!\left(\log^{2} n\right),
$$

> quindi la congettura non solo si verifica ma coglie l'ordine esatto. Si osservi che questa ricorrenza **non** è risolvibile con il metodo esperto in nessun caso: la spartiacque è $n^{\log_2 1} = 1$ e $f(n) = \log n$ la supera, ma non **polinomialmente** — non esiste alcun $\varepsilon > 0$ con $\log n = \Omega(n^{\varepsilon})$. È il classico "buco" fra il caso 2 e il caso 3 del teorema.

---

### **4. Esercizio 3 — Albero di ricorsione: $T(n) = 3T(n/2) + n^{2}$**

#### **4.1. Traccia**

Risolvere con l'albero di ricorsione

$$
T(n) =
\begin{cases}
1 & \text{se } n = 1,\\[4pt]
3\,T\!\left(\dfrac{n}{2}\right) + n^{2} & \text{se } n > 1 .
\end{cases}
$$

#### **4.2. Costruzione dell'albero**

Ogni nodo si espande in **tre figli** di dimensione dimezzata, e paga localmente il quadrato della propria dimensione.

| Livello $i$ | Numero di nodi | Dimensione di ciascun nodo | Costo di ciascun nodo | Costo del livello |
|---:|---:|---|---|---|
| $0$ | $1$ | $n$ | $n^{2}$ | $n^{2} = \left(\dfrac{3}{4}\right)^{0} n^{2}$ |
| $1$ | $3$ | $\dfrac{n}{2}$ | $\left(\dfrac{n}{2}\right)^{2} = \dfrac{n^{2}}{4}$ | $3\cdot\dfrac{n^{2}}{4} = \left(\dfrac{3}{4}\right)^{1} n^{2}$ |
| $2$ | $9$ | $\dfrac{n}{4}$ | $\dfrac{n^{2}}{16}$ | $9\cdot\dfrac{n^{2}}{16} = \left(\dfrac{3}{4}\right)^{2} n^{2}$ |
| $\vdots$ | $\vdots$ | $\vdots$ | $\vdots$ | $\vdots$ |
| $i$ | $3^{i}$ | $\dfrac{n}{2^{i}}$ | $\dfrac{n^{2}}{4^{i}}$ | $\left(\dfrac{3}{4}\right)^{i} n^{2}$ |

Il rapporto $3/4$ nasce dal confronto fra due andamenti opposti: il numero di nodi si **triplica** a ogni livello, ma il costo di ciascuno si riduce a **un quarto** (perché la dimensione si dimezza e il costo è quadratico). Prevale la riduzione:

$$
\frac{3^{i}}{4^{i}} = \left(\frac{3}{4}\right)^{i} \longrightarrow 0 .
$$

#### **4.3. Altezza dell'albero**

La ricorsione si arresta quando la dimensione arriva al caso base $n=1$:

$$
\frac{n}{2^{i}} = 1 \iff n = 2^{i} \iff \log n = \log 2^{i} \iff i = \log n .
$$

L'albero ha quindi $\log n + 1$ livelli, numerati da $0$ a $\log n$.

#### **4.4. Costo delle foglie**

Le foglie si trovano al livello $\log n$ e sono

$$
3^{\log_2 n} = n^{\log_2 3} \approx n^{1.585}
$$

(si usa l'identità $a^{\log_b n} = n^{\log_b a}$). Ciascuna costa $T(1)=1$, per un totale di $\Theta\!\left(n^{\log_2 3}\right) \approx \Theta\!\left(n^{1.585}\right)$: un contributo **inferiore** a $n^{2}$, come si vedrà confermato dal risultato.

#### **4.5. Somma su tutti i livelli**

$$
T(n) = \sum_{i=0}^{\log n - 1} \left(\frac{3}{4}\right)^{i} n^{2} \;+\; \Theta\!\left(n^{\log_2 3}\right)
\;=\; n^{2} \sum_{i=0}^{\log n - 1} \left(\frac{3}{4}\right)^{i} \;+\; \Theta\!\left(n^{\log_2 3}\right).
$$

La sommatoria è una **serie geometrica finita** di ragione $q = 3/4$, per la quale vale

$$
\sum_{i=0}^{m-1} q^{i} = \frac{1 - q^{m}}{1 - q}.
$$

Con $m = \log n$ e $q = 3/4$:

$$
\sum_{i=0}^{\log n - 1} \left(\frac{3}{4}\right)^{i}
= \frac{1 - \left(\frac{3}{4}\right)^{\log n}}{1 - \frac{3}{4}}
= \frac{1 - \left(\frac{3}{4}\right)^{\log n}}{\frac{1}{4}}
= 4\left(1 - \left(\frac{3}{4}\right)^{\log n}\right).
$$

Quindi

$$
T(n) \;=\; 4n^{2}\left(1 - \left(\frac{3}{4}\right)^{\log n}\right) + \Theta\!\left(n^{\log_2 3}\right).
$$

#### **4.6. Semplificazione e risultato**

Il fattore $\left(\frac{3}{4}\right)^{\log n}$ **tende a zero** al crescere di $n$, dunque il fattore parentetico è compreso fra $0$ e $1$ e tende a $1$:

$$
0 \;<\; 1 - \left(\frac{3}{4}\right)^{\log n} \;<\; 1 .
$$

Ne segue $T(n) \le 4n^{2} + \Theta(n^{\log_2 3}) = O(n^{2})$; d'altra parte il solo costo della radice è $n^{2}$, quindi $T(n) = \Omega(n^{2})$:

$$
\boxed{T(n) = \Theta(n^{2})}
$$

> 💡 In modo ancora più diretto: $\left(\frac{3}{4}\right)^{\log_2 n} = n^{\log_2 (3/4)} = n^{\log_2 3 - 2} \approx n^{-0.415}$, che tende a zero come una potenza negativa di $n$. Il contributo dei livelli profondi svanisce e resta solo il lavoro della radice, moltiplicato per la costante $4$ della serie geometrica.

#### **4.7. Verifica con il metodo esperto**

L'esercizio chiedeva l'albero di ricorsione, ma il risultato si può controllare con il metodo esperto, che qui è applicabile:

$$
a = 3, \qquad b = 2, \qquad f(n) = n^{2}, \qquad n^{\log_2 3} \approx n^{1.585}.
$$

$$
f(n) = n^{2} = \Omega\!\left(n^{\log_2 3 + \varepsilon}\right) \quad \text{con } \varepsilon \approx 0.4 ,
$$

e la condizione di regolarità

$$
3\cdot\left(\frac{n}{2}\right)^{2} = \frac{3}{4}n^{2} \le c\,n^{2} \quad \text{con } c = \frac{3}{4} < 1
$$

è verificata. **Caso 3**, quindi $T(n)=\Theta(f(n))=\Theta(n^{2})$. ✅

> 📌 Non è un caso che la costante $c=3/4$ della condizione di regolarità coincida esattamente con la ragione della serie geometrica dell'albero di ricorsione: sono la stessa quantità vista da due angolazioni. La condizione di regolarità del caso 3 **è** la richiesta che i costi dei livelli decrescano geometricamente.

---

### **5. Sintesi**

| # | Ricorrenza | Metodo richiesto | Risultato | Costante chiave |
|---:|---|---|---|---|
| 1 | $T(n)=T(n/3)+5n$ | esperto | $\Theta(n)$ | $c = 1/3$ (regolarità) |
| 2 | $T(n)=T(n/2)+\log n$ | sostituzione | $\Theta(\log^{2} n)$ | $c \ge 1$, si prende $c=2$ |
| 3 | $T(n)=3T(n/2)+n^{2}$ | albero di ricorsione | $\Theta(n^{2})$ | ragione $3/4$ |

> ✅ **Il criterio della ragione.** In tutti e tre gli esercizi la risposta dipende dal confronto fra il costo di un livello e quello del successivo. Se il rapporto è una costante $q<1$ domina la **radice** e il costo è $\Theta(f(n))$ (esercizi 1 e 3); se è $q>1$ dominano le **foglie**; se i livelli costano tutti uguale ($q=1$) si guadagna un fattore logaritmico. L'esercizio 2 è il caso di frontiera in cui il rapporto tende a $1$ ma non è costante, e infatti richiede un metodo diverso dal teorema.
