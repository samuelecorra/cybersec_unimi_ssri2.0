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

### **4. Modalità CFB (Cipher(text) Feedback)**

#### **4.1 Concetto generale**

NB: il docente lo denomina così, ma chiamarlo solo cypher feedback sembra far intuire che questa modalità vada a prendere come feedback l'output del cypher (cifrario, DES insomma) quando in realtà prende come feedback il cypher TEXT, ergo il testo cifrato! Fare attenzione, perché la successiva modalità spiegata prende invece come feedback l'output del cifrario!

Pensata per cifrare **flussi di dati** (streaming), non solo blocchi.

![](imgs/Pasted%20image%2020260222103632.png)

#### **Procedura a blocchi (la versione "specifica" illustrata dal prof)**

1. Si cifra l’IV con DES:  
   $E_k(IV)$

2. Si XORa con il primo blocco di testo in chiaro → otteniamo il primo blocco cifrato $y_1$.

3. Tale blocco fa da feedback, ergo da input per il round successivo, e si ripete il processo per i blocchi successivi.

#### **Generalizzazione a flusso, il cosiddetto s-bit CFB**

Questa volta inseriamo l'IV in un **registro di shift** da 64 bit, che scorre di **s bit** ad ogni round (es. s=8 bit).

Bisogna vederlo come un registro ha:

- a sinistra i primi 64-s bit dell'IV;
- a destra gli s bit dell'IV.

Spiegazione passo-passo:

1. Si cifra l’IV con DES: $E_k(IV)$

2. Dall'IV cifrato si preleva il primo gruppo di **s bit** A SINISTRA (es. 8 bit).

3. Questi vengono **XORati** con i primi **s bit** del testo in chiaro → otteniamo il testo cifrato $y_1$, OVVERO i primi s bit del cyphertext.

4. Il registro scorre di **s bit** e dunque sull'estremità destra si inseriscono gli s bit appena cifrati $y_1$.

5. Il processo si ripete per il blocco successivo, quindi se per la prima iterazione avevamo iniziato con la cifratura dell'IV, dalla seconda iterazione in avanti abbiamo da cifrare il registro dell'IV che ha iniziato ad esser shiftato nelle ultime s posizioni a destra.

6. Dopo averlo cifrato, ne prendiamo i primi s bit a sinistra e continuiamo a XORare con i successivi s bit del plaintext.

E così via...

![](imgs/Pasted%20image%2020260222103928.png)

**Decifratura:**

- Avviene con lo stesso algoritmo di cifratura, poiché:
  $$
  y = x \oplus S_s(E_k(IV)) \Rightarrow x = y \oplus S_s(E_k(IV))
  $$

Quindi per decifrare non ci serve nemmeno usare $DES^{-1}$, ma basta usare DES normale.

#### **4.2 Caratteristiche**

- **Tempo reale:** cifra e trasmette bit o byte appena disponibili. Non devo aspettare 64bit!
- Si può ragionare comodamente in termini di cifrare 1byte alla volta se s=8.
- La lunghezza del testo cifrato = lunghezza del testo in chiaro.
- Gli **errori si propagano** per alcuni blocchi successivi, questo poiché come in CBC il testo cifrato è funzione del testo in chiaro precedente.
- Usa sempre DES e **non** DES⁻¹.

![](imgs/Pasted%20image%2020260222104150.png)

- Adatta a **comunicazioni interattive** o **stream** (es. trasmissione caratteri).

---

### **5. Modalità OFB (Output Feedback)**

#### **5.1 Funzionamento**

Consideriamo sin da subito la variante s-bit.

Simile alla CFB, ma il **registro** viene aggiornato con l’**output del cifrario** e non con il testo cifrato.

![](imgs/Pasted%20image%2020260609154855.png)

1. Si cifra l’IV:  
   $E_k(IV)$
2. L’output viene usato per generare un flusso pseudocasuale $z_i$, il cosiddetto keystream.
3. Il testo cifrato è:  
   $y_i = x_i \oplus z_i$
4. Il flusso $z_i$ è indipendente dal testo in chiaro → può essere **precalcolato**.

**Decifratura:** identica alla cifratura, perché XOR è simmetrico.

![](imgs/Pasted%20image%2020260222104419.png)

#### **5.2 Caratteristiche**

- **Non propaga gli errori**: un bit errato nel cyphertext non altera i successivi.
- Adatta a **trasmissioni su canali rumorosi** (es. comunicazioni satellitari).

Svantaggio principale:

In modalità OFB, DES viene usato per generare un flusso di chiave che viene combinato con il plaintext tramite XOR. Per questo motivo OFB si comporta in modo simile a un cifrario a flusso: se un attaccante modifica un bit del ciphertext, nella decifratura viene modificato esattamente il bit corrispondente del plaintext.

