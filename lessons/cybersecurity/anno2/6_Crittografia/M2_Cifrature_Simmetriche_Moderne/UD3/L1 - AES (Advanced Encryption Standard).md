# **Lezione 1: Origine e caratteristiche dell’AES**

---

### **1. Introduzione storica**

Il **National Institute of Standards and Technology (NIST)** introdusse il **Data Encryption Standard (DES)** come standard nel **1977**, riaffermandolo più volte — fino al **dicembre 1998**.

Tuttavia, col tempo emersero **numerose critiche**:

- **Chiave troppo corta:** 56 bit (o 112 nel caso di 3-DES)
    
- **Blocchi limitati:** 64 bit
    
- **Criteri progettuali poco trasparenti:** sospetti di _trapdoor_ nelle S-box
    
- **Scarsa efficienza software:** soprattutto nel 3-DES
    

#### **Obiettivo del NIST**

Trovare un **nuovo cifrario a blocchi**, destinato a usi **commerciali e governativi**, che fosse:

- più **sicuro**,
    
- più **efficiente** del 3-DES,
    
- e **libero da restrizioni o brevetti**.
    

---

### **2. Il processo di selezione dell’AES**

#### **2.1 La nascita del concorso**

Nel **settembre 1997**, il NIST lanciò un **concorso pubblico internazionale** per scegliere il nuovo standard di cifratura, denominato **AES (Advanced Encryption Standard)**.  
Il processo fu completamente **aperto e trasparente**, con un intenso **scrutinio pubblico**.

**Tappe principali:**

- **Prima conferenza AES** – 20-23 agosto 1998  
    → presentate **15 candidature**.
    
- **Seconda conferenza AES** – 22-23 marzo 1999  
    → presentazione delle prime **analisi e test di sicurezza**.
    
- **9 agosto 1999** → annunciati i **5 finalisti**:  
    **MARS**, **RC6**, **Rijndael**, **Serpent**, **Twofish**.
    
- **Terza conferenza AES** – 13-14 aprile 2000  
    → analisi finale e confronto delle prestazioni.
    

---

#### **2.2 L’adozione ufficiale**

- **2 ottobre 2000:** scelta definitiva del vincitore: **Rijndael**.
    
- **28 febbraio 2001:** pubblicazione della **bozza FIPS** per 90 giorni di revisione pubblica.
    
- **6 dicembre 2001:** approvazione ufficiale come **FIPS PUB 197** (Federal Information Processing Standard).
    
- **26 maggio 2002:** entrata in vigore effettiva dello **standard AES**.
    

---

### **3. Requisiti e criteri di selezione**

#### **3.1 Requisiti del NIST**

- Cifrario **a blocchi**.
    
- **Lunghezza chiave:** 128, 192 o 256 bit.
    
- **Lunghezza blocco:** 128 bit.
    
- **Implementabile su smart card** e dispositivi a basse risorse.
    
- **Royalty-free**, cioè senza vincoli di licenza.
    

#### **3.2 Piattaforma di valutazione**

- Hardware: PC Pentium Pro 200 MHz, 64 MB RAM, Windows 95.
    
- Software: compilatore **Borland C++ 5.0** e **Java JDK 1.1**.
    

#### **3.3 Criteri di selezione**

1. **Sicurezza crittografica**.
    
2. **Efficienza** in implementazioni hardware e software.
    
3. **Dimensione del codice** e **memoria utilizzata**.
    

Ogni candidato doveva fornire:

- Descrizione e analisi dell’algoritmo (vantaggi e limiti).
    
- Stima dell’efficienza computazionale.
    
- Analisi rispetto agli attacchi noti (known/chosen plaintext).
    
- Implementazioni di riferimento in **ANSI C** e **Java**.
    

---

### **4. Candidati e finalisti**

**Finalisti del concorso AES:**

- **RIJNDAEL** – Joan Daemen, Vincent Rijmen
    
- **MARS** – IBM
    
- **RC6** – RSA Laboratories
    
- **SERPENT** – R. Anderson, E. Biham, L. Knudsen
    
- **TWOFISH** – B. Schneier, J. Kelsey, D. Whiting, D. Wagner, C. Hall, N. Ferguson
    

**Altri candidati notevoli:**  
CAST-256, CRYPTON, DEAL, DFC, E2, FROG, HPC, LOKI97, MAGENTA, SAFER+.

---

### **5. AES – Caratteristiche principali**

**Rijndael**, scelto come AES, non è un cifrario di Feistel.  
Opera in modo **completamente parallelo** sull’intero blocco di input, realizzando una **rete di sostituzioni e permutazioni** (_SP-network_).

#### **Parametri principali**

|Parametro|Valore|
|---|---|
|Dimensione del blocco|128 bit (fisso)|
|Lunghezza della chiave|128, 192 o 256 bit|
|Numero di round|10, 12 o 14|
|Sottochiavi totali|44, 52 o 60 parole da 32 bit|

---

#### **5.1 Struttura dei round**

Ogni round (tranne l’ultimo) è composto da **4 operazioni fondamentali**:

1. **SubBytes** → sostituzione _byte per byte_ tramite S-box.
    
2. **ShiftRows** → permutazione ciclica delle righe della matrice di stato.
    
3. **MixColumns** → combinazione lineare delle colonne usando aritmetica su $GF(2^8)$.
    
4. **AddRoundKey** → XOR bit-a-bit dello stato con la sottochiave corrispondente.
    

> L’ultimo round **non include** l’operazione MixColumns.

---

### **6. Sintesi finale**

Abbiamo visto:

- Il processo di **selezione pubblica** che ha portato all’adozione dell’AES.
    
- I **requisiti** fissati dal NIST per un cifrario moderno.
    
- Le **caratteristiche principali** del cifrario vincitore, **Rijndael**.
    

➡️ Dal **2001**, l’AES è lo **standard di cifratura simmetrica** riconosciuto a livello mondiale.  
Opera su **blocchi da 128 bit** e supporta **chiavi di 128, 192 o 256 bit**, combinando **efficienza** e **sicurezza elevata**.