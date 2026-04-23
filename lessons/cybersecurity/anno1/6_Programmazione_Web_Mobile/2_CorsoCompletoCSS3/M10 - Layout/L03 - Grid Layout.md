**Lezione 3 – Grid Layout**

Grid Layout è uno degli argomenti più delicati di tutto il CSS: è normale trovarlo difficile al primo giro.  
L’idea però non è “magica”: è solo un modo strutturato per piazzare gli elementi dentro una griglia, invece di lasciarli nel flusso normale.

Ti porto passo passo, senza saltare nulla, ma cercando di rendere ogni pezzo il più chiaro possibile.

---

**1. Concetto base: container e item**

Per usare una grid ci servono due attori:

- un **contenitore** (di solito un `div` con classe `container`)
    
- una serie di **celle / item** (di solito `div` con classe `item`, `item-1`, `item-2`, ecc.)
    

Esempio mentale:  
Pensa al profilo Instagram:

- tutta la sezione con le foto è il **container**
    
- ogni quadrato con una foto è un **item** della griglia
    
- alcuni item possono avere dimensioni diverse (es. un post in evidenza che occupa due colonne)
    

In HTML una struttura tipica potrebbe essere:

```html
<div class="container">
  <div class="item item-1">1</div>
  <div class="item item-2">2</div>
  <div class="item item-3">3</div>
  <div class="item item-4">4</div>
  <div class="item item-5">5</div>
</div>
```

---

**2. Impostare il container: display: grid**

Partiamo dal container e lo rendiamo una griglia:

```css
.container {
  width: 80%;
  margin: 5em auto;
  border: 5px solid black;
}
.item {
  background-color: blueviolet;
  color: white;
  font-weight: 700;
  padding: 10px;
}
```

Fin qui è ancora tutto in “normal flow”, solo con colori e bordi.  
Per attivare la grid:

```css
.container {
  display: grid;
}
```

Solo `display: grid` però non basta per “vedere” una griglia vera: dobbiamo dire al container **quante colonne** vogliamo e quanto devono essere larghe.

---

**3. grid-template-columns e unità fr**

La proprietà chiave è `grid-template-columns`: definisce quante colonne esistono e la larghezza di ognuna.

Esempio semplice:

```css
.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
```

- `1fr` significa “una frazione dello spazio disponibile”
    
- con `1fr 1fr` hai **2 colonne**, ognuna che prende metà dello spazio del container
    
- gli item si distribuiscono così:
    
    - item 1 → colonna 1, riga 1
        
    - item 2 → colonna 2, riga 1
        
    - item 3 → colonna 1, riga 2
        
    - item 4 → colonna 2, riga 2  
        …e così via
        

Se aggiungi un quarto, quinto, sesto item, la grid “va a capo” da sola sulle righe successive.

---

**4. Colonne in pixel vs frazioni**

Non sei obbligato a usare `fr`. Puoi usare anche valori fissi:

```css
grid-template-columns: 100px 100px;
```

In questo caso:

- la prima colonna è larga 100px
    
- la seconda colonna è larga 100px
    
- se il container è molto più largo, rimarrà spazio vuoto a destra
    

Puoi anche mescolare fisso + fr:

```css
grid-template-columns: 100px 1fr;
```

- prima colonna: 100px fissi
    
- seconda colonna: si prende **tutto il resto** dello spazio disponibile
    

Se scrivi tre volte:

```css
grid-template-columns: 100px 100px 100px;
```

avrai tre colonne da 100px e gli item andranno a riempirle riga per riga.

---

**5. grid-template-rows: altezza delle righe**

Analogamente, `grid-template-rows` definisce le righe:

```css
.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 100px 500px;
}
```

- due colonne, entrambe `1fr`
    
- due righe: la prima alta 100px, la seconda alta 500px
    

Se ci sono più item di quanti ne stiano in 2 righe x 2 colonne, la grid genera righe “in più” in modo implicito (tra poco vediamo come controllarle).

---

**6. La scorciatoia repeat()**

Scrivere `1fr 1fr 1fr` è ripetitivo. Possiamo usare `repeat()`:

```css
grid-template-columns: repeat(3, 1fr);
```

