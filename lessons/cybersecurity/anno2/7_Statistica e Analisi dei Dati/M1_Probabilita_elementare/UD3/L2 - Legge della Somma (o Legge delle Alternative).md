# **M1 UD3 Lezione 2 - Legge della Somma (o Legge delle Alternative)**

### **1. Introduzione**

In questa lezione formalizziamo la **Legge della Somma**, già intuita nel metodo della deframmentazione.  
Essa stabilisce come ricostruire la probabilità di un evento quando esso può verificarsi attraverso **più percorsi alternativi**.  
È detta anche **legge delle alternative**, **legge delle probabilità totali** o **regola del condizionamento medio**, ed è una delle fondamenta del ragionamento bayesiano.

---

### **2. Formulazione preliminare**

Talvolta non conosciamo direttamente la probabilità complessiva di un evento $B$, ma conosciamo:

- la probabilità di $B$ **insieme** a un evento $A$, cioè $P(B \cap A)$,
    
- e la probabilità di $B$ **insieme** al complementare di $A$, cioè $P(B \cap \neg A)$.


![](imgs/Pasted%20image%2020251212172024.png)

Poiché i due insiemi $B \cap A$ e $B \cap \neg A$ sono **disgiunti**, possiamo sommarne le probabilità:

$$  
P(B) = P(B \cap A) + P(B \cap \neg A)  
$$

Questa è la **prima formulazione** della legge della somma.

---

### **3. Interpretazione insiemistica**

Gli insiemi $A$ e $\neg A$ costituiscono una **partizione** dello spazio campionario, cioè due sottoinsiemi disgiunti la cui unione ricopre tutto l’insieme $S$.  
Quando scriviamo $P(B) = P(B \cap A) + P(B \cap \neg A)$, stiamo **ricostruendo l’insieme $B$** partendo dai due “pezzi” in cui è suddiviso da $A$ e dal suo complementare.

---

### **4. Legge della somma – seconda formulazione**

Possiamo ora sostituire i due termini usando la **legge del prodotto**:

$$
P(B \cap A) = P(A) \cdot P(B \mid A)
$$  
$$
P(B \cap \neg A) = P(\neg A) \cdot P(B \mid \neg A)  
$$

Sostituendo nella formula iniziale otteniamo la **seconda formulazione**, nota come **legge delle alternative**:

$$  
P(B) = P(A) \cdot P(B \mid A) + P(\neg A) \cdot P(B \mid \neg A)  
$$

---

### **5. Significato**

La legge della somma dice che la probabilità complessiva di $B$ è la **somma ponderata** delle probabilità condizionate di $B$, pesate in base alla probabilità di ciascun “ramo alternativo” ($A$ e $\neg A$).  
In altre parole, per trovare $P(B)$, raccogliamo le probabilità di **tutti i cammini** che conducono a $B$.

![](imgs/Pasted%20image%2020251212173746.png)

---

### **6. Albero delle possibilità**

Nel diagramma ad albero, la legge si interpreta graficamente così:

```
         ┌── B   (P(B|A))
A  (P(A))│
         └── ¬B

         ┌── B   (P(B|¬A))
¬A (P(¬A))│
          └── ¬B
```

$$  
P(B) = P(A) \cdot P(B \mid A) + P(\neg A) \cdot P(B \mid \neg A)  
$$

---

### **7. Altri nomi e interpretazioni**

Questa legge è nota con molti nomi equivalenti:

- **Legge delle probabilità totali**
    
- **Legge delle alternative**
    
- **Regola del condizionamento medio**
    
- **Regola della disintegrazione**
    
- **Regola della deframmentazione**

Il nome **“regola di Bayes”** è talvolta usato in letteratura, ma si preferisce evitarlo per non confonderla con il **Teorema di Bayes**, che è una conseguenza di questa legge.

---

### **8. Esempio 1 – Monete e urne**

