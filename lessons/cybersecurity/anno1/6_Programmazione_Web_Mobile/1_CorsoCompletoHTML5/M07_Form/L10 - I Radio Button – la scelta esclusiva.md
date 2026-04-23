## **Lezione 10: I Radio Button – la scelta esclusiva**

---

### **1. Introduzione: cos’è davvero un Radio Button**

A prima vista sembrano una versione “tonda” delle checkbox:

```html
<input type="radio">
```

Visivamente:

- la checkbox → quadrata
    
- il radio → tondo
    

Ma la **differenza concettuale** è enorme:

- **Checkbox** → più opzioni insieme (vero/falso, selezioni multiple)
    
- **Radio Button** → _una sola opzione possibile_ tra più alternative
    

Sono perfetti per scelte **mutuamente esclusive**:

- genere (maschio / femmina)
    
- fascia oraria
    
- metodo di pagamento
    
- modalità di spedizione
    
- livello “basic / pro / premium”
    

---

## **2. La struttura di base di un radio button**

Come per le checkbox:

- usiamo sempre una **label**
    
- colleghiamo `for="..."` con `id="..."`
    

Esempio:

```html
<label for="opt1">Opzione 1</label>
<input type="radio" id="opt1" name="scelta">
```

---

## **3. Perché serve un video dedicato?**

Perché i Radio Button hanno una regola essenziale e **contro-intuitiva** per chi arriva dalle checkbox.

### **Regola d’oro**

> **Tutti i radio button appartenenti allo stesso gruppo devono avere lo stesso `name`.**

Se non lo fai, non sarà possibile scegliere tra “una o l’altra”.  
Diventano semplicemente checkbox tonde.

---

## **4. Vediamo il problema reale**

Esempio _sbagliato_:

```html
<input type="radio" id="maschio" name="maschio">
<input type="radio" id="femmina" name="femmina">
```

Risultato:

- puoi selezionarli **entrambi**
    
- non puoi deselezionare manualmente
    
- la logica “una sola scelta possibile” si rompe
    

Per il browser sono **due campi diversi**, uno “maschio” e uno “femmina”.

---

## **5. La soluzione corretta: condividere il `name`**

Esempio corretto:

```html
<label for="maschio">Maschio</label>
<input type="radio" id="maschio" name="genere" value="maschio">

<label for="femmina">Femmina</label>
<input type="radio" id="femmina" name="genere" value="femmina">
```

Ora:

- puoi selezionare solo uno dei due
    
- non puoi deselezionare completamente (comportamento normale dei radio)
    
- quando il form viene inviato, il server riceverà:
    

```
genere=maschio
```

oppure

```
genere=femmina
```

Mai entrambe.

---

## **6. Il pattern ideale in un form reale**

Una struttura leggibile e semantica:

```html
<section>
  <h3>Genere</h3>

  <div>
    <label for="maschio">Maschio</label>
    <input type="radio" id="maschio" name="genere" value="maschio">
  </div>

  <div>
    <label for="femmina">Femmina</label>
    <input type="radio" id="femmina" name="genere" value="femmina">
  </div>
</section>
```

Motivi per cui funziona bene:

- la **section** raggruppa logicamente l’intero blocco
    
- ciascuna scelta ha un suo **div** ordinato
    
- il name condiviso garantisce la logica “o A o B”
    
- le label rendono cliccabile l’intera area
    

---

## **7. Comportamenti particolari delle radio**

### **7.1. Non si possono deselezionare**

Una volta selezionato un radio, puoi solo selezionare **un’altra opzione**, ma non “togliere tutto”.

Questo è voluto:

- il senso del radio è _“scegline una”_
    
- se vuoi una selezione opzionale (“sì/no/nessuna”), devi aggiungere un terzo radio come “Nessuna”
    

### **7.2. Serve sempre il `value`**

Il radio **richiede** un valore esplicito (a differenza della checkbox che può usare il default “on”).

Esempio:

```html
value="maschio"
value="femmina"
value="premium"
value="gratis"
```

---

## **8. Radio vs Checkbox – la sintesi finale**

|Comportamento|Checkbox|Radio|
|---|---|---|
|Selezioni multiple|✔️ sì|❌ no|
|Una sola scelta|❌ no|✔️ sì|
|Deselezionabile|✔️ sì|❌ no|
|Name per raggruppamento|irrilevante|**obbligatorio**|
|Valore|opzionale|consigliato/necessario|

---

## **9. Riepilogo**

- I radio si definiscono come le checkbox (`<input type="radio">`), ma il comportamento è totalmente diverso.
    
- Devono condividere lo **stesso name**.
    
- Permettono una sola scelta possibile.
    
- Non si possono deselezionare, solo cambiare selezione.
    
- Ogni radio dovrebbe sempre avere una **label** e un **value**.
    

---

## **10. Prossima lezione**

Ora che sappiamo gestire:

- selezioni multiple (checkbox)
    
- selezione esclusiva (radio)
    

passiamo a un input completamente diverso e molto importante:

## **→ il campo `file`, per permettere agli utenti di caricare documenti, foto, PDF, immagini e qualsiasi altra risorsa.**