# **M4 UD2 Lezione 2 - Modello E-R - Le cardinalità**

---

### **1. Introduzione**

Nel **modello Entità–Relazione (E–R)**, le **cardinalità** definiscono **quante volte un’entità o un attributo può partecipare** a una relazione.  
Esse specificano, per ogni partecipazione, il **numero minimo e massimo di occorrenze** che possono essere associate a un’altra entità o a un valore di attributo.

Capire le cardinalità è essenziale per rappresentare correttamente i **vincoli logici** tra i dati e per evitare **errori semantici** nella progettazione dello schema concettuale.

---

### **2. Cardinalità delle relazioni**

#### **Definizione**

La **cardinalità di una relazione** indica **quante occorrenze** di relazione possono coinvolgere una determinata occorrenza di entità.  
Per ogni entità che partecipa a una relazione, la cardinalità è espressa come una **coppia di valori**:

$$  
(\text{minimo}, \text{massimo})  
$$

Questi due numeri specificano:

- **minimo** → il numero minimo di volte in cui un’occorrenza dell’entità _deve_ partecipare alla relazione;
    
- **massimo** → il numero massimo di volte in cui _può_ partecipare alla relazione.

#### **Esempio**

![](imgs/Pasted%20image%2020260821002504.png)

- La cardinalità (0,5) specificata come partecipazione di impiegato alla relazione sviluppo ci dice che impiegato potrà essere associato ad un num di prodotti che va da un minimo di 0 prodotti a un massimo di 5.
    
- La cardinalità (1,20) ci dice che ogni prodotto potrà essere associato ad un numero di impiegati che va da un numero minimo di 1 ad un massimo di 20.
    
- La cardinalità (1,1) ci fa desumere che per ogni prodotto abbiamo esattamente un cliente
    
- Infine un _cliente_ può avere da 0 a 10 prodotti.

---

### **3. Regole generali delle cardinalità**

Qualsiasi coppia di interi non negativi può teoricamente essere usata come cardinalità, purché:

$$  
\text{minimo} \le \text{massimo}  
$$

Tuttavia, nella pratica si utilizzano solo alcuni valori standardizzati:

- **0** → per indicare partecipazione opzionale;
    
- **1** → per indicare partecipazione obbligatoria o singola;
    
- **n** → per indicare partecipazione multipla (numero qualsiasi ≥ 1).

---

### **4. Significato delle cardinalità minima e massima**

#### **Cardinalità minima**

- **0** → partecipazione _opzionale_ (l’entità può non partecipare alla relazione);
    
- **1** → partecipazione _obbligatoria_ (l’entità deve partecipare almeno una volta).

#### **Cardinalità massima**

- **1** → partecipazione _al più una volta_ (relazione singola);
    
- **n** → partecipazione _più volte_ (relazione multipla).

---

### **5. Cardinalità più comuni**

Le combinazioni più frequenti nel modello E–R sono quattro:

|**Cardinalità**|**Significato**|
|---|---|
|**(0,1)**|Partecipazione _opzionale_, _al più una volta_|
|**(0,n)**|Partecipazione _opzionale_, _più volte_|
|**(1,1)**|Partecipazione _obbligatoria_, _una sola volta_|
|**(1,n)**|Partecipazione _obbligatoria_, _più volte_|

---

### **6. Esempio grafico di cardinalità**

Esempio di relazione nel contesto universitario:

![](imgs/Pasted%20image%2020260821073253.png)

---

### **7. Classificazione delle relazioni binarie**

Le relazioni **binarie** (cioè tra due entità) possono essere classificate in base alle **cardinalità massime** delle due entità:

|**Tipo di relazione**|**Descrizione**|**Esempio**|
|---|---|---|
|**1:1 (uno a uno)**|A ogni occorrenza di un’entità corrisponde _al più_ un’occorrenza dell’altra.|_svolgere_ tra **studente** e **tesi**|
|**1:n (uno a molti)**|Un’entità può essere associata a _molte_ occorrenze dell’altra, ma non viceversa.|_seguire_ tra **studente** e **corso**|
|**n:m (molti a molti)**|Entrambe le entità possono avere _più associazioni_ reciproche.|_docenza_ tra **professore** e **corso**|

Attenzione: quando vedremo delle copie di cardinalità nel corso degli schemi/esempi, se sto specificando min/max ci sarà una virgola come separatore.
Se invece sto specificando classi di relazioni, troverò i due punti.

---

### **8. Cardinalità degli attributi**

Come per le relazioni, anche ogni **attributo** di un’entità o di una relazione può avere una **cardinalità**.

#### **Definizione**

La cardinalità di un attributo specifica **quanti valori** possono essere associati a ogni occorrenza di entità o relazione.

Ogni attributo è descritto da una coppia $(\text{min}, \text{max})$, con la condizione:

$$  
\text{min} \le \text{max}  
$$

I valori possibili sono ancora **0**, **1**, **n**.

---

### **9. Interpretazione delle cardinalità degli attributi**

#### **Cardinalità minima**

- **0** → proprietà opzionale;
    
- **1** → proprietà obbligatoria.

#### **Cardinalità massima**

- **1** → la proprietà ha al più _un valore_ (scalare);
    
- **n** → la proprietà può assumere _più valori_ (insieme).

---

### **10. Cardinalità degli attributi più comuni**

|**Cardinalità**|**Significato**|
|---|---|
|**(0,1)**|Proprietà opzionale, al più un valore.|
|**(0,n)**|Proprietà opzionale, può avere un insieme di valori.|
|**(1,1)**|Proprietà obbligatoria, esattamente un valore (proprietà scalare).|
|**(1,n)**|Proprietà obbligatoria, può avere più valori.|

> La cardinalità **(1,1)** è così comune da poter essere **omessa** nello schema E–R. Infatti in gergo si chiama **proprietà scalare**, ed è la default del modello.

---

### **11. Esempio di cardinalità degli attributi**

Esempio di entità _Impiegato_ e _Progetto_:

![](imgs/Pasted%20image%2020260821074833.png)

Interpretazione:

- Ogni **impiegato** deve avere _una matricola, un nome e un cognome_, ma può avere _più numeri di telefono_.
    
- Ogni **progetto** deve avere _codice e titolo_, ma può avere _uno o + sponsor_.

---

### **12. Sintesi finale**

**Abbiamo visto:**

- Il concetto di **cardinalità** applicato a relazioni e attributi.
    
- Le **quattro combinazioni principali** e il loro significato.
    
- La **classificazione delle relazioni binarie** (1:1, 1:n, n:m).
    
- L’uso delle cardinalità per modellare in modo accurato i **vincoli logici** del sistema informativo.

**Ricorda:**

> Le cardinalità sono un elemento chiave del modello E–R perché determinano **il grado di partecipazione** delle entità e delle proprietà, assicurando che lo schema rifletta correttamente la realtà del dominio applicativo.

---


![](imgs/Pasted%20image%2020251125050239.png)

