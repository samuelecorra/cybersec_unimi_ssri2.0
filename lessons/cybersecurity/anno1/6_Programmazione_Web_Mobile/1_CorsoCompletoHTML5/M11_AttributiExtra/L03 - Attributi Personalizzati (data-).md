## **Lezione 3: Attributi Personalizzati (`data-*`)**

Questa è l’ultima lezione del capitolo dedicato agli “attributi extra”, ed è una delle più importanti in assoluto per la programmazione moderna.

Gli **attributi personalizzati** — chiamati _custom attributes_ — sono la base di qualunque interazione dinamica quando passeremo a **JavaScript**.

---

# **1. Cosa sono gli attributi personalizzati**

Hai già visto centinaia di attributi:

- `id`
    
- `class`
    
- `onclick`
    
- `src`
    
- `href`
    
- `alt`
    
- `type`
    
- `aria-*`
    
- ecc.
    

Tutti questi esistono perché HTML li definisce.

Ma cosa succede se vuoi aggiungere un attributo tuo?

Tipo:

```html
<div ciao="true"></div>
```

Risposta:

- **il browser lo accetta**
    
- **la pagina funziona**
    
- **ma il codice è invalido**
    

E infatti il Validator ti spara un errore rosso:

> “L’attributo ‘ciao’ non è permesso su questo elemento”.

Perché?  
Perché **HTML non conosce questo attributo**, quindi lo considera un errore sintattico.

---

# **2. La soluzione standard: `data-*`**

HTML ha definito una convenzione universale:

> se vuoi creare attributi personalizzati, devi usare `data-qualcosa`.

Esempi validi:

```html
<div data-ciao="true"></div>
<div data-id="12345"></div>
<div data-interesse="alto"></div>
<div data-user-name="Edoardo"></div>
<div data-supermegaultravalore="pizza"></div>
```

Puoi scrivere QUALSIASI nome dopo `data-`.  
HTML non protesta mai.  
Il Validator non protesta mai.

Il motivo è semplice:

> Tutto ciò che inizia con `data-` è responsabilità dello sviluppatore.

È il tuo spazio privato, la tua “cassaforte” di dati agganciati all’HTML.

---

# **3. A cosa servono davvero gli attributi `data-*`**

### 💡 Servono a **collegare dati all’HTML**.

E chi li userà?

- **JavaScript** (quasi sempre)
    
- **CSS** (in casi particolari)
    

Gli attributi `data-*` sono fondamentali quando:

- hai elementi ripetuti (card, prodotti, articoli, film…)
    
- ognuno di questi elementi rappresenta una cosa diversa
    
- devi sapere _quale_ hai cliccato
    
- devi leggere un valore associato
    
- devi comunicare un’informazione al backend
    
- devi costruire un’interfaccia dinamica
    

Pensali così:

> gli attributi `data-*` sono il ponte tra HTML statico e logica dinamica.

---

# **4. L’esempio perfetto: le card stile Netflix**

Il tuo docente ha fatto una mini-demo per mostrarti il valore reale.

Immagina una card:

```html
<div class="card" data-id="A123">
  <img src="./img/the-office.jpg" alt="The Office">
  <h2>The Office</h2>
</div>
```

Altre card avranno:

```html
<div class="card" data-id="B992">
<div class="card" data-id="C534">
<div class="card" data-id="Z019">
```

Ogni card ha **lo stesso identico HTML**, solo che cambia:

- immagine
    
- titolo
    
- `data-id`
    

E quando l’utente clicca sulla card?

In JavaScript farai:

```javascript
card.addEventListener("click", function () {
    const id = this.dataset.id;
    console.log(id);
});
```

Magia:

- hai cliccato una card
    
- puoi leggere QUAL È la card cliccata
    
- puoi prendere `this.dataset.id`
    
- puoi usarlo per andare alla pagina giusta del film/serie
    

Questo è il cuore delle web app moderne.

---

# **5. Perché non usare semplicemente gli `id`?**

Per due motivi fondamentali:

### **1. Gli ID devono essere UNICI**

Non posso fare:

```html
<div id="card">…</div>
<div id="card">…</div>
<div id="card">…</div>
```

Ma posso fare:

```html
<div class="card" data-id="33">
<div class="card" data-id="34">
<div class="card" data-id="35">
```

### **2. Le card vengono generate dinamicamente**

Quando farai:

- cicli
    
- template
    
- fetch di dati dal database
    
- rendering automatico
    

Non potrai generare ID unici “a mano”.

`data-*` risolve perfettamente questo problema.

---

# **6. Esempi reali dove si usano `data-*`**

Ovunque.

- Amazon → `data-product-id`
    
- YouTube → `data-video-id`
    
- Instagram → `data-user-id`
    
- Netflix → `data-movie-id`
    
- Google → `data-lang`, `data-theme`
    
- Discord → centinaia di `data-*` per ogni messaggio
    

E più avanti, quando userai librerie come:

- React
    
- Vue
    
- Svelte
    
- Angular
    

Scoprirai che `data-*` è presente dappertutto nei componenti generati.

---

# **7. Possiamo usarli anche in CSS?**

Sì, anche se molto meno spesso.

Puoi fare:

```css
.card[data-id="A123"] {
  border: 2px solid red;
}
```

O:

```css
[data-theme="dark"] {
  background: black;
  color: white;
}
```

È uno strumento potente, ma il vero valore esplode con JavaScript.

---

# **8. Conclusione del capitolo**

Hai chiuso uno dei capitoli più importanti del corso HTML:

- eventi (`onclick`, `onchange`, ecc.)
    
- drag & drop (introduzione)
    
- attributi personalizzati (`data-*`)
    
