## **Lezione 2: MD4 e MD5**

### **1. Introduzione**

Le funzioni **MD4** e **MD5** rappresentano due tra le prime e più importanti **funzioni hash crittografiche** progettate per uso generale.  
Entrambe sono state ideate da **Ron Rivest**:

- **MD4** nel **1990**
    
- **MD5** nel **1991**
    

Il termine **MD** sta per _Message Digest_, cioè “impronta del messaggio”.  
Questi algoritmi producono un **digest di 128 bit** e sono ottimizzati per **architetture a 32 bit little-endian**, risultando molto efficienti nell’implementazione software.

---

### **2. Architetture Little-endian e Big-endian**

Le architetture dei processori differiscono nel modo in cui rappresentano le **parole da 32 bit** a partire da sequenze di byte.

#### **2.1 Little-endian**

- Il byte con indirizzo **più basso** è quello **meno significativo**.
    
- La parola $W$ composta dai byte $(B_1, B_2, B_3, B_4)$ si calcola come:
    

$$  
W = 2^{24}B_4 + 2^{16}B_3 + 2^{8}B_2 + 2^{0}B_1  
$$

- Esempio: processori **Intel x86**.
    

#### **2.2 Big-endian**

- Il byte con indirizzo **più basso** è quello **più significativo**.
    
- La parola $W$ si calcola come:
    

$$  
W = 2^{24}B_1 + 2^{16}B_2 + 2^{8}B_3 + 2^{0}B_4  
$$

- Esempio: **SPARCstation**, **PowerPC**.
    

---

### **3. Obiettivi di progettazione**

Le funzioni MD4 e MD5 sono state progettate per soddisfare quattro obiettivi principali:

1. **Sicurezza forte:**  
    Deve essere computazionalmente difficile trovare due messaggi con lo stesso hash.
    
2. **Sicurezza diretta:**  
    Non basata su problemi teorici (come la fattorizzazione o logaritmi discreti).
    
3. **Velocità:**  
    Esecuzione molto efficiente su architetture software a 32 bit.
    
4. **Semplicità e compattezza:**  
    Facile da implementare e descrivere, senza tabelle complesse o strutture dati estese.
    

---

### **4. Struttura generale di MD4**

#### **4.1 Blocchi e padding**

MD4 (e MD5) processano il messaggio in **blocchi da 512 bit**, cioè **16 parole da 32 bit** ciascuna.

- Il messaggio originale $M$ viene **padded** per diventare multiplo di 512 bit:  
    si aggiunge un bit `1`, poi zeri fino a raggiungere 448 bit modulo 512, e infine la **lunghezza originale** del messaggio su 64 bit.
    

In formula:

$$  
M' = M ; || ; 1 ; || ; 0...0 ; || ; \text{len}(M)  
$$

---

### **5. Operazioni fondamentali**

MD4 e MD5 utilizzano operazioni bit-wise molto semplici e veloci, definite su parole a 32 bit:

|Operazione|Significato|
|---|---|
|$(X \land Y)$|AND bit a bit|
|$(X \lor Y)$|OR bit a bit|
|$(X \oplus Y)$|XOR bit a bit|
|$(\neg X)$|NOT bit a bit|
|$(X + Y)$|Somma modulo $2^{32}$|
|$(X \lll s)$|Rotazione ciclica a sinistra di $s$ bit|

Tutte queste operazioni sono **estremamente efficienti** su CPU a 32 bit.

---

### **6. Funzioni logiche dei round**

Ogni round utilizza una **funzione logica diversa** sulle tre variabili $X, Y, Z$ (ognuna di 32 bit):

|Round|Funzione|Formula|Significato|
|:--|:--|:--|:--|
|1|$F(X,Y,Z)$|$(X \land Y) \lor (\neg X \land Z)$|“Se $X$ allora $Y$, altrimenti $Z$”|
|2|$G(X,Y,Z)$|$(X \land Y) \lor (X \land Z) \lor (Y \land Z)$|Due su tre|
|3|$H(X,Y,Z)$|$X \oplus Y \oplus Z$|Parità bit a bit|
|4 (solo MD5)|$I(X,Y,Z)$|$Y \oplus (X \lor \neg Z)$|Variante logica finale|

