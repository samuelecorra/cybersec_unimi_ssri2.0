# **M2 UD2 Lezione 6 - Idiomi di interrogazione – esiste/per ogni, minimo/massimo**

### **1. Introduzione**

Questa lezione affronta alcuni **idiomi di interrogazione** che permettono di esprimere in algebra relazionale concetti più complessi, come:

- **esistenza** e **universalità** delle tuple
    
- **ricerca di valori estremi** (minimo, massimo)
    
- **valori estremi relativi** per ciascun gruppo
    
- **tuple corrispondenti ai valori estremi**

Questi costrutti consentono di rappresentare **query di alto livello** (che in SQL si esprimono con `EXISTS`, `FOR ALL`, `MIN`, `MAX`, ecc.) direttamente in termini algebrici.

---

### **2. Esiste / Per ogni**

Sia una relazione $r(A,B)$.

- **Esiste**: trovare gli $A$ per cui **esiste** almeno un $B$ che soddisfa una condizione $p(B)$
    $$  
    \pi_A(\sigma_{p(B)}(r))  
    $$
    
- **Per ogni**: trovare gli $A$ per i quali **tutti** i $B$ soddisfano $p(B)$
    $$  
    \pi_A(r) - \pi_A(\sigma_{\lnot p(B)}(r))  
    $$

---

### **3. Esempio – Esiste**

Trovare gli **studenti** che hanno preso **più di 26** in **almeno un esame**:

$$  
\pi_{\text{Matr}}(\sigma_{\text{Voto}>26}(\text{REGISTRI}))  
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

|Matr|
|:--|
|33333|
|22222|
|11111|

---

### **4. Esempio – Per ogni**

Trovare gli **studenti** che hanno preso **più di 26 in tutti gli esami**:

$$  
\pi_{\text{Matr}}(\text{REGISTRI}) - \pi_{\text{Matr}}(\sigma_{\text{Voto} \le 26}(\text{REGISTRI}))  
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

|Matr|
|:--|
|33333|
|11111|

---

### **5. Minimo e massimo assoluto**

Sia una relazione $r(A,B)$.

- **Minimo assoluto**:
    $$  
    \text{val} := \pi_B(r)  
    $$  
    $$  
    \text{val} - \pi_B(\text{val}_{B > B'}(\rho_{B' \leftarrow B}(\text{val})))  
    $$
    
- **Massimo assoluto**:
    $$  
    \text{val} := \pi_B(r)  
    $$  
    $$  
    \text{val} - \pi_B(\text{val}_{B < B'}(\rho_{B' \leftarrow B}(\text{val})))  
    $$

---

### **6. Esempio – Minimo assoluto**

Trovare il **voto minimo**:

$$  
\text{VOTI} := \rho_{V \leftarrow Voto}(\pi_{Voto}(\text{REGISTRI}))  
$$  
$$  
\text{VOTI} - \pi_V(\text{VOTI}_{V > V'}(\rho_{V' \leftarrow V}(\text{VOTI})))  
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

|V|
|:--|
|25|

---

### **7. Esempio – Massimo assoluto**

Trovare la **data più recente**:

$$  
\text{DATE} := \rho_{D \leftarrow Data}(\pi_{Data}(\text{REGISTRI}))  
$$  
$$  
\text{DATE} - \pi_D(\text{DATE}_{D < D'}(\rho_{D' \leftarrow D}(\text{DATE})))  
$$

**Risultato**

|D|
|:--|
|21-11-05|

---

### **8. Minimo e massimo relativo**

Sia $r(A,B)$.

- **Minimo relativo (per gruppo):**
    $$  
    r - \pi_{A,B}(r_{A=A' \land B > B'}(\rho_{A',B' \leftarrow A,B}(r)))  
    $$
    
- **Massimo relativo (per gruppo):**
    $$  
    r - \pi_{A,B}(r_{A=A' \land B < B'}(\rho_{A',B' \leftarrow A,B}(r)))  
    $$

---

### **9. Esempio – Minimo relativo**

Trovare **per ogni studente il voto minimo**:

$$  
\text{VOTI} := \rho_{M,V \leftarrow Matr,Voto}(\pi_{Matr,Voto}(\text{REGISTRI}))  
$$  
$$  
\text{VOTI} - \pi_{M,V}(\text{VOTI}_{M=M' \land V > V'}(\rho_{M',V' \leftarrow M,V}(\text{VOTI})))  
$$

**Risultato**

|M|V|
|:--|:--|
|33333|30|
|22222|25|
|11111|28|

---

### **10. Esempio – Massimo relativo**

Trovare **per ogni corso la data più recente**:

$$  
\text{ESAMI} := \rho_{C,D \leftarrow Corso,Data}(\pi_{Corso,Data}(\text{REGISTRI}))  
$$  
$$  
\text{ESAMI} - \pi_{C,D}(\text{ESAMI}_{C=C' \land D < D'}(\rho_{C',D' \leftarrow C,D}(\text{ESAMI})))  
$$

**Risultato**

|C|D|
|:--|:--|
|Sistemi|15-06-05|
|Basi di dati|21-11-05|

---

### **11. Rispondente a minimo / massimo**

Sia $r(A,B)$.

- **Rispondente a minimo:**
    $$  
    \pi_A(r - \pi_{A,B}(r_{B > B'}(\rho_{A',B' \leftarrow A,B}(r))))  
    $$
    
- **Rispondente a massimo:**
    $$  
    \pi_A(r - \pi_{A,B}(r_{B < B'}(\rho_{A',B' \leftarrow A,B}(r))))  
    $$

---

### **12. Esempio – Rispondente a minimo**

Trovare gli **studenti che hanno preso il voto più basso**:

$$  
\text{VOTI} := \rho_{M,V \leftarrow Matr,Voto}(\pi_{Matr,Voto}(\text{REGISTRI}))  
$$  
$$  
\pi_M(\text{VOTI} - \pi_{M,V}(\text{VOTI}_{V > V'}(\rho_{M',V' \leftarrow M,V}(\text{VOTI}))))  
$$

**Risultato**

|M|
|:--|
|22222|

---

### **13. Esempio – Rispondente a massimo**

Trovare il **corso con la data più recente**:

$$  
\text{ESAMI} := \rho_{C,D \leftarrow Corso,Data}(\pi_{Corso,Data}(\text{REGISTRI}))  
$$  
$$  
\pi_C(\text{ESAMI} - \pi_{C,D}(\text{ESAMI}_{D < D'}(\rho_{C',D' \leftarrow C,D}(\text{ESAMI}))))  
$$

**Risultato**

|C|
|:--|
|Basi di dati|

---

### **14. Sintesi**

Abbiamo introdotto gli **idiomi di interrogazione** più complessi dell’algebra relazionale:

- **Esiste / Per ogni**
    
- **Minimo e massimo assoluto**
    
- **Minimo e massimo relativo**
    
- **Rispondente a minimo/massimo**

Questi schemi logici costituiscono la base formale dei **quantificatori** e delle **funzioni di aggregazione** nei linguaggi dichiarativi come **SQL**.


---

![](imgs/Pasted%20image%2020251125043550.png)

![](imgs/Pasted%20image%2020251125043600.png)

