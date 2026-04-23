## **Lezione 8: Il menù di navigazione (`<nav>`, liste e link)**

### **1. Che cos’è un menù di un sito**

Quando apri un sito, la prima cosa che cerchi quasi sempre è il **menù di navigazione**:

- su **desktop** di solito è in alto (spesso a destra)
    
- su **mobile** spesso è nascosto in un’icona a tre linee (il famoso **“hamburger”**)
    

Dal punto di vista HTML, però, il menù non è magia:  
**è semplicemente una lista di link**.

Quindi:

- tecnicamente: una **lista** (`<ul>`, `<li>`)
    
- dentro la lista: vari **link** (`<a href="...">...`)
    
- il tutto racchiuso in un contenitore semantico: `<nav>`
    

---

### **2. L’elemento `<nav>`: il contenitore della navigazione**

L’elemento:

```html
<nav> ... </nav>
```

significa: “dentro qui c’è la **navigazione principale** del sito”.

Serve a:

- dare **significato semantico** al codice (non è un `div` generico)
    
- aiutare:
    
    - screen reader
        
    - motori di ricerca
        
    - strumenti di accessibilità  
        a riconoscere **dove si trova il menù**.
        

> Idea chiave:  
> **`<nav>` non crea il menù da solo**, ma dice: “questa parte della pagina è dedicata ai link di navigazione”.

---

### **3. Struttura base di un menù**

Uno scheletro “canonico”:

```html
<nav>
  <ul>
    <li><a href="index.html">Home</a></li>
    <li><a href="chi-siamo.html">Chi siamo</a></li>
    <li><a href="contatti.html">Contatti</a></li>
  </ul>
</nav>
```

Componenti:

- `<nav>` → contenitore semantico della navigazione
    
- `<ul>` → lista non ordinata di voci di menù
    
- `<li>` → singola voce di menù
    
- `<a href="...">` → link alla pagina corrispondente
    

Esteticamente, senza CSS:

- i link appaiono **uno sotto l’altro** (lista verticale)
    
- con **pallini** (`list-style: disc`)
    
- link in blu, sottolineati
    

È normale: HTML si occupa della **struttura**, non dell’estetica.

---

### **4. Esempio concreto con le pagine degli esercizi**

Supponi di avere:

- `index.html` → “home”
    
- `esercizi/esercizio_3.html`
    
- `esercizi/esercizio_4.html`
    

Puoi creare un menù così in `index.html`:

```html
<nav>
  <ul>
    <li><a href="index.html">Home</a></li>
    <li><a href="esercizi/esercizio_3.html">Esercizio 3</a></li>
    <li><a href="esercizi/esercizio_4.html">Esercizio 4</a></li>
  </ul>
</nav>
```

Poi, nelle pagine degli esercizi, ad esempio in `esercizi/esercizio_3.html`:

```html
<nav>
  <ul>
    <li><a href="../index.html">Home</a></li>
    <li><a href="esercizio_3.html">Esercizio 3</a></li>
    <li><a href="esercizio_4.html">Esercizio 4</a></li>
  </ul>
</nav>
```

Note:

- da `index.html` i link sono **relativi** alla root del progetto
    
- da `esercizio_3.html` devi considerare che ti trovi nella cartella `esercizi/`, quindi:
    
    - per tornare alla home → `../index.html` (sali di una cartella)
        
    - per andare a `esercizio_4.html` → stesso livello, quindi solo `esercizio_4.html`
        

> Concetto importante:  
> il menù è composto da **link interni** al tuo sito, quasi sempre percorsi **relativi**.

---

### **5. Cenno al CSS: perché il menù “fa schifo” ma è giusto così**

Nel video il docente per un attimo:

- ha messo `list-style: none` sugli `<ul>` → per togliere i pallini
    
- ha tolto la sottolineatura dei link con `text-decoration: none`
    
- ha messo il menù in orizzontale usando `display: flex` ecc.
    

Quella parte è **CSS**, non HTML.

Tu, in questa fase:

- devi fissare bene **la struttura** (nav + ul + li + a)
    
- ignorare pure i dettagli estetici (saranno trattati nel corso di CSS)
    

HTML risponde alle domande:

- “**Cosa** è questo blocco?” → navigazione (`<nav>`)
    
- “**Cosa contiene?**” → una lista (`<ul>`) di voci (`<li>`), ognuna con un link (`<a>`)
    

CSS risponderà dopo a:

- “Come voglio **vederlo**?” (orizzontale, in alto, hamburger menu, colori, ecc.)
    

---

### **6. Il problema della ripetizione: lo stesso menù su ogni pagina**

Osservazione fondamentale:

- Se crei il menù solo in `index.html`, quando entri in `esercizio_3.html` **scompare**.
    
- Per avere un menù coerente su **tutte** le pagine, con solo HTML devi:
    
    - **copiare lo stesso blocco `<nav>...</nav>`** in ogni pagina
        
    - aggiustare solo gli `href` (per i percorsi relativi corretti)
        

Quindi:

- stesso menù in:
    
    - `index.html`
        
    - `esercizi/esercizio_3.html`
        
    - `esercizi/esercizio_4.html`
        
    - ecc.
        

È scomodo, e infatti nei siti “seri” si usano:

- **template** (PHP, Node, framework, ecc.)
    
- **componenti riutilizzabili**
    
- sistemi che permettono di definire il menù **una sola volta** e includerlo ovunque.
    

Ma **con solo HTML statico**:

- non hai questa possibilità
    
- devi accettare di **duplicare** il blocco `<nav>` in ogni pagina
    

> Questo “fastidio” che senti adesso è il motivo per cui esistono  
> linguaggi server-side, template engine, framework, componenti, ecc.  
> Ma per ora ci fermiamo a: **HTML statico = copia-incolla del menù**.

---

### **7. Riepilogo concettuale**

- Un **menù** è, strutturalmente, **una lista di link**.
    
- Lo racchiudi dentro `<nav>` per dire: “questa è la parte di navigazione”.
    
- Dentro `<nav>` usi:
    
    - `<ul>` → la lista delle voci
        
    - `<li>` → ogni singola voce
        
    - `<a href="...">` → il link alla pagina
        
- Senza CSS il menù appare “brutto”, ma **semanticamente corretto**.
    
- Per averlo in tutte le pagine, con puro HTML, devi **ripetere** il blocco `<nav>` in ogni file.
    