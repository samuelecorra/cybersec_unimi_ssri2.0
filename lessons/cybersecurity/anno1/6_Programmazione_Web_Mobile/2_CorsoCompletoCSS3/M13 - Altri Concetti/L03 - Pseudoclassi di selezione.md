## **Lezione 3: Pseudoclassi di selezione: `:first-child`, `:nth-child`, `:of-type`, `:empty`**

### **1. Ripasso veloce: cosa sono queste pseudoclassi**

Finora abbiamo usato pseudoclassi soprattutto per:

- **stato dei link / form** (`:hover`, `:focus`, `:valid`, `:invalid`, `:checked`, ecc.).
    

In questa lezione vediamo un altro gruppo importante:

> Pseudoclassi che **selezionano elementi in base alla loro posizione tra i figli**  
> (primo, ultimo, solo figlio, ennesimo figlio, di un certo tipo, ecc.).

Sono potentissime quando:

- vuoi stilare **solo il primo / ultimo elemento di una lista**,
    
- vuoi dare uno stile diverso al **figlio unico**,
    
- vuoi applicare uno stile al **terzo, quinto, ecc. elemento**,
    
- vuoi distinguere non solo “figlio n-esimo”, ma “figlio n-esimo di quel tipo”.
    

---

### **2. `:first-child`, `:last-child`, `:only-child`**

Partiamo da una lista:

```html
<ul>
  <li>Ciao 1</li>
  <li>Ciao 2</li>
  <li>Ciao 3</li>
</ul>
```

E magari aumentiamo la leggibilità:

```css
li {
  font-size: 30px;
}
```

#### **2.1 `:first-child`**

```css
li:first-child {
  color: red;
}
```

- Seleziona il `li` che è **primo figlio** del suo genitore.
    
- Funziona ovunque: se metti più `<ul>` con `<li>`, il **primo `<li>` di ogni `<ul>`** viene colorato.
    

> Se vuoi il primo `li` di **una specifica** lista, devi selezionare prima quella lista con una classe o un id, ad esempio:
> 
> ```css
> ul.menu li:first-child { ... }
> ```

#### **2.2 `:last-child`**

```css
li:last-child {
  color: blue;
}
```

- Seleziona **l’ultimo figlio** del genitore.
    
- Anche qui: se hai più liste, l’ultimo elemento di ognuna viene preso.
    

#### **2.3 `:only-child`**

```css
li:only-child {
  color: green;
}
```

Seleziona il `li` che è **l’unico figlio del genitore**.

Esempio:

```html
<ul>
  <li>Unico elemento</li>
</ul>
```

Qui quel `li` è:

- **primo** figlio,
    
- **ultimo** figlio,
    
- ma soprattutto **only-child**, perché **non ha fratelli**.
    

Se invece hai:

```html
<ul>
  <li>Elemento 1</li>
  <li>Elemento 2</li>
</ul>
```

- Nessuno dei due è `:only-child`.
    

Uso tipico:

- liste o contenitori che, quando hanno **un solo elemento**, vengono mostrati con uno stile “speciale” (es. messaggi vuoti, placeholder, stato particolare dell’interfaccia).
    

---

### **3. `:nth-child()` e l’idea di “posizione n-esima”**

`nth-child()` ti permette di prendere l’**elemento alla posizione N** tra i figli.

```css
li:nth-child(2) {
  color: orange;
}
```

- Seleziona il **secondo figlio** del genitore (qui il secondo `li`).
    
- **Attenzione**: in CSS si parte da **1**, non da 0 come in molti linguaggi di programmazione.
    

Con:

```html
<ul>
  <li>1</li>  <!-- nth-child(1) -->
  <li>2</li>  <!-- nth-child(2) -->
  <li>3</li>  <!-- nth-child(3) -->
  <li>4</li>  <!-- nth-child(4) -->
  <li>5</li>  <!-- nth-child(5) -->
</ul>
```

- `li:nth-child(5)` seleziona il quinto `li`.
    

> Nota: `:nth-child()` accetta anche formule tipo `2n`, `2n+1`, ecc., ma qui l’uso base è il singolo numero.

---

### **4. `:empty`**

La pseudoclasse `:empty` seleziona gli elementi **che non contengono nulla**:

- niente testo,
    
- niente elementi figli,
    
- neanche spazi o ritorni a capo visibili (se c’è un carattere, non è più vuoto).
    

Esempio:

```html
<ul>
  <li>Con testo</li>
  <li></li>
</ul>
```

```css
li:empty {
  color: red;
  /* oppure un'icona, un before, un messaggio, ecc. */
}
```

Nel nostro caso:

- il secondo `li` è vuoto,
    
- viene selezionato da `li:empty`.
    

È utile quando:

- hai card / blocchi / colonne che a volte sono senza contenuto,
    
