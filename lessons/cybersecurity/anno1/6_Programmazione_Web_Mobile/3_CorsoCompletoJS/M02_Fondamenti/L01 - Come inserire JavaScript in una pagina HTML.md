## **Lezione 1: Come inserire JavaScript in una pagina HTML**

### **1. Perché partiamo dall’HTML**

Iniziamo finalmente la sezione dei **fondamenti di JavaScript**.  
La prima domanda pratica è:

> “Come inserisco JavaScript dentro una pagina web?”

Per rispondere bene, dobbiamo ricordare **da dove nasce JavaScript**:

- JavaScript è nato come **linguaggio per il web**, per affiancare **HTML** (contenuto/struttura) e **CSS** (stile).
    
- Solo **dopo** è arrivato tutto il resto:
    
    - JavaScript lato **server** (Node.js, Deno…),
        
    - JavaScript per **desktop** (Electron),
        
    - JavaScript per **mobile** (React Native, ecc.).
        

In questo corso:

- per buona parte lavoreremo **sempre all’interno di una pagina HTML**;
    
- più avanti vedremo **Node** e scenari “fuori dal browser”, ma è fondamentale capire prima **la forma originaria**: JavaScript dentro l’HTML.
    

---

### **2. Primo metodo: `<script>` con codice inline**

Il modo più semplice per inserire JavaScript in una pagina è usare il tag:

```html
<script>
  console.log("Ciao, sono Edo");
</script>
```

Qualche nota importante:

- Il tag è **`<script>`**, **non** `<javascript>`.
    
- Dentro ci va codice **JavaScript puro**.
    
- Possiamo già vedere il risultato aprendo gli strumenti di sviluppo del browser:
    
    - premi **F12**,
        
    - vai nella tab **Console**,
        
    - vedrai il messaggio:  
        `Ciao, sono Edo`.
        

Dentro questo `<script>` in futuro potremo mettere:

- l’ascolto di un click,
    
- il cambio del background,
    
- la creazione dinamica di elementi,
    
- tutto il resto della logica della pagina.
    

Per ora ci basta il classico `console.log()`.

---

### **3. Dove mettere lo `<script>`: `head` vs `body`**

Possiamo inserire il tag `<script>` in **due zone** della pagina:

#### **3.1 Script nella `<head>`**

Esempio:

```html
<head>
  <script>
    console.log("Ciao, sono Edo da head");
  </script>
</head>
```

#### **3.2 Script nel `<body>`**

Esempio:

```html
<body>
  <!-- contenuto vari... -->

  <script>
    console.log("Ciao, sono Edo da body");
  </script>
</body>
```

Se li metti entrambi, nella console vedrai:

- `Ciao, sono Edo da head`
    
- `Ciao, sono Edo da body`
    

Fin qui sembra tutto uguale… ma **non lo è**.

---

### **4. Differenza fondamentale: quando viene eseguito lo script**

La differenza tra mettere lo script in **head** o in **body** è **il momento in cui viene eseguito**.

- Se lo script è nella **head**:
    
    - viene eseguito **prima** che la pagina sia completamente renderizzata;
        
    - la pagina **non prosegue il caricamento** finché quello script non è stato eseguito.
        
- Se lo script è nel **body, in fondo**:
    
    - viene eseguito **dopo** che la parte importante della pagina è stata caricata;
        
    - l’utente vede prima la pagina, poi parte lo script.
        

Quindi:

- `<script>` nella **head** → può essere **bloccante**.
    
- `<script>` alla **fine del body** → è generalmente **preferibile** per:
    
    - performance,
        
    - percezione di velocità da parte dell’utente.
        

In pratica, se lo script fa qualcosa di pesante (molta logica, richieste di rete, calcoli), metterlo in **head** rallenta il caricamento della pagina.

**Regola pratica standard:**

> Di default, i file JavaScript per il frontend si mettono **in fondo al `body`**.

Eccezione:  
alcune librerie o script **critici** per il rendering possono essere caricati nella `head`, ma devono essere **leggeri** e ben studiati.  
Più avanti parleremo anche di attributi come `async` e `defer` per migliorare questo comportamento.

---

### **5. Secondo metodo: `<script src="...">` con file esterno locale**

Il secondo modo (più usato in progetti reali) è separare il codice JavaScript dalla pagina HTML:

#### **5.1 HTML con `<script src>`**

```html
<body>
  <!-- contenuto della pagina -->

  <script src="index.js"></script>
</body>
```

