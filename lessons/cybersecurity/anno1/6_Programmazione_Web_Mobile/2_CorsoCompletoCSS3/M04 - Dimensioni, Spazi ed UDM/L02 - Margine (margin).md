## **Lezione 2: Margine (margin) – tutto quello che devi sapere, senza perdere un singolo concetto**

Adesso che il **box model** è chiaro, possiamo finalmente guardare da vicino la prima dimensione esterna del modello: **il margine**.

Il margine è fondamentale per _posizionare_ correttamente gli elementi sulla pagina e capire come si distanziano gli uni dagli altri e dai bordi del loro contenitore.

---

## **1. Che cos’è il margine?**

Il **margin** è lo **spazio esterno** di un elemento.

- Non fa parte della dimensione dell’elemento.
    
- Non influenza width/height.
    
- Serve solo a determinare **la distanza** dell'elemento:
    
    - dal **parente** (es. body)
        
    - dagli altri elementi adiacenti (sopra, sotto, destra, sinistra)
        

Quando un div ha `margin: 20px`, questo significa:

- 20px sopra
    
- 20px sotto
    
- 20px a destra
    
- 20px a sinistra
    
- e quindi il div sarà _staccato_ dal resto di 20px in ogni direzione.
    

---

## **2. Il margine del body esiste di default**

Quando hai aperto il div e hai visto uno “spazio bianco” tra il bordo della finestra e il div…  
Quello spazio **non era del div**, ma del **margin del body** (8px di default nei browser).

Appena hai fatto:

```
body {
  margin: 0;
}
```

immediatamente il div si è attaccato ai bordi della finestra come previsto.

---

## **3. Margin applicato a un elemento**

Se hai un div dentro il body:

- `margin-top` spinge il div verso il basso
    
- `margin-left` lo spinge verso destra
    
- `margin-bottom` spinge ciò che viene dopo
    
- `margin-right` spinge ciò che è a destra (quando presente)
    

Esempio, con:

```
margin-top: 40px;
margin-bottom: 5px;
margin-left: 100px;
```

succede esattamente questo:

- il div scende di 40px
    
- quello sotto sale fin quasi a toccarlo (5px)
    
- tutto è spostato 100px verso destra
    
- destra rimane invariato se non specifichi nulla
    

---

## **4. La scorciatoia `margin`**

La proprietà `margin` può accettare **1, 2, 3 o 4 valori**.

### **4.1 Un solo valore**

```
margin: 20px;
```

Applica 20px **ovunque**.

---

### **4.2 Due valori**

```
margin: 20px 0;
```

Interpretazione:

- **20px** → verticale (top e bottom)
    
- **0** → orizzontale (left e right)
    

---

### **4.3 Tre valori**

```
margin: 20px 0 100px;
```

Interpretazione:

- **20px** → top
    
- **0** → left e right
    
- **100px** → bottom
    

La regola è:  
**top – left/right – bottom**

---

### **4.4 Quattro valori**

```
margin: 20px 0 100px 200px;
```

Interpretazione:

In senso orario:

1. **top**
    
2. **right**
    
3. **bottom**
    
4. **left**
    

Quindi:

- top: 20px
    
- right: 0
    
- bottom: 100px
    
- left: 200px
    

---

## **5. Il margine non fa parte della dimensione del box**

A differenza di:

- padding
    
- border
    

il margine è completamente esterno al box.

`width` e `height` **non vengono toccati** dal margin.

Il margine definisce soltanto la **posizione** dell’elemento, non la sua dimensione.

---

## **6. Margin-top e margin-bottom possono “collassare”**

Lo vedremo in un video successivo, ma anticipo il concetto:  
quando due box verticali sono uno sopra l’altro, i loro margini verticali possono **sommarsi in un modo particolare** (collassano).

Restiamo però concentrati su quello che ti serve ora.

---

## **7. Messaggio visivo da ricordare per sempre**

Il margine è lo **spazio bianco esterno**, quello che separa i box come persone in discoteca:  
ognuno con un po’ di spazio personale, per non finire “a sardine”.

---

## **8. Riepilogo super chiaro**

- Il **margin** distanzia l’elemento da ciò che lo circonda.
    
- Può essere definito per direzione o tramite scorciatoia.
    
- Non aumenta la dimensione dell’elemento.
    
- Il body ha un margine di default.
    
- Conoscere bene le scorciatoie è fondamentale per scrivere CSS veloce e pulito.
    

---

## **9. Prossima lezione**

Adesso passiamo alla seconda dimensione essenziale dentro il box model:

### **→ Il padding**

lo spazio interno tra contenuto e bordo.