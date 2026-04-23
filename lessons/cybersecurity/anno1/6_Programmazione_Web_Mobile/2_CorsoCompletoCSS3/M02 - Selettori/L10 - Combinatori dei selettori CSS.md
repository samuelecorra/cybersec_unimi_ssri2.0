## **Lezione 10 – Combinatori dei selettori CSS**

### **1. Introduzione: cosa sono i combinatori**

Finora hai visto:

- selettori semplici (elementi, classi, ID, attributi)
    
- selettori combinati sullo **stesso elemento** (es. `.particolare.scemo`)
    

Con i **combinatori** facciamo un passo oltre:  
non selezioniamo più un _singolo elemento_ con più caratteristiche, ma **elementi diversi collegati tra loro** da una relazione gerarchica.

In pratica:

- `.classe1.classe2`  
    → **un solo elemento** che ha entrambe le classi
    
- `.classe1 .classe2` (con lo spazio)  
    → **due elementi diversi**:  
    un elemento con `.classe1` **che contiene** (da qualche parte dentro) un elemento con `.classe2`
    

I combinatori servono esattamente a definire questo tipo di relazione:

- “un elemento dentro un altro”
    
- “un elemento figlio diretto di un altro”
    
- “un elemento fratello subito dopo un altro”
    
- “un elemento fratello, anche non immediatamente dopo”
    

---

### **2. Discendente vs figlio diretto: spazio vs `>`**

#### **2.1. Esempio di partenza**

Immagina questo HTML:

```html
<p class="paragrafo">
  Lorem ipsum <strong class="cap">dolor</strong> sit amet.
</p>

<p>
  Lorem ipsum <strong class="cap">dolor</strong> sit amet.
</p>
```

- il **primo** `<strong class="cap">` si trova **dentro** un `<p class="paragrafo">`
    
- il **secondo** `<strong class="cap">` si trova dentro un `<p>` **senza** classe `paragrafo`
    

Vogliamo:

> “Tutti gli elementi di classe `.cap` che si trovano **dentro** elementi di classe `.paragrafo` devono essere rossi.”

Possiamo scrivere:

```css
.paragrafo .cap {
    color: red;
}
```

Effetto:

- il primo `strong.cap` (dentro `.paragrafo`) → **rosso**
    
- il secondo `strong.cap` (in un `p` senza `.paragrafo`) → **non viene toccato**
    

Questo è il **combinatore discendente**: lo spazio tra i selettori.

---

#### **2.2. Discendente: lo spazio (`A B`)**

Forma generale:

```css
.genitore .discendente {
    /* stile */
}
```

Significa:

> “Prendi tutti gli elementi con classe (o tag, o ID) `discendente` che si trovano **da qualche parte dentro** un elemento `genitore`, annidati a qualsiasi profondità.”

Quindi:

- figli
    
- nipoti
    
- pronipoti
    
- ecc.
    

sono **tutti** discendenti.

Con la metafora di famiglia:

- **discendente** = figlio, nipote, pronipote… tutta la linea
    

---

#### **2.3. Figlio diretto: il `>`**

Ora cambiamo combinatore:

```css
.paragrafo > .cap {
    color: green;
}
```

Questo seleziona solo:

> “Gli elementi `.cap` che sono **figli diretti** di `.paragrafo`.”

Se il tuo HTML è:

```html
<p class="paragrafo">
  Lorem ipsum <strong class="cap">dolor</strong> sit amet.
</p>
```

`strong.cap` è **figlio diretto** di `p.paragrafo`, quindi viene colorato.

Ma se lo modifichi così:

```html
<p class="paragrafo">
  Lorem ipsum 
  <span>
    <strong class="cap">dolor</strong>
  </span>
  sit amet.
</p>
```

Ora `strong.cap` **non è più** figlio diretto di `p.paragrafo`, ma figlio di `span`.  
Quindi:

- con `.paragrafo > .cap` → **NON viene selezionato**
    
- con `.paragrafo .cap` → viene comunque selezionato (è discendente)
    

---

#### **2.4. Riepilogo: spazio vs `>`**

- `.paragrafo .cap`  
    → **discendente**  
    → qualsiasi `.cap` che si trova _da qualche parte dentro_ `.paragrafo`
    
- `.paragrafo > .cap`  
    → **figlio diretto**  
    → solo `.cap` che è **subito dentro** `.paragrafo`, senza intermedi
    

Metafora famiglia:

- `>` = **figlio diretto**
    
- spazio = **discendente** (figlio, nipote, pronipote…)
    

---

### **3. Fratelli (siblings): `+` e `~`**

I **siblings** sono elementi che si trovano:

- allo **stesso livello gerarchico**
    
- con lo **stesso genitore**
    

Non sono né “dentro” né “sopra”: sono proprio **fratelli**.

Vedremo due combinatori:

- `+` → fratello _adiacente_
    
- `~` → tutti i fratelli successivi
    

---

#### **3.1. Fratello adiacente: `+`**

Sintassi:

```css
div + p {
    color: red;
}
```

Significa:

> “Il `p` che viene **subito dopo** un `div`, sullo stesso livello, deve essere rosso.”

Esempio HTML:

```html
<div class="box"></div>

<p>Paragrafo 1</p>
<p>Paragrafo 2</p>
<p>Paragrafo 3</p>
```

Con il CSS sopra:

- solo **Paragrafo 1** diventa rosso  
    (è il primo `p` che segue **immediatamente** il `div`)
    

Se aggiungi qualcosa in mezzo:

```html
<div class="box"></div>
<h1>Titolo</h1>
<p>Paragrafo 1</p>
<p>Paragrafo 2</p>
```

Ora:

- `div + p` **non** trova nessun match, perché il primo fratello dopo `div` è `h1`, non `p`
    

Quindi:

- `+` = “primo fratello immediatamente successivo, se è del tipo giusto”
    

---

#### **3.2. Fratelli generali: `~`**

Sintassi:

```css
div ~ p {
    color: green;
}
```

Significa:

> “Tutti i `p` che sono fratelli di `div` e vengono **dopo** di lui, devono essere verdi.”

Con HTML:

```html
<div class="box"></div>
<p>Paragrafo 1</p>
<p>Paragrafo 2</p>
<p>Paragrafo 3</p>
```

Risultato:

- Paragrafo 1, 2, 3 → **tutti verdi**  
    (sono tutti `p` siblings successivi al `div`)
    

Se aggiungi:

```html
<div class="box"></div>
<p>Paragrafo 1</p>

<div>Altro blocco</div>

<p>Paragrafo 2</p>
```

Sono comunque tutti `p` che si trovano:

- allo stesso livello del `div`
    
- **dopo** il `div`
    

Quindi vengono tutti selezionati da `div ~ p`.

---

#### **3.3. Attenzione ai figli: non sono siblings**

Se scrivi:

```html
<div class="box">
  <p>Figlio dentro il div</p>
</div>

<p>Paragrafo fuori</p>
```

- il `p` dentro `div.box` è **figlio**, non fratello → **non** viene selezionato da `div ~ p`
    
- il `p` fuori è sibling di `div.box` → viene selezionato da `div ~ p`
    

Quindi:

- `~` e `+` guardano solo **fratelli allo stesso livello**, mai elementi annidati dentro.
    

---

### **4. Un esempio di combinatore più complesso**

Il prof fa un esempio spinto per farti vedere quanto ci si possa spingere:

```css
div + div p {
    color: indigo;
}
```

Leggiamolo con calma:

- `div + div`  
    → prendi un `div` che è **adiacente** a un altro `div` (cioè un secondo `div` subito dopo un primo `div`)
    
- `div + div p`  
    → all’interno di questo **secondo `div`**, prendi i `p` discendenti
    

In parole:

> “Prendi il `div` che viene subito dopo un altro `div`, poi prendi tutti i `p` al suo interno.”

Questo mostra come i combinatori si possano **concatenare** e creare selettori molto specifici… ma anche molto difficili da leggere se esageri.

---

### **5. Effetti di specificità e cascata nei combinatori**

Quando il prof mostra esempi misti (rosso/verde), fa notare:

- se due regole hanno **stessa specificità** (es. entrambe con classi), vince:
    
    - quella **scritta dopo** → effetto **cascata**
        

Esempio:

```css
.paragrafo .cap {
    color: red;
}

.paragrafo > .cap {
    color: green;
}
```

Se entrambe colpiscono lo stesso elemento:

- hanno entrambe:
    
    - 1 classe (`.paragrafo`)
        
    - 1 classe (`.cap`)
        
- **specificità uguale**
    
- vince l’ultima scritta nel file → in questo caso, verde
    

Quando invece la struttura cambia (es. inserisci uno `span` in mezzo), può succedere che:

- `.paragrafo > .cap` smetta di valere (perché non è più figlio diretto)
    
- resta solo `.paragrafo .cap`, che colora di rosso
    

Quindi:

- i combinatori non cambiano la logica di specificità
    
- ma cambiano **quali elementi vengono effettivamente colpiti**
    

---

### **6. Uso pratico dei combinatori**

Il prof è molto onesto: in pratica succede spesso che:

- usi **tantissimo** classi e ID
    
- usi parecchio:
    
    - discendenti (`A B`)
        
    - figli diretti (`A > B`)
        
- usi **meno spesso** i siblings `+` e `~`, perché il codice diventa meno leggibile
    

In molti progetti moderni:

- si usano framework (Bootstrap, Tailwind, Bulma, ecc.)
    
- si lavora con classi ben strutturate, componenti, ecc.
    
- i combinatori molto “incastrati” (`div + div p span`) rischiano di creare CSS difficile da mantenere
    

Detto questo, è fondamentale:

- **conoscerli**, perché li troverai in giro
    
- saper leggere e capire cosa fanno
    
- usarli quando davvero servono (es. stili diversi per il primo elemento dopo un altro, liste particolari, ecc.)
    

---

### **7. Riepilogo finale**

- I **combinatori** collegano più selettori in base alla **relazione** tra gli elementi.
    
- **Discendente**:
    
    ```css
    A B
    ```
    
    B è **dentro** A, a qualsiasi profondità (figlio, nipote, pronipote…)
    
- **Figlio diretto**:
    
    ```css
    A > B
    ```
    
    B è **figlio diretto** di A (un solo livello di profondità)
    
- **Fratello adiacente**:
    
    ```css
    A + B
    ```
    
    il primo B che viene **subito dopo** A, sullo stesso livello
    
- **Fratelli generali**:
    
    ```css
    A ~ B
    ```
    
    **tutti** i B che sono fratelli di A e vengono **dopo** di lui
    

Metafora famiglia:

- `>` → figlio diretto
    
- spazio → discendente (figlio, nipote, pronipote…)
    
- `+` → fratello subito dopo
    
- `~` → fratelli più giovani, anche se in mezzo ci sono altri
    

Nel prossimo video: **esercizio globale** su tutti i selettori visti finora, per fissare davvero la sintassi e il modo di ragionare.