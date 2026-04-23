## **Lezione 2: Elementi semantici HTML**

---

## **1. Elementi semantici vs elementi non semantici**

In HTML esistono due grandi famiglie di elementi:

- **Elementi non semantici**:
    
    - `div`
        
    - `span`  
        Non dicono _nulla_ sul contenuto: sono solo **contenitori generici**.
        
- **Elementi semantici**:  
    Sono tag che, già dal **nome**, spiegano che ruolo ha il loro contenuto.  
    Alcuni esempi:
    
    - `header`
        
    - `nav`
        
    - `main`
        
    - `section`
        
    - `article`
        
    - `aside`
        
    - `footer`
        
    - `figure`
        
    - `figcaption`
        
    - `time`
        
    - `address`
        
    - `details`
        
    - `summary`
        
    - (e altri più rari, come `dialog`)
        

Se leggi `header`, **capisci subito** che è la parte iniziale di qualcosa.  
Se leggi `nav`, capisci che è una **navigazione**.  
Se leggi `article`, capisci che è un **articolo** di contenuto autonomo.

Invece:

```html
<div>...</div>
<span>...</span>
```

non ti dicono nulla: potrebbero essere qualsiasi cosa.

Per questo si dice che:

- `header`, `nav`, `main`… sono **elementi semantici**
    
- `div` e `span` sono **elementi non semantici**
    

---

## **2. Header vs head: stesso concetto, scopo diverso**

Attenzione alla distinzione:

- `head`
    
    - sta dentro `<html>`
        
    - contiene **metadati del documento**: `title`, `meta`, `link`, `script`, ecc.
        
    - **non è visibile** nella pagina
        
- `header`
    
    - sta dentro `<body>`
        
    - contiene l’intestazione **visibile** della pagina o di una sezione: logo, titolo, menù, ecc.
        

Esempio:

```html
<html>
  <head>
    <title>Titolo scheda browser</title>
    <meta charset="utf-8">
  </head>

  <body>
    <header>
      <img src="logo.png" alt="Logo del sito">
      <nav>...</nav>
    </header>
  </body>
</html>
```

**Idea chiave**:

- `head` = testa _del documento HTML_
    
- `header` = testa _del contenuto visibile_
    

---

## **3. Quante `header` posso avere?**

Puoi avere:

- una `header` generale per **tutta la pagina** (di solito in cima, con logo e menù)
    
- altre `header` interne per singole sezioni o articoli
    

Esempio tipico:

```html
<body>

  <!-- Header principale del sito -->
  <header>
    <img src="logo.png" alt="Logo sito">
    <nav>...</nav>
  </header>

  <main>
    <!-- Articolo -->
    <article>
      <header>
        <h1>Titolo dell’articolo</h1>
        <p>Paragrafo introduttivo</p>
        <address>
          Scritto da <a href="mailto:autore@example.com">Mario Rossi</a>
        </address>
        <time datetime="2025-11-29">29 novembre 2025</time>
      </header>

      <p>Testo dell’articolo...</p>
    </article>
  </main>

</body>
```

**Regola importante**:  
non annidare una `header` dentro un’altra `header`.  
Ogni `header` è la “testa” della propria sezione/articolo.

---

## **4. Main, footer e struttura base della pagina**

### **4.1 `main`: il contenuto principale**

`<main>` indica il **contenuto principale** della pagina.  
Deve apparire **una sola volta** per documento ed esclude:

- header generale
    
- footer generale
    
- menù laterali
    
- banner ripetuti
    

Esempio minimo:

```html
<body>

  <header>
    <nav>...</nav>
  </header>

  <main>
    <!-- Contenuto principale -->
    <section>...</section>
    <section>...</section>
  </main>

  <footer>
    ...
  </footer>

</body>
```

### **4.2 `footer`: la parte finale**

`<footer>` rappresenta il **piè di pagina**.

Dentro un footer puoi trovare:

- una seconda `nav` (link di servizio, policy, contatti)
    
- un logo
    
- un form per iscrizione newsletter
    
- contatti e dati legali
    

Esempio:

```html
<footer>
  <nav>
    <ul>
      <li><a href="/privacy">Privacy</a></li>
      <li><a href="/termini">Termini d’uso</a></li>
    </ul>
  </nav>

  <form>
    <label for="email-newsletter">Iscriviti alla newsletter</label>
    <input id="email-newsletter" type="email">
    <button type="submit">Iscriviti</button>
  </form>
</footer>
```

I footer reali possono essere molto grandi e divisi in 2–3 colonne, ma **semanticamente** restano un unico `<footer>`.

---

## **5. Article, section, aside: come organizzare il `main`**

### **5.1 `article`**

`<article>` definisce un contenuto **autonomo**, che avrebbe senso anche fuori dal contesto della pagina:

- un post di blog
    
- una news
    
- una scheda prodotto
    
- un commento importante
    

Esempio:

```html
<article>
  <header>
    <h1>Titolo articolo</h1>
    <p>Intro...</p>
  </header>

  <p>Testo dell’articolo...</p>
</article>
```

