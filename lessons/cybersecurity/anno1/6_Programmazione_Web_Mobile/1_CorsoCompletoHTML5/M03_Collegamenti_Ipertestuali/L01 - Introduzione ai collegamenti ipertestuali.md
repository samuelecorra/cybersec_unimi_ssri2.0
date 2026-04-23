## **Lezione 1: Introduzione ai collegamenti ipertestuali (`<a>`)**

I link sono talmente fondamentali che, senza di loro, il web smetterebbe di essere “web” (rete) e diventerebbe solo un insieme di pagine isolate.

In questa lezione fissiamo bene:

- che cos’è un **link** in HTML
    
- come si usa l’elemento **`<a>`**
    
- come collegare **pagine diverse**
    
- come trasformare **immagini e altri elementi** in link
    
- quali tipi di link esistono (interni, esterni, mail, download, frammenti di pagina)
    
- a cosa servono gli attributi **`title`**, **`hreflang`** e **`target`**
    

L’obiettivo è che, alla fine, tu possa guardare un sito e dire: “questo link è interno, questo esterno, qui hanno usato `_blank`, qui un `mailto`, qui un frammento di pagina”.

---

## **1. Perché i link sono così importanti**

Un link (collegamento ipertestuale) è:

- per l’utente: il **mezzo con cui si sposta** tra pagine e siti
    
    - dalla **home** alla pagina **contatti**
        
    - dal **blog** a un **articolo specifico**
        
    - da un articolo a **Wikipedia**, **Amazon**, ecc.
        
- per i motori di ricerca: la **strada** che seguono i **crawler**
    
    - un crawler arriva su una pagina → scansiona il contenuto
        
    - segue i link interni → scopre altre pagine dello stesso sito
        
    - segue i link esterni → scopre altri siti
        
    - i link da siti autorevoli verso di te sono un segnale di **fiducia** (“se la Gazzetta linka il tuo sito di sport locale, forse hai contenuti interessanti”).
        

Quindi i link non servono solo a “cliccare e andare da un’altra parte”:  
sono anche **segnali strutturali** per la SEO, per l’accessibilità e per l’architettura informativa del sito.

---

## **2. L’elemento base: `<a>` (Anchor)**

Il link in HTML si crea sempre con l’elemento:

```html
<a> ... </a>
```

“A” sta per **Anchor** (ancora): l’idea è che “ancoriamo” un pezzo di testo (o un elemento) a una destinazione.

La struttura minima è:

```html
<a href="URL-di-destinazione">Testo del link</a>
```

Esempio base, un link a Google:

```html
<a href="https://www.google.com">Vai su Google</a>
```

- `href` = **H**ypertext **REF**erence ⇒ dice _dove_ porta il link
    
- il contenuto tra `<a>` e `</a>` è ciò che l’utente vede e clicca (“Vai su Google”).
    

Se clicchi quel link nel browser, verrai portato su google.com.

---

## **3. Attributo `title`: descrizione extra del link**

L’attributo `title` permette di **descrivere** il link in modo più completo:

```html
<a href="https://www.google.com"
   title="Motore di ricerca più famoso al mondo">
  Vai su Google
</a>
```

- Per l’utente vedente: alcuni browser mostrano il `title` come **tooltip** quando passi il mouse sopra il link.
    
- Per screen reader e crawler: è un’informazione in più su **che tipo di risorsa** stai linkando.
    

Non è obbligatorio, ma è una buona abitudine quando il testo del link da solo è un po’ vago.

---

## **4. Link interni vs link esterni**

### **4.1. Link esterno (verso un altro sito)**

Esempio:

```html
<a href="https://www.wikipedia.org">Vai su Wikipedia</a>
```

Qui il browser lascia il tuo sito e va su un dominio completamente diverso.

---

### **4.2. Link interno (verso un’altra pagina del tuo sito)**

Se nella stessa cartella hai, ad esempio:

- `index.html`
    
- `esercizio2.html`
    
- `esercizio3.html`
    

puoi linkare così:

```html
<a href="esercizio2.html">Vai all'esercizio 2</a>

<a href="esercizio3.html">Vai all'esercizio 3</a>
```

Se invece la pagina è in una **sottocartella**, usi il percorso relativo:

```html
<a href="esercizi/esercizio2.html">Vai all'esercizio 2</a>
```

Qui `esercizi/` è una cartella e `esercizio2.html` è il file dentro quella cartella.

---

## **5. Rendere cliccabili immagini e altri elementi**

Domanda super-frequente:  
**“Come faccio a mettere un link su un’immagine?”**

La risposta è **semplice**:  
metti l’`<img>` **dentro** l’`<a>`.

```html
<a href="https://www.google.com">
  <img src="icon.webp" alt="Logo di Google" width="200" height="200" />
</a>
```

- Se clicchi sull’immagine, è come cliccare su un link testuale.
    
- Lo stesso trucco funziona con **qualsiasi elemento**: un `<div>`, un `<span>`, un titolo, ecc.
    

Esempio:

```html
<a href="esercizio3.html">
  <div>Vai alla pagina dell'esercizio 3</div>
</a>
```

Tutto ciò che sta dentro `<a> ... </a>` diventa “cliccabile”.

---

## **6. Tipi di link speciali**

Oltre al “link normale” a una pagina, abbiamo altri tipi molto usati.

### **6.1. Link a un frammento della stessa pagina (document fragment)**

