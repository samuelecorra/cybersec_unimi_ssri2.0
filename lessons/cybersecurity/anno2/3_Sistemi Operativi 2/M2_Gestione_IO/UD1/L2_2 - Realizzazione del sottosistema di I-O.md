# **M2 UD1 Lezione 2 - Realizzazione del sottosistema di I/O (parte 2)**

---

### **1. Introduzione**

Questa seconda parte approfondisce gli aspetti relativi alle **strutture dati** e all'**implementazione** del sottosistema di ingresso/uscita.

Dopo aver visto le funzioni generali di gestione delle periferiche, l'attenzione si sposta su:

- strutture dati mantenute dal kernel;
- informazioni specifiche dei processi utente;
- realizzazione di una richiesta di I/O;
- ottimizzazione delle prestazioni complessive del sottosistema.

> 📌 La realizzazione del sottosistema di I/O richiede strutture dati condivise nel kernel e strutture specifiche per ogni processo, collegate tra loro tramite puntatori e descrittori.

---

### **2. Strutture dati del kernel**

All'interno della gestione dell'I/O vengono create diverse strutture dati a supporto delle operazioni sui dispositivi.

Nel kernel, in memoria centrale, viene mantenuta una tabella che descrive:

- tutti i file aperti;
- tutte le periferiche in uso;
- le risorse attualmente occupate;
- le operazioni lecite su ciascuna risorsa;
- i puntatori alle procedure del sistema operativo che implementano tali operazioni.

#### **2.1. Tabella globale dei file e delle periferiche aperte**

La tabella globale del kernel consente al sistema operativo di sapere quali risorse sono in uso e come devono essere gestite.

Per ogni file o periferica aperta vengono conservati:

- stato della risorsa;
- tipo di risorsa;
- diritti o operazioni consentite;
- riferimenti al driver o alle routine di gestione;
- informazioni necessarie per completare letture, scritture e controlli.

> 💡 La tabella globale evita di duplicare informazioni comuni quando più processi usano la stessa risorsa.

#### **2.2. Buffer, cache e spooling nel kernel**

Sempre nello spazio del kernel vengono conservati:

- buffer per la gestione bufferizzata delle periferiche;
- aree di cache per dati già letti;
- riferimenti e strutture per la gestione dello spooling;
- code di richieste pendenti;
- informazioni di stato dei driver.

Queste strutture permettono di separare la richiesta logica del processo dall'effettiva interazione fisica con la periferica.

---

### **3. Strutture dati del processo utente**

Ogni processo mantiene una propria **tabella dei file aperti**.

Questa tabella contiene informazioni elementari specifiche del processo e puntatori alla tabella globale mantenuta dal sistema operativo.

#### **3.1. Informazioni specifiche del processo**

Nello spazio utente o nelle strutture associate al processo vengono mantenute informazioni specifiche come:

- descrittori usati dal processo;
- posizione corrente nella risorsa, quando rilevante;
- modalità con cui il processo ha aperto la risorsa;
- puntatori alle strutture globali del kernel.

Questo consente a processi diversi di condividere una stessa risorsa mantenendo comunque una visione specifica e indipendente.

> 📌 Due processi possono usare la stessa risorsa condivisa, ma ciascuno conserva le proprie informazioni locali di accesso.

---

### **4. Realizzazione di una richiesta di I/O**

Una richiesta di ingresso/uscita viene attivata da un processo tramite una **chiamata di sistema**.

Da quel momento il sistema operativo valuta se può rispondere immediatamente oppure se deve accedere realmente alla periferica.

#### **4.1. Risposta immediata del sistema operativo**

In alcuni casi il sistema operativo può completare la richiesta senza interpellare la periferica.

Questo accade, per esempio, quando:

- l'informazione richiesta è già presente in cache;
- la periferica è già nota come guasta e non ancora riparata;
- l'operazione può essere risolta usando informazioni già disponibili nel kernel.

Nel primo caso, il sistema operativo restituisce direttamente i dati richiesti. Nel secondo caso, termina immediatamente la richiesta segnalando l'impossibilità di completarla.

$$
\text{richiesta I/O} =
\begin{cases}
\text{risposta da cache o kernel} & \text{se risolvibile immediatamente} \\
\text{accesso alla periferica} & \text{se serve trasferimento fisico}
\end{cases}
$$

> ✅ Se la risposta è già disponibile nel kernel, evitare l'accesso alla periferica riduce drasticamente il tempo di completamento.

#### **4.2. Accesso effettivo alla periferica**

Se la richiesta non può essere soddisfatta immediatamente, il sistema operativo deve accedere fisicamente alla periferica.

La richiesta attraversa i livelli del sottosistema di I/O:

1. livello **device independent**;
2. livello **device dependent**;
3. gestione del **canale di comunicazione**;
4. periferica fisica.

La periferica riceve la richiesta e, dopo un certo tempo, la soddisfa. La gestione del canale di comunicazione gestisce le interruzioni, acquisisce le informazioni restituite e le passa ai livelli superiori.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3. Completamento della richiesta**

Quando il risultato torna ai livelli superiori, il sistema operativo completa l'operazione:

- aggiorna le strutture dati interne;
- risveglia o notifica il processo richiedente;
- restituisce i dati in caso di completamento positivo;
- restituisce una segnalazione di errore in caso di fallimento.

Il risultato viene presentato in modo standard, indipendente dalla specifica periferica e, a livello più alto, anche dalla tipologia del dispositivo.

> 📌 Il processo vede una risposta uniforme; la complessità del dispositivo, del driver e del canale resta nascosta nel kernel.

---

### **5. Prestazioni dell'I/O**

Le prestazioni del sottosistema di ingresso/uscita sono fondamentali per le prestazioni dell'intero sistema di elaborazione.

La gestione dell'I/O è critica perché coinvolge:

- esecuzione dei driver;
- schedulazione dei processi;
- gestione delle interruzioni;
- cambiamenti di contesto;
- copie di dati;
- accessi a periferiche spesso molto più lente della CPU.

#### **5.1. Esecuzione efficiente dei driver**

I driver devono essere eseguiti in modo efficiente perché si trovano nel percorso critico tra richiesta del processo e completamento dell'operazione fisica.

Un driver lento o mal progettato può rallentare l'intero sistema, anche quando la periferica non è il solo collo di bottiglia.

#### **5.2. Schedulazione e bilanciamento CPU/I-O**

La schedulazione dei processi influenza fortemente le prestazioni dell'I/O.

Il sistema deve bilanciare:

- attività di elaborazione;
- attività di ingresso/uscita;
- presenza di processi pronti;
- attese dovute a periferiche.

L'obiettivo è mantenere il processore impegnato mentre le periferiche operano, evitando sia CPU inattiva sia code eccessive sulle periferiche.

> 💡 Un buon sistema operativo sovrappone computazione e I/O: mentre un processo aspetta la periferica, un altro può usare la CPU.

---

### **6. Strategie di ottimizzazione**

Per migliorare le prestazioni del sottosistema di I/O si possono adottare diverse strategie.

#### **6.1. Ridurre i cambiamenti di contesto**

Troppi cambiamenti di contesto, spesso causati da interruzioni frequenti o da una schedulazione non adeguata, degradano le prestazioni globali.

Il sistema può ridurli operando sulla schedulazione dei processi e sulla gestione delle periferiche.

#### **6.2. Ridurre le copie di dati**

La copiatura dei dati tra periferica, kernel e spazio utente può essere costosa.

Per ridurre questo overhead, il sistema può:

- minimizzare gli spostamenti in memoria;
- usare caching;
- evitare accessi fisici ripetuti alla periferica;
- adottare meccanismi che riducano passaggi intermedi non necessari.

#### **6.3. Ridurre la frequenza delle interruzioni**

Le interruzioni sono necessarie per notificare eventi delle periferiche, ma una frequenza eccessiva può provocare molti cambiamenti di contesto e molto overhead.

Ridurre la frequenza delle interruzioni consente di diminuire il costo della gestione asincrona dell'I/O.

#### **6.4. Aumentare la concorrenza**

Aumentare la concorrenza nell'accesso alle periferiche permette di ridurre i tempi morti e mantenere nel sistema un numero adeguato di processi pronti all'esecuzione.

La concorrenza può riguardare:

- richieste su periferiche diverse;
- trasferimenti sovrapposti;
- code indipendenti;
- gestione parallela di elaborazione e I/O.

#### **6.5. Portare la gestione a livello più basso**

È opportuno portare la gestione delle periferiche al livello più basso possibile, quando questo consente di nascondere i dettagli e scegliere la soluzione più adatta alla specifica periferica.

Questo può significare spostare parte della gestione:

- nei driver;
- nei controller;
- nel firmware o microcodice della periferica.

#### **6.6. Bilanciare le prestazioni del sistema**

L'ottimizzazione deve considerare l'intero sistema, bilanciando il carico del processore con la quantità di richieste di ingresso/uscita.

Un sistema efficiente deve evitare che:

- la CPU resti inattiva in attesa delle periferiche;
- le periferiche siano sottoutilizzate;
- le interruzioni saturino il processore;
- le copie di dati diventino il collo di bottiglia.

> ⚠️ Ottimizzare solo la CPU o solo le periferiche non basta: le prestazioni dipendono dall'equilibrio tra elaborazione, memoria e I/O.

---

### **7. Sintesi finale**

> ✅ La realizzazione del sottosistema di I/O si basa su strutture dati del kernel, strutture specifiche dei processi, livelli di driver e meccanismi di completamento delle richieste.

In questa parte sono stati analizzati:

- tabelle globali del kernel per file e periferiche aperte;
- tabelle dei file aperti specifiche dei processi;
- buffer, cache e strutture di spooling;
- percorso di una richiesta di I/O;
- casi in cui il sistema operativo può rispondere immediatamente;
- accesso effettivo alla periferica tramite i livelli di driver;
- fattori critici di prestazione: cambi di contesto, copie di dati, interruzioni, concorrenza e bilanciamento del carico.

L'obiettivo complessivo è garantire una gestione efficiente, uniforme e affidabile delle periferiche, senza compromettere le prestazioni globali del sistema.
