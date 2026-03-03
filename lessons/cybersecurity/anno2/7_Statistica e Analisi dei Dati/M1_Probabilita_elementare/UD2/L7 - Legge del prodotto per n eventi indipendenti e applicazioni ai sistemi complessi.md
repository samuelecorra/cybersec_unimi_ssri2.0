# **M1 UD2 Lezione 7 - Legge del prodotto per n eventi indipendenti e applicazioni ai sistemi complessi**

### **1. Introduzione**

Dopo aver studiato la legge del prodotto per due e tre eventi, generalizziamo ora il concetto a un **numero qualsiasi di eventi indipendenti**.  
Questa estensione permette di analizzare **sistemi con molti componenti** (in serie, in parallelo o misti), fondamentali nello studio dell’**affidabilità dei sistemi complessi** — dalle reti informatiche ai circuiti elettronici.

---

### **2. Legge del prodotto per tre eventi indipendenti**

Per tre eventi qualsiasi vale:

$$  
P(A \cap B \cap C) = P(A) \cdot P(B|A) \cdot P(C|A \cap B)  
$$

Se gli eventi sono **indipendenti**, ogni evento non influisce sugli altri:

$$  
P(B|A) = P(B), \qquad P(C|A \cap B) = P(C)  
$$

Allora la formula si semplifica in:

$$  
P(A \cap B \cap C) = P(A) \cdot P(B) \cdot P(C)  
$$

---

### **3. Legge del prodotto per n eventi indipendenti**

In generale, per $n$ eventi $A_1, A_2, \dots, A_n$ indipendenti:

$$  
P(A_1 \cap A_2 \cap \dots \cap A_n) = P(A_1) \cdot P(A_2) \cdot \dots \cdot P(A_n)  
$$

In forma compatta, dove l'intersezione grande potremmo chiamarla "intersettoria" mentre il grande pigreco è la produttoria

$$  
P\left( \bigcap_{i=1}^{n} A_i \right) = \prod_{i=1}^{n} P(A_i)  
$$

Questa è la **legge del prodotto per n eventi indipendenti**.  
Si applica quando gli eventi non sono influenzati l’uno dall’altro — tipicamente in esperimenti **con reimbussolamento** o **senza memoria**.

---

### **4. Esempio – Tre estrazioni indipendenti**

Supponiamo un’urna con 5 palline bianche e 5 nere.  
Effettuiamo 3 estrazioni **con rimessa** (quindi indipendenti).

Ogni volta:

$$  
P(\text{nera}) = \frac{1}{2}  
$$

La probabilità di ottenere tre nere di seguito si può scrivere ancor più compattamente, perché essendo le estrazioni equiprobabili, abbiamo per tre volte da moltiplicare un mezzo e quindi possiamo semplicemente elevare alla potenza del numero di estrazioni, ovvero alla terza:

$$  
P(A \cap B \cap C) = \left( \frac{1}{2} \right)^3 = \frac{1}{8}  
$$

Per $n$ estrazioni, supponendo sempre l'equiprobabilità di estrazione della pallina nera, possiamo generalizzare così:

$$  
P(\text{tutte nere}) = \left( \frac{1}{2} \right)^n  
$$

---

### **5. Applicazioni all’affidabilità dei sistemi**

La legge del prodotto per eventi indipendenti è cruciale per calcolare la **probabilità di funzionamento di sistemi composti da più elementi**.  
Ogni componente ha una propria **affidabilità** (probabilità di funzionare) e una propria **fallibilità** (probabilità di guastarsi).

A seconda della configurazione dei componenti, il sistema può essere:

- **in serie**
    
- **in parallelo**
    
- **serie-parallelo** (combinato)

---

### **6. Sistemi in serie**

Un sistema con $n$ componenti **in serie** funziona solo se **tutti** funzionano.  
Se le affidabilità sono $R_1, R_2, \dots, R_n$, allora:

$$  
R_{sistema} = R_1 \cdot R_2 \cdot \dots \cdot R_n = \prod_{i=1}^{n} R_i  
$$

Se tutti i componenti hanno la stessa affidabilità $R$, allora:

$$  
R_{sistema} = R^n  
$$

**Osservazione:**  
più componenti in serie → maggiore vulnerabilità → minore affidabilità complessiva.

---

### **7. Esempio – Tre componenti in serie**

Siano:

- $R_A = 0.9$
    
- $R_B = 0.8$
    
