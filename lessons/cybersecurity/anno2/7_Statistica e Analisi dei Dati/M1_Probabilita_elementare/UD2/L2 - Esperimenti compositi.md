# **M1 UD2 Lezione 2 - Esperimenti compositi**

### **1. Introduzione**

In molti casi un esperimento non è costituito da una sola prova, ma da **più fasi successive**.  
Questi si chiamano **esperimenti compositi**, e per analizzarli serve combinare il principio di moltiplicazione con strumenti grafici come **tabelle** e **diagrammi ad albero**.

Tali rappresentazioni ci permettono di studiare sia gli **eventi indipendenti** (come due lanci di una moneta o di un dado), sia quelli **dipendenti** (come estrazioni da un’urna con o senza reimbussolamento).

---

### **2. Esperimenti composti da più prove**

Un esperimento composito può comprendere prove anche di tipo diverso.  
Esempio:

1. Lanciare una moneta
    
2. Estrarre una pallina da un’urna

Gli **esiti possibili** saranno coppie ordinate del tipo $(\text{moneta}, \text{pallina})$.  
Ad esempio, considerando T = testa, C = croce, N = pallina nera e B = pallina bianca:

$$  
S = { (T, N), (T, B), (C, N), (C, B) }  
$$

In generale:

- ogni prova produce un risultato parziale (singola lettera)
    
