## **Lezione 5: Elementi `br` e `hr` nel layout**

---

### **1. Perché `br` e `hr` stanno nel modulo “layout”**

Oggi vediamo due elementi molto semplici:

- `br`
    
- `hr`
    

Potresti pensare: _“Sono testuali, perché li metti nel discorso sulla struttura delle pagine?”_  
Perché, **nella pratica**, vengono spesso usati (male) per **gestire la disposizione visiva** dei contenuti:

- andare a capo “a mano”
    
- creare spazi vuoti
    
- tracciare righe di separazione
    

Quindi influenzano il **layout**, anche se non nel modo giusto.

---

### **2. `br` – Break (andare a capo**

`br` sta per **break** → “interruzione di riga”.

Serve a **mandare il testo a capo**, _dentro la stessa unità testuale_.

Esempio:

```html
<p>
  Questa è la prima riga.<br>
  Questa è la seconda riga.<br>
  Questa è la terza riga.
</p>
```

Risultato:

- ogni `br` inserisce un **a capo**
    
- non crea un nuovo paragrafo, solo una riga successiva
    

Se ne metti più di uno di fila:

```html
<p>
  Riga uno.<br><br><br>
  Riga due (dopo parecchio spazio).
</p>
```

Vedrai:

- più righe vuote tra “Riga uno.” e “Riga due.”
    

#### **2.1 L’uso sbagliato di `br` per lo spazio verticale**

Errore tipico:

```html
<p>Primo paragrafo...</p>
<br><br><br>
<p>Secondo paragrafo...</p>
```

Qui l’intenzione è:

> “Voglio più spazio tra i paragrafi.”

Questo è **sbagliato**, perché:

- `br` ha significato **testuale**: “vai a capo”
    
- un **paragrafo** è già un blocco autonomo (`<p>`), non un testo continuo in cui spezzi la riga
    
- lo “spazio” tra due blocchi va gestito con **CSS**, non con “a capo testuali”
    

Stessa pagina, fatto bene:

```html
<p>Primo paragrafo...</p>
<p>Secondo paragrafo...</p>
```

e nel CSS:

```css
p {
  margin-bottom: 50px;
}
```

Risultato:

- ottieni **spazio verticale pulito e controllabile**
    
- se vuoi più o meno spazio, modifichi **solo il CSS**
    

Quindi:

> `br` si usa per **andare a capo in una singola porzione di testo**  
> non per “fare spazio” tra blocchi.

---

### **3. `hr` – Riga orizzontale**

`hr` sta per **horizontal rule** (riga orizzontale).

È un elemento:

- **pre-formattato**: genera una riga orizzontale
    
- di solito con:
    
    - un po’ di **margin** sopra e sotto
        
    - un **bordo** di 1px
        

Esempio:

```html
<p>Sezione uno...</p>
<hr>
<p>Sezione due...</p>
```

Vedrai una linea tra i due paragrafi.

Se ispezioni l’elemento nel browser, noterai:

- dei margini automatici (spazio bianco sopra/sotto)
    
- un bordo (di solito `border-top: 1px solid`)
    

#### **3.1 Stilare un `hr`**

Puoi cambiargli stile via CSS, ad esempio:

```css
hr {
  padding: 10px;
}
```

otterrai una riga con più “spessore” visivo (perché il padding aumenta lo spazio interno intorno al bordo).

---

### **4. Possiamo ottenere lo stesso effetto con CSS**

Quello che fa `hr` (una riga orizzontale di separazione) si può replicare con un normale elemento + stile.

Esempio senza `hr`:

```html
<p>Sezione uno...</p>
<div class="bordo-sotto">
  <p>Sezione due...</p>
</div>
```

CSS:

```css
.bordo-sotto {
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
}
```

Oppure, se vuoi usare direttamente la classe sul paragrafo:

```html
<p class="bordo-sotto">Sezione uno...</p>
<p>Sezione due...</p>
```

```css
.bordo-sotto {
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
}
```

Risultato:

- ottieni lo **stesso effetto visivo** di una riga separatrice
    
- ma controllato tramite **classe**, quindi riutilizzabile ovunque
    

Questo approccio è spesso preferibile, perché:

- tutto ciò che riguarda **presentazione** e **spaziature** resta nel **CSS**
    
- puoi riusare `.bordo-sotto` ovunque senza inserire tag extra tipo `hr`
    

---

### **5. Cosa consigliare in pratica**

Riassumiamo:

- **`br`**
    
    - usalo **solo** quando stai dentro un testo e ti serve un **a capo logico**
        
        - esempio classico: indirizzi, poesie, testi brevi con righe specifiche
            
    - **non usarlo** per “fare spazio” tra paragrafi, sezioni, card, ecc.
        
    - spazio e layout vanno gestiti con:
        
        - `margin`
            
        - `padding`
            
        - altre proprietà CSS
            
- **`hr`**
    
    - è una riga di separazione **predefinita**
        
    - puoi usarlo per separare blocchi di contenuto, **se ti piace semanticamente** l’idea di “regola orizzontale”
        
    - in molti casi puoi invece:
        
        - usare una **classe** (es. `.bordo-sotto`)
            
        - applicare un `border-bottom` e dei margini o padding
            
    - in progetti più strutturati, spesso si preferisce la soluzione con classi, perché:
        
        - è più flessibile
            
        - si integra meglio con il sistema di design del sito
            

---

### **6. Conclusione**

Perché li abbiamo inseriti nel modulo “struttura/layout”?

- perché in giro per il web (soprattutto nei siti vecchi o scritti da principianti) vengono abusati:
    
    - `br` per fare “spazi”
        
    - `hr` per fare separatori ovunque
        
- volevamo mostrarti **subito** che:
    
    - **il layout si fa con CSS**, non con trucchi testuali
        
    - `br` e `hr` hanno un uso specifico e limitato
        

Da qui in poi, ogni volta che ti verrà voglia di mettere:

```html
<br><br><br>
```

pensaci un attimo e chiediti:

> “Questa è davvero una nuova riga di testo…  
> o è solo spazio che dovrei gestire con **margin/padding**?”

Prossima tappa: **esercitazione di fine capitolo**, dove metteremo insieme tutti questi elementi di layout (semantici + non semantici + classi/ID) in una struttura concreta.