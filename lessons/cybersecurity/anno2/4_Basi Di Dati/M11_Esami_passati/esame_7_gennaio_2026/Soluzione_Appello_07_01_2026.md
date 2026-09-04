# **Basi di Dati - Soluzione completa dell'appello del 7 gennaio 2026**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2026_01_07.pdf)

---

### **1. Domanda 1 - Proprietà ACIDe e distribuzione**

#### **1.1. Traccia originale**

> Elencare e descrivere in modo completo le proprietà ACIDe delle transazioni. Indicare quali di queste proprietà cambiano a seguito della distribuzione della base di dati, fornendo la motivazione e un esempio adeguato.

**Riferimenti:** [M6/UD1/L1 - Transazioni](<../../M06_Gestione_Transazioni/UD1/L1 - Transazioni.md>) · [M7/UD3/L1 - Ottimizzazione, concorrenza e atomicità](<../../M07_Architetture_Distribuite/UD3/L1 - Ottimizzazione, concorrenza e atomicità.md>)

#### **1.2. Soluzione**

Una transazione è un'unità logica di lavoro che termina con `commit` oppure `rollback`. Le proprietà ACIDe sono:

- **Atomicità:** o tutti gli effetti della transazione diventano permanenti, oppure nessuno. Un guasto prima del commit richiede l'`UNDO`; dopo il commit può essere necessario il `REDO`.
- **Consistenza:** se lo stato iniziale soddisfa i vincoli d'integrità e la transazione è corretta, anche lo stato finale li soddisfa. I controlli possono essere immediati o differiti al termine.
- **Isolamento:** l'esecuzione concorrente deve avere un effetto equivalente a un'esecuzione seriale; gli stati intermedi di una transazione non devono interferire con le altre.
- **Durabilità o persistenza:** dopo il commit gli effetti non devono andare persi, neppure in caso di crash; log e recovery permettono di ripristinarli.

Con la distribuzione cambiano i meccanismi necessari a garantire **atomicità** e **isolamento**.

L'atomicità diventa globale. Se un trasferimento addebita un conto sul nodo $N_1$ e accredita un conto sul nodo $N_2$, non è ammissibile che un nodo esegua il commit e l'altro l'abort. Serve quindi un protocollo di commit distribuito, come il **Two-Phase Commit**, che porti tutti i partecipanti alla stessa decisione anche in presenza di guasti o messaggi persi.

Anche l'isolamento deve essere globale. Due schedule possono essere serializzabili localmente ma imporre ordini opposti: $T_1<T_2$ su $N_1$ e $T_2<T_1$ su $N_2$. Il grafo globale presenta allora un ciclo; occorre coordinare 2PL o timestamp fra i nodi.

**Consistenza e persistenza non cambiano concettualmente**: i vincoli continuano a definire gli stati ammessi e ciascun nodo continua a garantire la durabilità locale tramite log, checkpoint e recovery. I vincoli globali possono però essere più difficili da verificare tecnologicamente.

> 📌 La distribuzione non ridefinisce ACIDe: rende globali le garanzie di atomicità e isolamento.

---

### **2. Domanda 2 - Prevenzione dei deadlock con timestamp**

#### **2.1. Traccia originale**

> Nella prevenzione dei deadlock basata sui timestamp, indicare quale transazione viene uccisa nei casi preemptive e non preemptive, quale controllo decide fra abort e attesa e quale timestamp riceve la transazione riavviata.

**Riferimento:** [M6/UD3/L8 - Deadlock](<../../M06_Gestione_Transazioni/UD3/L8 - Deadlock.md>)

#### **2.2. Soluzione**

Sia $T_i$ la transazione che richiede un lock posseduto da $T_j$; un timestamp minore indica una transazione più anziana.

**Caso non preemptive - wait-die:** il possessore non viene interrotto.

- se $TS(T_i)<TS(T_j)$, la richiedente $T_i$ è più anziana e **attende**;
- se $TS(T_i)>TS(T_j)$, la richiedente $T_i$ è più giovane e viene **abortita**.

**Caso preemptive - wound-wait:** la richiedente anziana può interrompere il possessore.

- se $TS(T_i)<TS(T_j)$, $T_i$ è più anziana e **aborta $T_j$**;
- se $TS(T_i)>TS(T_j)$, $T_i$ è più giovane e **attende**.

In entrambi i protocolli le attese sono orientate sempre nella stessa direzione rispetto all'età, quindi non possono formare un ciclo.

