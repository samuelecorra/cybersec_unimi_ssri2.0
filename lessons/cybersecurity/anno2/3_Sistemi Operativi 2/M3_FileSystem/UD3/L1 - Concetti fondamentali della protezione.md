# **M3 UD3 Lezione 1 - Concetti fondamentali della protezione**

---

### **1. Introduzione**

Questa lezione introduce i concetti fondamentali della **protezione delle risorse** nell'ambito del file system e, più in generale, del sistema operativo.

La protezione ha lo scopo di impedire accessi non autorizzati alle risorse del sistema.

Gli accessi da evitare possono essere:

- accessi errati compiuti accidentalmente;
- accessi non autorizzati compiuti intenzionalmente;
- accessi malevoli diretti a danneggiare dati, processi o strutture del sistema.

> 📌 Proteggere una risorsa significa stabilire chi può usarla, come può usarla e con quali limiti.

---

### **2. Obiettivi della protezione**

L'obiettivo della protezione è definire e far rispettare le **autorizzazioni** relative all'uso delle risorse.

Per ogni risorsa, il sistema deve stabilire:

- quali soggetti possono accedervi;
- quali operazioni possono eseguire;
- in quali condizioni l'accesso è consentito;
- come impedire accessi non ammessi.

Nel file system, questo significa controllare l'uso di file, directory e strutture informative.

Nel sistema operativo in generale, il problema si estende anche alle risorse fisiche e alle strutture di comunicazione tra processi.

---

### **3. Regole e meccanismi**

La protezione distingue tra **regole** e **meccanismi**.

#### **3.1. Regole**

Le regole definiscono:

- chi può usare una risorsa;
- quali operazioni può eseguire;
- quali vincoli devono essere rispettati.

Le regole esprimono quindi la politica di protezione.

#### **3.2. Meccanismi**

I meccanismi sono gli strumenti tecnici usati per imporre le regole.

Esempi di meccanismi sono:

- controlli di accesso;
- domini di protezione;
- matrici di accesso;
- liste di controllo degli accessi;
- capability;
- revoca dei diritti.

> 💡 Le regole dicono cosa deve essere permesso; i meccanismi realizzano concretamente quel controllo.

---

### **4. Risorse da proteggere**

Il sistema operativo deve proteggere tutte le risorse messe a disposizione dei processi per realizzare la computazione.

Le risorse si dividono in due grandi categorie.

#### **4.1. Risorse fisiche**

Sono risorse hardware o direttamente legate all'hardware, per esempio:

- processore;
- memoria centrale;
- periferiche;
- dispositivi di ingresso/uscita.

#### **4.2. Risorse informative**

Sono risorse logiche o informative, per esempio:

- file;
- directory;
- strutture di comunicazione tra processi;
- strutture di sincronizzazione;
- aree condivise;
- metadati del file system.

Ogni risorsa è caratterizzata da:

- un identificativo;
- un insieme di operazioni che possono essere eseguite su di essa.

> ⚠️ La protezione non riguarda solo i file: riguarda qualunque risorsa che un processo potrebbe usare in modo improprio.

---

### **5. Principio di minima conoscenza**

La definizione della protezione si basa sul **principio di minima conoscenza**, collegato al principio del minimo privilegio.

Un processo deve poter accedere solo alle risorse strettamente necessarie alla propria computazione.

In questo modo:

- si riduce la possibilità di errori accidentali;
- si limita il danno causabile da un processo compromesso;
- si impedisce l'uso improprio di risorse non necessarie;
- si protegge il sistema e gli altri processi.

> ✅ Un processo non dovrebbe poter vedere o usare risorse che non gli servono.

---

### **6. Dominio di protezione**

Un **dominio di protezione** descrive l'insieme delle risorse e delle operazioni lecite che un processo può effettuare quando opera in quel dominio.

Formalmente, un dominio è un insieme di coppie:

$$
D = \{(o_1, R_1), (o_2, R_2), \dots, (o_n, R_n)\}
$$

dove:

- $o_i$ è un oggetto, cioè una risorsa da proteggere;
- $R_i$ è l'insieme dei diritti ammessi su quell'oggetto.

I diritti indicano le operazioni lecite, per esempio:

- lettura;
- scrittura;
- esecuzione;
- stampa;
- cancellazione;
- modifica degli attributi;
- cambiamento di dominio.

> 📌 Un dominio di protezione è una collezione di autorizzazioni: oggetto più diritti ammessi su quell'oggetto.

---

### **7. Rappresentazione dei domini**

Dal punto di vista grafico, un dominio può essere visto come un insieme di regole.

Ogni regola associa:

- una risorsa;
- un insieme di operazioni consentite.

Due domini possono essere:

- **disgiunti**, se non hanno risorse o diritti in comune;
- **intersecati**, se condividono almeno una risorsa con almeno un diritto comune.

Per esempio, due domini possono entrambi consentire l'uso della stessa risorsa in stampa.

![](imgs/Pasted%20image%2020260601225215.png)

> 💡 L'intersezione tra domini indica che processi operanti in domini diversi possono avere alcune autorizzazioni comuni.

---

### **8. Associazione tra processo e dominio**

