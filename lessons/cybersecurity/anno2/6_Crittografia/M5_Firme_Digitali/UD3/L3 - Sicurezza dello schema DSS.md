## **Lezione 3: Sicurezza dello schema DSS**

### **1. Introduzione**

Questa lezione analizza la **sicurezza dello schema DSS (Digital Signature Standard)**, con particolare attenzione ai **tipi di attacco** e ai **principi matematici** su cui si basa la sua resistenza crittografica.  
Lo schema DSS, fondato sull’algoritmo DSA, deve la propria solidità alla **difficoltà computazionale del problema del logaritmo discreto**.

---

### **2. Tipologie di attacco**

#### **2.1 Total break – Key-only attack**

In un **attacco di tipo total break**, l’attaccante cerca di **recuperare la chiave privata $s$** conoscendo solo i parametri pubblici $(p, q, \alpha, \beta)$ e un messaggio firmato.  
Questo equivale a risolvere il problema del logaritmo discreto:

$$  
s = \log_{\alpha}(\beta) \pmod p  
$$

Poiché calcolare un logaritmo discreto modulo $p$ è computazionalmente intrattabile per numeri sufficientemente grandi, questo tipo di attacco è considerato **impraticabile**.

---

#### **2.2 Selective forgery – Key-only attack**

L’obiettivo dell’attaccante è **falsificare la firma di un messaggio specifico $M$** a nome di Alice.  
Senza conoscere la chiave privata, l’attaccante dovrebbe calcolare una firma $(\gamma, \delta)$ tale che:

$$  
\delta = (\text{SHA}(M) + s\gamma)r^{-1} \bmod q  
$$

Questo richiede di conoscere $s$, oppure di risolvere un’equazione che dipende da **$\log_g(\alpha^{\text{SHA}(M)} \beta^{\gamma})$**, ancora una volta riconducibile al logaritmo discreto.  
L’attacco risulta quindi **infeasible** per parametri di dimensioni corrette.

---

#### **2.3 Existential forgery – Key-only attack**

Nel caso dell’**existential forgery**, l’attaccante non cerca di firmare un messaggio scelto, ma di **produrre una qualsiasi coppia $(M, \text{firma})$ valida**.  
Può tentare di:

1. Scegliere valori casuali $g$ e $d$.
    
2. Calcolare un valore $z$ tale che:
    
    $$  
    \alpha^z = g^d \beta^{-\gamma}  
    $$
    
    e successivamente derivare un messaggio $M$ tale che:  
    $$  
    M = \text{SHA}^{-1}(z)  
    $$
    

Questo attacco richiederebbe di **invertire la funzione hash** SHA, cosa ritenuta **computazionalmente impossibile** se l’hash è robusto.

---

### **3. Chiavi globali e individuali**

Nel DSS è importante distinguere tra **parametri globali** e **parametri individuali**:

- I parametri **globali** $(p, q, \alpha)$ possono essere condivisi da più utenti.  
    In genere vengono scelti da un’autorità centrale.
    
- Ogni utente genera la propria **chiave privata $s$**, e calcola la **chiave pubblica $\beta = \alpha^s \bmod p$**.
    

Questa struttura consente l’uso di parametri comuni, riducendo i costi computazionali e semplificando la gestione delle chiavi.

---

### **4. Prestazioni e confronto con RSA**

La **firma DSA** è progettata per essere **più veloce nella generazione** rispetto alla verifica, a differenza di RSA, dove la **verifica è più rapida** della firma.

Dati sperimentali mostrano che:

- per chiavi da **512 a 2048 bit**, la **firma DSA** è fino a **10 volte più veloce** della firma RSA;
    
- tuttavia, la **verifica RSA** risulta mediamente più rapida della verifica DSA.
    

Esempio di confronto (indicativo):

|Algoritmo|Bit chiave|Firme/s|Verifiche/s|
|---|---|---|---|
|DSA|512|~2700|~3300|
|RSA|512|~3200|~3400|
|DSA|1024|~940|~1120|
|RSA|1024|~1080|~620|
|DSA|2048|~270|~340|
|RSA|2048|~320|~250|

_(Dati stimati su processori Pentium II e Celeron, software Crypto++ / OpenSSL)_

---

### **5. Sintesi finale**

- La sicurezza del DSS si basa sulla **difficoltà del problema del logaritmo discreto**, sia per la chiave privata $s$ che per i valori casuali $r$.
    
- Gli **attacchi diretti (total, selective, existential forgery)** risultano impraticabili con parametri di lunghezza adeguata.
    
- La generazione della firma è **più veloce** della verifica.
    
- L’efficienza e la sicurezza del DSS dipendono dalla corretta gestione dei **parametri globali** e dalla protezione della **chiave privata**.