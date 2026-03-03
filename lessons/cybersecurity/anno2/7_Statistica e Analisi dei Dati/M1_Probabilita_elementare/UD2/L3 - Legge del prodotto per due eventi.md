# **M1 UD2 Lezione 3 - Legge del prodotto per due eventi**

### **1. Introduzione**

Finora abbiamo usato il **principio di moltiplicazione** per contare le combinazioni possibili.  
Ora estendiamo la stessa idea alle **probabilità**, passando dal conteggio delle misure all’uso diretto dei **valori probabilistici**.

Questa lezione introduce la **legge del prodotto**, una regola fondamentale che consente di calcolare la probabilità di due eventi che si verificano insieme, distinguendo tra **eventi indipendenti** e **eventi dipendenti**.

---

### **2. Esperimento: il mazzo di carte**

Consideriamo un mazzo da scala di **54 carte**, composto da:

- 40 carte numeriche
    
- 12 figure
    
- 2 jolly

Quindi:

$$  
\#(\text{numeriche}) = 40, \quad \#(\text{figure}) = 12, \quad \#(\text{jolly}) = 2  
$$  
$$  
N = \#(S) = 54  
$$

Se non siamo interessati al valore specifico della carta ma solo alla sua **categoria**, possiamo rappresentare l’esperimento con **3 rami** (numerica, figura, jolly) di peso diverso (e quindi non 54 rami diversi!):

![](imgs/Pasted%20image%2020251216133931.png)

La misura di ciascun ramo chiaramente è conteggiabile manualmente, ma può rappresentare in generale quello che chiamiamo **peso statistico**.

---

### **3. Calcolo delle probabilità per singola estrazione**

Poiché ogni categoria ha un numero differente di carte, le probabilità sono proporzionali alle loro quantità. Ergo, si fa misura del ramo fratto misura totale:

$$  
P(\text{numerica}) = \frac{40}{54}, \qquad  
P(\text{figura}) = \frac{12}{54}, \qquad  
P(\text{jolly}) = \frac{2}{54}  
$$

Verifica di normalizzazione:

$$  
P(S) = \frac{40+12+2}{54} = 1  
$$

![](imgs/Pasted%20image%2020251216134824.png)

---

Tutto il discorso di dipendenza e indipendenza con l'estrazione della pallina da un'urna opaca chiaramente si può riapplicare anche col mazzo da scala da 54. Dunque possiamo anche eseguire più prove...

### **4. Estrazioni indipendenti (con reimbussolamento)**

Supponiamo di eseguire **due estrazioni successive**, rimettendo ogni carta dopo la prima estrazione.  
Le due prove sono quindi **indipendenti**, poiché il mazzo si ripristina ogni volta.

Vogliamo calcolare la probabilità di pescare **due figure**.

#### **Spazio campionario in termini di pesi**

|Prima estrazione|Seconda estrazione|Pesi combinati|
|---|---|---|
|numerica|numerica|40 × 40 = 1600|
|numerica|figura|40 × 12 = 480|
|numerica|jolly|40 × 2 = 80|
|figura|numerica|12 × 40 = 480|
|figura|figura|12 × 12 = 144|
|figura|jolly|12 × 2 = 24|
|jolly|numerica|2 × 40 = 80|
|jolly|figura|2 × 12 = 24|
|jolly|jolly|2 × 2 = 4|

![](imgs/Pasted%20image%2020251216134853.png)

Totale dei pesi:

$$  
N = 54 \times 54 = 2916  
$$

---

### **5. Calcolo della probabilità di due figure**

L’evento che ci interessa è:

$$  
E = \text{“figura alla prima e figura alla seconda estrazione”}  
$$

Numero di combinazioni favorevoli: $12 \times 12 = 144$
Quindi è subito importante notare come la moltiplicazione sia fondamentale. Tra poco formalizziamo...
$$  
P(E) = \frac{144}{2916} \approx 0.049 = 4,9\%
$$

	Si può dire dunque che, sull'albero corrispondente, il peso di ciascuna sequenza si ottiene moltiplicando i pesi statistici. Da ciò deriva naturalmente il paragrafo che segue:

---

### **6. Prodotto delle probabilità**

Nel caso di eventi indipendenti è più pratico calcolare direttamente la probabilità di ogni ramo:

$$  
P(E) = P(F_1) \times P(F_2)  
$$

dove $F_1$ e $F_2$ sono rispettivamente “figura alla prima” e “figura alla seconda estrazione”.

$$  
P(F_1) = \frac{12}{54}, \quad P(F_2) = \frac{12}{54}  
$$

Quindi:

$$  
P(F_1 \text{ e } F_2) = \frac{12}{54} \times \frac{12}{54} = \frac{144}{2916}  
$$

