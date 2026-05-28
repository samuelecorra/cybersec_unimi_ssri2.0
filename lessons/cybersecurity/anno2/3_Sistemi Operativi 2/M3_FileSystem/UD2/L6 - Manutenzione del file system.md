# **M3 UD2 Lezione 6 - Manutenzione del file system**

---

### **1. Introduzione**

Questa lezione affronta il problema della **manutenzione del file system**, necessaria per garantire:

- conservazione delle informazioni;
- consistenza dei dati;
- possibilita' di ripristino dopo errori o guasti.

Il file system deve infatti mantenere nel tempo uno stato corretto, anche quando si verificano malfunzionamenti, interruzioni improvvise o danneggiamenti del supporto fisico.

Gli aspetti principali sono:

- errori nel file system;
- coerenza tra dati e metadati;
- backup;
- restore;
- file system transazionali, logging e journaling.

> 📌 La manutenzione serve a mantenere il file system in uno stato corretto e recuperabile anche dopo condizioni anomale.

---

### **2. Errori nel file system**

Gli errori nel file system possono derivare da due categorie principali.

#### **2.1. Danneggiamenti logici**

I danneggiamenti logici riguardano le strutture dati del file system.

Possono essere:

- accidentali;
- dovuti a crash;
- causati da errori software;
- volontari, per esempio a seguito di azioni malevole.

Esempi di inconsistenza logica sono:

- metadati non allineati ai dati;
- blocchi marcati liberi ma ancora appartenenti a un file;
- blocchi assegnati a piu' file;
- directory non coerenti con i descrittori;
- dimensioni dei file non coerenti con i blocchi allocati.

#### **2.2. Danneggiamenti fisici**

I danneggiamenti fisici riguardano il supporto di memorizzazione.

Esempi:

- settori danneggiati;
- guasti del disco;
- guasti del controller;
- malfunzionamenti non recuperabili;
- distruzione fisica del sistema informativo.

In entrambi i casi, il sistema operativo deve tentare di preservare o ricostruire uno stato consistente del file system.

> ⚠️ Un file system puo' diventare inconsistente sia per errori nelle strutture logiche sia per guasti del supporto fisico.

---

### **3. Coerenza del file system**

La **coerenza** e' la proprieta' per cui dati e metadati rappresentano uno stato corretto del file system.

In particolare, deve esserci allineamento tra:

- informazioni presenti in memoria centrale durante l'uso del file system;
- informazioni memorizzate stabilmente sul disco.

Il problema nasce perche' molte operazioni del file system aggiornano piu' strutture.

Per esempio, creare un file puo' richiedere:

- aggiornamento della directory;
- allocazione dei blocchi;
- aggiornamento della lista libera;
- aggiornamento del descrittore del file;
- scrittura dei dati.

Se il sistema si interrompe a meta' dell'operazione, alcune strutture possono risultare aggiornate e altre no.

> 📌 La coerenza richiede che dati e metadati descrivano lo stesso stato del file system.

---

### **4. Controllore della coerenza**

Per garantire l'allineamento dei valori, il sistema puo' introdurre un **controllore della coerenza**.

Il controllore verifica che le strutture del file system siano compatibili tra loro.

Controlla, per esempio:

- se i blocchi indicati dai descrittori esistono davvero;
- se la lista dei blocchi liberi non contiene blocchi occupati;
- se un blocco non e' assegnato contemporaneamente a piu' file;
- se le directory puntano a descrittori validi;
- se le dimensioni dei file corrispondono ai blocchi allocati.

Quando rileva una incoerenza, puo':

- correggerla automaticamente;
- segnalare l'errore;
- isolare blocchi o file danneggiati;
- richiedere intervento amministrativo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ Il controllore della coerenza serve a riportare le strutture del file system a uno stato interpretabile e corretto.

---

### **5. Scrittura sincrona dei dati critici**

Per dati e metadati critici, il sistema non puo' sempre aspettare il normale scaricamento periodico su disco.

Alcune informazioni devono essere salvate in modo **sincrono**, cioe' immediatamente e in ordine controllato.

Questo vale soprattutto per:

- metadati di allocazione;
- directory;
- descrittori dei file;
- informazioni sulla lista libera;
- strutture di controllo del file system.

La scrittura sincrona riduce il rischio che, dopo un crash, il disco contenga uno stato incompleto o non interpretabile.

> ⚠️ La scrittura sincrona migliora la sicurezza dello stato su disco, ma puo' ridurre le prestazioni perche' obbliga ad attendere il completamento dell'I/O.

---

### **6. Backup**

Il **backup** e' il salvataggio di sicurezza di dati e metadati.

Serve a conservare una copia delle informazioni per superare:

- guasti del sistema;
- malfunzionamenti transitori non risolvibili;
- perdita accidentale di dati;
- danneggiamenti volontari;
- catastrofi con distruzione del sistema informativo.

Un backup deve includere non solo i dati dei file, ma anche le informazioni necessarie a ricostruire correttamente il file system.

---

### **7. Backup completo**

Il **backup completo** salva tutte le informazioni contenute nella memoria di massa.

