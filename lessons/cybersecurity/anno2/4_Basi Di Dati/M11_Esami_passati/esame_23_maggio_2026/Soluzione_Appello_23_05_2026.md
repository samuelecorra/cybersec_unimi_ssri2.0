# **Basi di Dati - Soluzione completa dell'appello del 23 maggio 2026**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonti originali: [pagina 1](esame_23_maggio_2026_1.jpg) · [pagina 2](esame_23_maggio_2026_2.jpg)

> ⚠️ Le fotografie mostrano il testo principale ma non due allegati espressamente citati: il foglio con le affermazioni della Domanda 4 e le tabelle di richieste/risposte dell'Esercizio 1. Per tali parti è riportato il procedimento completo, ma non vengono inventate righe o risposte non presenti nella fonte.

---

### **1. Domanda 1 - Two-Phase Commit**

#### **1.1. Traccia originale**

> Con riferimento alle basi di dati distribuite illustrare il funzionamento del protocollo two-phase commit. Illustrare dapprima il funzionamento in assenza di guasti e poi descrivere cosa succede in caso di guasti e le azioni di ripristino necessarie.

**Riferimenti:** [M7/UD3/L3 parte 1](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>) · [M7/UD3/L3 parte 2](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>)

#### **1.2. Ruoli e obiettivo**

Il Two-Phase Commit garantisce l'**atomicità globale**: tutti i Resource Manager partecipanti devono committare oppure tutti devono abortire. Il **Transaction Manager** coordina e decide; ogni **Resource Manager** gestisce la sotto-transazione e le risorse locali. Log persistenti e messaggi rendono recuperabile lo stato del protocollo.

#### **1.3. Esecuzione senza guasti**

**Prima fase - prepare/vote.** Il TM scrive `prepare`, includendo i partecipanti, invia `prepare` a tutti e avvia un timeout. Ogni RM:

- se può garantire il commit, forza nel log `ready`, conserva i lock e risponde `ready`;
- altrimenti risponde `non-ready` e abortisce localmente.

Il record `ready` è una promessa irrevocabile: da quel momento l'RM perde autonomia.

**Seconda fase - decision.** Se tutti hanno votato `ready`, il TM forza `global commit`; se almeno uno rifiuta o scade il timeout, forza `global abort`. Poi invia la decisione a tutti gli RM. Ciascun partecipante registra la decisione, esegue commit o undo, libera le risorse e invia `ack`. Ricevuti tutti gli `ack`, il TM scrive `complete`.

#### **1.4. Guasti e recovery**

**Caduta di un RM.** Al riavvio l'ultimo record decisivo stabilisce l'azione:

|Ultimo stato nel log RM|Recovery|
|---|---|
|nessun `ready` / azioni locali / `abort`|UNDO e abort autonomo|
|`commit`|REDO|
|`ready` senza decisione|stato incerto: chiedere la decisione al TM|

**Caduta del TM.** Al riavvio:

- dopo `prepare` ma prima della decisione può scegliere `global abort` o ripetere la prima fase;
- dopo `global commit` o `global abort` deve ritrasmettere la decisione finché riceve tutti gli `ack`;
- dopo `complete` non deve fare nulla.

**Perdita di messaggi.** Se manca `prepare` o `ready`, il timeout della prima fase conduce a `global abort`. Se manca la decisione o un `ack`, il TM ritrasmette la decisione, che è idempotente.

**Partizione di rete.** La transazione può avanzare soltanto nella partizione che contiene TM e tutti i partecipanti necessari. Gli RM già `ready` e isolati restano bloccati: la correttezza è preservata a scapito della disponibilità.

> 📌 Il punto vulnerabile del 2PC non è l'atomicità, che resta garantita, ma il possibile **blocco** dei partecipanti `ready` durante l'indisponibilità del coordinatore.

---

### **2. Domanda 2 - NULL polimorfo e logica a tre valori**

#### **2.1. Traccia originale**

> 1. Illustrare cosa vuol dire l'espressione “il valore null è polimorfo”, spiegando i significati che può assumere.
> 2. Descrivere il concetto di logica a tre valori, compilando le tabelle di verità allegate dei connettivi logici AND, OR e NOT, tenendo conto del valore nullo.

