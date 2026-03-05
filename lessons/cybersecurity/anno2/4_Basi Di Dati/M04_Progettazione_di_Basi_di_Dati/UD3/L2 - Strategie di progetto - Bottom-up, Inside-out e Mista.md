# **M4 UD3 Lezione 2 - Strategie di progetto - Bottom-up, Inside-out e Mista**

### **1. Introduzione**

Nella **progettazione concettuale** di una base di dati, le strategie non si limitano al solo approccio **top-down** (visto nella lezione precedente).  
Esistono infatti anche le strategie **bottom-up**, **inside-out** e **mista**, ciascuna con caratteristiche, vantaggi e limiti propri.

Queste strategie permettono di affrontare **diversi contesti progettuali**, a seconda che si parta da una visione globale del sistema o, al contrario, da porzioni locali e dettagliate del dominio applicativo.

---

### **2. Strategia Bottom-up**

#### **Definizione**

La strategia **bottom-up** (dal basso verso l’alto) parte da **sotto-componenti elementari** del sistema informativo, ricavate dalle specifiche, e le combina progressivamente per ottenere uno **schema concettuale completo**.

Il processo consiste quindi nel:

1. **Scomporre le specifiche** in componenti più piccole, ognuna delle quali descrive una parte autonoma del dominio.
    
2. **Rappresentare ciascuna componente** mediante uno schema concettuale parziale.
    
3. **Integrare i sotto-schemi** ottenuti fino a costruire lo **schema finale completo**.

#### **Rappresentazione del processo**

$$  
\text{Specifiche}  
\Rightarrow 
\text{Sotto-componenti}  
\Rightarrow
\text{Sotto-schemi}  
\Rightarrow
\text{Integrazione}  
\Rightarrow
\text{Schema finale}  
$$

---

### **3. Primitive di trasformazione Bottom-up**

Le **primitive di trasformazione** rappresentano le operazioni elementari che permettono di costruire, passo dopo passo, lo schema concettuale complessivo.

|**Sigla**|**Descrizione**|
|---|---|
|**T1**|Generazione di entità|
|**T2**|Generazione di relazione|
|**T3**|Generazione di generalizzazione|
|**T4**|Aggregazione di attributi su entità|
|**T5**|Aggregazione di attributi su relazione|

---

### **4. T1 – Generazione di entità**

Si individua, all’interno delle specifiche, una **classe di oggetti** con proprietà comuni e significato autonomo.  
Questa classe diventa una **nuova entità** dello schema.

#### **Esempio**

Dalle specifiche si identifica l’entità **Docente**, che rappresenta una persona incaricata di tenere corsi.

$$  
\text{DOCENTE}  
$$

---

### **5. T2 – Generazione di relazione**

Si riconosce un **legame logico** tra due o più entità, e lo si rappresenta con una **relazione**.

#### **Esempio**

Si introduce la relazione _Tenere_ fra le entità _Docente_ e _Corso_:

$$  
\text{DOCENTE} \xleftrightarrow{\text{TENERE}} \text{CORSO}  
$$

---

### **6. T3 – Generazione di generalizzazione**

Si individua, tra le entità, un **rapporto gerarchico** di tipo _padre–figlio_, in cui un’entità rappresenta una **generalizzazione** di altre.

#### **Esempio**

Dalle specifiche si può ricavare che _Personale_ è una generalizzazione di _Dipendente_ e _Consulente_.

$$  
\text{PERSONALE}  
\Rightarrow  
\begin{cases}  
\text{DIPENDENTE} \  
\text{CONSULENTE}  
\end{cases}  
$$

---

### **7. T4 – Aggregazione di attributi su entità**

Un insieme di attributi che descrivono un oggetto può essere **aggregato** per formare una nuova entità coerente.

#### **Esempio**

Dagli attributi:  
$$  
{\text{Matricola}, \text{Nome}, \text{Cognome}}  
$$  
si identifica la nuova entità:  
$$  
\text{STUDENTE}  
$$

Rappresentazione grafica:

```
STUDENTE
 ├── Matricola
 ├── Nome
 └── Cognome
```

---

### **8. T5 – Aggregazione di attributi su relazioni**

