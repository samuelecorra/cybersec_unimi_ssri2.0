## **Lezione 14 : Sottotitoli nei video HTML con `<track>`**

### **1. Introduzione**

Abbiamo visto come inserire video e come controllarli, ma manca una parte fondamentale per l’**accessibilità** e per l’**internazionalizzazione**: i **sottotitoli**.  
I sottotitoli servono a:

- utenti che non possono sentire l’audio
    
- utenti che non capiscono la lingua originale
    
- chi guarda il video in ambienti silenziosi / rumorosi
    
- migliorare la fruibilità generale del contenuto
    

Per inserirli, HTML usa l’elemento `<track>` all’interno del tag `<video>`.

---

### **2. Ripartiamo dal video di base**

Creiamo un video minimale su cui aggiungere i sottotitoli:

```html
<video width="300" controls>
    <source src="/immagini/rana-video.mp4" type="video/mp4">
</video>
```

Questo sarà il nostro punto di partenza.

---

### **3. L’elemento `<track>`**

L’elemento `<track>` rappresenta una traccia testuale associata al video.

Può mostrare:

- sottotitoli
    
- trascrizioni
    
- didascalie per l’accessibilità
    
- descrizioni audio
    
- metadati
    

Per i **sottotitoli standard** ci interessa:

- `kind="subtitles"`
    
- `src` → il file `.vtt`
    
- `srclang` → lingua (es: `it`, `es`, `en`)
    
- `label` → nome mostrato nel selettore del browser
    

Esempio base:

```html
<track
    kind="subtitles"
    src="/sottotitoli-es.vtt"
    srclang="es"
    label="Spanish">
```

---

### **4. Creazione del file VTT**

I sottotitoli non si scrivono direttamente nell’HTML: devono essere salvati in un file `.vtt` (WebVTT).

Procediamo creando un file chiamato `sottotitoli-es.vtt`.

Il formato WebVTT è molto semplice:

1. Prima riga: l’intestazione `WEBVTT`
    
2. Poi una lista di sottotitoli numerati
    
3. Ogni sottotitolo ha:
    
    - intervallo temporale
        
    - contenuto testuale
        
    - una riga di separazione tra uno e l’altro
        

---

### **5. Struttura di un file `.vtt`**

Esempio minimale:

```
WEBVTT

1
00:00:02.000 --> 00:00:06.000
Ecco il primo sottotitolo

2
00:00:06.000 --> 00:00:14.000
Ecco il secondo sottotitolo
```

I tempi sono nel formato:

```
HH:MM:SS.mmm --> HH:MM:SS.mmm
```

Con precisione al millisecondo.

---

### **6. Collegare i sottotitoli al video**

Mettiamo tutto insieme:

```html
<video width="300" controls>
    <source src="/immagini/rana-video.mp4" type="video/mp4">

    <track
        kind="subtitles"
        src="/sottotitoli-es.vtt"
        srclang="es"
        label="Spanish">

    <!-- Fallback -->
    Il tuo browser non supporta i video HTML5.
</video>
```

A questo punto il browser mostrerà l’icona dei sottotitoli e permetterà di attivarli.

---

### **7. Aggiungere più lingue**

Supponiamo di voler aggiungere i sottotitoli anche in italiano.

1. Copiamo il file `.vtt`
    
2. Lo rinominiamo come `sottotitoli-it.vtt`
    
3. Cambiamo i testi all’interno
    
4. Aggiungiamo una nuova `<track>`
    

Esempio:

```html
<video width="300" controls>
    <source src="/immagini/rana-video.mp4" type="video/mp4">

    <track
        kind="subtitles"
        src="/sottotitoli-es.vtt"
        srclang="es"
        label="Spanish">

    <track
        kind="subtitles"
        src="/sottotitoli-it.vtt"
        srclang="it"
        label="Italiano">
</video>
```

Ora, cliccando sull’icona dei sottotitoli, puoi selezionare:

- Spanish
    
- Italiano
    

Il browser attiverà la traccia scelta.

---

### **8. Perché servono `srclang` e `label`**

#### **`srclang`**

Serve al browser. Indica la lingua effettiva dei sottotitoli (`it`, `es`, `en`, …).

#### **`label`**

Serve allo **screen reader** e all’utente umano: è il nome mostrato nel menu.

Esempio:

```html
srclang="es"  → per il browser
label="Spanish" → ciò che l’utente legge
```

---

### **9. Comportamento di default**

- Se non specifichi nessun `<track>`, il video non avrà sottotitoli.
    
- Se specifichi una sola traccia, comparirà automaticamente l’opzione associata.
    
- Se ci sono più tracce, il browser mostrerà un piccolo menu di scelta.
    
- Il browser **non attiva automaticamente** i sottotitoli: l’utente sceglie.
    

---

### **10. File WebVTT più complessi**

WebVTT può supportare:

- stili CSS per sottotitoli
    
- posizionamento
    
- linee multiple
    
- cue per karaoke
    
- descrizioni audio
    
- capitoli
    

Ma per uso standard, bastano:

- la riga `WEBVTT`
    
- le tempistiche
    
- il testo
    

Ed è esattamente quanto serve per la quasi totalità dei siti web.

---

### **11. Esempio finale completo**

```html
<video width="300" controls>
    <source src="/immagini/rana-video.mp4" type="video/mp4">

    <track
        kind="subtitles"
        src="/sottotitoli-es.vtt"
        srclang="es"
        label="Spanish">

    <track
        kind="subtitles"
        src="/sottotitoli-it.vtt"
        srclang="it"
        label="Italiano">

    Il tuo browser non supporta i video HTML5.
</video>
```

---

### **12. Conclusione**

- I sottotitoli usano `<track>` con `kind="subtitles"`.
    
- I file sono in formato `.vtt` (WebVTT).
    
- Ogni traccia deve avere **src**, **srclang**, **label**.
    
- Puoi aggiungere più lingue duplicando file e tag.
    
- È una funzionalità fondamentale per l’accessibilità e per utenti stranieri.
    
- Il browser permette all’utente di scegliere la lingua al volo.
    
