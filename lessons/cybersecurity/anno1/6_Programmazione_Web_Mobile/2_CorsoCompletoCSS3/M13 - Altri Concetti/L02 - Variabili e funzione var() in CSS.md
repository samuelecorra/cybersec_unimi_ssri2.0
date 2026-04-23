## **Lezione 2: Variabili e funzione `var()` in CSS**

### **1. Cos’è una variabile (ripasso “alla buona”)**

Partiamo dal concetto generale di **variabile**, che conosci già da matematica / programmazione.

In matematica:

- scriviamo ad esempio  
    $x = 5$
    
- allora $x + 5$ significa in realtà $5 + 5 = 10$
    

Se poi cambiamo:

- $x = 6$  
    allora $x + 5 = 11$
    

L’idea è:

- **x non è il numero in sé**,
    
- x è **un contenitore** che _in questo momento_ vale 5, o 6, o qualcos’altro.
    

---

### **2. Perché non scrivere direttamente il numero?**

Immagina una formula un po’ più complessa:

```text
peso + 5 * peso² + petto
```

Se fai il “brutale” e scrivi solo numeri:

```text
60 + 5 * 60² + 60
```

e poi scopri che:

- il **peso** passa da 60 a 70,
    
- ma la **circonferenza del petto** resta 60,
    

hai diversi problemi:

- ci sono tanti `60` e **non sai più quale è il peso** e quale è il petto,
    
- per cambiare il peso devi andare a cercare **tutti i 60 giusti**,
    
- se ce ne sono 10, 20, 100, è un incubo.
    

Con le variabili invece:

```text
peso = 60
petto = 60

peso + 5 * peso² + petto
```

Se il peso diventa 70:

```text
peso = 70
petto = 60
```

- cambi **una sola riga**,
    
- tutta la formula si aggiorna automaticamente,
    
- e leggendo la formula capisci subito cosa rappresenta ogni valore (peso, petto, ecc.).
    

Questo è esattamente il motivo per cui in **programmazione** le variabili sono fondamentali:  
ti permettono di:

- **dare un nome a un valore** (leggibilità),
    
- **riusare quel valore** in tanti punti (mantenibilità),
    
- **cambiarlo in un solo posto** (scalabilità).
    

---

### **3. Perché ci servono le variabili in CSS?**

Domanda naturale: _“Ok, ma che c’entra con il CSS?”_

In CSS succede spesso una cosa simile:

- usi lo stesso **valore** in tanti punti (es. stesso colore, stesso border-radius, stessi spazi),
    
- dopo qualche mese / anno il cliente o il designer ti dice:
    
    - “Cambiamo il colore di accento.”
        
    - “Cambiamo il border-radius standard.”
        
    - “Modernizziamo il design.”
        

Se hai scritto il CSS così:

```css
border-radius: 20px;
/* … */
button {
  border-radius: 20px;
}
/* … */
input {
  border-radius: 20px;
}
```

e ci sono **20, 30, 50** occorrenze di `20px`, devi:

- cercare tutti,
    
- sperare di non confondere un `20px` che è border-radius con un `20px` che è margin, padding, ecc.
    

Molto meglio avere:

- una sola “sorgente” per quel valore,
    
- e poi riutilizzarlo ovunque.
    

Esattamente come con `peso` e `petto`.

---

### **4. Come si dichiarano le variabili in CSS (`:root` + `--nome`)**

In CSS le variabili si chiamano **custom properties** e si dichiarano così:

- il selettore più comune è `:root` (la “radice” del documento),
    
- il nome di una variabile CSS inizia **sempre** con `--`.
    

Esempio:

```css
:root {
  --radius-20: 20px;
  --accent-color: #8b5cf6;
  --accent-color-dark: #7c3aed;
}
```

- `:root` è l’elemento radice (equivalente all’`html`), quindi queste variabili sono **visibili ovunque** nel CSS.
    
- `--radius-20`, `--accent-color`, `--accent-color-dark` sono i nostri “contenitori di valore”.
    

Puoi anche usare nomi con trattini, come fa CSS per `margin-top`:

```css
:root {
  --border-radius-base: 20px;
  --color-accent: #8b5cf6;
}
```

---

### **5. Come usare le variabili: la funzione `var()`**

Per leggere il valore di una variabile usi la funzione **`var()`**:

```css
form {
  border-radius: var(--border-radius-base);
}

button {
  background-color: var(--color-accent);
  border-radius: var(--border-radius-base);
}

input {
  border-radius: var(--border-radius-base);
}
```

La funzione `var()` fa esattamente questo:

> “Prendi il valore della variabile `--border-radius-base` e usalo qui.”

Sintassi generale:

```css
var(--nome-variabile)
```

Volendo puoi specificare anche un **valore di fallback**:

```css
color: var(--color-titolo, black);
```

