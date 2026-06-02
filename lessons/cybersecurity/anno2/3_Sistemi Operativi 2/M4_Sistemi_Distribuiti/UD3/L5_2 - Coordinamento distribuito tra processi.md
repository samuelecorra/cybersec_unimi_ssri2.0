# **M4 UD3 Lezione 5 - Coordinamento distribuito tra processi (parte 2)**

---

### **1. Introduzione**

Questa seconda parte approfondisce ulteriori aspetti del coordinamento tra processi in ambiente distribuito.

Gli argomenti principali sono:

- atomicità delle transazioni distribuite;
- protocollo di commit a due fasi;
- gestione della concorrenza;
- protocolli bloccanti per i lock;
- elezione del coordinatore.

> 📌 Dopo l'ordinamento degli eventi e la mutua esclusione, il coordinamento distribuito deve garantire atomicità, concorrenza corretta e continuità dei coordinatori.

---

### **2. Atomicità nelle transazioni distribuite**

L'**atomicità** richiede che una sequenza di azioni venga vista come indivisibile.

Nel caso delle transazioni, questo significa che una transazione deve:

- completarsi interamente;
- oppure non produrre alcun effetto permanente.

In un ambiente distribuito, la transazione può coinvolgere più macchine.

Bisogna quindi estendere i concetti già visti per una singola macchina al caso in cui la transazione sia spezzata in più parti distribuite.

---

### **3. Coordinatori locali**

In ogni macchina è presente un **coordinatore locale** delle transazioni.

Il coordinatore locale deve:

- iniziare una transazione;
- spezzarla in sotto-transazioni;
- distribuire le sotto-transazioni sulle macchine opportune;
- raccogliere i risultati;
- decidere come gestire il risultato complessivo.

Se tutte le sotto-transazioni vengono completate correttamente, la transazione globale può essere confermata.

Se anche una sola parte fallisce, la transazione globale deve essere annullata.

> ⚠️ In una transazione distribuita non basta che una singola macchina completi correttamente: devono cooperare tutti i siti coinvolti.

---

### **4. Protocollo di commit a due fasi**

Per verificare il completamento coordinato di tutte le sotto-transazioni si usa il **protocollo di commit a due fasi**.

Il protocollo coinvolge:

- il coordinatore della macchina da cui parte la transazione;
- i coordinatori locali dei siti coinvolti.

Supponiamo che la transazione $T$ inizi sulla macchina $S_i$, con coordinatore $C_i$.

Tutti i siti attivati dalla transazione devono cooperare per decidere se $T$ può essere chiusa con commit o deve essere abortita.

---

### **5. Prima fase: prepare**

Quando tutte le sotto-transazioni hanno terminato localmente la parte assegnata, il coordinatore $C_i$ avvia la prima fase.

Il coordinatore:

1. aggiunge al proprio log una registrazione di preparazione, per esempio:

```text
<prepare T>
```

2. invia a tutte le macchine coinvolte un messaggio:

```text
prepare T
```

che chiede di preparare il completamento della transazione.

Quando una macchina $S_j$ riceve il messaggio, il suo coordinatore locale $C_j$ decide se il commit locale è possibile.

#### **5.1. Commit non possibile**

Se il commit locale non è possibile, $C_j$:

- registra nel proprio log:

```text
no T
```

- invia al coordinatore centrale:

```text
abort T
```

#### **5.2. Commit possibile**

Se il commit locale è possibile, $C_j$:

- registra nel proprio log:

```text
ready T
```

- invia al coordinatore centrale:

```text
ready T
```

> 📌 Nella prima fase ogni sito dichiara se è pronto a completare la propria parte della transazione.

---

### **6. Seconda fase: decisione**

Nella seconda fase, il coordinatore $C_i$ raccoglie le risposte.

Può decidere:

- commit;
- abort.

#### **6.1. Decisione di commit**

Se $C_i$ riceve `ready T` da tutte le macchine coinvolte, allora decide il commit globale.

Registra nel proprio log:

```text
commit T
```

e invia a tutti i siti:

```text
commit T
```

#### **6.2. Decisione di abort**

Se almeno un sito invia `abort T`, oppure se non arrivano tutte le risposte entro il timeout massimo, il coordinatore decide l'abort.

Registra nel proprio log:

```text
abort T
```

e invia a tutti i siti:

```text
abort T
```

#### **6.3. Chiusura locale**

Quando un coordinatore locale riceve la decisione finale:

- registra `commit T` oppure `abort T` nel proprio log;
- termina localmente la gestione della sotto-transazione.

