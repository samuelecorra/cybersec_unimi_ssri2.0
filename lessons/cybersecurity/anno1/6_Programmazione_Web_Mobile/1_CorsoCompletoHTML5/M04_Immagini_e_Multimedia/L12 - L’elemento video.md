## **Lezione 12: L’elemento `<video>`**

### **1. Introduzione**

L’elemento `<video>` è estremamente diretto: permette di incorporare un file video all’interno di una pagina HTML. Tuttavia, come per le immagini, esistono attributi importanti che controllano comportamento, compatibilità e accessibilità.  
Questa lezione sistematizza tutto ciò che serve per usare i video in maniera corretta e professionale.

---

### **2. Inserire un video**

Supponiamo di aver scaricato un video da PixaBay e di averlo salvato nella cartella `/immagini` con nome `rana-video.mp4`.

L’uso più semplice dell’elemento è:

```html
<video src="/immagini/rana-video.mp4"></video>
```

Questo mostra il video, ma:

- è troppo grande (se l’hai scaricato in alta risoluzione)
    
- non ha controlli
    
- non hai alcun controllo su autoplay, loop, audio, ecc.
    

---

### **3. Attributi fondamentali**

#### **3.1. Dimensioni: `width` e `height`**

Possiamo ridimensionare il video come un’immagine:

```html
<video src="/immagini/rana-video.mp4" width="300" height="169"></video>
```

Questi valori vanno scelti coerentemente con il formato originale.

---

#### **3.2. Controlli: `controls`**

L’attributo `controls` abilita:

- play/pausa
    
- volume
    
- modalità schermo intero
    
- velocità
    
- download
    
- Picture-in-picture (quando supportato)
    

```html
<video src="/immagini/rana-video.mp4" controls></video>
```

---

### **4. Fallback per browser non compatibili**

L’elemento `<video>` è “relativamente nuovo”: non tutti i browser molto vecchi lo supportano.  
Quando un browser _non_ supporta l’elemento, tutto ciò che è scritto **dentro** `<video>` ma fuori dai tag diventa il fallback.

```html
<video src="/immagini/rana-video.mp4" controls>
    Il tuo browser non supporta i video HTML5.
</video>
```

Puoi anche aggiungere un link alternativo:

```html
<video src="/immagini/rana-video.mp4" controls>
    Il tuo browser non supporta i video HTML5.  
    <a href="https://youtube.com/...">Guarda il video qui</a>.
</video>
```

---

### **5. Usare più formati (`<source>`)**

Esattamente come per l’elemento `<picture>`, anche `<video>` può contenere più `<source>`.  
Questo serve perché i browser non supportano tutti gli stessi codec e formati.

Per esempio:

```html
<video controls>
    <source src="/immagini/rana-video.webm" type="video/webm">
    <source src="/immagini/rana-video.mp4" type="video/mp4">
    Il tuo browser non supporta i video HTML5.
</video>
```

Logica del browser:

1. Prova `webm`
    
2. Se non lo supporta → prova `mp4`
    
3. Se non supporta nessuno dei due → mostra il fallback testuale
    

Questa logica è identica a quella di `<picture>`, ma con un fallback differente (non un `<img>`, bensì del testo).

---

### **6. Attributi aggiuntivi dei video**

#### **6.1. `autoplay`**

Fa partire il video automaticamente:

```html
<video src="/immagini/rana-video.mp4" autoplay></video>
```

Attenzione: molti browser richiedono anche **muted** per consentirlo (vedi sotto).

---

#### **6.2. `loop`**

Riproduce il video all’infinito:

```html
<video src="/immagini/rana-video.mp4" autoplay loop></video>
```

Utile per banner animati o sfondi video.

---

#### **6.3. `muted`**

Avvia il video senza audio.  
I browser moderni **di solito bloccano autoplay se il video non è muted**, per evitare fastidi all’utente.

```html
<video src="/immagini/rana-video.mp4" autoplay loop muted></video>
```

Con `muted` + `autoplay` puoi creare lo sfondo video tipico delle landing page.

---

#### **6.4. `poster`**

Imposta l’immagine di copertina (thumbnail), visibile prima della riproduzione:

```html
<video
    src="/immagini/rana-video.mp4"
    controls
    poster="/immagini/montagna-in-lontananza.jpg">
</video>
```

È identico al concetto di "copertina" dei video YouTube.

Se dimensioni e proporzioni sono coerenti con il video, il risultato è pulito e professionale.

---

### **7. Attributo `preload`**

`preload` indica **quanto** il browser deve precaricare il video prima della riproduzione.

Tre valori:

- `none` → non carica nulla prima del click
    
- `metadata` → carica solo dimensioni/durata
    
- `auto` → decide il browser, spesso carica tutto
    

Esempio:

```html
<video src="/immagini/rana-video.mp4" controls preload="metadata"></video>
```

È un attributo che diventa davvero importante **solo** in contesti più avanzati (prestazioni, JavaScript, analytics), quindi per ora è sufficiente sapere che:

> `preload` controlla quanto il browser “prepara” il video prima che l’utente interagisca.

---

### **8. Esempio completo**

```html
<video
    width="300"
    height="169"
    autoplay
    loop
    muted
    poster="/immagini/copertina.jpg"
    controls
>
    <source src="/immagini/rana-video.webm" type="video/webm">
    <source src="/immagini/rana-video.mp4" type="video/mp4">
    Il tuo browser non supporta i video HTML5.
</video>
```

---

### **9. Quando userai davvero i video?**

Situazioni tipiche:

- **video-banner** come sfondo dinamico di un hero header
    
- **presentazioni di prodotto** (autoplay, muted)
    
- **contenuti multimediali** con controlli (tutorial, lezioni, dimostrazioni)
    
- **gallerie animate** in sostituzione delle GIF (MP4 è infinitamente più efficiente)
    

Ricorda: MP4 è il formato più compatibile e più usato sul web.