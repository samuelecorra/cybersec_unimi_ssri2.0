## **LEZIONE 4: Il principio della Cascata nel CSS**

### **1. Introduzione ai tre pilastri: Cascata, Ereditarietà, Specificità**

CSS significa _Cascading_ Style Sheet.  
La parola _Cascading_ non è un vezzo: rappresenta uno dei tre concetti fondamentali su cui si basa tutto il funzionamento del CSS.

I tre pilastri sono:

1. Cascata
    
2. Ereditarietà
    
3. Specificità
    

In questa lezione studiamo la **cascata**, cioè il meccanismo con cui il browser **decide quale regola CSS applicare**, quando più regole competono per lo stesso elemento.

---

### **2. La cascata: l’idea di base**

Il comportamento più semplice della cascata è questo:  
**il browser applica le regole dall’alto verso il basso, e l’ultima regola valida che incontra è quella che vince.**

Esempio:

```css
h1 {
    color: red;
}

h1 {
    color: yellow;
}
```

L’H1 diventa **giallo**, perché la regola che appare dopo sovrascrive quella precedente.

Se inverto l’ordine:

```css
h1 {
    color: yellow;
}

h1 {
    color: red;
}
```

L’H1 diventa **rosso**.  
Semplice: ciò che arriva “più tardi” nel foglio di stile ha l’ultima parola.

---

### **3. Come il browser visualizza la cascata**

Se apri la console (F12 → tab _Elements_ → _Styles_), vedrai esattamente questo meccanismo:

- le regole **attive** sono normali,
    
- le regole **sovrascritte** hanno una **linea barrata**.
    

Il browser non ignora le regole “perse”: le legge comunque, ma poi le sovrascrive.

---

### **4. La cascata funziona anche tra **file diversi**

La priorità non dipende solo dal _contenuto_ delle regole, ma anche da _dove_ si trovano.

Esempio:

- Nello `<style>` interno scrivi:
    

```css
h1 {
    color: yellow;
}
```

- Nel file esterno `style.css` scrivi:
    

```css
h1 {
    color: red;
}
```

Il vincitore è **rosso**, _solo se_ il file esterno viene linkato **dopo** lo `<style>` interno.

Se inverti l’ordine:

```html
<link rel="stylesheet" href="style.css">
<style>
    h1 { color: yellow; }
</style>
```

Ora vince **yellow**.  
La cascata segue sempre l’ordine nel DOM: prima quello che appare prima, poi quello che appare dopo.

---

### **5. E l’inline? Vince sempre**

Lo stile inline:

```html
<h1 style="color: palegreen;">Titolo</h1>
```

Ha la precedenza su qualsiasi regola non-!important, anche se appare prima nel file HTML.  
Nel pannello _Styles_ della console comparirà come **ultimo vincitore**, sopra tutto.

La cascata in console ha un ordine visivo inverso:  
le regole più forti sono in alto, quelle più deboli in basso.

---

### **6. I tre livelli che compongono la cascata**

La cascata tiene conto di tre sorgenti di regole:

1. **User agent stylesheet**  
    È il CSS predefinito del browser.  
    Ogni browser ha il suo:
    
    - gli `h1` sono grandi e in bold
        
    - i `p` hanno margini verticali
        
    - `strong` è in grassetto
        
    - i form hanno stile proprietario (Safari ≠ Chrome ≠ Firefox)
        
2. **CSS dell’autore (tuo)**
    
    - inline
        
    - `<style>`
        
    - file esterni
        
3. **Regole dello user (rare, se l’utente imposta CSS personalizzato)**
    

L’ordine di forza è:

**Inline > File esterno / Style tag > User agent**

E ogni livello segue comunque la cascata dall’alto verso il basso.

---

### **7. Sovrascrivere lo user agent stylesheet**

Lo stile del browser è sempre presente anche se non scrivi CSS.

Esempio: lo strong è in bold di default.

La console mostra:

```
font-weight: bold;   /* User agent stylesheet */
```

Se scrivi:

```css
strong {
    font-weight: 400;
}
```

Vince il tuo stile e il grassetto sparisce.

Questo è fondamentale per capire perché:

- un form appare diverso su Safari e Chrome,
    
- certi elementi sono stilizzati anche senza CSS,
    
- a volte vogliamo “resettare” tutto per ottenere un layout coerente.
    

Più avanti vedremo come **rimuovere completamente** lo stile del browser con una regola universale.

---

### **8. Riepilogo visivo della cascata**

Su un elemento l’ordine di applicazione è:

1. User agent (default del browser)
    
2. Stili autore (file esterni, poi style tag)
    
3. Inline style (vince tutto)
    

All’interno di ogni gruppo:  
**l’ultima regola scritta che riguarda quell’elemento vince.**

La console mostra la cascata al contrario:  
in alto ciò che vince, in basso ciò che perde (sbarrato).

---

### **9. Prossima lezione: Ereditarietà**

Abbiamo visto il funzionamento della cascata.  
Adesso passiamo al secondo pilastro: **l’ereditarietà**, cioè il meccanismo tramite cui alcuni stili “si trasmettono” dai genitori ai figli nel DOM.

Spoiler: non tutti gli stili sono ereditabili, e capire quali lo sono è fondamentale per scrivere CSS pulito ed efficiente.no 