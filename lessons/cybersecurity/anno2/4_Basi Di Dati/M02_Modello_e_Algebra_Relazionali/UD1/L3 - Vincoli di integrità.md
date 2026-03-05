# **M2 UD1 Lezione 3 - Vincoli di integrità**

### **1. Introduzione**

Esistono **istanze di basi di dati** che, pur essendo **sintatticamente corrette**, non rappresentano valori coerenti con la realtà dell’applicazione.  
Per garantire la correttezza semantica delle informazioni, si introducono i **vincoli di integrità**.

Un **vincolo di integrità** esprime una **proprietà logica** che deve essere sempre verificata dalle istanze della base di dati affinché esse siano **corrette e consistenti** rispetto al mondo reale.

---

### **2. Definizione di vincolo di integrità**

Un vincolo di integrità è una **proprietà** che deve essere soddisfatta dalle istanze che rappresentano informazioni corrette per l’applicazione.

Ogni vincolo può essere visto come un **predicato** che, applicato a una base di dati, assume un valore:

- **vero (true)** → il vincolo è soddisfatto
    
- **falso (false)** → il vincolo non è soddisfatto

Ogni **schema di base di dati** ha associato un insieme di vincoli.  
Sono considerate **corrette** solo le istanze della base di dati che **soddisfano tutti i vincoli**.

---

### **3. Classificazione dei vincoli**

I vincoli si dividono in due grandi categorie:

#### **Vincoli intra-relazionali**

Definiti rispetto a **una singola relazione**.

- **Vincoli di tupla:** valutati su ciascuna tupla, indipendentemente dalle altre.
    
- **Vincoli di dominio:** definiti su singoli valori (attributi).
    
- **Vincoli di chiave:** imposti su un insieme di attributi che identificano univocamente le tuple all’interno della relazione.

#### **Vincoli inter-relazionali**

Coinvolgono **più relazioni**, esprimendo condizioni di coerenza tra dati appartenenti a tabelle diverse (es. integrità referenziale).

---

### **4. Vincolo di tupla**

I **vincoli di tupla** esprimono condizioni sui valori di ciascuna tupla, indipendentemente dalle altre.

Si definiscono tramite **espressioni booleane** (connettivi `AND`, `OR`, `NOT`) formate da **atomi** che confrontano:

- valori di attributi
    
- oppure espressioni aritmetiche su attributi

**Esempi**

- Relazione `ESAMI(Studente, Voto, Lode, Corso)`  
    $$  
    \text{NOT}(Lode = 'lode') \text{ OR } (Voto = 30)  
    $$
    
- Relazione `PAGAMENTI(Data, Importo, Ritenute, Netto)`  
    $$  
    Netto = Importo - Ritenute  
    $$

---

### **5. Vincolo di dominio**

I **vincoli di dominio** impongono condizioni sui valori assunti da singoli attributi all’interno di una tupla.

Anche qui si utilizzano **espressioni booleane** che confrontano valori di attributi con costanti o espressioni.

**Esempi**

- Relazione `ESAMI(Studente, Voto, Lode, Corso)`  
    $$  
    (Voto \ge 18) \text{ AND } (Voto \le 30)  
    $$  
    $$  
    (Lode = 'lode') \text{ OR } (Lode = NULL)  
    $$

---

### **6. Chiavi e superchiavi**

Una **chiave** è un insieme di attributi utilizzato per **identificare univocamente** le tuple di una relazione.

Un insieme di attributi $K$ è detto **superchiave** di una relazione $r$ se $r$ **non contiene due tuple distinte** $t_1$ e $t_2$ tali che:

$$  
t_1[K] = t_2[K]  
$$

Un insieme di attributi $K$ è una **chiave** se è una **superchiave minimale**, cioè non esiste un suo sottoinsieme $K' \subset K$ che sia anch’esso una superchiave.

---

### **7. Esempio di chiavi**

|Corso|Nascita|Nome|Cognome|Matricola|
|:--|:--|:--|:--|:--|
|Sicurezza|05/03/58|Luca|Neri|5536|
|Matematica|01/05/61|Luca|Neri|4846|
|Informatica|01/05/61|Luca|Rossi|4766|
|Sicurezza|29/04/59|Dario|Rossi|6328|
|Sicurezza|29/04/59|Luigi|Rossi|4328|

**Chiavi possibili:**

- `{Matricola}`
    
- `{Cognome, Nome, Nascita}`
    
- `{Nome, Corso}`

**Superchiavi:**  
Tutti i **superinsiemi** che contengono almeno una chiave.

---

