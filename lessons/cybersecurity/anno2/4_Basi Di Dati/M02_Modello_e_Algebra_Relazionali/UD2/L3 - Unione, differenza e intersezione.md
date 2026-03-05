# **M2 UD2 Lezione 3 - Unione, differenza e intersezione**

### **1. Introduzione**

Dopo aver analizzato gli operatori unari (selezione e proiezione), questa lezione introduce tre **operatori binari insiemistici** dell’algebra relazionale:

- **Unione ($\cup$)**
    
- **Differenza ($-$)**
    
- **Intersezione ($\cap$)**

Tutti e tre richiedono che le relazioni coinvolte abbiano **lo stesso insieme di attributi**, ovvero **lo stesso schema**.

---

### **2. Operatore di unione ($\cup$)**

L’**unione** combina due relazioni che hanno **lo stesso schema**, restituendo una nuova relazione contenente **tutte le tuple** che appartengono **ad almeno una** delle due.

$$  
r_1 \cup r_2  
$$

#### **Condizioni di applicabilità**

- $r_1$ e $r_2$ devono avere lo stesso **insieme di attributi**.
    
- Gli **attributi corrispondenti** devono avere **domini compatibili**.

#### **Caratteristiche**

- **Schema:** schema($r_1$) = schema($r_2$)
    
- **Grado:** grado($r_1$) = grado($r_2$)
    
- **Cardinalità:**

$$  
\max(\text{card}(r_1), \text{card}(r_2)) \le \text{card}(r_1 \cup r_2) \le \text{card}(r_1) + \text{card}(r_2)  
$$

Le **tuple duplicate** vengono **eliminate**.

---

#### **Esempio**

**STUDENTI**

|Nome|Cognome|
|:--|:--|
|Antonio|Rosa|
|Marta|Rossi|
|Carla|Bianchi|

**IMPIEGATI**

|Nome|Cognome|
|:--|:--|
|Marco|Viola|
|Maria|Gialli|
|Matteo|Verdi|
|Carla|Bianchi|

$$  
\text{STUDENTI} \cup \text{IMPIEGATI} = \text{IMPIEGATI} \cup \text{STUDENTI}  
$$

|Nome|Cognome|
|:--|:--|
|Antonio|Rosa|
|Marta|Rossi|
|Carla|Bianchi|
|Marco|Viola|
|Maria|Gialli|
|Matteo|Verdi|

---

### **3. Operatore di differenza ($-$)**

La **differenza** tra due relazioni restituisce tutte le tuple che **appartengono alla prima** ma **non alla seconda**.

$$  
r_1 - r_2  
$$

#### **Condizioni di applicabilità**

- $r_1$ e $r_2$ devono avere lo stesso schema.
    
- I domini degli attributi corrispondenti devono essere compatibili.

#### **Caratteristiche**

- **Schema:** schema($r_1$) = schema($r_2$)
    
- **Grado:** grado($r_1$) = grado($r_2$)
    
- **Cardinalità:**

$$  
0 \le \text{card}(r_1 - r_2) \le \text{card}(r_1)  
$$

> L’operatore **non è commutativo**, poiché l’ordine delle relazioni influisce sul risultato.

---

#### **Esempio**

**STUDENTI**

|Nome|Cognome|
|:--|:--|
|Antonio|Rosa|
|Marta|Rossi|
|Carla|Bianchi|

**IMPIEGATI**

|Nome|Cognome|
|:--|:--|
|Marco|Viola|
|Maria|Gialli|
|Matteo|Verdi|
|Carla|Bianchi|

**STUDENTI − IMPIEGATI**

|Nome|Cognome|
|:--|:--|
|Antonio|Rosa|
|Marta|Rossi|

**IMPIEGATI − STUDENTI**

|Nome|Cognome|
|:--|:--|
|Marco|Viola|
|Maria|Gialli|
|Matteo|Verdi|

---

### **4. Operatore di intersezione ($\cap$)**

L’**intersezione** restituisce le tuple che **compaiono in entrambe** le relazioni.

$$  
r_1 \cap r_2  
$$

#### **Definizione equivalente**

L’intersezione può essere definita **in termini di differenza**:

$$  
r_1 \cap r_2 \equiv r_1 - (r_1 - r_2)  
$$

#### **Caratteristiche**

- **Schema:** schema($r_1$) = schema($r_2$)
    
- **Grado:** grado($r_1$) = grado($r_2$)
    
- **Cardinalità:**

$$  
0 \le \text{card}(r_1 \cap r_2) \le \min(\text{card}(r_1), \text{card}(r_2))  
$$

---

#### **Esempio**

**STUDENTI**

|Nome|Cognome|
|:--|:--|
|Antonio|Rosa|
|Marta|Rossi|
|Carla|Bianchi|

**IMPIEGATI**

|Nome|Cognome|
|:--|:--|
|Marco|Viola|
|Maria|Gialli|
|Matteo|Verdi|
|Carla|Bianchi|

**STUDENTI ∩ IMPIEGATI = IMPIEGATI ∩ STUDENTI**

|Nome|Cognome|
|:--|:--|
|Carla|Bianchi|

---

### **5. Considerazioni finali**

- Gli **operatori insiemistici** ($\cup$, $-$, $\cap$) richiedono **uguaglianza di schema** tra le relazioni.
    
- Se i nomi degli attributi differiscono ma sono **semanticamente equivalenti**, è possibile usare la **ridenominazione ($\rho$)** prima dell’operazione.
    
- Tra tutti gli operatori binari, **solo la differenza non è commutativa**.

---

### **6. Sintesi**

In questa lezione abbiamo visto:

- I tre **operatori insiemistici** dell’algebra relazionale:
    
    - **Unione ($\cup$)**
        
    - **Differenza ($-$)**
        
    - **Intersezione ($\cap$)**
    
- Le **condizioni di applicabilità** (stesso schema e domini compatibili).
    
- Le **proprietà di cardinalità e commutatività**.
    
- L’uso di $\rho$ per uniformare i nomi degli attributi.

Questi operatori costituiscono la **base insiemistica** dell’algebra relazionale, da cui derivano tutte le operazioni più complesse di combinazione e filtraggio dei dati.


---

![](imgs/Pasted%20image%2020251125043248.png)

![](imgs/Pasted%20image%2020251125043259.png)

![](imgs/Pasted%20image%2020251125043307.png)

![](imgs/Pasted%20image%2020251125043319.png)

