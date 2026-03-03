# **M1 UD1 Lezione 2 - Operazioni insiemistiche con gli eventi**

### **1. Introduzione**

Nella lezione precedente abbiamo definito la probabilità in termini **insiemistici**: ogni evento è un sottoinsieme dello spazio dei campioni.  
Questa rappresentazione ci permette ora di **trasferire le operazioni logiche** sugli insiemi anche agli eventi.  
Le tre operazioni fondamentali sono:

- **Complemento** → “non A”
    
- **Intersezione** → “A e B”
    
- **Unione** → “A o B”

![](imgs/Pasted%20image%2020251212111130.png)

---

### **2. Evento complementare**

Ogni evento $A$ determina automaticamente un **evento complementare**, indicato con:

$$  
A^C \quad \text{o anche} \quad \neg A \quad \text{(si legge: “non A”)}  
$$

![](imgs/Pasted%20image%2020251212111224.png)

L’evento complementare rappresenta tutti gli esiti **in cui A non si verifica**.

#### Esempio

Nel lancio di un dado a sei facce, i due eventi più plausibili che si vogliono analizzare sono, a partire dallo spazio campionario:

- $S = {1, 2, 3, 4, 5, 6}$
    
- $A = {2, 4, 6}$ → “uscita di un numero pari”
    
- $A^C = {1, 3, 5}$ → “uscita di un numero non pari, quindi dispari”

---

### **3. Regola del complemento**

La probabilità dell’evento complementare è data da:

$$  
P(A^C) = 1 - P(A)  
$$

Questa è la **regola del complemento**, che deriva direttamente dal fatto che $A$ e $A^C$ coprono tutto lo spazio dei campioni senza sovrapporsi:

$$  
A \cup A^C = S  
$$

e dunque bisogna togliere dall'INTERO (equivalente a 1 = 100%) la probabilità nota per ottenerne la restante complementare. Rigirando il discorso:

$$  
P(A) + P(A^C) = P(S) = 1  
$$

![](imgs/Pasted%20image%2020251212112342.png)

---

### **4. Esempi sul complemento**

#### **Esempio 1 – Lancio di un dado**

$$  
P(A) = \frac{1}{2} \quad \Rightarrow \quad P(A^C) = 1 - \frac{1}{2} = \frac{1}{2}  
$$

#### **Esempio 2 – Moneta truccata**

Una moneta dà _Testa_ con probabilità $P(T)=\frac{3}{4}$.  
La probabilità di _Croce_ è:  
$$  
P(C) = 1 - \frac{3}{4} = \frac{1}{4}  
$$

#### **Esempio 3 – Affidabilità di un componente**

Se un componente funziona con probabilità $P(A)=0.97$,  
la probabilità che **fallisca** è:  
$$  
P(A^C) = 1 - 0.97 = 0.03  
$$

#### **Esempio 4 – Due monete bilanciate**

$$  
S = {TT, TC, CT, CC}, \quad A = {TT}  
$$  
$$  
P(A) = \frac{1}{4} \quad \Rightarrow \quad P(A^C) = 1 - \frac{1}{4} = \frac{3}{4}  
$$

---

### **5. Evento intersezione**

L’**intersezione** di due eventi $A$ e $B$, indicata con $A \cap B$, che può essere informalmente vista come $A \ e \ B$. Come già è intuibile, rappresenta la situazione in cui **entrambi si verificano** contemporaneamente.  
Corrisponde all’operatore logico **AND**.
Chiaramente per calcolare la probabilità di un tale evento, la formula va riaggiustata conseguentemente. Passiamo da:

