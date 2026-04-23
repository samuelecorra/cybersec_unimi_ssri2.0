# **Lezione 3 – Complessità per induzione**

### **1. Excursus introduttivo – Il genio bambino di Gauss**

Prima di affrontare la dimostrazione formale per induzione, vale la pena ricordare un episodio che ha fatto la storia della matematica e, in un certo senso, dell’informatica stessa.

Il giovane **Carl Friedrich Gauss**, quando aveva appena **sette anni**, ricevette dal maestro un esercizio punitivo: sommare tutti i numeri da 1 a 100.  
Il compito sembrava lungo e noioso, ma Gauss, con uno sguardo che solo un futuro genio avrebbe avuto, capì subito il **pattern nascosto**.  
Notò che accoppiando il primo numero con l’ultimo, il secondo con il penultimo e così via, si ottenevano sempre coppie uguali:

$$
\begin{aligned}
1 + 100 &= 101 \\
2 + 99  &= 101 \\
3 + 98  &= 101
\end{aligned}
$$

C’erano 50 coppie, ognuna con somma 101.  
In pochi secondi, Gauss rispose al maestro:

$$
50 \times 101 = 5050
$$

L’episodio mostra perfettamente **la logica dell’induzione**: riconoscere una regola generale dietro una serie di casi particolari e dedurre che **se vale per un certo passo, allora vale anche per quello successivo**.  
Questa intuizione è la stessa che usiamo oggi quando dimostriamo la correttezza o la complessità di un algoritmo.

---

### **2. Introduzione e motivazioni**

L’obiettivo di questa lezione è **richiamare la tecnica della dimostrazione per induzione** e capire come applicarla per **stimare la complessità di un algoritmo**.  
L’induzione è una delle tecniche più potenti della matematica discreta: consente di dimostrare proprietà che valgono per **un’infinità di casi** a partire da un solo punto di partenza e da una regola di passaggio.

---

### **3. Struttura generale della dimostrazione per induzione**

Ogni dimostrazione per induzione segue due fasi principali:

1. **Base dell’induzione:**  
   si dimostra che la tesi è vera per un valore iniziale, tipicamente $n = 1$ o $n = 0$.
2. **Passo induttivo:**  
   si assume che la tesi sia vera per un certo valore generico $n$ (ipotesi induttiva), e si dimostra che allora deve essere vera anche per $n + 1$.

> In simboli:  
> Se vale per $n = k$ (base), e se da “vale per $n$” segue “vale per $n+1$”,  
> allora la tesi è vera per **tutti** gli $n \geq k$.

Si noti che spesso l'induzione viene denotata con la scrittura $n \Rightarrow n +1$, dove $n$ è il punto di partenza della base.

---

### **4. Esempio classico: la somma dei primi n numeri naturali**

Vogliamo dimostrare la formula:

$$
\sum_{i=1}^{n} i = \frac{n(n + 1)}{2}
$$

Questa è proprio la formula che Gauss aveva intuito da bambino.

#### **Base dell’induzione**

Per $n = 1$:

$$
\sum_{i=1}^{1} i = 1 = \frac{1(1 + 1)}{2} = 1
$$

✅ La tesi è verificata.

---

#### **Passo induttivo**

Assumiamo che la formula sia vera per $n - 1$, cioè andiamo a sostituire $n$ con $n-1$ nella formula di partenza, ottenendo quel che vediamo qui sotto:

$$
\sum_{i=1}^{n-1} i = \frac{(n - 1)n}{2}
$$

Questa formula la prendiamo per VALIDA, VERA!

Da questa, dovremo giungere all'originale con solo ragionamento algebrico e nulla più.

Procediamo:
Dimostriamo che allora vale anche per $n$: sommiamo l'n-esimo numero

$$
\sum_{i=1}^{n} i = \left( \sum_{i=1}^{n-1} i \right) + n
$$

Sostituiamo l’ipotesi induttiva con quel che abbiamo sviluppato poc'anzi

$$
\sum_{i=1}^{n} i = \frac{(n - 1)n}{2} + n
$$

