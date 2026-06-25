# L3 — La Matrice degli Accessi e le sue Implementazioni

---

### **1. Il Problema della Memorizzazione della Politica**

Una volta definita una politica di controllo degli accessi, sorge un problema **implementativo**: come memorizzare in modo efficiente l'insieme delle regole di accesso?

L'informazione da memorizzare, per ogni coppia (soggetto, oggetto), è:
- Il soggetto ha qualche permesso di accesso sull'oggetto?
- Se sì, in quale modalità?

La struttura dati più naturale è la **matrice degli accessi**:

- Le **righe** sono indicizzate dai **soggetti**.
- Le **colonne** sono indicizzate dagli **oggetti**.
- Ogni **cella** $(S_i, O_j)$ specifica il privilegio (o l'insieme di privilegi) che il soggetto $S_i$ ha sull'oggetto $O_j$.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ La matrice degli accessi è in generale **grande e sparsa**: la maggior parte delle celle è vuota (la maggior parte dei soggetti non ha accesso alla maggior parte degli oggetti). Memorizzare l'intera matrice esplicitamente è quindi **inefficiente** in termini di spazio.

---

### **2. Le Tre Implementazioni della Matrice degli Accessi**

Per ovviare all'inefficienza, la matrice degli accessi viene memorizzata in forma compressa secondo tre approcci:

| Approccio | Struttura | Direzione di compressione |
|-----------|-----------|--------------------------|
| **Access Control List (ACL)** | Per ogni oggetto, lista dei soggetti con i relativi permessi | Per **colonne** |
| **Capability List** | Per ogni soggetto, lista degli oggetti con i relativi permessi | Per **righe** |
| **Tabella di autorizzazione** | Solo le triple $(S, O, \text{permesso})$ non nulle | Solo le celle non vuote |

---

### **3. Access Control List (ACL)**

L'ACL implementa la matrice degli accessi **per colonne**: per ciascun oggetto si mantiene un elenco dei soggetti che possono accedervi e con quale privilegio.

**Struttura:**
$$\text{ACL}(O_j) = \{(S_i, p_{ij}) \mid p_{ij} \neq \emptyset\}$$

**Vantaggi:**
- Rappresentazione **compatta** rispetto alla matrice completa.
- Fornisce un **sommario immediato** di chi può accedere a un certo oggetto — utile per verificare rapidamente le autorizzazioni su una risorsa specifica.
- Particolarmente efficiente quando ci sono **molti soggetti e pochi oggetti**.

**Svantaggi:**
- **Revoca difficile**: per rimuovere un soggetto da tutte le sue autorizzazioni, occorre scorrere *tutte* le ACL di *tutti* gli oggetti e rimuovere le occorrenze del soggetto.

> 💡 Le ACL sono lo strumento nativo di Unix/Linux per il controllo degli accessi ai file (i 9 bit `rwxrwxrwx` visibili con `ls -l` sono una forma compatta di ACL con tre categorie: proprietario, gruppo, altri).

---

### **4. Capability List**

La capability list implementa la matrice degli accessi **per righe**: per ciascun soggetto si mantiene un elenco degli oggetti a cui può accedere e con quale privilegio.

**Struttura:**
$$\text{CAP}(S_i) = \{(O_j, p_{ij}) \mid p_{ij} \neq \emptyset\}$$

**Vantaggi:**
- Rappresentazione **compatta**.
- Particolarmente utile quando si vuole **delegare i propri privilegi** a un altro soggetto: basta trasferire (parte de)lla propria capability list.

**Svantaggi:**
- **Revoca difficile**: per revocare un permesso specifico, occorre scorrere *tutte* le capability list di *tutti* i soggetti e rimuovere le occorrenze dell'oggetto/permesso da revocare.

---

### **5. Tabelle di Autorizzazione**

Le tabelle di autorizzazione memorizzano esplicitamente solo le **triple non nulle** $(S_i, O_j, p_{ij})$ della matrice, scartando tutte le celle vuote.

$$\text{AuthTable} = \{(S_i, O_j, p_{ij}) \mid p_{ij} \neq \emptyset\}$$

Questo è il meccanismo tipicamente utilizzato dai **sistemi di gestione di database (DBMS)**, dove le regole di accesso sono gestite come record di una relazione.

---

### **6. Implementazione in Linux (Unix-like)**

I sistemi operativi **Unix-like** (incluso Linux) implementano un meccanismo **discrezionario (DAC)** per il controllo degli accessi, basato su ACL semplificate.

**Soggetti in Linux:**
- Ogni **utente** identificato da un UID.
- L'utente speciale **root** (UID 0), amministratore di sistema con privilegi illimitati.

**Protezione dei file:**
Ogni file in Linux ha:
- Un **proprietario** (utente).
- Un **gruppo** di appartenenza.
- Un insieme di **modalità di accesso** (`read`, `write`, `execute`) separate per tre categorie:
  1. **Proprietario** (user).
  2. **Gruppo** (group).
  3. **Resto del mondo** (others).

Questi permessi sono codificati in **9 bit** e visualizzabili con il comando `ls -l`:

```
-rwxr-xr--  1 alice staff  4096 ...
 ^^^         proprietario: rwx (lettura + scrittura + esecuzione)
    ^^^      gruppo:       r-x (lettura + esecuzione)
       ^^^   altri:        r-- (solo lettura)
```

> 💡 I dettagli pratici su come leggere e modificare i permessi in Linux (comandi `chmod`, `chown`, `chgrp`, bit speciali SUID/SGID/sticky) saranno approfonditi nelle sessioni di laboratorio.

**Esecuzione dei programmi:**
- I programmi vengono eseguiti in **aree di memoria protette**.
- In genere un programma viene eseguito con i **privilegi del chiamante**.
- Esistono bit speciali (SUID, SGID) che consentono a un programma di essere eseguito con i privilegi del *proprietario del file* anziché del chiamante — meccanismo potente ma da usare con cautela dal punto di vista della sicurezza (anch'esso approfondito nel laboratorio).

---

### **7. Note Storiche**

Le politiche discrezionarie e mandatorie sono considerate "storiche": i modelli Bell-LaPadula e Biba risalgono agli **anni '80** e furono presentati per la prima volta in articoli scientifici dell'epoca. Sono meno utilizzate nella loro forma pura in sistemi commerciali moderni (dove predomina RBAC), ma rimangono fondamentali per comprendere i principi teorici del controllo degli accessi e trovano ancora applicazione in contesti militari e governativi.

---

### **8. Riepilogo**

> ✅ La matrice degli accessi è lo strumento concettuale per rappresentare una politica di controllo degli accessi, ma è troppo sparsa per essere memorizzata esplicitamente. Le tre implementazioni pratiche sono: **ACL** (per oggetto, efficiente per sapere chi accede a una risorsa), **capability list** (per soggetto, efficiente per la delega) e **tabella di autorizzazione** (solo triple non nulle, tipica dei DBMS). Linux usa ACL semplificate a 9 bit con tre livelli (proprietario, gruppo, altri). La revoca è onerosa in entrambe le prime due strutture: nelle ACL occorre scorrere tutti gli oggetti, nelle capability list tutti i soggetti.
