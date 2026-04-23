## **Lezione 10 : L’elemento `<picture>`**

### **1. Introduzione**

L’elemento `<picture>` sembra a prima vista simile a `<img>` o `<figure>`, ma in realtà permette un livello completamente diverso di controllo: puoi fornire al browser **più immagini alternative**, lasciando a lui la scelta migliore in base al contesto, come il **formato supportato** o la **dimensione dello schermo**.  
È uno strumento fondamentale per il **responsive design** delle immagini.

---

### **2. Struttura di base di `<picture>`**

L’elemento contiene:

1. **Uno o più `<source>`**, ognuno con una “condizione” (formato, larghezza schermo, ecc.).
    
2. **Un `<img>` finale**, che funge da _fallback_ universale.
    

Esempio minimale:

```html
<picture>
    <source srcset="/immagini/montagne.avif" type="image/avif">
    <source srcset="/immagini/montagne.webp" type="image/webp">
    <img src="/immagini/montagne.jpg" alt="Montagne in lontananza">
</picture>
```

Il browser sceglie **una sola immagine** tra tutte: quella migliore tra quelle che è in grado di supportare.

---

### **3. Logica di funzionamento**

#### **3.1. Il browser sceglie solo un’opzione**

- Non vengono caricate tutte le immagini: **una sola viene selezionata**.
    
- Se nessun `<source>` è compatibile, viene caricata l’immagine dell’`<img>`.
    

#### **3.2. Scenario classico: formati immagine**

Molti formati moderni hanno vantaggi, ma non tutti i browser li supportano:

- **AVIF** → qualità ottima, file piccolissimi
    
- **WebP** → supporto molto ampio
    
- **JPG** → supporto universale (fallback)
    

Con `<picture>` possiamo dire al browser:

> “Se supporti AVIF, usa quello.  
> Se non puoi, prova WebP.  
> Se non puoi neanche WebP, vai sul JPG.”

Esempio:

```html
<picture>
    <source srcset="/immagini/montagne.avif" type="image/avif">
    <source srcset="/immagini/montagne.webp" type="image/webp">
    <img src="/immagini/montagne.jpg" alt="Montagne in lontananza">
</picture>
```

Questa tecnica si chiama **fallback**: preparo alternative più moderne e compatte, e se non vanno bene, torno al formato “classico”.

---

### **4. `<picture>` per il responsive design**

Oltre ai formati, `<picture>` può cambiare immagine in base allo **schermo**, allo **zoom**, o all’**orientamento**.

Per farlo si usa l’attributo **media**, che funziona come le media query del CSS.

#### **Esempio: immagine diversa su mobile**

```html
<picture>
    <source srcset="/immagini/mobile.jpg" media="(max-width: 600px)">
    <img src="/immagini/desktop.jpg" alt="Immagine responsive">
</picture>
```

Significa:

- Se lo schermo ha una larghezza **≤ 600px**, usa `mobile.jpg`.
    
- Altrimenti, usa `desktop.jpg`.
    

Il browser “risponde” al contesto (da qui la parola _responsive_).

---

### **5. Perché usare immagini diverse su mobile?**

Il motivo non è solo tecnico, ma spesso **artistico**.

Su uno schermo piccolo:

- un’immagine grande, ricca di dettagli, risulta compressa e poco leggibile
    
- potrebbe essere meglio una **versione zoomata**, ad esempio concentrata su un soggetto principale
    
- potremmo volere una composizione più semplice
    

Su uno schermo grande:

- possiamo mostrare l’immagine completa
    
- più dettagli = migliore resa visiva
    

Esempio concettuale:

```html
<picture>
    <source srcset="/immagini/montagne-zoom-mobile.jpg" media="(max-width: 600px)">
    <img src="/immagini/montagne-full.jpg" alt="Montagne">
</picture>
```

Se il browser non trova l’immagine del `<source>`, userà automaticamente quella nel `<img>`.

---

### **6. Comportamenti di fallback**

Ci sono due livelli di fallback:

1. **Se un `<source>` fallisce**, prova il successivo.
    
2. **Se tutti falliscono**, carica l’`<img>`.
    
3. **Se il browser non supporta `<picture>`**, ignora tutto e usa comunque l’`<img>`.
    

Questo garantisce compatibilità fino ai browser più vecchi.

---

### **7. Esempio combinato completo**

Formati moderni + responsive design + fallback universale:

```html
<picture>
    <!-- Versione mobile (prima si controlla lo schermo) -->
    <source
        srcset="/immagini/montagne-mobile.avif"
        media="(max-width: 600px)"
        type="image/avif">

    <source
        srcset="/immagini/montagne-mobile.webp"
        media="(max-width: 600px)"
        type="image/webp">

    <!-- Versione desktop -->
    <source srcset="/immagini/montagne.avif" type="image/avif">
    <source srcset="/immagini/montagne.webp" type="image/webp">

    <!-- Fallback universale -->
    <img src="/immagini/montagne.jpg" alt="Montagne in lontananza">
</picture>
```

Ordine consigliato:

1. **Prima le condizioni più specifiche** (mobile)
    
2. **Poi le versioni base** (desktop)
    
3. **Infine l’`<img>`** (fallback definitivo)
    

---

### **8. Collegamento al responsive design**

`<picture>` è uno dei pilastri del responsive design insieme a:

- media query CSS
    
- layout fluidi
    
- unità relative (%, `vw`, `vh`)
    
- gestione dell’orientamento (portrait/landscape)
    

Serve a far sì che il sito “risponda” al dispositivo dell’utente presentando immagini perfette in ogni contesto:

- meno pesanti su mobile
    
- più dettagliate su desktop
    
- adattate artisticamente quando utile
    

---

### **9. Conclusione**

L’elemento `<picture>` permette di:

- fornire **più versioni della stessa immagine**
    
- creare immagini **responsive** che cambiano in base al dispositivo
    
- implementare **fallback robusti**
    
- ottimizzare **prestazioni**, **qualità** e **esperienza utente**
    
- separare la logica tra **design artistico** e struttura HTML
    

È uno degli strumenti fondamentali per gestire immagini moderne in modo professionale.