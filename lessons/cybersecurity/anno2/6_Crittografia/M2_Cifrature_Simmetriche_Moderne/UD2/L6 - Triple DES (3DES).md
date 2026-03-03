# **Lezione 6: Triple DES (3DES)**

---

### **1. Introduzione**

Il **Triple DES (3DES)** nasce come evoluzione del **Double DES**, per aumentare la sicurezza mantenendo compatibilità con l’algoritmo originale.

L’idea è applicare il DES **tre volte di seguito**, utilizzando **tre chiavi diverse**.

- **Lunghezza del blocco:** 64 bit
    
- **Chiave:** $(k_1, k_2, k_3)$ lunga $56 + 56 + 56 = 168$ bit
    
![](imgs/Pasted%20image%2020260222122505.png)

#### **Schema generale**

$$  
y = DES_{k_3}(DES_{k_2}(DES_{k_1}(x)))  
$$

---

### **2. Attacco Meet in the Middle sul Triple DES**

Come nel caso del Double DES, anche 3DES è vulnerabile in teoria all’attacco _Meet in the Middle_, ma con **complessità esponenzialmente maggiore**.

![](imgs/Pasted%20image%2020260222122533.png)

#### **Scenario base**

Dato:  
$$  
y = DES_{k_3}(DES_{k_2}(DES_{k_1}(x)))  
$$

1. Si costruisce una tabella con tutte le possibili combinazioni $(k_1, k_2)$:  
    $$  
    z = DES_{k_2}(DES_{k_1}(x))  
    $$
    
2. Si calcola, per ogni $k_3$:  
    $$  
    z' = DES^{-1}_{k_3}(y)  
    $$
    
3. Se $z = z'$, allora la chiave corretta è $(k_1, k_2, k_3)$.
    

---

### **3. Complessità dell’attacco**

#### **Caso 1 – Attacco base**

- **Spazio:** $2^{112}$ righe nella tabella
    
- **Tempo:**
    
    - $2^{112}$ cifrature per costruire la tabella
        
    - $2^{56}$ decifrature per $y$
        
    - $2^{56}$ ricerche (in media $O(1)$ con hash table)
        

👉 Complessità totale: circa **$2^{112}$ operazioni**.

![](imgs/Pasted%20image%2020260222165807.png)

---

#### **Caso 2 – Attacco rivisto**

Si sposta il punto d'incontro z, effettuato dopo la prima fase di decifratura!
Si considerano tutte le coppie $(k_2, k_3)$:

$$  
z = DES^{-1}_{k_2}(DES^{-1}_{k_3}(y))  
$$

e si confronta con la tabella dei valori $DES_{k_1}(x)$.

![](imgs/Pasted%20image%2020260222165844.png)

- **Spazio:** $2^{56}$ righe
    
- **Tempo:**
    
    - $2^{56}$ cifrature per $x$
        
    - $2^{112}$ decifrature per $y$
        
    - $2^{112}$ ricerche in tabella
        

👉 Anche in questo caso, la complessità è dell’ordine di **$2^{112}$**.

![](imgs/Pasted%20image%2020260222170249.png)

---

### **4. Sicurezza effettiva**

Il Triple DES, sebbene utilizzi chiavi per un totale di **168 bit**, garantisce una sicurezza **equivalente a circa 112 bit**.

|Tipo di attacco|Complessità|Sicurezza effettiva|
|---|---|---|
|Known plaintext attack|$2^{112}$|112 bit|
|Ricerca esaustiva|$2^{168}$|teorica|

Pertanto, 3DES è considerato **forte** ma non pienamente “moderno”.

---

### **5. Versione standard: 3DES EDE**

Per motivi di compatibilità con il DES originale, nella pratica si utilizza la versione **EDE (Encrypt–Decrypt–Encrypt)**:

$$  
y = DES_{k}(DES^{-1}_{k'}(DES_{k}(x)))  
$$

- **Lunghezza blocco:** 64 bit
    
- **Chiave:** $(k, k')$ di 112 bit
    
- **Nome completo:** $EDE_{k, k'}$
    
- **Standard:** ANSI X9.17, ISO 8732

![](imgs/Pasted%20image%2020260222170501.png)

#### **Motivo dell’EDE**

- Se $k = k'$, allora il Triple DES **diventa equivalente al DES singolo**, garantendo **retrocompatibilità** con vecchi sistemi.
    

---

### **6. Cifratura e decifratura**

#### **Cifratura**

$$  
y = DES_{k_3}(DES^{-1}_{k_2}(DES_{k_1}(x)))  
$$

![](imgs/Pasted%20image%2020260222170632.png)

#### **Decifratura**

$$  
x = DES^{-1}_{k_1}(DES_{k_2}(DES^{-1}_{k_3}(y)))  
$$

Quando $k_1 = k_3$, si parla di **3DES a due chiavi**, più efficiente ma con sicurezza leggermente inferiore.

---

### **7. Compatibilità con DES**

Se si scelgono le chiavi tali che $k = k'$, allora:

$$  
3DES_{k, k, k}(x) = DES_k(x)  
$$

![](imgs/Pasted%20image%2020260222170700.png)

---

### **8. Prestazioni**

Ecco alcune misure indicative di performance (CBC mode):

![](imgs/Pasted%20image%2020260222170903.png)

|Implementazione|CPU|Throughput|Clock per blocco|Velocità relativa|
|---|---|---|---|---|
|DES (CBC)|300 MHz|46 MB/s|417|🔹 1.0|
|3DES (CBC)|300 MHz|28 MB/s|695|🔸 0.6|
|Biham 64-bit|550 MHz|183 MB/s|192|🔹 3.9|
|Young 32-bit|233 MHz|39 MB/s|380|🔸 0.8|

👉 **Conclusione:** 3DES è **molto più lento** del DES singolo, ma enormemente più sicuro.

---

### **9. Sintesi finale**

Abbiamo visto:

- Il funzionamento del **Triple DES (3DES)**;
    
- L’**attacco Meet in the Middle** esteso a tre chiavi;
    
- La variante **EDE**, usata negli standard ufficiali;
    
- Le differenze di **prestazioni e sicurezza** rispetto al DES singolo.
    

➡️ **Conclusione:**  
Il Triple DES è **equivalente a un cifrario con chiave di 112 bit**.  
Ha rappresentato per decenni lo **standard industriale di crittografia simmetrica**, finché non è stato sostituito dall’**AES**.