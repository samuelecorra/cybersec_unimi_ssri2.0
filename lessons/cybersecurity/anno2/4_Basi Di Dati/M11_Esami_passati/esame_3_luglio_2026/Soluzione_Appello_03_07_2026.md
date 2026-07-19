# **Basi di Dati - Soluzione completa dell'appello del 3 luglio 2026**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonti originali: [pagina 1](esame3luglio26_1.jpg) · [pagina 2](esame3luglio26_2.jpg)

---

### **1. Esercizio 1 - Conteggio degli schedule equivalenti**

#### **1.1. Traccia originale**

Sia $T$ un insieme di $n$ transazioni, ciascuna composta da una singola operazione sullo stesso oggetto. Sia $S$ uno schedule di tali transazioni. Determinare il numero di schedule seriali view-equivalenti e conflict-equivalenti a $S$ nei casi:

1. tutte letture;
2. tutte scritture;
3. una lettura in posizione $k$ e tutte le altre scritture;
4. una scrittura in posizione $k$ e tutte le altre letture.

**Riferimenti:** [M6/UD3/L3 - View-serializzabilità](<../../M06_Gestione_Transazioni/UD3/L3 - View-serializzabilità.md>) · [M6/UD3/L4 - Conflict-serializzabilità](<../../M06_Gestione_Transazioni/UD3/L4 - Conflict-serializzabilità.md>) · [approfondimento sul conteggio](<../Approfondimenti_per_Esame/L2 - Conteggio degli schedule seriali equivalenti.md>)

#### **1.2. Risultati**

|Caso|View-equivalenti|Conflict-equivalenti|
|---|---:|---:|
|Tutte letture|$n!$|$n!$|
|Tutte scritture|$(n-1)!$|$1$|
|Una lettura in posizione $k$, resto scritture|$(n-2)!$|$1$|
|Una scrittura in posizione $k$, resto letture|$(k-1)!(n-k)!$|$(k-1)!(n-k)!$|

#### **1.3. Dimostrazione**

Con sole letture tutte le transazioni osservano il valore iniziale e non esistono conflitti: ogni permutazione è valida.

Con sole scritture la view-equivalenza impone soltanto di conservare lo scrittore finale; fissato quest'ultimo, le altre $n-1$ transazioni sono permutabili. Per la conflict-equivalenza, invece, ogni coppia di scritture confligge e tutto l'ordine di $S$ è fissato.

Con una sola lettura, la view-equivalenza deve preservare lo scrittore da cui essa legge — o la lettura iniziale, se $k=1$ — e lo scrittore finale. Restano $n-2$ scrittori liberamente ordinabili, da cui $(n-2)!$. Per i conflitti, tutte le scritture fissano reciprocamente il proprio ordine e il lettore deve conservare la posizione relativa rispetto a ciascuna: rimane soltanto $S$.

Con una sola scrittura, le $k-1$ letture precedenti devono continuare a leggere il valore iniziale e le $n-k$ successive devono continuare a leggere il valore scritto. Le letture sono permutabili all'interno dei due blocchi, per un totale $(k-1)!(n-k)!$. Gli stessi blocchi sono imposti dai conflitti lettura-scrittura, quindi il conteggio coincide.

---

### **2. Esercizio 2 - SQL e algebra relazionale**

#### **2.1. Schemi originali**

```text
CLIENTE(CF, Nome, Cognome, DataNascita)
OMBRELLONE(Id, Fila, Tipo, Prezzo)
PRENOTAZIONE(Id, CFCliente, Ombrellone, Data)
```

> Scrivere in SQL le seguenti interrogazioni:
>
> 1. Determinare il codice fiscale dei clienti che hanno prenotato sempre lo stesso ombrellone.
> 2. Determinare, per ciascuna data, il numero di diversi clienti che hanno prenotato ombrelloni, il numero di ombrelloni prenotati e il prezzo medio.
>
> Scrivere in algebra relazionale la seguente interrogazione: determinare il codice fiscale dei clienti che hanno prenotato solo ombrelloni in prima fila.

