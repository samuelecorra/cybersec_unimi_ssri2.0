## **Lezione 8: Flexbox - introduzione**

### **1. Perché Flexbox è così importante**

Flexbox è uno dei sistemi di layout che userai più spesso in assoluto in CSS, perché è:

- flessibile,
    
- relativamente compatto come sintassi,
    
- pensato per fare layout **monodimensionali** (una riga o una colonna) che si adattano allo spazio.
    

A differenza di proprietà “semplici” come `border` (dove hai 2–3 regole facili e finite), Flexbox è un **concetto**: un insieme di regole che lavorano insieme.  
Per chi è alle prime armi può risultare ostico proprio perché:

- devi imparare le proprietà del **container**,
    
- le proprietà dei **flex items**,
    
- e come interagiscono tra loro.
    

---

## **2. Container e Item: la base di Flexbox**

Esattamente come con le grid, anche Flexbox funziona sempre con la logica:

- **un container** (elemento genitore),
    
- **più item** (elementi figli diretti).
    

Esempio HTML:

```html
<div class="container">
  <div class="item">1</div>
  <div class="item">2</div>
  <div class="item">3</div>
</div>
```

E un po’ di stile iniziale:

```css
.container {
  background-color: #d9b3ff;   /* viola pastello stile guida CSS-Tricks */
  padding: 20px;
}

.item {
  background-color: orange;
  width: 100px;
  height: 100px;
  border-radius: 10px;
  color: white;
  font-size: 30px;
  font-weight: 600;
  display: flex;
  justify-content: center;
  align-items: center;
}
```

> Nota: qui ho già usato `display: flex` sull’`item` solo per centrare il **contenuto interno** (il numero) verticalmente e orizzontalmente.

---

## **3. `display: flex` e chi viene influenzato**

Il punto chiave:

- `display: flex` si scrive sul **container**,
    
- ma l’effetto si vede sui **figli** (gli item).
    

```css
.container {
  display: flex;
}
```

Questo significa:

- non è il container che “cambia forma” in modo evidente,
    
- sono le **posizioni degli item** che cambiano (da blocchi uno sotto l’altro a elementi in riga, ecc.).
    

Confronta:

- `display: block` → riguarda l’elemento in sé (il container occupa tutta la riga).
    
- `display: none` → fa sparire l’elemento (container + figli).
    
- `display: flex` → trasforma il container in **flex container** e gestisce l’allineamento dei figli.
    

Questa è una differenza concettuale importante:

- Block/inline/none → agiscono direttamente sull’elemento.
    
- Flex/grid → definiscono un _contesto di layout_ per i figli.
    

---

## **4. Flex direction: asse principale e verso**

La prima proprietà fondamentale del container è:

```css
.container {
  display: flex;
  flex-direction: row;     /* valore di default */
}
```

I valori principali:

- `row` → gli item vanno in **riga**, da sinistra a destra.
    
- `row-reverse` → in riga, ma da destra a sinistra (ordine visivo invertito).
    
- `column` → gli item vanno in **colonna**, dall’alto verso il basso.
    
- `column-reverse` → in colonna, dal basso verso l’alto.
    

Esempio:

```css
/* In colonna */
.container {
  display: flex;
  flex-direction: column;
}
```

Con `flex-direction: column` gli elementi sono uno sopra l’altro, **ma non come block**:  
sono comunque “flex items”, quindi:

- puoi usare gap,
    
- puoi usare `justify-content` e `align-items`,
    
- puoi strecciarli, invertire l’ordine, ecc.
    

> Schema mentale:
> 
> - Se sei in `row`, l’asse principale è orizzontale.
>     
> - Se sei in `column`, l’asse principale è verticale.
>     

---

## **5. Flex wrap: andare a capo o no**

Per default, Flexbox prova a tenere tutti gli item **sulla stessa riga** (o colonna) comprimendoli se serve.

La proprietà che controlla questo comportamento è:

```css
.container {
  display: flex;
  flex-wrap: nowrap;   /* default */
}
```

Valori:

- `nowrap` → tutti sulla stessa riga, anche se gli item si stringono.
    
- `wrap` → se non c’è spazio a sufficienza, gli item vanno a capo.
    
- `wrap-reverse` → vanno a capo, ma la direzione delle “righe” è invertita.
    

