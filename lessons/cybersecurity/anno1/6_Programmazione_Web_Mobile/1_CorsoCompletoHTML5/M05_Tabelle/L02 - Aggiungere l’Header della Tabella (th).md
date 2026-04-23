## **Lezione 2: Aggiungere l’Header della Tabella (`<th>`)**

### **1. Perché serve un header**

La tabella primitiva che abbiamo costruito nella lezione precedente è funzionale, ma **imprecisa**: chi legge può intuire che “Luca Rossi” sia un nome e cognome, ma non capisce subito:

- “30” è l’età?
    
- un voto?
    
- un punteggio?
    
- una quantità?
    

Lo stesso vale per la colonna “Calcio”:  
è un hobby? un lavoro? cosa guarda in TV? cosa gioca nel weekend?

Senza un header, un utente deve **indovinare**.  
Per questo esiste l’elemento **`<th>`**: definisce i **nomi delle colonne**.

---

### **2. Come si crea un header**

L’header è semplicemente una **riga aggiuntiva** (`<tr>`) posizionata in cima alla tabella, con celle speciali chiamate **`<th>`** invece di `<td>`.

Esempio:

```html
<table>
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

---

### **3. Perché `<th>` è speciale**

Appena inserisci `<th>`, succedono due cose automaticamente:

1. **Il testo diventa in grassetto**, senza bisogno di CSS.
    
2. **Il contenuto viene interpretato come “intestazione colonna”**, quindi ha senso semantico.
    

Questo ha effetti enormi:

- i **crawler** capiscono subito quali siano le intestazioni
    
- gli **screen reader** possono leggere correttamente riga per riga
    
- tu puoi stilizzare in modo separato header e dati
    
- il codice è leggibile e auto-esplicativo
    
- il CSS futuro potrà usare selettori come `th { ... }` senza influenzare i dati
    

Se un domani vuoi colorare l’header di blu:

```css
th {
    background-color: royalblue;
    color: white;
}
```

Non toccherai mai i `<td>`.

---

### **4. Prima miglioria “semantica” della nostra tabella**

Guardando il risultato:

- la tabella rimane esteticamente brutta
    
- ma diventa **chiara**, **comprensibile**, **semantica**
    

È il primo passo verso una tabella professionale.

Quando inizieremo a introdurre:

- sezioni (`<thead>`, `<tbody>`, `<tfoot>`)
    
- spanning (`rowspan`, `colspan`)
    
- caption
    
- tabelle complesse
    

le intestazioni saranno essenziali per mantenere ordine e significato.

---

### **5. Conclusione**

Hai imparato:

- perché gli header sono indispensabili
    
- cosa significa “intestazione di colonna”
    
- cosa distingue `<th>` da `<td>`
    
- perché questo migliora leggibilità, semantica e accessibilità
    
- come strutturare la tua tabella in modo corretto fin dal primo livello
    

Nel prossimo video cominciamo a **complicare leggermente la tabella**, aggiungendo nuovi elementi per renderla più strutturata.