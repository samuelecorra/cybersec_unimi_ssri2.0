# **M3 UD5 Lezione 6 - Transazioni atomiche**

### **1. Introduzione**

La lezione conclusiva di questa unità affronta il concetto di **transazione atomica**, uno dei principi fondamentali della sincronizzazione nei sistemi complessi.  
Le transazioni atomiche garantiscono che una sequenza di operazioni venga eseguita **tutta intera o per nulla**, senza lasciare il sistema in uno stato incoerente.

Sono particolarmente importanti nei **sistemi concorrenti e distribuiti**, nei quali più processi possono modificare contemporaneamente risorse condivise o basi di dati.

---
### **2. Definizione di transazione**

Una **transazione** è un insieme di istruzioni che, nel loro complesso, realizzano **un’unica funzione logica**.  
L’esecuzione di una transazione deve essere **indivisibile** dal punto di vista del sistema.

Esempio concettuale (trasferimento di denaro tra due conti):

```plaintext
read (contoA)
read (contoB)
contoA = contoA - 100
contoB = contoB + 100
write (contoA)
write (contoB)
commit
```

In questo esempio, o entrambe le scritture (`write`) vengono completate correttamente, oppure **nessuna modifica** diventa permanente.

---
### **3. Atomicità**

L’**atomicità** di una transazione garantisce che la sequenza di operazioni sia trattata come **un’unica operazione indivisibile**:

$$  
\begin{cases}  
\textbf{Commit:}~ & \text{tutte le operazioni completate correttamente → effetti permanenti.} \\\\  
\textbf{Abort:}~ & \text{errore o interferenza → annullamento di tutte le operazioni (roll-back).}  
\end{cases}  
$$

In altri termini, **o tutto o nulla**:  
le modifiche diventano effettive solo se la transazione termina con successo.

---
### **4. Tipologie di archivi**

Per comprendere come le transazioni vengano gestite, è necessario distinguere i tipi di **memoria** coinvolti.

$$  
\begin{cases}  
\textbf{Archivio volatile:}~ & \text{memoria che si perde allo spegnimento (RAM, cache).} \\\\  
\textbf{Archivio non volatile:}~ & \text{memoria persistente (dischi magnetici, SSD, nastri).} \\\\  
\textbf{Archivio stabile:}~ & \text{memoria ridondata su più archivi non volatili → alta affidabilità.}  
\end{cases}  
$$

Le transazioni atomiche si appoggiano su **archivi stabili**, che consentono di **recuperare lo stato precedente** in caso di errore o crash di sistema.

---
### **5. Transazioni atomiche individuali**

Quando una singola transazione deve essere garantita come atomica, il sistema operativo utilizza due tecniche principali:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Logging (registrazione).} \\\\  
\textbf{2.}~ & \text{Check pointing (punti di verifica).}  
\end{cases}  
$$

---
### **6. Logging (Write-Ahead Logging)**

#### **6.1. Concetto**

Il **log** è un registro che conserva in un archivio stabile le informazioni sulle transazioni e sul loro stato di esecuzione.

Ogni voce del log contiene:  
$$  
\langle \text{nome della transazione},~ \text{oggetto dei dati},~ \text{valore vecchio},~ \text{valore nuovo} \rangle  
$$

#### **6.2. Meccanismo**

Prima di modificare i dati, il sistema **scrive nel log** le informazioni necessarie per poter ripristinare (undo) o ripetere (redo) le operazioni, se necessario.

$$  
\begin{cases}  
\text{Inizio transazione:} & \langle T_i~\text{starts} \rangle \\\\  
\text{Fine transazione:} & \langle T_i~\text{commits} \rangle  
\end{cases}  
$$

#### **6.3. Recupero dal log**

$$  
\begin{cases}  
\textbf{undo}(T_i):~ & \text{riporta i dati ai valori precedenti.} \\\\  
\textbf{redo}(T_i):~ & \text{ripete le modifiche fino ai nuovi valori.}  
\end{cases}  
$$

In caso di fallimento:

- se il log contiene `<T_i starts>` ma non `<T_i commits>` → **undo**
    
- se il log contiene entrambi → **redo**

---
### **7. Check Pointing (punti di verifica)**

#### **7.1. Problema del log**

Un log molto lungo può rendere il ripristino estremamente lento, perché il sistema deve scorrere tutte le transazioni registrate.

#### **7.2. Soluzione**

Il **check pointing** riduce i tempi di recupero salvando periodicamente su memoria stabile:

- i record del log più recenti,
    
- i dati modificati,
    
- e un marcatore `<checkpoint>` che identifica l’ultimo stato coerente del sistema.

#### **7.3. Ripristino basato su checkpoint**

In caso di fallimento:  
$$  
\begin{cases}  
\text{Per ogni transazione dopo il checkpoint:} \\\\  
\quad \text{se } <T_i~\text{starts}>~\text{ma non } <T_i~\text{commits}>~\Rightarrow~\textbf{undo}(T_i) \\\\  
\quad \text{se } <T_i~\text{starts}>~\text{e } <T_i~\text{commits}>~\Rightarrow~\textbf{redo}(T_i)  
\end{cases}  
$$

---
### **8. Transazioni atomiche concorrenti**

Finora abbiamo considerato una singola transazione.  
In un sistema reale, però, **più transazioni atomiche possono essere eseguite contemporaneamente**, e devono essere coordinate in modo da produrre un risultato **equivalente a un’esecuzione seriale**.

Questo principio prende il nome di **serializzabilità**.

---
### **9. Serializzabilità**

#### **9.1. Concetto**

