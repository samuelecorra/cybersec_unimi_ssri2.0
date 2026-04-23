## **Lezione 6 – Background, Background-Color e Gradienti in CSS**

### **1. Introduzione**

In questa lezione lasciamo da parte la teoria del colore e iniziamo finalmente a lavorare **sul CSS pratico**.  
L’obiettivo è capire come funzionano:

- `background-color`
    
- `background` (la versione abbreviata “shorthand”)
    
- i **gradienti lineari**, **radiali** e **mesh gradients**
    
- la differenza tra colore di sfondo e immagine di sfondo
    
- cosa è un colore di fallback
    

Questa è la base del design visivo di qualsiasi sito web.

---

### **2. Background Color**

Quando non specifichiamo nulla, lo sfondo della pagina è **bianco** perché il browser applica i suoi stili di default (**user agent stylesheet**).

Possiamo cambiarlo semplicemente così:

```css
body {
    background-color: lightblue;
}
```

`background-color` accetta **tutti i tipi di valori colore**:

- nomi (`red`, `blue`, `black`)
    
- HEX (`#ff6600`)
    
- RGB / RGBA
    
- HSL / HSLA
    
- LCH
    
- Lab
    
- `transparent`
    
- `var()` per usare variabili CSS
    

`background-color` modifica _solo e soltanto_ il **colore** dello sfondo.

È semplice, diretto, preciso.

---

### **3. Background (Shorthand)**

Se invece usiamo solo:

```css
body {
    background: red;
}
```

Il risultato è identico, perché un colore è un tipo valido di background.  
Ma la differenza è fondamentale:  
`background` è una **shorthand property**, cioè un contenitore che può includere:

- colore
    
- immagini
    
- gradienti
    
- posizione
    
- ripetizione
    
- dimensioni
    
- attachment
    
- più immagini una sopra l’altra
    

Quando inizi a digitare in Visual Studio Code:

```css
background:
```

vedrai che non propone solo colori, ma anche:

- `linear-gradient()`
    
- `repeating-linear-gradient()`
    
- `radial-gradient()`
    
- `repeating-radial-gradient()`
    
- e tutte le forme più complesse di background
    

Il motivo per cui `background` è così usato è che ti permette di assegnare con un'unica proprietà cose come:

```css
background: linear-gradient(...), url(...), #000;
```

oppure un fallback:

```css
background: #0d1b2a;
background: linear-gradient(...);
```

---

### **4. Gradienti: perché usare un generatore**

Scrivere gradienti a mano è possibile, ma non conviene.

Perché?

- sono visivi, non testuali
    
- ricordare posizioni, percentuali, direzioni è inutile
    
- i generatori sono fatti apposta
    
- vedere il risultato in anteprima è 100 volte più comodo
    

Esempio di generatore: **cssgradient.io**, **uigradients**, **meshgradient.com**

Con questi strumenti puoi:

- cambiare i colori trascinandoli
    
- cambiare l’angolo
    
- cambiare la posizione
    
- aggiungere o rimuovere punti colore
    
- vedere subito il risultato finale
    

Poi copi il codice e lo incolli nel CSS.  
È esattamente quello che fanno i professionisti.

---

### **5. Linea del codice del gradiente (Linear Gradient)**

Copiando dal generatore, ottieni qualcosa del genere:

```css
background: linear-gradient(95deg,
    rgba(23,32,48,1) 0%,
    rgba(27,52,94,1) 36%,
    rgba(27,82,149,1) 100%
);
```

Ora sai leggerlo:

- `linear-gradient(...)` è una **funzione**
    
- `95deg` = direzione del gradiente
    
- ogni colore ha una **posizione**:
    
    - `0%` inizia
        
    - `36%` punto intermedio
        
    - `100%` fine
        

I colori possono essere:

- HEX
    
- RGB / RGBA
    
- HSL
    
- LCH
    
- Lab
    

---

### **6. Colore di fallback**

È buona norma impostare **prima un colore semplice**, poi il gradiente:

```css
background-color: #123456;
background: linear-gradient(...);
```

Se per qualche ragione il gradiente non viene renderizzato, il sito non diventa bianco:  
rimane il colore di fallback.

---

### **7. Mesh Gradients (i gradienti complessi)**

I **mesh gradients** sono gradienti evoluti formati da tanti cerchi di colore posizionati in punti diversi.

Sono molto usati nel web design moderno perché creano sfondi:

- fluidi
    
- organici
    
- tridimensionali
    
- artistici
    

Nessuno scrive a mano un mesh gradient.  
La sintassi è **lunghissima** e piena di `radial-gradient()` annidati.

Esempio reale:

```css
background-image:
    radial-gradient(...),
    radial-gradient(...),
    radial-gradient(...),
    ...ecc...
```

Ogni `radial-gradient()` rappresenta **una macchia di colore** con:

- una posizione
    
- un raggio
    
- un colore
    
- un livello di trasparenza
    

Risultato: sfumature creative e molto complesse.

Ancora una volta: **usa i generatori**.

---

### **8. Background-image per i gradienti multipli**

I mesh gradient generano **molte immagini di sfondo sovrapposte**, quindi usano:

```css
background-image:
    radial-gradient(...),
    radial-gradient(...),
    radial-gradient(...);
```

Il browser li combina uno sopra l’altro.  
Questo è il motivo per cui l’effetto sembra “pittorico”.

---

### **9. Cosa serve davvero sapere**

Per ora devi solo sapere:

- `background-color` → un colore semplice
    
- `background` → versione abbreviata che può contenere tutto
    
- `linear-gradient()` → sfumatura in una direzione
    
- `radial-gradient()` → sfumatura dal centro verso l’esterno
    
- `mesh gradient` → sfumatura complessa fatta con tanti `radial-gradient()`
    
- usa i generatori
    
- inserisci sempre un fallback
    

Niente esercizi a mano sui gradienti: **non ne vale la pena**.

---

### **10. Conclusione**

Ora hai un quadro chiaro di come funzionano i background e i gradienti.  
Queste sono basi essenziali per:

- creare layout professionali
    
- dare personalità ai tuoi siti
    
- gestire colori, sfondi, effetti visivi
    
- integrare il design con il resto della pagina
    

---

### **11. Prossima Lezione**

Nella prossima lezione parliamo del tema più importante quando si lavora coi colori:

👉 **Contrasto tra colori, accessibilità e leggibilità: come scegliere i colori giusti.**