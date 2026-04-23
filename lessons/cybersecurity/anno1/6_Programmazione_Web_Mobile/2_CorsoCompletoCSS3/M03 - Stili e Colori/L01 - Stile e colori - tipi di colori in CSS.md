## **Lezione 1 – Stile e colori: tipi di colori in CSS**

### **1. Obiettivo della sezione “Stile e colori”**

In questa nuova sezione cominciamo a lavorare seriamente con:

- colori (come si rappresentano in CSS e che differenza c’è tra le varie notazioni)
    
- e, nei prossimi video: `border`, `outline`, `box-shadow`, ecc.
    

Quindi non è solo teoria astratta sui colori: stiamo costruendo le basi per tutto il resto del lavoro grafico in CSS.

---

### **2. Tutti i modi principali per scrivere un colore in CSS**

In CSS possiamo esprimere un colore in vari modi:

- tramite **nome testuale**  
    `red`, `green`, `salmon`, `indigo`, ecc.
    
- tramite **RGB**  
    `rgb(255, 0, 0)`
    
- tramite **RGBA** (RGB + opacità)  
    `rgba(255, 0, 0, 0.5)`
    
- tramite **esadecimale (HEX)**  
    `#ff0000`, `#151515`, ecc.
    
- tramite **HSL**  
    `hsl(0, 100%, 50%)`
    
- tramite **HSLA** (HSL + opacità)  
    `hsla(0, 100%, 50%, 0.7)`
    
- tramite keyword speciali come `currentColor`
    

In questo video ci concentriamo su:

- nomi testuali
    
- RGB / RGBA
    
- HEX
    
- HSL / HSLA
    
- `currentColor`
    
- concetto di **shade** (sfumature predefinite) e palette tipo Material Design / Tailwind
    

I “nuovi” color space (tipo `lab()`, `lch()`, `oklch()`, ecc.) li vedremo nel video successivo.

---

### **3. Colori con nome (valori testuali)**

Nel CSS puoi scrivere direttamente il nome del colore:

```css
body {
  background-color: black;
}

p {
  color: salmon;
}

h1 {
  color: indigo;
}
```

- Sono le cosiddette **color keyword**.
    
- VS Code (o altri editor) ti mostra un elenco di colori possibili man mano che scrivi (`red`, `green`, `purple`, `salmon`, `saddlebrown`, …).
    

Vantaggi:

- sono velocissimi da usare, soprattutto per prototipare velocemente
    
- sono leggibili (anche a colpo d’occhio: `salmon` → tonalità salmone)
    

Limiti:

- hai un set **finito** di colori
    
- non hai controllo “fine” sul tono (se vuoi un “quasi nero” preciso, è più comodo usare HEX / RGB / HSL)
    

---

### **4. RGB e RGBA**

#### **4.1. RGB: Red, Green, Blue**

Sintassi:

```css
body {
  background-color: rgb(255, 0, 0);   /* rosso pieno */
}
```

`rgb(r, g, b)`:

- `r` = componente **Red** (rosso)
    
- `g` = componente **Green** (verde)
    
- `b` = componente **Blue** (blu)
    

Ogni componente:

- è un numero da **0** a **255**
    
- 0 significa “niente di quel colore”
    
- 255 significa “quel colore al massimo”
    

Esempi:

```css
/* rosso puro */
background-color: rgb(255, 0, 0);

/* verde puro */
background-color: rgb(0, 255, 0);

/* blu puro */
background-color: rgb(0, 0, 255);

/* viola (rosso + blu) */
background-color: rgb(255, 0, 255);

/* grigio medio (tutti e tre uguali) */
background-color: rgb(128, 128, 128);
```

Nel color picker di VS Code:

- vedi un quadrato con la gradazione del colore e il cursore
    
- in alto di solito trovi `rgb(...)`
    
- quando sposti il puntatore:
    
    - la componente dominante (red, green, blue) cresce o diminuisce
        
    - i numeri cambiano in tempo reale
        

---

#### **4.2. RGBA: RGB + Alfa (opacità)**

`rgba(r, g, b, a)`:

- primi tre parametri = come RGB
    
- `a` = **alfa**, l’opacità del colore
    

L’alfa:

- va da **0** a **1**:
    
    - `0` → completamente trasparente
        
    - `1` → completamente opaco
        
    - numeri intermedi (es. `0.3`, `0.5`, `0.8`) → semitrasparente
        

Esempio:

```css
/* sfondo rosso semitrasparente */
background-color: rgba(255, 0, 0, 0.5);
```

Se il valore `a` è `1`, il colore è equivalente all’RGB normale:

```css
rgba(255, 0, 0, 1)  /* praticamente uguale a rgb(255, 0, 0) */
```

Visualmente:

- con alfa = 1 → colore pieno
    
- con alfa → 0 → scompare lo sfondo (vedi ciò che c’è sotto)
    
- tutti i valori intermedi creano **overlay** trasparenti (effetto vetro, overlay, ecc.)
    

---

### **5. Notazione esadecimale (HEX)**

La notazione esadecimale è un altro modo di scrivere **RGB** in compatto.

Sintassi classica:

```css
/* #RRGGBB */
background-color: #000000;  /* nero */
background-color: #ffffff;  /* bianco */
background-color: #ff0000;  /* rosso */
background-color: #151515;  /* quasi-nero molto usato nei temi dark */
```

Struttura:

- `#RRGGBB`
    
- `RR` = componente **Red**
    
- `GG` = componente **Green**
    
- `BB` = componente **Blue**
    

Ogni coppia va da:

- `00` a `ff` (hex)
    
- corrispondono a 0–255 (decimale)
    

Perché 0–9 e A–F?

- in esadecimale i “numeri” vanno:
    
    - `0 1 2 3 4 5 6 7 8 9` poi
        
    - `a b c d e f`
        
- quindi:
    
    - `00` = 0 in decimale
        
    - `ff` = 255 in decimale
        

Esempi estremi:

- nero: `#000000` → tutte le componenti = 0
    
- bianco: `#ffffff` → tutte le componenti = massimo (255)
    

Non devi memorizzare tutta la logica interna; in pratica:

- impari a memoria 3–4 colori che usi spessissimo (es. il tuo quasi-nero preferito, un grigio, un accent color)
    
- il resto lo generi con color picker o strumenti online
    

> Nota: nel color picker di VS Code puoi passare da RGB a HEX con un click: lui converte da solo.

---

### **6. HSL e HSLA**

#### **6.1. HSL**

`hsl(h, s, l)`:

- `h` = **Hue** (tonalità) → il “tipo” di colore (rosso, blu, verde…)
    
- `s` = **Saturation** (saturazione) → quanto il colore è “vivo”
    
- `l` = **Lightness** (luminosità) → quanta luce ha quel colore (nero, grigio, bianco)
    

Tipicamente:

- `h` va da **0 a 360** (giro completo di ruota dei colori)
    
    - 0 ≈ rosso
        
    - 120 ≈ verde
        
    - 240 ≈ blu
        
- `s` e `l` sono in **percentuale** (`0%`–`100%`)
    

Esempi:

```css
/* rosso pieno */
background-color: hsl(0, 100%, 50%);

/* verde pieno */
background-color: hsl(120, 100%, 50%);

/* blu pieno */
background-color: hsl(240, 100%, 50%);

/* rosso desaturato (tendente al grigio) */
background-color: hsl(0, 30%, 50%);

/* rosso molto scuro */
background-color: hsl(0, 100%, 20%);
```

Intuizione:

- variando **Hue** giri sulla ruota dei colori
    
- variando **Saturation** rendi il colore:
    
    - pieno e acceso (saturazione alta)
        
    - slavato/grigio (saturazione bassa)
        
- variando **Lightness** lo fai:
    
    - scuro (vicino a 0%)
        
    - medio (50%)
        
    - chiaro / quasi bianco (vicino a 100%)
        

---

#### **6.2. HSLA: HSL + Alfa**

Come per RGB, esiste la versione con opacità:

```css
body {
  background-color: hsla(240, 100%, 50%, 0.3);
}
```

- primi tre parametri = Hue / Saturation / Lightness
    
- quarto parametro = Alfa (0–1)
    

Stessa logica del `rgba(...)`, ma con un modo diverso (e spesso più intuitivo) di ragionare sul colore di base.

---

### **7. La parola chiave `currentColor`**

`currentColor` è una keyword speciale.

Idea:

- ogni elemento ha una proprietà `color` (il colore del testo)
    
- `currentColor` significa “usa **il colore corrente del testo** su questo elemento”
    

Esempio:

```css
body {
  color: white;                /* colore del testo */
  background-color: currentColor;
}
```

In questo caso:

- `currentColor` viene sostituito con `white`
    
