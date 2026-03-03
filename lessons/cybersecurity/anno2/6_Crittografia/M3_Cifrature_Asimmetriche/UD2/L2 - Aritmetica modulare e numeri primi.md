# **Lezione 2: Aritmetica modulare e numeri primi**

### **1. Numeri primi**

Un numero intero $p > 1$ è detto **primo** se e solo se i suoi unici divisori positivi sono **1 e se stesso**.  
Esempi di numeri primi:  
$2, 3, 5, 7, 11, 13, 17, 19, \dots$

#### **Teorema fondamentale dell’aritmetica**

Ogni intero composto $n > 1$ può essere scritto in modo **unico** (a meno dell’ordine dei fattori) come prodotto di potenze di numeri primi:

$$  
n = p_1^{e_1} \cdot p_2^{e_2} \cdot \dots \cdot p_k^{e_k}  
$$

dove ogni $p_i$ è primo e ogni $e_i$ è un intero positivo.

---

### **2. Teorema della divisione**

Per ogni coppia di numeri $a \in \mathbb{Z}$ e $n \in \mathbb{N}$, esiste un’unica coppia $(q, r)$ tale che:

$$  
a = q \cdot n + r, \quad \text{con } 0 \le r \le n - 1  
$$

Il valore $r$ è detto **resto** o **$a \bmod n$**.

#### **Esempi**

- $a = 11, n = 7 \Rightarrow 11 = 1 \cdot 7 + 4 \Rightarrow 11 \bmod 7 = 4$
    
- $a = -11, n = 7 \Rightarrow -11 = (-2) \cdot 7 + 3 \Rightarrow -11 \bmod 7 = 3$
    

---

### **3. Congruenze modulo n**

Due numeri $a$ e $b$ sono **congruenti modulo $n$** se lasciano lo stesso resto nella divisione per $n$:

$$  
a \equiv b \pmod{n} \quad \Longleftrightarrow \quad a \bmod n = b \bmod n  
$$

Equivale a dire che la loro differenza è un multiplo di $n$:  
$$  
n \mid (a - b)  
$$

#### **Esempi**

- $73 \equiv 4 \pmod{23}$  
    poiché $73 \bmod 23 = 4$
    
- $21 \equiv -9 \pmod{10}$  
    poiché $21 - (-9) = 30$ è multiplo di 10
    

---

### **4. Classi di resto e insieme $Z_n$**

L’insieme dei numeri **congruenti tra loro modulo $n$** forma una **classe di resto**:

$$  
[a]_n = { a + k n \mid k \in \mathbb{Z} }  
$$

Il sistema completo delle classi di resto modulo $n$ è:

$$  
Z_n = { [0]_n, [1]_n, [2]_n, \dots, [n-1]_n }  
$$

#### **Esempio per $n=4$**

$$  
Z_4 = { [0]_4, [1]_4, [2]_4, [3]_4 }  
$$

dove:

- $[0]_4 = {\dots, -8, -4, 0, 4, 8, \dots}$
    
- $[1]_4 = {\dots, -7, -3, 1, 5, 9, \dots}$
    
- $[2]_4 = {\dots, -6, -2, 2, 6, 10, \dots}$
    
- $[3]_4 = {\dots, -5, -1, 3, 7, 11, \dots}$
    

---

### **5. Massimo Comune Divisore / Greatest Common Divisor (gcd)**

Il numero $d$ è il **massimo comune divisore** di $a$ e $n$ se:

- $d$ divide sia $a$ che $n$,
    
- e ogni altro divisore comune di $a$ e $n$ divide $d$.
    

$$  
d = \gcd(a, n)  
$$

Può essere espresso come combinazione lineare:

$$  
d = a \cdot x + n \cdot y  
$$

#### **Proprietà principali**

- $\gcd(a, n) = \gcd(a, -n) = \gcd(-a, n)$
    
- $\gcd(a, 0) = |a|$
    
- Se $\gcd(a, n) = 1$, allora $a$ e $n$ sono **relativamente primi**
    

---

### **6. Algoritmo di Euclide**

Il **metodo di Euclide** (circa 300 a.C.) permette di calcolare $\gcd(a, b)$ in modo efficiente:

```plaintext
Euclide(a, b):
  if b = 0 then return a
  else return Euclide(b, a mod b)
```

#### **Esempio 1**

$$  
\text{Euclide}(30, 21) = \text{Euclide}(21, 9) = \text{Euclide}(9, 3) = \text{Euclide}(3, 0) = 3  
$$

#### **Esempio 2**

$$  
\text{Euclide}(4864, 3458) = 38  
$$

#### **Complessità**

- Numero massimo di iterazioni: $\log_2 a$
    
- Complessità complessiva: $O((\log a)^2)$ operazioni su bit
    

---

### **7. L’insieme $Z_n^*$ e gli inversi**

Si definisce:  
$$  
Z_n^* = { [a]_n \mid 0 < a < n, \ \gcd(a, n) = 1 }  
$$

Gli elementi di $Z_n^*$ **hanno un inverso moltiplicativo modulo $n$**.

#### **Esempi**

- $Z_4^* = { [1]_4, [3]_4 }$
    
- $Z_8^* = { [1]_8, [3]_8, [5]_8, [7]_8 }$
    

Solo questi elementi in $Z_8$ hanno inverso rispetto alla moltiplicazione.

---

### **8. La funzione di Eulero**

La **funzione toziente** (o **funzione di Eulero**) φ(n) indica il **numero di interi minori di n che sono primi con n**.

#### **Esempi**

- $\varphi(37) = 36$
    
- $\varphi(35) = 24$
    

#### **Proprietà**

- Se $p$ è primo, allora $\varphi(p) = p - 1$
    
- Se $p, q$ sono primi, allora $\varphi(pq) = (p - 1)(q - 1)$
    
- In generale, se  
    $$  
    n = p_1^{e_1} p_2^{e_2} \dots p_k^{e_k}  
    $$  
    allora:  
    $$  
    \varphi(n) = n \cdot \left(1 - \frac{1}{p_1}\right) \cdot \left(1 - \frac{1}{p_2}\right) \cdot \dots \cdot \left(1 - \frac{1}{p_k}\right)  
    $$
    

---

### **9. Teorema di Eulero**

Per ogni $a \in Z_n^*$, vale:  
$$  
a^{\varphi(n)} \equiv 1 \pmod{n}  
$$

#### **Esempi**

- $3^4 = 81 \equiv 1 \pmod{10}$
    
- $2^{10} = 1024 \equiv 1 \pmod{11}$
    

---

### **10. Teorema di Fermat**

Se $p$ è primo e $a \in Z_p^*$, allora:  
$$  
a^{p-1} \equiv 1 \pmod{p}  
$$

e, in forma più generale:  
$$  
a^p \equiv a \pmod{p}  
$$

#### **Esempi**

- $7^{18} \equiv 1 \pmod{19}$
    
- $10^5 \equiv 10 \pmod{5} \equiv 0 \pmod{5}$
    

---

### **11. Correttezza della decifratura RSA**

Per l’algoritmo RSA valgono le seguenti condizioni:

$$  
\begin{cases}  
N = p \cdot q \\\\  
\varphi(N) = (p-1)(q-1) \\\\  
e \cdot d \equiv 1 \pmod{\varphi(N)}  
\end{cases}  
$$

Per ogni messaggio $M$ primo con $N$:  
$$  
C^d \bmod N = M  
$$

Infatti:  
$$  
C^d = (M^e)^d = M^{ed} = M^{1 + k\varphi(N)} = M \cdot (M^{\varphi(N)})^k \equiv M \pmod{N}  
$$

Se $M$ **non è primo con $N$**, si applica il **Teorema Cinese del Resto (CRT)** per garantire la correttezza.

---

### **12. Sintesi finale**

Abbiamo introdotto i concetti matematici su cui si basa RSA:

- **Numeri primi e aritmetica modulare**
    
- **Algoritmo di Euclide** e **gcd**
    
- **Insieme $Z_n^*$ e funzione di Eulero**
    
- **Teoremi di Fermat e di Eulero**, che assicurano la **correttezza della decifratura RSA**
    

Questi strumenti consentono di comprendere **perché RSA funziona** e **su quali proprietà matematiche** si fonda la sua sicurezza.

---