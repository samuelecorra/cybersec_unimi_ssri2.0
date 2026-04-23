## **Lezione 1: Introduzione alle Tabelle in HTML**

### **1. Perché nasce questo modulo**

In questo nuovo modulo studierai **le tabelle**.  
Saranno circa **dieci lezioni**, perché le tabelle sono:

- semplici nella loro forma più primitiva
    
- ma possono diventare molto complesse
    
- e soprattutto **semantiche** (intestazioni, caption, raggruppamenti, scope, accessibility)
    

In questo primo video non pensiamo ancora allo stile, né alle funzionalità avanzate:  
**mettiamo le fondamenta**, costruendo la tabella più semplice possibile.

---

### **2. La struttura minima di una tabella**

Una tabella HTML è composta da:

- **righe**
    
- **celle**
    

Come elementi abbiamo:

- `<table>` → contiene tutta la tabella
    
- `<tr>` → _table row_, una riga
    
- `<td>` → _table data_, una cella di una riga
    

Esempio base:

```html
<table>
    <tr>
        <td>Luca Rossi</td>
        <td>30</td>
        <td>Calcio</td>
    </tr>
    <tr>
        <td>Marco Verdi</td>
        <td>25</td>
        <td>Basket</td>
    </tr>
</table>
```

Risultato:

- una tabella primitiva
    
- nessuno stile
    
- funzionamento corretto, righe e colonne già visibili
    

Se duplichi le righe, ottieni subito una struttura tabellare riconoscibile.

---

### **3. Aggiungiamo un bordo minimo per capire cosa succede**

Le tabelle **di default sono brutte**, perché HTML puro non ha stile.

Possiamo mettere un bordo temporaneo:

```html
<table style="border: 1px solid red">
```

Oppure dare padding alle celle (sempre solo per capire meglio):

```html
<td style="padding: 5px">Luca Rossi</td>
```

Questo aiuta visivamente, ma ricorda:

> **lo stile appartiene a CSS, non a HTML**  
> quindi questo è solo un supporto didattico.

---

### **4. La regola d’oro: NON usare le tabelle per costruire layout**

Regola **assoluta** e **gravata da cento anatemi del web design**:

> **Non usare mai le tabelle per costruire layout grafici.**

Perché?

1. **Sono nate per i dati, non per l’impaginazione.**  
    HTML moderno ha Flexbox, Grid, responsive design…  
    Le tabelle servono a tabelle.
    
2. **Gravissimo problema semantico.**  
    Un crawler o screen reader pensa di trovare dati tabulari, non un layout.
    
3. **Pratica vecchissima (anni ’90 – primi 2000).**  
    Prima del CSS avanzato, purtroppo si usavano le tabelle per impaginare.  
    Ora è da evitare al 100%.
    
4. **Inaccessibile e difficile da mantenere.**  
    Layout rigido, difficoltà enormi per mobile, poca flessibilità.
    

---

### **5. Le tabelle NON sono responsive**

È un concetto fondamentale.

Esempio: se una cella contiene un testo lunghissimo, come “Lorem ipsum lorem ipsum…” impostato in `<p>`, questo:

- **si adatta**, va a capo, si restringe con la pagina
    
- funziona bene su mobile
    

Ma dentro una tabella:

- la cella **non sa come adattarsi**
    
- la larghezza rimane rigida
    
- su mobile si “taglia” fuori
    
- la tabella **non si restringe**, scorre fuori viewport
    

Esempio dimostrativo (come nel video):

- prendi una riga lunghissima → la tabella esce dalla pagina
    
- prendi un semplice `<p>` → il testo si adatta perfettamente
    

Questo è uno dei motivi per cui il layout a tabelle è morto e sepolto.

---

### **6. Conclusione della lezione**

Hai visto:

- la struttura primitiva
    
- `<table>`, `<tr>`, `<td>`
    
- differenza tra dati tabulari e layout
    
- perché non devi usare tabelle per impaginare
    
- perché non sono responsive
    
- come aggiungere uno stile minimo solo per capire cosa stai guardando
    

Con questa base, nelle prossime lezioni inizieremo ad aggiungere:

- intestazioni (`<th>`)
    
- caption
    
- sezioni (`<thead>`, `<tbody>`, `<tfoot>`)
    
- spanning di righe e colonne
    
- tabelle accessibili e semantiche
    
- tabelle complesse e ben strutturate
    
