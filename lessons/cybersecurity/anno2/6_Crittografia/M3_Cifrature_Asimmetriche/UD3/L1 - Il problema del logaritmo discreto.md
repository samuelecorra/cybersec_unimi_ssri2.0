
# **Lezione 1: Il problema del logaritmo discreto**

### **1. Altri cifrari asimmetrici e problemi matematici correlati**

Esistono diversi schemi di **cifratura asimmetrica**, ciascuno basato su un **problema matematico difficile** da risolvere.

|Algoritmo|Anno|Problema su cui si basa|Note|
|---|---|---|---|
|**RSA**|1977|Fattorizzazione di numeri grandi|Sicuro, molto diffuso|
|**Merkle–Hellman**|1978|Problema dello zaino 0–1|Molte varianti, quasi tutte rotte|
|**Rabin**|1979|Fattorizzazione|Simile a RSA, ma più teorico|
|**McEliece**|1978|Decodifica di codici lineari|Sicuro ma inefficiente|
|**El-Gamal**|1984|Logaritmo discreto|Base anche del DSA|
|**Curve ellittiche**|1985|Logaritmo discreto su curve|Alta sicurezza a chiavi più corte|
|**Hidden Field Equation (HFE)**|1996|Polinomi multivariabili|Sistema rotto|

📘 **Nota:**  
Il **crittosistema di El-Gamal** si fonda sulla **difficoltà del calcolo del logaritmo discreto**, un problema ritenuto **computazionalmente intrattabile** per numeri grandi.

---

### **2. Definizione del problema del logaritmo discreto**

Dato un gruppo moltiplicativo $G$ finito, un elemento $a \in G$ e un altro elemento $b \in G$, il **problema del logaritmo discreto (DL)** consiste nel determinare l’intero $x$ tale che:

$$  
a^x \equiv b \pmod{n}  
$$

dove $n$ è l’ordine del gruppo.

#### **Esempio**

Trovare $x$ tale che:  
$$  
3^x \equiv 7 \pmod{13}  
$$

La soluzione è:  
$$  
x = 6  
$$

perché $3^6 = 729 \equiv 7 \pmod{13}$.

---

### **3. Formulazione generale**

Sia $G$ un gruppo finito con operazione $\otimes$, e $H$ il sottogruppo generato da un elemento $a \in G$:

$$  
H = { a^i \mid i \ge 0 }  
$$

Allora il problema è determinare l’unico intero $x$, con $0 \le x \le |H|-1$, tale che:

$$  
a^x = b  
$$

Questo valore $x$ si chiama **logaritmo discreto di $b$ in base $a$**, e si scrive:

$$  
x = \log_a b  
$$

---

### **4. Complessità del problema del logaritmo discreto**

Calcolare $x$ dato $(a, n, b)$ è un problema **computazionalmente difficile**.

Se $n$ è primo, i migliori algoritmi conosciuti hanno complessità sub-esponenziale del tipo:

$$  
L_n[a, c] = O\left(e^{(c + o(1)) (\ln n)^a (\ln \ln n)^{1 - a}}\right)  
$$

dove $0 < a < 1$ e $c > 0$.

#### **Algoritmo più efficiente noto**

- **Number Field Sieve (NFS)**
    
- Tempo medio euristico:  
    $$  
    L_n[1/3, 1.923]  
    $$
    

👉 In pratica, questo significa che anche per numeri moderatamente grandi (ad esempio 512 o 1024 bit), il calcolo di un logaritmo discreto è **computazionalmente impraticabile**.

---

### **5. Generatori nei gruppi modulari**

Nel gruppo moltiplicativo $Z_p^*$ (gli interi da 1 a $p-1$ con moltiplicazione modulo $p$), un elemento $g$ si dice **generatore** se:

$$  
\{ \ g^i \bmod p \mid 1 \le i \le p-1 \ \} = Z_p^*  
$$

#### **Esempio con $p = 11$**

Calcoliamo i valori di $2^i \bmod 11$ per $1 \le i \le 10$:

|$i$|$2^i \bmod 11$|
|---|---|
|1|2|
|2|4|
|3|8|
|4|5|
|5|10|
|6|9|
|7|7|
|8|3|
|9|6|
|10|1|

Poiché tutti i valori da 1 a 10 compaiono, $g = 2$ è un **generatore di $Z_{11}^*$**.

---

### **6. Struttura dei gruppi modulari**

Ricordiamo che:

$$  
Z_p = \{ \ 0, 1, 2, \dots, p-1 \ \}  
$$

e

$$  
Z_p^* = \{ \ a \in Z_p : \gcd(a, p) = 1 \ \}  
$$

$Z_p^*$ è un gruppo moltiplicativo di ordine $p-1$, e per ogni generatore $g$,  
ogni elemento del gruppo può essere scritto come $g^k \bmod p$.

---

### **7. Sintesi finale**

- Il **problema del logaritmo discreto (DL)** consiste nel trovare $x$ tale che $a^x \equiv b \pmod{n}$.
    
- È considerato **computazionalmente intrattabile**, specialmente per grandi moduli.
    
- L’**algoritmo di El-Gamal** si basa esattamente su questa difficoltà.
    
- Il gruppo $Z_p^*$ fornisce la **base matematica** del sistema, con un **generatore** $g$ che permette di rappresentare tutti gli elementi.