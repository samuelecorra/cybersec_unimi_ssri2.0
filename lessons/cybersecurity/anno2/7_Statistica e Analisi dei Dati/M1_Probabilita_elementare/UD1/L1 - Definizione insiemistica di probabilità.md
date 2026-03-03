# **M1 UD1 Lezione 1 - Definizione insiemistica di probabilità**

### **1. Introduzione**

La probabilità nasce come strumento per **descrivere e quantificare l’incertezza** nei fenomeni reali.  
Ogni volta che il risultato di un esperimento non può essere previsto con certezza, possiamo usare il linguaggio della **teoria degli insiemi** per rappresentare in modo rigoroso i possibili esiti e calcolare le probabilità associate.

---

### **2. Esperimento di probabilità**

Un **esperimento di probabilità** è un’azione o un processo che può avere diversi esiti, **non prevedibili con certezza**.  
Esempi:

- Lancio di una moneta → possibili esiti: _Testa (T)_ o _Croce (C)_
    
- Lancio di un dado → possibili esiti: _1, 2, 3, 4, 5, 6_
    
- Lancio di due monete → possibili esiti: _TT, TC, CT, CC_

---

### **3. Esito**

Ciascun risultato elementare di un esperimento è detto **esito**.  
Indichiamo con $e_i$ il singolo esito, ad esempio:

- $e_1 = T$, $e_2 = C$ nel caso di una moneta
    
- $e_1 = 1$, …, $e_6 = 6$ per un dado

L’insieme di tutti gli esiti possibili sarà lo **spazio dei campioni**.

---

### **4. Spazio dei campioni**

Lo **spazio dei campioni**, indicato con $S$, è l’insieme di tutti i possibili esiti di un esperimento:

$$  
S = { e_1, e_2, \dots, e_n }  
$$

Esempi:

- Moneta: $S = {T, C}$
    
- Dado: $S = {1, 2, 3, 4, 5, 6}$
    
- Due monete: $S = {TT, TC, CT, CC}$

All’interno di $S$, ci interessano spesso solo **alcuni esiti specifici**.  
Tali sottoinsiemi di $S$ rappresentano gli **eventi**.

---

### **5. Evento**

Un **evento** è un sottoinsieme dello spazio dei campioni che raccoglie gli esiti che ci interessano.  
Se ad esempio vogliamo che, nel lancio di un dado, esca un numero pari:

$$  
E = {2, 4, 6} \subseteq S  
$$

Allora $E$ rappresenta l’evento _“uscita di un numero pari”_.

- Se $E$ contiene **un solo esito**, si parla di **evento elementare**  
    (es. $E = {3}$: uscita del numero 3)
    
- Se $E = \emptyset$, l’evento è **impossibile**
    
- Se $E = S$, l’evento è **certo**

---

### **6. Misura di un insieme**

A ogni insieme possiamo associare una **misura**, indicata con $\#(A)$, che esprime il **numero di elementi** dell’insieme $A$.  
Per esempio, nel lancio di un dado, sempre in riferimento al "lanciare un numero pari":

$$  
\#(S) = 6, \quad \#(E) = 3  
$$

Questa misura ci servirà per calcolare la probabilità di un evento.

---

### **7. Definizione insiemistica di probabilità**

La **probabilità** di un evento $E$ è definita come il **rapporto** tra la misura dell’evento e la misura dell’intero spazio campionario:

$$  
P(E) = \frac{\#(E)}{\#(S)}  
$$

dove:

- $\#(E)$ = numero di esiti favorevoli
    
- $\#(S)$ = numero di esiti possibili totali

Quindi, a parole:

$$  
P(Evento) = \frac{Esiti \ Favorevoli}{Esiti \ Totali}  
$$

#### Esempio

Nel lancio di un dado, la probabilità di ottenere un numero pari è:

$$  
P(\text{pari}) = \frac{3}{6} = \frac{1}{2} = 0.5 = 50\% 
$$

---

### **8. Esempi pratici**

#### **Esempio 1 – Moneta bilanciata**

$$  
P(\text{Testa}) = \frac{1}{2}  
$$

#### **Esempio 2 – Dado a quattro facce**

$$  
P(1) = \frac{1}{4}  
$$

#### **Esempio 3 – Urna con 3 palline nere e 4 bianche**

$$  
P(\text{nera}) = \frac{3}{7}  
$$

#### **Esempio 4 – Carte da gioco**

$$  
P(\text{asso di cuori}) = \frac{1}{52}  
$$  
$$  
P(\text{asso}) = \frac{4}{52} = \frac{1}{13}  
$$  
$$  
P(\text{cuori in mazzo da 40}) = \frac{10}{40} = \frac{1}{4}  
$$  
$$  
P(\text{cuori in mazzo da 54}) = \frac{13}{54}  
$$

#### **Esempio 5 – Estrazione al Lotto**

In un’estrazione vengono sorteggiati **5 numeri distinti** da un insieme di **90 numeri** (da 1 a 90).
Se tu **giochi un solo numero**, lo indovini **se quel numero compare tra i 5 estratti**.

Per questo la probabilità è:

  
$$
P(\text{indovinare un numero}) = \frac{\text{numeri favorevoli}}{\text{numeri possibili}} = \frac{5}{90} = \frac{1}{18}
$$

Cioè: **in media, 1 volta ogni 18 estrazioni**.

---

### **9. Proprietà fondamentali**

La probabilità di un evento $E$ è sempre una frazione propria, ergo deve essere un **numero compreso tra 0 e 1**:

$$  
0 \leq P(E) \leq 1  
$$

- $P(E) = 0$ → evento impossibile
    
- $P(E) = 1$ → evento certo

È spesso utile esprimere la probabilità in **percentuale**:
Da decimale a percentuale, come dice il nome, basta fare x100.
Viceversa da percentuale a decimale si fa /100.

- $P = 0.75$ → 75%
    
- $P = 0.03$ → 3%

---

### **10. Riepilogo concettuale**

| Concetto                | Descrizione                 | Esempio           |
| ----------------------- | --------------------------- | ----------------- |
| **Esperimento**         | Processo non deterministico | Lancio di un dado |
| **Esito**               | Risultato elementare        | 4                 |
| **Spazio dei campioni** | Insieme di tutti gli esiti  | ${1,2,3,4,5,6}$   |
| **Evento**              | Sottoinsieme di S           | ${2,4,6}$         |
| **Misura**              | Numero di elementi          | $\#(E)=3$         |
| **Probabilità**         | Rapporto tra misure         | $P(E)=3/6=1/2$    |

---

### **11. Conclusione**

La definizione insiemistica di probabilità costituisce la base formale dell’intera teoria.  
Essa consente di passare da una descrizione qualitativa (“può uscire un numero pari”) a una **quantificazione rigorosa** dell’incertezza.  
Nei moduli successivi, questa impostazione sarà estesa a casi più complessi, con eventi condizionati, leggi del prodotto e applicazioni reali alla sicurezza e all’affidabilità dei sistemi.