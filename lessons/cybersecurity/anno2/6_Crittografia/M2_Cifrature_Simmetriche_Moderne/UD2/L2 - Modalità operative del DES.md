# **Lezione 2: Modalità operative del DES**

---

### **1. Introduzione**

Il **DES** lavora su blocchi da **64 bit**, ma spesso i messaggi reali sono molto più lunghi.  
Per gestire testi di lunghezza arbitraria, si usano diverse **modalità operative** che definiscono **come concatenare** o **trasformare** i blocchi cifrati.

Le modalità standard, definite nel **NBS FIPS PUB 46 (1977)**, sono cinque:

1. **ECB – Electronic Codebook**
    
2. **CBC – Cipher Block Chaining**
    
3. **CFB – Cipher Feedback**
    
4. **OFB – Output Feedback**
    
5. **CTR – Counter**
    

---

### **2. Modalità ECB (Electronic Codebook)**

#### **2.1 Funzionamento**

Il messaggio in chiaro viene suddiviso in blocchi da **64 bit**:

$$  
x = x_1, x_2, \dots, x_n  
$$

Ciascun blocco è cifrato **in modo indipendente** con la **stessa chiave**:

$$  
y_i = E_k(x_i)  
$$

Risultato finale:

$$  
y = y_1, y_2, \dots, y_n  
$$

![](imgs/Pasted%20image%2020260221230518.png)

Se la lunghezza del messaggio **non è multipla di 64**, si procede con:

- **padding** (aggiunta di bit 1 seguito da zeri, es. `100...0`);
    
- o **troncamento** dell’ultimo blocco.
    

#### **2.2 Caratteristiche**

- È la modalità **più semplice e veloce**.
    
- **Errori non si propagano**: un bit errato non influenza altri blocchi.
    
- **Debolezza principale:** blocchi uguali in chiaro generano blocchi uguali cifrati → vulnerabile ad **attacchi di sostituzione**.
    
- Adatta solo alla **cifratura di singoli valori** (es. chiavi, codici, campi isolati).
    

💡 **Non adatta per testi lunghi**: ripetizioni nel messaggio producono schemi evidenti nel testo cifrato.

---

### **3. Modalità CBC (Cipher Block Chaining)**

#### **3.1 Funzionamento**

Ogni blocco viene **collegato** al precedente tramite XOR.

![](imgs/Pasted%20image%2020260222102836.png)

**Cifratura:**

$$  
\begin{cases}  
y_1 = E_k(x_1 \oplus IV) \\\\  
y_j = E_k(x_j \oplus y_{j-1}) \quad \text{per } j>1  
\end{cases}  
$$

**Decifratura:**

$$  
\begin{cases}  
x_1 = D_k(y_1) \oplus IV \\\\  
x_j = D_k(y_j) \oplus y_{j-1}  
\end{cases}  
$$

dove **IV (Initialization Vector)** è un blocco casuale di 64 bit.

![](imgs/Pasted%20image%2020260222102957.png)

#### **3.2 Caratteristiche**

- L’input di ogni round dipende dal **blocco cifrato precedente** → garantisce **diffusione**.
    
- L’IV **non deve essere segreto**, ma **non deve ripetersi**: può essere pubblico.
    
- È **meno veloce** di ECB per la dipendenza sequenziale.
    
- Gli **errori si propagano**: un errore in un blocco corrompe anche il successivo.
    
- Usato per **trasmissione di messaggi a blocchi**, poiché evita ripetizioni nel testo cifrato.

![](imgs/Pasted%20image%2020260222103217.png)

---

### **4. Modalità CFB (Cipher Feedback)**

#### **4.1 Concetto generale**

Pensata per cifrare **flussi di dati** (streaming), non solo blocchi.

Lo schema utilizza un **registro di 64 bit** inizializzato con l’**IV**.

![](imgs/Pasted%20image%2020260222103632.png)

**Procedura a s-bit:**

1. Si cifra l’IV con DES:  
    $E_k(IV)$
    
2. Si preleva il primo gruppo di **s bit** (es. 8 bit).
    
3. Questi vengono **XORati** con i primi **s bit** del testo in chiaro → otteniamo il testo cifrato $y_1$.
    
