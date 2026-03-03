# **Lezione 4: Misure tecniche per certificazione di processi e software**

### 1. Introduzione: certificazione dei processi software e contenziosi

La certificazione dei processi e del software rappresenta un tema centrale nella gestione della sicurezza dei sistemi informativi, in particolare quando i sistemi informatici sono utilizzati per attività che producono effetti economici o giuridici rilevanti.

La lezione introduce un caso di studio relativo ai concorsi a premi, nei quali un software deve effettuare estrazioni casuali per determinare i vincitori. Come evidenziato nella slide iniziale, tali sistemi possono operare in modalità instant win oppure tramite estrazione finale, ma in entrambi i casi devono garantire correttezza, affidabilità e verificabilità del funzionamento.

I problemi principali riguardano:

- Il numero di premi da assegnare
    
- Eventuali malfunzionamenti dell’infrastruttura
    
- La gestione delle contestazioni da parte degli utenti
    

Questi aspetti dimostrano che lo sviluppo del software non deve considerare solo la funzionalità operativa, ma anche la possibilità di dimostrare il corretto funzionamento in caso di contenzioso.

---

### 2. Regolamento del concorso e progettazione del software

Il regolamento del concorso costituisce la base delle specifiche funzionali del software.

Come illustrato nella slide a pagina 2, per partecipare al concorso l’utente deve possedere una conferma di acquisto ricevuta via email, accedere al sito web dedicato, registrarsi inserendo i propri dati e il codice alfanumerico ricevuto.

Il codice rappresenta un elemento fondamentale perché consente al sistema di verificare la validità della partecipazione. Il sistema deve garantire che:

- Ogni utente si registri una sola volta
    
- Ogni codice sia utilizzato una sola volta
    
- Un utente possa partecipare più volte con codici differenti
    

Dopo la registrazione, il sistema comunica immediatamente l’esito della giocata, che può essere vincente o non vincente, verificando la corrispondenza del codice con quelli generati dal sistema.

La società organizzatrice mantiene inoltre il potere di effettuare controlli e invalidare registrazioni non conformi al regolamento.

La progettazione del software deve quindi essere coerente con le specifiche del regolamento e prevedere anche la gestione delle possibili contestazioni future.

---

### 3. Processo base di gestione della giocata

Il diagramma presente a pagina 3 descrive il processo base di gestione della partecipazione al concorso.

Il flusso prevede:

1. Richiesta di giocata da parte dell’utente
    
2. Estrazione del risultato
    
3. Registrazione dell’esito nel log di debug
    
4. Registrazione dell’esito nel database tramite transazione
    
5. Notifica dell’esito all’utente
    
6. Invio di email e PEC per conservazione con marca temporale
    
7. Comunicazione dell’esito nella pagina web
    

Nel caso di errore del database, il sistema comunica l’errore all’utente e consente di ripetere la giocata.

Il problema principale evidenziato nella slide è che nel log potrebbe risultare una giocata vincente che non viene però registrata nel database a causa di un errore. L’utente, ricevendo un messaggio di errore, potrebbe contestare il risultato sostenendo di aver vinto.

Questo scenario evidenzia un rischio probatorio importante: l’incoerenza tra log e database può generare contenziosi.

---

### 4. Processo evoluto di gestione della giocata

Per risolvere il problema precedente viene proposto un processo evoluto, illustrato sempre a pagina 3.

In caso di errore del database, invece di perdere l’informazione, il sistema registra la query non eseguita e la inserisce in una coda di operazioni da completare successivamente.

Un processo batch periodico o manuale consente poi di completare le operazioni rimaste in sospeso e popolare il database.

Inoltre, viene inviata una notifica all’amministratore di sistema per segnalare l’errore.

Questo approccio migliora la resilienza del sistema, ma introduce una nuova criticità: se il database presenta problemi, può essere rischioso continuare a consentire la partecipazione degli utenti.

---

### 5. Processo evoluto completo con controllo preventivo del database

La slide a pagina 4 introduce una versione ancora più evoluta del processo.

Prima di consentire la giocata, il sistema verifica lo stato del database. Se il database non è disponibile, il sistema comunica all’utente che il servizio è in manutenzione e non permette la partecipazione.

Se il database è funzionante, la procedura continua normalmente.

In caso di errore durante la registrazione:

- Le query non eseguite vengono inserite in una coda
    
- L’amministratore riceve una notifica
    
- Un processo batch periodico svuota la coda e aggiorna il database
    
- Viene effettuata una verifica tramite query SELECT per controllare la correttezza dei dati
    

Se i dati non risultano corretti, il sistema prevede procedure di correzione e ripetizione.

Questo modello introduce controlli preventivi e meccanismi di recupero che aumentano l’affidabilità e la dimostrabilità del sistema.

La presenza di una dichiarazione peritale nel diagramma evidenzia l’obiettivo finale: dimostrare tecnicamente il corretto funzionamento del sistema in caso di contenzioso.

---

### 6. Concetto di prove precostituite nei sistemi software

Uno dei concetti più importanti della lezione è la creazione di prove precostituite.

Il software deve essere progettato non solo per funzionare correttamente, ma anche per produrre evidenze tecniche affidabili che possano essere utilizzate per dimostrare il comportamento del sistema.

Tra gli strumenti utilizzati vi sono:

- Logging strutturato
    
- Conservazione temporale tramite PEC o sistemi equivalenti
    
- Registrazioni su database con controlli di integrità
    
- Procedure di verifica periodica
    

Questo approccio consente di ridurre il rischio di contestazioni e di dimostrare la correttezza delle operazioni effettuate dal sistema.

La certificazione del software si basa proprio sulla capacità di produrre tali evidenze.

---

### 7. Sintesi finale

La lezione ha presentato un caso di studio relativo ai concorsi a premi per illustrare le misure tecniche necessarie alla certificazione di processi e software.

Sono stati analizzati diversi modelli di gestione della giocata, evidenziando i problemi legati alla coerenza dei dati e alle possibili contestazioni, e introducendo soluzioni evolute basate su controlli preventivi, code di operazioni e verifiche periodiche.

Il messaggio fondamentale della lezione è che nello sviluppo dei sistemi informatici non bisogna considerare soltanto la funzionalità operativa, ma anche la capacità di generare prove attendibili e verificabili, che possano essere utilizzate in caso di contenzioso o certificazione del software.