# **M1 UD3 Lezione 1 - Calcolo dell’unione di eventi**

### **1. Introduzione**

In questa lezione affrontiamo il calcolo della probabilità dell’**unione di due o più eventi**, cioè la probabilità che si verifichi **almeno uno** di essi.  
Studieremo tre approcci equivalenti ma con diversa utilità pratica:

1. Il **metodo dell’inclusione–esclusione**,
    
2. Il **metodo del complemento**,
    
3. Il **metodo della deframmentazione**.

Ognuno offre un punto di vista differente sullo stesso problema, e la scelta dipende dalla struttura degli eventi e dal livello di complessità del sistema.

---

### **2. Evento unione**

L’unione di due eventi $A$ e $B$ si indica con:

$$  
A \cup B  
$$

e rappresenta il verificarsi di **almeno uno** dei due eventi (cioè $A$, oppure $B$, oppure entrambi).

In termini di probabilità:

$$  
P(A \cup B) = \frac{\text{misura dell’unione di } A \text{ e } B}{\text{misura dello spazio } S}  
$$
Più sinteticamente:

$$  
P(A \cup B) = \frac{\#(A \cup B )}{\#S}  
$$

---

### **3. Metodo dell’inclusione–esclusione**

Quando due eventi non sono disgiunti (cioè si sovrappongono), la loro probabilità non può essere semplicemente sommata, perché l’intersezione verrebbe contata due volte.  
Per correggere questo, si usa la **formula dell’inclusione–esclusione**:

$$  
P(A \cup B) = P(A) + P(B) - P(A \cap B)  
$$

Questa regola deriva dal **principio di inclusione–esclusione** degli insiemi:

$$  
\#(A \cup B) = \#(A) + \#(B) - \#(A \cap B)  
$$

![](imgs/Pasted%20image%2020251216160750.png)

---

### **4. Estensione a tre insiemi**

Per tre eventi $A$, $B$ e $C$, la formula generale è:

$$  
P(A \cup B \cup C) = P(A) + P(B) + P(C)

- P(A \cap B) - P(A \cap C) - P(B \cap C)
    

- P(A \cap B \cap C)  
    $$


![](imgs/Pasted%20image%2020251216160824.png)

Questa versione tridimensionale corregge la sovrapposizione multipla delle intersezioni, aggiungendo e sottraendo progressivamente le parti in comune.

---

### **5. Metodo del complemento (o della silhouette)**

Un secondo metodo per calcolare la probabilità dell’unione consiste nell’usare il **complemento**.  
Si parte dall’insieme totale $S$ e si sottrae la probabilità dell’evento opposto, cioè quello in cui **nessuno dei due eventi si verifica**:

![](imgs/Pasted%20image%2020251216160915.png)

$$  
(A \cup B)^c = (\neg A) \cap (\neg B)  
$$

Da cui:

$$  
P(A \cup B) = 1 - P((\neg A) \cap (\neg B))  
$$

---

### **6. Vantaggi del metodo del complemento**

Il metodo del complemento è particolarmente utile quando:

- il numero di eventi è elevato;
    
- o la formula di inclusione–esclusione diventerebbe troppo lunga.

Per esempio, per tre eventi è già molto comodo!

$$ 
P(A \cup B \cup C) = 1 - P((\neg A) \cap (\neg B) \cap (\neg C))  
$$

Molto più compatto rispetto alla lunga forma vista prima.

---

### **7. La parola chiave “almeno”**

Nei problemi di probabilità, la parola **“almeno”** è un segnale per usare il **metodo del complemento**.  
Quando ci si chiede, ad esempio, “qual è la probabilità che esca almeno una volta un certo evento?”, conviene calcolare la probabilità dell’evento opposto e poi sottrarla da 1.

#### **Esempio**

Si lancia una moneta tre volte.  
Vogliamo la probabilità che esca **almeno una volta testa**.

L’evento complementare è “non esce mai testa” → “esce sempre croce”.  
C’è un solo caso favorevole su $2^3 = 8$ totali: $(C, C, C)$.

$$ 
P(\text{sempre croce}) = \frac{1}{8}  
$$

Da cui:

$$
P(\text{almeno una testa}) = 1 - \frac{1}{8} = \frac{7}{8}  
$$

---

### **8. Metodo della deframmentazione (o dei cocci)**

Un terzo approccio consiste nel **ricomporre l’unione** a partire dai **frammenti minimi disgiunti** che la compongono.  
Questo metodo si basa sull’idea che qualunque insieme può essere ricostruito come somma (unione) di “pezzi” distinti che non si sovrappongono.

Per due insiemi $A$ e $B$, i frammenti sono:

![](imgs/Pasted%20image%2020251216162652.png)

$$ 
(A \cap B), \quad (A \cap \neg B), \quad (\neg A \cap B), \quad (\neg A \cap \neg B)  
$$

Solo i primi tre appartengono all’unione $A \cup B$, quindi:

$$
P(A \cup B) = P(A \cap \neg B) + P(A \cap B) + P(\neg A \cap B)  
$$

---

### **9. Caratteristiche del metodo della deframmentazione**

- È **generale**: funziona per qualunque numero di eventi.
    
- È **esaustivo**: considera tutti i frammenti dello spazio campione.
    
- È **utile nei sistemi complessi**, dove si vogliono analizzare le probabilità dei diversi scenari elementari.

In pratica, il metodo dei “cocci” permette di sommare le probabilità dei pezzi disgiunti senza rischio di doppio conteggio.

---

### **10. Confronto tra i tre metodi**

| **Metodo**            | **Principio**                              | **Formula tipica**                          | **Quando usarlo**                              |
| --------------------- | ------------------------------------------ | ------------------------------------------- | ---------------------------------------------- |
| Inclusione–esclusione | Somma con correzione delle sovrapposizioni | $P(A)+P(B)-P(A\cap B)$                      | Quando si conoscono tutte le intersezioni      |
| Complemento           | Sottrazione del caso opposto               | $1-P(\neg A\cap\neg B)$                     | Quando compare “almeno” o ci sono molti eventi |
| Deframmentazione      | Somma dei frammenti disgiunti              | $P(A\cap\neg B)+P(A\cap B)+P(\neg A\cap B)$ | Quando si vuole analizzare caso per caso       |

---

### **11. Conclusione**

I tre metodi portano allo stesso risultato, ma ognuno offre un diverso punto di vista logico e pratico.  
Nel calcolo dell’unione di eventi, saper scegliere il metodo più adatto significa **semplificare i passaggi e ridurre gli errori** — un’abilità essenziale nell’analisi probabilistica e nello studio dell’affidabilità dei sistemi.