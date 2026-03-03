
# **Lezione 1: Algoritmo RSA**

### **1. Introduzione e contesto storico**

L’**algoritmo RSA** è il più celebre schema di **cifratura a chiave pubblica**.  
È stato proposto nel **1977** da **Rivest, Shamir e Adleman** del **MIT**, dai cui cognomi deriva il nome **RSA**.

RSA rappresenta una pietra miliare della **crittografia moderna**, perché consente di:

- cifrare e decifrare messaggi senza condividere una chiave segreta,
    
- firmare digitalmente documenti e messaggi,
    
- scambiare in sicurezza chiavi per cifrature simmetriche.
    

---

### **2. Fondamento matematico**

L’algoritmo RSA è basato su operazioni di **esponenziazione modulare** in un **campo finito**:

$$  
C \equiv M^e \pmod{n}  
$$

dove:

- $M$ è il messaggio da cifrare (convertito in un intero),
    
- $e$ è l’esponente di cifratura,
    
- $n$ è il modulo, cioè il prodotto di due numeri primi $p$ e $q$.
    

L’esponenziazione richiede circa $O((\log n)^3)$ operazioni,  
mentre la **sicurezza** dipende dalla **difficoltà della fattorizzazione** di grandi numeri $n = p \cdot q$,  
che è un problema computazionalmente **duro**, stimato in $O(e^{\log n \log \log n})$ operazioni.

Per questo motivo, nella pratica RSA utilizza **numeri di grandi dimensioni** (almeno **1024 bit**, spesso **2048** o più).

---

### **3. Generazione delle chiavi RSA**

Ogni utente genera la propria coppia di chiavi seguendo questi passaggi:

1. Si scelgono **due numeri primi grandi** $p$ e $q$.
    
2. Si calcola il **modulo**:  
    $$  
    n = p \cdot q  
    $$
    
3. Si calcola **φ(n)** (funzione di Eulero):  
    $$  
    \varphi(n) = (p-1)(q-1)  
    $$
    
4. Si sceglie un **esponente di cifratura $e$** tale che:  
    $$  
    \gcd(e, \varphi(n)) = 1  
    $$
    
5. Si calcola l’**esponente di decifratura $d$** come inverso moltiplicativo di $e$ modulo $\varphi(n)$:  
    $$  
    e \cdot d \equiv 1 \pmod{\varphi(n)}  
    $$
    

**Chiave pubblica:** $(n, e)$  
**Chiave privata:** $(n, d)$

---

### **4. Cifratura e decifratura**

#### **Cifratura**

Quando Bob vuole inviare un messaggio $M$ ad Alice:

1. Recupera la chiave pubblica di Alice $(n, e)$.
    
2. Calcola il testo cifrato:  
    $$  
    C = M^e \bmod n  
    $$
    

#### **Decifratura**

Quando Alice riceve $C$, usa la propria chiave privata $(n, d)$ per ottenere:  
$$  
M = C^d \bmod n  
$$

Solo Alice può decifrare, perché solo lei conosce $d$.

---

### **5. Esempio 1**

#### **Dati**

- $p = 47$, $q = 71$
    
- $n = 47 \cdot 71 = 3337$
    
- $\varphi(n) = (46)(70) = 3220$
    

Scelta:  
$$  
e = 79,\quad \gcd(79, 3220) = 1  
$$

Calcolo di $d$:  
$$  
79 \cdot 1019 \equiv 1 \pmod{3220}  
$$

Dunque:

- **Chiave pubblica:** $(n=3337, e=79)$
    
- **Chiave privata:** $(n=3337, d=1019)$
    

#### **Cifratura**

$$  
C = 688^{79} \bmod 3337 = 1570  
$$

#### **Decifratura**

$$  
M = 1570^{1019} \bmod 3337 = 688  
$$

✅ Il messaggio originale è recuperato correttamente.

---

### **6. Esempio 2**

#### **Dati**

- $p = 11$, $q = 5$
    
- $n = 11 \cdot 5 = 55$
    
- $\varphi(n) = (10)(4) = 40$
    
- Scelta $e = 3$, $\gcd(3, 40) = 1$
    
- Calcolo $d = 27$, perché:  
    $$  
    3 \cdot 27 \equiv 1 \pmod{40}  
    $$
    

**Chiavi:**

- Pubblica: $(n=55, e=3)$
    
- Privata: $(n=55, d=27)$
    

#### **Cifratura**

$$  
C = 57^{3} \bmod 55 = 47  
$$

#### **Decifratura**

$$  
M = 47^{27} \bmod 55 = 57  
$$

✅ Anche in questo caso la decifratura è corretta.

---

### **7. Esempio con blocchi binari**

Quando il messaggio è binario (es. una sequenza di bit), esso viene **suddiviso in blocchi** e ogni blocco viene cifrato come un numero:

Esempio:  
$$  
001010011110010001010011110010  
$$

Si suddivide in blocchi e si calcola:  
$$
\begin{aligned}
15 &\leftarrow 5^3 \bmod 55 \\
13 &\leftarrow 7^3 \bmod 55 \\
2 &\leftarrow 18^3 \bmod 55
\end{aligned}
$$


Cifrato finale: **15, 13, 2**

Decifratura:  
$$
\begin{aligned}
5 &\leftarrow 15^{27} \bmod 55 \\  
7 &\leftarrow 13^{27} \bmod 55 \\  
18 &\leftarrow 2^{27} \bmod 55  
\end{aligned}
$$

Ricostruendo i blocchi, si ottiene il messaggio originale.

---

### **8. Sintesi finale**

- **RSA** è un **cifrario a blocchi**, in cui il messaggio è un numero compreso tra $0$ e $n-1$.
    
- La **funzione one-way** utilizzata è l’**esponenziazione modulare**.
    
- La **sicurezza** dell’algoritmo si fonda sulla **difficoltà della fattorizzazione** di grandi numeri composti.
    
- È utilizzato sia per **cifratura**, sia per **firme digitali** e **scambio sicuro di chiavi**.