# **M8 UD1 Lezione 2 - DTD e XML Schema**

### **1. Introduzione**

I documenti XML, per quanto flessibili, possono diventare difficili da gestire se non si stabiliscono regole che ne definiscano la **struttura**.  
Per questo esistono strumenti come le **DTD (Document Type Definition)** e gli **XML Schema**, che permettono di:

- definire il **modello logico** dei documenti XML,
    
- specificare **quali tag** e **attributi** sono ammessi,
    
- indicare **le regole di annidamento** e **la cardinalità** degli elementi.

Un documento XML che rispetta le regole di una DTD o di uno Schema viene detto **valido** (_valid_).

---

### **2. Document Type Definition (DTD)**

La **DTD** serve per descrivere la **struttura** e il **contenuto** di un documento XML.  
Consente di definire:

- gli **elementi** e la loro **cardinalità**;
    
- gli **attributi** e le loro **proprietà** (obbligatorietà, valori di default, ecc.).

#### **Definizione di elementi**

Sintassi generale:

```xml
<!ELEMENT nome_elemento (contenuto)>
```

Tipologie di elementi:

- **contenenti altri elementi**  
    `<!ELEMENT PRODOTTO (DESCRIZIONE)>`
    
- **con testo (PCDATA)**  
    `<!ELEMENT DESCRIZIONE (#PCDATA)>`
    
- **con contenuto misto** (testo + elementi)  
    `<!ELEMENT ARTICOLO (#PCDATA | PRODOTTO)>`
    
- **vuoti**  
    `<!ELEMENT ARTICOLO EMPTY>`

---

### **3. Cardinalità degli elementi**

La cardinalità specifica **quante volte un elemento può comparire**.

|Simbolo|Significato|Esempio|
|:-:|:--|:--|
|_(niente)_|una sola volta|`<!ELEMENT LISTA (PRODOTTO)>`|
|`+`|una o più volte|`<!ELEMENT LISTA (PRODOTTO+)>`|
|`*`|zero o più volte|`<!ELEMENT LISTA (PRODOTTO*)>`|
|`?`|zero o una volta|`<!ELEMENT LISTA (PRODOTTO?)>`|

---

### **4. Definizione di attributi**

Gli attributi si definiscono con la sintassi:

```xml
<!ATTLIST nome_elemento nome_attributo tipo vincolo>
```

**Tipi principali:**

- `ID` – identificatore univoco;
    
- `CDATA` – dati testuali generici;
    
- `IDREF` – riferimento a un altro ID.

**Vincoli:**

- `#REQUIRED` → il valore è obbligatorio;
    
- `#IMPLIED` → il valore può mancare;
    
- `#FIXED "valore"` → se presente, deve essere esattamente quello specificato;
    
- `"valore"` → valore di **default**, usato se non ne viene fornito uno.

---

### **5. Esempio di DTD e documento XML**

**DTD**

```xml
<!ELEMENT ELENCO (PRODOTTO+)>
<!ELEMENT PRODOTTO (DESCRIZIONE, PREZZO?)>
<!ATTLIST PRODOTTO codice ID #REQUIRED>
<!ELEMENT DESCRIZIONE (#PCDATA)>
<!ELEMENT PREZZO (#PCDATA)>
```

**Documento XML**

```xml
<elenco>
  <prodotto codice="123">
    <descrizione>Libro</descrizione>
    <prezzo>50</prezzo>
  </prodotto>

  <prodotto codice="456">
    <descrizione>Penna stilo</descrizione>
  </prodotto>
</elenco>
```

---

### **6. XML Schema: definizione generale**

L’**XML Schema (XSD)** è un linguaggio più evoluto e potente rispetto alla DTD.  
Definisce **gli elementi, gli attributi e la struttura logica** di un documento XML in **forma XML**.

- Inizialmente sviluppato da Microsoft;
    
- divenuto **raccomandazione W3C nel 2001**;
    
- è anche detto **XSchema** o **XML Schema Definition (XSD)**.

---

### **7. Vantaggi dell’XML Schema rispetto ai DTD**

- È **estensibile** e basato su **XML stesso**;
    
- Supporta **tipi di dati numerici, stringhe, date, booleani, ecc.**;
    
- Permette di definire **tipi personalizzati** e **restrizioni**;
    
- Gestisce **namespace**;
    
- Offre maggiore **precisione e controllo semantico**.

---

### **8. Componenti di un XML Schema**

Un XML Schema può definire:

- **elementi semplici** (solo testo o valori base);
    
- **elementi complessi** (contenenti altri elementi o attributi);
    
