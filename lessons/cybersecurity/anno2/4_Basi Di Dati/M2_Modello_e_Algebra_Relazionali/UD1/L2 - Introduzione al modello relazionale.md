# **M2 UD1 Lezione 2 - Introduzione al modello relazionale**

### **1. Definizioni di base**

Il **modello relazionale** definisce formalmente la struttura dei dati utilizzando il concetto di **schema di relazione**.

Uno **schema di relazione** si indica con:

$$  
R(X)  
$$

dove:

- **R** è il nome della relazione
    
- **$X = {A_1, A_2, \ldots, A_n}$** è l’insieme degli **attributi** che la compongono

Uno **schema di base di dati** è un insieme di schemi di relazioni:

$$  
\mathcal{R} = {R_1(X_1), R_2(X_2), \ldots, R_n(X_n)}  
$$

dove ogni relazione ha un nome distinto.

L’**istanza di una relazione** $r$ su uno schema $R(X)$ è un **insieme di tuple** definite sugli attributi di $X$.  
Analogamente, l’**istanza di una base di dati** $r$ su $\mathcal{R}$ è un insieme di relazioni:

$$  
r = {r_1, r_2, \ldots, r_n}  
$$

con $r_i$ definita sul rispettivo schema $R_i(X_i)$.

---

### **2. Notazioni convenzionali**

Per convenzione si adottano le seguenti notazioni:

| Elemento                       | Notazione                      | Esempi            |
| ------------------------------ | ------------------------------ | ----------------- |
| **Attributi**                  | Lettere iniziali dell’alfabeto | $A, B, C, A', A₁$ |
| **Insiemi di attributi**       | Lettere finali dell’alfabeto   | $X, Y, Z, X', X₁$ |
| **Nomi di relazione (schema)** | Maiuscole                      | $R, S, R', R₁$    |
| **Relazioni (istanze)**        | Minuscole                      | $r, s, r', r₁$    |
| **Schema di base di dati**     | Maiuscole in grassetto         | **$R$**, **$S$**  |
| **Base di dati (istanza)**     | Minuscole corrispondenti       | $r, s$            |

---

### **3. Modello relazionale basato sui valori**

Nel modello relazionale, le **associazioni tra dati di relazioni diverse** avvengono **tramite i valori** dei domini.  
Non si utilizzano **puntatori logici** visibili a livello utente.

**Esempio**

**CORSI**

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

**AULE**

|Piano|Nome|
|:--|:--|
|Primo|Delta|
|Primo|Gamma|
|Terra|Beta|
|Terra|Alfa|

In questo esempio, il collegamento tra **CORSI** e **AULE** è implicito: il valore del campo _Aula_ di una relazione coincide con il valore del campo _Nome_ dell’altra.

> A livello fisico possono esistere puntatori o riferimenti, ma **non sono visibili a livello logico**.

---

### **4. Vantaggi dell’approccio basato sui valori**

Rispetto ai modelli più vecchi (basati su record e puntatori), il modello relazionale offre diversi vantaggi:

- Rappresenta solo ciò che è **rilevante per l’applicazione**, evitando dettagli realizzativi a basso livello.
    
- Garantisce **indipendenza fisica dei dati**, separando la rappresentazione logica da quella fisica.
    
- Favorisce la **portabilità**: tutta l’informazione risiede nei valori.
    
- Le **associazioni tra dati** sono **bidirezionali**, non dipendono da un verso di navigazione.

---

### **5. Strutture nidificate**

Il modello relazionale ammette **solo valori scalari**, cioè non strutturati.  
Tuttavia, è possibile rappresentare **informazioni complesse** organizzando opportunamente i dati in **più relazioni correlate**.

---

### **6. Esempio di strutture nidificate**

Supponiamo di voler rappresentare le **ricevute** di un ristorante.

**RICEVUTE**

|Totale|Data|Num|
|:--|:--|:--|
|99.00|6/7/05|2453|
|117.00|8/7/05|2564|

**DETTAGLIO**

|Importo|Descr.|Q.ta|Numero|
|:--|:--|:--|:--|
|6.00|Coperti|3|2453|
|27.00|Primi|3|2453|
|42.00|Secondi|2|2453|
|15.00|Antipasti|2|2453|
|3.00|Acqua|1|2453|
|25.00|Vino|1|2564|
|12.00|Dessert|2|2564|
|42.00|Secondi|2|2564|
|27.00|Primi|2|2564|
|4.00|Coperti|2|2564|

---

### **7. Strutture nidificate: osservazioni**

La rappresentazione sopra è corretta se:

- L’**ordine delle righe** non ha significato (irrilevante).
    
- Non ci sono **tuple duplicate**.

Se queste condizioni non sono rispettate, occorre introdurre un **identificatore univoco di riga** (es. un attributo _Riga_) per distinguere le occorrenze all’interno di ogni ricevuta.

---

### **8. Informazione incompleta e valori nulli**

Nel mondo reale possono esistere **informazioni incomplete o assenti**.  
Per rappresentarle, il modello relazionale introduce il concetto di **valore nullo (NULL)**.

Esistono tre casi distinti:

1. **Valore sconosciuto** → il dato esiste, ma non lo conosciamo (es. _indirizzo della prefettura di Firenze_).
    
2. **Valore inesistente** → il dato non esiste (es. _Tivoli non ha prefettura_).
    
3. **Informazione mancante** → non sappiamo se il dato esiste (es. _Prato è nuova provincia, non sappiamo se ha prefettura_).

**Esempio**

|Città|Prefettura|
|:--|:--|
|Firenze|Via IV Novembre, Roma|
|Tivoli|NULL|
|Prato|NULL|

---

### **9. Formalizzazione del valore nullo**

Nel modello relazionale, il valore **NULL** rappresenta **assenza di informazione**.

Si estende quindi la definizione di tupla:  
per ogni attributo $A$, il valore $t[A]$ può essere:

- un valore del dominio $dom(A)$
    
- **oppure** il valore nullo **NULL**

$$  
t[A] \in dom(A) \cup {NULL}  
$$

La presenza di valori nulli è **limitata** da vincoli a livello di schema, per garantire coerenza semantica (es. non ammettere NULL in chiavi primarie).

---

### **10. Esempi di valori nulli**

**STUDENTI**

|Matricola|Cognome|Nome|Data-nasc|
|:--|:--|:--|:--|
|276545|Rossi|Maria|NULL|
|NULL|Neri|Anna|23/04/1972|
|NULL|Verdi|Fabio|12/02/1972|

**CORSI**

|Codice|Titolo|Docente|
|:--|:--|:--|
|01|Analisi|Giani|
|03|Chimica|NULL|
|NULL|Chimica|Belli|

**ESAMI**

|Studente|Corso|Voto|
|:--|:--|:--|
|276545|01|28|
|NULL|02|27|
|276545|NULL|18|

> **Osservazione:** un eccessivo uso di valori NULL compromette la qualità e la coerenza dei dati.

---

### **11. Sintesi finale**

In questa lezione abbiamo visto:

- Le **definizioni e notazioni** fondamentali del modello relazionale
    
- La rappresentazione dei dati **tramite relazioni basate sui valori**
    
- L’uso dei **valori nulli** per rappresentare l’assenza di informazione
    
- I **vantaggi dell’indipendenza fisica** e della **portabilità dei dati**

Il modello relazionale, basato sui valori, costituisce una struttura **astratta ma estremamente potente**, che consente di descrivere e manipolare dati in modo coerente, indipendente e formale.

![](imgs/Pasted%20image%2020251125042954.png)