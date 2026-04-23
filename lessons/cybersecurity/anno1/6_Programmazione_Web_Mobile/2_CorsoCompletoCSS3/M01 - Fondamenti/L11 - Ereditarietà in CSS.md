## **Lezione 11 – Ereditarietà in CSS**

### **1. Che cos’è l’ereditarietà in CSS**

L’ereditarietà è uno dei concetti fondamentali del CSS.  
Nel linguaggio comune “ereditare” significa ricevere qualcosa da chi sta sopra di noi.  
Nel CSS il concetto è identico: alcuni elementi ricevono automaticamente certe proprietà dai loro “genitori” nella struttura del documento.

Un esempio immediato è il colore del testo.  
Se nel `body` scrivi:

```
body {
    color: yellow;
}
```

allora **tutto il testo della pagina diventa giallo**, perché tutti gli elementi all’interno del `body` ereditano quella proprietà, a meno che non venga specificato un colore diverso.

Aprendo gli Strumenti di Sviluppo, Chrome mostra chiaramente la voce:

> “Ereditato da body”

che indica da dove proviene quel valore.

In questo modo, `h1`, `p`, `strong` e qualunque altro elemento senza un proprio valore dichiarato prendono esattamente quello del genitore.

---

### **2. Ereditarietà e cascata: come si influenzano**

La cascata stabilisce che la regola più vicina all’elemento ha la precedenza.  
Perciò, anche se un elemento eredita una proprietà, questa può essere sovrascritta con una dichiarazione più specifica.

Per esempio:

```
h1 {
    color: red;
}
```

L’`h1` diventa rosso, nonostante l’ereditarietà dal `body`.  
Il `p`, invece, rimane giallo perché non ha regole proprie.

Quindi:

- ereditarietà → fornisce un valore di base
    
- cascata → decide chi vince quando ci sono più regole in competizione
    

Capire questo meccanismo è essenziale per scrivere CSS coerenti.

---

### **3. Il valore speciale `inherit`**

CSS permette anche di **forzare** l’ereditarietà tramite il valore:

```
inherit
```

Può essere usato su _qualsiasi_ proprietà, non solo sui colori.  
Significa: _“usa esattamente il valore del genitore”_.

Esempio:

```
h1 {
    color: inherit;
}
```

In questo caso l’`h1` assumerà sempre il colore del `body`, qualunque esso sia.  
È un modo per dire:

> “Non voglio alternative: prendi il colore del genitore.”

---

### **4. Perché `inherit` è davvero utile**

All’inizio può sembrare un concetto un po’ astratto, ma diventa utilissimo quando un sito ha più pagine, ognuna con il proprio stile.

Immagina:

- una pagina con `body { color: white; background: black }`
    
- un’altra con `body { color: black; background: white }`
    

Se gli `h1` hanno `color: inherit`, allora:

- nella pagina scura → gli `h1` diventano bianchi
    
- nella pagina chiara → gli `h1` diventano neri
    

Tutto automaticamente, senza riscrivere CSS per ogni pagina.

È un ottimo modo per garantire coerenza e ridurre duplicazioni.

Ovviamente, se successivamente aggiungi un’altra regola più specifica, la cascata la farà prevalere su `inherit`.  
Gli Strumenti di Sviluppo mostrano sempre quale regola vince e quale viene sovrascritta.

---

### **5. Conclusione della lezione**

L’ereditarietà è un pilastro del CSS.  
Serve per:

- propagare valori in modo automatico
    
- ridurre ripetizioni
    
- mantenere lo stile coerente
    
- controllare in modo preciso cosa può cambiare e cosa no
    

Ma l’argomento che crea più problemi in assoluto non è l’ereditarietà: è la **specificità**.  
Ed è proprio ciò che affronteremo nella prossima lezione.

Prepara la mente, perché lì inizia davvero la parte “da strapparsi i capelli”.