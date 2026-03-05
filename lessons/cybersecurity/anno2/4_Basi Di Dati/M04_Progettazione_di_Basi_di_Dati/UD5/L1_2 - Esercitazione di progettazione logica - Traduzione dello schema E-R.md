# **M4 UD5 Lezione 1 (parte II) - Esercitazione di progettazione logica - Traduzione dello schema E-R**

### **1. Introduzione**

Questa seconda parte dell’esercitazione prosegue con la **traduzione dello schema E–R** costruito in precedenza nel corrispondente **schema logico relazionale**.  
L’obiettivo è mostrare come, a partire dalle entità e relazioni individuate nella fase concettuale, si ottengano le **tabelle relazionali**, complete di attributi, chiavi primarie e chiavi esterne, secondo le **regole di traduzione standard**.

---

### **2. Schema logico**

Dallo schema concettuale costruito per l’associazione _Ed È Subito Sera (EESS)_, derivano le seguenti **relazioni** del modello logico relazionale:

---

#### **2.1 Relazioni principali**

$$  
\text{ISCRITTO(Nome, Cognome, Indirizzo, Email, TelCasa, TelUfficio, TelCellulare, Data, Tassa, Selettore)}  
$$

- Chiave primaria: $(Nome, Cognome)$
    
- L’attributo `Selettore` serve a distinguere i due tipi di iscritto (Membro o Supporter).

---

$$  
\text{EVENTO(Slogan, Città, Durata, Quota)}  
$$

- Chiave primaria: $(Slogan)$
    
- Attributi che descrivono le caratteristiche di ogni evento.

---

$$  
\text{ENTE(Nome, Descrizione, Indirizzo)}  
$$

- Chiave primaria: $(Nome)$
    
- Contiene i dati identificativi degli enti sponsor.

---

$$  
\text{ARTISTA(Nome, Cognome, Manager, Telefono)}  
$$

- Chiave primaria: $(Nome, Cognome)$
    
- Identifica ogni artista e le informazioni di contatto del suo manager.

---

$$  
\text{ESIBIZIONE(Codice, Tipo, Durata)}  
$$

- Chiave primaria: $(Codice)$
    
- Definisce la tipologia e la durata di ciascuna esibizione.

---

#### **2.2 Relazioni derivate**

$$  
\text{ORGANIZZARE(NomeIscritto, CognomeIscritto, SloganEvento)}  
$$

- Relazione tra _Supporter_ ed _Evento_.
    
- Chiave primaria: $(NomeIscritto, CognomeIscritto, SloganEvento)$
    
- Chiavi esterne:
    
    - $(NomeIscritto, CognomeIscritto)$ → **ISCRITTO**
        
    - $(SloganEvento)$ → **EVENTO**

---

$$  
\text{PARTECIPARE(NomeIscritto, CognomeIscritto, SloganEvento, NumAccompagnatori)}  
$$

- Relazione tra _Iscritto_ ed _Evento_.
    
- Chiave primaria: $(NomeIscritto, CognomeIscritto, SloganEvento)$
    
- Chiavi esterne:
    
    - $(NomeIscritto, CognomeIscritto)$ → **ISCRITTO**
        
    - $(SloganEvento)$ → **EVENTO**

---

$$  
\text{SOVVENZIONARE(SloganEvento, NomeEnte, Importo)}  
$$

- Relazione tra _Evento_ ed _Ente_.
    
- Chiave primaria: $(SloganEvento, NomeEnte)$
    
- Chiavi esterne:
    
    - $(SloganEvento)$ → **EVENTO**
        
    - $(NomeEnte)$ → **ENTE**

---

$$  
\text{COMPORRE(SloganEvento, CodiceEsibizione)}  
$$

- Relazione tra _Evento_ ed _Esibizione_.
    
- Chiave primaria: $(SloganEvento, CodiceEsibizione)$
    
- Chiavi esterne:
    
    - $(SloganEvento)$ → **EVENTO**
        
    - $(CodiceEsibizione)$ → **ESIBIZIONE**

---

$$  
\text{ESEGUIRE(SloganEvento, CodiceEsibizione, NomeArtista, CognomeArtista, Ingaggio)}  
$$

- Relazione ternaria tra _Evento_, _Esibizione_ e _Artista_.
    
- Chiave primaria: $(SloganEvento, CodiceEsibizione, NomeArtista, CognomeArtista)$
    
