## **Lezione 8 – I Selettori di Attributo**

### **1. Introduzione: cosa sono i selettori di attributo**

Continuiamo il viaggio nel mondo dei selettori CSS e arriviamo a una categoria molto potente e, allo stesso tempo, un po’ densa:  
i **selettori di attributo**.

Con questi selettori possiamo dire:

- “Prendimi tutti gli elementi che **hanno** un certo attributo”
    
- “Prendimi tutti gli elementi dove l’attributo X è **uguale** a un certo valore”
    
- “Prendimi tutti gli elementi dove l’attributo X **inizia con**, **finisce con**, **contiene** una certa stringa”
    

E così via.

Il prof fa subito una premessa importante:

- ci sono **molti casi diversi**
    
- è **normale** non ricordarli tutti al volo
    
- è normale sentirsi “in overload” se stai guardando tanti video di fila
    

Obiettivo minimo della lezione:

- capire **la sintassi base**  
    (come si scrive un selettore di attributo)
    
- memorizzare i casi fondamentali:
    
    - `[attributo]`
        
    - `[attributo="valore"]`
        
    - `[attributo^="valore"]` (inizia con)
        
    - `[attributo$="valore"]` (finisce con)
        
    - `[attributo*="valore"]` (contiene)
        

Il resto lo potrai sempre ripassare su documentazione, articoli, o riguardando la lezione.

---

### **2. Ripasso HTML: label, input e attributi**

Partiamo da un esempio che conosciamo già dall’HTML:  
un piccolo form con **label** e **input**.

```html
<label for="email">Email</label>
<input id="email" type="email" name="email">

<label for="password">Password</label>
<input id="password" type="password" name="password">
```

Qui vediamo vari **attributi**:

- sulla `label`:
    
    - `for="email"`
        
    - `for="password"`
        
- sull’`input`:
    
    - `id="email"` / `id="password"`
        
    - `type="email"` / `type="password"`
        
    - `name="email"` / `name="password"`
        

La coppia `label` + `for` è fondamentale per l’**accessibilità**:

- `for="email"` collega la label al campo con `id="email"`
    
- cliccando sulla label, il focus va sull’input collegato
    
- gli screen reader usano questa relazione per descrivere meglio il campo
    

Tutto questo lo conosciamo da HTML.  
Ora, in CSS, useremo **quegli stessi attributi** per selezionare gli elementi.

---

### **3. Sintassi base: selezionare elementi che hanno un certo attributo**

Forma base del selettore di attributo:

```css
[attributo] {
    /* regole CSS */
}
```

Questo significa:

> “Prendimi tutti gli elementi che **possiedono** questo attributo, indipendentemente dal valore.”

Esempio:

```css
[for] {
    color: red;
}
```

Se applichiamo questo CSS al nostro HTML:

```html
<label for="email">Email</label>
<label for="password">Password</label>
```

risultato:

- entrambe le `label` diventano rosse,  
    perché entrambe **hanno** l’attributo `for`.
    

Possiamo anche essere più specifici:

```css
label[for] {
    color: red;
}
```

Significa:

> “Prendimi solo gli elementi `label` che hanno un attributo `for`.”

In questo caso l’effetto è lo stesso, ma la selezione è più chiara e più specifica.  
È utile quando:

- lo stesso attributo potrebbe comparire su tag diversi
    
- e tu vuoi stilare solo uno specifico tipo di elemento
    

---

### **4. Selettori di attributo con valore esatto: `[attr="valore"]`**

Ora passiamo al caso in cui non ci basta “avere un attributo”, ma vogliamo che abbia **un valore preciso**.

Sintassi:

```css
[attributo="valore"] {
    /* regole */
}
```

E, più specifico:

```css
elemento[attributo="valore"] {
    /* regole */
}
```

Esempio sul nostro form:

```css
label[for="email"] {
    color: red;
}
```

In questo caso:

- solo la label con `for="email"` diventa rossa
    
- la label con `for="password"` resta con il colore di default
    

Più precisamente:

- `[for="email"]` → qualunque elemento con attributo `for` uguale a `"email"`
    
- `label[for="email"]` → solo gli elementi `label` che hanno `for="email"`
    

Questa è la **forma più usata** dei selettori di attributo:  
“attributo uguale a valore”.

---

### **5. Selettori “inizia con”: `[attr^="prefisso"]`**

Possiamo selezionare elementi in base al fatto che il loro attributo **inizi con** una certa stringa.

Sintassi:

```css
[attributo^="stringa"] {
    /* regole */
}
```

Il simbolo `^` (accento circonflesso) significa:

> “Il valore dell’attributo **comincia con** questa stringa.”

Esempio:

```css
label[for^="e"] {
    color: red;
}
```

Interpretazione:

> “Prendi le label il cui attributo `for` **inizia con la lettera `e`**.”

Con il nostro HTML:

```html
<label for="email">Email</label>
<label for="password">Password</label>
```

- `for="email"` inizia con `e` → viene selezionata
    
- `for="password"` inizia con `p` → non viene selezionata
    

Se poi cambiassimo `for="password"` in `for="epassword"`:

```html
<label for="email">Email</label>
<label for="epassword">Password</label>
```

e sistemassimo anche gli `id` correlati, entrambe le label verrebbero selezionate da:

