# **M1 UD5 Lezione 1 - Introduzione al Teorema di Bayes**

### **1. Introduzione**

In questa lezione introduciamo il **Teorema di Bayes**, un principio fondamentale della probabilità che permette di **invertire il verso del ragionamento**:  
dal **calcolo deduttivo** (“dalla causa prevedo l’effetto”) al **calcolo inferenziale** (“dall’effetto risalgo alla causa”).

Scoperto dal matematico inglese **Thomas Bayes (1701-1761)**, il teorema è alla base di molti campi moderni — dalla **diagnostica medica** all’**intelligenza artificiale**, fino ai **sistemi di sicurezza e analisi dati**.

---

### **2. Probabilità diretta: causa → effetto**

Fino ad ora abbiamo trattato situazioni di **probabilità diretta**, in cui la **causa è nota** e si vuole calcolare la probabilità dei possibili **effetti**.

Esempio:

- Lancio di due monete indipendenti con $P(T) = p$.
    
- La probabilità di due teste è $p^2$, quella di due croci è $q^2 = (1-p)^2$.

In questi casi si parte da un **modello generativo** che descrive il fenomeno e si calcolano gli esiti **in avanti**.  
Questo approccio è **deduttivo**.

---

### **3. Probabilità inversa: effetto → causa**

Ora ci spostiamo nella direzione opposta: vogliamo rispondere a domande **inferenziali**, in cui l’**effetto è noto**, ma la **causa** non lo è.  
Ci chiediamo: _dato un certo risultato osservato, quale causa è più probabile che lo abbia prodotto?_

Esempio:

- Estraggo una moneta da un sacchetto contenente monete diverse.
    
- Osservo che, lanciandola, esce **Testa**.
    
- Qual è la probabilità che la moneta estratta fosse quella con maggiore propensione a dare Testa?

Questa è una situazione di **probabilità inversa**:  
dato l’effetto osservato, vogliamo risalire alla causa più plausibile.

---

### **4. Cause certe ed effetti certi**

Il confronto tra i due approcci può essere sintetizzato così:

|Tipo di probabilità|Causa|Effetto|Direzione del ragionamento|
|---|---|---|---|
|**Diretta**|nota|incerta|Causa → Effetto|
|**Inversa**|incerta|nota|Effetto → Causa|

Nella **probabilità diretta**, la causa è fissata e calcoliamo la probabilità dei vari effetti.  
Nella **probabilità inversa**, invece, l’effetto è fissato e stimiamo la probabilità delle diverse cause che potrebbero averlo generato.

---

### **5. Rilevanza del metodo bayesiano**

Il ragionamento bayesiano è **centrale** in molte applicazioni pratiche.  
Ad esempio, in medicina:

- **Causa** → malattia
    
- **Effetto** → sintomo

Sappiamo che certe malattie producono certi sintomi, ma nella realtà clinica osserviamo l’opposto: un sintomo, e da quello dobbiamo inferire quale malattia (causa) lo ha prodotto.

Il processo diagnostico è dunque **un’inferenza bayesiana**.

---

### **6. Esempio: le tre monete**

Consideriamo un esperimento in due fasi:

1. Pesco **una moneta** a caso da un sacchetto che ne contiene tre (A, B, C).
    
2. Lancio la moneta scelta e osservo il risultato.

Le probabilità di estrazione sono uguali:  
$$  
P(A) = P(B) = P(C) = \tfrac{1}{3}  
$$

Le probabilità di ottenere **Testa** con ciascuna moneta sono invece diverse:  
$$  
P(T|A), \quad P(T|B), \quad P(T|C)  
$$

---

### **7. Albero delle possibilità – probabilità diretta**

In **probabilità diretta**, prima del lancio, vogliamo sapere la probabilità totale di ottenere Testa:

$$  
P(T) = P(A) \cdot P(T|A) + P(B) \cdot P(T|B) + P(C) \cdot P(T|C)  
$$

Questo risultato deriva dalla **legge della somma** applicata ai rami che portano a Testa.

Ogni ramo rappresenta un possibile percorso:  
“estraggo una certa moneta → ottengo un certo risultato”.

---

### **8. Albero delle possibilità – probabilità inversa**

In **probabilità inversa**, invece, sappiamo già che **è uscita Testa**.  
Ci chiediamo ora: _da quale moneta è più probabile che provenga?_

Per rispondere, consideriamo solo i **rami che portano all’effetto osservato (T)**.  
I rami che portano a Croce vengono eliminati, e dobbiamo **rinormalizzare** le probabilità affinché la somma torni a 1.

---

### **9. Rinormalizzazione**

