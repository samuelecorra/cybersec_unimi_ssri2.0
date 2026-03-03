# **Lezione 3: Fattorizzazione e test di primalità**

### **1. Infinità dei numeri primi**

Esistono **infiniti numeri primi**.  
La dimostrazione classica procede **per assurdo**:

- Supponiamo che esista un **massimo numero primo**, chiamato $p_{max}$.
    
- Consideriamo il numero:  
    $$  
    N = p_1 \cdot p_2 \cdot \dots \cdot p_{max} + 1  
    $$
    
- $N$ è maggiore di $p_{max}$, quindi dovrebbe essere **composto**.
    
- Tuttavia, **nessuno dei primi noti divide $N$**, poiché tutti lasciano resto $1$.
    
- Di conseguenza, $N$ deve essere **un nuovo numero primo**, contraddicendo l’ipotesi iniziale.
    

👉 **Conclusione:** i numeri primi sono infiniti.

---

### **2. Distribuzione dei numeri primi**

La funzione $\pi(x)$ indica il **numero di numeri primi minori o uguali a $x$**.

$$  
\pi(x) = \text{numero di primi in } [2, x]  
$$

#### **Teorema dei numeri primi**

Approssimazione asintotica:  
$$  
\pi(x) \sim \frac{x}{\ln x}  
$$

#### **Esempio numerico**

$$  
\pi(10^{10}) = 455{,}052{,}511 \  
\frac{10^{10}}{\ln 10^{10}} \approx 434{,}294{,}482  
$$

L’errore relativo è circa del **4%**, confermando l’accuratezza dell’approssimazione.

---

### **3. Probabilità che un numero casuale sia primo**

La **probabilità** che un intero casuale in $[2, x]$ sia primo è circa:  
$$  
P(\text{primo}) \approx \frac{1}{\ln x}  
$$

#### **Esempio 1 – Numeri di 512 bit**

- $\ln 2^{512} \approx 354.89$
    
- $P(\text{primo}) \approx \frac{1}{354.89}$
    
- In media, serve provare circa **355 numeri casuali** per trovarne uno primo.
    

Se si scelgono solo numeri **dispari**, i tentativi medi si dimezzano → circa **178 prove**.

#### **Esempio 2 – Numeri di 1024 bit**

- $\ln 2^{1024} \approx 709.78$
    
- $P(\text{primo}) \approx \frac{1}{709.78}$
    
- In media servono circa **710 prove**, o **355** se si scelgono solo numeri dispari.
    

---

### **4. Test di primalità**

I **test di primalità** servono a determinare se un numero $n$ è **primo o composto**.

Esistono due categorie principali:

#### **• Test deterministici**

- Forniscono un **risultato certo** (nessuna probabilità di errore).
    
- Storicamente molto lenti: fino al 2002 il miglior algoritmo era una variante di **Cohen–Lenstra** basata su **Adleman et al. (1983)**, con complessità:  
    $$  
    O((\ln p)^{\log \log \log p})  
    $$
    
- Nel **2002**, **Agrawal, Kayal e Saxena (AKS)** scoprirono un algoritmo **deterministico polinomiale**:  
    $$  
    O((\ln p)^{6 + \varepsilon})  
    $$
    

#### **• Test probabilistici**

- Forniscono un risultato **probabilistico**:
    
    - "Probabilmente primo"
        
    - "Composto"
        
- Possiedono una **probabilità di errore ≤ (1/2)**.
    
- Ripetendo il test $t$ volte, la probabilità di errore diventa:  
    $$  
    P_{errore} \le \left(\frac{1}{2}\right)^t  
    $$
    

---

### **5. Principali test probabilistici**

#### **Test di Solovay–Strassen (1977)**

- Basato sul criterio di **Euler–Jacobi**.
    
- Probabilità di errore ≤ $(1/2)^t$.
    
- Poco usato oggi, sostituito da test più efficienti.
    

#### **Test di Miller–Rabin**

- È **il più veloce e utilizzato nella pratica**.
    
- Probabilità di errore ≤ $(1/4)^t$.
    
