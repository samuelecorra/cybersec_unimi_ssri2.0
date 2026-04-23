## **Lezione 5 – I quattro modi per inserire CSS in una pagina HTML**

### **Obiettivo della lezione**

Ora che sappiamo cos’è il CSS e che il browser applica già del CSS di default, dobbiamo capire **come inserire noi il nostro stile all’interno di una pagina HTML**.  
Esistono **tre metodi principali** e un **quarto metodo extra**, utile più avanti quando parleremo di ottimizzazione e performance.

---

### **1. CSS interno nella sezione `<head>` tramite il tag `<style>`**

Questo è il metodo più semplice e immediato.

Nella sezione `<head>` della pagina si aggiunge:

```html
<style>
    h1 {
        color: red;
    }
</style>
```

Questo stile viene applicato subito e modifica il contenuto della pagina senza bisogno di file esterni.  
È perfetto per piccoli test, prototipi veloci o pagine molto semplici.

La logica è: il browser incontra il tag `<style>` nella head, legge le regole e le applica a tutto il documento.

---

### **2. CSS esterno in un file separato (.css)**

Appena il sito cresce, inserire tutto lo stile dentro il tag `<style>` diventa ingestibile.  
Per questo esiste il metodo più professionale: usare un **file CSS esterno**.

Esempio:

- si crea nella cartella `css/` un file chiamato `style.css`
    
- si scrive dentro:
    

```css
h1 {
    color: blue;
}
```

- poi si collega il file nella `<head>` tramite:
    

```html
<link rel="stylesheet" href="css/style.css">
```

A questo punto la pagina utilizza il foglio di stile esterno.

Questo approccio è fondamentale per progetti medi e grandi, perché separa **struttura** (HTML) e **stile** (CSS), evitando caos e duplicazioni.

---

### **3. CSS inline (all’interno di un singolo elemento)**

HTML permette di inserire lo stile direttamente dentro un tag:

```html
<h1 style="color: green;">Titolo</h1>
```

Questo metodo ha **massima priorità** nella cascata del CSS (lo vedremo nel dettaglio).  
Significa che _vince su tutto_, anche su uno stile esterno o interno.

È però anche il metodo **più scomodo**, perché:

- rende il codice HTML sporco e difficile da leggere,
    
- impedisce di riutilizzare lo stile,
    
- diventa ingestibile se un elemento ha molte regole.
    

Per questo si usa solo per casi speciali, prototipi veloci o micro-modifiche.

---

### **4. CSS scritto dentro il `<body>` (sconsigliato ma possibile)**

È tecnicamente possibile inserire il tag `<style>` anche dentro il body:

```html
<body>
    <style>
        h1 {
            color: blue;
        }
    </style>
</body>
```

Funziona perché il browser legge comunque il CSS, ovunque esso sia.

Questo metodo però è **sbagliato da un punto di vista di performance e struttura**:

- il browser deve caricare contenuto e stile in ordine errato,
    
- il caricamento può rallentare,
    
- HTML e CSS vengono mescolati inutilmente.
    

Lo vedremo in dettaglio più avanti, quando parleremo di **ottimizzazione** e del perché il CSS va quasi sempre caricato nella `<head>` per migliorare la velocità di rendering.

---

### **La logica della cascata (accenno preliminare)**

Quando due regole definiscono la stessa proprietà sullo stesso elemento, vince **quella più in basso** (o più “vicina” all’elemento).  
Esempio:

```html
<style>
    h1 { color: red; }
</style>

<style>
    h1 { color: blue; }
</style>
```

Il risultato è **blu** perché la seconda regola viene letta dopo la prima.

Il CSS inline vince su entrambe:

```html
<h1 style="color: green;">Titolo</h1>
```

Ora il titolo diventa verde perché il CSS inline ha priorità assoluta.

Questo è un concetto fondamentale che approfondiremo quando parleremo della _cascata_, dell’ereditarietà e della specificità.

---

### **Quale metodo useremo nel corso**

In questa fase iniziale useremo principalmente:

- il CSS interno (`<style>` nella head)
    
- e il CSS esterno (`style.css`)
    

Utilizzeremo il CSS inline solo per far vedere il comportamento della cascata, non come pratica standard.

Il CSS nel body rimane solo un’anteprima per capire cosa vedremo con l’ottimizzazione delle performance.

---

### **Conclusione**

Ora sappiamo _tutti i modi possibili_ per inserire CSS in una pagina HTML:

1. `<style>` nella head (pratico per esempi veloci)
    
2. file esterno `.css` (metodo corretto per siti reali)
    
3. stile inline (ha la precedenza assoluta ma è scomodo e sporco)
    
4. `<style>` nel body (possibile ma sconsigliato)
    

Nel prossimo video vedremo **come il browser applica realmente il CSS alla pagina** e inizieremo a capire i concetti chiave della cascata.