**Riferimenti:** [M3/UD1/L1 - Introduzione e domini](<../../M03_SQL/UD1/L1 - Introduzione e Domini.md>) · [M3/UD2/L1 - SELECT, FROM, WHERE](<../../M03_SQL/UD2/L1 - SELECT, FROM, WHERE.md>)

#### **2.2. Perché NULL è polimorfo**

`NULL` non è un valore ordinario e non appartiene al dominio dell'attributo. È un marcatore che può significare:

- **valore sconosciuto:** il valore esiste, ma non è noto;
- **valore inesistente:** per quell'occorrenza non esiste alcun valore;
- **valore non applicabile:** l'attributo non ha significato per quell'occorrenza.

È detto polimorfo perché lo stesso marcatore rappresenta assenze semanticamente differenti. Proprio per questo non si testa con `= NULL`, ma con `IS NULL` o `IS NOT NULL`.

#### **2.3. Logica a tre valori**

Un confronto che coinvolge `NULL` produce in generale `UNKNOWN` ($U$), non `TRUE` né `FALSE`. La clausola `WHERE` conserva soltanto le righe per cui il predicato vale `TRUE`.

**AND**

|$A$|$B$|$A\land B$|
|---|---|---|
|TRUE|TRUE|TRUE|
|TRUE|FALSE|FALSE|
|TRUE|UNKNOWN|UNKNOWN|
|FALSE|TRUE|FALSE|
|FALSE|FALSE|FALSE|
|FALSE|UNKNOWN|FALSE|
|UNKNOWN|TRUE|UNKNOWN|
|UNKNOWN|FALSE|FALSE|
|UNKNOWN|UNKNOWN|UNKNOWN|

**OR**

|$A$|$B$|$A\lor B$|
|---|---|---|
|TRUE|TRUE|TRUE|
|TRUE|FALSE|TRUE|
|TRUE|UNKNOWN|TRUE|
|FALSE|TRUE|TRUE|
|FALSE|FALSE|FALSE|
|FALSE|UNKNOWN|UNKNOWN|
|UNKNOWN|TRUE|TRUE|
|UNKNOWN|FALSE|UNKNOWN|
|UNKNOWN|UNKNOWN|UNKNOWN|

**NOT**

|$A$|$\neg A$|
|---|---|
|TRUE|FALSE|
|FALSE|TRUE|
|UNKNOWN|UNKNOWN|

> ⚠️ `A = 3 OR A <> 3` non seleziona le righe con `A IS NULL`: entrambe le comparazioni sono `UNKNOWN` e `UNKNOWN OR UNKNOWN` resta `UNKNOWN`.

---

### **3. Domanda 3 - Cardinalità minima e massima**

#### **3.1. Traccia originale**

Sono date:

- $r(\underline A,B)$, cardinalità 10;
- $s(\underline A,\underline C,D)$, cardinalità 20;
- $t(\underline C,E)$, cardinalità 5.

Gli attributi omonimi sono legati da vincoli di integrità referenziale; $D$ è intero. Determinare minimo e massimo di:

1. $r\bowtie s$;
2. $\pi_A(s)-\pi_A(r)$;
3. $\pi_A(r)-\pi_A(s)$.

**Riferimenti:** [M2/UD1/L3 - Vincoli di integrità](<../../M02_Modello_e_Algebra_Relazionali/UD1/L3 - Vincoli di integrità.md>) · [approfondimento sulle cardinalità](<../Approfondimenti_per_Esame/L1 - Cardinalita minima e massima di espressioni relazionali.md>)

#### **3.2. Vincoli derivati**

Le foreign key impongono:

$$
\pi_A(s)\subseteq\pi_A(r),\qquad \pi_C(s)\subseteq\pi_C(t).
$$

Poiché $(A,C)$ è chiave di $s$ e sono disponibili al massimo 5 valori distinti di $C$, uno stesso $A$ può comparire in al massimo 5 tuple. Per contenere 20 tuple servono almeno:

$$
\left\lceil\frac{20}{5}\right\rceil=4
$$

valori distinti di $A$. D'altra parte `s.A` può usare al massimo i 10 valori della chiave `r.A`:

$$
4\leq |\pi_A(s)|\leq 10.
$$

