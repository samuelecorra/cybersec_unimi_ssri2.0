# **M3 UD3 Lezione 1 - Ordinamenti, aggregazioni e raggruppamenti**

### **1. Introduzione**

Questa lezione introduce gli strumenti che permettono di costruire **interrogazioni complesse** in SQL:

- **ordinamento** dei risultati,
    
- **aggregazioni** su insiemi di tuple,
    
- **raggruppamento** dei dati in base a uno o più attributi.

Tali costrutti ampliano notevolmente la capacità di analisi dei dati, permettendo di ottenere statistiche, riepiloghi e classificazioni all’interno delle tabelle.

---

### **2. Ordinamento dei risultati**

La clausola `ORDER BY` permette di ordinare le righe del risultato secondo uno o più attributi:

```sql
ORDER BY Attributo [ASC | DESC]
{, Attributo [ASC | DESC]}
```

- `ASC` → ordine crescente (default).
    
- `DESC` → ordine decrescente.

#### **Esempio**

Tabelle:

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- ESAMI(Matr, Cod_corso, Data, Voto)

**Query:** elencare gli studenti di _SSRIonline_ che hanno sostenuto esami, ordinando per matricola crescente e, per ciascuno, per voto decrescente.

```sql
SELECT S.Matr, Cognome, Nome, Cod_corso, Voto
FROM Studenti AS S JOIN Esami AS E
ON S.Matr = E.Matr
WHERE CdL = 'SSRIonline'
ORDER BY S.Matr, Voto DESC;
```

---

### **3. Funzioni aggregate**

Le funzioni di **aggregazione** operano su insiemi di tuple e restituiscono un singolo valore.

SQL-2 offre cinque operatori aggregati fondamentali:

|Funzione|Descrizione|
|:--|:--|
|`COUNT`|Conta le righe o i valori di un attributo|
|`SUM`|Calcola la somma|
|`MAX`|Restituisce il valore massimo|
|`MIN`|Restituisce il valore minimo|
|`AVG`|Calcola la media|

#### **Sintassi generale**

```sql
<funzione> ([DISTINCT | ALL] Espressione)
```

- `DISTINCT`: considera solo i valori diversi (utile per `SUM` e `AVG`).
    
- `ALL`: considera tutti i valori non nulli (default).

---

### **4. COUNT**

```sql
COUNT ( * | [DISTINCT | ALL] ListaAttributi )
```

- `COUNT(*)` → restituisce il numero totale di righe.
    
- `COUNT(DISTINCT Attr)` → conta i valori distinti dell’attributo.
    
- `COUNT(ALL Attr)` → conta tutti i valori non nulli.

#### **Esempi**

```sql
-- Numero di studenti iscritti a SSRIonline
SELECT COUNT(*)
FROM Studenti
WHERE CdL = 'SSRIonline';
```

```sql
-- Numero di studenti che hanno fatto almeno un esame
SELECT COUNT(DISTINCT Matr)
FROM Studenti JOIN Esami ON Studenti.Matr = Esami.Matr;
```

---

### **5. SUM, MAX, MIN, AVG**

```sql
<sum | max | min | avg> ([DISTINCT | ALL] Attr)
```

#### **Esempi**

```sql
-- Voto più alto e più basso dell’esame “BasiDati” (codice BD)
SELECT MAX(Voto) AS Alto, MIN(Voto) AS Basso
FROM Esami
WHERE Cod_corso = 'BD';
```

```sql
-- Media dei voti della matricola 123456
SELECT AVG(Voto) AS MediaVoti
FROM Esami
WHERE Matr = '123456';
```

> ⚠️ **Errore comune:**  
> la `SELECT` deve contenere solo attributi compatibili con l’aggregazione.  
> Ad esempio:
> 
> ```sql
> SELECT Matr, MAX(Voto)
> FROM Esami
> WHERE Cod_corso = 'BD';
> ```
> 
> è una query scorretta, perché mescola un attributo non aggregato con una funzione aggregata senza raggruppamento.

