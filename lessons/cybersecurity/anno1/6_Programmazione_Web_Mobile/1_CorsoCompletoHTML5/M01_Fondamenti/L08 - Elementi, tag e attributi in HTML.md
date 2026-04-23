# **Lezione 8: Elementi, tag e attributi in HTML**

## **1. Che cos’è un elemento HTML (struttura base)**

Partiamo dalla forma più semplice:

```html
<p>Ciao</p>
```

Questo è un **elemento HTML** completo. È formato da:

- **Tag di apertura**: `<p>`
    
- **Tag di chiusura**: `</p>`
    
- **Contenuto**: `Ciao`
    

Tutta la combinazione (apertura + contenuto + chiusura) è l’**elemento**.

In modo più generale:

```html
<nomeTag>contenuto</nomeTag>
```

- `nomeTag` è il tipo di elemento (`p`, `h1`, `div`, `span`, `a`, …).
    
- Tra apertura e chiusura mettiamo il **contenuto**, che può essere:
    
    - semplice testo;
        
    - altri elementi HTML;
        
    - testo + elementi mescolati.
        

Questa è la struttura di base che useremo sempre.

---

## **2. Elementi annidati e relazione padre/figlio**

Un elemento non deve contenere per forza solo testo. Può contenere altri elementi.

Esempio:

```html
<body>
  <div>
    <p>Questo è un paragrafo.</p>
    <span>Questo è uno span.</span>
    <a href="https://example.com">Clicca qui</a>
  </div>
</body>
```

Qui succede questo:

- `body` contiene un solo figlio: il `div`.
    
- Il `div` contiene tre figli: `p`, `span`, `a`.
    
- Ogni elemento può avere **figli** (children) e un **padre** (parent).
    

Parliamo quindi di **gerarchia**:

- `body` è il **padre** di `div`;
    
- `div` è il padre di `p`, `span`, `a`;
    
- `p`, `span`, `a` sono i **figli** (children) di `div`.
    

Quando usiamo `Alt + Maiusc + F` (Prettier) vediamo la gerarchia attraverso l’**indentazione**: gli elementi “figli” sono spostati verso destra rispetto al padre. L’indentazione non cambia il funzionamento del codice, ma rende la struttura molto più chiara sia per noi che per altri sviluppatori.

> Idea mentale utile: pensa agli elementi HTML come a **matrioske**: ogni bambola contiene altre bambole più piccole. `html` contiene `head` e `body`; `body` contiene altri elementi; ogni elemento può contenerne altri, e così via.

---

## **3. Elementi vuoti: tag senza contenuto**

Abbiamo detto che di solito un elemento ha **apertura** e **chiusura**.  
Ma esistono alcune eccezioni: gli **elementi vuoti** (_void elements_).

Esempi importanti:

```html
<br />
<img src="foto.jpg" alt="Descrizione della foto" />
```

Caratteristiche:

- Non hanno **contenuto interno**.
    
- Non esiste un tag di chiusura del tipo `</img>` o `</br>`.
    
- Possiamo scriverli come `<br>` o `<br />` (Prettier in genere li trasforma nella versione `/>`).
    

Perché sono vuoti?

- `<br>` rappresenta solo un **a capo** (interruzione di riga). Non ha senso mettere contenuto “dentro”.
    
- `<img>` rappresenta un’**immagine** che viene caricata da un file esterno. L’immagine non “vive” dentro il tag, ma nel file indicato dall’attributo `src`.
    

Gli elementi vuoti **non accettano figli**. Non possiamo scrivere:

```html
<img>Testo</img>   <!-- SBAGLIATO -->
```

Il browser cercherà di correggere, ma è concettualmente errato. Per questo Prettier chiude automaticamente i tag vuoti in forma `/>` e basta.

---

## **4. Attributi: informazioni extra sugli elementi**

Negli esempi hai già visto scritte in azzurro accanto ai tag, tipo `lang`, `href`, `src`, `charset`, `content`, `name`…  
Questi sono gli **attributi**.

Struttura generale:

```html
<nomeTag attributo="valore">contenuto</nomeTag>
```

