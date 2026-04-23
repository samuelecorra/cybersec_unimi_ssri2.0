## **Lezione 9: Text Layout**

### **1. Introduzione**

In questa lezione vediamo come controllare **lo spazio** e **il layout** del testo, cioè tutte quelle proprietà CSS che determinano come un paragrafo viene impaginato, allineato e distribuito sulla pagina.

Usiamo un paragrafo `<p>` con un lorem ipsum abbastanza lungo, così possiamo osservare meglio ogni modifica.

---

### **2. Text Alignment**

### **2.1 A cosa serve**

`text-align` stabilisce come il testo viene allineato all’interno del suo contenitore.

Funziona esattamente come gli allineamenti che conosci già da Word o Google Docs.

### **2.2 Valori principali**

I valori davvero utili sono:

- **left** → testo allineato a sinistra (default)
    
- **right** → testo allineato a destra
    
- **center** → testo centrato
    
- **justify** → testo giustificato, che occupa tutta la larghezza possibile
    

Esistono anche altri valori come `start`, `end`, `initial`, `revert`, ma per ora non ci servono e si comportano come le varianti standard.

Esempio:

```css
p {
  text-align: justify;
}
```

---

### **3. Line Height**

### **3.1 Cos’è l’interlinea**

`line-height` definisce **lo spazio verticale tra una riga e l’altra** in un paragrafo.  
È l’interlinea che hai già incontrato in Word, ma controllata via CSS.

### **3.2 Come funziona**

Se usi:

```css
line-height: 2rem;
```

CSS calcola l’altezza totale della riga usando il font-size di riferimento.

Poiché un paragrafo ha default:

- `font-size: 16px`
    
- `2rem = 32px`
    

l’interlinea diventa quindi **32px**.

### **3.3 Effetto visivo**

Aumentando `line-height`, il testo diventa più arioso e leggibile.  
Diminuendola, diventa più compatto e denso.

---

### **4. Letter-Spacing**

### **4.1 Cos’è**

`letter-spacing` regola la distanza tra **ogni lettera** della stessa parola.

Esempio:

```css
letter-spacing: 5px;
```

Ogni carattere viene separato dagli altri di 5px.

### **4.2 Effetto**

Utile per:

- titoli stilizzati
    
- effetti grafici
    
- aumentare la leggibilità in certi font molto compressi
    

---

### **5. Word-Spacing**

### **5.1 Cos’è**

`word-spacing` regola lo spazio **tra una parola e l’altra**.

Esempio:

```css
word-spacing: 30px;
```

In questo caso ogni parola è molto distanziata dalla successiva.

### **5.2 Differenza con letter-spacing**

- `letter-spacing` → tra lettere
    
- `word-spacing` → tra parole
    

Sono concetti distinti e controllabili in modo indipendente.

---

### **6. Altre proprietà del text layout**

Abbiamo accennato a `word-break`, `overflow-wrap` e proprietà simili, che controllano come il testo va a capo quando incontra spazi, parole troppo lunghe o contenitori angusti.

Queste le vedremo più avanti perché sono concetti più avanzati, soprattutto in ottica responsive.

Per ora è sufficiente sapere che:

- le proprietà che abbiamo visto oggi sono la base
    
- i controlli sui “break” del testo arrivano al livello successivo
    

---

### **7. Conclusione**

Con questa lezione hai appreso tutte le proprietà essenziali per controllare la **formattazione del testo**:

- `text-align`
    
- `line-height`
    
- `letter-spacing`
    
- `word-spacing`
    

Ora hai un controllo completo sul layout del testo nei paragrafi.

Nella prossima lezione parleremo di un altro concetto fondamentale: **overflow**, cioè cosa succede quando un contenuto supera lo spazio disponibile.