La probabilità complessiva di Testa, già calcolata, vale:

$$  
P(T) = P(A)P(T|A) + P(B)P(T|B) + P(C)P(T|C)  
$$

La **probabilità a posteriori** di ciascuna moneta (cioè dopo aver osservato Testa) è ottenuta rinormalizzando i pesi dei cammini:

$$  
P(A|T) = \frac{P(A)P(T|A)}{P(T)}, \quad  
P(B|T) = \frac{P(B)P(T|B)}{P(T)}, \quad  
P(C|T) = \frac{P(C)P(T|C)}{P(T)}  
$$

Queste rappresentano le **probabilità aggiornate a posteriori**.

---

### **10. Probabilità a priori e a posteriori**

- **Probabilità a priori:** prima dell’esperimento, le monete sono equiprobabili → $P(A)=P(B)=P(C)=1/3$
    
- **Probabilità a posteriori:** dopo aver osservato Testa, aggiorniamo la nostra conoscenza → $P(A|T)$, $P(B|T)$, $P(C|T)$

Le probabilità a posteriori riflettono il **nuovo stato di informazione** dopo l’osservazione dell’effetto.

---

### **11. Esempio numerico**

Supponiamo:

- $P(A)=P(B)=P(C)=1/3$
    
- $P(T|A)=1$ (moneta A dà sempre Testa)
    
- $P(T|B)=1/2$ (moneta B è bilanciata)
    
- $P(T|C)=0$ (moneta C dà sempre Croce)

Calcoliamo la probabilità totale di Testa (ho già raccolto a fattore comune )

$$  
P(T) = \tfrac{1}{3}(1 + \tfrac{1}{2} + 0) = \tfrac{1}{2}  
$$

Ora possiamo calcolare le probabilità a posteriori:

$$  
P(A|T) = \frac{(1/3)(1)}{1/2} = \tfrac{2}{3}  
$$

$$  
P(B|T) = \frac{(1/3)(1/2)}{1/2} = \tfrac{1}{3}  
$$

$$  
P(C|T) = \frac{(1/3)(0)}{1/2} = 0  
$$

---

### **12. Interpretazione**

Dopo aver osservato che è uscita Testa:

- la moneta C (solo Croce) è **impossibile**,
    
- la moneta A (solo Testa) è **la più probabile** ($\tfrac{2}{3}$),
    
- la moneta B rimane **plausibile ma meno probabile** ($\tfrac{1}{3}$).

Abbiamo così _aggiornato_ la nostra conoscenza iniziale in base all’evidenza osservata.

---

### **13. Formula generale del Teorema di Bayes**

Per un insieme di cause alternative $C_1, C_2, ..., C_n$ che possono produrre un effetto $E$:

$$  
P(C_k|E) = \frac{P(C_k)P(E|C_k)}{\sum_i P(C_i)P(E|C_i)}  
$$

**In linguaggio umano:**  

> La probabilità che sia stata la k-esima causa $C_k$ a produrre l’effetto $E$, cioè la **probabilità posteriore** della causa $C_k$ dopo aver osservato l’effetto $E$, si ottiene applicando la **legge del prodotto** al numeratore, moltiplicando la **probabilità a priori** della causa $P(C_k)$ per la **probabilità condizionata dell’effetto dato che la causa si è verificata** $P(E \mid C_k)$.

> Il denominatore rappresenta invece la **probabilità totale dell’effetto $E$**, calcolata come somma dei contributi di **tutte le cause possibili**, ciascuna pesata per la propria probabilità a priori. Esso svolge il ruolo di **fattore di normalizzazione**, garantendo che le probabilità posteriori delle cause sommino a 1.

---

### **14. Sintesi concettuale**

| **Concetto**        | **Formula o significato**                              |
| ------------------- | ------------------------------------------------------ |
| Probabilità diretta | $P(E \mid C)$ — probabilità dell’effetto nota la causa |
| Probabilità inversa | $P(C \mid E)$ — probabilità della causa noto l’effetto |
| Legge di Bayes      | $P(C \mid E) = \dfrac{P(C) P(E \mid C)}{P(E)}$         |
| Rinormalizzazione   | $P(E) = \sum_i P(C_i) P(E \mid C_i)$                   |
| Aggiornamento       | da $P(C)$ (a priori) a $P(C \mid E)$ (a posteriori)    |

---

### **15. Conclusione**

Il **Teorema di Bayes** fornisce il ponte logico fra **conoscenza teorica e osservazione empirica**.  
Consente di **aggiornare le probabilità** man mano che si acquisiscono nuove informazioni,  
trasformando la probabilità da semplice previsione a **strumento di inferenza razionale**.