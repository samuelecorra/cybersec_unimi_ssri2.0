# **M2 UD2 Lezione 1 - Introduzione all’algebra relazionale**

### **1. Linguaggi per basi di dati**

I **linguaggi per basi di dati** possono essere classificati secondo due principali criteri:

- **Formali** o **programmativi**
    
- **Procedurali** o **dichiarativi**

Nel contesto delle basi di dati relazionali:

- **SQL** è un linguaggio **programmativo e dichiarativo**
    
- **L’algebra relazionale** è invece **formale e procedurale**

---

### **2. Origini e scopo dell’algebra relazionale**

L’**algebra relazionale** fu definita da **E. F. Codd (1970)** come linguaggio formale per la **manipolazione dei dati relazionali**.  
Serve per:

- **Formulare query** in modo preciso e non ambiguo
    
- Descrivere in forma **procedurale** come ottenere un risultato a partire da relazioni di base

Cinque operatori fondamentali sono sufficienti per garantire **l’intero potere espressivo** del linguaggio.

---

### **3. Operatori di base**

Gli **operatori di base** dell’algebra relazionale sono:

1. **Selezione ($\sigma$)**  
    → Estrae un **sottoinsieme di tuple (righe)** di una relazione.
    
2. **Proiezione ($\pi$)**  
    → Estrae un **sottoinsieme di attributi (colonne)** di una relazione.
    
3. **Unione ($\cup$)**  
    → Restituisce le tuple che appartengono **ad almeno una** delle due relazioni.
    
4. **Differenza ($-$)**  
    → Restituisce le tuple che appartengono a **una relazione ma non all’altra**.
    
5. **Prodotto cartesiano ($\times$)**  
    → Combina **tutte le tuple** di due relazioni tra loro.

---

### **4. Operatori derivati e aggiunti**

Oltre agli operatori di base, esistono:

#### **Operatori derivati**

- **Intersezione ($\cap$)** → insieme delle tuple comuni a due relazioni.
    
- **Join** → selezione eseguita su un prodotto cartesiano.
    
    - **Theta-join** → selezione con una condizione generica ($\theta$)
        
    - **Equi-join** → join in cui la condizione è un’uguaglianza
        
    - **Join naturale** → equi-join seguito dall’eliminazione degli attributi duplicati

#### **Operatori aggiunti**

- **Ridenominazione ($\rho$)** → rinomina gli attributi di una relazione.
    
- **Assegnamento** → consente di dare un nome a una relazione risultante da un’espressione.

---

### **5. Ordine di presentazione delle operazioni**

Nel corso si analizzeranno gli operatori in quest’ordine:

1. **Operatori aggiunti**
    
    - Ridenominazione
        
    - Assegnamento
    
2. **Operatori unari**
    
    - Selezione
        
    - Proiezione
    
3. **Operatori binari**
    
    - Unione
        
    - Differenza
        
    - Intersezione
        
    - Prodotto cartesiano
        
    - Join (naturale, theta-join, equi-join)

---

### **6. Ridenominazione ($\rho$)**

La **ridenominazione** serve per cambiare il **nome di uno o più attributi** di una relazione.

$$  
\rho_{B_1,B_2,\ldots,B_n \leftarrow A_1,A_2,\ldots,A_n}(r)  
$$

Dove:

- $r$ è una relazione
    
- $A_1,A_2,\ldots,A_n$ sono attributi esistenti di $r$
    
- $B_1,B_2,\ldots,B_n$ sono i nuovi nomi da assegnare

---

#### **Risultato della ridenominazione**

La relazione risultante è **identica a $r$**, salvo per i **nomi degli attributi modificati**.

- **Schema:** $(\text{schema}(r) - {A_1, \ldots, A_n}) \cup {B_1, \ldots, B_n}$
    
- **Grado:** $\text{grado}(r)$
    
- **Cardinalità:** $\text{card}(r)$

---

#### **Esempio**

**Relazione originale – CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

Applichiamo:
 $$  
\rho_{\text{Insegnam, Prof} \leftarrow \text{Corso, Docente}}(\text{CORSI})  
$$

**Risultato**

|Aula|Prof|Insegnam|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

---

### **7. Assegnamento**

L’**assegnamento** serve a **dare un nome** al risultato di un’espressione algebrica.  
La sintassi è:

$$  
\text{risultato} := \text{espressione algebrica}  
$$

dove:

- **risultato** → nome della nuova relazione
    
- **espressione algebrica** → operazione o combinazione di operazioni

---

#### **Esempio**

**Relazione originale – CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

Assegniamo il risultato della ridenominazione:

$$  
\text{INSEGNAMENTI} := \rho_{\text{Insegnam, Prof} \leftarrow \text{Corso, Docente}}(\text{CORSI})  
$$

**Relazione risultante – INSEGNAMENTI**

|Aula|Prof|Insegnam|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

---

### **8. Sintesi finale**

In questa lezione abbiamo introdotto:

- Le **caratteristiche dell’algebra relazionale** (formale e procedurale)
    
- I **cinque operatori di base**: selezione, proiezione, unione, differenza, prodotto cartesiano
    
- Gli **operatori derivati** e **aggiunti**
    
- Le **operazioni di ridenominazione ($\rho$)** e **assegnamento**

Questi operatori costituiscono il **vocabolario fondamentale** dell’algebra relazionale, base teorica su cui si fondano le **query SQL** e i **meccanismi di ottimizzazione** dei moderni DBMS.


---

DOMANDE ED ESERCIZI

![](imgs/Pasted%20image%2020251125043058.png)

![](imgs/Pasted%20image%2020251125043115.png)

![](imgs/Pasted%20image%2020251125043125.png)

