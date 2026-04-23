## **Lezione 7 – Che cos’è il DOM (Document Object Model)**

### **Che cosa rappresenta il DOM**

Il DOM, acronimo di _Document Object Model_, è la **rappresentazione a livello di dati** della pagina HTML che scriviamo.  
Il browser prende il nostro codice e lo trasforma in una struttura fatta di oggetti, nodi ed elementi, organizzata in modo gerarchico.  
In altre parole: il DOM è _la visione interna_ che il browser ha della nostra pagina.

La traduzione è letterale:

- _Document_ → il documento HTML che abbiamo scritto
    
- _Object_ → viene trasformato in oggetti gestibili
    
- _Model_ → diventa un modello dati strutturato
    

Questa struttura consente al browser di processare correttamente la pagina e, soprattutto, permette a JavaScript di modificarla dinamicamente.

---

### **Come appare il DOM**

Uno schema classico del DOM mostra una struttura ad albero:

- in cima c’è `document`
    
- sotto c’è l’elemento `html`
    
- allo stesso livello si trovano `head` e `body`
    
- dentro `body` si trovano gli elementi HTML annidati
    
- ogni nodo può contenere:
    
    - un _elemento_ (es. `h1`, `p`, `div`)
        
    - del _testo_
        
    - eventuali _attributi_, come `href`, `src`, `class`, ecc.
        

Questa struttura ad albero ricalca perfettamente quella che noi vediamo nel nostro file HTML.  
La differenza è che:

- **noi** la interpretiamo mentalmente in un attimo, in modo completamente naturale,
    
- **il browser**, invece, deve trasformarla in dati e salvarla in memoria perché ne ha bisogno per lavorare.
    

Il DOM non è quindi “un’altra cosa”: è semplicemente _la versione dati_ del nostro HTML, costruita dal browser per poterci lavorare sopra.

---

### **Perché il DOM è fondamentale**

Il DOM è indispensabile per due motivi:

1. **Serva al browser per mostrare la pagina**  
    Il browser non può lavorare direttamente sul testo grezzo dell’HTML.  
    Prima deve convertirlo in una struttura dati che gli permetta di capire:
    
    - quali elementi esistono,
        
    - quali elementi sono figli di quali altri,
        
    - dove si trova il testo,
        
    - quali attributi possiede ogni elemento.
        
2. **Permette a JavaScript di modificare la pagina in tempo reale**  
    Quando JavaScript interagisce con la pagina, non modifica il file HTML, ma **modifica il DOM**, cioè la rappresentazione in memoria.
    

La pagina che vediamo cambia perché il DOM cambia.

---

### **Esempio pratico con JavaScript**

Nel video, viene aggiunto questo codice JavaScript (semplificato):

```javascript
document.getElementsByTagName("h1")[0].style.color = "purple";
```

Cosa succede passo passo:

- `document` → rappresenta l’intero DOM della pagina.
    
- `getElementsByTagName("h1")` → cerca tutti gli elementi `<h1>` dentro il DOM.  
    Anche se ce n’è uno solo, JavaScript restituisce comunque una **lista di elementi** (come una scatola con dentro un solo oggetto).
    
- `[0]` → prende il primo elemento della lista.
    
- `.style.color = "purple"` → modifica direttamente il suo stile nel DOM.
    

Il file HTML originale non viene toccato:  
viene modificata la **rappresentazione interna** che il browser ha già creato.

Ecco perché, quando si esegue lo script, l’`h1` diventa viola.

---

### **Perché tutto questo è importante anche se usiamo solo CSS**

Per ora interagiamo solo con HTML e CSS, quindi il DOM sembra un concetto un po' astratto.  
Ma la realtà è che:

- il browser **usa il DOM anche per applicare il CSS**,
    
- il CSS lavora _sui nodi del DOM_,
    
- ogni stile che vediamo è il risultato di un’interazione tra DOM e CSS,
    
- ogni futura modifica dinamica (effetti, animazioni, interazioni) dipenderà dalla conoscenza del DOM.
    

Quando entreremo nel corso di JavaScript, il DOM diventerà protagonista assoluto.

---

### **Schema semplice per ricordare cos’è il DOM**

- Scriviamo HTML.
    
- Il browser lo legge.
    
- Lo converte in una struttura dati ad albero → **DOM**.
    
- Il CSS viene applicato al DOM.
    
- JavaScript può modificare il DOM.
    
- Il browser mostra a schermo ciò che risulta dal DOM.
    

---

Con questo concetto chiarito, siamo pronti per finalmente iniziare con la **sintassi di base del CSS**, ora che sappiamo “su cosa” il CSS viene applicato.