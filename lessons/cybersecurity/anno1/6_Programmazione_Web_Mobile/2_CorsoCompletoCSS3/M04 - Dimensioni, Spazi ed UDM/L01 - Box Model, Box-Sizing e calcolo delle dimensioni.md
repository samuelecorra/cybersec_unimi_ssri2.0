## **Lezione 1: Box Model, Box-Sizing e calcolo delle dimensioni**

### **1. Introduzione alla sezione**

Comincia la sezione dedicata alle **dimensioni, spazi e unità di misura**.  
Prima di parlare di margini, padding, width, height e unità come `px`, `rem`, `%`, bisogna capire il concetto fondamentale: **il Box Model**.  
Subito dopo, vedremo i due metodi con cui CSS calcola realmente la dimensione finale di un elemento: **content-box** e **border-box**.

---

### **2. Il Box Model: la scatola di ogni elemento**

Ogni elemento HTML è organizzato come una “cipolla” composta da 4 strati concentrici:

### **2.1 Contenuto (content)**

È la parte blu nel DevTools.  
Contiene il testo, le immagini o qualsiasi contenuto interno.

### **2.2 Padding**

È lo spazio **interno** tra contenuto e bordo.  
Se scrivi `padding: 20px`, aumenti lo spazio interno bianco attorno al contenuto.

### **2.3 Border**

È la cornice visibile dell’elemento.  
Può avere spessore, stile e colore.

### **2.4 Margin**

È lo spazio **esterno**, la distanza dell’elemento dagli altri elementi circostanti.

---

### **3. Il Box Model in DevTools**

In **F12 → Elements**, in basso trovi sempre il riquadro del **Box Model**.

Mostra esattamente:

- dimensione reale del contenuto
    
- padding effettivo
    
- spessore dei bordi
    
- margini
    
- somma totale delle dimensioni finali
    

Perfetto per capire gli effetti di ciò che scrivi in CSS.

---

### **4. Creiamo un esempio pratico**

Un div con:

- `width: 100px`
    
- `height: 100px`
    
- `padding: 20px`
    
- `border: 10px solid black`
    
- `margin: 40px`
    
- `background-color: red`
    

In DevTools vedrai:

- **content** (100×100)
    
- **padding** (20+20)
    
- **border** (10+10)
    
- **margin** (40+40)
    

È fondamentale capire che il **contenuto** è solo una parte della dimensione finale.

---

### **5. Il problema: come CSS calcola la dimensione finale?**

Con tutte queste parti (content + padding + border + margin), la vera dimensione dell’elemento può cambiare parecchio.

E qui entrano in gioco i due modi di calcolo:

---

## **6. Box-Sizing: il modo in cui CSS calcola width e height**

CSS può interpretare le dimensioni in due modi:

### **6.1 Metodo 1 — `content-box` (comportamento di default)**

È il comportamento standard del browser.

Quando scrivi:

```
width: 100px;
height: 100px;
```

CSS applica queste dimensioni **solo al contenuto**, ignorando padding e bordi.

Questo significa che:

```
dimensione totale = content + padding + border
```

Esempio del nostro div:

- content = 100px
    
- padding = 20px + 20px = 40px
    
- border = 10px + 10px = 20px  
    Totale = 100 + 40 + 20 = **160px**
    

In DevTools vedrai esattamente questo valore.

---

### **6.2 Metodo 2 — `border-box`**

Qui succede il contrario:  
**width e height indicano l'intera dimensione finale**, già comprensiva di padding e bordi.

```
width: 100px;
```

Non significa più “100px di contenuto”.  
Significa:

```
contenuto + padding + border = 100px totali
```

Quindi CSS comprime il contenuto per farlo rientrare nei 100px finali.

Il risultato:  
dimensione del div = **100px**, sempre.

---

### **7. La metafora perfetta: prezzo con IVA o senza IVA**

- **content-box** → il prezzo è **senza IVA** (aggiungi padding/border dopo).
    
- **border-box** → il prezzo è **già con IVA inclusa** (la cifra è totale e definitiva).
    

---

### **8. Come si attiva border-box**

Puoi impostarlo su un singolo elemento:

```
box-sizing: border-box;
```

Oppure, come fanno quasi tutti i siti moderni:

```
* {
  box-sizing: border-box;
}
```

Questo ha due vantaggi enormi:

- dimensioni più prevedibili
    
- layout più stabili
    
- meno calcoli mentali da fare
    
- meno errori nei layout complessi
    

---

### **9. Riepilogo finale**

#### **Box Model**

1. Content
    
2. Padding
    
3. Border
    
4. Margin
    

#### **Content-box (default)**

- width/height riferite solo al contenuto
    
- padding e border _si sommano_
    
- dimensione finale aumenta
    

#### **Border-box**

- width/height riferite all’intero elemento
    
- padding e border _inclusi_
    
- dimensione finale rimane fissa
    

---

### **10. Prossimo argomento**

Adesso che il box model è chiaro, passiamo alla parte successiva:  
**I margini** (margin, margin auto, margin collapsing e regole pratiche).