- $R_C = 0.7$

Allora:

$$  
R_S = R_A \cdot R_B \cdot R_C = 0.9 \cdot 0.8 \cdot 0.7 = 0.504  
$$

L’affidabilità complessiva è del **50,4%**.

---

### **8. Sistemi in parallelo**

Un sistema con $n$ componenti **in parallelo** funziona se **almeno uno** dei componenti funziona.  
Si guasta solo se **tutti** falliscono contemporaneamente.

Sia $F_i = 1 - R_i$ la fallibilità di ciascun componente.  
Allora:

$$  
F_P = F_1 \cdot F_2 \cdot \dots \cdot F_n = \prod_{i=1}^{n} F_i  
$$

e quindi:

$$  
R_P = 1 - F_P = 1 - \prod_{i=1}^{n} (1 - R_i)  
$$

---

### **9. Esempio – Tre componenti in parallelo**

Usiamo gli stessi componenti di prima:

$$  
R_A = 0.9, \quad R_B = 0.8, \quad R_C = 0.7  
$$

Calcoliamo prima le fallibilità:

$$  
F_A = 0.1, \quad F_B = 0.2, \quad F_C = 0.3  
$$

$$  
F_P = 0.1 \cdot 0.2 \cdot 0.3 = 0.006  
$$

$$  
R_P = 1 - F_P = 0.994  
$$

Il sistema in parallelo ha dunque affidabilità del **99,4%**, molto più alta rispetto al caso in serie.

---

### **10. Sistemi serie-parallelo**

Molti sistemi reali combinano **blocchi in serie e in parallelo**.  
In questi casi si procede calcolando prima l’affidabilità dei sottosistemi, e poi quella complessiva.  
È l’approccio detto “**divide et impera**”.

Esempio: un sistema composto da sottosistemi $T$ e $Q$ (entrambi paralleli), collegati in serie con altri componenti.

---

### **11. Calcolo su un sistema serie-parallelo**

Siano:

- $R_1=0.95$, $R_2=0.99$, $R_3=0.70$, $R_4=0.75$, $R_5=0.9$
    

Supponiamo che:

- $R_3$ e $R_4$ siano replicati in parallelo in due blocchi:  
    $T$ (tre componenti tipo $R_3$) e $Q$ (due componenti tipo $R_4$).


![](imgs/Pasted%20image%2020251212170626.png)

#### **Calcolo delle affidabilità dei blocchi paralleli**

$$  
F_T = (1 - R_3)^3 = (0.3)^3 = 0.027 \quad \Rightarrow \quad R_T = 1 - F_T = 0.973  
$$

$$  
F_Q = (1 - R_4)^2 = (0.25)^2 = 0.0625 \quad \Rightarrow \quad R_Q = 1 - F_Q = 0.9375  $$


#### **Sistema complessivo in serie**

$$  
R = R_1 \cdot R_2 \cdot R_T \cdot R_Q \cdot R_5 = 0.95 \cdot 0.99 \cdot 0.973 \cdot 0.9375 \cdot 0.9 \approx 0.81  
$$

---

### **12. Sistemi non serie-parallelo (a stella)**

Non tutti i sistemi possono essere ridotti a blocchi serie o paralleli.  
In alcune reti — come quelle “a stella” — i percorsi di ridondanza si intrecciano, e non si può applicare direttamente la legge del prodotto.  
In questi casi, è necessario usare la **legge della somma** (prossima lezione) per valutare le probabilità complessive.

---

### **13. Sintesi concettuale**

|**Tipo di sistema**|**Condizione di funzionamento**|**Formula**|**Osservazione**|
|---|---|---|---|
|**Serie**|Tutti devono funzionare|$R_S = \prod R_i$|Aumenta l’inaffidabilità|
|**Parallelo**|Almeno uno deve funzionare|$R_P = 1 - \prod (1 - R_i)$|Maggiore affidabilità|
|**Serie-parallelo**|Combinazione dei due|Calcolo in blocchi|Si usa “divide et impera”|

---

### **14. Conclusione**

La **legge del prodotto per n eventi indipendenti** rappresenta la chiave di lettura probabilistica dei sistemi composti.  
Grazie a essa possiamo stimare il comportamento globale di circuiti, reti e infrastrutture complesse partendo dal comportamento dei singoli componenti.  
La prossima lezione introdurrà la **legge della somma**, utile per affrontare casi non scomponibili in blocchi puramente serie o parallelo.