# **Lezione 1: Blowfish**

---

### **1. Introduzione e contesto storico**

Negli anni successivi al DES e prima dell’AES furono sviluppati numerosi **cifrari simmetrici a blocchi**, tra cui:

- **RC2** (1989)
    
- **IDEA** (_International Data Encryption Algorithm_, 1990)
    
- **Blowfish** (1993)
    
- **SAFER** (_Secure And Fast Encryption Routine_, 1994–1995)
    
- **RC5** (1994)
    
- **RC6** (1998)
    
- Altri: _Madryga, NDS, FEAL, REDOC, LOKI, Khufu, Knafre, MMB, GOST..._
    

---

### **2. Specifiche di Blowfish**

**Progettista:** Bruce Schneier (1993)  
**Struttura:** cifrario di tipo **Feistel**  
**Caratteristiche principali:**

|Proprietà|Valore|
|---|---|
|Dimensione blocco|64 bit|
|Numero di round|16|
|Lunghezza chiave|da 1 a 14 word (da 32 a 448 bit)|
|Tipo di operazioni|XOR, somma mod $2^{32}$|
|Efficienza|≈ 18 cicli di clock per byte (su CPU a 32 bit)|
|Memoria richiesta|< 5 KB|
|Implementazione|semplice e veloce|

> Blowfish è progettato per essere **libero da brevetti**, **open-source** e facilmente **implementabile in software**.

---

### **3. Struttura generale**

Blowfish opera su due metà di 32 bit:

$$  
L_0, R_0 \rightarrow L_{17}, R_{17}  
$$

Ogni round applica la funzione $F$ a una metà e la combina con l’altra tramite XOR e somme modulo $2^{32}$.

#### **Algoritmo di cifratura**

```text
for i = 1 to 16 do
    R_i = L_{i-1} ⊕ P_i
    L_i = F(R_i) ⊕ R_{i-1}
end for

L_{17} = R_{16} ⊕ P_{18}
R_{17} = L_{16} ⊕ P_{17}
```

---

### **4. Espansione della chiave**

Blowfish utilizza:

- **un array di 18 sottochiavi (P-array)**, ciascuna di 32 bit;
    
- **4 S-box**, ognuna di dimensione 8×32 (256 word da 32 bit).
    
![](imgs/Pasted%20image%2020260222191356.png)

#### **Schema di generazione**

- La chiave originale $K = (K_1, K_2, \dots, K_j)$ con $1 \le j \le 14$ viene espansa in:
    
    - un **P-array** da 18 word: $(P_1, P_2, \dots, P_{18})$
        
    - quattro **S-box**:  
        $S_1[0..255]$, $S_2[0..255]$, $S_3[0..255]$, $S_4[0..255]$
        

---

#### **4.1 Inizializzazione**

1. Si inizializzano **P-array e S-box** con le **cifre esadecimali della parte frazionaria di π**.
    

Esempio:

$$  
P_1 = 243F6A88, \quad P_2 = 85A308D3, \dots, S_{4,255} = 3AC372E6  
$$

2. Si esegue lo **XOR** tra il P-array e le parole della chiave (ciclicamente):
    

$$  
\begin{cases}  
P_1 = P_1 \oplus K_1 \\  
P_2 = P_2 \oplus K_2 \\  
\vdots \\  
P_{14} = P_{14} \oplus K_{14} \\  
P_{15} = P_{15} \oplus K_1 \\  
\vdots \\  
P_{18} = P_{18} \oplus K_4  
\end{cases}  
$$

---

#### **4.2 Generazione finale**

Si definisce $E_{P,S}[Y]$ come la **cifratura di Y con i valori correnti di P e S**.

Si calcolano successivamente:

$$  
\begin{cases}  
P_1, P_2 = E_{P,S}[0] \\  
P_3, P_4 = E_{P,S}[P_1 || P_2] \\  
\vdots \\  
P_{17}, P_{18} = E_{P,S}[P_{15} || P_{16}] \\  
\end{cases}  
$$

e analogamente per le S-box:

$$  
S_{1,0}, S_{1,1} = E_{P,S}[P_{17} || P_{18}], \quad \dots, \quad S_{4,254}, S_{4,255} = E_{P,S}[S_{4,252} || S_{4,253}]  
$$

> **Totale:** 521 cifrature necessarie per generare tutti gli array P e S.  
> **Conseguenza:** Blowfish non è adatto quando la chiave cambia frequentemente.  
> Tuttavia, le tabelle P e S possono essere **precalcolate e memorizzate** (circa 4 KB).

![](imgs/Pasted%20image%2020260222192021.png)

![](imgs/Pasted%20image%2020260222192115.png)

---

### **5. Funzione di cifratura F**

Ogni round utilizza una funzione $F$ che mescola i bit attraverso S-box e operazioni modulari.

1. L’input a 32 bit di $F$ è diviso in quattro byte $(a, b, c, d)$
    
2. L’output è calcolato come:
    

$$  
F(a,b,c,d) = ((S_1[a] + S_2[b]) \bmod 2^{32} \oplus S_3[c]) + S_4[d] \bmod 2^{32}  
$$

![](imgs/Pasted%20image%2020260222192248.png)

Ogni round coinvolge quindi **tutti i 64 bit del blocco**, a differenza del DES (dove solo metà del blocco viene elaborata per volta).

---

### **6. Decifratura**

La procedura è identica alla cifratura, ma le sottochiavi vengono applicate in **ordine inverso**.

#### **Algoritmo di decifratura**

![](imgs/Pasted%20image%2020260222192403.png)

```text
for i = 1 to 16 do
    R_i = L_{i-1} ⊕ P_{19-i}
    L_i = F(R_i) ⊕ R_{i-1}
end for

L_{17} = R_{16} ⊕ P_1
R_{17} = L_{16} ⊕ P_2
```

---

### **7. Caratteristiche di sicurezza**

- Sia le **sottochiavi** sia le **S-box** dipendono direttamente dalla **chiave segreta**  
    → rende Blowfish molto **resistente agli attacchi differenziali e lineari**.
    
- Ogni round coinvolge **l’intero blocco**, garantendo una diffusione più rapida rispetto al DES.
    
- È **immune a brute-force** con chiavi da 448 bit.
    
    > Testare una sola chiave richiede 522 esecuzioni dell’algoritmo (inclusa l’espansione).
    
- Nel 1995 Bruce Schneier offrì **1000 dollari** a chi avesse trovato un attacco efficace:  
    → solo **versioni ridotte** di Blowfish sono state parzialmente vulnerabili (Vaudenay).
    

---

### **8. Confronto con altri cifrari**

|Algoritmo|Cicli/byte|Fasi|Cicli/fase|
|---|---|---|---|
|Blowfish|18|16|9|
|RC5|23|12|16|
|DES|45|16|18|
|IDEA|50|8|50|
|Triple-DES|108|48|18|

➡️ **Conclusione:** Blowfish è uno dei **più efficienti cifrari a blocchi** del periodo pre-AES,  
con un eccellente equilibrio tra **velocità, sicurezza e semplicità di implementazione**.

---

### **9. Sintesi finale**

Abbiamo visto:

- la **struttura Feistel** e il **funzionamento** del Blowfish;
    
- la **procedura di espansione della chiave** basata su π;
    
- la **funzione F** che garantisce confusione e diffusione;
    
- le **proprietà di sicurezza e prestazioni** superiori rispetto ai cifrari precedenti.
    

➡️ Blowfish rimane uno dei cifrari **più studiati e affidabili** del XX secolo, base concettuale per successori come **Twofish** e l’attuale **AES**.