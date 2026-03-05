# **M2 UD2 Lezione 5 - Equivalenza e ottimizzazione delle espressioni algebriche**

### **1. Introduzione**

L’**algebra relazionale** è un **linguaggio procedurale**, e dunque **l’ordine delle operazioni** influisce direttamente sul **costo di esecuzione**.  
Scrivere un’espressione in modo ottimizzato significa ridurre la **dimensione dei risultati intermedi** e migliorare le **prestazioni complessive**.

Le **query SQL** vengono **tradotte internamente** dal **DBMS** in **espressioni algebriche**, che poi vengono **ottimizzate automaticamente**.

---

### **2. Equivalenza di espressioni**

Una **stessa query** può essere rappresentata da **diverse espressioni algebriche**.  
Due espressioni $E_1$ ed $E_2$ sono **equivalenti** se producono **lo stesso risultato** indipendentemente dai dati presenti nelle relazioni.

$$  
E_1 \equiv E_2  
$$

Le **trasformazioni di equivalenza** permettono di riscrivere un’espressione in una forma **equivalente ma meno costosa**, migliorando l’efficienza dell’esecuzione.

---

### **3. Principali trasformazioni di equivalenza**

- **Atomizzazione delle selezioni**
    
- **Idempotenza delle proiezioni**
    
- **Anticipazione delle selezioni rispetto al join**
    
- **Anticipazione delle proiezioni rispetto al join**
    
- **Inglobamento della selezione in un prodotto cartesiano**
    
- **Distributività** della selezione rispetto a $\cup$ e $-$
    
- **Distributività** della proiezione rispetto a $\cup$
    
- **Distributività** del join rispetto a $\cup$
    
- **Corrispondenza tra operatori insiemistici e selezioni**
    
- **Commutatività e associatività** di quasi tutti gli operatori binari (tranne la differenza)

---

### **4. Atomizzazione delle selezioni**

$$  
\sigma_{C_1 \land C_2}(E) \ \equiv \ \sigma_{C_1}(\sigma_{C_2}(E))  
$$

- $E$: espressione
    
- $C_1, C_2$: condizioni logiche

**Esempio**

$$  
\sigma_{\text{Cognome}='Rossi' \land \text{Nome}='Mario'}(\text{STUDENTI}) \ \equiv \ \sigma_{\text{Cognome}='Rossi'}(\sigma_{\text{Nome}='Mario'}(\text{STUDENTI}))  
$$

---

### **5. Idempotenza delle proiezioni**

$$  
\pi_X(E) \ \equiv \ \pi_X(\pi_{X \cup Y}(E))  
$$

- $E$: espressione
    
- $X, Y$: insiemi di attributi

**Esempio**

$$  
\pi_{\text{Cognome}}(\text{STUDENTI}) \ \equiv \ \pi_{\text{Cognome}}(\pi_{\text{Cognome, Nome}}(\text{STUDENTI}))  
$$

---

### **6. Anticipazione della selezione rispetto al join**

$$  
\sigma_C(E_1 \bowtie E_2) \ \equiv \ E_1 \bowtie \sigma_C(E_2)  
$$

- $E_1$, $E_2$: espressioni
    
- $C$: condizione che coinvolge **solo attributi di $E_2$**

**Esempio**

$$  
\sigma_{\text{Corso}='BasiDati'}(\text{STUDENTI} \bowtie \text{ESAMI}) \ \equiv \ \text{STUDENTI} \bowtie \sigma_{\text{Corso}='BasiDati'}(\text{ESAMI})  
$$

---

### **7. Anticipazione della proiezione rispetto al join (1)**

$$  
\pi_{X_1Y_2}(E_1 \bowtie E_2) \ \equiv \ E_1 \bowtie \pi_{Y_2}(E_2)  
$$

- $E_1$, $E_2$: espressioni
    
- $Y_2 \supseteq (X_1 \cap X_2)$: attributi di $E_2$ coinvolti nel join

**Esempio**

$$  
\pi_{\text{Matr,Cognome,Nome,Corso}}(\text{STUDENTI} \bowtie \text{ESAMI}) \ \equiv \ \text{STUDENTI} \bowtie \pi_{\text{Matr,Corso}}(\text{ESAMI})  
$$

---

### **8. Anticipazione della proiezione rispetto al join (2)**

Combinando **idempotenza** e **anticipazione**, si può scrivere:

$$  
\pi_Y(E_1 \bowtie E_2) \ \equiv \ \pi_Y(\pi_{Y_1}(E_1) \bowtie \pi_{Y_2}(E_2))  
$$

Dove:

- $Y_1 = (X_1 \cap Y) \cup J_1$
    
- $Y_2 = (X_2 \cap Y) \cup J_2$  
    ($J_1$ e $J_2$ = attributi coinvolti nel join)

**Esempio**

$$  
\pi_{\text{Cognome,Corso}}(\text{STUDENTI} \bowtie \text{ESAMI}) \ \equiv \ \pi_{\text{Cognome,Corso}}(\pi_{\text{Matr,Cognome}}(\text{STUDENTI}) \bowtie \pi_{\text{Matr,Corso}}(\text{ESAMI}))  
$$

> In questo modo si eliminano subito gli attributi non coinvolti nel join, riducendo il costo computazionale.

---

### **9. Inglobamento della selezione in un prodotto cartesiano**

$$  
\sigma_C(E_1 \times E_2) \ \equiv \ E_1 \bowtie_C E_2  
$$

- $E_1$, $E_2$: espressioni
    
- $C$: condizione su attributi di entrambe

**Esempio**

