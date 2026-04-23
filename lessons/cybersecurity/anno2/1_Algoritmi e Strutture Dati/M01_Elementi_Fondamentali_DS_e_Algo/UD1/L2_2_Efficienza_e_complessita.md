# **Lezione 2 – Efficienza e Complessità (parte 2)**

### **1. Introduzione: perché serve un nuovo linguaggio per l’efficienza**

Quando gli algoritmi diventano complessi, contare ogni singola operazione elementare diventa impossibile o inutile.  
Abbiamo bisogno di una misura più generale, che non calcoli _quanto tempo preciso_ impiega un algoritmo, ma _come cresce_ il tempo di esecuzione al crescere della dimensione dell’input $n$.

> In altre parole: non misuriamo il tempo in secondi, ma la **tendenza di crescita** del tempo. Questa è la cosiddetta Complessità Computazionale.

Questa tendenza viene espressa con un linguaggio matematico chiamato **notazione asintotica**.  
Serve a capire chi “cresce più in fretta” tra due algoritmi, indipendentemente dal computer o dal linguaggio.

---

### **2. Le due notazioni fondamentali: $O(f(n))$ e $Ω(f(n))$**

#### **$O(f(n))$ - Ordine di grandezza superiore**

Si legge “O grande di f di n”.  
Indica che il tempo di calcolo $T(n)$ **non cresce più velocemente** di una funzione $f(n)$, a meno di una costante.

Formalmente:

$$
T(n) \leq c \cdot f(n) \quad \text{per ogni } n \geq m
$$

Dove $c$ e $m$ sono costanti positive.  
👉 $O(f(n))$ rappresenta un **limite superiore**.

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

##### 🔹 **In sintesi**

| Simbolo    | Significato                                                      |
| ---------- | ---------------------------------------------------------------- |
| **$c$**    | Costante che “scala” la funzione di confronto $f(n)$             |
| **$m$**    | Punto di partenza (soglia) oltre il quale la disuguaglianza vale |
| **$f(n)$** | Funzione di riferimento (es. $n$, $n^2$, $n \log n$)             |
| **$T(n)$** | Tempo reale dell’algoritmo                                       |

---

##### 💡 **Esempio pratico**

Supponiamo che l’algoritmo abbia un tempo di esecuzione descritto da:

$$
T(n) = 5n^2 + 20n + 100
$$

e vogliamo capire se $T(n)$ appartiene a $O(n^2)$.

---

###### 1️⃣ **La definizione da rispettare**

Per dire che $T(n)$ è in $O(n^2)$, devo **trovare due costanti positive** $c$ e $m$ tali che:

$$
T(n) \le c \cdot n^2 \quad \text{per ogni } n \ge m
$$

---

###### 2️⃣ **Scegliamo delle costanti reali**

Partiamo dal caso in cui non è verificata, ad esempio per $n = 1$, $m = 10$:

$5(1)^2 + 20(1) + 100 \le 10(1)^2$

$125 \le 10$ ❌

Ma non è verificata nemmeno per $n = 5$:

$5(5)^2 + 20(5) + 100 \le 10(5)^2$

$125 + 100 + 100 = 325 \le 250$ ❌

Proviamo allora con:

- $c = 10$
- $m = 10$

Cioè voglio verificare se, **a partire da $n = 10$**, vale:

$$
5n^2 + 20n + 100 \le 10n^2
$$

---

###### 3️⃣ **Controlliamo la disuguaglianza**

Per $n = 10$, otteniamo:

$5(10)^2 + 20(10) + 100 \le 10(10)^2$

$500 + 200 + 100 = 800 \le 1000$ ✅

Se prendessimo $n = 11$, invece:

$5(11)^2 + 20(11) + 100 \le 10(11)^2$

$5(121) + 220 + 100 = 605 + 220 + 100 = 925 \le 1210$ ✅