```css
label[for^="e"] { color: red; }
```

perché:

- `email` inizia con `e`
    
- `epassword` inizia con `e`
    

---

### **6. Selettori “finisce con”: `[attr$="suffisso"]`**

Caso analogo: selezionare elementi in base a **come finisce** il valore dell’attributo.

Sintassi:

```css
[attributo$="stringa"] {
    /* regole */
}
```

Il simbolo `$` significa:

> “Il valore dell’attributo **finisce con** questa stringa.”

Esempio:

```css
label[for$="d"] {
    color: red;
}
```

Con:

```html
<label for="email">Email</label>
<label for="password">Password</label>
```

- `for="email"` finisce con `l` → non viene selezionata
    
- `for="password"` finisce con `d` → viene selezionata
    

Ovviamente non sei limitato a un solo carattere:

```css
label[for$="word"] { ... }
```

selezionerebbe gli elementi il cui `for` finisce con `"word"` (`password`, `myword`, ecc.).

---

### **7. Selettori “contiene”: `[attr*="substring"]`**

Altro caso molto importante:  
voglio selezionare gli elementi in base al fatto che il valore di un attributo **contenga** una certa stringa **in qualunque posizione**.

Sintassi:

```css
[attributo*="stringa"] {
    /* regole */
}
```

L’asterisco `*` significa:

> “Il valore dell’attributo **contiene** questa stringa (all’inizio, in mezzo o alla fine).”

Il prof usa un esempio con le immagini e l’attributo `alt`:

```html
<img src="logo.png" alt="Logo di CodeGrind">
<img src="logo-small.png" alt="Logo piccolo di CodeGrind">
<img src="avatar.png" alt="Avatar della community">
```

Supponiamo che tutte le immagini che nel `alt` contengono la parola `"CodeGrind"` debbano avere uno stile particolare.

Possiamo scrivere:

```css
img[alt*="CodeGrind"] {
    background-color: red;
    height: 100px;
}
```

Effetto:

- la prima immagine (`alt="Logo di CodeGrind"`) → contiene `"CodeGrind"` → viene stilizzata
    
- la seconda (`"Logo piccolo di CodeGrind"`) → contiene `"CodeGrind"` → viene stilizzata
    
- la terza (`"Avatar della community"`) → non contiene `"CodeGrind"` → non viene toccata
    

Se togliamo `"CodeGrind"` dal `alt` di una delle prime immagini, quella immagine **smette** di essere selezionata.

Questo pattern è molto potente per:

- tutte le immagini che parlano di un brand
    
- link con `href` che contengono una certa parte di URL
    
- elementi con `data-qualcosa` che includono determinate parole chiave
    

---

### **8. Altre varianti (che per ora ignoriamo)**

Esistono **altri operatori** per i selettori di attributo (oltre a `=`, `^=`, `$=`, `*=`), che gestiscono casi ancora più specifici:

- valori separati da spazi
    
- match di parole esatte
    
- ecc.
    

Il prof **non** li approfondisce qui di proposito, perché:

- aumenterebbero solo la confusione a questo stadio
    
- sono meno usati rispetto a quelli che abbiamo visto
    
- se ti serviranno, li potrai cercare nella documentazione (MDN, W3C, articoli, ecc.)
    

Messaggio importante:

> È normalissimo dimenticare i simboli esatti (`^`, `$`, `*`, ecc.).  
> Anche chi lavora da anni, a volte, li ripassa sulla documentazione.

L’importante è:

- sapere **che esistono**
    
- capire **a cosa servono**
    
- ricordare almeno la **forma base** e 3–4 casi fondamentali
    

---

### **9. Ricapitolone: cosa devi portarti a casa**

Riassumiamo i casi principali che voglio ti restino in testa:

#### **9.1. Presenza di attributo**

```css
[for] { ... }               /* tutti gli elementi che hanno attributo for */
label[for] { ... }          /* solo le label che hanno attributo for      */
```

#### **9.2. Attributo uguale a un valore**

```css
[for="email"] { ... }
label[for="email"] { ... }
```

#### **9.3. Attributo che inizia con una stringa**

```css
[for^="e"] { ... }          /* for inizia con "e" (es. "email", "epassword") */
```

#### **9.4. Attributo che finisce con una stringa**

```css
[for$="d"] { ... }          /* for finisce con "d" (es. "password") */
```

#### **9.5. Attributo che contiene una stringa**

```css
img[alt*="CodeGrind"] {
    background-color: red;
    height: 100px;
}
```

E, a livello concettuale:

- le **parentesi quadre** `[...]` indicano sempre: “sto selezionando in base a un **attributo**”
    
- dentro ci metti:
    
    - solo il nome dell’attributo → “ce l’ha/non ce l’ha”
        
    - o `attributo="valore"`
        
    - o `attributo^="valore"` → inizia con
        
    - o `attributo$="valore"` → finisce con
        
    - o `attributo*="valore"` → contiene
        

Se di tutto il video ti resta in testa almeno questa sintassi base, hai già un’ottima base pratica.

---

Nel prossimo video il prof ti mette alla prova con un **esercizio** sui selettori di attributo, così puoi vedere subito se queste cose ti sono entrate nelle dita, non solo nella testa.