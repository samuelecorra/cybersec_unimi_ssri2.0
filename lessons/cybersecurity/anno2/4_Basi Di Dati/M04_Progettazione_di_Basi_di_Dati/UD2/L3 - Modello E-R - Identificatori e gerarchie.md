# **M4 UD2 Lezione 3 - Modello E-R - Identificatori e gerarchie**

---

### **1. Introduzione**

Questa lezione completa lo studio del **modello Entità–Relazione (E–R)** approfondendo due concetti chiave:

- gli **identificatori**, che consentono di distinguere in modo univoco le occorrenze di un’entità;
    
- le **gerarchie di generalizzazione e specializzazione**, che descrivono relazioni di tipo “padre–figlio” tra entità, permettendo di modellare la realtà in modo più flessibile e semantico.

---

### **2. Identificatori delle entità**

#### **Definizione**

Ogni entità deve poter essere **identificata univocamente** all’interno della base di dati.  
Un **identificatore** è l’insieme dei concetti (attributi e/o relazioni) che permettono di distinguere una singola occorrenza dell’entità da tutte le altre.

Si distinguono due tipi principali:

- **Identificatori interni**
    
- **Identificatori esterni**

---

### **3. Identificatore interno**

Un **identificatore interno** è formato da **uno o più attributi scalari** appartenenti alla stessa entità.  
Questi attributi costituiscono una **chiave univoca** per le sue occorrenze.

#### **Esempi**

- L’entità _Impiegato_ può essere identificata dall’attributo _matricola_.
    
- L’entità _Libro_ può essere identificata dalla coppia _(autore, titolo)_.

#### **Rappresentazione grafica**

- Se l’identificatore è **un solo attributo**, questo viene rappresentato con un **cerchietto pieno**.
    
- Se l’identificatore è **composto da più attributi**, si disegna una **linea che collega e taglia** gli attributi coinvolti e termina con un **pallino pieno**.

```
IMPIEGATO
  ├──● Matricola
  ├── Cognome
  └── Nome
```

oppure:

```
LIBRO
  ├──● Autore
  ├──● Titolo
  └── Editore
```

---

### **4. Identificatori multipli**

Un’entità può avere **più di un identificatore** (chiavi alternative).  
In questo caso:

- tutti identificano univocamente le occorrenze,
    
- ma **solo uno** è scelto come identificatore principale,
    
- gli altri possono essere **opzionali** o usati come **chiavi secondarie**.

**Esempio**

```
IMPIEGATO
 ├──● Matricola
 ├── Nome
 ├── Cognome
 └── Codice Fiscale (0,1)
```

Entrambi _Matricola_ e _Codice Fiscale_ possono identificare univocamente un impiegato, ma il primo è l’identificatore principale.

---

### **5. Identificatore esterno**

Un **identificatore esterno** (o **identificazione esterna**) viene usato quando un’entità **non può essere identificata solo con i propri attributi**, ma necessita della **relazione con un’altra entità**.  
Questo avviene nel caso di **entità deboli**, cioè entità che **dipendono da un’altra** per la loro identificazione.

#### **Definizione formale**

Un’entità è identificata esternamente quando:

- è identificata da zero o più **attributi scalari**,
    
- **e** dalla partecipazione a una o più **relazioni** in cui compare con **cardinalità (1,1)**.

#### **Rappresentazione grafica**

Una **linea di identificazione** taglia:

- gli attributi coinvolti,
    
- e la linea che collega la relazione identificante,  
    terminando con un **pallino pieno**.

#### **Esempio**

Un _lavoro_ può essere identificato dal **dipartimento** e dall’**impiegato** che lo svolge:

```
IMPIEGATO ──< LAVORO >── DIPARTIMENTO
 (1,1)                      (1,n)
```

L’entità _Lavoro_ è **debole**, perché esiste solo se associata a un _Impiegato_ e a un _Dipartimento_.  
I suoi identificatori sono:  
**(Impiegato, Dipartimento)** + eventuali attributi interni.

---

### **6. Identificatori esterni multipli**

È possibile che un’identificazione esterna coinvolga un’altra entità **anch’essa debole**, purché non si creino **cicli di dipendenze**.

#### **Esempio**

```
TRENO ──< POSTO >── VETTURA
(1,n)             (1,n)
```

- _Posto_ è identificato da _Vettura_ e _Treno_.
    
- _Vettura_ è a sua volta identificata da _Treno_.  
    Tuttavia non devono esistere **cicli** come _Treno → Posto → Vettura → Treno_, perché renderebbero il modello ambiguo.

---

### **7. Generalizzazioni e specializzazioni**

