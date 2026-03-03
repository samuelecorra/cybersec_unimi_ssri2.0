

## **1. Introduzione**

Il **Selection Sort** è uno degli algoritmi di ordinamento più semplici da capire e implementare.  
Il suo principio si basa su un’idea intuitiva:

> ad ogni passo scelgo (select) l’elemento più piccolo dell’array non ordinato e lo metto nella posizione corretta.

L’obiettivo è riordinare un vettore di numeri (o elementi comparabili) in **ordine crescente**.  
L’algoritmo non è ricorsivo ed è un buon esempio per introdurre l’**analisi della complessità computazionale**.

---

## **2. Idea di fondo (razionale)**

Immagina di avere sul tavolo dieci carte con numeri mescolati.  
Per ordinarle, potresti comportarti così:

1. **Cerchi la carta con il numero più piccolo.**
    
2. La metti per prima nella fila ordinata.
    
3. Ripeti il procedimento con le carte rimanenti.
    

Questo è esattamente ciò che fa il Selection Sort, solo che invece delle carte abbiamo **posizioni in memoria**.

---

## **3. Funzionamento passo per passo**

Supponiamo di avere il seguente array di 10 elementi:

$$  
[\ 8,\ 6,\ 11,\ 17,\ 3,\ 15,\ 5,\ 19,\ 28,\ 12\ ]  
$$

### **Passo 1 – Ricerca del minimo**

- Partiamo dal primo elemento $a[0] = 8$.
    
- Confrontiamo 8 con tutti gli altri, da sinistra a destra:
    
    - 6 < 8 → nuovo minimo è 6
        
    - 11 > 6 → nulla cambia
        
    - 17 > 6 → nulla cambia
        
    - 3 < 6 → nuovo minimo è 3
        
    - 15, 5, 19, 28, 12 > 3 → nulla cambia
        
- Alla fine del ciclo, il valore minimo trovato è **3**, in posizione $a[4]$.
    

Scambiamo $a[0]$ con $a[4]$:

$$  
[3,6,11,17,8,15,5,19,28,12]  
$$

Ora la **prima posizione è già ordinata**.

---

### **Passo 2 – Seconda scansione**

- Partiamo da $a[1] = 6$ e cerchiamo il minimo tra gli elementi da $a[1]$ in poi.
    
- Confrontiamo 6 con 11, 17, 8, 15, 5, 19, 28, 12.
    
    - 5 < 6 → nuovo minimo è 5.
        
- A fine scansione, scambiamo 6 con 5:
    

$$  
[3,5,11,17,8,15,6,19,28,12]  
$$

Ora anche le prime **due posizioni sono ordinate.**

---

### **Passo 3 – Terza scansione**

- Partiamo da $a[2] = 11$.
    
- Cerchiamo il minimo tra $a[2] \ldots a[9]$.
    
    - 17 > 11
        
    - 8 < 11 → nuovo minimo
        
    - 15 > 8
        
    - 6 < 8 → nuovo minimo
        
    - 19, 28, 12 > 6 → nulla cambia
        
- Minimo = 6 (posizione $a[6]$).
    

Scambiamo $a[2]$ con $a[6]$:

$$  
[3,5,6,17,8,15,11,19,28,12]  
$$

E così via, fino a completare tutte le posizioni.

---

## **Implementazione e calcolo dell'efficienza come tempo di esecuzione**

![Pasted image 20251108151752](../../imgs/Pasted%20image%2020251108151752.png)

1) Si nota sin da subito che la variabile $n$ è un assegnamento esterno ai cicli ergo viene eseguito in tutto l'algoritmo una sola volta. Di conseguenza: $$T_1 = C_1 \ \cdot \ 1 = C_1$$
2) Il ciclo for come ormai dovrebbe essere assodato prevede che la variabile di ciclo sia inizializzata una volta sola, e quindi $$j = 1 \ ha \ costo \ C, costante$$
3) Dopodiché prevede le istruzioni di confronto e incremento, che sono ripetute più volte. Quante volte? Il ciclo va da **1 a n−1**, quindi i valori assunti da j sono:

```
1, 2, 3, ..., n−1
```

Quanti sono questi valori?
Per determinarlo, basti pensare al fatto che l’intervallo **inclusivo** tra due estremi interi $a$ e $b$ contiene **$(b - a) + 1$ elementi**, perché oltre alla distanza tra gli estremi ($b - a$) si conta anche **uno dei due estremi**.
Dal momento che i nostri due estremi sono $1$ ed $n-1$,
Formula dell’intervallo inclusivo:

