# **M3 UD2 Lezione 3 - Realizzazione dei file – Gestione dell’astrazione dei file (parte 1)**

### **1. Introduzione**

Questa lezione introduce le **modalità di memorizzazione dei file** nelle memorie di massa, mettendo in relazione la **visione logica** (astratta) del file con la **visione fisica** (reale) sui dispositivi di memorizzazione.

L’obiettivo è comprendere come il sistema operativo realizzi il **mappaggio tra gli elementi logici del file e i blocchi fisici del disco**, mantenendo efficienza e coerenza nella gestione dei dati.

---

### **2. Visione logica del file**

La **visione logica** considera il file come una **sequenza ordinata di elementi omogenei**, detti **record logici**.

- Gli elementi hanno **dimensione fissa o variabile** e sono organizzati in un insieme coerente.
    
- Il numero di record non è limitato a priori.

In termini astratti:

$$  
\text{File logico} = { R_1, R_2, \dots, R_n }  
$$

dove ciascun $( R_i )$ è un record logico tipizzato.

---

### **3. Visione fisica del file**

La **visione fisica** rappresenta come il file è effettivamente memorizzato nel dispositivo di massa.  
I dati sono organizzati in un insieme ordinato di **blocchi fisici** di dimensione fissa ( F ) (in byte).

$$  
\text{File fisico} = { B_1, B_2, \dots, B_m }  
$$

Ogni blocco fisico contiene una o più unità logiche (record o byte) del file.

---

### **4. Mappaggio tra visione logica e visione fisica**

Il **mappaggio** è la funzione che collega i **record logici** alla loro **posizione fisica** nel supporto di memoria.

#### **Parametri fondamentali**

- **L:** dimensione del record logico (in byte)
    
- **F:** dimensione del blocco fisico (in byte)

#### **Casi possibili**

1. **Un record logico per blocco fisico**
    
    - Se $( L < F )$ → si genera **sfrido** (spazio inutilizzato).
        
    - Se $( L > F )$ → il record non può essere contenuto in un singolo blocco.
    
2. **Più record logici per blocco fisico**
    
    - Numero massimo di record per blocco:  
        $$  
        K = \max{ n \ | \ nL \leq F }  
        $$
        
    - Se $( KL < F )$ → rimane una piccola parte di spazio inutilizzato (sfrido).

---

### **5. Visione logica omogenea del file: il Byte Stream**

Per evitare sprechi di spazio e semplificare la gestione, la maggior parte dei sistemi operativi moderni adotta una visione **omogenea** del file come **flusso continuo di byte (byte stream)**.

#### **Caratteristiche del byte stream**

- Il file è trattato come una sequenza di **byte non tipizzati**:  
    $$  
    \text{File} = { b_1, b_2, b_3, \dots, b_N }  
    $$
    
- Ogni byte è un’unità minima di informazione.
    
- Il sistema operativo non si preoccupa del significato semantico dei dati (testo, immagine, programma, ecc.).

Questo approccio rende **uniforme la gestione** dei file di qualsiasi tipo, lasciando l’interpretazione dei dati ai **processi utente o alle applicazioni**.

---

### **6. Mappaggio con byte stream**

Il mappaggio tra visione logica e fisica, attraverso il modello a **byte stream**, si realizza in due passaggi.

#### **6.1 Dalla visione logica a quella a byte stream**

- Si **rimuove la tipizzazione** dei record logici.
    
- Il file viene considerato come una sequenza di byte.

#### **6.2 Dalla visione a byte stream alla visione fisica**

- I byte vengono **raggruppati in blocchi fisici** di dimensione ( F ).
    
- Si ottiene una corrispondenza diretta tra:
    
    - blocchi logici (gruppi di byte),
        
    - e blocchi fisici del dispositivo.

---

### **7. Vantaggi del modello a byte stream**

- **Universalità:** ogni file può essere rappresentato come sequenza di byte.
    
- **Assenza di sfridi:** poiché il tipo base (byte) è sempre sottomultiplo della dimensione del blocco fisico ( F ).
    
- **Semplicità di gestione:** il file system può utilizzare algoritmi uniformi per tutti i tipi di file.
    
- **Compatibilità:** il byte stream è alla base dei file system più diffusi (es. UNIX, NTFS, ext4).

---

### **8. Sintesi finale**

|Concetto|Descrizione|
|---|---|
|**Visione logica**|File come sequenza di record logici tipizzati|
|**Visione fisica**|File come insieme ordinato di blocchi fisici|
|**Mappaggio**|Collegamento tra record logici e blocchi fisici|
|**Byte stream**|Flusso omogeneo di byte non tipizzati|
|**Vantaggi**|Nessuno sfrido, semplicità, universalità|

---

### **9. Conclusione**

La rappresentazione dei file come **flussi di byte** consente al sistema operativo di gestire i dati in modo **astratto, uniforme e indipendente** dal tipo di contenuto.  
Questo modello semplifica l’organizzazione fisica dei file e rappresenta la base per le **strategie di allocazione dello spazio su disco**, che verranno approfondite nella **parte 2 della lezione**.