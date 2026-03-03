
---

# **Lezione 1: Struttura del DES**

### **1. Cenni storici**

Il **Data Encryption Standard (DES)** nasce nei primi anni ’70 come risposta alla necessità di uno **standard di cifratura nazionale** negli Stati Uniti.

- **15 maggio 1973:** la **NBS** (oggi **NIST**) pubblica la prima richiesta per uno standard crittografico pubblico.
    
- **27 agosto 1974:** viene aperta una seconda chiamata.
    
- **1975:** IBM presenta una versione modificata del proprio cifrario **Lucifer** (1971), riducendo la lunghezza della chiave da 128 a **56 bit**.
    
- **1976:** due workshop pubblici discutono il progetto.
    
- **15 gennaio 1977:** il DES diventa **standard federale ufficiale** (FIPS PUB 46).
    
- Successive riaffermazioni: **1983, 1987, 1992**.
    
- **1999:** nasce **Triple DES (3DES)** per aumentare la sicurezza.
    
- **2001:** sostituzione ufficiale con l’**AES (Advanced Encryption Standard)**.
    

---

### **2. Struttura generale del DES**

Il DES è un **cifrario a blocchi** che elabora blocchi di **64 bit** di testo in chiaro, generando blocchi di **64 bit** di testo cifrato.  

![](imgs/Pasted%20image%2020260221223509.png)

La **chiave nominale** è lunga **64 bit**, ma solo **56 bit effettivi** vengono utilizzati: ogni byte include un **bit di parità**, che rappresenta lo XOR dei 7 bit precedenti.

![](imgs/Pasted%20image%2020260221223558.png)

Questa scelta riduce la **chiave effettiva** a 56 bit.

---

### **3. Architettura del cifrario**

Il DES segue una **rete di Feistel** a **16 iterazioni** (round).  
Ogni round usa una **sottochiave da 48 bit**, derivata dalla chiave principale da 56 bit tramite un processo detto **schedulazione delle chiavi**.

Schema generale:

![](imgs/Pasted%20image%2020260221223634.png)

```
Plaintext (64 bit)
↓
Permutazione iniziale (IP)
↓
16 iterazioni di Feistel (con sottochiavi K1...K16)
↓
Scambio finale delle metà (swap)
↓
Permutazione inversa (IP⁻¹)
↓
Ciphertext (64 bit)
```

---

### **4. Permutazione iniziale (IP)**

La **permutazione iniziale (Initial Permutation)** riorganizza i 64 bit del blocco in ingresso secondo una tabella fissa.

![](imgs/Pasted%20image%2020260221223755.png)

Esempio parziale:

|Posizione iniziale|1|2|3|4|5|6|7|8|
|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:-:|
|Bit permutato|58|50|42|34|26|18|10|2|

L’operazione continua fino al bit 64, generando un blocco riorganizzato.  
Non ha scopo crittografico diretto, ma serve per la **distribuzione dei bit** nelle posizioni interne dei round.

---

### **5. Permutazione inversa (IP⁻¹)**

Alla fine della cifratura, i bit vengono riportati al loro ordine originale tramite la **permutazione inversa (IP⁻¹)**, che è semplicemente l’inversa della IP.

![](imgs/Pasted%20image%2020260223201307.png)

Anche qui, la funzione è puramente di **riordino**, utile per il corretto flusso interno dell’algoritmo.

---

### **6. Struttura di un round di Feistel nel DES**

Ogni round divide il blocco da 64 bit in due parti da **32 bit**:

$$  
(L_{i-1}, R_{i-1})  
$$

e utilizza una sottochiave $K_i$ da 48 bit per generare il nuovo stato:

$$  
\begin{cases}  
L_i = R_{i-1} \\\\  
R_i = L_{i-1} \oplus f(R_{i-1}, K_i)  
\end{cases}  
$$


![](imgs/Pasted%20image%2020260221224022.png)

La funzione $f$ è l’elemento più importante del DES: realizza **espansione**, **sostituzione** e **permutazione**.

---

### **7. La funzione f**

La funzione $f$ riceve in input:

- la parte destra del blocco ($R_{i-1}$, 32 bit)
    
- la sottochiave ($K_i$, 48 bit)
    

e restituisce **32 bit** di output.

![](imgs/Pasted%20image%2020260221224223.png)

#### **Fasi interne:**

1. **Espansione (E):**  
    $R_{i-1}$ viene espanso da 32 a **48 bit** duplicando alcuni bit secondo una tabella predefinita.  
    Serve per poter combinare i 32 bit di testo con la sottochiave da 48 bit tramite XOR.

![](imgs/Pasted%20image%2020260221224500.png)

La prima colonna è un duplicato della quinta colonna, mentre la seconda colonna è duplicato dell'ultima.
    
2. **XOR con la sottochiave $K_i$**  
    L’output dell’espansione viene sommato modulo 2 con la sottochiave da 48 bit.
    
3. **Sostituzione tramite S-box (Substitution Boxes):**  
    L’output a 48 bit viene suddiviso in **8 gruppi da 6 bit**, ciascuno passato attraverso una **S-box**, che produce 4 bit in uscita.  
    → Totale: 8 × 4 = **32 bit**.
    
4. **Permutazione (P):**  
    I 32 bit risultanti vengono permutati secondo un’altra tabella (P-box), rimescolando ulteriormente l’informazione.
    