E così via: per valori sempre più grandi di $n$, la parte $5n^2$ domina, e la disuguaglianza resta vera.

---

###### 4️⃣ **Cosa significa**

Vuol dire che:

- per $n$ **più piccoli di 10**, magari l’inequazione non vale;
- ma **da $n = 10$ in poi**, il comportamento di $T(n)$ è “sotto” a una costante volta $n^2$ (cioè $10n^2$).

Quindi formalmente possiamo scrivere:

$$
T(n) = O(n^2) \quad \text{con } c = 10,, m = 10
$$

---

###### 🧠 **Tradotto in parole povere:**

> “Da quando $n$ diventa più grande di 10, il tempo del mio algoritmo cresce **non più velocemente** di 10 volte $n^2$.”

---

##### ⚠️ **Ora proviamo un altro caso “sbagliato”**

Supponiamo di scegliere:

- $c = 6$
- $m = 1$

Quindi vogliamo verificare:

$$
5n^2 + 20n + 100 \le 6n^2 \quad \text{per ogni } n \ge 1
$$

---

###### 🔹 P**asso 1: Calcoliamo per $n = 1$**

$5(1)^2 + 20(1) + 100 \le 6(1)^2$

❌ $125 \le 6$ → **falso**

---

###### 🔹 **Passo 2: Calcoliamo per $n = 5$**

$5(5)^2 + 20(5) + 100 \le 6(5)^2$

❌ $325 \le 150$ → **falso**

---

###### 🔹 **Passo 3: Calcoliamo per $n = 10$**

$5(10)^2 + 20(10) + 100 \le 6(10)^2$

❌ $800 \le 600$ → **ancora falso**

---

###### 🔹 **Passo 4: Calcoliamo per $n = 50$**

$5(50)^2 + 20(50) + 100 \le 6(50)^2$

✅ $13600 \le 15000$ → **finalmente vero!**

Spoiler: vi risparmio i passi successivi con $n = 100$, $n = 200$ e così via, ma da questo punto in poi la disuguaglianza resta sempre vera.

---

###### 🔍 **Cosa osserviamo?**

- Con $c = 6$, la disuguaglianza _non vale_ per $n$ piccoli (1, 5, 10)
- Ma _comincia a valere solo da un certo punto in poi_ (in questo caso, circa da $n = 40–50$ in avanti)

---

#### 🧩 **Conclusione**

La definizione formale di $O(f(n))$ richiede **che esistano delle costanti $c$ e $m$** tali che:

$$
T(n) \le c \cdot f(n) \quad \text{per ogni } n \ge m
$$

- Qui **$c=6$** funziona _solo se scegli m abbastanza grande_ (tipo $m=50$).
- Se imponi $m=1$, fallisce miseramente ❌
- Quindi, nella definizione, **$m$ serve proprio a ignorare i valori piccoli** di $n$ dove la disuguaglianza non regge ancora.

---

#### 🧠 **In parole povere:**

> “m è il punto da cui in poi l’andamento si stabilizza.”  
> “c è il fattore che mi serve per _coprire_ la funzione $T(n)$ con $f(n)$ moltiplicata per una costante.”

---

#### **$Ω(f(n))$ — Ordine di grandezza inferiore**

Si legge “omega di f di n”.  
Indica che $T(n)$ **non cresce più lentamente** di $f(n)$, a meno di una costante.

$$
T(n) \geq c \cdot f(n) \quad \text{per ogni } n \geq m
$$

👉 $Ω(f(n))$ rappresenta un **limite inferiore**. Non sto qui a rispiegare le due costanti... valgono considerazioni del tutto analoghe a prima, let's go on...

---

#### **Quando coincidono**

