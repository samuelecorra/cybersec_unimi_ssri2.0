# **Lezione 2: Crittosistema di El-Gamal**

### **1. Introduzione**

Il **crittosistema di El-Gamal**, proposto da **Taher El-Gamal nel 1984**, è uno dei più importanti algoritmi di **cifratura a chiave pubblica**.  
Si basa su due problemi matematici ritenuti computazionalmente difficili:

1. Il **problema del logaritmo discreto**
    
2. Il **problema di Diffie-Hellman**
    

Entrambi operano nel gruppo moltiplicativo $Z_p^*$, dove $p$ è un numero primo e $g$ è un generatore del gruppo.

---

### **2. Generazione delle chiavi**

#### **Parametri comuni a tutti gli utenti**

- $p$: numero **primo grande**
    
- $g$: **generatore** del gruppo moltiplicativo $Z_p^*$
    

Questi valori sono **pubblici**.

#### **Chiave privata**

Ogni utente sceglie un intero casuale:  
$$  
\alpha \in Z_{p-1}  
$$

#### **Chiave pubblica**

Si calcola:  
$$  
\beta = g^{\alpha} \bmod p  
$$

#### **Riepilogo**

|Utente|Chiave pubblica|Chiave privata|
|---|---|---|
|Alice|$(p, g, \beta)$|$\alpha$|

---

### **3. Cifratura**

Quando **Bob** vuole inviare un messaggio $M$ ad **Alice** (che ha chiave pubblica $(p, g, \beta)$):

1. Sceglie un valore casuale $k \in Z_{p-1}$
    
2. Calcola:  
    $$  
    \begin{aligned}  
    y_1 &= g^k \bmod p
	\end{aligned}  
	$$
    $$
    \begin{aligned}  
    y_2 &= M \cdot \beta^k \bmod p  
    \end{aligned}  
    $$
    
3. Il **testo cifrato** è la coppia:  
    $$  
    C = (y_1, y_2)  
    $$
    

---

### **4. Decifratura**

Quando **Alice** riceve $C = (y_1, y_2)$:

1. Calcola:  
    $$  
    z = y_1^{\alpha} \bmod p  
    $$
    
2. Determina il messaggio originale:  
    $$  
    M = y_2 \cdot z^{-1} \bmod p  
    $$
    

dove $z^{-1}$ è l’**inverso moltiplicativo** di $z$ modulo $p$.

---

### **5. Esempio numerico completo**

#### **Dati di Alice**

- $p = 2579$
    
- $g = 2$
    
- $\alpha = 765$
    
- $\beta = g^{\alpha} \bmod p = 2^{765} \bmod 2579 = 949$
    

**Chiavi:**

- Chiave pubblica $(p=2579, g=2, \beta=949)$
    
- Chiave privata $\alpha=765$
    

---

#### **Cifratura (Bob)**

Bob vuole inviare a Alice il messaggio $M = 1299$.

1. Sceglie $k = 853$
    
2. Calcola:  
    $$  
    \begin{aligned}  
    y_1 &= g^k \bmod p = 2^{853} \bmod 2579 = 435
    \end{aligned}
    $$
      
    $$
    \begin{aligned}
    y_2 &= M \cdot \beta^k \bmod p = 1299 \cdot 949^{853} \bmod 2579 = 2396  
    \end{aligned}  
    $$
    

**Testo cifrato:**  
$$  
C = (435, 2396)  
$$

---

#### **Decifratura (Alice)**

Alice riceve $C = (435, 2396)$ e usa $\alpha = 765$:

1. Calcola:  
    $$  
    z = y_1^{\alpha} \bmod p = 435^{765} \bmod 2579  
    $$
    
2. Calcola l’inverso:  
    $$  
    z^{-1} = (y_1^{\alpha})^{-1} \bmod p  
    $$
    
3. Recupera:  
    $$  
    M = y_2 \cdot z^{-1} \bmod p = 2396 \cdot (435^{-765}) \bmod 2579 = 1299  
    $$
    

✅ Il messaggio originale è stato correttamente recuperato.

---

### **6. Sicurezza del sistema**

#### **a) Sicurezza della generazione delle chiavi**

Un avversario conosce $(p, g, \beta)$ e vuole calcolare $\alpha$.

Poiché:  
$$  
\beta = g^{\alpha} \bmod p  
$$

questa operazione è **equivalente al calcolo del logaritmo discreto**, un problema computazionalmente difficile.

---

#### **b) Sicurezza della cifratura**

Un avversario conosce $(p, g, \beta)$ e il testo cifrato $C = (y_1, y_2)$, con:

$$  
y_1 = g^k \bmod p
$$
$$
y_2 = M \cdot \beta^k \bmod p  
$$

Recuperare $M$ equivale a risolvere il **problema di Diffie-Hellman**:

$$  
\text{dato } (g, g^k, g^{\alpha}), \text{ calcolare } g^{\alpha k}  
$$

che è considerato **computazionalmente intrattabile**.

---

### **7. Sintesi finale**

- Il **crittosistema di El-Gamal** è un sistema **asimmetrico probabilistico**, basato sui problemi del **logaritmo discreto** e di **Diffie-Hellman**.
    
- La **cifratura** produce coppie $(y_1, y_2)$ che variano ogni volta (grazie al valore casuale $k$).
    
- La **sicurezza** deriva dal fatto che conoscere $(p, g, \beta, y_1, y_2)$ non consente di ricavare né $M$ né $\alpha$ in tempi pratici.