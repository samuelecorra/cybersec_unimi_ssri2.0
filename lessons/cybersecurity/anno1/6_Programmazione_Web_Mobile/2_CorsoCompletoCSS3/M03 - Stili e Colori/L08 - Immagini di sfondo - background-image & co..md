## **Lezione 8: Immagini di sfondo (background-image & co.)**

In questa lezione passiamo dalla teoria sui colori all’uso pratico delle **immagini come sfondo** degli elementi, soprattutto del `body` (la classica “hero” in alto pagina, i caroselli, ecc.).

L’idea di base: il background di un elemento può non essere un colore piatto, ma una **immagine**, che poi andiamo a controllare con varie proprietà.

---

### **Impostare un’immagine di sfondo**

Per mettere un’immagine di sfondo, si usa:

```css
body {
    background-image: url("sfondo.jpg");
}
```

Note importanti:

- L’immagine deve essere in una posizione raggiungibile dal file CSS (es. stessa cartella, o una sottocartella tipo `img/sfondo.jpg`).
    
- Appena metti `background-image`, il browser:
    
    - piazza l’immagine partendo dall’angolo in alto a sinistra,
        
    - la mostra alla sua dimensione originale,
        
    - e per default la **ripete** in orizzontale e verticale per riempire l’area.
        

Se l’immagine è piccola, vedrai un “pattern” ripetuto. Se è grande ma non delle giuste proporzioni, potresti vedere solo una parte dell’immagine.

---

### **Controllare la dimensione: background-size**

La proprietà chiave è:

```css
background-size: valore;
```

I valori più importanti:

1. `auto`
    
    - Comportamento di default: l’immagine mantiene la sua dimensione originale.
        
2. `contain`
    
    - Scala l’immagine in modo che **stia tutta** dentro l’area dell’elemento:
        
        - rispetta il rapporto larghezza/altezza (aspect ratio),
            
        - può lasciare spazi vuoti.
            
    
    Esempio:
    
    ```css
    body {
        background-image: url("sfondo.jpg");
        background-size: contain;
    }
    ```
    
3. `cover`
    
    - Scala l’immagine in modo da **coprire completamente** l’area dell’elemento:
        
        - sempre rispettando l’aspect ratio,
            
        - può tagliare parti dell’immagine.
            
    - È il valore più usato per le hero full-screen.
        
    
    ```css
    body {
        background-image: url("sfondo.jpg");
        background-size: cover;
    }
    ```
    
    Se l’immagine è troppo piccola rispetto allo schermo, con `cover` verrà “stirata” e risulterà sgranata: meglio usare immagini con una buona risoluzione.
    
4. Valori espliciti (px, %, ecc.)
    
    Puoi anche impostare misure fisse:
    
    ```css
    body {
        background-image: url("sfondo.jpg");
        background-size: 1000px;    /* larghezza 1000px, altezza proporzionata */
    }
    ```
    
    Oppure:
    
    ```css
    body {
        background-image: url("sfondo.jpg");
        background-size: 10px 10px; /* larghezza 10px, altezza 10px */
    }
    ```
    
    In questo caso, l’immagine diventa un “quadrattino” che, combinato con la ripetizione, crea un pattern di sfondo.
    

---

### **Ripetizione dell’immagine: background-repeat**

Per default, una background image viene **ripetuta** in entrambe le direzioni.  
Puoi controllare questo comportamento con:

```css
background-repeat: valore;
```

Valori principali:

- `repeat`
    
    - default: ripete l’immagine in X e Y.
        
- `no-repeat`
    
    - mostra l’immagine una sola volta, senza ripeterla.
        
    
    ```css
    body {
        background-image: url("sfondo.jpg");
        background-repeat: no-repeat;
    }
    ```
    
- `repeat-x`
    
    - ripete solo in orizzontale.
        
- `repeat-y`
    
    - ripete solo in verticale.
        

Questo è utilissimo per:

- pattern,
    
- texture,
    
- piccoli dettagli che vuoi “tappezzino” l’elemento.
    

---

### **Posizionare l’immagine: background-position**

Per decidere dove piazzare l’immagine dentro l’elemento:

```css
background-position: valore-x valore-y;
```

Esempi con parole chiave:

```css
body {
    background-image: url("sfondo.jpg");
    background-repeat: no-repeat;
    background-position: center;          /* centro centro */
}

body {
    background-image: url("sfondo.jpg");
    background-repeat: no-repeat;
    background-position: top center;      /* sopra, centrata */
}

body {
    background-image: url("sfondo.jpg");
    background-repeat: no-repeat;
    background-position: bottom right;    /* in basso a destra */
}
```

Esempio con coordinate (x, y):

```css
body {
    background-image: url("sfondo.jpg");
    background-repeat: no-repeat;
    background-position: 40px 50px;  /* 40px da sinistra, 50px dall’alto */
}
```

Esistono anche:

- `background-position-x`
    
