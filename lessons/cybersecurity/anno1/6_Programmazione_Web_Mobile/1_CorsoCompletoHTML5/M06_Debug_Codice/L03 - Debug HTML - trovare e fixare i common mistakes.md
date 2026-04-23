## **Lezione 3: Debug HTML: trovare e correggere gli errori più comuni**

Questa è l’ultima lezione del mini-modulo sul **debug** e chiude il cerchio su tutto ciò che può andare storto quando scrivi HTML.  
Hai già visto:

- come usare la **Developer Tool**,
    
- come validare il codice col **W3C Validator**,
    
- e ora analizziamo gli **errori reali**, quelli che capitano davvero mentre codifichi.
    

L’obiettivo è imparare a riconoscerli _immediatamente_, perché HTML purtroppo **non segnala quasi nulla da solo**.  
L’interprete dei browser è molto permissivo, quindi _corregge da solo_ molti errori → e questo rende difficile accorgersene.

---

# **1. Errori con le virgolette degli attributi**

### ❌ Caso: virgoletta mancante in `src`, `href`, ecc.

Esempio:

```html
<img src="immagini/montagne.webp>
```

### Come te ne accorgi:

- VSCode mostra **rossi** i pezzi successivi.
    
- A volte NON lo vedi sullo scroll bar a destra.
    
- Il browser prova comunque a risolvere l’immagine → quindi la pagina _sembra funzionare_, ma l’HTML è rotto.
    

### Come risolvere:

- Chiudi l’attributo correttamente.
    
- Se Prettier è attivo, spesso lo **sistemerà lui** al salvataggio.
    
- Se non si vede nessun errore evidente, controlla F12 → “Elements”: lì si vede subito.
    

---

# **2. Tag annidati male (tipico: `<p>` dentro `<h2>`)**

Caso classico:

```html
<h2>
  Sezione due
  <p>Questo paragrafo è stato messo dentro l’h2 per errore</p>
</h2>
```

### Come te ne accorgi:

- L’H2 sembra “impazzito”:  
    tutto diventa grande e in grassetto.
    
- Il testo _non appare dove dovrebbe_, o appare con uno stile sbagliato.
    
- Con **F12 → Inspect** ti accorgi subito dell’errore: il `<p>` è _dentro_ `<h2>`.
    

### Soluzione:

- Estrarre l’elemento e chiudere correttamente i tag.
    

---

# **3. Tag NON chiusi**

Esempio:

```html
<h2>Sezione due
<p>Testo della sezione</p>
```

### Sintomi:

- Layout completamente rotto.
    
- Il browser “allunga” l’H2 su tutto il resto della pagina.
    
- Prettier segnala:  
    **“Unexpected closing tag H2”** → vuol dire che l’apertura **manca**.
    

### Come trovare il punto preciso:

1. Cerca `</h2>` con **CTRL + F**.
    
2. Controlla se l’apertura c’è.
    
3. Controlla l’indentazione via Developer Tool.
    
4. Chiudi correttamente il tag.
    

---

# **4. Errori nel testo degli attributi (apici interni)**

HTML consente:

```html
src="foto.jpg"
```

Ma se scrivi:

```html
src='immagini/montagne d'autunno.webp'
```

ROMPI tutto:

- Il browser interpreta l’apice di _d’autunno_ come **fine attributo**.
    
- Risultato → l’immagine non si carica e l’HTML dopo è corrotto.
    

### Regola d’oro:

- **Mai** inserire apici del _tipo scelto_ dentro l’attributo.
    
- Soluzione immediata:
    

Se dentro hai apostrofi → usa **doppi apici** fuori.

```html
src="immagini/montagne d'autunno.webp"
```

Se dentro hai doppi apici → usa **singoli apici** fuori.

---

# **5. Tag mancanti del tutto**

Esempio:

```html
</body>
</html>
```

senza aver aperto `<html>` o `<body>`.

Il browser tenta di autocorreggere, ma VSCode:

- mostra errori rossi,
    
- indica “Unexpected closing tag”,
    
- e il layout della pagina risulta completamente rotto.
    

### Come risolvere:

- Controlla la struttura principale.
    
- Usa **il metodo rapido**:
    
    - CTRL + F → “html”
        
    - CTRL + F → “body”
        
- Verifica che ogni apertura abbia una chiusura.
    

---

# **6. Mancanza dell’attributo fondamentale (`href`, `src`, ecc.)**

Esempio:

```html
<a>Link</a>
```

→ Non funziona.

Come verificarlo:

- Con F12 → click su “select element”.
    
- Clicca l’elemento.
    
- Se manca `href`, lo vedi immediatamente.
    

---

# **7. Il browser SI corregge da solo (ed è un problema)**

Molti errori HTML:

- non rompono la pagina,
    
- ma rompono **l'accessibilità**,
    
- o **la semantica**,
    
- o **la struttura del DOM**,
    
- o creano problemi quando si aggiunge CSS/JS.
    

È per questo che:

### Gli strumenti di debug servono a NOI, non al browser.

Il browser “tappa i buchi”.  
Ma noi dobbiamo scrivere codice pulito, perché:

- CSS si basa sulla struttura corretta,
    
- JavaScript DEVE trovare gli elementi in modo preciso,
    
- accessibilità e SEO dipendono dalla semantica.
    

---

# **8. Come riconoscere errori complessi → Developer Tool**

La Developer Tool serve per:

✔ vedere esattamente com’è diventato il DOM dopo che il browser ha “riparato” il codice,  
✔ trovare tag annidati male,  
✔ capire dove un elemento non dovrebbe trovarsi,  
✔ rintracciare box fuori posto,  
✔ capire se una regola CSS non si applica perché il tag è sbagliato,  
✔ individuare attributi mancanti.

### Come usarla:

- F12 → **Elements**
    
- clicca l’icona del selettore  
    (la freccia → rettangolo)
    
- clicca l’elemento incriminato
    

E vedi immediatamente:

- dove si trova nella struttura,
    
- quali tag lo contengono,
    
- quale attributo manca o è rotto.
    

---

# **9. Dispositivi mobili (preview responsive)**

La Developer Tool ti permette anche di:

- simulare vari device,
    
- simulare smartphone/tablet,
    
- testare la larghezza,
    
- testare il comportamento responsivo.
    

In HTML puro serve a poco,  
ma con CSS diventerà _fondamentale_.

---

# **10. Conclusione del modulo Debug**

Hai imparato a:

- individuare errori di virgolette,
    
- trovare tag annidati male,
    
- riconoscere tag non chiusi,
    
- evitare apici interni pericolosi,
    
- usare il validatore W3C,
    
- usare il Developer Tool per analizzare il DOM,
    
- interpretare i segnali di Prettier e VSCode.
    

HTML non ha un vero “debugger”:  
**sei tu il debugger.**

La Developer Tool è il tuo microscopio.  
Il Validator è il tuo secondo cervello.  
Prettier è il tuo assistente.