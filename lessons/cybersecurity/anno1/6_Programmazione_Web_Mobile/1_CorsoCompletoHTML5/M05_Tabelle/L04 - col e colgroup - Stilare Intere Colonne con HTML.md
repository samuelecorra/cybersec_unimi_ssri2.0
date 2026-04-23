## **Lezione 4: `<col>` e `<colgroup>` — Stilare Intere Colonne con HTML**

### **1. Perché servono `<col>` e `<colgroup>`**

Hai appena imparato a fare:

- intestazioni (`<th>`)
    
- unione di celle orizzontale (`colspan`)
    
- unione di celle verticale (`rowspan`)
    

Ora serve un’altra tecnica molto utile:  
**stilare un’intera colonna** senza dover intervenire su ogni singola cella.

Perché è importante?

- In CSS “puro” cambiare lo stile _di una sola colonna_ è più complicato di quanto sembri.
    
- Bisogna conoscere selettori avanzati come `nth-child()` o layout particolari.
    
- Per uno principiante, non è immediato.
    

HTML ci dà invece due elementi potentissimi:

- **`<colgroup>`** → rappresenta il gruppo di colonne
    
- **`<col>`** → rappresenta una singola colonna
    

E il browser applica **automaticamente** lo stile alla colonna intera.

Sono una _scorciatoia elegante_ che vale la pena conoscere.

---

### **2. Struttura: dove vanno `<colgroup>` e `<col>`**

La posizione corretta è **subito dentro `<table>`, prima di `<thead>` o `<tr>`**.

Esempio scheletro:

```html
<table>
    <colgroup>
        <col>
        <col>
        <col>
        <col>
    </colgroup>

    <tr> ... </tr>
    <tr> ... </tr>
</table>
```

Le quattro `<col>` rappresentano le quattro colonne visibili della tabella.

Se la tabella ha 4 colonne → devi avere 4 `<col>`.  
Se ne hai di meno, l’indicizzazione salta.

---

### **3. Applicare stile a una colonna**

La potenza sta qui: puoi usare degli **stili inline** sul singolo `<col>`.

Esempio: colorare tutta la quarta colonna di giallo.

```html
<table>
    <colgroup>
        <col>
        <col>
        <col>
        <col style="background-color: yellow;">
    </colgroup>

    <tr>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Età</th>
        <th>Hobby</th>
    </tr>
    <tr>
        <td>Luca</td>
        <td>Rossi</td>
        <td>30</td>
        <td>Calcio</td>
    </tr>
    <tr>
        <td>Marco</td>
        <td>Verdi</td>
        <td>25</td>
        <td>Basket</td>
    </tr>
</table>
```

Risultato:

- **tutta la colonna Hobby** diventa gialla
    
- senza toccare nessun `<td>`
    
- senza selettori avanzati
    
- senza CSS complesso
    

Semplice, diretto, immediato.

---

### **4. Modificare la larghezza di una colonna**

Puoi anche controllare la larghezza:

```html
<col style="width: 300px;">
```

E il browser allarga **tutte le celle della colonna**.

Esempio combinato:

```html
<col style="width: 300px; background-color: yellow;">
```

---

### **5. Limiti di `<col>` e `<colgroup>`**

È importante ricordare:

- possono gestire **solo**:
    
    - colori di sfondo
        
    - bordi
        
    - larghezza
        
    - visibilità (ad esempio: nascondere una colonna)
        
- non possono gestire:
    
    - padding
        
    - font
        
    - testo
        
    - contenuti interni delle celle
        

Sono utili **solo** per proprietà che riguardano _la colonna come blocco_.

Per tutto il resto useremo CSS vero.

---

### **6. Attenzione: il numero di `<col>` deve corrispondere alle colonne**

Se la tabella ha 4 colonne, ma scrivi:

```html
<colgroup>
    <col>
    <col>
    <col>
</colgroup>
```

E poi provi a stilare la quarta colonna…  
**non funziona**, perché non l’hai rappresentata.

È essenziale che la struttura del `colgroup` rispecchi la struttura reale della tabella.

---

### **7. Ricapitolazione**

Ora nella tua cassetta degli attrezzi hai:

- `<table>` → contenitore
    
- `<tr>` → righe
    
- `<th>` → header
    
- `<td>` → celle
    
- `colspan` → unione orizzontale
    
- `rowspan` → unione verticale
    
- `<colgroup>` + `<col>` → stilare intere colonne senza CSS avanzato
    

Con questi elementi puoi già creare tabelle professionali, strutturate, ben leggibili.

Nel prossimo video, faremo un **esercizio pratico di riepilogo**, per verificare che tutto sia solido prima di introdurre le sezioni `<thead>`, `<tbody>` e `<tfoot>`.