### **8. Esistenza di una chiave**

Ogni relazione $R(X)$ ha sempre **almeno una superchiave**: l’insieme di **tutti i suoi attributi $X$**.

L’esistenza di almeno una chiave in ogni relazione:

- garantisce la **riconoscibilità e accessibilità** dei dati;
    
- permette di **ricostruire le corrispondenze** tra dati contenuti in relazioni diverse.

---

### **9. Chiave primaria**

In ogni relazione è necessario identificare **una sola chiave primaria**, con le seguenti proprietà:

- Non sono ammessi **valori nulli** negli attributi che la compongono.
    
- Viene utilizzata per **identificare univocamente** le tuple.
    
- Può essere un **attributo aggiunto appositamente** (es. matricola, codice, numero progressivo).

**Notazione:**  
Si **sottolineano gli attributi** che fanno parte della chiave primaria.

**Esempi**

- STUDENTI(**Matricola**, Nome, Cognome)
    
- SEMINARI(**Codice**, Relatore, Titolo, Data)
    
- RICEVUTE(**Num**, Data, Totale)
    
- DETTAGLIO2(**Numero**, Q.ta, Riga, Descr, Importo)

---

### **10. Vincoli inter-relazionali: integrità referenziale**

Consideriamo due relazioni $R(X)$ e $S(Y)$, con:

- $X' \subset X$
    
- $Y' \subseteq Y$ e $Y'$ è la **chiave primaria** di $S$

Si dice che esiste un **vincolo di integrità referenziale** tra $X'$ e $Y'$ se:

> per ogni tupla $t_r \in R$ deve esistere una tupla $t_s \in S$ tale che  
> $t_r[A_i] = t_s[B_i]$ per ogni $i = 1, \ldots, n$  
> oppure $t_r[A_i] = NULL$

In altre parole:  
i valori di $X'$ in $R$ (detti **chiave esterna**) devono **corrispondere ai valori della chiave primaria $Y'$ in $S$**.

---

### **11. Esempio 1 – Integrità referenziale**

**RICEVUTE**

|Totale|Data|Num|
|:--|:--|:--|
|99.00|6/7/05|2453|
|117.00|8/7/05|2564|

**DETTAGLIO**

|Imp.|Descr.|Q.ta|Numero|
|:--|:--|:--|:--|
|6.00|Coperti|3|2453|
|27.00|Primi|3|2453|
|42.00|Secondi|2|2453|
|25.00|Vino|1|2564|
|12.00|Dessert|2|2564|
|42.00|Secondi|2|2564|
|27.00|Primi|2|2564|

Qui:

- Relazione $R = DETTAGLIO$
    
- Relazione $S = RICEVUTE$
    
- Chiave esterna $X' = Numero$
    
- Chiave primaria $Y' = Num$

Il vincolo di integrità referenziale assicura che **ogni valore di Numero in DETTAGLIO** esista anche come **Num in RICEVUTE**.

---

### **12. Esempio 2 – Integrità referenziale**

**AUTO**

|Numero|Provincia|Proprietario|
|:--|:--|:--|
|4E5432|FI|Rossi Mario|
|9H4467|MI|Verdi Paolo|
|7D6563|FI|Gialli Sara|
|4G7686|MO|Bianchi Silvia|

**INFRAZIONI**

|Num|Prov|Data|Codice|
|:--|:--|:--|:--|
|2F7653|MI|15/10/05|630876|
|4E5432|FI|12/10/05|987554|
|7D6563|MO|11/10/05|123435|
|4G7686|FI|15/10/05|726375|

- Relazione $R = INFRAZIONI$
    
- Relazione $S = AUTO$
    
- Chiave esterna $X' = {Prov, Num}$
    
- Chiave primaria $Y' = {Provincia, Numero}$

Le tuple in **INFRAZIONI** che non trovano corrispondenza in **AUTO** violano il vincolo di integrità referenziale.

---

### **13. Sintesi finale**

In questa lezione abbiamo introdotto:

- I **vincoli di integrità**, che garantiscono la correttezza dei dati
    
- Le principali tipologie:
    
    - **Intra-relazionali:** vincoli di tupla, di dominio e di chiave
        
    - **Inter-relazionali:** vincoli di integrità referenziale
    
- I concetti di **superchiave, chiave, chiave primaria e chiave esterna**

I vincoli di integrità sono fondamentali per garantire che una base di dati **resti coerente e consistente**, anche in presenza di operazioni di aggiornamento o interazione tra più relazioni.

![](imgs/Pasted%20image%2020251125043015.png)