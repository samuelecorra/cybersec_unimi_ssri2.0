# **M4 UD2 Lezione 1 - Modello E-R - Entità, relazioni e attributi**

### **1. Introduzione**

Il **modello Entità–Relazione (E–R)** è il modello concettuale più utilizzato per rappresentare, in modo formale e visivo, la **struttura logica delle informazioni** di una base di dati.  
Offre un insieme di **costrutti grafici e concettuali** che permettono di descrivere come i dati sono organizzati e come si relazionano tra loro.  
Ogni elemento del modello — entità, relazioni, attributi, cardinalità, identificatori e gerarchie — possiede una **rappresentazione grafica standardizzata**, che consente di costruire uno **schema E–R** facilmente interpretabile.

Uno schema E–R può essere espresso tramite:

- **diagramma grafico**, per la rappresentazione visiva;
    
- **frasi di specifica e di vincolo**, per la descrizione testuale e formale delle regole applicative.

---

### **2. Costrutti fondamentali del modello E-R**

Il modello Entità–Relazione comprende i seguenti costrutti principali:

- **Entità**
    
- **Relazione (o associazione)**
    
- **Attributi**
    
    - semplici
        
    - composti
    
- **Cardinalità**
    
    - di relazione
        
    - di attributo
    
- **Identificatori**
    
    - interni
        
    - esterni
    
- **Generalizzazione/Specializzazione**

Questi elementi costituiscono la **base semantica** per la progettazione concettuale di qualunque base di dati.

---

### **3. Entità**

#### **Definizione**

Un’**entità** rappresenta una **classe di oggetti** con caratteristiche comuni, dotata di **esistenza autonoma** rispetto all’applicazione in esame.  
Un’entità può corrispondere a concetti:

- **materiali**, come _persona_, _dipartimento_, _casa_;
    
- **immateriali**, come _conto corrente_, _esame_, _corso_.

Le **occorrenze** di un’entità sono le **istanze concrete** di tali oggetti.  
Esempi:

- Entità _Persona_: occorrenze → _Rossi_, _Bianchi_, _Verdi_.
    
- Entità _Dipartimento_: occorrenze → _Informatica_, _Fisica_, _Matematica_.

> L’occorrenza di un’entità **non è il valore che identifica l’oggetto**, ma **l’oggetto stesso**.

#### **Rappresentazione grafica**

Le entità vengono rappresentate con un **rettangolo** contenente il nome dell’entità, scritto in maiuscolo o in stampatello per chiarezza.

Esempio:

```
+----------------+
|   PERSONA      |
+----------------+

+----------------+
|   DIPARTIMENTO |
+----------------+
```

---

### **4. Relazioni**

#### **Definizione**

Una **relazione** (o **associazione**) rappresenta un **legame logico e significativo** tra due o più entità.  
Ogni **occorrenza di relazione** è una _n-upla_ composta dalle occorrenze delle entità coinvolte.  
In altre parole, una relazione collega **oggetti concreti** appartenenti a entità diverse.

#### **Esempi**

- Relazione binaria:  
    $$  
    \text{Residenza(Persona, Città)} \implies (\text{Rossi, Milano})  
    $$  
    Una persona _Rossi_ risiede nella città _Milano_.
    
- Relazione ternaria:  
    $$  
    \text{Mutuo(Banca, Persona, Casa)} \implies (\text{Banca d’Italia, Rossi, Via Verdi 1})  
    $$

#### **Rappresentazione grafica**

Le relazioni si rappresentano con un **rombo**, contenente il nome della relazione, collegato con **linee** alle entità partecipanti.

Esempio:

```
PERSONA ──< RESIDENZA >── CITTÀ
BANCA ──< MUTUO >── PERSONA ──< MUTUO >── CASA
```

#### **Più relazioni tra le stesse entità**

Due entità possono essere collegate da **più relazioni distinte**, ognuna con un significato diverso.  
Esempio:

```
PERSONA ──< RESIDENZA >── CITTÀ
PERSONA ──< SEDE_LAVORO >── CITTÀ
```

---

### **5. Relazioni ricorsive (autorelazioni)**

