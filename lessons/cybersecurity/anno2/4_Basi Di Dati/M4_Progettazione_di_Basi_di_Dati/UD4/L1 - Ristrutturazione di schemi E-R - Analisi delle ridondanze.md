# **M4 UD4 Lezione 1 - Ristrutturazione di schemi E-R - Analisi delle ridondanze**

### **1. Introduzione alla progettazione logica**

La **progettazione logica** è la fase in cui lo **schema concettuale E–R**, ottenuto durante la progettazione concettuale, viene **tradotto** in uno **schema logico** compatibile con il modello del DBMS scelto (solitamente il **modello relazionale**).  
L’obiettivo è produrre una rappresentazione che descriva **tutte le informazioni rilevanti** in modo **corretto ed efficiente**.

---

### **2. Le fasi della progettazione logica**

La progettazione logica è articolata in due fasi principali:

1. **Ristrutturazione dello schema E–R**
    
    - Serve per **ottimizzare e semplificare** lo schema concettuale.
        
    - Rende più efficienti le operazioni previste.
        
    - Traduce eventuali concetti che **non possono essere rappresentati direttamente** nel modello logico.
        
    - È **indipendente dal modello logico** scelto.
    
2. **Traduzione verso il modello logico**
    
    - Trasforma lo schema E–R ristrutturato in uno **schema logico relazionale**.
        
    - Questa fase è **dipendente dal modello** scelto (relazionale, gerarchico, a oggetti, ecc.).
        
    - Può includere ulteriori ottimizzazioni in base alle **caratteristiche specifiche del DBMS**.

---

### **3. Struttura del processo di progettazione logica**

$$  
\text{Schema E–R}  
\xRightarrow[\text{ottimizzazione}]{\text{Ristrutturazione}}  
\text{Schema E–R ristrutturato}  
\xRightarrow[\text{traduzione specifica}]{\text{Modello logico}}  
\text{Schema logico finale}  
$$

Durante il processo si considerano anche:

- i **vincoli di integrità** da trasferire nel modello logico;
    
- la **documentazione di supporto** dello schema;
    
- le **caratteristiche del carico applicativo** (cioè le operazioni più frequenti o costose).

---

### **4. Fasi della ristrutturazione dello schema E–R**

La ristrutturazione di uno schema E–R comprende:

1. **Analisi delle ridondanze**
    
2. **Eliminazione delle gerarchie**
    
3. **Partizionamento o accorpamento** di entità e relazioni
    
4. **Scelta degli identificatori primari**

Queste fasi migliorano la **chiarezza, efficienza e manutenibilità** del modello prima della traduzione logica.

---

### **5. Analisi delle ridondanze**

#### **Definizione**

Un’informazione è **ridondante** quando può essere **derivata** da altre informazioni già presenti nello schema.  
Le ridondanze possono riguardare **attributi** o **relazioni** e vanno individuate per evitare duplicazioni inutili.

#### **Tipologie principali**

- **Attributi derivabili** da:
    
    1. altri attributi della **stessa entità o relazione**;
        
    2. attributi di **altre entità o relazioni**;
        
    3. **operazioni di conteggio** di occorrenze.
    
- **Relazioni derivabili** da:
    
    - **composizione** di altre relazioni, spesso in presenza di **cicli** nello schema.

---

### **6. Esempi di ridondanza – Attributi della stessa entità**

#### **Esempio 1**

Attributi derivabili da altri **della stessa entità o relazione**:

$$  
\text{Totale} = \text{Imponibile} + \text{IVA}  
$$

Schema:

```
FATTURA
 ├── Imponibile
 ├── IVA
 └── Totale (derivato)
```

In questo caso, l’attributo _Totale_ è ridondante, poiché può essere calcolato in ogni momento a partire dagli altri due valori.

---

### **7. Esempi di ridondanza – Attributi di altre entità o relazioni**

#### **Esempio 2**

Un attributo può essere derivato da un **collegamento tra entità diverse**.

Esempio:  
Il _totale di un acquisto_ può essere derivato dal _prezzo del prodotto_, attraverso la relazione _composizione_.

Schema:

```
PRODOTTO (prezzo)
   │
 (1,n)
   │
COMPOSIZIONE
   │
 (1,n)
   │
ACQUISTO (totale derivato)
```

Dunque, l’attributo _totale_ di _Acquisto_ può essere calcolato dalla somma dei prezzi dei prodotti coinvolti nella relazione _Composizione_.

---

### **8. Esempi di ridondanza – Attributi derivabili da conteggio**

#### **Esempio 3**

Un attributo può essere derivato **contando le occorrenze** di una relazione.

Esempio:  
Il numero di abitanti di una città può essere calcolato **contando le persone** che risiedono in essa.

Schema:

```
CITTÀ (numero_abitanti derivato)
   │
 (1,n)
   │
RESIDENZA
   │
 (1,1)
   │
PERSONA (c.f.)
```

Quindi l’attributo _numero_abitanti_ è ridondante, poiché può essere ottenuto dal conteggio delle relazioni _residenza_.

---

### **9. Esempi di ridondanza – Relazioni derivabili**

#### **Esempio 4**

Una **relazione** può essere derivata dalla **composizione di altre relazioni**, in presenza di **cicli** nello schema.

Esempio:  
La relazione _docenza_ tra _professore_ e _corso_ può essere derivata dalla combinazione delle relazioni _insegnamento_ e _frequenza_.

Schema:

```
PROFESSORE ──< INSEGNAMENTO >── CORSO ──< FREQUENZA >── STUDENTE
           \_____________________________________________/
                          DOCENZA (derivata)
```

La relazione _docenza_ non aggiunge nuove informazioni e può essere omessa per evitare ridondanza.

---

### **10. Vantaggi e svantaggi della ridondanza**

#### **Vantaggi**

- **Riduce gli accessi al database**, evitando calcoli o join complessi.
    
- **Aumenta l’efficienza** delle interrogazioni che richiedono dati derivati frequentemente.

#### **Svantaggi**

- **Occupa più memoria**, anche se di solito in misura non significativa.
    
- **Richiede aggiornamenti aggiuntivi** per mantenere i dati derivati coerenti con quelli originali.
    
- **Introduce rischio di inconsistenza**, se i dati base cambiano e quelli derivati non vengono aggiornati correttamente.

---

### **11. Decisione di mantenere o eliminare una ridondanza**

La scelta di **mantenere o eliminare** una ridondanza dipende da:

- il **carico applicativo**, cioè la frequenza e il tipo di operazioni previste;
    
- il **costo computazionale** per calcolare i dati derivati;
    
- lo **spazio di memoria** disponibile e l’impatto della duplicazione.

> Non sempre i cicli nello schema corrispondono a vere ridondanze,  
> e non tutte le ridondanze devono essere eliminate:  
> talvolta possono essere mantenute per **motivi di efficienza**.

---

### **12. Sintesi finale**

**Abbiamo visto:**

- le **due fasi principali** della progettazione logica (ristrutturazione e traduzione);
    
- la **struttura della ristrutturazione dello schema E–R**;
    
- le **diverse tipologie di ridondanza** e come individuarle;
    
- i **vantaggi e svantaggi** del mantenerle o eliminarle.

**In sintesi:**

> L’analisi delle ridondanze è un passaggio essenziale per garantire che lo schema E–R sia **pulito, efficiente e coerente**, pronto per essere tradotto in uno **schema logico ottimizzato**.

---


![](imgs/Pasted%20image%2020251125050757.png)

