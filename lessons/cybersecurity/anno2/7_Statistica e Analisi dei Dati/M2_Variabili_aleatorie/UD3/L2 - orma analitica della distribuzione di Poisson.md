# **M2 UD3 Lezione 2 - Forma analitica della distribuzione di Poisson**

### **1. Dalla Binomiale alla Poissoniana**

Nella **distribuzione Binomiale** studiamo la probabilità di ottenere esattamente $k$ successi su $n$ prove indipendenti, ciascuna con probabilità di successo $p$:

$$  
B(k \mid n p) = \binom{n}{k} p^k q^{n-k}  
$$

Ora ci chiediamo: qual è l’**analogo continuo nel tempo** di questa distribuzione, cioè il modello che descrive **quanti eventi** avvengono in un certo intervallo, quando il tempo è **continuo** e gli eventi sono **indipendenti**?

Come la **Geometrica** è passata all’**Esponenziale** tramite il limite di infittimento, anche la **Binomiale** genera, nel limite continuo, la **distribuzione di Poisson**.

---

### **2. Schema dei modelli**

|**Tipo di esperimento**|**Discreto**|**Continuo**|
|---|---|---|
|**Tempi d’attesa** (attendo il primo evento)|Geometrica|Esponenziale|
|**Conteggio** (conto quanti eventi entro un intervallo)|Binomiale|Poissoniana|

---

### **3. Idea di base**

Immaginiamo un sistema in cui:

- gli eventi accadono **in modo casuale ma con tasso medio costante**,
    
- le occorrenze in intervalli disgiunti sono **indipendenti**.

Esempi tipici:

- numero di richieste a un server in un minuto,
    
- numero di fotoni che colpiscono un sensore,
    
- numero di guasti in un giorno.

Vogliamo determinare la **probabilità che si verifichino esattamente $k$ eventi** in un dato intervallo temporale.

---

### **4. Il limite dalla Binomiale**

Partiamo dalla forma binomiale:

$$  
P(k) = \binom{n}{k} p^k q^{n-k}  
$$

Facciamo tendere:  
$$  
n \to \infty, \quad p \to 0, \quad \text{con } np = \mu \text{ finito.}  
$$

- Poiché $p \to 0$, segue che $q = 1 - p \to 1$.
    
- $n \to \infty$ implica che $k \ll n$.
    
- La media rimane $\langle k \rangle = np = \mu$.

---

### **5. Approssimazione del termine combinatorio**

Per $k \ll n$ si può scrivere:

$$  
\binom{n}{k} = \frac{n!}{k!(n-k)!} \approx \frac{n^k}{k!}  
$$

mentre per il termine probabilistico:

$$  
q^{n-k} = (1 - p)^{n-k} \approx 1  
$$

perché $p$ è molto piccolo.

---

### **6. Passaggio al limite**

Sostituendo e semplificando:

$$  
P(k) \approx \frac{n^k}{k!} p^k q^{n-k} \approx \frac{(np)^k}{k!}  
$$

e poiché $np = \mu$ è costante, otteniamo:

$$  
P(k) \propto \frac{\mu^k}{k!}  
$$

Per normalizzare, imponiamo che la somma delle probabilità sia unitaria:

$$  
\sum_{k=0}^{\infty} P(k) = 1  
$$

Il fattore normalizzante è $e^{-\mu}$.

---

### **7. Forma analitica della Poissoniana**

La **distribuzione di Poisson** è quindi definita da:

$$  
P(k \mid \mu) = \frac{\mu^k e^{-\mu}}{k!}, \quad k = 0, 1, 2, \dots  
$$

dove:

- $\mu$ è la **media** (e anche la **varianza**) dei conteggi,
    
- $k$ è il numero di eventi osservati.

---

### **8. Proprietà fondamentali**

|**Proprietà**|**Formula / Valore**|**Significato**|
|---|---|---|
|**Dominio**|$k = 0, 1, 2, \dots$|Conteggi discreti|
|**Parametro**|$\mu = np$|Media del numero di eventi|
|**Media**|$\langle k \rangle = \mu$|Numero medio di eventi|
|**Varianza**|$\sigma^2 = \mu$|Dispersione uguale alla media|
|**Deviazione standard**|$\sigma = \sqrt{\mu}$|Scarto medio|
|**Normalizzazione**|$\sum_{k=0}^{\infty} P(k) = 1$|Probabilità totale unitaria|

---

### **9. Interpretazione**

La Poissoniana fornisce la probabilità di osservare **esattamente $k$ eventi** in un intervallo di tempo, sapendo che in media ci si aspetta $\mu$ eventi.

$$  
P(k) = \frac{\mu^k e^{-\mu}}{k!}  
$$

È una **famiglia parametrica a un solo parametro**, completamente determinata da $\mu$.

---

### **10. Esempi**

1. **Richieste a un server**
    
    - In media arrivano 4 richieste al secondo ($\mu = 4$).
        
    - Probabilità che ne arrivino esattamente 2:  
        $$  
        P(2) = \frac{4^2 e^{-4}}{2!} = \frac{16 e^{-4}}{2} = 8 e^{-4} \approx 0.1465  
        $$
    
1. **Decadimenti radioattivi**
    
    - Se in media si registrano $\mu = 10$ decadimenti al minuto,  
        la probabilità di osservarne esattamente 12 è:  
        $$  
        P(12) = \frac{10^{12} e^{-10}}{12!} \approx 0.0948  
        $$

---

### **11. Media, varianza e moda**

Per costruzione:  
$$  
\langle k \rangle = \mu, \quad \sigma^2 = \mu  
$$

La **moda** è l’intero $k_{\text{moda}}$ più vicino a $\mu$:  
$$  
k_{\text{moda}} = \lfloor \mu \rfloor  
$$

---

### **12. Cumulativa e anticumulativa**

Le funzioni cumulative si definiscono come:

$$  
F(k) = \sum_{i=0}^{k} P(i), \quad S(k) = \sum_{i=k+1}^{\infty} P(i)  
$$

Non esistono espressioni analitiche semplici per $F(k)$ e $S(k)$; si calcolano quindi:

- con tabelle,
    
- con software di calcolo,
    
- o tramite approssimazioni numeriche.

---

### **13. Mediana e quartili**

Analogamente, non esistono formule chiuse per mediana e quartili della Poissoniana.  
In genere si preferisce usare **valori tabulati** o **calcoli diretti** per piccoli $k$.

---

### **14. Sintesi finale**

| **Distribuzione** | **Formula**                  | **Parametri** | **Media / Varianza**         | **Campo di applicazione**                  |
| ----------------- | ---------------------------- | ------------- | ---------------------------- | ------------------------------------------ |
| **Binomiale**     | $\binom{n}{k} p^k q^{n-k}$   | $n p$         | $\mu = np$, $\sigma^2 = npq$ | Prove discrete, numero finito di tentativi |
| **Poissoniana**   | $\dfrac{\mu^k e^{-\mu}}{k!}$ | $\mu$         | $\mu = \sigma^2$             | Eventi rari in intervalli continui         |

---

### **15. Conclusione**

La **distribuzione di Poisson** rappresenta il **limite della Binomiale** per:  
$$  
n \to \infty, \quad p \to 0, \quad np = \mu \text{ finito.}  
$$

È il modello più usato per descrivere **conteggi di eventi rari**,  
dove le occorrenze sono indipendenti e il tasso medio rimane costante nel tempo.