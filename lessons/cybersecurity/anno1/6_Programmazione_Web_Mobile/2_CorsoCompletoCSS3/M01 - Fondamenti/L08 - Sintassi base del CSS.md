## **Lezione 8 – Sintassi base del CSS**

In questa lezione finalmente vediamo _come si scrive_ il CSS.  
La struttura è in realtà molto semplice: sono sempre le stesse tre cose ripetute all’infinito, solo con selettori e regole diverse.

Ti basta fissare bene questi vocaboli, perché li useremo continuamente:

- selettore
    
- blocco di regole (tra parentesi graffe)
    
- proprietà (o attributo)
    
- valore della proprietà
    

---

### **Struttura generale di una regola CSS**

La forma più semplice possibile è questa:

```css
h1 {
    color: red;
}
```

Qui abbiamo:

- `h1` → è il **selettore**
    
- `{ ... }` → sono le **parentesi graffe** che racchiudono le regole del selettore
    
- `color: red;` → è una **regola** composta da:
    
    - una **proprietà** (o attributo) → `color`
        
    - un **valore** → `red`
        
    - un **punto e virgola** alla fine → `;`
        

Questa è la “unità base” del CSS.

---

### **Il selettore**

Il selettore dice al browser: “Su quali elementi devo applicare queste regole?”.

Nell’esempio:

```css
h1 {
    color: red;
}
```

il selettore `h1` prende **tutti** gli elementi `<h1>` presenti nel DOM, non solo uno.  
Se nella pagina hai un solo `h1`, viene colpito solo quello; se ne hai dieci, vengono colpiti tutti e dieci.

Più avanti vedremo molti altri tipi di selettori:

- per classe (`.classe`)
    
- per id (`#id`)
    
- per discendenza (un elemento dentro un altro)
    
- per vicinanza (due elementi vicini)
    
- e così via
    

Per ora ti basta memorizzare che il selettore è la “freccia” che indica _chi_ vogliamo modificare.

---

### **Le parentesi graffe: il blocco di dichiarazioni**

Subito dopo il selettore ci sono le parentesi graffe:

```css
h1 {
    /* qui dentro vanno le regole */
}
```

Servono a dire: “Tutte le regole che scrivo qui dentro appartengono a questo selettore”.

Se dimentichi la parentesi di chiusura, il browser non capisce più dove finiscono le regole per `h1` e dove iniziano le altre: il file CSS diventa ambiguo.

In pratica, ogni blocco è fatto così:

```css
selettore {
    proprietà: valore;
    proprietà: valore;
    ...
}
```

Questa struttura ti entrerà in automatico nelle dita:  
scrivi il selettore, apri `{`, premi Invio, Visual Studio Code ti chiude da solo la parentesi e ti allinea il cursore dentro il blocco.

---

### **La regola: proprietà + valore**

Ogni riga dentro le parentesi graffe è una **regola** (o dichiarazione).  
Una regola dice al browser: “Per questo selettore, questa proprietà deve assumere questo valore”.

Esempio:

```css
h1 {
    color: blue;
    background-color: red;
    margin-top: 30px;
}
```

Qui abbiamo tre regole:

- `color: blue;`
    
- `background-color: red;`
    
- `margin-top: 30px;`
    

Ogni regola è formata da:

- **proprietà** (o attributo CSS): `color`, `background-color`, `margin-top`
    
- **valore**: `blue`, `red`, `30px`
    
- **punto e virgola**: chiude la regola
    

---

### **Proprietà (attributi) CSS**

Le proprietà sono i “campi” che vogliamo modificare di un elemento:

- colore del testo (`color`)
    
- sfondo (`background-color`)
    
- spazi esterni (`margin`)
    
- spazi interni (`padding`)
    
- bordi (`border`)
    
- font (`font-family`, `font-size`, `font-weight`)
    
- comportamento di layout (`display`, `position`, ecc.)
    

Puoi pensarle così: l’elemento HTML è un oggetto con tanti “parametri” regolabili.  
Ogni proprietà agisce su uno di questi parametri.

La sintassi è sempre:

```css
proprietà: valore;
```

Non si può invertire e non si può togliere i due punti.

---

### **Valori: tipi diversi a seconda della proprietà**

I valori non sono tutti uguali.  
Dipendono dalla proprietà che stai impostando:

- per `color` puoi usare:
    
    - nomi di colori (`red`, `blue`, `green`)
        
    - codici esadecimali (`#ff0000`)
        
    - `rgb(...)`, `rgba(...)`, ecc.
        
- per `margin-top` userai unità di misura:
    
    - `px`, `em`, `rem`, `%`, ecc.
        
- per `display` userai valori predefiniti:
    
    - `block`, `inline`, `flex`, `grid`, `none`, ecc.
        

Quindi:

- alcune proprietà accettano **valori numerici con unità** (es. `30px`, `2rem`, `50%`),
    
- altre accettano **parole chiave** (es. `block`, `inline`, `bold`),
    
- altre ancora accettano **funzioni** (es. `rgb(255, 0, 0)`).
    

Non ha senso, ad esempio, scrivere:

```css
margin-top: blue;
```

perché `margin-top` si aspetta una distanza, non un colore.

Impareremo le combinazioni corrette proprietà/valore nel corso, ma la cosa importante adesso è capire che:

- ogni proprietà ha un **insieme finito di valori validi**,
    
- o almeno un certo tipo di valori (numeri con unità, nomi di colori, ecc.).
    

---

### **Suggerimenti dei valori in Visual Studio Code**

Un grande aiuto viene dall’editor:

Se scrivi ad esempio:

```css
display: 
```

Visual Studio Code ti propone in automatico i valori validi:

- `block`
    
- `inline`
    
- `inline-block`
    
- `flex`
    
- `grid`
    
- `none`
    
- ecc.
    

Lo stesso vale per `color`, `position`, `text-align`, e tutte le proprietà più comuni.  
Questo ti permette sia di scoprire nuovi valori, sia di evitare errori di battitura.

Nelle prime fasi del corso è utile restare nel file `<style>` dentro l’HTML e sperimentare lì: ti basta scrivere la proprietà, mettere i due punti e lasciare che l’editor ti suggerisca i possibili valori.

---

### **Ricapitolando la sintassi base**

La forma più “povera” ma completa di una regola CSS è:

```css
selettore {
    proprietà: valore;
}
```

Ad esempio:

```css
h1 {
    color: red;
}
```

Da qui tutto il CSS non fa altro che crescere in ampiezza, ma la struttura mentale resta identica:

- scegli **chi** vuoi colpire → selettore
    
- apri il **blocco** con `{`
    
- per ogni cosa che vuoi cambiare scrivi una **proprietà** e un **valore**
    
- chiudi con `}`
    

Questa è la base assoluta di tutto quello che faremo.  
Nella prossima lezione andremo a vedere un altro pezzo fondamentale: **i commenti in CSS** e come usarli per tenere il codice chiaro e leggibile mentre i file crescono.