#### **2.2. SQL 1 - Clienti che hanno prenotato sempre lo stesso ombrellone**

```sql
SELECT p.CFCliente
FROM PRENOTAZIONE AS p
GROUP BY p.CFCliente
HAVING COUNT(DISTINCT p.Ombrellone) = 1;
```

Il raggruppamento parte da `PRENOTAZIONE`, quindi ogni cliente restituito ha almeno una prenotazione; “sempre” significa che l'insieme degli identificativi prenotati ha cardinalità 1.

#### **2.3. SQL 2 - Statistiche per data**

```sql
SELECT
  p.Data,
  COUNT(DISTINCT p.CFCliente) AS NumeroClienti,
  COUNT(DISTINCT p.Ombrellone) AS NumeroOmbrelloni,
  AVG(o.Prezzo) AS PrezzoMedio
FROM PRENOTAZIONE AS p
JOIN OMBRELLONE AS o
  ON o.Id = p.Ombrellone
GROUP BY p.Data;
```

`COUNT(DISTINCT ...)` rende esplicito che si contano clienti e ombrelloni diversi. La media è calcolata sui prezzi degli ombrelloni coinvolti nelle prenotazioni di quella data.

#### **2.4. Algebra - Clienti che hanno prenotato solo ombrelloni in prima fila**

Si sottraggono dai clienti con almeno una prenotazione quelli che hanno almeno una prenotazione fuori dalla prima fila:

$$
Bad:=\pi_{CFCliente}
\left(
PRENOTAZIONE\bowtie_{Ombrellone=Id}
\sigma_{Fila\neq\text{'prima'}}(OMBRELLONE)
\right),
$$

$$
\boxed{
\pi_{CFCliente}(PRENOTAZIONE)-Bad
}.
$$

Se il dominio codifica le file numericamente, il predicato diventa $Fila\neq 1$.

---

### **3. Esercizio 3 - Progettazione E-R per Mobili Pregiati**

#### **3.1. Traccia originale**

> La Mobili Pregiati (MP) S.p.A., che si occupa della vendita al grande pubblico di arredamenti da interno, vuole realizzare un'applicazione di basi di dati per la propria attività.
>
> **Descrizione del problema.** La MP si occupa principalmente della vendita di: tavoli, madie, letti e armadi. Ciascuno dei mobili venduti dalla MP è prodotto da un mobilificio (si noti che lo stesso mobilificio produce più mobili). Di ciascun mobilificio di cui la MP si serve, si conoscono la P.IVA (che lo identifica univocamente), il nome, l'indirizzo (composto da via, cap e città), il nome del rappresentante legale e il numero di telefono (non più di due) o la mail, ciascuno opzionalmente ma con specifica di almeno uno dei due.
>
> Di ciascun mobile venduto dalla MP si conoscono il prezzo (in Euro) e le dimensioni (espresse in termini di altezza, lunghezza e larghezza) e il codice identificativo univoco relativamente alla P.IVA di chi lo produce. Per gli armadi si tiene traccia, inoltre, del tipo di apertura (scorrevole o a battente), se è o meno attrezzato; per le madie del numero di ante che hanno; per i tavoli del materiale del piano; mentre per i letti si conosce se possono essere realizzati con contenitore o meno. Di ciascun letto, si tiene anche traccia del tipo di rete che monta. Ciascun modello di letto può infatti montare diversi tipi di rete, così come lo stesso tipo di rete può essere adatto a più letti. Delle reti commercializzate dalla MP si vuole tenere traccia del tipo (rete o doghe). Si noti che le reti a doghe possono essere solo abbinate a letti la cui larghezza sia non maggiore di 160 cm.
>
> I clienti della MP, per acquistare uno o più mobili, devono sottoscrivere un ordine che indichi quali mobili sono stati acquistati. Ciascun ordine riporta, oltre all'elenco dei mobili comprati, la data di sottoscrizione, il numero progressivo, il totale concordato da pagare, l'ammontare della caparra già versata e il tipo di pagamento scelto (contanti, carta di credito e assegno). La caparra (obbligatoria) è sempre pari al 30% del totale da pagare. Si noti che non è possibile eseguire un pagamento in contanti quando il totale da pagare è superiore ai 500 Euro.
>
> Per ciascun mobile ordinato, si tiene anche traccia del numero di esemplari richiesti dal cliente. Si noti che lo stesso cliente può sottoscrivere più ordini, ma ogni ordine riguarda un solo cliente. Per ogni cliente della MP, si vuole tenere traccia del codice fiscale, del nome, del cognome, dell'indirizzo e del numero di telefono.
>
> Progettare lo schema E-R che descrive le entità e le associazioni sopra descritte. Lo schema concettuale deve comprendere l'indicazione delle cardinalità di associazioni e attributi e l'indicazione degli identificatori di tutte le entità.