Serve per fare il classico “Vai giù” o “Torna su”, oppure per gli indici interni.

Funziona in due passi:

1. **Dai un `id` all’elemento di destinazione**:
    

```html
<h2 id="sezione-3">Sezione 3</h2>
```

2. **Crei un link che punta a quell’`id`** usando `#`:
    

```html
<a href="#sezione-3">Vai alla sezione 3</a>
```

Quando clicchi, il browser fa lo **scroll** fino all’elemento con `id="sezione-3"`.

Esempio con evidenziazione:

```html
<p><a href="#importante">Vai al paragrafo importante</a></p>

<p>Testo di riempimento...</p>
<p>Altro testo di riempimento...</p>

<p id="importante">
  <mark>Questo è il paragrafo importante.</mark>
</p>
```

---

### **6.2. Link email (`mailto:`)**

Apre il client di posta predefinito con una nuova email indirizzata a una certa persona:

```html
<a href="mailto:luca.rossi@mail.it">Scrivi a Luca</a>
```

Quando l’utente clicca:

- il browser apre il programma di posta
    
- pre-compila il campo **A:** con `luca.rossi@mail.it`.
    

Più avanti puoi anche aggiungere oggetto, testo precompilato ecc., ma per ora basta sapere che il prefisso è `mailto:`.

---

### **6.3. Link che avvia un download**

Se linkhi direttamente un file, ad esempio un’immagine:

```html
<a href="icon.webp">Apri l'immagine</a>
```

Il browser cercherà di **mostrarla**.

Se invece vuoi forzare il **download**, usi l’attributo `download`:

```html
<a href="icon.webp" download="icona-corso.webp">
  Scarica l'immagine
</a>
```

- `download` dice al browser: “non aprire il file, scaricalo”.
    
- Il valore (`icona-corso.webp`) è il **nome suggerito** per il file scaricato.
    

Puoi usare lo stesso trucco con PDF, ZIP, ecc.

---

## **7. Attributi avanzati: `hreflang` e `target`**

### **7.1. `hreflang`: lingua della pagina di destinazione**

`hreflang` serve per indicare in che **lingua** è la risorsa collegata:

```html
<a href="https://fr.wikipedia.org"
   hreflang="fr">
  Wikipedia in francese
</a>
```

- `hreflang="it"` ⇒ italiano
    
- `hreflang="en"` ⇒ inglese
    
- `hreflang="es"` ⇒ spagnolo
    
- ecc.
    

È soprattutto un **segnale per i motori di ricerca**: sanno che il contenuto linkato è in quella lingua.

---

### **7.2. `target`: dove aprire il link**

`target` dice _in quale contesto_ aprire la destinazione.

I valori che ti interessano davvero sono:

#### **`_self` (default)**

Apre il link **nella stessa scheda**.

```html
<a href="esercizio2.html" target="_self">
  Vai all'esercizio 2
</a>
```

In pratica è quello che succede anche se **non** scrivi `target`.

#### **`_blank`**

Apre il link in **una nuova scheda** (o nuova finestra):

```html
<a href="https://www.google.com" target="_blank">
  Apri Google in una nuova scheda
</a>
```

Uso tipico:

- link **interni** al tuo sito → di solito in **stessa tab** (no `_blank`)
    
- link **esterni** (Google, Amazon, Wikipedia, ecc.) → spesso in **nuova tab** (`_blank`), così l’utente non “perde” il tuo sito.
    

#### **`_parent`** e **`_top`**

Esistono anche:

- `target="_parent"`
    
- `target="_top"`
    

Servono soprattutto quando lavori con **iframe** (pagine dentro pagine) per far aprire il link:

- nel “genitore” (`_parent`)
    
- nel contesto più esterno (`_top`)
    

Nel 99% dei casi moderni, **userai solo**:

- niente `target` → equivalente a `_self`
    
- oppure `target="_blank"` per aprire in nuova scheda.
    

---

## **8. Riepilogo operativo**

Da portarti via da questa lezione:

- Il link si crea sempre con **`<a href="..."> ... </a>`**
    
- Il testo o gli elementi dentro `<a>` sono la **parte cliccabile**.
    
- Puoi mettere `<a>` **attorno a qualsiasi elemento**: immagini, `<div>`, titoli, icone.
    
- Tipi fondamentali:
    
    - link **interno** ad altra pagina del tuo sito (percorsi relativi)
        
    - link **esterno** ad altri siti
        
    - link a **frammento** della stessa pagina (`href="#id"`)
        
    - link **email** (`mailto:`)
        
    - link che forza il **download** (`download`)
        
- Attributi utili:
    
    - `title` → descrizione del link
        
    - `hreflang` → lingua della risorsa di destinazione
        
    - `target="_blank"` → nuova scheda
        

---

## **9. Mini-esercizio (facoltativo ma utile)**

Crea una pagina `link-demo.html` con:

1. Un link a `esercizio3.html` (stessa cartella).
    
2. Un link a `https://www.google.com` che si apre in **nuova tab** e ha un `title` descrittivo.
    
3. Un link a una sezione interna della pagina (usa `id` e `href="#..."`).
    
4. Un’immagine (anche finta `placeholder.png`) resa cliccabile e puntata a Google.
    
5. Un link `mailto:` a un’email fittizia.
    
6. Un link che scarica un file (anche un’immagine, usando `download`).