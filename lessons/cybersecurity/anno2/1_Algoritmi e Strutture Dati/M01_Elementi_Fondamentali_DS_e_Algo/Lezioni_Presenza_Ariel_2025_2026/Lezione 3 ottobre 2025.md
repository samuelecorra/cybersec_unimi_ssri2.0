
Quando si analizza la **complessità temporale** di un **algoritmo ricorsivo**, non possiamo più semplicemente contare le istruzioni come facciamo per un algoritmo iterativo.  
Questo perché una **chiamata ricorsiva** genera **altre chiamate**, ciascuna con un input ridotto, e l’esecuzione totale dipende da come queste chiamate si ramificano e si combinano tra loro.

Per gestire questa complessità, introduciamo tre metodi principali per risolvere le **relazioni di ricorrenza** (ovvero le formule che descrivono il tempo d’esecuzione di un algoritmo ricorsivo):

1. **Metodo di sostituzione**
    
2. **Metodo dell’albero di ricorsione**
    
3. **Metodo dell’esperto (Master Theorem)**
    

Iniziamo dal primo e più intuitivo: **il metodo di sostituzione**.

---

### **Metodo di sostituzione: idea di fondo**

L’idea di base è **ipotesi + verifica**.

- Si **ipotizza** una forma asintotica della funzione $T(n)$ (tempo di esecuzione in funzione dell’input $n$).
    
- Poi si **verifica** che l’ipotesi sia corretta usando una **dimostrazione per induzione matematica**.
    

In pratica, vogliamo “indovinare” la crescita di $T(n)$ e poi dimostrare che la nostra ipotesi regge per ogni $n$ abbastanza grande.

---

### **Esempio classico: Merge Sort**

Prendiamo come esempio la **ricorrenza** tipica dell’algoritmo di _Merge Sort_:

$$  
T(n) = 2T\left(\frac{n}{2}\right) + n  
$$

#### **Interpretazione:**

- Il termine $2T\left(\frac{n}{2}\right)$ rappresenta le **due chiamate ricorsive** su due metà del problema.
    
- Il termine $+ n$ rappresenta il **tempo lineare** impiegato per combinare (fondere) i risultati parziali.
    

---

### **4. Passo 1 — Ipotesi**

Ipotizziamo che la soluzione sia **asintoticamente proporzionale a $n \log n$**:

$$  
T(n) = O(n \log n)  
$$

Più precisamente, ipotizziamo:

$$  
T(n) \leq c \cdot n \log n  
$$

dove $c$ è una **costante positiva** da determinare.

---

### **5. Passo 2 — Verifica per induzione**

Dimostriamo per **induzione su $n$** che questa ipotesi è vera.

#### **Caso base**

Per $n = 1$, l’algoritmo si ferma e il costo è costante:

$$  
T(1) = c_0  
$$

e la formula $c_0 \leq c \cdot 1 \cdot \log 1 = 0$ è banalmente verificabile scegliendo $c$ sufficientemente grande (oppure si impone $T(1) \leq k$ costante).

#### **Passo induttivo**

Supponiamo che per tutti i valori **minori di $n$** valga:

$$  
T(k) \leq c , k \log k  
$$

Dimostriamo che allora vale anche per $n$:

$$  
T(n) = 2T\left(\frac{n}{2}\right) + n  
$$

Sostituiamo l’ipotesi induttiva nei termini ricorsivi:

$$  
T(n) \leq 2 \left[ c \cdot \frac{n}{2} \cdot \log\left(\frac{n}{2}\right) \right] + n  
$$

Semplifichiamo:

$$  
T(n) \leq c n \log\left(\frac{n}{2}\right) + n  
$$

Applichiamo la proprietà dei logaritmi:

$$  
\log\left(\frac{n}{2}\right) = \log n - \log 2  
$$

quindi:

$$  
T(n) \leq c n (\log n - \log 2) + n = c n \log n - c n \log 2 + n  
$$

Raggruppiamo:

$$  
T(n) \leq c n \log n - n (c \log 2 - 1)  
$$

Se scegliamo $c$ tale che $(c \log 2 - 1) \geq 0$, ovvero $c \geq \frac{1}{\log 2}$, otteniamo:

$$  
T(n) \leq c n \log n  
$$

che dimostra la correttezza dell’ipotesi.

---

### **6. Conclusione**

Abbiamo verificato che la soluzione della ricorrenza:

$$  
T(n) = 2T\left(\frac{n}{2}\right) + n  
$$

è effettivamente:

$$  
T(n) = O(n \log n)  
$$

---

### **7. Significato intuitivo**

Il risultato ci dice che:

- Ad ogni livello della ricorsione, spendiamo **O(n)** tempo per combinare i risultati.
    
- Ci sono circa **log n** livelli (perché ogni volta dividiamo l’input a metà).
    

Moltiplicando:

$$  
n \cdot \log n  
$$

otteniamo la complessità totale.

---

### **8. Sintesi visiva del metodo**