#### **3.2. Entità e identificatori**

|Entità|Identificatore|Attributi|
|---|---|---|
|MOBILIFICIO|PartitaIVA|Nome, Indirizzo composto da Via/CAP/Città, RappresentanteLegale, Telefono $(0,2)$, Mail $(0,1)$|
|MOBILE|Codice relativo al MOBILIFICIO|Prezzo, Altezza, Lunghezza, Larghezza|
|TIPO_RETE|Tipo|—|
|CLIENTE|CodiceFiscale|Nome, Cognome, Indirizzo, Telefono|
|ORDINE|NumeroProgressivo|DataSottoscrizione, TotaleConcordato, Caparra, TipoPagamento|

`MOBILE` è identificato esternamente dalla coppia `(Mobilificio, Codice)`, perché il codice è univoco soltanto rispetto al produttore. Per `ORDINE` la traccia parla invece di un numero progressivo senza limitarne l'unicità a un cliente: l'interpretazione conservativa è quindi un identificatore interno globale.

#### **3.3. Gerarchia dei mobili**

La specializzazione di `MOBILE` è esclusiva e, alla lettera, **parziale**: la traccia afferma che l'azienda vende “principalmente” le quattro categorie elencate, senza escludere altri mobili.

- `ARMADIO`: TipoApertura, Attrezzato;
- `TAVOLO`: MaterialePiano;
- `LETTO`: Contenitore;
- `MADIA`: NumeroAnte.

Tutti i sottotipi ereditano prezzo, dimensioni e identificatore dal mobile. Se in sede d'esame la docente chiarisse che il catalogo è limitato a queste quattro categorie, la stessa gerarchia diventerebbe totale; il termine “principalmente” rende però più rigorosa la scelta parziale in assenza di ulteriori indicazioni.

#### **3.4. Associazioni**

|Associazione|Cardinalità|Attributi e significato|
|---|---|---|
|PRODUCE fra MOBILIFICIO e MOBILE|MOBILIFICIO $(1,N)$; MOBILE $(1,1)$|identifica esternamente MOBILE|
|COMPATIBILE fra LETTO e TIPO_RETE|LETTO $(1,N)$; TIPO_RETE $(0,N)$|tipi di rete montabili sul modello|
|EFFETTUA fra CLIENTE e ORDINE|CLIENTE $(0,N)$; ORDINE $(1,1)$|ogni ordine riguarda un solo cliente|
|RIGA_ORDINE fra ORDINE e MOBILE|ORDINE $(1,N)$; MOBILE $(0,N)$|NumeroEsemplari, intero positivo|

#### **3.5. Regole di vincolo richieste dalla Domanda 3.2**

1. Ogni mobile è prodotto da uno e un solo mobilificio.
2. Il codice di un mobile è univoco nell'ambito del mobilificio produttore.
3. Ogni mobilificio dispone di almeno un recapito fra telefono e mail:

   $$
   numeroTelefoni+presenzaMail\geq 1,
   $$

   con al massimo due numeri di telefono.
