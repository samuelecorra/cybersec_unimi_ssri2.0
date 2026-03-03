# **Lezione 2: Struttura della cifratura AES**

---

### **1. Parametri principali dell’AES**

L’AES è un **cifrario a blocchi** con architettura **SP-Network** (_Substitution–Permutation Network_).  
Opera su blocchi di **128 bit**, suddivisi in una **matrice di 4×4 byte** detta **state**.

Ogni blocco è composto da **Nb = 4 word**, ciascuna da **32 bit (4 byte)**.  
La chiave può avere diverse lunghezze:

![](imgs/Pasted%20image%2020260222172644.png)

|Variante AES|Lunghezza chiave (Nk)|Blocchi (Nb)|Round (Nr)|
|---|---|---|---|
|AES-128|4 word = 128 bit|4|10|
|AES-192|6 word = 192 bit|4|12|
|AES-256|8 word = 256 bit|4|14|

> Ogni **word** corrisponde a 32 bit = 4 byte.

---

### **2. Spazio delle chiavi e sicurezza**

#### **Numero di chiavi possibili**

- **AES-128:** $2^{128} \approx 3.4 \times 10^{38}$ chiavi  
    → anche testando $2^{55}$ chiavi/s servirebbero **149.000 miliardi di anni** per provarle tutte.
    
- **AES-192:** $2^{192} \approx 6.2 \times 10^{57}$ chiavi
    
- **AES-256:** $2^{256} \approx 1.1 \times 10^{77}$ chiavi
    

➡️ Si stima che **AES resterà sicuro per almeno i prossimi 20 anni**, anche contro attacchi quantistici parziali.

---

### **3. Struttura della chiave**

La chiave è una matrice di byte di **4 righe e Nk colonne**, dove:

![](imgs/Pasted%20image%2020260222173029.png)

$$  
Nk = \frac{\text{lunghezza chiave in bit}}{32}  
$$

|Chiave|Byte totali|Nk|
|---|---|---|
|128 bit|16 byte|4|
|192 bit|24 byte|6|
|256 bit|32 byte|8|

**Esempio di chiave (128 bit):**

$$  
\begin{bmatrix}  
K_{0,0} & K_{0,1} & K_{0,2} & K_{0,3} \\  
K_{1,0} & K_{1,1} & K_{1,2} & K_{1,3} \\  
K_{2,0} & K_{2,1} & K_{2,2} & K_{2,3} \\  
K_{3,0} & K_{3,1} & K_{3,2} & K_{3,3}  
\end{bmatrix}  
$$

---

### **4. Blocco di input e matrice di stato**

Il **blocco di testo in chiaro** (128 bit = 16 byte) è rappresentato anch’esso come una matrice 4×4 di byte:

![](imgs/Pasted%20image%2020260222173130.png)

$$  
\text{State} =  
\begin{bmatrix}  
S_{0,0} & S_{0,1} & S_{0,2} & S_{0,3} \\  
S_{1,0} & S_{1,1} & S_{1,2} & S_{1,3} \\  
S_{2,0} & S_{2,1} & S_{2,2} & S_{2,3} \\  
S_{3,0} & S_{3,1} & S_{3,2} & S_{3,3}  
\end{bmatrix}  
$$

Ogni colonna rappresenta una **word** (32 bit).

L’input viene copiato nella **matrice di stato**, su cui avvengono tutte le operazioni.  
Al termine della cifratura, la **matrice di stato finale** viene copiata nel **blocco di output** (testo cifrato).

$$
\begin{aligned}
S_{r,c} &\leftarrow in_{r+4c} \\
out_{r+4c} &\leftarrow S_{r,c}
\end{aligned}
$$


dove:  
$$  
0 \le r < 4, \quad 0 \le c < Nb  
$$

![](imgs/Pasted%20image%2020260222173403.png)

---

### **5. Rappresentazione dei byte**

Nell’AES, il **byte** è l’unità di base su cui avvengono le operazioni.  
Ogni byte è rappresentato come un **polinomio di grado ≤ 7** con coefficienti binari (0 o 1):

