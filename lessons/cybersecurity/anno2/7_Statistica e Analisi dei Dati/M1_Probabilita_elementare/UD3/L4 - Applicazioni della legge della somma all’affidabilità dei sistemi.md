# **M1 UD3 Lezione 4 - Applicazioni della legge della somma all’affidabilità dei sistemi**

### **1. Introduzione**

In questa lezione applichiamo la **Legge della Somma** allo studio dell’**affidabilità dei sistemi complessi**, in particolare di quelli **non riconducibili a semplici strutture serie o parallelo**.  
Vedremo come scomporre un sistema in **scenari alternativi** e calcolare la probabilità complessiva di funzionamento pesando ciascuno scenario con la probabilità del corrispondente evento.

---

### **2. Richiamo – La legge della somma**

Ricordiamo la forma generale della legge:

$$  
P(B) = P(A_1)P(B \mid A_1) + P(A_2)P(B \mid A_2) + \dots + P(A_n)P(B \mid A_n)  
$$

In forma compatta:

$$  
P(B) = \sum_k P(A_k)P(B \mid A_k)  
$$

Questa legge consente di **combinare probabilità condizionate su scenari alternativi** $A_k$, e si applica perfettamente ai sistemi di affidabilità che possono funzionare in modi diversi a seconda dello stato di un componente chiave.

---

### **3. Rappresentazione ad albero**

Ogni scenario alternativo può essere rappresentato come un ramo dell’albero delle possibilità:

![](imgs/Pasted%20image%2020251212174427.png)

```
A₁ (P(A₁))
│
├── B       (P(B|A₁))
└── ¬B

A₂ (P(A₂))
│
├── B       (P(B|A₂))
└── ¬B

...
Aₙ (P(Aₙ))
│
├── B       (P(B|Aₙ))
└── ¬B
```

La probabilità totale dell’evento $B$ si ottiene sommando i contributi dei rami che portano a $B$.

---

### **4. Sistemi non serie-parallelo**

Non tutti i sistemi possono essere ridotti a **serie o parallelo puri**, come il seguente:

![](imgs/Pasted%20image%2020251212174455.png)

Consideriamo ad esempio un circuito composto da **quattro componenti $A$, $B$, $C$, $D$**.  

![](imgs/Pasted%20image%2020251212174513.png)

Se analizziamo il comportamento del sistema rispetto al componente $C$, possiamo distinguere due scenari:

- **$C$ non funziona ($\neg C$)** → il circuito si interrompe in quel punto, e $D$ diventa irrilevante.  
    Il sistema si riduce alla **serie di $A$ e $B$**.
    
    ![](imgs/Pasted%20image%2020251212174558.png)
    
    ![](imgs/Pasted%20image%2020251212174614.png)
    
- **$C$ funziona ($C$)** → il nodo $A$ diventa irrilevante, e il sistema si riduce al **parallelo di $B$ e $D$**.
    
    ![](imgs/Pasted%20image%2020251212174632.png)
    
    ![](imgs/Pasted%20image%2020251212174705.png)
    
    ![](imgs/Pasted%20image%2020251212174712.png)

---

### **5. Modellazione con la legge delle alternative**

Indichiamo con $T$ l’evento “funzionamento del sistema complessivo”.  
La probabilità totale di corretto funzionamento sarà:

$$  
P(T) = P(C) \cdot P(T \mid C) + P(\neg C) \cdot P(T \mid \neg C)  
$$

Le due probabilità condizionate rappresentano i due **scenari alternativi**.

---

### **6. Calcolo per componenti identici**

Supponiamo che tutti i componenti abbiano **affidabilità $p$** e **fallibilità $q = 1 - p$**.  
Allora:

- $P(C) = p$
    
- $P(\neg C) = q$

Applichiamo ora le regole di affidabilità note:

- **Serie di componenti:**  
    L’affidabilità complessiva è il prodotto delle singole:  
    $P(T \mid \neg C) = p^2$
    
- **Parallelo di componenti:**  
    La fallibilità complessiva è il prodotto delle fallibilità:  
    $P(T \mid C) = 1 - q^2$

