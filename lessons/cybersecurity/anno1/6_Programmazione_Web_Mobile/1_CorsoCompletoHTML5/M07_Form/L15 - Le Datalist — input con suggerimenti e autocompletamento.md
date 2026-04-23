## **Lezione 15: Le Datalist — input con suggerimenti e autocompletamento**

---

### **1. Introduzione**

La **datalist** è un ibrido tra:

- un normale `input type="text"`
    
- una `select` con opzioni predefinite
    

La differenza fondamentale è che:

- **la select obbliga** a scegliere una delle opzioni
    
- **la datalist suggerisce**, ma l’utente può scrivere quello che vuole
    

È quindi perfetta per:

- hobby
    
- città
    
- parole chiave
    
- ricerche
    
- campi semi-strutturati
    
- campi dove vuoi aiutare, ma non vincolare
    

---

## **2. Struttura base**

### **Input con datalist associata**

```html
<label for="hobby">Hobby</label>
<input type="text" id="hobby" name="hobby" list="hobby-list">

<datalist id="hobby-list">
  <option value="Calcio">
  <option value="Basket">
  <option value="Golf">
  <option value="Ciclismo">
</datalist>
```

---

## **3. Come funziona**

- L’utente può scrivere **qualsiasi cosa**
    
- Se ciò che scrive inizia a combaciare con un’opzione, il browser gli mostra un menu di **suggerimenti**
    
- L’utente può:
    
    - accettare il suggerimento
        
    - continuare a scrivere liberamente
        
    - scrivere qualcosa che non esiste nella lista
        

Questo è diverso dalla select, dove i valori sono _chiusi_.

---

## **4. Collegamento tra input e datalist: l’attributo `list`**

La parte più importante è:

```html
list="hobby-list"
```

Qui stiamo dicendo:

> “Caro input, se vuoi puoi usare questa datalist chiamata `hobby-list` come sorgente dei suggerimenti”.

Se manca `list=""`, la datalist **non funziona**, anche se esiste nel codice.

---

## **5. Differenza visiva e pratica con le Select**

### **SELECT (rigida)**

- L’utente _può_ scegliere solo tra le opzioni fornite
    
- Non può scrivere altro
    
- Perfetta per categorie, stati, tag, valori chiusi
    

### **DATALIST (flessibile)**

- L’utente può scrivere ciò che vuole
    
- Le opzioni appaiono solo come suggerimenti
    
- Perfetta per hobby, tag liberi, ricerche, autocompletamenti
    

---

## **6. Esempio pratico: suggerimenti dinamici per hobby**

Scrivendo:

- “c” → compaiono “Calcio” e “Ciclismo”
    
- “ba” → suggerisce “Basket”
    
- scrivendo “qwerty” → _nessun problema_, campo accettato
    

L’utente è libero e allo stesso tempo guidato.

---

## **7. Riepilogo completo**

|Funzione|Dove?|Effetto|
|---|---|---|
|Suggerimenti non vincolanti|`<datalist>`|mostra opzioni|
|Collegamento con input|`list="id"`|attiva autocompletamento|
|Input libero|`<input type="text">`|l’utente può digitare qualsiasi testo|
|Opzioni suggerite|`<option value="">`|appaiono mentre si scrive|

Le datalist sono una soluzione elegante per migliorare l’esperienza utente senza imporre vincoli.

---

## **8. Conclusione**

Con la datalist ottieni:

- autocomplete nativo
    
- semplicità
    
- zero JavaScript per l’autocompletamento
    
- libertà totale dell’utente