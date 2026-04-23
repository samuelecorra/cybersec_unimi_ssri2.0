## **Lezione – Il Selettore di Classe (`.classe`)**

### **1. Perché ci servono le classi (riprendiamo il problema dei paragrafi “particolari”)**

Qualche video fa avevamo questo problema:  
avevamo più paragrafi `<p>` “normali” (ad esempio rossi) e alcuni paragrafi che erano **“particolari”** e dovevano avere uno stile diverso.

Il punto era:

- alcuni `<p>` devono avere **uno stile speciale**
    
- tutti questi `<p>` speciali sono “particolari” allo stesso modo
    
- non vogliamo chiamarli `prova`, `ciao`, `pippo`, ecc.
    
- vogliamo una sola etichetta comune: **“particolare”**
    

Con i selettori di elemento (`p`) non possiamo distinguere tra un paragrafo e l’altro.  
Con gli ID (`id="prova"`) non possiamo, perché **l’ID è univoco**: non posso avere più volte `id="prova"`.

Ci serve quindi **un attributo che possa essere condiviso da più elementi**, uno “stesso nome” che possa valere per molti paragrafi contemporaneamente.

Questo attributo è **la classe**.

---

### **2. ID vs classi: metafora codice fiscale, nome e “gang”**

Per capire la differenza tra ID e classi, il prof usa due metafore:

#### **2.1. L’ID come codice fiscale**

- l’**ID** in HTML/CSS è come il **codice fiscale**:  
    è **univoco**, ce l’hai solo tu
    
- anche se esistono più persone che si chiamano “Edoardo”,  
    il codice fiscale di _quel_ Edoardo è uno solo, diverso da tutti gli altri
    
- perché due persone abbiano lo stesso codice fiscale, dovrebbero:
    
    - chiamarsi uguali (nome e cognome)
        
    - essere nati nello stesso giorno, mese, anno
        
    - nello stesso luogo  
        …praticamente impossibile
        

Quindi:

> L’ID è un identificatore **unico**: un solo elemento nella pagina può avere quell’ID.

---

#### **2.2. Le classi come “gang”, gruppi, categorie**

Le **classi**, invece, sono come:

- gruppi sociali
    
- categorie
    
- “gang” di persone anni ‘80/’90
    

Esempio: i **punk**.

- tanti ragazzi possono far parte della **classe “punk”**
    
- hanno uno stile in comune: vestiti neri, colori strani, pantaloni attillati, ecc.
    
- “punk” è una **categoria** condivisa
    

Allo stesso modo:

- ci possono essere molti **Edoardo**
    
- molti **Midali**
    
- forse un altro **Edoardo Midali**
    
- ma **il codice fiscale (ID)** di un particolare Edoardo Midali è solo suo
    

Tradotto in CSS/HTML:

- **ID** → identificatore **univoco** (come codice fiscale)
    
- **classi** → gruppi non univoci, condivisi (come “punk”, “studenti”, “motociclisti”, ecc.)
    

Le classi servono a dire:

> “Questi elementi appartengono allo stesso gruppo e quindi hanno **lo stesso stile**.”

Un elemento può avere:

- **un solo ID**
    
- **più classi** contemporaneamente
    

---

### **3. Definire e usare una classe “particolare”**

Torniamo ai nostri paragrafi.  
Vogliamo indicare che alcuni di essi sono “particolari”.

Nell’HTML scriviamo, ad esempio:

```html
<p class="particolare">Primo paragrafo particolare</p>
<p class="particolare">Secondo paragrafo particolare</p>
<p>Paragrafo normale</p>
```

In questo modo:

- i primi due `<p>` appartengono alla **classe `particolare`**
    
- il terzo è un paragrafo normale
    

Nel CSS definiamo lo stile della classe così:

```css
.particolare {
    background-color: #212121;   /* quasi nero: 21 21 21 */
    color: yellow;
    padding: 10px;
}
```

Effetto:

- tutti gli elementi con `class="particolare"` avranno:
    
    - sfondo quasi nero (`#212121`)
        
    - testo giallo
        
    - padding di 10px
        

Non importa **quanti** paragrafi particolari abbiamo:  
basta scrivere `class="particolare"` e **tutti erediteranno lo stesso stile**.

Questo è esattamente il problema che non potevamo risolvere con:

- selettori di elemento (`p`)
    
- o con ID (`id="prova"`), perché l’ID è unico
    

---