Se `--color-titolo` non è definita, userà `black`.

---

### **6. Esempio pratico: border-radius condiviso**

Supponiamo che nel sito ti piaccia un border-radius `20px` e lo usi ovunque:

```css
:root {
  --border-radius-base: 20px;
}

form {
  border-radius: var(--border-radius-base);
}

button {
  border-radius: var(--border-radius-base);
}

input {
  border-radius: var(--border-radius-base);
}

textarea {
  border-radius: var(--border-radius-base);
}
```

Se un domani il designer dice:

> “Voglio gli angoli meno arrotondati, mettiamo 8px.”

Ti basta cambiare:

```css
:root {
  --border-radius-base: 8px;
}
```

E **tutto il sito** si aggiorna da solo: form, bottoni, input, textarea…

---

### **7. Esempio pratico: palette di colori**

Nella realtà, per un sito un minimo strutturato, hai una **palette**:

- colore di sfondo,
    
- colore del testo,
    
- colore di accento,
    
- colore di accento scuro (hover),
    
- colori per errori, successi, warning,
    
- colori per bottoni disabilitati, input validi/invalidi, ecc.
    

Invece di ripetere questi HEX ovunque:

```css
button {
  background-color: #8b5cf6;
}

button:hover {
  background-color: #7c3aed;
}

input:valid {
  border-color: #16a34a;
}

input:invalid {
  border-color: #dc2626;
}
```

definisci la palette a livello di `:root`:

```css
:root {
  --color-accent: #8b5cf6;
  --color-accent-dark: #7c3aed;

  --color-success: #16a34a;
  --color-error: #dc2626;
}
```

e poi:

```css
button {
  background-color: var(--color-accent);
}

button:hover {
  background-color: var(--color-accent-dark);
}

input:valid {
  border-color: var(--color-success);
}

input:invalid {
  border-color: var(--color-error);
}
```

Se un giorno cambi brand / palette:

- aggiorni 4–5 variabili in `:root`,
    
- tutto il sito segue il nuovo design,
    
- non devi rincorrere i colori `#8b5cf6` sparsi in 1000 righe di CSS.
    

---

### **8. Perché usare `:root` invece di dichiarare le variabili dentro i singoli elementi?**

Potresti dichiarare variabili anche dentro un singolo selettore:

```css
form {
  --border-radius-base: 20px;
}
```

Ma:

- quella variabile sarebbe **visibile solo dentro il form e nei suoi figli**,
    
- fuori dal form non potresti usare `var(--border-radius-base)`.
    

Usare `:root` significa:

- “Queste sono le **variabili globali**, valide per tutto il documento”.
    

È il posto ideale per:

- palette colori,
    
- border-radius standard,
    
- spaziatura standard,
    
- dimensioni tipografiche di base,
    
- ecc.
    

---

### **9. Vantaggi reali: scalabilità e manutenzione**

Ricapitolando, usare variabili + `var()` porta vantaggi enormi:

1. **Leggibilità**
    
    - `border-radius: var(--border-radius-base);`  
        è molto più chiaro di `border-radius: 20px;` ripetuto ovunque.
        
    - Da solo il nome ti dice che quello è il “border radius standard”.
        
2. **Coerenza visuale**
    
    - Se il tuo design prevede sempre lo stesso radius / colore / padding,
        
    - le variabili ti costringono a mantenere uno stile coerente.
        
3. **Manutenzione ridicola**
    
    - Cambi un colore o un radius?  
        → modifichi **una riga** sotto `:root`, il resto si aggiorna.
        
4. **Evoluzione del progetto nel tempo**
    
    - Se dopo 5–10 anni rifai il look al sito,
        
    - molte delle modifiche si ridurranno a:
        
        - cambiare palette,
            
        - cambiare radius di base,
            
        - cambiare magari qualche misura tipografica.
            
5. **Compatibilità con framework moderni**
    
    - Molti framework CSS moderni (come Tailwind e affini) usano **intensivamente variabili e `var()`** sotto al cofano.
        
    - Capire come funzionano ti permette di leggere il loro CSS generato senza sentirlo “magico”.
        

---

### **10. Conclusione**

Le variabili CSS + funzione `var()` sono:

- la traduzione, lato stile, dello stesso concetto di variabile che userai in JavaScript, Python, ecc.;
    
- uno strumento fondamentale per rendere il tuo CSS:
    
    - **scalabile**,
        
    - **leggibile**,
        
    - **facile da manutenere**.
        

D’ora in poi, ogni volta che ti accorgi di ripetere lo **stesso valore** in più punti (soprattutto colori, radius, spaziature, dimensioni di font):

> fermati e chiediti:  
> “Ha senso trasformarlo in una variabile CSS?”

Nella sezione successiva continueremo ad aggiungere altri tasselli del “puzzle CSS”, sempre con questa ottica di renderlo più modulare e gestibile.