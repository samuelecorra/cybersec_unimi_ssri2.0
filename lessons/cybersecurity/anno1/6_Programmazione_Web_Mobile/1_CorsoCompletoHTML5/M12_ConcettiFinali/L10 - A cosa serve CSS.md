## **Lezione 10: A cosa serve il CSS (e come si incastra con l’HTML)**

Partiamo dall’idea chiave, senza girarci troppo intorno:

> **HTML = struttura**  
> **CSS = aspetto grafico (stile)**

Tutto il corso che hai appena fatto è stato dedicato a **HTML**:  
abbiamo costruito _la struttura logica_ delle pagine – titoli, paragrafi, immagini, link, sezioni, header, footer, form, ecc.

Adesso entra in gioco il **CSS**, che risponde alla domanda:

> “Ho la struttura… ma come faccio a renderla **bella**, leggibile, moderna e piacevole da usare?”

---

## **1. Partiamo dall’esempio del corso**

Nel video il prof fa proprio questo:

- prende la **stessa pagina HTML** dell’“esercizio 7” (quella su cui hai lavorato per tutto il corso),
    
- la duplica,
    
- e sulla copia applica un po’ di **CSS**.
    

Risultato:

### Versione **senza CSS** (solo HTML)

- Tutto il contenuto è:
    
    - appiccicato a sinistra
        
    - con font di default del browser (serif un po’ anonimo)
        
- Menu testuale semplice, senza nessun effetto al passaggio del mouse.
    
- Immagine piccola, spigolosa, senza bordi arrotondati.
    
- La sezione del codice è:
    
    - fondo bianco
        
    - testo nero standard
        
    - zero enfasi visiva.
        
- I crediti finali sono:
    
    - grandi come il resto
        
    - allineati banalmente
        
    - senza particolare gerarchia visiva.
        

È **corretto**, funziona, il browser capisce tutto…  
ma **a livello estetico è “bruttino”**, sembra un foglio Word buttato online.

---

### Versione **con CSS** applicato

Con qualche regola CSS, la pagina cambia così:

- Il **menu**:
    
    - sembra davvero un menu
        
    - quando passi sopra con il mouse, il link si **illumina di verde** (effetto hover).
        
- L’**immagine**:
    
    - è più grande
        
    - ha i **bordi arrotondati**
        
    - si integra meglio nel layout.
        
- Il **font**:
    
    - non è più il serif standard (quello con le “graffette” alle estremità delle lettere),
        
    - diventa un **font sans-serif**, più pulito e moderno (niente stanghette alle lettere).
        
- La sezione del **codice**:
    
    - ha uno **sfondo colorato** (ad esempio grigio scuro)
        
    - il testo è di un altro colore (per dare risalto)
        
    - visivamente si capisce che è “codice”, non testo normale.
        
- I **crediti**:
    
    - sono spostati leggermente più a destra
        
    - sono più piccoli
        
    - disturbano meno, ma sono comunque presenti.
        

In pratica:

> **Stesso HTML, due risultati completamente diversi**  
> → la differenza la fa **solo** il CSS.

---

## **2. Definizione pratica di CSS**

**CSS** = _Cascading Style Sheets_  
È un **linguaggio a parte**, separato da HTML, il cui scopo è:

> **Dire al browser _come_ deve apparire ciò che l’HTML dice _cos’è_**.

- HTML:  
    “Questo è un titolo, questo è un paragrafo, questa è un’immagine, qui c’è un link, qui c’è un blocco di codice…”
    
- CSS:  
    “Questo titolo deve essere grosso, rosso, centrato;  
    questo paragrafo deve essere con font X, grandezza Y, colore Z;  
    questa immagine deve avere bordi arrotondati e un’ombra;  
    questo link deve diventare verde quando ci passo sopra.”
    

---

## **3. HTML vs CSS: ruoli diversi, mondo reale**

Riassumiamo con un’analogia molto precisa:

- **HTML** è:
    
    - le **fondamenta**
        
    - i **muri**
        
    - la **disposizione delle stanze** di una casa.
        
- **CSS** è:
    
    - la **vernice sulle pareti**
        
    - il **pavimento**
        
    - le **tende, i mobili, le luci**
        
    - lo stile degli ambienti.
        

