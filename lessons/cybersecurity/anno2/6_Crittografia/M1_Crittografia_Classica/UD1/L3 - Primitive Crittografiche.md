# **Lezione 3: Primitive crittografiche**

---

### **1. Dalla cifratura segreta ai protocolli complessi**

In origine la crittografia serviva solo a **nascondere messaggi**, ma oggi il suo obiettivo è molto più ampio:  
risolvere un **insieme di problemi di sicurezza** tramite **protocolli matematici strutturati**.

I **protocolli crittografici** moderni si basano su **primitive**: mattoni fondamentali che risolvono **problemi semplici**, ma che possono essere **combinati** per affrontare situazioni molto più complesse (autenticazione, scambio sicuro, firme, ecc.).

---

### **2. Cosa sono le primitive crittografiche**

Le **primitive** possono essere di due tipi:

1. **Costrutti algoritmici**, come **DES**, **AES** o **RSA**, che rappresentano schemi di cifratura concreti.
    
2. **Problemi matematici** difficili da risolvere, come quelli derivanti dalla **teoria dei numeri** (fattorizzazione, logaritmo discreto, ecc.).
    

Ogni primitiva affronta un compito preciso.  
Le principali categorie sono:

|**Tipo di problema**|**Esempi di primitive**|**Obiettivo di sicurezza**|
|---|---|---|
|**Cifratura**|Cifrari simmetrici, asimmetrici|Riservatezza|
|**Autenticazione / integrità**|Funzioni **Hash**, **MAC**|Integrità e autenticità|
|**Firme digitali**|RSA, DSS|Autenticazione e non ripudio|
|**Altro**|Generatori pseudo-casuali, prove **zero-knowledge**|Supporto crittografico generale|

---

### **3. Cifrari simmetrici**

Nei **cifrari simmetrici** la stessa **chiave segreta** serve sia per cifrare sia per decifrare.

$$  
\begin{cases}  
c = E_k(m) \\\\  
m = D_k(c)  
\end{cases}  
$$

- $m$: messaggio in chiaro (plaintext)
    
- $c$: messaggio cifrato (ciphertext)
    
- $k$: chiave segreta condivisa

![](imgs/Pasted%20image%2020251124230922.png)

Entrambi i partecipanti (es. Alice e Bob) devono **conoscere la stessa chiave** e mantenerla segreta.

#### **Esempi principali**

![](imgs/Pasted%20image%2020251124230943.png)

- **DES** (Data Encryption Standard) e **Triple-DES**
    
- **RC2**, **RC4**, **RC5**, **RC6**
    
- **AES** (Advanced Encryption Standard)
    
- **Blowfish**
    

Ogni algoritmo opera su blocchi di **N bit**, trasformando il **testo in chiaro** in un **testo cifrato** della stessa lunghezza mediante operazioni di sostituzione e permutazione controllate dalla chiave.

#### **Vantaggi**

- Estremamente **veloci**
    
- Ottimi per **grandi quantità di dati**
    

#### **Svantaggi**

- Necessità di **condividere la chiave segreta** in modo sicuro
    
- Difficoltà nella **gestione delle chiavi** in reti estese
    

---

### **4. Cifrari asimmetrici (a chiave pubblica)**

I **cifrari asimmetrici** utilizzano **due chiavi distinte**:

- una **chiave pubblica** ($k_{pub}$), resa disponibile a tutti;
    
- una **chiave privata** ($k_{priv}$), conservata segretamente dal destinatario.

![](imgs/Pasted%20image%2020251124231142.png)

Funzionamento logico:

$$  
\begin{cases}  
c = E_{k_{pub}}(m) \\\\  
m = D_{k_{priv}}(c)  
\end{cases}  
$$

Esempio concettuale: una **cassaforte con due lucchetti**

- con la **chiave pubblica** chiudiamo (cifriamo);
    
- con la **chiave privata** apriamo (decifriamo).
    

Le due chiavi sono **matematicamente correlate** ma non intercambiabili.

#### **Caratteristiche fondamentali**

![](imgs/Pasted%20image%2020251124231246.png)

- Chiunque può cifrare un messaggio destinato a Bob, usando la **chiave pubblica di Bob**.
    
- Solo Bob, con la sua **chiave privata**, può decifrarlo.
    
- Non esiste una chiave condivisa tra Alice e Bob.
    
- Ogni utente genera la **propria coppia di chiavi** in autonomia.
    

