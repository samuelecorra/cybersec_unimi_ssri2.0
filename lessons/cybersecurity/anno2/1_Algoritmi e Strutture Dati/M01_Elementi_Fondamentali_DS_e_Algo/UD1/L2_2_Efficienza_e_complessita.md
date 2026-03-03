## **Lezione 2 – Efficienza e Complessità (parte 2)**

### **1. Introduzione: perché serve un nuovo linguaggio per l’efficienza**

Quando gli algoritmi diventano complessi, contare ogni singola operazione elementare diventa impossibile o inutile.  
Abbiamo bisogno di una misura più generale, che non calcoli _quanto tempo preciso_ impiega un algoritmo, ma _come cresce_ il tempo di esecuzione al crescere della dimensione dell’input $n$.

> In altre parole: non misuriamo il tempo in secondi, ma la **tendenza di crescita** del tempo.

Questa tendenza viene espressa con un linguaggio matematico chiamato **notazione asintotica**.  
Serve a capire chi “cresce più in fretta” tra due algoritmi, indipendentemente dal computer o dal linguaggio.

---

### **2. Le due notazioni fondamentali: $O(f(n))$ e $Ω(f(n))$**

#### **$O(f(n))$ — Ordine di grandezza superiore**

Si legge “O grande di f di n”.  
Indica che il tempo di calcolo $T(n)$ **non cresce più velocemente** di una funzione $f(n)$, a meno di una costante.

Formalmente:

$$  
T(n) \leq c \cdot f(n) \quad \text{per ogni } n \geq m  
$$

Dove $c$ e $m$ sono costanti positive.  
👉 O(f(n)) rappresenta un **limite superiore**.


> “A partire da un certo punto $m$ (cioè per tutti i valori di $n$ sufficientemente grandi), il tempo $T(n)$ non cresce più velocemente di $f(n)$, se moltiplichiamo $f(n)$ per una costante $c$.”

Quindi **$m$** rappresenta semplicemente il **valore di soglia** oltre il quale la disuguaglianza vale sempre. Ci serve poiché le funzioni possono avere **andamenti strani per i valori piccoli di n**.
Esempio:

$$  
T(n) = 5n^2 + 1000n + 40000  
$$

Per valori piccoli di $n$ (tipo $n=1$ o $n=2$), la parte “40000” domina.  
Ma per $n$ grandi, è chiaro che il termine $5n^2$ diventa quello dominante.

👉 Quindi, per dire che $T(n)$ è in $O(n^2)$, basta che **oltre una certa soglia $m$** (es. $m = 1000$), la disuguaglianza sia vera:  

$$  
T(n) \leq c \cdot n^2 \quad \text{per ogni } n \ge m  
$$

---

##### 🔹 In sintesi

|Simbolo|Significato|
|---|---|
|**$c$**|Costante che “scala” la funzione di confronto $f(n)$|
|**$m$**|Punto di partenza (soglia) oltre il quale la disuguaglianza vale|
|**$f(n)$**|Funzione di riferimento (es. $n$, $n^2$, $n \log n$)|
|**$T(n)$**|Tempo reale dell’algoritmo|

---

##### 💡 Esempio pratico

Supponiamo che l’algoritmo abbia un tempo di esecuzione descritto da:

$$  
T(n) = 5n^2 + 20n + 100  
$$

e vogliamo capire se $T(n)$ appartiene a $O(n^2)$.

---

###### 1️⃣ La definizione da rispettare

Per dire che $T(n)$ è in $O(n^2)$, devo **trovare due costanti positive** $c$ e $m$ tali che:

$$  
T(n) \le c \cdot n^2 \quad \text{per ogni } n \ge m  
$$

---

###### 2️⃣ Scegliamo delle costanti reali

Proviamo con:

- $c = 10$
    
- $m = 10$
    

Cioè voglio verificare se, **a partire da n = 10**, vale:

$$  
5n^2 + 20n + 100 \le 10n^2  
$$

---

###### 3️⃣ Controlliamo la disuguaglianza

Per $n = 10$:

- LHS (sinistra) = $5(10)^2 + 20(10) + 100 = 500 + 200 + 100 = 800$
    
- RHS (destra) = $10(10)^2 = 1000$
    

👉 $800 \le 1000$ ✅

Per $n = 11$:

- LHS = $5(11)^2 + 20(11) + 100 = 605 + 220 + 100 = 925$
    