Questo rende OFB malleabile: senza un meccanismo di autenticazione, un attaccante può alterare parti del messaggio cifrato producendo alterazioni controllate nel messaggio decifrato. Il problema è particolarmente grave se il plaintext rappresenta comandi, istruzioni o valori con significato operativo, perché anche la modifica di un solo bit può cambiare il comportamento del sistema.

Modalità come CBC o CFB propagano maggiormente gli errori, ma non risolvono da sole il problema dell’integrità. La soluzione corretta è associare alla cifratura un controllo di autenticità, per esempio tramite MAC, oppure usare una modalità di cifratura autenticata come GCM.

---

### **6. Modalità CTR (Counter)**

#### **6.1 Funzionamento**

Si introduce un **contatore (ma attenzione, qui il prof non parla minimamente del nonce = number used once, ovvero il valore che garantisce unicità nelle reali implementazioni)** delle stesse dimensioni del blocco in chiaro.  
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

Nella modalità CTR, l’accesso casuale significa che è possibile cifrare o decifrare direttamente un blocco qualsiasi del messaggio senza elaborare i blocchi precedenti.
Infatti il keystream del blocco $i$ si ottiene calcolando $E_k(\text{Counter}+i)$, quindi dipende solo dalla chiave e dal valore del contatore associato a quel blocco.

Questo rende CTR adatta a file grandi, dischi cifrati, database e trasmissioni in cui i blocchi possono essere letti o ricevuti fuori ordine. La sicurezza di CTR è dimostrabile matematicamente tramite una riduzione: se un attaccante riuscisse a distinguere CTR da una cifratura casuale, allora si potrebbe usare tale attaccante per distinguere il cifrario a blocchi sottostante da una permutazione pseudo-casuale.

Quindi, assumendo che il cifrario a blocchi sia sicuro e che i valori di counter non vengano mai riutilizzati con la stessa chiave, CTR raggiunge un livello di sicurezza almeno comparabile alle modalità classiche come CBC, CFB e OFB, risultando però più efficiente e parallelizzabile.

---

## **La condizione fondamentale: mai riusare il contatore (facoltativo)**

CTR però ha una condizione rigidissima:

> non bisogna mai usare due volte lo stesso valore di counter con la stessa chiave.

Perché se riusi lo stesso counter, generi lo stesso keystream.

Supponiamo due messaggi:

$$
y_i = x_i \oplus z_i
$$

$$
y_i' = x_i' \oplus z_i
$$

Se l’attaccante fa XOR tra i due ciphertext:

$$
y_i \oplus y_i'
$$

ottiene:

$$
(x_i \oplus z_i) \oplus (x_i' \oplus z_i)
$$

Riordinando:

$$
x_i \oplus x_i' \oplus z_i \oplus z_i
$$

ma:

$$
z_i \oplus z_i = 0
$$

quindi resta:

$$
y_i \oplus y_i' = x_i \oplus x_i'
$$

Questo è disastroso, perché l’attaccante elimina il keystream e ottiene una relazione diretta tra i due plaintext.

Quindi CTR è molto sicuro **solo se il nonce/counter non viene mai riutilizzato**.

---

### **7. Sintesi finale**

Abbiamo visto le **cinque modalità operative del DES**, tutte applicabili a qualunque **cifrario a blocchi**:

| Modalità | Tipo                             | Propagazione errori | Velocità        | Uso principale                 |
| -------- | -------------------------------- | ------------------- | --------------- | ------------------------------ |
| **ECB**  | Blocchi indipendenti             | Nessuna             | 🔹 Alta         | Dati brevi e isolati           |
| **CBC**  | Blocchi concatenati              | Sì                  | 🔸 Media        | Messaggi lunghi                |
| **CFB**  | Flusso basato su testo cifrato   | Sì                  | 🔸 Media        | Streaming / canali interattivi |
| **OFB**  | Flusso basato su output cifrario | No                  | 🔹 Alta         | Canali rumorosi                |
| **CTR**  | Flusso con contatore             | No                  | 🔹🔹 Molto alta | Comunicazioni parallele        |

---

In sintesi:

- Le **modalità operative** estendono l’uso di DES a messaggi di qualsiasi lunghezza.
- Tutti i **cifrari a blocchi moderni** (come AES) adottano le **stesse modalità standard**.
- La **scelta della modalità** influisce direttamente su **sicurezza, prestazioni e propagazione degli errori**.

---

> 💡 **Prossimo passo per l'esame:** ora conosci la struttura OFB ($w_t = E_K(w_{t-1})$, $c_t = m_t \oplus w_t$). Gli appelli 230907 e 230908 chiedono una **dimostrazione formale step-by-step** per dedurre l'intero keystream dato un blocco plaintext noto — argomento da **30 punti**. Vai a [UD5 / L3 – Modalità OFB personalizzata: deduzione formale del keystream](../UD5_Approfondimenti_Esame/L3%20-%20Modalità%20OFB%20personalizzata%20-%20deduzione%20formale%20del%20keystream.md) per la dimostrazione completa.
