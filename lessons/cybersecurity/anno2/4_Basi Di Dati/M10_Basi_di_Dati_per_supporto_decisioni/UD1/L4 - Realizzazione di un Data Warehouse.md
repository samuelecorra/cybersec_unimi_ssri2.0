# **M10 UD1 Lezione 4 - Realizzazione di un Data Warehouse**

### **1. Introduzione**

La realizzazione di un **data warehouse (DW)** può avvenire secondo due principali approcci architetturali:

- **ROLAP (Relational OLAP)**
    
- **MOLAP (Multidimensional OLAP)**

Entrambi hanno lo stesso obiettivo — **rendere disponibili i dati in forma analitica** — ma si differenziano per la **tecnologia di memorizzazione** e per le **tecniche di ottimizzazione** adottate.

---

### **2. Approcci alla realizzazione del Data Warehouse**

#### **a) ROLAP (Relational OLAP)**

- Si basa su **tecnologia relazionale** opportunamente adattata ed estesa.
    
- I **dati sono memorizzati in tabelle relazionali**, e le analisi vengono espresse tramite **istruzioni SQL**.
    
- Utilizza **strutture di accesso speciali** per ottimizzare le operazioni di aggregazione e interrogazione.
    
- Viene **costruito in modo incrementale**, attraverso la collezione di **data mart settoriali** (aree tematiche: vendite, clienti, marketing, ecc.).
    
- Adotta il **modello dimensionale**, tipicamente organizzato secondo lo **schema a stella** o il **fiocco di neve**.

#### **b) MOLAP (Multidimensional OLAP)**

- Memorizza i dati **direttamente in forma multidimensionale**.
    
- Utilizza **strutture dati proprietarie** ottimizzate per l’accesso rapido e per la rappresentazione dei cubi OLAP.
    
- È orientato all’analisi interattiva, ma **meno flessibile** in termini di gestione di volumi elevati di dati rispetto a ROLAP.

---

### **3. Schema a stella (Star Schema)**

Lo **schema a stella** è il modello più comune nei sistemi ROLAP.  
È composto da:

- una **tabella dei fatti**, che contiene le **misure quantitative**;
    
- più **tabelle dimensione**, che contengono le **prospettive di analisi** (tempo, luogo, prodotto, cliente, ecc.);
    
- vincoli di **integrità referenziale** tra la tabella dei fatti e le tabelle dimensione.

---

### **4. Componenti dello schema a stella**

#### **a) Tabella dei fatti**

- Contiene le **misure** (es. unità vendute, incassi).
    
- La **chiave primaria** è **composta dalle chiavi** delle tabelle dimensione.
    
- Gli attributi non chiave rappresentano **le misure del fatto** e sono in genere **numerici**.
    
- È normalmente in **forma normale (Boyce-Codd)** per garantire coerenza.

#### **b) Tabelle dimensione**

- Ogni tabella rappresenta **una dimensione di analisi** (Tempo, Prodotto, Cliente, ecc.).
    
- Ha **chiave semplice** e attributi descrittivi o gerarchici (es. Anno, Mese, Giorno per la dimensione Tempo).
    
- **Non è normalizzata**, poiché la ridondanza aumenta l’efficienza di accesso e riduce il numero di join.

---

### **5. Esempio di schema a stella**

#### **Tabella dei fatti: Vendite**

|CodTempo|CodLuogo|CodProdotto|CodCliente|Unità|Incasso|
|---|---|---|---|---|---|

#### **Tabelle dimensione:**

- **Tempo**: (CodTempo, Giorno, Mese, Trimestre, Anno)
    
- **Luogo**: (CodLuogo, Negozio, Città, Regione, Stato)
    
- **Prodotto**: (CodProdotto, Descrizione, Categoria, Colore, Modello)
    
- **Cliente**: (CodCliente, Nome, Cognome, Età, Professione)

→ Ogni chiave nella tabella `Vendite` fa riferimento alla chiave primaria della corrispondente tabella dimensione.

---

### **6. Schema a fiocco di neve (Snowflake Schema)**

Lo **schema a fiocco di neve** deriva da una **normalizzazione (anche parziale)** dello schema a stella.  
Serve a **ridurre le ridondanze** nelle dimensioni, ma può comportare **un degrado delle prestazioni**.

#### **Caratteristiche:**

- Le tabelle dimensione vengono **scomposte in più sotto-tabelle** (es. `Città → Regione → Stato`).
    
- Ogni tabella mantiene relazioni **n:1** verso i livelli superiori.
    
