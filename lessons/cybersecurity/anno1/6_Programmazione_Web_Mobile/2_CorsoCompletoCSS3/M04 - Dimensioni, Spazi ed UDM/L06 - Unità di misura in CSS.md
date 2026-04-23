## **Lezione 6: Unità di misura in CSS – px, %, em, rem, vw, vh**

---

### **1. Due grandi famiglie: assolute vs relative**

Quando in CSS dai una dimensione (width, height, font-size, padding, margin, ecc.) non passi solo un numero, ma anche **un’unità di misura**.

Quello che hai visto finora rientra in due famiglie:

- **Assolute** → non dipendono da nient’altro
    
    - esempio: `px`
        
- **Relative** → dipendono da qualcosa:
    
    - il contenitore
        
    - il font di riferimento
        
    - il viewport (la finestra visibile del browser)
        

In questa lezione hai:

- `px`
    
- `%`
    
- `em`
    
- `rem`
    
- `vh`
    
- `vw` (nel parlato dice “V e V”, ma concettualmente è `vw`)
    

---

### **2. Pixel (px): unità assoluta**

I **pixel** sono l’unità “fisica” dello schermo (anche se oggi è tutto un po’ astratto dai DPI, ma concettualmente va bene così):

- uno schermo Full HD è 1920 × 1080 → 1920 px di larghezza, 1080 px di altezza
    
- uno schermo 4K ha molti più pixel, ma **200px in CSS rimangono 200px** su ogni schermo
    

Quando scrivi:

```css
width: 200px;
height: 200px;
```

stai dicendo:

> Voglio un box largo 200 “puntini” e alto 200 “puntini”, sempre quelli, indipendentemente dal dispositivo.

Caratteristiche chiave:

- sono **facili da ragionare** mentalmente
    
- sono **rigidi** → non si adattano al contenitore o allo schermo
    

---

### **3. Percentuale (%): relativa al contenitore**

La percentuale è **relativa al parent** (contenitore diretto).

Esempi classici:

```css
.box {
  width: 100%;
}
```

Se `.box` sta direttamente dentro `body`:

- `width: 100%` → prende il 100% della larghezza di `body` (meno margini, ecc.)
    

Se hai:

```css
.box {
  width: 200px;
}

.box p {
  width: 50%;
}
```

Allora:

- il `p` è largo il **50%** di 200 px → quindi 100 px
    

Logica generale:

- `%` su `width`/`height` → fa riferimento alla dimensione del **contenitore**
    
- `%` su `width` → quasi sempre ok
    
- `%` su `height` → funziona solo se il parent ha un’altezza definita
    

Una cosa importante che hai visto nell’esempio:

- se un elemento ha `width: 100%` **ma** anche `max-width: 300px`  
    → non può andare oltre 300 px, anche se il 100% sarebbe di più
    

Quindi:

```css
width: 100%;
max-width: 300px;
```

significa:

> Cresci fino a 100% del contenitore, ma al massimo 300px.

---

### **4. em e rem: relative al font**

Qui entrano le unità più “subdole”: `em` e `rem`.

Hanno senso soprattutto su:

- `font-size`
    
- padding/margin legati al font (spazi proporzionati al testo)
    

Concetto chiave:

- `em` → relativo alla **font-size del parent (o dell’elemento stesso)**
    
- `rem` → relativo alla **font-size del root element (`html`)**
    

#### **4.1. Il valore di base: 16px**

Per default, quasi tutti i browser usano:

```css
html {
  font-size: 16px;
}
```

Se tu non tocchi niente:

- `1em` o `1rem` partono da 16px
    

Esempio:

```css
p {
  font-size: 2em;
}
```

Se il parent del `p` (o `html`, se nessuno cambia font-size) è 16px:

- `2em` → 2 × 16px = **32px**
    

Quindi:

- prima: `font-size` di default = 16px
    
- con `2em` → 32px
    

#### **4.2. em: relativo al parent**

Caso visto a lezione:

- `html` ha `font-size: 16px` (default)
    
- dentro `body` non cambi niente
    
- metti:
    

```css
p {
  font-size: 2em;
}
```

Risultato: 32px, perché:

- prende il valore ereditato (16) e lo moltiplica per 2
    

Ora cambia il parent. Supponi:

```css
.box1 {
  font-size: 8px;
}

.box1 p {
  font-size: 2em;
}
```

Qui:

- `p` sta dentro `.box1`
    
- `.box1` ha `font-size: 8px`
    
- `2em` → 2 × 8 = **16px**
    

Se in `.box2` non tocchi il font-size, rimane 16px:

```css
.box2 p {
  font-size: 1em;  /* o lasci default */
}
```

→ anche questo è 16px.

Risultato visivo:  
il `p` dentro `.box1` (2em su base 8) e quello dentro `.box2` (1em su base 16) hanno la **stessa dimensione visiva**.

Questa è la caratteristica di `em`:  
**scala in base alla gerarchia** e può “impilare” moltiplicazioni (se hai parent con `1.2em`, figli con `1.5em` ecc. diventa facile perdere i numeri).

#### **4.3. rem: relativo alla radice (html)**

`rem` = **root em** → sempre riferito al `font-size` di `html`.

Se fai:

```css
html {
  font-size: 16px;
}

p {
  font-size: 2rem;
}
```

otterrai sempre:

- `2rem` = 2 × 16px = **32px**
    

Non importa:

- in che div si trova il `p`
    
- che font-size abbiano i parent
    

Se cambi `html`:

```css
html {
  font-size: 20px;
}
```

allora:

- `2rem` = 40px
    

Quindi:

- `em` → “figlio” (si basa sul parent)
    
- `rem` → “nipote diretto dell’html” (ignora i parent intermedi)
    

È normale che questi concetti richiedano tempo ed esperienza:  
prima devi **sapere che esistono**, poi piano piano diventano naturali mentre fai progetti veri.

---

### **5. Viewport units: vh e vw**

Qui entriamo nel mondo del **viewport**.

Il viewport è:

> la parte di finestra in cui il sito è effettivamente visibile

Non è lo schermo intero:

- sopra hai barra del browser, tab, URL, ecc.
    
- sotto, su mobile, spesso la barra di navigazione
    

Quindi:

- Viewport width = **larghezza della parte bianca** dove vedi il sito
    
- Viewport height = **altezza della parte bianca** dove vedi il sito
    

Le unità:

- `1vw` = 1% della **larghezza** del viewport
    
- `1vh` = 1% dell’**altezza** del viewport
    

Quindi:

```css
width: 100vw;
height: 100vh;
```

significa:

> larghezza = 100% della viewport  
> altezza = 100% della viewport

E quindi il tuo elemento:

- si allarga sempre da bordo a bordo orizzontale
    
- si estende in altezza esattamente quanto la viewport (effetto “schermata intera”)
    

Differenza rispetto alla `%`:

- `width: 100%` → relativo al **contenitore**
    
- `width: 100vw` → relativo allo **schermo visibile** (viewport), **non al parent**
    

Esempio:

```css
.box {
  width: 100vw;
}
```

- prende sempre tutta la larghezza della viewport
    
- se ha anche margini, può creare overflow orizzontale (scrollbar)
    

Se fai:

```css
.box {
  width: 50vw;
}
```

ottieni un elemento largo **metà dello schermo**, indipendentemente da quanto è largo il parent.

---

### **6. Riepilogo mentale delle unità viste**

- **px** → unità assoluta, ogni valore è un “puntino di schermo”, non dipende da niente.
    
- **%** → relativo alla dimensione del **contenitore** (parent box).
    
- **em** → relativo al **font-size del parent** (o dell’elemento stesso, a seconda della proprietà).
    
- **rem** → relativo al **font-size di `html`** (root element).
    
- **vw** → 1% della **larghezza** del viewport.
    
- **vh** → 1% dell’**altezza** del viewport.
    

Regola pratica:

- se vuoi una dimensione **fissa** → usa `px`
    
- se vuoi qualcosa che si adatti al **parent** → usa `%`
    
- se vuoi che qualcosa sia proporzionato al **testo** → usa `em` o `rem` (per lo scaling globale quasi sempre `rem`)
    
- se vuoi che qualcosa sia proporzionato allo **schermo visibile** → usa `vh` / `vw`