4. La gerarchia `MOBILE` è parziale ed esclusiva; diventa totale soltanto assumendo che le quattro categorie esauriscano il catalogo.
5. Un tipo di rete a doghe può essere associato soltanto a un letto con larghezza non superiore a 160 cm.
6. Ogni ordine appartiene a un solo cliente e il numero progressivo identifica univocamente l'ordine.
7. Ogni ordine contiene almeno un mobile e `NumeroEsemplari>0` per ogni riga.
8. `TipoPagamento` appartiene a `{contanti, carta di credito, assegno}`.
9. Se `TotaleConcordato > 500`, allora `TipoPagamento <> 'contanti'`.

#### **3.6. Regole di derivazione richieste dalla Domanda 3.2**

La caparra è obbligatoria e vale sempre il 30% del totale:

$$
\boxed{Caparra=0{,}30\cdot TotaleConcordato}.
$$

Se si decide di non memorizzare il totale ma di derivarlo dai prezzi correnti, occorre introdurre una regola ulteriore:

$$
TotaleConcordato=\sum_{m\in ordine} NumeroEsemplari(m)\cdot PrezzoConcordato(m).
$$

È però preferibile memorizzare il prezzo concordato nella riga d'ordine, perché il prezzo di listino del modello può cambiare dopo la sottoscrizione. Quest'ultima è un'integrazione progettuale, non un requisito esplicito della traccia.

> 📌 La distinzione richiesta dalla docente è netta: un **vincolo** deve essere sempre vero; una **derivazione** specifica come calcolare un dato a partire da altri.

---

### **4. Domanda 1 - 2PC, finestra d'incertezza e read-only**

#### **4.1. Traccia originale**

> Con riferimento alle basi di dati distribuite illustrare il funzionamento del protocollo Two Phase Commit. Illustrare dapprima il funzionamento in assenza di guasti e poi descrivere cosa succede in caso di guasti e le azioni di ripristino necessarie. Nella trattazione illustrare anche il concetto di finestra di incertezza e di partecipazione read-only.

**Riferimenti:** [M7/UD3/L3 parte 1](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>) · [M7/UD3/L3 parte 2](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>)

#### **4.2. Esecuzione normale**

Il Transaction Manager registra `prepare`, invia la richiesta ai Resource Manager e raccoglie i voti. Un RM affidabile forza `ready` nel log, trattiene i lock e vota sì; un RM non pronto vota no. Il TM forza `global commit` soltanto se tutti hanno votato sì, altrimenti `global abort`, quindi invia la decisione. Gli RM registrano ed eseguono la decisione, liberano le risorse e inviano `ack`; il TM chiude con `complete`.

#### **4.3. Finestra d'incertezza**

Per un RM è l'intervallo fra la scrittura di `ready` e la ricezione della decisione globale. In tale intervallo l'RM:

- non può più abortire autonomamente;
- deve mantenere i lock e lo stato necessario al commit;
- può restare bloccato se TM o rete diventano indisponibili.

#### **4.4. Guasti**

- RM riavviato con ultimo record `ready`: chiede la decisione al TM; con `commit` esegue REDO, prima di `ready` può fare UNDO.
- TM riavviato dopo `prepare`: può abortire globalmente o ripetere la prima fase; dopo una decisione globale, la ritrasmette; dopo `complete`, nessuna azione.
- Messaggi `prepare/ready` persi: timeout e abort globale.
- Decisione o `ack` persi: ritrasmissione della decisione.
- Partizione: gli RM `ready` isolati restano in attesa, preservando atomicità ma perdendo disponibilità.

#### **4.5. Ottimizzazione read-only**

Un RM che ha eseguito soltanto letture può rispondere `read-only` nella prima fase. Non ha modifiche da rendere persistenti né da annullare, quindi libera subito le risorse ed esce dal protocollo; non riceve la seconda fase e non entra nella finestra d'incertezza. Il TM decide usando soltanto i partecipanti che possono avere effetti di scrittura.

