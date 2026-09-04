# **Basi di Dati - Soluzione completa dell'appello del 26 maggio 2025**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2025_05_26.pdf)

---

### **1. Domanda 1 - Integrità referenziale e CASCADE**

`PROGETTO.Matricola` è una foreign key verso `IMPIEGATO.Matricola`. Un inserimento/modifica in `PROGETTO` viola il vincolo se indica una matricola assente; una cancellazione o modifica della chiave in `IMPIEGATO` lo viola se lascia progetti orfani.

Le reazioni SQL a `DELETE` e `UPDATE` sono `NO ACTION`/`RESTRICT`, `CASCADE`, `SET NULL` e `SET DEFAULT`, compatibilmente con gli altri vincoli.

Con `ON DELETE CASCADE`, cancellando gli impiegati Rossi spariscono le matricole 1 e 4; soltanto la 1 è riferita. Rimangono:

|IMPIEGATO.Matricola|Cognome|Nome|
|---:|---|---|
|2|Verdi|Giuseppe|
|3|Gialli|Luca|

|PROGETTO.Matricola|Progetto|Ruolo|
|---:|---|---|
|2|Alfa|commerciale|
|3|Gamma|sviluppatore|

Cancellando invece da `PROGETTO` le righe `Alfa`, gli impiegati non cambiano: `CASCADE` si propaga dal padre riferito ai figli referenti, non al contrario. In `PROGETTO` restano `(1,Beta,manager)` e `(3,Gamma,sviluppatore)`.

---

### **2. Domanda 2 - Timestamp mono- e multiversione**

**Riferimento:** [M6/UD3/L6 - Timestamp](<../../M06_Gestione_Transazioni/UD3/L6 - Timestamp.md>)

Il timestamp $TS(T)$ fissa l'ordine seriale logico. Nel monoversione ogni oggetto conserva $RTM$, massimo timestamp dei lettori, e $WTM$, massimo timestamp degli scrittori:

$$
read(x,t)\text{ è accettata}\iff t\geq WTM(x),
$$

$$
write(x,t)\text{ è accettata}\iff t\geq RTM(x)\land t\geq WTM(x).
$$

Una lettura accettata aggiorna $RTM:=\max(RTM,t)$; una scrittura aggiorna $WTM:=t$.

Nel multiversione ogni versione $x_k$ ha propri $RTM(x_k),WTM(x_k)$. Un'operazione con timestamp $t$ sceglie la versione di massimo $WTM\leq t$: la lettura è sempre accettata e ne aggiorna `RTM`; la scrittura è accettata se $t\geq RTM$ della versione scelta e crea $x_t$ con $RTM=WTM=t$.

Risposte agli esempi richiesti:

1. **Monoversione ma non multiversione:** impossibile in due operazioni, e in generale con queste regole. Se una scrittura supera i massimi globali mono, supera anche l'`RTM` della versione scelta; le letture multi non sono mai meno permissive.
2. **Multiversione ma non monoversione:** con $TS(T_1)<TS(T_2)$, $w_2(x);r_1(x)$. Il mono rifiuta `r1` perché $TS_1<WTM(x)=TS_2$; il multi legge la versione iniziale.
3. **2PL ma non TS mono:** la stessa sequenza $w_2(x);r_1(x)$ è producibile in 2PL facendo terminare e sbloccare $T_2$ prima di $T_1$, ma è rifiutata dal timestamp mono.

---

### **3. Domanda 3 - Cardinalità relazionali**

Le chiavi sono `R.A`, `(S.A,S.C)` e `T.E`; inoltre $S.A$ riferisce $R.A$. Ne segue:

|Espressione|Minimo|Massimo|Motivazione|
|---|---:|---:|---|
|$R\bowtie S$|20|20|Ogni tupla di $S$ trova esattamente una tupla di $R$.|
|$\pi_A(R)-\pi_A(S)$|0|9|`S` può usare tutti i 10 valori di `R.A` oppure uno soltanto; la chiave `(A,C)` consente di ripetere `A` con `C` diversi.|
|$\pi_A(S)-\pi_A(R)$|0|0|È vuota per integrità referenziale.|

Le tuple fornite non restringono ulteriormente gli estremi: `(b,c,d)` impone che `b` compaia anche in `R`, ma gli altri valori restano distribuibili come sopra.

---

### **4. Domanda 4 - Data Cube e roll-up**

Poiché $R$ contiene tutte le coppie di $n$ valori di `A` e $m$ valori di `B`:

$$
\boxed{|R|=nm}.
$$

Il Data Cube a due dimensioni comprende i cuboidi `(A,B)`, `(A,ALL)`, `(ALL,B)` e `(ALL,ALL)`:

$$
\boxed{|cube(R)|=nm+n+m+1=(n+1)(m+1)}.
$$

Il roll-up su `B` aggrega tutti i valori di `B` per ciascun valore di `A`, quindi:

$$
\boxed{|rollup_B(R)|=n}.
$$

---

### **5. Esercizio 1 - Classificazione degli schedule**

**Riferimenti:** [view-serializzabilità](<../../M06_Gestione_Transazioni/UD3/L3 - View-serializzabilità.md>) · [conflict-serializzabilità](<../../M06_Gestione_Transazioni/UD3/L4 - Conflict-serializzabilità.md>)

|Schedule|VSR|CSR|Schedule seriale equivalente o motivo|
|---|---|---|---|
|A `r1 w2 w1 r3`|No|No|`r1` iniziale impone $T_1<T_2$, ma lo scrittore finale $T_1$ impone $T_2<T_1$.|
|B `w1 r2 r3 w2`|Sì|Sì|$T_1,T_3,T_2$.|
|C `w1 r2 w3 w2`|Sì|No|View-equivalente a $T_3,T_1,T_2$; il grafo ha il ciclo $T_2\leftrightarrow T_3$.|
|D `r1 w3 w1 w2`|Sì|No|View-equivalente a $T_1,T_3,T_2$; il grafo ha il ciclo $T_1\leftrightarrow T_3$.|

Nei casi C e D le scritture cieche spiegano perché VSR è strettamente più ampia di CSR.

---

### **6. Esercizio 2 - Prestiti di film**

#### **6.1. Numero medio di prestiti giornalieri per genere**

```sql
WITH PrestitiGiornalieri AS (
  SELECT f.Genere, p.DataPrestito, COUNT(*) AS NumeroPrestiti
  FROM FILM AS f
  JOIN PRESTITO AS p ON p.IdFilm = f.IdFilm
  GROUP BY f.Genere, p.DataPrestito
)
SELECT Genere, AVG(NumeroPrestiti * 1.0) AS MediaGiornaliera
FROM PrestitiGiornalieri
GROUP BY Genere;
```

La media è calcolata sui giorni in cui il genere ha almeno un prestito; per includere giorni a zero servirebbe una relazione calendario.

#### **6.2. Clienti che non hanno mai preso due volte lo stesso film**

```sql
SELECT c.IdTessera, c.Nome, c.Cognome
FROM CLIENTE AS c
WHERE NOT EXISTS (
  SELECT 1
  FROM PRESTITO AS p
  WHERE p.IdTessera = c.IdTessera
  GROUP BY p.IdFilm
  HAVING COUNT(*) >= 2
);
```

La query include correttamente anche chi non ha prestiti e non necessita di `DISTINCT`.

#### **6.3. Clienti che hanno preso tutti i film**

$$
LettoriUniversali:=
\pi_{IdTessera,IdFilm}(PRESTITO)\div\pi_{IdFilm}(FILM),
$$

$$
\boxed{CLIENTE\bowtie LettoriUniversali}.
$$

---

### **7. Esercizio 3 - Schema E-R Delicious Apple**

#### **7.1. Entità e gerarchie**

|Entità|Identificatore|Attributi|
|---|---|---|
|PIANTAGIONE|IdPiantagione|Città, Dimensione, Irrigazione, TipoTerreno|
|PIANTA|`(PIANTAGIONE, Progressivo)`|Posizione, DataImpianto|
|SPECIE|NomeScientifico|NomeComune, MeseMaturazione, DurataMedia|
|RACCOLTO|IdRaccolto|Data, QuantitàKg|
|MAGAZZINO|IdMagazzino|Capienza, Tipo, Indirizzo composto|

Gli identificatori tecnici sono introdotti dove la traccia non garantisce una chiave naturale. `PIANTA` è invece identificata esternamente: il progressivo è univoco soltanto nella piantagione.

`PIANTAGIONE` ha una specializzazione totale ed esclusiva:

- `BIOLOGICA(NumeroCertificazione, DataRilascio, Antiparassitario (1,N), ConcimeNaturale (1,N))`;
- `NON_BIOLOGICA(Fertilizzante (0,N), Pesticida (0,N))`.

#### **7.2. Associazioni e vincoli**

|Associazione|Cardinalità|Attributi|
|---|---|---|
|OSPITA|PIANTAGIONE $(1,N)$ - PIANTA $(1,1)$|identifica PIANTA|
|APPARTIENE|SPECIE $(1,N)$ - PIANTA $(1,1)$|—|
|INTERESSA|RACCOLTO $(1,N)$ - PIANTAGIONE $(0,N)$|—|
|STOCCAGGIO|RACCOLTO $(1,N)$ - MAGAZZINO $(0,N)$|KgAssegnati|

Per ogni raccolto deve valere $\sum KgAssegnati\leq QuantitàKg$; per ogni magazzino, la somma stoccata non può superare la capienza. Quantità, dimensioni, durata e chilogrammi sono positivi.

> ✅ La raccolta è modellata come evento condivisibile da più piantagioni; lo stoccaggio conserva la quantità assegnata a ciascuna coppia raccolto-magazzino.