---

### **7. Funzione di compressione**

La funzione di compressione di MD4/MD5 opera su:

- il blocco corrente $X$ di 512 bit (16 parole da 32 bit),
    
- e su un **buffer interno** $(A,B,C,D)$ di 128 bit (4 parole da 32 bit).
    

Ogni round esegue **16 operazioni** del tipo:

$$  
[ABCD.k.s.i]  
$$

dove:

- $k$ è l’indice della parola nel blocco,
    
- $s$ è lo shift circolare,
    
- $i$ è il numero dell’operazione,
    
- e la funzione logica dipende dal round.
    

Alla fine dei 4 round, il risultato dell’ultimo viene **sommato (mod $2^{32}$)** al buffer iniziale per formare il nuovo stato.  
Dopo l’ultimo blocco, si ottiene il digest di 128 bit.

---

### **8. MD5: formula di aggiornamento**

Ogni operazione di MD5 ha la forma:

$$  
A \leftarrow B + \left[ (A + W(B,C,D) + X[k] + T[i]) \lll s \right]  
$$

dove:

- $W$ è una delle funzioni $F, G, H, I$ (a seconda del round),
    
- $X[k]$ è la $k$-esima parola del blocco di messaggio,
    
- $T[i]$ è la $i$-esima **costante additiva**, definita come:
    

$$  
T[i] = \lfloor 2^{32} \cdot |\sin(i)| \rfloor  
$$

per $i = 1, 2, ..., 64$ (in radianti).

---

### **9. Sequenza operativa di MD5**

L’algoritmo MD5 inizia con i seguenti valori di buffer:

$$  
\begin{cases}  
A = 0x01234567 \\  
B = 0x89abcdef \\  
C = 0xfedcba98 \\  
D = 0x76543210  
\end{cases}  
$$

Per ogni blocco da 512 bit:

1. Si suddivide in 16 parole $X[0]...X[15]$.
    
2. Si salvano i valori iniziali $AA, BB, CC, DD$.
    
3. Si eseguono i 4 round, per un totale di 64 operazioni.
    
4. Alla fine, si aggiorna:  
    $$  
    A = A + AA, \quad B = B + BB, \quad C = C + CC, \quad D = D + DD  
    $$
    
5. Dopo l’ultimo blocco, l’output $(A,B,C,D)$ costituisce l’hash finale.
    

---

### **10. Differenze tra MD4 e MD5**

|Caratteristica|**MD4**|**MD5**|
|---|---|---|
|Numero di round|3|4|
|Funzioni logiche|F, G, H|F, G, H, I|
|Costanti additive|2|64|
|Velocità|Più veloce|Più sicuro|
|Sicurezza|Debole|Migliore, ma oggi vulnerabile|

In sintesi, MD5 nasce come **evoluzione più robusta di MD4**, ma ne eredita la stessa struttura di base (compressione iterata a 512 bit).

---

### **11. Sicurezza e vulnerabilità**

Negli anni, entrambe le funzioni sono state oggetto di analisi crittografiche:

- **MD4:**  
    Già nel 1991, Merkle dimostrò che **rimuovendo un round** era facile trovare collisioni.
    
- **MD5:**
    
    - Den Boer e Bosselaers (Crypto ’91) trovarono collisioni con un round omesso.
        
    - **Dobbertin (1996)** scoprì collisioni in MD5 **in pochi secondi** su un normale PC.
        
    - Oggi MD5 è considerato **insicuro**, vulnerabile a collisioni con un costo di circa $2^{64}$ operazioni.
        

---

### **12. Riepilogo finale**

- **MD4** e **MD5** elaborano messaggi in blocchi da 512 bit.
    
- Utilizzano **operazioni logiche e aritmetiche su 32 bit** per garantire velocità.
    
- Entrambe producono **digest di 128 bit**, ma la sicurezza effettiva di MD5 è oggi insufficiente.
    
- Le collisioni possono essere trovate con tecniche molto più efficienti rispetto al brute-force.