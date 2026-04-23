## **Lezione 1: Emmet Abbreviation – il turbo per scrivere HTML**

In questa lezione chiudiamo il corso con uno **strumento pratico** che userai _sempre_ quando scriverai HTML in VS Code:  
le **Emmet Abbreviations**.

Sono già integrate in VS Code: nessuna installazione, basta scrivere l’abbreviazione e premere `Tab` (o `Enter`, a seconda delle impostazioni).

---

## **1. Cos’è Emmet e perché ne parliamo in un corso di CSS**

**Emmet** è un mini-linguaggio di scorciatoie per generare HTML (e anche CSS) **in modo super veloce**.

Lo vediamo in un corso di CSS perché:

- la sintassi usa esattamente i concetti dei **selettori CSS**:
    
    - `>` figlio
        
    - `+` fratello
        
    - `#` per `id`
        
    - `.` per `class`
        
- ti permette di costruire velocemente la struttura HTML su cui poi applichi il CSS.
    

L’idea è:  
**scrivi una “formula” → premi Tab → Emmet ti spara fuori l’HTML completo.**

---

## **2. Abbreviazioni base: creare elementi**

### **2.1. Elemento semplice**

```text
div   →  Tab
```

Diventa:

```html
<div></div>
```

Vale per qualsiasi tag:

```text
p → Tab   →  <p></p>
ul → Tab  →  <ul></ul>
img → Tab →  <img src="" alt="">
```

> Per `img`, `link`, `input` ecc. Emmet sa già che sono self-closing o con attributi tipici.

---

## **3. Gerarchia: figli ( `>` ) e fratelli ( `+` )**

### **3.1. Figlio: operatore `>`**

```text
ul>li
```

Diventa:

```html
<ul>
  <li></li>
</ul>
```

Anche annidato:

```text
div>ul>li
```

→

```html
<div>
  <ul>
    <li></li>
  </ul>
</div>
```

### **3.2. Fratelli: operatore `+`**

```text
h1+p+img+h3
```

→

```html
<h1></h1>
<p></p>
<img src="" alt="">
<h3></h3>
```

È esattamente il concetto di **sibling** dei selettori CSS.

---

## **4. Risalire di livello: operatore `^`**

Se non usi `^`, quando continui la tua abbreviazione **resti dentro l’ultimo elemento creato**.

Esempio:

```text
div>ul>li+div
```

Diventa:

```html
<div>
  <ul>
    <li></li>
    <div></div>   <!-- fratello di <li>, ma ancora dentro <ul> -->
  </ul>
</div>
```

Se invece vuoi un `div` **fuori** da `ul` (fratello di `ul`, non di `li`), devi “risalire”:

```text
div>ul>li^^div
```

Spiegazione:

- `div>ul>li` → dentro `div` c’è `ul`, dentro `ul` c’è `li`
    
- `^` → risali di 1 livello (da `li` a `ul`)
    
- `^` → risali di un altro livello (da `ul` a `div`)
    
- `div` → fratello del primo `div` (ma nella pratica si usa per rientrare al livello corretto)
    

Attenzione: spesso basta un `^`, dipende da **a che livello** vuoi arrivare.

---

## **5. Moltiplicazione: operatore `*`**

### **5.1. Ripetere elementi**

```text
ul>li*5
```

→

```html
<ul>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
</ul>
```

Vale per qualsiasi cosa:

```text
p*3
```

→

```html
<p></p>
<p></p>
<p></p>
```

---

## **6. Raggruppare elementi: parentesi `()`**

Quando la combinazione diventa più lunga, puoi usare le parentesi per **raggruppare** blocchi.

Esempio:

```text
div>(ul>li*5)+div
```

Significa:

- dentro `div`:
    
    - un `ul` con 5 `li`
        
    - **più** un altro `div` dopo la lista
        

Risultato:

```html
<div>
  <ul>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
  </ul>
  <div></div>
</div>
```

Senza grouping, Emmet spesso interpreterebbe diversamente la gerarchia.

---

## **7. ID e classi: `#` e `.`**

Esattamente come nei selettori CSS.

### **7.1. ID con `#`**

```text
div#prova
```

→

```html
<div id="prova"></div>
```

Puoi combinarlo con figli:

```text
div#contenitore>ul>li*3
```

→

```html
<div id="contenitore">
  <ul>
    <li></li>
    <li></li>
    <li></li>
  </ul>
</div>
```