La transazione abortita viene riavviata con **lo stesso timestamp originario**. Assegnarle ogni volta un timestamp nuovo la renderebbe sempre più giovane e potrebbe causare abort indefiniti; conservare il timestamp le fa acquisire priorità relativa e previene la starvation.

---

### **3. Domanda 3 - Cardinalità di espressioni relazionali**

#### **3.1. Traccia originale**

Sono date le relazioni non vuote:

$$
r(\underline{A},B,C),\qquad
s(\underline{A},\underline{D},E),\qquad
t(\underline{C},E),
$$

con attributi omonimi legati da integrità referenziale. Determinare cardinalità minima e massima di:

$$
(\pi_C t)\cup(\pi_C r)
$$

e

$$
s\bowtie r.
$$

**Riferimenti:** [M2/UD1/L3 - Vincoli di integrità](<../../M02_Modello_e_Algebra_Relazionali/UD1/L3 - Vincoli di integrità.md>) · [M2/UD2/L4 - Join](<../../M02_Modello_e_Algebra_Relazionali/UD2/L4 - Prodotto cartesiano e Join.md>) · [approfondimento sulle cardinalità](<../Approfondimenti_per_Esame/L1 - Cardinalita minima e massima di espressioni relazionali.md>)

#### **3.2. Vincoli e risultati**

Dai riferimenti sugli attributi omonimi seguono in particolare:

$$
\pi_A(s)\subseteq\pi_A(r),\qquad
\pi_C(r)\subseteq\pi_C(t).
$$

|Espressione|Cardinalità minima|Cardinalità massima|Motivazione|
|---|---:|---:|---|
|$(\pi_C t)\cup(\pi_C r)$|$\lvert t\rvert$|$\lvert t\rvert$|La seconda proiezione è contenuta nella prima; inoltre `C` è chiave di $t$, quindi $\lvert\pi_C(t)\rvert=\lvert t\rvert$.|
|$s\bowtie r$|$\lvert s\rvert$|$\lvert s\rvert$|Il join naturale avviene su `A`: ogni tupla di $s$ riferisce una e una sola tupla di $r$, poiché `r.A` è chiave.|

Entrambe le operazioni sono ben definite e la cardinalità è quindi esatta, non soltanto limitata da un intervallo.

---

### **4. Domanda 4 - Supporto e confidenza**

#### **4.1. Traccia originale**

Con $N_T=100$ transazioni sono dati:

$$
supporto(X\to Y)=0{,}50,
$$

$$
supporto(X\to Z)=0{,}40,
$$

$$
confidenza(X\to Y)=1.
$$

Determinare minimo e massimo di $confidenza(X\to Z)$ e $supporto(Y\to Z)$.

**Riferimenti:** [M10/UD1/L5 - Data Mining](<../../M10_Basi_di_Dati_per_supporto_decisioni/UD1/L5 - Data Mining.md>) · [approfondimento sui limiti](<../Approfondimenti_per_Esame/L3 - Limiti per supporto e confidenza nelle regole di associazione.md>)

#### **4.2. Soluzione**

Il primo supporto dà $|X\cap Y|=50$. Poiché la confidenza è unitaria,

$$
\frac{|X\cap Y|}{|X|}=1
\quad\Longrightarrow\quad
X\subseteq Y,\qquad |X|=50.
$$

Dal secondo supporto segue $|X\cap Z|=40$. Pertanto la confidenza verso $Z$ è completamente determinata:

$$
\boxed{
\min confidenza(X\to Z)=\max confidenza(X\to Z)
=\frac{40}{50}=0{,}80
}.
$$

Poiché $X\subseteq Y$, le 40 transazioni di $X\cap Z$ appartengono necessariamente anche a $Y\cap Z$: il supporto minimo è quindi $0{,}40$. Inoltre le 10 transazioni di $X\setminus Z$ appartengono a $Y$ ma non a $Z$; al massimo $100-10=90$ transazioni possono appartenere a $Y\cap Z$. Ne segue:

$$
\boxed{0{,}40\leq supporto(Y\to Z)\leq 0{,}90}.
$$

Gli estremi sono raggiungibili: per il minimo si può porre $Y=X$; per il massimo si pone $Y$ uguale all'intera collezione e $Z$ uguale alla collezione privata delle 10 transazioni di $X\setminus Z$.

---

### **5. Esercizio 1 - Controllo di concorrenza con timestamp**

#### **5.1. Regole utilizzate**

**Riferimento:** [M6/UD3/L6 - Timestamp](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>)

Nel metodo monoversione:

- `read(o,t)` è accettata se $t\geq WTM(o)$ e aggiorna $RTM(o):=\max(RTM(o),t)$;
- `write(o,t)` è accettata se $t\geq WTM(o)$ e $t\geq RTM(o)$, aggiornando $WTM(o):=t$.

Nel metodo multiversione una lettura sceglie la versione con massimo $WTM\leq t$ ed è sempre accettata. Una scrittura seleziona la stessa versione candidata ed è accettata soltanto se $t\geq RTM$ di quella versione; in tal caso crea una nuova versione con $RTM=WTM=t$.

#### **5.2. Monoversione - intervallo di $T_1$**

Con $RTM(x)=WTM(x)=0$:

|Operazione|Esito|Stato o vincolo prodotto|
|---|---|---|
|`read(x,7)`|OK|$RTM(x)=7$, $WTM(x)=0$|
|`write(x,8)`|OK|$RTM(x)=7$, $WTM(x)=8$|
|`read(x,T1)`|OK|$T_1\geq8$ e $RTM(x)=T_1$|
|`write(x,12)`|NO|poiché $12\not<8$, deve valere $12<RTM(x)=T_1$|
|`read(x,10)`|OK|$10\geq8$; $RTM(x)$ resta $T_1$|
|`write(x,15)`|OK|richiede $15\geq RTM(x)=T_1$|

Intersecando i vincoli:

$$
\boxed{12<T_1\leq15}.
$$

#### **5.3. Monoversione - intervallo di $T_2$**

La sequenza su $y$ porta ai seguenti valori:

$$
write(y,4)\Rightarrow WTM(y)=4,
$$

$$
read(y,5)\Rightarrow RTM(y)=5,
$$

$$
write(y,10)\Rightarrow WTM(y)=10,
$$

$$
read(y,12)\Rightarrow RTM(y)=12.
$$

L'ultima `read(y,T2)` viene rifiutata esattamente quando $T_2<WTM(y)$. Quindi:

$$
\boxed{T_2<10}
$$

e, se si esplicita che i timestamp delle transazioni sono positivi, $0<T_2<10$.

#### **5.4. Multiversione - intervalli di $T_3$**

Indichiamo con $z_0$ la versione iniziale, con $RTM(z_0)=WTM(z_0)=0$.

- `read(z,2)` usa $z_0$ e porta $RTM(z_0)$ a 2;
- `write(z,5)` crea $z_5$ con $RTM(z_5)=WTM(z_5)=5$;
- `read(z,10)` usa $z_5$ e porta $RTM(z_5)$ a 10.

Per `write(z,T3)`:

- se $T_3<5$, la versione candidata è $z_0$ e la scrittura è accettata se $T_3\geq2$;
- se $T_3\geq5$, la candidata è $z_5$ e la scrittura è accettata se $T_3\geq10$.

Poiché la risposta è `OK`:

$$
\boxed{T_3\in[2,5)\cup[10,+\infty)}.
$$

#### **5.5. Multiversione - intervallo di $T_4$**

Dalla sequenza su $t$ si ottengono:

- la versione iniziale $t_0$, il cui $RTM$ diventa 5 dopo `read(t,5)`;
- $t_8$, creata da `write(t,8)`, con $RTM(t_8)=8$;
- $t_{15}$, creata da `write(t,15)`.

La `read(t,T4)` è sempre accettata, ma aggiorna $t_8$ soltanto se $8\leq T_4<15$. La successiva `write(t,12)` seleziona proprio $t_8$ ed è rifiutata se e solo se:

$$
12<RTM(t_8)=T_4.
$$

Occorre quindi che la lettura abbia scelto $t_8$ e che $T_4>12$:

$$
\boxed{12<T_4<15}.
$$

> ✅ Risultati dell'esercizio: $12<T_1\leq15$, $T_2<10$, $T_3\in[2,5)\cup[10,+\infty)$ e $12<T_4<15$.

---

### **6. Esercizio 2 - SQL e algebra relazionale**

#### **6.1. Schemi originali**

```text
GELATO(Id, Gusto, Calorie)
INGREDIENTE(Id, Nome, Tipo, Biologico, Prezzo)
RICETTA(IdGelato, IdIngrediente, Quantità)
```

Le chiavi sono `GELATO.Id`, `INGREDIENTE.Id` e la coppia `(RICETTA.IdGelato, RICETTA.IdIngrediente)`.