Quando un processo viene attivato, gli deve essere associato un dominio di protezione.

Questa associazione stabilisce:

- quali risorse può usare;
- quali operazioni può eseguire su ciascuna risorsa;
- quali cambiamenti di dominio può eventualmente richiedere.

L'associazione può essere:

- statica;
- dinamica.

---

### **9. Associazione statica**

Nell'associazione **statica**, il dominio viene scelto all'attivazione del processo e resta invariato per tutta la sua esecuzione.

Vantaggi:

- realizzazione semplice;
- controllo immediato;
- minore complessità di gestione.

Limiti:

- poca flessibilità;
- il processo potrebbe conservare diritti che gli servono solo in alcune fasi;
- può essere difficile applicare rigorosamente il principio di minima conoscenza.

---

### **10. Associazione dinamica**

Nell'associazione **dinamica**, il dominio associato al processo può cambiare durante l'esecuzione.

Il cambiamento può avvenire:

- su richiesta del processo;
- su richiesta del gestore del sistema;
- su richiesta di altri processi autorizzati;
- per modifica delle regole associate al dominio.

In quest'ultimo caso, l'identificativo simbolico del dominio può restare lo stesso, ma cambiano i diritti contenuti nel dominio.

> 📌 Nell'associazione dinamica può cambiare il dominio del processo oppure possono cambiare le regole associate al dominio.

---

### **11. Cambiamento di dominio**

Il cambiamento di dominio è esso stesso un'operazione soggetta a protezione.

Un processo può passare da un dominio $D_i$ a un dominio $D_j$ solo se possiede il diritto di effettuare tale cambiamento.

In questo modello, il dominio può essere trattato come una risorsa.

Se nel dominio $D_i$ compare la risorsa $D_j$ con il diritto di cambiamento, allora il processo può richiedere il passaggio a $D_j$.

Formalmente:

$$
(D_j, \text{switch}) \in D_i
$$

Se questa coppia appartiene a $D_i$, il processo operante in $D_i$ può invocare il cambiamento ed entrare in $D_j$.

> ⚠️ Anche il passaggio da un dominio a un altro deve essere autorizzato esplicitamente.

---

### **12. Modifica delle caratteristiche di un dominio**

Le caratteristiche di un dominio possono cambiare nel tempo.

Il sistema può:

- aggiungere nuovi diritti;
- revocare diritti esistenti;
- modificare le operazioni consentite su una risorsa;
- cambiare l'insieme delle risorse visibili nel dominio.

Queste operazioni devono essere controllate, perchè modificano direttamente le autorizzazioni dei processi che operano in quel dominio.

---

### **13. Revoca dei diritti**

La **revoca** consiste nella rimozione di diritti concessi in precedenza.

La revoca può essere classificata secondo più criteri.

#### **13.1. Revoca immediata o ritardata**

La revoca è **immediata** se ha effetto appena viene richiesta.

È **ritardata** se viene applicata solo dopo il completamento di alcune operazioni già in corso.

#### **13.2. Revoca selettiva o generale**

La revoca è **selettiva** se riguarda un singolo processo o utente.

È **generale** se riguarda tutti i processi o tutti gli utenti che possiedono quel diritto.

#### **13.3. Revoca parziale o totale**

La revoca è **parziale** se rimuove solo alcuni diritti.

È **totale** se elimina completamente la possibilità di usare una risorsa.

#### **13.4. Revoca temporanea o permanente**

La revoca è **temporanea** se sospende un diritto solo per un certo periodo.

È **permanente** se rimuove definitivamente il diritto.

> ✅ La revoca permette di adattare nel tempo le autorizzazioni e di reagire a errori, cambiamenti organizzativi o situazioni di rischio.

---

### **14. Sintesi**

| Concetto | Descrizione |
|---|---|
| **Protezione** | Difesa delle risorse da accessi non autorizzati, accidentali o malevoli |
| **Regole** | Specificano chi può usare cosa e in che modo |
| **Meccanismi** | Strumenti che impongono il rispetto delle regole |
| **Risorse fisiche** | Processore, memoria centrale, periferiche |
| **Risorse informative** | File, directory, strutture di comunicazione e sincronizzazione |
| **Principio di minima conoscenza** | Ogni processo accede solo alle risorse strettamente necessarie |
| **Dominio di protezione** | Insieme di coppie oggetto-diritti |
| **Associazione processo-dominio** | Statica o dinamica |
| **Cambiamento di dominio** | Operazione lecita solo se autorizzata |
| **Revoca** | Rimozione immediata/ritardata, selettiva/generale, parziale/totale, temporanea/permanente |

---

### **15. Conclusione**

La protezione delle risorse definisce quali operazioni un processo può effettuare sulle risorse fisiche e informative del sistema.

Il modello dei domini di protezione permette di rappresentare formalmente queste autorizzazioni come insiemi di coppie oggetto-diritti.

L'associazione tra processi e domini può essere statica o dinamica, e il cambiamento di dominio deve essere a sua volta autorizzato.

La revoca dei diritti completa il modello, permettendo di modificare nel tempo le autorizzazioni con granularità diversa.