> ✅ Il commit a due fasi garantisce che tutti i siti arrivino alla stessa decisione finale.

---

### **7. Tolleranza ai guasti del 2PC**

Il protocollo di commit a due fasi ha una buona tolleranza ai guasti rispetto a:

- macchine coinvolte;
- struttura di interconnessione;
- ritardi o perdite di comunicazione.

L'uso dei log consente di ricostruire lo stato della transazione dopo un guasto.

Il timeout consente al coordinatore di prendere una decisione anche quando alcune risposte non arrivano in tempo.

> ⚠️ Il 2PC migliora la consistenza, ma può introdurre attese se un partecipante o il coordinatore non risponde.

---

### **8. Gestione della concorrenza**

In un ambiente distribuito, più processi o transazioni possono richiedere accesso alle stesse risorse.

Per gestire la concorrenza servono:

- gestori di transazioni locali;
- gestori di transazioni globali;
- file di log;
- protocolli per l'accesso in mutua esclusione;
- protocolli di lock.

Esistono due famiglie generali:

- protocolli bloccanti;
- protocolli non bloccanti.

Questa lezione si concentra sui protocolli bloccanti.

---

### **9. Protocolli bloccanti**

I protocolli bloccanti gestiscono l'accesso alle risorse tramite lock.

Garantiscono che una risorsa sia usata in modo corretto, un processo o una transazione alla volta quando necessario.

Il blocco può essere:

- **esclusivo**, quando solo un processo può usare la risorsa;
- **condiviso**, quando più processi possono usarla contemporaneamente senza compromettere la consistenza.

La replicazione locale dei dati può essere usata per migliorare la velocità di accesso.

---

### **10. Coordinatore centralizzato dei lock**

Nel protocollo con coordinatore centralizzato, esiste un unico processo responsabile della gestione dei lock.

Caratteristiche:

- i dati non sono replicati;
- esiste un unico responsabile globale dei lock;
- la richiesta è semplice;
- un processo invia richiesta e attende risposta;
- al termine rilascia la risorsa.

Vantaggi:

- realizzazione semplice;
- controllo centralizzato.

Svantaggi:

- prestazioni scarse per collo di bottiglia;
- bassa tolleranza ai guasti;
- gestione degli stalli complicata;
- dipendenza da un singolo coordinatore.

> ⚠️ Il coordinatore centralizzato è semplice, ma vulnerabile e poco scalabile.

---

### **11. Coordinatori multipli dei lock**

Nel protocollo con coordinatori multipli, ogni macchina gestisce i lock delle proprie risorse locali.

Caratteristiche:

- i dati non sono replicati;
- ogni risorsa ha un responsabile locale;
- la richiesta resta semplice;
- non esiste un unico punto centralizzato per tutti i lock.

Vantaggi:

- prestazioni migliori rispetto al coordinatore unico;
- carico distribuito;
- responsabilità vicina alla risorsa.

Svantaggi:

- gestione degli stalli più complessa;
- per rilevare uno stallo può essere necessario interrogare più macchine.

---

### **12. Coordinatore a maggioranza**

Nel protocollo a maggioranza, i dati sono replicati su più macchine.

Se esistono $N$ copie della risorsa, esistono $N$ gestori locali del lock.

Per ottenere il lock globale, il processo deve ottenere il consenso da una maggioranza:

$$
\left\lfloor \frac{N}{2} \right\rfloor + 1
$$

lock locali.

Il processo richiedente può iniziare interrogando una maggioranza di coordinatori.

Se non ottiene abbastanza autorizzazioni, può interrogare altri coordinatori finchè non raggiunge il numero necessario.

Vantaggi:

- non è necessario contattare tutti i coordinatori;
- maggiore tolleranza ai guasti;
- la risorsa può restare disponibile anche se alcune copie non rispondono.

Svantaggi:

- realizzazione complessa;
- molti messaggi per ottenere e rilasciare il lock;
- algoritmi di stallo da modificare per gestire la maggioranza.

> 📌 Nel lock a maggioranza la risorsa è concessa quando si ottiene il consenso della maggioranza delle copie.

---

### **13. Protocollo polarizzato**

Il protocollo polarizzato è una variante del coordinatore a maggioranza.

Anche qui:

- i dati sono replicati;
- ogni macchina ha un responsabile locale dei lock.

La differenza è che le richieste vengono trattate diversamente in base al tipo di accesso.

#### **13.1. Accesso condiviso**

Se il lock richiesto è condiviso, basta una richiesta locale.

Questo riduce il sovraccarico per operazioni di lettura o comunque compatibili.