$$  
\sigma_{\text{Matr}=\text{Matr-st}}(\text{STUDENTI} \times \text{ESAMI}) \ \equiv \ \text{STUDENTI} \bowtie_{\text{Matr}=\text{Matr-st}} \text{ESAMI}  
$$

---

### **10. Distributività della selezione**

- **Rispetto all’unione:**
    

$$  
\sigma_C(E_1 \cup E_2) \ \equiv \ \sigma_C(E_1) \cup \sigma_C(E_2)  
$$

- **Rispetto alla differenza:**
    

$$  
\sigma_C(E_1 - E_2) \ \equiv \ \sigma_C(E_1) - \sigma_C(E_2)  
$$

> Da quest’ultima deriva anche la distributività rispetto all’intersezione.

**Esempio**

$$  
\sigma_{\text{Cognome}='Rossi'}(\text{STUDENTI} \cup \text{IMP}) \ \equiv \ \sigma_{\text{Cognome}='Rossi'}(\text{STUDENTI}) \cup \sigma_{\text{Cognome}='Rossi'}(\text{IMP})  
$$

---

### **11. Distributività della proiezione rispetto all’unione**

$$  
\pi_X(E_1 \cup E_2) \ \equiv \ \pi_X(E_1) \cup \pi_X(E_2)  
$$

> Non vale invece la distributività della proiezione rispetto a $-$ e $\cap$.

**Esempio**

$$  
\pi_{\text{Cognome,Nome}}(\text{STUDENTI} \cup \text{IMP}) \ \equiv \ \pi_{\text{Cognome,Nome}}(\text{STUDENTI}) \cup \pi_{\text{Cognome,Nome}}(\text{IMP})  
$$

---

### **12. Distributività del join rispetto all’unione**

$$  
E \bowtie (E_1 \cup E_2) \ \equiv \ (E \bowtie E_1) \cup (E \bowtie E_2)  
$$

**Esempio**

$$  
\text{TASSE} \bowtie (\text{STUDENTI} \cup \text{IMP}) \ \equiv \ (\text{TASSE} \bowtie \text{STUDENTI}) \cup (\text{TASSE} \bowtie \text{IMP})  
$$

---

### **13. Corrispondenza tra operatori insiemistici e selezioni**

$$  
\sigma_{C_1 \lor C_2}(E) \ \equiv \ \sigma_{C_1}(E) \cup \sigma_{C_2}(E)  
$$

$$  
\sigma_{C_1 \land C_2}(E) \ \equiv \ \sigma_{C_1}(E) \cap \sigma_{C_2}(E) \ \equiv \ \sigma_{C_1}(E) \bowtie \sigma_{C_2}(E)  
$$

$$  
\sigma_{C_1 \land \lnot C_2}(E) \ \equiv \ \sigma_{C_1}(E) - \sigma_{C_2}(E)  
$$

**Esempi**

$$  
\sigma_{\text{Corso}='Sistemi' \lor \text{Voto}=30}(\text{ESAMI}) \ \equiv \ \sigma_{\text{Corso}='Sistemi'}(\text{ESAMI}) \cup \sigma_{\text{Voto}=30}(\text{ESAMI})  
$$

$$  
\sigma_{\text{Corso}='Sistemi' \land \text{Voto}=30}(\text{ESAMI}) \ \equiv \ \sigma_{\text{Corso}='Sistemi'}(\text{ESAMI}) \cap \sigma_{\text{Voto}=30}(\text{ESAMI})  
$$

---

### **14. Commutatività e associatività**

Per tutti gli operatori binari (tranne la differenza) valgono:

$$  
E_1 \ \text{op} \ (E_2 \ \text{op} \ E_3) \ \equiv \ (E_1 \ \text{op} \ E_2) \ \text{op} \ E_3  
$$

$$  
E_1 \ \text{op} \ E_2 \ \equiv \ E_2 \ \text{op} \ E_1  
$$

con $\text{op} \in {\cup, \cap, \times, \bowtie}$.

**Esempio**

$$  
\text{STUDENTI} \bowtie (\text{ESAMI} \bowtie \text{CORSI}) \ \equiv \ (\text{STUDENTI} \bowtie \text{ESAMI}) \bowtie \text{CORSI}  
$$

---

### **15. Formule utili**

- $r \bowtie r \equiv r$
    
- $r \cup r \equiv r$
    
- $r \cap r \equiv r$
    
- $r - r \equiv \varnothing$
    
- $r \bowtie \varnothing \equiv \varnothing$
    
- $r \times \varnothing \equiv \varnothing$
    
- $\sigma_C(\varnothing) \equiv \varnothing$
    
- $\pi_X(\varnothing) \equiv \varnothing$
    
- $r \cup \varnothing \equiv r$
    
- $r - \varnothing \equiv r$
    
- $r \cap \varnothing \equiv \varnothing$
    
- $\varnothing - r \equiv \varnothing$

---

### **16. Sintesi finale**

In questa lezione abbiamo visto:

- Il concetto di **equivalenza tra espressioni algebriche**
    
- Le principali **trasformazioni di equivalenza**
    
- Le regole di **distributività, commutatività e associatività**
    
- Le **formule algebriche fondamentali** utili per l’ottimizzazione

L’obiettivo è ridurre il **costo di esecuzione** e migliorare l’efficienza delle query, ricordando che il **risultato di un’espressione algebrica è sempre una relazione**, anche se priva di nome.


---

![](imgs/Pasted%20image%2020251125043506.png)

![](imgs/Pasted%20image%2020251125043516.png)

![](imgs/Pasted%20image%2020251125043527.png)