Se $T(n)$ è sia $O(f(n))$ che $Ω(f(n))$, dovrebbe sovvenirci una deduzione alla Gauss Holmes: il tempo
effettivo di calcolo $T(n)$ cresce **esattamente alla stessa velocità** di $f(n)$, a meno di una costante moltiplicativa. Questo poiché è "schiacciato" tra i due limiti, ma questo implica subito che tutti coincidono. Quindi, i matematici hanno coniato un simbolo apposito per esprimere questa situazione:

$$
T(n) \in Θ(f(n))
$$

Il simbolo **Θ (Theta)** indica che $T(n)$ e $f(n)$ crescono _alla stessa velocità asintotica_.

---

#### **Intuizione visiva: La metafora del preventivo**

Per capire le notazioni asintotiche, immagina di chiedere a un meccanico un preventivo per una riparazione. Il tempo di esecuzione dell'algoritmo $T(n)$ è il costo finale, mentre $f(n)$ è la cifra di riferimento:

- **$O(f(n))$ (O-grande) → Il "Prezzo Massimo":**
  Il meccanico ti dice: _"Ti costerà al massimo 500€"_.
  Rappresenta un **tetto (limite superiore)**: l'algoritmo potrebbe metterci meno, ma mai di più. È la garanzia sul caso peggiore.

- **$\Omega(f(n))$ (Omega) → Il "Prezzo Minimo":**
  Il meccanico ti dice: _"Ti costerà almeno 100€"_.
  Rappresenta un **pavimento (limite inferiore)**: anche se tutto va bene, l'algoritmo non potrà mai metterci meno di così.

- **$\Theta(f(n))$ (Theta) → Il "Prezzo Fisso":**
  Il meccanico ti dice: _"Il prezzo è esattamente 300€"_.
  Rappresenta un **tunnel (limite stretto)**: il tempo di esecuzione è vincolato precisamente a quella funzione, senza grandi variazioni tra caso migliore e peggiore.

---

### **3. Operazioni elementari e ordine di grandezza**

Ogni istruzione elementare (come somma, confronto o assegnamento) costa **$O(1)$**, cioè tempo costante.  
Quando però le istruzioni si ripetono in cicli o chiamate ricorsive, il tempo cresce in funzione di $n$.

| Esempio                                                                                                                                                                                   | Complessità |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------- |
| Operazione singola<br>(assegnamento di una variabile, oppure un confronto, un'operazione aritmetica/booleana/di I/O oppure ancora una dichiarazione di funzione, un'accesso a memoria...) | $O(1)$      |
| Ciclo su n elementi                                                                                                                                                                       | $O(n)$      |
| Doppio ciclo annidato                                                                                                                                                                     | $O(n^2)$    |
| Triplo ciclo annidato                                                                                                                                                                     | $O(n³)$     |
| Ricorsione che dimezza i dati ogni volta                                                                                                                                                  | $O(log_n)$  |

Per ricordarsi la prima categoria, ecco il cheat code: "Le operazioni elementari NON sono cicli e NON sono ricorsioni". Da lì, potete sparare a raffica tutte le operazioni che vi vengono in mente, e saranno tutte $O(1)$.

ATTENZIONE: le operazioni elementari sono $O(1)$ oppure costanti, MA:

- Le istruzioni CONDIZIONALI (tipo `if–else`) possono avere complessità diversa a seconda del ramo eseguito, quindi non possiamo subito classificarle come "elementari", perché richiedono un'analisi più approfondita.
- Le istruzioni di I/O (input/output) possono essere $O(1)$ se si tratta di operazioni semplici, ma possono diventare più costose se coinvolgono grandi quantità di dati o operazioni complesse (es. scrivere su disco, leggere da rete, ecc.). Quindi, anche qui, è necessario valutare caso per caso.
- Le chiamate a procedure e funzioni POTREBBERO essere $O(1)$ se sono semplici, ma se la funzione chiamata contiene cicli o ricorsioni, allora la complessità dipenderà da ciò che accade all'interno di quella funzione. Quindi, anche in questo caso, è necessario analizzare il contenuto della funzione per determinare la complessità.

