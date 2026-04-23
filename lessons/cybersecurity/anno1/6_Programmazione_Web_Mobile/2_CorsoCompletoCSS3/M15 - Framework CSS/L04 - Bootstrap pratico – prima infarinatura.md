## **Lezione 4: Bootstrap pratico – prima infarinatura**

---

### **1. Obiettivo della lezione**

In questa lezione non vogliamo “imparare Bootstrap a fondo”, ma:

- capire **come si integra** con ciò che sappiamo di HTML + CSS,
    
- vedere **come si installa nel modo più semplice**,
    
- copiare **2 componenti reali** (navbar + carousel),
    
- avere la sensazione:
    
    > “Ok, ora so dove si incastra Bootstrap nel puzzle del front-end”.
    

Niente Node, niente build tool, niente setup complicati:  
solo file HTML normali + CDN di Bootstrap.

---

### **2. Come includere Bootstrap nel progetto**

Bootstrap offre molti modi di installazione. Nel sito ufficiale vedrai:

- comandi `npm` / `node`,
    
- pacchetti, build custom, ecc.
    

Per ora **ignoriamo tutto questo**:  
non conosci ancora JavaScript/Node in modo strutturato e ti complicherebbe solo la vita.

Ci interessano solo 2 vie:

1. **Scaricare i file CSS+JS** e metterli nel progetto (come fai col tuo `style.css`).
    
2. **Usare il CDN** → la via più veloce per iniziare.
    

Useremo il **CDN**.

---

### **3. Usare il CDN di Bootstrap**

Sul sito di Bootstrap, nella sezione **Download → CDN**, trovi qualcosa di simile (semplifico):

```html
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="..."
  crossorigin="anonymous"
/>

<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
  integrity="..."
  crossorigin="anonymous"
></script>
```

- Il `link` importa il **CSS minificato** di Bootstrap.
    
- Lo `script` importa il **JS di Bootstrap** (necessario per cose dinamiche: navbar collassabile, carousel, accordion, modali…).
    

Per il nostro **file HTML base**, possiamo fare così:

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <title>Prova Bootstrap</title>

    <!-- CSS di Bootstrap via CDN -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>

  <body>
    <!-- Qui metteremo i componenti Bootstrap (navbar, carousel, ecc.) -->

    <!-- JS di Bootstrap via CDN (alla fine del body) -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
    ></script>
  </body>
</html>
```

Note importanti:

- Il CSS puoi metterlo nella `<head>` (classico).
    
- Il JS è comodo **alla fine del `<body>`**, così la pagina HTML si carica prima e poi Bootstrap “attiva” i componenti dinamici.
    
- Il file `.min.css` è **minificato** (come abbiamo visto nella lezione precedente).
    

---

### **4. Verificare che Bootstrap sia caricato**

1. Salva il file (es. `index.html`).
    
2. Aprilo nel browser.
    
3. Premi `F12` → se ci fossero errori nel `link` o nello `script`, li vedresti in rosso in “Console”.
    

Se non hai errori e più avanti, quando aggiungi componenti, vedi che gli stili cambiano, allora **Bootstrap è attivo**.

---

### **5. Primo componente pratico: la Navbar**

Bootstrap offre una marea di esempi pronti nella doc.  
Prendiamo una **navbar** dalla sezione `Components → Navbar` e copiamola nel nostro `<body>`.

Esempio (semplificato):

```html
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Il Mio Sito</a>
    <button
      class="navbar-toggler"
      type="button"
      data-bs-toggle="collapse"
      data-bs-target="#navbarSupportedContent"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Materie</a>
        </li>
        <li class="nav-item dropdown">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            role="button"
            data-bs-toggle="dropdown"
          >
            Altro
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Storia</a></li>
            <li><a class="dropdown-item" href="#">Matematica</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
