## **Lezione 1: Formattazione del testo – enfasi, grassetto, evidenziazione**

---

## **1. Introduzione al modulo: formattazione del testo**

Abbiamo finito il primo esercizio “strutturale” (titoli, sezioni, paragrafi, meta tag…).  
Ora entriamo nel **nuovo modulo**: la **formattazione del testo**.

Obiettivo di questo blocco:

- imparare a:
    
    - mettere **enfasi** su alcune parole,
        
    - usare il **grassetto**,
        
    - usare il testo in **corsivo** (italic),
        
    - **evidenziare** parti importanti,
        
- capire la differenza tra:
    
    - elementi che servono **solo a cambiare l’aspetto visivo**;
        
    - elementi che hanno anche **significato semantico** e sono letti correttamente dagli **screen reader**.
        

Per questa lezione, ripartiamo da un file `index.html` vuoto, con il solito boilerplate, e costruiamo un esempio minimo.

---

## **2. Setup: un titolo e un paragrafo “piatto”**

Nel `body` costruiamo un mini documento:

```html
<body>
  <h1>Bel titolone</h1>
  <p>
    Lorem ipsum dolor sit amet consectetur adipisicing elit. Modi enim
    voluptates culpa repellendus ex dolor provident doloremque hic harum!
  </p>
</body>
```

Se apri la pagina nel browser:

- vedi un **titolone** (`<h1>`) e
    
- un **paragrafo** di testo.
    

È un testo “piatto”:

- tutte le parole hanno lo stesso peso visivo,
    
- nessuna parola risalta come importante,
    
- non c’è enfasi particolare su concetti chiave.
    

Ora impariamo a **colorare semanticamente** il testo.

---

## **3. Enfasi, grassetto, evidenziazione: `<em>`, `<strong>`, `<mark>`**

Partiamo dagli elementi **semantici**, cioè quelli che:

- modificano l’aspetto **visivo** del testo;
    
- comunicano anche un significato ai **lettori di schermo** (screen reader).
    

### **3.1 `<em>` – enfasi (italic semantico)**

```html
<p>
  Lorem ipsum dolor sit amet consectetur adipisicing elit.
  <em>Modi</em> enim voluptates culpa repellendus ex dolor...
</p>
```

Effetto:

- la parola **“Modi”** diventa in **corsivo** (italic);
    
- semanticamente significa: “Qui c’è **enfasi** su questa parola”.
    

Per uno screen reader:

- non è solo “testo in italico”,
    
- viene letta con **intonazione diversa**, come faresti tu a voce quando sottolinei una parola importante.
    

### **3.2 `<strong>` – enfasi forte (grassetto semantico)**

```html
<p>
  ... enim voluptates culpa repellendus ex
  <strong>dolor</strong> provident doloremque hic harum!
</p>
```

Effetto:

- la parola **“dolor”** appare in **grassetto**;
    
- semanticamente significa: “Qui c’è **enfasi forte / concetto fondamentale**”.
    

Lo screen reader farà qualcosa di analogo a:

- rallentare leggermente,
    
- cambiare la voce,
    
- mettere più peso su quella parola.
    

### **3.3 `<mark>` – evidenziazione tipo “evidenziatore”**

```html
<p>
  Lorem ipsum dolor sit amet consectetur adipisicing elit.
  <mark>Questa è una parte importante da ricordare</mark>
  nella lezione.
</p>
```

Effetto:

- sfondo giallo “evidenziatore” sul testo marcato;
    
- è come se avessi passato un evidenziatore su un libro.
    

Usi tipici:

- articoli didattici,
    
- spiegazioni con “frasi chiave”,
    
- note di studio online.
    

---

## **4. Gli elementi puramente visivi: `<u>`, `<b>`, `<i>`**

HTML offre anche elementi che **cambiano solo il look**, senza aggiungere significato semantico per gli screen reader.

### **4.1 `<u>` – underline (sottolineato)**

```html
<p>
  Questo testo è <u>sottolineato</u>.
</p>
```

Serve solo a mettere una **riga sotto** al testo.

### **4.2 `<b>` – bold (grassetto visivo)**

```html
<p>
  Questo testo è in <b>grassetto visivo</b>.
</p>
```

Visualmente è simile a `<strong>`, ma:

- per uno screen reader, `<b>` è solo una variazione grafica,
    
- **non comunica enfasi** come `<strong>`.
    

### **4.3 `<i>` – italic (corsivo visivo)**

```html
<p>
  Questo testo è in <i>corsivo visivo</i>.
</p>
```

È visivamente analogo a `<em>`, ma:

- non comunica “enfasi”,
    
- è solo stile.
    

---

## **5. Differenza concettuale: semantico vs solo visivo**

Ricapitoliamo la differenza fondamentale:

- **Semantici + visivi**:
    
    - `<em>` → enfasi (italic)
        
    - `<strong>` → enfasi forte (bold)
        
    - `<mark>` → evidenziazione semantica
        
    
    Questi tag:
    
    - cambiano lo stile,
        
    - **dicono qualcosa sul significato del testo**,
        
    - vengono interpretati dai **lettori di schermo**, che modulano la voce.
        