#### **3.3. Risultati**

|Espressione|Minimo|Massimo|Motivazione|
|---|---:|---:|---|
|$r\bowtie s$|20|20|ogni tupla di $s$ trova esattamente una tupla di $r$, perché `r.A` è chiave e `s.A` la riferisce|
|$\pi_A(s)-\pi_A(r)$|0|0|l'inclusione referenziale rende la differenza vuota|
|$\pi_A(r)-\pi_A(s)$|0|6|il minimo usa tutti i 10 valori di $A$; il massimo ne usa soltanto 4 in $s$|

Gli estremi sono raggiungibili: per il massimo 6 della terza espressione, si scelgono 4 valori di `A` e li si combina con tutti e 5 i valori di `C`, ottenendo le 20 chiavi composte distinte richieste.

---

### **4. Domanda 4 - Affermazioni sulle regole di associazione**

#### **4.1. Traccia leggibile**

> Sia data una collezione di $N_T$ transazioni, e tre articoli $A$, $B$ e $C$ che compaiono in queste. Compilare il foglio allegato indicando, per ciascuna affermazione (ciascuna da considerare indipendente dalle altre), se è sicuramente vera, sicuramente falsa, o se non è possibile determinarlo sulla base delle informazioni date.

#### **4.2. Limite e metodo**

Il foglio con le affermazioni non compare nelle due fotografie. Non è quindi possibile assegnare `Vero`, `Falso` o `Non determinabile` alle singole righe.

Il metodo corretto consiste nel tradurre ogni dato in relazioni fra cardinalità:

$$
supporto(A\to B)=\frac{|A\cap B|}{N_T},
\qquad
confidenza(A\to B)=\frac{|A\cap B|}{|A|}.
$$

Per ogni affermazione si costruisce poi:

- un modello che la rende vera;
- se possibile, un modello compatibile con gli stessi dati che la rende falsa.

Se esistono entrambi, la risposta è “non è possibile determinarlo”; se uno dei due è impossibile per inclusioni o limiti insiemistici, l'affermazione è forzatamente vera o falsa.

---

### **5. Esercizio 1 - Timestamp mono- e multiversione**

#### **5.1. Traccia leggibile**

La traccia chiede:

1. con `RTM` e `WTM` di $x$ e $y$ inizializzati a 0, determinare i valori assumibili da $T_1$ e $T_2$ per una sequenza monoversione;
2. con `RTM` e `WTM` di $z$ inizializzati a 0, determinare i valori assumibili da $T_3$ e $T_4$ per una sequenza multiversione.

Le sequenze di richieste e risposte sono però nelle tabelle allegate non fotografate.

#### **5.2. Procedura monoversione**

Per ogni `read(o,t)`:

- se $t<WTM(o)$, $T_t$ viene abortita;
- altrimenti $RTM(o):=\max(RTM(o),t)$.

Per ogni `write(o,t)`:

- se $t<WTM(o)$ oppure $t<RTM(o)$, $T_t$ viene abortita;
- altrimenti $WTM(o):=t$.

Ogni risposta `ok/no` della tabella produce quindi una disuguaglianza sul timestamp ignoto. L'intersezione di tutte le disuguaglianze relative alla stessa transazione restituisce l'insieme dei valori possibili.

#### **5.3. Procedura multiversione**

Una lettura con timestamp $t$ sceglie la versione con massimo $WTM\leq t$ e ne aggiorna `RTM`. Una scrittura sceglie la stessa versione di riferimento; è respinta se $t<RTM$ della versione, altrimenti crea una versione con $RTM=WTM=t$.

> ⚠️ Senza l'ordine e gli esiti delle richieste non si possono dedurre intervalli numerici per $T_1,T_2,T_3,T_4$: gli indicatori sono aggiornati sequenzialmente.

---

### **6. Esercizio 2 - SQL e algebra relazionale**

#### **6.1. Schemi**

```text
INVITATO(CF, Nome, Cognome, DataNascita, Città, NomeTavolo)
TAVOLO(Nome, ColoreTovaglia, Posizione, MaxPosti)
INTOLLERANZA(CFInvitato, Sostanza)
```