Significa: “crea 3 colonne, ognuna da 1fr”.  
È equivalente a:

```css
grid-template-columns: 1fr 1fr 1fr;
```

Possiamo usarlo anche con valori fissi:

```css
grid-template-rows: repeat(3, 100px);
```

Tre righe, ognuna alta 100px.

---

**7. Espandere un item: grid-column e grid-row**

Possiamo dire a un singolo item di occupare più colonne o più righe.  
È il concetto simile a `colspan`/`rowspan` delle tabelle HTML.

Supponiamo di avere una grid 3x3 e di voler fare l’item 1 “più largo”:

```css
.container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 100px);
}
.item-1 {
  background-color: aqua;
  grid-column: 1 / 3;  /* parte dalla colonna 1, arriva fino all’inizio della 3 */
}
```

- `grid-column: 1 / 3` significa “dal bordo della colonna 1 al bordo della colonna 3”
    
- l’item 1 occuperà la colonna 1 e la colonna 2
    
- gli altri item “slittano” nelle celle libere rimaste
    

Stessa logica per le righe:

```css
.item-1 {
  grid-column: 1 / 3;
  grid-row: 1 / 3;  /* occupa riga 1 e riga 2 */
}
```

Puoi scriverlo anche separato:

```css
.item-1 {
  grid-column-start: 1;
  grid-column-end: 3;
  grid-row-start: 1;
  grid-row-end: 3;
}
```

Ma la forma compatta `grid-column: 1 / 3` e `grid-row: 1 / 3` è molto più comune.

---

**8. Righe e colonne “in più”: grid-auto-rows, grid-auto-columns**

Immagina di definire:

```css
grid-template-rows: repeat(3, 100px);
```

Quindi tre righe da 100px.  
Se aggiungi più item di quanti ne stiano nella griglia 3xN, il browser crea righe extra **implicitamente**.

Per non lasciare al browser la scelta dell’altezza di queste righe extra, puoi usare:

```css
grid-auto-rows: 100px;
```

Significa: “ogni riga aggiuntiva che crei, falla alta 100px”.

Analogamente per le colonne:

```css
grid-auto-columns: 100px;
```

---

**9. grid-auto-flow: dove metti gli item in più**

Per default, la grid riempie per righe (row): riempie una riga e poi va a quella successiva.

Possiamo cambiare questo comportamento con:

```css
grid-auto-flow: column;
```

Significa: “quando aggiungi item, riempi le colonne prima, poi scendi”.

È utile quando vuoi creare layout dinamici, dove non sai esattamente quanti item arriveranno (cosa che vedrai meglio con JavaScript).

---

**10. gap vs grid-gap**

Il “distanziamento” tra le celle della grid si gestisce con `gap`:

```css
.container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 40px;
}
```

- `gap: 40px` imposta 40px tra tutte le righe e tutte le colonne
    

Puoi differenziare righe e colonne:

```css
gap: 40px 10px;  /* 40px tra le righe, 10px tra le colonne */
```

Esiste anche `grid-gap`, ma è considerata **obsoleta**: funzionava nello stesso modo, ma oggi si usa semplicemente `gap`.

---

**11. Responsività con fr e minmax()**

Se usi `1fr`, le colonne si allargano o restringono automaticamente con il container:  
è ciò che rende la grid **naturalmente più responsive** rispetto ai pixel fissi.

Esempio:

```css
.container {
  width: 80%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
```

Se restringi la finestra:

- il container si stringe (80% del body)
    
- le colonne `1fr` si ridistribuiscono dello spazio disponibile  
    Quindi il layout è già parzialmente responsive.
    

Per controllare meglio il comportamento, c’è `minmax()`:

```css
grid-template-columns: 100px 1fr minmax(100px, 500px);
```

- colonna 1: sempre 100px
    
- colonna 2: `1fr` (tutto lo spazio restante)
    
- colonna 3: almeno 100px, al massimo 500px
    

Se allunghi la pagina, la terza colonna cresce fino a 500px, poi si ferma, e il resto dello spazio va alla colonna `1fr`.

---

**12. Allineare il contenuto dentro le celle: justify-items e align-items**