- **Solo visivi**:
    
    - `<b>` → grassetto visivo
        
    - `<i>` → corsivo visivo
        
    - `<u>` → sottolineato visivo
        
    
    Questi tag:
    
    - cambiano solo l’aspetto,
        
    - non comunicano nulla di particolare agli screen reader.
        

Per l’accessibilità moderna:

- **preferisci sempre** `<em>` e `<strong>` rispetto a `<i>` e `<b>` quando vuoi enfatizzare un concetto;
    
- usa `<mark>` quando vuoi **segnalare visivamente e semanticamente** che un pezzo di testo è “chiave”.
    

---

## **6. Usare `<span>` come contenitore generico + `style`**

Finora abbiamo usato tag pronti:

- `<strong>`,
    
- `<em>`,
    
- `<mark>`,
    
- `<b>`, `<i>`, `<u>`.
    

Ma c’è un altro modo: usare un elemento **generico**, `<span>`, e stilizzarlo noi.

### **6.1 Cos’è `<span>`**

```html
<span>Testo dentro uno span</span>
```

Caratteristiche:

- è un elemento **inline** (non va a capo da solo),
    
- **non ha significato semantico** (non dice niente di particolare),
    
- è una sorta di “scatolina” che contiene testo.
    

Se sostituissi lo span con un `<div>`:

```html
<div>Testo</div>
```

il div:

- è un elemento **block**,
    
- occupa tutta la riga e va a capo prima/dopo.
    

Quindi:

- `<span>` → inline, usato per formattare o raggruppare in modo leggero parti di testo;
    
- `<div>` → block, usato per blocchi di layout / sezioni.
    

### **6.2 Stilizzare `<span>` con `style`**

Possiamo usare lo span come “aggancio” per il CSS.  
Per esempio, per fare un grassetto personalizzato:

```html
<p>
  Lorem ipsum dolor sit amet
  <span style="font-weight: 900;">adipisci</span>
  elit.
</p>
```

Oppure:

```html
<span style="font-style: italic;">testo in corsivo</span>
```

O entrambi:

```html
<span style="font-weight: 900; font-style: italic;">
  grassetto + italic
</span>
```

In pratica, con `<span>` + `style` puoi ottenere:

- grassetto,
    
- corsivo,
    
- colori diversi,
    
- sfondo diverso…
    

ma è **soltanto visivo**, come `<b>`/`<i>`.

A livello di semantica:

- `<span>` non dice “enfasi”,
    
- non dice “parola chiave”,
    
- non aiuta i lettori di schermo nell’intonazione.
    

---

## **7. Quale metodo usare, quando**

Per ottenere grassetto/italic puoi scegliere tra **tre strade**:

1. **Solo visivo**:
    
    - `<b>`, `<i>`, `<u>`, oppure `<span style="...">`  
        → usali se davvero ti serve **solo** un effetto grafico, senza significato.
        
2. **Semantico + visivo (consigliato)**:
    
    - `<strong>` per grassetto con enfasi,
        
    - `<em>` per corsivo con enfasi,
        
    - `<mark>` per evidenziare concetti chiave  
        → sono la scelta giusta quando vuoi che:
        
        - il lettore umano veda l’enfasi,
            
        - lo screen reader la **riproduca nella voce**.
            
3. **Layout / stile complesso**:
    
    - `<span>` + CSS (inline o, meglio, in un file `.css`)  
        → utile quando:
        
        - vuoi un effetto particolare,
            
        - vuoi applicare una classe e gestire lo stile globalmente nel CSS,
            
        - ma ricorda che semantica e accessibilità, in questo caso, dipendono da **come lo usi**.
            

Regola pratica:

- Se vuoi **enfasi nel significato** → usa `<em>` / `<strong>` / `<mark>`.
    
- Se vuoi solo un trucco grafico → puoi usare `<span>` con stile o `<b>` / `<i>` / `<u>`.
    
- Se hai utenti con screen reader (spoiler: sul web, sì) → pensa sempre alla **semantica**, non solo alla grafica.
    

---

## **8. Conclusione**

In questa lezione hai visto i primi strumenti di **formattazione del testo** in HTML:

- `<em>`, `<strong>`, `<mark>` per enfasi semantica e visiva;
    
- `<u>`, `<b>`, `<i>` per effetti solo grafici;
    
- `<span>` come contenitore inline “vuoto” da riempire con stile (`style` o CSS esterno);
    
- la differenza tra **inline** (`<span>`) e **block** (`<div>`).
    

Già con questi pochi elementi, il tuo testo passa da “muro uniforme” a contenuto:

- più leggibile,
    
- più chiaro,
    
- più accessibile.
    

Nel prossimo video, prenderai l’**Esercizio 1** e lo arricchirai usando questi elementi di formattazione per evidenziare parti importanti del contenuto.