Le chiavi indicate sono `INVITATO.CF`, `TAVOLO.Nome` e la coppia `(CFInvitato,Sostanza)`.

> Scrivere in SQL le seguenti interrogazioni:
>
> 1. Determinare il nome dei tavoli a cui sono stati assegnati più invitati rispetto al numero massimo di persone che possono ospitare.
> 2. Determinare, per ciascun tavolo a cui non è stata assegnata alcuna persona intollerante al lattosio, il numero di posti occupati.
>
> Scrivere in algebra relazionale la seguente interrogazione: determinare il nome dei tavoli a cui siedono solo invitati della stessa città.

#### **6.2. SQL 1 - Tavoli sovraffollati**

```sql
SELECT t.Nome
FROM TAVOLO AS t
JOIN INVITATO AS i
  ON i.NomeTavolo = t.Nome
GROUP BY t.Nome, t.MaxPosti
HAVING COUNT(*) > t.MaxPosti;
```

Il `WHERE` non può sostituire `HAVING`, perché il confronto usa il conteggio dell'intero gruppo.

#### **6.3. SQL 2 - Posti occupati nei tavoli senza intolleranti al lattosio**

```sql
SELECT t.Nome, COUNT(i.CF) AS PostiOccupati
FROM TAVOLO AS t
LEFT JOIN INVITATO AS i
  ON i.NomeTavolo = t.Nome
WHERE NOT EXISTS (
  SELECT 1
  FROM INVITATO AS il
  JOIN INTOLLERANZA AS x
    ON x.CFInvitato = il.CF
  WHERE il.NomeTavolo = t.Nome
    AND x.Sostanza = 'lattosio'
)
GROUP BY t.Nome;
```

Il `LEFT JOIN` conserva anche un tavolo vuoto, per il quale `COUNT(i.CF)` vale 0. `COUNT(*)` restituirebbe erroneamente 1 per la riga esterna prodotta dal join.

#### **6.4. Algebra - Tavoli con invitati tutti della stessa città**

Si rinominano due copie di `INVITATO`:

$$
I_1:=\rho_{I_1}(INVITATO),\qquad I_2:=\rho_{I_2}(INVITATO).
$$

I tavoli “cattivi” sono quelli con almeno una coppia di invitati di città diversa:

$$
Bad:=\pi_{I_1.NomeTavolo}
\left(
\sigma_{I_1.NomeTavolo=I_2.NomeTavolo\ \land\ I_1.\mathrm{Citta}\neq I_2.\mathrm{Citta}}
(I_1\times I_2)
\right).
$$

La risposta è:

$$
\boxed{\pi_{NomeTavolo}(INVITATO)-Bad}.
$$

Questa formulazione restituisce i tavoli con almeno un invitato e senza coppie di città diverse. Se si volessero includere anche i tavoli vuoti, il primo operando andrebbe sostituito con $\pi_{Nome}(TAVOLO)$ dopo una ridenominazione compatibile.

---

### **7. Esercizio 3 - Progettazione E-R per OM**

#### **7.1. Traccia originale**

> Il produttore di ombrelloni OM vuole realizzare un'applicazione di basi di dati per la propria attività.
>
> **Descrizione del problema.** La OM produce e vende al pubblico ombrelloni di alta qualità. Ogni ombrellone prodotto e venduto dalla OM è caratterizzato da un identificativo univoco, da un nome commerciale, dall'altezza e, solo per alcuni modelli regolabili, dall'altezza massima. La OM produce e vende ombrelloni di due tipologie: portatili, che hanno forma rotonda e per i quali è necessario tenere traccia del diametro (espresso in cm), e fissi, che hanno forma rettangolare e per i quali è necessario tenere traccia della larghezza e della lunghezza (espresse in cm). Per gli ombrelloni portatili, la OM produce anche custodie, caratterizzate da un codice univoco e dal materiale in cui è realizzata. Si noti che ogni ombrellone portatile può avere una sola custodia, mentre ogni custodia può essere associata a diversi ombrelloni portatili.
>
> Gli ombrelloni della OM possono essere associati a diversi accessori (quali ad esempio ganci appendiabiti e tavolini), realizzati da selezionati produttori caratterizzati dalla partita IVA, dalla ragione sociale, e da uno o più numeri di telefono di contatto. Per ogni accessorio, è necessario tenere traccia del nome (univoco per ogni produttore) e della tipologia. Si noti che ogni produttore può produrre diversi accessori, che lo stesso accessorio può essere montato su diversi ombrelloni, e che per ogni accessorio montato su un ombrellone è necessario tenere traccia del numero di esemplari montati.
>
> I clienti della OM, dei quali occorre tenere traccia del codice fiscale, del nome, del cognome, e dell'indirizzo (composto da via, numero civico e città), acquistano gli ombrelloni perfezionando ordini di acquisto per i quali è necessario mantenere il codice che li identifica univocamente, la data, il venditore che ha seguito l'ordine, e la lista degli ombrelloni coinvolti nell'ordine. Si noti che ogni ordine è sempre associato ad un solo cliente.
>
> Progettare lo schema E-R che descrive le entità e le associazioni sopra descritte. Lo schema concettuale deve comprendere l'indicazione delle cardinalità di associazioni e attributi e l'indicazione degli identificatori di tutte le entità.

