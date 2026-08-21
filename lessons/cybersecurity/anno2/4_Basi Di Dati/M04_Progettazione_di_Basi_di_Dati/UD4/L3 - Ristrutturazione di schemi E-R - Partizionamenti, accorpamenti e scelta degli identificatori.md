# **M4 UD4 Lezione 3 - Ristrutturazione di schemi E-R - Partizionamenti, accorpamenti e scelta degli identificatori**

### **1. Introduzione**

Durante la fase di **ristrutturazione di uno schema E–R**, oltre all'analisi delle ridondanze e all'eliminazione delle gerarchie, è possibile modificare la struttura dello schema attraverso operazioni di:

1. **partizionamento di entità e relazioni**;
    
2. **accorpamento di entità e relazioni**;
    
3. **eliminazione di attributi multivalore**;
    
4. **eliminazione di attributi composti**;
    
5. **scelta degli identificatori primari**.
    

Queste trasformazioni hanno principalmente due obiettivi:

- **aumentare l'efficienza delle operazioni**, avvicinando o separando informazioni in base a come vengono utilizzate;
    
- **tradurre costrutti del modello E–R che non sono direttamente supportati dal modello relazionale**.
    

---

### **2. Le fasi della ristrutturazione**

Ricordiamo che la ristrutturazione di uno schema E–R comprende quattro fasi fondamentali:

1. **Analisi delle ridondanze**
    
2. **Eliminazione delle gerarchie**
    
3. **Partizionamento o accorpamento di entità e relazioni**
    
4. **Scelta degli identificatori primari**
    

Nella lezione precedente abbiamo analizzato l'**eliminazione delle gerarchie**.

In questa lezione ci concentriamo invece sulle ultime trasformazioni necessarie prima della traduzione dello schema E–R nel modello relazionale.

---

### **3. Partizionamento e accorpamento**

Le operazioni di **partizionamento** e **accorpamento** vengono utilizzate soprattutto per migliorare l'efficienza delle operazioni applicative.

L'idea generale è molto semplice:

- se alcune informazioni vengono utilizzate **sempre separatamente**, può essere conveniente **separarle**;
    
- se alcune informazioni vengono utilizzate **frequentemente insieme**, può essere conveniente **riunirle**.
    

---

### **Partizionamento**

Il **partizionamento** consiste nel dividere una struttura dello schema in più strutture distinte.

Può essere utilizzato per:

> separare attributi o occorrenze che vengono normalmente acceduti da operazioni differenti.

---

### **Accorpamento**

L'**accorpamento** consiste invece nel riunire più strutture dello schema in una sola.

Può essere utilizzato per:

> collocare nella stessa entità o relazione informazioni che vengono frequentemente accedute insieme.

---

### **4. Partizionamento di entità**

Il partizionamento di un'entità può essere effettuato principalmente in due modi:

1. **partizionamento orizzontale**;
    
2. **partizionamento verticale**.
    

---

### **5. Partizionamento orizzontale**

Nel **partizionamento orizzontale**, un'entità $E$ viene sostituita da due o più entità che si dividono le **istanze** dell'entità originale.

In altre parole:

> vengono divise le **occorrenze**, non gli attributi.

![](imgs/Pasted%20image%2020260821155654.png)

dove le istanze precedentemente appartenenti a $E$ vengono distribuite tra $E_1$ ed $E_2$.

---

#### **Collegamento con le gerarchie**

Il partizionamento orizzontale equivale concettualmente a sostituire un'entità con alcune sue **figlie di generalizzazione/specializzazione**.

Si sta cioè dividendo l'insieme delle occorrenze del padre in sottoinsiemi distinti.

---

#### **Esempio: personale universitario**

![](imgs/Pasted%20image%2020260821155654.png)

In questo caso:

- gli **attributi rimangono sostanzialmente gli stessi**;
    
- vengono invece separate le **istanze** in base alla loro categoria.

---

#### **Idea chiave**

> **Partizionamento orizzontale = divido le righe/istanze.**

È utile quando gruppi differenti di occorrenze vengono gestiti frequentemente da operazioni differenti.

---

### **6. Partizionamento verticale**

