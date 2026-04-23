## **Lezione 11: Gli headings (`<h1>`–`<h6>`) nel `<body>`**

---

### **1. I primi veri elementi nel `body`**

Ora iniziamo davvero a mettere **contenuto visibile** nel `<body>`.

I primi elementi che introduciamo sono gli **headings**, cioè i titoli:  
`<h1>`, `<h2>`, `<h3>`, `<h4>`, `<h5>`, `<h6>`.

Sono quei testi grandi che, già senza CSS, _sembrano_ titoli di sezione:

```html
<h1>Ciao titolo</h1>
```

Se apri questa pagina nel browser, vedrai un testo grande, in grassetto:  
**è un titolo vero e proprio**, non solo “testo più grande”.

---

### **2. Quali headings esistono e a cosa servono**

Gli headings in HTML sono **sei**:

```html
<h1>Titolo principale</h1>
<h2>Sottotitolo / Sezione principale</h2>
<h3>Sottosezione</h3>
<h4>Sottosottosezione</h4>
<h5>Livello più profondo (quasi mai usato)</h5>
<h6>Ancora più profondo (quasi mai usato)</h6>
```

In pratica:

- `<h1>` → titolo principale della pagina (di solito **uno solo** per pagina).
    
- `<h2>` → titoli delle sezioni principali.
    
- `<h3>` → titoli delle sottosezioni.
    
- `<h4>`, `<h5>`, `<h6>` → livelli più profondi, che si usano **raramente**.
    

Nella pratica reale:

- spesso ci si ferma a **`<h3>`**,
    
- **`<h4>`** solo in testi molto strutturati,
    
- **`<h5>`** e **`<h6>`** quasi mai.
    

---

### **3. Perché usare headings invece di un semplice paragrafo**

Potresti chiederti:

> “Ma non potrei usare un `<p>` con font grande, o uno `<span>` stilato a caso?”

Visivamente sì, **ma sarebbe un errore**.

Gli headings servono a **dichiarare la struttura logica del contenuto**, non solo lo stile:

- ai **motori di ricerca** (crawler/bot) dicono:
    
    - “Questo è il titolo principale.”
        
    - “Questi sono i titoli delle sezioni.”
        
- agli **screen reader** (per persone non vedenti) permettono di:
    
    - scorrere rapidamente titoli e sottotitoli,
        
    - capire l’organizzazione logica della pagina.
        

Usare un `<p>` o uno `<span>` gigante con CSS **inganna l’occhio umano**, ma **non comunica nulla** ai bot e agli strumenti di accessibilità.

È la differenza tra:

```html
<h2>Perché usare gli headings</h2>
```

e:

```html
<p style="font-size: 40px; font-weight: 800;">Perché usare gli headings</p>
```

Visivamente possono sembrare simili.  
Semanticamente, il primo è un titolo vero; il secondo è solo un paragrafo truccato.

---

### **4. Una struttura di esempio con `h1`, `h2`, `h3`**

Immaginiamo un articolo sul tema:

> “Headings in HTML”

Possibile struttura:

```html
<h1>Headings in HTML</h1>

<h2>Quali sono gli headings</h2>
<p>In HTML abbiamo sei livelli di headings, da h1 a h6...</p>

<h2>Perché usare gli headings</h2>
<p>Gli headings servono a dare struttura logica alla pagina...</p>

<h2>Headings e accessibilità</h2>
<p>Gli screen reader usano gli headings per aiutare la navigazione...</p>
```

Possiamo anche aggiungere sottosezioni con `h3`:

```html
<h1>Headings in HTML</h1>

<h2>Quali sono gli headings</h2>

<h3>H1 – Titolo principale</h3>
<p>Descrizione di H1...</p>

<h3>H2 – Titoli di sezione</h3>
<p>Descrizione di H2...</p>

<h3>H3 – Sottosezioni</h3>
<p>Descrizione di H3...</p>
```

Cosa si ottiene:

- **per chi vede**:
    
    - un titolone (`<h1>`)
        
    - tre sezioni principali (`<h2>`)
        
    - sottopunti chiari con `<h3>`
        
- **per crawler e screen reader**:
    
    - una struttura ad albero chiara e navigabile.
        

---

### **5. Differenza tra `<title>` e `<h1>`**

Attenzione:  
Il **`<title>`** (nel `<head>`) e **`<h1>`** (nel `<body>`) non sono la stessa cosa, anche se spesso sono simili.

- `<title>` → è il titolo della **pagina** come documento:
    
    - compare nella tab del browser;
        
    - compare nei risultati di Google.
        
- `<h1>` → è il titolo del **contenuto principale** della pagina.
    

Esempio realistico:

```html
<title>Headings in HTML – il mio sito</title>

<h1>Headings in HTML</h1>
```

Qui:

- il `<title>` dice: “Questa pagina, nel contesto del sito, parla di headings”;
    
- l’`<h1>` dice: “Questo è il tema specifico del contenuto”.
    

Su Wikipedia, ad esempio:

- il `<title>` è qualcosa tipo: `Italia - Wikipedia`;
    
- l’`<h1>` è solo: `Italia`.
    

Stesso argomento, **due livelli diversi**:

- `<title>` → documento + sito;
    
