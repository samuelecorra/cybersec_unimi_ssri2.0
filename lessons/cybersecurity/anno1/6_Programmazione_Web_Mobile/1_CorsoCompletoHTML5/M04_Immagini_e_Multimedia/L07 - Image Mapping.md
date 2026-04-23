## **Lezione 7: Image Mapping — Rendere “cliccabili” zone interne di un’immagine**

### 1. Perché esiste il mapping delle immagini

L’**image mapping** permette di prendere una singola immagine e suddividerla in “zone” cliccabili, ognuna con un proprio link, tooltip o comportamento.

Esempi tipici:

- Una **cartina geografica** in cui clicchi sulle regioni.
    
- Una **foto di una stanza** dove clicchi sugli oggetti.
    
- Un **catalogo prodotti** in cui clicchi sui vestiti indossati da un modello.
    
- Un **poster interattivo** dove parti diverse portano a pagine diverse.
    

Oggi si usa molto raramente (meno dell’1% dei casi), ma è comunque parte dello standard HTML e devi conoscerlo.

---

### 2. Come funziona l’image mapping a livello concettuale

Serve:

1. **Un’immagine** `<img>` che “punta” a una mappa tramite l’attributo `usemap`.
    
2. **Una mappa** `<map>` che contiene una o più aree cliccabili.
    
3. **Delle aree** `<area>` che definiscono:
    
    - forma (circle, rect, poly…),
        
    - coordinate,
        
    - link (`href`),
        
    - tooltip (`title`),
        
    - `alt`,
        
    - proprietà di sicurezza come `rel="noopener"`.
        

HTML non fa nulla di “magico”: disegna la tua immagine e poi _sovrappone_ una serie di coordinate sensibili che diventano link.

---

### 3. Il primo passo: legare immagine e mappa

Esempio:

```html
<img src="immagini/prova.webp" alt="Esempio" usemap="#mappa">
```

Qui succede una cosa fondamentale:

- `usemap="#mappa"` dice all’immagine:  
    **“Utilizza la mappa chiamata mappa”**.
    

Quell’`#` indica un identificatore, come vedrai meglio in CSS.

---

### 4. Creare la mappa con `<map>`

Sotto all’immagine si scrive:

```html
<map name="mappa">
    <!-- Aree cliccabili qui dentro -->
</map>
```

Attenzione importante:

> Per le mappe si usa l’attributo **name**, non `id`.  
> È un’eccezione storica dello standard HTML.

---

### 5. Creare un’area cliccabile: `<area>`

Un’area è un elemento _vuoto_ (non ha tag di chiusura).

Esempio di cerchio cliccabile:

```html
<area
    shape="circle"
    coords="75,75,75"
    href="https://google.com"
    target="_blank"
    rel="noopener"
    alt="Google"
    title="Vai su Google"
/>
```

Vediamo ogni attributo:

- **shape**
    
    - `circle`
        
    - `rect`
        
    - `poly`
        
    - (forme complesse)
        
- **coords**  
    Dipende dalla shape:
    
    - cerchio → `x, y, raggio`
        
    - rettangolo → `x1, y1, x2, y2`
        
    - poligono → lista di coordinate `x,y,x,y,x,y,…`
        
- **href**  
    Link a cui andare.
    
- **target="_blank"**  
    Apre in nuova scheda.
    
- **rel="noopener"**  
    Necessario per sicurezza quando si usa `_blank`.  
    Previene attacchi tramite `window.opener`.
    
- **alt**  
    Alternativa testuale (per screen reader).
    
- **title**  
    Tooltip che appare passando sopra.
    

---

### 6. Lavorare “a mano” è difficile: perché?

Il problema principale è questo:

> Le coordinate di `<area>` sono **numeri assoluti** basati sulle dimensioni reali dell’immagine.

Quindi:

- se l’immagine è larga 300px e la allarghi a 600px,
    
- _tutte le coordinate diventano sbagliate_.
    

Il browser non le ridimensiona automaticamente.

Per questo, mappare a mano è laborioso.

---

### 7. Utilizzare tool online per mappare un’immagine

Fortunatamente esistono generatori:

- carichi l’immagine,
    
- trascini rettangoli/cerchi/poligoni,
    
- imposti link e title,
    
- premi “Show me the code”.
    

Il tool produce direttamente:

```html
<map name="mappa">
    <area shape="rect" coords="12,45,90,130" href="https://google.com" target="_blank" title="Google">
    <area shape="circle" coords="150,80,40" href="https://facebook.com" target="_blank" title="Facebook">
</map>
```

Tu devi:

- incollarlo sotto l’immagine,
    
- aggiungere **rel="noopener"** a ogni link per sicurezza.
    

---

### 8. Un esempio completo e funzionante

```html
<img src="immagini/prova.webp" alt="Mappa esempio" usemap="#mappa">

<map name="mappa">
    <area
        shape="rect"
        coords="10,20,120,100"
        href="https://google.com"
        target="_blank"
        rel="noopener"
        alt="Google"
        title="Google"
    >

    <area
        shape="circle"
        coords="180,75,40"
        href="https://facebook.com"
        target="_blank"
        rel="noopener"
        alt="Facebook"
        title="Facebook"
    >
</map>
```

Passando con il mouse sulle zone:

- il cursore cambia da freccia a **manina**;
    
- cliccando vieni portato ai link impostati.
    

---

### 9. Problema critico: DO NOT cambiare dimensioni all’immagine

> Le coordinate funzionano **solo** se l’immagine ha esattamente la stessa larghezza/altezza usata quando hai creato la mappa.

Se aggiungi:

```html
<img style="height: 400px">
```

o se la CSS la ridimensiona, i punti non combaceranno più:

- clicchi nel “cerchio” ma il browser pensa che sia più su,
    
- clicchi nel rettangolo ma finisci nel link sbagliato.
    

Le mappe immagine si basano su coordinate assolute, non scalabili.

---

### 10. Quando usare davvero l’image mapping?

È utile quando:

- una singola immagine deve diventare un’interfaccia cliccabile,
    
- non puoi separare l’immagine in pezzi diversi.
    

Esempi realistici:

- mappa geografica di regioni,
    
- planimetrie,
    
- grafici interattivi molto semplici,
    
- composizioni fotografiche in cui non vuoi ritagliare l’immagine.
    

È invece sconsigliato quando:

- hai un normale layout web,
    
- puoi semplicemente usare immagini separate,
    
- devi fare responsive avanzato (le mappe non reagiscono bene ai resize).
    

Per progetti moderni complessi, di solito si preferisce:

- SVG interattivi,
    
- overlay CSS/JS,
    
- componenti JavaScript dedicati.
    

---

### 11. Conclusione

L’image mapping:

- è una tecnica **antica ma ancora valida**,
    
- appartiene all’HTML puro,
    
- permette di rendere cliccabili zone interne di un’unica immagine,
    
- si basa su coordinate assolute **non ridimensionabili**,
    
- è scomodo farlo a mano → si usano tool online,
    
- serve raramente, ma sapere usarlo è parte della formazione completa.
    