### **4. Classi, ID e specificità: chi vince?**

Nel nostro esempio, alcuni paragrafi particolari hanno **anche un ID**, ad esempio:

```html
<p id="prova" class="particolare">...</p>
<p id="ciao" class="particolare">...</p>
```

E nel CSS:

```css
p {
    color: red;
}

.particolare {
    background-color: #212121;
    color: yellow;
    padding: 10px;
}

#prova {
    color: blue;
}

#ciao {
    color: green;
}
```

Cosa succede?

- tutti i `p` → rossi, per colpa del selettore `p`
    
- i `p.particolare` → sfondo scuro, padding, e colore giallo
    
- ma quelli con ID:
    
    - `#prova` → diventa **blu**
        
    - `#ciao` → diventa **verde**
        

Perché?

Per via della **specificità**:

- elemento (`p`) → 1 punto
    
- classe (`.particolare`) → 10 punti
    
- ID (`#prova`, `#ciao`) → 100 punti
    

Quindi:

- `p` → colore rosso (1 punto)
    
- `.particolare` → colore giallo (10 punti)
    
- `#prova` / `#ciao` → blu/verde (100 punti)
    

L’ID prevale sulla classe e sul tag elemento.  
Per questo il colore giallo della classe viene **sovrascritto** dal colore dell’ID.

Se “facciamo finta” che l’ID non esista (cioè togliamo le regole `#prova` e `#ciao`), **tutti i `particolare` diventano gialli**, come definito dalla classe.

Questo esempio serve a farti vedere una cosa che può capitare spesso:

> Un elemento può far parte di una classe, **ma avere anche un ID** con regole aggiuntive.  
> In quel caso, le regole dell’ID vincono per specificità.

---

### **5. Più classi sullo stesso elemento: `particolare`, `scemo`, `palestrato`**

Una cosa bellissima delle classi è che:

> Un elemento può appartenere a **più classi contemporaneamente**.

Esempio in HTML:

```html
<p class="particolare scemo">...</p>
<p class="particolare">...</p>
<p class="palestrato scemo">...</p>
```

E nel CSS:

```css
.particolare {
    background-color: #212121;
    color: yellow;
    padding: 10px;
}

.scemo {
    height: 100px;
}

.palestrato {
    margin-bottom: 120px;
}
```

Cosa succede?

- gli elementi con `.particolare` → sfondo scuro, testo giallo, padding 10px
    
- gli elementi con `.scemo` → altezza 100px
    
- gli elementi con `.palestrato` → margine inferiore di 120px
    

Quindi un elemento con classi `particolare scemo`:

- ha **tutti gli stili** di `.particolare`
    
- e **tutti gli stili** di `.scemo`
    

Un elemento con classi `palestrato scemo`:

- ha stili da `.palestrato`
    
- e stili da `.scemo`
    

---

#### **5.1. Metafora: avvocato, cassiere, motociclista, palestrato**

Il prof fa un’altra metafora “sociale”:

- una persona può essere **avvocato** (prima classe)
    
- un’altra **cassiere** (altra classe)
    
- entrambe possono essere anche **motociclisti** (classe comune)
    
- un’altra persona ancora può essere **palestrata**
    
- ogni classe aggiunge un’etichetta, uno “stile sociale”
    

Tradotto:

- le **classi sono etichette**
    
- un elemento può essere contemporaneamente:
    
    - `.studenti`
        
    - `.universitari`
        
    - `.over-30`
        
    - `.padri`
        
    - `.motociclisti`
        
    - ecc.
        

La cosa che **non cambia mai** è l’ID (il “codice fiscale”).  
Le classi si combinano, l’ID rimane unico.

---

### **6. Selettore con più classi attaccate: `.particolare.scemo`**

Adesso facciamo un passo in più:  
non solo un elemento può avere più classi, ma possiamo scrivere selettori che le richiedono **tutte insieme**.

Esempio:

```css
.particolare.scemo {
    border-radius: 10px;
    height: 200px;
}
```

Questo selettore significa:

> “Seleziona gli elementi che hanno **sia** la classe `particolare` **sia** la classe `scemo`.”

Solo gli elementi con **entrambe** le classi vengono colpiti.

Esempio HTML:

```html
<p class="particolare scemo">Io ho sia particolare che scemo</p>
<p class="particolare">Io sono solo particolare</p>
<p class="scemo">Io sono solo scemo</p>
```

Risultato:

- il primo `<p>` → ha:
    
    - gli stili di `.particolare`
        
    - gli stili di `.scemo`
        
    - **e** gli stili di `.particolare.scemo` (border-radius, height, ecc.)
        
- il secondo → solo gli stili di `.particolare`
    
- il terzo → solo gli stili di `.scemo`
    

È fondamentale:

> Se sono **attaccati** (`.particolare.scemo`), significa “**stesse classi sullo stesso elemento**”.

Questo vale indipendentemente dal tag:  
posso avere anche:

```html
<h1 class="particolare scemo">Titolo particolare e scemo</h1>
```

e anche l’`h1` prenderà **gli stessi stili** definiti in `.particolare` e `.particolare.scemo`.

---

### **7. Selettore con spazio: `.particolare .scemo` (discendente)**

Ora cambiamo leggermente sintassi:

```css
.particolare .scemo {
    height: 200px;
    border-radius: 10px;
    background-color: red;
}
```

Attenzione: qui c’è uno **spazio** tra `.particolare` e `.scemo`.

Significato:

> “Seleziona gli elementi di classe `.scemo` che si trovano **all’interno** di un elemento con classe `.particolare`.”

Non stiamo più parlando di **uno stesso elemento** che ha entrambe le classi.  
Stiamo parlando di:

- un **contenitore** (classe `particolare`)
    
- che **contiene** un altro elemento (classe `scemo`)
    

Esempio HTML:

```html
<p class="particolare">
    Paragrafo particolare
    <span class="scemo">Ciao</span>
</p>

<p>
    Paragrafo normale
    <span class="scemo">Ciao anche da qui</span>
</p>
```

In questo caso:

- il primo `span.scemo` → si trova **dentro un elemento `.particolare`**
    
- il secondo `span.scemo` → si trova **dentro un `<p>` normale**, senza classe `particolare`
    

Con il selettore `.particolare .scemo`:

- solo il **primo** `span` viene stilizzato (height 200px, bordo arrotondato, sfondo rosso)
    
- il secondo `span.scemo`, non essendo dentro `.particolare`, **non viene toccato**
    

Per rendere visibile l’altezza dello `span`, nel video:

- si imposta `display: block` sullo `span`
    
- si mette un `background-color: red`
    
- si aumenta `height: 200px`
    
- si aggiunge `border-radius: 10px`
    

In questo modo si vede chiaramente che solo lo `span` dentro `.particolare` viene colpito.

Se poi aggiungiamo `class="particolare"` anche al secondo `p`, in modo che entrambi i paragrafi siano `.particolare`, allora **entrambi gli `span.scemo` verranno colpiti** dal selettore `.particolare .scemo`.

---

### **8. Differenza chiave: `.particolare.scemo` vs `.particolare .scemo`**

Riassumiamo questa differenza importantissima:

- `.particolare.scemo`  
    → **stesso elemento** che ha sia la classe `particolare` sia la classe `scemo`
    
- `.particolare .scemo`  
    → un elemento con classe `scemo` che si trova **dentro** un elemento con classe `particolare` (relazione di discendenza)
    

La sintassi:

- **senza spazio** → più classi sullo stesso elemento
    
- **con spazio** → combinatore “discendente”: un elemento dentro un altro
    

È una differenza **sottile nella scrittura**, ma **enorme nel risultato**.

---

### **9. Conclusione della lezione**

In questa lezione abbiamo visto, in modo approfondito:

- perché le classi esistono e perché servono rispetto agli ID
    
- la metafora codice fiscale (ID) vs nomi / gruppi (classi)
    
- come definire una classe (`.particolare`) e usarla su più elementi
    
- come specificità e ID possono sovrascrivere lo stile delle classi
    
- come un elemento può avere più classi contemporaneamente (`particolare`, `scemo`, `palestrato`)
    
- come selezionare:
    
    - elementi con **più classi contemporaneamente** (`.particolare.scemo`)
        
    - elementi **di una classe contenuti** in un’altra (`.particolare .scemo`)
        
- la differenza concettuale e pratica tra le due sintassi
    

Siamo andati lunghi, ma **questa base sulle classi è fondamentale**.  
Non era possibile spiegarla davvero bene nel corso HTML; ora in CSS diventa centrale.

Nel **prossimo video** il prof parlerà delle **liste di selettori**, un argomento molto più leggero, ma che completa il quadro dei fondamenti sui selettori.