Nel **partizionamento verticale**, un'entità $E$ viene sostituita da due entità $E_1$ ed $E_2$ che si dividono:

- gli **attributi**;
    
- eventualmente anche le **relazioni**

dell'entità originale.

A differenza del partizionamento orizzontale, quindi:

> le stesse informazioni logiche relative a un'istanza vengono distribuite tra più entità.

---

#### **Collegamento tra le nuove entità**

Poiché $E_1$ ed $E_2$ descrivono aspetti differenti della stessa istanza originaria, le due nuove entità devono essere collegate tramite una **relazione**.

Una delle due mantiene l'**identificatore originale**.

L'altra viene invece **identificata esternamente** attraverso la relazione con l'entità che conserva l'identificatore.

![](imgs/Pasted%20image%2020260821160218.png)

---

#### **Cardinalità**

La relazione tra le due entità risultanti presenta cardinalità:

```
(1,1) — (1,1)
```

poiché ogni istanza di una parte corrisponde precisamente alla relativa istanza dell'altra parte.

---

#### **Esempio: impiegato**

![](imgs/Pasted%20image%2020260821160303.png)

---

#### **Idea chiave**

> **Partizionamento verticale = divido le colonne/attributi.**

È particolarmente utile quando gruppi diversi di attributi vengono normalmente utilizzati da operazioni differenti.

---

### **7. Differenza tra partizionamento orizzontale e verticale**

|Tipo|Cosa viene diviso?|Intuizione|
|---|---|---|
|**Orizzontale**|Le istanze dell'entità|Divido le **righe**|
|**Verticale**|Gli attributi e/o le relazioni|Divido le **colonne**|

#### **Esempio mentale**

Se abbiamo:

|   |   |   |   |   |
|---|---|---|---|---|
|matr.|nome|cognome|stipendio|livello|
|1|Mario|Rossi|2000|4|
|2|Luca|Bianchi|1800|3|
|3|Anna|Verdi|2400|5|

Con un **partizionamento orizzontale** potremmo separare:

```
Impiegati livello <= 3
```

da:

```
Impiegati livello > 3
```

Con un **partizionamento verticale**, invece, potremmo separare:

```
matr., nome, cognome
```

da:

```
matr., stipendio, livello
```

> La metafora di **righe vs colonne** è utile per ricordare la differenza concettuale.

---

### **8. Accorpamento di entità**

L'**accorpamento di entità** rappresenta sostanzialmente l'operazione opposta al partizionamento.

Due entità $E_1$ ed $E_2$ collegate da una relazione vengono sostituite da una sola entità $E$ contenente:

- gli **attributi di entrambe**;
    
- le **relazioni di entrambe**.

---

#### **Quando è possibile**

L'accorpamento considerato nella lezione riguarda entità legate da una relazione:

- **1:1**;
    
- oppure **1**.
    

Gli attributi delle due entità vengono quindi trasferiti nella nuova entità risultante.

---

#### **Esempio: persona e appartamento**

Supponiamo di avere:

```
PERSONA
- c.f.
- nome
- cognome
```

e:

```
APPARTAMENTO
- indirizzo
- interno
```

legate dalla relazione:

```
INTESTAZIONE
```

con opportune cardinalità.

Le due entità possono essere accorpate facendo confluire nell'entità risultante gli attributi di entrambe.

![](imgs/Pasted%20image%2020260821160438.png)

---

#### **Effetto sulle cardinalità**

Gli attributi provenienti dall'entità accorpata possono diventare **opzionali**, in funzione delle cardinalità presenti nello schema originale.

Per esempio, se non tutte le persone possiedono un appartamento, nell'entità risultante:

```
indirizzo
interno
```

potranno essere presenti con cardinalità minima pari a `0`.

---

#### **Motivazione**

L'accorpamento può risultare conveniente quando gli attributi appartenenti a entità differenti vengono:

> **acceduti frequentemente insieme**.

In questo modo si riduce la necessità di attraversare continuamente la relazione che collegava le due entità.

---

### **9. Partizionamento e accorpamento di relazioni**

Le operazioni di partizionamento e accorpamento non riguardano solamente le **entità**.

Possono essere applicate anche alle **relazioni**.

---

### **10. Partizionamento di relazioni**