---

### **5. Domanda 2 - Deadlock distribuito**

#### **5.1. Traccia originale**

> Discutere il problema del deadlock in un sistema distribuito illustrando un possibile esempio di algoritmo per la sua rilevazione unitamente a un esempio di esecuzione.

**Riferimento:** [M7/UD3/L2 - Controllo di concorrenza](<../../M07_Architetture_Distribuite/UD3/L2 - Controllo di concorrenza.md>)

#### **5.2. Problema**

Ogni DBMS vede soltanto il proprio grafo di attesa. I grafi locali possono essere aciclici mentre la loro unione contiene un ciclo. Per esempio:

$$
T_1@N_1\to T_2@N_1,
\qquad
T_2@N_2\to T_1@N_2.
$$

Nessun nodo, isolatamente, possiede abbastanza informazioni per concludere che $T_1\leftrightarrow T_2$.

#### **5.3. Algoritmo distribuito per propagazione delle attese**

Ogni nodo mantiene le dipendenze locali e rappresenta una catena che attraversa il confine come:

$$
E_{in}\to T_i\to T_j\to E_{out}.
$$

Periodicamente:

1. integra le nuove sequenze ricevute con gli archi locali;
2. compone le catene aventi estremi compatibili;
3. inoltra solo le sequenze estese ai nodi verso cui prosegue l'attesa;
4. dichiara deadlock quando una catena ritorna al proprio evento iniziale.

Una regola di orientamento, per esempio comunicare una particolare sequenza soltanto dal nodo con indice maggiore a quello con indice minore, evita duplicazioni senza perdere cicli.

#### **5.4. Esempio di esecuzione**

Supponiamo:

- su $N_1$, la sotto-transazione $T_{11}$ detiene $x$ e $T_{21}$ lo attende;
- su $N_2$, $T_{22}$ detiene $y$ e $T_{12}$ lo attende;
- $T_{11}$ attende il completamento RPC di $T_{12}$;
- $T_{22}$ attende il completamento RPC di $T_{21}$.

Le catene locali vengono propagate e composte fino a ottenere:

$$
T_{11}\to T_{12}\to T_{22}\to T_{21}\to T_{11}.
$$

Il ciclo prova il deadlock globale. Il sistema sceglie allora una vittima, di norma la transazione meno costosa da riavviare, la abortisce, rilascia i lock e spezza il ciclo.

---

### **6. Domanda 3 - Regole di vincolo e derivazione**

#### **6.1. Traccia originale**

> 3.1 Nell'ambito della progettazione concettuale, illustrare i concetti di regole di vincolo e di derivazione.
>
> 3.2 Riportare le regole di vincolo e di derivazione relative alla progettazione dell'Esercizio 3.

**Riferimento:** [M4/UD2/L4 - Documentazione di schemi](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L4 - Modello E-R - Documentazione di schemi.md>)

#### **6.2. Soluzione**

Una regola di vincolo è un'asserzione che limita gli stati leciti della base di dati e deve risultare sempre vera. Una regola di derivazione specifica invece come calcolare un concetto derivato a partire da altri concetti presenti nello schema. Entrambe fanno parte delle business rule e accompagnano il diagramma E-R quando la notazione grafica non esprime tutta la semantica.

Le regole puntuali dell'Esercizio 3 sono elencate nelle sezioni 3.5 e 3.6 di questa soluzione. La derivazione esplicita richiesta dalla traccia è $Caparra=30\%\cdot TotaleConcordato$; i vincoli includono almeno un recapito del mobilificio, massimo due telefoni, larghezza dei letti con rete a doghe, identificazione relativa dei mobili, positività delle quantità e divieto di contanti sopra 500 euro.

---

### **7. Domanda 4 - Cardinalità di espressioni relazionali e SQL**

