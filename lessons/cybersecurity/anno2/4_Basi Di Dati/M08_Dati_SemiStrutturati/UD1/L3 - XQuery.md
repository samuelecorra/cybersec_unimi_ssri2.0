# **M8 UD1 Lezione 3 - XQuery**

### **1. Introduzione**

Il linguaggio **XQuery** consente di esprimere **interrogazioni su documenti XML**, in modo analogo a come **SQL** opera sulle basi di dati relazionali.  
È stato proposto dal **W3C** il **15 febbraio 2001** ed è costruito su due componenti fondamentali:

- **XPath**, per l’individuazione dei nodi e dei percorsi all’interno del documento;
    
- le **espressioni FLWOR**, che definiscono il flusso logico della query (FOR, LET, WHERE, ORDER, RETURN).

---

### **2. XPath**

**XPath** (XML Path Language) è il linguaggio che permette di **indirizzare parti specifiche** di un documento XML.  
Il documento XML viene modellato come **un albero di nodi** in cui la relazione principale è **padre–figlio**.

Una _path expression_ (espressione di percorso):

- seleziona nodi o valori attraverso cammini sull’albero;
    
- può restituire:
    
    - un **insieme di nodi** (non ordinato e senza duplicati),
        
    - un **valore booleano**,
        
    - un **numero**,
        
    - o una **stringa**.

L’interpretazione di una path expression avviene **a partire dal nodo contesto**, ossia il nodo dal quale inizia la navigazione.  
La funzione `document("nomefile.xml")` identifica la **radice del documento**.

---

### **3. Sintassi XPath**

Simboli principali:

|Simbolo|Significato|
|:-:|---|
|`.`|Nodo corrente|
|`..`|Nodo padre|
|`/`|Radice o figlio del nodo corrente|
|`//`|Discendente (a qualsiasi livello)|
|`@`|Attributo|
|`*`|Qualsiasi nodo|
|`[ ]`|Predicato (condizione o filtro)|
|`[n]`|Posizione n-esima del nodo|

Esempio:

```xpath
libreria/libro/@codice
```

→ seleziona l’attributo `codice` di tutti gli elementi `<libro>` figli di `<libreria>`.

---

### **4. Esempi di Path Expression**

#### **Esempio 1**

```xpath
document("libri.xml")/Elenco/Libro
```

Restituisce **tutti i libri** nell’elenco del documento `libri.xml`.

#### **Esempio 2**

```xpath
document("libri.xml")/Elenco/Libro[Editore="Feltrinelli" and @disponibilità="S"]/Titolo
```

Restituisce i **titoli dei libri pubblicati da Feltrinelli** che sono **disponibili**.

#### **Esempio 3**

```xpath
document("libri.xml")//Autore
```

Restituisce **tutti gli autori** presenti nel documento, indipendentemente dal livello di annidamento.

#### **Esempio 4**

```xpath
document("libri.xml")/Elenco/Libro[1]/*
```

Restituisce **tutti gli elementi contenuti nel primo libro** del documento `libri.xml`.

---

### **5. Espressioni FLWOR**

Le **espressioni FLWOR** (FOR, LET, WHERE, ORDER, RETURN) sono il cuore di **XQuery**, e ricordano la struttura `SELECT-FROM-WHERE` del linguaggio SQL.  
Esse associano **variabili a valori** ed eseguono iterazioni per costruire i risultati finali.

#### **Clausole principali:**

- **FOR** – itera sugli elementi di un documento.
    
- **LET** – assegna una variabile a un risultato (senza iterazione).
    
- **WHERE** – applica condizioni di filtro.
    
- **ORDER BY** – ordina le tuple risultanti.
    
- **RETURN** – genera il risultato finale (nodo, valore o struttura XML).

---

### **6. Clausola FOR**

#### Esempio 1

```xquery
FOR $lib IN document("libri.xml")//Libro
RETURN $lib
```

→ restituisce **tutti i libri** del documento.

#### Esempio 2

```xquery
FOR $lib IN document("libri.xml")//Libro
FOR $auth IN $lib/Autore
RETURN $auth
```

