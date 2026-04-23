## **Lezione 18: Immagini Vettoriali (SVG)**

### **1. Introduzione**

Le immagini SVG (_Scalable Vector Graphics_) rappresentano un tipo di grafica completamente diverso dalle classiche immagini raster (JPG, PNG, WebP).  
Lo scopo di questa lezione è capire:

- cosa sono le immagini vettoriali
    
- come funzionano rispetto alle immagini raster
    
- quando conviene usarle e quando NO
    
- come crearle, manipolarle e incorporarle
    
- quali vantaggi e svantaggi presentano
    
- perché sono così diffuse nel web moderno (icone, loghi, forme decorative, divisori)
    

---

### **2. Raster vs Vettoriale: la differenza fondamentale**

#### **2.1. Immagini rasterizzate (JPG, PNG, WebP)**

- Sono composte da **pixel**, cioè quadratini di colore.
    
- Ogni immagine è “fissa” nella sua risoluzione.
    
- Se allarghi un’immagine raster, i pixel **si ingrandiscono** e l’immagine diventa **sgranata**.
    
- È come una maglietta taglia M: se la metti a una persona XL, si deforma e si “rompe”.
    

Esempio:  
Se un'immagine è 200×200 pixel, anche se la mostri a 2000×2000, i pixel restano 200×200 → si allargano → si sfocano.

---

#### **2.2. Immagini vettoriali (SVG)**

- NON sono fatte di pixel.
    
- Sono descritte da **regole matematiche** e **proporzioni** (linee, curve, percorsi).
    
- Il browser disegna l’immagine ogni volta **in base alla dimensione necessaria**.
    
- Puoi allargare un SVG a 10px o 10 metri: resterà sempre perfettamente nitido.
    
- È come avere un modello matematico della maglietta: se la persona è XL, la maglietta viene “ridisegnata” con le proporzioni corrette.
    

Esempio:  
Un quadrato vettoriale non è 200×200 pixel, ma:

- “ha lato lungo X”
    
- “è centrato a metà”
    
- “riempito di questo colore”
    

Il browser lo renderizza ogni volta su misura.

---

### **3. Perché gli SVG sono perfettamente nitidi**

Quando zoomi un SVG:

- non stai zoomando pixel già esistenti
    
- stai dicendo al browser di **ridisegnare l’immagine** con valori più grandi
    

Risultato:  
Nitidezza perfetta a qualsiasi zoom.

Se metti un SVG accanto a un PNG ingrandito, il PNG sgranerà, l’SVG no.

---

### **4. Cosa contiene un file SVG**

Un SVG è **puro codice**, scritto in linguaggio XML.

Esempio di SVG semplice:

```html
<svg width="300" height="200">
    <rect width="100%" height="100%" fill="black" />
</svg>
```

Il browser:

1. legge il codice
    
2. capisce la forma
    
3. la disegna sullo schermo usando proporzioni perfette
    

Per questo gli SVG sono “intelligenti”: non contengono pixel ma istruzioni.

---

### **5. Creare un SVG a mano**

Non si fa quasi mai, ma è utile per capire.

#### **Esempio 1: Rettangolo**

```html
<svg width="300" height="200">
    <rect width="100%" height="100%" fill="black" />
</svg>
```

#### **Esempio 2: Cerchio centrato**

```html
<svg width="300" height="200">
    <circle
        r="90"
        cx="150"
        cy="100"
        fill="blue" />
</svg>
```

- `cx` = posizione X del centro
    
- `cy` = posizione Y del centro
    
- `r` = raggio
    

---

### **6. Generatori SVG: il metodo moderno**

Nella pratica reale, **nessuno** crea SVG complessi a mano.  
Si usano generatori online:

#### Esempi tipici:

- **Shape Divider Generator** (per creare onde, divisori decorativi, forme geometriche)
    
- **Blob Generators** (forme organiche)
    
- **Icon Generator** (icone vettoriali)
    
- **SVG Backgrounds**
    
- **Haikei** (forme artistiche)
    

