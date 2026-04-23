## **Lezione: `<thead>`, `<tbody>` e `<tfoot>` — Strutturare la Tabella in Sezioni Semantiche**

### **1. Perché questi elementi sono importanti**

Finora hai visto:

- `<caption>` per dare un titolo
    
- `<colgroup>` per stilare colonne
    
- `<th>`, `<td>`, `<tr>` per i dati
    
- `colspan` e `rowspan` per unire celle
    

Ora introduciamo tre elementi che **non cambiano l’aspetto** della tabella e **non modificano l’accessibilità**, ma sono fondamentali per:

- organizzare il codice
    
- rendere la tabella leggibile anche quando diventa enorme
    
- separare logicamente header, contenuto e footer
    
- applicare stili CSS in modo pulito e naturale
    

I tre elementi sono:

- **`<thead>`** — la testa della tabella
    
- **`<tbody>`** — il corpo
    
- **`<tfoot>`** — il piede (zona finale)
    

Sono detti **elementi semantici**, perché descrivono il significato delle varie parti della tabella.

---

### **2. Come strutturiamo la tabella**

Ripartiamo dalla versione completa, e aggiungiamo le sezioni.

#### **HEADER della tabella — `<thead>`**

Contiene esclusivamente la riga con i nomi delle colonne.

```html
<thead>
    <tr>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Età</th>
        <th>Quota</th>
    </tr>
</thead>
```

#### **BODY della tabella — `<tbody>`**

Qui dentro mettiamo TUTTE le righe dei dati.

```html
<tbody>
    <tr>
        <td>Luca</td>
        <td>Rossi</td>
        <td>30</td>
        <td>400</td>
    </tr>
    <tr>
        <td>Marco</td>
        <td>Verdi</td>
        <td>25</td>
        <td>500</td>
    </tr>
</tbody>
```

#### **FOOTER della tabella — `<tfoot>`**

Serve per:

- note finali
    
- pulsanti di paginazione (“Mostra 20 righe”, “Pagina successiva”)
    
- totali
    
- comandi
    
- riassunti
    

Esempio semplice:

```html
<tfoot>
    <tr>
        <td colspan="3">Totale</td>
        <td>900</td>
    </tr>
</tfoot>
```

---

### **3. Struttura completa**

```html
<table>
    <caption>Quote pagate per ingresso in società</caption>

    <colgroup>
        <col>
        <col>
        <col>
        <col>
    </colgroup>

    <thead>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Età</th>
            <th>Quota</th>
        </tr>
    </thead>

    <tbody>
        <tr>
            <td>Luca</td>
            <td>Rossi</td>
            <td>30</td>
            <td>400</td>
        </tr>

        <tr>
            <td>Marco</td>
            <td>Verdi</td>
            <td>25</td>
            <td>500</td>
        </tr>
    </tbody>

    <tfoot>
        <tr>
            <td colspan="3">Totale</td>
            <td>900</td>
        </tr>
    </tfoot>
</table>
```

Visivamente non cambia nulla…  
ma internamente la tabella diventa **perfetta**, leggibile, pulita, professionale.

---

### **4. Perché questi elementi sono così utili**

#### **A) Ordine mentale e leggibilità del codice**

Quando avrai tabelle complesse con:

- header multipli
    
- grouping
    
- righe evidenziate
    
- totali parziali
    
- filtri e paginazione
    

`<thead>`, `<tbody>` e `<tfoot>` sono l’unico modo per non impazzire.

#### **B) CSS facile ed elegante**

Quando useremo CSS, potremo fare:

```css
thead {
    background-color: #eee;
    font-weight: bold;
}

tbody tr:nth-child(even) {
    background-color: #fafafa;
}

tfoot {
    background-color: #ddd;
}
```

Queste cose sarebbero molto più complicate senza sezioni semantiche.

#### **C) Approccio professionale**

Tutte le tabelle professionali — da Bootstrap a Material Design, fino ai data-table moderni — usano questa struttura.  
È uno standard di qualità.

---

### **5. Nota importantissima su `<colgroup>`**

Come visto nel video:

- `<colgroup>` può tranquillamente convivere con `<thead>`
    
- può essere inserito **subito dopo `<caption>`**
    
- mantiene perfettamente la sua funzione
    

```html
<table>
    <caption>...</caption>

    <colgroup>
        <col>
        <col>
        <col>
        <col style="background-color: aqua;">
    </colgroup>

    <thead>...</thead>
    <tbody>...</tbody>
</table>
```

---

### **6. Conclusione**

Hai aggiunto:

- **tre elementi fondamentali per la struttura semantica della tabella**
    
- utili per te, per il codice, per il CSS futuro
    
- non obbligatori, ma caldamente raccomandati in progetti seri
    

Ora la tabella è _finalmente_ un oggetto completo, professionale e pronto a diventare davvero complesso.