Al netto di queste precisazioni/eccezioni, che dir si voglia, è dunque vero che le operazioni elementari sono $O(1)$, ma è importante essere consapevoli che non tutte le istruzioni che sembrano "semplici" rientrano in questa categoria senza un'analisi più approfondita.

HINT: Se un'istruzione è accompagnata da graffe, proprio come in un blocco condizionale o quando si chiama una funzione, allora è necessario esaminare il contenuto di quel blocco o di quella funzione per determinare la complessità effettiva dell'istruzione.

---

### **4. Esempi pratici di analisi del codice**

#### **Esempio 1 – Istruzione condizionale**

```c
if ( condizione in O(f(n)) ) {
        // operazioni per O(g(n))
    } else {
        // operazioni per O(h(n))
    }
```

L’esempio rappresenta un **blocco condizionale `if–else`** in cui possono esserci **operazioni diverse**a seconda che la condizione sia vera o falsa.
In analisi della complessità, noi vogliamo capire **quanto tempo impiega nel caso peggiore** l’intero blocco.

---

##### 🔹 **Spiegazione dei simboli**

| Simbolo    | Cosa rappresenta                                               | Esempio intuitivo                                                                           |
| ---------- | -------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| **$f(n)$** | Il **tempo per valutare la condizione** dell’`if`              | Controllare se un elemento è maggiore di 0 → $O(1)$; cercare un valore in un array → $O(n)$ |
| **$g(n)$** | Il **tempo del blocco “if”** (cioè se la condizione è vera)    | Eseguire un ciclo che scorre un array → $O(n)$                                              |
| **$h(n)$** | Il **tempo del blocco “else”** (cioè se la condizione è falsa) | Fare solo una stampa a video → $O(1)$                                                       |

---

##### 🔹 **La logica della formula**

Quando si calcola la complessità totale di un costrutto `if–else`, bisogna considerare:

1. Il tempo per **valutare la condizione** → $O(f(n))$
2. Il tempo del **ramo eseguito** → può essere $O(g(n))$ oppure $O(h(n))$

Ma poiché in analisi asintotica ci interessa il **caso peggiore**, si prende **il massimo** tra tutti i possibili tempi.

Ecco perché la formula generale è:

$$
O( \ \max \ \{ \ f(n), \ g(n), \ h(n) \ \} \ )
$$

---

##### 🔹 **Esempio concreto**

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
O( \ \max \{ \ n, n, 1 \ \} \ ) = O(n)
$$

La complessità totale è $O(n)$, cioè _lineare_, perché il costo maggiore è quello dei cicli sull’array.

---

##### 🔹 **Altro esempio (più estremo)**

```c
if ( ricercaBinaria(array, n) ) { // O(log n)
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
O( \ \max \{ \ \log n, n^2, n \ \} \ ) = O(n^2)
$$

👉 Il caso peggiore è il ramo quadratico: domina tutto.

---

##### 🧠 **In sintesi**

Nel caso appena visto, e in molti altri, di solito...

| Simbolo | Significato intuitivo                    | Esempio tipico              |
| ------- | ---------------------------------------- | --------------------------- |
| $f(n)$  | Tempo per verificare la condizione       | Ricerca o confronto         |
| $g(n)$  | Tempo del ramo “if” (condizione vera)    | Parte più costosa possibile |
| $h(n)$  | Tempo del ramo “else” (condizione falsa) | Parte alternativa           |
|         |                                          |                             |

E il risultato è sempre:

$$
O(\ \max \ \{ \ f(n), \ g(n), \ h(n) \ \} \ )
$$

perché in analisi della complessità **conta solo il ramo più costoso**.

---

#### **Esempio 2 – Un ciclo semplice e un nested for**

Supponiamo che un programma contenga in sequenza due blocchi for, ma con una differenza: il primo è un ciclo semplice, mentre il secondo è un ciclo annidato (nested loop).

```c
for (i = 0; i < n; i++)
    operazioni per O(f(n));

