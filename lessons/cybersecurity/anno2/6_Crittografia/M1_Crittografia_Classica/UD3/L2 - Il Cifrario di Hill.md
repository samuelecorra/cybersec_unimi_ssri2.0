# **Lezione 2: Il Cifrario di Hill**

---

### **1. Origini e principio generale**

Il **Cifrario di Hill** è un cifrario **multilettera** inventato nel **1929** da **Lester S. Hill**.  
A differenza dei cifrari monoalfabetici o polialfabetici classici, Hill utilizza l’**algebra lineare** per cifrare gruppi di lettere contemporaneamente.

#### **Idea fondamentale**

- Si considerano blocchi di $m$ lettere consecutive del testo in chiaro.
    
- Ogni blocco è rappresentato come **vettore numerico** di lunghezza $m$.
    
- La cifratura avviene tramite una **moltiplicazione matriciale modulo 26**:
    

$$  
Y = E_K(X) = X \cdot K \pmod{26}  
$$

dove:

- $X$ = vettore del testo in chiaro,
    
- $Y$ = vettore del testo cifrato,
    
- $K$ = matrice chiave di dimensione $m \times m$.
    

---

### **2. Esempio per m = 2**

Consideriamo il caso più semplice, con blocchi di **due lettere**.

$$  
\begin{cases}  
y_1 = 11x_1 + 3x_2 \pmod{26} \\\\  
y_2 = 8x_1 + 7x_2 \pmod{26}  
\end{cases}  
$$

In forma matriciale:

$$  
\begin{pmatrix}  
y_1 & y_2  
\end{pmatrix}
=
\begin{pmatrix}  
x_1 & x_2  
\end{pmatrix}  
\begin{pmatrix}  
11 & 8 \\  
3 & 7  
\end{pmatrix}  
\pmod{26}  
$$

Questa matrice $K$ è la **chiave segreta** del cifrario.

Ricordiamo brevemente che per la moltiplicazione matriciale...
Il risultato $Y$ è ancora un vettore riga:

$$
Y = (y_1, y_2)
$$

e si calcola così:

$$

y_1 = x_1 k_{11} + x_2 k_{21}

$$
ovvero moltiplichiamo e sommiamo la coppia di x per la prima colonna e poi idem con la seconda colonna:
$$

y_2 = x_1 k_{12} + x_2 k_{22}

$$

ATTENZONE: **solo alla fine**, si fa il modulo:

$$

y_1 \equiv y_1 \pmod{26}, \qquad y_2 \equiv y_2 \pmod{26}

$$

---

### **3. Cifratura**

Data la matrice chiave

$$  
K =  
\begin{pmatrix}  
11 & 8 \\  
3 & 7  
\end{pmatrix}  
$$

e il testo in chiaro **"CIAO"**, suddividiamo il messaggio in blocchi da due lettere:

|Testo|C|I|A|O|
|---|---|---|---|---|
|Numero|2|8|0|14|

Ora calcoliamo la cifratura per ciascun blocco:



#### **Primo blocco (C, I) → (2, 8)**

$$
Y = (2, 8)
\begin{pmatrix}
11 & 8 \\
3 & 7
\end{pmatrix}
=
\left(2 \cdot 11 + 8 \cdot 3,\; 2 \cdot 8 + 8 \cdot 7 \right)
=
$$

$$
(22 + 24,\; 16 + 56)
=
(46,\; 72)
\equiv
(20,\; 20)
\pmod{26}
$$
#### **Secondo blocco (A, O) → (0, 14)**

$$  
Y = (0, 14)  
\begin{pmatrix}  
11 & 8 \\  
3 & 7  
\end{pmatrix} =

(0 + 42,\ 0 + 98) = (42,\ 98) \equiv (16,\ 20) \pmod{26}  
$$

Convertendo i numeri in lettere:

| Numero  | 20  | 20  | 16  | 20  |
| ------- | --- | --- | --- | --- |
| Lettera | U   | U   | Q   | U   |


---

### **4. Decifratura**

Per decifrare, serve la **matrice inversa modulo 26**, $K^{-1}$, tale che:

$$  
K \cdot K^{-1} = K^{-1} \cdot K = I  
$$

Nel nostro esempio:

$$  
K^{-1} =  
\begin{pmatrix}  
7 & 18 \\  
23 & 11  
\end{pmatrix}  
$$

Eseguiamo ora la decifratura del testo cifrato `UUQU` (cioè $(20,20),(16,20)$):

#### **Primo blocco**

$$
X = Y K^{-1}
= (20, 20)
\begin{pmatrix}
7 & 18 \\
23 & 11
\end{pmatrix}
=
\left(20\cdot 7 + 20\cdot 23,\; 20\cdot 18 + 20\cdot 11\right)
=
$$