$$  
P(E) = \frac{\#(E)}{\#(S)}  
$$
A "specificare" l'evento:
$$  
P(A \cap B) = \frac{\#(A \cap B)}{\#(S)}  
$$

#### **Esempio – Carte da gioco**

Si estrae una carta da un mazzo di 52 carte.  
Definiamo:

- $A$ = “carta di cuori”
    
- $B$ = “figura” (fante, donna, re)

L’evento _“carta di cuori e figura”_ è:

$$  
A \cap B = { \text{fante di cuori}, \text{donna di cuori}, \text{re di cuori} }  
$$

$$  
\#(A \cap B) = 3 \quad \Rightarrow \quad P(A \cap B) = \frac{3}{52}  
$$

---

### **6. Evento unione**

L’**unione** di due eventi $A$ e $B$, indicata con $A \cup B$, rappresenta l’occorrenza di **almeno uno dei due eventi**.  
Corrisponde all’operatore logico **OR**. Del tutto analogamente all'evento intersezione, qui la "E" dell'evento generico va riaggiustata per calcolare la probabilità:

$$  
P(A \cup B) = \frac{\#(A \cup B)}{\#(S)}  
$$

#### **Esempio – Carte da gioco**

Si estrae una carta da un mazzo di 52 carte.

- $A$ = “carta di cuori”
    
- $B$ = “figura”

L’evento _“carta di cuori o figura”_ non è affatto banale da calcolare!
Quando lavoravamo con l'intersezione, bastava contare a mano le occorrenze: l’evento _“carta di cuori e figura”_ portava chiaramente a stabilire che solo 3 carte sono figure di cuori. Ma nel caso dell'unione c'è uno step in più da fare...


L'OR implica che avremo a che fare con:

- carte di cuori
- figure
- e inevitabilmente anche figure di cuori...

Ma allora per il nostro scopo il terzo punto diventa ridondante, perché rappresenta un "doppione"...
Decidiamo dunque di scrivere un'espressione che possa eliminare la ridondanza:
$$  
A \cup B = A + B - (A \cap B)  
$$

In termini di cardinalità:

$$  
\#(A \cup B) = \#(A) + \#(B) - \#(A \cap B)  
$$

Nel nostro esempio:  
$$  
\#(A) = 13, \quad \#(B) = 12, \quad \#(A \cap B) = 3  
$$

$$  
\Rightarrow \#(A \cup B) = 13 + 12 - 3 = 22  
$$

$$  
P(A \cup B) = \frac{22}{52} = \frac{11}{26}  
$$

---

### **7. Metodo di inclusione-esclusione**

La regola appena applicata è il **principio di inclusione-esclusione**, che permette di evitare di contare due volte gli esiti comuni ai due eventi.

$$  
\#(A \cup B) = \#(A) + \#(B) - \#(A \cap B)  
$$

e quindi:

$$  
P(A \cup B) = P(A) + P(B) - P(A \cap B)  
$$

---

### **8. Eventi disgiunti**

Due eventi si dicono **disgiunti** (o **incompatibili**) se **non possono verificarsi contemporaneamente**, cioè se la loro intersezione è vuota:

$$  
A \cap B = \emptyset  
$$

In tal caso:

$$  
P(A \cup B) = P(A) + P(B)  
$$

---

#### **Esempio: eventi disgiunti con un mazzo da 52 carte**

Si estrae **una sola carta**.

Definiamo gli eventi:

- $A$ = “la carta è **di cuori**”
    
- $B$ = “la carta è **di picche**”

Una carta **non può essere contemporaneamente di cuori e di picche**.

Quindi:  
$$  
A \cap B = \emptyset  
$$

Gli eventi sono **disgiunti (incompatibili)**.
Nel mazzo:

- Carte di cuori: 13
    
- Carte di picche: 13

$$  
P(A) = \frac{13}{52} \qquad P(B) = \frac{13}{52}  
$$

L’evento

> “carta di cuori **o** di picche”

non ha sovrapposizioni, quindi **non c’è nulla da sottrarre**:

$$  
P(A \cup B) = P(A) + P(B)  
$$

$$  
P(A \cup B) = \frac{13}{52} + \frac{13}{52} = \frac{26}{52} = \frac{1}{2}  
$$
Non è altro che una versione semplificata in cui si fa - 0.

---

### **9. Riepilogo concettuale**

| Operazione           | Simbolo                | Significato                    | Formula                                  |
| -------------------- | ---------------------- | ------------------------------ | ---------------------------------------- |
| **Complemento**      | $A^C$ o $\neg A$       | “non A”                        | $P(A^C)=1-P(A)$                          |
| **Intersezione**     | $A \cap B$             | “A e B”                        | $P(A \cap B)=\frac{\#(A \cap B)}{\#(S)}$ |
| **Unione**           | $A \cup B$             | “A o B”                        | $P(A \cup B)=P(A)+P(B)-P(A \cap B)$      |
| **Eventi disgiunti** | $A \cap B = \emptyset$ | “A e B non possono coesistere” | $P(A \cup B)=P(A)+P(B) - 0$              |

---

### **10. Conclusione**

Le operazioni insiemistiche costituiscono la grammatica della probabilità.  
Ogni problema più complesso — che riguardi eventi condizionati, sistemi composti o applicazioni ingegneristiche — si basa su queste **tre regole fondamentali**:

- **Complemento:** tutto ciò che non è A
    
- **Intersezione:** tutto ciò che è A e B
    
- **Unione:** tutto ciò che è A o B

Comprenderle a fondo significa poter manipolare e combinare eventi con sicurezza logica e precisione matematica.