- Chiavi esterne:
    
    - $(SloganEvento)$ → **EVENTO**
        
    - $(CodiceEsibizione)$ → **ESIBIZIONE**
        
    - $(NomeArtista, CognomeArtista)$ → **ARTISTA**

---

### **3. Vincoli di integrità referenziale**

#### **3.1 Collegamenti tra relazioni**

I vincoli di integrità referenziale garantiscono la **coerenza dei collegamenti** tra tabelle, assicurando che i valori delle chiavi esterne corrispondano sempre a chiavi primarie esistenti.

Di seguito i principali vincoli derivati dallo schema:

---

#### **Riferimenti da `ORGANIZZARE`**

$$
\begin{aligned}
\text{ISCRITTO.(Nome, Cognome)} &\rightarrow \text{ORGANIZZARE.(NomeIscritto, CognomeIscritto)} \\  
\text{EVENTO.(Slogan)} \rightarrow &\text{ORGANIZZARE.(SloganEvento)}  
\end{aligned}
$$

---

#### **Riferimenti da `PARTECIPARE`**

$$
\begin{aligned}
\text{ISCRITTO.(Nome, Cognome)} &\rightarrow \text{PARTECIPARE.(NomeIscritto, CognomeIscritto)} \\  
\text{EVENTO.(Slogan)} \rightarrow &\text{PARTECIPARE.(SloganEvento)}  
\end{aligned}
$$

---

#### **Riferimenti da `SOVVENZIONARE`**

$$
\begin{aligned}
\text{EVENTO.(Slogan)} &\rightarrow \text{SOVVENZIONARE.(SloganEvento)} \\  
\text{ENTE.(Nome)} &\rightarrow \text{SOVVENZIONARE.(NomeEnte)}  
\end{aligned}
$$

---

#### **Riferimenti da `COMPORRE`**

$$
\begin{aligned}
\text{EVENTO.(Slogan)} &\rightarrow \text{COMPORRE.(SloganEvento)} \\  
\text{ESIBIZIONE.(Codice)} &\rightarrow \text{COMPORRE.(CodiceEsibizione)}  
\end{aligned}
$$

---

#### **Riferimenti da `ESEGUIRE`**

$$
\begin{aligned}
\text{EVENTO.(Slogan)} &\rightarrow \text{ESEGUIRE.(SloganEvento)} \\
\text{ESIBIZIONE.(Codice)} &\rightarrow \text{ESEGUIRE.(CodiceEsibizione)} \\
\text{ARTISTA.(Nome, Cognome)} &\rightarrow \text{ESEGUIRE.(NomeArtista, CognomeArtista)}
\end{aligned}
$$


---

### **4. Schema logico finale**

#### **4.1 Elenco completo delle relazioni**

$$  
\begin{aligned}  
\text{ISCRITTO} & (Nome, Cognome, Indirizzo, Email, TelCasa, TelUfficio, TelCellulare, Data, Tassa, Selettore) \\\\  
\text{EVENTO} & (Slogan, Città, Durata, Quota) \\\\  
\text{ENTE} & (Nome, Descrizione, Indirizzo) \\\\  
\text{ARTISTA} & (Nome, Cognome, Manager, Telefono) \\\\  
\text{ESIBIZIONE} & (Codice, Tipo, Durata) \\\\  
\text{ORGANIZZARE} & (NomeIscritto, CognomeIscritto, SloganEvento) \\\\  
\text{PARTECIPARE} & (NomeIscritto, CognomeIscritto, SloganEvento, NumAccompagnatori) \\\\  
\text{SOVVENZIONARE} & (SloganEvento, NomeEnte, Importo) \\\\  
\text{COMPORRE} & (SloganEvento, CodiceEsibizione) \\\\  
\text{ESEGUIRE} & (SloganEvento, CodiceEsibizione, NomeArtista, CognomeArtista, Ingaggio)  
\end{aligned}  
$$

---

### **5. Sintesi finale**

**Abbiamo visto:**

- la **traduzione sistematica** dello schema E–R nello **schema logico relazionale**;
    
- la definizione di **chiavi primarie** e **chiavi esterne** per ogni relazione;
    
- la formulazione esplicita dei **vincoli di integrità referenziale**;
    
- la rappresentazione completa e coerente di tutte le **dipendenze** tra le entità principali del dominio.

**In sintesi:**

> Il risultato finale è uno **schema logico relazionale ben normalizzato**, in cui ogni tabella rispecchia le relazioni concettuali del modello E–R e garantisce la **consistenza e integrità** dei dati dell’associazione _Ed È Subito Sera_.