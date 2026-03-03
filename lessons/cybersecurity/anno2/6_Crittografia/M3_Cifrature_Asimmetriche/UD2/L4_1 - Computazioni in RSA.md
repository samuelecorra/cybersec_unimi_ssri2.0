# **Lezione 4: Computazioni in RSA (parte 1)**

### **1. Operazioni necessarie per l’algoritmo RSA**

Per eseguire l’algoritmo **RSA** è necessario saper effettuare alcune **operazioni fondamentali**:

- **Elevazione a potenza modulare**
    
- **Generazione di numeri primi**
    
- **Scelta dell’esponente pubblico $e$**
    
- **Calcolo dell’esponente privato $d$**
    

Il legame tra le due chiavi è:

$$  
d \leftarrow e^{-1} \bmod \big( (p-1)(q-1) \big)  
$$

---

### **2. Elevazione a potenza modulare**

L’obiettivo è calcolare:

$$  
x^y \bmod z  
$$

Tre metodi principali:

1. **Naive**
    
2. **Left-to-right**
    
3. **Right-to-left**
    

---

### **3. Metodo naïve**

```plaintext
Potenza_Modulare_naive(x, y, z)
  a ← 1
  for i = 1 to y do
      a ← (a · x) mod z
  return a
```

#### **Limite**

Se $y$ è di **512 bit**, occorrono circa $2^{512}$ moltiplicazioni.  
La complessità cresce **esponenzialmente** con la lunghezza dell’esponente.

---

### **4. Metodo left-to-right**

L’idea di base è esprimere l’esponente in forma binaria:

$$  
y = y_0 2^0 + y_1 2^1 + \dots + y_t 2^t  
$$

Da cui:

$$  
x^y = x^{y_0 + 2(y_1 + 2(y_2 + \dots + 2(y_{t-1} + 2y_t))...))}  
$$

#### **Esempio numerico**

$$  
40 = 0·2^0 + 0·2^1 + 0·2^2 + 1·2^3 + 0·2^4 + 1·2^5  
$$

L’algoritmo procede **dall’alto verso il basso** (dal bit più significativo al meno significativo).

---

### **5. Metodo left-to-right – Algoritmo**

```plaintext
Potenza_Modulare(x, y, z)
  a ← 1
  for i = t downto 0 do
      a ← (a · a) mod z
      if yi = 1 then
          a ← (a · x) mod z
  return a
```

#### **Complessità**

Se $y$ è di 512 bit, occorrono **circa 512 moltiplicazioni modulari**,  
quindi la complessità è **polinomiale** nella lunghezza dell’esponente.

#### **Esempio**

$$  
3^{40} \bmod 73 = 8  
$$

Rappresentazione binaria di $40$:

$$  
y_5=1,\ y_4=0,\ y_3=1,\ y_2=0,\ y_1=0,\ y_0=0  
$$

---

### **6. Metodo right-to-left**

Anche qui si parte dalla rappresentazione binaria di $y$:

$$  
y = y_0 2^0 + y_1 2^1 + \dots + y_t 2^t  
$$

Da cui:

$$  
x^y = x^{2^0 y_0} \cdot x^{2^1 y_1} \cdot \dots \cdot x^{2^t y_t}  
$$

Il calcolo procede **dal bit meno significativo al più significativo**.

#### **Esempio**

$$  
40 = 0·2^0 + 0·2^1 + 0·2^2 + 1·2^3 + 0·2^4 + 1·2^5  
$$

$$  
x^{40} = (x^1)^0 · (x^2)^0 · (x^4)^0 · (x^8)^1 · (x^{16})^0 · (x^{32})^1 = x^8 \cdot x^{32}  
$$

---

### **7. Metodo right-to-left – Algoritmo**

```plaintext
Potenza_Modulare(x, y, z)
  if y = 0 then return 1
  X ← x
  P ← 1
  if y0 = 1 then X ← x
  for i = 1 to t do
      X ← (X · X) mod z
      if yi = 1 then P ← (P · X) mod z
  return P
```

#### **Esempio**

$$  
5^{596} \bmod 1234 = 1013  
$$

|i|yᵢ|X|P|
|---|---|---|---|
|0|0|5|1|
|1|0|25|1|
|2|1|625|625|
|3|0|681|625|
|4|1|1011|67|
|5|0|369|67|
|6|1|421|1059|
|7|0|779|1059|
|8|0|947|1059|
|9|1|925|1013|

👉 Anche questo metodo ha **complessità polinomiale** rispetto alla lunghezza dell’esponente.

---

### **8. Scelta dell’esponente pubblico $e$**

Per migliorare l’efficienza delle operazioni RSA, si sceglie l’esponente pubblico in modo da **ridurre il numero di moltiplicazioni** necessarie durante la cifratura.

Scelte tipiche:

- $e = 3$
    
- $e = 2^{16} + 1 = 65537$
    
    - Rappresentazione binaria:  
        $$  
        e = 1\underbrace{0000000000000000}16 1  
        $$
        
    - È una scelta ottimale: **basso costo computazionale**, ma sicurezza elevata.
        

---

### **9. Performance sperimentali (Crypto++ su Celeron 850 MHz)**

|Dimensione chiavi|Cifratura (ms)|Decifratura (ms)|
|---|---|---|
|**512 bit**|0.14|1.93|
|**1024 bit**|0.32|10.23|
|**2048 bit**|0.89|64.13|

**Osservazioni:**

- La **cifratura** è molto più veloce della **decifratura** (poiché usa $e$ piccolo e $d$ grande).
    
- Aumentando la dimensione delle chiavi, la **sicurezza cresce**, ma anche il **tempo di calcolo**.
    

---

### **10. Sintesi finale**

- Le **computazioni RSA** si basano sull’elevazione a potenza modulare.
    
- I metodi **left-to-right** e **right-to-left** riducono il tempo di calcolo da esponenziale a **polinomiale**.
    
- L’esponente pubblico $e$ viene scelto per **ottimizzare la velocità di cifratura**, mantenendo comunque una sicurezza adeguata.