## **Lezione 9 – Pseudoclassi e Pseudoelementi**

### **1. Introduzione: perché esistono pseudoclassi e pseudoelementi**

Entriamo ora in una delle parti più affascinanti dei selettori CSS:  
le **pseudoclassi** e i **pseudoelementi**.

Sono concetti fondamentali, perché permettono di:

- reagire agli **stati** di un elemento (es. quando ci passi sopra col mouse)
    
- selezionare **parti interne** di un elemento che _non esistono_ nel codice HTML (es. la prima lettera di un paragrafo)
    

L’obiettivo di questa lezione è chiarire perfettamente:

- **la differenza concettuale** tra pseudoclassi e pseudoelementi
    
- **la sintassi** (un solo `:` per le pseudoclassi, due `::` per gli pseudoelementi)
    
- alcuni **esempi pratici** per memorizzare tutto
    

Il prof specifica: esistono tantissime pseudoclassi e tanti pseudoelementi.  
Non devi impararli ora: molti li vedremo in contesti specifici (link, liste, form, ecc.).

---

## **2. Le Pseudoclassi: rappresentano _stati_ dell’elemento**

### **2.1. Idea fondamentale**

Una _pseudoclasse_ è una sorta di “classe automatica”, che **non scriviamo noi** nell’HTML.

Si attiva quando l’elemento si trova in un certo **stato**, ad esempio:

- ci stai passando sopra col mouse
    
- lo stai cliccando
    
- ha il focus (caratteristica tipica degli input)
    
- è il primo figlio della sua lista
    
- è disabilitato
    
- è selezionato
    
- ecc.
    

Sono **classi**, ma non le assegni tu:  
le assegna il **browser**, in base alle azioni dell’utente e alla struttura HTML.

Esempi visibili dal DevTools (ispezione → “Force state”):

```
:hover
:active
:focus
```

---

### **2.2. La sintassi delle pseudoclassi**

La pseudoclasse si scrive con **un solo due-punti (`:`)**:

```
elemento:pseudoclasse { ... }
```

Esempio:

```css
h1:hover {
    color: red;
}
```

Questo significa:

> Quando l’H1 si trova nello stato “hover”, cioè quando ci passo sopra col mouse, diventa rosso.

È una delle pseudoclassi più intuitive: la reattività al mouse.

---

### **2.3. Esempio pratico**

HTML:

```html
<h1>Ciao</h1>
```

CSS:

```css
h1 {
    color: black;
}

h1:hover {
    color: red;
    cursor: pointer;
}
```

Effetto:

- di base è nero
    
- appena ci passi sopra:
    
    - diventa rosso
        
    - il cursore diventa la manina (“pointer”)
        

---

### **2.4. Pseudoclassi + lista di selettori**

Possiamo applicare una pseudoclasse a più selettori contemporaneamente usando la virgola:

```css
h1:hover,
p:hover {
    color: red;
}
```

Effetto:

- qualsiasi `h1` o `p` al passaggio del mouse diventa rosso
    
- una singola regola → due elementi diversi → nessuna ripetizione
    

---

## **3. Gli Pseudoelementi: selezionano _parti interne_ dell’elemento**

### **3.1. Idea fondamentale**

Gli pseudoelementi rappresentano _porzioni_ dell’elemento:

- la **prima riga** di un paragrafo
    
- la **prima lettera**
    
- l’**intero contenuto generato**
    
- il **prima/dopo** del contenuto reale
    

Sono “elementi logici”:  
**esistono nel browser**, ma **non sono scritti nell’HTML**.

Per questo si chiamano _pseudo-elementi_.

---

### **3.2. Sintassi degli pseudoelementi**

Si scrivono con **due due-punti (`::`)**:

```
elemento::pseudoelemento { ... }
```

I più famosi (tra quelli già introdotti):

- `::first-line`
    
- `::first-letter`
    

---

### **3.3. Esempio: prima riga e prima lettera**

HTML:

```html
<p>
  Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
</p>
```

CSS:

```css
p::first-line {
    color: green;
    font-size: 20px;
}

p::first-letter {
    color: orange;
    font-size: 30px;
}
```

Effetto:

- **solo la prima riga a schermo** del paragrafo diventa verde e più grande  
    (ATTENZIONE: la prima riga _cambia_ in base alla larghezza dello schermo)
    
- **solo la primissima lettera** diventa arancione e più grande
    

---

### **3.4. Perché la prima riga cambia?**

Perché _non_ è la prima riga dell’HTML.  
È la prima riga **a video**, dipendente da:

- larghezza della finestra
    
- font
    
- zoom
    
- padding/margini
    

È un elemento logico “calcolato” dal browser.

---

## **4. Un altro esempio di pseudoelemento: i figli logici**

Il prof fa un altro esempio utile senza scrivere codice:

Le liste:

```html
<ul>
  <li>Uno</li>
  <li>Due</li>
  <li>Tre</li>
</ul>
```

Qui abbiamo gli elementi reali (`li`), ma esistono anche “elementi logici”:

- il **primo figlio**
    
- il **secondo figlio**
    
- gli elementi **pari**
    
- gli elementi **dispari**
    
- l’ultimo figlio
    

Questi non li scrivi tu nell’HTML, ma il CSS può selezionarli come se fossero elementi reali.

Esempi tipici:

```
li:first-child
li:last-child
li:nth-child(odd)
li:nth-child(even)
```

Sono pseudoclassi o pseudoelementi?

⚠️ Attenzione:  
`first-child`, `nth-child` ecc. sono **pseudoclassi**, NON pseudoelementi.

Ma il prof li cita come esempio di “elementi logici”, perché:

- non li definisci tu
    
- fanno parte della “struttura interna” analizzata dal browser
    

---

## **5. Differenza fondamentale tra pseudoclassi e pseudoelementi**

Ricapitoliamo in modo cristallino:

### **Pseudoclassi**

`:` (un due-punti)

- rappresentano **stati** di un elemento  
    (hover, focus, active, visited, disabled, first-child, ecc.)
    
- non le scriviamo noi
    
- le assegna il **browser**
    

Esempi:

```
:hover
:focus
:active
:first-child
:nth-child(odd)
```

---

### **Pseudoelementi**

`::` (due due-punti)

- rappresentano **parti interne** dell’elemento, generate dal browser
    
- non esistono nell’HTML, ma fanno parte del “contenuto logico”
    

Esempi:

```
::first-line
::first-letter
::before
::after
```

---

## **6. Sintesi finale della lezione**

Quello che devi portare a casa ora è solo questo:

### **Pseudoclassi ⇒ stati**

Scrittura: `elemento:pseudoclasse`

Esempi:

```css
h1:hover { color: red; }
input:focus { border-color: blue; }
li:nth-child(odd) { background: #eee; }
```

### **Pseudoelementi ⇒ parti dell’elemento**

Scrittura: `elemento::pseudoelemento`

Esempi:

```css
p::first-line { color: green; }
p::first-letter { font-size: 30px; }
div::before { content: "→ "; }
```

Non serve imparare tutta la lista:  
molti li vedrai solo quando servono davvero (link, form, liste, ecc.).

---

## **7. Prossimo argomento: i combinatori**

Ora che conosci:

- selettori base
    
- selettori multipli
    
- selettori di attributo
    
- pseudoclassi
    
- pseudoelementi
    

siamo pronti per un pezzo importante della sintassi CSS:

### **i combinatori**, cioè:

- discendente
    
- figlio diretto
    
- fratello adiacente
    
- fratello generale
    

che permettono di definire relazioni tra gli elementi.

Nel prossimo video si parte da qui.