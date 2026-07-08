## **Lezione 2: Controllo degli accessi in Linux**

### **1. Modello di sicurezza UNIX/Linux**

Il modello di sicurezza di **UNIX** e **Linux** si fonda su una struttura semplice ma molto efficace, basata su tre elementi fondamentali:

- **Soggetti (Who?)** → utenti e processi
    
- **Oggetti (What?)** → file, directory, socket, pipe, dispositivi hardware, oggetti kernel, dati di processo
    
- **Operazioni di accesso (How?)** → lettura (_read_), scrittura (_write_), esecuzione (_execute_)
    

Tutti gli accessi alle risorse del sistema sono regolati in base a **diritti associati a utenti e gruppi**, e ogni operazione viene controllata rispetto a tali permessi.

Storicamente UNIX è uno dei primi esempi concreti di implementazione di una politica **DAC**: le decisioni di accesso dipendono dall’identità del soggetto, dal proprietario della risorsa, dal gruppo associato e dai permessi impostati sull’oggetto.

La semplicità del modello deriva anche dall’idea “tutto è un file”: non solo file e directory, ma anche socket, pipe, dispositivi, terminali e molte risorse esposte dal kernel vengono trattati con interfacce e controlli simili a quelli dei file.

---

### **2. Tipologie di account**

I sistemi UNIX/Linux gestiscono due grandi categorie di account:

1. **Account di servizio**
    
    - Utilizzati per eseguire processi in background (es. _server web_, _daemon di sistema_).
        
    - Non sono associati a utenti reali, ma a funzionalità specifiche.
        
2. **Account utente**
    
    - Legati a individui reali.
        
    - Ogni utente è identificato da un **UID (User ID)** univoco.
        
    - L’**UID 0** è riservato all’utente **root**, che dispone di **accesso totale** al sistema.
        
    - Solo root può eseguire operazioni di amministrazione e manipolare risorse protette.

Questa concentrazione di privilegi è potente ma anche rischiosa: root è un singolo punto critico del modello UNIX/Linux. Se un attaccante ottiene codice in esecuzione con UID 0, può accedere praticamente a tutto il sistema e modificarne le politiche.
        

Inoltre, UNIX organizza gli utenti in **gruppi**, che rappresentano insiemi di account con **privilegi condivisi** su file e risorse.  
Ogni gruppo è identificato da un **GID (Group ID)** e da un **nome**.

---

### **3. Gestione dei permessi e modello ACL**

Tutte le risorse in Linux — file, directory, dispositivi, socket — sono trattate come **file**.  
Ogni file ha:

- Un **proprietario utente (owner)**
    
- Un **gruppo proprietario (group)**
    
- Un set di **permessi di accesso**
    

Il sistema utilizza un modello basato su **Access Control List (ACL)** semplificate.  
Ogni oggetto dispone di un’ACL che specifica **quali soggetti possono eseguire determinate operazioni**.

Durante ogni tentativo di accesso, il sistema confronta la richiesta dell’utente con i permessi memorizzati nell’ACL del file o della directory.

Le informazioni sugli utenti e sui gruppi sono mantenute in file di sistema come `/etc/passwd`, `/etc/shadow` e i file relativi ai gruppi. I permessi sui file sono invece memorizzati nelle strutture del filesystem, in particolare negli inode, distinguendo tra **owner**, **group** e **others**.

I bit di permesso possono essere modificati in modo discrezionale dal proprietario dell’oggetto, quando autorizzato, o da root. Questa è la componente DAC del modello: chi controlla una risorsa può regolare i permessi secondo le regole del sistema.

![](imgs/Pasted%20image%2020260708183035.png)

> Esempio:
> 
> - _User Bob:_ permesso di scrittura su File1
>     
> - _User Alice:_ permesso di lettura e scrittura su File1
>     

---

### **4. Struttura dei file e inode**

Tutti i file UNIX sono gestiti tramite le strutture dati **inode (index node)** — strutture di controllo che contengono le informazioni chiave sul file.  
Ogni inode include:

- Attributi del file (dimensione, tipo, timestamp, permessi, ecc.)
    
- Proprietario (UID) e gruppo (GID)
    
- Puntatori ai blocchi di dati fisici sul disco
    

#### **Caratteristiche principali**

- Più nomi (link) possono riferirsi allo stesso inode.
    
- Esiste una **tabella degli inode** per tutti i file del filesystem.
    
- Quando un file viene aperto, il suo inode viene **caricato in memoria**.
    

Le **directory** formano un **albero gerarchico** che può contenere file o altre directory.  
Ogni directory è un file speciale che **mappa i nomi dei file agli inode corrispondenti**.

L’accesso effettivo a un file dipende anche dai permessi sulle directory del percorso: per raggiungere un oggetto non basta avere permessi sul file finale, ma occorre poter attraversare le directory che lo contengono. In UNIX classico non esiste una vera ereditarietà automatica generale dei permessi come in altri sistemi, ma la struttura ad albero influenza comunque il controllo dell’accesso.

![](imgs/Pasted%20image%2020260708183409.png)

---

### **5. Processi e privilegi in Linux**

Ogni **processo** in Linux è isolato dagli altri e viene eseguito **come un utente specifico**.  
Ciò significa che:

- Un processo può accedere solo ai file per i quali l’**UID associato** possiede i permessi.
    
- Tutti i processi eseguiti dallo stesso utente condividono le stesse autorizzazioni.
    
- I processi eseguiti da **root** possono modificare i propri privilegi, abbassandoli temporaneamente per ridurre i rischi.

Anche i processi possono essere considerati risorse controllate dal sistema operativo. In condizioni normali sono isolati tra loro: non condividono liberamente la memoria e accedono solo alle risorse permesse dal proprio contesto di esecuzione.
    

