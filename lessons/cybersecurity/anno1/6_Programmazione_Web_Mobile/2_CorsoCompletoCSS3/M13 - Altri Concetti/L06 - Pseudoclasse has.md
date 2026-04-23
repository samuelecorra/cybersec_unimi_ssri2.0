## **Lezione 6: Pseudoclasse `:has()`**

### **1. Idea fondamentale**

La pseudoclasse `:has()` significa:

> **“Dammi questo elemento… solo se AL SUO INTERNO c’è (o non c’è) qualcos’altro.”**

È il primo selettore **parent-aware** della storia del CSS.  
Prima di `:has()`, CSS poteva selezionare solo _discendenti_, mai “risalire al genitore”.

`:has()` cambia tutto:  
puoi selezionare **il genitore in base ai figli**.

È stata introdotta da pochissimo ed è ora supportata in tutti i browser moderni.

---

## **2. Primo esempio semplice: contare i figli**

HTML:

```html
<ul>
  <li>1</li>
  <li>2</li>
  <li>3</li>
  <li>4</li>
  <li>5</li>
  <li>6</li>
</ul>
```

CSS:

```css
ul:has(:nth-child(6)) {
  color: red;
  font-size: 30px;
}
```

Significa:

- “Prendi la `ul`”
    
- “MA SOLO SE al suo interno ha un figlio che è `:nth-child(6)`”
    

Dato che il `<ul>` ha 6 elementi, viene selezionata.  
Se cambi in:

```css
ul:has(:nth-child(7))
```

…non succede nulla, perché non esiste un settimo figlio.

---

## **3. Selezionare il parente, non il figlio**

Questo è il punto più importante.

Senza `:has()`, se scrivi:

```css
section h2.prova { background: yellow; }
```

stai stilando **l’H2**, non la `section`.

Con `:has()`:

```css
section:has(h2.prova) {
  background: yellow;
}
```

stai stilando **la section**, cioè il genitore.

Esempio pratico:

HTML:

```html
<section>
  <h2 class="prova">Titolo speciale</h2>
</section>

<section>
  <h2>Titolo normale</h2>
</section>
```

CSS:

```css
section:has(h2.prova) {
  background: yellow;
}
```

Risultato:

- la **prima** section si colora (perché contiene un H2.prova)
    
- la seconda **no**
    

---

## **4. Funziona con qualsiasi combinazione**

Puoi scrivere:

```css
section:has(h2.prova, h2.ciao)
```

e CSS selezionerà:

- le `section` che contengono **o** un `h2.prova`
    
- **o** un `h2.ciao`
    

Esempio:

```css
section:has(h2.prova, h2.ciao) {
  border: 3px solid red;
}
```

---

## **5. Usare `:not()` insieme a `:has()`**

Puoi negare la condizione interna.

HTML:

```html
<section>
  <h2 class="prova">A</h2>
</section>

<section>
  <h2>B</h2>
</section>
```

CSS:

```css
section:not(:has(h2.prova)) {
  background: lightblue;
}
```

Risultato:

- la section con **prova** NON viene presa (perché la stai escludendo)
    
- la section **senza** `.prova` sì
    

Due selector logici insieme:

- `:has()` → definisce la condizione (“ha questo dentro”)
    
- `:not()` → la nega (“non deve avere questo dentro”)
    

---

## **6. Perché `:has()` è rivoluzionaria**

Prima del 2023, per fare una cosa così:

> “se la card contiene un video, cambia layout”

dovevi usare **JavaScript**.

Con `:has()`:

```css
.card:has(video) {
  display: flex;
  flex-direction: column;
}
```

Oppure:

> “Se un input dentro il form è invalido, colora tutto il form”

```css
form:has(input:invalid) {
  border: 3px solid red;
}
```

Oppure:

> “Se una card NON contiene un’immagine, aumenta il padding”

```css
.card:not(:has(img)) {
  padding: 3rem;
}
```

Questo è potentissimo, perché:

- puoi fare logica basata sui figli
    
- ma applicata al contenitore
    
- **senza scrivere nemmeno una riga di JavaScript**
    

---

## **7. Altri esempi utili**

### **7.1 Sidebar attiva se contiene link attivo**

```css
nav:has(a.active) {
  background: #222;
}
```

### **7.2 Se la tabella contiene errori, colorala**

```css
table:has(.error-row) {
  border-color: red;
}
```

### **7.3 Se il form contiene campi required, rendi visibile un avviso**

```css
form:has(input[required]) .warning {
  display: block;
}
```

### **7.4 Se una card contiene un bottone disabled, cambiale stile**

```css
.card:has(button:disabled) {
  opacity: 0.6;
}
```

---

## **8. Sintassi da ricordare**

```css
selector:has(selettore-interno) {
  /* stile che applicheremo al genitore */
}
```

Funziona con:

- classi
    
- id
    
- pseudo-classi
    
- pseudo-elementi
    
- combinazioni complesse
    
- più selettori fra parentesi
    

---

## **9. Comprendere la triade IS / NOT / HAS**

Hai visto tre selettori avanzati che lavorano insieme:

- **`:is()`** → inclusione intelligente
    
- **`:not()`** → esclusione intelligente
    
- **`:has()`** → condizione basata sui figli
    

Sono tre strumenti che permettono di:

- evitare codice duplicato
    
- selezionare elementi in modo logico
    
- scrivere CSS molto più pulito
    
- ridurre il bisogno di JavaScript per la logica di base
    
- stilare il contenitore in base ai contenuti (cosa mai possibile prima)
    

Se li hai capiti tutti e tre, sei oggettivamente a un **livello CSS avanzato**.

Ora possiamo continuare con gli ultimi concetti mancanti della sezione.