Oppure, per gli elementi vuoti:

```html
<nomeTag attributo="valore" />
```

Gli attributi servono a **specificare proprietà aggiuntive** dell’elemento.

### 4.1 Esempio con i link: `<a href="...">`

```html
<a href="https://www.google.com">Clicca qui</a>
```

- Tag: `a` (anchor, ancora/ipertesto).
    
- Attributo `href`: indica **a quale URL** deve portare il link.
    
- Contenuto: “Clicca qui” → il testo che l’utente vede e su cui clicca.
    

Senza `href`, l’elemento `a` non saprebbe dove portare.

---

### 4.2 Esempio con le immagini: `<img src="..." alt="...">`

```html
<img src="foto-vacanza.jpg" alt="Io al mare al tramonto" />
```

- Tag: `img`
    
- Attributo `src` (source): indica il **percorso del file immagine**.
    
- Attributo `alt` (testo alternativo): descrive l’immagine per:
    
    - screen reader;
        
    - casi in cui l’immagine non si carica;
        
    - accessibilità e SEO.
        

Qui **non abbiamo contenuto tra apertura e chiusura**, perché `img` è un elemento vuoto.

---

### 4.3 Attributo `lang` sull’elemento `<html>`

```html
<html lang="it">
```

- Tag: `html`
    
- Attributo `lang`: indica la **lingua principale** del documento.
    
- Valore `"it"`: italiano.
    

Importanza:

- Aiuta gli screen reader a usare la pronuncia corretta.
    
- Aiuta i motori di ricerca a capire la lingua della pagina.
    
- È una **best practice** di accessibilità: molti validatori lo richiedono.
    

---

### 4.4 Attributi booleani (senza valore esplicito)

Esistono attributi che possono essere scritti **senza valore**: il solo fatto che esistano li rende attivi.  
Esempio tipico:

```html
<button disabled>Invia</button>
```

Qui:

- `disabled` è un attributo booleano.
    
- Il bottone risulterà disabilitato (non cliccabile).
    

In HTML5 si può scrivere anche `disabled="disabled"`, ma la forma breve è molto usata.

---

## **5. L’attributo `style` e il collegamento al CSS**

Un attributo molto potente, che useremo solo in forma basilare, è:

```html
style="..."
```

Serve per aggiungere **stile CSS** direttamente su un singolo elemento.

Esempio:

```html
<h1 style="color: red;">Titolo rosso</h1>
```

- `style` è l’attributo.
    
- `color: red;` è **codice CSS**.
    
- Il risultato: il titolo viene mostrato in rosso.
    

È importante capire:

- **HTML** = struttura, “ossa” della pagina (sezioni, titoli, paragrafi, blocchi).
    
- **CSS** = stile, “pittura” della pagina (colori, font, dimensioni, layout).
    

Nel corso HTML useremo lo `style` solo per piccoli esempi, senza entrare davvero nella logica completa del CSS (che sarà oggetto di un corso a parte).

---

## **6. Riepilogo dei concetti chiave**

- Un **elemento HTML** è composto da:
    
    - tag di apertura;
        
    - (eventuale) contenuto;
        
    - tag di chiusura.
        
- Gli elementi possono essere **annidati**: elementi “figli” dentro elementi “padre”, come matrioske.
    
- Alcuni elementi sono **vuoti** (`<br>`, `<img>`, …): niente contenuto interno, niente tag di chiusura.
    
- Gli **attributi** arricchiscono gli elementi con informazioni extra:
    
    - `href` per i link;
        
    - `src` e `alt` per le immagini;
        
    - `lang` per la lingua del documento;
        
    - attributi booleani come `disabled`;
        
    - `style` per lo stile inline (CSS).
        

Questi concetti sono la base assoluta per tutto il resto: titoli, paragrafi, liste, link, immagini, form, layout, accessibilità.

---

Nella prossima lezione il prof passa a una distinzione importantissima:

> **Elementi “block” vs elementi “inline”**

Questa differenza è già rilevante in HTML e diventa **cruciale** quando inizieremo a lavorare con il CSS e con il layout delle pagine.