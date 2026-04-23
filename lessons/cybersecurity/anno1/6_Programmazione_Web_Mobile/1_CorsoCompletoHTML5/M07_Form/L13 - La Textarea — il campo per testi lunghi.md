## **Lezione 13: La Textarea — il campo per testi lunghi**

---

### **1. Introduzione**

La **textarea** è il campo usato ogni volta che l’utente deve scrivere **tante righe di testo**: biografie, descrizioni, commenti, messaggi, note, contenuti estesi.  
A differenza dell’`input type="text"`, che permette **una sola riga**, la textarea:

- supporta _righe multiple_
    
- permette l’andata a capo
    
- può essere ridimensionata dall’utente
    
- accetta testi molto lunghi
    
- è estremamente flessibile
    

La trovi in editor, moduli di contatto, pannelli di admin, blog, WordPress… quasi ovunque.

---

## **2. Struttura base**

```html
<textarea id="bio" name="bio"></textarea>
```

Caratteristiche immediatamente visibili:

- occupa più righe
    
- permette l’andata a capo naturale
    
- mostra lo **scroll verticale** quando il testo supera l’altezza disponibile
    
- ha un angolo in basso a destra per ridimensionare manualmente il box
    

Esempio d’uso: una bio lunga, una descrizione del profilo, un messaggio.

---

## **3. Dimensioni: `cols` e `rows`**

Gli attributi principali per dimensionare una textarea senza CSS sono:

### **`cols` – larghezza in caratteri**

```html
<textarea cols="40"></textarea>
```

Il numero indica quanti **caratteri** ci stanno _prima_ dell’andata a capo automatica.  
Attenzione: come hai notato, la conta parte da 0 → quindi `cols="10"` significa **11 caratteri percepiti**.

### **`rows` – numero di righe visibili**

```html
<textarea rows="20"></textarea>
```

Imposta quante righe vuoi mostrare all’utente al caricamento.

Combinando `cols` e `rows` ottieni una textarea già dimensionata come vuoi tu.

---

## **4. Ridimensionamento: `resize` (solo con CSS)**

Il ridimensionamento della textarea **non si controlla con HTML**, ma con CSS:

```css
textarea {
  resize: both;        /* resize libero */
  resize: vertical;    /* solo verticale */
  resize: horizontal;  /* solo orizzontale */
  resize: none;        /* disabilita resize */
}
```

Questo è utile quando vuoi evitare che l’utente “rompa” il layout trascinando troppo la textarea.

---

## **5. Il comportamento dell’andata a capo: `wrap`**

Esistono tre valori:

- `soft` → predefinito
    
- `hard`
    
- `off` → nessuna andata a capo, compare subito lo scroll
    

### **`wrap="soft"`** (valore di default)

Va a capo _visivamente_ ma quando invia il form **non aggiunge** il `\n`.

### **`wrap="hard"`**

Va a capo _visivamente_ e _inserisce_ anche il ritorno a capo nel testo inviato.

### **`wrap="off"`**

Blocca qualsiasi andata a capo automatica.  
Risultato: lo scroll orizzontale compare molto presto.

Questo attributo non viene quasi mai usato, ma se un giorno vedi una textarea con _scroll orizzontale immediato_, probabilmente è stato messo `off`.

---

## **6. Quando usare davvero una textarea**

Usa una `<textarea>` quando:

- l’utente deve inserire più frasi o paragrafi
    
- serve un testo lungo (bio, descrizione, note, recensioni…)
    
- serve supportare caratteri speciali e righe multiple
    
- vuoi un campo che sembri “un foglio di testo” più che una singola riga
    

NON usarla quando serve:

- un solo valore breve
    
- un codice numerico
    
- una riga unica obbligatoria (nome, cognome, città…)
    

---

## **7. Riepilogo completo**

|Funzione|Attributo|Effetto|
|---|---|---|
|Larghezza in caratteri|`cols=""`|imposta larghezza iniziale|
|Altezza in righe|`rows=""`|imposta numero righe visibili|
|Comportamento andata a capo|`wrap="soft/hard/off"`|gestisce stile dell'output|
|Ridimensionamento|`resize:` (CSS)|abilita/disabilita resize manuale|
|Identificazione dati|`id=""` / `name=""`|necessari per label e invio form|

---

## **8. Conclusione**

La textarea è uno degli elementi più importanti per la raccolta di testo reale.  
A differenza dei campi singola riga, qui abbiamo pieno controllo su:

- righe
    
- colonne
    
- ridimensionamento
    
- comportamento del testo
    

Nella prossima lezione inizieremo a introdurre altri elementi utili nei form, completando tutta la panoramica.