Abbiamo una **moneta sbilanciata** (probabilità di testa = 4/10).  
Se esce **Testa**, scegliamo l’**urna 1** (con 3/5 di palline nere).  
Se esce **Croce**, scegliamo l’**urna 2** (con 4/5 di palline nere).

Vogliamo la probabilità complessiva di estrarre una **pallina nera**.

Dati:

$$  
P(T) = \frac{4}{10}, \quad P(C) = \frac{6}{10}  
$$
$$
P(\text{Nera} \mid T) = \frac{3}{5}, \quad P(\text{Nera} \mid C) = \frac{4}{5}  
$$
Albero:

![](imgs/Pasted%20image%2020251212173820.png)

Applichiamo la legge della somma:

$$
P(\text{Nera}) = P(T) \cdot P(\text{Nera} \mid T) + P(C) \cdot P(\text{Nera} \mid C)  
$$

$$
P(\text{Nera}) = \frac{4}{10}\cdot\frac{3}{5} + \frac{6}{10}\cdot\frac{4}{5} = \frac{18}{25}  
$$

---

### **9. Esempio 2 – Canale di comunicazione**

Un canale trasmette bit 0 e 1, con possibilità di errore.

- $P(t=1) = p$, da cui ricaviamo dunque $P(t=0) = q = 1 - p$
    
- $P(r=1 \mid t=1)$ = probabilità che un 1 arrivi corretto al ricevente $r$.
    
- $P(r=1 \mid t=0)$ = probabilità che uno 0 venga ricevuto come 1

Albero:

![](imgs/Pasted%20image%2020251212173905.png)


![](imgs/Pasted%20image%2020251212173923.png)

Vogliamo la probabilità complessiva di **ricevere un 1**:

$$  
P(r=1) = P(t=1) \cdot P(r=1 \mid t=1) + P(t=0) \cdot P(r=1 \mid t=0)  
$$

---

### **10. Esempio numerico – Canale di comunicazione**

Il canale trasmette **1 correttamente nel 95% dei casi** e gli input contengono **60% di 1**:

$$ 
P(t=1) = 0.60, \quad P(t=0) = 0.40  
$$  
$$ 
P(r=1 \mid t=1) = 0.95, \quad P(r=1 \mid t=0) = 0.05  
$$

$$
P(r=1) = (0.60)(0.95) + (0.40)(0.05) = 0.59  
$$

La probabilità di ricevere un 1 è **59%**.

---

### **11. Nota finale**

Nella pratica, raramente si chiede la probabilità di **ricevere** un certo simbolo.  
Più spesso, ricevuto un simbolo, si vuole sapere **quale fosse quello trasmesso**:  
questo è l’ambito del **Teorema di Bayes**, che verrà introdotto nella prossima unità.

---

### **12. Sintesi concettuale**

|**Concetto**|**Significato**|
|---|---|
|**Formulazione preliminare**|Calcolo diretto a partire dagli insiemi $A$ e $\neg A$|
|**Legge delle alternative**|Somma ponderata delle probabilità condizionate|
|**Forma generale**|Estensione alla partizione di $n$ eventi|
|**Esempio del canale di comunicazione**|Applicazione della legge alle probabilità di trasmissione e ricezione|

---

$$  
P(B) = P(B \cap A) + P(B \cap \neg A)  
$$

$$  
P(B) = P(A)P(B \mid A) + P(\neg A)P(B \mid \neg A)  
$$

$$  
P(B) = \sum_i P(A_i) \cdot P(B \mid A_i)  
$$

$$  
P(r=1) = P(t=1)P(r=1 \mid t=1) + P(t=0)P(r=1 \mid t=0)  
$$

---
### **13. Conclusione**

La **Legge della Somma** fornisce un metodo potente per calcolare la probabilità totale di un evento in presenza di **condizioni alternative**.  
È il ponte logico tra la probabilità condizionata e il Teorema di Bayes, e rappresenta una delle regole fondamentali del pensiero probabilistico moderno.