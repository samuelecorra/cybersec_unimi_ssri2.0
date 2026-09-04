# **Basi di Dati - Soluzione completa dell'appello del 13 giugno 2025**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2025_06_13.pdf)

---

### **1. Domanda 1 - Gerarchie E-R e ristrutturazione**

**Riferimenti:** [gerarchie](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L3 - Modello E-R - Identificatori e gerarchie.md>) · [eliminazione delle gerarchie](<../../M04_Progettazione_di_Basi_di_Dati/UD4/L2 - Ristrutturazione di schemi E-R - Eliminazione delle gerarchie.md>)

Una specializzazione è **totale** se ogni istanza del padre appartiene ad almeno una figlia, altrimenti è **parziale**; è **esclusiva** se appartiene ad al più una figlia, altrimenti è **sovrapposta**. Si ottengono quindi quattro combinazioni: per esempio Persona→Uomo/Donna totale-esclusiva; Dipendente→Studente parziale; Persona→Studente/Lavoratore sovrapposta.

La figura presenta $P\to\{F1,F2\}$ **parziale e sovrapposta**, `P(k1,a1)`, `F1(a2)`, `F2(a3)`, `R1` fra P $(0,1)$ ed E1 $(1,N)$, e `R2` fra F2 ed E2, entrambi $(1,N)$.

|Strategia|Schema ristrutturato|Regole/informazione non più grafica|
|---|---|---|
|Mantenimento|Si conservano P, F1, F2; due associazioni P-F1 e P-F2 hanno P $(0,1)$ e figlia $(1,1)$ e identificano esternamente le figlie. R1 e R2 restano.|Nessun vincolo di copertura o esclusione è richiesto: la gerarchia originaria ammette né figlie o entrambe.|
|Collasso verso l'alto|Si eliminano F1,F2; P riceve `a2`,`a3` opzionali e i booleani `isF1`,`isF2`. R2 diventa P $(0,N)$-E2 $(1,N)$; R1 resta.|`a2` è valorizzato solo per `isF1`; `a3` e almeno un legame R2 esistono esattamente per `isF2`. Entrambi i flag possono essere falsi o veri.|
|Collasso verso il basso|Si elimina P, replicando `k1,a1` e R1 nelle figlie. Poiché la gerarchia è parziale serve una figlia residuale F0; poiché è sovrapposta, un P può comparire in F1 e F2. R2 resta su F2.|Occorrono unicità globale di `k1`, coerenza delle copie sovrapposte e una regola che imponga a ogni E1 almeno un legame R1 complessivo; senza F0 si perderebbero i P non specializzati.|

---

### **2. Domanda 2 - Livelli di isolamento e lock di predicato**

|Livello SQL|Garanzia minima|
|---|---|
|READ UNCOMMITTED|può ammettere letture sporche, non ripetibili e fantasmi|
|READ COMMITTED|vieta letture sporche; può ammettere non ripetibili e fantasmi|
|REPEATABLE READ|vieta letture sporche e non ripetibili; può ammettere fantasmi|
|SERIALIZABLE|vieta tutte e tre le anomalie e garantisce un comportamento serializzabile|

I lock esclusivi di scrittura sono mantenuti fino al termine; aumentando il livello si estende la durata dei lock di lettura. Per eliminare i fantasmi non basta bloccare le tuple già esistenti: un **lock di predicato** blocca l'insieme logico definito da una condizione, per esempio `saldo > 1000`, impedendo anche inserimenti o aggiornamenti che farebbero entrare nuove tuple nell'insieme fino al termine della transazione.

---

### **3. Domanda 3 - Trigger per le letture timestamp**

Una lettura `(T,ts,'R',x)` va rifiutata se $ts<WTM(x)$, dove `WTM` è il massimo timestamp delle scritture riuscite sullo stesso oggetto. In PostgreSQL:

```sql
CREATE FUNCTION controlla_lettura_ts()
RETURNS trigger
LANGUAGE plpgsql
AS $$
DECLARE
  wtm bigint;
BEGIN
  IF NEW.TipoOperazione = 'R' THEN
    SELECT COALESCE(MAX(TimestampTransazione), 0)
      INTO wtm
      FROM OPERAZIONI
     WHERE Oggetto = NEW.Oggetto
       AND TipoOperazione = 'W';

    IF NEW.TimestampTransazione < wtm THEN
      RAISE EXCEPTION 'lettura fuori ordine: TS %, WTM %',
        NEW.TimestampTransazione, wtm;
    END IF;
  END IF;
  RETURN NEW;
END;
$$;

CREATE TRIGGER operazioni_check_read
BEFORE INSERT ON OPERAZIONI
FOR EACH ROW
EXECUTE FUNCTION controlla_lettura_ts();
```

La nuova riga registrerà la lettura riuscita e permetterà di ricavare l'`RTM` come massimo dei timestamp di lettura.

---

### **4. Domanda 4 - Cardinalità relazionali**

Le chiavi sono `r.A`, `(s.A,s.D)` e `(t.A,t.F)`; `s.A` e `t.A` riferiscono `r.A`.

|Espressione|Minimo|Massimo|Motivazione|
|---|---:|---:|---|
|$\pi_A(s)$|1|10|`s` è non vuota; i valori di `A` appartengono ai 10 valori della chiave di $r$.|
|$\sigma_{A=F}(r\bowtie t)$|0|10|Il join ha 80 tuple, una per tupla di $t$; con `A=F`, la chiave `(A,F)` ammette al più una tupla per ciascuno dei 10 valori di `A`.|

Entrambi gli estremi sono raggiungibili scegliendo opportunamente `D` e `F`.

---

### **5. Esercizio 1 - Verifica 2PL base**

Lo schedule **non può** essere generato da 2PL a lock binario. Fino al conflitto decisivo:

|Passo|Azione necessaria|Lock detenuti dopo il passo|
|---:|---|---|
|1|`lock1(x); r1(x)`|$T_1:\{x\}$|
|2|`lock1(z); w1(z)`|$T_1:\{x,z\}$|
|3|`lock2(t); w2(t)`|$T_1:\{x,z\}$; $T_2:\{t\}$|
|4|`lock3(y); r3(y)`|precedenti; $T_3:\{y\}$|
|5|per `r4(y)`, $T_3$ dovrebbe eseguire `unlock3(y)`|ma $T_3$ deve ancora acquisire `t` per `w3(t)`|

$T_3$ non può acquisire `t` prima dello sblocco, perché `t` è detenuto da $T_2$; non può acquisirlo dopo, perché sarebbe già nella fase decrescente. Simmetricamente, se $T_2$ liberasse `t` per far avanzare $T_3$, non potrebbe poi acquisire `x` per `w2(x)`. Non esiste quindi alcun inserimento di lock/unlock conforme alle due fasi.

---

### **6. Esercizio 2 - Regole di associazione**

Scrivendo $a=|A|$, $ab=|A\cap B|$ e $abc=|A\cap B\cap C|$, i dati diventano

$$
\frac{ab}{N_T}=\frac{ab}{a},\qquad ab=abc.
$$

La seconda uguaglianza impone sempre $A\cap B\subseteq C$. La prima implica $a=N_T$ soltanto se $ab>0$; se $ab=0$ è verificata per qualunque $a>0$. Questo caso limite va conservato.

|Affermazione|Esito|Motivo|
|---|---|---|
|$supp(A\to B)=supp(A\to C)$|Non determinabile|$A\cap C$ può essere più grande di $A\cap B$.|
|$supp(A\to C)=conf(A\to C)$|Non determinabile|è forzata se $A$ è universale, non nel caso $ab=0$.|
|$conf(A\to B)=conf(A\to BC)$|Vero|$ab=abc$, con lo stesso denominatore $a$.|
|Ogni transazione con A e B contiene C|Vero|$|AB|=|ABC|$ equivale a $AB\subseteq C$.|
|B compare più di C|Non determinabile|i dati vincolano solo la loro intersezione con A.|
|C non compare mai senza A|Non determinabile|segue se $ab>0$ e dunque A è universale, ma non è forzata se $ab=0$.|

