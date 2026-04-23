## **Lezione 8: Esercizio 6 – Testo e immagini (con image map)
### 1. Obiettivo dell’esercizio

In questo esercizio si costruisce una pagina HTML che:

- contiene **testo** già visto negli esercizi precedenti;
    
- integra **due immagini** (almeno);
    
- richiede che **una di queste immagini** abbia **almeno due aree mappate** tramite `image map`, ognuna con il proprio link.
    

È un esercizio che:

- consolida l’uso corretto dell’elemento `<img>`;
    
- introduce l’uso pratico delle **image map** (`<map>` e `<area>`);
    
- insiste su buone pratiche: nomi dei file, `alt`, `width`/`height`, `rel="noopener"`, verifiche con gli strumenti di sviluppo.
    

---

### 2. Preparazione: immagini e file

1. Scaricare le immagini (es. da Unsplash):
    
    - `montagna-in-lontananza.webp`
        
    - `vista-montagne-innevate.webp`
        
2. Posizionarle nella cartella del progetto, ad esempio:
    

```text
/immagini/montagna-in-lontananza.webp
/immagini/vista-montagne-innevate.webp
```

3. Dare **nomi chiari e descrittivi**, separando le parole con trattini:
    

- `montagna-in-lontananza.webp`
    
- `vista-montagne-innevate.webp`
    

Questo aiuta sia l’organizzazione del progetto sia il lato SEO.

---

### 3. Inserire la prima immagine “in evidenza” (hero)

L’idea è avere una **prima immagine in alto**, come immagine di copertina dell’articolo.

Esempio:

```html
<img
    src="immagini/montagna-in-lontananza.webp"
    alt="Vista di montagne in lontananza durante il tramonto"
    width="400"
    height="266"
/>
```

Punti chiave:

- `src` → percorso corretto all’interno del progetto.
    
- `alt` → descrizione reale dell’immagine, utile per:
    
    - accessibilità,
        
    - SEO,
        
    - casi in cui la risorsa non si carica.
        
- `width` e `height` → valori coerenti con le dimensioni reali:
    
    - aiutano il browser a riservare lo spazio corretto,
        
    - migliorano la stabilità del layout (evitano “salti” in caricamento),
        
    - sono ben visti dagli strumenti come Lighthouse.
        

La prima immagine, essendo in cima alla pagina, è perfetta anche come futura **immagine di anteprima** (per Facebook, Twitter, ecc.) quando imposterai i vari meta tag Open Graph.

---

### 4. Inserire la seconda immagine all’interno del contenuto

Si inserisce una seconda immagine in una sezione successiva dell’articolo (per esempio prima di una sezione 3):

```html
<img
    src="immagini/vista-montagne-innevate.webp"
    alt="Vista di montagne innevate"
    title="Montagne innevate"
/>
```

Note:

- `title` mostra un tooltip al passaggio del mouse, ma può creare rumore con alcuni screen reader: va usato con criterio.
    
- In questo esercizio, su questa immagine si andrà a costruire la **mappa immagine**.
    

Per questa seconda immagine, bisogna stare attenti alle dimensioni:

- se si usa una **image map**, le coordinate dipendono dalle dimensioni reali,
    
- se si cambia artificialmente `width`/`height` via HTML o CSS senza aggiornare le coordinate, la mappa si “sposta” e non combacia più.
    

Per l’esercizio, conviene **non manipolare** le dimensioni di questa immagine finché si lavora sul mapping.

---

### 5. Collegare l’immagine alla mappa con `usemap`

Per rendere la seconda immagine “mappabile”, si aggiunge l’attributo `usemap`:

```html
<img
    src="immagini/vista-montagne-innevate.webp"
    alt="Vista di montagne innevate"
    usemap="#mappa-montagna"
/>
```

- `usemap="#mappa-montagna"` dice all’immagine:
    
    > “Utilizza la mappa chiamata `mappa-montagna`.”
    

Poi, più sotto nel codice, si definisce la mappa:

```html
<map name="mappa-montagna">
    <!-- Qui dentro le varie <area> -->
</map>
```

Attenzione: per `<map>` si usa **name**, non `id`.

---

### 6. Creare le aree cliccabili con `<area>`

L’obiettivo è che **almeno due zone** dell’immagine portino a due pagine diverse, per esempio:

- una zona che porta a `esercizio_3.html`
    
- una zona che porta a `esercizio_4.html`
    

