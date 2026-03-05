# **M3 UD2 Lezione 2 - Join e variabili**

### **1. Introduzione**

In SQL, i **join** consentono di combinare i dati provenienti da più tabelle in base a una condizione di corrispondenza tra attributi.  
Essi rappresentano l’equivalente delle **operazioni di join dell’algebra relazionale**, ma con una sintassi più leggibile e flessibile, introdotta con **SQL-92**.  
Inoltre, SQL consente di assegnare **alias (variabili)** alle tabelle, facilitando la scrittura di query più compatte e chiare, soprattutto quando si lavora su più occorrenze della stessa tabella.

---

### **2. Sintassi generale del JOIN**

```sql
SELECT AttrEspr [[AS] Alias] {, AttrEspr [[AS] Alias]}
FROM Tabella [[AS] Alias]
     [TipoJoin JOIN Tabella [[AS] Alias] ON CondizioneJoin]
[WHERE AltraCondizione];
```

#### **Tipi di join:**

- **INNER JOIN** (default): restituisce solo le tuple con corrispondenze in entrambe le tabelle.
    
- **LEFT [OUTER] JOIN**: mantiene tutte le tuple della tabella di sinistra.
    
- **RIGHT [OUTER] JOIN**: mantiene tutte le tuple della tabella di destra.
    
- **FULL [OUTER] JOIN**: mantiene tutte le tuple di entrambe le tabelle, riempiendo con `NULL` dove non vi sono corrispondenze.
    
- **NATURAL JOIN**: unisce automaticamente le tabelle sugli attributi con lo stesso nome (implementato raramente).

---

### **3. Esempio di INNER JOIN**

**Tabelle di riferimento:**

- STUDENTI(Matr, Cognome, Nome, CdL)
    
- ESAMI(Matr, Cod_corso, Data, Voto)

**Query:**

```sql
SELECT S.Matr, Cognome, Nome
FROM Studenti AS S JOIN Esami AS E
ON S.Matr = E.Matr
WHERE Voto = 30;
```

È **equivalente** a:

```sql
SELECT S.Matr, Cognome, Nome
FROM Studenti AS S, Esami AS E
WHERE S.Matr = E.Matr AND Voto = 30;
```

---

### **4. JOIN senza clausola WHERE**

Quando non si specifica alcuna condizione di filtro, il join produce semplicemente il **prodotto cartesiano** tra le tabelle.

```sql
SELECT S.Matr, Cognome, Nome, Cod_corso, Voto
FROM Studenti AS S JOIN Esami AS E
ON S.Matr = E.Matr;
```

Se uno studente **non ha sostenuto esami**, **non compare** nel risultato.

---

### **5. Outer Join**

Gli **outer join** permettono di **mantenere le tuple** di una o entrambe le tabelle anche in assenza di corrispondenza.  
I valori mancanti vengono riempiti con `NULL`.

#### **Tipologie di outer join:**

- **LEFT OUTER JOIN:** mantiene tutte le tuple della tabella di sinistra.
    
- **RIGHT OUTER JOIN:** mantiene tutte le tuple della tabella di destra.
    
- **FULL OUTER JOIN:** mantiene tutte le tuple di entrambe le tabelle.

---

### **6. Esempi di Outer Join**

#### **Esempio 1 – LEFT JOIN**

**Obiettivo:** selezionare tutti gli studenti, con l’eventuale elenco dei loro esami.

```sql
SELECT S.*, Cod_corso, Voto
FROM Studenti AS S LEFT JOIN Esami AS E
ON S.Matr = E.Matr;
```

Gli studenti senza esami compariranno con `NULL` nei campi relativi a `Cod_corso` e `Voto`.

---

#### **Esempio 2 – RIGHT JOIN**

**Tabelle di riferimento:**

- ESAMI(Matr, Cod_corso, Data, Voto)
    
- CORSI(Codice, Titolo, Docente)

**Obiettivo:** selezionare tutti i corsi con l’eventuale elenco degli studenti che hanno sostenuto l’esame.

```sql
SELECT Codice, Titolo, Matr
FROM Esami RIGHT JOIN Corsi
ON Cod_corso = Codice;
```

I corsi **senza studenti associati** compariranno comunque, con `NULL` nei campi relativi alle matricole.

---

### **7. Uso delle variabili (alias)**

SQL permette di assegnare **alias** alle tabelle — vere e proprie **variabili** — utili per:

- abbreviare i riferimenti ai nomi delle tabelle;
    
- distinguere più occorrenze della stessa tabella (autojoin);
    
- scrivere query nidificate o auto-correlate.

---

### **8. Esempio con autojoin**

**Tabella di riferimento:**

- IMPIEGATI(Matr, Cognome, Nome, Dip, Matr_manager)

**Obiettivo:** elencare gli impiegati del dipartimento “A” indicando anche nome e cognome del loro manager (se esiste).

```sql
SELECT I1.*, I2.Cognome, I2.Nome
FROM Impiegati AS I1 LEFT JOIN Impiegati AS I2
ON I1.Matr_manager = I2.Matr
WHERE I1.Dip = 'A';
```

Qui:

- `I1` rappresenta gli impiegati;
    
- `I2` rappresenta i manager.  
    Le due occorrenze si riferiscono alla **stessa tabella**, ma a ruoli diversi.

---

### **9. In sintesi**

In questa lezione abbiamo appreso:

- i **diversi tipi di join** (`INNER`, `LEFT`, `RIGHT`, `FULL`, `NATURAL`);
    
- come i **join esterni (outer)** permettano di conservare tuple senza corrispondenza;
    
- l’importanza degli **alias** per semplificare e rendere più espressive le interrogazioni SQL, soprattutto in presenza di **autojoin** o **query nidificate**.

---

![](imgs/Pasted%20image%2020251125044218.png)

![](imgs/Pasted%20image%2020251125044231.png)

![](imgs/Pasted%20image%2020251125044241.png)

![](imgs/Pasted%20image%2020251125044252.png)

