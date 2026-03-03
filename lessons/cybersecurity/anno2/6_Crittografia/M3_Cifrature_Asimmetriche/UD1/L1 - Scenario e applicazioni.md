# **Lezione 1: Scenario e applicazioni**

---

### **1. Cifrari simmetrici e problema della distribuzione delle chiavi**

Nei sistemi di **cifratura simmetrica**, la sicurezza si basa su una **chiave segreta condivisa** tra mittente e destinatario.  
Tuttavia, sorge un problema cruciale: **come fanno Alice e Bob a condividere in modo sicuro una chiave comune** su un **canale insicuro**?

Sono possibili alcune soluzioni:

- **Uso di un canale privato**, ad esempio un corriere fidato o un incontro riservato.
    
- **Uso di una terza parte fidata**, che genera la chiave di sessione e la invia a entrambi in modo sicuro.
    

Queste soluzioni, però, **non sono scalabili**.  
In una rete con $n$ utenti, ogni coppia deve condividere una chiave distinta:

- Ogni utente deve memorizzare $n-1$ chiavi.
    
- Il numero totale di chiavi segrete è dell’ordine di $n^2 / 2$.
    
- L’aggiunta di un nuovo utente implica la redistribuzione delle chiavi a tutti i precedenti.
    

👉 **Soluzione:** introdurre i **cifrari asimmetrici**, che eliminano la necessità di chiavi condivise.

---

### **2. Cifrari asimmetrici: il principio**

I **cifrari asimmetrici** utilizzano una **coppia di chiavi**:

- una **chiave pubblica ($k_{pub}$)** per cifrare,
    
- una **chiave privata ($k_{priv}$)** per decifrare.
    

L’idea è paragonabile a una **cassaforte con due lucchetti**:

- Con la **chiave pubblica** si chiude la cassaforte (si cifra).
    
- Con la **chiave privata** si apre la cassaforte (si decifra).
    

L’asimmetria è dunque:  
$$  
k_{pub} \neq k_{priv}  
$$

---

### **3. Meccanismo di cifratura e decifratura**

#### **Cifratura**

Bob vuole inviare un messaggio $M$ ad Alice:

1. Recupera la **chiave pubblica di Alice** dal registro pubblico.
    
2. Cifra il messaggio:
    

$$  
C \leftarrow \text{CIFRA}(k_{pub}, M)  
$$

3. Invia $C$ ad Alice sul canale insicuro.
    

#### **Decifratura**

Alice riceve il messaggio cifrato $C$ e lo decifra con la sua **chiave privata**:

$$  
M \leftarrow \text{DECIFRA}(k_{priv}, C)  
$$

---

### **4. Proprietà dei cifrari asimmetrici**

#### **Proprietà funzionali**

- Ogni utente può generare autonomamente la propria coppia di chiavi.
    
- Chiunque può cifrare un messaggio per un destinatario (usando la sua chiave pubblica).
    
- Solo il destinatario può decifrare (grazie alla sua chiave privata).
    
- Non ci sono chiavi condivise: ogni utente conserva solo la propria chiave privata.
    

#### **Proprietà di sicurezza**

- È **computazionalmente inammissibile** risalire alla chiave privata conoscendo la chiave pubblica e l’algoritmo.
    
- È **computazionalmente inammissibile** derivare il messaggio originale conoscendo solo la cifratura e la chiave pubblica.
    
- È invece **computazionalmente facile** eseguire cifratura e decifratura quando si possiedono le chiavi corrette.
    

---

### **5. Applicazioni principali**

L’uso della crittografia asimmetrica si riassume in tre grandi categorie:

1. **Encryption / Decryption** → per la **confidenzialità** dei dati.
    
2. **Digital Signatures** → per **autenticazione e non ripudio**.
    
3. **Key Exchange** → per lo **scambio sicuro di chiavi di sessione** (da usare poi con cifrari simmetrici).
    

#### **Tabella di confronto degli algoritmi più noti**

|Algoritmo|De/Cifra|Firma|Scambio chiavi|
|---|---|---|---|
|**RSA**|✔️|✔️|✔️|
|**Curva Ellittica (ECC)**|✔️|✔️|✔️|
|**Diffie-Hellman**|✔️|❌|✔️|
|**DSS**|❌|✔️|❌|

---

### **6. Cifrari simmetrici vs asimmetrici**

|Aspetto|Cifratura simmetrica|Cifratura asimmetrica|
|---|---|---|
|**Velocità**|Molto elevata (es. DES 100–1000× più veloce di RSA)|Più lenta, richiede calcoli matematici complessi|
|**Distribuzione chiavi**|Complessa (serve canale sicuro o KDC)|Semplificata (chiave pubblica liberamente distribuibile)|
|**Applicazioni tipiche**|Comunicazioni tra utenti noti, singolo utente|Comunicazioni aperte, autenticazione, firma digitale|

#### **Cifrari ibridi**

In pratica, si combinano i due approcci:

1. Si genera una **chiave di sessione simmetrica $k$**.
    
2. Si cifra $k$ con la chiave pubblica del destinatario:  
    $$  
    C_1 \leftarrow \text{CIFRA}(k_{pub}, k)  
    $$
    
3. Si cifra il messaggio $M$ con la chiave $k$:  
    $$  
    C_2 \leftarrow E(k, M)  
    $$
    
4. Si inviano entrambi:  
    $$  
    (C_1, C_2)  
    $$
    

Questo metodo unisce la **sicurezza dell’asimmetrica** alla **velocità della simmetrica**.

---

### **7. Fondamenti matematici: funzioni one-way e trapdoor**

I cifrari asimmetrici moderni si basano su **problemi matematici difficili**, come quelli studiati in **teoria dei numeri**.

Il concetto chiave è quello di **funzione one-way**:

- **Facile da calcolare** in una direzione.
    
- **Difficile da invertire** senza informazioni aggiuntive.
    

Una **trapdoor** (letteralmente “botola segreta”) è l’informazione che **rende possibile l’inversione** della funzione.  
Questa proprietà garantisce che solo chi possiede la chiave privata possa decifrare.

---

### **8. Sintesi finale**

- I **cifrari asimmetrici** si basano su una **coppia di chiavi**: pubblica e privata.
    
- Per cifrare un messaggio, si usa la **chiave pubblica del destinatario**.
    
- Per decifrare, si utilizza la **chiave privata** corrispondente.
    
- Sono alla base di molti protocolli di sicurezza moderni (TLS, PGP, ecc.), oltre che della **firma digitale** e dello **scambio sicuro di chiavi**.