In un tool online di image mapping si:

1. carica l’immagine `vista-montagne-innevate.webp`;
    
2. selezionano zone rettangolari (o cerchi, poligoni) direttamente con il mouse;
    
3. si assegnano:
    
    - `href` (link),
        
    - `title`,
        
    - eventuale `target`.
        

Il tool genera qualcosa di questo tipo:

```html
<map name="mappa-montagna">
    <area
        shape="rect"
        coords="12,34,150,120"
        href="esercizi/esercizio_3.html"
        target="_self"
        alt="Area 1"
        title="Prova 1"
    >
    <area
        shape="rect"
        coords="160,34,300,120"
        href="esercizi/esercizio_4.html"
        target="_self"
        alt="Area 2"
        title="Prova 2"
    >
</map>
```

---

### 7. Migliorie di sicurezza: `rel="noopener"`

Per i link che si aprono in **nuova scheda** (`target="_blank"`), è buona pratica aggiungere:

```html
rel="noopener"
```

Ad esempio:

```html
<area
    shape="rect"
    coords="12,34,150,120"
    href="esercizi/esercizio_3.html"
    target="_blank"
    rel="noopener"
    alt="Esercizio 3"
    title="Vai all'esercizio 3"
/>
```

Perché?

- Evita che la nuova pagina possa interagire con la finestra che l’ha aperta tramite `window.opener`.
    
- È una raccomandazione di sicurezza della documentazione HTML/MDN.
    

Molti generatori di mapping:

- compilano automaticamente `href`, `title`, `target`,
    
- **non** aggiungono `rel="noopener"` → va aggiunto a mano.
    

---

### 8. Verifica del funzionamento della mappa

Una volta inserita la `map` e collegata con `usemap`:

1. Aprire la pagina nel browser.
    
2. Spostare il mouse sull’immagine:
    
    - l’area “normale” mostra il cursore standard;
        
    - le aree mappate mostrano la **manina** (cursor: pointer).
        
3. Cliccare nelle aree:
    
    - la prima area porta a `esercizio_3.html`;
        
    - la seconda area porta a `esercizio_4.html`.
        

Se cliccando si viene portati alla pagina sbagliata o in una zona “strana”, è probabile che:

- le coordinate non corrispondano più alle dimensioni attuali dell’immagine,  
    ad esempio perché è stata cambiata `height` o `width`.
    

---

### 9. Controllare il codice con Lighthouse e DevTools

Per abituarsi a una mentalità professionale, si può usare:

- **DevTools** (`F12`)
    
- scheda **Lighthouse** o analogo strumento di audit
    

Cosa controllare:

- immagini con `alt` corretti;
    
- presenza di `width` e `height` per le immagini dove non c’è mapping critico;
    
- link con `target="_blank"` che abbiano anche `rel="noopener"`;
    
- eventuali warning su accessibilità/performance.
    

Esempi di miglioramenti:

- aggiungere `width` e `height` alla prima immagine (hero);
    
- aggiungere `rel="noopener"` alle `<area>` che aprono nuove schede.
    

L’obiettivo non è usare Lighthouse ossessivamente, ma capire:

> Se scrivo codice pulito fin da subito, gli strumenti automatici mi daranno subito punteggi alti senza dover “riparare” dopo.

---

### 10. Ricapitolazione dell’esercizio

In questo esercizio hai:

1. Inserito due immagini con:
    
    - nomi file descrittivi,
        
    - `alt` significativi,
        
    - dimensioni impostate dove opportuno.
        
2. Impostato una delle immagini come **base di una mappa immagine**, usando:
    
    - `usemap="#mappa-montagna"` sull’`<img>`;
        
    - `<map name="mappa-montagna">` come contenitore.
        
3. Creato **almeno due aree cliccabili** con `<area>`:
    
    - `shape` (rettangolo),
        
    - `coords` (coordinate assolute),
        
    - `href` verso altri esercizi,
        
    - `title` per il tooltip,
        
    - `rel="noopener"` per sicurezza (quando serve).
        
4. Usato gli strumenti del browser per:
    
    - verificare che i link funzionino,
        
    - controllare l’accessibilità e le performance.
        

Con questo, hai chiuso il capitolo dedicato alle immagini “base” (inserite nel contenuto, ottimizzate e mappate) e sei pronto a passare agli elementi pensati apposta per testo + immagine: **`<figure>` e `<figcaption>`.