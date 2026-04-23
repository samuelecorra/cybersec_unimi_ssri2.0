## **Lezione 5 – La proprietà `float` e la gestione del testo attorno alle immagini**

### **1. Setup della lezione**

Per evitare lezioni troppo lunghe, hai già preparato:

- **tre div** (`.box1`, `.box2`, `.box3`) con background diversi
    
- ognuno con **margin-bottom: 10px**
    
- una **section** contenente un’**immagine** e un **paragrafo**
    

Questa struttura serve per mostrare il comportamento di `float`.

---

### **2. Il significato di _float_**

`float` significa letteralmente **“galleggiare”**:  
l’elemento galleggia a sinistra o a destra e il contenuto successivo gli scorre **attorno**.

È una proprietà storicamente **molto usata** per costruire layout (anni 2000–2010), ma oggi è quasi totalmente sostituita da:

- **flexbox**
    
- **grid**
    

Tuttavia _devi_ conoscerla per capire codice legacy, WordPress vecchi temi, blog e layout classici.

---

### **3. Float su un elemento block**

Hai tre `div` verticali (block).  
Appena applichi:

```css
.box1 {
  float: left;
}
```

succedono **due cose**:

1. `.box1` rimane un elemento **block**, ma non occupa più l’intera riga.
    
2. `.box2` sale e gli si **affianca** sulla stessa riga (se c’è spazio).
    

È come dire:

> “Non prendere tutta la riga: galleggia a sinistra”.

Se fai:

```css
float: right;
```

lo stesso div si posiziona sulla **destra**, e ciò che segue si sposta accanto.

---

### **4. Il caso più famoso: immagine + testo**

Quando fai:

```css
img {
  float: left;
}
```

il testo scorre attorno all’immagine:

- l’immagine “galleggia” a sinistra
    
- il testo si adatta intorno ai bordi
    

Per rendere tutto più pulito, si aggiunge un distacco:

```css
img {
  float: left;
  width: 300px;
  margin-right: 10px;
}
```

Risultato: il testo si distribuisce armoniosamente intorno all’immagine.

È la famosa grafica **alla Word**, dei primi blog e dei giornali online.

---

### **5. Perché oggi non si usa quasi più float?**

Perché:

- con `float` il testo si attacca all’elemento in modi non sempre prevedibili
    
- la gestione degli allineamenti è complicata
    
- serve spesso `clear` per evitare effetti indesiderati
    
- flexbox e grid risolvono tutto con più controllo e meno bug
    

---

### **6. Il problema del float: influenza anche gli elementi successivi**

Quando un elemento è flottante, anche gli elementi dopo **subiscono** il suo effetto.

Esempio: se l’immagine è `float:left`, il paragrafo successivo si stringe e si “incolona” intorno ad essa, anche quando non vorresti.

Per evitare questo comportamento si usa la proprietà **complementare**: `clear`.

---

### **7. La proprietà `clear` (il contrario di `float`)**

`clear` impone a un elemento:

> “Ignora il float alla tua sinistra/destra”.

Esempio:

```css
p {
  clear: left;
}
```

Effetto:

- il paragrafo **non sarà più influenzato dal float dell’immagine**
    
- tornerà a posizionarsi sotto, a tutta larghezza
    

Funziona anche con:

```css
clear: right;
clear: both;
```

**Quando serve davvero?**

- quando hai più elementi flottanti
    
- quando vuoi che solo alcuni subiscano il float
    
- per evitare comportamenti imprevedibili nei layout “vecchi”
    

---

### **8. Perché non approfondire troppo float/clear?**

Perché:

- sono tecniche “storiche”
    
- verranno sostituite completamente da Flexbox e Grid
    
- rischiano di confondere ora che stai costruendo mentalmente i layout moderni
    

Conoscere float è utile per “capire” il passato,  
ma per costruire layout pratici userai quasi solo:

- **display:flex**
    
- **display:grid**
    

---

### **9. Ricapitolazione**

**Float:**

- fa “galleggiare” l’elemento a sinistra o a destra
    
- il contenuto successivo si adatta attorno ad esso
    
- usato molto con immagini e testo
    
- oggi quasi sostituito da flex e grid
    

**Clear:**

- serve per dire a un elemento di **non essere influenzato** dal float precedente
    
- `clear: left`, `clear: right`, `clear: both`
    

---

Quando sarai su **Flexbox** (prossime lezioni) vedrai immediatamente perché float/clear sono stati definitivamente superati, e capirai molto meglio l’evoluzione dei layout.