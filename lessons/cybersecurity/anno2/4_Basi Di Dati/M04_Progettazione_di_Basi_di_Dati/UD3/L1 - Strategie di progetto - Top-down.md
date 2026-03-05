# **M4 UD3 Lezione 1 - Strategie di progetto - Top-down**

### **1. Introduzione**

Nel processo di **progettazione concettuale** di una base di dati, esistono varie **strategie di sviluppo dello schema E–R**.  
Come in ogni progetto di ingegnerizzazione complesso, possiamo adottare approcci diversi per costruire lo schema a partire dalle specifiche dei requisiti.  
Le principali strategie sono:

- **Top-down**
    
- **Bottom-up**
    
- **Inside-out**
    
- **Mista**

In questa lezione analizziamo in dettaglio la **strategia top-down**, una delle più classiche e utilizzate.

---

### **2. Strategia Top-down**

#### **Definizione**

La strategia **top-down** (dall’alto verso il basso) parte da una **visione globale e astratta** del sistema informativo, per poi procedere con **raffinamenti successivi** che introducono via via maggior dettaglio.  
Il processo inizia dalle **specifiche generali** e si conclude con uno **schema concettuale completo**.

#### **Fasi operative**

1. **Dalle specifiche → schema iniziale**  
    Si costruisce un primo schema contenente pochi concetti molto generali.
    
2. **Schema iniziale → schemi intermedi**  
    Si applicano una serie di **trasformazioni elementari (primitive)** per descrivere meglio ciascun concetto.
    
3. **Schema intermedio → schema finale**  
    Dopo vari passaggi di raffinamento, si ottiene lo **schema concettuale completo**, che rappresenta fedelmente i requisiti informativi.

#### **Rappresentazione del processo**

$$  
\text{Specifiche} ;\Rightarrow; \text{Schema iniziale} ;\Rightarrow; \text{Schema intermedio} ;\Rightarrow; \text{Schema finale}  
$$

Ogni passaggio comporta un **raffinamento concettuale**.

---

### **3. Primitive di trasformazione top-down**

Le **primitive di trasformazione** sono le operazioni elementari che consentono di passare da uno schema meno dettagliato a uno più completo.  
Ogni primitiva opera su un singolo concetto (entità o relazione) per renderlo più articolato o specifico.

|**Sigla**|**Descrizione**|
|---|---|
|**T1**|Da entità a relazione tra entità|
|**T2**|Da entità a generalizzazione|
|**T3**|Da relazione a insieme di relazioni|
|**T4**|Da relazione a entità con relazioni|
|**T5**|Introduzione di attributi su entità|
|**T6**|Introduzione di attributi su relazioni|

Vediamole in dettaglio.

---

### **4. T1 – Da entità a relazione tra entità**

Una singola entità può essere sostituita da **due entità collegate tra loro** da una **relazione**.  
Questo avviene quando si vuole distinguere tra un concetto generale e le sue **occorrenze specifiche**.

#### **Esempio**

L’entità _Corso_ può essere sostituita da:

- _TipoCorso_ → descrive il corso in generale;
    
- _EdizioneCorso_ → rappresenta l’erogazione del corso in un determinato anno accademico;  
    collegate dalla relazione _Tipologia_.

$$  
\text{CORSO} \Rightarrow \text{TIPO\_CORSO} \xleftrightarrow{\text{TIPOLOGIA}} \text{EDIZIONE\_CORSO}  
$$

---

### **5. T2 – Da entità a generalizzazione**

Un’entità può essere **scomposta in più sotto–entità** distinte, quando le sue occorrenze si suddividono in categorie con proprietà specifiche.

#### **Esempio**

L’entità _Impiegato_ può essere generalizzata in:

- _Full-time_
    
- _Part-time_

$$  
\text{IMPIEGATO} \Rightarrow \text{FULL\_TIME}, \text{PART\_TIME}  
$$

Questa trasformazione introduce una **gerarchia di specializzazione**, già vista nelle lezioni precedenti.