- `<h1>` → argomento dell’articolo.
    

---

### **6. Headings, SEO e copywriting (in pillole)**

Senza entrare in un corso di SEO, la logica è:

- l’`<h1>` di solito contiene la **parola chiave principale** della pagina.
    
- gli `<h2>` riprendono quella parola chiave in modo naturale, collegando sotto-temi.
    

Esempio:

```html
<h1>Headings in HTML</h1>

<h2>Quali sono gli headings in HTML</h2>
<h2>Perché usare gli headings in HTML</h2>
<h2>Headings in HTML e accessibilità</h2>
```

Non scrivo:

- `Perché usarli`
    
- `Accessibilità`
    

ma:

- `Perché usare gli headings in HTML`
    
- `Headings in HTML e accessibilità`
    

Stesso contenuto concettuale, ma:

- più chiaro per chi legge,
    
- più chiaro per Google,
    
- più coerente con l’argomento principale.
    

---

### **7. CSS vs semantics: perché non “truccare” altri elementi**

Potrei prendere uno `<span>` e farlo sembrare un titolo:

```html
<span style="font-size: 46px; font-weight: 800;">
  Quali sono gli headings
</span>
```

Funziona visivamente.  
Ma:

- per i bot → è solo testo generico;
    
- per lo screen reader → non è un titolo, quindi l’utente non lo vede come step di navigazione.
    

Se invece uso:

```html
<h2>Quali sono gli headings</h2>
```

ho **sia**:

- struttura logica,
    
- **sia** possibilità di modificarne lo stile via CSS (dimensione, colore, font, margini…).
    

Conclusione:  
**prima scegli il tag semantico giusto**, poi applichi lo stile.

---

### **8. Gerarchia corretta e errori da evitare**

Gli headings funzionano come una **toc (tabella dei contenuti) gerarchica**.

Regola mentale semplice:

- `<h1>` → titolo globale
    
- `<h2>` → “capitoli”
    
- `<h3>` → “sottocapitoli”
    
- `<h4>` → “sotto-sottocapitoli”
    
- ecc.
    

#### **8.1 Esempio corretto**

```html
<h1>Headings in HTML</h1>

<h2>Quali sono gli headings</h2>
<p>Testo...</p>

<h2>Perché usare gli headings</h2>
<p>Testo...</p>

<h3>Struttura logica</h3>
<p>Testo...</p>

<h3>SEO e accessibilità</h3>
<p>Testo...</p>
```

Qui:

- c’è **un solo `<h1>`**,
    
- gli `<h2>` sono “fratelli” (stesso livello),
    
- gli `<h3>` dipendono dal `<h2>` sopra.
    

#### **8.2 Esempio sbagliato**

```html
<h1>Headings in HTML</h1>

<h3>Quali sono gli headings</h3>
<h2>Perché usare gli headings</h2>
```

Problemi:

- si salta da `h1` a `h3` senza introdurre `h2`;
    
- la gerarchia è confusa;
    
- gli strumenti di controllo accessibilità lo segnaleranno come errore;
    
- per chi usa screen reader, la pagina sembra disordinata.
    

Altro errore:

```html
<h3>Quali sono gli headings</h3>
<!-- nessun paragrafo sotto, si passa direttamente ad altro -->
<h2>Perché usare gli headings</h2>
```

Un `h3` dovrebbe avere **contenuto correlato** sotto (paragrafi, liste, ecc.).  
Se metti un titolo senza contenuti, ottieni:

- per chi legge: un “titolo fantasma”;
    
- per i validatori di accessibilità: un warning.
    

---

### **9. Cosa succede quando i template sono fatti male**

In molti temi e template complicati (es. WordPress creati male):

- usano `h3` per voci di menù,
    
- headings messi “a caso” solo per lo stile,
    
- salti di livello (da `h1` a `h4`),
    
- più `h1` non necessari.
    

Risultato:

- **disastro lato accessibilità**,
    
- struttura incomprensibile per screen reader e crawler,
    
- warning negli strumenti di analisi.
    

Il tuo obiettivo, invece, è:

- pensare sempre a **come struttureresti un indice di un libro**,
    
- riflettere quella struttura con `h1`–`h6`.
    

---

### **10. Regole pratiche da portarti dietro**

Per non sbagliare quasi mai:

1. **Un solo `<h1>` per pagina** → il titolo principale del contenuto.
    
2. Usa `<h2>` per le **macro-sezioni**.
    
3. Usa `<h3>` per le **sottosezioni** di un `<h2>`.
    
4. Non saltare i livelli “a caso”:
    
    - evita `h1 → h3` senza `h2`,
        
    - mantieni coerente la gerarchia.
        
5. Non usare headings solo per “fare grande il testo”.
    
6. Ricorda che gli headings servono:
    
    - a chi vede, per orientarsi visivamente;
        
    - ai crawler, per capire il contenuto;
        
    - agli screen reader, per navigare la pagina.
        

Se strutturi bene gli headings, sei **già a metà dell’opera** per avere:

- pagina chiara,
    
- accessibile,
    
- SEO-friendly.
    

---

Nella prossima lezione entreremo nei **paragrafi** (`<p>`) e vedremo come combinarli con gli headings per costruire testi leggibili, ben strutturati e pronti per il web moderno.