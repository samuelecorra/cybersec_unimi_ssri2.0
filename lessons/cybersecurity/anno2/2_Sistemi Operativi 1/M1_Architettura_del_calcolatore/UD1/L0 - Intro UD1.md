# Lezione 0 - Intro Unità Didattica 1 – Architettura e funzionamento dei sistemi di elaborazione

### 1. Introduzione

Questa unità didattica costituisce il vero **ripasso tecnico** delle fondamenta dell’elaborazione automatica.  
Il suo scopo non è semplicemente rivedere nozioni già note, ma **ricontestualizzarle dal punto di vista del sistema operativo**, che si trova a dover gestire ogni componente della macchina in modo coordinato e controllato.

Durante il corso di _Architettura degli Elaboratori_ abbiamo già studiato come **funziona internamente un calcolatore**:  
abbiamo visto com’è fatta una **CPU**, come viene organizzata la **memoria**, come i dati vengono trasferiti attraverso il **bus di sistema** e come avviene il **ciclo di esecuzione delle istruzioni**.  
Ora, questi stessi concetti torneranno utili per comprendere **come il sistema operativo sfrutta tali meccanismi** per realizzare la propria gestione delle risorse.

---
### 2. Obiettivi dell’unità

Al termine di questa unità dovremo essere in grado di:

1. **Conoscere l’architettura e il funzionamento della macchina di Von Neumann**, comprendendo come i programmi e i dati condividano la stessa memoria e come questo modello costituisca la base di ogni sistema operativo moderno.
    
2. **Comprendere il funzionamento delle chiamate di procedura e delle interruzioni (interrupt)**, ossia i due meccanismi fondamentali che consentono al processore di:
    
    - eseguire codice in modo strutturato e modulare (procedure e stack),
        
    - reagire a eventi esterni o eccezioni interne (interrupt hardware e software).
        
3. **Conoscere le tecniche di connessione alle periferiche**, cioè come il processore comunica con dispositivi esterni tramite:
    
    - I/O programmato,
        
    - I/O tramite interrupt,
        
    - e **DMA (Direct Memory Access)**, la tecnica più efficiente e autonoma.
        
4. **Comprendere l’architettura e il funzionamento delle reti informatiche**, ossia come i sistemi di elaborazione si connettono e cooperano in rete, creando l’ambiente distribuito che il sistema operativo deve supportare.

---
### 3. Dalla teoria di Von Neumann alla gestione del SO

La **macchina di Von Neumann** è il punto di partenza di ogni sistema operativo moderno.  
Il suo principio fondamentale è che **programmi e dati risiedono nella stessa memoria** e vengono gestiti tramite un ciclo operativo costante:

$$  
\text{Ciclo di elaborazione} =  
\begin{cases}  
\text{Fetch:} & \text{preleva l’istruzione dalla memoria} \\\\  
\text{Decode:} & \text{interpreta l’istruzione} \\\\ 
\text{Execute:} & \text{esegue l’operazione richiesta}  
\end{cases}  
$$

Il sistema operativo si innesta su questo ciclo, **decidendo quali programmi devono essere eseguiti, quando e con quali risorse**.  
Per farlo, si serve di meccanismi hardware come **le chiamate di procedura** (per il controllo del flusso) e **gli interrupt** (per la gestione asincrona degli eventi).

---
### 4. Interruzioni e chiamate di procedura

Le **chiamate di procedura** rappresentano il modo in cui il processore gestisce la **modularità del codice**:  
quando una funzione viene invocata, la CPU salva lo stato corrente sullo **stack**, salta alla procedura e poi torna indietro grazie al **salvataggio del contesto** (indirizzo di ritorno, registri, parametri).

Gli **interrupt**, invece, sono eventi che **interrompono il normale flusso di esecuzione** per permettere al processore di gestire un’operazione urgente, come:

- una richiesta da parte di una periferica,
    
- un errore hardware,
    
- o una chiamata al sistema operativo (system call).

Il sistema operativo è proprio il software che **risponde a tali interruzioni**, determinando **quale codice eseguire e in quale ordine**, garantendo così la stabilità e la sicurezza del sistema.

---
### 5. Connessione e gestione delle periferiche

Ogni sistema operativo deve poter **dialogare con l’hardware esterno**: tastiera, disco, rete, stampante, ecc.  
Questo avviene attraverso **tecniche di connessione** che permettono di bilanciare **velocità, efficienza e controllo**:

1. **I/O programmato:** il processore attende che la periferica completi l’operazione.
    
2. **I/O con interrupt:** la periferica segnala la fine dell’operazione al processore, liberandolo durante l’attesa.
    
3. **DMA (Direct Memory Access):** il dispositivo trasferisce direttamente i dati in memoria senza passare per la CPU, riducendo il carico di lavoro del processore.

Il sistema operativo controlla e coordina questi meccanismi tramite **driver**, **buffer** e **gestione degli interrupt**.

---
### 6. Architettura di rete e sistemi distribuiti

Infine, un moderno sistema operativo non gestisce più un singolo calcolatore isolato, ma un **insieme di sistemi collegati in rete**.  
Conoscere i **principi base dell’architettura di rete** — protocolli, livelli di comunicazione, indirizzamento, scambio di pacchetti — è indispensabile per comprendere come il sistema operativo:

- gestisce connessioni e socket,
    
- sincronizza processi distribuiti,
    
- e garantisce la sicurezza delle comunicazioni.

Questi concetti si collegheranno più avanti ai temi dei **sistemi distribuiti e della virtualizzazione**, presenti nel secondo modulo del corso.

---
### 7. Conclusione

L’unità didattica serve quindi a consolidare il quadro tecnico su cui poggia l’intero corso:  
una visione chiara di **come funziona la macchina sottostante**, dei **meccanismi che regolano il controllo del flusso** e dei **canali di comunicazione tra processore e mondo esterno**.

Solo comprendendo a fondo questi aspetti sarà possibile affrontare, nei moduli successivi, **la logica del sistema operativo come gestore e virtualizzatore** di tutte queste risorse fisiche.