Esempio (molti item):

```html
<div class="container">
  <div class="item">1</div>
  <div class="item">2</div>
  <div class="item">3</div>
  <div class="item">4</div>
  <div class="item">5</div>
  <div class="item">6</div>
  <div class="item">7</div>
</div>
```

```css
.container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
```

Se stringi la finestra:

- con `nowrap` gli item si schiacciano tutti sulla stessa riga,
    
- con `wrap` alcuni scendono sulla riga successiva quando non c’è spazio.
    

---

## **6. `flex-flow`: direzione + wrap insieme**

`flex-flow` è un’abbreviazione di:

- `flex-direction`
    
- `flex-wrap`
    

Esempio:

```css
.container {
  display: flex;
  flex-flow: row wrap;
}
```

Questo equivale a:

```css
.container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
```

È utile, ma per imparare è meglio tenere _separati_:

- `flex-direction`
    
- `flex-wrap`
    

così ti abitui bene al ruolo di ciascuno.

---

## **7. Justify-content: distribuire gli item lungo l’asse principale**

`justify-content` controlla **come vengono distribuiti gli item lungo l’asse principale** (quello definito da `flex-direction`).

```css
.container {
  display: flex;
  flex-direction: row;   /* asse principale: orizzontale */
  justify-content: center;
}
```

Valori comuni:

- `flex-start` / `start` → tutti attaccati all’inizio.
    
- `flex-end` / `end` → tutti alla fine.
    
- `center` → al centro.
    
- `space-between` → spazio solo **tra** gli item (niente sui lati estremi).
    
- `space-around` → spazio attorno agli item (mezzo spazio sui bordi).
    
- `space-evenly` → spazio uniforme tra gli item e tra item e bordi.
    

Esempio classico in `row`:

```css
.container {
  display: flex;
  justify-content: space-between;
}
```

Uso tipico:

- logo a sinistra,
    
- menu centrale,
    
- icone a destra.
    

Se cambi `flex-direction: column`, l’asse principale diventa verticale, quindi `justify-content` sposta gli item **in alto / centro / basso** a seconda del valore.

---

## **8. Align-items: allineamento lungo l’asse trasversale**

Se `justify-content` lavora sull’asse principale, `align-items` lavora sull’asse **perpendicolare**.

Esempio in `row` (asse principale orizzontale):

```css
.container {
  display: flex;
  flex-direction: row;
  align-items: center;
}
```

Valori:

- `flex-start` → tutti “in alto” (sull’asse trasversale).
    
- `flex-end` → tutti “in basso”.
    
- `center` → centrati.
    
- `stretch` → gli item si allungano per occupare tutta l’altezza disponibile (se non hai fissato `height` sugli item).
    
- `baseline` → allineati in base alla linea di base del testo (utile con testi di dimensioni diverse).
    

Se passi a `flex-direction: column`, succede l’effetto “inverso”:

- asse principale verticale → `justify-content` controlla “sopra/centro/sotto”,
    
- asse trasversale orizzontale → `align-items` controlla “sinistra/centro/destra”.
    

Schema mentale importante:

- **In `row`**:
    
    - `justify-content` → orizzontale
        
    - `align-items` → verticale
        
- **In `column`**:
    
    - `justify-content` → verticale
        
    - `align-items` → orizzontale
        

---

## **9. Proprietà sugli item: order, flex-basis, flex-grow, flex-shrink**

Finora abbiamo lavorato sul **container**.  
Ora passiamo alle proprietà che agiscono sui **singoli item**.

### **9.1. `order`: cambiare l’ordine visivo**

Normalmente gli item vengono mostrati in ordine sorgente:

```html
<div class="item item-1">1</div>
<div class="item item-2">2</div>
<div class="item item-3">3</div>
```

Con `order` possiamo cambiare l’ordine _visivo_:

```css
.item-1 { order: 2; }
.item-2 { order: 1; }
.item-3 { order: 3; }
```

Risultato: visivamente l’ordine sarà `2, 1, 3`.

Questo diventa molto utile in **responsive**:

- su desktop li mostri in un certo ordine,
    
- su mobile li “incolonni” in un altro ordine usando `order`.
    

---

### **9.2. `flex-basis`: dimensione di partenza (base)**

