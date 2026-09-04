# **Basi di Dati - Soluzione completa dell'appello del 14 gennaio 2025**

**Docente:** Pierangela Samarati · **Tempo:** 2 ore e 30 minuti

Fonte originale: [testo completo dell'appello](BD-2025_01_14.pdf)

---

### **1. Domanda 1 - Integrità referenziale e autorizzazioni**

**Riferimenti:** [vincoli relazionali](<../../M02_Modello_e_Algebra_Relazionali/UD1/L3 - Vincoli di integrità.md>) · [vincoli SQL](<../../M03_SQL/UD1/L2 - Schemi, Tabelle e Vincoli.md>) · [privilegio REFERENCES](<../../M03_SQL/UD5/L3 - Autorizzazioni e controllo dell’accesso.md>)

Un vincolo di integrità referenziale

$$
R[X]\subseteq S[K]
$$

impone che ogni valore non nullo della chiave esterna `R.X` compaia nella chiave candidata `S.K`. Il DBMS controlla quindi:

- `INSERT` o modifica di `R.X`: il nuovo riferimento deve esistere in `S`;
- cancellazione o modifica di `S.K`: nessuna tupla di `R` deve restare orfana.

Per `ON DELETE` e `ON UPDATE`, SQL offre `NO ACTION`/`RESTRICT` (rifiuto), `CASCADE` (propagazione), `SET NULL` e `SET DEFAULT`; le ultime due richiedono che colonna, nullabilità e default lo consentano.

Il vincolo può essere dichiarato dal proprietario della tabella referente o da chi possiede il privilegio `REFERENCES` sulle colonne riferite. Il controllo è necessario perché una foreign key altrui limita aggiornamenti e cancellazioni sulla tabella riferita e può quindi interferire con l'autonomia del proprietario.

---

### **2. Domanda 2 - Prevenzione dei deadlock**

**Riferimento:** [M6/UD3/L8 - Deadlock](<../../M06_Gestione_Transazioni/UD3/L8 - Deadlock.md>)

Sia $T_i$ la richiedente e $T_j$ la detentrice del lock, con timestamp minore per la transazione più anziana.

|Politica|Se $TS_i<TS_j$|Se $TS_i>TS_j$|
|---|---|---|
|non interrompente, **wait-die**|$T_i$ attende|$T_i$ viene abortita|
|interrompente, **wound-wait**|$T_i$ abortisce $T_j$|$T_i$ attende|

Le attese hanno sempre la stessa direzione rispetto all'età, quindi non possono formare cicli. Una transazione abortita riparte con il **timestamp originario**: acquisisce progressivamente anzianità relativa e non rischia starvation.

---

### **3. Domanda 3 - Cardinalità relazionali**

Le chiavi sono $(A,B)$ per $r$, `A` per $s$ e `B` per $t`. I riferimenti implicano

$$
\pi_A(r)\subseteq\pi_A(s),\qquad \pi_B(r)\subseteq\pi_B(t).
$$

**Riferimento:** [metodo per le cardinalità](<../Approfondimenti_per_Esame/L1 - Cardinalita minima e massima di espressioni relazionali.md>)

|Espressione|Minimo|Massimo|Motivazione|
|---|---:|---:|---|
|$\pi_A(r)\bowtie\pi_A(s)$|$1$|$\min(|r|,|s|)$|Il join coincide con $\pi_A(r)$; $r$ è non vuota e la chiave composta consente ripetizioni di `A`.| 
|$\sigma_{A=1\lor A=5}(s)$|$0$|$2$|`s.A` è chiave: al più una tupla per ciascuno dei due valori.| 
|$\pi_{A,B}(r)-(\pi_A(s)\times\pi_B(t))$|$0$|$0$|Ogni coppia `(A,B)` di $r$ compare nel prodotto per i due vincoli referenziali.| 

---

### **4. Esercizio 1 - Ripresa a caldo**

**Riferimento:** [M6/UD2/L3 - Gestione dei guasti](<../../M06_Gestione_Transazioni/UD2/L3 - Gestione dei guasti.md>)

Al checkpoint sono attive

$$
\boxed{CK(T_2,T_4)}:
$$

$T_1$ ha già committato e $T_3$ ha già abortito. Scorrendo dal checkpoint al guasto:

- $T_4$ e $T_5$ terminano con abort;
- $T_2$ termina con commit;
- $T_6$ è ancora attiva.

Pertanto

$$
\boxed{UNDO=\{T_6\},\qquad REDO=\{T_2\}}.
$$

Si percorre anzitutto il log all'indietro e si annullano, in ordine inverso, le azioni di $T_6$:

1. `I(T6,O11,A11)`: eliminare `O11`;
2. `D(T6,O9,B9)`: reinserire `O9` con valore `B9`.

Poi si percorre in avanti dalla prima azione rilevante e si ripetono le azioni di $T_2$:

1. `D(T2,O2,B2)`: eliminare `O2`;
2. `I(T2,O5,A5)`: inserire `O5 := A5`;
3. `U(T2,O10,B10,A10)`: porre `O10 := A10`.

Gli effetti di $T_1$ sono coperti dal checkpoint; quelli di $T_3,T_4,T_5$ erano già stati annullati prima del guasto.

---

### **5. Esercizio 2 - VSR, CSR e correzione dello schedule**

Per

$$
w_1(x)\ r_1(x)\ w_2(y)\ r_3(x)\ r_2(y)\ w_3(x)\ r_3(t)\ w_2(x)\ w_1(t)\ r_2(t)
$$

`r3(x)` legge da $T_1$, quindi ogni serializzazione view-equivalente dovrebbe imporre $T_1<T_3$. `r3(t)` legge invece il valore iniziale, mentre $T_1$ scrive `t`, e impone $T_3<T_1$. Il ciclo rende lo schedule **non VSR** e dunque anche **non CSR**.

Rimuovendo `r3(t)` si elimina il vincolo contrario. Il grafo dei conflitti rimasto contiene

$$
T_1\to T_3,\qquad T_1\to T_2,\qquad T_3\to T_2,
$$

quindi è aciclico e ammette l'unico ordine seriale

$$
\boxed{T_1,T_3,T_2}.
$$

La rimozione rende lo schedule sia CSR sia VSR.

---

### **6. Esercizio 3 - SQL e algebra relazionale**

**Riferimenti:** [aggregazioni SQL](<../../M03_SQL/UD3/L1 - Ordinamenti, aggregazioni e raggruppamenti.md>) · [interrogazioni nidificate](<../../M03_SQL/UD3/L2 - Interrogazioni insiemistiche e nidificate.md>) · [massimo in algebra](<../../M02_Modello_e_Algebra_Relazionali/UD2/L6 - Idiomi di interrogazione – esiste - per ogni, minimo - massimo.md>)

#### **6.1. Gelati con più frutta degli altri ingredienti**

```sql
SELECT g.Id, g.Gusto
FROM GELATO AS g
JOIN RICETTA AS r ON r.IdGelato = g.Id
JOIN INGREDIENTE AS i ON i.Id = r.IdIngrediente
GROUP BY g.Id, g.Gusto
HAVING SUM(CASE WHEN i.Tipo = 'frutta' THEN r.Quantità ELSE 0 END)
     > SUM(CASE WHEN i.Tipo = 'frutta' THEN 0 ELSE r.Quantità END);
```

#### **6.2. Gelati senza latticini**

```sql
SELECT DISTINCT g.Gusto
FROM GELATO AS g
WHERE NOT EXISTS (
  SELECT 1
  FROM RICETTA AS r
  JOIN INGREDIENTE AS i ON i.Id = r.IdIngrediente
  WHERE r.IdGelato = g.Id
    AND i.Tipo = 'latticini'
);
```

`DISTINCT` è necessario perché la chiave dichiarata è `Id`, non `Gusto`: due modelli diversi potrebbero condividere lo stesso gusto.

#### **6.3. Ingrediente in quantità massima per gelato**

Poste $R_1:=\rho_{G_1,I_1,Q_1}(RICETTA)$ e $R_2:=\rho_{G_2,I_2,Q_2}(RICETTA)$:

$$
Dominati:=\pi_{G_1,I_1}
\left(\sigma_{G_1=G_2\land Q_1<Q_2}(R_1\times R_2)\right),
$$

$$
\boxed{\rho_{G_1,I_1}(\pi_{IdGelato,IdIngrediente}(RICETTA))-Dominati}.
$$

La disuguaglianza stretta conserva tutti gli ex aequo al massimo.

---

### **7. Esercizio 4 - Schema E-R FREDDOPOLARE**

**Riferimenti:** [entità e associazioni](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L1 - Modello E-R - Entità, relazioni e attributi.md>) · [cardinalità](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L2 - Modello E-R - Le cardinalità.md>) · [identificatori e gerarchie](<../../M04_Progettazione_di_Basi_di_Dati/UD2/L3 - Modello E-R - Identificatori e gerarchie.md>)

#### **7.1. Entità e gerarchie**

|Entità|Identificatore|Attributi|
|---|---|---|
|PRODUTTORE|PartitaIVA|RagioneSociale; Telefono $(1,N)$|
|CONDIZIONATORE|`(PRODUTTORE, Codice)`|NomeCommerciale, PompaCalore, Prezzo|
|FILTRO|`(Funzionalità, Lunghezza, Larghezza)`|le tre caratteristiche note|
|MOTORE|CodiceCatalogo|RumoreDb, Potenza, Prezzo|
|MANUTENZIONE|Tipo|Durata, Prezzo|

`PRODUTTORE` è specializzato in modo totale ed esclusivo in `PRODUTTORE_PARETE` e `PRODUTTORE_SOFFITTO`. `CONDIZIONATORE` è a sua volta specializzato, totalmente ed esclusivamente, in:

- `PARETE`, con `Incasso`;
- `SOFFITTO`, con `TipoSoffitto` e `LatoCm`.

#### **7.2. Associazioni**

|Associazione|Cardinalità|Attributi|
|---|---|---|
|PRODUCE|PRODUTTORE $(1,N)$ - CONDIZIONATORE $(1,1)$|—|
|MONTA|CONDIZIONATORE $(1,N)$ - FILTRO $(1,N)$|—|
|ABBINATO_A|CONDIZIONATORE $(1,1)$ - MOTORE $(1,N)$|—|
|RICHIEDE|MOTORE $(1,N)$ - MANUTENZIONE $(1,N)$|Periodicità|

`CONDIZIONATORE` ha identificazione esterna perché il codice è univoco soltanto per produttore. Va inoltre documentato il vincolo di corrispondenza: un produttore di parete produce solo modelli `PARETE`, e analogamente per il soffitto. La periodicità appartiene a `RICHIEDE`, perché varia con la coppia motore-intervento.

> ✅ Lo schema specifica tutte le entità, gli identificatori, gli attributi multivalore, le gerarchie, le cardinalità e i vincoli non grafici richiesti.
