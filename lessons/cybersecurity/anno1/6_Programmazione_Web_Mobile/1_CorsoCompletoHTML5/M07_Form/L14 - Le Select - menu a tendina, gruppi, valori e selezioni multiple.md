## **Lezione 14: Le Select — menu a tendina, gruppi, valori e selezioni multiple**

---

### **1. Introduzione**

Le **select** sono i classici **menu a tendina** usati per scegliere uno o più valori da un elenco predefinito: hobby, nazioni, categorie, livelli, preferenze.  
Sono uno degli elementi più importanti nei form, perché permettono di ridurre gli errori e guidare l’utente in una scelta chiusa.

---

## **2. Struttura base**

```html
<select id="hobby" name="hobby">
  <option>Calcio</option>
  <option>Basket</option>
  <option>Golf</option>
</select>
```

Risultato: un menu a tendina con tre opzioni cliccabili.

---

## **3. Valori: `value=""`**

Come per gli altri input, ogni `option` può avere un **valore interno**, diverso dal testo mostrato:

```html
<option value="calcio">Calcio</option>
<option value="basket">Basket</option>
<option value="golf">Golf</option>
```

- ciò che **vede l’utente** → “Calcio”
    
- ciò che **il codice riceve** → `"calcio"`
    

È buona norma _inserire sempre un value_, anche se identico all’etichetta.

---

## **4. Selezione di default: `selected`**

Puoi pre-selezionare un’opzione al caricamento della pagina:

```html
<option value="basket" selected>Basket</option>
```

Solo un’opzione può essere `selected` nelle select normali (singola scelta).

---

## **5. Raggruppare le opzioni con `optgroup`**

Per creare categorie dentro il menu:

```html
<select id="hobby" name="hobby">
  <optgroup label="Sport">
    <option value="calcio">Calcio</option>
    <option value="basket">Basket</option>
    <option value="golf">Golf</option>
  </optgroup>

  <optgroup label="Arte">
    <option value="pittura">Pittura</option>
    <option value="scrittura">Scrittura</option>
    <option value="disegno">Disegno</option>
  </optgroup>
</select>
```

Gli `optgroup`:

- NON sono selezionabili
    
- servono solo a dividere visivamente le categorie
    

---

## **6. Selezioni multiple: `multiple`**

Se vuoi permettere all’utente di scegliere più hobby:

```html
<select id="hobby" name="hobby" multiple>
```

Comportamento:

- **CTRL + click** → seleziona più elementi non consecutivi
    
- **SHIFT + click** → seleziona un intervallo
    

Questo trasforma la select in una lista selezionabile.

---

## **7. Altezza visibile: `size=""`**

Quando `multiple` è attivo, la select smette di essere un menu a tendina e diventa una lista.

Puoi controllarne l’altezza:

```html
<select id="hobby" name="hobby" multiple size="5">
```

Mostra **5 righe** visibili alla volta senza aprire il dropdown.

Esempi:

- `size="2"` → due elementi visibili
    
- `size="10"` → select molto alta
    
- `size="1"` → si comporta di nuovo come dropdown standard
    

---

## **8. Riepilogo completo della Select**

|Funzione|Attributo / Elemento|Effetto|
|---|---|---|
|Identificatore|`id=""`|collega label e codice JS|
|Nome per invio|`name=""`|il server riceve questo nome|
|Valore interno|`value=""`|dato inviato al server|
|Opzione predefinita|`selected`|seleziona al load|
|Selezioni multiple|`multiple`|abilita più scelte|
|Altezza lista|`size=""`|definisce quante opzioni visibili|
|Gruppi di opzioni|`<optgroup>`|divide il menu in categorie|

---

## **9. Conclusione**

La select è uno strumento potente e flessibile, ideale quando:

- esiste un elenco chiuso di valori
    
- vuoi evitare errori di battitura
    
- vuoi semplificare drasticamente l’input dell’utente
    
- servono una o più scelte ordinate in categorie
    

Nella prossima lezione vediamo le **datalist**, che uniscono input libero + suggerimenti.