# **Lezione 2: Crittosistemi su curve ellittiche**

### **1. Introduzione**

Le **curve ellittiche** non si limitano alla teoria matematica:  
possono essere utilizzate per costruire **sistemi crittografici pratici**, analoghi a quelli già visti per RSA ed El-Gamal, ma con **livelli di sicurezza equivalenti a chiavi molto più corte**.

Due esempi principali di applicazione:

1. **Analogo di Diffie–Hellman su curve ellittiche (ECDH)**
    
2. **Crittosistema di El-Gamal su curve ellittiche (ECEG)**
    

---

### **2. Problemi computazionali di base**

La sicurezza della crittografia su curve ellittiche si fonda su due problemi ritenuti **computazionalmente difficili**:

#### **a) Logaritmo discreto su curve ellittiche (ECDLP)**

Dato un punto generatore $G$ di una curva $E_p(a,b)$ e un punto $Z$ appartenente alla curva, il problema consiste nel trovare l’intero $x$ tale che:

$$  
x \cdot G = Z  
$$

- **Facile:** calcolare $Z = x \cdot G$ conoscendo $x$
    
- **Difficile:** ricavare $x$ conoscendo solo $G$ e $Z$
    

---

#### **b) Problema di Diffie–Hellman su curve ellittiche (ECDHP)**

Dati tre punti $G$, $x \cdot G$, e $y \cdot G$ su $E_p(a,b)$, il problema consiste nel calcolare:

$$  
x \cdot y \cdot G  
$$

Senza conoscere né $x$ né $y$, questo calcolo è considerato **computazionalmente intrattabile**.

---

### **3. Crittosistema di El-Gamal su curve ellittiche**

#### **Chiavi**

Ogni utente dispone di:

|Tipo|Contenuto|
|---|---|
|**Chiave pubblica**|$E_p(a,b),; G,; P_A = x_A \cdot G$|
|**Chiave privata**|$x_A$|

I parametri della curva $(E_p(a,b), G)$ possono essere **comuni a più utenti**.

---

### **4. Cifratura (Bob → Alice)**

Bob vuole inviare un messaggio $P_M$ rappresentato da un punto della curva $E_p(a,b)$ all’utente Alice.

1. Sceglie un intero casuale $k \in [1, p-1]$
    
2. Calcola:  
    $$  
    \begin{aligned}  
    C_1 &= k \cdot G \  

    \end{aligned}  
    $$
    $$  
    \begin{aligned}  

    C_2 &= P_M + k \cdot P_A  
    \end{aligned}  
    $$
    
3. Il **testo cifrato** è la coppia:  
    $$  
    C = (C_1, C_2)  
    $$
    

---

### **5. Decifratura (Alice)**

Quando Alice riceve $(C_1, C_2)$, procede così:

1. Conosce la propria chiave privata $x_A$
    
2. Calcola:  
    $$  
    P_M = C_2 - x_A \cdot C_1  
    $$
    

**Verifica:**

$$  
\begin{aligned}  
C_2 - x_A \cdot C_1  
&= (P_M + k \cdot P_A) - x_A \cdot (k \cdot G) \\  
&= P_M + k \cdot (x_A \cdot G) - k \cdot (x_A \cdot G) \\  
&= P_M  
\end{aligned}  
$$


✅ La decifratura è corretta.

---

### **6. Confronto con il sistema El-Gamal classico**

|Operazione|El-Gamal classico|El-Gamal su curve ellittiche|
|---|---|---|
|Operazione base|Moltiplicazione modulare|Addizione di punti|
|Esponenziazione|Ripetute moltiplicazioni|Somma ripetuta di punti|
|Sicurezza|Basata su fattorizzazione o logaritmo discreto|Basata su ECDLP|
|Lunghezza chiavi|Molto maggiore|Molto minore a parità di sicurezza|

---

### **7. Lunghezza delle chiavi**

Le curve ellittiche permettono di ridurre drasticamente la lunghezza delle chiavi mantenendo lo stesso livello di sicurezza.

|Uso|ECC (bit)|RSA (bit)|Cifrari a blocchi (bit)|
|---|---|---|---|
|**Personale**|136|768|56–64|
|**Commerciale**|160|1024|128|
|**Militare**|200|2048|160|

_(Dati RSA Labs)_

Un’ulteriore analisi di **Certicom** mostra che una chiave **ECC da 160 bit** offre sicurezza equivalente a **una chiave RSA da 1024 bit**.

---

### **8. Brevetti e implementazioni**

Le principali aziende che hanno brevettato o ottimizzato implementazioni ECC sono:

- **Apple Computer** → implementazioni efficienti di curve su $GF(p)$ e $GF(2^m)$
    
- **Certicom** → moltiplicazione in basi normali
    
- **Cylink** → moltiplicazione in basi normali
    

---

### **9. Sintesi finale**

- La **crittografia su curve ellittiche (ECC)** realizza una traslazione dei problemi classici di RSA ed El-Gamal nel contesto delle **operazioni sui punti di una curva**.
    
- Le **operazioni modulari** vengono sostituite da **addizioni e moltiplicazioni di punti**.
    
- L’**esponenziazione** diventa una **somma ripetuta** (moltiplicazione scalare).
    
- A parità di sicurezza, ECC richiede **chiavi più corte, meno memoria e meno potenza di calcolo**.