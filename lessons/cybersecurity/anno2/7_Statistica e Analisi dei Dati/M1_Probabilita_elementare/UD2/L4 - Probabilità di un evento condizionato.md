# **M1 UD2 Lezione 4 - Probabilità di un evento condizionato**

### **1. Introduzione**

Finora abbiamo studiato esperimenti indipendenti e dipendenti.  
Ora approfondiamo **come cambia la probabilità di un evento quando sappiamo che un altro si è verificato**.  
Questo porta alla **definizione formale di probabilità condizionata**, e ai concetti di **correlazione positiva**, **correlazione negativa** e **indipendenza** tra eventi.

---

### **2. Relazioni tra eventi**

Se due eventi possono influenzarsi, si dice che sono **dipendenti**.  
L’effetto può essere di tre tipi:

- **Positivo**: la probabilità del secondo evento aumenta.
    
- **Negativo**: la probabilità del secondo evento diminuisce.
    
- **Nullo**: la probabilità del secondo evento resta invariata (eventi indipendenti).

In generale, dopo che si è verificato un evento $B$, la probabilità di un altro evento $C$ può:

1. Aumentare → correlazione positiva
    
2. Diminuire → correlazione negativa
    
3. Restare invariata → indipendenza

---

### **3. Notazione formale**

Per indicare la probabilità di $C$ **sapendo che $B$ si è verificato**, scriviamo:

$$  
P(C \mid B)  
$$

che si legge “**probabilità di C dato B**”.

- Se $P(C \mid B) > P(C)$ → $B$ condiziona **positivamente** $C$.
    
- Se $P(C \mid B) < P(C)$ → $B$ condiziona **negativamente** $C$.
    
- Se $P(C \mid B) = P(C)$ → $B$ **non influisce** su $C$ (indipendenza).

---

### **4. Esempio 1 – Lancio di un dado**

Sia $C$: _“esce un numero pari”_.  
Prima del lancio:

$$  
P(C) = \frac{3}{6} = 0.5  
$$

Ora ci viene detto che si è verificato l’evento  
$B$: _“è uscito un numero strettamente minore di 6”_.

Il nuovo spazio dei campioni si riduce: ora è ${1, 2, 3, 4, 5}$, che contiene solo due numeri pari (2 e 4).

$$  
P(C \mid B) = \frac{2}{5} = 0.4  
$$

La probabilità è **diminuita** (dal 50% al 40%):  
gli eventi $B$ e $C$ sono **correlati negativamente**.

---

### **5. Esempio 2 – Correlazione positiva**

Sempre con lo stesso dado, sia  
$B$: _“è uscito un numero strettamente maggiore di 1”_.

Il nuovo spazio è ${2,3,4,5,6}$, con tre numeri pari.

$$  
P(C \mid B) = \frac{3}{5} = 0.6 > 0.5  
$$

La probabilità è **aumentata**: gli eventi sono **correlati positivamente**.

---

### **6. Esempio 3 – Indipendenza**

Se $B$: _“è uscito un numero strettamente minore di 5”_,  
lo spazio campionario diventa: ${1,2,3,4}$, dove i numeri pari e dispari restano nella stessa proporzione.

$$  
P(C \mid B) = \frac{2}{4} = 0.5 = P(C) = P(B)
$$

La probabilità non cambia: $B$ e $C$ sono **indipendenti**.

---

### **7. Interpretazione insiemistica**

Quando si verifica un evento $B$, lo **spazio dei campioni** si restringe a $B$.  
La parte di $C$ che **non appartiene** anche a $B$ diventa impossibile.

Pertanto, per calcolare la nuova probabilità di $C$ dobbiamo considerare solo la parte comune $(C \cap B)$.

![](imgs/Pasted%20image%2020251216144855.png)

---

### **8. Formula generale della probabilità condizionata**

Dall'analisi insiemistica viene logico scrivere:

$$  
P(C \mid B) = \frac{\#(C \cap B)}{\#(B)}  
$$

- Il numeratore $\#(C \cap B)$ rappresenta la **parte comune** dei due eventi.
    
- Il denominatore $\#(B)$ serve a **rinormalizzare** lo spazio campionario: una volta saputo che $B$ si è verificato, consideriamo solo i casi all’interno di $B$.

Adesso ci farebbe comodo "omogeneizzare" questa relazione...
Dividiamo sia num che den per la misura di S, ovvero $\#S$, così da poter applicare proprio il concetto di probabilità!

$$  
P(C \mid B) = \frac{\#(C \cap B) / \#S}{\#(B) / \#S}  
$$
Otteniamo dunque:

$$  
P(C \mid B) = \frac{P(C \cap B)}{P(B)}  
$$
Questa è la definizione generale di probabilità condizionata, sempre valida $\forall C,B$

#### **Perché questa forma è superiore (e fondamentale)?**

Perché ora:

- **non compare più lo spazio campionario**
    
- **non servono conteggi**
    
- **non serve equiprobabilità**
    
- vale per:
    
    - eventi continui
        
    - probabilità assegnate
        
    - modelli astratti
        
    - variabili aleatorie
        
    - statistica, inferenza, AI, cybersecurity

👉 È la **definizione ufficiale** di probabilità condizionata.

---

### **9. Significato della “rinormalizzazione”**

Dividendo per $P(B)$, ridimensioniamo tutte le probabilità interne a $B$ in modo che la loro somma torni a 1.

Esempio:

$$  
\frac{P(C \cap B)}{P(B)} + \frac{P(\neg C \cap B)}{P(B)} = 1  
$$

Abbiamo semplicemente “riscala­to” lo spazio campionario, mantenendo la coerenza delle probabilità all’interno del nuovo sottoinsieme $B$.

---

### **10. Confronto tra $P(C)$ e $P(C \mid B)$**

A seconda della **proporzione di $C$ dentro $B$**, rispetto alla proporzione di $C$ dentro $S$, abbiamo tre casi:

|Relazione|Condizione|Interpretazione|
|---|---|---|
|**Correlazione positiva**|$P(C \mid B) > P(C)$|B aumenta la probabilità di C|
|**Indipendenza**|$P(C \mid B) = P(C)$|B non influisce su C|
|**Correlazione negativa**|$P(C \mid B) < P(C)$|B riduce la probabilità di C|

---

### **11. Esempio – Carte da gioco**

Nel mazzo da 54 carte:

- 40 numeriche, 12 figure, 2 jolly.

Sappiamo che dunque la probabilità di ottenere una figura (che sarà l'evento C condizionato al B) è:

$$  
P(C) = \frac{12}{54} \approx 0,22 
$$


Inizialmente però ci viene fornita un'informazione extra: sappiamo che avviene l'evento $B$: “è uscita una carta di cuori”. Questo restringe lo spazio campionario da 54 carte a sole 13 carte.

Poi, condizionatamente a ciò, si vuole capire come varia la probabilità di $C$.

$$  
P(C \mid B) = \frac{P(C \cap B)}{P(B)}  
$$
Il numeratore è facile da conteggiare: le figure di cuori sono 3, su uno spazio campionario di 54 carte:

$$\frac{3}{54}$$

Il denominatore idem: ci sono 13 carte di cuori su 54 di spazio campionario:

$$\frac{13}{54}$$

Facciamo la divisione invertendo il secondo termine e semplificando i 54 e otteniamo

$$  
P(C \mid B) = \frac{\frac{3}{54}}{\frac{13}{54}} = \frac{3}{54} \cdot \frac{54}{13} = \frac{3}{13} \approx 0,23> P(C)  
$$

Il verificarsi di $B$ **aumenta** la probabilità di $C$, anche se di pochissimo: correlazione positiva.

Chiaramente potevamo snellire i calcoli se avessimo usato la formula che non prevede /#S, ma è del tutto valido anche così!

---

### **12. Esempio – Mazzo da 52 carte**

Se il mazzo non contiene jolly (52 carte),  
la proporzione di figure resta uguale anche limitandosi ai cuori:

$$  
P(C \mid B) = \frac{3}{13} = \frac{12}{52} = P(C)  
$$

Qui $B$ non influisce su $C$: gli eventi sono **indipendenti**.

---

### **13. Esempio – Due monete**

Sia $S = {TT, TC, CT, CC}$.

Evento $B$: “almeno una Testa”  
→ $B = {TT, TC, CT}$

Evento $A$: “due Teste”  
→ $A = {TT}$

$$  
P(A) = \frac{1}{4}, \qquad P(A \mid B) = \frac{1}{3}  
$$

Sapere che almeno una moneta ha dato Testa **aumenta** la probabilità che entrambe lo abbiano fatto.

---

### **14. Sintesi finale**

- Se $P(C \mid B) > P(C)$ → correlazione **positiva**
    
- Se $P(C \mid B) = P(C)$ → **indipendenza**
    
- Se $P(C \mid B) < P(C)$ → correlazione **negativa**

Formula chiave:

$$  
P(C \mid B) = \frac{P(C \cap B)}{P(B)}  
$$

---

### **15. Conclusione**

La **probabilità condizionata** rappresenta il modo in cui aggiorniamo le nostre conoscenze quando apprendiamo nuove informazioni.  
È la base per tutti i ragionamenti **bayesiani** e per l’analisi della **dipendenza tra eventi**, ponendo il fondamento matematico dell’intero pensiero probabilistico moderno.