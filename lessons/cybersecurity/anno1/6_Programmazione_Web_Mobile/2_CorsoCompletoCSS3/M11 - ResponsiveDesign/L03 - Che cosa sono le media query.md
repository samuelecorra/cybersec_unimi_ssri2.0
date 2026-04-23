
**Lezione 3: Che cosa sono le media query**

Le media query sono uno strumento essenziale del CSS “moderno”, cioè da quando esistono i dispositivi mobile in massa (circa da 15 anni in poi).

Puoi vederle come:

> un blocco speciale di CSS che viene applicato **solo se una certa condizione è vera**.

Esempi di condizioni:

- larghezza dello schermo minore di un certo valore
    
- larghezza compresa tra due valori
    
- orientamento dello schermo (portrait vs landscape)
    
- tipo di media (schermo normale, stampa, ecc.)
    

Il caso più comune è usare le media query per i **breakpoint**, cioè i “punti di rottura” tra un tipo di schermo e l’altro:

- sotto una certa larghezza → layout mobile
    
- tra due larghezze → layout tablet
    
- sopra una certa larghezza → layout desktop
    

---

**Esempio pratico: layout flex da mobile a desktop**

Riprendiamo l’esempio dei box affiancati con Flexbox.

Per desktop li vogliamo **in orizzontale**, mentre per mobile li vogliamo **uno sotto l’altro** (colonna).  
Se non usiamo media query, otteniamo o solo la versione desktop o solo la versione mobile.

Idea:

- scrivo il CSS base pensando al **mobile**
    
- poi, con le media query, modifico il layout per tablet e desktop
    

E qui entra il concetto fondamentale:

---

**Mobile first: perché si parte sempre dal mobile**

“Mobile first” significa:

1. Scrivo il CSS base come se stessi progettando solo per mobile
    
2. Solo dopo, con le media query, “aggiusto” per tablet, laptop, desktop grandi
    

Perché conviene?

- La navigazione web oggi è **maggioritaria da dispositivi mobili** (smartphone, spesso anche tablet)
    
- Tante persone usano il telefono:
    
    - sui mezzi pubblici
        
    - in giro
        
    - la sera sul divano con tablet/telefono anche se hanno un PC in casa
        
- Su desktop, spesso “qualcosa si vede” anche se il layout non è perfetto.  
    Su mobile, se sbagli layout, il sito diventa proprio inutilizzabile (testo microscopico, elementi fuori schermo, impossibilità di cliccare).
    

Quindi l’ordine corretto è:

- prima progetto bene la versione mobile (quella più critica)
    
- poi espando verso schermi più grandi con le media query
    

---

**Sintassi base di una media query**

Struttura generale:

```css
@media screen and (max-width: 300px) {
  /* CSS che verrà applicato
     solo se la larghezza dello schermo è
     al massimo 300px */
}
```

Spiegazione:

- `@media` → introduce il blocco speciale
    
- `screen` → tipo di media (schermo). Esistono anche altre media type (es. `print` per la stampa)
    
- `and` → combinazione logica
    
- `(max-width: 300px)` → condizione: larghezza massima 300px
    

Se la condizione è vera, il codice dentro le parentesi graffe viene applicato (e può sovrascrivere il CSS definito prima).

---

**Caso reale: sezione “immagine + testo” alternata**

Immagina di avere sezioni tipo:

- sezione 1: immagine – testo
    
- sezione 2: testo – immagine
    
- sezione 3: immagine – testo
    
- sezione 4: testo – immagine
    

Per desktop questo è carino (layout alternato).  
Per mobile, invece, quasi sempre vogliamo:

- immagine sopra
    
- testo sotto
    

per tutte le sezioni, per non creare confusione.

Strategia usata nel corso:

- HTML:
    
    - la sezione “dispari” ha già l’ordine immagine → testo
        
    - quella “pari” ha l’ordine testo → immagine
        
- CSS mobile (base): gestisco l’ordine con `order` (Flexbox) per fare in modo che da mobile l’ordine sia sempre immagine sopra, testo sotto
    
- CSS desktop (media query da 768px in su): sistemo l’ordine alternato
    

Per gestire solo le sezioni pari, uso una classe speciale, ad esempio:

```html
<section class="ordinare">
  ...
</section>
```

E nel CSS, fuori dalle media query:

```css
.ordinare p {
  order: 2;
}

.ordinare div {
  order: 1;
}
```

Questo è il comportamento **mobile first** (mobile sistemato per primo).

Poi, con una media query per tablet/desktop, cambio layout e ordini:

```css
@media screen and (min-width: 768px) {
  section {
    display: flex;
    flex-direction: row;
  }

  .ordinare p {
    order: 1;
  }

  .ordinare div {
    order: 2;
  }
}
```

Spiegazione:

- sotto i 768px → layout verticale, immagine sopra, testo sotto
    
- da 768px in su → layout orizzontale, con sezioni alternate grazie all’uso della classe `.ordinare`
    

Nota: si è visto anche che, in alternativa, si potrebbe mettere **sempre** l’immagine prima del testo nell’HTML e usare solo le media query per cambiare l’ordine da desktop. È una scelta di stile:

- se vuoi leggere l’HTML e capire subito quali sezioni sono “alternate”, preferisci scrivere l’HTML già alternato e usare le classi `.ordinare`
    
- se preferisci avere un HTML più uniforme, metti sempre immagine → testo e alterni solo via CSS
    

Entrambe le soluzioni sono valide.

---

