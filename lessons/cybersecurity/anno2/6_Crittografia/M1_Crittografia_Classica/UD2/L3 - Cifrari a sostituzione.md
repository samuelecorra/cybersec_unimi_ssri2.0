# **Lezione 3: Cifrari a sostituzione**

---

### **1. Il Cifrario di Cesare**

Il **Cifrario di Cesare** è uno dei più antichi esempi di **cifrario a sostituzione monoalfabetico**.  
Ogni lettera del messaggio viene **sostituita da quella che si trova tre posizioni più avanti** nell’alfabeto.

![](imgs/Pasted%20image%2020251125003408.png)

#### **Esempio storico**

Svetonio, nella _Vita dei Cesari_, cita una lettera inviata da **Gaio Giulio Cesare** a **Cicerone** cifrata in questo modo:

```
OMNIA GALLIA EST DIVISA IN PARTES TRES
RPQLD JDOOLD HVW GLYLVD LQ SDUWHV WUHV
```


Il messaggio cifrato si ottiene spostando ogni lettera in avanti di **tre posizioni**:

| **Alfabeto in chiaro** | A   | B   | C   | D   | E   | F   | G   | H   | I   | J   | K   | L   | M   | N   | O   | P   | Q   | R   | S   | T   | U   | V   | W   | X   | Y   | Z   |     |
| ---------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| **Alfabeto cifrato**   | D   | E   | F   | G   | H   | I   | J   | K   | L   | M   | N   | O   | P   | Q   | R   | S   | T   | U   | V   | W   | X   | Y   | Z   | A   | B   | C   |     |


Questa corrispondenza si può anche esprimere in forma **matematica modulare**.

---

### **2. Cifrari a Shift (o a Scorrimento)**

Il Cifrario di Cesare è un caso particolare dei **cifrari a shift**, in cui la chiave $k$ può assumere **qualsiasi valore** tra 1 e 25.

$$  
\begin{cases}  
c_i = E(p_i) = (p_i + k) \mod 26 \\\\  
p_i = D(c_i) = (c_i - k) \mod 26  
\end{cases}  
$$

Nel caso del Cifrario di Cesare, $k = 3$.

| Lettere | A   | B   | C   | D   | E   | F   | G   | H   | I   | J   | K   | L   | M   | N   | O   | P   | Q   | R   | S   | T   | U   | V   | W   | X   | Y   | Z   |     |
| ------- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Numeri  | 0   | 1   | 2   | 3   | 4   | 5   | 6   | 7   | 8   | 9   | 10  | 11  | 12  | 13  | 14  | 15  | 16  | 17  | 18  | 19  | 20  | 21  | 22  | 23  | 24  | 25  |     |

➡️ **Significato:**  
Assegnando un numero a ciascuna lettera, possiamo **esprimere la cifratura come un’operazione matematica modulo 26**.  
In pratica, al posto di ogni simbolo $p_i$ del messaggio in chiaro, si scrive il simbolo corrispondente dell’alfabeto cifrante.

---

### **3. Cifrari a sostituzione monoalfabetici**

In generale, un **cifrario a sostituzione monoalfabetico** sostituisce **ogni lettera** del testo in chiaro con **una lettera diversa**, secondo una corrispondenza fissa e biunivoca.

#### **Esempio**

Testo in chiaro:  
`C A S A`

Testo cifrato:  
`T O P O`

L’alfabeto cifrante in questo caso può essere rappresentato come segue:

| **Alfabeto in chiaro** | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |  
|-------------------------|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|  
| **Alfabeto cifrante** | O | C | T | M | B | W | L | A | K | J | D | X | I | N | E | Y | S | U | P | F | Z | R | Q | H | V | G |

➡️ La **chiave segreta** in questo tipo di cifrario è proprio **l’ordine dell’alfabeto cifrante**.

---

### **4. Cifrari con frase chiave**

