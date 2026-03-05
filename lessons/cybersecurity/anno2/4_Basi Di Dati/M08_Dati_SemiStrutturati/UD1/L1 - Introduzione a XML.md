# **M8 UD1 Lezione 1 - Introduzione a XML**

### **1. Dati semistrutturati**

Nei **sistemi di basi di dati relazionali**, tutti i dati devono rispettare rigidamente uno **schema predefinito**.  
I **dati semistrutturati**, invece, possono:

- **rispettare solo parzialmente** uno schema,
    
- oppure **non avere alcuno schema fisso**.

Questo modello risulta particolarmente utile quando:

- le informazioni provengono da **fonti eterogenee**,
    
- lo schema è **dinamico o in evoluzione**,
    
- o si desidera una maggiore **flessibilità nella rappresentazione**.

---

### **2. XML: eXtensible Markup Language**

L’**XML** è un **formato di file standard** proposto dal **W3C (World Wide Web Consortium)** per la **distribuzione di documenti elettronici** (libri, manuali, cataloghi, ecc.) sul Web.

- Nato nel **1986** come **meta-linguaggio** per la definizione di linguaggi di markup,
    
- oggi rappresenta un **ponte tra HTML e i sistemi informativi**, grazie alla sua capacità di descrivere in modo strutturato i dati.

---

### **3. XML, HTML e basi di dati**

**In comune con HTML:**

- i dati XML sono anch’essi **documenti**;
    
- le proprietà dei dati vengono **espresse tramite tag** di marcatura.

**In comune con le basi di dati:**

- XML possiede un vero **modello dei dati**, definito da **DTD** o **XML Schema**;
    
- dispone di **linguaggi di interrogazione e trasformazione**;
    
- i dati sono **indipendenti dalla modalità di visualizzazione**, rendendo XML adatto a molteplici applicazioni.

---

### **4. Differenze tra XML e HTML**

- **HTML**: utilizza un **insieme fisso di tag** per la formattazione e la presentazione grafica.
    
- **XML**: è uno **standard per creare linguaggi di markup personalizzati**, con tag definiti dall’utente.

Esempio comparativo:

**HTML**

```html
<h1>Basi di dati: compl</h1>
<ul>
  <li>P.Samarati</li>
  <li>SSRI</li>
  <li>online</li>
</ul>
```

**XML**

```xml
<insegnamento>
  <titolo>Basi di dati: compl</titolo>
  <docente>P.Samarati</docente>
  <CdL>SSRI</CdL>
  <edizione>online</edizione>
</insegnamento>
```

➡️ **Conclusione:** XML non sostituisce HTML, ma lo **completa**.

- **XML** descrive **cosa rappresentano i dati**,
    
- **HTML** descrive **come visualizzarli**.

---

### **5. Usi principali di XML**

XML viene impiegato per:

- **separare i dati** dalla loro rappresentazione grafica;
    
- **scambiare informazioni** tra sistemi incompatibili;
    
- supportare la **comunicazione B2B** (Business-to-Business);
    
- **condividere e memorizzare dati**;
    
- **gestire documenti** nei browser moderni, essendo un semplice file di testo.

Essendo un **documento testuale**, può essere manipolato da qualsiasi software che gestisca file di testo.

---

### **6. Sintassi di XML**

Ogni documento XML deve:

- avere un **elemento radice** che contiene tutti gli altri;
    
- includere tag **di apertura e chiusura** obbligatori;
    
- essere **case-sensitive** (es. `<a>` è diverso da `<A>`);
    
- avere tag **correttamente annidati** (no `<a><b></a></b>`).

Esempio:

```xml
<prodotto>
  <descrizione>Libro</descrizione>
  <prezzo>50</prezzo>
</prodotto>
```

Documenti con elementi vuoti:

```xml
<prodottovuoto></prodottovuoto>
<!-- oppure -->
<prodottovuoto/>
```

---

### **7. Prologo di un documento XML**

Ogni file XML inizia con una **dichiarazione iniziale**, che indica:

- la **versione** del linguaggio,
    
- la **codifica** dei caratteri (opzionale).

Esempio:

```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
```

---

### **8. Elementi e attributi**

#### **Elementi**

- Possono includere **altri elementi figli**;
    
- possono contenere **testo o essere vuoti**;
    
- costituiscono la **struttura logica** del documento.

#### **Attributi**

- Si trovano **all’interno del tag di apertura**;
    
- servono per **associare valori aggiuntivi** all’elemento, senza far parte del contenuto.

Esempio:

```xml
<prodotto codice="123456">
  <descrizione>Libro</descrizione>
  <prezzo>50</prezzo>
</prodotto>
```

**Regole per gli attributi:**

- i valori devono essere **racchiusi tra virgolette**;
    
- gli attributi non possono contenere **più valori**;
    
- non possono avere **elementi figli**;
    
- sono adatti a **memorizzare metadati** (informazioni descrittive).

---

### **9. Documento ben formato**

Un documento XML è **ben formato** (_well-formed_) se:

- inizia con il **prologo**;
    
- tutti i tag hanno **apertura e chiusura** corretta;
    
- la **nidificazione** è coerente;
    
- gli **attributi** sono codificati correttamente (valori tra apici);
    
- i nomi dei tag rispettano la **distinzione maiuscole/minuscole**.

---

### **10. Namespace**

In XML, **elementi e attributi provenienti da documenti diversi** possono avere **lo stesso nome**.  
Per evitare ambiguità si introducono i **namespace**, ovvero spazi dei nomi che distinguono gli elementi mediante **prefissi**.

Esempio:

```xml
<d:documento>
  <d:titolo>Lez 1</d:titolo>
  <d:testo>bla bla</d:testo>
</d:documento>

<p:persona>
  <p:titolo>dott</p:titolo>
  <p:nome>Rossi</p:nome>
</p:persona>
```

Il namespace è dichiarato con la sintassi:

```xml
xmlns:prefisso="URI"
```

Esempio:

```xml
xmlns:d="http://miospazioweb/d_schema"
```

**Proprietà dei namespace:**

- l’associazione di un namespace si **propaga ai figli** dell’elemento;
    
- se dichiarato nel **prologo**, si applica a tutto il documento;
    
- **elementi fratelli** possono appartenere a namespace differenti.

Esempio:

```xml
<documento>
  <a:titolo>Lezione 1</a:titolo>
  <b:testo>bla bla</b:testo>
</documento>
```

---

### **11. Sintesi finale**

In questa lezione abbiamo introdotto:

- il concetto di **dati semistrutturati** e la loro flessibilità;
    
- il linguaggio **XML**, la sua sintassi e i suoi vantaggi rispetto a HTML;
    
- la struttura dei **documenti XML**, con elementi e attributi;
    
- e l’uso dei **namespace** per gestire nomi duplicati tra diversi contesti.

**In sintesi**, XML rappresenta un linguaggio universale per **descrivere e scambiare dati strutturati**, mantenendo separata la rappresentazione logica dal modo in cui i dati vengono visualizzati o utilizzati.

---


![](imgs/Pasted%20image%2020251125054011.png)

