## **M3 UD3 Lezione 3 - Funzioni scalari in SQL**

### **1. Introduzione**

In molte interrogazioni SQL capita di dover manipolare valori singoli: stringhe, numeri, date, condizioni.  
Per questo SQL mette a disposizione un insieme di **funzioni scalari**, cioè funzioni che operano su un singolo valore alla volta e restituiscono un singolo risultato.  
Non modificano tabelle, non aggregano righe, non cambiano la logica dei `group by`: servono semplicemente per trasformare un valore in un altro modo utile per la nostra query.

Sono strumenti fondamentali nelle **interrogazioni complesse** perché permettono di "pulire", controllare, normalizzare e reinterpretare i dati direttamente nella `select`.

---

### **2. Tipologie di funzioni scalari**

SQL standard prevede diverse famiglie di funzioni. Le implementazioni commerciali aggiungono molte altre funzioni non standard, ma il cuore è sempre lo stesso.

#### **2.1. Funzioni temporali**

Restituiscono informazioni di data e ora.  
Esempi tipici:

- `current_date`
    
- `current_time`
    
- `current_timestamp`

Queste funzioni sono molto usate per filtrare righe "ad oggi", calcolare differenze temporali o creare log temporali.

#### **2.2. Funzioni di manipolazione di stringhe**

Servono per lavorare sulle stringhe: lunghezza, concatenazioni, estrazioni, trasformazioni.  
Esempi:

- `char_length(stringa)`
    
- `upper()`, `lower()`
    
- `substring()`

Sono molto utili quando si devono formattare output, costruire etichette o sistemare colonne testuali non uniformi.

#### **2.3. Funzioni di conversione di dominio**

Permettono di cambiare il tipo di un valore.  
L’esempio principale è:

- `cast(espr as tipo)`

È essenziale quando si confrontano valori di tipi diversi o quando un campo viene importato come testo ma in realtà contiene numeri o date.

#### **2.4. Funzioni condizionali**

Sono la parte più importante di questa lezione.  
Non aumentano la potenza espressiva del linguaggio (potresti fare tutto anche senza), ma permettono di scrivere query **molto più compatte e leggibili**.

Le principali sono:

- `coalesce`
    
- `nullif`
    
- `case`

---

### **3. Le funzioni condizionali**

#### **3.1. `coalesce`**

`coalesce` prende in input una sequenza di espressioni e restituisce la **prima che non è nulla**.

È perfetta quando vuoi fornire un valore “di default” al posto di un `null`.

#### **Esempio**

Tabella: `STUDENTI(Matr, Cognome, Nome, CdL)`

Obiettivo: mostrare il corso di laurea di ogni studente, ma se `CdL` è nullo, mostrare la stringa `"non ins."`.

```sql
select Matr, Cognome, Nome, coalesce(CdL, 'non ins.')
from Studenti;
```

Il risultato è una tabella senza valori nulli nella colonna CdL, più leggibile e più utile.

---

#### **3.2. `nullif`**

`nullif` serve a trasformare un valore specifico in `null`.

Funziona così:

- Se l’espressione è _uguale_ al valore costante → restituisce `null`.
    
- Altrimenti → restituisce l’espressione stessa.

È utile quando una tabella usa un valore “speciale” (come `"ritirato"`, `"−1"`, `"NA"`) invece del vero valore nullo.

#### **Esempio**

Tabella: `ESAMI(Matr, Cod-corso, Data, Voto)`

Obiettivo: riportare `null` al posto del voto `"ritirato"`.

```sql
select Matr, Cod-corso, Data, nullif(Voto, 'ritirato')
from Esami;
```

In questo modo `"ritirato"` viene trattato come _mancanza di voto_, cioè `null`, rendendo più coerenti i dati.

---

#### **3.3. `case`**

`case` è lo strumento condizionale più potente e flessibile in SQL.  
È equivalente al costrutto `if – else if – else` nei linguaggi imperativi.

La sintassi generale è:

```sql
case
    when Condizione1 then Espressione1
    when Condizione2 then Espressione2
    ...
    else EspressioneDefault
end
```

Ti permette di costruire **logiche complesse direttamente dentro la SELECT**, senza dover modificare la tabella o creare campi aggiuntivi.

#### **Esempio**

Tabella: `ESAMI(Matr, Cod-corso, Data, Voto)`

Obiettivo: classificare i voti in tre fasce A, B, C.

```sql
select Matr, Cod-corso,
    case
        when Voto < 24 then 'C'
        when Voto >= 24 and Voto < 27 then 'B'
        else 'A'
    end as Fascia
from Esami;
```

Risultato: una nuova colonna `Fascia` che non esiste nella tabella fisica, ma viene costruita dinamicamente sulla base del contenuto dei voti.

---

### **4. Considerazioni finali**

- Le **funzioni scalari** permettono di manipolare il valore di ogni singola riga.
    
- Le **funzioni condizionali** non aggiungono potenza teorica al linguaggio, ma rendono le query molto più leggibili, compatte e controllabili.
    
- Ogni DBMS commerciale (MySQL, PostgreSQL, SQL Server, Oracle…) aggiunge molte funzioni proprie che non fanno parte dello standard SQL, quindi è sempre utile sapere quali estensioni offre la piattaforma che usi.

---

### **5. In sintesi**

- Hai visto le funzioni scalari standard: temporali, stringhe, conversioni.
    
- Hai studiato le tre funzioni condizionali più importanti:
    
    - `coalesce`
        
    - `nullif`
        
    - `case`
    
- Hai visto come servono per controllare `null`, sostituire valori speciali e costruire categorie o trasformazioni sui dati direttamente nella query.

---

![](imgs/Pasted%20image%2020251125045000.png)

![](imgs/Pasted%20image%2020251125045010.png)

![](imgs/Pasted%20image%2020251125045022.png)