Una relazione $R$ viene sostituita da due relazioni:

```
R1
R2
```

che collegano le **stesse entità**.

Le occorrenze della relazione originale vengono suddivise tra le due nuove relazioni.

---

#### **Quando è utile**

Il partizionamento viene utilizzato quando differenti gruppi di occorrenze della relazione vengono:

> **acceduti sempre separatamente**.

In tal caso non è necessariamente conveniente conservarli tutti all'interno della stessa relazione.

---

#### **Esempio**

Nelle slide viene considerata una relazione di **composizione** tra:

```
GIOCATORE
```

e:

```
SQUADRA
```

che può essere separata distinguendo:

- la **composizione attuale**;
    
- la **composizione passata**.

In questo modo due categorie di associazioni concettualmente differenti vengono rappresentate con due relazioni distinte.

![](imgs/Pasted%20image%2020260821160606.png)

---

### **11. Accorpamento di relazioni**

L'operazione inversa consiste nell'accorpare due relazioni:

```
R1
R2
```

in una singola relazione:

```
R
```

---

L'accorpamento può essere utilizzato quando le due relazioni:

- collegano le **stesse entità**;
    
- rappresentano due aspetti dello **stesso concetto**;
    
- hanno occorrenze che vengono **sempre accedute contemporaneamente**.

In queste condizioni può essere più conveniente rappresentare entrambe attraverso una sola relazione.

---

### **12. Partizionamento vs accorpamento**

Possiamo quindi sintetizzare il criterio generale:

|   |   |
|---|---|
|Situazione|Operazione conveniente|
|Informazioni utilizzate normalmente **separatamente**|**Partizionamento**|
|Informazioni utilizzate frequentemente **insieme**|**Accorpamento**|

> Il criterio fondamentale è quindi il **carico operativo**, cioè il modo in cui le applicazioni accedono realmente ai dati.

---

### **13. Trasformazione di concetti non supportati dal modello relazionale**

Il partizionamento e l'accorpamento non vengono utilizzati solamente per ragioni di efficienza.

Alcune trasformazioni sono necessarie perché determinati costrutti ammessi nel modello E–R:

- **non sono direttamente supportati dal modello relazionale**.

In particolare occorre gestire:

1. gli **attributi multivalore**;
    
2. gli **attributi composti**.

---

### **14. Eliminazione degli attributi multivalore**

Gli **attributi multivalore** non sono direttamente supportati dal modello relazionale.

Un attributo multivalore è un attributo che, per una singola istanza dell'entità, può assumere **più valori**.

Per esempio:

```
PERSONA
- c.f.
- nome
- cognome
- telefono (0,n)
```

Una persona può infatti possedere:

- nessun numero di telefono;
    
- un numero;
    
- più numeri.
    

Nel modello relazionale non possiamo semplicemente inserire un numero arbitrario di valori all'interno dello stesso attributo.

---

### **b) Soluzione**

Si crea una **nuova entità** contenente l'attributo multivalore.

Questa nuova entità viene collegata all'entità originale mediante una **relazione**.

Schema concettuale:

```
E
- id
- a1
- a2 (multivalore)
```

diventa:

```
E                         E2
- id                      - a2
- a1

          R
```

---

### **c) Cardinalità della relazione**

La cardinalità della nuova relazione dipende:

1. dalla **cardinalità originale dell'attributo multivalore**;
    
2. dal fatto che lo stesso valore dell'attributo possa oppure no comparire più volte.
    

Nello schema generale della lezione:

```
z = 1
```

se il valore dell'attributo multivalore **non può essere ripetuto**;

mentre:

```
z = n
```

se lo stesso valore può comparire in più occorrenze.

---

### **d) Esempio: numero di telefono**

Da:

```
PERSONA
- c.f.
- nome
- cognome
- telefono (0,n)
```

si passa a:

```
PERSONA
- c.f.
- nome
- cognome
```

collegata attraverso una relazione, per esempio:

```
RECAPITO
```

all'entità:

```
TELEFONO
- telefono
```

---

### **e) Rappresentazione grafica**