Quando un utente esegue un comando:

- La **shell** viene eseguita con il suo UID.
    
- Ogni comando avviato eredita l’UID della shell tramite la **fork()**.

Quando un processo crea un processo figlio, quest’ultimo eredita normalmente il contesto di sicurezza del padre. Per questo i processi avviati dallo stesso utente tendono ad avere le stesse autorizzazioni, salvo cambi espliciti di UID, GID o privilegi.

Per limitare i danni in caso di compromissione, i demoni e i processi di sistema dovrebbero spesso abbandonare i privilegi elevati appena possibile, passando a UID o gruppi meno privilegiati.
    

---

### **6. ID utente nei processi**

Ogni processo ha tre diversi **User ID**:

|**Tipo di ID**|**Significato**|
|---|---|
|**RUID (Real User ID)**|Identifica l’utente che ha avviato il processo.|
|**EUID (Effective User ID)**|Determina i permessi effettivi del processo (usato nei controlli di accesso).|
|**SUID (Saved User ID)**|Memorizza il valore dell’EUID prima di eventuali modifiche.|

All’avvio, tutti e tre gli ID sono identici, corrispondenti all’utente che ha lanciato il processo.  
Durante l’esecuzione, possono essere modificati con meccanismi particolari.

Il controllo degli accessi usa soprattutto l’**Effective User ID**: è questo valore a determinare quali file e risorse il processo può effettivamente usare. Il **Real User ID** conserva l’identità dell’utente che ha avviato il processo, mentre il **Saved User ID** permette di recuperare un valore precedente dell’EUID quando il programma alterna fasi privilegiate e non privilegiate.

> 📌 La distinzione tra RUID, EUID e SUID serve a separare identità dell’utente, privilegi effettivamente usati nei controlli e possibilità controllata di tornare a privilegi precedenti.

---

### **7. Meccanismi speciali: SetUID, SetGID e Sticky Bit**

#### **SetUID e SetGID**

Quando un file eseguibile ha impostato il bit **SetUID** o **SetGID**, il sistema assegna **temporaneamente i privilegi del proprietario (o del gruppo)** del file al processo che lo esegue.

> Esempio:  
> Se un file di proprietà di _root_ ha il bit **SetUID** attivo, un utente normale che lo esegue ottiene temporaneamente **privilegi amministrativi** durante l’esecuzione del programma.

**Effetti:**

- `EUID` e `EGID` assumono temporaneamente i valori del proprietario del file.
    
- Il processo ritorna ai privilegi originali al termine dell’esecuzione.
    

Questa funzionalità è utile per programmi che devono accedere a **risorse di sistema riservate**, ma può introdurre **rischi di sicurezza** se mal configurata.

Un esempio classico è il programma per cambiare password: un utente ordinario deve poter aggiornare la propria password, ma non dovrebbe avere accesso generale ai file di sistema che contengono informazioni di autenticazione. Il programma SetUID permette di eseguire solo quella funzione privilegiata, se il codice è scritto correttamente.

---

#### **Sticky Bit**

Quando applicato a una **directory**, lo _sticky bit_ impedisce a un utente di **rinominare, spostare o cancellare** file appartenenti ad altri nella stessa directory.  
Solo il **proprietario del file** o **root** può modificarli.

> Esempio pratico:  
> La directory `/tmp` ha il _sticky bit_ attivo, in modo che più utenti possano usarla senza rischiare di cancellare i file altrui.

---

#### **Superuser (root)**

L’utente con **UID 0** è il **superuser**, esente da qualsiasi restrizione.  
Può:

- accedere a qualunque file o risorsa;
    
- modificare liberamente gli ID utente di un processo;
    
- impostare o rimuovere _SetUID_ e _Sticky Bit_.
    

Gli utenti non privilegiati, invece, possono modificare solo il proprio **EUID**, impostandolo sul valore di **RUID** o **SUID**.

In altre parole, root può cambiare liberamente gli identificativi effettivi dei processi; un utente ordinario può solo muoversi tra identità già previste dal proprio contesto, ad esempio tra real user ID e saved user ID. Questa restrizione evita che un processo non privilegiato assuma arbitrariamente l’identità di un altro utente.

---

### **8. Sicurezza e rischi dei privilegi elevati**

I programmi **SetUID root** possono introdurre **vulnerabilità gravi**:

- Consentono l’esecuzione di codice con **privilegi totali**.
    
- Eventuali errori nel codice possono essere **sfruttati per ottenere accesso amministrativo**.
    

Per questo motivo:

- L’uso di **SetUID root** deve essere limitato.
    
- Ogni programma privilegiato deve essere **controllato e validato**.
    
- È buona pratica eseguire i processi con il **principio del privilegio minimo**, riducendo i diritti quando non necessari.

> ⚠️ I programmi SetUID sono bersagli privilegiati: se contengono vulnerabilità, l’attaccante può trasformare un errore applicativo in escalation di privilegi.
    

---

### **9. Sintesi finale**

Il sistema di controllo degli accessi di Linux si basa su:

- **Utenti e gruppi** identificati da UID e GID.
    
- **Permessi classici** di lettura, scrittura ed esecuzione.
    
- **Meccanismi speciali** come SetUID, SetGID e Sticky Bit.
    
- **Isolamento dei processi** e applicazione rigorosa dei diritti.
    

> In sintesi, Linux implementa un modello **DAC esteso**, con possibilità di **gestione granulare delle autorizzazioni** e **elevata separazione dei privilegi**.  
> Questa architettura garantisce un buon equilibrio tra **sicurezza, flessibilità e controllo amministrativo**.
