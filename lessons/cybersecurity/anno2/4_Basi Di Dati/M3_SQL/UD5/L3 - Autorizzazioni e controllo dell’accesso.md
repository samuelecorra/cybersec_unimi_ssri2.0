# **M3 UD5 Lezione 3 - Autorizzazioni e controllo dell’accesso**

### **1. Introduzione**

SQL adotta una **politica di controllo degli accessi di tipo discrezionale e chiusa**, basata sul concetto di **ownership** (proprietà).  
Ciò significa che **solo le azioni esplicitamente autorizzate** possono essere eseguite e che **il creatore di un oggetto** (tabella, vista, dominio, ecc.) **ne diventa automaticamente il proprietario**, ricevendo tutti i privilegi su di esso.

> Per le viste, i privilegi del proprietario sono **limitati a quelli che già possedeva** sulle tabelle sottostanti.

Ogni elemento dello schema può essere protetto, e il sistema tiene traccia di **chi possiede, concede e utilizza** ciascun privilegio.

---

### **2. Utenti e amministrazione**

Esiste un **utente predefinito**, `_system`, che rappresenta l’**amministratore di sistema**:  
ha **accesso completo** a tutte le risorse e può creare, modificare o cancellare qualsiasi oggetto del database.

Il **proprietario di un oggetto** può:

- **concedere** privilegi ad altri utenti;
    
- se lo fa **con l’opzione `GRANT OPTION`**, il destinatario potrà a sua volta **propagare** quel privilegio ad altri.

---

### **3. Struttura delle autorizzazioni**

Ogni autorizzazione in SQL è definita da:

- **Oggetto:** risorsa a cui si riferisce (tabella, vista, dominio, procedura).
    
- **Azione:** operazione consentita sull’oggetto.
    
- **Soggetto:** utente, ruolo o procedura che riceve il privilegio.
    
- **Concedente:** chi ha concesso il privilegio (`_system` per le autorizzazioni originarie).
    
- **Grant option:** flag che indica se il privilegio può essere ulteriormente concesso.

---

### **4. Azioni e privilegi**

Le principali **azioni autorizzabili** su tabelle sono:

- `SELECT` – lettura delle tuple (eventualmente su singoli attributi).
    
- `INSERT` – inserimento di nuove tuple.
    
- `UPDATE` – modifica dei valori esistenti.
    
- `DELETE` – rimozione di tuple.
    
- `TRIGGER` – definizione di trigger sulla tabella.
    
- `REFERENCES` – possibilità di riferirsi alla tabella in vincoli di integrità referenziale.
    
- `ALL PRIVILEGES` – concessione collettiva di tutti i privilegi precedenti.

Esistono inoltre privilegi per:

- **domini** (es. `USAGE`),
    
- **procedure** (es. `EXECUTE`).

---

### **5. Privilegio `REFERENCES`**

Il privilegio `REFERENCES` permette di **utilizzare una tabella o un attributo in un vincolo di chiave esterna**.  
Questo privilegio è cruciale perché **le politiche di reazione ai vincoli** (come `NO ACTION`) possono **influenzare altre tabelle esterne**.

---

### **6. Comando `GRANT`**

Il comando `GRANT` consente al proprietario di **concedere privilegi** su un oggetto ad altri utenti o ruoli.

#### **Sintassi generale**

```sql
GRANT <ListaPrivilegi | ALL PRIVILEGES>
ON <Risorsa>
TO <ListaDestinatari>
[WITH GRANT OPTION];
```

- `WITH GRANT OPTION` → abilita il destinatario a **propagare** il privilegio.

#### **Esempio**

Proprietario: **Alice** della tabella `STUDENTI`.

```sql
GRANT SELECT ON Studenti TO Bob WITH GRANT OPTION;
```

Bob potrà ora concedere a sua volta il privilegio:

```sql
GRANT SELECT ON Studenti TO Carl WITH GRANT OPTION;
GRANT SELECT ON Studenti TO David;
```

