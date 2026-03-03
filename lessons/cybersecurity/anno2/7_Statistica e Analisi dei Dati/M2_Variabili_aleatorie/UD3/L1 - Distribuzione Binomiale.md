# **M2 UD3 Lezione 1 - Distribuzione Binomiale**

### **1. Dal tempo d’attesa al conteggio degli eventi**

Finora abbiamo studiato modelli (geometrico, esponenziale) che rispondevano alla domanda:

> “Quanto tempo devo aspettare prima che avvenga un certo evento?”

In quei casi la **variabile aleatoria** rappresentava il **tempo (o il numero di tentativi)** necessario al verificarsi del primo successo.

Ora cambiamo prospettiva: fissiamo un **intervallo di tempo o un numero di prove** e chiediamoci:

> “Quante volte si verifica l’evento entro questo intervallo?”

Nascono così le **distribuzioni di conteggio**, che descrivono la **probabilità di ottenere esattamente $k$ eventi** (successi) su $n$ prove.

---

### **2. Esempio introduttivo: lanci di moneta**

Supponiamo di lanciare una moneta **tre volte** ($n=3$), e chiediamoci:

- qual è la probabilità di avere **0 teste**,
    
- **1 testa**,
    
- **2 teste**,
    
- **3 teste**.

Le possibili sequenze sono $2^3 = 8$:

```
CCC, CCT, CTC, CTT, TCC, TCT, TTC, TTT
```

Ogni sequenza ha probabilità $p^k q^{n-k}$, dove:

- $p$ = probabilità di ottenere **Testa**,
    
- $q = 1 - p$ = probabilità di **Croce**.

Per esempio:

- $P(k=3) = p^3$
    
- $P(k=2) = 3p^2q$
    
- $P(k=1) = 3pq^2$
    
- $P(k=0) = q^3$

---

### **3. Struttura della distribuzione**

Le probabilità appena elencate si ottengono come **prodotto di due fattori**:

1. **Parte probabilistica:**  
    $$p^k q^{n-k}$$  
    rappresenta la probabilità di una singola sequenza con $k$ successi e $(n-k)$ fallimenti.
    
2. **Parte combinatoria:**  
    rappresenta **quante sequenze** diverse hanno esattamente $k$ successi.

---

### **4. Il fattore combinatorio**

Il numero di modi diversi in cui possiamo ottenere $k$ successi su $n$ prove è dato da:

$$  
\binom{n}{k} = \frac{n!}{k!(n-k)!}  
$$

Questa quantità rappresenta il numero di **disposizioni non ordinate** di $k$ elementi tra $n$.

---

### **5. Forma analitica della distribuzione binomiale**

Combinando la parte combinatoria e quella probabilistica otteniamo la **formula generale**:

$$  
P(k) = \binom{n}{k} p^k q^{n-k}  
$$

Questa è la **distribuzione Binomiale**, indicata anche come:

$$  
B(k \mid p, n)  
$$

Essa fornisce la **probabilità di ottenere esattamente $k$ successi** in $n$ prove indipendenti, ciascuna con probabilità di successo $p$.

---

### **6. Significato generale**

La distribuzione Binomiale risponde alla domanda:

> “Qual è la probabilità di ottenere esattamente $k$ successi in $n$ tentativi, sapendo che la probabilità di successo per ogni tentativo è $p$?”

Esempio:

- probabilità di ottenere **2 teste su 3 lanci**  

$$P(k=2) = \binom{3}{2} p^2 q = 3p^2q$$

---

### **7. Normalizzazione**

Lo sviluppo del binomio di Newton:  
$$  
(a + b)^n = \sum_{k=0}^n \binom{n}{k} a^{n-k} b^k  
$$

Sostituendo $a = q$, $b = p$, e ricordando che $q + p = 1$, otteniamo:

$$  
\sum_{k=0}^{n} \binom{n}{k} p^k q^{n-k} = (p + q)^n = 1  
$$

