## **Lezione 3: Politiche di controllo degli accessi – DAC**

### **1. Introduzione al Discretionary Access Control (DAC)**

Il **Discretionary Access Control (DAC)** è un modello di controllo degli accessi che regola l’uso delle risorse **in base all’identità del soggetto** e alle **regole di autorizzazione** definite dal proprietario.

Si definisce _“discrezionale”_ perché un’entità (utente o processo) dotata di privilegi adeguati può, **a propria discrezione**, concedere ad altri soggetti il permesso di accedere a determinate risorse.

> Esempio: un utente che possiede un file può decidere di condividere il diritto di lettura o scrittura con altri utenti del sistema.

Questa delega è la caratteristica distintiva rispetto al MAC: il sistema non impone soltanto etichette centralizzate, ma permette a chi controlla una risorsa di modificare la distribuzione dei privilegi secondo le regole previste dalla politica.

---

### **2. Struttura del modello DAC: la matrice di accesso**

Il modello DAC è spesso rappresentato tramite una **matrice di accesso**, che specifica i diritti di ciascun soggetto su ciascun oggetto.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Righe:** soggetti (utenti, gruppi, processi, applicazioni).
    
- **Colonne:** oggetti (file, directory, dispositivi, record, ecc.).
    
- **Celle:** diritti di accesso (read, write, execute, delete, own...).
    

Ogni voce della matrice indica **quali operazioni** un determinato soggetto può eseguire su un determinato oggetto.

Tuttavia, poiché nella pratica la matrice è **molto sparsa**, vengono usate rappresentazioni più compatte, come gli **elenchi di controllo degli accessi (ACL)** e i **ticket di capacità (Capability Lists)**.

Per esempio, una cella può indicare che l’utente $B$ può leggere `File1`, mentre l’utente $C$ può leggere e scrivere lo stesso file. Su un altro oggetto, come `File2`, $B$ potrebbe avere lettura, scrittura e proprietà, mentre $C$ potrebbe avere solo lettura. In sistemi reali, con molti utenti e molte risorse, la maggior parte delle celle resta vuota: per questo la matrice completa è concettualmente utile ma inefficiente da memorizzare direttamente.

---

### **3. Implementazioni pratiche**

#### **a. Elenchi di Controllo degli Accessi (ACL – Access Control List)**

Un **ACL** è una **colonna della matrice di accesso**, ossia l’elenco dei soggetti autorizzati per uno specifico oggetto, insieme ai loro diritti.

- Per ogni oggetto (es. file) esiste un elenco che indica **chi può fare cosa**.
    
- Può contenere una voce **pubblica o predefinita**, che stabilisce i diritti minimi per utenti non elencati.
    
- Segue sempre la regola del **privilegio minimo** (solo le operazioni strettamente necessarie).

In altri termini, per ogni risorsa si memorizza la lista dei soggetti che possono accedervi e dei relativi privilegi. È la rappresentazione naturale quando la domanda principale è: “chi può accedere a questo oggetto?”.
    

**Vantaggi:**

- È facile determinare **chi può accedere** a una determinata risorsa.
    

**Svantaggi:**

- È difficile determinare **quali risorse** sono accessibili da un utente specifico.
    

---

#### **b. Ticket di capacità (Capability Lists)**

Un **Capability Ticket** è una **riga della matrice di accesso**, ossia l’elenco delle risorse e dei diritti associati a un singolo utente.

- Ogni utente possiede una serie di ticket che specificano **quali oggetti** può usare e **in quale modo**.
    
- I ticket possono essere **trasferibili o prestabili**, ma ciò introduce **problemi di sicurezza** (devono essere protetti da falsificazioni).
    
- La loro integrità viene garantita dal **sistema operativo** o da un **token crittografico non falsificabile** (es. password casuale lunga, codice MAC).

La capability list è quindi la lettura per righe della matrice: per ogni soggetto si raccolgono gli oggetti accessibili e le operazioni consentite. È la rappresentazione naturale quando la domanda principale è: “che cosa può fare questo utente o processo?”.
    

**Vantaggi:**

- È facile sapere **quali diritti ha un utente**.
    

**Svantaggi:**

- È difficile sapere **chi ha accesso** a una risorsa specifica.
    

---

#### **c. Tabelle di autorizzazione**

Un’alternativa proposta da [Sandhu, 1994] è la **Authorization Table**, una struttura **non sparsa** in cui ogni riga rappresenta **un singolo diritto** di un soggetto su un oggetto.

|**Soggetto**|**Operazione**|**Oggetto**|
|---|---|---|
|A|Own|File 1|
|A|Read|File 1|
|A|Write|File 1|
|B|Read|File 4|
|C|Write|File 2|

- Ordinando la tabella per **soggetto**, si ottiene una **Capability List**.
    
- Ordinandola per **oggetto**, si ottiene un **ACL**.
    
- È facilmente implementabile tramite **database relazionali**.

La tabella di autorizzazione elimina la sparseness della matrice: si memorizzano solo le triple effettivamente presenti. Ordinando o indicizzando la tabella per soggetto si ottiene un comportamento simile alle capability list; ordinandola per oggetto si ottiene un comportamento simile alle ACL.
    

---

### **4. Matrice di controllo degli accessi estesa**

