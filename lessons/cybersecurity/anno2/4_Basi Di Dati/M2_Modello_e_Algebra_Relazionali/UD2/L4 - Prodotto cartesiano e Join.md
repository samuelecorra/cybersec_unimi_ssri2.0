# **M2 UD2 Lezione 4 - Prodotto cartesiano e Join**

### **1. Introduzione**

Con questa lezione si conclude lo studio degli **operatori binari** dell’algebra relazionale.  
Dopo unione, differenza e intersezione, analizzeremo ora:

- il **prodotto cartesiano ($\times$)**
    
- i **diversi tipi di join**: _theta-join_, _equi-join_ e _join naturale_

Queste operazioni consentono di **combinare i dati** di relazioni diverse, creando nuove informazioni a partire da quelle esistenti.

---

### **2. Prodotto cartesiano ($\times$)**

Siano $r_1$ e $r_2$ due relazioni definite su **insiemi di attributi disgiunti**.  
Il prodotto cartesiano è l’insieme di tutte le **tuple concatenate** ottenute da una tupla di $r_1$ e una di $r_2$.

$$  
r_1 \times r_2 = { t_1 \cup t_2 \mid t_1 \in r_1, \ t_2 \in r_2 }  
$$

#### **Caratteristiche**

- **Schema:** schema($r_1$) concatenato a schema($r_2$)
    
- **Grado:** grado($r_1$) + grado($r_2$)
    
- **Cardinalità:**  
    $$  
    \text{card}(r_1 \times r_2) = \text{card}(r_1) \times \text{card}(r_2)  
    $$

Il risultato è una **relazione senza nome** che contiene **tutte le combinazioni possibili** di tuple.

---

### **3. Esempio 1 – Prodotto cartesiano**

**CORSI**

|Titolo|Codice|
|:--|:--|
|Sistemi|CS102|
|Basi di dati|CS101|

**STUDENTI**

|Nome|Cognome|
|:--|:--|
|Antonio|Rosa|
|Marta|Rossi|
|Carla|Bianchi|

**STUDENTI × CORSI = CORSI × STUDENTI**

|Nome|Cognome|Titolo|Codice|
|:--|:--|:--|:--|
|Antonio|Rosa|Sistemi|CS102|
|Antonio|Rosa|Basi di dati|CS101|
|Marta|Rossi|Sistemi|CS102|
|Marta|Rossi|Basi di dati|CS101|
|Carla|Bianchi|Sistemi|CS102|
|Carla|Bianchi|Basi di dati|CS101|

---

### **4. Esempio 2 – Prodotto cartesiano**

**CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

**AULE**

|Piano|Nome|
|:--|:--|
|Primo|Beta|
|Terra|Alfa|

**CORSI × AULE = AULE × CORSI**

|Aula|Docente|Corso|Piano|Nome|
|:--|:--|:--|:--|:--|
|Beta|Neri|Basi di dati|Primo|Beta|
|Beta|Neri|Basi di dati|Terra|Alfa|
|Alfa|Rossi|Architetture|Primo|Beta|
|Alfa|Rossi|Architetture|Terra|Alfa|

---

### **5. Join (θ-join)**

Il **join (theta)** è una combinazione tra relazioni basata su una **condizione logica**.

$$  
r_1 \ \theta\text{-join} \ r_2 \quad \equiv \quad \sigma_{\text{condizione}}(r_1 \times r_2)  
$$

Dove:

- $r_1$ e $r_2$ sono relazioni su insiemi di attributi disgiunti
    
- la **condizione** è un’espressione booleana composta da operatori $\land, \lor, \lnot$
    
- le **condizioni atomiche** sono del tipo $A \ \theta \ B$, con:
    
    - $A$ attributo di $r_1$
        
    - $B$ attributo di $r_2$
        
    - $\theta \in {=, \neq, <, >, \le, \ge}$

#### **Caratteristiche**

- **Schema:** schema($r_1$) concatenato a schema($r_2$)
    
- **Grado:** grado($r_1$) + grado($r_2$)
    
- **Cardinalità:**  
    $$  
    0 \le \text{card}(r_1 \ \theta\text{-join} \ r_2) \le \text{card}(r_1) \times \text{card}(r_2)  
    $$

---

### **6. Esempio – Theta-join**

**CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

**AULE**

|Piano|Nome|
|:--|:--|
|Primo|Beta|
|Terra|Alfa|

$$  
\text{CORSI} \ \text{Aula=Nome} \ \text{AULE}  
$$

|Aula|Docente|Corso|Piano|Nome|
|:--|:--|:--|:--|:--|
|Beta|Neri|Basi di dati|Primo|Beta|
|Alfa|Rossi|Architetture|Terra|Alfa|

> Il theta-join restituisce le tuple del prodotto cartesiano che **soddisfano la condizione specificata**.

