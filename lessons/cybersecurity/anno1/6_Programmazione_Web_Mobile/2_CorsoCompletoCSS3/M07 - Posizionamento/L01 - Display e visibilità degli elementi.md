## **Lezione 1: Display e visibilità degli elementi**

### **1. Introduzione: perché parliamo di display?**

Il posizionamento degli elementi è la base di qualunque **layout**:  
prima di usare flex, grid, colonne, header, sidebar ecc., devi capire **come ogni elemento “si presenta” sulla pagina**.

Questa “forma base” è controllata principalmente da una proprietà: **`display`**.

`display` risponde a domande del tipo:

- occuperà tutta la riga o solo lo spazio del contenuto?
    
- potrà stare affiancato ad altri elementi o no?
    
- potrà avere margini verticali (sopra/sotto) o solo orizzontali?
    
- si comporta come un blocco o come una parola nel testo?
    
- è visibile oppure no?
    

In questa lezione vediamo:

- `display: block`
    
- `display: inline`
    
- `display: inline-block`
    
- `display: none`
    
- confronto con `visibility: hidden`
    

Flex e grid li useremo **dopo**, perché sono “super poteri” di layout costruiti sopra queste basi.

---

### **2. Il comportamento “di fabbrica”: user agent stylesheet**

### **2.1 Div e span a confronto**

Partiamo da questo HTML:

```html
<div>ciao</div>
<span>ciao</span>
```

A schermo:

- il `<div>` va a capo, occupa tutta la riga
    
- lo `<span>` rimane nella stessa riga, come una parola
    

Se apri gli strumenti dev del browser (F12) e selezioni il `<div>`, vedi nello **user agent stylesheet**:

```css
div {
  display: block;
}
```

Se selezioni `<span>`, vedi che **non** ha `display: block`; di default, il browser lo tratta come:

```css
span {
  display: inline;
}
```

Quindi:

- `div` → **block**
    
- `span` → **inline**
    

Queste sono convenzioni **universali**, decise dagli standard e implementate in tutti i browser.

---

### **3. Display: block vs inline**

### **3.1 `display: block`**

Un elemento con `display: block`:

- occupa **tutta la riga** disponibile
    
- manda a capo quello che viene dopo
    
- può contenere altri elementi (block o inline)
    
- reagisce normalmente a margini e padding **sia orizzontali che verticali**
    

Esempi tipici di elementi block (di default):

- `div`
    
- `p`
    
- `section`
    
- `header`
    
- `footer`
    
- `ul`, `ol`, `li`
    

Se scrivi:

```html
<div>Prima</div>
<div>Dopo</div>
```

Vedrai:

- “Prima” su una riga
    
- “Dopo” su una nuova riga
    

perché ognuno prende l’intera riga.

---

### **3.2 `display: inline`**

Un elemento con `display: inline`:

- **non** occupa tutta la riga
    
- si comporta come una parola in una frase
    
- può stare affiancato ad altri inline
    
- **non** rompe la riga
    
- **non** applica margini verticali (top/bottom)
    

Esempi tipici di elementi inline di default:

- `span`
    
- `a`
    
- `strong`
    
- `em` (`<i>`, `<b>`)
    
- altri elementi “di formattazione del testo”
    

Se scrivi:

```html
<span>ciao</span>
<span>come stai?</span>
```

Vedrai: `ciao come stai?` tutto sulla stessa riga (se c’è spazio).

---

### **4. Cambiare il display di un elemento**

Puoi **forzare** il comportamento usando `display` in CSS.

### **4.1 Rendere block uno span**

```css
span {
  display: block;
}
```

Ora ogni `<span>`:

- occupa tutta la riga
    
- va a capo come se fosse un `div`
    

### **4.2 Rendere inline un div**

```css
div {
  display: inline;
}
```

Ora i `<div>` si comportano come parole:

- stanno sulla stessa riga
    
- non prendono tutta la larghezza
    

Questo dimostra che non è “magico” che `div` sia block:  
è semplicemente perché lo user agent gli assegna `display: block`.

---

### **5. Il problema dei margini verticali sugli elementi inline**

### **5.1 Esempio con `div` (block)**

```css
div {
  margin-top: 10px;
  margin-bottom: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  border: 2px solid red;
}
```

Risultato:

- il padding (verde) sopra e sotto si vede
    
- il margine (arancione) sopra e sotto si vede
    
- lo spazio totale cresce correttamente
    

