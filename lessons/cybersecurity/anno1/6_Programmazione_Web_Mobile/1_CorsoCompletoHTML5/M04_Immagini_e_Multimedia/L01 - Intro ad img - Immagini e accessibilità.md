## **Lezione 1: Intro ad `<img>` – Immagini e accessibilità**

## **1. Introduzione**

L’elemento `<img>` è il fondamento di tutte le immagini sul web. È un **elemento vuoto**, quindi **non ha un tag di chiusura**, e possiede alcuni attributi essenziali per funzionare correttamente, essere accessibile, e comportarsi bene durante il caricamento della pagina.

Questa lezione mette ordine in tutto ciò che serve davvero sapere per usare le immagini in HTML nel modo corretto già da ora, anche prima di passare al CSS.

---

## **2. La struttura base di un’immagine**

```html
<img src="immagini/icona.webp" alt="..." />
```

- **`src`** indica _la sorgente_ dell’immagine (il percorso).
    
- **`alt`** fornisce _il testo alternativo_.
    

Il browser **non richiede** un tag di chiusura.

---

## **3. L’attributo `alt`: perché è fondamentale**

### **3.1 Due funzioni essenziali**

L’attributo `alt` serve a due scopi:

1. **Mostrare un testo sostitutivo se l’immagine non carica**  
    (ad esempio per errore nel percorso o connessione lenta)
    
2. **Descrivere l’immagine ai lettori di schermo**  
    per utenti non vedenti o ipovedenti.
    

Esempio di errore volontario nel `src`:

```html
<img src="immagini/icona-sbagliata.webp" alt="Schermata della web app SketchPad" />
```

Il browser, non trovando il file, mostra il testo.  
Lo screen reader usa sempre quel testo per “raccontare” l’immagine.

---

### **3.2 Come NON si deve scrivere il `alt`**

Errore comune (molto grave in accessibilità):

```html
<img src="runner.jpg" alt="migliori scarpe da corsa" />
```

⚠️ **Perché è sbagliato?**

- Non descrive l’immagine: parla di scarpe, ma magari nell’immagine c’è solo un runner.
    
- È un tentativo di “truffare” i motori di ricerca ripetendo le keyword.
    
- Gli screen reader ripetono male lo stesso concetto: titolo + immagine = duplicato inutile.
    

### **3.3 Scrittura corretta**

Scrivi sempre **ciò che si vede**:

```html
<img src="runner.jpg" alt="Un uomo che corre su un sentiero di campagna" />
```

Il testo deve essere:

- **Descrittivo**
    
- **Essenziale**
    
- **Non pieno di keyword**
    
- **Sincero rispetto all’immagine**
    

---

## **4. `width` e `height` – perché vanno SEMPRE messi**

Prima del CSS si usano così:

```html
<img src="immagini/icona.webp" width="400" height="400" alt="Schermata della web app SketchPad" />
```

### **Perché sono importanti?**

Per evitare il **CLS (Cumulative Layout Shift)**.

### **Spiegazione Feynman-style**

Immagina che la pagina carichi riga per riga.  
Se il browser _non sa_ quanto spazio occuperà l’immagine, mette prima il testo in alto…  
…poi quando l’immagine finalmente arriva, _spinge tutto verso il basso_.

Questo “salto” è un **layout shift** ed è considerato un problema grave dai motori di ricerca.

Quando imposti width/height:

- il browser **riserva lo spazio esatto**, anche se l’immagine non è ancora caricata,
    
- l’impaginazione **non salta**,
    
- l’esperienza utente è migliore.
    

---

## **5. Evitare la trascinabilità dell’immagine (`draggable="false"` )**

Di default le immagini possono essere trascinate con il mouse:

- Effetto brutto
    
- Effetto indesiderato nelle web app
    
- Impatta sulla UX
    

Puoi impedirlo così:

```html
<img src="immagini/icona.webp" alt="Schermata della web app SketchPad" draggable="false" />
```

Ora l’immagine non si può più “tirare” col mouse come un file trascinabile.

---

## **6. L’attributo `title` e il tooltip**

Puoi aggiungere un piccolo “suggerimento” che compare quando passi con il mouse:

```html
<img src="immagini/icona.webp" alt="Schermata della web app SketchPad" title="Schermata della web app SketchPad" />
```

Questo fa comparire il **tooltip**.

### ⚠️ Nota importante sugli screen reader

Al momento della registrazione:

- `title` può creare confusione ai lettori di schermo
    
- quindi non è consigliato usarlo sempre
    

Mantienilo presente, ma usalo solo quando serve davvero.

---

## **7. Ricapitolazione (Feynman-style)**

Se vuoi usare un’immagine _bene_, devi chiederti tre cose:

### **1. Come la descrivo a chi non la vede?**

→ Scrivi un `alt` che descrive _esattamente_ ciò che l’immagine mostra.

### **2. Come evito che il layout esploda mentre carica?**

→ Imposta `width` e `height`.

### **3. Voglio impedire trascinamenti accidentali?**

→ Aggiungi `draggable="false"`.

Tutto il resto (CSS, responsive images, `srcset`, `picture`, lazy loading, ottimizzazione SEO) arriverà nei prossimi video.

---

## **8. Esempio finale completo**

```html
<img
  src="immagini/icona.webp"
  alt="Schermata della web app SketchPad"
  width="400"
  height="400"
  draggable="false"
/>
```

Pulito, accessibile, stabile, pronto per la produzione.