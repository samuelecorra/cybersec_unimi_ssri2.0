## **Lezione 3: SHA-1**

### **1. Introduzione**

**SHA (Secure Hash Algorithm)** è una famiglia di funzioni hash standardizzata dal governo degli Stati Uniti nel **1993**, sotto il nome di **SHS (Secure Hash Standard)**.  
Nel **1994** fu introdotta una versione migliorata, chiamata **SHA-1**, che differisce dalla precedente solo per l’aggiunta di **uno shift nell’espansione dei blocchi**.

#### **Caratteristiche principali**

- Lunghezza dell’hash: **160 bit**
    
- Architetture target: **32 bit big-endian**
    
- Struttura generale: simile a **MD4** e **MD5**, ma più sicura
    
- Operazioni: efficienti, tutte su parole di 32 bit
    

---

### **2. Struttura generale di SHA-1**

#### **2.1 Suddivisione del messaggio**

SHA-1 elabora il messaggio in **blocchi da 512 bit**, ciascuno composto da **16 parole da 32 bit**.

Il messaggio originale $M$ viene **padded** per diventare un multiplo di 512 bit:

$$  
M' = M \ || \ 1 \ || \ 0...0 \ || \ \text{len}(M)  
$$

dove:

- il bit `1` segnala la fine del messaggio,
    
- gli zeri completano fino a 448 bit modulo 512,
    
- gli ultimi 64 bit contengono la lunghezza del messaggio originario.
    

---

#### **2.2 Espansione del blocco**

Ogni blocco produce 80 parole da 32 bit:

- Le prime 16 sono copiate direttamente dal blocco:  
    $$  
    X[0], X[1], ..., X[15]  
    $$
    
- Le successive 64 vengono calcolate secondo la relazione:
    

$$  
X[t] = (X[t-3] \oplus X[t-8] \oplus X[t-14] \oplus X[t-16]) \lll 1  
$$

dove `\lll 1` rappresenta una **rotazione a sinistra di 1 bit**.  
Questa espansione aumenta la diffusione dei bit e la resistenza agli attacchi.

---

### **3. Funzioni logiche**

SHA-1 utilizza quattro funzioni logiche $F(t, X, Y, Z)$, che cambiano in base al round:

|Round (t)|Funzione $F(t,X,Y,Z)$|Descrizione|
|:--|:--|:--|
|0–19|$(X \land Y) \lor (\neg X \land Z)$|_if X then Y else Z_|
|20–39|$X \oplus Y \oplus Z$|Parità bit a bit|
|40–59|$(X \land Y) \lor (X \land Z) \lor (Y \land Z)$|Due su tre|
|60–79|$Y \oplus X \oplus Z$|Parità bit a bit|

Queste funzioni introducono non-linearità e bilanciano diffusione e confusione all’interno del calcolo dell’hash.

---

### **4. Costanti additive**

Ogni gruppo di 20 iterazioni usa una **costante additiva** diversa $K[t]$:

|Round|Intervallo t|Costante (esadecimale)|
|:--|:-:|:-:|
|1|0–19|`5A827999`|
|2|20–39|`6ED9EBA1`|
|3|40–59|`8F1BBCDC`|
|4|60–79|`CA62C1D6`|

Queste costanti derivano da:  
$$  
K[t] = \lfloor 2^{30} \cdot \sqrt{p_i} \rfloor  
$$  
dove $p_i$ è il $i$-esimo numero primo (2, 3, 5, 10).

---

### **5. Algoritmo SHA-1 passo-passo**

#### **5.1 Inizializzazione**

Il buffer iniziale di 160 bit è composto da 5 registri a 32 bit:

$$  
\begin{cases}  
A = 0x67452301 \\  
B = 0xEFCDAB89 \\  
C = 0x98BADCFE \\  
D = 0x10325476 \\  
E = 0xC3D2E1F0  
\end{cases}  
$$

---

#### **5.2 Elaborazione per blocchi**

Per ogni blocco da 512 bit:

1. Copia le 16 parole iniziali in $X[0...15]$.
    
