## **Lezione 16 : L’elemento `<iframe>`**

### **1. Introduzione**

L’elemento `<iframe>` permette di **incorporare un’intera pagina web dentro un’altra pagina web**.  
È uno degli elementi più antichi dell’HTML (anni ’90 – primi 2000), quando molti siti erano strutturati proprio come un mosaico di pagine caricate all’interno di un unico contenitore.

Oggi l’uso degli iframe è **molto ridotto**, perché:

- il web moderno privilegia contenuti integrati: immagini, video, audio
    
- l’inclusione di pagine esterne è considerata rischiosa a livello di sicurezza
    
- molte funzionalità possono essere ottenute con API e JavaScript
    

Tuttavia, ci sono **due casi moderni e molto comuni** in cui gli iframe vengono ancora utilizzati:

1. Incorporare video da YouTube, Vimeo e altre piattaforme simili.
    
2. Incorporare mappe interattive, come Google Maps.
    

---

### **2. Come funziona un iframe**

Un iframe è una **finestra verso un’altra pagina web**.  
All’interno della nostra pagina, il browser carica un altro sito, completo di:

- `<!DOCTYPE html>`
    
- `<html>`, `<head>`, `<body>`
    
- script, stili, contenuti
    
- interattività propria del sito incorporato
    

HTML dell’iframe:

```html
<iframe
    src="https://www.google.com/maps/embed?..."
    width="500"
    height="350">
</iframe>
```

Significa letteralmente:

> “Mostra questa pagina web, dentro la mia pagina, in un riquadro 500 × 350.”

Se apri l’iframe con "Ispeziona", vedrai l’intera struttura del sito incorporato.

---

### **3. Gli attributi fondamentali**

#### **3.1. `src`**

È l’URL della pagina esterna da caricare.

```html
src="https://www.google.com/maps/embed?..."
```

#### **3.2. `width` e `height`**

Impostano le dimensioni dell’iframe:

```html
width="500"
height="350"
```

#### **3.3. `title`**

Fondamentale per accessibilità e screen reader:

```html
title="Google Maps di New York"
```

---

### **4. Il concetto di “incorporare”**

Quando esegui un embedded di Google Maps:

- apri Google Maps
    
- scegli “Condividi” → “Incorpora mappa”
    
- ottieni un codice `<iframe>`
    

Google genera automaticamente un URL speciale che permette alla mappa di funzionare all’interno della tua pagina.

Lo stesso avviene con YouTube, Vimeo, Spotify, ecc.

---

### **5. Fallback (molto raro ma formalmente necessario)**

Tutti i browser moderni supportano gli iframe, quindi il fallback è quasi sempre inutile.  
Ma per completezza, puoi scriverlo:

```html
<iframe src="..." width="500" height="350">
    <p>Il tuo browser non supporta gli iframe.</p>
</iframe>
```

Il contenuto entro il tag verrà mostrato solo in caso di browser antichissimi.

---

### **6. Sicurezza degli iframe**

L’iframe è, tecnicamente, **una “finestra” da un sito esterno dentro il tuo sito**.

Per questo, storicamente, è stato un vettore di attacchi:

- cattura di dati sensibili
    
- registrazione dei comportamenti dell’utente
    
- esecuzione di script non autorizzati
    
- phishing visivo
    
- overlay invisibili cliccabili
    

I browser moderni hanno introdotto molte limitazioni per impedire abusi, ma la regola rimane:

> **Incorpora SOLO contenuti completamente affidabili.**

Siti considerati sicuri:

- Google
    
- YouTube
    
- Facebook
    
- Vimeo
    
- piattaforme certificate o estremamente popolari
    

Evita assolutamente:

- siti sconosciuti
    
- contenuti non verificati
    
- servizi non HTTPS
    
- domini sospetti
    

---

### **7. L’attributo `sandbox`**

Se devi incorporare un contenuto non garantito al 100%, usa il sandbox:

```html
<iframe src="..." sandbox></iframe>
```

Vuoto significa:

- blocca script
    
- blocca pop-up
    
- blocca navigazione esterna
    
- impedisce molte interazioni rischiose
    

Puoi anche attivare permessi specifici:

```html
sandbox="allow-popups allow-scripts"
```

Ma **solo** se sai esattamente cosa stai facendo.

In pratica:

- contenuto ultra-affidabile → niente sandbox
    
- contenuto dubitabile → sandbox sempre attivo
    

Questo crea un ambiente “blindato” simile ad una modalità protetta.

---

### **8. Altri attributi che potresti vedere**

#### **8.1. `loading="lazy"`**

Tenta il caricamento “pigro”: carica l’iframe solo quando sta per entrare in viewport.

Alcuni browser non lo supportano ancora (Firefox, Safari in certi contesti).

#### **8.2. `referrerpolicy`**

Controlla quali dati vengono inviati al sito incorporato quando l’utente visualizza l’iframe.  
È un attributo avanzato, utile per privacy e sicurezza, ma fuori dallo scopo di questa lezione.

---

### **9. Esempio completo**

```html
<iframe
    src="https://www.google.com/maps/embed?pb=..."
    width="500"
    height="350"
    title="Google Maps di New York"
    loading="lazy"
>
    <p>Il tuo browser non supporta gli iframe.</p>
</iframe>
```

---

### **10. Un avvertimento importante**

Se togli il foglio di stile che elimina il bordo, molti iframe mostreranno un bordo predefinito molto brutto.  
Google Maps genera già regole CSS inline che lo rimuovono. Puoi esternalizzare quelle regole in un file CSS, ma la logica non cambia.

---

### **11. Conclusione**

L’elemento `<iframe>`:

- incorpora un’intera pagina web dentro un’altra
    
- è usato quasi esclusivamente per servizi esterni famosi (YouTube, Google Maps, ecc.)
    
- ha implicazioni di sicurezza **molto importanti**
    
- può essere protetto con `sandbox`
    
- richiede un attributo `title` per l’accessibilità
    
- può usare `loading="lazy"` per migliorare le prestazioni
    

Nella prossima lezione si vedrà come incorporare **un video di YouTube** usando esattamente questo meccanismo.