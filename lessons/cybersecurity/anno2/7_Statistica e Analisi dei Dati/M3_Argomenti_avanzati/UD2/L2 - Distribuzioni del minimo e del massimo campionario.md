# **M3 UD2 Lezione 2 - Distribuzioni del minimo e del massimo campionario**

### **1. Introduzione**

Dopo aver introdotto la nozione di **distribuzione campionaria** nella lezione precedente, in questa lezione studiamo come variano, da campione a campione, alcune **grandezze caratteristiche**:

- **minimo campionario**,
    
- **massimo campionario**,
    
- e, successivamente, la **media campionaria**.

L’obiettivo è comprendere **come queste grandezze si distribuiscono**, dato che — essendo funzioni del campione — **sono esse stesse variabili aleatorie**.

---

### **2. Dalla popolazione al campione e viceversa**

Nel linguaggio della statistica:

- la **popolazione** corrisponde alla **distribuzione o densità** $f(t)$ di partenza;
    
- il **campione** è un insieme di $n$ valori generati indipendentemente da tale distribuzione.

Da questa distinzione derivano tre prospettive complementari:

|Prospettiva|Descrizione|
|:--|:--|
|**Probabilista**|Conosce la distribuzione e vuole prevedere come sarà il campione. → _Distribuzioni campionarie_|
|**Statistico inferenziale**|Conosce il campione e vuole risalire alla distribuzione. → _Statistica inferenziale_|
|**Statistico descrittivo**|Conosce solo il campione e lo descrive. → _Statistica descrittiva_|

Da ora in poi useremo **“popolazione”** come sinonimo di **distribuzione** o **densità di probabilità**, per indicare la sorgente da cui il campione è estratto.

---

### **3. Una distribuzione, molti campioni**

Da una stessa popolazione si possono estrarre **molti campioni diversi**.  
Ciascun campione darà valori differenti per:

- minimo,
    
- massimo,
    
- media,
    
- mediana, ecc.

Lo studio delle **distribuzioni campionarie** si concentra quindi su **come variano questi valori da campione a campione**.

---

### **4. Esempio: popolazione uniforme**

Supponiamo di avere una popolazione uniforme definita nell’intervallo $[a,b]$.  
Se estraiamo un campione di $n=3$ eventi, e ne prendiamo la **mediana empirica**, essa non cadrà sempre esattamente sulla mediana vera della popolazione (il punto medio tra $a$ e $b$).

Ripetendo l’esperimento più volte, la mediana empirica cambierà, assumendo valori distribuiti **attorno alla mediana vera**, ma con maggiore concentrazione in quella zona.

👉 La **mediana campionaria** è quindi una **variabile aleatoria** con una sua densità specifica.

---

### **5. Minimo e massimo campionario come variabili aleatorie**

#### **Minimo empirico**

- È il valore più piccolo tra quelli del campione.
    
- Cambia da campione a campione → è una **variabile aleatoria**.
    
- La sua densità non coincide con quella della popolazione, ma è **spostata verso l’estremo inferiore**.

#### **Massimo empirico**

- È il valore più grande nel campione.
    
- Anch’esso varia tra campioni → è una **variabile aleatoria**.
    
- La sua densità è **spostata verso l’estremo superiore**.

In entrambi i casi, la forma della distribuzione dipende dalla **popolazione di partenza** e dal **numero di elementi del campione**.

---

### **6. La media empirica come variabile aleatoria**

Analogamente, anche la **media aritmetica del campione** è una variabile aleatoria:

- Ogni dato del campione è una realizzazione di una variabile casuale.
    
- La media è una funzione di tutte queste realizzazioni.
    
- Quindi anch’essa è una variabile casuale, con una propria **densità campionaria**.

Nel linguaggio della statistica, diremo che:

> La _densità campionaria_ di una grandezza è la distribuzione di probabilità che descrive i valori assunti da tale grandezza al variare dei campioni estratti da una stessa popolazione.

---

### **7. Minimo, massimo e media aritmetica**

D’ora in poi ci concentreremo sullo studio della **densità del minimo, del massimo e della media** di un campione di $n$ eventi estratti da una data popolazione.

Per esempio:

- Se la popolazione è **uniforme**, calcoleremo le densità di minimo e massimo campionario.
    
- Se la popolazione è **esponenziale**, rifaremo lo stesso calcolo.

Nell’unità successiva, sfruttando questi risultati, vedremo come usare la **probabilità inversa** per stimare i parametri della popolazione a partire dal campione.

---

### **8. Strumenti per il calcolo delle densità campionarie**

Gli strumenti necessari sono già noti:

- Per le **densità del minimo e del massimo**:
    
    - useremo i risultati visti nello studio dell’affidabilità dei sistemi:
        
        - **serie → minimo**,
            
        - **parallelo → massimo**.
    
- Per la **media aritmetica**:
    
    - useremo i risultati sulla **somma di variabili aleatorie** (unità precedente).

---

### **9. Analogia con l’affidabilità dei sistemi**

Consideriamo $n$ componenti identici e indipendenti, ciascuno con funzione di sopravvivenza $S(t)$ e funzione di guasto $F(t)$.

|Sistema|Condizione di guasto|Funzione cumulativa|Interpretazione statistica|
|:--|:--|:--|:--|
|**Serie**|Il sistema si guasta se si guasta il **primo componente**|$S_{\text{serie}}(t) = [S(t)]^n$|Densità del **minimo campionario**|
|**Parallelo**|Il sistema si guasta se si guasta **l’ultimo componente**|$F_{\text{parallelo}}(t) = [F(t)]^n$|Densità del **massimo campionario**|

Da queste relazioni si possono ottenere, per derivazione, le densità dei minimi e dei massimi campionari.

---

### **10. Densità del minimo campionario**

Data una popolazione con:

- densità $f(t)$,
    
- cumulativa $F(t)$,
    
- e anticumulativa $S(t) = 1 - F(t)$,

l’anticumulativa del **minimo campionario** di $n$ eventi è:

$$  
S_{\text{min}}(t) = [S(t)]^n  
$$

La **densità del minimo campionario** si ottiene derivando:

$$  
f_{\text{min}}(t) = -\frac{dS_{\text{min}}(t)}{dt} = n , f(t) [S(t)]^{n-1}  
$$

---

### **11. Densità del massimo campionario**

Analogamente, la **cumulativa** del **massimo campionario** è:

$$  
F_{\text{max}}(t) = [F(t)]^n  
$$

E la densità corrispondente è:

$$  
f_{\text{max}}(t) = \frac{dF_{\text{max}}(t)}{dt} = n , f(t) [F(t)]^{n-1}  
$$

---

### **12. Esempio: popolazione uniforme**

Per una distribuzione **uniforme** $f(x)$ su $[0,1]$:

$$  
f(x) = 1, \quad F(x) = x, \quad S(x) = 1 - x  
$$

Allora:

- **Minimo campionario:**  
    $$  
    S_{\text{min}}(x) = (1 - x)^n \quad \Rightarrow \quad f_{\text{min}}(x) = n(1 - x)^{n-1}  
    $$
    
- **Massimo campionario:**  
    $$  
    F_{\text{max}}(x) = x^n \quad \Rightarrow \quad f_{\text{max}}(x) = n x^{n-1}  
    $$

---

### **13. Esempio: popolazione esponenziale**

Per una distribuzione **esponenziale** con parametro $\lambda$:

$$  
f(t) = \lambda e^{-\lambda t}, \quad F(t) = 1 - e^{-\lambda t}, \quad S(t) = e^{-\lambda t}  
$$

- **Minimo campionario:**  
    $$  
    S_{\text{min}}(t) = e^{-n\lambda t} \quad \Rightarrow \quad f_{\text{min}}(t) = n\lambda e^{-n\lambda t}  
    $$  
    → è ancora una **distribuzione esponenziale**, ma con parametro $n\lambda$.
    
- **Massimo campionario:**  
    $$  
    F_{\text{max}}(t) = (1 - e^{-\lambda t})^n \quad \Rightarrow \quad f_{\text{max}}(t) = n\lambda e^{-\lambda t}(1 - e^{-\lambda t})^{n-1}  
    $$
    
    → non è più esponenziale.

---

### **14. Osservazioni importanti**

- Le **densità del minimo e del massimo** dipendono dalla popolazione di origine e dalla dimensione $n$ del campione.
    
- Alcune distribuzioni (come l’esponenziale) **non hanno massimo teorico**, ma ogni campione finito **ha comunque un massimo osservato**.
    
- Lo stesso vale per la **media campionaria**: esiste sempre per un campione finito, ma **non necessariamente per la popolazione** (es. distribuzione di Cauchy).

---

### **15. Sintesi finale**

|Quantità campionaria|Cumulativa|Densità|
|:--|:--|:--|
|**Minimo**|$S_{\text{min}}(t) = [S(t)]^n$|$f_{\text{min}}(t) = n f(t)[S(t)]^{n-1}$|
|**Massimo**|$F_{\text{max}}(t) = [F(t)]^n$|$f_{\text{max}}(t) = n f(t)[F(t)]^{n-1}$|