`(n−1 − 1) + 1 = n−1`

Quindi le operazioni “ripetute” avvengono **n−1 volte**.

Ogni `for` o `while` **controlla la condizione una volta in più**:  
l’ultima volta la verifica fallisce e fa uscire dal ciclo.

👉 Quindi aggiungiamo un ulteriore **+1** per questo controllo finale, e poi semplifichiamo ove possibile:

```
(n−1) − 1 + 1 + 1 = n
```

Quindi in definitiva il primo ciclo costa: 

$$T_2 = C_2 \cdot n$$

4) L'assegnamento $smallest \leftarrow j$  ha un costo costante che va però moltiplicato per il numero di iterazioni in quanto è parte del corpo del primo ciclo:

$$ T_3 = C_3 \cdot (n-1-1+1) = C_3 \cdot (n-1)$$

5) Ora facciamo lo stesso identico ragionamento per il secondo ciclo, sorvoliamo tranquillamente il fatto che l'inizializzazione della variabile di ciclo sia un evento singolo di costo costante:

$$
\text{Costo ciclo interno} = 
(\text{estremo superiore} - \text{estremo inferiore}) + 1_{\text{(inclusivo)}} + 1_{\text{(uscita)}}
$$

$$
T_{\text{interno}}(j) = n - (j+1) + 1 + 1 = n - j -1 +1 + 1 = C_4 \cdot (n - j + 1)
$$

---

🔹 Ma il ciclo è _interno_ al ciclo esterno
Nel _Selection Sort_, il ciclo interno si esegue **una volta per ogni iterazione del ciclo esterno**, quindi per:

$$  
j = 1, 2, 3, \dots, n - 1  
$$

Quindi il **tempo totale** del ciclo interno lungo tutto l’algoritmo è la **somma** dei costi per ciascun valore di j:

$$  
T_{\text{interno}}(n) = \sum_{j=1}^{n-1} (n - j + 1)  
$$

---

Ogni iterazione del ciclo interno comporta un numero **costante di operazioni** (confronto, eventuale assegnazione, incremento, controllo condizione, ecc.).  
Chiamiamo questo costo medio **c** come al solito...

Quindi la somma reale è:

$$  
T_{\text{interno}}(n) = C_4 \sum_{j=1}^{n-1} (n - j + 1)  
$$

---
Quindi viene logico che quando:

$$
\begin{aligned}
j = 1 &\Rightarrow \text{Complessità} = n \\
j = 2 &\Rightarrow \text{Complessità} = n - 1 \\
j = 3 &\Rightarrow \text{Complessità} = n - 2
\end{aligned}
$$

Sviluppiamo la sommatoria (facoltativo per ora ma ripassare gli argomenti di Analisi Matematica se in difficoltà con i passaggi seguenti)

$$  
\sum_{j=1}^{n-1} (n - j + 1)  
= \sum_{j=1}^{n-1} (n + 1) - \sum_{j=1}^{n-1} j  
$$

Ora, ricordando che $\sum_{j=1}^{n-1} j = \frac{(n-1)n}{2}$ e $\sum_{j=1}^{n-1} (n+1) = (n-1)(n+1)$,  

otteniamo:

$$  
T_{\text{interno}}(n) = c \left[(n-1)(n+1) - \frac{n(n-1)}{2}\right]  
$$

---
Facendo un po’ di algebra:

$$  
T_{\text{interno}}(n) = C_4 \cdot \frac{n^2 - n}{2}  
$$

---
Quando $n$ cresce molto, il termine dominante è $n^2$, quindi:

$$  
T_{\text{interno}}(n) \in O(n^2)  
$$

---

Il confronto `if A[i] < A[smallest]` è interno al ciclo più interno, quindi per un singolo valore di `j` si esegue **(n − j − 1) + 1 = (n − j)** volte.  
Globalmente, poiché il ciclo esterno varia da **j = 1** a **j = n − 1**, il numero totale dei confronti è:

$$  
T_{\text{confronti}}(n) = C_5 \cdot\sum_{j=1}^{n-1} (n - j)  
= \frac{n(n - 1)}{2}  
= O(n^2)  
$$

---

L’assegnamento nel ramo `then` (`smallest ← i`) non ha un numero fisso di esecuzioni, ma dipende dall’istanza dell’input.  
Indichiamo con **t₍ⱼ₎** il numero di volte in cui, per un dato valore di `j`, la condizione dell’`if` risulta vera.

Nel **caso migliore**, con array già ordinato in senso crescente, la condizione `A[i] < A[smallest]` è sempre falsa ⇒ 

