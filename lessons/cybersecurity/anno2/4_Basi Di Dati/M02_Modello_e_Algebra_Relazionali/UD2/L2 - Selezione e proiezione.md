# **M2 UD2 Lezione 2 - Selezione e proiezione**

### **1. Introduzione**

Nell’algebra relazionale, gli **operatori unari** agiscono su **una sola relazione** per produrne un’altra.  
I principali operatori unari sono:

- **Selezione ($\sigma$)** → estrae le **righe** che soddisfano una certa condizione.
    
- **Proiezione ($\pi$)** → estrae le **colonne** di interesse.

---

### **2. Operatore di selezione ($\sigma$)**

La **selezione** permette di estrarre tutte le tuple di una relazione che soddisfano una **condizione logica**.

$$  
\sigma_{\text{condizione}}(r)  
$$

Dove:

- $r$ è la relazione di partenza
    
- la **condizione** è un’espressione booleana composta da operatori logici $(\land, \lor, \lnot)$
    
- le **condizioni atomiche** sono del tipo $A \ \theta \ B$ oppure $A \ \theta \ c$, con:
    
    - $\theta \in {=, \neq, <, >, \le, \ge}$
        
    - $A$ e $B$ attributi di $r$ su cui il confronto abbia senso
        
    - $c$ una costante compatibile con il dominio di $A$

---

### **3. Risultato della selezione**

Il risultato della selezione è una **nuova relazione senza nome**, che contiene **solo le tuple** di $r$ che soddisfano la condizione.

- **Schema:** schema($r$)
    
- **Grado:** grado($r$)
    
- **Cardinalità:** ≤ card($r$)

La selezione non altera né gli attributi né il grado della relazione, ma può ridurre il numero di tuple.

---

### **4. Esempi di selezione**

**Relazione di partenza – CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

**Esempio 1**

$$  
\sigma_{\text{Aula} = 'Beta'}(\text{CORSI})  
$$

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Beta|Neri|Basi di dati|

**Esempio 2**

$$  
\sigma_{\text{Aula} = 'Gamma' \ \lor \ \text{Corso} = 'Sistemi'}(\text{CORSI})  
$$

|Aula|Docente|Corso|
|:--|:--|:--|
|Gamma|Bianchi|Linguaggi|
|Beta|Verdi|Sistemi|

---

### **5. Operatore di proiezione ($\pi$)**

La **proiezione** permette di selezionare solo **alcuni attributi (colonne)** di una relazione.

$$  
\pi_Y(r)  
$$

Dove:

- $r$ è la relazione di partenza
    
- $Y$ è un **sottoinsieme di attributi** di $r$

Il risultato è una nuova relazione contenente solo le **colonne** specificate in $Y$.

---

### **6. Proprietà della proiezione**

- **Schema:** $Y$
    
- **Grado:** $|Y|$
    
- **Cardinalità:** ≤ card($r$)

> La cardinalità resta invariata solo se $Y$ è una **superchiave**; altrimenti, le **tuple duplicate vengono eliminate**.

---

### **7. Esempi di proiezione**

**Relazione di partenza – CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

**Esempio 1**

$$  
\pi_{\text{Aula}}(\text{CORSI})  
$$

|Aula|
|:--|
|Gamma|
|Beta|
|Alfa|

**Esempio 2**

$$  
\pi_{\text{Corso, Docente}}(\text{CORSI})  
$$

|Docente|Corso|
|:--|:--|
|Verdi|Sistemi|
|Bianchi|Linguaggi|
|Neri|Basi di dati|
|Rossi|Architetture|

---

### **8. Combinazione di selezione e proiezione**

Gli operatori **possono essere composti** per filtrare e poi ridurre i dati.  
Esempio:

$$  
\pi_{\text{Docente}}(\sigma_{\text{Aula} = 'Beta'}(\text{CORSI}))  
$$

**Passaggi logici:**

1. Seleziona le tuple con `Aula = 'Beta'`
    
2. Proietta l’attributo `Docente`

**Risultato**

|Docente|
|:--|
|Verdi|
|Neri|

---

### **9. Combinazione con assegnamento**

È possibile salvare il risultato di un’operazione di selezione in una **nuova relazione**, per poi applicare una proiezione.

**Esempio**

$$  
\text{BETA} := \sigma_{\text{Aula} = 'Beta'}(\text{CORSI})  
$$

$$  
\pi_{\text{Docente}}(\text{BETA})  
$$

|Docente|
|:--|
|Verdi|
|Neri|

---

### **10. Sintesi finale**

In questa lezione abbiamo visto:

- L’**operatore di selezione ($\sigma$)** per filtrare righe in base a condizioni booleane.
    
- L’**operatore di proiezione ($\pi$)** per scegliere colonne e rimuovere duplicati.
    
- Come **comporre** le operazioni di selezione e proiezione.
    
- L’uso dell’**assegnamento** per rendere le espressioni più leggibili e modulari.

Questi operatori costituiscono la base della **manipolazione procedurale dei dati**, fondamentali per comprendere l’esecuzione e l’ottimizzazione delle query SQL.


---

ESERCIZI

![](imgs/Pasted%20image%2020251125043154.png)

![](imgs/Pasted%20image%2020251125043205.png)

![](imgs/Pasted%20image%2020251125043215.png)

![](imgs/Pasted%20image%2020251125043225.png)

