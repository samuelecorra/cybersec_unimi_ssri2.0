## **Lezione 6 -  Liste di selettori (`p, h2, .classe, #id`)**

### **1. Cosa sono le liste di selettori**

In questa lezione, mentre “ripuliamo” un po’ il CSS, introduciamo un concetto molto semplice ma super utile:  
le **liste di selettori**.

**Idea di base:**

> Una _lista di selettori_ è quando diciamo:  
> “Voglio applicare **lo stesso stile** a **più selettori** contemporaneamente”.

Sintassi:

```css
selettore1, selettore2, selettore3 {
    /* regole in comune */
}
```

Dove ciascun elemento della lista (`selettore1`, `selettore2`, …):

- può essere un **selettore di elemento** (`p`, `h2`, `strong`)
    
- oppure un **selettore di classe** (`.scemo`, `.particolare`)
    
- oppure un **selettore di ID** (`#prova`)
    
- oppure un selettore più complesso (combinazioni, discendenti, ecc.)
    

L’importante è la **virgola**:  
essa separa i selettori diversi che devono condividere lo _stesso blocco di regole_.

---

### **2. Esempio semplice: stesso colore per `p` e `h2`**

Partiamo da una situazione molto basica.

Nel nostro HTML abbiamo, ad esempio:

```html
<h1>Corso CSS</h1>
<h2>Sottotitolo</h2>
<p>Primo paragrafo.</p>
<p>Secondo paragrafo.</p>
```

Mettiamo il caso che:

- tutti i `<p>` debbano essere rossi
    
- anche l’`<h2>` debba essere rosso
    
- ma questo stile rosso **non** è il colore di default (quello del `body`), è un colore “speciale” da mettere solo su questi elementi
    

Con quello che sappiamo finora, potremmo scrivere:

```css
p {
    color: red;
}

h2 {
    color: red;
}
```

Funziona, ma stiamo **ripetendo lo stesso codice**:

- stessa proprietà `color`
    
- stesso valore `red`
    
- solo cambiando il selettore
    

Questo è ridondante e poco pulito.

Con le **liste di selettori**, possiamo unire questi due selettori:

```css
p, h2 {
    color: red;
}
```

Risultato:

- tutti i paragrafi `<p>` sono rossi
    
- il sottotitolo `<h2>` è rosso
    
- la proprietà `color: red` è scritta **una sola volta**
    

Stiamo dicendo letteralmente:

> “Applica `color: red` a tutti i `p` **e** a tutti gli `h2`.”

---

### **3. Liste con elementi, classi e ID insieme**

Lo stesso identico meccanismo funziona per **qualunque tipo di selettore**.

Possiamo mischiare:

- selettori di elemento
    
- classi
    
- ID
    

Esempio:

```css
p, 
h2, 
.scemo, 
#prova {
    color: red;
}
```

Con un CSS del genere:

- tutti i `p` → testo rosso
    
- tutti gli `h2` → testo rosso
    
- tutti gli elementi con `class="scemo"` → testo rosso
    
- l’elemento con `id="prova"` → testo rosso
    

E nell’HTML potremmo avere:

```html
<strong class="scemo">Sono strong con classe scemo</strong>
<h1 id="prova">Sono un h1 con id prova</h1>
```

Anche questi diventano rossi, perché compaiono nella _lista dei selettori_.

Questa non è la soluzione “migliore” per colorare tutto di rosso in un progetto reale, ma serve a capire il concetto:

> Nella lista possiamo mettere **qualsiasi selettore**, semplice o complesso.

---

### **4. Evitare ripetizioni: condividere solo ciò che è davvero in comune**

Attenzione: usare le liste di selettori **non significa** che da quel momento in poi quegli elementi debbano avere lo stesso stile completo.

L’idea è:

- mettiamo nella lista **solo le regole che davvero hanno in comune**
    
- lasciamo poi ad ogni selettore le sue **regole specifiche**
    

Esempio:

```css
p, h2, .scemo, #prova {
    color: red;
}
```

Poi possiamo aggiungere stili dedicati:

```css
p {
    background-color: black;
    padding: 10px;
}

h2 {
    text-transform: uppercase;
}

.scemo {
    font-style: italic;
}

#prova {
    font-size: 2rem;
}
```

Risultato:

- `p`, `h2`, `.scemo`, `#prova` condividono **solo** il `color: red`
    
- ognuno ha poi le sue caratteristiche specifiche
    

L’idea chiave:

> Le liste di selettori servono a **non riscrivere cento volte la stessa regola** quando è identica per più elementi.

---

### **5. Esempio pratico importante: pagina a tutto schermo con `html` e `body`**

Adesso vediamo un caso molto realistico, che incontrerai spesso quando farai layout seri:  
la **pagina a tutto schermo**.

#### **5.1. Obiettivo**

Vogliamo:

- sfondo scuro che copre **tutta la finestra**
    
- niente margini bianchi intorno
    
- un titolo centrato esattamente **al centro della pagina**, sia in verticale che in orizzontale
    

Per esempio:

```html
<h1>Corso CSS</h1>
```

#### **5.2. Primo step: stilare il `body`**

Partiamo da qualcosa del genere:

```css
body {
    background-color: #212121;  /* 21 21 21 */
    color: white;
}
```