**Riferimenti:** [M3/UD3/L1 - Aggregazioni e raggruppamenti](<../../M03_SQL/UD3/L1 - Ordinamenti, aggregazioni e raggruppamenti.md>) · [M3/UD3/L2 - Interrogazioni nidificate](<../../M03_SQL/UD3/L2 - Interrogazioni insiemistiche e nidificate.md>) · [M2/UD2/L6 - Minimo e massimo](<../../M02_Modello_e_Algebra_Relazionali/UD2/L6 - Idiomi di interrogazione – esiste - per ogni, minimo - massimo.md>)

#### **6.2. SQL 1 - Quantità di creme superiore agli altri ingredienti**

```sql
SELECT g.Id, g.Gusto
FROM GELATO AS g
JOIN RICETTA AS r
  ON r.IdGelato = g.Id
JOIN INGREDIENTE AS i
  ON i.Id = r.IdIngrediente
GROUP BY g.Id, g.Gusto
HAVING SUM(CASE
             WHEN i.Tipo = 'creme' THEN r.Quantità
             ELSE 0
           END)
     > SUM(CASE
             WHEN i.Tipo = 'creme' THEN 0
             ELSE r.Quantità
           END);
```

Il confronto avviene sulle **quantità totali**, non sul numero di ingredienti. Un gelato composto soltanto da creme è correttamente selezionato perché la quantità degli altri tipi vale zero.

#### **6.3. SQL 2 - Gelati senza latticini**

```sql
SELECT DISTINCT g.Gusto
FROM GELATO AS g
WHERE NOT EXISTS (
  SELECT 1
  FROM RICETTA AS r
  JOIN INGREDIENTE AS i
    ON i.Id = r.IdIngrediente
  WHERE r.IdGelato = g.Id
    AND i.Tipo = 'latticini'
);
```

`NOT EXISTS` esprime direttamente l'assenza di ingredienti di tipo `latticini` e non soffre dei problemi di `NOT IN` in presenza di `NULL`.

#### **6.4. Algebra - Ingrediente impiegato nella maggiore quantità per gelato**

Si creano due copie rinominate di `RICETTA`:

$$
R_1:=\rho_{G_1,I_1,Q_1\leftarrow IdGelato,IdIngrediente,Quantità}(RICETTA),
$$

$$
R_2:=\rho_{G_2,I_2,Q_2\leftarrow IdGelato,IdIngrediente,Quantità}(RICETTA).
$$

Le coppie dominate, per cui esiste nello stesso gelato un ingrediente in quantità strettamente maggiore, sono:

$$
Dominati:=
\pi_{G_1,I_1}
\left(
\sigma_{G_1=G_2\land Q_1<Q_2}(R_1\times R_2)
\right).
$$

Il risultato richiesto è:

$$
\boxed{
\rho_{G_1,I_1\leftarrow IdGelato,IdIngrediente}
\left(\pi_{IdGelato,IdIngrediente}(RICETTA)\right)
-Dominati
}.
$$

Se più ingredienti condividono la quantità massima per lo stesso gelato, vengono restituiti tutti: nessuno di essi è dominato da una quantità strettamente maggiore.

---

### **7. Esercizio 3 - Progettazione E-R per l'azienda di condizionatori**

#### **7.1. Scelte concettuali**

**Riferimenti:** [M4/UD2/L1 - Entità, relazioni e attributi](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L1 - Modello E-R - Entità, relazioni e attributi.md>) · [M4/UD2/L2 - Cardinalità](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L2 - Modello E-R - Le cardinalità.md>) · [M4/UD2/L3 - Identificatori e gerarchie](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L3 - Modello E-R - Identificatori e gerarchie.md>) · [M4/UD2/L4 - Documentazione](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L4 - Modello E-R - Documentazione di schemi.md>)

Il testo descrive modelli di prodotti e piani periodici di manutenzione, non i singoli esemplari venduti né le manutenzioni già eseguite. Lo schema concettuale deve quindi rappresentare i tipi di condizionatore, motore, filtro e intervento.

#### **7.2. Entità, attributi e identificatori**

|Entità|Identificatore|Attributi e cardinalità|
|---|---|---|
|PRODUTTORE|PartitaIVA|RagioneSociale $(1,1)$; Telefono $(1,N)$|
|CONDIZIONATORE|identificatore esterno `(PRODUTTORE, Codice)`|Codice $(1,1)$, NomeCommerciale $(1,1)$, PompaCalore $(1,1)$, PrezzoEuro $(1,1)$|
|FILTRO|$(Funzionalità, Lunghezza, Larghezza)$|Funzionalità, LunghezzaCm e LarghezzaCm, tutti $(1,1)$|
|MOTORE|CodiceCatalogo|RumoreDb, PotenzaAssorbita, PrezzoVendita, tutti $(1,1)$|
|INTERVENTO_MANUTENZIONE|Tipo|Durata e Prezzo, entrambi $(1,1)$|

