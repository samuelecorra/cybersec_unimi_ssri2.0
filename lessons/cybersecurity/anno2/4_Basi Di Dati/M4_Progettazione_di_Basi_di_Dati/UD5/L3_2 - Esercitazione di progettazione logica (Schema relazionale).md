# **M4 UD5 Lezione 3 - Parte II - Esercitazione di progettazione logica (Schema relazionale)**

### **1. Introduzione**

In questa seconda parte si effettua la **traduzione logica** dello schema concettuale della _Cascina La Piccola Luna_, ottenendo il corrispondente **schema relazionale** completo di chiavi primarie, chiavi esterne e vincoli di integrità referenziale.  
L’obiettivo è rappresentare in modo rigoroso tutti i collegamenti tra appezzamenti, lotti, prodotti, contadini e raccolti.

---

### **2. Schema logico relazionale**

Dallo schema E–R della fase concettuale si ottengono le seguenti relazioni del **modello logico relazionale**:

---

#### **2.1 Relazioni principali**

$$  
\text{APPEZZAMENTO(Nome, TipoTerreno, PosizioneGeografica)}  
$$

- Chiave primaria: $(Nome)$
    
- Descrive ciascun appezzamento gestito dalla cascina.

---

$$  
\text{LOTTO(NomeAppezzamento, Codice, Dimensione, DataSemina, MediaMesiSemina, NomeProdotto)}  
$$

- Chiave primaria: $(NomeAppezzamento, Codice)$
    
- Chiavi esterne:
    
    - $(NomeAppezzamento)$ → **APPEZZAMENTO**
        
    - $(NomeProdotto)$ → **PRODOTTO**

---

$$  
\text{PRODOTTO(NomeScientifico, Costo, Ente, Biologico, Tradizionale)}  
$$

- Chiave primaria: $(NomeScientifico)$
    
- Gli attributi `Biologico` e `Tradizionale` sono **indicatori logici** (booleani) che specificano la tipologia del prodotto.
    
- `Ente` rappresenta l’ente certificatore (solo per i prodotti biologici).
    
- Le informazioni relative ai pesticidi e ai trattamenti sono gestite da relazioni dedicate.

---

$$  
\text{CONTADINO(CF, Nome, Cognome, Indirizzo, Telefono)}  
$$

- Chiave primaria: $(CF)$
    
- Contiene i dati anagrafici di ciascun contadino.

---

$$  
\text{RACCOLTO(Codice, Data, Quantità, NomeAppezzamento, CodiceLotto)}  
$$

- Chiave primaria: $(Codice)$
    
- Chiavi esterne:
    
    - $(NomeAppezzamento, CodiceLotto)$ → **LOTTO**

---

#### **2.2 Relazioni derivate**

$$  
\text{ASSEGNAMENTO(NomeAppezzamento, CFContadino, Mansione)}  
$$

- Chiave primaria: $(NomeAppezzamento, CFContadino)$
    
- Chiavi esterne:
    
    - $(NomeAppezzamento)$ → **APPEZZAMENTO**
        
    - $(CFContadino)$ → **CONTADINO**
    
- Registra i contadini assegnati a ciascun appezzamento e le relative mansioni.

---

$$  
\text{ESECUZIONE(CFContadino, CodiceRaccolto)}  
$$

- Chiave primaria: $(CFContadino, CodiceRaccolto)$
    
- Chiavi esterne:
    
    - $(CFContadino)$ → **CONTADINO**
        
    - $(CodiceRaccolto)$ → **RACCOLTO**
    
- Indica i contadini che hanno partecipato ai vari raccolti.

---

$$  
\text{TRATTAMENTO(NomeProdotto, CodiceTrattamento)}  
$$

- Chiave primaria: $(NomeProdotto, CodiceTrattamento)$
    
- Chiave esterna: $(NomeProdotto)$ → **PRODOTTO**
    
- Elenca i trattamenti previsti per ogni prodotto.

---

$$  
\text{PESTICIDA(NomeProdotto, CodicePesticida)}  
$$

- Chiave primaria: $(NomeProdotto, CodicePesticida)$
    
- Chiave esterna: $(NomeProdotto)$ → **PRODOTTO**
    
