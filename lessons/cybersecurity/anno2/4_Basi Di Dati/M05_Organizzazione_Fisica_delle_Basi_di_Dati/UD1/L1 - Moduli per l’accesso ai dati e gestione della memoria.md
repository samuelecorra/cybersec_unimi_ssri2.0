# **M5 UD1 Lezione 1 - Moduli per l’accesso ai dati e gestione della memoria**

### **1. Introduzione**

Questa lezione inaugura lo studio dell’**organizzazione fisica delle basi di dati**, illustrando come il **DBMS** gestisca la memoria e strutturi l’accesso ai dati.  
Si parte dal principio di **indipendenza fisica**, secondo cui il livello logico (visibile agli utenti e alle query SQL) è separato dal livello fisico (dove i dati sono effettivamente memorizzati).  
Il DBMS traduce quindi le operazioni logiche in operazioni fisiche ottimizzate, interagendo con la memoria attraverso moduli dedicati.

---

### **2. Organizzazione fisica dei dati**

- Gli **utenti** e le **interrogazioni SQL** lavorano solo sul livello logico.
    
- Il **DBMS** si occupa di **mappare** queste operazioni in accessi fisici ai file su memoria secondaria.
    
- Ciò garantisce **indipendenza fisica**, cioè la possibilità di modificare l’organizzazione fisica senza influire sulle applicazioni.

---

### **3. Moduli del DBMS per l’accesso ai dati**

L’esecuzione di un’interrogazione coinvolge più componenti del DBMS:

1. **Gestore delle interrogazioni (ottimizzatore)**  
    Traduce le interrogazioni SQL in operazioni fisiche sui file, scegliendo il piano di esecuzione più efficiente.
    
2. **Gestore dei metodi di accesso**  
    Traduce le operazioni logiche sulle strutture fisiche (tabelle, indici, ecc.) in operazioni di lettura e scrittura su memoria secondaria.
    
3. **Gestore del buffer**  
    Gestisce una porzione di memoria principale per ridurre l’accesso al disco, mantenendo temporaneamente i dati più utilizzati.
    
4. **Gestore della memoria secondaria**  
    Si occupa delle effettive operazioni di input/output tra disco e memoria principale.

---

#### **3.1 Schema generale**

$$
\begin{aligned}
&\text{Gestore interrogazioni} \\  
&\downarrow \\  
&\text{Gestore metodi di accesso} \\  
&\downarrow \\  
&\text{Gestore del buffer} \\  
&\downarrow \\  
&\text{Gestore memoria secondaria} \\  
&\downarrow \\  
&\text{Memoria secondaria}  
\end{aligned}
$$

Ogni livello agisce come intermediario tra quello superiore e quello inferiore, migliorando efficienza e modularità.

---

### **4. Memoria secondaria**

#### **4.1 Vantaggi**

- **Elevata capacità**: la memoria secondaria (dischi) può contenere quantità di dati enormi, superiori alla memoria principale.
    
- **Persistenza**: i dati non si perdono quando il sistema viene spento, al contrario della RAM.

#### **4.2 Svantaggi**

- **Non accessibile direttamente dai programmi**: i dati devono essere caricati in RAM.
    
- **Accesso per blocchi di dimensione fissa**: ogni operazione coinvolge l’intero blocco, anche se serve solo una piccola parte.
    
- **Tempi di accesso elevati**:
    
    - tempo di posizionamento della testina;
        
    - tempo di latenza (attesa di rotazione del disco);
        
    - tempo di trasferimento dei dati.

> Indicativamente, la memoria principale opera in $\approx 10^{-9}$ secondi, mentre l’accesso al disco richiede $\approx 10^{-3}$ secondi.

---

### **5. Il buffer**

Il **buffer** è un’area della **memoria principale** gestita dal DBMS per **ridurre gli accessi al disco**.  
Contiene copie temporanee dei blocchi di dati più utilizzati.

