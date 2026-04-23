# **Lezione 1: Analisi del layout delle pagine web**

## **1. Perché analizziamo ora il layout**

Quando cominciamo a costruire un sito, la prima cosa che dobbiamo capire non è lo stile, non è il design, e non è neppure il JavaScript.  
La base di tutto è **la struttura HTML**, perché ogni pagina web – anche la più bella, moderna e animata – _nasce_ da un insieme di elementi HTML organizzati in modo corretto.

Il CSS può renderla splendida.  
Il JavaScript può renderla interattiva.  
Ma **la struttura rimane sempre HTML**.

Oggi analizziamo diversi tipi di pagine reali (prese da ThemeForest), ignorando il design e osservando solo _come_ sono costruite.  
È il modo migliore per imparare a riconoscere cosa è fatto bene e cosa è fatto male.

---

## **2. Dashboard (Web App a pagina unica)**

### **2.1 Cos’è una dashboard**

Una _dashboard_ è una web-app.  
Non è un sito normale: serve per gestire dati, utenti, statistiche, ordini, domini, ecc.

La caratteristica principale delle dashboard moderne è:

- **non ricaricano la pagina** quando navighi da una sezione all’altra
    
- la navigazione è “interna”, gestita da tecnologie che vedremo più avanti
    
- tutto avviene “dentro una singola pagina”
    

Quella che analizziamo è una demo, quindi ha ricariche normali.  
Ma il concetto è chiaro: **tutto avviene dentro un unico contenitore**.

### **2.2 La struttura HTML delle dashboard**

Aprendo il codice… vediamo quasi **solo `<div>`**.

Questo perché:

- non sono pagine destinate al pubblico
    
- non devono essere indicizzate su Google
    
- non devono per forza rispettare tutte le regole di accessibilità
    

Per noi è importantissimo sapere che **questa cosa NON la faremo mai** nei nostri progetti “puliti”.

Noi faremo:

- `header`
    
- `nav`
    
- `main`
    
- `section`
    
- `article`
    
- `aside`
    
- `footer`
    

Una dashboard professionale _potrebbe_ farlo, ma per noi è un cattivo esempio.

---

## **3. Landing Page (One-Page Website)**

### **3.1 Cosa è una landing page**

Una landing è una **pagina unica** che contiene tutto ciò che serve all’utente:

- cosa offre il prodotto
    
- schermate
    
- funzionalità
    
- testimonianze
    
- prezzi
    
- FAQ
    
- contatti
    

Quando clicchi sul menù:

- **non si apre una nuova pagina**
    
- vieni spostato in basso
    
- spesso usando animazioni o funzioni aggiuntive
    

È un sito con una sola pagina `index.html`.

### **3.2 Struttura della landing**

Questa volta il codice è molto più sano:

- `header` → parte superiore del sito
    
- `nav` → menù di navigazione
    
- `section` → ogni blocco verticale del sito
    
- `footer` → chiusura finale
    

Esempio estratto dalla pagina analizzata:

```html
<header>
  <nav>
    <ul>
      <li>...</li>
    </ul>
  </nav>
</header>

<section>
  ...
</section>

<section>
  ...
</section>

<footer>
  ...
</footer>
```

### **3.3 Cosa fanno bene**

- Usano correttamente `header`, `nav`, `section`, `footer`.
    
- Usano `h1`, `h2`, `h3` in ordine gerarchico.
    
- Usano qualche attributo di accessibilità.
    

### **3.4 Cosa fanno male**

Con Lighthouse notiamo:

- immagini senza `width` e `height`
    
- immagini non ottimizzate (`.png` invece dei moderni `.webp`)
    
- mancano alcuni meta tag
    
- headings usati male (es. `h5` per puro estetismo)
    
- niente `label` nei form
    
- mancano attributi ARIA essenziali
    

Tutto questo lo eviteremo noi.

---

## **4. FAQ Page (Frequently Asked Questions)**

Una pagina FAQ di solito ha questa struttura:

```html
<header>...</header>

<section>
  <!-- breadcrumb -->
</section>

<section>
  <!-- tutte le domande -->
</section>

<footer>...</footer>
```