#### **7.1. Dati originali**

- $R(\underline A,B)$, $|R|=10$, contiene $(a,b)$;
- $S(\underline A,\underline C,D)$, $|S|=20$, contiene $(b,c,d)$;
- $T(\underline E,F)$, $|T|=20$, contiene $(a,c)$;
- `S.A` è foreign key verso `R.A`.

#### **7.2. Risultati**

|N.|Espressione|Minimo|Massimo|Motivazione|
|---:|---|---:|---:|---|
|1|$R\bowtie S$|20|20|ogni tupla di $S$ trova esattamente una tupla di $R$|
|2|$\pi_A(R)-\pi_A(S)$|0|9|$S.A$ usa da 1 a 10 valori della chiave di $R$|
|3|$\pi_A(S)-\pi_A(R)$|0|0|la foreign key impone l'inclusione|
|4|`SELECT S.A FROM S UNION SELECT T.E FROM T`|20|29|`T.E` è chiave: 20 valori distinti; l'estremo superiore 30 è impedito dalle tuple sicuramente presenti e dal vincolo|
|5|`SELECT S.A,S.C FROM S UNION ALL SELECT T.E,T.F FROM T`|40|40|`UNION ALL` conserva tutte le 20+20 righe|

#### **7.3. Giustificazione del massimo 29 nella UNION**

Per ottenere 30 servirebbero 10 valori distinti in $\pi_A(S)$ e nessuna sovrapposizione con i 20 valori di $\pi_E(T)$. Ma se $S$ usa 10 valori distinti di `A`, per il vincolo referenziale usa tutti i 10 valori di `R.A`, compreso `a`. Poiché `T` contiene `(a,c)`, `a` appartiene anche a $\pi_E(T)$: l'intersezione non è vuota e il massimo è al più 29.

Il valore 29 è raggiungibile in due modi: usando 10 valori in $S.A$ con una sola sovrapposizione (`a`), oppure 9 valori che escludono `a` e sono tutti disgiunti dai 20 valori di `T.E`.

---

### **8. Domanda 5 - Limiti di supporto**

#### **8.1. Traccia originale**

Con $N_T=100$:

$$
supporto(X\to Y)=0{,}3,
\quad confidenza(X\to Y)=1,
\quad confidenza(X\to Z)=0{,}5.
$$

Determinare minimo e massimo di $supporto(X\to Z)$ e $supporto(Y\to Z)$.

**Riferimenti:** [M10/UD1/L5 - Data Mining](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>) · [approfondimento](<../Approfondimenti_per_Esame/L3 - Limiti per supporto e confidenza nelle regole di associazione.md>)

#### **8.2. Soluzione**

La confidenza unitaria implica $X\subseteq Y$. Dal supporto segue $|X|=|X\cap Y|=30$. La confidenza verso $Z$ dà $|X\cap Z|=15$.

Quindi:

$$
\boxed{\min supporto(X\to Z)=\max supporto(X\to Z)=0{,}15}.
$$

I 15 elementi di $X\cap Z$ sono obbligatoriamente in $Y\cap Z$, mentre i 15 elementi di $X\setminus Z$ sono obbligatoriamente in $Y\setminus Z$. Perciò:

$$
\boxed{0{,}15\leq supporto(Y\to Z)\leq 0{,}85}.
$$

Il minimo si ottiene senza altre intersezioni fra $Y$ e $Z$; il massimo includendo in $Y\cap Z$ le 15 transazioni obbligate e tutte le 70 esterne a $X$.

---

### **9. Checklist finale dell'appello**

- Tutti i quesiti e tutti i dati delle due pagine sono stati trascritti.
- Le tre parti teoriche sono svolte con definizioni, meccanismi ed esempi.
- Tutti gli esercizi hanno risultato motivato e verificato.
- Le regole dell'E–R sono separate in vincoli e derivazioni, come richiesto esplicitamente dalla Domanda 3.
