Il corso di **Sistemi Operativi II** approfondisce le funzioni avanzate del sistema operativo, concentrandosi sulla **gestione efficiente delle risorse hardware** — memoria, dispositivi di I/O, file system e risorse distribuite.  
Dopo le basi viste in OS1 (processi, scheduling e sincronizzazione), questo secondo modulo mostra **come il sistema operativo realizza concretamente l’astrazione delle risorse** e garantisce l’illusione di un ambiente uniforme e stabile per i processi utente.

### **Struttura del corso**

#### **Modulo 1 – Gestione della memoria centrale**

Analizza le strategie con cui il sistema operativo organizza e assegna la memoria ai processi:

- partizioni fisse e variabili,
    
- frammentazione interna/esterna,
    
- memoria virtuale, paging e segmentazione,
    
- algoritmi di sostituzione delle pagine (LRU, FIFO, Clock, ecc.).  
    Obiettivo: comprendere come il sistema fornisce a ogni processo una **visione virtuale e continua della memoria**, anche quando fisicamente non lo è.
    

#### **Modulo 2 – Gestione dell’ingresso/uscita**

Tratta l’interfacciamento del sistema operativo con le **periferiche**, l’uso di **driver, buffer e canali DMA**, e le strategie di scheduling delle operazioni di I/O.  
Obiettivo: ottimizzare la comunicazione CPU–periferiche e ridurre i tempi di attesa dei processi.

#### **Modulo 3 – File system**

Studia la struttura logica e fisica dei file e delle directory, i metodi di allocazione dei blocchi (contigua, indicizzata, concatenata) e la gestione di metadati e permessi.  
Obiettivo: capire come il sistema operativo organizza i dati in modo **persistente e sicuro**.

#### **Modulo 4 – Sistemi distribuiti**

Introduce i concetti fondamentali di **distribuzione delle risorse** e cooperazione tra più nodi:

- comunicazione e sincronizzazione distribuita,
    
- clock logici e fisici,
    
- file system distribuiti,
    
- tolleranza ai guasti e sicurezza.  
    Obiettivo: comprendere come estendere i principi dei sistemi operativi tradizionali a **reti di calcolatori interconnessi**.
    

---

In sintesi, **Sistemi Operativi II** completa il percorso iniziato con OS1, passando dal _“come gestire i processi”_ al _“come gestire le risorse fisiche e virtuali”_.  
Alla fine del corso sarai in grado di **analizzare, progettare e valutare** le principali strategie di gestione delle risorse in ambienti sia **locali** che **distribuiti**.