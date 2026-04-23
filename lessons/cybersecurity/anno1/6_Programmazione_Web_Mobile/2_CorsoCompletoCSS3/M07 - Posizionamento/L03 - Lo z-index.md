## **Lezione 3: Lo `z-index`**

### **1. Introduzione alla terza dimensione**

Finora hai sempre ragionato in **2D**:

- un elemento sotto l’altro (asse **Y**),
    
- o a sinistra/destra (asse **X**).
    

Con `position: absolute`, `fixed` e `sticky` hai introdotto un terzo asse:

- l’asse **Z**, cioè la **profondità**,  
    che stabilisce **chi sta sopra** e **chi sta sotto** nella pagina.
    

Il browser di default impila gli elementi come se fossero **cartoncini piatti**, uno sopra l'altro.  
Lo `z-index` ti permette di decidere **l’ordine di impilamento**.

---

### **2. Ripasso rapido: quando lo `z-index` funziona**

Lo `z-index` ha effetto **solo** sugli elementi che hanno una posizione diversa da `static`:

- `position: relative`
    
- `position: absolute`
    
- `position: fixed`
    
- `position: sticky`
    

Se l’elemento è `static`, lo `z-index` **non funziona**.

---

### **3. Setup iniziale per capire la sovrapposizione**

```html
<p class="pg">ciao</p>
<div class="box">ciao</div>
<div class="box2">ciao</div>
```

```css
.pg {
  padding: 20px;
  background-color: orange;
}

.box {
  background-color: green;
  padding: 10px;
  position: absolute;
  top: 40px;
  left: 20px;
}

.box2 {
  background-color: red;
  padding: 10px;
  position: absolute;
  top: 20px;
  left: 30px;
}
```

Risultato visivo:

- `box2` (rossa) si trova **sopra** `box` (verde)  
    perché viene **dopo nel DOM**, e quindi “vince” di default.
    

Senza `z-index`, il browser disegna gli elementi **in ordine di codice**.

---

### **4. Cos’è davvero lo `z-index`**

`z-index` è un **numero intero** che rappresenta “quanto è in alto il cartoncino”.

- maggiore è il numero → più l’elemento sta **sopra**
    
- minore è il numero → l’elemento sta **sotto**
    

Default:  
Se non specificato, lo `z-index` è **0**.

Esempi:

```css
.box {
  z-index: 0;
}

.box2 {
  z-index: 1;   /* box2 sta sopra box */
}
```

Se inverti:

```css
.box {
  z-index: 2;
}

.box2 {
  z-index: 1;
}
```

La card verde torna **sopra** la card rossa.

---

### **5. Cosa succede se usi numeri negativi**

```css
.box {
  z-index: -1;
}
```

L’elemento può:

- finire **dietro** altri elementi,
    
- in alcuni casi finire **dietro al body** o dietro contenitori opachi,
    
- diventare inaccessibile ai click.
    

Un caso pratico dove serve:

- far rimanere una “decorazione” visuale **dietro** al contenuto (pattern, illustrazione).
    

---

### **6. Esempio completo per visualizzare la logica dei cartoncini**

```css
.box {
  background-color: green;
  position: absolute;
  top: 40px;
  left: 20px;
  z-index: 1;
}

.box2 {
  background-color: red;
  position: absolute;
  top: 20px;
  left: 30px;
  z-index: 0;
}
```

Effetto mentale:

- hai due cartoncini fisici,
    
- li accavalli un po’,
    
- decidi chi sta sopra spostandoli avanti/indietro.
    

Questo è lo `z-index`.

---

### **7. Perché il browser usa gli “stacking contexts”**

(non approfondito ora, ma ti do il concetto base che ti eviterà bug futuri)

Ogni volta che crei elementi con:

- `position` non-static
    
- `z-index` dichiarato
    
- `opacity < 1`
    
- `transform`
    
- `filter`
    
- `flex` o `grid` in certi casi
    

Crei un **nuovo stacking context**.

Significa:  
l’elemento con `z-index` fa da **contenitore**, e i figli non possono “uscire” dal suo livello in Z.

Ma questo lo vedremo avanti nei layout avanzati e nel corso CSS completo.

---

### **8. Quando userai davvero lo `z-index`?**

Non è una proprietà da usare spesso, ma:

- **navbar fissa sopra il contenuto**
    
- **popup**
    
- **modal**
    
- **menu mobile che si apre sopra tutto**
    
- **tooltip**
    
- **notifiche**
    
- **overlay scuri dietro finestre modali**
    
- **card che hanno elementi in overlay (cuore, badge, angoli arrotondati "sforati")**
    

Quindi sì: in quasi ogni progetto reale lo userai almeno una o due volte.

---

### **9. Ricapitolone semplice**

- senza `position`, lo `z-index` NON funziona
    
- `z-index` controlla la profondità (asse Z)
    
- valore alto → sopra
    
- valore basso → sotto
    
- valore negativo → sotto TUTTI
    
- se due elementi hanno lo stesso `z-index`, vince l’**ordine nel DOM**
    
- pensa sempre ai **cartoncini sovrapposti**
    

---

La lezione successiva sarà su **float**, per capire come venivano costruiti i layout prima di flexbox. Anche se oggi si usa molto meno, ti serve per leggere codice vecchio e capire comportamenti in WordPress e template storici.