- `background-position-y`
    

ad esempio:

```css
body {
    background-image: url("sfondo.jpg");
    background-repeat: no-repeat;
    background-position-x: right;
    background-position-y: top;
}
```

Ma in pratica, nella maggior parte dei casi, basta il `background-position` “classico”.

---

### **Sfondo fisso vs sfondo che scorre: background-attachment**

Di default, l’immagine di sfondo “si muove” quando fai scroll.  
Questo comportamento si controlla con:

```css
background-attachment: valore;
```

Valori importanti:

- `scroll`
    
    - default: lo sfondo scorre insieme al contenuto.
        
- `fixed`
    
    - lo sfondo resta **fermo** mentre il contenuto scorre sopra.
        
    - effetto base dei layout “parallax”.
        

Esempio:

```css
body {
    background-image: url("sfondo.jpg");
    background-size: cover;
    background-attachment: fixed;
}
```

Quando scrolli, vedrai il contenuto muoversi, ma l’immagine restare “ancorata” allo schermo.

---

### **Limitare la zona coperta dallo sfondo: background-clip**

Qui entra in gioco la “box model” (contenuto, padding, bordo):

- content box: area occupata dal contenuto (testo, immagini, ecc.)
    
- padding: spazio interno tra contenuto e bordo
    
- border: il bordo vero e proprio
    

Per vedere bene la differenza, consideriamo un `div`:

```html
<div class="box">
    Contenuto di prova dentro il div.
</div>
```

```css
.box {
    padding: 30px;
    border: 5px dashed black;
    background-image: url("sfondo.jpg");
    background-size: cover;
}
```

Ora aggiungiamo:

```css
.box {
    background-clip: border-box;
}
```

I tre valori principali:

1. `border-box`
    
    - lo sfondo arriva fino al bordo, cioè copre:
        
        - contenuto + padding + area del bordo (dietro il bordo).
            
2. `padding-box`
    
    - lo sfondo copre contenuto + padding,
        
    - ma **non** va sotto il bordo (dietro al bordo vedresti il colore di default, es. bianco).
        
3. `content-box`
    
    - lo sfondo copre solo l’area del contenuto,
        
    - il padding resta “vuoto” (colore di sfondo di default dell’elemento),
        
    - il bordo resta esterno.
        

Esempio:

```css
.box {
    padding: 30px;
    border: 5px dashed black;
    background-image: url("sfondo.jpg");
    background-size: cover;

    /* Prova uno alla volta: */
    background-clip: border-box;   /* sfondo fino al bordo */
    /* background-clip: padding-box; */
    /* background-clip: content-box; */
}
```

È un controllo fine, utile quando vuoi giochi di colore tra contenuto, padding e bordo.

---

### **Mescolare colore di sfondo e immagine: background-blend-mode**

Ultimo pezzo: puoi combinare una **background-image** con un **background-color** usando modalità di fusione (blend), concetto molto simile a Photoshop, Figma, ecc.

Esempio base:

```css
body {
    background-image: url("sfondo.jpg");
    background-color: rgb(0, 0, 255);  /* blu */
    background-size: cover;
    background-blend-mode: multiply;
}
```

Qui succede:

- l’immagine e il colore blu vengono “fusi” secondo la modalità `multiply`,
    
- il risultato è un’immagine con una dominante blu, senza toccare il file originale.
    

Puoi provare vari valori per `background-blend-mode`:

- `multiply`
    
- `screen`
    
- `overlay`
    
- `darken`
    
- `lighten`
    
- `color-burn`
    
- `color-dodge`
    
- `difference`
    
- `exclusion`
    

Il vantaggio è enorme:

- un’unica immagine di base,
    
- tanti effetti diversi solo cambiando CSS,
    
- niente bisogno di generare mille versioni dell’immagine in un editor di grafica.
    

---

### **Collegamento con la proprietà shorthand background**

Ricorda che:

```css
background: ... ;
```

è una proprietà “shorthand” che può contenere:

- colore di sfondo,
    
- immagine,
    
- posizione,
    
- repeat,
    
- attachment,
    
- ecc.
    

Per ora ha più senso tenere le proprietà separate per capire bene cosa fa cosa:

```css
body {
    background-image: url("sfondo.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
}
```

In futuro potrai comprimere se vorrai, ma prima è importante avere chiaro ogni singolo pezzo.

---

### **Idea chiave da portarti dietro**

- `background-image` ti mette una immagine alle spalle dell’elemento.
    
- `background-size`, `background-repeat`, `background-position`, `background-attachment`, `background-clip` ti permettono di controllarla in modo molto preciso.
    
- Con `background-blend-mode` puoi anche fonderla con un colore di sfondo e ottenere effetti grafici complessi senza toccare i file delle immagini.
    

Dalla prossima lezione, si passa al mondo dei bordi (`border`), che abbiamo appena sfiorato negli esempi ma che merita un approfondimento serio.