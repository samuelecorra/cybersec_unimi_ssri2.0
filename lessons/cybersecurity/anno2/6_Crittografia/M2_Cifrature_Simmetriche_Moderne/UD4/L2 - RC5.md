# **Lezione 2: RC5**

---

### **1. Introduzione**

L’**RC5** è un cifrario simmetrico ideato da **Ron Rivest nel 1995** per la **RSA Data Security Inc.**  
È un algoritmo estremamente **flessibile e parametrico**, pensato per essere **facile da implementare** e **veloce** su processori di uso comune.

#### **Caratteristiche generali**

- **Struttura:** non di tipo Feistel.
    
- **Flessibilità:** basato su tre parametri configurabili:
    
    - $w$ → lunghezza delle **word** (16, 32 o 64 bit)
        
    - $r$ → numero di **round** (da 0 a 255)
        
    - $b$ → lunghezza della **chiave** in byte (da 0 a 255)
        
- **Designazione completa:**  
    $RC5\text{-}w/r/b$ (es. _RC5–32/12/16_)
    

---

### **2. Proprietà e vantaggi**

- Utilizza solo **operazioni elementari dei processori moderni**: somma, XOR e rotazioni.
    
- Le **rotazioni dipendono dai dati** (_data-dependent rotations_), rendendo il cifrario più imprevedibile.
    
- Richiede **poca memoria** → ideale per **smart card** e dispositivi embedded.
    
- È **semplice da analizzare** e **molto efficiente**.
    
- È stato incluso in vari prodotti della RSA: **BSAFE, JSAFE, S/Mail**.
    

---

### **3. Operazioni fondamentali**

RC5 lavora su **parole di w bit** e utilizza le seguenti operazioni:

$$  
\begin{cases}  
a + b &\text{somma modulo } 2^w \\  
a - b &\text{sottrazione modulo } 2^w \\  
a \oplus b &\text{XOR bit a bit} \\  
a \ll b &\text{rotazione a sinistra di } a \text{ di } b \text{ bit} \\  
a \gg b &\text{rotazione a destra di } a \text{ di } b \text{ bit}  
\end{cases}  
$$

> Il numero di bit di rotazione è determinato dai **log₂(w)** bit meno significativi dell’operando $b$.

---

### **4. Struttura generale del cifrario**

#### **Input e parametri**

- Testo in chiaro: due parole $(A, B)$ di $w$ bit ciascuna
    
- Chiave: $b$ byte
    
- Round: $r$
    
- Sottochiavi generate: $2r + 2$, ciascuna di $w$ bit
    

---

### **5. Cifratura**

#### **Algoritmo**

![](imgs/Pasted%20image%2020260222194139.png)

```text
A ← A + S[0]
B ← B + S[1]
for i = 1 to r do
    A ← ((A ⊕ B) <<< B) + S[2i]
    B ← ((B ⊕ A) <<< A) + S[2i + 1]
```

- $S[0 \dots 2r + 1]$ è l’array delle sottochiavi (word da $w$ bit)
    
- Ogni round aggiorna **entrambe** le metà del blocco
    
- L’operazione `<<<` indica una **rotazione a sinistra**
    

---

### **6. Decifratura**

![](imgs/Pasted%20image%2020260222194416.png)

#### **Algoritmo inverso**

```text
for i = r downto 1 do
    B ← ((B - S[2i + 1]) >>> A) ⊕ A
    A ← ((A - S[2i]) >>> B) ⊕ B
B ← B - S[1]
A ← A - S[0]
```

> `>>>` indica la **rotazione a destra**.

La struttura è **simmetrica**: stessa logica, ma con operazioni e sottochiavi in ordine inverso.

---

### **7. Architettura e rappresentazione dei dati**

RC5 è progettato per architetture **little-endian** (es. Intel).  
Ciò significa che, in un valore a 32 bit, il **byte meno significativo** è memorizzato per primo.

Esempio:

|Architettura|Byte order|Esempio (a₁a₂a₃a₄)|
|---|---|---|
|Little-endian (Intel)|$a_1 + a_2 2^8 + a_3 2^{16} + a_4 2^{24}$||
|Big-endian (SPARC)|$a_4 + a_3 2^8 + a_2 2^{16} + a_1 2^{24}$||

---

### **8. Schedulazione della chiave**

#### **8.1 Struttura degli array**

- **K[0 … b–1]** → chiave originale (in byte)
    
- **L[0 … c–1]** → array di $c = \lceil 8b / w \rceil$ parole da $w$ bit
    
- **S[0 … 2r+1]** → array di sottochiavi generate

![](imgs/Pasted%20image%2020260222194632.png)

Se $8b$ non è multiplo di $w$, la chiave viene **padded con zeri** fino a raggiungere il multiplo.

---

#### **8.2 Inizializzazione**

Vengono definite due costanti magiche dipendenti da $w$:

- $P_w = \text{Odd}[(e - 2) \cdot 2^w]$
    
- $Q_w = \text{Odd}[(\varphi - 1) \cdot 2^w]$
    

dove:

- $e = 2.718281828459045...$ (numero di Nepero)
    
- $\varphi = (1 + \sqrt{5}) / 2 = 1.6180339887...$ (rapporto aureo)
    
- $\text{Odd}[x]$ = intero **dispari più vicino** a $x$.

|w (bit)|$P_w$|$Q_w$|
|---|---|---|
|16|b7e1|9e37|
|32|b7e15163|9e3779b9|
|64|b7e151628aed2a6b|9e3779b97f4a7c15|

---

#### **8.3 Inizializzazione array S**

```text
S[0] = P_w
for i = 1 to 2r + 1 do
    S[i] = S[i - 1] + Q_w
```

![](imgs/Pasted%20image%2020260222194818.png)

---

#### **8.4 Mixing function (funzione di mescolamento)**

Serve per combinare in modo non lineare gli array **S** e **L**.

```text
X = Y = 0
i = j = 0
repeat 3 * max(c, 2r + 1) times
    X = S[i] = (S[i] + X + Y) <<< 3
    Y = L[j] = (L[j] + X + Y) <<< (X + Y)
    i = (i + 1) mod (2r + 1)
    j = (j + 1) mod c
```

---

### **9. Modalità operative**

RC5 può essere utilizzato in varie modalità di cifratura a blocchi:

|Modalità|Descrizione|
|---|---|
|**RC5-ECB**|Blocchi di input fissi da $2w$ bit|
|**RC5-CBC**|Blocchi concatenati multipli di $2w$|
|**RC5-CBC PAD**|Variante CBC con padding dell’ultimo blocco|
|**RC5-CTS**|Blocchi di lunghezza variabile, senza padding|

---

### **10. Sintesi finale**

Abbiamo visto:

- La **struttura parametrica** dell’RC5 e i suoi tre parametri $w/r/b$;
    
- Le **operazioni aritmetiche e logiche** fondamentali;
    
- La **schedulazione della chiave** basata su $P_w$ e $Q_w$ (da $e$ e $\varphi$);
    
- Le **funzioni di cifratura e decifratura** con rotazioni dipendenti dai dati;
    
- Le **diverse modalità operative**.
    

➡️ **RC5** è un cifrario compatto, efficiente e altamente flessibile, che ha ispirato lo sviluppo di **RC6** e altri algoritmi moderni.