Questi strumenti generano:

- codice SVG
    
- eventuale CSS
    
- file scaricabili
    

Puoi poi incollare il codice direttamente nel tuo HTML o salvarlo come file `.svg`.

---

### **7. Inserire un SVG nel tuo sito**

Ci sono due metodi.

#### **7.1. Inserirlo come immagine raster**

```html
<img src="/immagini/logo.svg" alt="Logo">
```

Pro:

- semplice
    
- funziona con qualsiasi immagine
    

Contro:

- non puoi controllare l’SVG via CSS/JavaScript
    
- l’SVG diventa un file esterno come un PNG
    

---

#### **7.2. Inserirlo come codice inline**

```html
<svg ...> ... </svg>
```

Pro:

- puoi modificarlo via CSS
    
- puoi cambiare colori dinamicamente
    
- puoi animarlo
    
- puoi manipolare ogni parte con JavaScript
    

Contro:

- il codice SVG appare dentro l’HTML (più pesante)
    
- non è memorizzato in cache dal browser
    

Questo metodo è il più potente, e quello usato per:

- icone
    
- loghi
    
- grafiche personalizzate
    
- forme decorative
    

---

### **8. Vantaggi degli SVG**

1. **Nitidezza infinita**  
    Nessun pixel → nessuna sgranatura.
    
2. **File spesso molto leggeri**  
    Soprattutto per icone o forme basiche.
    
3. **Manipolabili via CSS e JS**  
    Puoi cambiare colore, animare linee, trasformare forme.
    
4. **Perfetti per icone, loghi, UI**  
    Per questo siti e app moderne usano quasi solo SVG.
    
5. **Ridimensionamento perfetto**  
    Da 10px a 10 metri senza perdere qualità.
    

---

### **9. Svantaggi degli SVG**

1. **Non sono cacheabili** (quando inline)  
    Il browser non può salvarli come asset separati.
    
2. **Codice più pesante all’interno della pagina**  
    Un SVG complesso può generare centinaia di righe di path.
    
3. **Debug più complesso**  
    Se l’SVG è generato, il codice risultante può essere molto articolato.
    
4. **Meno immediati da “vedere” nel codice**  
    Un PNG può essere aperto e visto; un SVG richiede interpretazione.
    
5. **Se usati come immagini, cambiare colore è difficile**
    
    - File esterno: devi modificare il file .svg
        
    - Inline: puoi usare CSS ma aumentarà il peso dell’HTML
        

---

### **10. Caso pratico: cambiare colore**

#### **Caso A – SVG come file immagine:**

Se hai 10 pagine con la stessa icona:

```html
<img src="/icone/quadrato.svg">
```

Per cambiare colore → basta modificare il file `quadrato.svg`.

E tutte le 10 pagine cambiano automaticamente.

---

#### **Caso B – SVG inline in 10 punti diversi:**

```html
<svg>...</svg>
<svg>...</svg>
<svg>...</svg>
...
```

Per cambiare colore → devi modificare 10 volte il codice, uno per ogni punto.

Risultato: **non scalabile**.

---

### **11. Quando usare SVG e quando NO**

#### **Usa SVG per:**

- icone
    
- loghi
    
- forme decorative
    
- shape divider
    
- elementi UI
    
- grafica minimal
    
- illustrazioni stilizzate
    

#### **Non usare SVG per:**

- foto
    
- immagini complesse
    
- immagini con molti dettagli
    
- texture fotografiche
    
- immagini reali
    

Per quelle → JPG / WebP / PNG.

---

### **12. Conclusione**

Le immagini vettoriali SVG:

- non usano pixel
    
- sono nitide a qualsiasi dimensione
    
- sono scritte in XML come codice
    
- si creano con generatori, non a mano
    
- hanno vantaggi enormi per icone e UI
    
- hanno svantaggi in termini di codice, cache e complessità
    
- vanno usate con intelligenza nei punti giusti del sito
    

Sono uno strumento potentissimo per il web moderno, ed è fondamentale saperle padroneggiare.