Visivamente:

![](imgs/Pasted%20image%2020251216134959.png)

---

### **7. Estrazioni dipendenti (senza reimbussolamento)**

Ora togliamo la carta estratta: l’esperimento diventa **dipendente** perché la seconda estrazione avviene su un mazzo modificato.

![](imgs/Pasted%20image%2020251216135035.png)

Dopo la prima figura estratta:

- restano 53 carte,
    
- e solo 11 figure.

---

### **8. Albero delle possibilità (dipendenza tra prove)**

| **Prima** | **Prob.** | **Seconda** | **Prob. cond.** |
| --------- | --------- | ----------- | --------------- |
| N1        | 40/54     | N2          | 39/53           |
|           |           | F2          | 12/53           |
|           |           | J2          | 2/53            |
| F1        | 12/54     | N2          | 40/53           |
|           |           | F2          | 11/53           |
|           |           | J2          | 2/53            |
| J1        | 2/54      | N2          | 40/53           |
|           |           | F2          | 12/53           |
|           |           | J2          | 1/53            |

---

### **9. Calcolo della probabilità dipendente**

Nel caso dipendente, la probabilità di due figure è:

![](imgs/Pasted%20image%2020251216135113.png)

Iniziamo a chiamare l'operatore | come "quando", "dato"...

$$  
P(F_1 \text{ e } F_2) = P(F_1) \times P(F_2|F_1)  
$$

Tradotto in linguaggio naturale: "La probabilità di due figure è il prodotto tra la probabilità di pescare una figura e la probabilità di pescare una seconda figura quando ne è già stata estratta una".

Sostituendo:

$$  
P(F_1 \text{ e } F_2) = \frac{12}{54} \times \frac{11}{53} = \frac{132}{2862} \approx 0.046  = 4,6\%
$$

L’evento “due figure” risulta quindi **leggermente meno probabile** senza reimbussolamento, il che dovrebbe essere del tutto comprensibile!

---

### **10. Significato di $P(B|A)$**

Il simbolo $P(B|A)$ si legge come:

- “probabilità di $B$ dato $A$”
    
- oppure “probabilità di $B$ quando $A$ si è verificato”
    
- oppure “probabilità di $B$ condizionato a $A$”.

Formalmente, indica che **la probabilità di $B$ è calcolata restringendo lo spazio campionario** ai casi in cui $A$ è già avvenuto.

---

### **11. Legge del prodotto per due eventi**

La **legge del prodotto** si enuncia così:

$$  
P(A \text{ e } B) = P(A) \times P(B|A)  
$$
ATTENZIONE: la notazione "e" è usata per comodità, in realtà dovremmo usare:

$$  
P(A \cap B) = P(A) \times P(B|A)  
$$

Ma chiaramente sono del tutto equivalenti, sia chiaro!

Questa formula permette di calcolare la probabilità congiunta di due eventi sapendo:

- la probabilità del primo ($P(A)$),
    
- e la probabilità del secondo condizionata al primo ($P(B|A)$).

---

### **12. Esempi di applicazione**

1. **Due figure**  
    $$  
    P(F_1 \text{ e } F_2) = \frac{12}{54} \times \frac{11}{53}  
    $$
    
2. **Figura seguita da numero**  
    $$  
    P(F_1 \text{ e } N_2) = \frac{12}{54} \times \frac{40}{53}  
    $$
    
3. **Due numeriche**  
    $$  
    P(N_1 \text{ e } N_2) = \frac{40}{54} \times \frac{39}{53}  
    $$
    
4. **Due jolly**  
    $$  
    P(J_1 \text{ e } J_2) = \frac{2}{54} \times \frac{1}{53}  
    $$

---

### **13. Riepilogo concettuale**

| Caso                     | Formula                               | Descrizione                         |
| ------------------------ | ------------------------------------- | ----------------------------------- |
| **Eventi indipendenti**  | $P(A \text{ e } B) = P(A)P(B)$        | L’esito di $A$ non influisce su $B$ |
| **Eventi dipendenti**    | $P(A \text{ e } B) = P(A)P(B \mid A)$ | L'esito di A influisce su B         |
| **Simbolo condizionale** | $P(B \mid A)$                         | Probabilità di B dato A             |

---

### **14. Conclusione**

La **legge del prodotto** rappresenta il ponte tra il **principio di moltiplicazione** e la **probabilità condizionata**.  
Essa permette di trattare con rigore sia gli esperimenti **indipendenti**, sia quelli **dipendenti**, fornendo il fondamento logico per il **Teorema di Bayes** e per la modellazione di sistemi con eventi correlati.