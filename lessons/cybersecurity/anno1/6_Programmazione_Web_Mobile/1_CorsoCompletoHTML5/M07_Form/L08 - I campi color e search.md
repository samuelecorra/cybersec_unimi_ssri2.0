## **Lezione 8: I campi `color` e `search`**

---

### **1. Introduzione**

I prossimi due input sono tra i più semplici da usare, ma anche tra i più comodi che HTML5 ci mette a disposizione:

- `type="color"` → il **color picker** nativo del browser
    
- `type="search"` → il campo di ricerca con funzionalità extra
    

Sono input estremamente facili ma molto utili, soprattutto per migliorare **l’esperienza utente** senza dover aggiungere JavaScript o CSS avanzati.

---

## **2. `type="color"` – il color picker**

```html
<input type="color" id="colore" name="colore">
```

Appena lo scrivi, il browser trasforma l’input in un **selettore grafico di colori**.

Risultato:

- un quadratino con il colore attuale
    
- cliccandolo si apre un pannello di selezione
    
- puoi scegliere qualsiasi colore con:
    
    - ruota cromatica
        
    - slider di luminosità
        
    - valori **RGB**
        
    - valori **HSL**
        
    - valori **HEX** (es: `#ff0000`)
        

Esempio pratico:

- scegli un colore → il valore restituito sarà in HEX, es:  
    `#1a73e8`
    

### **2.1. Perché è utile**

Prima di `type="color"`, per ottenere un color picker serviva:

- JavaScript
    
- una libreria esterna
    
- HTML complicato
    
- tanto CSS
    

Adesso:  
**una sola riga di codice**.

È perfetto per:

- personalizzare temi
    
- scegliere colori di profili o avatar
    
- configuratori grafici
    
- editor semplici
    

Più avanti, con il CSS, potremo anche **stilizzarlo** per renderlo meno “grezzo”.

---

## **3. `type="search"` – il campo ricerca intelligente**

```html
<input type="search" id="cerca" name="cerca">
```

A prima vista sembra un normale `text`.

Ma la differenza si nota nell’esperienza d’uso.

### **3.1. La X magica (clear button)**

Quando scrivi qualcosa:

- compare una piccola **X** sul lato destro
    
- cliccando la X → il campo si svuota automaticamente
    

Questo fa la differenza soprattutto nei form lunghi, nelle search bar dei siti, nei pannelli di filtraggio, ecc.

### **3.2. Prima: complicatissimo. Ora: immediato.**

In passato per ottenere una X dovevi:

- aggiungere un’icona
    
- posizionarla con CSS
    
- intercettare il click con JavaScript
    
- svuotare il campo da script
    

Con `type="search"`:

- tutto è integrato
    
- nessun codice extra
    
- UX già curata dal browser
    

### **3.3. Comportamento identico su mobile**

Su smartphone:

- la tastiera si adatta leggermente
    
- i sistemi operativi aggiungono pulsanti extra
    

È un piccolo dettaglio che però rende le search bar molto più rapide e naturali.

---

## **4. Perché usare `search` e non `text`?**

Usa `search` quando:

- il campo è chiaramente pensato per ricerche
    
- vuoi la possibilità di svuotarlo velocemente
    
- vuoi un comportamento standard su tutti i browser
    

Usa `text` quando:

- il campo non ha nulla a che fare col concetto di ricerca
    

Un singolo cambio di parola (`text → search`) ti regala una UI migliore senza costi extra.

---

## **5. Riepilogo**

|Type|Cosa fa|Vantaggi|
|---|---|---|
|`color`|apre un color picker nativo|zero JS, zero CSS, valore HEX pronto|
|`search`|campo di ricerca con pulsante “clear”|UX migliorata, funzionalità integrate|

---

## **6. Prossima lezione**

Adesso passiamo agli input più “interattivi”:

- `checkbox`
    
- `radio`
    
- `file`
    

Sono fondamentali in praticamente tutti i form reali, e richiedono una spiegazione dedicata per capirne bene struttura, comportamento e differenze.

Andiamo avanti.