# **M3 UD3 Lezione 2 - Interrogazioni insiemistiche e nidificate**

### **1. Introduzione**

Le **interrogazioni complesse** in SQL non si limitano a operazioni di ordinamento o aggregazione:  
possono anche **combinare risultati** provenienti da più query distinte (_interrogazioni insiemistiche_) oppure **includere query all’interno di altre query** (_interrogazioni nidificate_).

Queste due categorie permettono di risolvere problemi che richiedono un confronto tra insiemi di risultati o una selezione basata su condizioni dipendenti da altre interrogazioni.

---

### **2. Interrogazioni insiemistiche**

Le **operazioni insiemistiche** permettono di unire, confrontare o differenziare i risultati di due interrogazioni SQL.

#### **Sintassi generale**

```sql
<SelectSQL>
{ UNION | INTERSECT | EXCEPT } [ALL]
<SelectSQL>;
```

- `UNION` → unione dei risultati.
    
- `INTERSECT` → intersezione dei risultati.
    
- `EXCEPT` → differenza tra i risultati.
    
- `ALL` → mantiene i duplicati (per default vengono eliminati).

#### **Condizioni di compatibilità**

- Gli **operandi** devono avere **lo stesso numero di attributi**.
    
- Gli attributi corrispondenti devono avere **domini compatibili**.
    
- La corrispondenza è determinata **in base alla posizione**, non al nome.
    
- Il risultato eredita i **nomi degli attributi** dal primo operando.

---

### **3. Esempi di interrogazioni insiemistiche**

#### **Esempio 1 – Differenza (EXCEPT)**

Tabelle:

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- ESAMI(Matr, Cod_corso, Data, Voto)

**Obiettivo:** trovare le matricole degli studenti iscritti a _SSRIonline_ che **non hanno sostenuto alcun esame**.

```sql
SELECT Matr
FROM Studenti
WHERE CdL = 'SSRIonline'
EXCEPT
SELECT Matr
FROM Esami;
```

---

#### **Esempio 2 – Intersezione (INTERSECT)**

Tabelle:

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- ESAMI(Matr, Cod_corso, Data, Voto)
    
- CORSI(Codice, Titolo, Docente)

**Obiettivo:** trovare le matricole degli studenti iscritti a _SSRIonline_ che **hanno sostenuto l’esame di “Basidati”**.

```sql
SELECT Matr
FROM Studenti
WHERE CdL = 'SSRIonline'
INTERSECT
SELECT Matr
FROM Esami JOIN Corsi ON Cod_corso = Codice
WHERE Titolo = 'Basidati';
```

---

### **4. Interrogazioni nidificate**

Una **query nidificata** (o _subquery_) è un’interrogazione **contenuta all’interno di un’altra query**, tipicamente nella clausola `WHERE`.

#### **Esempi di strutture di nidificazione**

1. Confronto di un attributo con il risultato di una query:
    
    ```sql
    ValScalare <Operatore [ANY | ALL]> (SelectSQL)
    ```
    
2. Confronto tramite appartenenza a un insieme:
    
    ```sql
    ValScalare [NOT] IN (SelectSQL)
    ```
    
3. Uso del **quantificatore esistenziale**:
    
    ```sql
    [NOT] EXISTS (SelectSQL)
    ```

#### **Operatore e quantificatori**

|Operatore|Significato|
|:--|:--|
|`ANY`|la condizione è vera se **almeno una** tupla soddisfa il confronto|
|`ALL`|la condizione è vera se **tutte** le tuple soddisfano il confronto|
|`IN`|equivalente a `= ANY`|
|`NOT IN`|equivalente a `<> ALL`|

---

### **5. Esempi di interrogazioni nidificate**

#### **Esempio 1 – Studenti con almeno un 28 o 30**

```sql
SELECT Cognome, Nome
FROM Studenti
WHERE Matr = ANY (
    SELECT Matr
    FROM Esami
    WHERE Voto = 28 OR Voto = 30
);
```

`= ANY` può essere sostituito con `IN`.

