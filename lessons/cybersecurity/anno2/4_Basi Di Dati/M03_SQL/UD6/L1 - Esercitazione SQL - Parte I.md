# **M3 UD6 Lezione 1 - Esercitazione SQL - Parte I**

### **1. Schemi di riferimento**

- **CELLULARE(Codice, CFUtente, Modello, Marca, Colore)**
    
- **ABBONAMENTO(Numero, CFUtente, Operatore, Tariffa)**
    
- **UTENTE(CF, Nome, Cognome, Città)**

---

### **2. Interrogazione 1 – Tariffa minima Vodafone**

```sql
SELECT MIN(Tariffa)
FROM Abbonamento
WHERE Operatore = 'Vodafone';
```

---

### **3. Interrogazione 2 – Utenti con Nokia e abbonamento Tim**

**Stile SQL-89 (virgole + WHERE):**

```sql
SELECT Nome, Cognome
FROM Utente, Abbonamento AS A, Cellulare AS C
WHERE CF = A.CFUtente
  AND CF = C.CFUtente
  AND Operatore = 'Tim'
  AND Marca = 'Nokia';
```

**Stile SQL-92 (JOIN espliciti):**

```sql
SELECT Nome, Cognome
FROM Utente
JOIN Abbonamento AS A ON CF = A.CFUtente
JOIN Cellulare   AS C ON CF = C.CFUtente
WHERE Operatore = 'Tim'
  AND Marca = 'Nokia';
```

---

### **4. Interrogazione 3 – Utenti con più di tre numeri**

```sql
SELECT CF, Cognome, Nome
FROM Utente
JOIN Abbonamento ON CF = CFUtente
GROUP BY CF, Cognome, Nome
HAVING COUNT(Numero) > 3;
```

---

### **5. Interrogazione 4 – Per operatore: #abbonamenti e #persone servite**

```sql
SELECT Operatore,
       COUNT(Numero)            AS NumAbbonamenti,
       COUNT(DISTINCT CFUtente) AS NumPersoneServite
FROM Abbonamento
GROUP BY Operatore;
```

---

### **6. Interrogazione 5 – Operatori con esclusiva in almeno una città**

**Versione con `NOT EXISTS`:**

```sql
SELECT DISTINCT A1.Operatore
FROM Abbonamento AS A1
JOIN Utente AS U1 ON A1.CFUtente = U1.CF
WHERE NOT EXISTS (
    SELECT *
    FROM Abbonamento AS A2
    JOIN Utente AS U2 ON A2.CFUtente = U2.CF
    WHERE U1.Città = U2.Città
      AND A1.Operatore <> A2.Operatore
);
```

**Versione equivalente con `NOT IN`:**

```sql
SELECT DISTINCT A1.Operatore
FROM Abbonamento AS A1
JOIN Utente AS U1 ON A1.CFUtente = U1.CF
WHERE U1.Città NOT IN (
    SELECT U2.Città
    FROM Abbonamento AS A2
    JOIN Utente AS U2 ON A2.CFUtente = U2.CF
    WHERE A1.Operatore <> A2.Operatore
);
```

