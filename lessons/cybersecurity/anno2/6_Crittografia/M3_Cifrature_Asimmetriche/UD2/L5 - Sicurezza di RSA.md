# **Lezione 5: Sicurezza di RSA**

### **1. Introduzione**

La sicurezza del sistema **RSA** si basa su due aspetti principali:

1. **Sicurezza della generazione delle chiavi**
    
2. **Sicurezza della cifratura**
    

Entrambi dipendono dalla difficoltà computazionale di alcune operazioni matematiche, in particolare la **fattorizzazione di grandi numeri**.

---

### **2. Sicurezza della generazione delle chiavi**

#### **Scenario**

L’avversario conosce la **chiave pubblica** $(n, e)$  
e vuole risalire alla **chiave privata** $d$.

Poiché:  
$$  
d = e^{-1} \bmod ((p-1)(q-1))  
$$

per ottenere $d$, deve conoscere $p$ e $q$ oppure $\varphi(n)$.

---

#### **Attacco 1 – Fattorizzazione di n**

Se l’attaccante riuscisse a **fattorizzare $n$**, potrebbe:

1. Calcolare $p$ e $q$
    
2. Calcolare $\varphi(n) = (p - 1)(q - 1)$
    
3. Calcolare $d = e^{-1} \bmod \varphi(n)$
    

Da qui, la **sicurezza di RSA** dipende direttamente dalla **difficoltà di fattorizzare $n = p \cdot q$**.

---

#### **Attacco 2 – Calcolo diretto di $\varphi(n)$**

Se un avversario potesse calcolare $\varphi(n)$,  
sarebbe comunque in grado di **ricavare $p$ e $q$**, perché:

$$  
\begin{cases}  
n = p \cdot q \\\\  
\varphi(n) = (p - 1)(q - 1)  
\end{cases}  
$$

Sostituendo $p = \frac{n}{q}$, si ottiene un’equazione quadratica:

$$  
p^2 - (n - \varphi(n) + 1)p + n = 0  
$$

Le soluzioni di questa equazione sono proprio i valori di $p$ e $q$.

**Esempio:**

$$  
n = 84{,}773{,}093 \  
\varphi(n) = 84{,}754{,}668  
$$

Radici:  
$p = 9539$, $q = 8887$

---

#### **Attacco 3 – Calcolo di d**

Se un avversario potesse **calcolare direttamente $d$** da $(n, e)$,  
potrebbe anche **fattorizzare $n$**.

Un algoritmo in grado di calcolare $d$ può essere usato come **oracolo** in un **algoritmo di tipo Las Vegas**, che fattorizza $n$ con probabilità almeno $1/2$.

---

#### **Equivalenza**

In sintesi:

$$  
\text{Fattorizzare } n \quad \Longleftrightarrow \quad \text{Calcolare } d  
$$

Entrambi i problemi sono considerati **computazionalmente equivalenti**.

---

### **3. Progresso degli attacchi di fattorizzazione**

|Anno|Cifra RSA|Cifre decimali|Bit|Potenza richiesta (MIPS·anno)|Algoritmo|
|---|---|---|---|---|---|
|1984|71|236|0.01|QS||
|1988|106|350|140|QS||
|1993|120|397|825|QS||
|1994|129|425|5000|QS||
|1996|130|430|1000|GNFS||
|1999|155|512|8000|GNFS||
|2003|174|576|—|GNFS||
|2003|200|663|—|GNFS||
|2005|193|640|—|GNFS||

Dove:

- **QS** = Quadratic Sieve
    
- **GNFS** = General Number Field Sieve (il più efficiente noto)
    

**Esempi storici:**

- **RSA-129 (1994):** 1600 computer per 8 mesi (“factoring by e-mail”)
    
- **RSA-155 (1999):** 300 workstation + supercomputer CRAY per 5 mesi
    
- **RSA-193 (2005):** cluster di 30 CPU Opteron 2.2 GHz, 5 mesi
    

---

### **4. Lunghezza del modulo n**

Per garantire sicurezza, i numeri primi $p$ e $q$ devono avere **dimensioni simili** e sufficientemente grandi.

|Uso|Lunghezza consigliata|
|---|---|
|Uso personale|≥ 768 bit|
|Aziende|≥ 1024 bit|
|Autorità di certificazione|≥ 2048 bit|

---

### **5. Sicurezza della cifratura RSA**

#### **Scenario**

L’avversario conosce:  
$$  
(n, e) \quad \text{e} \quad C = M^e \bmod n  
$$  
e vuole ottenere $M$.

#### **Attacco 1 – Fattorizzazione di n**

1. Fattorizza $n$
    
2. Calcola $\varphi(n) = (p-1)(q-1)$
    
3. Trova $d = e^{-1} \bmod \varphi(n)$
    
4. Decifra $C$ calcolando $M = C^d \bmod n$
    

---

#### **Nota**

Non si sa se **calcolare $M$ da $C$ e $(n, e)$** sia **equivalente alla fattorizzazione**.  
È un **problema aperto**: non è stato dimostrato né che lo sia, né che non lo sia.

---

### **6. Attacchi non basati sulla fattorizzazione**

