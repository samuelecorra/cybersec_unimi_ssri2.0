# **M1 UD2 Lezione 1 - Paginazione (parte 2)**

---

### **1. Introduzione**

Nella prima parte abbiamo visto i principi della **paginazione** e il ruolo della **MMU** nel tradurre automaticamente gli indirizzi logici in indirizzi fisici.  
Tuttavia, quando lo spazio di indirizzamento diventa molto ampio (ad esempio nei sistemi a 32 o 64 bit), le **tabelle delle pagine** possono diventare estremamente grandi e difficili da gestire.

In questa lezione analizziamo le principali **tecniche di ottimizzazione** delle tabelle delle pagine e le **funzionalità aggiuntive** legate alla protezione e condivisione delle pagine.

---

### **2. Gestione di grandi tabelle delle pagine**

Quando un processo ha un vasto spazio logico, la sua tabella delle pagine può contenere **migliaia o milioni di voci**.

#### **2.1. I due problemi delle tabelle grandi**

- **La tabella non sta nella MMU**: la memoria interna alla MMU è limitata; tabelle troppo grandi non vi possono essere contenute interamente.
- **Se la tabella sta in RAM, ogni traduzione richiede un accesso alla RAM**: ad ogni indirizzamento del processore, la MMU dovrebbe leggere la voce della tabella delle pagine in memoria centrale → **raddoppio degli accessi** in RAM per ogni operazione del processo, con un pesante impatto sulle prestazioni.

#### **2.2. Strategie di ottimizzazione**

Per ridurre lo spazio necessario e migliorare la velocità di traduzione degli indirizzi, si utilizzano diverse tecniche:

- **Memoria ausiliaria di traduzione (TLB)**
- **Tabelle gerarchiche delle pagine**
- **Tabelle con hashing**
- **Tabelle invertite**

Ognuna di queste strategie mira a trovare un equilibrio tra **rapidità di accesso** e **riduzione dello spazio occupato**.

---

### **3. Memoria ausiliaria di traduzione (TLB)**

La **Translation Look-Aside Buffer (TLB)** è una **memoria associativa** ad alta velocità situata all'interno della **MMU**, specializzata nel contenere le **ultime corrispondenze** tra pagine logiche e pagine fisiche cui si è acceduto, in modo da ritrovarle rapidamente.

![](imgs/Pasted%20image%2020260531221727.png)

#### **Funzionamento (con i tre scenari)**

Il processore genera un indirizzo logico costituito da numero di pagina logica $p$ e spiazzamento $d$. La MMU procede così:

1. **Ricerca nella TLB**: il numero $p$ viene usato come **tag** di ricerca nella memoria associativa.
2. ✅ **TLB hit**: se esiste una riga in TLB con quel numero $p$, viene restituito immediatamente il numero di pagina fisica $f$ associato → si ottiene subito l'indirizzo fisico $(f, d)$.
3. ⚠️ **TLB miss, ma pagina presente in RAM**: se il tag $p$ non è in TLB, la MMU consulta la **tabella delle pagine completa** del processo in esecuzione (eventualmente in RAM). Se la pagina è effettivamente caricata, viene restituito $f$ e la TLB viene aggiornata con la nuova entry.
4. ❌ **TLB miss + Page fault**: se la tabella delle pagine in RAM **non contiene** un riferimento a una pagina fisica per $p$ (perché la pagina logica non è mai stata caricata o è stata scaricata), la MMU **solleva un'interruzione** (trap). La risposta all'interruzione:
   - **carica** la pagina mancante in una pagina fisica disponibile;
   - **aggiorna** la tabella delle pagine;
   - **riprende** l'esecuzione dell'accesso che era stato abortito.

#### **Vantaggio**

Riduce drasticamente il tempo medio di accesso alla memoria: nella **maggior parte dei casi** (TLB hit) la traduzione logico-fisica avviene **senza dover accedere alla RAM** per leggere la tabella delle pagine.

---

### **4. Tabella gerarchica delle pagine**

Per ridurre le dimensioni complessive della tabella, è possibile organizzarla in **più livelli**.  
La forma più comune è la **tabella delle pagine a due livelli**.

![](imgs/Pasted%20image%2020260531222220.png)

#### **Funzionamento**

- La **tabella esterna** funziona come **sommario** delle tabelle interne: contiene **puntatori** a tabelle secondarie più piccole.
- Ogni **tabella secondaria** gestisce una porzione dello spazio di indirizzamento.
- Solo le tabelle effettivamente utilizzate dal processo vengono **create e mantenute in memoria** — le altre non occupano spazio.

#### **Esempio concettuale**

$$
\text{Indirizzo logico} = (\text{Indice}_1, \text{Indice}_2, \text{Offset})
$$

- **Indice₁** → seleziona una voce nella tabella esterna.
- **Indice₂** → seleziona la pagina all'interno della tabella secondaria.
- **Offset** → specifica la posizione esatta nella pagina.

#### **Perché funziona: principio di località**