- Dalla tabella dei fatti si possono raggiungere tutte le tabelle delle dimensioni **attraverso catene di join**.

**Vantaggio:** minore ridondanza.  
**Svantaggio:** interrogazioni più lente a causa dei join multipli.

---

### **7. Operazioni su ROLAP**

In un sistema ROLAP, i dati vengono presentati all’utente con una **visione multidimensionale**.  
Le interrogazioni espresse su questa visione vengono poi **tradotte in SQL** dal sistema.

#### **Esempio di query ROLAP (roll-up)**

> “Selezionare le vendite complessive del 2003 per categoria di articolo e trimestre.”

```sql
SELECT P.Categoria, T.Trimestre, SUM(V.Unità)
FROM Vendite AS V, Prodotto AS P, Tempo AS T
WHERE V.CodiceProdotto = P.CodiceProdotto
  AND V.CodiceTempo = T.CodiceTempo
  AND T.Anno = 2003
GROUP BY P.Categoria, T.Trimestre
ORDER BY P.Categoria, T.Trimestre;
```

---

### **8. Aggregazioni in SQL**

Lo **standard SQL** offre costrutti dedicati alle **aggregazioni multidimensionali**, introducendo due opzioni fondamentali:

|Clausola|Descrizione|
|---|---|
|**WITH CUBE**|Esegue **tutte le possibili aggregazioni** tra le dimensioni specificate.|
|**WITH ROLLUP**|Esegue **aggregazioni progressive**, risalendo lungo la gerarchia di una dimensione alla volta.|

Entrambe utilizzano il valore speciale `ALL`, che rappresenta “tutti i valori possibili” del dominio.

---

### **9. Esempi pratici di aggregazione**

#### **a) Esempio con `WITH CUBE`**

```sql
SELECT Modello, Anno, Colore, SUM(Unità)
FROM Vendite AS V, Prodotto AS P, Tempo AS T
WHERE V.CodiceProdotto = P.CodiceProdotto
  AND V.CodiceTempo = T.CodiceTempo
  AND P.Modello IN ('fiat', 'ford')
  AND P.Colore = 'rosso'
  AND T.Anno BETWEEN 1994 AND 1995
GROUP BY Modello, Anno, Colore
WITH CUBE;
```

→ Genera tutte le aggregazioni possibili: per modello, anno, colore e combinazioni tra essi.

#### **b) Esempio con `WITH ROLLUP`**

```sql
SELECT Modello, Anno, Colore, SUM(Unità)
FROM Vendite AS V, Prodotto AS P, Tempo AS T
WHERE V.CodiceProdotto = P.CodiceProdotto
  AND V.CodiceTempo = T.CodiceTempo
  AND P.Modello IN ('fiat', 'ford')
  AND P.Colore = 'rosso'
  AND T.Anno BETWEEN 1994 AND 1995
GROUP BY Modello, Anno, Colore
WITH ROLLUP;
```

→ Esegue un’aggregazione progressiva: prima per (Modello, Anno, Colore), poi per (Modello, Anno), poi per (Modello), e infine globale.

---

### **10. Ottimizzazioni nei sistemi ROLAP**

Per migliorare l’efficienza delle operazioni analitiche, i sistemi ROLAP utilizzano diverse tecniche:

- **Indici bitmap**  
    Efficienti per valutare condizioni logiche (AND, OR) e predicati di uguaglianza.
    
- **Indici di join**  
    Precalcolano i join tra la tabella dei fatti e le tabelle dimensione, riducendo i tempi di esecuzione.
    
- **Viste materializzate**  
    Precomputano e salvano i risultati di query o aggregazioni usate frequentemente, permettendo un accesso immediato ai dati più richiesti.

---

### **11. Sintesi finale**

In questa lezione abbiamo analizzato:

- i due principali approcci alla realizzazione dei data warehouse (**ROLAP e MOLAP**);
    
- i **modelli concettuali di schema**: a **stella** e a **fiocco di neve**;
    
- le **tecniche SQL di aggregazione** con `WITH CUBE` e `WITH ROLLUP`;
    
- e le principali **ottimizzazioni** utilizzate nei sistemi ROLAP (indici bitmap, join e viste materializzate).

**In sintesi:**  
La progettazione di un data warehouse richiede un **equilibrio tra efficienza e semplicità**:

- lo **schema a stella** massimizza le prestazioni,
    
- lo **schema a fiocco di neve** riduce la ridondanza,
    
- e le **aggregazioni SQL** consentono analisi flessibili e multidimensionali sui dati.

---


![](imgs/Pasted%20image%2020251125055533.png)

