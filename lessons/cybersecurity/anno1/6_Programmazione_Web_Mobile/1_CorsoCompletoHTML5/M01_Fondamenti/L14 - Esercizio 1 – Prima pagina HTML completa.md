## **Lezione 14: Esercizio 1 – Prima pagina HTML completa**

---

### **1. Traccia dell’esercizio**

Obiettivo dell’esercizio:

- creare una **pagina HTML** con:
  - un **titolo grande**;
  - **tre sezioni titolate** (Sezione 1, Sezione 2, Sezione 3);
  - in ogni sezione, **almeno un paragrafo** e **un titoletto di paragrafo** (`<h3>`).
- completare il `<head>` con:
  - `<title>` della pagina;
  - `meta description`;
  - `meta keywords` (facoltative ma esercitative);
  - meta tag per rendere la pagina **indicizzabile** e con link **follow** per i robot;
  - una **favicon**.

![alt text](esercizio_finale/image.png)

In pratica: pochissimi tag HTML, ma usati già **con criterio professionale** (SEO, accessibilità, struttura).

---

### **2. Struttura base del documento HTML**

Partiamo dal boilerplate HTML5:

```html
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Esercizio #1</title>
  </head>
  <body></body>
</html>
```

Punti chiave:

- `<!DOCTYPE html>` → dichiara HTML5.
- `<html lang="it">` → lingua principale italiana (motori di ricerca + screen reader).
- meta di **charset**, **compatibilità** e **viewport** → ci arrivano praticamente “di default” dai template, ma ora sappiamo cosa significano.
- `<title>` → il titolo che compare nella tab del browser e nei risultati dei motori di ricerca.

Su questa base andiamo ad arricchire `<head>` e `<body>`.

---

### **3. Completare il `<head>`: favicon, description, keywords, robots**

#### **3.1 Aggiungere la favicon**

La favicon è l’icona della pagina (tab del browser, preferiti, ecc.).

Nella cartella del progetto abbiamo ad esempio `icona.webp`.  
Nel `<head>` aggiungiamo:

```html
<link rel="icon" type="image/webp" href="icona.webp" />
```

- `rel="icon"` → relazione: “sono l’icona del sito”.
- `type="image/webp"` → tipo di file (può essere anche `image/png`, `image/jpeg`, ecc.).
- `href="icona.webp"` → percorso del file.

Se la favicon è configurata correttamente, il browser mostrerà l’ico­nci­na accanto al titolo della pagina.

---

#### **3.2 Meta description**

La **description** è il testo che comparirà sotto il titolo sui motori di ricerca.

```html
<meta
  name="description"
  content="Descrizione del nostro primo esercizio in HTML."
/>
```

- `name="description"` → meta di tipo “description”.
- `content="..."` → testo descrittivo della pagina.

---

#### **3.3 Meta keywords (esercizio)**

Anche se ormai per la SEO moderna non sono fondamentali, le **keywords** sono utili per esercitarsi:

```html
<meta
  name="keywords"
  content="corso HTML, corso HTML italiano, imparare HTML"
/>
```

- Le parole chiave sono separate da virgole.
- Non c’è un “giusto/errato” rigido: conta capire il meccanismo.

---

#### **3.4 Meta robots: indicizzazione e link follow**

Vogliamo dire ai motori di ricerca:

- **indicizza** la pagina (index),
- **segui** i link che trovi dentro (follow).

Si possono usare due meta distinti:

```html
<meta name="robots" content="index" /> <meta name="robots" content="follow" />
```

Oppure un unico meta con entrambe le direttive (ma nel video sono separati, quindi rimaniamo coerenti all’esercizio).

Effetto:

- `index` → la pagina può comparire nelle SERP;
- `follow` → i bot seguono i link interni per scansionare altre pagine.

---

### **4. Il `<head>` completo dell’esercizio**

Mettiamo tutto insieme:

```html
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <meta
    name="description"
    content="Descrizione del nostro primo esercizio in HTML."
  />
  <meta
    name="keywords"
    content="corso HTML, corso HTML italiano, imparare HTML"
  />
  <meta name="robots" content="index" />
  <meta name="robots" content="follow" />

  <link rel="icon" type="image/webp" href="icona.webp" />

  <title>Esercizio #1</title>
</head>
```

Già con questo:

- la pagina è **comprensibile ai motori di ricerca**;
- appare con un minimo di **ottimizzazione SEO** (title + description + keywords);
- è **indicizzabile** e i link sono “follow”.

---

