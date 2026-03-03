
## **Lezione 1: Schema DSS (Digital Signature Standard)**

### **1. Introduzione**

Il **Digital Signature Standard (DSS)** è uno schema di firma digitale proposto dal **NIST** nell’agosto del **1991** e rivisto nel **1993**.  
Alla base del DSS vi è il **Digital Signature Algorithm (DSA)**, una variante ingegnosa dello **schema di El-Gamal**, progettata per rendere la firma più efficiente e standardizzata.

Le firme DSS:

- utilizzano la funzione hash **SHA**, che genera un digest di **160 bit**;
    
- hanno una **lunghezza complessiva di 320 bit**, ideale per dispositivi a risorse limitate come le **smart card**;
    
- basano la loro sicurezza sull’**intrattabilità del problema del logaritmo discreto**.
    

---

### **2. Chiavi DSA**

Ogni utente (es. **Alice**) genera le proprie chiavi in questo modo:

- **$p$**: numero primo di $L$ bit, con $512 \leq L \leq 1024$ e multiplo di 64.
    
- **$q$**: numero primo di **160 bit**, tale che $q \mid (p - 1)$.
    
- **$\alpha$**: elemento di ordine $q$ nel gruppo moltiplicativo $\mathbb{Z}_p^*$, quindi $\alpha^q \equiv 1 \pmod{p}$.
    
- **$s$**: numero casuale con $s < q$, che rappresenta la **chiave privata**.
    
- **$\beta = \alpha^s \bmod p$** è la **chiave pubblica**.
    

**Riassumendo:**

|Tipo|Parametri|Descrizione|
|---|---|---|
|Chiave privata|$(p, q, \alpha, s)$|Include il segreto $s$|
|Chiave pubblica|$(p, q, \alpha, \beta)$|Include $\beta = \alpha^s \bmod p$|

---

### **3. Ordine di un elemento**

Per comprendere la generazione di $\alpha$, occorre introdurre il concetto di **ordine** di un elemento in $\mathbb{Z}_n^*$.

> L’**ordine** di un elemento $\alpha \in \mathbb{Z}_n^*$ è il più piccolo intero positivo $r$ tale che $\alpha^r \equiv 1 \pmod{n}$.

Secondo il **teorema di Lagrange**, per ogni $\alpha \in \mathbb{Z}_n^*$ vale che:  
$$  
\text{ord}(\alpha) \mid \varphi(n)  
$$  
e se $p$ è primo, $\text{ord}(\alpha) \mid (p - 1)$.

---

### **4. Esempio di chiavi DSA**

Esempio numerico:

- $p = 7879$ (primo)
    
- $q = 101$ (primo, con $q \mid (p - 1)$ perché $p - 1 = 78q$)
    
- $s = 75$ (numero casuale, chiave privata)
    
- $\alpha = 170 \in \mathbb{Z}_{7879}^*$ di ordine 101
    
- $\beta = \alpha^s \bmod p = 170^{75} \bmod 7879 = 4567$
    

**Chiavi finali:**

- Chiave privata: $(p, q, \alpha, s) = (7879, 101, 170, 75)$
    
- Chiave pubblica: $(p, q, \alpha, \beta) = (7879, 101, 170, 4567)$
    

---

### **5. Firma DSA**

Per firmare un messaggio $M$, Alice esegue:

1. Sceglie un **numero casuale** $r$ in $[1, q - 1]$.
    
2. Calcola:  
    $$  
    \gamma = (\alpha^r \bmod p) \bmod q  
    $$
    
3. Calcola:  
    $$  
    \delta = ( \text{SHA}(M) + s \gamma ) r^{-1} \bmod q  
    $$  
    dove $r^{-1}$ è l’inverso moltiplicativo di $r$ modulo $q$.
    

La **firma digitale** è la coppia $(\gamma, \delta)$.

$$  
\text{Firma}_{DSA}(M) = (\gamma, \delta)  
$$

---

### **6. Verifica della firma DSA**

Per verificare la firma $(\gamma, \delta)$ di un messaggio $M$, il verificatore procede così:

1. Calcola:  
    $$  
    e' = \text{SHA}(M) \cdot \delta^{-1} \bmod q  
    $$  
    $$  
    e'' = \gamma \cdot \delta^{-1} \bmod q  
    $$
    
2. Verifica la condizione:  
    $$  
    \gamma \stackrel{?}{=} ( \alpha^{e'} \beta^{e''} \bmod p ) \bmod q  
    $$
    

Se l’uguaglianza è verificata, la firma è **valida**; altrimenti, è **falsa**.

---

### **7. Efficienza del DSA**

- **Lunghezza della firma:** 320 bit (160 bit per ciascuno dei due valori $\gamma$ e $\delta$).
    
- **Computazioni off-line:** generazione di $r$, calcolo di $s\gamma$ e dell’inverso $r^{-1} \bmod q$.
    
- **Computazioni on-line:** applicazione dell’hash e pochi prodotti/modulari, rendendo la firma **rapida ed efficiente**.
    

---

### **8. Correttezza della verifica**

Si può dimostrare che:  
$$  
\gamma = ( \alpha^{e'} \beta^{e''} \bmod p ) \bmod q  
$$  
poiché:

$$  
e' = \text{SHA}(M) \delta^{-1} \bmod q, \quad e'' = \gamma \delta^{-1} \bmod q  
$$

e, sostituendo $\beta = \alpha^s \bmod p$ e $\delta^{-1}(\text{SHA}(M) + s\gamma) \equiv r \bmod q$,  
si ottiene:  
$$  
( \alpha^r \bmod p ) \bmod q = \gamma  
$$

che conferma la **correttezza matematica** del processo di verifica.

---

### **9. Sintesi finale**

- Il **DSA** è lo schema di firma usato dallo **standard DSS**.
    
- Si basa sul **problema del logaritmo discreto**, garantendo robustezza crittografica.
    
- Le firme prodotte sono **sempre di lunghezza 320 bit**.
    
- Lo schema è utilizzabile **solo per la generazione di firme** (non per la cifratura).
    
- La sicurezza del DSS dipende dalla **casualità di $r$** e dalla **robustezza della funzione hash** utilizzata.