Se togli tutto il CSS ad un sito:

- non muore niente a livello logico:  
    il contenuto c’è ancora, i link funzionano, i form ci sono.
    
- ma torna “nudo”, tipo sito anni ’90: solo testo, link blu, layout a colonne un po’ triste.
    

È esattamente quello che il prof ti ha mostrato:

- “Tutti i siti senza lo stile risulterebbero così” → la versione grezza.
    
- “Quello che divide un sito Nike da un sito Adidas, da Google, da Apple, dal mio sito… è lo **stile**.”
    

---

## **4. Cosa possiamo controllare con il CSS**

Già da ora puoi mettere un elenco mentale chiaro di cosa governa CSS:

1. **Colori**
    
    - Colore di sfondo della pagina
        
    - Colore del testo
        
    - Colori diversi per titoli, sottotitoli, link, pulsanti, ecc.
        
2. **Tipografia (font)**
    
    - Tipo di font: `serif`, `sans-serif`, font personalizzati (Google Fonts, ecc.)
        
    - Dimensione del testo (`font-size`)
        
    - Spessore (`bold`, `light`, ecc.)
        
    - Interlinea, spazi tra lettere, maiuscole/minuscole, ecc.
        
3. **Layout**
    
    - Posizionamento degli elementi:
        
        - tutto in colonna
            
        - colonne multiple
            
        - sezioni affiancate
            
    - Margini esterni (`margin`)
        
    - Spaziature interne (`padding`)
        
    - Larghezze, altezze, centrature, ecc.
        
4. **Stile delle immagini**
    
    - Bordi (`border`)
        
    - Bordi arrotondati (`border-radius`)
        
    - Ombre (`box-shadow`)
        
    - Allineamento (destra, sinistra, centro).
        
5. **Aspetto di link e menu**
    
    - Come appare un link normalmente
        
    - Come cambia quando:
        
        - ci passi sopra col mouse (`:hover`)
            
        - è stato visitato
            
        - è attivo.
            
    - Effetto “menu che si illumina” quando ci sei sopra.
        
6. **Stati interattivi (senza logica, solo visual)**
    
    - Colore diverso di bottoni al passaggio del mouse
        
    - Sottolineature che compaiono o spariscono
        
    - Cambi di sfondo su hover o focus.
        

> Tutto questo è **solo CSS**:  
> nessuna logica, nessun calcolo, nessuna condizione → solo **presentazione**.

---

## **5. Come si incastra nel workflow che hai visto**

Tu oggi sei qui:

- Hai imparato **HTML** per:
    
    - strutturare una pagina
        
    - conoscere i tag e i loro significati semantici
        
    - preparare un sito “serio” a livello di contenuto.
        

Il passo successivo naturale è:

- **aggiungere CSS** per:
    
    - rendere il sito **bello**
        
    - renderlo **leggibile**
        
    - dare un’identità visiva (come un brand vero).
        

Il prof riassume così:

> “CSS è la verniciatura della nostra struttura HTML.”

Quindi la pipeline mentale è:

1. **HTML** → definisco _cosa c’è_ nella pagina (contenuto + struttura).
    
2. **CSS** → definisco _come appare_ tutto questo (stile + layout).
    
3. Più avanti: **JavaScript** → definirà _cosa fa_ (logica + interazione avanzata).
    

---

## **6. Risposta secca alla domanda “A cosa serve CSS?”**

Se dovessi rispondere in una frase, in stile esame:

> **CSS è un linguaggio usato per definire lo stile e l’aspetto grafico delle pagine HTML: colori, font, dimensioni, spaziature, layout, bordi, animazioni e reazioni visive all’interazione dell’utente. Senza CSS, la pagina è strutturata ma visivamente grezza.**

Con questo hai la visione globale chiara.  
Nel prossimo passo, quando affronterai il corso di CSS, andrai a:

- imparare **la sintassi**
    
- collegare file `.css` alle pagine HTML
    
- costruire **layout veri** (header, sidebar, footer, colonne, card, ecc.)
    
- curare il design quasi come un front-end dev “vero”.