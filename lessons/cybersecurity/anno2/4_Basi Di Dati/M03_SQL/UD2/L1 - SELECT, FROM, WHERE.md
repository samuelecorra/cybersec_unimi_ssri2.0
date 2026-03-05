# **M3 UD2 Lezione 1 - SELECT, FROM, WHERE**

### **1. Introduzione a SQL come linguaggio dichiarativo**

SQL è un **linguaggio dichiarativo**:  
l’utente **specifica cosa vuole ottenere**, non **come** ottenerlo.  
Il DBMS si occupa di tradurre l’interrogazione in un piano di esecuzione ottimizzato, lasciando al programmatore il compito di concentrarsi solo sulla **leggibilità e correttezza logica** della query.

---

### **2. Struttura generale di un’interrogazione semplice**

```sql
SELECT ListaAttributi
FROM ListaTabelle
[WHERE Condizione];
```

L’interrogazione:

- restituisce i valori indicati in `ListaAttributi`,
    
- a partire dal **prodotto cartesiano** delle tabelle elencate in `FROM`,
    
- **filtrando** le tuple che soddisfano la `Condizione`.

In algebra relazionale, l’operazione equivale a:

$$  
\pi_{\text{ListaAttributi}} (\sigma_{\text{Condizione}} (T_1 \times T_2 \times \dots \times T_n))  
$$

dove $T_1, T_2, \dots, T_n$ sono le tabelle specificate nella clausola `FROM`.

---

### **3. Sintassi estesa della SELECT**

```sql
SELECT AttrEspr [[AS] Alias] {, AttrEspr [[AS] Alias]}
FROM Tabella [[AS] Alias] {, Tabella [[AS] Alias]}
[WHERE Condizione];
```

Esempio:

```sql
-- Tabella: STUDENTI(Matr, Cognome, Nome, CdL)
SELECT Cognome, Nome, CdL AS CorsoDiLaurea
FROM Studenti
WHERE Matr = '123456';
```

---

### **4. Clausola SELECT**

L’elenco di attributi o espressioni (`AttrEspr`) può contenere:

- `*` → include tutti gli attributi
    
    ```sql
    SELECT * FROM Studenti;
    ```
    
- Espressioni aritmetiche
    
    ```sql
    SELECT Tasse/12 AS TasseMensili FROM Iscrizioni;
    ```
    
- Eliminazione dei duplicati con `DISTINCT` (di default SQL li mantiene)
    
    ```sql
    SELECT DISTINCT CdL FROM Studenti;
    SELECT DISTINCT Cognome, Nome FROM Studenti;
    SELECT ALL CdL FROM Studenti; -- duplicati mantenuti
    ```

---

### **5. Clausola FROM**

Elenca le tabelle coinvolte nell’interrogazione.  
Ogni tabella può essere **ridenominata** tramite `AS`.

Esempio:

```sql
FROM Studenti AS S, Esami AS E
```

SQL-92 consente inoltre di specificare il **join** direttamente nella clausola `FROM`:

```sql
FROM Studenti JOIN Esami ON Studenti.Matr = Esami.Matr
```

---

### **6. Clausola WHERE**

La clausola `WHERE` filtra le tuple in base a **condizioni booleane**.  
I predicati sono analoghi a quelli dell’algebra relazionale, con l’aggiunta di operatori specifici di SQL:

- **LIKE** – per il confronto di stringhe
    
    ```sql
    Corsi.Titolo LIKE 'Basi%';
    Matr LIKE '12_45_';
    ```
    
- **BETWEEN** – per confronti di intervalli (date o numeri)
    
    ```sql
    Esami.Data BETWEEN '2005-01-01' AND '2005-12-31';
    ```

---

### **7. Esempi di interrogazioni semplici**

**Tabelle di riferimento:**

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- ESAMI(Matr, Cod_corso, Data, Voto)

**Esempi:**

```sql
SELECT * 
FROM Studenti
WHERE CdL = 'SSRI' OR CdL = 'SSRIonline';
```

```sql
SELECT DISTINCT Data
FROM Esami
WHERE Voto = 30;
```

```sql
SELECT DISTINCT Cognome, Nome
FROM Studenti, Esami
WHERE Studenti.Matr = Esami.Matr AND Voto = 30;
```

---

### **8. Valori NULL nelle interrogazioni**

Il valore `NULL` rappresenta l’assenza o l’indeterminatezza di un dato:

- _Esiste ma non noto_ → es. data di nascita mancante
    
- _Non esiste_ → es. numero di patente per un minorenne
    
- _Non si sa se esiste_ → es. patente per un maggiorenne

#### **Differenze tra SQL-89 e SQL-2**

|Standard|Logica|Confronto con NULL|
|:--|:--|:--|
|SQL-89|Due valori (Vero/Falso)|Ritorna sempre `FALSE`|
|SQL-2|Tre valori (Vero/Falso/Sconosciuto)|Ritorna `UNKNOWN`|

Per verificare la presenza o l’assenza di valori nulli:

```sql
Attributo IS [NOT] NULL
```

---

### **9. Logica a tre valori**

SQL restituisce solo le tuple per cui la condizione risulta **VERA**.  
Le tabelle seguenti illustrano la logica a tre valori:

#### **AND**

|A|B|A AND B|
|:--|:--|:--|
|V|V|V|
|V|U|U|
|V|F|F|
|U|V|U|
|U|U|U|
|U|F|F|
|F|V|F|
|F|U|F|
|F|F|F|

#### **OR**

|A|B|A OR B|
|:--|:--|:--|
|V|V|V|
|V|U|V|
|V|F|V|
|U|V|V|
|U|U|U|
|U|F|U|
|F|V|V|
|F|U|U|
|F|F|F|

#### **NOT**

|A|NOT A|
|:--|:--|
|V|F|
|U|U|
|F|V|

---

### **10. Esempio sull’uso di NULL**

```sql
SELECT *
FROM Studenti
WHERE CdL = 'SSRI' OR CdL <> 'SSRI';
```

Questa interrogazione **esclude** le tuple con `CdL = NULL`, poiché il confronto con `NULL` restituisce `UNKNOWN`.  
Per ottenere le tuple in cui `CdL` non è nullo, si scrive:

```sql
SELECT *
FROM Studenti
WHERE CdL IS NOT NULL;
```

---

### **11. In sintesi**

In questa lezione abbiamo visto:

- la struttura generale di un’interrogazione SQL (`SELECT`, `FROM`, `WHERE`);
    
- l’uso delle clausole `DISTINCT`, `LIKE`, `BETWEEN`;
    
- la gestione dei **valori nulli** e la **logica a tre valori** introdotta da SQL-2.

Ricorda:  
SQL **non elimina automaticamente i duplicati** — è necessario usare `DISTINCT` — e restituisce solo le tuple per cui la condizione è **vera**.

---

![](imgs/Pasted%20image%2020251125044053.png)

![](imgs/Pasted%20image%2020251125044109.png)

![](imgs/Pasted%20image%2020251125044152.png)