Una **relazione ricorsiva** collega **occorrenze della stessa entità**.  
In questi casi, le linee del diagramma vengono etichettate per indicare i **ruoli** delle occorrenze, specialmente se la relazione non è simmetrica.

#### **Esempi**

- Relazione _amicizia(persona, persona)_  
    (può essere simmetrica)
    
- Relazione _ha(persona, persona)_  
    (es. _persona_ “ha” _figlio_)

Rappresentazione:

```
+---------+
| PERSONA |
+---------+
   ^     \
   |      \
   |       v
   └─< AMICIZIA >─┘
```

---

### **6. Relazioni e prodotto cartesiano**

Nel modello E-R, una relazione è concettualmente assimilabile a una **relazione matematica**:  
essa rappresenta un **sottoinsieme del prodotto cartesiano** delle entità coinvolte.

Se abbiamo due entità:

$$  
\text{Persona} = {p_1, p_2, p_3, p_4, p_5} \  
\text{Città} = {x, y, z, w}  
$$

La relazione **Residenza(Persona, Città)** è un sottoinsieme di:

$$  
\text{Persona} \times \text{Città}  
$$

E contiene solo le coppie effettivamente esistenti (es. $(p_1, x)$, $(p_2, y)$, …).

> In una relazione **non possono esistere n-uple ripetute**, poiché ogni legame logico è unico.

---

### **7. Attributi**

#### **Definizione**

Gli **attributi** descrivono le **proprietà elementari** di entità o relazioni, rilevanti per l’applicazione.  
Ogni attributo associa a ciascuna occorrenza di entità o relazione **un valore** appartenente a un **dominio**, cioè l’insieme dei valori ammissibili.

#### **Esempio**

L’entità _Persona_ può avere:  
$$  
\text{Età} \in [0, 120]  
$$  
cioè un attributo _Età_ con dominio “numeri interi da 0 a 120”.

#### **Rappresentazione grafica**

Gli attributi si rappresentano con **cerchi (pallini)** collegati all’entità o relazione corrispondente.

Esempio:

```
STUDENTE
   |
   |── Matricola
   |── Nome
   |── Cognome
```

---

### **8. Attributi composti**

Un **attributo composto** raggruppa **sottoattributi** che condividono affinità semantiche.  
Serve a rappresentare strutture più dettagliate, mantenendo la coerenza del modello.

#### **Esempio**

Attributo composto: _Indirizzo_, composto da:  
$$  
\text{Indirizzo} = {\text{Via}, \text{Numero}, \text{Città}}  
$$

#### **Rappresentazione grafica**

Un cerchio con all’interno il nome dell’attributo composto, da cui partono i cerchi dei sottoattributi:

```
STUDENTE
   |
   |── Indirizzo
         ├── Via
         ├── Numero
         └── Città
```

---

### **9. Entità, relazioni e attributi: distinzione concettuale**

Non esistono regole assolute per stabilire se un concetto debba essere modellato come **entità**, **relazione** o **attributo**.  
La scelta dipende dal **contesto applicativo** e dall’**importanza semantica** del concetto.

|**Tipo di concetto**|**Quando usarlo**|
|---|---|
|**Entità**|Se rappresenta un concetto autonomo e significativo.|
|**Attributo**|Se descrive una proprietà semplice o marginale di un’entità o relazione.|
|**Relazione**|Se rappresenta un legame logico o funzionale tra entità.|

Esempio pratico:

- “Studente” → **entità**
    
- “Nome” → **attributo**
    
- “Sostenere esame” → **relazione** tra _studente_ e _corso_

---

### **10. Sintesi finale**

**Abbiamo visto:**

- i **costrutti fondamentali del modello E-R**: entità, relazioni e attributi;
    
- il significato di **occorrenza**, **dominio**, **prodotto cartesiano**;
    
- la **rappresentazione grafica** dei costrutti e i criteri di distinzione tra essi.

**Ricorda:**

> Entità, relazioni e attributi non sono fatti assoluti, ma **dipendono dal contesto** dell’applicazione.  
> Il valore del modello E–R sta nella sua capacità di rappresentare in modo chiaro e rigoroso la realtà informativa che il database dovrà gestire.

---


![](imgs/Pasted%20image%2020251125050216.png)

