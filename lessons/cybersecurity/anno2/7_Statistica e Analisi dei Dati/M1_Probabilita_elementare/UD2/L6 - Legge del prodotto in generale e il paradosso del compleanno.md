# **M1 UD2 Lezione 6 - Legge del prodotto in generale e il paradosso del compleanno**

### **1. Introduzione**

Nelle lezioni precedenti abbiamo visto la **legge del prodotto** per due eventi, e poi per eventi indipendenti.  
Ora la estendiamo a **tre o più eventi** — cioè a un numero qualunque di prove — e mostriamo come questa regola si applichi in un caso reale tanto celebre quanto controintuitivo: **il paradosso del compleanno**.

---

### **2. Legge del prodotto per tre eventi**

Per tre eventi $A$, $B$ e $C$, la probabilità che si verifichino **tutti e tre** è data da:

$$  
P(A \cap B \cap C) = P(A) \cdot P(B \mid A) \cdot P(C \mid A \cap B)  
$$

L’idea è che, per ottenere $A \cap B \cap C$, prima devono accadere $A$ e $B$, e poi, dato che questi si sono verificati, anche $C$.  
Si tratta di una **catena logica di eventi** in cui ogni fattore successivo è condizionato dai precedenti. Ciò che deve entrarci in testa è che "aggiungiamo in coda" gli eventi successivi. Quindi nel caso di tre eventi, mettiamo in coda la probabilità di C dato il fatto che siano successi i precedenti, ovvero A e B intersecati.

---

### **3. Esempio – Tre estrazioni senza reimbussolamento**

Supponiamo un’urna con 5 palline bianche e 5 nere.  
Vogliamo calcolare la probabilità di estrarre **tre nere di seguito**, senza reimbussolamento.

Definiamo gli eventi:

- $A$: la prima pallina è nera
    
- $B$: la seconda è nera
    
- $C$: la terza è nera

Le probabilità sono facili da conteggiare senza particolari elucubrazioni:

$$  
P(A) = \frac{5}{10}, \quad P(B \mid A) = \frac{4}{9}, \quad P(C \mid A \cap B) = \frac{3}{8}  
$$

Applicando la legge del prodotto:

$$  
P(A \cap B \cap C) = \frac{5}{10} \cdot \frac{4}{9} \cdot \frac{3}{8} = \frac{1}{12}  
$$

Quindi la probabilità di estrarre tre palline nere consecutive è $\tfrac{1}{12}$.

---

### **4. Legge del prodotto per $n$ eventi**

Generalizzando, per $n$ eventi $A_1, A_2, A_3, \dots, A_n$ la formula diventa:

$$  
P(A_1 \cap A_2 \cap \dots \cap A_n)  
= P(A_1) \cdot P(A_2 \mid A_1) \cdot P(A_3 \mid A_1 \cap A_2) \cdot \dots \cdot P(A_n \mid A_1 \cap A_2 \cap \dots \cap A_{n-1})  
$$

Ogni termine successivo è una **probabilità condizionata**, calcolata tenendo conto di tutti gli eventi precedenti.

---

### **5. Interpretazione logica**

La legge del prodotto riflette una struttura logica “a catena”:  
ogni evento accade **dopo** che si sono verificati quelli precedenti.

  
$$\text{Per esempio:}\quad  
A_1 \Rightarrow A_2 \Rightarrow A_3 \Rightarrow \dots \Rightarrow A_n  
$$

In un **diagramma ad albero**, la sequenza principale di eventi corrisponde al ramo orizzontale, mentre i rami trasversali rappresentano gli eventi alternativi che non si verificano.

![](imgs/Pasted%20image%2020251216153341.png)

---

### **6. Applicazione: il paradosso del compleanno**

#### **Problema**

Qual è la probabilità che, in un gruppo di $n$ persone, **almeno due** condividano lo stesso compleanno?

Assumiamo:

- nessun anno bisestile, quindi 365 giorni totali;
    
- ogni compleanno è equiprobabile;
    
- i compleanni delle persone sono indipendenti.