Lampson, Graham e Denning (1971–72) proposero un modello generale per DAC basato su un **insieme di soggetti, oggetti e regole di accesso**.

#### **Requisiti fondamentali:**

1. **Rappresentare** lo stato di protezione (quali diritti sono assegnati a chi).
    
2. **Far rispettare** i diritti di accesso.
    
3. **Permettere modifiche** controllate ai diritti (es. concessione o revoca).
    

#### **Estensione della matrice:**

Oltre ai file, l’universo degli oggetti include:

- **Processi:** diritti di terminazione, sospensione, riattivazione.
    
- **Dispositivi:** diritti di lettura/scrittura, blocco/sblocco, controllo operativo.
    
- **Regioni di memoria:** diritti di lettura/scrittura su aree protette.
    
- **Soggetti stessi:** possibilità di concedere o revocare diritti ad altri.

Nella matrice estesa anche i soggetti possono comparire come oggetti: questo permette di rappresentare diritti di amministrazione o controllo tra soggetti, ad esempio il fatto che un soggetto possa modificare, creare, eliminare o delegare diritti relativi a un altro soggetto. Il diritto `own` può quindi indicare una forma di proprietà o gerarchia amministrativa.
    

#### **Funzionamento logico:**

1. Un soggetto $S_0$ richiede un’operazione $\alpha$ su un oggetto $X$.
    
2. Il sistema genera un messaggio $(S_0, \alpha, X)$ al controllore di $X$.
    
3. Il controllore interroga la matrice $A$:
    
    - Se $\alpha \in A[S_0, X]$, l’accesso è **consentito**.
        
    - Altrimenti, è **negato**, e viene segnalata una **violazione di protezione**.
        

---

### **5. Domini di protezione**

Un **dominio di protezione** è un insieme di oggetti con i relativi diritti di accesso.

- In termini di matrice di accesso, **ogni riga** rappresenta un dominio.
    
- Tradizionalmente, ogni utente ha un proprio dominio, e tutti i processi da lui generati **condividono gli stessi diritti**.
    
- Tuttavia, un utente può anche creare **sotto-domini** con privilegi ridotti (es. per eseguire un programma non fidato).

Un dominio è quindi un raggruppamento compatto di autorizzazioni: definisce quali oggetti possono essere usati e con quali diritti da un certo insieme di soggetti o processi. In termini di matrice, ragionare per domini equivale a raggruppare permessi comuni, invece di ripeterli separatamente per ogni processo.
    

> L’obiettivo è ridurre al minimo i diritti effettivi di ciascun processo, rispettando il **principio del privilegio minimo**.

#### **Associazione dominio-processo**

- **Statica:** un processo conserva sempre lo stesso dominio.
    
- **Dinamica:** un processo può cambiare dominio (es. passare da lettura a scrittura).
    

**Esempio:**

- In **modalità utente**, certe aree di memoria sono protette.
    
- In **modalità kernel**, sono permesse istruzioni privilegiate e accesso alla memoria protetta.

La distinzione tra modalità utente e modalità kernel è un esempio concreto di dominio di protezione: nel dominio utente alcune istruzioni e aree di memoria sono inaccessibili, mentre nel dominio kernel sono disponibili operazioni privilegiate necessarie al funzionamento del sistema operativo.
    

---

### **6. RBAC – Role-Based Access Control**

Nel modello **RBAC0**, sono presenti quattro tipi di entità fondamentali:

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

|**Entità**|**Descrizione**|
|---|---|
|**Utente**|Persona con accesso al sistema. Ogni utente ha un identificatore univoco (User ID).|
|**Ruolo**|Funzione lavorativa con un insieme definito di permessi.|
|**Autorizzazione**|Diritto o privilegio di eseguire una specifica azione su uno o più oggetti.|
|**Sessione**|Mappatura temporanea tra un utente e un sottoinsieme dei ruoli a lui assegnati.|

#### **Funzionamento**

- Gli utenti **non ricevono direttamente i permessi**, ma li **ereditano dai ruoli**.
    
- Durante una sessione, un utente può **attivare solo alcuni dei ruoli** a lui assegnati.
    
- Il modello semplifica la gestione dei privilegi, riducendo gli errori e migliorando la sicurezza.

RBAC0 è il modello base: modelli più evoluti introducono vincoli e gerarchie tra ruoli. Durante una sessione, l’utente non usa necessariamente tutti i ruoli assegnati, ma solo quelli attivati; i permessi disponibili derivano dai ruoli attivi in quella sessione.

> 📌 Nel DAC i permessi sono legati alla discrezione del proprietario della risorsa; nell’RBAC i permessi sono mediati dai ruoli organizzativi, rendendo più semplice amministrare utenti con mansioni simili.
    

---

### **7. Sintesi finale**

Il **Discretionary Access Control (DAC)** fornisce un sistema **flessibile e diffuso**, dove i proprietari delle risorse gestiscono i permessi secondo necessità.  
Tuttavia, la sua natura “discrezionale” introduce **rischi di propagazione incontrollata dei privilegi**.

> In sintesi:
> 
> - Il DAC è **efficiente e personalizzabile**, ma **meno sicuro** del MAC.
>     
> - I modelli **RBAC e ABAC** rappresentano evoluzioni moderne del controllo discrezionale, orientate alla semplificazione amministrativa e alla gestione centralizzata delle autorizzazioni.