- RHS = $10(11)^2 = 1210$
    

👉 $925 \le 1210$ ✅

E così via: per valori sempre più grandi di $n$, la parte $5n^2$ domina, e la disuguaglianza resta vera.

---

###### 4️⃣ Cosa significa

Vuol dire che:

- per $n$ **più piccoli di 10**, magari l’inequazione non vale;
    
- ma **da $n = 10$ in poi**, il comportamento di $T(n)$ è “sotto” a una costante volta $n^2$ (cioè $10n^2$).
    

Quindi formalmente possiamo scrivere:

$$  
T(n) = O(n^2) \quad \text{con } c = 10,, m = 10  
$$

---

###### 🧠 Tradotto in parole povere:

> “Da quando $n$ diventa più grande di 10, il tempo del mio algoritmo cresce **non più velocemente** di 10 volte $n^2$.”


---

##### ⚠️ Ora proviamo un caso “sbagliato”

Supponiamo di scegliere:

- $c = 6$
    
- $m = 1$
    

Quindi vogliamo verificare:

$$  
5n^2 + 20n + 100 \le 6n^2 \quad \text{per ogni } n \ge 1  
$$

---

###### 🔹 Passo 1: Calcoliamo per $n = 1$

LHS = $5(1)^2 + 20(1) + 100 = 125$  
RHS = $6(1)^2 = 6$

❌ $125 \le 6$ → **falso**

---

###### 🔹 Passo 2: Calcoliamo per $n = 5$

LHS = $5(5)^2 + 20(5) + 100 = 125 + 100 + 100 = 325$  
RHS = $6(5)^2 = 150$

❌ $325 \le 150$ → **falso**

---

###### 🔹 Passo 3: Calcoliamo per $n = 10$

LHS = $5(10)^2 + 20(10) + 100 = 800$  
RHS = $6(10)^2 = 600$

❌ $800 \le 600$ → **ancora falso**

---

###### 🔹 Passo 4: Calcoliamo per $n = 50$

LHS = $5(50)^2 + 20(50) + 100 = 12500 + 1000 + 100 = 13600$  
RHS = $6(50)^2 = 15000$

✅ $13600 \le 15000$ → **finalmente vero!**

---

###### 🔍 Cosa osserviamo?

- Con $c = 6$, la disuguaglianza _non vale_ per $n$ piccoli (1, 5, 10)
    
- Ma _comincia a valere solo da un certo punto in poi_ (in questo caso, circa da $n = 40–50$ in avanti)
    

---

#### 🧩 Conclusione

La definizione formale di $O(f(n))$ richiede **che esistano delle costanti $c$ e $m$** tali che:  

$$  
T(n) \le c \cdot f(n) \quad \text{per ogni } n \ge m  
$$

- Qui **$c=6$** funziona _solo se scegli m abbastanza grande_ (tipo $m=50$).
    
- Se imponi $m=1$, fallisce miseramente ❌
    
- Quindi, nella definizione, **$m$ serve proprio a ignorare i valori piccoli** di $n$ dove la disuguaglianza non regge ancora.
    

---

#### 🧠 In parole povere:

> “m è il punto da cui in poi l’andamento si stabilizza.”  
> “c è il fattore che mi serve per _coprire_ la funzione $T(n)$ con $f(n)$ moltiplicata per una costante.”


---

#### **$Ω(f(n))$ — Ordine di grandezza inferiore**

Si legge “omega di f di n”.  
Indica che $T(n)$ **non cresce più lentamente** di $f(n)$, a meno di una costante.

$$  
T(n) \geq c \cdot f(n) \quad \text{per ogni } n \geq m  
$$

👉 $Ω(f(n))$ rappresenta un **limite inferiore**. Non sto qui a rispiegare le due costanti... valgono considerazioni del tutto analoghe

---

#### **Quando coincidono**

Se $T(n)$ è sia $O(f(n))$ che $Ω(f(n))$, diciamo che:

$$  
T(n) \in Θ(f(n))  
$$

Il simbolo **Θ (Theta)** indica che $T(n)$ e $f(n)$ crescono _alla stessa velocità asintotica_.

---

#### **Intuizione visiva**

Immagina che $T(n)$ sia un corridore.

- $O(f(n))$ è il **tetto massimo**: non può andare più veloce.
    
- $Ω(f(n))$ è il **pavimento minimo**: non può andare più lento.
    
