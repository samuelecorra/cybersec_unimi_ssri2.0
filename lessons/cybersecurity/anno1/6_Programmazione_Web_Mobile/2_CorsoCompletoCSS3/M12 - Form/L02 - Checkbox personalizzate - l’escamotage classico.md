## **Lezione 2: Checkbox personalizzate - l’escamotage “classico”**

### **1. Perché le checkbox sono “speciali”**

Le checkbox, a differenza degli altri input (`text`, `email`, `password`, ecc.), sono ancora abbastanza “rigide” da stilizzare:

- ogni browser le disegna con il **proprio** stile nativo,
    
- le proprietà CSS standard non bastano per trasformarle liberamente,
    
- non esiste (ancora) una sintassi tipo:  
    `checkbox-background: ...; checkbox-checkmark: ...;` ecc.
    

Risultato: per ottenere una checkbox **veramente custom**, dobbiamo usare un **trucco**:

- mantenere una **checkbox reale** (per accessibilità, focus da tastiera, stato `checked`, ecc.),
    
- **nasconderla visivamente**,
    
- disegnare una **“finta” checkbox** con uno `span`,
    
- e sincronizzare lo stato `checked` della vera checkbox con l’aspetto della finta.
    

È letteralmente un trucco da illusionista: la vera checkbox lavora “sotto il tappeto”, quella che l’utente vede è un elemento qualsiasi che reagisce al suo stato.

---

## **2. Struttura HTML dello stratagemma**

La struttura tipica è:

- una `label` contenitore,
    
- dentro:
    
    - l’`input type="checkbox"` **nascosto**,
        
    - uno `span` che fungerà da “finta” checkbox (il quadratino),
        
    - il testo (“Accetti i termini…?”).
        

Esempio:

```html
<label class="custom-checkbox">
  <input
    type="checkbox"
    class="hidden-checkbox"
    id="prova"
  >
  <span class="checkmark"></span>
  Accetti termini e condizioni di utilizzo
</label>
```

- `custom-checkbox` → è il contenitore cliccabile.
    
- `hidden-checkbox` → la vera checkbox, che verrà nascosta.
    
- `checkmark` → il quadratino che disegniamo noi (la finta checkbox).
    
- Il testo è parte della label, quindi cliccando sul testo si attiva la checkbox.
    

> Nota: usare `<label>` è fondamentale per mantenere il comportamento “corretto”: clic sul testo → toggle della checkbox.

---

## **3. Stilare il contenitore `label.custom-checkbox`**

Prima rendiamo la `label` un target comodo da cliccare:

```css
.custom-checkbox {
  position: relative;
  display: inline-block;
  cursor: pointer;
  padding-left: 35px;   /* spazio per il quadratino a sinistra */
  margin-bottom: 10px;
}
```

- `position: relative;` → serve per posizionare **assolutamente** lo span `.checkmark` all’interno.
    
- `padding-left: 35px;` → creiamo spazio a sinistra del testo per inserire il “quadrato”.
    
- `cursor: pointer;` → dà feedback visivo che è cliccabile.
    

---

## **4. Nascondere la checkbox reale (senza romperne il comportamento)**

La checkbox vera non deve vedersi, ma deve restare **interattiva**:

```css
.hidden-checkbox {
  position: absolute;
  opacity: 0;
}
```

- `opacity: 0;` → non è visibile, ma esiste ed è cliccabile via label.
    
- `position: absolute;` → la togliamo dal flow, non occupa spazio.
    

Se vuoi essere ancora più “preciso” si può anche ridurne dimensioni e spostarla fuori, ma `opacity: 0` è già sufficiente in molti casi.

---

## **5. Disegnare la “finta” checkbox: `.checkmark`**

Ora creiamo il quadratino che **l’utente vedrà**:

```css
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  width: 18px;
  height: 18px;
  border: 3px solid #333;
  border-radius: 0;         /* o 50% se la vuoi tonda */
  background-color: white;  /* opzionale */
}
```

- `position: absolute;` + `top: 0; left: 0;` → lo piazziamo nell’angolo sinistro della label.
    
- `width`/`height` → dimensioni del quadrato.
    
- `border` → il contorno del quadrato.
    
- `border-radius: 0;` → quadrato perfetto, `50%` → cerchietto tipo radio.
    

Se vuoi una checkbox tonda:

```css
.checkmark {
  border-radius: 50%;
}
```

