# **M1 UD3 Lezione 3 - Legge della Somma per n cammini**

### **1. Introduzione**

In questa lezione estendiamo la **Legge della Somma** a un numero qualunque di cammini alternativi.  
Vedremo come, partendo dal caso semplice di due eventi, si generalizza il concetto a una **partizione dello spazio campionario**, e applicheremo la formula a esempi numerici e concreti.

---

### **2. Richiamo**

Nella versione preliminare avevamo:

$$  
P(B) = P(B \cap A) + P(B \cap \neg A)  
$$

dove $A$ e $\neg A$ formano una **partizione** dell’insieme $B$ e anche dell’intero spazio $S$.  
Ora consideriamo il caso in cui lo spazio sia suddiviso in **più insiemi disgiunti** $A_1, A_2, \dots, A_n$, la cui unione ricopre l’intero spazio.

---

### **3. Partizione dello spazio**

Una **partizione** è una famiglia di insiemi **disgiunti** che ricoprono completamente lo spazio $S$.  

![](imgs/Pasted%20image%2020251212174019.png)

Formalmente:

$$  
S = A_1 \cup A_2 \cup \dots \cup A_n  
$$

con $A_i \cap A_j = \varnothing$ per ogni $i \ne j$.

Ogni evento $B$ può essere visto come la somma (unione) dei suoi “pezzi” contenuti in ciascun sottoinsieme $A_i$:

$$  
B = (A_1 \cap B) \cup (A_2 \cap B) \cup \dots \cup (A_n \cap B)  
$$

---

### **4. Forma generale della Legge della Somma**

Applicando l’assioma di additività della probabilità ai pezzi disgiunti:

$$  
P(B) = P(A_1 \cap B) + P(A_2 \cap B) + \dots + P(A_n \cap B)  
$$

In forma compatta:

$$  
P(B) = \sum_{i=1}^{n} P(A_i \cap B)  
$$

---

### **5. Forma condizionata**

Possiamo ora sostituire ogni termine usando la **legge del prodotto**:

$$  
P(A_i \cap B) = P(A_i) \cdot P(B \mid A_i)  
$$

e otteniamo la forma più generale della legge della somma:

$$  
P(B) = \sum_{i=1}^{n} P(A_i) \cdot P(B \mid A_i)  
$$

Questa è la **forma generale della Legge delle Alternative**, valida per qualsiasi partizione dello spazio campionario.

---

### **6. Rappresentazione ad albero**

La legge può essere visualizzata con un **albero delle possibilità**, in cui ogni ramo rappresenta un evento $A_i$ seguito dai suoi possibili esiti rispetto a $B$:

![](imgs/Pasted%20image%2020251212174058.png)

```
        ┌── B   (P(B|A₁))
A₁ (P(A₁))│
        └── ¬B

        ┌── B   (P(B|A₂))
A₂ (P(A₂))│
        └── ¬B

        ┌── B   (P(B|Aₙ))
Aₙ (P(Aₙ))│
        └── ¬B
```

Ogni cammino completo ($A_i \rightarrow B$) rappresenta una **via alternativa** per cui si può verificare l’evento $B$.  
La somma delle probabilità di tutti questi cammini dà $P(B)$.

---

### **7. Esempio 1 – Tre monete**

Un sacchetto contiene **tre monete diverse**:

- Moneta **A**: esce sempre Testa ($P(T|A)=1$)
    
- Moneta **B**: bilanciata ($P(T|B)=1/2$)
    
- Moneta **C**: esce sempre Croce ($P(T|C)=0$)

Ognuna ha la stessa probabilità di essere estratta:

$$  
P(A)=P(B)=P(C)=\frac{1}{3}  
$$

La probabilità complessiva di ottenere Testa è:

$$  
P(T) = P(A) \cdot P(T|A) + P(B) \cdot P(T|B) + P(C) \cdot P(T|C)  
$$

$$  
P(T) = \frac{1}{3}(1) + \frac{1}{3}\left(\frac{1}{2}\right) + \frac{1}{3}(0) = \frac{1}{2}  
$$

Complessivamente l’esperimento risulta **bilanciato**: una moneta è neutra, e le altre due si compensano.

---

### **8. Esempio 2 – Rappresentazione ad albero**

![](imgs/Pasted%20image%2020251212174123.png)

```
           A (1/3)
           │
           └── T  (1)
           
           B (1/3)
           │
           └── T  (1/2)

           C (1/3)
           │
           └── T  (0)
```

Applicando la legge della somma ai tre rami:

$$  
P(T) = P(A)P(T|A) + P(B)P(T|B) + P(C)P(T|C)  
$$

---

### **9. Esempio 3 – Carte da gioco: Asso e Due di Picche**

In un mazzo ben mescolato, vogliamo la probabilità che **l’Asso di Picche** sia **vicino al Due di Picche**.

Suddividiamo i casi possibili:

- $B$: “Asso in cima”
    
- $C$: “Asso in fondo”
    
- $D$: “Asso in mezzo”

Questi eventi formano una partizione dello spazio, con:

$$  
P(B) = \frac{1}{52}, \quad P(C) = \frac{1}{52}, \quad P(D) = \frac{50}{52}  
$$

---

### **10. Calcolo delle probabilità condizionate**

- Se l’Asso è in **cima** o in **fondo**, solo una posizione può ospitare il Due accanto:  
    $P(E|B) = P(E|C) = \frac{1}{51}$
    
- Se l’Asso è in **mezzo**, ci sono due posizioni possibili:  
    $P(E|D) = \frac{2}{51}$

---

### **11. Applicazione della legge della somma**

![](imgs/Pasted%20image%2020251212174213.png)

$$  
P(E) = P(B)P(E|B) + P(C)P(E|C) + P(D)P(E|D)  
$$

$$  
P(E) = \frac{1}{52}\frac{1}{51} + \frac{1}{52}\frac{1}{51} + \frac{50}{52}\frac{2}{51}  
$$

$$  
P(E) = \frac{1+1+100}{52\cdot 51} = \frac{102}{2652} = \frac{1}{26}  
$$

La probabilità che l’Asso di Picche sia vicino al Due di Picche è **1 su 26**.

---

### **12. Osservazioni**

- La **legge della somma per n cammini** permette di calcolare la probabilità complessiva sommando i contributi di tutte le vie possibili verso l’evento d’interesse.
    
- È applicabile sia ad **esperimenti sequenziali** sia a **situazioni statiche** che possono essere concettualmente scomposte in casi alternativi disgiunti.
    
- È la base concettuale del **Teorema di Bayes**, che utilizzerà questa stessa struttura per aggiornare le probabilità a posteriori.

---

### **13. Sintesi concettuale**

|**Concetto**|**Significato**|
|---|---|
|**Somma delle intersezioni**|Somma delle probabilità dei casi disgiunti $A_i \cap B$|
|**Legge della somma generale**|Somma ponderata delle probabilità condizionate|
|**Esempio con le monete**|Tre cammini alternativi verso lo stesso evento $B$|
|**Esempio con le carte**|Applicazione a casi statici non sequenziali|

---

$$  
P(B) = \sum_i P(A_i \cap B)  
$$

$$  
P(B) = \sum_i P(A_i) \cdot P(B \mid A_i)  
$$

---

### **14. Conclusione**

La **legge della somma per n cammini** rappresenta la forma più generale della regola delle alternative.  
Essa consente di analizzare esperimenti complessi suddividendoli in **sottocasi disgiunti**, semplificando notevolmente il calcolo delle probabilità totali.  
Questa formula costituisce la **base logica del Teorema di Bayes**, che studieremo nella prossima unità.