- Rappresenta i pesticidi ammessi per i prodotti tradizionali.
    

---

### **3. Vincoli di integrità referenziale**

I **vincoli di integrità referenziale** assicurano la coerenza dei collegamenti tra relazioni, impedendo che vengano inseriti dati non esistenti nelle tabelle collegate.

---

#### **3.1 Elenco dei vincoli principali**

---

#### **Da `LOTTO` a `APPEZZAMENTO` e `PRODOTTO`**

$$  
\begin{aligned}  
\text{APPEZZAMENTO.(Nome)} &\rightarrow \text{LOTTO.(NomeAppezzamento)} \\  
\text{PRODOTTO.(NomeScientifico)} &\rightarrow \text{LOTTO.(NomeProdotto)}  
\end{aligned}  
$$

---

#### **Da `RACCOLTO` a `LOTTO`**

$$  
\begin{aligned}  
\text{LOTTO.(NomeAppezzamento, Codice)} &\rightarrow \text{RACCOLTO.(NomeAppezzamento, CodiceLotto)}  
\end{aligned}  
$$

---

#### **Da `ASSEGNAMENTO` a `APPEZZAMENTO` e `CONTADINO`**

$$  
\begin{aligned}  
\text{APPEZZAMENTO.(Nome)} &\rightarrow \text{ASSEGNAMENTO.(NomeAppezzamento)} \\  
\text{CONTADINO.(CF)} &\rightarrow \text{ASSEGNAMENTO.(CFContadino)}  
\end{aligned}  
$$

---

#### **Da `ESECUZIONE` a `CONTADINO` e `RACCOLTO`**

$$  
\begin{aligned}  
\text{CONTADINO.(CF)} &\rightarrow \text{ESECUZIONE.(CFContadino)} \\  
\text{RACCOLTO.(Codice)} &\rightarrow \text{ESECUZIONE.(CodiceRaccolto)}  
\end{aligned}  
$$

---

#### **Da `TRATTAMENTO` e `PESTICIDA` a `PRODOTTO`**

$$  
\begin{aligned}  
\text{PRODOTTO.(NomeScientifico)} &\rightarrow \text{TRATTAMENTO.(NomeProdotto)} \\  
\text{PRODOTTO.(NomeScientifico)} &\rightarrow \text{PESTICIDA.(NomeProdotto)}  
\end{aligned}  
$$

---

### **4. Schema logico completo**

$$  
\begin{aligned}  
\text{APPEZZAMENTO} & (Nome, TipoTerreno, PosizioneGeografica) \\  
\text{LOTTO} & (NomeAppezzamento, Codice, Dimensione, DataSemina, MediaMesiSemina, NomeProdotto) \\  
\text{PRODOTTO} & (NomeScientifico, Costo, Ente, Biologico, Tradizionale) \\  
\text{CONTADINO} & (CF, Nome, Cognome, Indirizzo, Telefono) \\  
\text{RACCOLTO} & (Codice, Data, Quantità, NomeAppezzamento, CodiceLotto) \\  
\text{ASSEGNAMENTO} & (NomeAppezzamento, CFContadino, Mansione) \\  
\text{ESECUZIONE} & (CFContadino, CodiceRaccolto) \\  
\text{TRATTAMENTO} & (NomeProdotto, CodiceTrattamento) \\  
\text{PESTICIDA} & (NomeProdotto, CodicePesticida)  
\end{aligned}  
$$

---

### **5. Sintesi finale**

In questo schema logico:

- ogni tabella corrisponde a un’entità o relazione dello schema E–R;
    
- tutte le **chiavi primarie e chiavi esterne** sono chiaramente definite;
    
- i **vincoli di integrità** mantengono la coerenza tra appezzamenti, lotti, prodotti, raccolti e contadini;
    
- la gestione dei **prodotti biologici e tradizionali** è unificata nella tabella `PRODOTTO`, con relazioni aggiuntive per trattamenti e pesticidi.

> Il risultato finale è uno **schema relazionale completo e normalizzato**, pronto per essere implementato in un DBMS relazionale e utilizzato per la gestione digitale delle attività agricole della _Cascina La Piccola Luna_.