for (j = 0; j < n; j++) {
    for (k = 0; k < m; k++) {
        operazioni per O(g(m));
    }
}
```

Per calcolare complessivamente la complessità di questa parte di programma, tenendo conto di entrambi i cicli, si cercherà il massimo tra le operazioni di ciascun ciclo.

NOTA BENE: E' un esempio, si faranno supposizioni al 101% arbitrarie, non cercate il pelo nell'uovo del "perché abbiamo supposto così o colà"

Prendiamo la complessità del primo ciclo, che è data dal prodotto tra il numero di iterazioni e il costo di ogni iterazione:

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

Supponendo che $g(m)$ sia costante e che $m$ sia dell'ordine di $n$. Possiamo sostituire dunque $m$ con $n$ e $g(m)$ con 1:

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

Poiché $\max \ \{ \ n^2, n^2 \ \} = n^2$,

$$
O(n^2)
$$

✅ Quindi sì, **la conclusione è perfettamente corretta**:

> Sotto queste ipotesi, entrambi i cicli hanno la **stessa complessità asintotica quadratica** $O(n^2)$.

---

### **5. La complessità di un algoritmo**

Definizione generale:

> La **complessità di un algoritmo** è l’ordine di grandezza $O(T(n))$ del numero di operazioni elementari eseguite nel **caso pessimo**, in funzione della dimensione $n$ dei dati di ingresso.

![](imgs/Pasted%20image%2020260422041142.png)

---

#### **Classificazione**

- **Algoritmo efficiente** → complessità **polinomiale**, cioè $O(n^c)$ con $c$ costante positiva.  
   Esempi: $O(n)$, $O(n^2)$, $O(n^3)$
- **Algoritmo inefficiente** → complessità **superpolinomiale**, cioè $O(c^n)$ o peggiore.  
   Esempi: $O(2^n)$, $O(n!)$, $O(n^n)$, ecc.

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

Come si è già intuito da questa e dalle scorse lezioni, l'informatica ha un attaccamento morboso nei confronti del sorting, ovvero alle tecniche di ordinamento. Perché? Perché è un problema fondamentale, che si presenta in tantissimi contesti diversi, e che ha una grande varietà di soluzioni, con complessità molto diverse tra loro. Con questo preambolo, vediamo di sfuggita per poi riprendere più avanti il **Selection Sort**, un algoritmo di ordinamento semplice ma non ottimale, che ha complessità $O(n^2)$.

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

> Nota: qui assumiamo l’implementazione classica che **scansiona interamente** il sottoarray rimanente per trovare il minimo (niente “early exit”, in quanto l'early exit è considerabile come un'ottimizzazione, ergo una feature EXTRA che prescinde totalmente dall'algoritmo originale, e che non tiene conto del caso peggiore).

---

##### **Somma aritmetica “alla Gauss” (trucco di pairing)**

Scriviamo la somma di tutti i confronti eseguiti dall'algoritmo in duplice copia: una in ordine crescente e una in ordine decrescente:

$$
\begin{aligned}
S &= 1 + 2 + 3 + \dots + (n-2) + (n-1) \\\\
S &= (n-1) + (n-2) + \dots + 3 + 2 + 1
\end{aligned}
$$

Se sommiamo membro a membro, ogni colonna fa **n**:

$$
2S = n + n + \dots + n \quad \text{(ripetuto (n-1) volte)} = n(n-1).
$$

Ma dove l'abbiamo già sentito?
Questo trucco di pairing è esattamente l'intuizione geniale che ebbe Gauss da bambino, quando gli venne chiesto di sommare i primi 100 numeri naturali come punizione, dal momento che era una mansione che gli avrebbe portato via molto tempo. Peccato che la sua maestra non avesse tenuto conto che il giovane matematico fosse in grado di bypassare totalmente il calcolo manuale number-by-number in favore di una bella spremitura di meningi che lo ha portato all'equazione:

$$
S=\frac{n(n-1)}{2}.
$$

Osserva che qui $S$ è la somma da $1$ a $(n-1)$; è la formula standard delle progressioni aritmetiche:

$$
1+2+\dots+k=\frac{k(k+1)}{2} \quad \text{con } k=n-1.
$$

---

##### **Mini-check con un esempio**

Per $n=5$:

- 1ª posizione: $4$ confronti
- 2ª posizione: $3$
- 3ª posizione: $2$
- 4ª posizione: $1$

Totale: $4+3+2+1=10$ e infatti

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

Partiamo col dire che, se fossimo in grado di dimostrare che qualunque algoritmo inventato per risolvere un certo problema ha complessità $Ω(f(n))$, allora avremmo stabilito una **limitazione inferiore** per quel problema: nessun algoritmo potrà essere più veloce di $f(n)$!

Se invece un certo algoritmo dovesse avere complessità $O(g(n))$, allora avremmo una **limitazione superiore**: quel problema può essere risolto in tempo non più lento di $g(n)$.

#### **Algoritmo ottimo**

Un algoritmo è **ottimo** (o ottimale) se:

- sono state stabilite entrambe le limitazioni (superiore e inferiore) per il problema, e se
- f(n) = g(n) (cioè se le limitazioni coincidono).

Perché? Perché ciò implica che la sua complessità in ordine di grandezza è esattamente quella del problema, quindi non può essere migliorato da nessun altro algoritmo.

---

### **8. Come si trovano le limitazioni inferiori**

#### **a) Dimensione dei dati**

Se un problema richiede di esaminare tutti i dati, la complessità inferiore è almeno $Ω(n)$.

Esempio:  
Trovare il minimo in un array di n elementi → bisogna guardarli tutti → $Ω(n)$.

➡️ Gli algoritmi del minimo che abbiamo visto nelle scorse lezioni sono **ottimali**, poiché scandiscono tutti i dati e raggiungono il limite inferiore → $O(n)$ e $Ω(n)$ → $Θ(n)$.

---

#### **b) Eventi contabili**

Se un problema richiede di ripetere un certo evento (es. un confronto) almeno n volte, la complessità inferiore è $Ω(n)$.

Esempio:  
Per trovare il minimo tra n valori servono almeno n−1 confronti → $Ω(n)$.

➡️ Anche qui, abbiamo una seconda conferma chegli algoritmi del minimo raggiungono il limite inferiore → **ottimali**.

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

- Abbiamo introdotto le notazioni $O(f(n))$, $Ω(f(n))$, $Θ(f(n))$ per descrivere la crescita del tempo di calcolo.
- Un algoritmo è **efficiente** se ha complessità polinomiale.
- Un algoritmo è **ottimo** se raggiunge il limite inferiore del problema.
- Le limitazioni inferiori si trovano con:
  1. L’analisi della **dimensione dei dati**
  2. Il conteggio degli **eventi ricorrenti**
- Capire la complessità non serve solo a classificare gli algoritmi, ma a **pensare in termini di risorse**, rendendo il pensiero informatico più preciso, scalabile e universale.

#### ***QUESTA LEZIONE E LE DUE PRECEDENTI SONO FONDAMENTALI PER TUTTO IL RESTO DEL CORSO, QUINDI ASSICURATEVI DI AVERLE BEN CHIARE PRIMA DI PROSEGUIRE! NON SI PUO' STUDIARE NESSUNA DATA STRUCTURE O ALGORITMO SE NON SI SANNO A MENADITO I CONCETTI INIZIALI SULL'EFFICIENZA E LA CONSEGUENTE COMPLESSITA' TEMPORALE. Non abbiamo ancora menzionato minimamente la complessità spaziale, poiché è un topic che viaggia su tutt'altro binario.***
