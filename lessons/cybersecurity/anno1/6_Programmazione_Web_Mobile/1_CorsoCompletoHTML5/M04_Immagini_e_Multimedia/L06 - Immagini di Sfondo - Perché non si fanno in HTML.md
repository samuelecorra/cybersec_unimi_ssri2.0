## **Lezione 6: Immagini di Sfondo — Perché _non_ si fanno in HTML e come funzionano in CSS

### 1. Il problema: “Come metto un'immagine di sfondo in HTML?”

È una domanda molto comune tra chi ha appena iniziato e conosce solo HTML.

La risposta è semplice e categorica:

> **In HTML non puoi mettere un’immagine come sfondo.  
> Le immagini di sfondo appartengono al CSS, non all’HTML.**

Per capire il perché, dobbiamo distinguere i due ruoli:

- **HTML = contenuto e struttura.**  
    Titoli, paragrafi, immagini “vere”, link, liste… tutto ciò che _fa parte del contenuto visibile_.
    
- **CSS = stile e presentazione.**  
    Colori, sfondi, bordi, allineamenti, dimensioni, decorazioni, layout.
    

HTML non è stato progettato per gestire:

- sfondi,
    
- colori,
    
- dimensioni manuali,
    
- allineamenti,
    
- posizionamento,
    
- immagini decorative.
    

Per queste cose serve CSS.

È il motivo per cui, finora, non abbiamo usato alcun colore né modificato alcun layout: HTML non può farlo.

---

### 2. Perché uno sfondo _non_ è un contenuto HTML

Un’immagine “di sfondo” non fa parte del contenuto semantico della pagina.  
Non ha significato, non è informazione, non è un nodo importante per la struttura.

È **decorazione pura** → e la decorazione appartiene al CSS.

Un `<img>` invece è **contenuto** (un'informazione vera e propria), e va quindi in HTML.

---

### 3. Come si mette davvero un’immagine di sfondo (CSS)

Vediamolo in pratica con un esempio semplice.

Supponiamo di avere un paragrafo:

```html
<p>Questo è un paragrafo di test</p>
```

Per trasformarlo in un elemento con immagine di sfondo:

```html
<p style="
    background-image: url('immagini/icona.webp');
    background-size: cover;
    background-position: center;
    height: 300px;
    color: white;
    padding: 20px;
">
    Questo è un paragrafo di test
</p>
```

### 3.1. Spieghiamo ogni proprietà

- **`background-image: url('...')`**  
    Imposta l’immagine di sfondo.
    
- **`background-size: cover`**  
    Fa sì che l’immagine copra tutto l’elemento, senza buchi.
    
- **`background-position: center`**  
    Allinea il punto centrale dell’immagine al centro dell’elemento.
    
- **`height: ...`**  
    Aggiunta solo per l’esempio: senza contenuto sufficiente, il paragrafo sarebbe troppo piccolo per vedere lo sfondo.
    
- **`color: white`**  
    Colore del testo per contrastare lo sfondo.
    
- **`padding: 20px`**  
    Aggiunge spazio interno per evitare testo attaccato ai bordi.
    

Tutte cose impossibili da ottenere **in puro HTML**.

---

### 4. Perché devi usare i singoli apici `'...'` dentro `url()`

Quando scrivi:

```css
background-image: url("immagini/icona.webp");
```

sei dentro a uno `style=" ... "` che usa già le virgolette doppie `"..."`.

Per evitare conflitti:

- **l’attributo HTML** usa `" "`,
    
- **l’URL CSS** usa `' '`.
    

Esempio corretto:

```html
<p style="background-image: url('immagini/icona.webp');">
```

Se usassi `" "` dentro `" "` l’HTML andrebbe in confusione.

---

### 5. Perché HTML da solo non può farlo

Riepiloghiamo il motivo fondamentale:

- HTML è un linguaggio **di struttura e contenuto**.  
    Non controlla presentazione né estetica.
    
- CSS è un linguaggio **di stile e layout**.  
    Gestisce sfondi, colori, forme, dimensioni, animazioni, layout responsive…
    

> L’immagine di sfondo è 100% CSS.  
> HTML non è progettato per farlo.

Per questo, durante tutto il corso HTML, non abbiamo mai:

- applicato colori,
    
- cambiato layout,
    
- aggiunto effetti,
    
- modificato allineamenti,
    
- messo sfondi.
    

Non è incompetenza: **HTML semplicemente non lo permette**.

---

### 6. La soluzione completa arriva nel corso CSS

Quando inizierai il corso CSS (che è il naturale successivo a HTML), imparerai:

- **background-image** in tutte le sue varianti,
    
- **background-size** (`cover`, `contain`, valori personalizzati),
    
- **background-position**,
    
- **gradienti come sfondi**,
    
- **immagini multiple come sfondo**,
    
- **sfondi fissi mentre la pagina scorre**,
    
- **overlay e filtri**,
    
- **layout avanzati**.
    

Il CSS è ciò che trasforma una pagina da:

- _struttura grezza_ → HTML puro,
    

in:

- _interfaccia grafica professionale_ → HTML + CSS.
    

---

### 7. Conclusione

L’immagine di sfondo:

- **non può** essere messa in HTML,
    
- **si mette** solo tramite CSS,
    
- **è parte dello stile**, non del contenuto.
    

Oggi hai visto:

- perché HTML non può farlo,
    
- come si fa davvero in CSS,
    
- e perché ha senso che sia così.
    

Da questo punto, possiamo tornare a lavorare sul puro HTML sapendo che:

- se vogliamo estetica → CSS,
    
- se vogliamo struttura → HTML.
    