### **5. Strutturare il `<body>`: titolo grande, sezioni, paragrafi, titoletti**

Ora passiamo al contenuto visibile.

#### **5.1 Titolo principale (`<h1>`) e paragrafo introduttivo**

```html
<body>
  <h1>Grande titolone</h1>
  <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Non, culpa?</p>
</body>
```

- `<h1>` → titolo principale della pagina.
- `<p>` → breve paragrafo introduttivo (usiamo un mini lorem ipsum come riempitivo).

---

#### **5.2 Tre sezioni titolate (`<h2>`)**

Come richiesto dall’esercizio, creiamo:

- Sezione 1,
- Sezione 2,
- Sezione 3.

Struttura base:

```html
<h2>Sezione 1</h2>
<!-- sottotitoli + paragrafi -->

<h2>Sezione 2</h2>
<!-- sottotitoli + paragrafi -->

<h2>Sezione 3</h2>
<!-- sottotitoli + paragrafi -->
```

---

#### **5.3 Sottotitoli di paragrafo (`<h3>`) e paragrafi (`<p>`)**

Per la **Sezione 1**, nel video:

- ci sono **due titoletti** (`<h3>`),
- ognuno con un rispettivo paragrafo.

Esempio:

```html
<h2>Sezione 1</h2>

<h3>Titoletto 1.1</h3>
<p>
  Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam quisquam
  dolorum consequatur mollitia iste sit reiciendis, nemo suscipit nostrum...
</p>

<h3>Titoletto 1.2</h3>
<p>
  Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio aliquid,
  modi enim voluptates culpa repellendus ex dolor, provident doloremque...
</p>
```

Per le **altre sezioni** puoi procedere allo stesso modo:

```html
<h2>Sezione 2</h2>
<p>Lorem ipsum dolor sit amet consectetur adipisicing elit...</p>

<h2>Sezione 3</h2>
<p>Lorem ipsum dolor sit amet consectetur adipisicing elit...</p>
```

Oppure aggiungere titoletti anche per Sezione 2 e Sezione 3, se vuoi allenarti con più `<h3>`.

---

### **6. Il body completo dell’esercizio (versione tipo)**

Una versione “tipo” coerente con il video può essere:

```html
<body>
  <h1>Grande titolone</h1>
  <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Non, culpa?</p>

  <h2>Sezione 1</h2>

  <h3>Titoletto 1.1</h3>
  <p>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam quisquam
    dolorum consequatur mollitia iste sit reiciendis, nemo suscipit nostrum.
  </p>

  <h3>Titoletto 1.2</h3>
  <p>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio aliquid,
    modi enim voluptates culpa repellendus ex dolor, provident doloremque.
  </p>

  <h2>Sezione 2</h2>
  <p>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam quisquam
    dolorum consequatur mollitia iste sit reiciendis, nemo suscipit nostrum...
  </p>

  <h2>Sezione 3</h2>
  <p>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam quisquam
    dolorum consequatur mollitia iste sit reiciendis, nemo suscipit nostrum...
  </p>
</body>
```

Quello che conta non è il testo finto in sé, ma la **struttura gerarchica**:

- 1 solo `<h1>`;
- più `<h2>` come sezioni;
- `<h3>` come titoletti interni ai paragrafi;
- paragrafi `<p>` ben separati e leggibili.

---

### **7. Cosa abbiamo davvero imparato con questo esercizio**

Anche se i tag che conosciamo sono ancora pochi, abbiamo già:

- una pagina con:
  - **struttura semantica corretta** (headings + paragrafi),
  - **meta tag SEO** (title, description, keywords),
  - indicazioni chiare ai **motori di ricerca** (robots index/follow),
  - **favicon** funzionante,
  - `lang="it"` per lingua e accessibilità.

In più, il video insiste su un punto fondamentale:

> Non è “scuola”: usare la **documentazione ufficiale** (MDN, W3Schools, ecc.) è parte del lavoro, non “barare”.

L’importante è:

- capire **i concetti** (cos’è description, cos’è robots, cos’è favicon…),
- saperli poi cercare nella documentazione,
- tradurre una consegna in **codice HTML corretto**.

---

Abbiamo finito il modulo dei fondamenti basilari di HTML5. Dalla prossima macro-sezione il corso entra nel merito della **formattazione del testo**: useremo nuovi tag (grassetto, corsivo, enfasi, citazioni, ecc.) per arricchire il contenuto, mantenendo sempre chiara la struttura che hai costruito in questo esercizio.
