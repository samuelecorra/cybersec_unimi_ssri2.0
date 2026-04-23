## **Lezione 2 – Il Selettore Globale (`*`)**

### **1. Introduzione rapida al selettore globale**

Il selettore globale è probabilmente il selettore più semplice di tutto il CSS.  
Si scrive con un solo carattere:

```
*
```

e significa letteralmente:

> “Seleziona **tutti** gli elementi della pagina.”

Non importa quale sia il tag: `html`, `body`, `div`, `h1`, `p`, `strong`, `span`, `a`, perfino elementi che non pensi mai esplicitamente… il selettore globale li colpisce **tutti senza eccezioni**.

È il selettore più ampio possibile.

---

### **2. Esempio pratico: applicare un bordo a tutto**

Per dimostrare cosa fa il selettore globale, immaginiamo di scrivere:

```css
* {
    border: solid 3px black;
}
```

Questa regola dice:

- voglio un **bordo solido**
    
- di **3 pixel**
    
- di **colore nero**
    
- su **tutti i lati** (alto, basso, sinistra, destra)
    
- **per ogni elemento della pagina**
    

Quando avvii la pagina (anche con “Go Live”), il risultato è chiarissimo:  
tutto diventa pieno di bordi neri, uno per ogni singolo elemento.

È un effetto visivo “orrendo”, ma proprio per questo serve:  
per mostrare immediatamente come il selettore globale raggiunge qualsiasi nodo del DOM.

---

### **3. Confronto con un selettore normale**

Se invece scrivessimo:

```css
h1 {
    border: solid 3px black;
}
```

allora il bordo sarebbe applicato solo all’`h1`, e nella console vedremmo:

- `h1` → ha il bordo
    
- `p` → non lo ha
    
- `strong` → non lo ha
    
- `div` → non lo ha
    
- `body` → non lo ha
    

Nel momento in cui sostituiamo `h1` con `*`, la regola si applica a **tutti** gli elementi:

- `html`
    
- `body`
    
- `h1`
    
- `p`
    
- `strong`
    
- `div`
    
- qualunque altro elemento presente
    

Ed è esattamente quello che si vede passando con l’ispezione elemento nella console del browser.

---

### **4. Sovrascrivere il selettore globale**

Se vogliamo cambiare solo l’`h1`, possiamo semplicemente aggiungere un’altra regola più specifica:

```css
h1 {
    border: solid 3px red;
}
```

In questo caso:

- tutti gli elementi hanno bordo nero (perché colpiti da `*`)
    
- l’`h1` ha bordo rosso (perché sovrascrive la regola globale)
    

Questo dimostra immediatamente due cose:

1. Il selettore globale **dà lo stile base**, ma
    
2. qualunque selettore più specifico lo **sovrascrive**.
    

È un ottimo modo per visualizzare subito il concetto di specificità e cascata.

---

### **5. Quando si usa davvero il selettore globale**

Il selettore globale si usa pochissimo, perché colpire tutto raramente è ciò che desideriamo.  
Tuttavia, esistono alcuni casi reali:

#### **5.1. Resettare o neutralizzare lo stile base del browser**

Anche se non lo sappiamo ancora fare (lo vedremo più avanti), il selettore globale può essere usato per:

- togliere padding, margini default
    
- cancellare lo stile di base dello User Agent
    
- partire da un foglio completamente “bianco”
    

Esempio di reset molto comune:

```css
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
```

Questo non lo abbiamo ancora spiegato, ma è la base dei famosi _CSS reset_ o dei _normalize.css_.

#### **5.2. Applicare una regola a _tutto_, consapevolmente**

In casi rari, può servire davvero applicare uno stile a tutta la pagina.

Per esempio, se vuoi testare layout, spaziature, bordi o debug visuali.

---

### **6. Conclusione della lezione**

Il selettore globale è molto semplice:

- si scrive con `*`
    
- colpisce **ogni** elemento del DOM
    
- è potentissimo, quindi va usato con cautela
    
- serve spesso nei reset CSS o per scopi di debug
    
- nella pratica quotidiana si utilizza poco
    

Nel prossimo video il prof passa ai **selettori di elemento**, il tipo più basilare dopo quello globale.