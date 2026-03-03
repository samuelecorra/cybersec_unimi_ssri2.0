# **Lezione 2: Il caso di studio di Java Sandbox**

### 1. Introduzione al modello di sicurezza Java

La lezione analizza il modello di sicurezza della piattaforma Java attraverso il caso di studio del **Java Sandbox**, uno dei primi esempi concreti di architettura software progettata per eseguire codice potenzialmente non fidato in modo sicuro.

La sicurezza della piattaforma Java è stata sviluppata progressivamente nel tempo, a partire dal modello iniziale di Java 1.0 fino all’architettura più flessibile introdotta in Java 2. L’obiettivo principale era permettere l’esecuzione di codice scaricato da Internet senza compromettere il sistema locale dell’utente.

Il riferimento bibliografico principale della lezione è il testo _Inside Java 2 Platform Security_, che descrive in dettaglio l’architettura, le API e le implementazioni della sicurezza Java.

---

### 2. Applet e applicazioni nel modello Java 1.0

Nel modello di sicurezza originale di Java 1.0 viene introdotta una distinzione fondamentale tra **applets** e **applicazioni**.

Le applet sono programmi scaricati da Internet ed eseguiti all’interno di un browser o di un ambiente controllato. Poiché il codice proviene da una fonte esterna, si assume che possa essere potenzialmente malevolo. Per questo motivo le applet vengono eseguite in un **sandbox**, cioè un ambiente isolato e controllato che limita severamente le operazioni consentite.

Le applicazioni, invece, sono programmi presenti nel filesystem locale e vengono considerate affidabili, analogamente ai normali programmi installati sul sistema.

Questa distinzione introduce un modello di sicurezza basato sulla fiducia nella provenienza del codice.

---

### 3. Security policy del sandbox in Java 1.0

Nel modello sandbox di Java 1.0, le applet sono soggette a restrizioni molto severe.

Un’applet:

- non può accedere al filesystem locale né ai dispositivi
    
- può connettersi solo al server da cui proviene
    
- non può eseguire programmi locali
    
- viene marcata come codice non fidato
    
- non può manipolare o estendere le classi base del sistema
    

Le applicazioni locali invece hanno accesso illimitato alle risorse e possono definire una propria politica di sicurezza.

Questo modello è estremamente rigido e viene spesso descritto come **modello “tutto o niente”**.

---

### 4. Meccanismi fondamentali della sicurezza Java

L’architettura di sicurezza Java si basa su diversi meccanismi complementari:

- sicurezza dei tipi del linguaggio
    
- verifica del bytecode
    
- controllo runtime del caricamento delle classi
    
- accesso mediato alle risorse tramite JVM
    

La **sicurezza dei tipi** garantisce che le operazioni sui dati siano coerenti con i tipi dichiarati, prevenendo manipolazioni illegali della memoria.

La **Java Virtual Machine (JVM)** svolge un ruolo centrale perché controlla l’esecuzione del codice e media l’accesso alle risorse del sistema.

---

### 5. Verifica del bytecode

Un aspetto fondamentale è la verifica del bytecode prima dell’esecuzione.

Anche se il compilatore Java controlla il codice sorgente, non si può assumere che il bytecode provenga realmente dal compilatore. Un attaccante potrebbe generare bytecode manipolato.

Il **bytecode verifier** controlla quindi che:

- non vengano effettuate conversioni illegali di tipo
    
- non vengano modificati riferimenti di memoria
    
- non vengano violate restrizioni di accesso
    
- gli oggetti vengano utilizzati correttamente
    
- i metodi vengano invocati con parametri corretti
    

Questo processo garantisce che solo codice conforme alle regole di sicurezza Java venga eseguito.

---

### 6. Efficienza della verifica statica

La verifica del bytecode viene effettuata principalmente in modo statico prima dell’esecuzione.

Gli stessi controlli potrebbero essere eseguiti ogni volta a runtime, ma ciò rallenterebbe significativamente il sistema. Grazie alla verifica preventiva, la JVM può assumere che alcune proprietà siano già garantite durante l’esecuzione.

Questo rappresenta un compromesso tra sicurezza ed efficienza.

---

### 7. Sicurezza dei tipi e vulnerabilità logiche

La lezione evidenzia che programmi non corretti dal punto di vista logico possono comunque generare problemi di sicurezza anche se il linguaggio è sicuro.

Viene fornito un esempio basato su un controllo di uguaglianza tra stringhe per autorizzare l’accesso:

Se la funzione di confronto non funziona correttamente, un utente non autorizzato potrebbe ottenere accesso.