Un processo tipicamente **opera localmente** nella memoria centrale, sia per il codice (si esegue una funzione alla volta) sia per i dati (si accede a strutture contigue). Di conseguenza, in ogni momento **serve solo una porzione ridotta** della tabella delle pagine — quella relativa allo spazio logico effettivamente utilizzato nell'immediato.

##### **Caricamento on-demand delle sotto-tabelle**

Se durante una traduzione la **sotto-tabella necessaria non è presente** nella MMU/memoria centrale:

1. il sistema carica prima la **porzione di tabella delle pagine** desiderata;
2. dopodiché completa l'accesso all'indirizzo richiesto.

Questa idea può essere **estesa gerarchicamente a tre o più livelli**, fondamentale negli spazi a 64 bit dove anche una tabella a due livelli sarebbe enorme.

![](imgs/Pasted%20image%2020260531223429.png)

---

### **5. Tabella delle pagine con hashing**

Nei sistemi con spazio di indirizzamento molto grande (soprattutto a 64 bit), si adotta un approccio basato su **funzioni di hash**.

![](imgs/Pasted%20image%2020260531222412.png)

#### **Motivazione (località)**

Anche se lo spazio di indirizzamento logico è enorme, un processo tipicamente **non usa tantissime pagine** nell'immediato futuro: a causa del principio di **località**, ne usa un **sotto-insieme abbastanza ridotto**. Una **tabella di hash** molto più piccola dell'intero spazio logico può quindi essere usata per "ridistribuire" le poche pagine effettivamente caricate.

#### **Struttura**

Ogni voce della tabella contiene:

- il **numero della pagina virtuale**,
- il **numero del frame fisico** corrispondente,
- un **puntatore** all'eventuale voce successiva in caso di collisione.

#### **Funzionamento**

1. Si applica una **funzione hash** al numero di pagina virtuale → si ottiene un indice nella tabella.
2. Si accede alla voce corrispondente nella tabella.
3. In **caso di collisione** (più pagine logiche generano lo stesso valore di hash):
   - si **scandisce la lista concatenata** della cella corrispondente;
   - si confronta il numero di pagina logica memorizzato in ciascun elemento con quello cercato;
   - quando si trova la corrispondenza, si estrae il numero di pagina fisica $f$ e si combina con lo spiazzamento $d$ per ottenere l'indirizzo fisico.

Tutte queste operazioni possono essere gestite **direttamente dalla MMU**, se il dispositivo hardware è progettato per supportare questo approccio.

Questo metodo è efficiente in sistemi con molti processi attivi e spazi logici estesi.

---

### **6. Tabella invertita delle pagine**

La **tabella invertita** rappresenta un approccio completamente diverso: invece di mantenere una tabella per ogni processo, il sistema tiene **una sola tabella globale** per tutta la memoria fisica.

#### **Struttura**

$$
\text{TabellaInversa[PaginaFisica]} =
\begin{cases}
(\text{Processo}, \text{PaginaLogica}), & \text{se caricata} \\\\
\text{---}, & \text{se non caricata}
\end{cases}
$$

Ogni voce indica **quale processo** e **quale pagina logica** si trovano in una determinata **pagina fisica**.

#### **Funzionamento operativo**

Il processore genera un indirizzo logico $(p, d)$. A questo indirizzo viene **automaticamente associato l'identificatore del processo in esecuzione** (l'ID è noto al sistema):

1. la coppia **(ID processo, $p$)** viene cercata **scandendo** la tabella invertita;
2. quando viene trovata la riga che la contiene, **l'indice di quella riga** è il numero della **pagina fisica** in cui la pagina logica è caricata;
3. l'indirizzo fisico è quindi $(f, d)$, ottenuto accoppiando l'indice trovato con lo spiazzamento.

Se la coppia **non viene trovata** in alcuna riga, significa che la pagina logica di quel processo non è caricata in nessuna pagina fisica → si verifica un **page fault**.

##### **Perché serve associare il processo alla pagina logica**

La tabella invertita è **unica per tutto il sistema** (corrisponde alle pagine fisiche presenti in RAM, non a uno spazio logico per-processo). Quindi una stessa pagina logica numero $p$ può esistere per processi diversi e finire in pagine fisiche differenti. È **indispensabile** distinguerle, e per questo motivo ogni voce identifica la pagina tramite **(processo, pagina logica)**, non solo tramite il numero di pagina logica.

#### **Caratteristiche**

- È **unica per tutto il sistema**, non per singolo processo.
- Riduce notevolmente lo spazio occupato da più tabelle individuali.
- La ricerca richiede però un meccanismo di confronto più complesso (scansione), spesso supportato da hardware dedicato e da una TLB ben dimensionata.

---

### **7. Protezione delle pagine**

Ogni processo deve poter accedere **solo alle proprie pagine**, garantendo sicurezza e isolamento tra i programmi.

#### **7.1. Protezione implicita: tabella delle pagine per processo**