Allo stesso modo, un insieme di attributi che descrivono un legame può essere aggregato per formare una **relazione**.

#### **Esempio**

Gli attributi _Voto_ e _Data_ possono essere raccolti come relazione _Esame_ che collega _Studente_ e _Corso_:

$$  
\text{STUDENTE} \xleftrightarrow{\text{ESAME(Voto, Data)}} \text{CORSO}  
$$

---

### **9. Vantaggi e svantaggi della strategia Bottom-up**

#### **Vantaggi**

- Si adatta bene alla **decomposizione del problema** in parti indipendenti.
    
- Ogni componente può essere progettata da **gruppi di lavoro diversi**, favorendo la collaborazione su **progetti complessi**.
    
- Permette di **progredire gradualmente**, anche quando non si dispone di una visione globale iniziale del sistema.

#### **Svantaggi**

- Richiede una **fase di integrazione** tra schemi diversi, che può risultare **difficile e soggetta a conflitti** (nomi, significati, vincoli).
    
- Ogni progettista può avere una **visione parziale** del sistema, con rischio di **incoerenze semantiche** tra i sotto-schemi.

---

### **10. Strategia Inside-out**

La strategia **inside-out** è un **caso particolare** della strategia bottom-up.  
Consiste nel partire da **alcuni concetti centrali** del dominio e procedere poi per **espansione graduale (“a macchia d’olio”)**, aggiungendo i concetti più vicini a quelli già modellati.

#### **Fasi**

1. Si individuano i concetti principali del sistema (le “entità cuore”).
    
2. Si rappresentano i concetti strettamente collegati.
    
3. Si prosegue espandendo progressivamente lo schema fino a includere tutte le parti rilevanti.

---

### **11. Inside-out – Vantaggi e svantaggi**

#### **Vantaggi**

- Evita la complessa **integrazione di schemi** separati, tipica del bottom-up.
    
- Permette di **sviluppare lo schema in modo naturale**, a partire dal nucleo più rilevante del dominio.

#### **Svantaggi**

- Richiede di **esaminare continuamente tutte le specifiche**, per individuare i concetti non ancora rappresentati.
    
- Non consente di procedere **per livelli di astrazione**, come nel top-down.
    
- Può risultare difficile da gestire in domini molto vasti.

---

### **12. Strategia Mista**

La strategia **mista** combina gli approcci **top-down** e **bottom-up**, cercando di sfruttarne i vantaggi e minimizzarne i limiti.

#### **Definizione**

- Si **suddividono i requisiti** in **componenti indipendenti**, come nel bottom-up.
    
- Si definisce un **“schema scheletro”**, che rappresenta in modo astratto i concetti principali dell’applicazione, come nel top-down.
    
- I sotto-schemi vengono poi **sviluppati e integrati** progressivamente.

---

### **13. Vantaggi della strategia mista**

- Combina i **vantaggi della decomposizione** (bottom-up) e della **visione globale** (top-down).
    
- Facilita lo sviluppo di **applicazioni complesse**, dove è necessario iniziare il progetto anche se non tutti i dati o i requisiti sono ancora noti.
    
- Lo schema “scheletro” fornisce un **punto di riferimento unificato** per l’integrazione dei vari sotto-schemi.

> È la **strategia più usata nella pratica**, soprattutto per sistemi informativi di grandi dimensioni.

---

### **14. Sintesi finale**

**Abbiamo visto:**

- le tre strategie di progettazione concettuale:
    
    - **Bottom-up** → dai dettagli verso la visione d’insieme;
        
    - **Inside-out** → dall’interno verso l’esterno, espandendo progressivamente;
        
    - **Mista** → combinazione dei due approcci precedenti;
    
- le **primitive di trasformazione bottom-up** e il loro ruolo;
    
- i **vantaggi e limiti** di ciascun metodo.

**Ricorda:**

> Nella pratica, la **strategia mista** è la più efficace:  
> permette di partire da una struttura generale e, al tempo stesso, di sviluppare i dettagli in modo incrementale, mantenendo coerenza e flessibilità durante l’intero processo di progettazione concettuale.

---


![](imgs/Pasted%20image%2020251125050721.png)