|Passo|Descrizione|Formula chiave|
|---|---|---|
|1|Scrivi la ricorrenza|$T(n) = 2T(n/2) + n$|
|2|Formula un’ipotesi asintotica|$T(n) = O(n \log n)$|
|3|Applica l’ipotesi per induzione|$T(n) \leq 2[c(n/2)\log(n/2)] + n$|
|4|Semplifica e verifica|$T(n) \leq c n \log n - c n \log 2 + n$|
|5|Determina una condizione su $c$|$c \geq \frac{1}{\log 2}$|
|6|Concludi|$T(n) = O(n \log n)$|

---

### **9. Nota generale**

Questo stesso metodo si può usare per qualunque ricorrenza del tipo:

$$  
T(n) = aT\left(\frac{n}{b}\right) + f(n)  
$$

anche se in quei casi spesso è più rapido applicare il **metodo dell’esperto (Master Theorem)**, che fornisce una formula diretta senza dover ipotizzare e dimostrare per induzione.

---

Procediamo ora con gli altri due strumenti annunciati.

---

### **Metodo dell’albero di ricorsione**

Questo approccio visualizza tutte le chiamate ricorsive come un **albero**: ogni nodo rappresenta una chiamata, con il suo costo locale, e i figli rappresentano le chiamate generate.

1. **Disegna la struttura delle chiamate** a partire dalla radice (input di dimensione $n$).
2. **Annota il costo locale** di ciascun nodo, cioè il lavoro svolto senza contare le chiamate figlie.
3. **Somma i costi livello per livello**, sfruttando regolarità e fattori comuni.
4. **Conta quanti livelli ha l’albero** (di solito $\log_b n$ quando ogni volta l’input si riduce di un fattore $b$).
5. **Somma i costi di tutti i livelli** per ottenere il tempo totale.

Per la ricorrenza di Merge Sort:

- **Livello 0** (radice): costo $n$.
- **Livello 1**: due nodi, ciascuno costa $n/2$; il totale del livello è ancora $n$.
- **Livello 2**: quattro nodi da $n/4$ ciascuno; il livello costa $n$.
- ...
- **Livello $k$**: $2^k$ nodi da $n/2^k$ ciascuno; il livello costa $n$.

L’albero termina quando il sotto-problema scende a dimensione 1, quindi il numero di livelli è $\log_2 n$. Il costo complessivo è:

$$
T(n) = n \cdot (\log_2 n + 1) = O(n \log n)
$$

Questo conferma visivamente il risultato ottenuto con il metodo di sostituzione e aiuta a intuire **quanto lavoro svolge ogni livello** della ricorsione.

---

### **Metodo dell’esperto (Master Theorem)**

Il Master Theorem è una scorciatoia che fornisce direttamente la complessità asintotica per ricorrenze del tipo:

$$
T(n) = a\,T\left(\frac{n}{b}\right) + f(n)
$$

con $a \geq 1$, $b > 1$ e $f(n)$ funzione asintoticamente positiva. Il teorema confronta $f(n)$ con $n^{\log_b a}$ e distingue tre casi principali:

1. **$f(n)$ inferiore**: se $f(n) = O(n^{\log_b a - \epsilon})$ per qualche $\epsilon > 0$, allora $T(n) = \Theta(n^{\log_b a})$.
2. **$f(n)$ in equilibrio**: se $f(n) = \Theta(n^{\log_b a} \log^k n)$ per $k \geq 0$, allora $T(n) = \Theta(n^{\log_b a} \log^{k+1} n)$.
3. **$f(n)$ dominante**: se $f(n) = \Omega(n^{\log_b a + \epsilon})$ per qualche $\epsilon > 0$ e soddisfa una condizione tecnica di regolarità (spesso detta **regularity condition**), allora $T(n) = \Theta(f(n))$.

Per Merge Sort abbiamo $a = 2$, $b = 2$, quindi $n^{\log_b a} = n^{\log_2 2} = n$. Il termine extra è $f(n) = n$, che rientra nel **secondo caso** (equilibrio con $k = 0$). Il Master Theorem fornisce subito:

$$
T(n) = \Theta(n \log n)
$$

Nessuna ipotesi da indovinare, nessuna somma da calcolare: basta riconoscere il formato della ricorrenza.

---

### **Confronto e quando usare quale metodo**

- **Sostituzione**: potente e generico, ma richiede una buona intuizione iniziale sulla forma della soluzione. Ottimo per affinare la comprensione e per ricorrenze non standard.
- **Albero di ricorsione**: intuitivo e visivo; perfetto per spiegare perché certi risultati emergono (utile nelle prove orali o per presentazioni).
- **Master Theorem**: rapidissimo quando la ricorrenza ha la forma adatta; da usare per controllare velocemente i risultati degli altri metodi.

Nei prossimi appunti useremo spesso questi strumenti in parallelo: risolvere la stessa ricorrenza in più modi aumenta la confidenza nel risultato e permette di affrontare anche i casi in cui uno dei tre metodi non è direttamente applicabile.