- vuoi mostrare uno stile speciale per “slot vuoti” (placeholder, messaggi tipo “Nessun elemento”, ecc.).
    

---

### **5. Versioni “of-type”: `:first-of-type`, `:last-of-type`, `:nth-of-type`, `:only-of-type`**

Finora abbiamo lavorato su **figli in generale** (indipendentemente dal loro tag).

Adesso vediamo le versioni che funzionano **per tipo di elemento**.

Immagina:

```html
<section>
  <div>Ciao</div>
  <ul>
    <li>Voce 1</li>
    <li>Voce 2</li>
  </ul>
  <p>
    Testo con uno <strong>strong</strong> dentro
  </p>
</section>
```

Aggiungiamo un po’ di markup:

```html
<p>
  Testo con uno <strong>strong 1</strong> dentro
</p>
<p>
  Altro testo con <strong>strong 2</strong>
</p>
```

#### **5.1 `:first-of-type`**

```css
strong:first-of-type {
  color: red;
}
```

- Seleziona il **primo `strong`** tra gli `strong` fratelli dello stesso genitore.
    
- Se ci sono più `strong` dentro quello stesso `p`, prende il primo.
    

Se hai:

```html
<p>
  <strong>uno</strong>
  <strong>due</strong>
  <strong>tre</strong>
</p>
```

- `strong:first-of-type` colpisce solo **“uno”**.
    

#### **5.2 `:last-of-type`**

```css
strong:last-of-type {
  color: blue;
}
```

- Stesso concetto, ma per **l’ultimo** `strong`.
    

#### **5.3 `:nth-of-type()`**

```css
strong:nth-of-type(2) {
  color: orange;
}
```

- Seleziona il **secondo `strong`** tra gli `strong` fratelli **dello stesso genitore**.
    

#### **5.4 `:only-of-type`**

```css
p:only-of-type {
  color: green;
}
```

- Seleziona l’elemento che è **l’unico di quel tipo** tra i figli del genitore.
    

Esempio:

```html
<section>
  <p>Unico paragrafo</p>
  <ul>...</ul>
</section>
```

Qui:

- c’è solo un `p` dentro `section`,
    
- quindi `p:only-of-type` lo seleziona.
    

Se invece ci fossero due `<p>`, nessuno dei due sarebbe `:only-of-type`.

---

### **6. Differenza chiave: `*child` vs `*of-type`**

Ricapitolando:

- `:first-child`, `:last-child`, `:nth-child()`, `:only-child`
    
    - lavorano sulla **posizione rispetto a tutti i figli**, indipendentemente dal tag;
        
- `:first-of-type`, `:last-of-type`, `:nth-of-type()`, `:only-of-type`
    
    - lavorano sulla **posizione rispetto ai fratelli dello stesso tipo di elemento**.
        

Esempio pratico:

```html
<section>
  <h2>Titolo</h2>
  <p>Paragrafo 1</p>
  <p>Paragrafo 2</p>
</section>
```

- `p:first-child` **non** seleziona nulla  
    (il primo child è `h2`, non `p`).
    
- `p:first-of-type` seleziona **“Paragrafo 1”**  
    (è il primo `p` tra i `p`).
    

---

### **7. A cosa servono in pratica**

Queste pseudoclassi sono fondamentali per:

- liste (`ul`, `ol`, `menu`, ecc.),
    
- tabelle (`tr`, `td`),
    
- serie di card (`.card` dentro una `.grid`),
    
- componenti ripetuti (badge, tag, pillole, ecc.).
    

Esempi concreti:

- eliminare il margine sull’**ultimo elemento** (`:last-child`) di una lista verticale,
    
- stilare in modo diverso **il primo e l’ultimo elemento** di un menu,
    
- dare uno stile specifico a una card che è **l’unica** in una riga (`:only-child` / `:only-of-type`),
    
- evidenziare una particolare posizione (es. il **terzo** elemento di una timeline) con `:nth-child(3)`.
    

---

### **8. Riepilogo rapido**

Le pseudoclassi viste:

- **Per posizione generale:**
    
    - `:first-child`
        
    - `:last-child`
        
    - `:only-child`
        
    - `:nth-child(n)`
        
    - `:empty`
        
- **Per tipo di tag:**
    
    - `:first-of-type`
        
    - `:last-of-type`
        
    - `:only-of-type`
        
    - `:nth-of-type(n)`
        

Sono tutte **pseudoclassi di selezione**: non parlano di stato (hover, focus, checked…), ma di **posizione e struttura** del DOM.

Nelle prossime parti andremo ad affrontare i selettori e concetti che avevamo lasciato da parte all’inizio perché più complessi, ma che ora puoi capire molto meglio grazie a questo blocco di pseudo-selettori.