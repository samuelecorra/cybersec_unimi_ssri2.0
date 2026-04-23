## **Lezione 5: Pseudoclasse `:not()`**

### **1. Idea intuitiva**

La pseudoclasse `:not()` significa esattamente:

> **“tutto quello che NON è …”**

È una **negazione logica**:  
prende il selettore finale, gli applica il filtro, e scarta ciò che corrisponde a quello che metti dentro le parentesi.

---

## **2. Primo esempio semplice: classi**

HTML:

```html
<div class="prova">A</div>
<div class="ciao">B</div>
<div class="salvo">C</div>
```

CSS:

```css
div:not(.prova, .ciao) {
  background-color: red;
}
```

Significa:

- prendi tutti i `div`
    
- ma **escludi** quelli che sono `.prova` e `.ciao`
    

Risultato:

- `div.prova` → NON viene preso
    
- `div.ciao` → NON viene preso
    
- `div.salvo` → SI, perché non è né prova né ciao
    

È equivalente mentalmente a:

> “Tutti i div tranne questi due”.

---

## **3. Negare un selettore semplice**

Se scrivo:

```css
:not(div) {
  background: red;
}
```

Significa:

> “Tutti gli elementi che **NON** sono `div`”.

Infatti diventa rosso tutto il resto del documento (body, html, ecc.), tranne i `div`.

---

## **4. Esempio logico con `li:not(...)`**

### **4.1 Setup**

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

Ora usiamo la pseudoclasse `:nth-child()`.

### **4.2 Dispari → ma negati**

```css
li:not(:nth-child(odd)) {
  font-size: 40px;
}
```

`odd` = dispari, quindi:

- `.not(odd)` = **pari**
    

Risultato:

- 2, 4, 6 → presi (pari)
    
- 1, 3, 5 → scartati (dispari)
    

È perfettamente coerente con la negazione logica:

> NON (dispari) = pari.

---

## **5. Combinazioni più complesse**

Puoi negare qualunque cosa:

- classi
    
- pseudo-classi
    
- combinazioni
    
- gruppi multipli
    

Esempio:

```css
li:not(:nth-child(odd), :last-child) {
  color: blue;
}
```

Questo significa:

> “Prendi tutti i `li` che **NON** sono dispari  
> e che **NON** sono l’ultimo.”

L’ultimo viene escluso anche se sarebbe pari, perché l’esclusione lo elimina dalla selezione.

Infatti:

- 1, 3, 5 → scartati (dispari)
    
- 6 → scartato (last-child)
    
- 2, 4 → selezionati (pari e non-last)
    

Se togli l’ultimo filtro:

```css
li:not(:nth-child(odd)) {
  color: blue;
}
```

Riappare anche il 6.

---

## **6. Quando usare `:not()`**

`:not()` è comodissimo quando vuoi:

- selezionare **tutto tranne qualcosa**
    
- rimuovere eccezioni
    
- stilare un gruppo ampio senza riscrivere mille selettori
    

Esempi di uso reale:

- tutti i bottoni **tranne** quelli disabilitati
    
- tutte le card **tranne** quelle con classi speciali
    
- tutti i link **tranne** quelli che sono in un menu particolare
    
- tutti gli elementi di una lista **tranne** i primi due
    
- tutti gli input **tranne** quelli hidden
    

Esempio pratico:

```css
button:not(:disabled) {
  cursor: pointer;
  opacity: 1;
}
```

---

## **7. Due cose importanti da ricordare**

### **7.1 La negazione vale per TUTTO quello che metti dentro**

```css
li:not(.rosso, .grande)
```

Significa:

- NON `.rosso`
    
- NON `.grande`
    

Quindi **prende gli elementi che non sono né uno né l’altro**.

---

### **7.2 Il difficile non è la sintassi, ma la logica**

Gli errori più comuni:

- dimenticarsi che si sta **negando**
    
- pensare “solo questo” anziché “tutto TRANNE questo”
    
- confondere `:not(odd)` con `:nth-child(even)` (sono equivalenti, ma il cervello inciampa)
    

È normalissimo: serve un po’ di pratica mentale.

---

## **8. Conclusione**

La pseudoclasse `:not()` è semplicissima nella sintassi:

```css
:not(selettore)
```

…ma richiede lucidità logica.

Ti permette di scrivere selettori molto più puliti:

- filtri fuori ciò che non vuoi,
    
- ottieni automaticamente tutto il resto,
    
- ed eviti lunghissime liste di esclusioni manuali.
    

Ora che hai:

- `:is()` (inclusione)
    
- `:not()` (esclusione)
    

sei pronto per **`:has()`**, che è la più potente delle tre. È fondamentale capirle bene in quest’ordine, perché `:has()` le usa entrambe per ragionare sui contenuti.

Ci arriviamo tra pochissimo.