$$  
t_j = 0  
$$

Nel **caso peggiore**, con array ordinato in senso opposto (decrescente), la condizione è sempre vera ⇒  

$$  
t_j = n - j  
$$

Globalmente, come per l’`if`, l’assegnamento sarà incluso in una sommatoria sui valori di `j`:

$$  
T_{\text{then}}(n) = C_6 \cdot\sum_{j=1}^{n-1} t_j  
$$

Nel **worst case**:  

$$  
T_{\text{then}}(n) = \sum_{j=1}^{n-1} (n - j)  
= \frac{n(n - 1)}{2}  
= O(n^2)  
$$

Nel **best case**:  

$$  
T_{\text{then}}(n) = 0  
$$


---

L’istruzione di **exchange** viene eseguita solo all’interno del **ciclo esterno** e, assumendo che lo scambio sia **elementare** (cioè a costo costante, senza considerare la variabile temporanea necessaria in C), il suo **costo è costante**.

Poiché il ciclo esterno varia da **j = 1** a **j = n − 1**, il numero di volte in cui viene eseguito lo scambio è:

$$  
(n - 1) - 1 + 1 = n - 1  
$$

Quindi:

$$  
T_{\text{exchange}}(n) = C_7 \cdot (n - 1) = O(n)  
$$

---

## 🔹 1. Somma dei tempi parziali

Sappiamo che la complessità totale è data dalla somma di tutti i contributi:

$$  
T(n) = T_1 + T_2 + T_3 + T_4 + T_{\text{interno}}(n) + T_{\text{confronti}}(n) + T_{\text{then}}(n) + T_{\text{exchange}}(n)  
$$

Nel nostro schema, li possiamo ricondurre a:

$$  
T(n) = C_1 + C_2n + C_3(n-1) + C_4\sum_{j=1}^{n-1}(n-j+1) + C_5\sum_{j=1}^{n-1}(n-j) + C_6\sum_{j=1}^{n-1}t_j + C_7(n-1)  
$$

---

## 🔹 2. Sostituzione del termine $t_j$

Per non lasciare la complessità in funzione di (j), bisogna **sostituire $t_j$ con il suo valore nei diversi scenari:

- **Caso migliore** (array già ordinato): ($t_j$ = 0)
    
- **Caso peggiore** (array inverso): ($t_j$ = n - j)
    
- **Caso medio**: ($t_j \approx \frac{n - j}{2}$)
    

---

## 🔹 3. Espansione e semplificazione delle sommatorie

Ricordiamo che:

$$  
\sum_{j=1}^{n-1}(n-j) = \frac{n(n-1)}{2}  
$$

e

$$  
\sum_{j=1}^{n-1}(n-j+1) = \frac{n^2 - n}{2} + (n - 1)  
$$

Sostituendo e raggruppando tutti i termini, la funzione complessiva diventa (caso peggiore):

$$  
T(n) = C_1 + C_2n + C_3(n-1) + C_4\frac{n^2 - n}{2} + C_5\frac{n(n-1)}{2} + C_6\frac{n(n-1)}{2} + C_7(n-1)  
$$

---

## 🔹 4. Termine dominante

Tutti i termini con costanti o fattori lineari $(C_1, C_2n, C_3(n-1), C_7(n-1))$ sono **trascurabili** rispetto a quelli quadratici.  
I termini che dominano la crescita sono quelli in ($n^2$):

$$  
\frac{n^2}{2}(C_4 + C_5 + C_6)  
$$

---

## 🔹 5. Complessità asintotica

Pertanto, nel **caso peggiore**:

$$  
T_{\text{worst}}(n) \in O(n^2)  
$$

Nel **caso migliore** (nessun ramo `then` eseguito):

$$  
T_{\text{best}}(n) \in O(n^2)  
$$

👉 Anche se non avvengono scambi, i confronti vengono comunque fatti, quindi **la complessità resta quadratica**.

Nel **caso medio**:

$$  
T_{\text{avg}}(n) \in O(n^2)  
$$

---

## 🔹 6. Conclusione sintetica (come la prof)

> Sommando tutti i tempi parziali $(T_1 \dots T_7)$ e risolvendo le sommatorie, otteniamo una funzione complessiva $(T(n))$ con termine dominante in $(n^2)$.  
> Ne consegue che l’algoritmo **Selection Sort** ha **complessità temporale quadratica**:
> 
> $$  
 T(n) = \Theta(n^2)  
 $$