→ restituisce **tutti gli autori** dei libri elencati nel documento.

---

### **7. Clausola LET**

```xquery
LET $lib := document("libri.xml")//Libro
RETURN $lib
```

La clausola `LET` assegna **un singolo binding** alla variabile: l’intero insieme di libri è memorizzato in `$lib`.

---

### **8. Clausola WHERE**

```xquery
FOR $lib IN document("libri.xml")//Libro
WHERE $lib/Editore="Bompiani" 
  AND $lib/@disponibilità="S"
RETURN $lib
```

→ seleziona **tutti i libri pubblicati da Bompiani** che risultano **disponibili**.

---

### **9. Clausola RETURN**

La clausola `RETURN` genera l’output della query.  
Il risultato può essere:

- un **nodo singolo**,
    
- una **foresta ordinata di nodi**,
    
- oppure un **valore**.

Esempio di costruttore di elemento:

```xquery
FOR $lib IN document("libri.xml")//Libro
WHERE $lib/Editore="Bompiani"
RETURN <EdB>{$lib/Titolo}</EdB>
```

Output:

```xml
<EdB><Titolo>Il nome della rosa</Titolo></EdB>
<EdB><Titolo>Il sospetto</Titolo></EdB>
```

---

### **10. Clausola ORDER BY**

Permette di **ordinare il risultato** della clausola `RETURN`.

Esempio:

```xquery
FOR $lib IN document("libri.xml")//Libro
RETURN <Libro>
  {$lib/Titolo},
  {$lib/Editore}
</Libro>
ORDER BY (Titolo ASCENDING)
```

→ restituisce l’elenco di libri **ordinato alfabeticamente per titolo**.

---

### **11. XQuery – Esempio di Join**

Supponiamo di avere due documenti XML:

**costruttore.xml**

```xml
<costruttore>
  <nome-co>Toyota</nome-co>
  <anno>2005</anno>
  <modello>
    <nome-mo>Prius</nome-mo>
    <cilindrata>1497</cilindrata>
    <potenza>77</potenza>
    <aliment>benzina</aliment>
  </modello>
</costruttore>
```

**macchina.xml**

```xml
<macchina>
  <venditore>Mario Rossi</venditore>
  <marca>Toyota</marca>
  <anno>2005</anno>
  <modello>Prius</modello>
  <colore>grigio metallizzato</colore>
  <prezzo>25000</prezzo>
</macchina>
```

**Query XQuery (join tra i due documenti):**

```xquery
FOR $c IN document("costruttore.xml")//costruttore,
    $m IN document("macchina.xml")//macchina,
    $mo IN $c/modello
WHERE $c/anno=$m/anno
  AND $c/nome-co=$m/marca
  AND $mo/nome-mo=$m/modello
RETURN <autovettura>
  {$m/marca},
  {$m/venditore},
  {$m/modello},
  {$mo/cilindrata},
  {$m/prezzo}
</autovettura>
```

→ produce un’unione tra le informazioni sui costruttori e sui veicoli, generando un documento XML integrato.

---

### **12. XQuery – Conteggio e aggregazione**

Esempio:

```xquery
FOR $e IN document("libri.xml")//Libro/Editore
LET $lib := document("libri.xml")//Libro[Editore=$e]
WHERE count($lib) > 100
RETURN $e
```

→ trova **gli editori che hanno almeno 100 libri** nell’elenco.

---

### **13. Sintesi finale**

In questa lezione abbiamo visto:

- come **XPath** individua le parti di un documento XML mediante percorsi strutturali;
    
- come le **espressioni FLWOR** permettono di costruire query potenti e flessibili;
    
- come **XQuery** può operare su **uno o più documenti XML**, includendo join, filtri, ordinamenti e costruzioni personalizzate di output.

**In sintesi:**  
XQuery è per XML ciò che **SQL** è per le basi di dati relazionali — un linguaggio completo per **estrarre, trasformare e integrare informazioni** da sorgenti dati strutturate e semistrutturate.

---


![](imgs/Pasted%20image%2020251125054408.png)

