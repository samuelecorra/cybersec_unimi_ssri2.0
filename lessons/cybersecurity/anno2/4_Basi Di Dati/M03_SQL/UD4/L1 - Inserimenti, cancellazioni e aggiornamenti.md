# **M3 UD4 Lezione 1 - Inserimenti, cancellazioni e aggiornamenti**

### **1. Introduzione**

Questa lezione presenta i **comandi di modifica dei dati** (DML – _Data Manipulation Language_) che permettono di operare direttamente sui contenuti delle tabelle.  
Le istruzioni principali sono tre:

- `INSERT` → per l’inserimento di nuove tuple,
    
- `DELETE` → per la cancellazione di tuple esistenti,
    
- `UPDATE` → per la modifica dei valori negli attributi.

Tutte queste operazioni agiscono su **insiemi di tuple**, non su singoli record isolati, e devono sempre rispettare i **vincoli di integrità** definiti nel database.

---

### **2. Inserimento (`INSERT`)**

#### **Sintassi generale**

```sql
INSERT INTO NomeTabella [(ListaAttributi)]
    { VALUES (ListaDiValori) | SelectSQL };
```

- **ListaAttributi**: indica gli attributi per cui vengono forniti i valori.
    
    - Se omessa, vengono considerati **tutti gli attributi** della tabella.
        
    - Gli attributi non specificati assumono valore `NULL`.
    
- **ListaDiValori**: valori letterali da inserire.
    
- **SelectSQL**: consente di inserire i risultati di un’altra interrogazione (inserimento _da select_).
    
- La corrispondenza tra attributi e valori è **posizionale**.

---

#### **Esempi di inserimento**

Tabelle di riferimento:

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- STUDENTIESTERNI(Codice, Cognome, Nome)

**Esempio 1 – Inserimento esplicito di valori**

```sql
INSERT INTO Studenti
VALUES ('123456', 'Rossi', 'Mario', 'SSRI');
```

**Esempio 2 – Inserimento da altra tabella**

```sql
INSERT INTO Studenti(Matr, Cognome, Nome)
SELECT *
FROM StudentiEsterni;
```

---

### **3. Cancellazione (`DELETE`)**

#### **Sintassi generale**

```sql
DELETE FROM NomeTabella [WHERE Condizione];
```

- **Condizione:** specifica quali tuple devono essere eliminate.
    
    - Se omessa, **tutte le tuple vengono cancellate** → la tabella rimane vuota, ma **non viene eliminata** (diverso da `DROP`).
    
- Se sono presenti **vincoli di integrità referenziale** con politica `CASCADE`, la cancellazione può propagarsi ad altre tabelle.

---

#### **Esempi di cancellazione**

Tabelle di riferimento:

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- ESAMI(Matr, Cod_corso, Data, Voto)

**Esempio 1 – Cancellazione di un singolo studente**

```sql
DELETE FROM Studenti
WHERE Matr = '123456';
```

**Esempio 2 – Cancellazione condizionata su altra tabella**

```sql
DELETE FROM Studenti
WHERE Matr NOT IN (SELECT Matr FROM Esami);
```

> ✅ Questo comando elimina gli studenti che **non hanno sostenuto alcun esame**.

---

### **4. Aggiornamento (`UPDATE`)**

#### **Sintassi generale**

```sql
UPDATE NomeTabella
SET Attributo = <Espressione | SelectSQL | NULL | DEFAULT>
{ , Attributo = <Espressione | SelectSQL | NULL | DEFAULT> }
[WHERE Condizione];
```

- **Condizione:** come nelle interrogazioni `SELECT`.
    
    - Se omessa, **tutte le tuple vengono aggiornate**.
    
- È possibile modificare uno o più attributi contemporaneamente.
    
- L’assegnazione può utilizzare:
    
    - un valore costante,
        
    - il risultato di una sottointerrogazione,
        
    - `NULL`,
        
    - `DEFAULT`,
        
    - oppure un’espressione aritmetica o logica.

---

### **5. Esempi di aggiornamento**

#### **Esempio 1 – Incremento dei voti**

Tabella: ESAMI(Matr, Cod_corso, Data, Voto)

**Obiettivo:** aumentare di un punto (se possibile) il voto di tutti gli studenti che hanno sostenuto più di tre esami.

```sql
UPDATE Esami
SET Voto = Voto + 1
WHERE Voto < 30
  AND Matr IN (
      SELECT Matr
      FROM Esami
      GROUP BY Matr
      HAVING COUNT(*) > 3
  );
```

> ✅ Aumenta il voto solo per studenti con più di tre esami e senza superare 30.

---

#### **Esempio 2 – Aggiornamento condizionale con `CASE`**

**Obiettivo:** aumentare di un punto i voti di “Basidati” (codice ‘BD’) e diminuire di un punto quelli di “Sistemi” (codice ‘SO’).

```sql
UPDATE Esami
SET Voto =
    CASE
        WHEN (Cod_corso = 'BD' AND Voto < 30) THEN Voto + 1
        WHEN (Cod_corso = 'SO' AND Voto > 18) THEN Voto - 1
        ELSE Voto
    END;
```

> ✅ SQL consente di applicare modifiche differenziate tramite condizioni multiple.

---

### **6. In sintesi**

Abbiamo visto i tre comandi fondamentali del **DML di modifica**:

|Operazione|Comando SQL|Descrizione|
|:--|:--|:--|
|Inserimento|`INSERT`|Inserisce nuove tuple in una tabella.|
|Cancellazione|`DELETE`|Elimina tuple in base a una condizione.|
|Aggiornamento|`UPDATE`|Modifica i valori di uno o più attributi.|

Tutte le istruzioni operano su **insiemi di tuple**, e il DBMS si occupa di mantenere la **coerenza e l’integrità referenziale** dei dati durante le operazioni.

---

![](imgs/Pasted%20image%2020251125045432.png)

![](imgs/Pasted%20image%2020251125045441.png)

![](imgs/Pasted%20image%2020251125045449.png)

![](imgs/Pasted%20image%2020251125045501.png)