4. Il registro scorre di **s bit** e si inserisce $y_1$.
    
5. Il processo si ripete per il blocco successivo.

![](imgs/Pasted%20image%2020260222103928.png)

**Decifratura:**

- Avviene con lo stesso algoritmo di cifratura, poiché:
    
    $$  
    y = x \oplus S_s(E_k(IV)) \Rightarrow x = y \oplus S_s(E_k(IV))  
    $$
    

#### **4.2 Caratteristiche**

- **Tempo reale:** cifra e trasmette bit o byte appena disponibili.
    
- La lunghezza del testo cifrato = lunghezza del testo in chiaro.
    
- Gli **errori si propagano** per alcuni blocchi successivi.
    
- Usa sempre DES e **non** DES⁻¹.

![](imgs/Pasted%20image%2020260222104150.png)
    
- Adatta a **comunicazioni interattive** o **stream** (es. trasmissione caratteri).
    

---

### **5. Modalità OFB (Output Feedback)**

#### **5.1 Funzionamento**

Simile alla CFB, ma il **registro** viene aggiornato con l’**output del cifrario** e non con il testo cifrato.

![](imgs/Pasted%20image%2020260222104239.png)

1. Si cifra l’IV:  
    $E_k(IV)$
    
2. L’output viene usato per generare un flusso pseudocasuale $z_i$.
    
3. Il testo cifrato è:  
    $y_i = x_i \oplus z_i$
    
4. Il flusso $z_i$ è indipendente dal testo in chiaro → può essere **precalcolato**.
    

**Decifratura:** identica alla cifratura, perché XOR è simmetrico.

![](imgs/Pasted%20image%2020260222104419.png)

#### **5.2 Caratteristiche**

- **Non propaga gli errori**: un bit errato nel ciphertext non altera i successivi.
    
- Adatta a **trasmissioni su canali rumorosi** (es. comunicazioni satellitari).
    
- **Svantaggio:** se un attaccante modifica un bit in $y$, anche il bit corrispondente in $x$ cambia → vulnerabile a **bit-flipping**.
    

---

### **6. Modalità CTR (Counter)**

#### **6.1 Funzionamento**

Si introduce un **contatore (Counter)** delle stesse dimensioni del blocco in chiaro.  
Per ogni blocco:

$$  
y_i = x_i \oplus E_k(\text{Counter} + i)  
$$

![](imgs/Pasted%20image%2020260222104624.png)

Il contatore viene **incrementato** ad ogni blocco, generando un flusso indipendente dal testo.

![](imgs/Pasted%20image%2020260222104635.png)

**Decifratura:**

$$  
x_i = y_i \oplus E_k(\text{Counter} + i)  
$$

#### **6.2 Caratteristiche**

- **Elevata efficienza**: i blocchi possono essere cifrati **in parallelo**.
    
- Supporta **pre-elaborazione**: si può precalcolare $E_k(\text{Counter}+i)$ prima di ricevere i dati.
    
- Supporta **accesso casuale** ai blocchi cifrati.
    
- È **sicuro e semplice**, richiede solo l’algoritmo di cifratura.
    

---

### **7. Sintesi finale**

Abbiamo visto le **cinque modalità operative del DES**, tutte applicabili a qualunque **cifrario a blocchi**:

|Modalità|Tipo|Propagazione errori|Velocità|Uso principale|
|---|---|---|---|---|
|**ECB**|Blocchi indipendenti|Nessuna|🔹 Alta|Dati brevi e isolati|
|**CBC**|Blocchi concatenati|Sì|🔸 Media|Messaggi lunghi|
|**CFB**|Flusso basato su testo cifrato|Sì|🔸 Media|Streaming / canali interattivi|
|**OFB**|Flusso basato su output cifrario|No|🔹 Alta|Canali rumorosi|
|**CTR**|Flusso con contatore|No|🔹🔹 Molto alta|Comunicazioni parallele|

---

In sintesi:

- Le **modalità operative** estendono l’uso di DES a messaggi di qualsiasi lunghezza.
    
- Tutti i **cifrari a blocchi moderni** (come AES) adottano le **stesse modalità standard**.
    
- La **scelta della modalità** influisce direttamente su **sicurezza, prestazioni e propagazione degli errori**.