### **5.2 Stesso codice su `span` (inline)**

```css
span {
  margin-top: 10px;
  margin-bottom: 10px;
  padding-top: 10px;
  padding-bottom: 10px;
  border: 2px solid red;
}
```

Risultato:

- il **padding** si vede (il box cresce)
    
- il **bordo** intorno al testo si vede
    
- ma il **margine verticale** (top e bottom) **non ha effetto**
    

Negli strumenti dev vedrai:

- padding e bordi presenti
    
- margini verticali impostati ma **non applicati** alla disposizione del layout
    

Motivo: gli elementi inline sono pensati come “pezzi di testo”, e non possono “spingere” verso l’alto o verso il basso la riga come farebbe un block.

---

### **6. `display: inline-block`: il “mischione” utile**

`inline-block` è una combinazione di:

- **inline** → può stare affiancato ad altri elementi sulla stessa riga
    
- **block** → può avere larghezza/altezza, padding e margini verticali
    

Esempio:

```css
span {
  display: inline-block;
  margin-top: 10px;
  margin-bottom: 10px;
  padding: 10px;
  border: 2px solid red;
}
```

Risultato:

- lo `<span>` non occupa tutta la riga (come un inline)
    
- ma ora i **margini verticali funzionano**
    
- viene trattato come un piccolo “blocchetto” allineato in linea
    

È utilissimo per:

- bottoni inline
    
- piccoli box affiancati
    
- etichette, tag, pillole ecc.
    

---

### **7. `display: none` e `visibility: hidden`**

Ora vediamo la parte “nascondi/mostra”.

### **7.1 `display: none`**

Esempio:

```html
<div style="background-color: orange;">
  Contenuto
</div>
```

```css
div {
  display: none;
}
```

Effetto:

- l’elemento **non è più visibile**
    
- ma soprattutto è come se **non esistesse nel layout**:
    
    - non occupa spazio
        
    - gli elementi sotto si “chiudono” al suo posto
        

È come se lo togliessi dall’HTML (dal punto di vista visivo/layout), pur lasciandolo nel DOM.

Lo useremo tantissimo per:

- menu a comparsa
    
- modali
    
- elementi che appaiono solo in certe condizioni
    

---

### **7.2 `visibility: hidden`**

```css
div {
  visibility: hidden;
}
```

Effetto:

- il contenuto, il bordo e lo sfondo **spariscono**
    
- ma **lo spazio rimane occupato**
    

È come dire: “questo slot resta lì, ma al momento è vuoto e invisibile”.

Quindi:

- `display: none` → rimuove dal flusso, non occupa più spazio
    
- `visibility: hidden` → nasconde, ma **mantiene lo spazio**
    

### **7.3 Interazione tra `display` e `visibility`**

Se scrivi:

```css
div {
  display: none;
  visibility: hidden;
}
```

Conta di più `display: none`:

- l’elemento non c’è nel layout
    
- `visibility` non ha alcun effetto visibile (perché non c’è proprio niente da mostrare)
    

---

### **8. Quando usare uno e quando l’altro**

- **`display: none`**
    
    - vuoi che l’elemento **sparisca e liberi spazio**
        
    - es. apri/chiudi un accordion, un menu, una sezione
        
- **`visibility: hidden`**
    
    - vuoi che lo spazio rimanga **riservato**
        
    - es. tabelle, griglie, pannelli dove alcuni slot sono temporaneamente vuoti ma non devono “far chiudere” lo schema
        

Nel web moderno di solito vedi più spesso `display: none`,  
ma `visibility: hidden` torna utile in casi specifici di layout, o quando la coerenza delle colonne/righe è importante.

---

### **9. Ricapitolando**

- `display: block` → occupa tutta la riga, supporta padding/margini verticali
    
- `display: inline` → si comporta come testo, niente margini verticali
    
- `display: inline-block` → via di mezzo: sta in linea, ma ha comportamento “da blocco” sui margini/size
    
- `display: none` → elemento completamente rimosso dal layout
    
- `visibility: hidden` → elemento invisibile, ma lo spazio resta
    

Questa è la base del posizionamento:  
prima capisci **che tipo di “scatola” è l’elemento**, poi sopra ci costruisci layout più complessi (flex, grid, layout responsive).

Nella prossima lezione inizieremo a parlare della proprietà **`position`**, che ci permetterà di spostare gli elementi rispetto al loro contesto (static, relative, absolute, fixed, sticky).