---

### **6. Raggruppamento dei dati**

Il **raggruppamento** consente di applicare le funzioni di aggregazione a **sottoinsiemi di tuple** definite da uno o più attributi.

Clausole principali:

- `GROUP BY` → definisce i criteri di raggruppamento.
    
- `HAVING` → filtra i gruppi in base a condizioni che coinvolgono funzioni aggregate.

#### **Sintassi generale**

```sql
SELECT ListaAttributi, FunzioniAggregate
FROM Tabella
[WHERE Condizione]
GROUP BY ListaAttributi
[HAVING CondizioneAggregata];
```

---

### **7. Esempi di raggruppamento**

#### **Esempio 1 – Media voti per studente nel 2005**

```sql
SELECT Matr, AVG(Voto) AS MediaVoti
FROM Esami
WHERE Data BETWEEN '2005-01-01' AND '2005-12-31'
GROUP BY Matr;
```

#### **Esempio 2 – Studenti con almeno tre esami**

```sql
SELECT Matr, AVG(Voto) AS MediaVoti
FROM Esami
GROUP BY Matr
HAVING COUNT(*) >= 3;
```

---

### **8. Regole di correttezza per GROUP BY**

In una query che utilizza `GROUP BY`, la lista degli attributi nella `SELECT` deve contenere **solo**:

- attributi presenti nella clausola `GROUP BY`;
    
- oppure **funzioni aggregate** sugli altri attributi.

#### **Esempio scorretto**

```sql
SELECT Cod_corso, Data, AVG(Voto)
FROM Esami
GROUP BY Cod_corso;
```

#### **Esempio corretto**

```sql
SELECT Cod_corso, AVG(Voto)
FROM Esami
GROUP BY Cod_corso;
```

---

### **9. Esempio con JOIN e raggruppamento**

```sql
SELECT S.Matr, Cognome, Nome, AVG(Voto) AS MediaVoti
FROM Esami AS E JOIN Studenti AS S
ON S.Matr = E.Matr
GROUP BY S.Matr, Cognome, Nome;
```

> Tutti gli attributi non aggregati (qui `S.Matr`, `Cognome`, `Nome`) devono comparire nel `GROUP BY`.

---

### **10. Clausole WHERE e HAVING**

- `WHERE` filtra **le tuple** prima del raggruppamento.
    
- `HAVING` filtra **i gruppi** dopo l’aggregazione.  
    Solo le condizioni che coinvolgono **funzioni aggregate** devono comparire in `HAVING`.

#### **Esempio**

```sql
SELECT Cod_corso
FROM Esami
WHERE Data BETWEEN '2005-01-01' AND '2005-12-31'
GROUP BY Cod_corso
HAVING AVG(Voto) > 25;
```

---

### **11. Raggruppamento e ordinamento combinati**

```sql
SELECT Cod_corso, AVG(Voto)
FROM Esami
GROUP BY Cod_corso
HAVING COUNT(*) > 3
ORDER BY AVG(Voto) DESC;
```

Questa query restituisce:

- i corsi con più di 3 esami,
    
- la media dei voti per ciascuno,
    
- in ordine decrescente di media.

---

### **12. In sintesi**

Abbiamo visto come:

- ordinare i risultati con `ORDER BY`;
    
- utilizzare le **funzioni di aggregazione** (`COUNT`, `SUM`, `MAX`, `MIN`, `AVG`);
    
- applicare il **raggruppamento** (`GROUP BY`) e filtrare i risultati aggregati con `HAVING`.

Ricorda: in una query con `GROUP BY`, la **lista della SELECT** deve contenere solo attributi aggregati o presenti nel gruppo, e le condizioni sulle aggregazioni vanno espresse in `HAVING`, non in `WHERE`.

---

![](imgs/Pasted%20image%2020251125044313.png)

![](imgs/Pasted%20image%2020251125044324.png)

![](imgs/Pasted%20image%2020251125044331.png)

![](imgs/Pasted%20image%2020251125044340.png)

