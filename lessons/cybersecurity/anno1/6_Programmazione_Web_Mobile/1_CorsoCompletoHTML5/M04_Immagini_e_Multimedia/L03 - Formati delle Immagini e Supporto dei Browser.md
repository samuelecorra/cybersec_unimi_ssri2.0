## **Lezione 3: Formati delle Immagini e Supporto dei Browser**

### 1. Perché i formati delle immagini sono fondamentali

Dopo aver compreso le regole di copyright, dobbiamo capire **come funzionano i formati delle immagini**.  
Non è un dettaglio estetico: il formato che scegli influisce direttamente su:

- **la qualità visiva**,
    
- **le prestazioni del sito**,
    
- **la velocità di caricamento su mobile**,
    
- **la SEO e l’esperienza utente**,
    
- **la compatibilità tra browser**.
    

Capire i formati significa capire **quando** usare un’immagine, **in quale formato**, e **perché**.

### 2. I formati più conosciuti: JPG e PNG

Partiamo dalle basi.

#### JPG (o JPEG)

- Formato storico.
    
- Comprime molto bene → file leggeri.
    
- **Non supporta la trasparenza**.  
    Se hai un oggetto ritagliato, lo sfondo non può essere “vuoto”: diventerà bianco.
    
- Ideale per fotografie, sfondi e immagini “piene”.
    

#### PNG

- Formato di immagine con **trasparenza**.
    
- Perfetto per: loghi, icone, disegni, illustrazioni con bordi netti.
    
- Di solito pesa più del JPG a parità di dimensione.
    

Molti errori comuni in HTML nascono da questo:

> “Perché l’immagine ha lo sfondo bianco?”  
> Perché stai usando un JPG al posto di un PNG.

### 3. Il formato GIF

GIF è un formato più datato ma ancora usato perché permette di avere **immagini animate**.

Le GIF sono:

- immagini, non video;
    
- animate già “di loro”;
    
- spesso usate per piccole animazioni decorative.
    

Non servono competenze HTML particolari: l’animazione è interna al file.

### 4. I formati moderni: WebP, AVIF e JPEG XL

Negli ultimi anni sono comparsi nuovi formati creati per un obiettivo preciso:

> “Stessa qualità visiva, file molto più leggeri.”

I tre moderni sono:

- **WebP**
    
- **AVIF**
    
- **JPEG XL**
    

Fra questi, **WebP è attualmente quello più diffuso e più supportato**.

#### 4.1 WebP

È il formato moderno più popolare nel web.

- Riduce enormemente il peso del file rispetto a JPG/PNG.
    
- Mantiene alta qualità.
    
- Supporta trasparenza (come PNG).
    
- Supporta animazioni (come GIF).
    

Per questo motivo quasi tutti i nuovi siti stanno passando a WebP.

#### 4.2 AVIF

Formato ancora più efficiente, con qualità altissima anche a pesi molto bassi.

Limite attuale:

- supporto browser non ancora “totale”.
    
- su alcuni sistemi è appena arrivato.
    

#### 4.3 JPEG XL

Altro formato di nuova generazione, progettato per essere ancora più avanzato.

Limite:

- supporto browser in ritardo rispetto a WebP.
    

### 5. Perché si stanno diffondendo questi formati

Perché le immagini sono sempre più numerose e pesanti.  
Le connessioni desktop migliorano, ma:

- **su mobile** non avrai mai la stessa stabilità,
    
- e Google penalizza pagine lente.
    

Ridurre il peso delle immagini significa:

- caricamenti più rapidi,
    
- meno consumo dati,
    
- migliore SEO,
    
- migliore usabilità.
    

Ecco perché WebP, AVIF e JPEG XL sono così importanti per lo sviluppo moderno.

### 6. Convertire JPG/PNG in formati moderni

Se hai immagini classiche (JPG, PNG, GIF), puoi convertirle facilmente tramite servizi online.

Esempio: **convertio.co**

La procedura è semplice:

1. Carichi il file (es. `prova.jpg`).
    
2. Scegli il formato di destinazione (es. WebP).
    
3. Converti.
    
4. Scarichi.
    

Nell’esempio del tuo testo:

- Da **89 KB** → **37 KB**  
    senza perdita visiva significativa.
    

Più avanti nel corso analizzerai il tema del _peso delle immagini_, ma già qui hai visto un primo risultato concreto.

### 7. Il problema della compatibilità dei browser

Non basta conoscere i formati: bisogna sapere **quali browser li supportano**.

Lo strumento universale per questo è:

**caniuse.com**  
(ricordalo come “can I use?” → “posso usarlo?”)

Esempio: supporto WebP

- Chrome → supporto stabile dal 2014
    
- Edge → supporto completo
    
- Firefox → dal 2019
    
- Safari → solo dal 2022
    
- Internet Explorer → _non supporta WebP_
    

Questo significa:

- per il pubblico moderno → **WebP è sicuro**
    
- per ambienti tecnici molto vecchi (aziende, intranet) → servono fallback
    

Il principio è semplice:

> Prima di usare un nuovo formato, controlla _se puoi usarlo_.

### 8. Quando devi fare attenzione

Se stai sviluppando:

- un e-commerce moderno,
    
- un blog,
    
- un sito pubblicitario,
    
- un portfolio,
    
- qualsiasi progetto rivolto “alla massa”,
    

WebP è la scelta migliore.

Se invece ti rivolgi a contesti specifici dove si usa ancora Internet Explorer (rarissimo, ma non impossibile), devi prevedere:

- un’immagine in WebP,
    
- e una versione di fallback (JPG/PNG).
    

### 9. Riassunto dei formati e direzione futura

I formati “storici”:

- **JPG**
    
- **PNG**
    
- **GIF**
    

Li userai ancora, ma sempre meno.

I formati moderni:

- **WebP** (oggi il più usato)
    
- **AVIF**
    
- **JPEG XL**
    

AVIF e JPEG XL sono più avanzati, ma ancora non ampiamente supportati.  
In futuro potranno superare WebP, ma la transizione richiederà tempo.

Per ora, nella maggior parte dei casi, **WebP è la scelta ottimale**.