# **M4 UD5 Lezione 1 - Esercitazione di progettazione concettuale - Schema E-R**

### **1. Introduzione**

In questa lezione si propone un esercizio completo di **progettazione concettuale**, basato sulla descrizione in linguaggio naturale dei **requisiti** forniti dall’associazione culturale _Ed È Subito Sera (EESS)_.  
L’obiettivo è costruire uno **schema Entità–Relazione (E–R)** che rappresenti in modo corretto e coerente tutti i dati e i legami tra gli elementi coinvolti nella gestione degli eventi organizzati.

---

### **2. Analisi dei requisiti**

#### **2.1 Contesto generale**

L’associazione _Ed È Subito Sera (EESS)_ organizza **eventi culturali** in tutta Italia, dedicati alla **poesia contemporanea**.  
La base di dati richiesta dovrà permettere di gestire:

- gli **eventi** e le loro caratteristiche;
    
- gli **enti sponsor** che finanziano gli eventi;
    
- le **esibizioni** e gli **artisti** che vi partecipano;
    
- gli **iscritti** all’associazione e i loro eventuali **accompagnatori**.

---

#### **2.2 Descrizione dei requisiti**

##### **Eventi**

Ogni evento è caratterizzato da:

- **uno slogan**,
    
- **una città** in cui si svolge,
    
- **una durata**,
    
- **una quota di partecipazione**.

Gli eventi possono essere **sovvenzionati da enti sponsor**, ciascuno dei quali può finanziare **più eventi**, versando **importi differenti** per ciascuno.

##### **Enti sponsor**

Per ogni ente sponsor si conoscono:

- il **nome**,
    
- una **breve descrizione dell’attività**,
    
- l’**indirizzo**,
    
- l’**importo versato** per la sponsorizzazione.

Poiché lo stesso ente può sponsorizzare più eventi, la relazione **Sponsorizzazione** tra _Ente_ ed _Evento_ sarà **molti-a-molti (N:M)**, con l’attributo **importo** associato alla relazione.

---

#### **Esibizioni e artisti**

Ogni **evento** è composto da una o più **esibizioni**, e ogni **esibizione** può avere luogo in più eventi (relazione N:M).  
Ogni esibizione è caratterizzata da:

- una **tipologia**,
    
- una **durata**.

Gli **artisti** che si esibiscono sono identificati da:

- **nome e cognome**,
    
- **nome del manager**,
    
- **numero di telefono** del manager.

Un artista può partecipare a più esibizioni, e un’esibizione può coinvolgere più artisti → relazione **N:M** tra _Artista_ ed _Esibizione_.  
Poiché l’importo dell’ingaggio varia **in base all’esibizione e all’evento**, tale importo sarà un **attributo della relazione ternaria** tra _Artista_, _Esibizione_ ed _Evento_.

---

#### **Iscritti all’associazione**

Per ogni **iscritto** a _EESS_ si conoscono:

- **nome**,
    
- **cognome**,
    
- **indirizzo**,
    
- **numeri di telefono** (casa, ufficio, eventualmente cellulare),
    
- **indirizzo e-mail**,
    
- **data di iscrizione**,
    
- **tassa di iscrizione**.

Gli iscritti si suddividono in due **classi**:

- **Membri**;
    
- **Supporter** (coloro che partecipano all’organizzazione degli eventi).

Tutti gli iscritti possono partecipare **gratuitamente** agli eventi.  
Gli **accompagnatori** invece devono versare la **quota di partecipazione** prevista per l’evento: per ciascun iscritto ed evento è necessario memorizzare il **numero di accompagnatori**.

---

### **3. Costruzione dello schema E–R**

#### **3.1 Entità principali**

Dall’analisi dei requisiti emergono le seguenti **entità** con i rispettivi attributi:

|**Entità**|**Attributi principali**|
|---|---|
|**Evento**|slogan, città, durata, quota_partecipazione|
|**Ente**|nome, descrizione, indirizzo|
|**Esibizione**|tipologia, durata|
|**Artista**|nome, cognome, manager, telefono_manager|
|**Iscritto**|nome, cognome, indirizzo, telefono_casa, telefono_ufficio, telefono_cellulare, email, data_iscrizione, tassa_iscrizione|
|**Membro / Supporter**|specializzazione di _Iscritto_|
|**Accompagnatore**|numero_accompagnatori|

---

#### **3.2 Relazioni tra entità**

|**Relazione**|**Tipo**|**Descrizione**|
|---|---|---|
|**Sponsorizzazione**|N:M|tra _Ente_ ed _Evento_, con attributo `importo`.|
|**Composizione**|N:M|tra _Evento_ ed _Esibizione_.|
|**Partecipazione artistica**|ternaria|tra _Artista_, _Esibizione_ ed _Evento_, con attributo `importo_ingaggio`.|
|**Organizzazione**|1:N|tra _Supporter_ ed _Evento_ (i supporter organizzano eventi).|
|**Partecipazione iscritti**|N:M|tra _Iscritto_ ed _Evento_, con attributo `num_accompagnatori`.|

---

#### **3.3 Gerarchie**

- **Iscritto** è entità padre di **Membro** e **Supporter**.  
    Si tratta di una **gerarchia totale ed esclusiva**, poiché ogni iscritto è esattamente o un membro o un supporter.

---

### **4. Sintesi dello schema E–R**

Lo schema E–R risultante potrà essere riassunto così:

$$  
\text{ENTITÀ: } { \text{Evento, Ente, Esibizione, Artista, Iscritto, Membro, Supporter} }$$
$$
\text{RELAZIONI: } { \text{Sponsorizzazione, Composizione, Partecipazione artistica, Organizzazione, Partecipazione iscritti} }  
$$

**Caratteristiche principali:**

- Presenza di una **relazione ternaria** (_Artista–Esibizione–Evento_) con attributo `importo_ingaggio`.
    
- Gestione di una **gerarchia di specializzazione** (_Iscritto–Membro–Supporter_).
    
- Uso di **relazioni associative** per modellare partecipazioni e sponsorizzazioni.
    
- Tutte le **cardinalità** possono essere derivate dai vincoli descritti nei requisiti.

---

### **5. Conclusione**

L’esercizio rappresenta un caso completo di **modellazione concettuale**, che integra tutti i principali aspetti della progettazione E–R:

- Entità e relazioni con attributi specifici;
    
- Gerarchie di generalizzazione;
    
- Relazioni binarie, ternarie e molti-a-molti;
    
- Attributi dipendenti dal contesto (come l’importo di sponsorizzazione o d’ingaggio).

> Il risultato finale è uno **schema E–R completo e coerente**, pronto per essere ristrutturato e tradotto in **schema logico relazionale** nella fase successiva.