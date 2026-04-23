**Lezione 4: Masonry layout – che cos’è e perché te lo cito solo di passaggio**

Restiamo ancora un secondo nel mondo delle grid per parlare del **Masonry layout** (quello in stile Pinterest).  
Qui l’obiettivo non è insegnarti a implementarlo passo per passo, ma:

- farti sapere che cos’è,
    
- collegarlo al contesto di grid/flex,
    
- chiarire perché oggi è molto meno usato,
    
- dirti quando _forse_ ha ancora senso.
    

---

**1. Cos’è il Masonry layout (idea generale)**

Il termine arriva da “mason” = muratore, muratura.  
L’idea visiva è quella di un muro di mattoni:

- i blocchi non sono allineati perfettamente su righe regolari,
    
- ogni “tassello” (foto, card, box) ha un’altezza diversa,
    
- l’effetto finale è di **blocchi incastrati a incastro**, con vuoti riempiti in modo intelligente.
    

In pratica:

- non hai righe perfettamente allineate come in una **grid classica**,
    
- ma colonne con oggetti di altezze diverse, che “scalano” uno sotto l’altro,
    
- il risultato è un layout molto dinamico, tipo bacheca di immagini.
    

---

**2. Il collegamento con Pinterest**

Il Masonry layout è diventato famoso grazie a **Pinterest**:

- la home di Pinterest è un muro di immagini verticali,
    
- ogni immagine ha un’altezza diversa, ma la larghezza è più o meno costante,
    
- le immagini sono organizzate in colonne, e gli spazi vuoti vengono riempiti da altre immagini,
    
- l’effetto è proprio quello tipico “muro di mattoni colorati”.
    

Se guardi uno screenshot di Pinterest:

- vedi colonne di foto,
    
- ogni colonna ha “blocchi” più alti o più bassi,
    
- le righe non sono allineate: la foto in alto a destra può essere molto più alta di quella a sinistra, e sotto si incastrano altre foto più basse.
    

Questo è il Masonry layout “canonico”.

---

**3. Dove si usava (e dove avrebbe ancora senso)**

Il Masonry layout è stato molto di moda negli **anni 2010**:

- tanti portfolio creativi,
    
- gallerie fotografiche,
    
- siti di designer, illustratori, fotografi,
    
- template “premium” per mostrare tanti lavori in modo dinamico.
    

Esempio tipico:

- pagina portfolio di un grafico,
    
- tanti lavori con immagini verticali/orizzontali miste,
    
- tutti organizzati in un muro stile Pinterest.
    

Oggi però:

- è un **trend molto meno usato**,
    
- design e UI moderne stanno andando più verso layout più puliti, simmetrici, griglie più regolari, card più uniformi,
    
- personalmente (e anche secondo tanti articoli di design) non è più un pattern “di massa”.
    

Detto questo, in contesti come:

- un portfolio artistico particolare,
    
- un sito che vuole volutamente richiamare Pinterest,
    
- una galleria creativa con focus visivo forte,
    

il Masonry può ancora essere una scelta “stilistica” voluta.

---

**4. Perché lo cito in una sezione sul Grid Layout**

Dalle immagini sembra **“una griglia”**, quindi istintivamente uno pensa:

> “Lo faccio con `display: grid` e ho finito”.

In realtà:

- ottenere un **vero** Masonry (con celle che si riempiono gli spazi in verticale “a cascata”) **con le sole CSS Grid classiche** è più complicato di quanto sembri,
    
- spesso si è usato storicamente:
    
    - **Flexbox** con colonne,
        
    - oppure layout multi-colonna (`column-count`, `column-gap`) per far scorrere gli elementi a “colonna di testo”,
        
    - oppure JavaScript per calcolare le posizioni.
        

Quindi:

- visivamente “somiglia” a una griglia,
    
- ma **non è** la griglia regolare che stiamo studiando con `grid-template-columns` e `grid-template-rows`,
    
- richiede trick diversi e spesso più codice/accessori.
    

---

**5. Perché nel corso non viene sviluppato in dettaglio**

Durante la preparazione del corso, l’idea iniziale era:

- fare una lezione dedicata al _come_ implementarlo in CSS.
    

Poi, guardando:

- trend attuali di design,
    
- articoli di riferimento,
    
- esperienza pratica,
    

la conclusione è stata:

- il Masonry layout oggi è **molto poco utilizzato**,
    
- non vale la pena investirci tempo didattico pesante, soprattutto in un corso che vuole darti strumenti moderni, riutilizzabili ovunque,
    
- una volta che hai in mano **Grid** e **Flexbox**, se un domani _davvero_ ti servirà un Masonry, sarai comunque in grado di:
    
    - cercare una soluzione specifica (CSS + magari un pizzico di JS),
        
    - capire e adattare snippet trovati in documentazione o articoli.
        

Per questo motivo:

- il Masonry viene **citato**, spiegato come concetto di layout,
    
- ma non viene implementato passo passo in codice.
    

---

**6. Cosa ti devi portare a casa, davvero**

Per i tuoi appunti, la cosa importante è:

- **Definizione**: Masonry layout = layout “a mattoni”, stile Pinterest, con card di altezze diverse incastrate in colonne, senza righe allineate.
    
- **Origine**: reso famoso da Pinterest; usato tanto 2010–2015, molto meno oggi.
    
- **Uso tipico**: portfolio creativi, bacheche di immagini, gallerie.
    
- **Implementazione**: non è una semplice `grid`; di solito si ottiene con:
    
    - flexbox + colonnine,
        
    - layout multi-colonna,
        
    - oppure soluzioni ibride con JavaScript.
        
- **Motivo per cui non lo studiamo in dettaglio adesso**:
    
    - è poco usato,
        
    - è più un pattern di nicchia/stilistico,
        
    - dopo aver imparato bene grid/flex, se mai ti servirà, saprai tranquillamente costruirtelo.
        

Quindi: lo conosci di nome, lo riconosci a vista, sai collocarlo mentalmente nella “famiglia dei layout”, ma per ora ti concentri su **Grid**, **Flexbox**, **normal flow** e layout più moderni e ricorrenti.