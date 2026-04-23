## **Lezione 5: Lazy Loading delle Immagini in HTML**

### 1. Cos’è il lazy loading (caricamento “pigro”)

**Lazy loading** = _caricamento pigro_.

L’idea è semplice:

- invece di **scaricare tutte le immagini subito** quando la pagina viene caricata,
    
- scarichi **solo quelle che l’utente vede subito a schermo**,
    
- e **rimandi il download delle altre** a quando l’utente scorre e sta per raggiungerle.
    

È “pigro” perché il browser fa il minimo indispensabile all’inizio, e carica il resto **solo quando serve davvero**.

Metafora:

- Devi portare su con l’ascensore dei mobili pesanti.
    
- Invece di caricarli tutti insieme facendo esplodere l’ascensore,
    
- fai **più viaggi**: prima le cose essenziali, poi il resto.
    

Con le immagini è uguale:

- prima carichi quelle **visibili subito**,
    
- poi, quando l’utente scorre, carichi le successive.
    

---

### 2. Viewport: cosa vede davvero l’utente

Quando una pagina si carica, tu:

- **non vedi tutta la pagina**, ma solo la parte che entra nella finestra del browser.
    

Quella parte visibile si chiama **viewport**.

- Il resto è “sotto” e diventa visibile solo quando fai **scroll**.
    
- Tuttavia, **senza lazy loading**, il browser scarica comunque tutte le immagini (anche quelle che stanno fuori dal viewport).
    

Con lazy loading, invece:

- le immagini **fuori dal viewport** vengono _posticipate_,
    
- e scaricate quando ti avvicini scorrendo.
    

---

### 3. Perché il lazy loading migliora performance ed esperienza utente

Senza lazy loading:

- pagina con 20 immagini pesanti,
    
- il browser scarica tutto subito,
    
- tempo di caricamento più lungo,
    
- l’utente aspetta anche per immagini che non vedrà mai (magari abbandona prima).
    

Con lazy loading:

- il browser scarica subito solo le immagini _above the fold_ (quelle visibili nel viewport iniziale),
    
- le altre vengono richieste **solo quando stai per vederle**,
    
- il **carico si distribuisce nel tempo**:
    
    - meno dati subito,
        
    - caricamento percepito più veloce,
        
    - migliore sensazione di “reattività”.
        

Vale sia:

- su **mobile** (dove la rete è più lenta o instabile),
    
- sia su **desktop**, perché puntiamo a caricamenti praticamente istantanei.
    

---

### 4. L’attributo `loading` su `<img>` (e `<iframe>`)

In HTML, il lazy loading delle immagini si imposta con un semplice attributo:

```html
<img src="immagini/foto-portfolio.webp" alt="Descrizione" loading="lazy">
```

L’attributo `loading` può avere principalmente due valori:

- `loading="lazy"` → **caricamento pigro**
    
    - il browser scarica l’immagine solo quando l’utente sta per vederla.
        
- `loading="eager"` → **caricamento “impaziente”**
    
    - il browser scarica l’immagine subito, anche se non è ancora visibile.
        

Concetto:

- `lazy` = “te la do solo quando serve, all’ultimo”
    
- `eager` = “dammi tutto subito, non voglio aspettare”
    

L’attributo `loading` funziona anche sugli **`<iframe>`** (che vedrai più avanti).

---

### 5. Supporto dei browser: cosa succede se non capiscono `loading`

I browser moderni (Chrome, Edge, Safari recenti…) hanno già il supporto a `loading="lazy"`.

Se un browser **non supporta** il lazy loading:

- vede l’attributo `loading`,
    
- non lo riconosce,
    
- **lo ignora semplicemente**,
    
- e carica le immagini come ha sempre fatto.
    

Quindi:

- **non si rompe nulla**,
    
- hai solo un miglioramento nei browser che lo supportano,
    
- e un comportamento normale in quelli che non lo supportano.
    

---

### 6. Quali immagini devono essere `eager` e quali `lazy`

Non ha senso mettere `loading="lazy"` su **tutte** le immagini a caso.

Dividiamo:

#### 6.1. Immagini che **non** devono essere lazy

Sono le immagini che:

- l’utente vede immediatamente quando arriva sulla pagina,
    
- fanno parte dell’**hero** iniziale o di sezioni critiche.
    

Esempi:

- Logo del sito.
    
- Immagine principale di un prodotto in una landing page.
    
- Banner in alto che spiega subito di cosa parla il sito.
    

Queste vanno lasciate **senza `loading`** (default eager)  
oppure, se vuoi essere esplicito:

```html
<img src="immagini/hero-prodotto.webp"
     alt="Scarpe da corsa con cuscinetto in gel"
     loading="eager">
```

#### 6.2. Immagini che possono (e devono) essere lazy

Tutte quelle che:

- stanno **sotto** il viewport iniziale,
    
- compaiono solo dopo lo scroll,
    
- fanno parte di gallerie, portfolio molto lunghi, sezioni “più in giù”.
    

Esempi:

```html
<img src="immagini/foto-portfolio-1.webp"
     alt="Dettaglio scarpa vista laterale"
     loading="lazy">

<img src="immagini/foto-portfolio-2.webp"
     alt="Dettaglio scarpa vista posteriore"
     loading="lazy">
```

Su mobile, attenzione:

- il viewport è più piccolo → meno immagini visibili subito,
    
- quindi ancora più immagini possono essere caricate in lazy rispetto al desktop.
    

---

### 7. Lazy loading e SEO: perché aiuta anche il posizionamento

Il lazy loading migliora **performance**, e le performance sono un pezzo della SEO tecnica.

Cosa succede se:

- la pagina è più leggera all’inizio,
    
- si carica più velocemente,
    
- gli utenti restano invece di uscire.
    

Effetti:

- **migliore esperienza utente** → meno rimbalzi,
    
- **migliori segnali per Google** (Core Web Vitals, ecc.),
    
- **punteggi migliori in strumenti come Lighthouse**,
    
- maggiori probabilità di essere premiato nei risultati di ricerca.
    

Il lazy loading, quindi:

- non è “solo” un trucco di performance,
    
- ma uno strumento per rendere il sito più competitivo lato SEO.
    

---

### 8. Schema mentale finale

Quando imposti immagini in HTML, pensa a tre livelli:

1. **Formato**
    
    - Preferisci formati moderni (WebP, poi AVIF/JPEG XL).
        
2. **Ottimizzazione dell’immagine**
    
    - Peso ridotto (KB),
        
    - Dimensioni in pixel coerenti con lo spazio che occupano,
        
    - Nome file descrittivo e SEO-friendly.
        
3. **Strategia di caricamento**
    
    - Immagini immediatamente visibili → `eager` o default.
        
    - Immagini lontane dallo sguardo iniziale → `loading="lazy"`.
        

Con questo, le immagini non sono più solo “decorazioni”, ma diventano:

- **ottimizzate**,
    
- **veloci**,
    
- **SEO consapevoli**,
    
- e caricate in modo intelligente in base al comportamento dell’utente.