RSA può essere vulnerabile anche a **strategie matematiche o implementative** non legate direttamente alla fattorizzazione.

---

#### **a) Chosen Ciphertext Attack (CCA)**

Un attaccante può scegliere un valore casuale $x$ e costruire:

$$  
C' = C \cdot x^e \bmod n  
$$

Se dispone di un oracolo che restituisce la decifratura $M' = (C')^d \bmod n$, allora:

$$  
M' = (C \cdot x^e)^d = C^d \cdot x^{ed} \equiv M \cdot x \pmod{n}  
$$

Da cui:  
$$  
M = M' \cdot x^{-1} \bmod n  
$$

👉 Questo attacco sfrutta la **proprietà omomorfica** di RSA:  
$$  
(M_1 \cdot M_2)^e \equiv M_1^e \cdot M_2^e \pmod{n}  
$$

---

#### **b) Common Modulus Attack**

Se due utenti condividono **lo stesso modulo $n$** ma usano **diversi esponenti pubblici $e_1, e_2$**,  
e se $\gcd(e_1, e_2) = 1$, allora un messaggio $M$ cifrato per entrambi può essere recuperato.

Sia:

$$  
\begin{cases}  
C_1 = M^{e_1} \bmod n \\\\  
C_2 = M^{e_2} \bmod n  
\end{cases}  
$$

Usando **Euclide esteso**, si trovano $x, y$ tali che:  
$$  
1 = e_1x + e_2y  
$$

Da cui:  
$$  
M = C_1^x \cdot C_2^y \bmod n  
$$

---

#### **c) Low Exponent Attack**

Se lo stesso **esponente $e$** (es. $e=3$) viene usato con **moduli diversi** $(n_1, n_2, n_3)$ e lo stesso messaggio $M$:

$$  
\begin{cases}  
C_1 = M^3 \bmod n_1 \\\\  
C_2 = M^3 \bmod n_2 \\\\  
C_3 = M^3 \bmod n_3  
\end{cases}  
$$

Allora, con il **Teorema Cinese del Resto (CRT)** si può costruire:

$$  
x \equiv  
\begin{cases}  
C_1 \pmod{n_1} \\\\  
C_2 \pmod{n_2} \\\\  
C_3 \pmod{n_3}  
\end{cases}  
$$

che porta a:  
$$  
x \equiv M^3 \pmod{n_1n_2n_3}  
$$

e da cui si ottiene:  
$$  
M = \sqrt[3]{x}  
$$

---

### **7. Informazioni parziali sul messaggio**

Ci si chiede:  
è possibile ottenere **informazioni parziali su $M$** conoscendo solo $C = M^e \bmod n$?

#### **Predicato “mezzo intervallo”**

Definiamo:  
$$  
\text{half}_{n,e}(C) =  
\begin{cases}  
0, & \text{se } M < \frac{n}{2} \\\\  
1, & \text{se } M > \frac{n}{2}  
\end{cases}  
$$

Determinare $\text{half}_{n,e}(C)$ è **equivalente a invertire RSA**.

---

#### **Predicato di parità**

Definiamo:  
$$  
\text{parità}_{n,e}(C) = \text{bit meno significativo di } M  
$$

I due predicati sono **computazionalmente equivalenti**:  
$$  
\text{parità}_{n,e}(C) = \text{half}_{n,e}(C \cdot (2^{-1})^e \bmod n)  
$$

---

### **8. Attacchi alle implementazioni hardware**

Oltre agli attacchi matematici, RSA può essere vulnerabile anche a **side-channel attacks**, cioè attacchi basati sull’analisi fisica del dispositivo.

#### **Timing Attack** _(Kocher, 1997)_

- Analizza il **tempo di esecuzione** dell’esponenziazione modulare.
    
- Permette di ricostruire i bit di $d$ **uno alla volta**.
    

#### **Power Attack** _(Kocher, 1999)_

- Analizza la **potenza consumata** da una smartcard durante la decifratura.
    
- Permette di dedurre la **chiave privata $d$**.
    

---

### **9. Sintesi finale**

- La **sicurezza di RSA** si fonda sulla **difficoltà di fattorizzare numeri molto grandi**.
    
- È necessario utilizzare moduli **almeno da 768–1024 bit** per una protezione adeguata.
    
- Esistono attacchi:
    
    - **Matematici** (basati su proprietà dei moduli e degli esponenti)
        
    - **Fisici** (basati su tempi, potenza o radiazioni elettromagnetiche)
        
- Una corretta implementazione e un’adeguata lunghezza delle chiavi sono **essenziali per mantenere RSA sicuro**.

---

> 💡 **Prossimo passo per l’esame:** ora conosci CCA, Common Modulus e Low Exponent. Gli appelli 240708 e 250715 chiedono di analizzare la variante **Double-RSA** — due cifrature RSA in cascata con esponenti diversi — e spiegarne la sicurezza effettiva (**10 punti**). Vai a [UD5 / L1 – Double-RSA: analisi di sicurezza](../UD5_Approfondimenti_Esame/L1%20-%20Double-RSA%20-%20analisi%20di%20sicurezza.md) per la dimostrazione completa di equivalenza con RSA singolo.