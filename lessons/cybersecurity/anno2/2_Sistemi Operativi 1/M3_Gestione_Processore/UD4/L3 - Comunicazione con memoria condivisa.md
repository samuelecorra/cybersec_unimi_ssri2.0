# **M3 UD4 Lezione 3 - Comunicazione con memoria condivisa**

### **1. Introduzione**

La **memoria condivisa** è uno dei metodi più diretti e veloci per permettere a due o più processi di **comunicare** e **cooperare**.  
L’idea è semplice: invece di scambiarsi messaggi o file, i processi **condividono una stessa area di memoria centrale**, in cui scrivono e leggono dati comuni.

Tuttavia, la semplicità apparente nasconde problematiche importanti di **consistenza** e **sincronizzazione**.  
Infatti, se più processi accedono simultaneamente alla stessa area, possono verificarsi **conflitti di lettura/scrittura** che compromettono la correttezza dei dati.

---
### **2. Tipologie di condivisione**

Esistono due modi principali per realizzare la condivisione della memoria:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Condivisione di variabili globali.} \\\\  
\textbf{2.}~ & \text{Condivisione di aree di memoria o buffer.}  
\end{cases}  
$$

Entrambe le tecniche permettono a processi distinti di accedere a dati comuni, ma differiscono per **livello di astrazione** e **complessità di gestione**.

---
### **3. Condivisione di variabili globali**

#### **3.1. Concetto**

Due processi possono comunicare leggendo e scrivendo su **variabili globali condivise**.  
Questo approccio è semplice e intuitivo, ma richiede che il sistema operativo gestisca correttamente l’accesso concorrente a tali variabili.

#### **3.2. Problemi principali**

$$  
\begin{cases}  
\textbf{Identificazione dei processi comunicanti:}~ & \text{bisogna stabilire un canale diretto tra mittente e destinatario.} \\\\  
\textbf{Consistenza delle operazioni:}~ & \text{una scrittura può interferire con una lettura simultanea.} \\\\  
\textbf{Mutua esclusione:}~ & \text{occorre sincronizzare l’accesso per evitare condizioni di race.}  
\end{cases}  
$$

La **mutua esclusione** è essenziale: un solo processo per volta deve poter modificare la variabile condivisa.  
Questo verrà gestito, come vedremo, tramite **semafori**, **mutex** o **monitor**.

---
### **4. Realizzazione della memoria condivisa**

Esistono due approcci principali per implementare la memoria condivisa:

$$  
\begin{cases}  
\textbf{1. Area copiata dal sistema operativo:}~ & \text{i dati vengono trasferiti tramite copia tra spazi utente e kernel.} \\\\  
\textbf{2. Area fisicamente condivisa:}~ & \text{i processi accedono direttamente alla stessa porzione di memoria fisica.}  
\end{cases}  
$$

Nel primo caso, la comunicazione è **più sicura ma più lenta**, perché avviene tramite il kernel.  
Nel secondo caso, è **molto più veloce**, ma richiede **sincronizzazione accurata** per evitare corruzione dei dati.

---
### **5. Condivisione di buffer**

#### **5.1. Concetto**

Un **buffer condiviso** è un’area di memoria che funge da **zona intermedia** per lo scambio di informazioni tra processi.  
È tipicamente utilizzato per implementare il modello **produttore-consumatore**.

#### **5.2. Problemi principali**

$$  
\begin{cases}  
\textbf{Identificazione dei processi:}~ & \text{il buffer deve essere noto a entrambi (comunicazione diretta).} \\\\  
\textbf{Consistenza degli accessi:}~ & \text{più scritture o letture simultanee possono corrompere i dati.} \\\\  
\textbf{Mutua esclusione:}~ & \text{serve una forma di sincronizzazione per regolare l’accesso.}  
\end{cases}  
$$

#### **5.3. Realizzazioni**

$$  
\begin{cases}  
\textbf{Buffer gestito dal sistema operativo:}~ & \text{i dati vengono copiati tra kernel e processi.} \\\\  
\textbf{Buffer fisicamente condiviso:}~ & \text{i processi accedono direttamente alla stessa area di memoria.}  
\end{cases}  
$$

---
### **6. Il modello Produttore–Consumatore**

Il paradigma **Produttore–Consumatore** è il classico esempio di comunicazione tramite memoria condivisa.  
Rappresenta due processi cooperanti:

- il **produttore**, che genera dati e li scrive nel buffer;
    
- il **consumatore**, che legge e utilizza tali dati.

#### **6.1. Struttura del buffer**

Il buffer può avere **capacità illimitata** o **limitata**.  
Nel caso più comune (buffer limitato), bisogna evitare che:

- il produttore scriva su un buffer pieno;
    
- il consumatore legga da un buffer vuoto.

---
#### **6.2. Attività del produttore e del consumatore**

$$  
\begin{cases}  
\textbf{Produttore:}~ &  
\begin{cases}  
\text{1. Genera un’informazione elementare.} \\\\  
\text{2. La memorizza nel buffer condiviso.}  
\end{cases} \\\\  
\textbf{Consumatore:}~ &  
\begin{cases}  
\text{1. Legge un’informazione dal buffer.} \\\\  
\text{2. La elabora o la utilizza.}  
\end{cases}  
\end{cases}  
$$

Questa interazione è continua e ciclica, e richiede **sincronizzazione** per evitare accessi concorrenti impropri.

---
### **7. Esempio pratico in Java**

Un semplice esempio di implementazione di buffer limitato in Java è mostrato di seguito (schema concettuale tratto da `BoundedBuffer.java`):

```java
public class BoundedBuffer {
    private static final int BUFFER_SIZE = 5;
    private int count; // numero di elementi nel buffer
    private int in;    // posizione libera successiva
    private int out;   // posizione piena successiva
    private Object[] buffer;

    public BoundedBuffer() {
        count = 0;
        in = 0;
        out = 0;
        buffer = new Object[BUFFER_SIZE];
    }

    // Metodo del produttore
    public void insert(Object item) {
        while (count == BUFFER_SIZE); // attesa attiva: buffer pieno
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;
    }

    // Metodo del consumatore
    public Object remove() {
        while (count == 0); // attesa attiva: buffer vuoto
        Object item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;
        return item;
    }
}
```

🔎 Questo esempio mostra la logica di base del modello **Produttore–Consumatore**, ma **non gestisce la sincronizzazione reale**:  
in un sistema operativo vero, le sezioni di attesa attiva (`while(...) ;`) verrebbero sostituite da **meccanismi di sincronizzazione efficienti**, come **semafori** o **monitor**.

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Memoria condivisa:}~ & \text{area comune a più processi per lo scambio di dati.} \\\\  
\textbf{Variabili globali:}~ & \text{metodo semplice ma rischioso senza sincronizzazione.} \\\\  
\textbf{Buffer condiviso:}~ & \text{metodo strutturato per scambi produttore-consumatore.} \\\\  
\textbf{Problemi:}~ & \text{consistenza, identificazione e mutua esclusione.}  
\end{cases}  
$$

---
### **9. Conclusione**

La comunicazione tramite memoria condivisa è **molto efficiente**, ma richiede **disciplina rigorosa**:  
i processi devono cooperare senza violare la coerenza dei dati.

È per questo che, nei sistemi moderni, la memoria condivisa è quasi sempre accompagnata da **meccanismi di sincronizzazione** (semafori, monitor, mutex), che verranno approfonditi nelle lezioni successive.