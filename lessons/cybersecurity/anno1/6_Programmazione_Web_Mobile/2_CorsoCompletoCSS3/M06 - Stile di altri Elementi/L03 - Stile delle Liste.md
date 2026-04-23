## **Lezione 3: Stile delle Liste**

### **1. Introduzione**

Continuiamo con gli stili degli elementi e oggi trattiamo uno dei componenti più usati nel web design: **le liste** (`ul`, `ol`).  
Le liste hanno una caratteristica che le distingue da quasi tutti gli altri elementi HTML: **il marker**, cioè il pallino, il quadrato o il numero che precede ogni `<li>`.

In questa lezione impariamo a:

- modificare il tipo di marker
    
- usare immagini come marker
    
- controllare la posizione del marker
    
- usare la scorciatoia `list-style`
    
- applicare colori e padding alle liste
    
- capire quando un marker fa parte o meno del contenuto
    

---

### **2. UL e OL: differenza rapida**

Creiamo due liste:

```html
<ul>
  <li>A</li>
  <li>B</li>
  <li>C</li>
</ul>

<ol>
  <li>Primo</li>
  <li>Secondo</li>
  <li>Terzo</li>
</ol>
```

- **`ul` (unordered list)** → lista non ordinata, marker = pallini
    
- **`ol` (ordered list)** → lista ordinata, marker = numeri
    

Le userai spesso entrambe, ma con scopi diversi.

---

### **3. list-style-type**

### **3.1 Cos’è**

`list-style-type` definisce **il tipo di marker** da usare.

Esempi per `ul`:

```css
ul li {
  list-style-type: square;   /* quadratino pieno */
}
```

Altri valori possibili per UL:

- `disc` (default)
    
- `circle`
    
- `square`
    

### **3.2 Per OL abbiamo più opzioni**

Poiché OL rappresenta concetti ordinati, i tipi disponibili sono molti di più:

```css
ol li {
  list-style-type: decimal;
}
```

Puoi anche usare:

- `upper-roman` → I, II, III
    
- `lower-roman` → i, ii, iii
    
- `upper-latin` → A, B, C
    
- `lower-latin` → a, b, c  
    …e tanti altri (controllabili con Ctrl+Space).
    

### **3.3 Nota importante**

Cambiare il marker di un’`ol` in un simbolo “non numerico” **non ha senso semantico**:  
se la lista è ordinata, conserva l’ordine.

---

### **4. list-style-image**

### **4.1 Usare un'immagine come marker**

Puoi sostituire il marker standard con un’icona personalizzata:

```css
ul li {
  list-style-image: url("img/icon.png");
}
```

Anche un’immagine Base64 funziona (come quella usata per i link esterni).

Questo ti permette di creare bullet point personalizzati e coerenti con il design del sito.

---

### **5. list-style-position**

### **5.1 Outside (default)**

Il marker resta **fuori** dal contenuto del `<li>`, nella zona del padding.

```css
list-style-position: outside;
```

### **5.2 Inside**

Il marker viene trattato come **parte del contenuto**:

```css
list-style-position: inside;
```

#### Effetti visivi:

- con _inside_, il marker rientra nella box blu del contenuto
    
- con _outside_, sta nella zona verde del padding
    

È fondamentale capire questa differenza quando si lavora con layout complessi.

---

### **6. Scorciatoia: list-style**

Puoi combinare type, position e image in un’unica dichiarazione:

```css
list-style: circle inside;
```

Oppure:

```css
list-style: square outside url("img/icon.png");
```

### **6.1 Quando usarla**

- Preferibile quando è chiaro e leggibile
    
- Evita di ripetere tre proprietà separate
    
- Fa risparmiare byte sul CSS (utile su progetti grandi)
    

Sempre ricordando che **leggibilità > risparmio**, ma in questo caso la scorciatoia è molto chiara.

---

### **7. Esempio pratico con colori e padding**

### **7.1 Stilizziamo UL**

```css
ul {
  background: blue;
  padding: 20px;
  list-style-position: outside;
}

ul li {
  margin-bottom: 10px;
}
```

- il background blu copre tutta l’area del contenitore
    
- il marker resta fuori dal contenuto perché la posizione è `outside`
    

### **7.2 Stilizziamo OL**

```css
ol {
  background: red;
  padding: 20px;
  list-style-position: inside;
}

ol li {
  margin-bottom: 10px;
}
```

- il background rosso copre tutta la parte interna
    
- i marker (i numeri) fanno parte del contenuto perché `inside`
    

Questo ti permette di controllare perfettamente la disposizione grafica delle tue liste.

---

### **8. Importanza delle liste nel web**

Le liste non sono un dettaglio minore:

- sono strutture usatissime nei siti
    
- organizzano contenuti in modo leggibile e lineare
    
- aiutano la SEO
    
- sono perfette per guide, documentazione, tutorial, articoli
    
- molti layout reali sono costruiti come liste (menu, sidebar, card, sezioni ordinate)
    

I tuoi corsi, ad esempio, sono liste di sezioni → liste di video → liste di concetti.

Saperle stilizzare bene è fondamentale.

---

### **9. Conclusione**

In questa lezione hai imparato a:

- scegliere il marker giusto
    
- sostituire il marker con un’immagine
    
- modificare la posizione inside/outside
    
- usare la scorciatoia `list-style`
    
- applicare padding e margini per un layout pulito
    
- lavorare con UL e OL in modo semantico ed efficace
    

Siamo pronti a proseguire:  
nella prossima lezione vedremo **come stilizzare le tabelle**, e subito dopo faremo un esercizio per consolidare questa sezione.