2. Espandi a $X[0...79]$ secondo la formula di espansione.
    
3. Salva i registri iniziali:  
    $AA, BB, CC, DD, EE$.
    
4. Per $t = 0...79$, calcola:
    

$$  
\text{TEMP} = (A \lll 5) + F(t, B, C, D) + E + X[t] + K[t]  
$$

e aggiorna:

$$  
\begin{cases}  
E \leftarrow D \\  
D \leftarrow C \\  
C \leftarrow (B \lll 30) \\  
B \leftarrow A \\  
A \leftarrow \text{TEMP}  
\end{cases}  
$$

5. Alla fine del blocco:
    

$$  
\begin{cases}  
A = A + AA \\  
B = B + BB \\  
C = C + CC \\  
D = D + DD \\  
E = E + EE  
\end{cases}  
$$

Il risultato $(A,B,C,D,E)$ dopo l’ultimo blocco è l’hash finale a **160 bit**.

---

### **6. Confronto tra SHA-1 e MD4/MD5**

|Caratteristica|**SHA-1**|**MD4 / MD5**|
|:--|:--|:--|
|Output|160 bit|128 bit|
|Numero di round|80 (4 × 20)|64 (4 × 16)|
|Sicurezza contro collisioni|Maggiore (80 bit effettivi)|Inferiore (64 bit effettivi)|
|Architettura target|Big-endian|Little-endian|
|Complessità|Più elevata, ma simile struttura|Più semplice, ma meno sicura|

> SHA-1 offre una **sicurezza superiore** grazie a un digest più lungo e a una diffusione maggiore dei bit.

---

### **7. Evoluzione della famiglia SHA**

Per rafforzare ulteriormente la sicurezza, nel 2000 furono proposte versioni aggiornate:

|Versione|Lunghezza hash|Lunghezza blocco|Parola base|Sicurezza (bit)|
|:--|:-:|:-:|:-:|:-:|
|**SHA-256**|256 bit|512 bit|32 bit|128|
|**SHA-384**|384 bit|1024 bit|64 bit|192|
|**SHA-512**|512 bit|1024 bit|64 bit|256|

- Tutte basate sugli stessi principi di MD4/MD5/SHA-1.
    
- **SHA-384** è una versione troncata di SHA-512 con costanti iniziali differenti.
    

---

### **8. Altre funzioni hash storiche**

|Funzione|Autori|Anno|Output|Note|
|---|---|---|---|---|
|**Snefru**|R. Merkle|1990|128–256 bit|Una delle prime funzioni hash sicure|
|**N-hash**|NTT (Giappone)|1990|128 bit|Poco diffusa|
|**HAVAL**|Zheng, Pieprzyk, Seberry|1992|128–256 bit|Multivariabile, configurabile|
|**FFT-hash I / II**|C. Schnorr|1991–1992|—|Rotte rapidamente|
|**RIPEMD-160**|Bosselaers, Dobbertin, Preneel|1996|160 bit|Alternativa europea sicura|

---

### **9. Attacchi e stato della sicurezza**

Negli anni 2000 sono emerse diverse vulnerabilità:

- **Luglio 2004** → Collisioni trovate in MD4, MD5, HAVAL-128 e RIPEMD da **Xiaoyun Wang** et al.
    
- **Febbraio 2005** → Attacco pratico a **SHA-1**, richiede circa $2^{69}$ calcoli di hash.
    
- **Antoine Joux** → Collisioni in SHA-0 (la versione precedente a SHA-1).
    

⚠️ Tutti questi sono **collision attacks**, non **pre-image attacks**:  
non permettono di invertire l’hash, ma solo di trovare due messaggi diversi con lo stesso digest.

---

### **10. Riepilogo finale**

- **SHA-1** genera digest di **160 bit**.
    
- Garantisce circa **80 bit di sicurezza** contro attacchi del compleanno.
    
- **Successori**: SHA-256, SHA-384, SHA-512 (più sicuri e più lunghi).
    
- Oggi SHA-1 è considerato **obsoleto** per uso crittografico, ma rimane un passo fondamentale nella storia delle funzioni hash.