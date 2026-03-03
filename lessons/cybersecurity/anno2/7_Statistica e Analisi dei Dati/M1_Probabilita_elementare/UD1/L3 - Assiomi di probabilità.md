# **M1 UD1 Lezione 3 - Assiomi di probabilità**

### **1. Introduzione**

Nelle lezioni precedenti abbiamo definito la probabilità come rapporto tra casi favorevoli e casi possibili.  
Ora formalizziamo questa idea attraverso tre **assiomi fondamentali**, che ogni funzione di probabilità deve rispettare per essere coerente dal punto di vista matematico.

Gli assiomi, introdotti da **Andrey Kolmogorov (1933)**, costituiscono la base rigorosa dell’intera teoria della probabilità.

![](imgs/Pasted%20image%2020251216122350.png)

Tra i più importanti e influenti matematici del [XX secolo](https://it.wikipedia.org/wiki/XX_secolo "XX secolo"), compì importanti progressi in diversi campi accademici, tra cui la [teoria delle probabilità](https://it.wikipedia.org/wiki/Teoria_delle_probabilit%C3%A0 "Teoria delle probabilità"), la [topologia](https://it.wikipedia.org/wiki/Topologia "Topologia"), la [logica intuizionista](https://it.wikipedia.org/wiki/Logica_intuizionista "Logica intuizionista"), la [turbolenza](https://it.wikipedia.org/wiki/Turbolenza "Turbolenza"), la [meccanica classica](https://it.wikipedia.org/wiki/Meccanica_classica "Meccanica classica") e la [complessità computazionale](https://it.wikipedia.org/wiki/Complessit%C3%A0_computazionale "Complessità computazionale"). Si devono a lui l'introduzione della definizione di insieme limitato e gli assiomi del calcolo probabilistico.

---

### **2. I tre assiomi fondamentali**

#### **Assioma 1 – Positività**

Per ogni evento $E$ definito nello spazio dei campioni $S$, vale:

$$  
P(E) \ge 0  
$$

La probabilità non può mai essere negativa:  
l’evento può avere probabilità **zero** (evento impossibile) ma non inferiore a zero.

---

#### **Assioma 2 – Additività (per eventi disgiunti)**

Se due eventi $A$ e $B$ **non possono verificarsi contemporaneamente**, cioè se:

$$  
A \cap B = \emptyset  
$$

allora vale:

$$  
P(A \cup B) = P(A) + P(B)  
$$

Questa proprietà si chiama **additività** ed esprime il fatto che, se gli eventi sono incompatibili, le loro probabilità si sommano.

---

#### **Assioma 3 – Normalizzazione**

L’intero spazio dei campioni $S$ ha probabilità pari a 1:

$$  
P(S) = 1  
$$

Questo significa che **qualcosa deve accadere**. In parole semplici, conferma che l'intero spazio campionario rappresenta il 100%.
Da ciò deriva la disuguaglianza generale:

$$  
0 \le P(A) \le 1  
$$

---

### **3. Generalità sugli assiomi**

Ricapitolando, per ogni spazio dei campioni $S$ e per eventi $A$, $B$:

$$
\begin{cases}  
P(A) \ge 0 \\\\  
P(A \cup B) = P(A) + P(B) \quad \text{se } A \cap B = \emptyset \\\\  
P(S) = 1  
\end{cases}  
$$

Ogni definizione o modello di probabilità — anche non insiemistico — è valido solo se rispetta questi tre principi.

---

### **4. Dalla misura ai pesi statistici**

In alcuni contesti la probabilità non è definita direttamente, ma si parte da **pesi statistici** non normalizzati (in inglese _odds_).

Esempio:  
se un cavallo è “dato 3 a 2”, ciò significa che:

- l’evento “vince” ha peso 3,
    
- l’evento “non vince” ha peso 2.

La somma dei pesi totali è:

$$  
N = 3 + 2 = 5  
$$

Da cui ricaviamo le probabilità normalizzate:

$$  
P(\text{vince}) = \frac{3}{5}, \qquad P(\text{non vince}) = \frac{2}{5}  
$$

---

### **5. Procedura di normalizzazione**

La **normalizzazione** è il processo con cui si trasformano i pesi grezzi in probabilità valide.

Passaggi:

1. Si parte da pesi statistici arbitrari $w_1, w_2, ..., w_n$
    
2. Si calcola la somma totale, che si ottiene tramite la sommatoria da 1 ad n di tali pesi arbitrari:

$$  
    N = \sum_{i=1}^{n} w_i  
    $$

3. Si definiscono le probabilità normalizzate, dividendo le arbitrarie per la somma totale trovata

$$  
    P_i = \frac{w_i}{N}  
    $$

In questo modo la somma delle probabilità risulta sempre uguale a 1:

$$  
\sum_{i=1}^{n} P_i = 1  
$$

---

### **6. Esempio 1 – Cavallo “3 a 2”**

Indichiamo con $V$ l’evento _“il cavallo vince”_.

- Pesi: 3 (vittoria) e 2 (non vittoria)
    
- Totale: $N = 3 + 2 = 5$

$$  
P(V) = \frac{3}{5}, \qquad P(V^C) = \frac{2}{5}  
$$

Verifica:

$$  
P(V) + P(V^C) = \frac{3}{5} + \frac{2}{5} = 1  
$$

---

### **7. Esempio 2 – Tre cavalli in gara**

Se tre cavalli $A, B, C$ sono dati “5:3:2”, i pesi sono rispettivamente 5, 3 e 2.  
Il totale dei pesi è:

$$  
N = 5 + 3 + 2 = 10  
$$

Le probabilità normalizzate saranno:

$$  
P(A) = \frac{5}{10}, \quad P(B) = \frac{3}{10}, \quad P(C) = \frac{2}{10}  
$$

e anche in questo caso:

$$  
P(A) + P(B) + P(C) = 1  
$$

---

### **8. Riepilogo concettuale**

|Assioma|Nome|Formula|Significato|
|---|---|---|---|
|1|**Positività**|$P(A) \ge 0$|La probabilità non è mai negativa|
|2|**Additività**|$P(A \cup B) = P(A) + P(B)$ se $A \cap B = \emptyset$|Eventi disgiunti si sommano|
|3|**Normalizzazione**|$P(S) = 1$|La probabilità totale è 1|

|Procedura|Formula|Scopo|
|---|---|---|
|**Normalizzazione dei pesi**|$P_i = \frac{w_i}{\sum w_i}$|Trasformare pesi in probabilità valide|

---

### **9. Conclusione**

Gli assiomi di Kolmogorov costituiscono la **struttura logica** della probabilità moderna.  
Tutti i teoremi e le applicazioni — dalla probabilità condizionata al Teorema di Bayes — si basano su queste tre regole.

Comprenderli significa avere un quadro solido su cui costruire le parti successive: **legge del prodotto, legge della somma e affidabilità dei sistemi probabilistici**.