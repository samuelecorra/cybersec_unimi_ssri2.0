# **M2 UD2 Lezione 7 - Idiomi di interrogazione - cardinalità e copertura**

### **1. Introduzione**

Questa lezione conclude lo studio degli **idiomi di interrogazione** in algebra relazionale, presentando due costrutti di tipo avanzato:

- **Cardinalità**: per esprimere condizioni sul **numero di associazioni** tra elementi di una relazione.
    
- **Copertura**: per individuare gli elementi che sono **associati a tutti i valori possibili** di un’altra relazione.

Entrambi gli idiomi estendono la capacità espressiva dell’algebra relazionale, permettendo di rappresentare interrogazioni complesse come “almeno due”, “al più uno”, “tutti i valori”, ecc.

---

### **2. Cardinalità – concetto generale**

Sia una relazione $r(A,B)$.  
L’obiettivo è individuare gli $A$ associati a **un certo numero di valori distinti di $B$**.

- **Almeno due $B$**:  
    $$  
    \pi_A(r_{A=A' \land B \neq B'}(\rho_{A',B' \leftarrow A,B}(r)))  
    $$
    
- **Al più un $B$**:  
    $$  
    \pi_A(r) - \pi_A(r_{A=A' \land B \neq B'}(\rho_{A',B' \leftarrow A,B}(r)))  
    $$

---

### **3. Esempio – Almeno due valori associati**

Trovare gli **studenti che hanno sostenuto almeno due esami**.

$$  
\text{ESAMI} := \rho_{M,C \leftarrow Matr,Corso}(\pi_{Matr,Corso}(\text{REGISTRI}))  
$$

$$  
\pi_M(\text{ESAMI}_{M=M' \land C \neq C'}(\rho_{M',C' \leftarrow M,C}(\text{ESAMI})))  
$$

**REGISTRI**

|Voto|Data|Corso|Matr|
|:--|:--|:--|:--|
|30|16-05-05|Basi di dati|33333|
|25|15-03-05|Sistemi|22222|
|27|21-11-05|Basi di dati|22222|
|30|15-06-05|Sistemi|11111|
|28|20-01-05|Basi di dati|11111|

**Risultato**

|M|
|:--|
|22222|
|11111|

---

### **4. Esempio – Al più un valore associato**

Trovare gli **studenti che hanno sostenuto al più un esame**.

$$  
R1 := \rho_{M,C \leftarrow Matr,Corso}(\pi_{Matr,Corso}(\text{REGISTRI}))  
$$

$$  
\text{ESAME} := \pi_M(R1_{M=M' \land C \neq C'}(\rho_{M',C' \leftarrow M,C}(R1)))  
$$

$$  
(\pi_M(R1)) - \text{ESAME}  
$$

**Risultato**

|M|
|:--|
|33333|

---

### **5. Cardinalità – casi generali**

Si può generalizzare il concetto di cardinalità per contare relazioni più complesse.

Sia $r(A,B)$:

- **Almeno tre $B$**:  
    $$  
    \pi_A(\sigma_{A=A' \land A=A'' \land B \neq B' \land B \neq B'' \land B' \neq B''}(r \times \rho_{A',B' \leftarrow A,B}(r) \times \rho_{A'',B'' \leftarrow A,B}(r)))  
    $$
    
- **Almeno $n$ $B$**:  
    si usano $(n-1)$ prodotti cartesiani.
    
- **Al più $n$ $B$**:  
    $$  
    \pi_A(r) - \text{“A associati ad almeno } (n+1) \text{ B”}  
    $$
    
- **Esattamente $n$ $B$**:  
    $$  
    \text{“A associati ad almeno } n \text{ B”} - \text{“A associati ad almeno } (n+1) \text{ B”}  
    $$

---

### **6. Copertura – concetto generale**

Siano due relazioni:

- $r(A,B)$: associazioni tra $A$ e $B$
    
- $s(B)$: insieme di valori di $B$

L’obiettivo è trovare gli $A$ che sono associati a **tutti i valori di $B$ presenti in $s$**.

$$  
\pi_A(r) - \pi_A((\pi_A(r) \times s) - r)  
$$

---

### **7. Esempio – Copertura**

Trovare le **matricole degli studenti** che hanno sostenuto **tutti gli esami**.

$$  
\text{CO} := \pi_{\text{Esame}}(\text{CORSI})  
$$

$$  
\pi_{\text{Matr}}(\text{ESAMI}) - \pi_{\text{Matr}}((\pi_{\text{Matr}}(\text{ESAMI}) \times \text{CO}) - \text{ESAMI})  
$$

**CORSI**

|Titolo|Esame|
|:--|:--|
|Sistemi|CS102|
|Basi di dati|CS101|

**ESAMI**

|Esame|Matr|
|:--|:--|
|CS102|11111|
|CS102|22222|
|CS101|11111|

**Risultato**

|Matr|
|:--|
|11111|

---

### **8. Sintesi**

In questa lezione abbiamo introdotto due nuovi **idiomi algebrici**:

- **Cardinalità**, per contare il numero di associazioni tra elementi di una relazione.
    
- **Copertura**, per verificare se un elemento è associato a **tutti** i valori di un’altra relazione.

Questi costrutti completano l’insieme degli **idiomi di interrogazione** dell’algebra relazionale, offrendo strumenti per esprimere condizioni di tipo **quantitativo e universale**, analoghi ai costrutti `COUNT`, `ALL`, e `EXISTS` presenti in SQL.


---

![](imgs/Pasted%20image%2020251125043618.png)

![](imgs/Pasted%20image%2020251125043627.png)

![](imgs/Pasted%20image%2020251125043641.png)