Vantaggi:

- e' semplice da gestire;
- contiene uno stato completo del sistema;
- rende il ripristino piu' diretto.

Svantaggi:

- richiede molto tempo;
- richiede molto spazio;
- puo' essere costoso se eseguito frequentemente.

> 📌 Il backup completo e' il punto di riferimento da cui puo' partire un successivo ripristino.

---

### **8. Backup incrementale**

Il **backup incrementale** salva solo le informazioni modificate dall'ultimo backup.

Rispetto al backup completo:

- e' piu' rapido;
- occupa meno spazio;
- riduce il carico sul sistema.

Il ripristino, pero', e' piu' complesso.

Per ricostruire lo stato finale bisogna partire da un backup completo e poi applicare, nell'ordine corretto, tutti i backup incrementali successivi.

Formalmente:

$$
\text{stato ripristinato} =
\text{backup completo} +
\sum \text{backup incrementali successivi}
$$

> ⚠️ Il backup incrementale migliora le prestazioni del salvataggio, ma rende piu' articolata la procedura di restore.

---

### **9. Restore**

Il **restore** e' l'operazione di ripristino.

Consiste nel ricaricare dati e metadati nella memoria di massa principale prendendoli dal supporto su cui erano stati salvati, spesso una memoria terziaria o un sistema esterno di backup.

L'obiettivo e' riportare il file system a uno stato:

- precedente al guasto;
- coerente;
- utilizzabile dai processi.

Il restore puo' essere:

- **totale**, se ricostruisce l'intero file system;
- **parziale**, se recupera solo alcuni file, directory o porzioni di dati.

> ✅ Backup e restore permettono la sopravvivenza delle informazioni anche quando il file system originario non e' piu' affidabile.

---

### **10. File system transazionali**

Per rendere backup e ripristino piu' efficienti, si possono introdurre file system orientati alle **transazioni**.

Una transazione e' una sequenza di operazioni che deve essere trattata come un'unita' logica.

Una transazione deve risultare:

- completata interamente;
- oppure non applicata.

Non deve rimanere visibile uno stato intermedio.

Nel file system, una transazione puo' rappresentare, per esempio:

- creazione di un file;
- cancellazione di un file;
- aggiornamento di un descrittore;
- modifica della lista libera;
- scrittura di dati e metadati collegati.

---

### **11. Logging**

Nei sistemi basati su **logging**, le operazioni vengono registrate in un log.

Il log conserva le informazioni necessarie a capire quali transazioni:

- erano iniziate;
- sono state completate;
- sono rimaste incomplete al momento del guasto.

Dopo un crash, il sistema analizza il log e puo':

- ripetere le transazioni completate ma non ancora consolidate su disco;
- annullare o ignorare le transazioni incomplete;
- riportare il file system a uno stato consistente precedente o successivo, ma comunque corretto.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 Il logging permette di ricostruire lo stato corretto osservando la storia recente delle operazioni.

---

### **12. Journaling**

Il **journaling** e' una tecnica basata sulla registrazione delle attivita' del file system in un journal.

Prima di modificare stabilmente le strutture principali del file system, il sistema registra nel journal le operazioni da eseguire.

In caso di guasto, il journal consente di stabilire quali operazioni erano complete e quali no.

Questo rende possibile:

- completare operazioni gia' registrate correttamente;
- annullare operazioni incomplete;
- evitare controlli completi lunghi dell'intero file system;
- ridurre i tempi di recupero dopo un crash.

Il journaling puo' riguardare:

- solo i metadati;
- dati e metadati.

> 📌 Il journaling non sostituisce il backup: protegge soprattutto dalla perdita di coerenza dopo crash, non dalla distruzione fisica del supporto.

---

### **13. Sintesi**

| Aspetto | Problema | Tecnica di gestione |
|---|---|---|
| **Errori logici** | Strutture dati incoerenti | Controllore della coerenza |
| **Errori fisici** | Supporto danneggiato | Backup, restore, isolamento guasti |
| **Coerenza** | Dati e metadati non allineati | Scritture sincrone, verifiche |
| **Backup completo** | Salvataggio dell'intero sistema | Copia totale di dati e metadati |
| **Backup incrementale** | Salvataggio efficiente | Copia delle sole modifiche |
| **Restore** | Recupero dopo guasto | Ricostruzione da backup |
| **Logging** | Recupero dello stato recente | Analisi e ripetizione/annullamento transazioni |
| **Journaling** | Consistenza dopo crash | Registro delle operazioni del file system |

---

### **14. Conclusione**

La manutenzione del file system ha lo scopo di garantire che le informazioni restino conservate e consistenti anche in presenza di errori o guasti.

La coerenza richiede l'allineamento tra dati e metadati in memoria centrale e su disco.

Backup e restore permettono di recuperare uno stato corretto dopo perdita o danneggiamento delle informazioni.

Logging e journaling migliorano la capacita' del file system di recuperare rapidamente uno stato consistente dopo interruzioni improvvise, riducendo il rischio che operazioni parziali lascino strutture incoerenti.
