## Lezione 9: L’elemento `<figure>` e la descrizione semantica dei contenuti multimediali

### 1. Perché esiste `<figure>`

L’elemento `<figure>` è stato introdotto in HTML5 per risolvere un problema molto semplice ma molto frequente:

> **Associare un contenuto multimediale (immagine, codice, citazione, poesia…) alla sua descrizione o didascalia.**

È il classico effetto “libro di scuola”:

- immagine,
    
- sotto una didascalia coerente e semanticamente legata a quella immagine.
    

Prima di `<figure>` si usava un `<div>` o un `<p>` “piazzato a caso”. Funzionava, ma non era semanticamente corretto.  
Con `<figure>` e `<figcaption>`:

- il browser capisce la relazione,
    
- gli screen reader la leggono correttamente,
    
- i motori di ricerca lo interpretano meglio,
    
- il codice rimane pulito e chiaro.
    

---

### 2. La struttura base di `<figure>`

Il blocco è sempre questo:

```html
<figure>
    <img src="immagini/prova.jpg" alt="Breve descrizione dell’immagine">
    <figcaption>Descrizione dell’immagine</figcaption>
</figure>
```

Risultato:

- l’immagine appare,
    
- sotto appare la didascalia _senza spazi strani_,
    
- l’intero blocco è letto come un’unica “unità logica”.
    

---

### 3. Dimostrazione pratica (come nel video)

Partiamo dalla struttura:

```html
<figure>
    <img src="immagini/prova.jpg" alt="Una bellissima prova" height="300">
    <figcaption>Classica descrizione dell’immagine</figcaption>
</figure>
```

Osservazioni:

- A differenza di un `<p>` messo sotto l’immagine, `<figcaption>` è allineato e formattato _esattamente_ come vuole lo standard.
    
- L’immagine e la sua descrizione diventano un’unica entità semantica.
    

---

### 4. Aggiungere crediti o link nell’area del figcaption

Spesso i siti di foto (Unsplash, Pexels…) chiedono di inserire un credito.  
Lo standard vuole che la descrizione dell’immagine _comprenda anche i crediti_.

Esempio:

```html
<figure>
    <img src="immagini/montagna.jpg" alt="Montagne al tramonto">
    <figcaption>
        Crediti: Foto di
        <a href="https://unsplash.com" target="_blank" rel="noopener">
            Luca Rossi su Unsplash
        </a>
    </figcaption>
</figure>
```

Il blocco rimane perfettamente semantico.

---

### 5. `<figure>` non è solo per immagini

Il suo scopo è **associare un contenuto a una didascalia**.  
Quindi dentro `<figure>` puoi mettere praticamente qualunque cosa:

- codice,
    
- citazioni (`<blockquote>`),
    
- poesie,
    
- diagrammi ASCII (con `<pre>`),
    
- SVG,
    
- tabelle,
    
- perfino video.
    

L’importante è che _figcaption descriva quel contenuto_.

---

### 6. Esempio: codice + figcaption

Supponiamo di voler descrivere un pezzo di codice:

```html
<figure>
    <figcaption>Esempio di image mapping – primo frammento</figcaption>

    <pre>
<code>
<area shape="rect" coords="12,34,150,120" href="..." />
</code>
    </pre>
</figure>
```

La descrizione spiega cosa rappresenta quel codice.

---

### 7. Esempio: citazione con `<blockquote>`

```html
<figure>
    <figcaption>Luca Rossi – massima di vita</figcaption>
    <blockquote>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi porta elit in arcu varius.
    </blockquote>
</figure>
```

Gli screen reader interpreteranno correttamente che:

- la citazione è di Luca Rossi,
    
- è una “figura” autonoma nel testo.
    

---

### 8. Esempio: poesia con `<pre>` o formattazione libera

```html
<figure>
    <figcaption>Poesia tratta da “Un libro a caso”, autore sconosciuto</figcaption>

    <pre>
Dove il vento passa lento
e la luna guarda il mare,
resto fermo ad aspettare.
    </pre>
</figure>
```

Uso di `<pre>`:

- mantiene gli a capo,
    
- mantiene gli spazi,
    
- rispetta esattamente la struttura testuale originale.
    

---

### 9. Regole semantiche da ricordare

1. **`<figcaption>` può trovarsi sia sopra che sotto** l’elemento principale.  
    Sotto è più comune, sopra è totalmente valido.
    
2. `<figure>` **non va usato per qualsiasi immagine**, ma per quelle che:
    
    - richiedono una descrizione,
        
    - hanno un ruolo importante nell'articolo (immagine principale, grafico, schema, foto storica…).
        
3. Non è obbligatorio usare `<figure>` se l’immagine è puramente decorativa.
    
4. `<figcaption>` è facoltativo:  
    `<figure>` _può esistere anche senza_.
    

---

### 10. Quando usare `<figure>` in modo professionale

Usalo sempre quando:

- vuoi corredare un’immagine di una descrizione completa;
    
- vuoi inserire crediti fotografici;
    
- vuoi mostrare codice commentato;
    
- vuoi presentare diagrammi, grafici, tabelle con descrizione;
    
- vuoi includere citazioni o poesie come elementi “autonomi”.
    

Non usarlo quando:

- l’immagine è puramente decorativa (in quel caso metti `alt=""`);
    
- l’immagine è parte integrante del flusso del testo e non richiede didascalia.
    

---

### 11. Conclusione

L’elemento `<figure>`:

- è semplice,
    
- è potente,
    
- è semantico,
    
- si usa per contenuti (immagini, codici, citazioni, poesie) con _significato_ proprio,
    
- permette di raggruppare contenuto + descrizione in un blocco pulito, leggibile, professionale.
    

È uno degli elementi che più migliorano:

- accessibilità,
    
- SEO,
    
- leggibilità del codice,
    
- ordine semantico.
    

Quando sei pronto, procediamo con l’elemento **`<picture>`**.