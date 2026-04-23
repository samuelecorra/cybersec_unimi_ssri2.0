## Lezione 4: Immagini, Performance e SEO

### 1. Perché le immagini contano per la SEO (non solo “grafica”)

Le immagini non sono solo “abbellimento” della pagina.  
Ogni immagine influisce su:

- **Performance** del sito (velocità di caricamento).
    
- **Esperienza utente** (UX: quanto il sito sembra fluido/reattivo).
    
- **SEO** (posizionamento su Google, anche su Google Immagini).
    
- **Accessibilità** (se sono nominate e descritte correttamente).
    

Per Google e gli altri motori di ricerca, un sito “ben fatto” non è solo bello da vedere: deve essere **veloce**, **leggero**, **ordinato** e **comprensibile** sia agli utenti che ai crawler.

Abbiamo già visto i **formati moderni** (WebP, AVIF, JPEG XL).  
Adesso entriamo nel merito di:

- **peso del file** (in KB/MB),
    
- **dimensioni in pixel** (larghezza/altezza),
    
- **nome del file** (importante per Google Immagini).
    

---

### 2. Il peso del file: perché è cruciale (soprattutto su mobile)

Esempio concreto:

- `prova.jpg` → **89 KB**
    
- `prova.webp` → **37 KB**
    

Le immagini appaiono praticamente **identiche** all’occhio umano, ma:

- WebP pesa circa **un terzo**.
    
- Risparmio di circa **60–70%** di peso.
    

#### 2.1. Perché vogliamo immagini che pesano meno?

1. **Connessione mobile instabile**  
    Su desktop con fibra puoi “permetterti” qualche spreco.  
    Su mobile, con rete ballerina, ogni kilobyte pesa:
    
    - Se il sito è leggero, la pagina si carica comunque rapidamente.
        
    - Se il sito è pieno di immagini pesanti, l’utente aspetta, si spazientisce e abbandona.
        
2. **Aspettative dell’utente moderno**  
    Le persone ormai si aspettano:
    
    - caricamento quasi istantaneo,
        
    - zero attese percepibili.
        
    
    Due secondi di attesa in più possono significare **decine di punti percentuali di traffico perso** (30–40% delle persone che chiudono prima ancora di vedere la pagina).
    
3. **Impatto diretto sulla SEO**  
    Google analizza le performance (es. tramite strumenti come Lighthouse) e valuta:
    
    - quanto velocemente si carica la pagina,
        
    - quanto “pesano” le risorse,
        
    - se ci sono elementi oversize inutili.
        
    
    Se il sito è lento:
    
    - gli utenti tornano indietro subito → **alto bounce rate**;
        
    - Google “si annota” che la pagina è lenta e poco apprezzata;
        
    - questo può peggiorare il ranking.
        

Quindi il **peso delle immagini** non è un dettaglio tecnico, ma un pezzo importante del puzzle SEO.

---

### 3. Formati moderni come alleati delle performance

Per ora il nostro riferimento pratico è:

- **WebP** come formato principale
    
- (AVIF e JPEG XL come futuri step, quando il supporto browser sarà più maturo)
    

Motivi:

- compressione molto più efficiente rispetto a JPG/PNG;
    
- supporto alla trasparenza;
    
- possibilità di sostituire anche molte GIF animate;
    
- perfettamente adatto al web moderno.
    

Con WebP (e poi AVIF/JPEG XL) otteniamo:

- **meno peso** → caricamento più rapido;
    
- **stessa qualità percepita** nella maggioranza dei casi.
    

---

### 4. Nome del file immagine: SEO e Google Immagini

Il **nome del file** è spesso sottovalutato, ma è fondamentale.

Esempio sbagliato:

- `prova.webp`
    
- `img1.jpg`
    
- `foto_definitiva_finalissima_vera2.png`
    

Questi nomi non dicono nulla né a Google né all’utente.

#### 4.1. Un buon nome di file descrive davvero l’immagine

Supponiamo che l’immagine rappresenti:

> una mousse di cioccolato con mirtilli, in una colazione.

Potremmo chiamarla ad esempio:

- `colazione-mousse-cioccolato-mirtilli.webp`  
    oppure
    
- `mousse-cioccolato-mirtilli.webp`
    

Regole pratiche:

- usiamo **parole descrittive reali** (come fossero mini-parole chiave);
    
- separiamo le parole con **trattini** (`-`), non con underscore (`_`);
    
- **niente spazi** nel nome del file;
    
- non esageriamo con nomi troppo lunghi, ma neanche con robe generiche tipo `foto1`.
    

Perché i trattini e non gli spazi?

- gli spazi possono causare problemi negli URL,
    
- i trattini aiutano i motori di ricerca a distinguere le parole.
    

#### 4.2. Collegamento con Google Immagini

Google Immagini è un **motore di ricerca a sé**.  
Se il file si chiama:

- `mousse-cioccolato-mirtilli.webp`
    

ha molte più probabilità di comparire quando qualcuno cerca:

- `mousse cioccolato mirtilli`,
    
- `dessert cioccolato e mirtilli`,
    
- ecc.
    

Immagina:

- il tuo sito non è prima pagina su Google “normale”,
    
- ma una tua immagine appare tra le prime su **Google Immagini**,
    
- la foto è bella → l’utente clicca → arriva al tuo sito.
    

Questa è **SEO tramite immagini**: usi il canale “images” per portare traffico al tuo sito.

