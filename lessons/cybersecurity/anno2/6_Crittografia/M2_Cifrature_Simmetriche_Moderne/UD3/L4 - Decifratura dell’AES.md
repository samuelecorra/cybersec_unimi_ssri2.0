# **Lezione 4: Decifratura dell’AES**

---

### **1. Differenze rispetto alla cifratura**

L’algoritmo di **decifratura** dell’AES **non è identico** a quello di cifratura.  
Utilizza:

- una **sequenza differente** di trasformazioni,
    
- le **trasformazioni inverse** di quelle viste in precedenza.
    

Lo **svantaggio principale** è che ciò richiede una **doppia implementazione**:  
una per la cifratura e una per la decifratura.

![](imgs/Pasted%20image%2020260222183312.png)

---

### **2. Pseudocodice della decifratura AES**

```text
InvCipher(byte in[4*Nb], byte out[4*Nb], word w[Nb*(Nr+1)])
{
    byte state[4, Nb]
    state ← in
    AddRoundKey(state, w + Nr * Nb)
    for round = Nr - 1 step -1 to 1 {
        InvShiftRows(state)
        InvSubBytes(state)
        AddRoundKey(state, w + round * Nb)
        InvMixColumns(state)
    }
    InvShiftRows(state)
    InvSubBytes(state)
    AddRoundKey(state, w)
    out ← state
}
```

Come si nota, la **sequenza delle operazioni** è invertita rispetto alla cifratura, e ciascuna trasformazione viene sostituita dalla sua **funzione inversa**.

---

### **3. Trasformazioni inverse**

#### **3.1 InvShiftRows**

Effettua uno **shift ciclico verso destra** delle righe della matrice di stato.  

![](imgs/Pasted%20image%2020260222183817.png)

Formalmente:

$$  
S'_{r,c} = S_{r,(c - \text{shift}(r,Nb)) \bmod Nb}  
$$

dove:

$$  
\text{shift}(r,4) =  
\begin{cases}  
0 & r = 0 \\  
1 & r = 1 \\  
2 & r = 2 \\  
3 & r = 3  
\end{cases}  
$$

In pratica:

- la prima riga resta invariata,
    
- la seconda viene ruotata di **1 byte a destra**,
    
- la terza di **2 byte**,
    
- la quarta di **3 byte**.
    

---

#### **3.2 InvSubBytes**

Sostituisce ogni byte della matrice di stato con il suo corrispondente secondo la **Inverse S-box**:

![](imgs/Pasted%20image%2020260222183838.png)

$$  
S'_{r,c} = S^{-1}\text{-box}(S_{r,c})  
$$

La **Inverse S-box** è costruita come la S-box diretta ma invertendo il processo:

- si calcola prima la **trasformazione affine inversa**,
    
- poi si prende l’**inverso moltiplicativo** nel campo $GF(2^8)$.
    

---

#### **3.3 InvMixColumns**

Effettua la moltiplicazione di ciascuna colonna della matrice di stato per una **matrice fissa** nel campo $GF(2^8)$:

![](imgs/Pasted%20image%2020260222184001.png)

$$  
\begin{bmatrix}  
S'_{0,c} \\ S'_{1,c} \\ S'_{2,c} \\ S'_{3,c}  
\end{bmatrix}

\begin{bmatrix}  
0e & 0b & 0d & 09 \\  
09 & 0e & 0b & 0d \\  
0d & 09 & 0e & 0b \\  
0b & 0d & 09 & 0e  
\end{bmatrix}  
\cdot  
\begin{bmatrix}  
S_{0,c} \\ S_{1,c} \\ S_{2,c} \\ S_{3,c}  
\end{bmatrix}  
$$

In forma polinomiale:

$$  
S'_c(x) = a^{-1}(x) \cdot S_c(x) \mod (x^4 + 1)  
$$

con:

$$  
a^{-1}(x) = {0b}x^3 + {0d}x^2 + {09}x + {0e}  
$$

Tutte le operazioni avvengono nel campo finito $GF(2^8)$ con polinomio irriducibile:  
$$  
m(x) = x^8 + x^4 + x^3 + x + 1  
$$

---

#### **3.4 AddRoundKey**

Come nella cifratura, combina lo **stato** e la **chiave di round** tramite XOR:

![](imgs/Pasted%20image%2020260222184022.png)

$$  
S'_{r,c} = S_{r,c} \oplus W_{r,c}  
$$

Questa trasformazione è **auto-inversa** (cioè identica nella cifratura e nella decifratura).

---

### **4. Variante strutturalmente equivalente**

Esiste anche una versione alternativa della decifratura, con la **stessa struttura della cifratura**, che:

- utilizza **le trasformazioni inverse**,
    
- ma richiede una **diversa schedulazione delle chiavi** (cioè una diversa generazione dell’ordine delle sottochiavi).
    

In questo caso, **l’ordine delle operazioni resta identico**, ma ciascuna viene sostituita con la sua funzione inversa.

---

### **5. Proprietà di scambio tra trasformazioni**

Per ottimizzare l’implementazione, si possono **scambiare** alcune trasformazioni senza alterare il risultato.

#### **Scambio tra InvShiftRows e InvSubBytes**

$$  
\text{InvShiftRows}(\text{InvSubBytes}(S_i)) = \text{InvSubBytes}(\text{InvShiftRows}(S_i))  
$$

#### **Scambio tra AddRoundKey e InvMixColumns**

$$  
\text{InvMixColumns}(S_i \oplus W_j) = \text{InvMixColumns}(S_i) \oplus \text{InvMixColumns}(W_j)  
$$

> La trasformazione **InvMixColumns non viene applicata alla prima e all’ultima chiave di fase**.

---

### **6. Aspetti implementativi**

#### **Microprocessori a 8 bit**

- **ShiftRows:** semplice operazione di scorrimento.
    
- **AddRoundKey:** XOR tra byte.
    
- **SubBytes:** richiede una tabella di **256 byte**.
    
- **MixColumns:** moltiplicazione matriciale in $GF(2^8)$,  
    implementabile tramite **shift e XOR condizionati**.
    

#### **Microprocessori a 32 bit**

- Operazioni su **word da 32 bit**.
    
- Ogni fase può essere espressa come **una singola trasformazione combinata** (somma lineare di vettori).
    
- Si usano **4 tabelle** di 256 word (4 × 1 KB) per la cifratura,  
    e tabelle analoghe con **Inverse S-box** per la decifratura.
    

> Per l’ultima fase è sufficiente una sola tabella con tre rotazioni.  
> Ogni colonna dell’output richiede **4 XOR e 4 lookup** di tabella.

---

### **7. Sintesi finale**

Abbiamo visto:

- Le **trasformazioni inverse** dell’AES.
    
- Le **differenze strutturali** tra cifratura e decifratura.
    
- Le **ottimizzazioni** che permettono di ridurre le implementazioni doppie.
    

👉 Nella **decifratura**:

- si utilizzano **InvSubBytes**, **InvShiftRows**, **InvMixColumns** e **AddRoundKey**,
    
- in **ordine inverso** rispetto alla cifratura.
    

➡️ È possibile anche adottare una versione strutturalmente equivalente,  
che conserva la sequenza originale ma applica trasformazioni inverse con **chiavi rischedulate**.