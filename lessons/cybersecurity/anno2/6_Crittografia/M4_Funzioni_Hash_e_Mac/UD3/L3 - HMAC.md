## **Lezione 3: HMAC**

### **1. Introduzione e standard**

L’**HMAC (Hash-based Message Authentication Code)** è lo schema standard per la costruzione di **codici MAC basati su funzioni hash**.  
È definito nei principali documenti normativi:

- **RFC 2104 – HMAC: Keyed-Hashing for Message Authentication** (febbraio 1997)
    
- **ANSI X9.71 – Keyed Hash Message Authentication Code** (2000)
    
- **FIPS 198 – The Keyed-Hash Message Authentication Code (HMAC)** (pubblicato nel 2002)
    

HMAC si basa sull’uso di una **funzione hash come black-box**, cioè senza modificarne la struttura interna, permettendo:

- **facile sostituzione** della funzione hash (MD5, SHA-1, RIPEMD-160, …);
    
- **semplice gestione delle chiavi**;
    
- **buona efficienza** su qualsiasi piattaforma.
    

---

### **2. Definizione generale di HMAC**

Sia:

- $H$ una **funzione hash iterata**, con **Initialization Vector (IV)** fisso;
    
- $n$ la lunghezza in bit dell’hash prodotto (es. 128 per MD5, 160 per SHA-1);
    
- $b$ la lunghezza in bit dei blocchi di input della funzione hash (es. 512 bit per SHA-1);
    
- $M$ il messaggio, suddiviso in blocchi da $b$ bit;
    
- $K$ la chiave segreta condivisa.
    

Si definiscono due **costanti di padding**:

- `ipad` = byte `0x36` (00110110₂) ripetuto $b/8$ volte
    
- `opad` = byte `0x5C` (01011100₂) ripetuto $b/8$ volte
    

---

### **3. Normalizzazione della chiave**

La chiave $K$ viene adattata alla dimensione del blocco della funzione hash:

$$  
K' =  
\begin{cases}  
K ; || ; 0\ldots0, & \text{se } \text{len}(K) < b \\\\  
H(K) ; || ; 0\ldots0, & \text{se } \text{len}(K) > b  
\end{cases}  
$$

In altre parole:

- se $K$ è più corta del blocco, viene **riempita di zeri**;
    
- se $K$ è più lunga, viene prima **hashata** e poi **padded**.
    

---

### **4. Formula di HMAC**

Una volta normalizzata la chiave, HMAC è definito come:

$$  
HMAC(K, M) = H \big( (K' \oplus opad) ; || ; H((K' \oplus ipad) ; || ; M) \big)  
$$

Il funzionamento è **a due livelli di hash**:

1. Un hash interno che combina la chiave (mascherata con `ipad`) e il messaggio.
    
2. Un hash esterno che combina la chiave (mascherata con `opad`) e il risultato del primo hash.
    

---

### **5. Precomputazione**

Per rendere HMAC più efficiente, è possibile precomputare:

$$  
f(IV, K' \oplus ipad) \quad \text{e} \quad f(IV, K' \oplus opad)  
$$

dove $f$ rappresenta la funzione di compressione iterata della hash.  
Questo permette di riutilizzare le parti costanti quando si devono autenticare molti messaggi con la stessa chiave.

---

### **6. Output troncato**

In molte applicazioni non si usa l’intero output della funzione hash, ma solo i **primi $t$ bit**.  
Esempi:

- **HMAC-SHA1-80:** usa solo 80 bit dei 160 totali.
    
- **HMAC-MD5:** usa tutti i 128 bit.
    

Raccomandazioni standard:

- $t \ge n/2$ per mantenere sicurezza sufficiente.
    
- Minimo assoluto consigliato:
    
    - **RFC 2104:** $t \ge 80$ bit
        
    - **FIPS 198:** $t \ge 32$ bit
        

---

### **7. Sicurezza di HMAC**

La sicurezza di HMAC dipende **direttamente dalle proprietà della funzione hash utilizzata**.  
In particolare, se un avversario riesce ad attaccare con successo HMAC, allora può:

1. Calcolare l’output della **funzione di compressione** anche quando l’IV è casuale e sconosciuto.
    
2. Trovare **collisioni nella funzione hash** in condizioni non controllate.
    

Questo significa che HMAC eredita la robustezza della hash sottostante, ma non la peggiora.

---

### **8. Attacchi conosciuti**

Il **miglior attacco teorico** a HMAC è basato sul **paradosso del compleanno**:  
richiede circa $2^{n/2}$ coppie $(M, HMAC_K(M))$ per trovare una collisione.

Esempio:

- Per HMAC-MD5 ($n = 128$), servono circa $2^{64}$ coppie.
    
- Per HMAC-SHA1 ($n = 160$), servono circa $2^{80}$ coppie.
    

Tali quantità sono **computazionalmente proibitive**, quindi l’algoritmo è considerato **sicuro nella pratica**.

---

### **9. Considerazioni pratiche**

- L’avversario **non conosce la chiave $K$**, quindi non può generare autonomamente coppie $(M, HMAC_K(M))$.
    
- Può solo osservare messaggi autentici trasmessi con la stessa chiave.
    
- Anche in questo scenario, non è in grado di produrre nuovi MAC validi.
    

> **Conclusione:**  
> HMAC rimane sicuro fintanto che la funzione hash sottostante non è rotta e la chiave segreta resta privata.

---

### **10. Sintesi finale**

- HMAC usa una **funzione hash come black-box** e combina chiave e messaggio con due maschere (`ipad`, `opad`).
    
- È **standardizzato** (RFC 2104, FIPS 198) e ampiamente adottato.
    
- Supporta **qualsiasi funzione hash sicura** (MD5, SHA-1, SHA-256, RIPEMD-160…).
    
- La **sicurezza** dipende dalla robustezza della funzione hash e dalla segretezza della chiave.
    
- L’uso di un **output troncato** è accettabile purché sufficientemente lungo (≥ n/2).