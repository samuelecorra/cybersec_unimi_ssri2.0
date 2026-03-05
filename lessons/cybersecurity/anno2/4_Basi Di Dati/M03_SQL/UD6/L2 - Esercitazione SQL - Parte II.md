# **M3 UD6 Lezione 1 - Esercitazione SQL - Parte II**

### **1. Schemi di riferimento**

- **RADIO(Codice, Nome, Frequenza, Luogo)**
    
- **PROGRAMMA(Codice, Nome, Conduttore, FasciaOraria, Durata, Tipo, CodiceRadio)**

---

### **2. Interrogazione 1 — Radio con ≤ 8 ore musicali (e almeno un programma musicale)**

```sql
SELECT CodiceRadio
FROM Programma
WHERE Tipo = 'musicale'
GROUP BY CodiceRadio
HAVING SUM(Durata) <= 8;
```

_Nota:_ il `WHERE Tipo = 'musicale'` garantisce l’esistenza di almeno un programma musicale.

---

### **3. Interrogazione 2 — Radio sole nel proprio luogo**

**Versione con `NOT IN`:**

```sql
SELECT R1.Nome, R1.Frequenza
FROM Radio AS R1
WHERE R1.Luogo NOT IN (
    SELECT R2.Luogo
    FROM Radio AS R2
    WHERE R1.Codice <> R2.Codice
);
```

**Versione equivalente con `NOT EXISTS`:**

```sql
SELECT R1.Nome, R1.Frequenza
FROM Radio AS R1
WHERE NOT EXISTS (
    SELECT *
    FROM Radio AS R2
    WHERE R1.Luogo = R2.Luogo
      AND R1.Codice <> R2.Codice
);
```

---

### **4. Interrogazione 3 — Conduttori solo musicali**

**(a) Con operatori insiemistici:**

```sql
SELECT DISTINCT Conduttore
FROM Programma
WHERE Tipo = 'musicale'
EXCEPT
SELECT Conduttore
FROM Programma
WHERE Tipo <> 'musicale';
```

**(b) Con `NOT IN`:**

```sql
SELECT DISTINCT Conduttore
FROM Programma
WHERE Tipo = 'musicale'
  AND Conduttore NOT IN (
      SELECT Conduttore
      FROM Programma
      WHERE Tipo <> 'musicale'
  );
```

**(c) Con `NOT EXISTS` correlata:**

```sql
SELECT DISTINCT P1.Conduttore
FROM Programma AS P1
WHERE P1.Tipo = 'musicale'
  AND NOT EXISTS (
      SELECT *
      FROM Programma AS P2
      WHERE P2.Conduttore = P1.Conduttore
        AND P2.Tipo <> 'musicale'
  );
```

---

### **5. Interrogazione 4 — Radio con il maggior numero di programmi**

```sql
CREATE VIEW NumProgrammi(NomeRadio, NumProg) AS
SELECT R.Nome, COUNT(*)
FROM Radio AS R
JOIN Programma AS P ON R.Codice = P.CodiceRadio
GROUP BY P.CodiceRadio, R.Nome;

SELECT NomeRadio
FROM NumProgrammi
WHERE NumProg = (SELECT MAX(NumProg) FROM NumProgrammi);
```

