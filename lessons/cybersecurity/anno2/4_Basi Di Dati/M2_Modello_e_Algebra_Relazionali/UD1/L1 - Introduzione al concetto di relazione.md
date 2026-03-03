# **M2 UD1 Lezione 1: Introduzione al concetto di relazione**

### **1. Origine del modello relazionale**

Il **modello relazionale** fu proposto da **E. F. Codd nel 1970** come modello logico che favorisse l’**indipendenza dei dati**, separando la rappresentazione logica da quella fisica.  
Nonostante l’idea risalga agli anni ’70, il modello venne **implementato nei DBMS reali solo nel 1981**, poiché inizialmente considerato troppo astratto.

Il modello relazionale si basa su due principi fondamentali:

- Il **concetto matematico di relazione**
    
- La **rappresentazione tabellare** delle relazioni

---

### **2. Significato del termine “relazione”**

Il termine **relazione** assume **tre accezioni differenti** nel contesto delle basi di dati:

1. **Relazione matematica** → secondo la **teoria degli insiemi**
    
2. **Relazione nel modello relazionale** → rappresentata come **tabella**
    
3. **Relazione (relationship)** nel **modello concettuale Entità-Relazione (E-R)**, utilizzato nella progettazione concettuale delle basi di dati

---

### **3. Relazione matematica**

Siano $D_1, D_2, \ldots, D_n$ insiemi (anche non distinti).  
Il **prodotto cartesiano** dei domini è:

$$D_1 \times D_2 \times \ldots \times D_n = {(d_1, d_2, \ldots, d_n) \mid d_1 \in D_1, d_2 \in D_2, \ldots, d_n \in D_n}$$

Una **relazione matematica** su $D_1, D_2, \ldots, D_n$ è un **sottoinsieme del prodotto cartesiano** $D_1 \times D_2 \times \ldots \times D_n$.  
Può essere rappresentata anche in **forma tabellare**, dove ogni riga corrisponde a una n-upla dell’insieme.

---

### **4. Caratteristiche della relazione matematica**

Data una relazione matematica $r \subseteq D_1 \times D_2 \times \ldots \times D_n$:

- **Domini della relazione:** $D_1, D_2, \ldots, D_n$
    
- **Grado della relazione:** numero di domini $n$
    
- **Cardinalità della relazione:** numero di n-uple contenute in $r$
    
- **Cardinalità del prodotto cartesiano:** $|D_1| \times |D_2| \times \ldots \times |D_n|$

In generale, le relazioni sono **finite**, anche se i domini possono essere **infiniti**.

**Esempio**

Sia $D_1 = {a, b}$ e $D_2 = {x, y, z}$.  

Allora:

$$D_1 \times D_2 = {(a,x), (a,y), (a,z), (b,x), (b,y), (b,z)}$$

Una relazione $r \subseteq D_1 \times D_2$ potrebbe essere:

$$r = {(a,x), (a,z), (b,y)}$$

→ **Grado:** 2  
→ **Cardinalità:** 3

---

### **5. Proprietà fondamentali**

Una **relazione sui domini** $D_1, D_2, \ldots, D_n$ è un **insieme di n-uple ordinate** tali che

$$d_1 \in D_1, d_2 \in D_2, \ldots, d_n \in D_n$$

Proprietà principali:

- La relazione è un **insieme** → le n-uple sono **distinte e non ordinate** tra loro
    
- Le n-uple sono **ordinate internamente** → l’**i-esimo valore** di ciascuna tupla proviene dall’**i-esimo dominio**

**Esempio**

|Reti|Fuori|RetiCasa|FuoriCasa|
|:--|:--|:--|:--|
|0|1|Juve|Milan|
|3|1|Milan|Roma|
|2|2|Roma|Juve|
|0|1|Milan|Lazio|
|1|3|Lazio|Juve|

Ogni **posizione** ha un significato preciso (ruolo del dominio).  
Ad esempio, nel dominio “Squadre”, i ruoli **“Casa”** e **“Fuori”** sono distinti in base alla posizione.

---

### **6. Relazione nel modello relazionale**

Nel **modello relazionale**, a ciascun dominio è **associato un nome (attributo)**, che ne definisce il ruolo nella relazione.  
Gli attributi costituiscono l’**intestazione delle colonne** della tabella.

**Esempio**

|RetiCasa|FuoriCasa|RetiFuori|SquadraCasa|SquadraFuori|
|:--|:--|:--|:--|:--|
|0|1|Juve|Milan||
|3|1|Milan|Roma||
|2|2|Roma|Juve||
|0|1|Milan|Lazio||
|1|3|Lazio|Juve||

Proprietà:

- Ogni **attributo** ha un nome **univoco** nella relazione.
    
- L’**ordinamento degli attributi è irrilevante**: la struttura non è posizionale ma **nominale**.
    
- Ogni colonna rappresenta un **dominio** e ogni riga una **tupla** (n-upla di valori).

---

### **7. Definizione formale di relazione**

Sia:

- $X$ = insieme degli **attributi**
    
- $D$ = insieme dei **domini**

Definiamo:

- Una **funzione**  
    $dom: X \to D$  
    che associa a ogni attributo $A \in X$ il proprio dominio $dom(A) \in D$
    
- Una **tupla (o n-upla)** su $X$ è una **funzione**  
    $t: X \to \bigcup D$  
    tale che per ogni attributo $A \in X$, $t(A) \in dom(A)$

Una **relazione su $X$** è un **insieme di tuple su $X$**.

---

### **8. Notazione e proprietà tabellari**

Sia $t$ una tupla su $X$, e siano $A \in X$ e $Y \subseteq X$:

- $t[A]$ oppure $t.A$: valore dell’attributo $A$ nella tupla $t$
    
- $t[Y]$: insieme dei valori degli attributi in $Y$

**Esempio**

|RetiCasa|FuoriCasa|RetiFuori|
|:--|:--|:--|
|2|Roma|Juve|
|0|Milan|Lazio|
|1|Lazio|Juve|

Sia $t$ la tupla della prima riga:

- $t[Casa] = t.Casa = \text{Juve}$
    
- $t[Casa, RetiCasa] = [\text{Juve}, 3]$

---

### **9. Proprietà di una tabella relazionale**

Una tabella rappresenta correttamente una relazione se soddisfa le seguenti condizioni:

- I **valori di ogni colonna** sono **omogenei** (stesso dominio)
    
- Le **righe (tuple)** sono **distinte** tra loro
    
- Le **intestazioni delle colonne (attributi)** sono **tutte diverse**

Inoltre:

- L’**ordine delle righe** è irrilevante
    
- L’**ordine delle colonne** è irrilevante

La tabella è dunque una **rappresentazione logica** e non fisica dei dati.

---

### **10. Sintesi finale**

In questa lezione abbiamo introdotto:

- Il **concetto di relazione** nelle sue diverse accezioni
    
- La **relazione matematica** e le sue proprietà
    
- La **relazione nel modello relazionale**, basata su attributi e tuple
    
- Le **regole che una tabella deve rispettare** per rappresentare una relazione

Questi concetti costituiscono la base teorica del modello relazionale, su cui si fondano tutti i **DBMS moderni** e i **linguaggi di interrogazione** come SQL.

![](imgs/Pasted%20image%2020251125042936.png)