📘 **Grafo delle autorizzazioni:**

```
Alice → Bob → Carl → David
```

Ogni nodo può trasferire i privilegi ricevuti se ha la `GRANT OPTION`.

---

### **7. Comando `REVOKE`**

Il comando `REVOKE` consente di **revocare** privilegi precedentemente concessi.  
Può essere eseguito solo da chi li ha concessi originariamente.

#### **Sintassi generale**

```sql
REVOKE <ListaPrivilegi | ALL PRIVILEGES>
ON <Risorsa>
FROM <ListaUtenti>
[RESTRICT | CASCADE];
```

#### **Comportamento**

- **`RESTRICT`** → impedisce la revoca se l’utente ha già propagato il privilegio.
    
- **`CASCADE`** → propaga la revoca in modo ricorsivo, eliminando anche le autorizzazioni derivate.

#### **Esempio**

```sql
REVOKE SELECT ON Studenti FROM Bob RESTRICT;
```

> ❌ Non eseguita se Bob ha concesso il privilegio ad altri.

```sql
REVOKE SELECT ON Studenti FROM Bob CASCADE;
```

> ✅ Rimuove anche le autorizzazioni concesse da Bob a Carl e David.

📘 **Grafo dopo `CASCADE`:**

```
Alice (proprietario)
Bob ✗  Carl ✗  David ✗
```

---

### **8. Autorizzazioni su viste**

SQL **non consente** di specificare autorizzazioni **dipendenti dai valori dei dati** (es. “solo sugli studenti di SSRI”).  
Tuttavia, è possibile **simulare questo comportamento tramite viste**.

#### **Procedura:**

1. Si definisce una vista che seleziona solo i dati consentiti.
    
2. Si concedono privilegi su quella vista.
    
3. L’uso della vista **non richiede privilegi** sulle tabelle sottostanti.

#### **Esempio**

```sql
CREATE VIEW StudentiSSRI AS
SELECT *
FROM Studenti
WHERE CdL = 'SSRI';

GRANT SELECT ON StudentiSSRI TO Ellen;
```

> ✅ Ellen può leggere solo le tuple della vista, non tutte le righe della tabella `Studenti`.

---

### **9. Ruoli in SQL**

SQL-3 introduce il concetto di **ruolo** per gestire gruppi di privilegi in modo modulare.

- Un **ruolo** rappresenta un insieme di privilegi.  
    Attivando il ruolo, l’utente ottiene **tutti i privilegi associati**.
    
- Diversamente dai gruppi, i ruoli possono essere **attivati o disattivati** dinamicamente.

#### **Concessione e revoca di ruoli**

```sql
GRANT <ListaRuoli> TO <ListaUtenti>
[WITH ADMIN OPTION];
```

- `WITH ADMIN OPTION` → consente al destinatario di **concedere ulteriormente** il ruolo.

```sql
REVOKE [ADMIN OPTION] <ListaRuoli>
FROM <ListaUtenti>
[RESTRICT | CASCADE];
```

> Come per le autorizzazioni, anche la revoca dei ruoli può propagarsi **ricorsivamente**.

---

### **10. In sintesi**

Abbiamo visto:

- la **politica discrezionale chiusa** del controllo accessi in SQL;
    
- i **privilegi e le azioni** concedibili su oggetti e attributi;
    
- i comandi **`GRANT` e `REVOKE`**, con le opzioni di propagazione;
    
- l’uso delle **viste per autorizzazioni selettive**;
    
- l’introduzione dei **ruoli** per gestire insiemi di privilegi in modo flessibile.

In SQL, sicurezza e gestione dei privilegi sono **decentralizzate ma rigorosamente tracciate**, consentendo un controllo preciso e personalizzabile sugli accessi ai dati.

---

![](imgs/Pasted%20image%2020251125045752.png)

![](imgs/Pasted%20image%2020251125045810.png)

![](imgs/Pasted%20image%2020251125045820.png)

![](imgs/Pasted%20image%2020251125045834.png)

