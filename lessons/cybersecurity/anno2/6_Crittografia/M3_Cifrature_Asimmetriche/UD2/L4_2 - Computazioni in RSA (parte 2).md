# **Lezione 4: Computazioni in RSA (parte 2)**

### **1. Obiettivo**

Per completare l’implementazione dell’algoritmo **RSA**, dopo aver generato i numeri primi $p$ e $q$, bisogna:

- Calcolare **$e$** (esponente pubblico) e **$d$** (esponente privato)
    
- Verificare che:  
    $$  
    \gcd(e, \varphi(n)) = 1  
    $$
    
- Calcolare:  
    $$  
    d = e^{-1} \bmod \varphi(n)  
    $$
    

Dove $\varphi(n) = (p-1)(q-1)$ è la funzione di Eulero.

---

### **2. Algoritmo esteso di Euclide**

L’**algoritmo esteso di Euclide** permette di:

- Calcolare il **$\gcd(a, n)$**
    
- Trovare **l’inverso moltiplicativo** di $a$ modulo $n$, se $\gcd(a,n)=1$
    

Restituisce tre valori $(d, x, y)$ tali che:

$$  
d = \gcd(a, n) = a \cdot x + n \cdot y  
$$

---

#### **Algoritmo (versione ricorsiva)**

```plaintext
Euclide-esteso(a, n)
  if n = 0 then return (a, 1, 0)
  (d', x', y') ← Euclide-esteso(n, a mod n)
  (d, x, y) ← (d', y', x' - ⌊a/n⌋ · y')
  return (d, x, y)
```

#### **Complessità**

Ha lo stesso tempo di esecuzione asintotico dell’algoritmo classico di Euclide, ovvero $O((\log n)^2)$.

---

### **3. Esempio: Euclide esteso (99, 78)**

|a|n|d|x|y|
|---|---|---|---|---|
|99|78|3|-11|14|
|78|21|3|3|-11|
|21|15|3|-2|3|
|15|6|3|1|-2|
|6|3|3|0|1|
|3|0|3|1|0|

Risultato finale:  
$$  
\gcd(99, 78) = 3 \  
99(-11) + 78(14) = 3  
$$

---

### **4. Soluzione di una congruenza $a x \equiv b \pmod{n}$**

#### **Condizioni di esistenza**

La congruenza $a x \equiv b \pmod{n}$ ammette soluzioni **se e solo se**:

$$  
g = \gcd(a, n) \text{ divide } b  
$$

Se $g \mid b$, allora esistono **$g$ soluzioni distinte** modulo $n$:

$$  
x_i = x' \cdot \frac{b}{g} + i \cdot \frac{n}{g}, \quad i = 0, 1, \dots, g-1  
$$

dove $(x', y')$ sono ottenuti da **Euclide-esteso(a, n)** tramite:  
$$  
g = a \cdot x' + n \cdot y'  
$$

---

### **5. Caso particolare: $a x \equiv 1 \pmod{n}$**

La congruenza ha **soluzione unica** se e solo se $\gcd(a, n) = 1$.

In tal caso:  
$$  
a \cdot x' + n \cdot y = 1  
$$

Il valore $x'$ è l’**inverso moltiplicativo** di $a$ modulo $n$, indicato con:

$$  
a^{-1} \bmod n = x'  
$$

---

### **6. Esempi di inverso moltiplicativo**

#### **Esempio 1 – modulo 8**

Si calcolano tutti gli $a$ tali che $\gcd(a, 8) = 1$:

|a|$a^{-1} \pmod{8}$|
|---|---|
|1|1|
|3|3|
|5|5|
|7|7|

Gli altri non hanno inverso perché non primi con 8.

---

#### **Esempio 2 – modulo 7**

Tutti i numeri da 1 a 6 sono primi con 7:

|a|$a^{-1} \pmod{7}$|
|---|---|
|1|1|
|2|4|
|3|5|
|4|2|
|5|3|
|6|6|

---

#### **Esempio 3 – calcolo di $5^{-1} \pmod{7}$**

Usiamo Euclide esteso:

$$  
1 = -2 \cdot 7 + 3 \cdot 5  
$$

⇒ $x = 3$, quindi:  
$$  
5^{-1} \equiv 3 \pmod{7}  
$$

---

### **7. Sistemi di congruenze e Teorema Cinese del Resto (CRT)**

Se $n = p \cdot q$, una congruenza modulo $n$ può essere **decomposta** in due congruenze modulo $p$ e $q$:

$$  
\begin{cases}  
a x \equiv b \pmod{p} \\  
a x \equiv b \pmod{q}  
\end{cases}  
$$

#### **Teorema Cinese del Resto**

Siano $m_1, m_2, \dots, m_t$ interi positivi e **a coppie primi tra loro** ($\gcd(m_i, m_j)=1$ per $i \ne j$).  
Siano $a_1, a_2, \dots, a_t$ gli interi associati.

Allora esiste **una sola soluzione modulo $M = m_1 m_2 \dots m_t$** del sistema:

$$  
\begin{cases}  
x \equiv a_1 \pmod{m_1} \\  
x \equiv a_2 \pmod{m_2} \\  
\vdots \\  
x \equiv a_t \pmod{m_t}  
\end{cases}  
$$

Questa soluzione si ottiene con:

$$  
x = \sum_{i=1}^{t} a_i M_i y_i \pmod{M}  
$$

dove:  
$$  
M_i = \frac{M}{m_i}, \quad y_i = M_i^{-1} \pmod{m_i}  
$$

---

### **8. Esempio di CRT**

Sistema:  
$$  
\begin{cases}  
x \equiv 2 \pmod{5} \\  
x \equiv 3 \pmod{13}  
\end{cases}  
$$

Calcolo:  
$$
\begin{aligned}
M &= 5 \cdot 13 = 65 \\
M_1 &= 13, \quad M_2 = 5 \\
y_1 &= 13^{-1} \bmod 5 = 2 \\
y_2 &= 5^{-1} \bmod 13 = 8
\end{aligned}
$$


Soluzione:  
$$  
x = (2)(13)(2) + (3)(5)(8) = 52 + 120 = 172
$$
$$
x \equiv 172 \bmod 65 \Rightarrow x = 42  
$$

---

### **9. Generazione delle chiavi RSA**

1. **Input:** lunghezza $L$ del modulo
    
2. **Genera** due numeri primi $p$ e $q$ ciascuno di lunghezza $L/2$
    
3. Calcola $n = p \cdot q$
    
4. Scegli casualmente $e$
    
5. Se $\gcd(e, (p-1)(q-1)) = 1$  
    allora calcola $d = e^{-1} \bmod (p-1)(q-1)$  
    altrimenti ripeti dal passo 4
    

---

### **10. Sintesi finale**

- L’**algoritmo di Euclide esteso** consente di calcolare **l’inverso modulo $n$** (fondamentale per $d$).
    
- Se l’inverso non esiste, si possono applicare tecniche come il **Teorema Cinese del Resto (CRT)** per risolvere congruenze modulari.
    
- Con $p$, $q$, $e$, e $d$ ottenuti, è possibile completare la **generazione delle chiavi RSA**.