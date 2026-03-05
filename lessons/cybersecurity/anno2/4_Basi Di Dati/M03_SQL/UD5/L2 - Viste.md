# **M3 UD5 Lezione 2 - Viste**

### **1. Introduzione**

Le **viste** (_views_) sono **tabelle virtuali** definite come il risultato di un’interrogazione SQL su una o più tabelle (di base o virtuali).  
Non memorizzano fisicamente i dati, ma **fanno riferimento a quelli esistenti**, consentendo di presentare una **rappresentazione personalizzata e semplificata** delle informazioni.

Le viste sono strumenti fondamentali per:

- semplificare la scrittura di interrogazioni complesse,
    
- limitare l’accesso ai dati sensibili,
    
- offrire una diversa prospettiva logica dei dati.

---

### **2. Creazione di una vista**

#### **Sintassi generale**

```sql
CREATE VIEW NomeVista [(ListaAttributi)] AS
SELECT ...
[WITH [LOCAL | CASCADED] CHECK OPTION];
```

- `ListaAttributi`: opzionale, consente di assegnare nomi espliciti agli attributi della vista.
    
- `CHECK OPTION`: garantisce che le modifiche eseguite sulla vista **non violino** il predicato di selezione della sua definizione.
    
    - `LOCAL`: applica il controllo solo alla vista stessa.
        
    - `CASCADED` (default): applica il controllo anche alle viste su cui essa si basa.

---

### **3. Esempi di viste**

#### **Esempio 1 – Vista con filtro temporale**

Tabelle:  
`ESAMI(Matr, Cod_corso, Data, Voto)`

```sql
CREATE VIEW Esami05 AS
SELECT *
FROM Esami
WHERE Data BETWEEN '2005-01-01' AND '2005-12-31';
```

La vista **Esami05** mostra solo gli esami sostenuti nell’anno 2005.

---

#### **Esempio 2 – Vista con join**

Tabelle:  
`STUDENTI(Matr, Cognome, Nome, CdL)`  
`ESAMI(Matr, Cod_corso, Data, Voto)`

```sql
CREATE VIEW EsamiSSRI AS
SELECT Esami.Matr, Cod_corso
FROM Studenti JOIN Esami
ON Studenti.Matr = Esami.Matr
WHERE CdL = 'SSRI';
```

La vista **EsamiSSRI** elenca gli esami sostenuti dagli studenti del corso di laurea _SSRI_.

---

### **4. Viste su altre viste**

È possibile definire **viste su viste**, purché **non vi siano dipendenze circolari** (mutua dipendenza).

#### **Esempio**

```sql
CREATE VIEW Esami05(Matr, Cod_corso) AS
SELECT Matr, Cod_corso
FROM Esami
WHERE Data BETWEEN '2005-01-01' AND '2005-12-31';

CREATE VIEW StudentiAttivi(Matr) AS
SELECT Matr
FROM Esami05;
```

> ✅ `StudentiAttivi` si basa su `Esami05`, ma non il contrario — quindi la dipendenza è lecita.

---

### **5. Uso delle viste nelle interrogazioni**

Le viste permettono di formulare **interrogazioni più semplici**, **riutilizzabili** e **leggibili**, anche in presenza di operatori complessi come `GROUP BY`, `HAVING`, `UNION`.

---

#### **Esempio 1 – CdL con il maggior numero di studenti**

Tabella:  
`STUDENTI(Matr, Cognome, Nome, CdL)`

```sql
SELECT CdL
FROM Studenti
GROUP BY CdL
HAVING COUNT(*) >= ALL (
    SELECT COUNT(*)
    FROM Studenti
    GROUP BY CdL
);
```

> ⚠️ Alcuni interpreti SQL non consentono interrogazioni nidificate nella clausola `HAVING`.  
> In tal caso, si può ricorrere a una vista.

---

#### **Soluzione con vista**

```sql
CREATE VIEW NumStudenti(CdL, Numero) AS
SELECT CdL, COUNT(*)
FROM Studenti
GROUP BY CdL;

SELECT CdL
FROM NumStudenti
WHERE Numero = (SELECT MAX(Numero) FROM NumStudenti);
```

> ✅ L’uso di una vista intermedia (`NumStudenti`) semplifica la logica della query principale.

---

#### **Esempio 2 – Numero medio di esami per studente**

Tabella:  
`ESAMI(Matr, Cod_corso, Data, Voto)`

```sql
CREATE VIEW NumEsami(Matr, Numero) AS
SELECT Matr, COUNT(*)
FROM Esami
GROUP BY Matr;

SELECT AVG(Numero)
FROM NumEsami;
```

> ❌ L’interrogazione diretta con `AVG(COUNT(*))` non è ammessa in SQL,  
> poiché non è possibile annidare operatori aggregati.  
> ✅ La soluzione con vista intermedia permette di ottenere lo stesso risultato.

---

### **6. Viste e aggiornamenti**

Le modifiche eseguite su una vista vengono propagate alle **tabelle di base** su cui essa è definita, ma **solo in casi non ambigui**.

- Le viste **basate su una sola tabella** sono **generalmente aggiornabili**.
    
- Le viste **basate su più tabelle o su aggregazioni** non lo sono, poiché il DBMS non può determinare **a quale tabella applicare la modifica**.

---

#### **Esempio 1 – Vista aggiornabile**

```sql
CREATE VIEW Esami05 AS
SELECT *
FROM Esami
WHERE Data BETWEEN '2005-01-01' AND '2005-12-31';

UPDATE Esami05
SET Voto = Voto + 1
WHERE Voto < 25;
```

> ✅ L’aggiornamento viene propagato automaticamente alla tabella `Esami`.

---

#### **Esempio 2 – Vista non aggiornabile**

Tabelle:  
`CORSI(Codice, Titolo)`  
`ASSEGNAZIONI(Cod_corso, Docente)`

```sql
CREATE VIEW ListaDocCorsi(Docente, Titolo) AS
SELECT Docente, Titolo
FROM Corsi JOIN Assegnazioni ON Codice = Cod_corso;

UPDATE ListaDocCorsi
SET Titolo = 'Basidati'
WHERE Docente = 'Rossi';
```

> ⚠️ Ambiguità: non è chiaro se il **titolo del corso** sia cambiato o se **Rossi insegni un corso diverso**.  
> In questi casi, l’aggiornamento non è consentito.

---

### **7. In sintesi**

In questa lezione abbiamo visto:

- la **definizione e creazione** delle viste (`CREATE VIEW`);
    
- l’uso del **controllo di coerenza** con `CHECK OPTION`;
    
- la possibilità di **definire viste su altre viste**;
    
- l’utilizzo delle viste per **semplificare query complesse**;
    
- le **limitazioni negli aggiornamenti** delle viste derivate da più tabelle.

Le viste rappresentano uno degli strumenti più potenti e versatili di SQL, poiché permettono di combinare **astrazione, sicurezza e semplicità d’uso** all’interno di un sistema di basi di dati.

---

![](imgs/Pasted%20image%2020251125045651.png)

![](imgs/Pasted%20image%2020251125045700.png)

![](imgs/Pasted%20image%2020251125045710.png)