- $Θ(f(n))$ è la **corsia ideale** in cui corre realmente.
    

---

### **3. Operazioni elementari e ordine di grandezza**

Ogni istruzione elementare (come somma, confronto o assegnamento) costa **$O(1)$**, cioè tempo costante.  
Quando però le istruzioni si ripetono in cicli o chiamate ricorsive, il tempo cresce in funzione di $n$.

|Esempio|Complessità|
|---|---|
|Operazione singola|O(1)|
|Ciclo su n elementi|O(n)|
|Doppio ciclo annidato|O(n²)|
|Triplo ciclo annidato|O(n³)|
|Ricorsione che dimezza i dati ogni volta|O(log n)|

---

### **4. Esempi pratici di analisi del codice**

#### **Esempio 1 – Istruzione condizionale**

L’esempio rappresenta un **blocco condizionale `if–else`** in cui possono esserci **operazioni diverse**a seconda che la condizione sia vera o falsa.
In analisi della complessità, noi vogliamo capire **quanto tempo impiega nel caso peggiore** l’intero blocco.

---
##### 🔹 **Spiegazione dei simboli**

|Simbolo|Cosa rappresenta|Esempio intuitivo|
|---|---|---|
|**$f(n)$**|Il **tempo per valutare la condizione** dell’`if`|Controllare se un elemento è maggiore di 0 → $O(1)$; cercare un valore in un array → $O(n)$|
|**$g(n)$**|Il **tempo del blocco “if”** (cioè se la condizione è vera)|Eseguire un ciclo che scorre un array → $O(n)$|
|**$h(n)$**|Il **tempo del blocco “else”** (cioè se la condizione è falsa)|Fare solo una stampa a video → $O(1)$|

---

##### **🔹 La logica della formula

Quando si calcola la complessità totale di un costrutto `if–else`, bisogna considerare:

1. Il tempo per **valutare la condizione** → $O(f(n))$
    
2. Il tempo del **ramo eseguito** → può essere $O(g(n))$ oppure $O(h(n))$
    

Ma poiché in analisi asintotica ci interessa il **caso peggiore**, si prende **il massimo** tra tutti i possibili tempi.

Ecco perché la formula generale è:

$$  
O( \ \max \ \{ \ f(n), g(n), h(n) \ \} \ )  
$$

---

##### **🔹 Esempio concreto**

Supponiamo:

```c
if ( cercaElemento(array, n) ) {  // cercaElemento scansiona l'array
    stampaTutti(array, n);        // stampa tutti gli elementi
} else {
    printf("Vuoto");              // stampa una sola parola
}
```

- La condizione `cercaElemento` scorre l’array ⇒ **f(n) = O(n)**
    
- Il ramo `if` stampa n elementi ⇒ **g(n) = O(n)**
    
- Il ramo `else` fa solo una stampa ⇒ **h(n) = O(1)**
    

👉 Quindi:

$$  
O(\max{n, n, 1}) = O(n)  
$$

La complessità totale è $O(n)$, cioè _lineare_, perché il costo maggiore è quello dei cicli sull’array.

---

##### **🔹 Altro esempio (più estremo)**

```c
if ( cercaBinaria(array, n) ) { // O(log n)
    algoritmoQuadratico(array, n); // O(n^2)
} else {
    algoritmoLineare(array, n); // O(n)
}
```

→ Qui:

- $f(n) = O(\log n)$
    
- $g(n) = O(n^2)$
    
- $h(n) = O(n)$
    

Allora:

$$  
O(\max{\log n, n^2, n}) = O(n^2)  
$$

👉 Il caso peggiore è il ramo quadratico: domina tutto.

---

##### **🧠 In sintesi**

|Simbolo|Significato intuitivo|Esempio tipico|
|---|---|---|
|$f(n)$|Tempo per verificare la condizione|Ricerca o confronto|
|$g(n)$|Tempo del ramo “if” (condizione vera)|Parte più costosa possibile|
|$h(n)$|Tempo del ramo “else” (condizione falsa)|Parte alternativa|

E il risultato è sempre: 

$$  
O(\max{f(n), g(n), h(n)})  
$$

perché in analisi della complessità **conta solo il ramo più costoso**.

---

#### **Esempio 2 – Un ciclo semplice e un nested for

```c
for (i = 0; i < n; i++) 
    operazioni per O(f(n));

for (j = 0; j < n; j++) {
    for (k = 0; k < m; k++) {
        operazioni per O(g(m));
    }    
}
```