- l’insieme ordinato di tutti i risultati costituisce l’**esito composito** (l'nupla, in questo caso la coppia)
    
- e l’insieme di tutti gli esiti compositi forma lo **spazio dei campioni** $S$ (tutte le varie coppie/nuple).

---

### **3. Esempio – Due lanci di moneta**

Nel doppio lancio di una moneta, gli esiti possibili che formano lo spazio campionario globale sono le **coppie ordinate**:

$$  
S = { (T, T), (T, C), (C, T), (C, C) }  
$$

Anche se i due lanci sono esperimenti distinti, lo **spazio campionario** è lo stesso di quello ottenuto lanciando una moneta due volte di seguito.  
Nel calcolo delle probabilità, ciò permette di trattare i due casi nello stesso modo. LA COSA NON DOVREBBE SORPRENDERE!

---

### **4. Misura dello spazio e degli eventi**

Come in precedenza, la **misura** si ottiene contando gli elementi.  
Negli esperimenti composti, però, gli elementi sono **n-uple ordinate**:

- **Coppie** → esperimenti in 2 fasi
    
- **Terne** → esperimenti in 3 fasi
    
- **n-tuple** → esperimenti in $n$ fasi

Esempio: nel lancio di due monete  
$$  
\#(S) = 2 \times 2 = 4 \ esiti \ possibili 
$$  
ottenuto con il **principio di moltiplicazione**.

---

### **5. Eventi da esperimenti compositi**

Gli eventi si definiscono come sottoinsiemi di $S$.  
Possono essere:

- **Elementari:** formati da un solo esito, es.  
    $E = {(T, C)}$
    
- **Non elementari:** formati da più esiti, es.  
    $E = {(T, T), (T, C), (C, T)}$ = _“almeno una Testa”_
    si noti che in questo caso l'evento è "arbitrario", cioè è tranquillamente definibile da un umano che vuole studiare un particolare caso. Quindi prestare sempre attenzione alla conversione bilaterale tra linguaggio umano e linguaggio probabilistico.

---

### **6. Calcolo della probabilità**

Come sempre:

$$  
P(E) = \frac{\#(E)}{\#(S)}  
$$

La differenza è che, negli esperimenti compositi, $\#(S)$ e $\#(E)$ vengono calcolati tramite il **principio di moltiplicazione** e il **conteggio delle n-uple**.

- Il principio di moltiplicazione serve per la misura dello **spazio campionario** $S$.
    
- Tabelle e alberi servono per contare gli esiti dell’**evento** $E$.

---

### **7. Esempio 1 – Due monete**

#### **Spazio campionario (tabella)**

| |Testa|Croce|
|---|---|---|
|**Testa**|(T,T)|(T,C)|
|**Croce**|(C,T)|(C,C)|

Ogni cella rappresenta una coppia ordinata.

#### **Probabilità dell’evento (T,T)**

$$  
\#(S) = 2 \times 2 = 4, \qquad \#(E) = 1  
$$

$$  
P((T,T)) = \frac{1}{4}  
$$

Poiché la moneta è bilanciata, ogni esito ha probabilità uguale di $\frac{1}{4}$.

---

### **8. Esempio 2 – Due facce uguali**

Evento $E = {(T,T), (C,C)}$  
Usando la tabella sopra:

$$  
P(E) = \frac{2}{4} = \frac{1}{2}  
$$

---

### **9. Esempio 3 – Due dadi a quattro facce**

I dadi a quattro facce sono questi:

![](imgs/Pasted%20image%2020251216124306.png)

![](imgs/Pasted%20image%2020251216133156.png)

Numero di esiti totali, lanciandone due o uno per due volte (già dimostrata l'equivalenza dei due esperimenti!):

$$  
\#(S) = 4 \times 4 = 16  
$$

Evento “due facce uguali”: $E = {(1,1), (2,2), (3,3), (4,4)}$  
Contiamo manualmente il numero di casi favorevoli = 4

$$  
P(E) = \frac{4}{16} = \frac{1}{4}  
$$

![](imgs/Pasted%20image%2020251216133208.png)

---

### **10. Alberi delle possibilità**

Nel **diagramma ad albero**, ogni livello rappresenta una fase dell’esperimento.  
Ad esempio, nel doppio lancio di una moneta:

![](imgs/Pasted%20image%2020251216133351.png)

Ogni percorso completo corrisponde a un esito.

---

### **11. Eventi indipendenti e dipendenti**

Due prove si dicono **indipendenti** se l’esito della prima **non influenza** l’esito della seconda.  
Esempi:

- Doppio lancio di una moneta
    
- Doppio lancio di un dado


![](imgs/Pasted%20image%2020251216133431.png)

Qui la probabilità resta la stessa in ogni prova.

Quando invece un esito modifica le condizioni della prova successiva, si parla di **eventi dipendenti**.  
Un classico esempio è l’estrazione di palline da un’urna.

---

### **12. Estrazione con e senza reimbussolamento**

#### **Con reimbussolamento (eventi indipendenti)**

Urna: contiene tre palline denominate ${R, B, Y}$  

![](imgs/Pasted%20image%2020251216133517.png)

Dopo ogni estrazione, la pallina viene rimessa e l’urna rimescolata.  
Le probabilità restano invariate:

$$  
\#(S) = 3 \times 3 = 9, \qquad P(RB) = \frac{1}{9}  
$$
L'albero dunque è:

![](imgs/Pasted%20image%2020251216133555.png)

#### **Senza reimbussolamento (eventi dipendenti)**

Se la pallina non viene rimessa:

- la seconda estrazione avviene su un insieme più piccolo;
    
- le probabilità cambiano.

$$  
\#(S) = 3 \times 2 = 6, \qquad P(RB) = \frac{1}{6}  
$$

L'albero dunque varia:

![](imgs/Pasted%20image%2020251216133637.png)

---

### **13. Alberi e tabelle a confronto**

| **Con reimbussolamento**           | **Senza reimbussolamento**              |
| ---------------------------------- | --------------------------------------- |
| $\#(S)=3\times3=9$                 | $\#(S)=3\times2=6$                      |
| Ogni ramo è identico al precedente | I rami si riducono dopo ogni estrazione |
| Esperimento indipendente           | Esperimento dipendente                  |

![](imgs/Pasted%20image%2020251216133732.png)

![](imgs/Pasted%20image%2020251216133703.png)

L’albero è preferibile alle tabelle nei casi complessi, perché consente di visualizzare facilmente la **dipendenza tra prove**.

---

### **14. Esperimenti con e senza memoria**

- Un esperimento con **prove indipendenti** è detto **senza memoria**: il passato non influenza il futuro.
    
- Un esperimento con **prove dipendenti** è detto **con memoria**: le estrazioni precedenti cambiano le probabilità successive.

Esempi:

- Lancio di monete o dadi → **senza memoria**
    
- Estrazione da urna senza reimbussolamento → **con memoria**

---

### **15. Riepilogo concettuale**

|Concetto|Descrizione|Esempio|
|---|---|---|
|**Esperimento composito**|Esperimento formato da più prove|Due lanci di moneta|
|**Spazio campionario**|Insieme di tutte le n-uple ordinate|${(T,T), (T,C), (C,T), (C,C)}$|
|**Evento indipendente**|Un esito non influenza il successivo|Monete, dadi|
|**Evento dipendente**|Un esito influenza il successivo|Urna senza reimbussolamento|
|**Esperimento senza memoria**|Prove indipendenti|Dadi, monete|
|**Esperimento con memoria**|Prove dipendenti|Urne|

---

### **16. Conclusione**

Gli **esperimenti compositi** generalizzano la nozione di evento singolo e introducono la dipendenza tra prove.  
Comprendere quando un sistema è **indipendente** o **dipendente** è fondamentale per applicare correttamente la **legge del prodotto** e, più avanti, il **Teorema di Bayes**, che si basano proprio su queste relazioni.