> **[PLACEHOLDER IMMAGINE — Eliminazione dell'attributo multivalore TELEFONO da PERSONA]**

---

### **f) Idea chiave**

> Un attributo che può contenere **più valori** viene trasformato in una **nuova entità** collegata all'entità originale.

Quindi:

```
attributo multivalore
        ↓
nuova entità + relazione
```

---

# **15. Eliminazione degli attributi composti**

### **a) Problema**

Anche gli **attributi composti** non sono direttamente supportati dal modello relazionale.

Un attributo composto è un attributo costituito da più componenti.

Per esempio:

```
indirizzo
├── via
├── numero
└── CAP
```

---

### **b) Due possibili soluzioni**

Per eliminare un attributo composto esistono **due strategie**.

---

### **Soluzione 1 — Considerare il composto come attributo semplice**

Si elimina la suddivisione nelle singole componenti e si mantiene soltanto l'attributo complessivo.

Per esempio:

```
INDIRIZZO
```

viene trattato come un normale attributo semplice.

Quindi:

```
indirizzo
├── via
├── numero
└── CAP
```

diventa:

```
indirizzo
```

---

### **Soluzione 2 — Eliminare il composto e mantenere le componenti**

È possibile fare l'opposto:

- eliminare l'attributo composto;
    
- mantenere direttamente le sue componenti come attributi semplici dell'entità.
    

Quindi:

```
indirizzo
├── via
├── numero
└── CAP
```

diventa:

```
via
numero
CAP
```

---

### **c) Schema generale**

Se abbiamo:

```
a
├── a1
├── a2
└── a3
```

possiamo trasformarlo:

#### **Prima possibilità**

```
E
- a1
- a2
- a3
```

oppure:

#### **Seconda possibilità**

```
E
- a123
```

dove l'intero valore composto viene trattato come un unico attributo.

---

### **d) Rappresentazione grafica**

> **[PLACEHOLDER IMMAGINE — Eliminazione di un attributo composto: due possibili trasformazioni]**

---

### **e) Idea chiave**

Per un attributo composto possiamo quindi:

```
COMPOSTO
   │
   ├── mantenere il tutto come attributo semplice
   │
   └── mantenere separatamente le componenti
```

---

# **16. Scelta degli identificatori primari**

Dopo aver completato le trasformazioni dello schema è necessario scegliere, per ogni entità, un **identificatore primario**.

---

### **a) Identificatori e chiavi candidate**

Un'entità può possedere più identificatori.

Questi rappresentano le possibili **chiavi candidate**.

Occorre quindi selezionare quale di essi verrà utilizzato come:

> **identificatore primario**, cioè come futura **chiave primaria** della relazione corrispondente.

---

### **b) Identificatori non selezionati**

Gli identificatori che non vengono scelti come primari **non vengono necessariamente persi**.

Durante la successiva fase di **progettazione fisica**, possono infatti essere utilizzati come:

> **indici secondari**.

Questo permette comunque di utilizzarli per accedere efficientemente ai dati.

---

# **17. Criteri per scegliere l'identificatore primario**

Quando un'entità possiede più identificatori, è preferibile scegliere come primario un identificatore che presenti determinate caratteristiche.

---

### **1. Utilizzato frequentemente nelle operazioni**

È preferibile un identificatore che venga:

> utilizzato in molte operazioni per accedere all'entità.

Se molte interrogazioni individuano un'entità utilizzando un certo identificatore, utilizzarlo come primario può risultare conveniente.

---

### **2. Composto da pochi attributi**

È preferibile scegliere un identificatore formato da:

> **pochi attributi**.

Per esempio, tra:

```
(codice)
```

e:

```
(nome, cognome, dataNascita, città)
```

a parità delle altre condizioni, il primo è strutturalmente più semplice.

L'obiettivo è evitare identificatori primari inutilmente complessi.

---

### **3. Costituito da attributi interni**

È preferibile utilizzare identificatori costituiti solamente da:

> **attributi interni dell'entità**.

Un identificatore interno appartiene direttamente all'entità stessa.

Questo viene preferito rispetto a un **identificatore esterno**, che dipende invece dalla partecipazione dell'entità a una relazione con un'altra entità.

---

# **18. Identificatori che non possono essere primari**

Esiste inoltre una restrizione fondamentale:

> non possono essere scelti come identificatori primari identificatori contenenti attributi che possono assumere **valore nullo**.

La chiave primaria deve infatti consentire di identificare ogni istanza dell'entità.

Un attributo che può essere assente non può quindi costituire correttamente parte dell'identificatore primario.

---

# **19. Criteri riassuntivi per la scelta della chiave primaria**

Quando esistono più identificatori candidati, possiamo ricordare questa gerarchia di preferenze:

```
IDENTIFICATORE PRIMARIO IDEALE
        │
        ├── usato frequentemente
        │
        ├── composto da pochi attributi
        │
        ├── formato da attributi interni
        │
        └── nessun attributo può essere nullo
```

---

# **20. Quadro complessivo della ristrutturazione**

Possiamo ora vedere l'intera fase di ristrutturazione come una sequenza di trasformazioni.

```
SCHEMA E-R INIZIALE
        │
        ▼
Analisi delle ridondanze
        │
        ▼
Eliminazione delle gerarchie
        │
        ▼
Partizionamenti / accorpamenti
        │
        ├── entità
        └── relazioni
        │
        ▼
Eliminazione costrutti non supportati
        │
        ├── attributi multivalore
        └── attributi composti
        │
        ▼
Scelta identificatori primari
        │
        ▼
SCHEMA E-R RISTRUTTURATO
```

Lo schema ottenuto è così maggiormente adatto alla successiva traduzione nel **modello relazionale**.

---

# **21. Schema mentale delle trasformazioni**

### **Partizionamento orizzontale**

```
Una entità
    ↓
più entità che dividono le ISTANZE
```

> **Divido le righe.**

---

### **Partizionamento verticale**

```
Una entità
    ↓
più entità che dividono gli ATTRIBUTI
```

> **Divido le colonne.**

---

### **Accorpamento**

```
Più entità/relazioni
        ↓
una struttura unica
```

> Utile quando le informazioni vengono **usate insieme**.

---

### **Attributo multivalore**

```
attributo (0,n)
      ↓
nuova entità + relazione
```

---

### **Attributo composto**

```
       attributo composto
             │
      ┌──────┴──────┐
      ▼             ▼
composto come    componenti come
attributo        attributi semplici
semplice
```

---

### **Identificatori**

```
più chiavi candidate
        ↓
scelta della chiave primaria
```

preferendo:

```
frequente
+ corta
+ interna
+ senza null
```

---

# **22. Sintesi finale**

**Abbiamo visto:**

- il **partizionamento di entità**:
    
    - **orizzontale**, quando vengono separate le istanze;
        
    - **verticale**, quando vengono separati attributi e relazioni;
        
- l'**accorpamento di entità** collegate da relazioni 1:1 o 1;
    
- il **partizionamento e l'accorpamento delle relazioni**;
    
- l'eliminazione degli **attributi multivalore** attraverso la creazione di una nuova entità;
    
- le due possibili trasformazioni degli **attributi composti**;
    
- i criteri per scegliere gli **identificatori primari**.
    

---

### **Da ricordare**

> **Partizionamento**
> 
> = separo ciò che viene usato separatamente.

> **Accorpamento**
> 
> = riunisco ciò che viene usato frequentemente insieme.

> **Partizionamento orizzontale**
> 
> = separo le **istanze**.

> **Partizionamento verticale**
> 
> = separo gli **attributi**.

> **Attributo multivalore**
> 
> = creo una **nuova entità**.

> **Attributo composto**
> 
> = mantengo il composto come attributo semplice oppure mantengo separatamente le sue componenti.

> **Identificatore primario**
> 
> = preferibilmente frequente, semplice, interno e senza attributi nulli.

---

> **[PLACEHOLDER IMMAGINE — Partizionamento orizzontale]**

> **[PLACEHOLDER IMMAGINE — Partizionamento verticale]**

> **[PLACEHOLDER IMMAGINE — Accorpamento di entità]**

> **[PLACEHOLDER IMMAGINE — Partizionamento di relazioni]**

> **[PLACEHOLDER IMMAGINE — Eliminazione attributo multivalore]**

> **[PLACEHOLDER IMMAGINE — Eliminazione attributo composto]**

---