Per entrambi i cicli, la complessità è data dal massimo tra le operazioni di ciascun ciclo.

Prendiamo la complessità

$$  
O( \ n \cdot f(n) \ )  
$$

Supponiamo che $f(n)$ sia lineare. Sotto questa ipotesi, sviluppiamo:

$$
O( \ n^2 \ )  
$$

Reiteriamo lo stesso ragionamento per il nested loop:

$$  
O( \ n \cdot m \cdot g(m) \ )  
$$

Supponendo che $g(m)$ sia costante e che $m$ sia dell'ordine di $n$. Possiamo sostituire dunque $m$ con $n$ e$g(m)$ con 1:

$$  
O( \ n \cdot n \cdot 1 \ )  
$$

Da cui finalmente:

$$  
O( \ n^2 \ )  
$$

Sotto le suddette condizioni, ci accorgiamo che entrambi i cicli hanno il medesimo ordine di complessità. Formalmente:

Consideriamo il caso peggiore complessivo: un programma che contiene **entrambi i cicli** (uno dopo l’altro) avrà come costo totale:

$$O( \ \max \ \{ \ n \cdot f(n), \ n \cdot m \cdot g(m) \ \} \ )$$

👉 perché, quando analizziamo **più blocchi consecutivi**, in asintotica conta **il più costoso** (quello col grado di crescita maggiore).

Avendo prima supposto:

- $f(n)$ sia lineare → $f(n) = O(n)$
    
- $g(m)$ sia costante → $g(m) = O(1)$
    
- $m$ sia “dell’ordine di n” → $m \sim n$
    

Sostituendo:  
$$O( \ \max \ \{ \ n^2,  \ n^2 \ \} \ )$$

Poiché $\max{n^2, n^2} = n^2$, 

$$  
O(n^2)  
$$

✅ Quindi sì, **la conclusione è perfettamente corretta**:

> Sotto queste ipotesi, entrambi i cicli hanno la **stessa complessità asintotica quadratica** $O(n^2)$.

---

### **5. La complessità di un algoritmo**

Definizione generale:

> La **complessità di un algoritmo** è l’ordine di grandezza $O(T(n))$ del numero di operazioni elementari eseguite nel **caso pessimo**, in funzione della dimensione $n$ dei dati di ingresso.

---

#### **Classificazione**

- **Algoritmo efficiente** → complessità **polinomiale**, cioè $O(n^c)$ con $c$ costante positiva.  
    Esempi: $O(n)$, $O(n^2)$, $O(n^3)$
    
- **Algoritmo inefficiente** → complessità **superpolinomiale**, cioè $O(c^n)$ o peggiore.  
    Esempi: $O(2^n)$, $O(n!)$
    

---

### **6. Esempio – L’ordinamento di n valori**

#### **Algoritmo superpolinomiale**

Generare tutte le possibili permutazioni dei $n$ valori e verificare quale sia ordinata.

- Numero di permutazioni: $n!$
    
- Verifica di ordinamento: $O(n)$
    

Complessità totale:

$$  
O(n \cdot n!)  
$$

➡️ Inaccettabile anche per input medi: cresce troppo rapidamente.

---

#### **Algoritmo polinomiale (Selection Sort)**

1. Trova il minimo tra $n$ elementi e mettilo in prima posizione;
    
2. Trova il minimo tra i rimanenti $n-1$ elementi non ordinati e mettilo in seconda posizione;
    
3. Trova il minimo tra i rimanenti $n-2$ elementi non ordinati e mettilo in terza posizione;
4. E così via fino all'esaurimento degli elementi...

Numero totale di confronti??

- Alla **1ª posizione** bisogna trovare il minimo tra **n** elementi ⇒ servono **$n−1$** confronti (confrontiamo il “minimo corrente” con ciascuno degli altri).
- Alla **2ª posizione** restano **$n−1$** elementi ⇒ **$n−2$** confronti.
- Alla **3ª posizione** restano **$n−2$** elementi ⇒ **$n−3$** confronti.
- …
- All’ultima posizione utile restano **2** elementi ⇒ **1** confronto.

> Nota: qui assumiamo l’implementazione classica che **scansiona interamente** il sottoarray rimanente per trovare il minimo (niente “early exit”).

---

##### **Somma aritmetica “alla Gauss” (trucco di pairing)**

Scriviamo la somma due volte, una in ordine crescente e una in ordine decrescente:

$$  
\begin{aligned}  
S &= 1 + 2 + 3 + \dots + (n-2) + (n-1) \\\\ 
S &= (n-1) + (n-2) + \dots + 3 + 2 + 1  
\end{aligned}  
$$

Se sommi membro a membro, ogni colonna fa **n**:  

$$  
2S = n + n + \dots + n \quad \text{(ripetuto (n-1) volte)} = n(n-1).  
$$  

Quindi  

$$
S=\frac{n(n-1)}{2}.  
$$

Osserva che qui (S) è la somma da **1** a **(n-1)**; è la formula standard delle progressioni aritmetiche:  

$$  
1+2+\dots+k=\frac{k(k+1)}{2} \quad \text{con } k=n-1.  
$$

---

##### **Mini-check con un esempio**

Per (n=5):

- 1ª posizione: (4) confronti
    
- 2ª posizione: (3)
    
- 3ª posizione: (2)
    
- 4ª posizione: (1)
    

Totale: (4+3+2+1=10) e infatti 

$$  
\frac{5\cdot 4}{2}=10.  
$$

---

##### **Morale per la complessità**

Il termine dominante è proporzionale a $n^2$:

$$  
\frac{n(n-1)}{2}=\frac{n^2-n}{2}=\Theta(n^2) \ \Rightarrow \ O(n^2).  
$$

(Per completezza: gli **scambi** sono al più $n-1$, quindi $(O(n))$; il costo lo fanno i **confronti**.)

$$  
(n - 1) + (n - 2) + \dots + 1 = \frac{n(n - 1)}{2}  
$$

Complessità:

$$  
O(n^2)  
$$

➡️ Molto più gestibile, anche se non ottimale per grandi $n$.

---

### **7. Complessità di un problema e algoritmi ottimi**

#### **Complessità di un problema**

Ogni problema ha una **complessità intrinseca**, cioè una soglia minima di operazioni che _qualunque_
algoritmo dovrà compiere per risolverlo.

#### **Algoritmo ottimo**

Un algoritmo è **ottimo** se:

- esiste una limitazione inferiore $Ω(f(n))$ dimostrata per il problema,
    
- e l’algoritmo lo risolve in $O(f(n))$.
    

Allora $O(f(n))$ è il miglior tempo possibile: non esiste algoritmo asintoticamente più veloce.

---

### **8. Come si trovano le limitazioni inferiori**

#### **a) Dimensione dei dati**

Se un problema richiede di esaminare tutti i dati, la complessità inferiore è almeno $Ω(n)$.

Esempio:  
Trovare il minimo in un array di n elementi → bisogna guardarli tutti → $Ω(n)$.

➡️ Gli algoritmi del minimo sono **ottimali**.

---

#### **b) Eventi contabili**

Se un problema richiede di ripetere un certo evento (es. un confronto) almeno n volte, la complessità inferiore è $Ω(n)$.

Esempio:  
Per trovare il minimo tra n valori servono almeno n−1 confronti → $Ω(n)$.

➡️ Anche qui, gli algoritmi del minimo raggiungono il limite inferiore → **ottimali**.

---

### **9. Osservazioni e limiti dei metodi**

Gli strumenti “dimensione dei dati” ed “eventi contabili” sono utili ma **limitati**.  
Non sempre bastano per problemi più astratti o strutturati (grafi, alberi, linguaggi formali).

Inoltre, se non si specificano bene le ipotesi, si rischia di cadere in errore.

Esempio:  
“Trovare il minimo in un insieme ordinato” ha complessità $Ω(1)$, perché basta leggere il primo elemento.

➡️ Serve sempre **chiarire le premesse** prima di affermare una complessità.

---

### **10. Sintesi finale**

- Abbiamo introdotto le notazioni **O(f(n))**, **Ω(f(n))**, **Θ(f(n))** per descrivere la crescita del tempo di calcolo.
    
- Un algoritmo è **efficiente** se ha complessità polinomiale.
    
- Un algoritmo è **ottimo** se raggiunge il limite inferiore del problema.
    
- Le limitazioni inferiori si trovano con:
    
    1. L’analisi della **dimensione dei dati**
        
    2. Il conteggio degli **eventi ricorrenti**
        
- Capire la complessità non serve solo a classificare gli algoritmi, ma a **pensare in termini di risorse**, rendendo il pensiero informatico più preciso, scalabile e universale.