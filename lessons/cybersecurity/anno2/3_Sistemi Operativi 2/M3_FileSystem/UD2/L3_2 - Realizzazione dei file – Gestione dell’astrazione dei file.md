# **M3 UD2 Lezione 3 - Realizzazione dei file – Gestione dell’astrazione dei file (parte 2)**

### **1. Introduzione**

Dopo aver analizzato la rappresentazione logica e fisica dei file, questa seconda parte si concentra sulle **operazioni fondamentali** che costituiscono la gestione dell’astrazione dei file:  
**apertura, lettura, scrittura, posizionamento (seek) e chiusura.**

Queste operazioni consentono al sistema operativo di **mediare tra l’utente e la memoria di massa**, mantenendo la coerenza dei dati e la corretta gestione delle risorse.

---

### **2. Apertura**

L’apertura di un file è il **primo passo di qualsiasi operazione di accesso**.  
Serve a stabilire un **collegamento logico** tra il file richiesto e le strutture di gestione interne del sistema operativo.

#### **Fasi dell’apertura**

1. **Identificazione del file**  
    Il sistema localizza il file all’interno del file system, partendo dal suo nome e dal percorso (path).
    
2. **Verifica dei permessi**  
    Controlla che l’utente disponga delle **autorizzazioni necessarie** (lettura, scrittura, esecuzione).
    
3. **Caricamento delle informazioni di gestione**
    
    - Recupera i **metadati** dalla directory o dal descrittore del file.
        
    - Inizializza una **voce nella tabella dei file aperti** del sistema e una nel processo.
    
4. **Restituzione del riferimento**  
    L’operazione restituisce un **file descriptor** o **handle**, usato dal processo per riferirsi al file durante le operazioni successive.

---

### **3. Lettura**

L’operazione di **lettura** consente di trasferire dati dal file (memoria di massa) alla memoria centrale del processo.

#### **Procedura**

1. Il sistema utilizza il **file descriptor** per identificare il file.
    
2. Recupera il **puntatore alla posizione corrente** nel file.
    
3. Calcola il **blocco fisico corrispondente** e lo legge dal dispositivo di memoria.
    
4. Trasferisce i dati nel **buffer utente**.
    
5. Aggiorna il **puntatore di posizione** per la successiva operazione.

#### **Note**

- In caso di lettura oltre la fine del file (EOF), il sistema restituisce un **valore nullo o errore**.
    
- Nei file system avanzati, le letture possono essere **precaricate (prefetch)** per ottimizzare le prestazioni.

---

### **4. Scrittura**

L’operazione di **scrittura** trasferisce dati dalla memoria centrale del processo verso la memoria di massa.

#### **Procedura**

1. Il sistema individua la **posizione fisica del blocco** in cui scrivere.
    
2. Se necessario, **alloca nuovi blocchi** o aggiorna la mappa di allocazione.
    
3. I dati vengono **copiati nel buffer del sistema operativo**.
    
4. Il contenuto viene poi **scritto effettivamente su disco**, in modo sincrono o asincrono.
    
5. Il **puntatore di posizione** viene aggiornato in base ai byte scritti.

#### **Osservazioni**

- La scrittura può essere **ritardata** per motivi di efficienza, accumulando più operazioni prima del commit.
    
- In caso di errore o interruzione improvvisa, il **journaling** (nei file system moderni) permette di ripristinare la coerenza.

---

### **5. Posizionamento (Seek)**

L’operazione di **seek** consente di spostare il **puntatore corrente** di lettura/scrittura all’interno del file, senza eseguire trasferimenti di dati.

#### **Modalità tipiche**

- Spostamento **assoluto** → rispetto all’inizio del file.
    
- Spostamento **relativo** → rispetto alla posizione corrente.
    
- Spostamento **dalla fine** → utile per accodare nuovi dati.

#### **Funzioni principali**

- Permette **accesso diretto** ai dati in qualsiasi punto del file.
    
- Migliora le prestazioni nei sistemi che supportano **file di grandi dimensioni o accesso casuale**.

---

### **6. Chiusura**

La **chiusura del file** segna la fine delle operazioni di accesso da parte di un processo.

#### **Fasi**

1. **Aggiornamento dei metadati**
    
    - Vengono salvati i dati relativi a dimensione, timestamp, e posizione finale del puntatore.
    
2. **Scrittura del contenuto residuo (flush)**
    
    - Eventuali byte ancora presenti nel buffer vengono **scritti definitivamente su disco**.
    
3. **Rilascio delle strutture di gestione**
    
    - Le voci del file nelle **tabelle dei file aperti** (di sistema e di processo) vengono **rimosse o marcate come libere**.

#### **Importanza**

La chiusura garantisce la **consistenza dei dati** e libera le **risorse di memoria** utilizzate per la gestione del file.

---

### **7. Sintesi operativa**

|Operazione|Funzione principale|Risultato|
|---|---|---|
|**Apertura**|Inizializza il collegamento tra processo e file|Ritorna un file descriptor|
|**Lettura**|Trasferisce dati dal file al buffer utente|Aggiorna il puntatore|
|**Scrittura**|Trasferisce dati dal buffer al file|Aggiorna e alloca blocchi se necessario|
|**Seek**|Riposiziona il puntatore corrente|Nessun trasferimento di dati|
|**Chiusura**|Libera risorse e aggiorna i metadati|File salvato e buffer svuotati|

---

### **8. Conclusione**

La gestione dell’astrazione dei file si basa su un insieme di **operazioni standardizzate** che il sistema operativo fornisce per **manipolare in modo sicuro ed efficiente i file**.  
Attraverso apertura, lettura, scrittura, posizionamento e chiusura, il sistema mantiene la **coerenza tra la rappresentazione logica e quella fisica dei dati**, completando così il ciclo di vita di un file all’interno del file system.