Il codice del condizionatore è dichiarato univoco soltanto nell'ambito del produttore: `CONDIZIONATORE` è quindi un'entità debole identificata dalla relazione `PRODUCE` e dal proprio `Codice`.

La traccia non fornisce un codice per il filtro. La terna delle caratteristiche note è assunta come identificatore naturale; se il dominio ammettesse filtri diversi con identiche caratteristiche, occorrerebbe introdurre un `IdFiltro` interno, perché il testo non offre un altro discriminante.

#### **7.3. Gerarchie**

`PRODUTTORE` ha una specializzazione **totale ed esclusiva** in:

- `PRODUTTORE_PARETE`;
- `PRODUTTORE_SOFFITTO`.

Ogni produttore appartiene esattamente a una delle due classi, perché è specializzato in un solo tipo di condizionatore.

Anche `CONDIZIONATORE` ha una specializzazione **totale ed esclusiva** in:

- `CONDIZIONATORE_PARETE`, con attributo `Incasso` booleano $(1,1)$;
- `CONDIZIONATORE_SOFFITTO`, con `TipoSoffitto` $(1,1)$ nel dominio `{controsoffittatura, tradizionale}` e `LatoCm` $(1,1)$.

Le entità figlie ereditano attributi, identificatore e associazioni del padre.

#### **7.4. Associazioni e cardinalità**

|Associazione|Entità partecipanti|Attributi|Significato|
|---|---|---|---|
|PRODUCE|PRODUTTORE $(1,N)$ - CONDIZIONATORE $(1,1)$|—|Ogni modello ha esattamente un produttore; l'associazione partecipa al suo identificatore esterno.|
|MONTA|CONDIZIONATORE $(1,N)$ - FILTRO $(1,N)$|—|Un modello può montare più filtri e lo stesso filtro è adatto a più modelli.|
|ABBINATO_A|CONDIZIONATORE $(1,1)$ - MOTORE $(1,N)$|—|Ogni condizionatore richiede un solo tipo di motore; un motore serve più modelli.|
|RICHIEDE|MOTORE $(1,N)$ - INTERVENTO_MANUTENZIONE $(1,N)$|Periodicità $(1,1)$|Motori e tipi d'intervento sono in relazione molti-a-molti; la frequenza dipende dalla coppia.|

#### **7.5. Vincoli di integrità da documentare**

1. Un `PRODUTTORE_PARETE` produce soltanto `CONDIZIONATORE_PARETE`; analogamente, un `PRODUTTORE_SOFFITTO` produce soltanto `CONDIZIONATORE_SOFFITTO`.
2. `Codice` è univoco all'interno di ciascun produttore, non necessariamente nell'intero catalogo.
3. Ogni produttore ha almeno un numero di telefono.
4. `PompaCalore` e `Incasso` sono booleani; prezzi, dimensioni, rumore, potenza, durata e periodicità sono positivi.
5. Ogni condizionatore appartiene a uno e un solo sottotipo e ha esattamente un motore compatibile.
6. `TipoSoffitto` assume soltanto i valori `controsoffittatura` o `tradizionale`.
7. La periodicità è attributo di `RICHIEDE`, non di `INTERVENTO_MANUTENZIONE`, perché lo stesso intervento può avere frequenze diverse per motori diversi.

Il primo vincolo può essere reso graficamente separando `PRODUCE` in due associazioni fra i corrispondenti sottotipi; se si mantiene l'associazione unica, deve comparire nella documentazione dello schema.

> 📌 Non va introdotta un'entità per l'intervento materialmente eseguito: la traccia richiede il piano periodico associato a ciascun tipo di motore, non data e storico delle esecuzioni.

---

### **8. Checklist finale dell'appello**

- Le quattro domande teoriche sono risolte con definizioni, motivazioni ed estremi raggiungibili.
- I quattro intervalli del controllo timestamp sono ricavati riga per riga.
- Le due query SQL e l'interrogazione in algebra relazionale gestiscono correttamente aggregazione, assenza e parità sul massimo.
- Lo schema E-R specifica entità, identificatori, attributi, gerarchie, cardinalità e vincoli non esprimibili graficamente.