**Secondo esempio: box che cambia colore ai breakpoints**

Costruiamo un esempio più semplice per capire la logica dei breakpoint.

HTML:

```html
<div class="box">1</div>
```

CSS base:

```css
.box {
  height: 500px;
  background-color: red;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 60px;
  font-weight: bold;
  color: white;
}
```

Ora vogliamo:

- mobile “piccolo” → rosso (default)
    
- da 425px in su → salmone
    
- da 768px in su → blu
    
- da 1024px in su → giallo-verde
    

Possiamo farlo così:

```css
@media screen and (min-width: 425px) {
  .box {
    background-color: salmon;
  }
}

@media screen and (min-width: 768px) {
  .box {
    background-color: blue;
  }
}

@media screen and (min-width: 1024px) {
  .box {
    background-color: yellowgreen;
  }
}
```

Comportamento:

- sotto 425px → rosso (CSS base)
    
- da 425px a 767px → salmone
    
- da 768px a 1023px → blu
    
- da 1024px in su → giallo-verde
    

Occhio all’errore classico visto nel video:  
se sbagli una larghezza (es. scrivi `1024 pixel` invece di `1024px`), la media query non viene applicata e ti sembra che “non funzioni” nulla.

---

**Uso combinato di min-width e max-width**

Possiamo anche limitare una media query a una “finestra” di valori:

```css
@media screen and (min-width: 1024px) and (max-width: 1100px) {
  .box {
    background-color: yellowgreen;
  }
}
```

Qui `yellowgreen` viene usato solo se:

- larghezza ≥ 1024px
    
- e larghezza ≤ 1100px
    

Se poi allarghi oltre 1100px, questa condizione non è più vera e il browser “torna indietro” alla media query precedente che ancora è valida (o al CSS base, se nessuna media query successiva è valida).

Stesso discorso se scrivi:

```css
@media screen and (min-width: 768px) and (max-width: 1024px) {
  .box {
    background-color: salmon;
  }
}
```

Solo in quel range vedrai il colore salmone; fuori ritorni agli altri stili definiti da altre media query.

---

**Media query e orientamento: landscape vs portrait**

Possiamo filtrare anche per orientamento:

```css
@media screen and (orientation: landscape) {
  .box {
    background-color: darkred;
  }
}
```

Se testiamo su un device emulato (es. iPhone X nei DevTools):

- in portrait → questa media query è falsa
    
- in landscape → questa media query diventa vera
    

Attenzione però a un dettaglio importante:

Quando ruoti il dispositivo:

- `width` e `height` si scambiano
    
- quindi la tua `min-width` potrebbe improvvisamente diventare vera o falsa
    

Esempio:

```css
@media screen and (min-width: 768px) and (orientation: landscape) {
  .box {
    background-color: darkred;
  }
}
```

Questa media query è vera solo se:

- la larghezza è almeno 768px
    
- lo schermo è in landscape
    

In più, se questa media query è scritta **dopo** altre media query, può sovrascrivere i colori già settati in precedenza. Se è scritta **prima**, può essere a sua volta sovrascritta da media query successive.

Ordine e condizioni contano tantissimo.

---

**Il problema vero delle media query: sovrapposizioni logiche**

La difficoltà delle media query non è tanto la sintassi, ma:

- più condizioni scrivi (min-width, max-width, orientation, ecc.)
    
- più è facile che
    
    - due (o più) media query siano **contemporaneamente vere**
        
    - alcune regole vengano sovrascritte e non te ne accorgi
        
    - il risultato finale non sia quello che ti aspettavi
        

Quindi hai due livelli di complessità:

1. La normale cascata CSS (ordine delle regole, specificità, ecc.)
    
2. La logica delle condizioni (`min-width`, `max-width`, `orientation`, ecc.) che determinano quali blocchi siano attivi
    

È facilissimo pensare “questa media query è l’ultima, quindi vince lei”, ma in realtà:

- se un’altra media query successiva è ancora vera e ridefinisce la stessa proprietà
    
- oppure se hai sbagliato un valore (px, min/max, ecc.)
    
- ottieni risultati poco intuitivi
    

---

**Come aiutarsi: usare DevTools per ispezionare le media query attive**

Nei DevTools (F12), nella scheda degli stili, puoi vedere:

- tutte le media query attive in quel momento
    
- da quale file/regola arrivano
    
- in che ordine vengono applicate
    
- quali proprietà vengono sovrascritte da altre
    

Vantaggi:

- non devi “indovinare” quali media query sono vere: lo vedi chiaramente
    
- puoi cliccare sulle media query, modificare i valori al volo e vedere l’effetto in tempo reale
    
- puoi controllare anche font-size, padding, margini, non solo i colori
    

---

**Uso tipico delle media query nella pratica**

Nella maggior parte dei casi, userai le media query per:

- cambiare layout (es. `flex-direction: column` → `row`)
    
- spostare l’ordine di elementi in Flexbox o Grid (`order`, `grid-template-columns`, ecc.)
    
- modificare grandezza dei font, padding, margini, spazi
    
- nascondere o mostrare certi elementi solo su mobile o solo su desktop
    
- cambiare dimensioni di immagini, card, sidebar, menu
    

Quindi il pattern classico è:

- CSS base = versione mobile (mobile first)
    
- media query 1 = tablet
    
- media query 2 = laptop
    
- media query 3 = desktop molto grandi
    

Dentro ogni media query, fai piccoli “aggiustamenti” mirati, senza riscrivere tutto.