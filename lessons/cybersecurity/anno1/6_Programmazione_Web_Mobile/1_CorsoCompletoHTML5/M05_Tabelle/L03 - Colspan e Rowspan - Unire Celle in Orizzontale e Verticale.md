## **Lezione 3: Colspan e Rowspan — Unire Celle in Orizzontale e Verticale**

### **1. Perché impariamo `colspan` e `rowspan`**

In Excel puoi selezionare più celle e fare **“Unisci celle”**.  
In HTML puoi ottenere esattamente lo stesso risultato grazie a **colspan** (unione orizzontale) e **rowspan** (unione verticale).

Questi due attributi ti permettono di:

- creare **celle che occupano più colonne**
    
- creare **celle che occupano più righe**
    
- strutturare **totali, categorie, sezioni, intestazioni complesse**
    
- realizzare tabelle molto più ricche e professionali
    

Sono un blocco fondamentale della costruzione tabellare.

---

### **2. Aggiungiamo un caso realistico: il totale**

Partiamo dalla tabella con header che abbiamo già:

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

Supponiamo di voler aggiungere un **totale** sotto la tabella, che occupi **tre colonne**, mentre la quarta colonna mostra il valore:

```
Totale: |           |           | 3004 €
```

Senza unione di celle, saresti costretto a scrivere:

```html
<tr>
    <td>Totale</td>
    <td></td>
    <td></td>
    <td>3004 €</td>
</tr>
```

Bruttissimo e anti-semantico.

---

### **3. Usare `colspan`: unire celle in orizzontale**

L’obiettivo: fare sì che “Totale” occupi **tre colonne**.

Usiamo:

```html
<td colspan="3">Totale</td>
```

E la riga diventa:

```html
<tr>
    <td colspan="3">Totale</td>
    <td>3004 €</td>
</tr>
```

Risultato:

- Le tre celle della riga precedente vengono **inglobate** dentro un’unica cella larga
    
- La colonna del valore rimane nella sua posizione corretta
    
- Il codice rimane pulito e semantico
    
- La tabella prende forma professionale
    

---

### **4. Visualizzazione mentale del `colspan`**

Immagina la tua mano con **pollice e mignolo aperti**: una “spanna”.

> `colspan="3"` = _questa cella si prende una spanna larga tre colonne_

È esattamente quello che accade.

---

### **5. Usare `rowspan`: unire celle in verticale**

Ora passiamo all’opposto: unire celle **su più righe**.

Esempio: abbiamo un solo nome “Marco”, ma quattro cognomi diversi:

```html
<tr>
    <td>Marco</td>
    <td>Rossi</td>
</tr>
<tr>
    <td>Marco</td>
    <td>Neri</td>
</tr>
<tr>
    <td>Marco</td>
    <td>Bianchi</td>
</tr>
<tr>
    <td>Marco</td>
    <td>Gialli</td>
</tr>
```

È innaturale ripetere “Marco” quattro volte.  
Meglio unirle verticalmente.

### **6. Applichiamo `rowspan`**

`rowspan="4"` → la cella si estende su quattro righe.

```html
<tr>
    <td rowspan="4">Marco</td>
    <td>Rossi</td>
</tr>
<tr>
    <td>Neri</td>
</tr>
<tr>
    <td>Bianchi</td>
</tr>
<tr>
    <td>Gialli</td>
</tr>
```

Spiegazione:

- la cella `<td rowspan="4">Marco</td>` occupa **lo spazio di 4 celle verticali**
    
- quindi le altre tre celle “Marco” devono essere **eliminate**
    
- le righe si riallineano automaticamente
    

Risultato perfetto e pulito.

---

### **7. Perché `rowspan` è più delicato**

Con `colspan` agisci **nella stessa riga**.

Con `rowspan` agisci **su più righe diverse**:

- devi cancellare le celle duplicate
    
- devi assicurarti che il numero di colonne rimanga coerente
    
- devi mantenere la struttura della tabella allineata
    

Una volta capito, diventa naturale.

---

### **8. Ora puoi creare tabelle veramente complesse**

Con:

- `<table>`
    
- `<tr>`
    
- `<th>`
    
- `<td>`
    
- `colspan`
    
- `rowspan`
    

puoi già costruire:

- tabelle di fatture
    
- tabelle di rendicontazione
    
- orari scolastici
    
- fogli presenze
    
- planning settimanali
    
- tabelle statistiche
    
- report professionali
    

Lo stile arriverà dopo, ma la **struttura** ora ce l’hai.

Nel prossimo video aggiungiamo un altro pezzo fondamentale della semantica delle tabelle:

> **`<col>` e `<colgroup>`**, che ci permettono di gestire le colonne come elementi veri e propri.