> 
> Sia nel caso migliore, medio e peggiore, poiché il numero dei confronti dipende solo dalla dimensione (n), non dalla disposizione iniziale dei dati.


---

## **1. Scopo della notazione asintotica**

Le notazioni **O**, **Ω** e **Θ** servono per descrivere **il comportamento di una funzione di costo $T(n)$** (cioè il tempo o lo spazio richiesto da un algoritmo) **al crescere dell’input $n$**, **trascurando le costanti e i termini meno rilevanti**.

> In pratica: ci dicono **quanto cresce $T(n)$ per $n$ grandi**, e **in che ordine di grandezza** lo fa.

---

## **2. Notazione O grande – limite superiore**

### **Definizione formale**

Una funzione $T(n)$ è in **O(g(n))** se esistono due costanti **positive** $c$ e $n_0$ tali che:

$$  
T(n) \leq c \cdot g(n) \quad \text{per ogni } n \geq n_0  
$$

### **Interpretazione**

- “O grande” fornisce **un limite superiore**:  
    indica che **$T(n)$ non cresce più velocemente** di $g(n)$ (a meno di una costante).
    
- Serve per indicare **il caso peggiore (worst case)**.
    

### **Esempio**

Se $T(n) = 3n^2 + 10n + 50$,  
allora $T(n) \in O(n^2)$, perché per $n$ abbastanza grandi:

$$  
3n^2 + 10n + 50 \leq c \cdot n^2  
$$

(ad esempio con $c=4$ e $n_0=10$).

➡️ **In sintesi:** $O(g(n))$ = _crescita al massimo come $g(n)$._

---

## **3. Notazione Ω grande – limite inferiore**

### **Definizione formale**

Una funzione $T(n)$ è in **Ω(g(n))** se esistono due costanti **positive** $c$ e $n_0$ tali che:

$$  
T(n) \geq c \cdot g(n) \quad \text{per ogni } n \geq n_0  
$$

### **Interpretazione**

- “Omega grande” fornisce **un limite inferiore**:  
    indica che **$T(n)$ non può crescere più lentamente** di $g(n)$ (a meno di una costante).
    
- Serve per indicare **il caso migliore (best case)** o una **stima minima**.
    

### **Esempio**

Se $T(n) = 3n^2 + 10n + 50$,  
allora $T(n) \in Ω(n^2)$, perché per $n$ grandi:

$$  
3n^2 + 10n + 50 \geq c \cdot n^2  
$$

(ad esempio con $c=3$ e $n_0=1$).

➡️ **In sintesi:** $Ω(g(n))$ = _crescita almeno come $g(n)$._

---

## **4. Notazione Θ grande – limite stretto (equivalenza asintotica)**

### **Definizione formale**

Una funzione $T(n)$ è in **Θ(g(n))** se esistono **due costanti positive** $c_1, c_2$ e un $n_0$ tali che:

$$  
c_1 \cdot g(n) \leq T(n) \leq c_2 \cdot g(n) \quad \text{per ogni } n \geq n_0  
$$

### **Interpretazione**

- “Theta grande” fornisce **un limite asintoticamente stretto**:  
    indica che $T(n)$ cresce **esattamente allo stesso ordine di grandezza** di $g(n)$.
    
- In altre parole, $T(n)$ è **sia O(g(n)) che Ω(g(n))**.
    

### **Esempio**

Per $T(n) = 3n^2 + 10n + 50$,  
possiamo dire che $T(n) \in Θ(n^2)$ perché:

$$  
c_1 n^2 \leq 3n^2 + 10n + 50 \leq c_2 n^2  
$$

per certe costanti $c_1, c_2$ e $n_0$ grandi abbastanza.

➡️ **In sintesi:** $Θ(g(n))$ = _crescita allo stesso ordine di grandezza di $g(n)$._

---

## **5. Riassunto comparativo**

|Notazione|Tipo di limite|Significato|Caso tipico|
|---|---|---|---|
|**O(g(n))**|Superiore|$T(n)$ cresce **al massimo come** $g(n)$|Caso peggiore|
|**Ω(g(n))**|Inferiore|$T(n)$ cresce **almeno come** $g(n)$|Caso migliore|
|**Θ(g(n))**|Stretto|$T(n)$ cresce **esattamente come** $g(n)$|Caso medio (o ordine reale)|

---

## **6. Relazioni tra le notazioni**

$$  
T(n) \in Θ(g(n)) \quad \Leftrightarrow \quad T(n) \in O(g(n)) \ \text{e} \ T(n) \in Ω(g(n))  
$$

Questo significa che:

- se conosci solo $O(g(n))$, hai un **limite superiore** (potrebbe essere un’approssimazione larga);
    
- se conosci anche $Ω(g(n))$, puoi restringere il comportamento esatto con $Θ(g(n))$.
    

---

## **7. Esempio visivo**

Immagina tre curve:

- $g(n)$ = linea guida
    
- $O(g(n))$ = funzione che **sta sotto o coincide** con una curva più ripida
    
- $Ω(g(n))$ = funzione che **sta sopra o coincide** con una curva più piatta
    
- $Θ(g(n))$ = funzione che **si “incolla” a $g(n)$** in crescita
    

---

## **8. Esempio intuitivo finale**

|Algoritmo|Tempo $T(n)$|O-grande|Ω-grande|Θ-grande|
|---|---|---|---|---|
|Ricerca lineare|$an + b$|$O(n)$|$Ω(n)$|$Θ(n)$|
|Ricerca binaria|$a \log n + b$|$O(\log n)$|$Ω(\log n)$|$Θ(\log n)$|
|Ordinamento naïve (Bubble sort)|$an^2 + bn + c$|$O(n^2)$|$Ω(n^2)$|$Θ(n^2)$|


---

## **Osservazione 1**

$$  
f(n) = a_k n^k + a_{k-1} n^{k-1} + \dots + a_1 n + a_0 = \Theta(n^k)  
$$

Per ogni **funzione polinomiale di grado $k$**, con **coefficiente dominante positivo** ($a_k > 0$), la crescita asintotica è determinata **solo dal termine di grado più alto**.

### **Spiegazione**

Quando $n$ diventa molto grande, i termini di grado inferiore ($n^{k-1}, n^{k-2}, …$) diventano trascurabili rispetto a $n^k$.  
Quindi, per esempio:

$$  
3n^5 + 2n^3 + n + 7 = \Theta(n^5)  
$$  

poiché il termine dominante è $3n^5$.

---

## **Distinzioni tra classi asintotiche**

- $Θ(n^k) \ne Θ(n^h)$ se $k \ne h$  
    → due polinomi di grado diverso **non crescono allo stesso ritmo**.
    
- $Θ(a^n) \ne Θ(b^n)$ se $a \ne b$  
    → due funzioni esponenziali con basi diverse **non sono dello stesso ordine di grandezza**.  
    Ad esempio, $2^n$ cresce molto più lentamente di $3^n$.
    
- $Θ(\log_a n) = Θ(\log_b n)$ per ogni $a, b$  
    → **tutti i logaritmi sono equivalenti asintoticamente**, poiché differiscono solo per una costante moltiplicativa:


$$  
\log_a n = \frac{\log_b n}{\log_b a}  
$$
    
- $Θ(n^k \log n) \ne Θ(n^k)$  
    → aggiungere un fattore $\log n$ **cambia l’ordine di crescita**: $n^k \log n$ cresce più rapidamente di $n^k$.
    

---

## **Catena di inclusioni tra classi asintotiche**

Per $h < k$ e $a < b$:

$$  
O(\log n) \subseteq O(n^h) \subseteq O(n^k) \subseteq O(n^k \log n) \subseteq O(a^n) \subseteq O(b^n)  
$$

### **Interpretazione intuitiva**

- Le funzioni **logaritmiche** crescono più lentamente di quelle **polinomiali**.
    
- Tra i polinomi, cresce di più quello con esponente maggiore.
    
- Una funzione **poli-logaritmica** ($n^k \log n$) è più grande di un semplice polinomio ma più piccola di un’esponenziale.
    
- Le **esponenziali** dominano tutte le funzioni polinomiali.  
    E tra esse, quella con **base più grande** cresce più velocemente.
    

---

## **In sintesi (ordine di crescita crescente)**

$$  
\log n \prec n^h \prec n^k \prec n^k \log n \prec a^n \prec b^n  
$$

cioè:

- logaritmo → lento
    
- polinomi → più veloci
    
- polinomio × logaritmo → ancora più veloce
    
- esponenziale → rapidissima crescita
    

---

## **Esempio pratico**

|Funzione|Tipo di crescita|Classe Θ|
|---|---|---|
|$\log n$|logaritmica|Θ(log n)|
|$n$|lineare|Θ(n)|
|$n^2$|quadratica|Θ(n²)|
|$n^2 \log n$|quasi cubica|Θ(n² log n)|
|$2^n$|esponenziale base 2|Θ(2ⁿ)|
|$3^n$|esponenziale base 3|Θ(3ⁿ)|
