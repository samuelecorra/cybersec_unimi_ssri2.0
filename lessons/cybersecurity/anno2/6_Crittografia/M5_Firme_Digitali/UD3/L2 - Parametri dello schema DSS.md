## **Lezione 2: Parametri dello schema DSS**

### **1. Introduzione**

In questa lezione vengono illustrate nel dettaglio le **procedure di generazione dei parametri** del **Digital Signature Standard (DSS)**, fondamentali per costruire in modo sicuro le chiavi dello schema DSA.  
I parametri principali da determinare sono **$p$**, **$q$** e **$\alpha$**, che devono rispettare precisi vincoli matematici per garantire la sicurezza del sistema.

---

### **2. Scelta dei parametri $p$, $q$, $\alpha$**

Per generare i parametri principali del DSS:

1. Si sceglie un **numero primo $p$**.
    
2. Si sceglie un **numero primo $q$ di 160 bit** tale che **$q \mid (p - 1)$**.
    
3. Si seleziona un elemento **$\alpha$** in $\mathbb{Z}_p^*$ di **ordine $q$**.
    

La relazione $q \mid (p - 1)$ è essenziale perché consente di costruire un sottogruppo moltiplicativo di ordine $q$, su cui si basa la sicurezza dello schema.

---

### **3. Generazione di $p$ e $q$**

#### **3.1 Idea generale**

Si sceglie:

- un **$q$** di 160 bit (primo);
    
- un **$p$** di 512–1024 bit (primo) tale che $q \mid (p - 1)$.
    

Una possibile procedura:

1. Si genera un numero casuale $X$ di 512 o 1024 bit.
    
2. Si calcola:
    
    $$  
    p \leftarrow X - ((X \bmod 2q) - 1)  
    $$
    
3. Se $p$ è primo e $p \ge 2^{511}$, si accetta; altrimenti, si ripete.  
    In questo modo, **$2q \mid (p - 1)$**.
    

---

#### **3.2 Procedura dettagliata**

Il NIST propone una generazione più rigorosa (in pseudocodice):

1. Calcola interi $n, b$ tali che $L - 1 = 160n + b$.
    
2. Genera una sequenza casuale $S$ di almeno 160 bit.
    
3. Calcola:  
    $$  
    U = \text{SHA}(S) \oplus \text{SHA}((S + 1) \bmod 2^g)  
    $$
    
4. Forma $q$ da $U$ ponendo il bit più significativo (MSB) e il meno significativo (LSB) a 1.
    
5. Ripeti fino a ottenere un **$q$ primo**.
    
6. Per costruire $p$, calcola:  
    $$  
    W = V_0 + V_1 2^{160} + \dots + (V_n \bmod 2^b) 2^{160n}  
    $$  
    $$  
    X = W + 2^{L - 1}  
    $$  
    $$  
    p = X - ((X \bmod 2q) - 1)  
    $$
    
7. Ripeti finché $p$ è **primo** e **$p \ge 2^{L-1}$**.
    

Questa procedura garantisce che i due numeri soddisfino le condizioni richieste e abbiano la dimensione corretta.

---

### **4. Scelta dell’elemento di ordine $q$**

Dopo aver generato $p$ e $q$, è necessario determinare un elemento $\alpha$ di **ordine $q$** in $\mathbb{Z}_p^*$.

Procedura `Scegli_ordineq(p, q)`:

1. Scegli un generatore casuale $g \in \mathbb{Z}_p^*$.
    
2. Calcola:  
    $$  
    \alpha = g^{(p - 1)/q} \bmod p  
    $$
    
3. Se $\alpha \ne 1$, accetta; altrimenti, ripeti.
    

Poiché $\alpha^q \equiv g^{p - 1} \equiv 1 \pmod p$, l’**ordine** di $\alpha$ divide $q$.  
Essendo $q$ primo, l’ordine può essere solo 1 oppure $q$: se $\alpha \ne 1$, allora $\text{ord}(\alpha) = q$.

---

### **5. Probabilità di successo**

La probabilità che un elemento $g$ scelto casualmente in $\mathbb{Z}_p^*$ produca un valore $\alpha$ valido (cioè di ordine $q$) è molto alta.

Il numero di generatori modulo $p$ è $\varphi(\varphi(p)) = \varphi(p - 1)$, e si può stimare:

$$  
\varphi(p) > \frac{p}{6 \ln \ln(p)}  
$$

Da ciò segue che la **probabilità di successo** è maggiore di:

$$  
\frac{1}{6 \ln \ln(p - 1)}  
$$

e il **numero medio di iterazioni** richieste è inferiore a $6 \ln \ln(p - 1)$.

Esempio:

- per $p$ di **512 bit**, ≈ 35 iterazioni in media;
    
- per $p$ di **1024 bit**, ≈ 39 iterazioni;
    
- per $p$ di **2048 bit**, ≈ 44 iterazioni.
    

---

### **6. Sintesi finale**

- La generazione dei parametri DSA richiede:
    
    - **$q$ primo di 160 bit**,
        
    - **$p$ primo di 512–1024 bit**, multiplo di 64,
        
    - **$\alpha$** di **ordine $q$** in $\mathbb{Z}_p^*$.
        
- La corretta scelta di questi parametri garantisce la **robustezza** dello schema DSA e la **sicurezza delle firme**.
    
- L’intero processo si basa sull’uso della funzione **SHA** e su controlli iterativi di **primalità**, per assicurare che i valori generati rispettino tutti i vincoli matematici previsti dallo standard DSS.