Visivamente sembra si sia “colorata tutta la pagina”.  
Ma se apriamo gli strumenti di sviluppo e ispezioniamo:

- `html` ha un’altezza limitata al contenuto
    
- `body` è alto solo quanto l’`h1`
    

Quindi:

- lo sfondo in realtà copre solo la parte “occupata” dal contenuto
    
- non abbiamo ancora un vero “full screen”
    

#### **5.3. Centrare il contenuto con Flex (senza preoccuparsi troppo dei dettagli)**

Il prof usa `flex` (che impareremo bene più avanti) per centrare il contenuto:

```css
body {
    background-color: #212121;
    color: white;

    display: flex;
    justify-content: center;   /* centro orizzontale */
    align-items: center;       /* centro verticale */
}
```

Questo centra il nostro `h1` **all’interno del `body`**.  
Ma il `body` è ancora alto solo “quel pezzetto” legato all’`h1`.

Quindi:

> Stiamo centrando il titolo **rispetto a un body basso**, non rispetto all’intera finestra.

#### **5.4. Tentativo: `height: 100%` solo sul `body`**

Proviamo a dire:

```css
body {
    height: 100%;
}
```

Ma non funziona come ci aspettiamo:  
il `body` non riempie ancora tutta la pagina.

Perché?

Perché per usare `height: 100%`, il suo contenitore (cioè `html`) deve avere anch’esso un’altezza definita al 100%.  
Altrimenti il browser non sa “da che cosa prendere il 100%”.

In più, ci sono i **margini di default** che causano scroll e bordini bianchi.

#### **5.5. Sistemare anche `html`: altezza 100% e margini a zero**

Allora aggiungiamo:

```css
html {
    margin: 0;
    width: 100%;
    height: 100%;
}
```

E per il body:

```css
body {
    margin: 0;
    width: 100%;
    height: 100%;

    background-color: #212121;
    color: white;

    display: flex;
    justify-content: center;
    align-items: center;
}
```

Ora:

- `html` è largo 100% e alto 100% della finestra
    
- `body`, che sta dentro `html`, può a sua volta prendere `width: 100%` e `height: 100%`
    
- grazie a `display: flex` e alle altre proprietà, il nostro `h1` è davvero centrato **al centro della pagina intera**
    
- i `margin: 0` su `html` e `body` eliminano i margini di default del browser, evitando scroll indesiderati
    

#### **5.6. Ma vediamo il problema: codice duplicato tra `html` e `body`**

Notiamo subito una cosa:

```css
html {
    margin: 0;
    width: 100%;
    height: 100%;
}

body {
    margin: 0;
    width: 100%;
    height: 100%;

    /* altre regole specifiche del body */
}
```

Abbiamo ripetuto **tre righe identiche**:

- `margin: 0;`
    
- `width: 100%;`
    
- `height: 100%;`
    

sia in `html` che in `body`.

Qui entrano in gioco le **liste di selettori**.

---

### **6. Applicare la lista di selettori a `html` e `body`**

Invece di scrivere due blocchi quasi uguali, possiamo scrivere:

```css
html, body {
    margin: 0;
    width: 100%;
    height: 100%;
}
```

Questa lista dice:

> “Sia `html` che `body` devono avere:  
> `margin: 0`, `width: 100%`, `height: 100%`.”

Poi, separatamente, diamo al `body` le sue regole _extra_:

```css
body {
    background-color: #212121;
    color: white;

    display: flex;
    justify-content: center;
    align-items: center;
}
```

Così:

- **evitiamo duplicazioni**
    
- il codice è più pulito e leggibile
    
- è chiaro che `html` e `body` condividono la stessa “base” di dimensione
    
- il `body` ha poi la responsabilità di gestire l’allineamento a centro e lo stile del contenuto
    

Questo è uno degli esempi **più concreti e frequenti** in cui userai una lista di selettori:

```css
html, body {
    margin: 0;
    width: 100%;
    height: 100%;
}
```

È una base tipica per:

- landing page fullscreen
    
- dashboard
    
- web app a tutto schermo
    
- layout centrati in maniera precisa
    

---

### **7. Ricapitolando: quando usare le liste di selettori**

Le liste di selettori sono utili quando:

1. **Più elementi (o classi, o ID) devono condividere esattamente lo stesso stile**, o parte dello stesso stile.
    
2. Vuoi **evitare ripetizioni** identiche di proprietà e valori.
    
3. Vuoi rendere chiaro che certi selettori fanno parte di una **stessa famiglia logica** (es. `html, body`).
    

Le regole per usarle:

- separa ogni selettore con una **virgola**
    
- dentro le parentesi graffe metti **solo** le proprietà in comune
    
- aggiungi poi blocchi CSS specifici per ogni selettore, se serve
    

Esempio generico:

```css
h2, p, .messaggio, #alert {
    color: red;
}

/* Stili specifici */
h2 { font-weight: bold; }
p { line-height: 1.5; }
.messaggio { border: 1px solid red; }
#alert { background-color: yellow; }
```

---

Con questo abbiamo introdotto in modo completo le **liste di selettori** e visto un caso reale molto usato (`html, body` per avere la pagina a tutto schermo).

Nel prossimo video, come anticipa il prof, cominceremo con **l’esercizio 2**, il primo esercizio pratico di questa nuova sezione sui selettori: inizieremo a sporcarci le mani con tutto quello che abbiamo visto finora.