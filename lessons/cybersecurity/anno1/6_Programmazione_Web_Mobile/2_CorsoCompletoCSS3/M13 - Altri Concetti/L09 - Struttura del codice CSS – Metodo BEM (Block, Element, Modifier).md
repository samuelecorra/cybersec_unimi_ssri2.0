## **Lezione 9: Struttura del codice CSS – Metodo BEM (Block, Element, Modifier)**

### **1. Introduzione**

Quando inizierai a costruire siti reali, il CSS diventerà **grande**, **disordinato** e **difficile da mantenere**.  
È normale: il CSS cresce rapidamente e senza disciplina diventa ingestibile.

Per evitare questo caos, negli anni sono nate varie metodologie:

- **BEM** (la più intuitiva per principianti)
    
- **OOCSS**
    
- **SMACSS**
    
- **ITCSS**
    
- ecc.
    

Oggi guardiamo solo **BEM**, che è quella che più ti aiuta a mantenere chiarezza e ordine senza costringerti in schemi rigidi.

---

## **2. Cos’è BEM**

BEM sta per:

- **B — Block**
    
- **E — Element**
    
- **M — Modifier**
    

E serve per _nominare e strutturare_ le classi in modo leggibile, scalabile e pulito.

### **2.1 Block**

Il **Block** è il componente principale.  
Esempi comuni:

- `.card`
    
- `.navbar`
    
- `.footer`
    
- `.modal`
    
- `.button`
    

Un **block** deve avere senso _da solo_.  
È un pezzo indipendente dell’interfaccia.

### **2.2 Element**

Gli **Element** sono parti interne del block.  
Si indicano con:

```
block__element
```

Esempi in una card:

- `.card__image`
    
- `.card__content`
    
- `.card__title`
    
- `.card__description`
    
- `.card__button`
    

Queste classi esistono _solo_ dentro la card: dipendono da essa.

### **2.3 Modifier**

Un **Modifier** rappresenta una variante o uno stato.

```
block--modifier
```

Esempi:

- `.card--disabled`
    
- `.card--featured`
    
- `.card--large`
    
- `.button--primary`
    
- `.button--outline`
    

Il modifier **non crea un nuovo componente**, ma cambia l’aspetto del block esistente.

---

## **3. L’esempio completo della lezione**

Hai una card con:

- immagine
    
- contenuto
    
- titolo
    
- descrizione
    
- badge _“in arrivo”_
    
- stato _disabled_
    

La struttura HTML BEM diventa:

```html
<div class="card card--disabled">
  <div class="card__image"></div>
  <div class="card__content">
    <h2 class="card__title">Titolo</h2>
    <p class="card__description">Descrizione...</p>
  </div>

  <span class="card__notice">IN ARRIVO</span>
</div>
```

---

## **4. Stili CSS in BEM**

### **4.1 Il Block**

```css
.card {
  width: 300px;
  border-radius: 10px;
  box-shadow: 5px 5px 8px rgba(0,0,0,0.2);
  position: relative;
}
```

---

### **4.2 Gli Element**

```css
.card__image {
  width: 100%;
  height: 180px;
  background-image: url("sfondo.jpg");
  background-size: cover;
  background-position: center;
}
```

```css
.card__content {
  padding: 20px;
}
```

```css
.card__title {
  text-transform: uppercase;
}
```

```css
.card__description {
  color: grey;
}
```

---

### **4.3 Il Modifier**

```css
.card--disabled .card__image {
  filter: grayscale(100%);
}
```

Badge:

```css
.card__notice {
  display: none;
  background: black;
  color: white;
  padding: 10px 20px;
  border-radius: 50%;
  text-transform: uppercase;
  position: absolute;
  top: 10px;
  right: 10px;
  z-index: 10;
}
```

Badge visibile **solo se la card è disabilitata**:

```css
.card--disabled .card__notice {
  display: block;
}
```

Perfetto: tutto chiaro, leggibile e scalabile.

---

## **5. Perché usare BEM**

### **Vantaggi reali:**

- **Chiarezza immediata**  
    Guardando `.card__title` sai subito chi è il padre e che ruolo ha.
    
- **Niente conflitti strani**  
    Le classi sono uniche e descrittive.
    
- **Scalabilità infinita**  
    Puoi avere 50 card diverse senza caos.
    
- **Manutenibilità**  
    Se riapri il progetto dopo mesi, riesci a capire tutto.
    
- **Compatibilità totale con CSS puro, SCSS, Tailwind, React, Vue, Svelte…**  
    (Anche se con Tailwind usi meno BEM, ma la logica mentale resta utile.)
    

---

## **6. Quando NON usare BEM**

- Nei siti piccolissimi.
    
- Nei prototipi veloci.
    
- Quando usi pesantemente **utility-first** (Tailwind, UnoCSS).
    

Ma quando il progetto cresce, BEM ti salva la vita.

---

## **7. Conclusione**

Hai ora tutti gli strumenti per:

- capire cos’è un **pattern di architettura CSS**
    
- strutturare componenti complessi in modo leggibile
    
- evitare il tipico CSS “spaghetti” da principiante
    
- prepararti ai framework moderni
    

Puoi continuare ad usare il tuo stile libero,  
ma scontrarti con il disordine ti farà capire esattamente _perché_ BEM è stato inventato.

E quando accadrà, saprai già come funziona.