#### **5.2 File JavaScript separato (`index.js`)**

```js
console.log("Ciao, sono Edo da script source index.js");
```

Quando ricarichi la pagina e apri la console, vedrai:

`Ciao, sono Edo da script source index.js`

Che cosa è successo?

- L’HTML **non contiene più direttamente** il codice JavaScript.
    
- Il browser vede `<script src="index.js">`,
    
- Effettua una **richiesta HTTP** per scaricare `index.js`,
    
- Esegue il codice contenuto in quel file.
    

Questo si chiama **“esternalizzare”** il codice:

- la pagina HTML rimane più **pulita e leggibile**;
    
- il file JS può diventare anche di **centinaia di righe** senza sporcare l’HTML;
    
- è più facile da mantenere, riutilizzare, organizzare.
    

L’effetto di posizionare lo script in **head** o **body** resta lo stesso anche con `src`:

- script in `head` → esecuzione prima del rendering, potenzialmente bloccante;
    
- script in fondo al `body` → esecuzione dopo il caricamento dell’HTML, più fluida.
    

---

### **6. Costo nascosto: più file, più richieste HTTP**

Quando il codice era scritto inline:

```html
<script>
  // 700 righe di codice
</script>
```

il browser scaricava **una sola risorsa** (`index.html`).

Quando usiamo:

```html
<script src="index.js"></script>
```

il browser deve scaricare:

- `index.html`
    
- **e poi anche** `index.js`.
    

Quindi:

- vantaggio → codice separato, più organizzato;
    
- svantaggio → **una richiesta HTTP in più**.
    

Nel mondo reale, questo è un tema di **ottimizzazione delle performance** (che vedremo più avanti):  
bilanciare **leggibilità/manutenibilità** e **numero di richieste**.

---

### **7. Terzo metodo: `<script src="...">` con file esterno via CDN**

Finora abbiamo usato un file locale.

Ma possiamo anche caricare file JavaScript (o CSS) ospitati **sul web**, usando un **CDN (Content Delivery Network)**.

Esempi tipici:

- libreria **jQuery**,
    
- framework **Tailwind CSS** (anche se è un framework CSS, sotto usa JavaScript),
    
- altre librerie front-end.
    

#### **7.1 Esempio generico di CDN**

```html
<script src="https://cdn.qualcosa.com/libreria.min.js"></script>
```

In questo caso:

- non abbiamo il file nella nostra cartella,
    
- lo prendiamo da un **server esterno**.
    

Un **CDN** è un’infrastruttura di server sparsi nel mondo che distribuiscono file statici (come JS, CSS, immagini) in modo veloce.  
Se hai seguito il corso HTML, hai già visto questo concetto.

---

### **8. Esempio concreto: Tailwind via CDN**

Supponiamo di voler usare **Tailwind CSS** tramite CDN.

Nel nostro HTML:

```html
<head>
  <!-- altri tag, magari lo <style> ecc. -->

  <script src="https://cdn.tailwindcss.com"></script>
</head>
```

Poi nel body scriviamo:

```html
<h1 class="text-4xl text-red-500">
  Ciao!
</h1>
```

Cosa succede?

- Il browser scarica:
    
    - `index.html`,
        
    - il file JS di Tailwind da `https://cdn.tailwindcss.com`,
        
    - eventuali altri file JS nostri (es. `index.js`).
        
- Tailwind interpreta quelle classi (`text-4xl`, `text-red-500`) e applica lo stile.
    

**Attenzione importante:**  
La pagina di Tailwind stessa dice che il CDN:

- è comodo per **test, gioco, esercizi, prototipi**,
    
- **non va usato in produzione**, perché:
    
    - è meno ottimizzato,
        
    - ci sono questioni di performance, caching, build, ecc.
        

In produzione si usa un flusso più evoluto (build, PostCSS, ecc.), ma qui ci basta vedere il meccanismo:  
**anche quel file è JavaScript**, viene scaricato, interpretato ed eseguito.

Se apri gli strumenti di sviluppo → tab **Network**:

- vedrai tra le richieste:
    
    - `index.html`,
        
    - il file di Tailwind (`cdn.tailwindcss.com`),
        
    - il tuo `index.js` (se presente),
        
- e puoi verificare che:
    
    - il **Content-Type** del file di Tailwind è `text/javascript`,
        
    - quindi è **a tutti gli effetti un file JS** gigante.
        