- **indicatori** di ordine, cardinalità e gruppi;
    
- **attributi** con vincoli e tipi specifici.

---

### **9. Elementi semplici**

Gli **elementi semplici** non contengono altri elementi o attributi.  
Possono avere:

- **valori di default o fissi**,
    
- **restrizioni** sui dati (minimo, massimo, pattern, ecc.).

Esempi di definizione:

```xml
<xs:element name="età" type="xs:integer"/>
<xs:element name="cognome" type="xs:string"/>
<xs:element name="colore" type="xs:string" default="blu"/>
```

Esempio d’uso nel documento XML:

```xml
<età>65</età>
<cognome>Rossi</cognome>
```

---

### **10. Restrizioni nei tipi semplici**

Le restrizioni (o **facet**) limitano i valori ammessi per un elemento.

|Restrizione|Descrizione|
|---|---|
|`enumeration`|Elenco di valori accettabili|
|`fractionDigits`|Numero di cifre decimali|
|`length`|Lunghezza esatta|
|`minInclusive` / `maxInclusive`|Limiti inclusivi|
|`minExclusive` / `maxExclusive`|Limiti esclusivi|
|`pattern`|Espressione regolare ammessa|
|`totalDigits`|Numero totale di cifre|
|`whiteSpace`|Regole di gestione degli spazi|

Esempio:

```xml
<xs:element name="sesso">
  <xs:simpleType>
    <xs:restriction base="xs:string">
      <xs:pattern value="maschio|femmina"/>
    </xs:restriction>
  </xs:simpleType>
</xs:element>
```

---

### **11. Elementi complessi**

Un **elemento complesso** può contenere:

- **altri elementi**,
    
- **attributi**,
    
- o entrambi.

Sintassi:

```xml
<xs:complexType> ... </xs:complexType>
```

Esempio:

```xml
<xs:element name="persona">
  <xs:complexType>
    <xs:sequence>
      <xs:element name="Cognome" type="xs:string"/>
      <xs:element name="Nome" type="xs:string"
                  maxOccurs="4" minOccurs="1"/>
    </xs:sequence>
  </xs:complexType>
</xs:element>
```

---

### **12. Indicatori di struttura**

Gli indicatori definiscono come devono essere organizzati gli elementi.

|Indicatore|Descrizione|
|---|---|
|`all`|Tutti gli elementi, in qualunque ordine|
|`sequence`|Elementi in ordine specificato|
|`choice`|Uno e un solo elemento|
|`any`|Qualsiasi elemento ammesso|
|`maxOccurs` / `minOccurs`|Cardinalità massima/minima|
|`group`|Raggruppamento logico di elementi|

---

### **13. Attributi negli XML Schema**

Definiti con la sintassi:

```xml
<xs:attribute name="nome" type="tipo"
              default|fixed="valore"
              use="required|optional"/>
```

Esempio:

```xml
<xs:element name="cognome">
  <xs:complexType>
    <xs:simpleContent>
      <xs:extension base="xs:string">
        <xs:attribute name="lang"
                      type="xs:string"
                      use="required"/>
      </xs:extension>
    </xs:simpleContent>
  </xs:complexType>
</xs:element>
```

Documento XML corrispondente:

```xml
<cognome lang="it">Rossi</cognome>
```

---

### **14. Esempio completo di XML Schema**

**Schema (XSD)**

```xml
<?xml version="1.0"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema">

  <xs:element name="note">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="to" type="xs:string"/>
        <xs:element name="from" type="xs:string"/>
        <xs:element name="head" type="xs:string"/>
        <xs:element name="body" type="xs:string"/>
      </xs:sequence>
    </xs:complexType>
    <xs:attribute name="when" type="xs:date" use="required"/>
  </xs:element>

</xs:schema>
```

**Documento XML valido**

```xml
<?xml version="1.0"?>
<note when="2006-07-15">
  <to>Rossi Andrea</to>
  <from>Bianchi Riccardo</from>
  <head>Promemoria</head>
  <body>Compleanno Michela</body>
</note>
```

---

### **15. Sintesi finale**

In questa lezione abbiamo visto:

- come le **DTD** e gli **XML Schema** permettano di **definire la struttura dei documenti XML**;
    
- le **differenze** principali tra i due strumenti:
    
    - una **DTD non è un documento XML**,
        
    - un **XML Schema sì**, quindi è più flessibile e potente;
    
- la possibilità di **definire tipi di dati, cardinalità e vincoli**;
    
- e come **validare** un documento XML per garantire consistenza, correttezza e interoperabilità.

---


![](imgs/Pasted%20image%2020251125054337.png)

