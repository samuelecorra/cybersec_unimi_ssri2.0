# **Basi di Dati - Soluzione completa dell'appello del 4 luglio 2025**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2025_07_04.pdf)

---

### **1. Domanda 1 - Two-Phase Commit**

**Riferimenti:** [2PC](<../../M07_Architetture_Distribuite/UD3/L3_1 - Protocollo di commit a due fasi.md>) · [guasti e ottimizzazioni](<../../M07_Architetture_Distribuite/UD3/L3_2 - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni.md>)

Il 2PC garantisce l'atomicità globale di una transazione distribuita: tutti i Resource Manager (RM) committano oppure tutti abortiscono.

1. **Prepare/voto.** Il Transaction Manager (TM) forza `prepare` nel log e interroga gli RM. Ogni RM che può committare forza `ready`, conserva i lock e vota sì; altrimenti vota no e abortisce.
2. **Decisione.** Tutti sì implicano `global commit`; un no o un timeout implica `global abort`. Il TM forza la decisione, la invia agli RM, attende gli `ack` e registra `complete`.

Recovery essenziale:

- RM senza `ready`: può abortire; con commit: `REDO`; con `ready` ma senza decisione: resta incerto e interroga il TM;
- TM dopo `prepare`: può abortire o ripetere la fase; dopo una decisione: la ritrasmette; dopo `complete`: nessuna azione;
- perdita di `prepare`/voto: timeout e abort; perdita di decisione/`ack`: ritrasmissione idempotente.

La **finestra di incertezza** va dal `ready` alla ricezione della decisione: l'RM non può più decidere autonomamente e può restare bloccato se il TM è irraggiungibile. Un RM di sola lettura vota `read-only`, libera subito le risorse ed esce prima della seconda fase.

---

### **2. Domanda 2 - Prevenzione dei deadlock**

Sia $T_i$ la richiedente e $T_j$ la detentrice del lock; timestamp minore significa più anziana.

- **Wait-die, non preemptive:** $T_i$ attende se più anziana; se più giovane, viene abortita.
- **Wound-wait, preemptive:** se $T_i$ è più anziana abortisce $T_j$; se è più giovane, attende.

La transazione abortita riparte con lo stesso timestamp, così diventa relativamente più anziana e non subisce abort indefiniti.

---

### **3. Domanda 3 - Cardinalità nello schema concettuale**

Indicando con $R$ il numero di occorrenze dell'associazione, i minimi e massimi impongono:

|Confronto|Relazione sempre valida|Motivazione|
|---|---|---|
|$\lvert A\rvert$ rispetto a $\lvert B\rvert$|$\boxed{\lvert A\rvert\leq\lvert B\rvert}$|ogni A partecipa almeno una volta, ogni B esattamente una|
|$\lvert B\rvert$ rispetto a $\lvert C\rvert$|$\boxed{\lvert B\rvert\geq\lvert C\rvert}$|ogni C partecipa almeno una volta, ogni B al più una|
|$\lvert B\rvert$ rispetto a $\lvert D\rvert$|$\boxed{?}$|entrambi i lati ammettono partecipazione nulla|
|$\lvert B\rvert$ rispetto a $\lvert E\rvert$|$\boxed{\lvert B\rvert=\lvert E\rvert}$|associazione uno-a-uno totale su entrambi i lati|
|$\lvert C\rvert$ rispetto a $\lvert F\rvert$|$\boxed{\lvert C\rvert\leq\lvert F\rvert}$|ogni C ha un F, ogni F è associato ad al più un C|

---

### **4. Domanda 4 - Equivalenze di algebra relazionale**

Le chiavi sono `r.A`, `s.D` e `(t.A,t.D)`; `t.A` e `t.D` riferiscono rispettivamente `r.A` e `s.D`.

|Confronto|Esito|Motivazione|
|---|---|---|
|$r\bowtie s\ ?\ r\times s$|Sì|Gli schemi non hanno attributi omonimi: il join naturale è il prodotto cartesiano.|
|$(\pi_A r)\cap(\pi_A t)\ ?\ (\pi_A r)-(\pi_A t)$|No|Il primo è $\pi_A(t)$; il secondo è il suo complemento relativo in $\pi_A(r)$.|
|$(\pi_D s)\cup(\pi_D t)\ ?\ \pi_D s$|Sì|$\pi_D(t)\subseteq\pi_D(s)$ per il riferimento.|
|$\pi_A(r\bowtie t\bowtie s)\ ?\ \pi_A r$|No|Il primo membro è $\pi_A(t)$; non è imposto che ogni tupla di $r$ sia riferita da $t$.|

---

### **5. Esercizio 1 - VSR, CSR, 2PL e timestamp**

Si usa la convenzione naturale della traccia $TS(T_1)<TS(T_2)<TS(T_3)$. Per 2PL “a due stati” ogni accesso richiede un lock esclusivo: le operazioni sullo stesso oggetto di una transazione non possono essere separate da operazioni altrui mentre il lock è detenuto.

