# **M3 UD1 Lezione 3 - Condivisione dei file e protezione (parte 1)**

---

### **1. Introduzione**

La **condivisione dei file** e la **protezione** servono a permettere l'uso condiviso delle informazioni in modo corretto, consistente e controllato.

In un sistema multiutente, più processi e più utenti possono voler usare le stesse informazioni contenute in un file. Il file system deve quindi stabilire quando l'accesso concorrente è sicuro, quando deve essere impedito e quali utenti hanno diritto a eseguire certe operazioni.

In questa lezione vengono trattati:

- problema della condivisione dei file;
- compatibilità tra operazioni concorrenti;
- identificazione di utenti e gruppi;
- diritti di accesso;
- accesso remoto tramite trasferimento di file;
- file system di rete;
- file system distribuiti.

> 📌 Condividere un file significa permettere a più processi, eventualmente di utenti diversi, di usare la stessa informazione senza duplicarla inutilmente e senza renderla inconsistente.

---

### **2. Condivisione dei file**

La condivisione dei file consiste nella possibilità che più processi usino le stesse informazioni contenute in un file.

È tipica degli ambienti multiutente ed è utile per:

- condividere informazioni mantenendone una sola copia;
- evitare duplicazioni inutili;
- supportare la collaborazione tra utenti;
- rendere disponibili dati comuni a più processi.

#### **2.1. Operazioni compatibili**

L'accesso contemporaneo a un file è possibile quando le operazioni eseguite dai processi sono compatibili.

Il caso più semplice è la lettura simultanea: se tutti i processi leggono soltanto, il file non viene modificato e ciascun processo osserva informazioni consistenti.

> ✅ Più letture simultanee sono compatibili perché nessun processo modifica il contenuto del file.

#### **2.2. Operazioni incompatibili**

La scrittura è incompatibile con altre operazioni concorrenti sullo stesso file.

Se un processo scrive mentre altri leggono, i lettori possono osservare informazioni parzialmente modificate e quindi inconsistenti.

Se più processi scrivono contemporaneamente, le modifiche possono interferire tra loro.

$$
\text{accesso concorrente} =
\begin{cases}
\text{ammissibile} & \text{se tutte le operazioni sono letture} \\
\text{da serializzare} & \text{se almeno una operazione è scrittura}
\end{cases}
$$

> ⚠️ Una scrittura deve essere eseguita in mutua esclusione rispetto a qualunque altra operazione sul file, sia lettura sia scrittura.

---

### **3. Utenti, gruppi e autenticazione**

In un ambiente multiutente è necessario identificare univocamente utenti e gruppi.

Prima di concedere accesso ai file, il sistema deve effettuare l'**autenticazione**, cioè verificare l'identità dell'utente che accede al sistema.

Una volta autenticato, l'utente viene associato a:

- identità utente;
- eventuali gruppi di appartenenza;
- diritti di accesso sui file;
- limiti imposti dal sistema o dall'utente stesso ai processi avviati.

> 📌 L'autenticazione stabilisce chi è l'utente; la protezione stabilisce quali operazioni può eseguire sui file.

---

### **4. Diritti di accesso**

Per ogni file è possibile specificare quali operazioni sono lecite per un certo utente o per un certo gruppo.

I diritti possono essere definiti per:

- **proprietario** del file, cioè l'utente che lo ha creato o ne detiene la proprietà;
- **gruppo** a cui appartiene il proprietario;
- altri utenti;
- altri gruppi;
- tutti gli utenti definiti nel sistema.

#### **4.1. Ereditarietà dei diritti da utente a processo**

Una volta che un utente è autenticato, i processi che attiva ereditano normalmente i diritti dell'utente.

Questi diritti possono però essere ridotti:

- dall'utente stesso;
- dal sistema operativo;
- da politiche di sicurezza specifiche.

> ⚠️ Un processo non dovrebbe ottenere più diritti dell'utente che lo ha attivato, salvo politiche esplicite e controllate dal sistema operativo.

---

### **5. Accesso remoto tramite trasferimento di file**

L'accesso ai file può avvenire anche verso un file system remoto.

Una prima modalità è il **trasferimento di file**: il file si trova su una macchina server e viene copiato sulla macchina client dove opera il processo.

#### **5.1. Modello client/server**

Il processo richiede un file che si trova sul server. Il comando di trasferimento viene eseguito in parte sulla macchina client e in parte sulla macchina server.

Il file viene copiato localmente e il processo lavora sulla copia scaricata.

Esempi di protocolli:

- **FTP**;
- **HTTP**.

#### **5.2. Accesso anonimo e autenticato**

Il server può consentire:

- accesso **anonimo**, in cui l'utente non viene identificato individualmente;
- accesso **autenticato**, in cui l'utente dichiara la propria identità e il server decide se concedere l'accesso.

Nel caso anonimo, l'accesso è tipicamente concesso a tutti o negato a tutti secondo le regole del server.

#### **5.3. Coerenza della copia**

Dopo il trasferimento, il processo lavora sulla copia locale.

La copia originale sul server rimane inalterata, a meno che il processo non invii nuovamente il file al server sovrascrivendo l'originale. Questo è possibile solo se il server concede anche il diritto di scrittura.

> ⚠️ Nel trasferimento di file, altri processi continuano a vedere la copia sul server, non necessariamente la copia locale modificata dal client.

---

### **6. File system di rete**

Un secondo approccio è il **file system di rete**.

In questo modello, il server conserva i file e li rende visibili alle macchine della rete. Un processo su un client può accedere via rete in lettura o scrittura, se dispone dei diritti necessari.

#### **6.1. Accesso ai file remoti**

Il file può rimanere sul server e le operazioni vengono inviate via rete.

In alcuni modelli, il file può essere trasferito temporaneamente sul client, mentre la copia sul server viene bloccata. Alla fine dell'uso, se modificato, il contenuto viene riportato nella copia originale.

#### **6.2. Visibilità della distribuzione**

Nel file system di rete, il processo conosce la struttura della rete e la distribuzione delle porzioni di file system tra le macchine.

È una situazione analoga a un sistema con più dischi e più file system separati, senza una completa unificazione tramite montaggio.

> 📌 Nel file system di rete, la distribuzione fisica delle informazioni resta in parte visibile al processo o all'utente.

---

### **7. File system distribuito**

Il **file system distribuito** offre un livello di astrazione più alto.

Il processo, qualunque sia la macchina su cui è in esecuzione, vede un unico file system. Non deve sapere su quale server o macchina sia fisicamente allocato il file.

Sarà il sistema operativo distribuito a scoprire dove si trova il file nel sistema reale e a gestire l'accesso.

#### **7.1. Trasparenza dell'allocazione**

Nel file system distribuito:

- il processo vede un unico albero logico;
- l'allocazione fisica dei file è nascosta;
- il sistema operativo distribuito risolve la posizione reale delle risorse informative;
- l'accesso appare uniforme da qualunque macchina.

#### **7.2. Requisiti**

Questa soluzione richiede:

- forte interazione tra i sistemi operativi delle singole macchine;
- compatibilità tra i nodi;
- cooperazione per creare un'unica visione del file system;
- gestione trasparente di localizzazione, accesso e autorizzazioni.

> 💡 Il file system distribuito estende alla rete l'idea di montaggio logico: l'utente vede un unico spazio, anche se i dati sono fisicamente distribuiti.

---

### **8. File system di rete e file system distribuito**

La differenza principale è il grado di trasparenza.

Nel **file system di rete**, il processo conosce la struttura della rete e la distribuzione delle risorse.

Nel **file system distribuito**, il processo vede un unico file system trasparente; la localizzazione effettiva viene gestita dal sistema operativo distribuito.

$$
\text{accesso remoto} =
\begin{cases}
\text{trasferimento di file} & \text{copia locale esplicita} \\
\text{file system di rete} & \text{server visibile al client} \\
\text{file system distribuito} & \text{unico file system trasparente}
\end{cases}
$$

> ✅ Maggiore trasparenza significa maggiore semplicità per l'utente, ma anche maggiore complessità per il sistema operativo.

---

### **9. Sintesi finale**

> ✅ La condivisione dei file richiede compatibilità tra operazioni concorrenti, autenticazione degli utenti e controllo dei diritti di accesso.

In questa lezione sono stati analizzati:

- condivisione dei file in ambiente multiutente;
- compatibilità tra letture e incompatibilità delle scritture concorrenti;
- autenticazione di utenti e gruppi;
- diritti di accesso associati a proprietario, gruppi e altri utenti;
- ereditarietà dei diritti dai processi agli utenti;
- accesso remoto tramite FTP o HTTP;
- file system di rete;
- file system distribuito;
- differenza tra visibilità della rete e trasparenza completa della distribuzione.

La protezione e la coerenza completano il supporto all'uso condiviso dei file, garantendo che le informazioni siano accessibili solo a chi ne ha diritto e in stati consistenti.