---

### **7. Equi-join**

L’**equi-join** è un caso particolare di _theta-join_ in cui la condizione è una **uguaglianza** (o una congiunzione di uguaglianze).

Esempio di sintassi:

$$  
r_1 \ \text{A=B} \ r_2  
$$

Il risultato è analogo a un _theta-join_, ma la condizione prevede solo confronti di uguaglianza.

---

### **8. Join naturale**

Il **join naturale** combina due relazioni che **hanno almeno un attributo in comune**.  
Restituisce tutte le tuple che hanno **valori uguali** sugli attributi condivisi, **eliminando le colonne duplicate**.

#### **Schema e proprietà**

Siano:

- $X_1$: attributi di $r_1$
    
- $X_2$: attributi di $r_2$
    
- $X_{12} = X_1 \cap X_2$

Allora:

- **Schema:** attributi di $r_1$ concatenati a quelli di $r_2$, con gli attributi comuni presenti una sola volta
    
- **Grado:** grado($r_1$) + grado($r_2$) − |$X_{12}$|
    
- **Cardinalità:**  
    $$  
    0 \le \text{card}(r_1 \bowtie r_2) \le \text{card}(r_1) \times \text{card}(r_2)  
    $$

---

### **9. Esempio 1 – Join naturale**

**CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

**AULE**

|Piano|Aula|
|:--|:--|
|Primo|Beta|
|Terra|Alfa|

$$  
\text{CORSI} \ \bowtie \ \text{AULE}  
$$

|Aula|Piano|Docente|Corso|
|:--|:--|:--|:--|
|Beta|Primo|Neri|Basi di dati|
|Alfa|Terra|Rossi|Architetture|

---

### **10. Esempio 2 – Join naturale su tre relazioni**

**STUDENTI**

|Cognome|Matr|
|:--|:--|
|Verdi|33333|
|Rossi|22222|
|Bianchi|11111|

**ESAMI**

|Codice|Matr|
|:--|:--|
|CS101|33333|
|CS102|22222|
|CS101|11111|

**CORSI**

|Titolo|Codice|
|:--|:--|
|Sistemi|CS102|
|Basi di dati|CS101|

$$  
\text{STUDENTI} \ \bowtie \ \text{ESAMI} \ \bowtie \ \text{CORSI}  
$$

|Cognome|Matr|Codice|Titolo|
|:--|:--|:--|:--|
|Bianchi|11111|CS101|Basi di dati|
|Rossi|22222|CS102|Sistemi|
|Verdi|33333|CS101|Basi di dati|

---

### **11. Definizione formale del join naturale**

Il join naturale può essere espresso in termini di **ridenominazione**, **equi-join** e **proiezione**.

Siano:

- $r_1(A,B,C)$
    
- $r_2(B,C,D)$

$$  
r_1 \bowtie r_2 = \pi_{A,B,C,D} \big( \sigma_{B=B' \land C=C'} (\rho_{B'C' \leftarrow BC}(r_2)) \big)  
$$

---

### **12. Casi estremi del join naturale**

- Se $X_1 = X_2$ e quindi $X_{12} = X_1 = X_2$, allora:  
    $$  
    r_1 \bowtie r_2 = r_1 \cap r_2  
    $$
    
- Se $X_{12} = \varnothing$, allora:  
    $$  
    r_1 \bowtie r_2 = r_1 \times r_2  
    $$

---

### **13. Altri tipi di join (accenni)**

Oltre ai join analizzati, esistono varianti:

- **Semi-join** → proietta il join su una sola delle relazioni coinvolte.
    
- **Outer join** → include anche le tuple **senza corrispondenza** nell’altra relazione, completandole con **valori nulli**.

Queste varianti sono **tipiche del linguaggio SQL** e saranno trattate in quel contesto.

---

### **14. Sintesi finale**

In questa lezione abbiamo studiato:

- Il **prodotto cartesiano ($\times$)**, base di tutte le combinazioni tra relazioni.
    
- I **join** come casi particolari del prodotto cartesiano con condizioni:
    
    - _theta-join_ → condizione generica
        
    - _equi-join_ → condizione di uguaglianza
        
    - _join naturale_ → uguaglianza implicita sugli attributi comuni
    
- I **casi limite** in cui il join si riduce a intersezione o a prodotto cartesiano.

Il join rappresenta uno degli strumenti **più potenti e frequenti** nella manipolazione dei dati relazionali, non solo nell’algebra ma anche nei linguaggi pratici come SQL.


---

![](imgs/Pasted%20image%2020251125043353.png)

![](imgs/Pasted%20image%2020251125043410.png)

![](imgs/Pasted%20image%2020251125043419.png)

![](imgs/Pasted%20image%2020251125043427.png)

![](imgs/Pasted%20image%2020251125043438.png)