---

### **7. Strategia**

È più semplice calcolare prima la probabilità **complementare**:  
che **tutti i compleanni siano diversi**.

Definiamo:

- $A_1$: la persona 1 ha un compleanno “libero”;
    
- $A_2$: la persona 2 ha un compleanno diverso da quello della prima;
    
- $A_3$: la persona 3 ha un compleanno diverso dai primi due;
    
- e così via.

---

### **8. Calcolo della probabilità che tutti i compleanni siano distinti**

Per la prima persona:

$$  
P(A_1) = \frac{365}{365}  
$$

Per la seconda:

$$  
P(A_2 \mid A_1) = \frac{364}{365}  
$$

Per la terza:

$$  
P(A_3 \mid A_1 \cap A_2) = \frac{363}{365}  
$$

In generale, per la i-esima persona, vale la seguente relazione:

$$  
P(A_i \mid A_1 \cap A_2 \cap \dots \cap A_{i-1}) = \frac{365 - (i - 1)}{365}  
$$

Applicando la legge del prodotto per $n$ eventi, dobbiamo concatenare il prodotto di tutte le probabilità delle singole persone.

Consideriamo la **n-esima persona**.

Prima di lei ci sono:

- n - 1 persone
    
- quindi n - 1 **compleanni già occupati**
    
- quindi n - 1 **giorni vietati**

I giorni ancora liberi sono: 365 - (n - 1)

che, svolgendo la parentesi, diventa: 365 - n + 1

Da cui possiamo ricavare la relazione:

$$  
P(A_1 \cap A_2 \cap \dots \cap A_n)  
= \frac{365}{365} \cdot \frac{364}{365} \cdot \frac{363}{365} \cdot \dots \cdot \frac{365 - n + 1}{365}  
$$

---

### **9. Probabilità di almeno due compleanni coincidenti**

La probabilità richiesta è il **complemento** della precedente:

$$  
P(\text{almeno due compleanni uguali}) = 1 - P(A_1 \cap A_2 \cap \dots \cap A_n)  
$$

---

### **10. Risultati numerici (calcoli esplicitati)**

Vediamo qualche caso reale:
#### **Caso 1 — 23 persone**

$$
P(A) = \frac{365}{365} \cdot \frac{364}{365} \cdot \frac{363}{365} \cdot \dots \cdot \frac{343}{365}
$$
  
(dato che 365 - 23 + 1 = 343).

Svolgendo il prodotto numericamente (con calcolatrice):

$$
P(A) \approx 0.4927
$$
Calcoliamo il complementare:

$$
P(B) = 1 - P(A) = 1 - 0.4927 = 0.5073
$$
Ed ecco:

$$
P(\text{almeno due compleanni uguali}) \approx 50.7\%
$$

➡️ **Con sole 23 persone, la probabilità supera il 50%**.

---

#### **Caso 2 — 57 persone**

$$
P(A) = \frac{365}{365} \cdot \frac{364}{365} \cdot \dots \cdot \frac{309}{365}
$$
  
(dato che 365 - 57 + 1 = 309).

Valore numerico del prodotto:

$$
P(A) \approx 0.0106
$$
Da cui:

$$
P(B) = 1 - 0.0106 = 0.9894
$$
$$
P(\text{almeno due compleanni uguali}) \approx 98.9\%
$$
  
➡️ **Praticamente certo**.

---

#### **Caso 3 — 68 persone**

$$
P(A) = \frac{365}{365} \cdot \frac{364}{365} \cdot \dots \cdot \frac{298}{365}
$$
  
(dato che 365 - 68 + 1 = 298).

Valore numerico:

$$
P(A) \approx 0.00032
$$
Da cui:  

$$
P(B) = 1 - 0.00032 = 0.99968
$$
$$
P(\text{almeno due compleanni uguali}) \approx 99.97\%
$$

➡️ **Statisticamente inevitabile**.

---

### **11. Perché sembra un paradosso**

