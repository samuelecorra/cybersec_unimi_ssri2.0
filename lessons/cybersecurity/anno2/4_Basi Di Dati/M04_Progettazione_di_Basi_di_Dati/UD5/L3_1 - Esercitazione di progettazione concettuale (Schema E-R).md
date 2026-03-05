# **M4 UD5 Lezione 3 - Parte I - Esercitazione di progettazione concettuale (Schema E-R)**

### **1. Introduzione**

In questa esercitazione si progetta lo **schema concettuale E–R** per la base di dati della **cascina “La Piccola Luna”**, un’azienda agricola che desidera digitalizzare la gestione delle proprie attività agricole e produttive.  
L’obiettivo è analizzare i requisiti e costruire un modello che rappresenti in modo coerente **terreni, lotti, prodotti, contadini e raccolti**, evidenziando le relazioni logiche tra queste entità.

---

### **2. Analisi dei requisiti**

#### **2.1 Struttura dei terreni**

La cascina dispone di **più appezzamenti di terreno**, ognuno dei quali è **suddiviso in più lotti**.

Per ogni **appezzamento** si conoscono:

- **nome** dell’appezzamento;
    
- **tipo di terreno** (es. sabbioso, argilloso, misto);
    
- **posizione geografica**.

Per ogni **lotto** si conoscono:

- **codice identificativo**, univoco **all’interno dell’appezzamento**;
    
- **dimensione** (espressa in ettari o metri quadrati);
    
- **data dell’ultima semina**;
    
- **numero medio di mesi** che devono trascorrere prima del **raccolto successivo**.

> Relazione: ogni **appezzamento** è **1:N** con i **lotti** (un appezzamento contiene più lotti).

---

#### **2.2 Prodotti coltivati**

Ogni lotto è utilizzato per la **coltivazione di un prodotto specifico**.

Per ogni **prodotto** si conoscono:

- **nome scientifico**;
    
- **lista dei trattamenti** da eseguire;
    
- **costo delle sementi** (in euro al chilo).

I prodotti si distinguono in due **categorie principali**:

1. **Prodotti biologici**, per i quali **non è ammesso l’uso di pesticidi**;
    
2. **Prodotti tradizionali**, per i quali è ammessa la **lista dei pesticidi utilizzabili**.

> Le due categorie **non sono mutuamente esclusive**:  
> lo stesso prodotto può avere **coltivazioni sia biologiche che tradizionali**.

Per ogni **prodotto biologico**, occorre registrare il **nome dell’ente certificatore**.  
Per ogni **prodotto tradizionale**, si deve specificare la **lista dei pesticidi ammessi**.

> Ciò suggerisce la presenza di **due entità derivate** (sottotipi) di _Prodotto_:  
> **Prodotto_Biologico** e **Prodotto_Tradizionale**,  
> con una **gerarchia parziale e sovrapposta** (non esclusiva).

---

#### **2.3 Contadini**

La cascina impiega diversi **contadini**, per i quali si registrano le **informazioni anagrafiche**:

- nome,
    
- cognome,
    
- indirizzo,
    
- numero di telefono.

Ogni contadino può essere **assegnato a uno o più appezzamenti**, e su ciascun appezzamento può **svolgere diverse mansioni**.

> Ne deriva una **relazione N:M** tra _Contadino_ e _Appezzamento_,  
> con un eventuale attributo `mansione`.

---

#### **2.4 Raccolti**

Per ogni **raccolto** si desidera memorizzare:

- la **data**;
    
- la **quantità di prodotto raccolto**;
    
- il **lotto** da cui proviene;
    
- i **contadini** che vi hanno partecipato.

> Ne risulta una relazione complessa:
> 
> - tra _Raccolto_ e _Lotto_: **N:1**, poiché ogni raccolto è relativo a un solo lotto;
>     
> - tra _Raccolto_ e _Contadino_: **N:M**, poiché più contadini possono lavorare allo stesso raccolto e viceversa.

---

### **3. Costruzione dello schema E–R**

#### **3.1 Entità principali**

|**Entità**|**Attributi principali**|
|---|---|
|**Appezzamento**|nome, tipo_terreno, posizione|
|**Lotto**|codice, dimensione, data_ultima_semina, mesi_raccolto|
|**Prodotto**|nome_scientifico, trattamenti, costo_sementi|
|**Prodotto_Biologico**|ente_certificatore|
|**Prodotto_Tradizionale**|lista_pesticidi|
|**Contadino**|nome, cognome, indirizzo, telefono|
|**Raccolto**|data, quantità|

---

#### **3.2 Relazioni individuate**

|**Relazione**|**Tipo**|**Descrizione**|
|---|---|---|
|**Composizione**|1:N|tra _Appezzamento_ e _Lotto_|
|**Coltivazione**|1:N|tra _Lotto_ e _Prodotto_|
|**Assegnazione**|N:M|tra _Contadino_ e _Appezzamento_, con attributo `mansione`|
|**Partecipazione**|N:M|tra _Raccolto_ e _Contadino_|
|**Appartenenza**|N:1|tra _Raccolto_ e _Lotto_|

---

#### **3.3 Gerarchie**

- **Prodotto_Biologico** e **Prodotto_Tradizionale** sono **sottotipi** di _Prodotto_.
    
- La gerarchia è:
    
    - **parziale**, poiché non tutti i prodotti appartengono necessariamente a una categoria;
        
    - **sovrapposta**, poiché un prodotto può essere sia biologico che tradizionale.

---

### **4. Schema E–R sintetico**

$$
\begin{aligned}
&\text{ENTITÀ: } { \text{Appezzamento, Lotto, Prodotto, Prodotto\_Biologico, Prodotto\_Tradizionale, Contadino, Raccolto} } \\  
&\text{RELAZIONI: } { \text{Composizione, Coltivazione, Assegnazione, Partecipazione, Appartenenza} }
\end{aligned}
$$

---

### **5. Sintesi finale**

Il modello concettuale della _Cascina La Piccola Luna_ descrive in modo completo:

- la **struttura dei terreni** (appezzamenti e lotti);
    
- le **caratteristiche dei prodotti** e la distinzione tra tipologie biologiche e tradizionali;
    
- la **gestione del personale agricolo** e delle **mansioni**;
    
- la **tracciabilità dei raccolti** e della **manodopera** coinvolta.

> Lo schema E–R risultante è pronto per la **traduzione logica** nel modello relazionale,  
> dove verranno definite tabelle, chiavi e vincoli di integrità referenziale.