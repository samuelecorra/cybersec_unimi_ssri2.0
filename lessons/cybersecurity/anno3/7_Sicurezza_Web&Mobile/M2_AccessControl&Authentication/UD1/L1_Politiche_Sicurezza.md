# L1 — Politiche di Sicurezza e Controllo degli Accessi

---

### **1. Dalla Pianificazione della Sicurezza al Controllo degli Accessi**

Nella lezione precedente abbiamo visto che la sicurezza va pianificata combinando prevenzione, rilevamento e reazione. Nella fase di **prevenzione**, è necessario:

- Specificare **da chi o da cosa** ci si vuole proteggere.
- Identificare quali **proprietà di sicurezza** devono essere soddisfatte dal sistema.
- Stabilire quali utenti siano **autorizzati** ad accedere a determinate risorse e quali no.
- Definire il **livello di classificazione** delle risorse (es. *top secret*, *secret*, *riservato*, *non classificato*).

In termini operativi, la pianificazione della sicurezza implica tre attività principali:

1. **Definire regole di accesso** e controllare che chi accede alle risorse le rispetti → questo è il dominio del **controllo degli accessi**.
2. **Verificare che il sistema funzioni come previsto** → fase di rilevamento.
3. **Trasmettere i dati in forma cifrata** mediante crittografia e protocolli crittografici → modulo dedicato alla comunicazione sicura.

Questa lezione si concentra sul primo punto.

---

### **2. Obiettivo del Controllo degli Accessi**

> 📌 **Obiettivo del modulo di controllo degli accessi:** garantire che gli utenti abbiano accesso a **tutte e sole** le risorse o i servizi per i quali sono **autorizzati**.

Per raggiungere questo obiettivo, il processo si articola in tre passi:

1. **Definire una politica di sicurezza** che specifichi quali operazioni gli utenti possono compiere sulle risorse.
2. **Scegliere un meccanismo** che implementi (faccia rispettare) la politica.
3. **Verificare** che sia la politica sia il meccanismo siano validi e coerenti tra loro.

---

### **3. Politiche vs. Meccanismi: Separazione dei Livelli di Astrazione**

> 📌 **Politiche e meccanismi operano a livelli di astrazione diversi:**
> - Le **politiche** sono regole ad **alto livello** che descrivono quali accessi sono autorizzati nel sistema.
> - I **meccanismi** sono le **funzioni che implementano** le politiche.

Questa separazione porta vantaggi concreti:

- Mantiene distinte la fase di **analisi dei requisiti** (politica) dall'**implementazione** (meccanismo).
- Permette di **confrontare diverse politiche** per il controllo degli accessi.
- Permette di **confrontare diversi meccanismi** che implementano la stessa politica.
- Permette di progettare meccanismi che implementino **più politiche contemporaneamente**, aumentando la flessibilità del sistema.

**Esempio — Politica di sicurezza in una banca:**
Una banca potrebbe voler garantire le seguenti proprietà di sicurezza:
- Autenticazione dei clienti agli sportelli, agli ATM e sul web (home banking).
- Non ripudio delle transazioni.
- Integrità dei conti correnti.
- Segretezza dei dati interni e dei dati dei clienti.
- Disponibilità di un sistema di allarme.
- Assenza di conflitti di interesse (es. un impiegato che gestisce il proprio conto nella stessa banca).

---

### **4. Linguaggi per Descrivere le Politiche di Sicurezza**

Le politiche di sicurezza possono essere espresse in tre modi:

| Modalità | Vantaggi | Svantaggi |
|----------|----------|-----------|
| **Linguaggio naturale** | Facile da comprendere | Impreciso; adatto alla fase di analisi, non alla specifica formale |
| **Matematica formale** | Molto precisa e non ambigua | Difficile da leggere e comprendere |
| **Linguaggi specifici ad hoc** (pseudo-codice) | Bilanciano precisione e leggibilità | Richiedono conoscenza del linguaggio specifico |

---

### **5. Composizione di Politiche in Sistemi Distribuiti**

In un sistema distribuito come Internet, è inevitabile che politiche di sicurezza definite da entità diverse vengano **composte** tra loro.

> ⚠️ Quando politiche diverse entrano in **conflitto**, le discrepanze tra di esse possono diventare una **vulnerabilità**. Bisogna prestare estrema attenzione quando un sistema complesso nasce dalla composizione di politiche eterogenee: le incongruenze possono aprire falle che nessuna delle singole politiche avrebbe introdotto individualmente.

---

### **6. Terminologia del Controllo degli Accessi**

Prima di descrivere il funzionamento di un sistema di controllo degli accessi, è necessario definire con precisione i termini utilizzati.