#### **7.2. Entità e identificatori**

|Entità|Identificatore|Attributi principali|
|---|---|---|
|OMBRELLONE|Id|NomeCommerciale, Altezza, AltezzaMassima opzionale|
|CUSTODIA|Codice|Materiale|
|PRODUTTORE|PartitaIVA|RagioneSociale, Telefono multivalore obbligatorio|
|ACCESSORIO|Nome + PRODUTTORE|Tipologia|
|CLIENTE|CodiceFiscale|Nome, Cognome, Indirizzo composto da Via, NumeroCivico, Città|
|ORDINE|Codice|Data, Venditore|

`ACCESSORIO` ha identificazione esterna: il nome è univoco soltanto rispetto al produttore.

#### **7.3. Gerarchia degli ombrelloni**

La specializzazione di `OMBRELLONE` è **totale ed esclusiva**:

- `PORTATILE`, con attributo `Diametro`;
- `FISSO`, con attributi `Larghezza` e `Lunghezza`.

Ogni ombrellone appartiene a una sola delle due categorie. `AltezzaMassima` ha cardinalità $(0,1)$ e può essere valorizzata soltanto per un modello regolabile; l'eventuale proprietà `Regolabile` va documentata come vincolo se non viene modellata con un'ulteriore specializzazione.

#### **7.4. Associazioni e cardinalità**

|Associazione|Partecipazione lato A|Partecipazione lato B|Attributi|
|---|---|---|---|
|ASSOCIA_PORTATILE_CUSTODIA|PORTATILE $(0,1)$|CUSTODIA $(0,N)$|—|
|PRODUCE|PRODUTTORE $(1,N)$|ACCESSORIO $(1,1)$|—|
|MONTA|OMBRELLONE $(0,N)$|ACCESSORIO $(0,N)$|NumeroMontati|
|EFFETTUA|CLIENTE $(0,N)$|ORDINE $(1,1)$|—|
|COMPRENDE|ORDINE $(1,N)$|OMBRELLONE $(0,1)$|—|

Le cardinalità $(1,N)$ sul lato produttore esprimono che ogni produttore selezionato realizza uno o più accessori. Ogni ordine appartiene a un solo cliente e contiene almeno un ombrellone; un ombrellone fisico venduto può comparire in al più un ordine.

#### **7.5. Vincoli non esprimibili graficamente**

1. `AltezzaMassima` è valorizzata se e solo se l'ombrellone è regolabile.
2. Il nome di un accessorio è univoco all'interno del relativo produttore.
3. `NumeroMontati` è un intero strettamente positivo.
4. Solo gli ombrelloni portatili possono essere associati a una custodia.

> ✅ Lo schema completo deve mostrare identificatori, attributi composti e multivalore, cardinalità minime e massime e proprietà totale/esclusiva della gerarchia.

---

### **8. Checklist finale dell'appello**

- Domande 1–3: risolte integralmente.
- Domanda 4: metodo completo; classificazioni mancanti perché il foglio delle affermazioni non è disponibile.
- Esercizio 1: metodo completo; intervalli numerici non ricavabili perché mancano le tabelle.
- Esercizi 2–3: risolti integralmente.