### **7.2. Classi con `.`**

```text
ul.flex-col.bg-red-500
```

→

```html
<ul class="flex-col bg-red-500"></ul>
```

Con `li` annidati:

```text
div#prova>ul.flex.flex-col.bg-red-500>li.item*5
```

→

```html
<div id="prova">
  <ul class="flex flex-col bg-red-500">
    <li class="item"></li>
    <li class="item"></li>
    <li class="item"></li>
    <li class="item"></li>
    <li class="item"></li>
  </ul>
</div>
```

Per classi diverse sugli stessi `li` vediamo tra poco il simbolo `$`.

---

## **8. Attributi personalizzati: `[attr=valore]`**

Puoi aggiungere _qualsiasi_ attributo usando le quadre.

### **8.1. Su `div`**

```text
div[data-nome="Edoardo"][data-cognome="Midali"]
```

→

```html
<div data-nome="Edoardo" data-cognome="Midali"></div>
```

### **8.2. Su `input`**

```text
input#nome[name="nome"][type="text"]
```

→

```html
<input id="nome" name="nome" type="text">
```

Combinabile con tutto il resto (`>`, `+`, `*`, ecc.).

---

## **9. Testo e numerazione: `{}` e `$`**

### **9.1. Testo statico: `{...}`**

```text
p{Ciao mondo}
```

→

```html
<p>Ciao mondo</p>
```

Con ripetizione:

```text
li{Elemento}*3
```

→

```html
<li>Elemento</li>
<li>Elemento</li>
<li>Elemento</li>
```

### **9.2. Testo numerato: `$`**

`$` viene sostituito dal **numero della ripetizione**.

```text
li{$}*5
```

→

```html
<li>1</li>
<li>2</li>
<li>3</li>
<li>4</li>
<li>5</li>
```

Combini testo + numero:

```text
li{Ciao sono il numero $}*5
```

→

```html
<li>Ciao sono il numero 1</li>
<li>Ciao sono il numero 2</li>
<li>Ciao sono il numero 3</li>
<li>Ciao sono il numero 4</li>
<li>Ciao sono il numero 5</li>
```

### **9.3. Numerare classi con `$`**

```text
ul>li.item$*5
```

→

```html
<ul>
  <li class="item1"></li>
  <li class="item2"></li>
  <li class="item3"></li>
  <li class="item4"></li>
  <li class="item5"></li>
</ul>
```

Puoi combinare tutto:

```text
ul>li.item${Elemento $}*5
```

→

```html
<ul>
  <li class="item1">Elemento 1</li>
  <li class="item2">Elemento 2</li>
  <li class="item3">Elemento 3</li>
  <li class="item4">Elemento 4</li>
  <li class="item5">Elemento 5</li>
</ul>
```

---

## **10. Esempio riassuntivo “da esame”**

Abbreviazione:

```text
section#blog>h2{Articoli recenti}+ul.posts>li.post-item${Articolo $}*3
```

Risultato:

```html
<section id="blog">
  <h2>Articoli recenti</h2>
  <ul class="posts">
    <li class="post-item1">Articolo 1</li>
    <li class="post-item2">Articolo 2</li>
    <li class="post-item3">Articolo 3</li>
  </ul>
</section>
```

Da una riga a una struttura completa in un colpo solo.

---

## **11. Cosa devi portarti via da Emmet**

Concetti fondamentali da fissare:

- `div` + `Tab` → genera il tag
    
- `>` → figlio
    
- `+` → fratello
    
- `^` → risali di un livello nella gerarchia
    
- `*N` → ripeti N volte
    
- `()` → raggruppa blocchi complessi
    
- `#id` → `id="..."`
    
- `.classe` → `class="..."`
    
- `[attr=val]` → attributi generici
    
- `{testo}` → contenuto testuale
    
- `$` → indice della ripetizione (per classi numerate e testi)
    

Se interiorizzi questi, **l’HTML diventa quasi “digitale”**: pensi in termini di struttura e la scrivi come se fosse una formula.

---

Da qui, nell’**esercizio finale di corso**, Emmet ti servirà per:

- generare velocemente la struttura della pagina
    
- evitare di sprecare tempo a scrivere tag ripetitivi
    
- concentrarti sul **layout e sul CSS**, che è il vero obiettivo del progetto.
    

Quando vuoi, passiamo al progettone di fine corso.