L’errore comune è pensare che la probabilità sia legata al proprio compleanno, ma il calcolo considera **tutte le coppie possibili** di persone nel gruppo.  
Il numero di coppie cresce quadraticamente con $n$:

$$  
\text{Numero di coppie} = \frac{n(n-1)}{2}  
$$

Anche con un numero relativamente piccolo di persone, le combinazioni possibili sono moltissime, e la probabilità di coincidenze cresce rapidamente.

![](imgs/Pasted%20image%2020251216154911.png)

---

### **12. Nota applicativa – Generazione casuale di codici**

Questo effetto non è solo teorico.  
In informatica, si ritrova nella **collisione di hash** o nella **generazione casuale di password**:  
se il numero di elementi generati diventa grande rispetto al numero totale di combinazioni possibili, le collisioni (duplicati) diventano molto probabili.

---

### **13. Esercizio – Il paradosso del compleanno in gennaio**

Hai **Max + altre 7 persone** ⇒ in totale **$n = 8$** persone in un'ascensore...

Sappiamo che i compleanni possono cadere solo in **gennaio**, quindi ci sono **$31$** date possibili (1–31).

Assumiamo che ogni giorno di gennaio sia **equiprobabile** e indipendente.

---

#### **1. Probabilità che tutti abbiano compleanni diversi**

Definiamo l’evento:

- $A$ = “tutti gli 8 compleanni sono distinti”.
    
Ragioniamo persona per persona (come nel paradosso dei compleanni):

- **1ª persona**: può avere qualunque giorno ⇒ $\dfrac{31}{31}$
    
- **2ª persona**: deve evitare 1 giorno già usato ⇒ $\dfrac{30}{31}$
    
- **3ª persona**: deve evitare 2 giorni già usati ⇒ $\dfrac{29}{31}$
    
- …
    
- **8ª persona**: deve evitare 7 giorni già usati ⇒ $\dfrac{24}{31}$

Quindi, per la legge del prodotto:

$$

P(A)=\frac{31}{31}\cdot\frac{30}{31}\cdot\frac{29}{31}\cdot\frac{28}{31}\cdot\frac{27}{31}\cdot\frac{26}{31}\cdot\frac{25}{31}\cdot\frac{24}{31}

$$

Ora rendiamo esplicito il “calcolo implicito”:

$$

P(A)=\frac{31\cdot 30\cdot 29\cdot 28\cdot 27\cdot 26\cdot 25\cdot 24}{31^8}

$$

Valore numerico (prodotto calcolato):

$$

P(A)\approx 0.3729355545

$$

Quindi:

$$

P(\text{tutti diversi})\approx 37.29\%

$$

---

#### **2. Probabilità che almeno due condividano la stessa data**

Definiamo:

- $B$ = “almeno due persone hanno lo stesso compleanno”.

È il complementare di $A$, quindi:

$$

P(B)=1-P(A)

$$

Sostituendo il valore numerico:

$$

P(B)=1-0.3729355545\approx 0.6270644455

$$

Quindi:

$$

P(\text{almeno due uguali})\approx 62.71\%

$$

---

#### **Risultato finale**

- **Tutti diversi:** $\approx 37.29\%$
    
- **Almeno due uguali:** $\approx 62.71\%$

---

### **14. Sintesi concettuale**

|**Concetto**|**Descrizione**|
|---|---|
|**Legge del prodotto per tre eventi**|Probabilità congiunta di tre eventi dipendenti|
|**Legge del prodotto per n eventi**|Estensione a una catena di eventi successivi|
|**Complemento del paradosso del compleanno**|Probabilità che almeno due eventi coincidano|

---

### **15. Conclusione**

La **legge del prodotto** è lo strumento generale per calcolare la probabilità congiunta di più eventi, anche condizionati tra loro.  
Il **paradosso del compleanno** mostra come, anche in fenomeni semplici, l’intuizione possa fallire di fronte alla crescita combinatoria.  
Questa lezione chiude l’Unità 2, ponendo le basi per la successiva: **il Teorema di Bayes e l’aggiornamento delle probabilità**.