### **4.1 Le breadcrumb**

Le breadcrumb (“briciole di pane”) indicano la gerarchia:

```
Home > FAQ > Domanda
```

Il nome deriva dalla favola di Pollicino, che lasciava briciole per ritrovare la strada.

### **4.2 Come strutturano le FAQ**

Ogni domanda dovrebbe essere:

```html
<h2>Domanda?</h2>
<p>Risposta...</p>
```

Ma spesso vediamo:

```html
<h2>Domanda?</h2>
<div>Risposta...</div>
```

Funziona, ma non è semanticamente corretto.

---

## **5. Blog moderno**

### **5.1 Il blog come struttura**

I blog moderni seguono un pattern preciso:

- **header** con logo + navigazione
    
- **main** → tutti gli articoli
    
- ogni articolo → `<article>`
    
- immagini → spesso dentro `<figure>` + `<figcaption>`
    
- tag e metadata → ottimi per SEO
    

Esempio:

```html
<article>
  <h2>Titolo</h2>
  <p>Intro...</p>
  <figure>
    <img src="..." alt="...">
    <figcaption>Fonte immagine</figcaption>
  </figure>
</article>
```

### **5.2 Cosa fanno bene**

- Usano `article`
    
- Usano `time` con `datetime`
    
- Usano `figure` e `figcaption`
    

### **5.3 Cosa fanno male**

- mancano `rel="tag"` nei tag
    
- mancano `aside` (presenti solo in alcune parti)
    
- headings non sempre corretti
    
- mancano strutture ARIA
    

### **5.4 Struttura pagina di un articolo**

Di solito:

```html
<header>...</header>

<main>
  <article>
    <h1>...</h1>
    <p>...</p>

    <figure>...</figure>

    <h2>Sezione articolo</h2>
    <p>...</p>

    <blockquote>...</blockquote>
  </article>

  <aside>
    <!-- articoli correlati -->
  </aside>
</main>

<footer>...</footer>
```

---

## **6. E-Commerce**

Un e-commerce ha quasi sempre:

- `header` con menù e carrello
    
- **HERO / SLIDER** iniziale
    
- `section` con prodotti
    
- `section` con categorie
    
- `section` con offerte
    
- `footer`
    

Tutto perfettamente segmentato in sezioni.

---

## **7. Sito aziendale / Corporate Website**

La struttura è sempre:

1. **header**
    
2. **hero (sezione iniziale ad alto impatto)**
    
3. serie di **section**:
    
    - servizi
        
    - team
        
    - progetti
        
    - recensioni
        
4. **footer**
    

È la struttura più comune del web.

---

## **8. La struttura generale di _qualsiasi_ pagina web moderna**

Con quello che abbiamo visto, possiamo riassumere:

### **Struttura corretta**

```html
<body>

  <header>
    <nav>...</nav>
  </header>

  <main>

    <!-- facoltativo -->
    <article>...</article>
    <aside>...</aside>

    <!-- in alternativa -->
    <section>...</section>
    <section>...</section>

  </main>

  <footer>...</footer>

</body>
```

### **La regola fondamentale**

Tutto ciò che appartiene al **contenuto principale della pagina** deve stare dentro `main`.  
Tutto quello che è contorno → `aside`.  
Tutto ciò che è una macro-area verticale → `section`.  
Tutto ciò che è un contenuto autonomo → `article`.

---

## **9. Cosa portiamo a casa da questa lezione**

- Le dashboard usano _quasi solo div_, ed è normale, ma NON è un buon esempio.
    
- Le landing page sono spesso strutturate meglio.
    
- I blog sono ottimi per osservare `article`, `figure`, `figcaption`, `time`, `aside`.
    
- Gli e-commerce sono una sequenza di `section`.
    
- I siti aziendali sono lineari: header → hero → section → footer.
    
- Molti template hanno **problemi di accessibilità**, **SEO** e **semanticità**.
    
- Noi costruiremo pagine con **struttura HTML perfetta**, poi ci penserà il CSS a renderle bellissime.