Questo dimostra che la sicurezza dei tipi non elimina completamente i rischi: errori logici rimangono possibili.

---

### 8. SecurityManager e ClassLoader

Nel JDK 1.0 la sicurezza è gestita principalmente da due classi:

- SecurityManager
    
- ClassLoader
    

Il **SecurityManager** definisce i metodi di controllo dei permessi, come l’accesso ai file. Se un’operazione non è consentita, viene generata un’eccezione.

È possibile creare una propria policy di sicurezza definendo una sottoclasse di SecurityManager e installandola tramite il metodo `System.setSecurityManager`. Una volta installata, la policy non può essere modificata.

Il **ClassLoader** è responsabile del caricamento delle classi e della gestione degli spazi dei nomi.

Tra le sue funzioni principali:

- trovare e caricare le definizioni delle classi
    
- separare namespace di applet diverse
    
- mantenere riferimento al loader che ha caricato la classe
    
- verificare il bytecode delle classi non fidate
    

Le classi presenti nel CLASSPATH sono considerate fidate nelle prime versioni di Java.

---

### 9. Applet firmate in JDK 1.1

Con JDK 1.1 viene introdotto il concetto di **applet firmate digitalmente**.

Un’applet firmata da un’entità fidata può ottenere permessi equivalenti a quelli delle applicazioni locali.

Questo introduce un modello di sicurezza basato sulla **fiducia crittografica** nella provenienza del codice.

---

### 10. Evoluzione in Java 2: controllo degli accessi fine-grained

Java 2 introduce un modello di sicurezza molto più flessibile basato su:

- security policies
    
- permessi
    
- controllo degli accessi granulare
    

Questo supera il modello rigido delle versioni precedenti e consente di assegnare diritti specifici a specifiche applicazioni o componenti.

Ad esempio, un’applet può avere il permesso di leggere un determinato file ma non altri.

---

### 11. CodeSource e Permission

Due classi fondamentali del modello Java 2 sono:

- CodeSource
    
- Permission
    

La classe CodeSource rappresenta l’origine del codice, includendo:

- URL di provenienza
    
- certificati digitali
    

La classe Permission rappresenta i permessi assegnati, con varie sottoclassi come:

- FilePermission
    
- SocketPermission
    

Questo modello consente di associare diritti specifici a codice proveniente da determinate fonti.

---

### 12. ProtectionDomain e Policy

Il **ProtectionDomain** rappresenta l’insieme dei permessi associati a una classe.

Viene creato combinando:

- CodeSource
    
- PermissionCollection
    

Ogni classe appartiene a un ProtectionDomain, assegnato al momento della creazione e non modificabile successivamente.

La classe **Policy** rappresenta invece l’interfaccia per definire le politiche di sicurezza.

Dato un CodeSource, la Policy restituisce l’insieme dei permessi associati.

---

### 13. Processo di caricamento delle classi e assegnazione dei permessi

Quando una classe richiede il caricamento di un’altra classe:

1. il ClassLoader la carica e verifica il bytecode
    
2. viene determinato il CodeSource
    
3. la Policy restituisce i permessi
    
4. viene creato o riutilizzato un ProtectionDomain
    
5. la classe viene associata al ProtectionDomain
    

Successivamente la classe può essere eseguita, mantenendo controlli runtime.

---

### 14. Controllo runtime dei permessi

Quando un metodo richiede un permesso specifico:

- la JVM chiama `checkPermission`
    
- viene coinvolto l’AccessController
    
- viene controllato il ProtectionDomain del chiamante
    
- se il permesso è presente l’operazione è consentita
    
- altrimenti viene generata un’eccezione
    

Il controllo tiene conto dell’intera catena di chiamate e delle operazioni privilegiate (`doPrivileged`).

Questo modello realizza una forma di **mediazione completa** coerente con i principi di sicurezza studiati nelle lezioni precedenti.

---

### 15. Sintesi della lezione

La lezione ha analizzato l’architettura di sicurezza Java attraverso l’evoluzione del sandbox.

I principi fondamentali sono:

- sicurezza dei tipi del linguaggio
    
- verifica statica del bytecode
    
- controllo runtime dei permessi
    
- accesso mediato alle risorse
    

È stata inoltre illustrata l’evoluzione:

- Java 1.0: sandbox rigido per applet
    
- JDK 1.1: applet firmate
    
- Java 2: controllo degli accessi granulare e flessibile
    

Il modello Java rappresenta uno dei primi esempi completi di architettura di sicurezza per esecuzione di codice non fidato.

---

Se vuoi, puoi caricare la **prossima lezione** 👍.