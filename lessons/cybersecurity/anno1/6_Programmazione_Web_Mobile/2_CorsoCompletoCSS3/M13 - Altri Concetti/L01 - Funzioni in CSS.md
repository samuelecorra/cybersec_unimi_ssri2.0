## **Lezione 1: Funzioni in CSS**

### **1. Introduzione alle funzioni**

In CSS una _funzione_ è qualsiasi costrutto con:

- **un nome**,
    
- **parentesi tonde**,
    
- **uno o più parametri al loro interno**.
    

La sintassi è:

```
nomeFunzione(parametri)
```

Queste funzioni vengono valutate dal browser in tempo reale e restituiscono un **valore** che CSS può usare per proprietà come colori, dimensioni, trasformazioni, immagini, gradienti e molto altro.

Molte funzioni le hai già usate fin dalle prime lezioni, senza che fossero presentate come “funzioni”.

---

### **2. Esempi di funzioni già usate**

#### **2.1 Funzioni colore**

- `rgb(…)`
    
- `rgba(…)`
    
- `hsl(…)`
    
- `hsla(…)`
    

Esempio:

```css
background-color: rgb(255, 130, 90);
```

Restituiscono un **colore** calcolato dai parametri numerici.

---

#### **2.2 Funzioni dei gradienti**

- `linear-gradient(…)`
    
- `radial-gradient(…)`
    

Esempio:

```css
background: linear-gradient(45deg, red, blue);
```

Anche qui: nome + parentesi + parametri → risultato = immagine generata.

---

#### **2.3 Funzioni `transform`**

Ogni operazione di trasformazione è una funzione:

- `rotate(45deg)`
    
- `scale(1.2)`
    
- `translateX(50px)`
    
- `skewY(10deg)`
    

Esempio:

```css
transform: rotate(45deg) scale(1.2);
```

Sono funzioni che manipolano la geometria di un elemento.

---

#### **2.4 Funzione `url()`**

Ad esempio in:

```css
background-image: url('immagine.png');
```

`url()` è anch’essa una funzione: prende un parametro (il percorso dell’immagine) e restituisce il riferimento all’asset da usare.

Non viene percepita come una “funzione” perché è molto comune, ma tecnicamente lo è.

---

### **3. La funzione `calc()`**

`calc()` è probabilmente la funzione più “matematica” del CSS.

Serve a **calcolare valori al volo**, combinando unità diverse:

- percentuali,
    
- pixel,
    
- `rem`,
    
- `vw`,
    
- ecc.
    

#### **3.1 Esempio pratico**

Immagina di voler dare al form una larghezza pari a:

> larghezza viewport  
> **meno** 200px.

```css
width: calc(100vw - 200px);
```

Il browser fa il calcolo e applica il valore finale.

Puoi sommare, sottrarre, moltiplicare, dividere:

```css
padding-left: calc(2rem + 10px);
height: calc(100% - 3em);
```

È utilissimo per:

- layout flessibili
    
- compensare sidebar, paddings, margini
    
- fare spacing responsivo anche senza media query
    

---

### **4. Funzione `var()` (anticipazione)**

Anche la funzione `var()` è... una funzione:

```css
color: var(--primary);
```

Il suo scopo è prendere una **variabile** CSS, definita altrove con `--primary`.

Non l’approfondiamo qui, perché la lezione successiva sarà interamente su:

- cosa sono le variabili CSS,
    
- come si dichiarano,
    
- come ereditano,
    
- come si sovrascrivono con `:root`,
    
- perché rivoluzionano i temi scuri/chiari, i design system, i componenti riutilizzabili.
    

L’importante è riconoscere che:

- `var()` = funzione
    
- prende 1 o 2 parametri: la variabile e un fallback
    

```css
var(--colore-titolo, black)
```

---

### **5. Lista delle funzioni CSS**

Esistono **decine e decine** di funzioni in CSS: matematiche, geometriche, di trasformazione, di immagine, di colore, di filtro, di animazione.

Non ha senso studiarle tutte ora.

Quello che devi sapere è:

> ogni volta che vedi _nome + parentesi_ → stai usando una funzione.

Quando avrai più esperienza potrai esplorare la lista completa cercando:

- **CSS functions**
    
- **funzioni CSS MDN**
    
- oppure la pagina dedicata nella documentazione del tuo corso.
    

---

### **6. Conclusione**

Questa mini-lezione aveva un solo obiettivo:

> chiarire cosa sia una _funzione_ in CSS e riconoscerle ovunque compaiano.

Molte le stai già usando naturalmente.  
Adesso però hai la consapevolezza che:

- `rgb()`, `hsl()`, `linear-gradient()`, `url()`, `rotate()`, `calc()`, `var()`  
    sono tutte funzioni vere e proprie.
    

La prossima lezione sarà dedicata a **`var()` e alle variabili CSS**, che aprono un mondo completamente nuovo per temi, componentizzazione e scalabilità del codice.