Semplificando:

$$
\sum_{i=1}^{n} i = \frac{n^2 - n + 2n}{2} = \frac{n^2 + n}{2} = \frac{n(n + 1)}{2}
$$

Avendo ritrovato la formula originale, possiamo concludere che:
✅ Quindi la tesi è vera anche per $n$.

---

#### **Conclusione**

Poiché è verificata per $n = 1$ e, assumendo la verità per $n - 1$, risulta vera per $n$,  
possiamo concludere che la formula è valida per **ogni numero naturale n**.

ATTENZIONE: la scelta dei parametri in fase di ipotesi induttiva segue del tutto la convenienza della dimostrazione!

Potevamo anche scegliere di dimostrare la tesi per $n + 1$ a partire da $n$, ma in questo caso sarebbe stato più complicato, poiché avremmo dovuto riscrivere la formula in modo da esprimere $n + 1$ in funzione di $n$. Guardate per credere:

Assumiamo che la formula sia vera per $n$ (**Ipotesi Induttiva**):

$$
\sum_{i=1}^{n} i = \frac{n(n + 1)}{2}
$$

Dimostriamo che allora vale anche per $n + 1$. Dobbiamo quindi arrivare a ottenere:

$$
\frac{(n + 1)(n + 2)}{2}
$$

Partiamo sommando il termine $(n + 1)$ alla sommatoria precedente:

$$
\sum_{i=1}^{n+1} i = \left( \sum_{i=1}^{n} i \right) + (n + 1)
$$

Sostituiamo l’ipotesi induttiva:

$$
\sum_{i=1}^{n+1} i = \frac{n(n + 1)}{2} + (n + 1)
$$

Per semplificare, raccogliamo il fattore comune $(n + 1)$:

$$
\sum_{i=1}^{n+1} i = (n + 1) \left( \frac{n}{2} + 1 \right)
$$

Risolvendo la frazione in parentesi:

$$
\sum_{i=1}^{n+1} i = (n + 1) \left( \frac{n + 2}{2} \right) = \frac{(n + 1)(n + 2)}{2}
$$

✅ **La tesi è verificata per n + 1.**

Parametri shiftati di 1, ma il risultato è lo stesso: questo ci consente di generalizzare la dimostrazione per ogni $n$.

---

### **5. Applicazione dell’induzione alla complessità**

Ora utilizziamo lo stesso schema per dimostrare **l’ordine di grandezza** di una funzione che descrive la complessità di un algoritmo.

Vogliamo dimostrare che la sommatoria dei primi $n$ numeri elevati a una potenza $h$ è limitata superiormente da una funzione polinomiale di grado $h + 1$:

$$
\sum_{i=1}^{n} i^h = O(n^{h + 1})
$$

Si procede per induzione sul parametro $h$.

---

#### **Caso base: $h = 0$**

Sostituiamo:

$$
\sum_{i=1}^{n} i^0 = O(n^{0 + 1})
$$

$$
\sum_{i=1}^{n} 1 = O(n^{1})
$$

$$
\sum_{i=1}^{n} 1 = O(n)
$$

Ricordiamo che la sommatoria di una costante equivale al prodotto della costante per il numero di termini sommati:

$$
\sum_{i=1}^{n} c = c \cdot n
$$

e, nel caso in cui la costante valga $1$:

$$
\sum_{i=1}^{n} 1 = 1 \cdot n = n
$$

Da cui infine possiamo dedurre, confrontando i due lati destri:

$$
n = O(n)
$$

Tutto torna, ergo...
✅ La base è verificata.

---

#### **Passo induttivo: da $h$ a $h + 1$**

Assumiamo che la tesi sia vera per un certo $h$, cioè la formula valida di partenza è proprio:

$$
\sum_{i=1}^{n} i^h = O(n^{h + 1})
$$

e dimostriamo che allora vale anche per $h + 1$.

A fine dimostrazione dovremmo dunque giungere a questo punto:

$$
\sum_{i=1}^{n} i^{h + 1} = O(n^{(h + 1) + 1}) = O(n^{h + 2})
$$