### **5.2 `section`**

`<section>` raggruppa contenuti che appartengono alla stessa **macro-sezione tematica**:

- “Servizi”
    
- “Chi siamo”
    
- “Testimonianze”
    
- “Piani tariffari”
    

Esempio:

```html
<main>

  <section id="servizi">
    <h2>I nostri servizi</h2>
    <p>...</p>
  </section>

  <section id="testimonianze">
    <h2>Cosa dicono di noi</h2>
    <p>...</p>
  </section>

</main>
```

### **5.3 `aside`**

`<aside>` è il contenuto **laterale**, accessorio rispetto al main:

- sidebar con articoli correlati
    
- box “Autore”
    
- link rapidi
    
- banner
    

Due scelte comuni:

1. `article` + `aside` **fratelli** dentro `main`
    
2. `aside` **dentro** `main` ma fuori dall’`article`
    

Esempio preferito (article + aside affiancati):

```html
<main>

  <article>
    <h1>Articolo principale</h1>
    <p>Contenuto...</p>
  </article>

  <aside>
    <h2>Articoli correlati</h2>
    <ul>
      <li><a href="#">Articolo 1</a></li>
      <li><a href="#">Articolo 2</a></li>
    </ul>
  </aside>

</main>
```

---

## **6. Figure e figcaption (ripasso veloce)**

`<figure>` e `<figcaption>` li hai già visti: servono per immagini (o altri contenuti visivi) con una descrizione collegata.

```html
<figure>
  <img src="grafico.png" alt="Andamento delle vendite 2024">
  <figcaption>Figura 1: vendite mensili 2024.</figcaption>
</figure>
```

Sono elementi semantici perché:

- `figure` indica che il contenuto è una “figura”
    
- `figcaption` è la didascalia associata
    

---

## **7. `details` e `summary`: l’accordion nativo di HTML**

Questi sono gli **elementi nuovi** della lezione.

### **7.1 Cosa fanno `details` e `summary`**

- `<details>` definisce un blocco **espandibile/collassabile**
    
- `<summary>` è il **titolo cliccabile** che mostra/nasconde il contenuto
    

Esempio base:

```html
<details>
  <summary>QWERTY</summary>
  <p>QWERTY corrisponde alle prime lettere della tastiera standard.</p>
</details>
```

Comportamento:

- di default vedi solo il `summary` (+ una freccetta)
    
- clicchi sul `summary`
    
- il contenuto interno a `details` si **apre**
    
- riclicchi e si **chiude**
    

Questo pattern viene chiamato spesso:

- “accordion”
    
- sezione “collapse”
    
- pannello espandibile
    

HTML ce lo offre gratis, senza scrivere una riga di JavaScript.

### **7.2 `details` + `summary` per le FAQ**

È perfetto per le FAQ (domande frequenti):

```html
<h1>FAQ</h1>

<details>
  <summary>Posso annullare l’abbonamento?</summary>
  <p>Sì, puoi annullarlo in qualsiasi momento dal pannello impostazioni.</p>
</details>

<details>
  <summary>Come contatto l’assistenza?</summary>
  <p>Puoi scriverci via email a support@example.com.</p>
</details>
```

Risultato:

- vedi l’elenco delle domande
    
- apri solo quelle che ti interessano
    
- è lo stesso comportamento che spesso vedi:
    
    - nelle FAQ dei siti
        
    - nei risultati di Google con blocchi espandibili
        

---

## **8. Ricollegare tutto al layout visto nella lezione precedente**

Se metti insieme ciò che hai visto finora, il layout “tipo” di una pagina moderna è:

```html
<body>

  <header>
    <img src="logo.png" alt="Logo sito">
    <nav>
      <ul>
        <li><a href="#servizi">Servizi</a></li>
        <li><a href="#prezzi">Prezzi</a></li>
        <li><a href="#faq">FAQ</a></li>
      </ul>
    </nav>
  </header>

  <main>

    <section id="hero">
      <h1>La tua app preferita</h1>
      <p>Testo introduttivo...</p>
    </section>

    <section id="servizi">
      <h2>Servizi</h2>
      <p>...</p>
    </section>

    <section id="prezzi">
      <h2>Piani tariffari</h2>
      <p>...</p>
    </section>

    <section id="faq">
      <h2>FAQ</h2>

      <details>
        <summary>Prima domanda</summary>
        <p>Risposta...</p>
      </details>

      <details>
        <summary>Seconda domanda</summary>
        <p>Risposta...</p>
      </details>
    </section>

  </main>

  <footer>
    <nav>
      <ul>
        <li><a href="/privacy">Privacy</a></li>
        <li><a href="/contatti">Contatti</a></li>
      </ul>
    </nav>
  </footer>

</body>
```

Noti che:

- **tutti i blocchi principali** usano elementi **semantici**
    
- i pochi `div` che useremo in futuro serviranno per lo **stile**, non per il senso logico
    
- se togli il CSS, la pagina rimane leggibile e correttamente strutturata
    