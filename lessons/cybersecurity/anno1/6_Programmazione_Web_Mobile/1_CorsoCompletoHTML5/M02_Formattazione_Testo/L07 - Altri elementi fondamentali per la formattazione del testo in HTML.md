## **Lezione 7: Altri elementi fondamentali per la formattazione del testo in HTML**

Oggi affrontiamo una lunga serie di elementi HTML dedicati alla **formattazione semantica del testo**. Questi tag sono tra i più ignorati dagli sviluppatori principianti, ma fanno la differenza tra:

- _markup improvvisato_
    
- _e markup professionale, chiaro, accessibile e semanticamente corretto._
    

Li analizziamo uno per uno, con esempi ed eventuali casi d’uso.

---

## **1. `<abbr>` — Abbreviazioni e acronimi**

Serve per indicare abbreviazioni o acronimi.

### **Esempio corretto**

```html
<p>
  La <abbr title="Federazione Italiana Giuoco Calcio">FIGC</abbr> ha aggiornato il regolamento.
</p>
```

### Perché è importante?

- Lo screen reader può pronunciare l’abbreviazione nel modo corretto.
    
- Al passaggio del mouse, il browser mostra il significato (se c’è l’attributo `title`).
    
- La prima volta è **buona pratica** spiegare l’acronimo come testo normale:
    

```html
<p>
  La Federazione Italiana Giuoco Calcio (FIGC) ha deciso di...
</p>
```

Dalla seconda volta in poi puoi usare solo `<abbr>`.

---

## **2. `<address>` — Informazioni di contatto**

È riservato a indirizzi fisici, email, recapiti ufficiali dell’autore di un contenuto.

### **Esempio semplice**

```html
<address>
  Via Torino 8<br />
  20100 Milano<br />
  Italia
</address>
```

### **Esempio complesso**

```html
<address>
  Luca Rossi<br />
  Via Torino 8<br />
  20100 Milano<br />
  <ul>
    <li>Telefono: 328 555 1234</li>
    <li>Email: luca.rossi@example.com</li>
  </ul>
</address>
```

Il browser lo renderizza in _italic_ per convenzione.

---

## **3. `<sup>` e `<sub>` — Apici e pedici**

Sono usati in chimica, matematica, date inglesi, formule.

### **Esempi**

- Potenze:
    

```html
<p>x<sup>2</sup> + x<sup>3</sup></p>
```

- Formula chimica:
    

```html
<p>H<sub>2</sub>O</p>
```

- Date inglesi:
    

```html
<p>Today is the 25<sup>th</sup> of February.</p>
```

---

## **4. Elementi per rappresentare codice informatico**

Questi sono fondamentali per articoli tecnici, documentazione e blog di programmazione.

---

### **4.1 `<code>` — Codice inline**

Per rappresentare frammenti brevi di codice all’interno di una frase.

```html
<p>La funzione <code>getElementById()</code> restituisce il nodo selezionato.</p>
```

---

### **4.2 `<pre>` — Testo preformattato**

Mantiene:

- spazi,
    
- indentazioni,
    
- a capo.
    

È ideale per blocchi di codice.

```html
<pre>
if (x >= 5) {
    console.log("OK");
}
</pre>
```

Per stile migliore si combina quasi sempre con `<code>`:

```html
<pre><code>
if (x >= 5) {
    console.log("OK");
}
</code></pre>
```

---

### **4.3 `<var>` — Variabile**

Usato in matematica, pseudocodice, formule, documentazione tecnica.

```html
<p>La variabile <var>x</var> rappresenta il numero di elementi.</p>
```

---

### **4.4 `<kbd>` — Input da tastiera**

Rappresenta una combinazione di tasti.

Perfetto nelle guide.

```html
<p>Premi <kbd>Alt</kbd> + <kbd>Shift</kbd> + <kbd>F</kbd> per formattare il documento.</p>
```

---

### **4.5 `<samp>` — Output del programma**

Serve per mostrare l’output di un terminale, di un compilatore, di un log.

```html
<samp>
Pinging google.com with 32 bytes of data:
Reply from 142.250.184.78: bytes=32 time=12ms TTL=111
</samp>
```

Combinato con `<pre>` mantiene perfettamente la formattazione:

```html
<pre><samp>
Pinging google.com with 32 bytes of data:
Reply from 142.250.184.78: bytes=32 time=12ms TTL=111
</samp></pre>
```

---

## **5. `<time>` — Date semanticamente corrette**

Serve per indicare una data o un orario in modo comprensibile ai motori di ricerca.

### **Esempio in un articolo**

```html
<p>
  Articolo pubblicato il 
  <time datetime="2023-06-21">21 giugno 2023</time>.
</p>
```

### Il formato `datetime`

Deve essere:

```
YYYY-MM-DD
```

Oppure:

```
YYYY-MM-DDTHH:MM
```

Per orari con fuso orario:

```
YYYY-MM-DDTHH:MMZ
```

### Perché usarlo?

- Google identifica correttamente la data di pubblicazione.
    
- Risulta utile nei microdati e nella SEO articolistica.
    
- Aiuta gli screen reader a trattarla come una data reale.
    

---

## **6. Riepilogo generale**

|Tag|Significato|Caso d’uso|
|---|---|---|
|`<abbr>`|Abbreviazione/acronimo|FIGC, ONU, HTML…|
|`<address>`|Contatti / autore pagina|Recapiti, email, indirizzi|
|`<sup>`|Apice|x², date inglesi|
|`<sub>`|Pedice|H₂O, formule|
|`<code>`|Codice inline|Funzioni, variabili|
|`<pre>`|Testo preformattato|Blocchi di codice|
|`<var>`|Variabile|Matematica, pseudocodice|
|`<kbd>`|Input da tastiera|Combinazioni di tasti|
|`<samp>`|Output di programma|Terminale, log|
|`<time>`|Data/orario semanticamente corretti|Articoli, blog|

---

## **7. Conclusione**

Questa lezione chiude la parte dedicata agli **elementi avanzati di formattazione testuale**.  
Ora hai un arsenale completo per scrivere:

- articoli professionali,
    
- documentazione tecnica,
    
- pagine web accessibili e ben strutturate,
    
- contenuti da blog o tutorial.
    

Nel prossimo video affronteremo **l’esercizio finale del modulo**, dove andrai a combinare tutti gli elementi imparati finora.