---

## **6. Collegare lo stato `checked` alla finta checkbox**

Qui entra in gioco il trucco vero:  
quando la checkbox reale (`.hidden-checkbox`) è `:checked`, vogliamo che **lo span `.checkmark` mostri la spunta**.

Il selettore chiave è:

```css
.hidden-checkbox:checked ~ .checkmark::before {
  /* stile della spunta */
}
```

Significa:

> “Quando la checkbox nascosta è checked, prendi lo span `.checkmark` che viene **dopo** (`~`) e crea/applica uno pseudo-elemento `::before`.”

### **6.1. Creare la spunta con `::before`**

```css
.hidden-checkbox:checked ~ .checkmark::before {
  content: "\2713";     /* Unicode del segno di spunta ✓ */
  display: block;
  text-align: center;
  line-height: 16px;    /* per centrare verticalmente nel quadrato */
  font-size: 16px;
  font-weight: bold;
  color: green;
}
```

Cosa succede:

- Quando la `.hidden-checkbox` è `checked`, CSS crea un contenuto **solo visivo** (`::before`) dentro `.checkmark`.
    
- `content: "\2713";` → carattere Unicode “checkmark”.
    
- `display: block;` + `text-align: center;` → centriamo la spunta nello span.
    
- `line-height` simile all’altezza del quadratino → centraggio verticale.
    

Quando la checkbox NON è `checked`, questo `::before` semplicemente **non esiste**, quindi il quadrato è vuoto.

---

## **7. Come funziona il trucco “davvero” (step mentale)**

1. **L’utente clicca sulla label** (testo o quadrato non importa).
    
2. La label è collegata alla `.hidden-checkbox` → la checkbox reale cambia stato (`checked` / non `checked`).
    
3. Il selettore `.hidden-checkbox:checked ~ .checkmark::before` diventa attivo.
    
4. CSS disegna la spunta dentro `.checkmark`.
    
5. Visivamente sembra che la “checkbox” si sia attivata, ma in realtà:
    
    - l’unico elemento interattivo vero è la checkbox nascosta,
        
    - lo span è solo un **indicatore grafico** dello stato di quella checkbox.
        

È un trucco da illusionista:

- il “meccanismo vero” è nascosto (`.hidden-checkbox`),
    
- quello che vedi (`.checkmark`) è solo una rappresentazione grafica sincronizzata tramite pseudo-classi e pseudo-elementi.
    

---

## **8. Personalizzazione avanzata**

Una volta impostato lo scheletro puoi fare di tutto con `.checkmark` e con `::before`:

- cambiare il `background-color` quando è checked:
    

```css
.hidden-checkbox:checked ~ .checkmark {
  background-color: #4caf50;
  border-color: #4caf50;
}
```

- usare un’icona SVG invece del carattere Unicode,
    
- aggiungere `transition` per animare il cambio di stato:
    

```css
.checkmark {
  transition: background-color 0.2s ease, border-color 0.2s ease;
}

.hidden-checkbox:checked ~ .checkmark {
  background-color: #4caf50;
  border-color: #4caf50;
}
```

- cambiare forma, ombre, scale, ecc.
    

L’unica cosa che resta fissa è il **pattern logico**:

- checkbox vera nascosta,
    
- elemento finto che si aggiorna in base allo stato `:checked`.
    

---

## **9. Perché è un “escamotage” tipico di CSS**

In un mondo ideale (o con un futuro CSS4 più ricco) potremmo avere:

- proprietà native per controllare l’aspetto del check,
    
- pseudo-elementi dedicati tipo `::checkmark`,
    
- shorthand come `checkbox-style: filled-circle` o simili.
    

Ad oggi, però:

- CSS è maturo,
    
- i bisogni di design sono avanzati,
    
- ma alcune cose (come la checkbox super custom) **non sono ancora gestite nativamente**.
    

Quindi si usano questi “trucchi”, esattamente come hai visto:

- sfruttare HTML + CSS + pseudo-classi,
    
- combinare elementi nascosti e “finti” per ottenere il risultato desiderato,
    
- mantenendo funzionalità e accessibilità della checkbox di base.
    

Con questa base pronta, possiamo ora passare alle **pseudoclassi dei form** in generale (`:focus`, `:valid`, `:invalid`, `:disabled`, ecc.) per rifinire l’esperienza utente sugli input.