Per semplificare la memorizzazione e la trasmissione della chiave, si può derivare l’alfabeto cifrante da una **frase chiave**, eliminando le lettere ripetute.

#### **Esempio**

Frase chiave:  
`JULIUS CAESAR`

L’alfabeto cifrante risultante diventa:

| **Alfabeto in chiaro** | A | B | C | D | E | F | G | H | I | J | K | L | M | N | O | P | Q | R | S | T | U | V | W | X | Y | Z |  
|-------------------------|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|  
| **Alfabeto cifrante** | J | U | L | I | S | C | A | E | R | T | V | W | X | Y | Z | B | D | F | G | H | K | M | N | O | P | Q |

Questa tecnica permette di **derivare l’intero alfabeto cifrante** a partire da una sola parola chiave condivisa tra mittente e destinatario.

---

### **5. Il Cifrario Affine**

Il **Cifrario Affine** è un caso particolare di **cifrario a sostituzione** in cui ogni lettera del testo in chiaro $p$ viene cifrata applicando una **funzione affine** nel modulo 26:

$$  
c = E(p) = (k_1 p + k_2) \mod 26  
$$

dove:

- $k_1$ e $k_2$ sono le **due componenti della chiave**,
    
- $k_1$ deve essere **invertibile modulo 26**, cioè:
    
    $$  
    MCD(k_1, 26) = 1  
    $$
    

La funzione di **decifratura** è quindi:

$$  
p = D(c) = k_1^{-1} (c - k_2) \mod 26  
$$

dove $k_1^{-1}$ è l’inverso moltiplicativo di $k_1$ modulo 26. SI NOTI CHE INFATTI ESSENDO ARITMETICA MODULARE NON BASTA DIVIDERE PER $k_1$, ma bisogna ricavarne per forza l'inverso.

---

### **6. Esempio di cifrario affine**

Sia $k_1 = 7$ e $k_2 = 3$.

#### **Cifratura**

$$  
c = E(p) = (7p + 3) \mod 26  
$$

#### **Decifratura**

$$  
p = D(c) = 7^{-1}(c - 3) \mod 26 =
$$

$$
15(c - 3) \mod 26  
$$

Poiché $7 \times 15 \equiv 1 \ (\text{mod } 26)$, $15$ è l’inverso moltiplicativo di $7$.
Ricordiamo che per calcolare l'inverso moltiplicativo mod $n$ in questi casi si può banalmente procedere per tentativi...

Esempio pratico:

- Testo in chiaro: `HOT`
    
- Rappresentazione numerica: `H=7, O=14, T=19`
    
- Cifratura:  
    $$  
    (7×7 + 3) \mod 26 = 0 = a
    $$
    $$
    (7×14 + 3) \mod 26 = 23 = x
    $$
    $$
    (7×19 + 3) \mod 26 = 6 = g
    $$
    
- Testo cifrato: **A X G**
    

---

### **7. Condizioni sulla chiave**

Affinché la decifratura sia sempre possibile, deve esistere l’inverso $k_1^{-1}$.  
Questo accade **solo se $k_1$ e 26 sono coprimi**, cioè:

$$  
\gcd(k_1, 26) = 1  
$$

Invece, $k_2$ può assumere **qualsiasi valore** da 0 a 25.

---

### **8. In sintesi**

In questa lezione abbiamo studiato i principali **cifrari a sostituzione monoalfabetica**:

- Il **Cifrario di Cesare**, basato su uno shift costante.
    
- I **Cifrari a shift generici**, con chiave variabile $k$.
    
- I **Cifrari con frase chiave**, che generano un alfabeto cifrante personalizzato.
    
- Il **Cifrario Affine**, che utilizza una funzione matematica lineare modulo 26.
    

Tutti questi metodi si basano su un **principio comune**:  
a ogni simbolo del messaggio in chiaro corrisponde **una sola** lettera nel messaggio cifrato.