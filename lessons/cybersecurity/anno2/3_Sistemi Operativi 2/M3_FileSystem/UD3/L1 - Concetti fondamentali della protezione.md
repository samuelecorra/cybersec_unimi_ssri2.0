# **M3 UD3 Lezione 1 - Concetti fondamentali della protezione**

### **1. Introduzione**

La **protezione** è una delle funzioni fondamentali del sistema operativo e ha lo scopo di **difendere le risorse** da accessi non autorizzati, errati o malevoli.  
Ogni sistema operativo deve garantire che le sue risorse — fisiche o informative — siano utilizzate **solo da chi ne possiede l’autorizzazione**.

In termini generali:

- le **regole** specificano _chi può fare cosa_ sulle risorse;
    
- i **meccanismi** sono gli strumenti tecnici che **impongono il rispetto di tali regole**.

---

### **2. Obiettivo della protezione**

L’obiettivo è **definire e far rispettare le autorizzazioni** che stabiliscono:

- **chi** può accedere a una risorsa,
    
- **quali operazioni** può eseguire su di essa.

Questo garantisce **integrità, riservatezza e correttezza** nell’uso delle risorse del sistema.

---

### **3. Tipologie di risorse**

Le risorse che devono essere protette si dividono in due categorie principali:

#### **3.1 Risorse fisiche**

- CPU
    
- Memoria centrale
    
- Periferiche di ingresso/uscita

#### **3.2 Risorse informative**

- File e directory
    
- Strutture di comunicazione tra processi
    
- Meccanismi di sincronizzazione (semafori, pipe, ecc.)

Ogni risorsa è caratterizzata da:

- un **identificativo univoco**,
    
- un **insieme di operazioni lecite** che possono essere eseguite su di essa.

---

### **4. Principio di minima conoscenza**

Il **principio di minima conoscenza** (o _least privilege principle_) stabilisce che:

> Un processo deve poter accedere **solo alle risorse strettamente necessarie** per la propria computazione.

Questo principio riduce la possibilità di **abusi, errori o attacchi**, limitando il danno potenziale in caso di compromissione del processo.

---

### **5. Domini di protezione**

Un **dominio di protezione** definisce un **insieme di risorse** e le **operazioni lecite** su di esse, associate a un determinato processo.

Formalmente:

$$  
\text{Dominio} = \langle \text{Oggetto}, \text{Diritti} \rangle  
$$

dove:

- **Oggetto** → la risorsa da proteggere,
    
- **Diritti** → le operazioni consentite su quella risorsa (lettura, scrittura, esecuzione, ecc.).

Un processo autorizzato ad accedere a un dominio può **eseguire soltanto le operazioni definite dai diritti associati** a quel dominio.

---

### **6. Associazione tra processi e domini**

L’associazione tra processi e domini può avvenire secondo due modalità:

#### **6.1 Associazione statica**

- Il processo mantiene **sempre lo stesso dominio** per tutta la durata della sua esecuzione.
    
- È più semplice da implementare ma meno flessibile.

#### **6.2 Associazione dinamica**

- Il processo può **cambiare dominio di protezione** durante l’esecuzione.
    
- Ogni cambio di dominio avviene **solo se il processo possiede i diritti necessari** per eseguire tale operazione.
    
- Permette maggiore **flessibilità e sicurezza** nei sistemi multiutente.

---

### **7. Revoca dei diritti d’accesso**

La **revoca** consiste nella **rimozione parziale o totale dei diritti di accesso** concessi in precedenza.

Le principali modalità di revoca sono:

|Tipo di revoca|Descrizione|
|---|---|
|**Immediata / Ritardata**|La revoca può avere effetto subito o dopo un certo tempo.|
|**Selettiva / Generale**|Può riguardare uno specifico utente/processo o tutti gli utenti.|
|**Parziale / Totale**|Può revocare solo alcune operazioni o tutte le autorizzazioni.|
|**Temporanea / Permanente**|I diritti possono essere sospesi per un periodo o rimossi definitivamente.|

La possibilità di **revocare dinamicamente i permessi** è fondamentale per mantenere la sicurezza del sistema in ambienti in continua evoluzione.

---

### **8. Sintesi finale**

|Concetto|Descrizione sintetica|
|---|---|
|**Protezione**|Difesa delle risorse da accessi non autorizzati|
|**Obiettivo**|Definire chi può accedere a cosa e in che modo|
|**Risorse fisiche**|CPU, memoria, periferiche|
|**Risorse informative**|File, strutture di comunicazione, semafori|
|**Principio di minima conoscenza**|Ogni processo accede solo alle risorse necessarie|
|**Dominio di protezione**|Insieme di risorse e diritti leciti|
|**Associazione dominio-processo**|Statica o dinamica|
|**Revoca dei diritti**|Immediata, selettiva, parziale o temporanea|

---

### **9. Conclusione**

La **protezione delle risorse** è un aspetto essenziale della sicurezza nei sistemi operativi.  
Attraverso il concetto di **dominio di protezione**, l’adozione del **principio di minima conoscenza** e la possibilità di **revoca dinamica dei permessi**, il sistema riesce a **prevenire accessi impropri**, a **limitare i danni** derivanti da errori o intrusioni, e a mantenere **l’integrità complessiva del sistema**.