$$
(140 + 460,\; 360 + 220)
=
(600,\; 580)
\equiv
(2,\; 8)
\pmod{26}
$$

→ `C I`

#### **Secondo blocco**

$$  
(16,20)  
\begin{pmatrix}  
7 & 18 \\  
23 & 11  
\end{pmatrix} =

(112 + 460,\ 288 + 220) = (572,508) \equiv (0,14) \pmod{26}  
$$

→ `A O`

✅ **Testo decifrato:** `CIAO`

---

### **5. Invertibilità della matrice chiave**

Affinché la cifratura sia **invertibile**, la matrice $K$ deve avere un **determinante invertibile modulo 26**.

Condizione generale è che sian coprimi (gdc = greatest common divisor!)

$$  
\gcd(\det(K), 26) = 1  
$$

In questo caso, per una matrice 2x2 si fa prodotto diag principale - prodotto diag secondaria

$$  
\det(K) = 11 \times 7 - 8 \times 3 = 77 - 24 = 53 \equiv 1 \pmod{26}  
$$

Poiché $\gcd(1,26) = 1$, la matrice è invertibile.

---

### **6. Come calcolare l’inversa modulo 26**

Per una matrice $2 \times 2$:

$$  
A =  
\begin{pmatrix}  
a & b \\  
c & d  
\end{pmatrix}  
\Rightarrow  
A^{-1} = (\det(A))^{-1}  
\begin{pmatrix}  
d & -b \\  
-c & a  
\end{pmatrix}  
\pmod{26}  
$$

Nell’esempio:

$$  
(\det K)^{-1} = 1^{-1} = 1  
$$

e quindi:

$$  
K^{-1} =  
\begin{pmatrix}  
7 & -8 \\  
-3 & 11  
\end{pmatrix}  
\equiv  
\begin{pmatrix}  
7 & 18 \\  
23 & 11  
\end{pmatrix}  
\pmod{26}  
$$

---

### **7. Attacco al cifrario di Hill**

Il **cifrario di Hill** può essere violato tramite un **attacco Known Plaintext**, se l’avversario conosce:

- la **dimensione $m$** della matrice, e
    
- almeno **$m$ coppie distinte** di vettori $(X_i, Y_i)$ di testo in chiaro e cifrato.
    

Infatti, avendo:  
$$  
Y = X K  
$$  
si può moltiplicare entrambi i lati per $X^{-1}$:

$$  
X^{-1}Y = K  
$$

→ ottenendo direttamente la matrice chiave $K$.

Esempio: la matrice a sx è il testo in chiaro, quella a destra il cifrato. Non si sa la chiave MA...

$$
\begin{pmatrix}
5 & 17 \\
4 & 3
\end{pmatrix}
K
=
\begin{pmatrix}
2 & 3 \\
1 & 1
\end{pmatrix}
$$

Invertiamo la prima:

$$
\begin{pmatrix}
5 & 17 \\
4 & 3
\end{pmatrix}^{-1}
=
\begin{pmatrix}
23 & 17 \\
4 & 21
\end{pmatrix}
$$

Ora la moltiplichiamo per quella cifrata e otteniamo la matrice chiave di partenza!

$$
K =
\begin{pmatrix}
23 & 17 \\
4 & 21
\end{pmatrix}
\begin{pmatrix}
2 & 3 \\
1 & 1
\end{pmatrix}
=
\begin{pmatrix}
11 & 8 \\
3 & 7
\end{pmatrix}
$$

---

### **8. Proprietà matematiche**

Una matrice $A$ di ordine $n$ è **invertibile modulo m** se e solo se:

- $\det(A) \neq 0 \pmod{p}$ per ogni **divisore primo** $p$ di $m$.
    
- Nel nostro caso, con $m=26$, i divisori primi sono 2 e 13.
    

Quindi, la matrice è invertibile se:

$$  
\det(A) \neq 0 \pmod{2} \quad \text{e} \quad \det(A) \neq 0 \pmod{13}  
$$

---

### **9. In sintesi**

- Il **Cifrario di Hill** è una cifratura **multilettera lineare** basata su **algebra modulare**.
    
- È più robusto dei cifrari monoalfabetici perché:
    
    - elimina la corrispondenza fissa tra lettere,
        
    - e nasconde la frequenza dei singoli simboli.
        
- Tuttavia, resta vulnerabile a **Known Plaintext Attacks** se si raccolgono abbastanza coppie $(X,Y)$.
    
- È un passaggio cruciale verso la **crittografia moderna**, dove algebra lineare e teoria dei numeri diventano strumenti centrali.