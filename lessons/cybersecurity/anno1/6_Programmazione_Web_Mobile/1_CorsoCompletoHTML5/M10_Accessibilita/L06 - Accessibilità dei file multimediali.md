## **Lezione 6: Accessibilità dei file multimediali**

### **1. Perché ripassiamo proprio ora**

Le cose che vediamo in questa lezione **le hai già incontrate**:  
immagini con `alt`, audio con trascrizione, video con `track`.

Qui però le mettiamo **tutte insieme** dentro il capitolo “Accessibilità”, così smettono di essere dettagli sparsi e diventano:

> un piccolo **set di regole** da applicare ogni volta che usi contenuti multimediali.

Dividiamo tutto in tre blocchi:

- **immagini**
    
- **audio**
    
- **video**
    

---

### **2. Immagini: l’`alt` non è opzionale**

Per le immagini, l’accessibilità si gioca su un solo punto fondamentale:

```html
<img src="immagini/prodotto.jpg" alt="Scarpa da running rossa con suola bianca" />
```

Se l’immagine **ha contenuto informativo**, il testo alternativo `alt` è **obbligatorio**:

- descrive ciò che si vede
    
- permette allo screen reader di “raccontarla”
    
- sostituisce l’immagine quando non viene caricata
    

Se l’immagine è **puramente decorativa**, ci sono due strade:

1. `alt=""` (vuoto) → lo screen reader la salta
    
2. oppure, ancora meglio, combini anche `aria-hidden="true"` se vuoi che venga ignorata completamente:
    

```html
<img src="immagini/decorazione.svg" alt="" aria-hidden="true" />
```

---

### **3. Audio: mai senza trascrizione**

Per l’audio il concetto è semplice:

> se il contenuto è solo audio, **deve esistere una versione testuale equivalente**.

Esempio: pagina di un podcast ben fatta:

- player audio in alto
    
- sotto **trascrizione completa** dell’episodio
    

La trascrizione:

- permette a chi **non sente** di fruire lo stesso del contenuto
    
- aiuta anche chi non può ascoltare (es. in ufficio, in treno, ecc.)
    
- migliora persino il SEO (Google indicizza il testo)
    

Struttura ideale:

- indicare **chi parla**
    
- indicare **quando** (minuto:secondo)
    
- testo ben spezzato in paragrafi, non un muro compatto
    

Esempio schematizzato:

```html
<article>
  <h1>Episodio 12 – Titolo del podcast</h1>

  <audio controls src="audio/episodio12.mp3"></audio>

  <h2>Trascrizione</h2>

  <p><strong>[00:00 – Edoardo]</strong> Benvenuti in questa puntata...</p>
  <p><strong>[02:15 – Ospite]</strong> Grazie per l'invito, oggi parleremo di...</p>
</article>
```

Molti siti fanno trascrizioni “buttate lì”, un blocco unico di testo.  
Se vuoi fare un lavoro davvero accessibile, **tratta la trascrizione come una parte vera e propria del contenuto**:

- paragrafi
    
- grassetti
    
- divisione per interventi
    
- leggibilità
    

---

### **4. Video: il cuore dell’accessibilità sono le `track`**

Per i video usiamo l’elemento:

```html
<video controls height="300">
  <source src="video/rana.mp4" type="video/mp4" />
</video>
```

La parte accessibile si costruisce con i `track`:

```html
<video controls height="300">
  <source src="video/rana.mp4" type="video/mp4" />

  <track kind="subtitles" src="sottotitoli-it.vtt" srclang="it" label="Italiano" />
  <track kind="subtitles" src="sottotitoli-en.vtt" srclang="en" label="English" />
</video>
```

Vediamo i **quattro kind importanti**, legati all’accessibilità:

- `subtitles`
    
- `descriptions`
    
- `captions`
    
- `chapters`
    

---

### **4.1. `subtitles`: i sottotitoli “classici”**

`kind="subtitles"` serve per chi:

- **sente bene**,
    
- ma **non capisce la lingua originale** del video.
    

Esempio:

```html
<track kind="subtitles" src="sub-en.vtt" srclang="en" label="English" />
<track kind="subtitles" src="sub-es.vtt" srclang="es" label="Español" />
```

Dal player l’utente può scegliere:

- nessun sottotitolo
    
- sottotitoli in inglese
    
- sottotitoli in spagnolo, ecc.
    

---

### **4.2. `descriptions`: descrizione per chi non vede**

`kind="descriptions"` è per chi **non vede il video**, ma può ascoltare.

Qui la traccia non si limita a scrivere i dialoghi, ma **descrive ciò che succede visivamente**:

- ambienti
    
- cambi di scena
    
- azioni importanti
    
- elementi che si vedono ma non si dicono
    

Esempio:

```html
<track kind="descriptions" src="descrizione-it.vtt" srclang="it" label="Descrizione audio (IT)" />
```

Nel file `.vtt` trovi cose del tipo:

```text
00:00:00.000 --> 00:00:04.000
[Scena] Una rana verde salta da una foglia all'altra sopra uno stagno.

00:00:04.000 --> 00:00:08.000
[Scena] Inizia a piovere leggermente, si vedono le gocce sull'acqua.
```

Gli screen reader leggono questa traccia mentre il video va.

---

### **4.3. `captions`: per chi non sente nella _stessa lingua_**

`kind="captions"` serve per persone **non udenti**, che però conoscono la lingua del video.

La differenza dalla traccia `subtitles` è che i captions includono anche:

- chi sta parlando
    
- suoni rilevanti (musica, rumori, ecc.)
    
- informazioni extra che un udente percepirebbe comunque
    

Esempio:

```html
<track kind="captions" src="captions-it.vtt" srclang="it" label="Italiano (CC)" />
```

Nel file `.vtt` puoi trovare robe tipo:

```text
00:00:00.000 --> 00:00:03.000
[Edoardo] Benvenuti nella puntata di oggi.

00:00:03.000 --> 00:00:06.000
[Musica drammatica in sottofondo]

00:00:06.000 --> 00:00:09.000
[Voce narratore] Nel video si vede una città al tramonto.
```

Nel player HTML5, di solito i captions appaiono con la sigla **“CC”**.

---

### **4.4. `chapters`: capitoli del video**

`kind="chapters"` permette di definire **capitoli interni al video**:

```html
<track kind="chapters" src="chapters.vtt" srclang="it" label="Capitoli" />
```

Il file `chapters.vtt` conterrà cose del tipo:

```text
00:00:00.000 --> 00:02:00.000
Introduzione

00:02:00.000 --> 00:10:00.000
Parte 1 – Setup del progetto

00:10:00.000 --> 00:20:00.000
Parte 2 – Implementazione
```

⚠ Importante:  
il player video nativo **non mostra automaticamente un’interfaccia per i capitoli**.  
HTML5 li supporta, ma **sei tu** che, con JavaScript, dovrai:

- leggere il file dei capitoli
    
- costruire un elenco cliccabile (una sorta di indice del video)
    
- far saltare il video al punto giusto
    

Questa è una cosa che vedrai meglio quando avrai in mano **JavaScript**.

---

### **5. Riepilogo operativo veloce**

Quando usi **immagini**:

- metti sempre `alt`
    
- se è solo decorativa: `alt=""` e volendo `aria-hidden="true"`
    

Quando usi **audio**:

- prevedi una **trascrizione testuale** nella pagina
    
- se vuoi fare le cose fatte bene, curane anche la formattazione
    

Quando usi **video**:

- aggiungi `track` almeno per:
    
    - `subtitles` (per altre lingue)
        
    - `captions` (per non udenti, stessa lingua)
        
- valuta `descriptions` per contenuti importanti
    
- usa `chapters` se il video è lungo e strutturato in parti
    

---

### **6. Prossimo passo: accessibilità da mobile**

Abbiamo chiuso il blocco dei contenuti multimediali dal punto di vista dell’accessibilità.  
Nel passo successivo andiamo a vedere **l’accessibilità da mobile**, dove entrano in gioco:

- tap
    
- dimensioni dei target
    
- viewport
    
- zoom
    
- layout e leggibilità su schermi piccoli.