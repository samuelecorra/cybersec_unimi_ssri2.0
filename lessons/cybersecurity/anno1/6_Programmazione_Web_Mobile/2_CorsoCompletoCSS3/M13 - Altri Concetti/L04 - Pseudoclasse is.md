## **Lezione 4: Pseudoclasse `:is()`**

### **1. Idea intuitiva di `:is()`**

La pseudoclasse `:is()` serve a **scrivere meno codice** quando hai:

- **un pezzo di selettore finale uguale**
    
- che vuoi applicare a **più “prefissi” diversi**.
    

L’idea è:

- invece di scrivere:
    
    ```css
    ul li,
    ol li {
      font-size: 30px;
    }
    ```
    
- puoi scrivere:
    
    ```css
    :is(ul, ol) li {
      font-size: 30px;
    }
    ```
    

`is(ul, ol)` significa:

> “Prendi tutti gli elementi che sono **ul** oppure **ol**… e poi dentro di loro prendi i `li`”.

In pratica `:is()` ti evita di **ripetere mille volte lo stesso pezzo di selettore finale**.

---

### **2. Primo esempio: `ul` + `ol` con `li`

Immagina questo HTML:

```html
<ul>
  <li>Voce UL 1</li>
  <li>Voce UL 2</li>
</ul>

<ol>
  <li>Voce OL 1</li>
  <li>Voce OL 2</li>
</ol>
```

#### **2.1 Versione “vecchia” (senza `:is`)**

```css
ul li,
ol li {
  font-size: 30px;
}
```

Significa:

- tutti i `li` dentro un `ul`,
    
- tutti i `li` dentro un `ol`,
    
- hanno `font-size: 30px`.
    

#### **2.2 Versione con `:is()`**

```css
:is(ul, ol) li {
  font-size: 30px;
}
```

È equivalente a:

```css
ul li,
ol li {
  font-size: 30px;
}
```

Cosa fa `:is(ul, ol)`?

- prende tutti gli elementi che sono `ul` **oppure** `ol`;
    
- poi, attaccando `li` dopo, ottieni:
    
    - `ul li`
        
    - `ol li`
        

Se cambi uno dei selettori dentro `:is()` (per esempio metti `div` al posto di `ol`), è come se stessi modificando l’elenco di “prefissi” su cui applicare il pezzo finale.

Esempio:

```css
:is(ul, div) li {
  font-size: 30px;
}
```

Equivale a:

```css
ul li,
div li {
  font-size: 30px;
}
```

---

### **3. Esempio più complesso: `section`, `article`, `aside` + `h1…h6`**

Immagina una struttura tipica di pagina:

```html
<section>
  <h1>Section H1</h1>
  <h2>Section H2</h2>
  <h3>Section H3</h3>
  <h4>Section H4</h4>
  <h5>Section H5</h5>
  <h6>Section H6</h6>
</section>

<article>
  <h1>Article H1</h1>
  <h2>Article H2</h2>
  <h3>Article H3</h3>
  <h4>Article H4</h4>
  <h5>Article H5</h5>
  <h6>Article H6</h6>
</article>

<aside>
  <h1>Aside H1</h1>
  <h2>Aside H2</h2>
  <h3>Aside H3</h3>
  <h4>Aside H4</h4>
  <h5>Aside H5</h5>
  <h6>Aside H6</h6>
</aside>
```

Mettiamo il font un po’ più grande:

```css
h1, h2, h3, h4, h5, h6 {
  font-size: 30px;
}
```

Ora supponi di voler colorare **tutti gli `h1–h6` che stanno dentro**:

- `section`
    
- `article`
    
- `aside`
    

ma **non** tutti gli altri `h1–h6` fuori da lì.

#### **3.1 Versione senza `:is()` (super verbosa)**

Dovresti scrivere:

```css
section h1,
section h2,
section h3,
section h4,
section h5,
section h6,
article h1,
article h2,
article h3,
article h4,
article h5,
article h6,
aside h1,
aside h2,
aside h3,
aside h4,
aside h5,
aside h6 {
  color: red;
}
```

Funziona, ma è un muro di testo.

#### **3.2 Versione con `:is()`**

Con `:is()` puoi compattare il tutto così:

```css
:is(section, article, aside) :is(h1, h2, h3, h4, h5, h6) {
  color: blue;
}
```

Cosa succede qui?

- Primo `:is(section, article, aside)`  
    prende tutti i:
    
    - `section`
        
    - `article`
        
    - `aside`
        
- Secondo `:is(h1, h2, h3, h4, h5, h6)`  
    prende tutti i titoli da `h1` a `h6`.
    

In combinazione:

- è come se CSS avesse espanso mentalmente tutte le combinazioni:
    
    - `section h1`
        
    - `section h2`
        
    - …
        
    - `section h6`
        
    - `article h1`
        
    - …
        
    - `aside h6`
        

È esattamente quello che faceva il selettore gigante, ma scritto in modo **molto più leggibile**.

---

### **4. Esempio con classi: `div:is(.prova, .ciao)`**

Altro caso tipico: stesso tag, **più classi diverse**.

HTML:

```html
<div class="prova">Ciao</div>
<div class="ciao">Ciao</div>
<div class="orazio">Orazio</div>
```

Voglio dare uno stile a:

- tutti i `div` che sono `.prova`
    
- tutti i `div` che sono `.ciao`
    
- ma **non** a `.orazio`.
    

#### **4.1 Versione senza `:is()`**

```css
div.prova,
div.ciao {
  background-color: red;
}
```

#### **4.2 Versione con `:is()`**

```css
div:is(.prova, .ciao) {
  background-color: red;
}
```

Logica:

- `div:is(.prova, .ciao)` equivale a dire:
    
    - `div.prova`
        
    - `div.ciao`
        
- il `div.orazio` non rientra in nessuno dei due, quindi resta fuori.
    

---

### **5. Come pensare a `:is()` mentalmente**

Puoi pensare a `:is()` come a una **moltiplicazione di combinazioni**:

- `:is(A, B) C`  
    si espande in  
    `A C, B C`
    
- `X :is(Y, Z)`  
    si espande in  
    `X Y, X Z`
    
- `:is(A, B) :is(C, D)`  
    si espande in  
    `A C, A D, B C, B D`
    

Quindi:

```css
:is(section, article, aside) :is(h1, h2, h3, h4, h5, h6)
```

è come scrivere:

```css
section h1,
section h2,
...,
aside h6
```

---

### **6. Quando usarla, e quando puoi farne a meno**

`:is()` è utile quando:

- hai **tanti selettori simili** con un pezzo in comune,
    
- vuoi evitare copia-incolla infinito,
    
- vuoi avere CSS **più pulito e più mantenibile**.
    

È **una comodità**, non un obbligo:

- puoi fare siti perfettamente funzionanti senza usare `:is()`,
    
- ma quando il progetto cresce e i selettori si ripetono ovunque, `:is()` ti salva da:
    
    - codice duplicato,
        
    - selettori chilometrici,
        
    - errori di copia-incolla.
        

Nei prossimi passaggi, il discorso continuerà con `:not()` e `:has()`, che ragionano in modo simile (selezioni “chi è X, ma non Y”, oppure “chi contiene Y”), ma è giusto che tu metabolizzi prima bene la logica di `:is()`.