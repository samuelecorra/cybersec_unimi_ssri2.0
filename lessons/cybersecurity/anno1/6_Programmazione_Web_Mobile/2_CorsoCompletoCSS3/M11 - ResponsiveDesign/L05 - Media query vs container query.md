**Lezione 5: Media query vs container query: cosa cambia davvero**

Finora abbiamo ragionato solo in termini di **media query**:

- guardo la dimensione del **device** (viewport)
    
- se lo schermo è più largo o più stretto di una certa soglia → applico CSS diversi
    

Esempio classico:

- sopra una certa larghezza → 4 card per riga
    
- a metà → 2 card per riga
    
- su mobile → 1 card per riga
    

Con le **container query** cambia il punto di vista:

> Non guardo più solo quanto è grande lo schermo,  
> ma quanto è grande il **contenitore** di un elemento.

“Container” qui significa “l’elemento che contiene altri elementi”, esattamente come abbiamo sempre chiamato “container” un wrapper, un div di layout, una card, una sezione, ecc.

L’idea è:

- invece di dire: “se il device è largo tot, fai questo layout”
    
- posso dire: “se **questa card** è larga meno di tot, cambia design al suo interno”
    

Quindi il CSS non si adatta solo al **device**, ma anche alla **dimensione del singolo componente**.

---

**Esempio mentale: card che cambia struttura quando è stretta**

Immagina una card classica:

- immagine in alto
    
- testo sotto
    
- card con bordo arrotondato, ombra, ecc.
    

Quando la card è bella larga, questo layout va benissimo: l’immagine risalta, il testo sotto è leggibile.

Ma se la card diventa **molto stretta** (perché il container di pagina si restringe), potresti preferire un layout completamente diverso. Ad esempio:

- immagine che riempie tutta la card
    
- testo sovrapposto sopra l’immagine, centrato, con colore bianco
    

Questa è una logica “dipendente dalla card”:

> “Se la larghezza della card è piccola, trasformala in card ‘full image’ con testo sopra”.

Con le media query classiche dovresti ragionare così:

- se lo schermo è sotto X px → suppongo che anche le card siano piccole → cambio layout
    

Con le **container query**, invece, puoi fare:

- se **questa card** è larga meno di 275px → applico questa variante di layout
    
- indipendentemente dal fatto che il device sia mobile, tablet, desktop, finestra ristretta, ecc.
    

È un cambio di mentalità enorme.

---

**Passo 1: definire che cos’è un container**

Per usare le container query, devi prima dire al browser _“questo elemento va considerato un container”_.

Nel nostro esempio:

- prima avevamo un “container” di griglia (un wrapper che contiene una serie di card)
    
- ora vogliamo che **ogni singola card** sia vista come un container per il suo contenuto
    

Quindi:

- il div `.container-grid` è il contenitore della griglia (non ci interessa per la container query)
    
- il div `.card` diventa il _container logico_ per immagine + testo
    

Sul CSS, l’idea è:

```css
.card {
  container-type: inline-size;
  /* opzionale, ma consigliato: */
  container-name: card;
}
```

Note importanti:

- `container-type: inline-size` significa che il container considera **solo la larghezza** (inline axis) come dimensione di riferimento per le query; l’altezza viene lasciata fluida in base al contenuto.
    
- `container-type: size` invece considera sia larghezza che altezza, ma è più raro e più “rigido”: lo userai solo se ti serve davvero.
    
- `container-name: card` è opzionale, ma utile se vuoi riferirti a questo container per nome; nel primo esempio si può anche omettere, perché il browser capisce comunque che l’elemento `.card` è un container.
    

---

**Passo 2: scrivere una container query con @container**

La sintassi è simile alle media query, ma usiamo `@container` invece di `@media`.

Idea:  
“Se la card è larga al massimo 275px, cambia layout interno.”

```css
@container (max-width: 275px) {
  /* qui dentro scriviamo il CSS che deve
     valere solo quando la card è stretta */
}
```

A livello di tooling:

- molti editor (tipo VS Code) ancora non suggeriscono bene `container-type`, `@container`, ecc.
    
- spesso li segnano come “proprietà sconosciute” perché sono molto nuovi
    
- il codice però funziona nei browser moderni anche se il tuo editor si lamenta
    

---

**Applicazione pratica: card con immagine + testo sovrapposto**

Immaginiamo di avere una card strutturata tipo grid:

- card con display: grid
    
- immagine in una riga
    
- testo in un’altra riga
    

Nella **versione stretta** vogliamo:

- immagine con altezza uguale a tutta la card
    
- testo sovrapposto all’immagine
    
- card con bordi arrotondati
    
- testo in bianco
    

Dentro la container query possiamo fare qualcosa del genere:

```css
@container (max-width: 275px) {
  .card {
    position: relative;
    border-radius: 20px;
    /* box-shadow e altre cose le puoi tenere o togliere */
    grid-template-rows: auto; /* adattiamo le righe alla nuova struttura */
  }

  .card img {
    height: 100%;
    width: 100%;
    object-fit: cover;
    border-radius: 20px; /* per far combaciare l’immagine col bordo card */
  }

  .card .contenuto {
    position: absolute;
    top: 0;
    left: 0;
    padding: 10px;
    color: white;
    /* altre proprietà per leggibilità del testo, sfondo semi-trasparente, ecc. */
  }
}
```

Risultato:

- quando la card è più larga di 275px → layout normale (immagine sopra, testo sotto)
    
- quando la card scende sotto 275px di larghezza → layout “testo sopra immagine”
    

Notare che:

- non abbiamo toccato la media query del device
    
- l’unica cosa che conta è quanto è larga **quella singola card**
    
- se la card quasi si “comprime” perché la griglia cambia, la container query scatta da sola
    

---

**Container query vs media query sulla griglia**

Prima, con le media query, facevamo:

- `@media` sul **viewport** per decidere quante colonne avere:
    
    - 4 colonne su desktop molto grande
        
    - 2 colonne su tablet
        
    - 1 colonna su mobile
        

Questa parte è ancora perfettamente sensata: sono regole di layout globale.

Ad esempio:

```css
@media screen and (min-width: 1024px) {
  .container-grid {
    grid-template-columns: repeat(4, 1fr);
  }
}

@media screen and (min-width: 768px) and (max-width: 1023px) {
  .container-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media screen and (max-width: 767px) {
  .container-grid {
    grid-template-columns: 1fr;
  }
}
```

Ora, con le container query:

- **sopra** queste logiche di griglia, possiamo aggiungere logiche più fini:
    
    - “se una card è piccola, trasformala”
        
    - “se una card è grande, mantieni layout classico”
        

Quindi:

- media query → dipendono dal device (viewport)
    
- container query → dipendono dal container (card, sezione, body, ecc.)
    

---

**Passo successivo: usare il body come container al posto delle media query**

Per farti capire quanto sono potenti le container query, nel video viene mostrato anche un trucco:

- invece di mettere `container-type` sulla `.card`
    
- metti `container-type` direttamente sul `body`
    

```css
body {
  container-type: inline-size;
  container-name: body;
}
```

A questo punto, puoi sostituire le media query con:

```css
@container body (min-width: 768px) {
  /* CSS che prima stava in @media (min-width: 768px) */
}
```

Perché funziona?

- il `body` occupa praticamente tutta la larghezza della pagina
    
- quindi la larghezza del body corrisponde, in pratica, alla larghezza del viewport
    
- di fatto, stai trasformando una media query “sul device” in una container query “sul body”
    

Risultato:

- container query sono abbastanza potenti da **sostituire** a livello teorico le media query
    
- puoi gestire sia il layout globale (usando il body come container) sia i layout locali (card, componenti, sezioni)
    

---

**Allora buttiamo via le media query? No (per ora).**

Anche se le container query sono più flessibili, nel video ti viene dato un consiglio molto importante:

- prima impara bene le **media query**
    
- usale per un po’
    
- facci dei progetti veri (layout completi, pagine intere, ecc.)
    
- solo dopo inizia a introdurre le container query per casi più raffinati
    

Motivi:

1. Le media query esistono da circa 15 anni, sono stabili e super documentate.
    
2. La maggior parte dei **framework CSS** (Tailwind, Bootstrap, ecc.) usa ancora media query, non container query, come base del sistema responsive.
    
3. Le container query sono nuove, non sono ancora diffuse ovunque come “standard mentale”.
    
4. Alcuni tool (editor, plugin, estensioni) ancora non le supportano bene (niente suggerimenti, warning di proprietà sconosciute, ecc.).
    

Quindi il percorso consigliato è:

- oggi:
    
    - design responsive con media query
        
    - ragionamento mobile-first sul device
        
- domani (quando avrai “centinaia di ore di CSS addosso” e almeno un progetto serio):
    
    - torni sulle container query
        
    - inizi a usarle per componenti complessi (card, componenti riutilizzabili, layout nidificati)
        
    - eventualmente sostituisci alcune media query con container query (es. sul body)
        

---

**Riassunto concettuale**

- Le **media query** guardano il **device** (viewport):
    
    - “se lo schermo è così largo, fai così”
        
- Le **container query** guardano il **container**:
    
    - “se questo elemento ha questa larghezza, allora cambia layout al suo interno”
        
- `container-type: inline-size` → il container valuta la larghezza
    
- `container-type: size` → valuta larghezza + altezza
    
- `@container (max-width: Xpx)` → blocco CSS che si attiva quando il container è al massimo largo Xpx
    
- puoi combinare media query e container query:
    
    - media query per il layout globale (quante colonne, header, menù, ecc.)
        
    - container query per il comportamento interno delle singole card / componenti
        

Per ora, l’obiettivo è:

- padroneggiare le media query
    
- fissare bene la mentalità responsive
    
- poi, quando ti sentirai solido, ripescare le container query come “arma avanzata” per componenti intelligenti che si adattano in base allo spazio che hanno a disposizione.