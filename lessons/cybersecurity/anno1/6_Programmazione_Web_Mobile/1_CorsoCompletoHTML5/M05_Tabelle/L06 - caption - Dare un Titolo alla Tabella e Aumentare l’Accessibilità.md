## **Lezione 6: `<caption>` — Dare un Titolo alla Tabella e Aumentare l’Accessibilità**

### **1. Perché il `<caption>` è fondamentale**

Finora abbiamo costruito tabelle che “visivamente” si capiscono da sole:

- Nome
    
- Cognome
    
- Età
    
- Quota
    

Per un utente normovedente è immediato capire il contesto.  
Ma per un utente **non vedente**, che utilizza uno **screen reader**, la situazione è completamente diversa.

Uno screen reader:

- non vede la tabella
    
- non deduce il contesto
    
- inizia semplicemente a leggere **tutte le celle una dopo l’altra**
    

Risultato senza caption:

> Nome, colonna. Cognome, colonna. Età, colonna. Quota, colonna.  
> Luca, Rossi, 30, 400.  
> Marco, Verdi, 25, 500.  
> … (e continua)

L’utente cieco **non sa** _di che tabella si tratti_, né se valga la pena ascoltarla.

Ed è qui che entra in gioco `<caption>`.

---

### **2. Come si usa `<caption>`**

Il `<caption>` va messo **subito all’interno della `<table>`**, prima di tutto il resto.

Esempio:

```html
<table>
    <caption>Quote pagate per ingresso in società</caption>

    <tr>
        <th>Nome</th>
        <th>Cognome</th>
        <th>Età</th>
        <th>Quota</th>
    </tr>

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
</table>
```

---

### **3. Cosa cambia per gli screen reader**

Con il caption, lo screen reader annuncia subito:

> “Tabella: Quote pagate per ingresso in società”

L’utente cieco ora:

- sa **di cosa parla** la tabella
    
- può decidere **se ascoltarla o saltarla**
    
- ha un orientamento chiaro rispetto ai dati che seguiranno
    

Con un singolo elemento HTML, abbiamo reso la tabella **dieci volte più accessibile**.

---

### **4. Il valore per gli utenti normovedenti**

Il `<caption>` è utilissimo anche per chi vede:

- funziona come **titolo** della tabella
    
- facilita la lettura in documenti lunghi
    
- rende il contenuto più ordinato e professionale
    
- sostituisce (oppure integra) un eventuale titolo fuori dalla tabella
    

Nella maggior parte dei siti moderni, un caption ben stilizzato diventa una parte elegante e chiara della UI.

---

### **5. Quanto è pesante da implementare?**

Zero fatica:

- una riga di codice
    
- nessuna configurazione
    
- nessuna semantica difficile
    
- massima resa in accessibilità
    

Un rapporto **costo/beneficio perfetto**.

---

### **6. Ricapitolazione**

Hai imparato che:

- `<caption>` è un titolo interno alla tabella
    
- è fondamentale per chi usa screen reader
    
- migliora la chiarezza complessiva
    
- si inserisce subito dentro `<table>`
    
- è uno degli elementi più sottovalutati dell’HTML
    
- aumenta l’accessibilità in modo significativo con sforzo minimo
    

Ora che la tabella ha un titolo, nel prossimo video introduciamo le **sezioni della tabella** — `<thead>`, `<tbody>` e `<tfoot>` — che rendono la struttura ancora più chiara, corretta e semantica.