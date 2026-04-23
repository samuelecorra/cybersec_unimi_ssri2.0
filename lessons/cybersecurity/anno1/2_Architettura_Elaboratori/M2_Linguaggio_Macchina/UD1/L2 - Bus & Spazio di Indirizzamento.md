# **Lezione 2: Bus & Spazio di Indirizzamento**

---

## **1. Struttura tripartita del Bus di Sistema**

Nel modello di Von Neumann si adotta una **notazione prevalentemente anglosassone** e si rappresenta il **bus di sistema** come una **struttura tripartita**:

- **Data Bus**
    
- **Address Bus**
    
- **Control Bus**
    

Questi tre sottosistemi lavorano insieme per permettere alla CPU di comunicare con:

- memoria,
    
- periferiche,
    
- interfacce di I/O.
    

---

## **2. Data Bus – Trasferimento dei dati**

Il **Data Bus** è il **primo livello del bus** ed è la porzione dedicata al:

> trasferimento **in parallelo** di una **stringa di bit** tra l’unità **master (CPU)** e le unità **slave (memoria e I/O)**.

Se indichiamo con $d$ il numero di linee (fili) del Data Bus, allora:

- $d$ determina la **dimensione della parola di memoria**,
    
- quindi quanti bit possono essere trasferiti **contemporaneamente**.
    

### **Banda passante del Data Bus**

La **banda passante** è definita come:

> il numero di bit trasferibili per unità di tempo.

Essa è **direttamente proporzionale a $d$**, a parità di tutte le altre condizioni.

---

## **3. Evoluzione storica del Data Bus**

Nel tempo, $d$ è cresciuto in modo esponenziale:

- 8 bit
    
- 16 bit
    
- 32 bit
    
- 64 bit
    
- 128 bit
    

Ogni aumento di $d$ ha portato un **incremento diretto delle prestazioni** del sistema.

---

## **4. Address Bus – Spazio di indirizzamento**

L’**Address Bus** è il **secondo livello del bus**.

Il suo compito è permettere alla CPU (unità master) di:

> indicare **a quale cella** di memoria o di periferica intende fare riferimento.

Se indichiamo con $a$ il numero di linee dell’Address Bus, allora:

> il numero massimo di celle indirizzabili è:
> 
> $2^a$

Questo valore prende il nome di:

> **spazio di indirizzamento** della CPU.

---

## **5. Spazio di indirizzamento vs memoria fisica**

È fondamentale distinguere tra:

- **spazio di indirizzamento** → tutte le celle che la CPU **potrebbe teoricamente indirizzare**,
    
- **memoria fisica reale** → celle **effettivamente presenti** nel calcolatore.
    

Esempio:

- Address Bus a **32 bit** → $2^{32} = 4 \text{ GB}$
    
- PC reale con **2 GB di RAM**
    

Significa che **solo una parte dello spazio indirizzabile è realmente occupata da memoria fisica**.

---

## **6. Conseguenze dell’aumento dell’Address Bus**

All’aumentare di $a$ aumentano direttamente:

- la **dimensione massima dei programmi eseguibili**,
    
- la **quantità massima di dati elaborabili**.
    

A differenza del Data Bus, l’Address Bus:

- non è cresciuto con semplici raddoppi costanti,
    
- ha seguito una crescita più irregolare:
    
    - 16 bit,
        
    - 20 bit,
        
    - 24 bit,
        
    - 32 bit,
        
    - nelle architetture PC.
        

---

## **7. Control Bus – Segnali di controllo**

Il **Control Bus** è la **terza e ultima porzione** del bus di sistema.

A differenza degli altri due:

- **Data Bus → trasferisce dati aggregati**
    
- **Address Bus → trasferisce indirizzi aggregati**
    
- **Control Bus → ogni linea è indipendente**
    

Ogni linea del Control Bus trasporta:

> un **segnale di controllo distinto**, con un significato ben preciso.

---

## **8. Esempi di segnali sul Control Bus**

Alcune linee tipiche sono:

- **R (Read)** → lettura,
    
- **W (Write)** → scrittura,
    
- **Reset**
    
- **Clock**
    

Ogni linea funziona come un **interruttore autonomo**, indipendente dalle altre.

---

## **9. Direzione dei segnali sul Control Bus**

Esistono **due grandi categorie di linee**:

### **a) Linee CPU → Dispositivi Slave**

Servono alla CPU per:

- impartire comandi,
    
- controllare memoria e periferiche.
    

Sono **linee in uscita dalla CPU**, perché la CPU è il **master**.

Esempio tipico: **R / W**.

---

### **b) Linee Dispositivi Slave → CPU**

Servono agli slave per:

- segnalare eventi alla CPU,
    
- richiedere attenzione,
    
- attivare particolari modalità di interazione.
    

Sono **linee in ingresso alla CPU**.

---

## **10. Complessità del Control Bus**

Il **numero di linee del Control Bus**:

- non è fisso,
    
- dipende direttamente:
    
    - dalla **complessità della CPU**,
        
    - dal **numero e tipo di dispositivi slave**,
        
    - dalle **modalità di interazione supportate**.
        

Più la CPU è complessa, più il Control Bus diventa articolato.