Il primo livello di protezione è **implicito** nella struttura stessa della paginazione: la **tabella delle pagine di un processo** — mantenuta dal sistema operativo — contiene **solo** le pagine fisiche assegnate a quel processo. Di conseguenza, gli indirizzi logici generati dal processo non possono "uscire" dal suo spazio: la MMU tradurrà solo gli indirizzi che cadono in pagine logiche definite nella sua tabella.

Un processo non ha quindi alcun modo di accedere a pagine fisiche di altri processi semplicemente per costruzione del meccanismo di traduzione.

#### **7.2. Protezione esplicita: bit di protezione**

Se si vuole una **granularità maggiore** — ad esempio distinguere tra pagine **leggibili**, **scrivibili** ed **eseguibili** all'interno dello stesso processo — si introducono **bit di protezione** in ciascuna voce della tabella.

| Tipo di accesso       | Descrizione                                               |
| --------------------- | --------------------------------------------------------- |
| **Lettura/Scrittura** | La pagina può essere letta e modificata.                  |
| **Sola Lettura**      | La pagina può solo essere letta.                          |
| **Sola Esecuzione**   | La pagina contiene codice eseguibile ma non modificabile. |

La **MMU** si fa carico di:

- mantenere e aggiornare i bit di protezione di ogni pagina;
- **verificare la congruità** dell'operazione richiesta dal processore (lettura, scrittura, fetch di istruzione) con i permessi della pagina;
- sollevare un'eccezione/trap in caso di **violazione di accesso** (ad esempio, tentativo di scrittura su pagina di sola lettura).

---

### **8. Condivisione delle pagine**

In alcuni casi, più processi possono condividere porzioni di codice o dati comuni (ad esempio librerie di sistema o moduli condivisi).  
La paginazione semplifica questa operazione: **più tabelle delle pagine possono puntare alla stessa pagina fisica**, mantenendo però i propri permessi di accesso.

![](imgs/Pasted%20image%2020260531224137.png)

#### **Esempio: editor condiviso tra più utenti**

Supponiamo che **tre utenti** vogliano eseguire l'editing di documenti, ciascuno con la propria istanza del processo editor:

- il **codice eseguibile dell'editor** è **identico** per tutte e tre le istanze → invece di caricarlo tre volte, viene caricato **una sola volta** in alcune pagine fisiche;
- le **tre tabelle delle pagine** (una per processo) **puntano alle stesse pagine fisiche** per la sezione del codice;
- le **pagine di dati** (il documento in editing) restano invece **separate**, una versione privata per ogni utente.

#### **Requisito: separazione netta tra codice e dati**

Per poter condividere le pagine di codice, il caricamento del programma deve garantire una **chiara separazione** tra le porzioni di codice e quelle di dati:

- ⚠️ **Le pagine di dati non devono mai "cadere" all'interno di una pagina di codice** — se una pagina fisica contiene sia codice sia dati, non può essere condivisa, perché i dati sono per loro natura privati.

Sarà il **caricatore** (loader) a garantire la condivisione delle pagine, **segnalando al sistema operativo** l'uso delle stesse pagine fisiche da parte dei diversi processi.

#### **Vantaggi**

Ciò consente di:

- **risparmiare memoria fisica** (una sola copia delle pagine condivise anziché $N$);
- favorire la cooperazione tra processi;
- garantire coerenza dei dati condivisi.

---

### **9. Sintesi finale**

- La **paginazione** consente di gestire efficientemente la memoria, spostando solo **piccole porzioni di codice o dati**.
- Le **tecniche avanzate** (TLB, tabelle gerarchiche, con hashing e invertite) riducono i costi in spazio e tempo.
- La **protezione e condivisione** delle pagine assicurano isolamento, sicurezza e cooperazione tra processi.
- Il sistema operativo gestisce tutto in modo **automatico e trasparente**, fornendo a ogni processo l'illusione di disporre di **una memoria continua e privata**, molto più ampia di quella fisicamente presente.

#### **9.1. I tre meriti chiave della paginazione**

In conclusione, la paginazione si distingue per **tre proprietà fondamentali**:

1. **Spazio logico maggiore dello spazio fisico**: la paginazione crea **uno spazio logico più grande dello spazio fisico** assegnato al processo in memoria centrale, caricando dinamicamente le pagine logiche all'occorrenza.
2. **Gestione automatica e indipendente dal programmatore**: tutta la gestione è in carico al sistema operativo (e alla MMU), quindi è **sicura per costruzione** e **non dipende dalla volontà o dall'abilità del programmatore** — al contrario di overlaying, dove la responsabilità ricadeva sull'applicativo.
3. **Gestione efficiente senza ricerca**: lavorando con **piccole porzioni di uguale dimensione**, ogni pagina logica può essere caricata in **qualunque pagina fisica libera** della memoria centrale — **senza dover cercare** un'area di dimensione idonea né compattare la RAM. Questo elimina gran parte dell'overhead delle tecniche precedenti.
