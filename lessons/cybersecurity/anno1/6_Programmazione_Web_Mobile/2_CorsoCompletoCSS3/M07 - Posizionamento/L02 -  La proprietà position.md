## **Lezione 2: La proprietà `position`**

### **1. Introduzione a `position`**

Con `display` hai imparato **come** un elemento occupa lo spazio (blocco, in linea, ecc.).  
Con `position` impari **dove** metterlo, anche “forzando” la sua posizione con coordinate.

La proprietà `position` controlla **se e come** hanno effetto queste quattro proprietà:

- `top`
    
- `right`
    
- `bottom`
    
- `left`
    

I valori fondamentali sono:

- `static`
    
- `relative`
    
- `absolute`
    
- `fixed`
    
- `sticky`
    

Li vediamo uno per uno, con il modello mentale giusto.

---

### **2. `position: static` (default)**

### **2.1 Cos’è**

Ogni elemento, se non dici niente, ha:

```css
position: static;
```

Questo significa:

- l’elemento sta **dove il flusso del documento lo mette**, in ordine normale (uno sotto l’altro, da sinistra a destra)
    
- le proprietà `top`, `right`, `bottom`, `left` **non hanno nessun effetto**
    

Esempio:

```html
<div class="box">ciao</div>
```

```css
.box {
  border: 2px solid red;
  position: static;   /* è anche il default */
  left: 50px;         /* NON fa nulla */
  top: 50px;          /* NON fa nulla */
}
```

L’elemento rimane dov’è, ignorando `top/left`.

---

### **3. `position: relative`**

### **3.1 Idea chiave**

`relative` significa:

> “Prendi la posizione normale dell’elemento (static), e poi **spostalo di un offset** rispetto a quella posizione”.

Quindi:

- l’elemento **continua a occupare il suo spazio originale nel flusso**
    
- ma **visivamente** viene spostato
    

### **3.2 Esempio**

```css
.box {
  position: relative;
  top: 50px;
  left: 50px;
}
```

Effetto:

- l’elemento viene disegnato **50px più in basso** (top: 50) e **50px più a destra** (left: 50)
    
- il “buco” dello spazio originale rimane (cioè gli altri elementi non si chiudono su di lui)
    

Se usi:

- `top: 50px` → sposto verso **il basso**
    
- `bottom: 50px` → sposto verso **l’alto**
    
- `left: 50px` → sposto verso **destra**
    
- `right: 50px` → sposto verso **sinistra**
    

---

### **4. `position: absolute`**

### **4.1 Idea chiave**

`absolute` significa:

> “Togli l’elemento dal flusso normale e posizionalo con coordinate **assolute** rispetto a un contenitore di riferimento.”

Effetti:

- l’elemento **non occupa più spazio** nel flusso (come se fosse “staccato” dal resto)
    
- `top/right/bottom/left` **lo posizionano in coordinate**
    

### **4.2 Rispetto a chi sono “assolute” le coordinate?**

Regola fondamentale:

- se NON c’è nessun antenato con `position` diversa da `static`,  
    le coordinate sono riferite al **`body` / pagina** (viewport iniziale del documento)
    
- se esiste un antenato con `position: relative` (o `absolute`, `fixed`, `sticky`),  
    l’elemento assoluto prende quell’elemento come **riferimento**
    

### **4.3 Esempio base: rispetto al `body`**

```html
<div class="box">ciao</div>
```

```css
.box {
  position: absolute;
  top: 50px;
  right: 50px;
}
```

Effetto:

- la `box` viene messa a 50px dal bordo superiore e 50px dal bordo destro del **body**
    
- scorrendo la pagina, si muove insieme al contenuto (non è fissa allo schermo, è assoluta nella pagina, non nel viewport)
    

---

### **5. `absolute` dentro un contenitore `relative`**

Questa è la combinazione **più importante** da capire, perché si usa ovunque.

### **5.1 Struttura HTML**

```html
<section class="container">
  <div class="box">ciao</div>
</section>
```

### **5.2 CSS senza `relative`**

```css
.container {
  background-color: orange;
}

.box {
  position: absolute;
  top: 0;
  left: 0;
}
```

Se guardi la pagina, noterai spesso che:

- `top: 0; left: 0;` posiziona la box rispetto all’**angolo in alto a sinistra della pagina** (body), NON della `section`
    

Perché?  
Perché nessun antenato ha una `position` diversa da `static`.

### **5.3 Aggiungiamo `position: relative` al contenitore**

```css
.container {
  background-color: orange;
  position: relative;
}

.box {
  position: absolute;
  top: 0;
  left: 0;
}
```

Ora:

- `top: 0; left: 0;` significa “angolo in alto a sinistra della **section**”
    
- l’elemento assoluto è “ancorato” alla `container`
    

Quindi:

- `position: relative` sul contenitore → definisce **il sistema di riferimento**
    
- `position: absolute` sul figlio → lo posiziona **dentro quel sistema di riferimento**
    

---

### **6. Esempio pratico: card con icona “cuore”**

### **6.1 HTML di esempio**

```html
<div class="card">
  <div class="card-image"></div>
  <div class="card-content">
    <h2>Titolo card</h2>
    <p>Lorem ipsum...</p>
  </div>
  <div class="cuore">♥</div>
</div>
```

### **6.2 Base della card**

```css
.card {
  width: 300px;
  background-color: white;
  box-shadow: 2px 2px 5px rgba(0,0,0,.3);
  position: relative;      /* fondamentale! */
}

.card-image {
  height: 150px;
  background-color: orange;  /* al posto di un’immagine vera */
}

.card-content {
  padding: 10px;
}
```

### **6.3 Posizionare il cuore in modo assoluto**

```css
.cuore {
  width: 40px;
  height: 40px;
  background-color: red;
  color: white;
  padding: 10px;
  position: absolute;
  top: 50%;
  right: 20px;
}
```

- `position: absolute` → il cuore viene tolto dal flusso e posizionato a coordinate
    
- `position: relative` sulla `.card` → il cuore è posizionato **rispetto alla card**, non alla pagina
    

Risultato:

- la card rimane nel layout come box normale
    
- il cuore è “incollato” in una posizione precisa sopra la card (per esempio centrale in verticale e spostato verso destra)
    

Questa combinazione `relative + absolute` è lo **standard** per:

- icone di like / preferiti
    
- badge di notifica
    
- etichette “-50%”
    
- bottoni sopra immagini
    

---

### **7. `position: fixed`**

### **7.1 Idea chiave**

`fixed` è simile ad `absolute`, ma le coordinate sono riferite al **viewport** (la finestra visibile dello schermo), non al documento.

```css
.box {
  position: fixed;
  top: 50px;
  right: 50px;
}
```

Effetto:

- la box resta sempre a 50px dal bordo superiore e 50px dal bordo destro **dello schermo**
    
- anche se scrolli la pagina, **non si muove**
    

È perfetto per:

- pulsanti “torna su”
    
- floating button (assistente, chat, help)
    
- navbar o barre fisse
    
- banner persistenti
    

---

### **8. `position: sticky`**

### **8.1 Idea chiave**

`sticky` è un mix tra `relative` e `fixed`:

- all’inizio si comporta come un normale elemento nel flusso (`relative`)
    
- quando raggiunge una certa posizione definita (es. `top: 0`), si “appiccica” e si comporta come `fixed` rispetto al viewport
    

### **8.2 Esempio**

```html
<div class="header-sticky">Sono sticky</div>
<p>...tanto testo sotto...</p>
```

```css
.header-sticky {
  position: sticky;
  top: 0;
  background: lightgray;
}
```

Comportamento:

1. finché non scrolli abbastanza, l’elemento scorre normalmente con la pagina
    
2. quando la sua parte superiore raggiunge `top: 0`, si **ferma lì** e rimane appiccicato in alto mentre il resto scorre sotto
    

Se metti `top: 20px`, si blocca quando arriva a 20px dall’alto.

---

### **9. Ricapitolone mentale**

- **`static`**
    
    - posizione normale nel flusso
        
    - `top/right/bottom/left` ignorati
        
- **`relative`**
    
    - parte dalla posizione normale
        
    - poi viene spostato di `top/right/bottom/left`
        
    - lo spazio originale nel layout resta **occupato**
        
- **`absolute`**
    
    - tolto dal flusso (non occupa più spazio)
        
    - posizionato con coordinate rispetto a:
        
        - il primo antenato con `position` ≠ `static`
            
        - altrimenti il `body`
            
    - usatissimo dentro contenitori `relative`
        
- **`fixed`**
    
    - tolto dal flusso
        
    - posizionato rispetto al **viewport**
        
    - non si muove quando scrolli
        
- **`sticky`**
    
    - finché non raggiunge `top` (o `bottom`) → normale nel flusso
        
    - quando lo raggiunge → resta appiccicato in quella posizione rispetto al viewport
        

Capire bene questi cinque comportamenti è fondamentale:  
sono la base per navbar, card, overlay, popup, sidebar e qualunque layout un minimo avanzato.

Nel prossimo video entrerai in un’altra proprietà cruciale legata alla sovrapposizione degli elementi: **`z-index`**.