Due o più transazioni concorrenti sono dette **serializzabili** se la loro esecuzione parallela produce **lo stesso risultato** che si otterrebbe eseguendole **in sequenza** in qualche ordine.

$$  
T_1 \parallel T_2 \quad \Rightarrow \quad T_1 \circ T_2 \quad \text{o} \quad T_2 \circ T_1  
$$

L’obiettivo è dunque garantire la **coerenza globale** dei dati anche in presenza di transazioni concorrenti.

---
### **10. Tecniche per la serializzabilità**

Esistono due livelli principali di controllo:

$$  
\begin{cases}  
\textbf{A livello di transazione:}~ & \text{ogni transazione è trattata come una sezione critica.} \\\\  
\textbf{A livello di operazioni:}~ & \text{si controlla la concorrenza tra le singole operazioni.}  
\end{cases}  
$$

I principali approcci operativi sono:
$$  
\begin{cases}  
\text{Protocolli basati su lock (blocco)} \\\\  
\text{Protocolli basati su timestamp (marca temporale)}  
\end{cases}  
$$

---
### **11. Protocolli basati su lock**

#### **11.1. Concetto di lock**

Un **lock** è una variabile associata a un dato che indica se esso è **libero** o **occupato**.

$$  
\text{Lock} =  
\begin{cases}  
\text{libero}, & \text{accesso consentito;} \\\\  
\text{in uso}, & \text{accesso negato (transazione in attesa).}  
\end{cases}  
$$

**Tipi di lock:**

- **condiviso (shared):** più transazioni possono leggere contemporaneamente;
    
- **esclusivo (exclusive):** una sola transazione può leggere o scrivere.

---
#### **11.2. Protocollo di lock di base**

1. Una transazione $T_i$ richiede un lock su un dato $Q$.
    
2. Se il lock è libero → accesso concesso.
    
3. Se il lock è occupato:
    
    - se $T_i$ chiede un lock esclusivo → attende;
        
    - se $T_i$ chiede un lock condiviso → può accedere solo se il lock è già condiviso.

Questo protocollo **non garantisce sempre la serializzabilità**.

---
#### **11.3. Protocollo di lock a due fasi (2PL)**

Il **Two-Phase Locking (2PL)** garantisce la serializzabilità grazie a una regola semplice:

$$  
\begin{cases}  
\textbf{Fase di crescita:}~ & \text{la transazione può ottenere lock, ma non rilasciarli.} \\\\  
\textbf{Fase di contrazione:}~ & \text{la transazione può rilasciare lock, ma non acquisirne di nuovi.}  
\end{cases}  
$$

Questa tecnica assicura un ordine coerente delle operazioni, ma **non previene gli stalli (deadlock)**.

---
### **12. Protocolli basati su timestamp**

#### **12.1. Concetto di timestamp**

Ogni transazione $T_i$ riceve una **marca temporale (timestamp)** $TS(T_i)$ che rappresenta il momento della sua creazione.  
Essa è **univoca** e stabilisce la **priorità temporale** della transazione.

$$  
TS(T_i) < TS(T_j) \Rightarrow T_i \text{ è più vecchia di } T_j  
$$

#### **12.2. Tipi di timestamp**

$$  
\begin{cases}  
\text{W-timestamp}(Q):~ & \text{marca temporale dell’ultima scrittura su } Q. \\\\  
\text{R-timestamp}(Q):~ & \text{marca temporale dell’ultima lettura su } Q.  
\end{cases}  
$$

#### **12.3. Regole operative**

$$  
\begin{cases}  
\textbf{read}(Q):~ &  
\begin{cases}  
\text{Se } TS(T_i) < W\text{-timestamp}(Q) \Rightarrow \text{lettura negata, roll-back.} \\\\  
\text{Altrimenti } R\text{-timestamp}(Q) = \max(R\text{-timestamp}(Q), TS(T_i)).  
\end{cases} \\\\\\  
\textbf{write}(Q):~ &  
\begin{cases}  
\text{Se } TS(T_i) < R\text{-timestamp}(Q) \Rightarrow \text{scrittura negata, roll-back.} \\\\  
\text{Se } TS(T_i) < W\text{-timestamp}(Q) \Rightarrow \text{scrittura negata, roll-back.} \\\\  
\text{Altrimenti la scrittura è accettata.}  
\end{cases}  
\end{cases}  
$$

In questo modo, l’ordine di serializzazione tra due transazioni in conflitto è **determinato dai rispettivi timestamp**.

---
### **13. Sintesi finale**

$$  
\begin{cases}  
\textbf{Transazione atomica:}~ & \text{sequenza indivisibile di operazioni (commit o abort).} \\\\  
\textbf{Logging:}~ & \text{registro di tutte le modifiche per recupero (undo/redo).} \\\\  
\textbf{Check Pointing:}~ & \text{salvataggio periodico dello stato coerente.} \\\\  
\textbf{Serializzabilità:}~ & \text{equivalenza con un’esecuzione sequenziale.} \\\\  
\textbf{Locking e Timestamp:}~ & \text{tecniche per garantire coerenza tra transazioni concorrenti.}  
\end{cases}  
$$

---
### **14. Conclusione**

Le **transazioni atomiche** rappresentano la massima espressione della sincronizzazione nei sistemi operativi e nei database.  
Esse garantiscono che i dati rimangano **consistenti e affidabili** anche in presenza di **fallimenti** o **concorrenza**.

Attraverso meccanismi come **logging**, **check pointing**, **locking** e **timestamp**,  
i sistemi moderni assicurano che ogni gruppo di operazioni si comporti **come un’unica unità logica**,  
mantendendo la coerenza del sistema anche nelle condizioni più complesse.