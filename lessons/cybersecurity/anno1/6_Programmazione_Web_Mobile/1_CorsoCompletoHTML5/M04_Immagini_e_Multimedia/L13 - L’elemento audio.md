## **Lezione 13 : L’elemento `<audio>`**

### **1. Introduzione**

L’elemento `<audio>` è la controparte sonora del tag `<video>`.  
È estremamente semplice: serve per riprodurre **effetti sonori**, **musica**, **podcast**, o qualunque contenuto audio direttamente nella pagina.

Non ha attributi complessi come poster, width o height, e la sua struttura è molto più lineare rispetto al video.

---

### **2. Inserire un file audio**

Supponiamo di aver scaricato un file `.wav` da Mixkit (o simili) e di averlo inserito nella cartella del progetto.

Uno scheletro minimo dell’elemento è:

```html
<audio>
    <source src="/audio/effect.wav" type="audio/wav">
</audio>
```

Ma così non comparirà niente nella pagina: per rendere l’audio controllabile serve l’attributo `controls`.

---

### **3. L’attributo `controls`**

Attivandolo, compare il player audio nativo del browser:

```html
<audio controls>
    <source src="/audio/effect.wav" type="audio/wav">
</audio>
```

Il player generalmente permette:

- Play/pausa
    
- Volume
    
- Velocità di riproduzione
    
- Download del file (dipende dal browser)
    

È molto più semplice del pannello dei video, perché l’audio non ha contenuto visivo.

---

### **4. Compatibilità tra formati e uso di `<source>`**

Esattamente come con `<video>`, anche l’audio può contenere **più `<source>`**, per supportare formati diversi.

Formati audio comuni sul web:

- `wav`
    
- `mp3` (il più compatibile in assoluto)
    
- `ogg` (open-source)
    

Esempio con fallback multiplo:

```html
<audio controls>
    <source src="/audio/effect.wav" type="audio/wav">
    <source src="/audio/effect.mp3" type="audio/mpeg">
    Il tuo browser non supporta l’audio HTML5.
</audio>
```

Logica:

1. Il browser prova `wav`.
    
2. Se non lo supporta → prova `mp3`.
    
3. Se non supporta nulla → mostra il testo di fallback.
    

Il meccanismo è identico a `<video>` e `<picture>`: il browser sceglie _una sola_ risorsa valida.

---

### **5. Fallback testuale**

Anche qui il fallback non può essere un’immagine o un’altra alternativa multimediale.  
È semplicemente testo:

```html
Il tuo browser non supporta l’audio HTML5.
```

Puoi anche aggiungere un link:

```html
<a href="/audio/effect.wav">Scarica il file</a>
```

Questo è utile quando devi garantire accessibilità anche a dispositivi molto vecchi.

---

### **6. Attributi utili dell’audio**

L’elemento è molto più limitato rispetto al video.

Gli attributi principali sono:

- **controls** → mostra il player
    
- **autoplay** → riproduce automaticamente (spesso richiede `muted`, ma l’audio mutato non ha senso)
    
- **loop** → ripete il file all’infinito
    
- **muted** → avvia l’audio muto (molto raro)
    
- **preload** → come nei video: `auto`, `metadata`, `none`
    

Esempio completo:

```html
<audio controls autoplay loop preload="metadata">
    <source src="/audio/effect.mp3" type="audio/mpeg">
    <source src="/audio/effect.wav" type="audio/wav">
    Il tuo browser non supporta l’audio HTML5.
</audio>
```

---

### **7. Differenze principali rispetto a `<video>`**

|Funzionalità|`<video>`|`<audio>`|
|---|---|---|
|Dimensioni (`width`, `height`)|✔️|❌|
|Poster (copertina)|✔️|❌|
|Controlli avanzati|✔️|essenziali|
|Fallback|testo|testo|
|Formati multipli con `<source>`|✔️|✔️|

L’audio è quindi molto più essenziale e immediato da usare.

---

### **8. Quando usare realmente l’elemento audio**

Casi d’uso tipici:

- effetti sonori nei siti (pagine portfolio, microinterazioni)
    
- podcast o audio lezioni
    
- player personalizzati con JavaScript
    
- musica di sottofondo _solo_ se sensata e non invasiva
    
- notifiche in app web (assieme a JS)
    

Su siti moderni, l’audio è spesso integrato con JavaScript per migliorare l’esperienza utente.

---

### **9. Conclusione**

L’elemento `<audio>` è semplice, lineare e poco problematico.  
Ti permette di includere file sonori in modo nativo, con:

- compatibilità multi-formato
    
- controlli automatici del browser
    
- fallback testuale
    
- nessuna complessità grafica
    

È la soluzione più diretta per gestire audio in una pagina HTML.