---

### **7. Esercizio 3 - Corse**

#### **7.1. Città con almeno cinque utenti oltre 1.000 km**

```sql
WITH Forti AS (
  SELECT u.Id, u.Città
  FROM UTENTE AS u
  JOIN CORSA AS c ON c.IdUtente = u.Id
  GROUP BY u.Id, u.Città
  HAVING SUM(c.KmPercorsi) > 1000
)
SELECT Città
FROM Forti
GROUP BY Città
HAVING COUNT(*) >= 5;
```

#### **7.2. Record personale nell'ultima corsa**

```sql
SELECT c.IdUtente
FROM CORSA AS c
WHERE NOT EXISTS (
  SELECT 1 FROM CORSA AS x
  WHERE x.IdUtente = c.IdUtente
    AND (x.Data, x.OraInizio, x.Progressivo)
        > (c.Data, c.OraInizio, c.Progressivo)
)
AND NOT EXISTS (
  SELECT 1 FROM CORSA AS p
  WHERE p.IdUtente = c.IdUtente
    AND (p.Data, p.OraInizio, p.Progressivo)
        < (c.Data, c.OraInizio, c.Progressivo)
    AND p.KmPercorsi >= c.KmPercorsi
);
```

Il progressivo scioglie eventuali parità di data e ora; “più lunga” è misurata dai chilometri percorsi e richiede un confronto stretto, quindi una distanza precedente uguale esclude l'utente.

#### **7.3. Città in cui tutti gli utenti hanno corso**

$$
SenzaCorsa:=\pi_{Id}(UTENTE)-\rho_{Id\leftarrow IdUtente}(\pi_{IdUtente}(CORSA)),
$$

$$
\boxed{\pi_{Città}(UTENTE)-\pi_{Città}(UTENTE\bowtie SenzaCorsa)}.
$$

---

### **8. Esercizio 4 - Schema E-R MobilBagno**

|Entità|Identificatore|Attributi|
|---|---|---|
|MOBILE|`(NomeCommerciale, Linea)`|Stile, Dimensioni, PrezzoListino, Colore $(1,N)$|
|COMPOSIZIONE|IdComposizione|LunghezzaTotale, PrezzoComplessivo|
|CLIENTE|CodiceFiscale|Nome, Cognome, Telefono|
|LAVABO|IdLavabo|Forma, Materiale, Prezzo, Dimensioni|
|TOP|IdTop|Materiale, PrezzoMq, Colore $(1,N)$|
|RUBINETTO|IdRubinetto|Materiale, Colore, Tipologia, PrezzoListino|

Gli ultimi quattro identificatori tecnici sono necessari dove non è data una chiave naturale. `LAVABO` è specializzato totalmente ed esclusivamente in `CLASSICO(AltezzaDaTerra)` e `INCASSO`.

|Associazione|Cardinalità|Attributi|
|---|---|---|
|COMPONE|COMPOSIZIONE $(1,N)$ - MOBILE $(0,N)$|Quantità|
|PRENOTA|CLIENTE $(0,N)$ - COMPOSIZIONE $(0,N)$|—|
|ABBINABILE|INCASSO $(0,N)$ - MOBILE $(0,N)$|—|
|COLLOCABILE|INCASSO $(0,N)$ - TOP $(1,N)$|—|
|MONTA|LAVABO $(1,1)$ - RUBINETTO $(0,N)$|—|

Quantità e prezzi sono positivi; ogni composizione contiene almeno un mobile, ogni top è compatibile con almeno un lavabo a incasso e ogni lavabo monta esattamente un modello di rubinetto.