- Basato sul **Teorema di Fermat**:  
    se $p$ è primo e $a \in Z_p^*$, allora  
    $$  
    a^{p-1} \equiv 1 \pmod{p}  
    $$
    

---

### **6. Algoritmo di Miller–Rabin**

Sia $p$ un numero dispari:

1. Si scrive:  
    $$  
    p - 1 = 2^k \cdot q \quad \text{con } q \text{ dispari e } k > 0  
    $$
    
2. Si sceglie un $a$ casuale con $1 < a < p - 1$
    
3. Si calcolano i seguenti valori:  
    $$  
    a^q, \ a^{2q}, \ a^{4q}, \ \dots, \ a^{2^k q} \pmod{p}  
    $$
    
4. Se:
    
    - $a^q \equiv 1 \pmod{p}$ oppure
        
    - $\exists j \in [0, k-1] \text{ tale che } a^{2^j q} \equiv -1 \pmod{p}$  
        → **$p$ è probabilmente primo.**
        
5. In caso contrario → **$p$ è composto.**
    

---

### **7. Insieme dei witness di Miller–Rabin**

Definiamo l’insieme dei **witness** (testimoni di compositezza):

$$
W_{MR}(p) = \left\{ 
\, a \;:\; a^q \not\equiv 1 \pmod{p} \;\text{ e }\; 
a^{2^j q} \not\equiv -1 \pmod{p}, \;
\forall j \in [0,\, k-1]
\,\right\}
$$


- Se $p$ è **primo**, allora $W_{MR}(p)$ è **vuoto**.
    
- Se $p$ è **composto**, allora:  
    $$  
    |W_{MR}(p)| \ge \frac{3}{4} \cdot \varphi(p)  
    $$
    

---

### **8. Esempio 1 – Miller–Rabin con p = 29**

$$  
p - 1 = 2^2 \cdot 7  
$$

Quindi:  
$$  
W_{MR}(29) = { a : a^7 \not\equiv 1 \pmod{29}, \ a^{2^j \cdot 7} \not\equiv -1 \pmod{29} }  
$$

- Per $a = 10$:  
    $$  
    10^7 \equiv 17 \pmod{29}, \quad (10^7)^2 \equiv 28 \equiv -1 \pmod{29}  
    $$  
    → output: **“primo”**
    
- Per $a = 2$:  
    $$  
    2^7 \equiv 12 \pmod{29}, \quad (2^7)^2 \equiv 28 \equiv -1 \pmod{29}  
    $$  
    → output: **“primo”**
    

Provando per tutti $a \in [1, 28]$ il risultato è sempre “primo”.

---

### **9. Esempio 2 – Miller–Rabin con p = 221**

$$  
p - 1 = 2^2 \cdot 55  
$$

$$  
W_{MR}(221) = { a : a^{55} \not\equiv 1 \pmod{221}, \ a^{2^j \cdot 55} \not\equiv -1 \pmod{221} }  
$$

- Per $a = 5$:  
    $$  
    5^{55} \equiv 112 \pmod{221}, \quad (5^{55})^2 \equiv 168 \pmod{221}  
    $$  
    → output: **“composto”**
    
- Per $a = 21$:  
    $$  
    21^{55} \equiv 200 \pmod{221}, \quad (21^{55})^2 \equiv 220 \equiv -1 \pmod{221}  
    $$  
    → output: **“primo”**
    

---

### **10. Sintesi finale**

In questa lezione abbiamo visto:

- La **distribuzione dei numeri primi** e il motivo per cui sono infiniti.
    
- I **test di primalità**, sia deterministici (come AKS) che probabilistici.
    
- Il **test di Miller–Rabin**, oggi il più usato nella pratica, grazie alla sua **velocità e affidabilità statistica**.
    

**Concetti chiave:**

- I numeri primi sono infiniti ma distribuiti in modo irregolare.
    
- La generazione di grandi primi (512 o 1024 bit) è alla base della sicurezza RSA.
    
- I test di primalità probabilistici sono fondamentali per generare rapidamente tali numeri.