---

### **6. T3 – Da relazione a insieme di relazioni**

Una relazione può essere **sostituita da più relazioni** più specifiche, ognuna delle quali rappresenta una particolare situazione.

#### **Esempio**

La relazione _Docenza(Docente, Corso)_ può essere sostituita da:

- _DocenzaCorrente_ (nell’anno accademico in corso)
    
- _DocenzaPassata_ (negli anni precedenti)

$$  
\text{DOCENZA(Docente, Corso)} \Rightarrow  
\begin{cases}  
\text{DOCENZA\_CORRENTE(Docente, Corso)} \\\\  
\text{DOCENZA\_PASSATA(Docente, Corso)}  
\end{cases}  
$$

---

### **7. T4 – Da relazione a entità con relazioni**

Una relazione può diventare una **nuova entità autonoma**, se il concetto che rappresenta ha **significato indipendente** e **attributi propri**.

#### **Esempio**

La relazione _Contratto_ tra _Consulente_ e _Azienda_ può essere trasformata nell’entità _Contratto_, in relazione sia con _Consulente_ che con _Azienda_.

$$  
\text{CONSULENTE} \xleftrightarrow{\text{CONTRATTO}} \text{AZIENDA}  
\Rightarrow
\text{CONSULENTE} \xleftrightarrow{\text{stipula}} \text{CONTRATTO} \xleftrightarrow{\text{riguarda}} \text{AZIENDA}  
$$

---

### **8. T5 – Introduzione di attributi su entità**

Questa trasformazione prevede l’aggiunta di **attributi descrittivi** a un’entità, per arricchirne le informazioni.

#### **Esempio**

L’entità _Studente_ può essere arricchita con gli attributi:  
$$  
{\text{Matricola}, \text{Nome}, \text{Cognome}}  
$$

Rappresentazione grafica:

```
STUDENTE
  ├── Matricola
  ├── Nome
  └── Cognome
```

---

### **9. T6 – Introduzione di attributi su relazioni**

Analogamente, è possibile **aggiungere attributi a una relazione**, per rappresentare proprietà che riguardano il legame stesso tra le entità.

#### **Esempio**

La relazione _Esame(Studente, Corso)_ può avere attributi aggiuntivi:  
$$  
{\text{Data}, \text{Voto}}  
$$

Rappresentazione:

```
STUDENTE ──< ESAME >── CORSO
                 ├── Data
                 └── Voto
```

---

### **10. Vantaggi e svantaggi della strategia top-down**

#### **Vantaggi**

- Tutti gli aspetti presenti nello **schema finale** sono già contenuti, almeno implicitamente, nello schema iniziale.
    
- Il progettista può **descrivere l’intero sistema in modo astratto**, concentrandosi poi sui singoli dettagli passo dopo passo.
    
- Consente di **mantenere coerenza logica e semantica** durante tutto il processo di raffinamento.

#### **Svantaggi**

- È applicabile solo se si dispone **fin dall’inizio di una visione globale** del dominio applicativo.
    
- Non è adatta a sistemi molto complessi o a progetti in cui i requisiti vengono raccolti in modo **incrementale** o **parziale**.
    
- Può risultare inefficiente se si devono continuamente ridefinire concetti già raffinati.

---

### **11. Sintesi finale**

**Abbiamo visto:**

- la **strategia top-down** nella progettazione concettuale;
    
- le **primitive di trasformazione** (da T1 a T6) che consentono il passaggio da concetti generali a dettagli specifici;
    
- i **vantaggi e limiti** di questo approccio.

**In sintesi:**

> La strategia top-down permette di costruire uno schema E–R partendo da una visione astratta e globale del dominio, raffinata progressivamente attraverso trasformazioni controllate.  
> È un metodo efficace per progetti di piccole o medie dimensioni, dove i requisiti sono chiari e stabili sin dall’inizio.

---


![](imgs/Pasted%20image%2020251125050623.png)