Le **gerarchie** (o **generalizzazioni/specializzazioni**) descrivono **relazioni logiche di tipo padre–figlio** tra entità.  
Si usano per modellare **categorie e sotto-categorie** di oggetti, quando le entità figlie condividono parte delle proprietà del padre ma possiedono anche **caratteristiche specifiche**.

#### **Definizione**

- L’entità **E** è la **generalizzazione** delle entità **E₁, E₂, …, Eₙ**.
    
- Le entità **E₁, …, Eₙ** sono **specializzazioni** di **E**.

Ogni occorrenza di una **figlia** è anche occorrenza della **padre**, e **eredita tutte le sue proprietà**:

- attributi,
    
- identificatori,
    
- relazioni,
    
- ulteriori gerarchie.

---

### **8. Rappresentazione grafica delle gerarchie**

Le generalizzazioni/specializzazioni si rappresentano con **frecce** che collegano le entità figlie alla loro entità padre.

Esempio:

```
            PERSONA
             /    \
     LAVORATORE   STUDENTE
                     / |  \
             I_ANNO II_ANNO FUORICORSO
```

Tutte le proprietà di _PERSONA_ (es. _codice fiscale_, _nome_, _cognome_) vengono **ereditate automaticamente** dalle entità figlie.

---

### **9. Tipologie di gerarchie**

Le gerarchie si classificano in base a due **dimensioni ortogonali**:

1. **Totalità** → _totale_ o _parziale_
    
2. **Esclusività** → _esclusiva_ o _sovrapposta_

---

#### **(a) Totale vs Parziale**

- **Totale:** ogni occorrenza della **padre** appartiene _ad almeno una_ figlia.  
    Esempio:  
    _Studente_ → _I anno, II anno, III anno, fuoricorso_  
    (tutti gli studenti rientrano in una di queste categorie).
    
- **Parziale:** alcune occorrenze della **padre** possono non appartenere a nessuna figlia.  
    Esempio:  
    _Persona_ → _Lavoratore_, _Studente_  
    (non tutte le persone sono lavoratori o studenti).

---

#### **(b) Esclusiva vs Sovrapposta**

- **Esclusiva:** ogni occorrenza della **padre** può appartenere _al più_ a una figlia.  
    Esempio:  
    _Studente_ → _I anno, II anno, III anno, fuoricorso_  
    (uno studente appartiene a un solo gruppo).
    
- **Sovrapposta:** una stessa occorrenza della **padre** può appartenere _a più figlie_.  
    Esempio:  
    _Persona_ → _Lavoratore_ e _Studente_  
    (una persona può essere entrambe le cose).

---

### **10. Le quattro combinazioni possibili**

|**Tipo di gerarchia**|**Descrizione**|
|---|---|
|**(totale, esclusiva)**|Ogni occorrenza della padre appartiene _esattamente_ a una figlia.|
|**(totale, sovrapposta)**|Ogni occorrenza della padre appartiene _ad almeno una_ figlia.|
|**(parziale, esclusiva)**|Ogni occorrenza della padre appartiene _al più_ a una figlia.|
|**(parziale, sovrapposta)**|Ogni occorrenza della padre può appartenere a _nessuna, una o più_ figlie.|

---

### **11. Rappresentazione grafica delle proprietà**

Le proprietà di una generalizzazione/specializzazione vengono indicate con **etichette** sulla freccia che unisce le entità figlie alla padre.

Esempio:

```
(p,s) → parziale e sovrapposta  
(t,e) → totale e esclusiva  
(t,s) → totale e sovrapposta  
(p,e) → parziale e esclusiva
```

Esempio applicativo:

```
             PERSONA (p,s)
              /     \
       LAVORATORE  STUDENTE
                    / |  \
           I_ANNO II_ANNO FUORICORSO (t,e)
```

---

### **12. Regole sugli identificatori**

Solo alcuni elementi possono far parte di un identificatore:

- **Attributi scalari** dell’entità (con cardinalità **(1,1)**);
    
- **Relazioni** in cui l’entità partecipa con **cardinalità (1,1)**.

Non è ammesso includere:

- attributi con cardinalità **(0,1)** o **(1,n)**;
    
- relazioni opzionali o a molteplicità variabile.

---

### **13. Sintesi finale**

**Abbiamo visto:**

- come si definiscono e rappresentano gli **identificatori** interni ed esterni;
    
- il concetto di **entità debole** e la sua dipendenza da un’altra entità;
    
- la struttura e il significato delle **gerarchie di generalizzazione e specializzazione**;
    
- le **quattro combinazioni possibili** tra totalità e esclusività.

**Ricorda:**

> Ogni entità deve avere almeno un identificatore.  
> Le gerarchie permettono di modellare in modo ordinato e coerente le relazioni di ereditarietà tra concetti, migliorando la chiarezza e la flessibilità dello schema E–R.

---


![](imgs/Pasted%20image%2020251125050418.png)
