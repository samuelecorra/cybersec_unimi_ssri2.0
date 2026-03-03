# **Lezione 3: Cifrature a flusso – RC4**

---

### **1. Principio delle cifrature a flusso**

Le **cifrature a flusso (stream ciphers)** operano cifrando **un byte o un bit alla volta**, anziché blocchi interi di dati.  
L’idea base è semplice:

- Si genera una **sequenza pseudo-casuale di byte**, detta **keystream**:  
    $z_0, z_1, z_2, z_3, \dots, z_i$
    
- Si combina la keystream con il testo in chiaro **tramite XOR**:  
    $$  
    C_i = M_i \oplus z_i  
    $$
    

dove:

- $M_i$ = $i$-esimo byte del messaggio in chiaro,
    
- $C_i$ = $i$-esimo byte cifrato.
    

---

### **2. Caratteristiche generali**

- Cifrano **un byte per volta** → estrema **velocità**.
    
- Sono spesso implementati in **pochissime linee di codice**.
    
- Utilizzano operazioni molto **semplici (XOR, swap, somma mod 256)**.
    
- Devono produrre una **keystream con lungo periodo** (cioè che non si ripeta rapidamente).
    
- La keystream deve possedere **proprietà di casualità statistica**.
    

> Maggiore è la lunghezza del periodo e più la sequenza appare casuale, maggiore è la resistenza alla crittoanalisi.

![](imgs/Pasted%20image%2020260222195653.png)

---

### **3. Confronto con altri cifrari**

|Algoritmo|Chiave (bit)|Velocità (Mbit/s)|
|---|---|---|
|Triple-DES|168|3|
|DES|56|9|
|RC2|1024|0.98–10|
|**RC4**|2048|**458–2048**|

➡️ I **cifrari a flusso**, e in particolare **RC4**, sono **molto più veloci** dei cifrari a blocchi, mantenendo una buona sicurezza se ben implementati.

---

### **4. L’algoritmo RC4**

#### **4.1 Generalità**

- Ideato da **Ron Rivest** nel **1987**.
    
- È uno dei cifrari a flusso **più diffusi** nella storia.
    
- La **chiave** ha **dimensione variabile**: da 1 a 256 byte.
    
- Genera una keystream con **periodo > 10^{100}**.
    
- Opera su **byte**, non su bit.
    
- È estremamente **facile da implementare** e **molto veloce**.
    

#### **Applicazioni note:**

- **SSL/TLS** → per la sicurezza delle connessioni Web.
    
- **WEP (Wired Equivalent Privacy)** → per la cifratura delle reti wireless (oggi sostituito dal WPA).
    

---

### **5. Struttura interna**

RC4 utilizza due vettori principali:

- **S** → vettore di 256 byte contenente una **permutazione** degli interi $0,1,\dots,255$
    
- **K** → chiave segreta (da 1 a 256 byte)
    

Durante la fase di inizializzazione si costruisce un **vettore T**, ripetendo la chiave per riempire 256 elementi:

$$  
T[i] = K[i \bmod h], \quad 0 \le i < 256  
$$

dove $h$ è la lunghezza della chiave in byte.

---

### **6. Fase di inizializzazione (Key Scheduling Algorithm – KSA)**

![](imgs/Pasted%20image%2020260222200524.png)

1. **Inizializzazione:**
    

```text
for i = 0 to 255
    S[i] = i
    T[i] = K[i mod h]
```

2. **Permutazione:**
    

```text
j = 0
for i = 0 to 255
    j = (j + S[i] + T[i]) mod 256
    swap(S[i], S[j])
```

![](imgs/Pasted%20image%2020260222200603.png)

Dopo questa fase:

- Il vettore **S** contiene una **permutazione pseudo-casuale** dipendente dalla chiave.
    
- La chiave **non viene più utilizzata** direttamente.
    

---

### **7. Generazione della keystream (PRGA – Pseudo-Random Generation Algorithm)**

La keystream viene prodotta un byte alla volta:

```text
i = j = 0
while (true)
    i = (i + 1) mod 256
    j = (j + S[i]) mod 256
    swap(S[i], S[j])
    t = (S[i] + S[j]) mod 256
    k = S[t]
```

- Ogni **k** rappresenta un byte della keystream.
    
- Ogni byte del testo in chiaro viene cifrato come:
    

$$  
C_i = M_i \oplus k  
$$

- Per la decifratura, si applica lo stesso XOR:
    

$$  
M_i = C_i \oplus k  
$$

> Poiché XOR è un’operazione auto-inversa, cifratura e decifratura coincidono.

![](imgs/Pasted%20image%2020260222200828.png)

---

### **8. Proprietà operative**

- Dopo l’indice **255**, RC4 ricomincia da **S[0]**.
    
- Il vettore **S** continua a essere **permutato dinamicamente** durante l’intera generazione.
    
- Ogni byte successivo della keystream dipende dallo stato interno corrente → elevata **sensibilità** alla chiave.
    

---

### **9. Sicurezza e vulnerabilità**

#### **9.1 Punti di forza**

- Nessun attacco noto efficace con chiavi ≥ 128 bit.
    
- Alta velocità → perfetto per cifrare flussi in tempo reale.
    
- Struttura semplice e facilmente implementabile in hardware o software.
    

#### **9.2 Debolezze note**

- **2001:** scoperto un attacco al protocollo **WEP**, dovuto a una cattiva **gestione della chiave** e non a RC4 stesso.
    
    - WEP usava una chiave RC4 di 64 bit composta da:
        
        - 40 bit di **password condivisa**,
            
        - 24 bit di **IV (Initialization Vector)**.
            
    - Problemi:
        
        - IV troppo corto → valori riutilizzati dopo $2^{24}$ pacchetti.
            
        - Spazio totale delle chiavi ridotto ($2^{40}$).
            
        - Chiavi non aggiornate → vulnerabilità a intercettazioni.
            

> Queste falle non dipendono dall’algoritmo RC4, ma da un uso scorretto della chiave nel protocollo WEP.

---

### **10. Sintesi finale**

Abbiamo visto:

- Il **principio di funzionamento dei cifrari a flusso**, basato su keystream e XOR.
    
- La **struttura e le fasi operative di RC4**:  
    **KSA** (inizializzazione) e **PRGA** (generazione della keystream).
    
- I **vantaggi** (velocità, semplicità, efficienza) e le **criticità** (uso improprio in WEP).
    

➡️ **Conclusione:**  
RC4 è uno dei cifrari a flusso più celebri, oggi considerato **obsoleto in ambiti crittografici moderni**, ma rimane un **caso di studio fondamentale** per comprendere la logica delle cifrature basate su **stream pseudo-casuali**.