```

Mettila dentro il `<body>`, sopra tutto il resto.

Cosa noti:

- **zero CSS scritto da te**,
    
- navbar già con:
    
    - font carini,
        
    - spaziature,
        
    - colori di base,
        
    - dropdown funzionante,
        
    - comportamento responsive.
        

Se restringi la finestra:

- la navbar collassa,
    
- compare il bottone “hamburger”,
    
- il menu si apre/chiude grazie al **JavaScript di Bootstrap** (che hai importato con il `script` del CDN).
    

Puoi già personalizzare il contenuto:

- cambia `Il Mio Sito` con il tuo titolo,
    
- cambia `Materie`, `Storia`, ecc.
    

E puoi cambiare il colore di sfondo con una classe diversa (`bg-dark`, `bg-primary`, ecc.) se vuoi sperimentare.

---

### **6. Secondo componente: un Carousel di immagini**

Ora aggiungiamo un **carousel** (slider di immagini).  
Nella doc vai in `Components → Carousel` e copia un esempio base.

Semplifichiamo:

```html
<div id="mainCarousel" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="img/slide1.jpg" class="d-block w-100" alt="Immagine 1" />
    </div>
    <div class="carousel-item">
      <img src="img/slide2.jpg" class="d-block w-100" alt="Immagine 2" />
    </div>
    <div class="carousel-item">
      <img src="img/slide3.jpg" class="d-block w-100" alt="Immagine 3" />
    </div>
  </div>

  <button
    class="carousel-control-prev"
    type="button"
    data-bs-target="#mainCarousel"
    data-bs-slide="prev"
  >
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button
    class="carousel-control-next"
    type="button"
    data-bs-target="#mainCarousel"
    data-bs-slide="next"
  >
    <span class="carousel-control-next-icon"></span>
  </button>
</div>
```

Mettilo **sotto la navbar** nel `<body>`.

Poi:

- assicurati che le immagini `img/slide1.jpg`, `img/slide2.jpg`, `img/slide3.jpg` esistano davvero,
    
- puoi anche usare per test una stessa immagine tre volte.
    

Appena salvi e ricarichi:

- hai un **carousel funzionante**:
    
    - autoplay (se c’è l’attributo `data-bs-ride`),
        
    - frecce per scorrere,
        
    - transizioni fluide.
        

Tutto questo:

- senza scrivere **una riga di CSS tuo**,
    
- senza scrivere **una riga di JavaScript tuo**.
    

Bootstrap ti ha dato:

- HTML + classi collegate a CSS già pronto,
    
- attributi `data-bs-*` che agganciano il comportamento JS.
    

---

### **7. Cenno alla griglia e ai layout responsive**

Un pezzo gigantesco di Bootstrap è il **sistema a griglia** (`grid system`):

- classi come `container`, `row`, `col-6`, `col-md-4`, ecc.
    
- ti permettono di dividere lo schermo in **colonne** e creare layout responsive senza dover scrivere tu tutte le media query.
    

Esempio mini:

```html
<div class="container my-5">
  <div class="row">
    <div class="col-md-4">
      Colonna 1
    </div>
    <div class="col-md-4">
      Colonna 2
    </div>
    <div class="col-md-4">
      Colonna 3
    </div>
  </div>
</div>
```

- Su schermi larghi → 3 colonne affiancate.
    
- Su schermi stretti → le colonne si dispongono una sotto l’altra.
    

Non entriamo nel dettaglio ora (ci vorrebbe un corso dedicato), ma:

> la griglia di Bootstrap è uno dei motivi principali per cui è così amato nei progetti veloci.

---

### **8. Cosa fare se Bootstrap ti piace**

Questa lezione ti deve dare **solo il quadro generale**:

- sai come **inserire Bootstrap** tramite CDN,
    
- hai visto come **usare la doc per copiare componenti** (navbar, carousel),
    
- hai capito che:
    
    - è tutto **HTML + classi**,
        
    - CSS e JS sono già pronti dietro le quinte.
        

Se dopo questo corso di CSS pensi:

> “Bootstrap mi piace, voglio usarlo seriamente”

allora il passo successivo è:

- aprire la **documentazione ufficiale**,
    
- studiare con calma:
    
    - **Layout / Grid**,
        
    - **Utilities** (margin, padding, colori, tipografia),
        
    - **Components** (navbar, card, alert, form, modali, ecc.),
        
- fare **un progettino reale** (es. un mini sito portfolio) usando solo Bootstrap.
    

---

### **9. Ricapitolazione**

- Abbiamo incluso Bootstrap via **CDN** (CSS + JS).
    
- Abbiamo creato:
    
    - una **navbar responsive**,
        
    - un **carousel di immagini**.
        
- Abbiamo visto che:
    
    - i componenti sono **già stilati e già responsive**,
        
    - molti componenti usano anche **JavaScript** (ma tu non hai dovuto scrivere JS).
        
- Ora sai **dove si inserisce Bootstrap** nel flusso HTML/CSS:
    
    > è un grosso pacchetto di componenti e classi già pronti che ti fa risparmiare un’enorme quantità di codice e di tempo.
    

Nel prossimo passaggio, come promesso, si passa a **Tailwind** per vedere l’altro paradigma (utility-first) in pratica.