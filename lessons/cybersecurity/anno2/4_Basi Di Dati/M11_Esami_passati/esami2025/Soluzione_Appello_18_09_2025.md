# **Basi di Dati - Soluzione completa dell'appello del 18 settembre 2025**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2025_09_18.pdf)

---

### **1. Domanda 1 - Two-Phase Commit**

Il 2PC coordina TM e RM per garantire che una transazione distribuita termini ovunque con commit oppure ovunque con abort.

- **Fase 1:** il TM registra e invia `prepare`; un RM pronto forza `ready`, mantiene i lock e vota sì, altrimenti vota no.
- **Fase 2:** tutti i sì producono `global commit`; un no o timeout produce `global abort`. La decisione è forzata nel log, inviata, confermata con `ack` e chiusa da `complete`.

Un RM caduto fa `REDO` se trova commit, abortisce se non era `ready`, e interroga il TM se trova soltanto `ready`. Un TM ripartito dopo `prepare` abortisce o ripete la fase; dopo la decisione la ritrasmette. Messaggi decisionali e `ack` persi vengono ritrasmessi; una partizione può bloccare i partecipanti incerti.

La finestra fra `ready` e decisione è detta **finestra di incertezza**. Un partecipante che ha soltanto letto risponde `read-only`, libera i lock ed esce dal protocollo senza attendere la seconda fase.

**Riferimenti:** [2PC](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>) · [recovery](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>)

---

### **2. Domanda 2 - Controllo timestamp**

Il timestamp impone l'ordine seriale logico senza lock e quindi senza deadlock.

Nel **monoversione**, ogni oggetto $x$ conserva $RTM(x)$ e $WTM(x)$:

$$
r_t(x):\quad t<WTM(x)\Rightarrow abort;
\quad RTM(x):=\max(RTM(x),t),
$$

$$
w_t(x):\quad t<RTM(x)\lor t<WTM(x)\Rightarrow abort;
\quad WTM(x):=t.
$$

Nel **multiversione**, ogni versione $x_k$ ha propri indicatori. Si sceglie $x_j$ con massimo $WTM(x_j)\leq t$: la lettura è sempre accettata e aggiorna $RTM(x_j)$; la scrittura è respinta se $t<RTM(x_j)$, altrimenti crea $x_t$ con $RTM=WTM=t$.

Esempio minimo, con $t_1<t_2$:

$$
w_2(x);\ r_1(x).
$$

Il mono rifiuta `r1` perché $t_1<WTM(x)=t_2$; il multi la accetta leggendo la versione iniziale $x_0$. Una sola operazione non può mostrare alcuna differenza, dunque due sono il minimo.

---

### **3. Domanda 3 - Cardinalità relazionali**

Le chiavi sono `r.A`, `s.B`, `t.C`; inoltre

$$
\pi_B(r)\subseteq\pi_B(s),\qquad \pi_C(s)\subseteq\pi_C(t).
$$

|Espressione|Minimo|Massimo|Motivazione|
|---|---:|---:|---|
|$r\bowtie t$|200|200|Non vi sono attributi comuni: è $r\times t$.|
|$\pi_B(s)-\pi_B(r)$|20|29|$\pi_B(s)$ ha 30 valori; i 10 record di $r$ usano da 1 a 10 valori distinti di `B`.|
|$\pi_B(r)-\pi_B(s)$|0|0|Differenza vuota per il riferimento.|
|$\sigma_{B=C}(s)$|0|20|`B` è chiave e `C` può assumere soltanto i 20 valori della chiave `t.C`; quindi al più 20 coppie distinte soddisfano `B=C`.|
|$\pi_D(s\bowtie t)$|1|20|Ogni tupla di $s$ trova una tupla di $t$; la proiezione può mostrare da uno a tutti i 20 valori di `D`.|

---

### **4. Esercizio 1 - Trigger di archiviazione**

Il trigger deve essere `BEFORE DELETE`, così gli acquisti vengono archiviati e rimossi prima che la foreign key verso `CLIENTE` sia verificata:

```sql
CREATE FUNCTION archivia_cliente()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
  INSERT INTO VECCHICLIENTI(CF, Cognome, Nome)
  VALUES (OLD.CF, OLD.Cognome, OLD.Nome);

  INSERT INTO STORICOACQUISTO(CFCliente, CodiceRobot, Data, ModPagamento)
  SELECT CFCliente, CodiceRobot, Data, ModPagamento
  FROM ACQUISTO
  WHERE CFCliente = OLD.CF;

  DELETE FROM ACQUISTO
  WHERE CFCliente = OLD.CF;

  RETURN OLD;
END;
$$;

CREATE TRIGGER cliente_before_delete
BEFORE DELETE ON CLIENTE
FOR EACH ROW
EXECUTE FUNCTION archivia_cliente();
```

Le tre azioni appartengono alla stessa transazione della cancellazione: un errore provoca il rollback dell'intero spostamento.

---

### **5. Esercizio 2 - Supporto e confidenza**

Con $N=1000$ e $|X\cap Y|=300$:

$$
conf(X\to Y)=\frac{300}{|X|}=0{,}3
\Rightarrow |X|=1000.
$$

Inoltre $|Y\cap Z|=0{,}3|Y|$. Poiché $Y$ contiene almeno i 300 elementi di $X\cap Y$:

|Insieme|Minimo|Massimo|
|---|---:|---:|
|$X$|1000|1000|
|$Y$|300|1000|
|$Z$|90|790|

Il minimo di $Z$ si ottiene con $|Y|=300$ e $Z=Y\cap Z$; il massimo aggiunge ai 90 elementi dell'intersezione tutti i 700 elementi esterni a $Y$.

I supporti delle regole opposte coincidono sempre:

$$
\boxed{supp(Y\to Z)=supp(Z\to Y)=\frac{|Y\cap Z|}{1000}}.
$$

Il confronto fra le confidenze non è determinabile, perché

$$
conf(Y\to Z)=0{,}3,\qquad
conf(Z\to Y)=\frac{0{,}3|Y|}{|Z|}
$$

può essere minore, uguale o maggiore di 0,3 secondo che $|Z|$ sia maggiore, uguale o minore di $|Y|$.

---

### **6. Esercizio 3 - Scuola di formazione**

#### **6.1. Lezioni col minimo numero di partecipanti**

```sql
WITH Conteggi AS (
  SELECT l.codlez, COUNT(f.CF) AS partecipanti
  FROM LEZIONE AS l
  LEFT JOIN FREQUENZA AS f ON f.codlez = l.codlez
  GROUP BY l.codlez
)
SELECT codlez
FROM Conteggi
WHERE partecipanti = (SELECT MIN(partecipanti) FROM Conteggi);
```

Il `LEFT JOIN` include le lezioni con zero partecipanti; non serve `DISTINCT`.

#### **6.2. Lezioni con partecipanti della stessa città**

```sql
SELECT l.codlez, l.data, l.ora, l.tipo
FROM LEZIONE AS l
JOIN FREQUENZA AS f ON f.codlez = l.codlez
JOIN ISCRITTO AS i ON i.CF = f.CF
GROUP BY l.codlez, l.data, l.ora, l.tipo
HAVING MIN(i.città) = MAX(i.città);
```

Il join interno richiede almeno un partecipante; `MIN=MAX` evita un `DISTINCT` non necessario.

#### **6.3. Iscritti che hanno seguito solo laboratorio**

$$
Lab:=\pi_{CF}\left(FREQUENZA\bowtie\sigma_{tipo='laboratorio'}(LEZIONE)\right),
$$

$$
Teoria:=\pi_{CF}\left(FREQUENZA\bowtie\sigma_{tipo='teoria'}(LEZIONE)\right),
$$

$$
\boxed{Lab-Teoria}.
$$

La selezione anticipata riduce gli operandi del join; il primo operando esclude chi non ha frequentato alcuna lezione.

---

### **7. Esercizio 4 - Schema E-R Buona Visione**

#### **7.1. Entità e gerarchie**

|Entità|Identificatore|Attributi|
|---|---|---|
|FILM|Codice|Titolo, Durata, Regista, Anno, Recensione $(1,N)$|
|ATTORE|IdAttore|Nome, Cognome, DataNascita|
|SPETTACOLO|Codice|Data, Ora|
|SALA|IdSala|Nome, NumeroPosti, NumeroFile, TipoAudio|
|BIGLIETTO|`(SPETTACOLO, NumeroPosto)`|Prezzo|

Gli identificatori tecnici di attore e sala evitano di assumere univocità non dichiarate. `FILM` ha una gerarchia **parziale e sovrapposta**: `AZIONE(TecnicoEffetti)`, `MUSICAL(Compositore)`, `ANIMAZIONE(CharacterDesign)`. È parziale perché esistono altri generi e sovrapposta perché un'animazione può essere anche musical o azione.

`BIGLIETTO_RIDOTTO(Motivazione)` è una specializzazione parziale di `BIGLIETTO`; l'indicatore `Ridotto` è derivabile dall'appartenenza al sottotipo.

#### **7.2. Associazioni e vincoli**

|Associazione|Cardinalità|Attributi|
|---|---|---|
|RECITA|FILM $(1,N)$ - ATTORE $(1,N)$|Protagonista booleano|
|PROIETTA|FILM $(1,N)$ - SPETTACOLO $(1,1)$|—|
|SI_SVOLGE|SALA $(0,N)$ - SPETTACOLO $(1,1)$|—|
|VENDUTO_PER|SPETTACOLO $(0,N)$ - BIGLIETTO $(1,1)$|identifica BIGLIETTO|

Per ogni spettacolo il numero di posti dei biglietti deve essere compreso nella capienza della sala e non può ripetersi; prezzo, durata e capacità sono positivi. La motivazione è presente se e solo se il biglietto è ridotto.

> ✅ Il ruolo da protagonista è attributo di `RECITA`, perché dipende dalla coppia attore-film e non dall'attore in assoluto.