#### **6.1. Elementi Principali**

- **Soggetto:** entità **attiva** (es. utente, processo) che richiede l'accesso a una risorsa.
- **Oggetto:** entità **passiva** (es. file, risorsa hardware, record di database) a cui si vuole accedere.
- **Operazione di accesso:** la modalità con cui il soggetto interagisce con l'oggetto (lettura, scrittura, esecuzione, ecc.).

#### **6.2. Permessi e Privilegi**

- **Permesso** (o *diritto di accesso*): la possibilità di accedere a una risorsa secondo una certa modalità (es. permesso di lettura su un file).
- **Privilegio:** un **insieme di permessi** assegnati direttamente a un ruolo specifico (es. amministratore, operatore).

---

### **7. Modalità di Accesso**

A livello elementare, le modalità di accesso di un soggetto su un oggetto si riducono a due:

1. **Osservazione** dell'oggetto (lettura).
2. **Alterazione/modifica** dell'oggetto (scrittura).

In pratica, le modalità variano a seconda della tipologia di oggetto:

| Tipo di oggetto | Modalità di accesso tipiche |
|----------------|---------------------------|
| **File / dati generici** | Lettura (*read*), scrittura (*write*), append |
| **Processi / eseguibili** | Esecuzione (*execute*) |
| **Database (DBMS)** | Select, insert, update, delete |

**Esempio pratico — Sistemi Unix:**

Nei sistemi Unix, le modalità di accesso su **file** sono tre:

| Permesso | Simbolo | Significato |
|----------|---------|-------------|
| *Read* | `r` | Permesso di leggere il contenuto del file |
| *Write* | `w` | Permesso di scrivere/modificare il file |
| *Execute* | `x` | Permesso di eseguire il file come programma |

Le stesse tre modalità si applicano alle **directory**, ma con significato diverso:

| Permesso | Simbolo | Significato sulle directory |
|----------|---------|---------------------------|
| *Read* | `r` | Permesso di elencare il contenuto della directory (`ls`) |
| *Write* | `w` | Permesso di creare, rinominare o eliminare file nella directory |
| *Execute* | `x` | Permesso di attraversare/ricercare nella directory (accesso ai file in essa contenuti) |

---

### **8. Il Reference Monitor**

Il controllo degli accessi viene tipicamente gestito dal **sistema operativo**, tramite un componente logico chiamato **Reference Monitor**.

> 📌 **Reference Monitor:** macchina astratta che **media tutte le richieste di accesso** e decide come rispondere a ciascuna, in base alla politica di sicurezza definita. È concettualmente una "guardia" interposta tra ogni soggetto e ogni oggetto del sistema.

**Funzionamento:**

```
Soggetto → [richiesta di accesso a Oggetto con Operazione X]
                        ↓
              Reference Monitor
                        ↓
         consulta la Politica di Sicurezza
                        ↓
        ACCESSO CONCESSO  /  ACCESSO NEGATO
```

La correttezza dell'intero sistema di controllo degli accessi dipende da due condizioni:
1. **Corretta identificazione e autenticazione** dell'utente che effettua la richiesta.
2. **Corretta definizione e implementazione** della politica di sicurezza.

---

### **9. Autenticazione vs. Autorizzazione**

Autenticazione e autorizzazione sono concetti **strettamente correlati ma distinti**:

> 📌 **Autenticazione:** il Reference Monitor **verifica l'identità** del soggetto che effettua la richiesta. Risponde alla domanda: *"Chi sei?"*

> 📌 **Autorizzazione:** una volta autenticato il soggetto, il Reference Monitor decide se **concedere o negare l'accesso** alla risorsa, in base alla politica di sicurezza. Risponde alla domanda: *"Hai il diritto di fare questa operazione?"*

Il Reference Monitor gestisce quindi **due fasi distinte e sequenziali**:
1. **Autenticazione/Identificazione** dell'utente.
2. **Autorizzazione** (o negazione) dell'accesso alla risorsa richiesta.

---

### **10. Riepilogo**

> ✅ Il controllo degli accessi garantisce che ogni utente acceda a tutte e sole le risorse per cui è autorizzato. Si articola in: definizione di una **politica** (regole ad alto livello), scelta di un **meccanismo** (implementazione), e verifica della loro coerenza. Il **Reference Monitor** è il componente che fa rispettare la politica, gestendo sequenzialmente **autenticazione** (chi sei?) e **autorizzazione** (cosa puoi fare?). Le politiche e i meccanismi operano a livelli di astrazione distinti: tenerli separati aumenta la chiarezza, la confrontabilità e la flessibilità del sistema.