Partiamo...
Osserviamo che per le proprietà delle potenze possiamo riscrivere comodamente il termine della sommatoria:

$$
\sum_{i=1}^{n} i^{h + 1} = \sum_{i=1}^{n} i \cdot i^h
$$

Poiché $i \leq n$ per ipotesi, possiamo stimare che la sommatoria originale sia minore di una ipotetica sommatoria in cui sostituiamo $i$ con $n$:

$$
\sum_{i=1}^{n} i^{h + 1} \leq \sum_{i=1}^{n} n \cdot i^h
$$

Ricordiamo che le costanti moltiplicative si possono estrarre: nel caso del termine a destra del minore o uguale, possiamo trasformarlo così:

$$\sum_{i=1}^{n} n \cdot i^h = n \sum_{i=1}^{n} i^h$$

Ergo lo facciamo subito:

$$
\sum_{i=1}^{n} i^{h + 1} \leq   n \sum_{i=1}^{n} i^h
$$

Perché? Sempre perché ci è congeniale. Infatti...
Ora, riprendendo l'ipotesi induttiva fatta poc'anzi, secondo la quale:

$$
\sum_{i=1}^{n} i^h = O(n^{h + 1})
$$

Possiamo sostituire l'O grande nella disequazione precedente al secondo membro:

$$
\sum_{i=1}^{n} i^{h + 1} \leq n \cdot O(n^{h + 1})
$$

Ora manca solo un passaggio: la $n$ si può portare dentro l’O grande, poiché $O(f(n))$ è chiuso rispetto alla moltiplicazione per costanti e per funzioni polinomiali (con "chiuso" intendiamo che moltiplicare un elemento di quell'insieme per $n$ produrrebbe un risultato che è ancora in $O(f(n))$), quindi stiamo reversando quel che abbiamo fatto prima, quando abbiamo estratto la $n$ dalla sommatoria:

$$
\sum_{i=1}^{n} i^{h + 1} \leq O(n^1 \cdot n^{h+1})
$$

Ora applichiamo le proprietà delle potenze e giungiamo a questo punto:

$$
\sum_{i=1}^{n} i^{h + 1} \leq O(n^{h+2})
$$

---

#### ✅ **Conclusione del passo induttivo**

Poiché la notazione $O()$ rappresenta già **un limite superiore**,  
la disuguaglianza $\leq$ diventa **uguaglianza asintotica**.

Infatti poiché $O(n^{h+2})$ **già significa** “una funzione che cresce al massimo come $n^{h+2}$”,  
scrivere “≤” è ridondante:  
il “≤” è già _implicito_ nella definizione stessa di “= O(...)”.

Quindi possiamo riscrivere in modo più pulito e corretto:

$$
\sum_{i=1}^{n} i^{h + 1} = O(n^{h + 2})
$$

👉 In altri termini, abbiamo appena dimostrato che **se la tesi vale per $h$, allora vale anche per $h + 1$**.

---

#### ✳️ **Conclusione finale dell’induzione**

Dal momento che:

1. il **caso base** ($h = 0$) è stato verificato, e
2. il **passo induttivo** è stato dimostrato,

possiamo concludere che **la proprietà vale per ogni intero non negativo $h$**.

---

### **6. Conclusione generale**

Abbiamo dimostrato, per induzione, che:

$$
\sum_{i=1}^{n} i^h = O(n^{h + 1})
$$

per ogni valore intero non negativo di $h$.  
Questo risultato è molto utile nell’analisi della complessità, perché mostra **come cresce una somma di termini polinomiali**.

---

### **7. Riflessione finale**

L’induzione matematica è il cuore del pensiero computazionale.  
In fondo, ogni algoritmo ricorsivo **è una forma di induzione travestita**: parte da un caso base e procede per passi successivi costruendo la soluzione generale.

E proprio come il giovane Gauss, anche noi impariamo che la vera intelligenza non sta nel calcolare velocemente, ma nel **riconoscere la struttura nascosta** che rende il calcolo inevitabile — e bellissimo.
