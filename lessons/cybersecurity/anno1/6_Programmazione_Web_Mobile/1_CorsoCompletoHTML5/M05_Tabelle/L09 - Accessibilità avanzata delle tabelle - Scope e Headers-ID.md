## **Lezione 9: Accessibilità avanzata delle tabelle — Scope e Headers/ID (la spiegazione definitiva)**

Ultimo video teorico del modulo, e anche uno dei più importanti per chi vuole fare HTML in modo **professionale**, “da sviluppatore vero”: rendere le tabelle completamente _accessibili_.

La difficoltà non è capire cosa _vede_ un utente normodotato, ma cosa _capisce_ uno screen reader davanti a una tabella complessa.

Finora abbiamo visto:

- `<caption>` per descrivere la tabella
    
- `<thead>`, `<tbody>`, `<tfoot>` per strutturarla
    
- `<th>` e `<td>` per distinguere header e dati
    

Questo però non basta.

Ora entriamo nella parte **semantica**: come far capire a un lettore automatico _quali dati dipendono da quali header_.

---

# **1. Metodo 1 — L’attributo `scope`**

È il metodo “ufficiale” per tabelle complesse generate in modo **tabellare puro**.

Serve a dire:

- “questa header è header _di colonna_”
    
- “questa header è header _di riga_”
    
- “questa header rappresenta un _gruppo di colonne_”
    
- “questa header rappresenta un _gruppo di righe_”
    

Gli screen reader, senza `scope`, capiscono solo che una cella è una `<th>`, ma **non sanno a quali celle è collegata**.

---

## **1.1 Scope di base**

### `scope="col"`

La header appartiene a **una colonna**.

```html
<th scope="col">Jeans</th>
```

### `scope="row"`

La header appartiene a **una riga**.

```html
<th scope="row">Milano</th>
```

In tabelle semplici (tipo un foglio Excel piccolo) basterebbe questo.

---

## **1.2 Scope complessi**

Quando usi `rowspan` o `colspan`, una header può rappresentare **più righe** o **più colonne**.

E allora servono:

### `scope="colgroup"`

Header che rappresenta **un gruppo di colonne** (tipico: "Vestiti", che contiene “jeans”, “magliette”, “felpe”…)

```html
<th scope="colgroup" colspan="3">Vestiti</th>
```

### `scope="rowgroup"`

Header che rappresenta **un gruppo di righe** (tipico: “Italia”, che unisce Milano, Roma, Napoli…)

```html
<th scope="rowgroup" rowspan="3">Italia</th>
```

---

## **1.3 Perché serve?**

Perché uno screen reader legge così:

> “Tabella: Prodotti venduti per negozio.  
> Header colonna: Jeans.  
> Header riga: Milano.  
> Valore: 74.”

Senza `scope` direbbe:

> “TH. TD. TD. 74. …”

Ed è **impossibile orientarsi**.

---

# **2. Metodo 2 — Coppia `id` + `headers`**

Questo è ancora più potente e spesso più intuitivo, soprattutto quando:

- la tabella è estremamente complessa,
    
- vuoi collegare esplicitamente ogni singola cella alle sue header.
    

È anche il metodo raccomandato dalle WCAG per tabelle **molto intricate**.

---

## **2.1 Struttura generale**

### 1. Ogni `<th>` riceve un `id`.

```html
<th id="oggetto">Oggetto</th>
<th id="luogo">Luogo</th>
<th id="data">Data</th>
<th id="costo">Costo</th>
```

### 2. Ogni `<td>` indica _di quali header dipende_ tramite `headers=""`.

```html
<td headers="oggetto scarpe">Scarpe</td>
<td headers="luogo scarpe">Foot Locker</td>
<td headers="data scarpe">01/02/2024</td>
<td headers="costo scarpe">59€</td>
```

Notare:

- **Ordine:** prima header di colonna, poi header di riga
    
- **Spazi:** separano più header
    
- Tutto è leggibile da un lettore come:
    

> “Luogo — Scarpe — Foot Locker”

---

## **2.2 Perché questo metodo è più intuitivo?**

Perché replica esattamente una matrice.

Ecco la metafora perfetta: **la scacchiera**.

- Colonne: A B C D E F G H
    
- Righe: 1 2 3 4 5 6 7 8
    

La cella C5 è semplicemente:

```
headers="C 5"
```

Il concetto è identico.

E infatti l’esempio finale mostrava proprio questo: collegare ogni cella al suo “nome” (A1, B1, C1…), come in scacchi o battaglia navale.

---

# **3. Quando usare `scope` e quando usare `headers`**

### ✓ Usa `scope` quando:

- la tabella è moderatamente complessa
    
- ci sono raggruppamenti logici (colgroup, rowgroup)
    
- vuoi mantenere un codice più compatto
    

### ✓ Usa `headers/id` quando:

- la tabella è estremamente complessa o dinamica
    
- le relazioni tra celle e header non sono ovvie
    
- generi le tabelle da un backend e vuoi automatizzare l’accesso
    

Entrambi sono riconosciuti dagli screen reader.

---

# **4. Cosa cambia per l’utente cieco**

Con `scope` / `headers`:

> “Tabella Vendite.  
> Colonna: Jeans.  
> Riga: Milano.  
> Valore: settantaquattro.”

Senza accessibilità:

> “Valore: settantaquattro.”

Chi non vede non ha idea:

- né di cosa rappresenta il dato
    
- né di a quale riga appartenga
    
- né di quale colonna stia leggendo
    
- né del contesto
    

---

# **5. Conclusione**

In questa lezione hai imparato:

- come ragionano gli screen reader
    
- come rendere una tabella _realmente_ accessibile
    
- come usare `scope="col"`, `scope="row"`, `scope="colgroup"`, `scope="rowgroup"`
    
- come usare il metodo alternativo `id` + `headers`
    
- come pensare la tabella come una scacchiera / matrice logica
    

Ora possiamo passare all’esercizio finale, dove userai:

- `<caption>`
    
- `<thead>`
    
- `<tbody>`
    
- `<tfoot>`
    
- `<th>` di riga e colonna
    
- `colspan` e `rowspan`
    
- `scope` e `headers/id`
    

Vuoi che ti prepari subito l’esercizio completo?