|#|VSR|CSR|2PL|TS mono|TS multi|
|---:|:---:|:---:|:---:|:---:|:---:|
|1 `r3 w1 w2 w3`|No|No|No|No|No|
|2 `r1 w3 w1 r2`|No|No|No|No|Sì|
|3 `r3 w1 w3 w2`|Sì|No|No|No|No|
|4 `w1 r3 w2 r2`|Sì|Sì|Sì|No|No|
|5 `r2 w2 w1 w3`|Sì|Sì|Sì|No|No|
|6 `w1 r3 r2 w3`|Sì|Sì|No|Sì|Sì|
|7 `r1 w1 w2 w3`|Sì|Sì|Sì|Sì|Sì|

Ordini seriali per le righe VSR: 3 → $T_3,T_1,T_2$; 4 → $T_1,T_3,T_2$; 5 → $T_2,T_1,T_3$; 6 e 7 → $T_1,T_2,T_3$.

Punti decisivi:

- 1 e 2 hanno vincoli view incompatibili fra lettura iniziale e scrittore finale;
- 3 è VSR ma ha il ciclo di conflitto $T_3\to T_1\to T_3$;
- 6 è CSR, ma il lock di $T_3$ dovrebbe restare fra `r3` e `w3`, bloccando `r2`;
- in 2 il multiversione accetta `r1(x_0)`, crea $x_3$, poi $x_1$, e `r2` legge $x_1$; il mono rifiuta `w1` dopo $w_3$.

---

### **6. Esercizio 2 - Prestiti di libri**

#### **6.1. Media giornaliera per genere**

```sql
WITH PrestitiGiornalieri AS (
  SELECT l.Genere, p.DataPrestito, COUNT(*) AS NumeroPrestiti
  FROM LIBRO AS l
  JOIN PRESTITO AS p ON p.ISBN = l.ISBN
  GROUP BY l.Genere, p.DataPrestito
)
SELECT Genere, AVG(NumeroPrestiti * 1.0) AS MediaGiornaliera
FROM PrestitiGiornalieri
GROUP BY Genere;
```

#### **6.2. Iscritti che non hanno mai ripreso lo stesso libro**

```sql
SELECT i.IdTessera, i.Nome, i.Cognome
FROM ISCRITTOBIB AS i
WHERE NOT EXISTS (
  SELECT 1
  FROM PRESTITO AS p
  WHERE p.IdTessera = i.IdTessera
  GROUP BY p.ISBN
  HAVING COUNT(*) >= 2
);
```

#### **6.3. Iscritti che hanno letto tutti i libri**

$$
Universali:=\pi_{IdTessera,ISBN}(PRESTITO)\div\pi_{ISBN}(LIBRO),
$$

$$
\boxed{ISCRITTOBIB\bowtie Universali}.
$$

---

### **7. Esercizio 3 - Schema E-R Consult**

#### **7.1. Entità e gerarchia**

|Entità|Identificatore|Attributi|
|---|---|---|
|DIPENDENTE|Matricola|Nome, Cognome, DataAssunzione, Telefono, Stipendio|
|TEAM|AliasMail|Tipologia|
|CLIENTE|PartitaIVA|RagioneSociale, Indirizzo composto|
|PROGETTO|`(CLIENTE, Codice)`|NomeCommerciale, DataConsegna, Costo|
|SERVIZIO|`(PROGETTO, Codice)`|Tipo, DataInizio, CostoGiornaliero|
|SLA|IdSLA|Descrizione/parametri dell'accordo|

`DIPENDENTE` ha una specializzazione **totale e sovrapposta** in `SVILUPPATORE`, con `Linguaggio $(1,N)$`, `TEAM_LEADER` e `SERVICE_MANAGER`, con `ModelloAuto`. La sovrapposizione rappresenta lo sviluppatore che assume anche uno dei ruoli gestionali.

#### **7.2. Associazioni**

|Associazione|Cardinalità|
|---|---|
|RIFERISCE_A|TEAM_LEADER $(1,1)$ - SERVICE_MANAGER $(0,N)$|
|DIRIGE|TEAM_LEADER $(1,1)$ - TEAM $(1,1)$|
|ASSEGNATO|SVILUPPATORE $(0,1)$ - TEAM $(1,N)$|
|COMMISSIONA|CLIENTE $(0,N)$ - PROGETTO $(1,1)$, identificante|
|LAVORA_SU|TEAM $(0,N)$ - PROGETTO $(1,N)$|
|CORREDA|PROGETTO $(0,N)$ - SERVIZIO $(1,1)$, identificante|
|RISPETTA|SERVIZIO $(1,N)$ - SLA $(0,N)$|

Gli identificatori di progetto e servizio sono esterni perché i rispettivi codici sono univoci solo entro cliente e progetto. `IdSLA` è introdotto perché la traccia non fornisce una chiave.

> ✅ La relazione uno-a-uno `DIRIGE` esprime anche “un team leader dirige un solo team e viceversa”; l'appartenenza multipla ai ruoli è invece demandata alla gerarchia sovrapposta.