#### **13.2. Accesso esclusivo**

Se il lock richiesto è esclusivo, serve una richiesta globale.

Bisogna ottenere la certezza che nessun altro processo acceda alla risorsa in conflitto.

Vantaggi:

- minore overhead per letture;
- migliore efficienza quando prevalgono accessi condivisi.

Svantaggi:

- overhead simile alla maggioranza per scritture;
- difficoltà di gestione degli stalli.

> 💡 Il protocollo polarizzato sfrutta l'asimmetria tra lettura condivisa e scrittura esclusiva.

---

### **14. Necessità di eleggere un coordinatore**

Molte tecniche distribuite richiedono processi di coordinamento.

I coordinatori possono servire per:

- mutua esclusione;
- rilevamento degli stalli;
- sostituzione di token persi;
- gestione dell'ingresso/uscita;
- coordinamento delle transazioni.

Se un coordinatore muore, diventa irraggiungibile o non è più usabile, bisogna eleggere un nuovo coordinatore.

---

### **15. Algoritmo del bullo**

Nell'**algoritmo del bullo**, ogni processo ha una priorità.

Il processo con priorità più alta tra quelli disponibili deve diventare coordinatore.

Quando un processo $P$ si accorge che il coordinatore non funziona:

1. invia un messaggio di elezione a tutti i processi con priorità più alta;
2. attende una risposta entro un timeout $T$.

Se non riceve risposta, $P$ elegge se stesso coordinatore e informa tutti.

Se riceve risposta, significa che un processo con priorità maggiore è attivo.

Allora $P$ attende l'identificatore del nuovo coordinatore.

Se dopo un timeout non riceve l'identificatore del nuovo coordinatore, fa ripartire l'algoritmo.

> ✅ Nell'algoritmo del bullo vince il processo attivo con priorità più alta.

---

### **16. Algoritmo dell'anello**

Nell'**algoritmo dell'anello**, i processi sono organizzati in un anello logico.

La comunicazione procede in modo unidirezionale lungo l'anello.

L'elezione costruisce una lista dei processi attivi.

Quando un processo $P$ rileva che il coordinatore non funziona:

1. crea una lista attiva vuota;
2. invia al processo successivo un messaggio di elezione con la lista.

Quando un processo riceve un messaggio di elezione:

- se è il primo messaggio di elezione che riceve, crea o aggiorna la lista attiva includendo se stesso e inoltra il messaggio;
- se il messaggio non contiene il proprio identificatore, aggiunge il predecessore o il proprio identificatore alla lista e inoltra;
- se il messaggio contiene il proprio identificatore, la lista è completa.

Quando la lista attiva è completa, ogni processo può determinare il nuovo coordinatore scegliendo il processo con priorità più alta.

Se il processo stesso è quello a priorità più alta, diventa coordinatore.

Altrimenti sa quale processo ha assunto il ruolo.

> 📌 L'algoritmo dell'anello elegge il coordinatore costruendo cooperativamente la lista dei processi attivi.

---

### **17. Sintesi**

| Tema | Tecnica | Scopo |
|---|---|---|
| **Atomicità** | Commit a due fasi | Confermare o annullare globalmente una transazione |
| **2PC - fase 1** | Prepare | Verificare se i siti sono pronti |
| **2PC - fase 2** | Commit/abort | Diffondere la decisione finale |
| **Concorrenza** | Protocolli di lock | Garantire accesso corretto alle risorse |
| **Lock centralizzato** | Coordinatore unico | Semplice ma fragile |
| **Lock multipli** | Coordinatori locali | Migliori prestazioni, stalli più complessi |
| **Lock a maggioranza** | Consenso della maggioranza delle copie | Tolleranza ai guasti con dati replicati |
| **Protocollo polarizzato** | Letture locali, scritture globali | Ridurre overhead in lettura |
| **Algoritmo del bullo** | Priorità più alta | Eleggere coordinatore |
| **Algoritmo dell'anello** | Lista attiva su anello logico | Eleggere coordinatore in modo cooperativo |

---

### **18. Conclusione**

Il coordinamento distribuito richiede tecniche diverse a seconda del problema.

L'atomicità delle transazioni viene gestita con il commit a due fasi, che coordina la decisione tra tutti i siti coinvolti.

La concorrenza viene gestita tramite protocolli di lock centralizzati, distribuiti, a maggioranza o polarizzati.

Quando un coordinatore non è più disponibile, algoritmi come il bullo e l'anello permettono di eleggerne uno nuovo e mantenere il sistema operativo.
