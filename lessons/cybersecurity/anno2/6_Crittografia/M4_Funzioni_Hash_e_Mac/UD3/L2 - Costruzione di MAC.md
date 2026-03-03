## **Lezione 2: Costruzione di MAC**

### **1. Introduzione**

In questa lezione vengono presentati i principali **metodi di costruzione dei codici MAC**, basati su due diverse famiglie di primitive crittografiche:

- **Cifrari a blocchi**, da cui nasce il **CBC-MAC**
    
- **Funzioni hash**, da cui derivano le varianti:
    
    - Metodo del **segreto prefisso**
        
    - Metodo del **segreto suffisso**
        
    - **HMAC**
        

Ognuno di questi approcci ha vantaggi, limiti e vulnerabilità specifiche.

---

### **2. MAC basati su cifrari a blocchi**

#### **2.1 Esempio di costruzione**

Consideriamo un messaggio suddiviso in blocchi da 64 bit:

$$  
M = X_1 || X_2 || ... || X_m  
$$

e definiamo:

$$  
\Delta M = X_1 \oplus X_2 \oplus ... \oplus X_m  
$$

Il MAC può essere calcolato come:

$$  
MAC(K, M) = E_K(\Delta M)  
$$

dove $E_K$ rappresenta una cifratura simmetrica (es. **DES in modalità ECB**).

---

#### **2.2 Attacchi**

1. **Attacco a forza bruta**  
    Osservando la coppia $(M, MAC(K,M))$, un attaccante può provare tutte le $2^{56}$ chiavi DES possibili per trovare $K$.
    
2. **Attacco di manipolazione**  
    È possibile costruire un nuovo messaggio valido:
    
    - Si definisce $Y_m = Y_1 \oplus \Delta M$
        
    - La coppia $Y_1 \oplus Y_m || MAC(K, M)$ risulta ancora accettata come autentica.
        

Questo dimostra che **schemi troppo semplici** non offrono sufficiente resistenza.

---

### **3. CBC-MAC**

#### **3.1 Struttura generale**

Uno dei metodi più usati e standardizzati è il **CBC-MAC (Cipher Block Chaining MAC)**, definito in FIPS PUB 113 e ANSI X9.17.

Il messaggio $M = X_1, X_2, ..., X_n$ viene diviso in blocchi di 64 bit (per DES) o 128 bit (per AES), e processato come segue:

$$  
\begin{aligned}  
Y_0 &= IV = 0 \  
Y_i &= E_K(X_i \oplus Y_{i-1}) \quad \text{per } i = 1, 2, ..., n  
\end{aligned}  
$$

Il **MAC finale** è l’output dell’ultimo blocco, eventualmente troncato:

$$  
MAC(M,K) = \text{troncamento}(Y_n)  
$$

Il troncamento serve a ridurre la lunghezza del tag (ad esempio a 32 o 64 bit).

---

### **4. MAC basati su funzioni hash**

Le funzioni hash possono essere impiegate per costruire MAC più efficienti e facilmente implementabili in software.

#### **Vantaggi principali**

- Sono generalmente **più veloci** dei cifrari a blocchi.
    
- Sono **ampiamente disponibili** nelle librerie di sistema.
    
- Non presentano **restrizioni di esportazione** come gli algoritmi di cifratura.
    

Tuttavia, la **costruzione ingenua** può introdurre gravi vulnerabilità.

---

### **5. Metodo del segreto prefisso**

#### **5.1 Definizione**

Un approccio semplice consiste nel concatenare la chiave segreta **prima** del messaggio:

$$  
MAC(K, M) = H(K, M)  
$$

dove $H$ è una funzione hash iterata.

#### **5.2 Debolezza**

Poiché le funzioni hash iterative (come MD5 o SHA) elaborano i dati a blocchi, un attaccante può prolungare il messaggio senza conoscere la chiave:

$$  
H(K, M || X_{n+1}) = f(X_{n+1}, H(K, M))  
$$

ottenendo quindi un **nuovo MAC valido** per un messaggio esteso $M' = M || X_{n+1}$.

Questo è noto come **existential forgery attack** (attacco di falsificazione esistenziale).

#### **5.3 Possibile contromisura**

Aggiungere esplicitamente la **lunghezza del messaggio** alla computazione:

$$  
H(K, L, M) \quad \text{con } L = \text{len}(M)  
$$

---

### **6. Metodo del segreto suffisso**

#### **6.1 Definizione**

In questo caso la chiave viene concatenata **dopo** il messaggio:

$$  
MAC(K, M) = H(M, K)  
$$

#### **6.2 Debolezza**

Questo schema è vulnerabile a un **attacco del compleanno**:

- Si cercano due messaggi $M$ e $M'$ tali che:
    

$$  
H(M) = H(M')  
$$

- Ne consegue che:
    

$$  
H(M, K) = H(M', K)  
$$

quindi è possibile costruire un **nuovo messaggio valido** con lo stesso MAC.

La complessità di questo attacco è circa $O(2^{|hash|/2})$, quindi per un hash a 128 bit servono circa $2^{64}$ tentativi.

---

### **7. HMAC**

L’**HMAC (Hash-based Message Authentication Code)** nasce per risolvere le debolezze dei metodi precedenti.  
Combina una funzione hash iterata (es. SHA-1 o SHA-256) con due costanti chiamate **opad** e **ipad**, e con due passaggi successivi di hashing:

$$  
HMAC(K, M) = H\big( (K \oplus opad) ; || ; H((K \oplus ipad) ; || ; M) \big)  
$$

Dove:

- `opad` = blocco di byte 0x5C ripetuti
    
- `ipad` = blocco di byte 0x36 ripetuti
    
- La chiave $K$ viene adattata alla lunghezza del blocco della hash (es. 512 bit per SHA-1)
    

L’HMAC offre:

- **resistenza agli attacchi di estensione**,
    
- **robustezza formale**,
    
- **portabilità** su qualunque funzione hash sicura.
    

---

### **8. Conclusioni**

In questa lezione abbiamo visto che:

- I MAC possono essere costruiti a partire da **cifrari a blocchi** (CBC-MAC) o **funzioni hash** (HMAC e varianti).
    
- I metodi del **segreto prefisso** e **suffisso** sono vulnerabili a estensioni e collisioni.
    
- L’**HMAC** rappresenta oggi lo standard più sicuro e utilizzato, adottato in protocolli come **TLS**, **SSH**, e **IPsec**.