Per questo conviene, fin da subito, **rinominare bene i file** invece di usare `prova`, `foto1`, ecc.  
Cambiare i nomi dopo, su un sito già pieno di immagini, è un disastro: devi rinominare i file e aggiornare tutti i riferimenti.

---

### 5. Dimensioni in pixel: perché 4K è spesso uno spreco

Qui non parliamo di peso in KB/MB, ma di:

- **larghezza** (es. 1920 px),
    
- **altezza** (es. 1080 px).
    

Esempio:

- un’immagine a **4K** pesa circa **4 MB**;
    
- le versioni ottimizzate che usavi prima stavano sui **30–90 KB**.
    

Differenza enorme: decine di volte più pesante.

#### 5.1. Il problema pratico

Una foto 4K:

- viene comunque mostrata in uno spazio molto più piccolo (un box in pagina, una card, una miniatura);
    
- l’utente non vede tutta la risoluzione disponibile;
    
- tu però lo obblighi a scaricare **4 MB** di dati… per riempire un box magari largo 400–600 px.
    

Metafora:

> È come comprare una villa da 25 stanze, pagarne tutti i costi, e poi vivere chiuso in una sola stanza.  
> Tutto il resto è solo spreco.

Nel web:

- stai sprecando banda,
    
- aumenti i tempi di caricamento,
    
- peggiori il punteggio di performance.
    

#### 5.2. Larghezza ideale

In generale:

- per immagini **full-width** (banner grandi) → max ~1920×1080;
    
- per immagini più piccole (card, box, sezioni interne) → valori tipo 1200, 800, 600 px di larghezza sono spesso più che sufficienti.
    

Se un’immagine occupa in pagina un rettangolo relativamente piccolo, non ha alcun senso che abbia dimensioni a livello 4K.

#### 5.3. Come ridimensionare (resize)

Esistono servizi online che permettono di fare il **resize**:

1. Carichi l’immagine.
    
2. Imposti una larghezza massima (es. 1200 px).
    
3. Il sito genera una versione ridimensionata.
    
4. Poi, se vuoi, converti ulteriormente in WebP.
    

La combinazione “**ridimensionare** + **formato moderno**” è il vero boost:

- meno pixel → meno dati da conservare,
    
- formato efficiente → compressione migliore,
    
- risultato: peso finale molto basso a parità di qualità percepita.
    

---

### 6. Range di peso consigliato

Regola empirica:

- immagini “normali” di pagina → idealmente **sotto i 100 KB**;
    
- comunque **sotto i 150 KB** nella maggior parte dei casi;
    
- eccezione: grandi banner/full-width → possono arrivare a 200 KB o poco più, se proprio necessario.
    

Un buon esercizio:  
vai sui grandi siti di news o portali famosi e scarica qualche immagine degli articoli.  
Noterai che:

- quasi tutte sono sotto i 150 KB,
    
- e spesso molto sotto.
    

Questo perché:

- la maggior parte delle immagini è decorativa;
    
- servono a spezzare il testo, dare un colpo d’occhio;
    
- nessuno zooma al 400% per controllare i pori della pelle.
    

---

### 7. Il legame tra immagini e punteggio SEO complessivo

I motori di ricerca non guardano solo un aspetto isolato, ma un **insieme di fattori**:

- formati moderni o obsoleti;
    
- peso totale della pagina;
    
- dimensioni in pixel coerenti o eccessive;
    
- nome file significativo o no;
    
- attributi `alt` per l’accessibilità;
    
- HTML scritto bene o no;
    
- struttura logica (heading, meta, ecc.);
    
- contenuto di qualità o no.
    

Se:

- usi immagini enormi,
    
- con nomi senza senso,
    
- in formati pesanti,
    
- con HTML trascurato,
    

tutti i “meno” si sommano:

- peggiore esperienza utente,
    
- più rimbalzi,
    
- peggior giudizio degli algoritmi,
    
- ranking più basso.
    

Se invece:

- scegli formati moderni,
    
- ridimensioni le immagini,
    
- ne controlli il peso,
    
- dai nomi sensati ai file,
    
- scrivi buon codice HTML,
    

stai mandando molti **segnali positivi** a:

- utenti,
    
- crawler,
    
- motori di ricerca.
    

---

### 8. Ricapitolando: checklist immagini “SEO-friendly”

Per ogni immagine, prova a rispettare questi punti:

1. **Formato**
    
    - Preferisci: **WebP** (oggi)
        
    - Tieni d’occhio: **AVIF, JPEG XL** (futuro).
        
2. **Peso (KB/MB)**
    
    - Obiettivo: < 100 KB,
        
    - Tetto massimo tipico: < 150 KB,
        
    - Eccezione: grandi banner.
        
3. **Dimensioni in pixel**
    
    - Niente 4K inutili se l’immagine occupa un box piccolo.
        
    - Ridimensiona a una larghezza coerente con lo spazio in pagina.
        
4. **Nome del file**
    
    - Descrittivo (parole chiave vere),
        
    - Parole separate con **trattini**,
        
    - Niente spazi e nomi tipo `prova.jpg`.
        

Una volta che fai tutto questo **subito**, mentre costruisci il sito, non dovrai tornare indietro a “ripulire” centinaia di immagini in futuro.

Quando questi aspetti “fuori dal codice HTML” sono sotto controllo, puoi tornare tranquillo alla parte di markup sapendo che:

- le tue immagini sono leggere,
    
- ben nominate,
    
- pronte a lavorare a favore della SEO, non contro.