---

### **8. Le S-box**

Ogni **S-box** implementa una sostituzione non lineare:  
a ogni input da 6 bit corrisponde un output da 4 bit, secondo una tabella fissa.

![](imgs/Pasted%20image%2020260221224702.png)

**Esempio di funzionamento:**

Input binario: `101110`

![](imgs/Pasted%20image%2020260221224738.png)

- Primo e ultimo bit → selezionano la riga (`10` = 2)
    
- Quattro bit centrali → selezionano la colonna (`0111` = 7)
    
- In corrispondenza: valore = 11  
    → Output binario = `1011`
    
#### **Proprietà delle S-box spiegate**

- **Ogni riga è una permutazione dei numeri 0–15.**
    
    Questo significa che, fissati i bit esterni (che determinano la riga nel DES), la funzione sui restanti 4 bit è **biunivoca**: non ci sono collisioni all’interno della stessa riga.
    
    La motivazione è evitare perdite di informazione locale e impedire che l’attaccante possa sfruttare relazioni semplici tra input e output. Inoltre garantisce una distribuzione uniforme dei valori e contribuisce alla confusione (confusion) del cifrario.
    
- **Nessuna S-box è una funzione lineare o affine.**
    
    Una funzione lineare soddisfa relazioni del tipo
    
    $S(x \oplus y) = S(x) \oplus S(y)$
    
    Se una S-box fosse lineare o affine, l’intero cifrario diventerebbe analizzabile con algebra lineare su $\mathbb{F}_2$, rendendo possibile recuperare la chiave con tecniche relativamente semplici.
    
    La non linearità è quindi il requisito fondamentale per la sicurezza: introduce complessità matematica e impedisce attacchi lineari.
    
- **Cambiando un solo bit di input, variano almeno due bit di output.**
    
    Questa proprietà realizza l’**effetto valanga (avalanche effect)**: una piccola modifica nell’input produce una variazione significativa nell’output.
    
    L’obiettivo è impedire che le differenze possano essere tracciate facilmente attraverso i round del cifrario. In particolare, questa proprietà aumenta la resistenza alla **crittanalisi differenziale**, perché le differenze si propagano rapidamente e in modo imprevedibile.
    
- **Le uscite sono bilanciate: per ogni bit, il numero di zeri e di uno è quasi uguale.**
    
    Ogni bit di output deve comportarsi come una variabile casuale con probabilità circa $1/2$.
    
    Se esistesse un bias (ad esempio più zeri che uno), l’attaccante potrebbe sfruttarlo statisticamente per ottenere informazioni sulla chiave.
    
    Il bilanciamento riduce quindi la possibilità di **crittanalisi lineare** e rende l’output indistinguibile dal rumore casuale.
    

---

### **9. Schedulazione delle chiavi**

Il DES genera **16 sottochiavi da 48 bit** a partire dalla chiave iniziale da 56 bit.

![](imgs/Pasted%20image%2020260221225626.png)

#### **Passaggi principali:**

1. **Permutazione PC-1:**  
    Rimuove gli 8 bit di parità e riorganizza i restanti 56 bit.
    
![](imgs/Pasted%20image%2020260221225808.png)
    
2. **Divisione:**  
    La chiave viene divisa in due metà da 28 bit:  
    $C_0$ e $D_0$.
    
3. **Rotazioni (LS):**  
    A ogni round, entrambe le metà vengono ruotate **a sinistra** di 1 o 2 posizioni secondo una tabella fissa:
    
![](imgs/Pasted%20image%2020260221225909.png)

    Totale: 28 rotazioni complessive.
    
4. **Permutazione PC-2:**  
    Dopo ogni rotazione, i 56 bit vengono compressi e permutati per generare la sottochiave da 48 bit ($K_i$).
    
![](imgs/Pasted%20image%2020260221225822.png)

---

### **10. Decifratura**

Il **DES è simmetrico**:  
l’algoritmo di decifratura è identico a quello di cifratura, ma utilizza le sottochiavi **in ordine inverso**:

$$  
K_{16}, K_{15}, \dots, K_1  
$$

![](imgs/Pasted%20image%2020260221230013.png)

Questo è possibile grazie alla **struttura di Feistel**, che garantisce l’invertibilità del processo.

![](imgs/Pasted%20image%2020260221230134.png)

---

### **11. Riepilogo finale**

- DES elabora **blocchi da 64 bit** con una **chiave effettiva da 56 bit**.
    
- È composto da **16 fasi (round)**, ognuna con una propria sottochiave.
    
- La sicurezza deriva da:
    
    - la **rete di Feistel**;
        
    - le **S-box** non lineari;
        
    - la **schedulazione delle chiavi**.
        

---

### **Sintesi**

Abbiamo visto:

- L’intera struttura interna del DES;
    
- Il funzionamento della funzione $f$ e delle S-box;
    
- Il processo di generazione delle sottochiavi;
    
- Il meccanismo simmetrico di cifratura e decifratura.
    

**Punto chiave:**  
DES realizza concretamente i principi di **confusione e diffusione** di Shannon, ma oggi è considerato **insicuro** a causa della limitata lunghezza della chiave (56 bit), facilmente vulnerabile agli attacchi a forza bruta.