Quindi la distribuzione binomiale è **già normalizzata**.

---

### **8. Esempi numerici**

Per $n = 1$:

- La Binomiale coincide con la **Bernoulliana**:  

$$  
    P(0) = q, \quad P(1) = p  
    $$

Per $n = 2$:  
$$  
\begin{cases}  
P(0) = q^2 \\\\  
P(1) = 2pq \\\\  
P(2) = p^2  
\end{cases}  
$$

Per $n = 3$:  
$$  
\begin{cases}  
P(0) = q^3 \\\\  
P(1) = 3pq^2 \\\\  
P(2) = 3p^2q \\\\  
P(3) = p^3  
\end{cases}  
$$

---

### **9. Effetto dei parametri**

- Per **$p$ piccoli** → la distribuzione è sbilanciata verso $k=0$.
    
- Per **$p$ grandi** → è sbilanciata verso $k=n$.
    
- Per **$p = 0.5$** → la distribuzione è **simmetrica**.

All’aumentare di $n$, la distribuzione tende a diventare **continua** e ad assumere una **forma a campana**, anticipando la **densità gaussiana**.

---

### **10. Media e varianza**

I valori principali della Binomiale sono:

$$  
\text{Media: } \mu = n p  
$$

$$  
\text{Varianza: } \sigma^2 = n p q  
$$

$$  
\text{Deviazione standard: } \sigma = \sqrt{n p q}  
$$

---

### **11. Moda**

La **moda** della Binomiale** coincide con l’intero** compreso tra:  
$$  
p(n + 1) - 1 \le k_{\text{moda}} \le p(n + 1)  
$$

**Esempio:**  
per $p = 0.5$ e $n = 8$  
$$  
k_{\text{moda}} = 4 = \mu  
$$  
quindi per $p = 0.5$ la distribuzione è simmetrica e **moda = media**.

---

### **12. Cumulativa e anticumulativa**

La **funzione cumulativa** $F(k)$ e la **anticumulativa** $S(k)$ si definiscono come:

$$  
F(k) = \sum_{i=0}^{k} P(i), \quad S(k) = \sum_{i=k+1}^{n} P(i)  
$$

Non esiste una forma analitica semplice per $F(k)$ o $S(k)$, quindi i valori vengono in genere:

- calcolati direttamente per $n$ piccoli,
    
- tabulati o
    
- ottenuti tramite calcolo numerico.

---

### **13. Esempio applicativo**

**Esempio – sistema a 3 generatori**

Tre generatori indipendenti ($n = 3$) alimentano una lampadina che resta accesa se **almeno due** sono attivi.

$$  
P(\text{lampadina accesa}) = P(k \ge 2)  
$$

Calcoliamo:

$$  
P(k \ge 2) = 1 - [P(k=0) + P(k=1)]  
$$

$$  
P(k=0) + P(k=1) = q^3 + 3pq^2  
$$

$$  
\Rightarrow P(\text{funziona}) = 1 - (q^3 + 3pq^2)  
$$

---

### **14. Riepilogo finale**

|**Proprietà**|**Formula / Valore**|**Significato**|
|---|---|---|
|**Distribuzione**|$P(k) = \binom{n}{k} p^k q^{n-k}$|Probabilità di $k$ successi su $n$ prove|
|**Normalizzazione**|$\sum_{k=0}^{n} P(k) = 1$|La somma delle probabilità è unitaria|
|**Media**|$\mu = n p$|Numero medio di successi|
|**Varianza**|$\sigma^2 = n p q$|Dispersione dei conteggi|
|**Deviazione standard**|$\sigma = \sqrt{n p q}$|Scarto medio dalla media|
|**Moda**|$p(n + 1) - 1 \le k_{\text{moda}} \le p(n + 1)$|Valore più probabile|
|**Cumulativa**|$F(k) = \sum_{i=0}^{k} P(i)$|Probabilità che i successi siano ≤ $k$|