`flex-basis` definisce la **dimensione di base** di un item sull’asse principale, prima che intervengano `flex-grow` e `flex-shrink`.

Esempio:

```css
.item {
  flex-basis: 200px;
}
```

Se l’asse principale è orizzontale (`row`), questo grosso modo sostituisce il `width` di partenza:

- base di partenza: 200px,
    
- poi Flexbox calcola eventuali allargamenti o restringimenti in base allo spazio disponibile.
    

In pratica puoi pensare a:

```css
.item {
  flex-basis: 200px;
}
```

come a un “**width di base** controllato da Flexbox”.

---

### **9.3. `flex-shrink`: quanto l’item si rimpicciolisce**

`flex-shrink` dice **quanto un item è disposto a rimpicciolirsi rispetto agli altri** quando lo spazio non basta.

Default: `flex-shrink: 1` per tutti → si restringono tutti in proporzione uguale.

Esempio:

```css
.item {
  flex-basis: 200px;
  flex-shrink: 1;
}

.item-1 {
  flex-shrink: 2;
}
```

Se stringi la finestra e lo spazio non basta:

- tutti e tre gli item si stringono,
    
- ma l’`item-1` (con `flex-shrink: 2`) si rimpicciolisce **il doppio** rispetto agli altri.
    

Interpretazione:

- `flex-shrink` controlla il **rapporto di restringimento** quando manca spazio.
    

---

### **9.4. `flex-grow`: quanto l’item cresce quando c’è spazio extra**

`flex-grow` è l’opposto concettuale: stabilisce **quanto l’item cresce** quando c’è **spazio in più** rispetto alla somma delle basi (`flex-basis`).

Esempio:

```css
.item {
  flex-basis: 200px;
  flex-grow: 0;
}

.item-1 {
  flex-grow: 2;
}

.item-2 {
  flex-grow: 1;
}

.item-3 {
  flex-grow: 0;
}
```

Se il container è molto largo:

- tutti partono da 200px,
    
- lo spazio extra viene ripartito solo tra chi ha `flex-grow > 0`,
    
- il rapporto è 2 : 1 : 0 → l’item-1 si prende **il doppio dello spazio extra** rispetto all’item-2, l’item-3 non cresce oltre i 200px.
    

Metafora:

- immagina una riga con tre blocchi: due pannelli di contenuto, una sidebar.
    
- La sidebar (`flex-grow: 0`) resta sempre stretta.
    
- I pannelli centrali (`flex-grow: 1` e `2`) si allargano occupando lo spazio extra.
    

---

## **10. Align-self: eccezioni per un singolo item**

`align-items` imposta l’allineamento **di tutti gli item** sull’asse trasversale.

Se vuoi che **solo un item** si comporti diversamente, usi `align-self` sull’item:

```css
.container {
  display: flex;
  align-items: flex-end;   /* tutti in basso */
}

.item-3 {
  align-self: flex-start;  /* ma il terzo va in alto */
}
```

- `align-items` → regola di default per tutti.
    
- `align-self` → eccezione per il singolo elemento (può usare gli stessi valori: `flex-start`, `flex-end`, `center`, `stretch`, `baseline`).
    

Non “sovrascrive” nel senso di annullare la proprietà, ma dice:

> “per questo item concreto, ignora il valore globale e usa questo”.

---

## **11. Riassunto mentale di Flexbox**

Per ora, a livello di concetto, ti basta fissare questi pilastri:

- Flexbox vive sempre su **un container** con dei **figli**.
    
- Scrivi `display: flex` sul container, e poi:
    
    - decidi l’asse principale con `flex-direction`,
        
    - decidi se andare a capo con `flex-wrap`,
        
    - distribuisci gli item lungo l’asse principale con `justify-content`,
        
    - li allinei sull’asse trasversale con `align-items`.
        
- Sugli item:
    
    - `order` → cambia l’ordine visivo,
        
    - `flex-basis` → dimensione di base,
        
    - `flex-grow` → quanto cresce se c’è spazio,
        
    - `flex-shrink` → quanto si restringe se lo spazio non basta,
        
    - `align-self` → eccezione puntuale all’`align-items` del container.
        

Nel prossimo passo, Flexbox lo vedrai “sul campo” in esercizi pratici e, più avanti, combinato con il responsive design per gestire layout diversi su desktop e mobile.