#### **Vantaggi**

- Elimina il problema della distribuzione sicura delle chiavi.
    
- Permette l’autenticazione e le firme digitali.
    

#### **Svantaggi**

- È molto **più lento** dei cifrari simmetrici.
    
- Richiede **fondamenti matematici robusti** per la sicurezza (RSA, El-Gamal, ECC).
    

---

### **5. Firme digitali**

La **firma digitale** è l’equivalente elettronico della **firma autografa**.  
Serve per **autenticare un documento**, **garantirne l’integrità** e **impedire il ripudio**.

#### **Soluzioni errate**

Una semplice **immagine digitalizzata** della firma non è sicura: può essere copiata o manipolata facilmente.

#### **Requisiti di una firma digitale**

1. Deve poter essere **prodotta solo dal legittimo firmatario**.
    
2. Nessun altro deve essere in grado di **riprodurla**.
    
3. Deve poter essere **verificata pubblicamente** da chiunque.
    

#### **Algoritmi principali**

- **RSA** (Rivest-Shamir-Adleman)
    
- **DSS (Digital Signature Standard)**
    

La firma si ottiene applicando un **algoritmo di cifratura** su una **rappresentazione univoca** del messaggio, detta **hash**.

---

### **6. Funzioni hash**

![](imgs/Pasted%20image%2020251124231736.png)

Una **funzione hash** trasforma un messaggio $M$ di lunghezza arbitraria in un valore $h(M)$ di **lunghezza fissa** (detta _digest_).

$$  
h : \{0,1\}^* \rightarrow \{0,1\}^n  
$$

#### **Proprietà**

- **Facile da calcolare**, ma **difficile da invertire**.
    
- **Piccole variazioni** in $M$ producono **grandi differenze** in $h(M)$.
    
- È **univoca** (bassa probabilità di collisioni).
    

#### **Uso tipico**

Verifica di integrità:

1. Si calcola l’hash del file originale: $H = h(M)$
    
2. Si conserva $H$ in luogo sicuro.
    
3. Più tardi, si ricontrolla il file: $H' = h(M')$
    
4. Se $H = H'$, il file **non è stato modificato**.
    

In pratica, $h(M)$ agisce come **impronta digitale** del file.

---

### **7. Integrità e certificazione del tempo**

Le funzioni hash vengono anche usate per:

- **certificare l’integrità** di un documento nel tempo;
    
- dimostrare che un file **esisteva in una certa data** (timestamping).
    

Esempio:  
Si pubblica pubblicamente il valore $h(M)$ associato a un documento.  
Se in futuro qualcuno presenta un file diverso, la **divergenza dell’hash** rivelerà l’alterazione.

---

### **8. MAC – Message Authentication Code**

Un **MAC** è un codice di autenticazione calcolato su un messaggio usando una **chiave segreta condivisa**.

![](imgs/Pasted%20image%2020251124232059.png)

$$  
MAC = f_k(M)  
$$

- $M$: messaggio
    
- $k$: chiave segreta
    
- $f$: funzione di autenticazione
    

#### **Proprietà principali**

- Garantisce **integrità** e **autenticità**.
    
- Non fornisce riservatezza (il messaggio può essere visibile).
    
- È simile a una firma digitale, ma **basata su una chiave segreta condivisa**.
    

---

### **9. Riepilogo delle primitive principali**

|**Categoria**|**Esempi**|**Obiettivo di sicurezza**|
|---|---|---|
|**Cifratura simmetrica**|DES, AES, RC4, Blowfish|Riservatezza con chiave segreta|
|**Cifratura asimmetrica**|RSA, El-Gamal, ECC|Riservatezza e scambio sicuro|
|**Firme digitali**|RSA, DSS|Autenticità e non ripudio|
|**Hash e MAC**|SHA-1, MD5, HMAC|Integrità e autenticazione|
|**Altre primitive**|RNG, Zero-Knowledge|Supporto e anonimato|

---

### **10. Conclusione**

Le **primitive crittografiche** costituiscono la **grammatica base** della sicurezza digitale.  
Da esse nascono i **protocolli** che proteggono ogni aspetto della nostra vita digitale:  
dalle transazioni online alla posta elettronica, dalle password ai sistemi blockchain.

Conoscere come funzionano e quali proprietà garantiscono è essenziale per capire **come si costruisce la fiducia nei sistemi informatici moderni**.