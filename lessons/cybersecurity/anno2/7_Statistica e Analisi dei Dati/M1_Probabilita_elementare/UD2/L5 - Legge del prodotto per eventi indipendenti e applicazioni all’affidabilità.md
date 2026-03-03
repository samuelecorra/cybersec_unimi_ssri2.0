# **M1 UD2 Lezione 5 - Legge del prodotto per eventi indipendenti e applicazioni all’affidabilità**

### **1. Introduzione**

Dopo aver studiato la legge del prodotto per eventi generici, analizziamo ora il caso particolare — ma molto importante — in cui gli eventi siano **indipendenti**.  
In questa condizione, la probabilità del verificarsi simultaneo di due eventi si ottiene semplicemente **moltiplicando le loro probabilità individuali**.  
Questo principio trova applicazione diretta nello studio dell’**affidabilità dei sistemi**, dove si valutano le probabilità che componenti o sottosistemi funzionino correttamente.

---

### **2. Legge del prodotto per eventi indipendenti**

Per due eventi qualsiasi $B$ e $C$, vale la **legge generale del prodotto** che abbiamo già discusso ampiamente nella lezione 3, ma che ripropongo qui per esaustività:

$$  
P(B \text{ e } C) = P(B) \cdot P(C \mid B)  
$$

Se però gli eventi sono **indipendenti**, per definizione:

$$  
P(C \mid B) = P(C)  
$$
Questo perché non si inficiano a vicenda!!! Assioma importante...

Da cui segue la forma semplificata della **legge del prodotto per eventi indipendenti**:

$$  
P(B \text{ e } C) = P(B) \cdot P(C)  
$$

Questa formula significa che, quando due eventi non si influenzano, la probabilità che si verifichino insieme è il prodotto delle loro probabilità individuali. Già visto in L3 con le carte, ma qua approfondiamo...

---

### **3. Applicazioni: affidabilità dei sistemi**

In ingegneria e informatica, l’**affidabilità** (reliability) misura la probabilità che un sistema funzioni correttamente in un certo intervallo di tempo.  
Se un sistema è composto da più **componenti indipendenti**, la probabilità complessiva che funzioni dipende dal modo in cui questi sono collegati — **in serie** o **in parallelo**.

---

### **4. Sistemi in serie**

Un sistema con due componenti A e B in **serie** funziona **solo se entrambi** i componenti sono operativi.  
Basta infatti che uno si guasti per bloccare il funzionamento dell’intero sistema.

Sia:

- $P(X)$ la probabilità che il sistema funzioni
    
- $P(A)$ la probabilità che il componente $A$ funzioni
    
- $P(B)$ la probabilità che il componente $B$ funzioni

Allora:

$$  
P(X) = P(A \text{ e } B)  
$$
Dicitura del tutto analoga ad:

$$  
P(X) = P(A \cap B)  
$$
Preferiremo questa più formale d'ora in avanti...

Se i due componenti sono **indipendenti**, possiamo applicare la legge del prodotto:

$$  
P(X) = P(A) \cdot P(B)  
$$

---

### **5. Affidabilità e notazione**

Nel linguaggio dell’affidabilità:

- $R_X$ = affidabilità del sistema
    
- $R_A$, $R_B$ = affidabilità dei singoli componenti

La formula diventa, con un semplice cambio di notazione che lascia invariata la logica:

$$  
R_X = R_A \cdot R_B  
$$

**Interpretazione:**  
L’affidabilità di un sistema in serie è il **prodotto** delle affidabilità dei componenti.  
Più componenti in serie significano più possibilità di guasto → affidabilità complessiva minore.

---

### **6. Esempio numerico – Serie**

$$  
R_A = 0.9, \quad R_B = 0.9  
$$

$$  
R_X = R_A \cdot R_B = 0.9 \cdot 0.9 = 0.81  
$$

Un sistema formato da due componenti in serie, ciascuno con affidabilità 90%, ha affidabilità complessiva del **81%**. Ergo si "riduce".

---

### **7. Sistemi in parallelo**

Un sistema con due componenti in **parallelo** funziona se **almeno uno dei due** è operativo.  
Si guasta solo se **entrambi** i componenti sono guasti contemporaneamente.

Sia:

- $P(\neg Y)$ la probabilità che il sistema non funzioni
    
- $\neg A$, $\neg B$ gli eventi “A non funziona” e “B non funziona”

Allora:

$$  
P(\neg Y) = P(\neg A \text{ e } \neg B)  
$$

Se i componenti sono indipendenti:

$$  
P(\neg Y) = P(\neg A) \cdot P(\neg B)  
$$

---

### **8. Fallibilità**

Col complemento appena spiegato sopra, possiamo introdurre il concetto complementare della reliability, ovvero la fallibility.
Si definisce:

- $F_Y = P(\neg Y)$ → **fallibilità** del sistema
    
- $F_A = P(\neg A)$, $F_B = P(\neg B)$ → **fallibilità** dei componenti

Allora vale:

$$  
F_Y = F_A \cdot F_B  
$$

La **fallibilità** di un sistema in parallelo è il prodotto delle fallibilità dei suoi componenti indipendenti.

---

### **9. Dalle fallibilità alle affidabilità**

Poiché $R = 1 - F$, possiamo risalire all’affidabilità del sistema:

$$  
R_Y = 1 - F_Y  
$$

E se servono le fallibilità ma abbiamo le affidabilità iniziali:

$$  
F_A = 1 - R_A \qquad F_B = 1 - R_B  
$$

---

### **10. Esempio numerico – Parallelo**

$$  
R_A = 0.9, \quad R_B = 0.9  
$$

$$  
F_A = 1 - R_A = 0.1, \qquad F_B = 1 - R_B = 0.1  
$$

$$  
F_Y = F_A \cdot F_B = 0.1 \cdot 0.1 = 0.01  
$$

$$  
R_Y = 1 - F_Y = 1 - 0.01 = 0.99  
$$

Il sistema in parallelo ha quindi affidabilità complessiva del **99%**, maggiore rispetto al caso in serie, con conseguente fallibilità complessiva dell'1%.

---

### **11. Confronto finale**

|**Configurazione**|**Condizione di funzionamento**|**Formula**|**Risultato con $R_A = R_B = 0.9$**|
|---|---|---|---|
|**Serie**|Tutti i componenti devono funzionare|$R_X = R_A \cdot R_B$|0.81|
|**Parallelo**|Basta che funzioni almeno uno|$R_Y = 1 - (1 - R_A)(1 - R_B)$|0.99|

---

### **12. Sintesi concettuale**

- La **legge del prodotto per eventi indipendenti** è la base per il calcolo dell’affidabilità di sistemi composti.
    
- Nei **sistemi in serie**, le affidabilità si moltiplicano.
    
- Nei **sistemi in parallelo**, si moltiplicano le fallibilità e poi si applica la legge del complemento.
    
- I modelli di affidabilità sono essenziali in ingegneria dei sistemi, informatica, reti e sicurezza, per stimare la probabilità che un sistema complesso resti operativo.

---

### **13. Conclusione**

La legge del prodotto per eventi indipendenti non è solo un risultato teorico, ma uno strumento concreto per progettare e valutare sistemi reali.  
Capire come combinare correttamente **affidabilità** e **fallibilità** consente di modellare reti, macchine e sistemi digitali in modo predittivo e sicuro.
