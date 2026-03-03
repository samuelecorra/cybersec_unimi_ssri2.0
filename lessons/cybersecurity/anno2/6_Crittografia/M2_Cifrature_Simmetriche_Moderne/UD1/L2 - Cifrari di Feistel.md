# **Lezione 2: Cifrari di Feistel**

---

### **1. Origini e principi generali**

La **struttura di Feistel** è alla base di molti cifrari a blocchi moderni.  
Fu ideata da **Horst Feistel** nel 1973 come applicazione pratica dei **principi di Shannon** formulati nel 1949.

L’idea centrale è costruire una **rete di sostituzioni e permutazioni** (S-P network), dove più cifrature vengono applicate **in sequenza**.  
Ogni nuovo livello (“round”) combina sostituzioni e permutazioni, producendo un cifrario complessivamente **più complesso** e sicuro rispetto a ciascuna singola trasformazione.

---

### **2. Principi di Shannon**

#### **2.1 Diffusione**

Il principio di **diffusione** mira a **nascondere la struttura statistica** del testo in chiaro.  
Ogni simbolo del testo cifrato deve dipendere da **più simboli del testo in chiaro**, così che eventuali regolarità si disperdano.

Esempio semplificato su caratteri:

$$  
y_n = \sum_{i=1}^{k} m_{n+i} \pmod{26}  
$$

In ambito binario, la diffusione si realizza tramite **permutazioni** dei bit, cioè cambiando il loro ordine secondo una regola determinata dalla chiave.  
Per un blocco di $n$ bit, lo **spazio delle chiavi** possibili per la permutazione è:

$$  
n!  
$$

Questa operazione è realizzata da una **P-box** (_Permutation box_).

![](imgs/Pasted%20image%2020260221221939.png)

---

#### **2.2 Confusione**

Il principio di **confusione** serve a rendere **complessa e non lineare** la relazione tra testo cifrato e chiave.  
Anche conoscendo la statistica del testo cifrato, deve essere impossibile dedurre la chiave.

Si ottiene tramite un meccanismo di **sostituzione**: ogni parola binaria viene sostituita con un’altra parola binaria secondo una regola definita dalla chiave.

Per $n$ bit, lo spazio delle chiavi possibili è:

$$  
2^n!  
$$

Questa operazione è realizzata da una **S-box** (_Substitution box_).

![](imgs/Pasted%20image%2020260221222122.png)

---

### **3. Struttura del cifrario di Feistel**

Ogni **round** di un cifrario di Feistel divide il blocco di input in due parti:

$$  
(L_{i-1}, R_{i-1})  
$$

e applica una **funzione F** (dipendente dalla sottochiave $K_i$) a una delle due metà, combinandola con l’altra tramite XOR:

![](imgs/Pasted%20image%2020260221222300.png)

#### **Cifratura**

$$  
\begin{cases}  
L_i = R_{i-1} \\\\  
R_i = L_{i-1} \oplus F(K_i, R_{i-1})  
\end{cases}  
$$

#### **Decifratura**

$$  
\begin{cases}  
R_{i-1} = L_i \\\\  
L_{i-1} = R_i \oplus F(K_i, L_i)  
\end{cases}  
$$

Il punto chiave è che **la funzione round F non deve essere invertibile**,  
poiché l’intera struttura di Feistel **garantisce l’invertibilità complessiva**.  
Questo rende l’algoritmo efficiente e versatile.

---

### **4. Funzionamento generale**

Indichiamo con $w_0$ lo **stato iniziale** (il plaintext).  
A ogni round si applica una trasformazione $g$ dipendente dalla sottochiave $k_i$:

$$  
w_i = g(w_{i-1}, k_i)  
$$

Nel cifrario di Feistel, $g$ opera nel modo seguente:

$$  
w_i = (L_i, R_i) = g((L_{i-1}, R_{i-1}), k_i)  
$$

dove

$$  
L_i = R_{i-1}, \quad R_i = L_{i-1} \oplus f(R_{i-1}, K_i)  
$$

---

### **5. Parametri fondamentali di una rete Feistel**

- **Dimensione del blocco:**  
    Blocchi grandi aumentano la sicurezza ma riducono la velocità.
    
- **Dimensione della chiave:**  
    Chiavi più grandi garantiscono maggiore sicurezza ma aumentano i tempi di elaborazione.
    
- **Numero di round:**  
    Tutte le fasi hanno la stessa struttura, e un numero maggiore di round accresce la robustezza contro la crittoanalisi.
    
- **Schedulazione della chiave:**  
    A partire da una chiave iniziale $K$ vengono generate tante **sottochiavi $K_i$** quanti sono i round.
    
- **Funzione round $f$:**  
    Più è complessa e non lineare, più la cifratura è resistente agli attacchi.
    

---

### **6. Vantaggi della struttura di Feistel**

**Durante la cifratura:**

- È sufficiente implementare un solo round e ripeterlo più volte.
    
- Lo stesso codice viene riutilizzato per ogni round.
    

**Durante la decifratura:**

- Si usa **lo stesso algoritmo** impiegato per la cifratura.
    
- Cambia solo l’ordine delle sottochiavi (inverso).
    

Questo consente **efficienza e simmetria** nell’implementazione.

---

### **7. Esempi di cifrari di Feistel**

- **DES (Data Encryption Standard)**
    
- **Blowfish**
    

Entrambi utilizzano la struttura a rete di Feistel come base della loro sicurezza.

---

### **8. Sintesi finale**

Abbiamo visto:

- Le caratteristiche fondamentali dei **cifrari di Feistel**.
    
- Come la loro struttura realizzi concretamente i **principi di confusione e diffusione** enunciati da Shannon.
    
- Il motivo per cui **la cifratura è sempre invertibile**, indipendentemente dalla specifica funzione $f$ adottata.
    

In sintesi, la rete di Feistel rappresenta il **cuore concettuale** di gran parte dei cifrari simmetrici moderni,  
permettendo di combinare sicurezza teorica e implementazione pratica.