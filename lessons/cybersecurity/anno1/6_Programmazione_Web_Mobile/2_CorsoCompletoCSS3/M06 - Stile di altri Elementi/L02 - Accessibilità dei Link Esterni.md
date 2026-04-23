## **Lezione 2: Accessibilità dei Link Esterni**

### **1. Introduzione**

Riprendiamo il discorso sui link e affrontiamo un aspetto spesso ignorato dagli sviluppatori principianti ma **fondamentale per l’accessibilità**:  
**segnalare correttamente i link esterni**.

L’utente deve sempre sapere quando:

- sta lasciando il nostro sito
    
- si sta spostando verso una risorsa esterna
    
- il contenuto di destinazione non fa parte del nostro dominio
    

Questo non è un dettaglio estetico, ma una regola di UX, sicurezza e trasparenza.

---

### **2. Link interni vs link esterni**

### **2.1 Link interno**

Un link che punta a una sezione o pagina del sito stesso:

```html
<a href="#info">ciao</a>
```

È interno, rimane nel dominio attuale.

### **2.2 Link esterno**

Un link che porta fuori dal sito:

```html
<a href="https://facebook.com">ciao tre</a>
```

Quando un link porta l’utente altrove, dobbiamo:

1. **aprire il link in una nuova tab o finestra**
    
2. **mostrare visivamente che si tratta di una destinazione esterna**
    

---

### **3. Perché segnalare un link esterno**

### **3.1 Sicurezza**

Un utente potrebbe credere di essere ancora nel tuo sito, dove si sente al sicuro.  
Aprire un sito esterno senza avvisare può:

- creare confusione
    
- esporre l’utente a contenuti malevoli
    
- far perdere la percezione del contesto
    

### **3.2 UX (Esperienza Utente)**

YouTube, Discord, LinkedIn, GitHub, Medium, ecc. mostrano un popup:  
“Stai lasciando il sito. Vuoi continuare?”

Perché?

- trasparenza
    
- chiarezza
    
- coerenza visiva
    
- responsabilità
    

### **3.3 Navigazione semplice**

Aprire il link in una nuova tab permette all’utente di:

- non perdere la pagina attuale
    
- continuare a navigare il nostro sito
    
- tornare facilmente indietro
    

---

### **4. target="_blank"**

La prima regola per i link esterni:

```html
<a href="https://facebook.com" target="_blank">Facebook</a>
```

Questo fa sì che il link:

- si apra in una nuova scheda
    
- non sostituisca la pagina corrente
    

È lo **standard** per i link esterni.

---

### **5. Indicatore visivo del link esterno**

### **5.1 L’iconcina**

Molti siti moderni aggiungono una piccola icona a destra del testo del link per indicare visivamente che si esce dal sito.  
Esempio tipico: una piccola freccia diagonale.

È un simbolo ormai riconosciuto universalmente.

### **5.2 Implementazione con pseudo-elemento**

Utilizziamo una proprietà avanzata: lo **pseudo-elemento** `::after`.

Ecco la struttura:

```css
a[target="_blank"]::after {
  content: url("data:image/png;base64, ..."); 
  margin-left: 4px;
}
```

### **5.3 Cosa significa**

- `a[target="_blank"]` → seleziona tutti i link che aprono una nuova tab
    
- `::after` → aggiunge un **elemento virtuale** subito dopo il testo del link
    
- `content: ...` → inserisce l’immagine (in formato Base64)
    
- `margin-left` → distanzia l’icona dal testo
    

### **5.4 Perché “pseudo-elemento”?**

Perché:

- non è un vero elemento HTML
    
- non è presente nel codice scritto da noi
    
- viene “iniettato” dal browser
    
- è parte dell’elemento `<a>` ma non appare nel markup
    
- si comporta come un figlio aggiunto dinamicamente
    

Puoi verificarlo dall’ispezione degli elementi:  
vedrai `::after` appeso all’elemento `<a>`.

---

### **6. Il data-image in Base64**

La stringa enorme che hai incollato è semplicemente:

- una piccola immagine PNG
    
- codificata in Base64
    
- caricata direttamente nel CSS
    

Serve per non dover importare file esterni o SVG.

Non è essenziale capire la codifica Base64 ora:  
ti basta sapere che è una versione testuale di un’immagine.

---

### **7. Perché non abbiamo trattato questo in HTML**

Perché per creare questa funzionalità servivano concetti che hai imparato solo adesso:

- selettori avanzati
    
- pseudo-elementi
    
- gestione del contenuto dinamico (`content:`)
    
- margini
    
- concetto di attributi nei selettori CSS
    
- conoscenza di overflow e display (per capire l’impaginazione dell’icona)
    

Ora hai tutti i prerequisiti per comprenderlo.

---

### **8. Risultato finale**

Ogni link esterno:

- si apre in una nuova tab
    
- mostra un’icona accanto
    
- garantisce trasparenza all’utente
    
- rispetta le linee guida di accessibilità
    

Esempio completo:

```html
<a href="https://facebook.com" target="_blank">
  Vai a Facebook
</a>
```

```css
a[target="_blank"]::after {
  content: url("data:image/png;base64, ...");
  margin-left: 4px;
}
```

Questo pattern è usato ovunque perché è minimale, elegante e funzionale.

---

### **9. Conclusione**

Ora sai come:

- distinguere i link interni dai link esterni
    
- aprire correttamente i link esterni in una nuova tab
    
- notificare l’utente della destinazione esterna
    
- usare uno pseudo-elemento `::after` per mostrare un’icona
    
- capire la differenza tra pseudo-elemento e pseudo-classe
    

Nella prossima lezione entreremo in un nuovo argomento:  
**lo stile delle liste**, un elemento chiave nel web design.