---

### **9. Effetti del posizionamento del CDN (`head` vs `body`)**

Riprendiamo l’esempio di Tailwind:

1. Se metti lo script **in fondo al body**:
    
    ```html
    <body>
      <h1 class="text-4xl text-red-500">Ciao!</h1>
    
      <script src="https://cdn.tailwindcss.com"></script>
    </body>
    ```
    
    - La pagina viene inizialmente renderizzata con **lo stile di base** oppure con il CSS precedente.
        
    - Poi, una volta caricato Tailwind, gli stili cambiano.
        
    - A volte puoi percepire un piccolo “flash” di cambiamento.
        
2. Se metti lo script **nella head**:
    
    ```html
    <head>
      <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body>
      <h1 class="text-4xl text-red-500">Ciao!</h1>
    </body>
    ```
    
    - Tailwind viene caricato **prima** del rendering del body.
        
    - Quando la pagina appare, il testo è **già rosso e grande**.
        
    - Non vedi il cambio “dopo” il render.
        

Questo dimostra in pratica ciò che dicevamo prima:

- gli script nella **head** influenzano **il modo** in cui la pagina viene renderizzata;
    
- gli script nel **body** possono intervenire **dopo** che la pagina è già stata mostrata.
    

E ogni scelta ha **vantaggi e svantaggi**:

- head → più controllo sul render iniziale, ma potenzialmente più blocchi;
    
- body → pagina percepita più “scattante”, ma alcuni effetti arrivano dopo.
    

---

### **10. Combinare script inline, script con `src`, file locali e CDN**

In una pagina reale puoi avere:

```html
<body>
  <h1 id="titolo" class="text-4xl">Ciao</h1>

  <!-- Script inline -->
  <script>
    console.log("Ciao dalla pagina!");
  </script>

  <!-- File JS locale -->
  <script src="index.js"></script>

  <!-- Libreria esterna via CDN -->
  <script src="https://cdn.tailwindcss.com"></script>
</body>
```

Tutti e tre sono **validi**:

- **inline** → comodo per piccoli script veloci o test;
    
- **locale** (`index.js`) → per il grosso della logica della tua applicazione;
    
- **CDN** → per librerie di terze parti (temporaneamente via CDN o, in produzione, spesso tramite bundle).
    

---

### **11. Riepilogo: tutti i modi per inserire JavaScript in HTML**

Riassumiamo quanto visto:

1. **Script inline**
    
    ```html
    <script>
      console.log("Messaggio");
    </script>
    ```
    
2. **Script esterno locale**
    
    ```html
    <script src="index.js"></script>
    ```
    
3. **Script esterno remoto (CDN)**
    
    ```html
    <script src="https://cdn.esempio.com/libreria.js"></script>
    ```
    
4. **Posizionamento**
    
    - **`<head>`**:
        
        - lo script viene eseguito **prima** del render;
            
        - è **bloccante** se pesante;
            
        - ha senso per librerie che servono subito (ma vanno usate con criterio).
            
    - **In fondo al `<body>`**:
        
        - lo script viene eseguito **dopo** il caricamento dell’HTML;
            
        - è lo standard consigliato per la maggior parte dei casi.
            
5. **Ogni script è una richiesta in più**
    
    - Inline → una sola richiesta (l’HTML);
        
    - `src` locale o CDN → **richieste HTTP aggiuntive**.
        

---

### **12. Conclusione: abbiamo solo aperto la porta**

In questa lezione abbiamo fatto una cosa che sembra banale:

> “Come inserisco JavaScript in una pagina HTML?”

Ma in realtà abbiamo già toccato concetti di:

- **ordine di esecuzione**,
    
- **performance**,
    
- **organizzazione del codice**,
    
- **CDN e librerie esterne**.
    

E non abbiamo ancora:

- scritto vera logica,
    
- definito variabili,
    
- visto la sintassi in dettaglio.
    

Quindi:

- ora sai **tutti i modi principali** per collegare JavaScript a una pagina HTML;
    
- sai che esiste una differenza concettuale tra **head** e **body**;
    
- sai che puoi usare sia file **locali** che file da **CDN**;
    
- sai che queste scelte avranno impatto su **performance** e **user experience**.
    

Dalla prossima lezione iniziamo finalmente a parlare di **sintassi di JavaScript**:  
come si scrivono le istruzioni, cosa sono le variabili, come funziona davvero il linguaggio.