$$  
\{b_7b_6b_5b_4b_3b_2b_1b_0\}  
\longleftrightarrow  
b_7x^7 + b_6x^6 + b_5x^5 + b_4x^4 + b_3x^3 + b_2x^2 + b_1x + b_0  
$$

Esempio:  
$$  
\{11010100\} \rightarrow x^7 + x^6 + x^4 + x^2 + 1 = \{d4\}_{16}  
$$

I valori dei byte sono quindi espressi in **esadecimale** (da 00 a FF).

---

### **6. Operazioni sui byte nel campo finito GF(2⁸)**

L’AES esegue le sue operazioni nel Galois Field **campo finito $GF(2^8)$**, che contiene 256 elementi.

#### **Addizione**

È definita come la **somma modulo 2 dei coefficienti** (cioè XOR bit a bit):

$$  
\{57\} \oplus \{83\} = \{d4\}  
$$

In forma polinomiale:  
$$  
( x^6 + x^4 + x^2 + x + 1 ) \oplus ( x^7 + x + 1 ) = x^7 + x^6 + x^4 + 1  
$$

---

#### **Moltiplicazione**

La moltiplicazione tra due elementi di $GF(2^8)$ corrisponde alla **moltiplicazione dei polinomi modulo un polinomio irriducibile** di grado 8.

Il polinomio irriducibile scelto da AES è:

$$  
m(x) = x^8 + x^4 + x^3 + x + 1  
$$

Esempio:  
$$  
\{57\} \cdot \{83\} = \{c1\}  
$$

![](imgs/Pasted%20image%2020260222174241.png)

Le operazioni di moltiplicazione sono associative e ogni elemento (tranne 0) ha un **inverso moltiplicativo**.

---

### **7. Polinomi con coefficienti in GF(2⁸)**

Una **word** da 4 byte, $[a_0, a_1, a_2, a_3]$, può essere interpretata come un **polinomio di grado ≤ 3** con coefficienti in $GF(2^8)$:

$$  
a(x) = a_3x^3 + a_2x^2 + a_1x + a_0  
$$

Analogamente per un’altra word:  
$$  
b(x) = b_3x^3 + b_2x^2 + b_1x + b_0  
$$

Le **addizioni** e **moltiplicazioni** tra word avvengono modulo $x^4 + 1$:

$$  
d(x) = a(x) \cdot b(x) \mod (x^4 + 1)  
$$

![](imgs/Pasted%20image%2020260222175021.png)

![](imgs/Pasted%20image%2020260222175216.png)

---

### **8. Polinomi utilizzati da AES**

AES utilizza in particolare i seguenti polinomi per la **trasformazione MixColumns**:

$$  
a(x) = \{03\}x^3 + \{01\}x^2 + \{01\}x + \{02\}  
$$

$$  
a^{-1}(x) = \{0b\}x^3 + \{0d\}x^2 + \{09\}x + \{0e\}  
$$

Questi polinomi **non sono irriducibili**, ma sono scelti perché garantiscono **l’invertibilità** della trasformazione e una **buona diffusione dei bit** (effetto valanga).

---

### **9. Proprietà riassuntive**

- AES opera su **byte e word (4 byte)**.
    
- Tutte le trasformazioni avvengono sulla **matrice di stato 4×4**.
    
- Ogni byte è un elemento di **$GF(2^8)$**, rappresentato come un polinomio binario.
    
- Ogni word è un polinomio di **grado < 4** con coefficienti in **$GF(2^8)$**.
    
- Tutte le moltiplicazioni sono eseguite **modulo $x^8 + x^4 + x^3 + x + 1$**.
    

---

### **10. Sintesi finale**

In questa lezione abbiamo:

- definito i **parametri fondamentali** dell’AES (Nk, Nb, Nr);
    
- introdotto la rappresentazione dei **byte** e delle **word**;
    
- descritto le operazioni nel campo finito $GF(2^8)$;
    
- visto come le **moltiplicazioni e addizioni** vengano eseguite a livello di polinomi.
    

👉 Nella prossima lezione analizzeremo nel dettaglio le **operazioni interne di ogni round**:  
**SubBytes, ShiftRows, MixColumns e AddRoundKey.**