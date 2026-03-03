# **Lezione 3: Il Cifrario di Vigenère**

---

### **1. Introduzione**

Il **Cifrario di Vigenère** è un **cifrario polialfabetico** ideato nel **1586** da **Blaise de Vigenère**.  
Rappresenta un’evoluzione del **Cifrario di Cesare**, perché **utilizza più cifrature di Cesare**, una per ogni lettera della chiave.

#### **Principio di base**

- Ogni **cifratura di Cesare** corrisponde a un **diverso scorrimento dell’alfabeto**.
    
- La **chiave** stabilisce quale scorrimento applicare a ciascuna lettera del testo in chiaro.
    
- La chiave viene **ripetuta ciclicamente** fino a coprire l’intera lunghezza del messaggio.
    

---

### **2. Esempio di cifratura**

|**Testo in chiaro**|C|O|D|I|C|E|M|O|L|T|O|S|I|C|U|R|O|
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|**Chiave**|R|E|B|U|S|R|E|B|U|S|R|E|B|U|S|R|E|
|**Testo cifrato**|T|S|E|C|U|V|Q|P|F|L|F|W|J|W|M|I|S|
SI SOMMA SEMPRE IN MOD26 OVVIAMENTE!!!

Esempio C + R = 2 + 17 = 19mod26 = T

💡 **Risultato:**

> Testo in chiaro → `CODICE MOLTO SICURO`  
> Chiave → `REBUS`  
> Testo cifrato → `TSECU VQPFL FWJWM IS`

---

### **3. Il Quadrato di Vigenère**

Per semplificare la cifratura, Vigenère introdusse una **tabella di 26 alfabeti** disposti in modo **ciclicamente shiftato**:  
ogni riga rappresenta uno **scorrimento diverso** dell’alfabeto.

|       | **A** | **B** | **C** | **D** | **E** | **F** | **G** | **H** | **I** | **J** | **K** | **L** | **M** | **N** | **O** | **P** | **Q** | **R** | **S** | **T** | **U** | **V** | **W** | **X** | **Y** | **Z** |     |
| ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | --- |
| **A** | A     | B     | C     | D     | E     | F     | G     | H     | I     | J     | K     | L     | M     | N     | O     | P     | Q     | R     | S     | T     | U     | V     | W     | X     | Y     | Z     |     |
| **B** | B     | C     | D     | E     | F     | G     | H     | I     | J     | K     | L     | M     | N     | O     | P     | Q     | R     | S     | T     | U     | V     | W     | X     | Y     | Z     | A     |     |
| **C** | C     | D     | E     | F     | G     | H     | I     | J     | K     | L     | M     | N     | O     | P     | Q     | R     | S     | T     | U     | V     | W     | X     | Y     | Z     | A     | B     |     |
| **D** | D     | E     | F     | G     | H     | I     | J     | K     | L     | M     | N     | O     | P     | Q     | R     | S     | T     | U     | V     | W     | X     | Y     | Z     | A     | B     | C     |     |
| **E** | E     | F     | G     | H     | I     | J     | K     | L     | M     | N     | O     | P     | Q     | R     | S     | T     | U     | V     | W     | X     | Y     | Z     | A     | B     | C     | D     |     |
| ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   | ...   |       |     |

![](imgs/Pasted%20image%2020260221194703.png)

💡 Ogni **riga** è una **versione ruotata** dell’alfabeto:  
la riga **R** inizia da R, la riga **E** da E, e così via.

---

### **4. Cifratura e decifratura**

#### **Cifratura**

Per cifrare una lettera:

1. Si prende la **lettera del testo in chiaro** (colonna in alto).
    
2. Si prende la **lettera della chiave** (riga a sinistra).
    
3. La **lettera cifrata** è quella all’**intersezione** tra riga e colonna.
    

#### **Decifratura**

Per decifrare:

1. Si prende la **lettera della chiave** (riga).
    
2. Si cerca la **lettera cifrata** in quella riga.
    
3. Si risale alla **lettera in chiaro** leggendo l’intestazione della colonna corrispondente.
    

---

### **5. Formulazione matematica**

Indicando:

- $M = M_0 M_1 M_2 … M_n$ come il **testo in chiaro**,
    
- $K = K_0 K_1 K_2 … K_{t-1}$ come la **chiave**,
    
- $C = C_0 C_1 C_2 … C_n$ come il **testo cifrato**,

![](imgs/Pasted%20image%2020260221195046.png)

la relazione di cifratura è:

$$  
C_i = (M_i + K_i) \mod 26  
$$

e la decifratura:

$$  
M_i = (C_i - K_i) \mod 26  
$$

---

### **6. Esempio numerico**

Cifriamo la parola `COD` con la chiave `REB`.

|Lettera|C|O|D|
|---|---|---|---|
|Numero (A=0)|2|14|3|
|Chiave|R|E|B|
|Numero chiave|17|4|1|
|Operazione|2+17|14+4|3+1|
|Mod 26|19|18|4|
|Risultato|T|S|E|

✅ **Testo cifrato:** `TSE`

---

### **7. Sicurezza del Cifrario di Vigenère**

Per una chiave di lunghezza $t$:

- Esistono $26^t$ possibili chiavi → una **ricerca esaustiva** è **impraticabile** anche per $t$ modesti.
    
- Ogni lettera del testo in chiaro può essere cifrata in **t modi diversi**,  
    rendendo inefficace la semplice **analisi di frequenza**.
    

Tuttavia, se la chiave è **troppo corta** o **ripetitiva**, il cifrario è vulnerabile all’**attacco di Kasiski** o a metodi basati su **correlazioni periodiche**.

---

### **8. In sintesi**

- Il **Cifrario di Vigenère** è una generalizzazione del **Cifrario di Cesare** con chiave variabile.
    
- Ogni lettera del messaggio in chiaro viene cifrata con **un diverso alfabeto di Cesare**, scelto in base alla chiave.
    
- È uno dei primi esempi di **cifrario realmente polialfabetico**,  
    capace di resistere all’analisi di frequenza semplice.
    
- Rappresenta un **ponte concettuale** tra la **crittografia classica** e quella **moderna** basata su chiavi più complesse.