---

#### **Esempio 2 – Studenti che non hanno mai preso 30**

```sql
SELECT Cognome, Nome
FROM Studenti
WHERE Matr <> ALL (
    SELECT Matr
    FROM Esami
    WHERE Voto = 30
);
```

`<> ALL` può essere scritto come `NOT IN`.

---

#### **Esempio 3 – Studenti che hanno fatto almeno un esame con il docente “Rossi”**

```sql
SELECT Cognome, Nome
FROM Studenti
WHERE Matr IN (
    SELECT Matr
    FROM Esami
    WHERE Cod_corso IN (
        SELECT Codice
        FROM Corsi
        WHERE Docente = 'Rossi'
    )
);
```

---

#### **Esempio 4 – Studenti con omonimi**

```sql
SELECT *
FROM Studenti AS S1
WHERE (Nome, Cognome) IN (
    SELECT Nome, Cognome
    FROM Studenti AS S2
    WHERE S1.Matr <> S2.Matr
);
```

Per trovare chi **non** ha omonimi basta sostituire `IN` con `NOT IN`.

---

#### **Esempio 5 – Studenti con il voto più alto**

```sql
SELECT Matr
FROM Esami
WHERE Voto = (SELECT MAX(Voto) FROM Esami);
```

Equivalente a:

```sql
SELECT Matr
FROM Esami
WHERE Voto >= ALL (SELECT Voto FROM Esami);
```

---

#### **Esempio 6 – Studenti che hanno fatto più di un esame nello stesso giorno**

```sql
SELECT Matr
FROM Esami AS E1
WHERE EXISTS (
    SELECT *
    FROM Esami AS E2
    WHERE E1.Matr = E2.Matr
      AND E1.Data = E2.Data
      AND E1.Cod_corso <> E2.Cod_corso
);
```

---

#### **Esempio 7 – Alternativa con EXISTS**

```sql
SELECT *
FROM Studenti AS S1
WHERE EXISTS (
    SELECT *
    FROM Studenti AS S2
    WHERE S1.Cognome = S2.Cognome
      AND S1.Nome = S2.Nome
      AND S1.Matr <> S2.Matr
);
```

La versione **negata** (`NOT EXISTS`) trova gli studenti **senza omonimi**.

---

### **6. Limiti delle interrogazioni nidificate**

- Le subquery **non possono contenere operatori insiemistici** (`UNION`, `INTERSECT`, `EXCEPT`).  
    Questi possono essere usati solo a livello esterno.
    
- Le **regole di visibilità delle variabili** stabiliscono che:
    
    - una variabile è visibile **solo nella query in cui è definita** e nelle sue nidificazioni;
        
    - se il nome è omesso, si assume il riferimento alla variabile più vicina.

#### **Esempio scorretto**

```sql
SELECT *
FROM Studenti
WHERE Matr IN (
        SELECT Matr
        FROM Esami AS E1
        WHERE Voto = 28
    )
OR Matr IN (
        SELECT Matr
        FROM Esami AS E2
        WHERE E2.Matr = E1.Matr
    );
```

> ❌ Errore: `E1` non è visibile al di fuori della prima subquery.

---

### **7. In sintesi**

In questa lezione abbiamo studiato:

- Le **operazioni insiemistiche** (`UNION`, `INTERSECT`, `EXCEPT`) e le loro condizioni di compatibilità.
    
- Le **interrogazioni nidificate**, con l’uso di operatori (`IN`, `ANY`, `ALL`, `EXISTS`, `NOT EXISTS`).
    
- Le regole di **visibilità delle variabili** e i limiti strutturali delle subquery.

Questi costrutti permettono di costruire interrogazioni **multilivello e articolate**, combinando i risultati di più ricerche in modo logico e preciso.

![](imgs/Pasted%20image%2020251125044357.png)

![](imgs/Pasted%20image%2020251125044411.png)

![](imgs/Pasted%20image%2020251125044420.png)

![](imgs/Pasted%20image%2020251125044427.png)