- quindi lo sfondo del `body` diventa bianco (come il testo → pessima idea, ma serve come esempio)
    

Altro esempio utile:

```css
button {
  color: #ffffff;
  border: 2px solid currentColor;  /* bordo dello stesso colore del testo */
}
```

Se cambi il `color` del bottone, il bordo seguirà automaticamente:

```css
button.primary {
  color: #00ff88;  /* cambia colore del testo e automaticamente del bordo */
}
```

Se non imposti un `color` esplicito su un elemento:

- `currentColor` farà riferimento al `color` ereditato (es. nero di default del browser, o quello del genitore).
    

---

### **8. Color picker e convertitori online**

Anche se tutta la teoria è interessante, **nella pratica**:

- userai spesso il **color picker dell’editor** (es. VS Code)
    
- oppure **tool online** per convertire/creare palette
    

Nel color picker:

- puoi cliccare sul quadratino di anteprima colore, e:
    
    - muovere la manopolina per cambiare tonalità
        
    - passare da `rgb(...)` a `hsl(...)` a `#hex` con un click
        
    - regolare l’**opacità** tramite slider verticale (diventa `rgba(...)` o `hsla(...)`)
        

Nei convertitori online:

- inserisci un colore (es. `#151515` o `rgb(21, 21, 21)`)
    
- vedi automaticamente:
    
    - RGB
        
    - HEX
        
    - HSL
        
    - eventuali varianti di opacità
        
    - e spesso una serie di **shade** (più chiaro/più scuro) già pronte da copiare
        

Questo ti evita di:

- ricordare a memoria formule di conversione tra RGB / HEX / HSL
    
- dover fare calcoli a mano
    

---

### **9. Il concetto di “shade” (sfumature)**

Ogni colore ha **migliaia di varianti**:

- leggermente più scuro
    
- leggermente più chiaro
    
- leggermente più saturo
    
- ecc.
    

In teoria potresti scegliere **qualsiasi** valore intermedio.  
In pratica sarebbe ingestibile.

Per questo, librerie e sistemi di design usano il concetto di **shade** predefinite:

- una scala ordinata di tonalità dello stesso colore, numerata in modo standard.
    

Esempi:

#### **9.1. Material Design (Google)**

Per ogni colore (es. `Red`):

- hai una scala tipo:
    
    - `50`
        
    - `100`
        
    - `200`
        
    - `300`
        
    - `400`
        
    - `500`
        
    - `600`
        
    - `700`
        
    - `800`
        
    - `900`
        

Ogni numero rappresenta:

- `50` → versione molto chiara
    
- `500` → tono “base”
    
- `900` → versione molto scura
    

Accanto ad ogni shade hai il corrispondente **HEX** (che puoi copiare e usare in CSS).

#### **9.2. Tailwind CSS**

Stessa idea, ma organizzata per famiglie:

- `slate-50`, `slate-100`, …, `slate-900`, `slate-950`
    
- `gray-50`, `gray-100`, …, `gray-900`, `gray-950`
    
- `zinc-50` … `zinc-950`
    
- `red-50` … `red-950`
    
- ecc.
    

Le shade tipo `950` sono state introdotte per avere:

- colori **molto scuri** adatti ai **dark theme**
    

Questa struttura:

- ti dà una **palette coerente**: tutti i colori sono pensati per stare bene insieme
    
- ti semplifica la vita: invece di “inventare a caso” mille colori, ti appoggi a una scala pensata bene
    
- rende facile definire temi chiari/scuri consistenti
    

---

### **10. Riepilogo**

In questo video hai visto:

- che i colori in CSS si possono esprimere in tanti modi:
    
    - **nomi testuali** (`red`, `salmon`, `indigo`, …)
        
    - **RGB / RGBA** → quantità di rosso/verde/blu + opacità
        
    - **HEX** → versione compatta in esadecimale di RGB
        
    - **HSL / HSLA** → tonalità/saturazione/luminosità + opacità
        
    - keyword **`currentColor`** per riutilizzare automaticamente il colore del testo
        
- che editor e tool online:
    
    - ti permettono di convertire tra questi formati
        
    - ti offrono **shade** già pronte per costruire palette solide
        
- che sistemi come **Material Design** e **Tailwind** organizzano le sfumature con numeri (50–900/950) per facilitare la scelta di varianti più chiare/scure.
    

Nel prossimo video il focus saranno i **nuovi tipi di colori** introdotti di recente in CSS e un po’ di discorso su palette e psicologia del colore.