Sostituendo:

$$  
P(T) = p(1 - q^2) + q p^2  
$$

---

### **7. Interpretazione del risultato**

La legge delle alternative consente di trattare sistemi complessi come **combinazioni di scenari equivalenti più semplici**, pesando ciascun scenario in base alla probabilità che esso si verifichi.  
Nel caso studiato, il circuito si comporta come una miscela probabilistica tra:

- uno schema **in serie (A–B)**, attivo se $C$ fallisce,
    
- e uno schema **in parallelo (B–D)**, attivo se $C$ funziona.

---

### **8. Secondo esempio – Sistema con cinque linee di comunicazione**

Consideriamo ora un sistema composto da **cinque linee** (tutte con la stessa affidabilità $p$ e fallibilità $q$), disposte come segue:

- una linea **centrale (C)** verticale,
    
- e due coppie di linee orizzontali sopra e sotto, collegate ai capi.

![](imgs/Pasted%20image%2020251212174944.png)

Questo sistema **non è riducibile** a un classico schema serie-parallelo.

---

### **9. Scenari alternativi**

Identifichiamo il componente chiave $C$ e analizziamo i due scenari:

- Se **$C$ non funziona ($\neg C$)** → il sistema equivale a **un parallelo di due serie**, ciascuna formata da due linee orizzontali.
    
    ![](imgs/Pasted%20image%2020251212175032.png)
    
- Se **$C$ funziona ($C$)** → il sistema equivale a **una serie di due paralleli** (uno sopra e uno sotto).
    
    ![](imgs/Pasted%20image%2020251212175038.png)

---

### **10. Calcolo delle affidabilità parziali**

**Caso $\neg C$ – Parallelo di serie:**

- Ogni serie di due componenti ha affidabilità $p^2$
    
- Fallibilità di una serie: $(1 - p^2)$
    
- Fallibilità del parallelo: $(1 - p^2)^2$
    
- Affidabilità complessiva del parallelo:  
    $P(T \mid \neg C) = 1 - (1 - p^2)^2$


![](imgs/Pasted%20image%2020251212175140.png)

**Caso $C$ – Serie di paralleli:**

- Ogni parallelo di due componenti ha affidabilità $(1 - q^2)$
    
- Affidabilità della serie dei due paralleli:  
    $P(T \mid C) = (1 - q^2)^2$

---

### **11. Combinazione dei due scenari**

La probabilità totale di funzionamento del sistema complessivo è:

$$  
P(T) = P(C)P(T \mid C) + P(\neg C)P(T \mid \neg C)  
$$

Sostituendo $P(C)=p$, $P(\neg C)=q$, otteniamo:

$$  
P(T) = p(1 - q^2)^2 + q[1 - (1 - p^2)^2]  
$$

---

### **12. Analisi del risultato**

Il valore di $P(T)$ fornisce una stima precisa dell’affidabilità complessiva di un sistema non serie-parallelo.  
Questo approccio consente di **modellare componenti interdipendenti** in modo semplice, trattandoli come **scenari condizionati alternativi**.

---

### **13. Sintesi concettuale**

|**Formula**|**Significato**|
|---|---|
|$P(T) = P(C)P(T \mid C) + P(\neg C)P(T \mid \neg C)$|Legge delle alternative applicata all’affidabilità|
|$P(T \mid C) = 1 - q^2$|Affidabilità del parallelo|
|$P(T \mid \neg C) = p^2$|Affidabilità della serie|
|$P(T) = p(1 - q^2) + q p^2$|Risultato del primo circuito|
|$P(T) = p(1 - q^2)^2 + q[1 - (1 - p^2)^2]$|Risultato del secondo circuito|

---

### **14. Conclusione**

La **Legge della Somma** è uno strumento estremamente potente anche nell’ambito dell’**affidabilità dei sistemi**.  
Permette di affrontare configurazioni non riducibili a serie o parallelo, scomponendo il sistema in **scenari alternativi** e combinandone i risultati in modo pesato.  
È il ponte che collega la teoria della probabilità alla progettazione e valutazione di sistemi reali.