Una volta definita la griglia (colonne e righe), possiamo decidere dove gli item stanno **dentro** la loro cella.

Le due proprietà chiave sono:

- `justify-items` → asse orizzontale (x)
    
- `align-items` → asse verticale (y)
    

Esempio:

```css
.container {
  display: grid;
  grid-template-columns: repeat(3, 100px);
  grid-template-rows: repeat(3, 100px);
  justify-items: center;
  align-items: center;
}
```

Risultato:

- ogni `item` è centrato orizzontalmente nella sua cella
    
- e centrato verticalmente nella sua cella
    

Valori principali:

- `stretch` (default): l’item si allarga per riempire tutta la cella
    
- `start`: allineato all’inizio (sinistra per `justify`, alto per `align`)
    
- `center`: al centro
    
- `end`: alla fine (destra o in basso)
    

---

**13. Allineare l’intera griglia: justify-content e align-content**

A volte la griglia (l’insieme delle celle) è **più piccola** del container.  
Esempio:

```css
.container {
  width: 80%;
  height: 500px;
  display: grid;
  grid-template-columns: repeat(3, 100px);
  grid-template-rows: repeat(2, 100px);
}
```

- la griglia occupa 3x100px in larghezza e 2x100px in altezza
    
- ma il container è molto più grande
    

Qui entrano in gioco:

- `justify-content` → sposta la griglia sul piano orizzontale all’interno del container
    
- `align-content` → sposta la griglia sul piano verticale all’interno del container
    

Esempi:

```css
.container {
  justify-content: center;   /* griglia centrata orizzontalmente */
  align-content: center;     /* griglia centrata verticalmente   */
}
```

Oppure:

- `start` → appiccicata all’inizio
    
- `end` → appiccicata alla fine
    
- `space-between`, `space-around`, `space-evenly` → distribuzioni con spaziatura
    

Differenza importante:

- `justify-content` / `align-content` → agiscono sull’intera **grid** (cioè sulle “pistrelle” come blocco unico)
    
- `justify-items` / `align-items` → agiscono su come **gli item** stanno dentro le singole celle
    

---

**14. Allineare un singolo item: justify-self e align-self**

Se vuoi spostare solo **un item** senza toccare gli altri, usi `justify-self` e `align-self` direttamente su quell’item.

Esempio:

```css
.item-1 {
  justify-self: stretch;
  align-self: stretch;
}
```

- l’item-1 si allarga per riempire tutta la cella (sia in x che in y)
    
- gli altri item restano come deciso da `justify-items` e `align-items` nel container
    

Riassunto delle “famiglie”:

- `justify-*` → asse orizzontale
    
- `align-*` → asse verticale
    
- `*-content` → allineano il **blocco griglia** nel container
    
- `*-items` → allineano tutti gli **item** dentro le loro celle
    
- `*-self` → allineano un **singolo item**
    

---

**15. Parallelo con Flexbox**

Le proprietà tipo:

- `justify-content`
    
- `align-items`
    
- `align-content`
    
- `justify-self` / `align-self` (dove supportato)
    

le ritroverai **anche con Flexbox**.  
Il significato generale resta lo stesso:  
justify → orizzontale, align → verticale, content/items/self → su cosa stai agendo.

---

**Conclusione**

Il Grid Layout non è semplice al primo impatto perché introduce:

- un sistema di colonne e righe
    
- il concetto di celle che possono espandersi
    
- righe/colonne implicite
    
- allineamenti sia del blocco griglia sia dei singoli item
    

Però il cuore è sempre quello:

- definisci la **struttura**: `grid-template-columns`, `grid-template-rows`, `repeat()`, `minmax()`
    
- controlli gli **item**: `grid-column`, `grid-row`, `justify-items`, `align-items`, `justify-self`, `align-self`
    
- controlli la **griglia nel container**: `justify-content`, `align-content`, `gap`, `grid-auto-*`
    

È normale doverci tornare più volte e fare tanti esperimenti.  
L’importante è avere in testa il quadro generale: la grid è il sistema per “disegnare una tabella super potenziata”, su cui posizionare i tuoi elementi in modo preciso e responsive.