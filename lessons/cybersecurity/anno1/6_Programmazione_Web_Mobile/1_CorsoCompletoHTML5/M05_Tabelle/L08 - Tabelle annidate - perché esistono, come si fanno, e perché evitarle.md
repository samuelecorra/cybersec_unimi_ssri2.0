## **Lezione 8: Tabelle annidate — perché esistono, come si fanno, e perché evitarle**

### **1. Perché parliamo delle tabelle annidate**

Questa è una **digressione tecnica**: serve solo a mostrarti che _HTML lo permette_, non che sia una buona idea usarle.

- Sono brutte da leggere
    
- Rendono il codice pesante e ingestibile
    
- Creano problemi di layout
    
- Complicano qualsiasi intervento di CSS
    
- Rompono l’accessibilità
    
- Sono praticamente inutilizzate nel web moderno
    

Io stesso, in anni di lavoro, **non ricordo un singolo caso reale** in cui valesse la pena usarle.

Però… è possibile farlo. E (per completezza) ti mostro come.

---

### **2. Come si crea una tabella annidata**

Partiamo dalla tabella "grande", e scegliamo una cella.  
Dentro a **quella cella (`<td>`)** possiamo semplicemente inserire un’altra `<table>`.

Esempio:

```html
<table border="1">
    <tr>
        <td>
            Luca
        </td>

        <td>
            <!-- Tabella annidata -->
            <table border="1">
                <thead>
                    <tr>
                        <th>Dato 1</th>
                        <th>Dato 2</th>
                        <th>Dato 3</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td>A</td>
                        <td>B</td>
                        <td>C</td>
                    </tr>
                </tbody>
            </table>
        </td>

        <td>30</td>
        <td>400</td>
    </tr>
</table>
```

Risultato:  
una tabella dentro una cella della tabella esterna.

Niente di magico: HTML legge tutto come "contenuto", e una tabella è semplicemente un altro contenuto.

---

### **3. Possiamo usare anche `<thead>`, `<tbody>`, `<tfoot>`**

Assolutamente sì.

Una tabella annidata è una tabella completa al 100%, quindi puoi scriverla:

```html
<table>
    <thead>...</thead>
    <tbody>...</tbody>
</table>
```

anche se sei dentro un `<td>`.

---

### **4. Perché è quasi sempre una pessima idea**

Qui è fondamentale essere chiari:  
nel 2025 praticamente nessuno usa tabelle annidate, e questi sono i motivi concreti:

#### **A) Layout ingestibile**

Il CSS moderno (Flexbox, Grid) ha completamente sostituito l’uso delle tabelle per impaginare.

Una tabella annidata:

- non è responsive
    
- non si adatta agli schermi piccoli
    
- si allarga a dismisura
    
- costringe a scrollbar interne orribili
    

#### **B) Codice difficile da mantenere**

Una struttura così:

```html
<table>
    <tr>
        <td>
            <table>
                <tr>
                    <td>
                        <table>...</table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
```

diventa immediatamente un **incubo per chiunque**.

#### **C) Accessibilità compromessa**

Gli screen reader si confondono facilmente:

- l’utente sente "Tabella… colonna 1 riga 1… tabella… colonna 1 riga 1…"
    
- orientarsi è complicatissimo
    

Le WCAG raccomandano di **evitare annidamenti non strettamente necessari**.

#### **D) Prestazioni peggiori**

Rendering più complesso, più ricalcoli di layout, più HTML da mantenere.

---

### **5. Quando può avere senso usarle?**

Raramente, quasi mai.

Gli unici casi borderline possono essere:

- documenti statici tipo fatture con sottotabelle (più per dovere di struttura che per estetica)
    
- tabelle generate da software legacy che non puoi modificare
    
- template di email HTML (dove le tabelle sono ancora usate per compatibilità con Outlook)
    

Fuori da questi contesti: _non usarle_.

---

### **6. Conclusione**

Hai visto:

- che le tabelle annidate **sono possibili**
    
- come si scrivono
    
- perché praticamente **non si devono usare**
    
- quali sono i pochi casi estremi in cui potresti incontrarle
    

È semplicemente una conoscenza **"da completezza"**, per evitare confusione se un giorno ti capita un codice ereditato da vent’anni fa.