#### **5.1 Caratteristiche**

- È condiviso da tutte le transazioni.
    
- È organizzato in **pagine**, ciascuna pari a un multiplo della dimensione del blocco.
    
- Il **gestore del buffer** si occupa del **caricamento e scaricamento** delle pagine tra memoria principale e secondaria.

---

### **6. Gestore del buffer**

Il gestore del buffer mantiene:

- un **direttorio** che indica quali blocchi fisici sono caricati e in quale pagina si trovano;
    
- per ogni pagina:
    
    - un **contatore di utilizzo** (numero di processi che la usano);
        
    - un **bit di stato (dirty bit)** che segnala se la pagina è stata modificata.

#### **Funzionamento**

- Le **letture e scritture** vengono effettuate direttamente sulle pagine in buffer.
    
- Le **scritture su disco** sono **differite**, cioè avvengono solo quando necessario, per ridurre l’I/O.

---

### **7. Primitive del gestore del buffer**

#### **7.1 Principali operazioni**

|**Operazione**|**Funzione**|
|---|---|
|`fix`|Carica una pagina nel buffer (se non è già presente) e incrementa il contatore.|
|`unfix`|Decrementa il contatore di utilizzo di una pagina.|
|`setDirty`|Indica che la pagina è stata modificata (imposta il bit di stato).|
|`force`|Scrive immediatamente una pagina modificata dal buffer su disco (sincrono).|
|`flush`|Scrive su disco le pagine inutilizzate o scadute (asincrono).|

---

#### **7.2 Funzionamento della primitiva `fix`**

$$  
\begin{cases}  
\text{Se la pagina è nel buffer:} & \text{ritorna l’indirizzo.} \\\\  
\text{Se ci sono pagine libere:} & \text{carica la pagina da disco nel buffer.} \\\\  
\text{Altrimenti:} & \text{seleziona una “pagina vittima” e la riscrive su disco (flush),} \\  
& \text{poi carica la nuova pagina richiesta.}  
\end{cases}  
$$

---

### **8. Politiche di gestione del buffer**

#### **8.1 Scelta della “pagina vittima”**

Due strategie possibili:

- **Steal**: consente di scegliere come vittima anche una pagina appartenente a un’altra transazione.
    
- **No-steal**: vieta questa scelta; usata più spesso nei DBMS commerciali per garantire coerenza.

---

#### **8.2 Principi di ottimizzazione**

Il DBMS adotta politiche simili a quelle dei sistemi operativi, basate su:

- **principio di località**: i dati usati di recente hanno alta probabilità di essere riutilizzati;
    
- **legge empirica 80/20**: circa il 20% dei dati è richiesto dall’80% delle operazioni.

> Ciò permette al DBMS di mantenere in memoria solo le porzioni di dati più utili, riducendo drasticamente gli accessi al disco.

---

### **9. DBMS e file system**

Il **DBMS** utilizza il **sistema operativo** per creare e cancellare file, ma implementa un proprio livello di gestione per garantire:

- **efficienza**;
    
- **controllo transazionale**;
    
- **gestione autonoma dei blocchi** come un unico spazio di memoria logico.

Sopra questo spazio, il DBMS costruisce le proprie **strutture fisiche** (file organizzati, indici, ecc.) e fornisce **metodi di accesso ottimizzati**.

---

### **10. Sintesi finale**

**Abbiamo visto:**

- l’indipendenza fisica tra livello logico e livello di memorizzazione;
    
- i moduli del DBMS coinvolti nell’accesso ai dati;
    
- il funzionamento della memoria secondaria e del buffer;
    
- le principali politiche di gestione e ottimizzazione della memoria.

> In sintesi, la **gestione del buffer** è cruciale per l’efficienza complessiva del DBMS, poiché riduce i tempi di accesso e ottimizza l’uso delle risorse di memoria.

---


![](imgs/Pasted%20image%2020251125051131.png)

