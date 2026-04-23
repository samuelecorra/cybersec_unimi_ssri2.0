## **Lezione 9: Le checkbox – funzionamento reale, valori e comportamento**

---

### **1. Introduzione: cos’è davvero una checkbox**

Le _checkbox_ sono una delle componenti più riconoscibili dei form:

- piccola casella quadrata
    
- può essere selezionata o deselezionata
    
- può avere **più selezioni contemporanee**
    

Sono perfette per:

- accettare termini e condizioni
    
- scegliere più opzioni indipendenti
    
- attivare/disattivare funzionalità
    
- confermare impostazioni
    

Come sempre, la **label** è fondamentale per garantire usabilità e accessibilità.

---

## **2. La struttura di base di una checkbox**

Ecco il pattern base:

```html
<label for="accetto">Accetto i termini</label>
<input type="checkbox" id="accetto" name="accetto">
```

Risultato: cliccando sulla label, la casella si spunta.  
Questo migliora enormemente l’esperienza utente.

### **2.1. Perché la label è cruciale**

- La casella è piccola → difficile da cliccare (soprattutto su mobile).
    
- Collegare la label all’input (`for="..."`) permette di cliccare l’intera frase.
    
- UX infinitamente migliore.
    

Se manca la label:

- devi “beccare” il quadratino esatto
    
- è molto fastidioso soprattutto in testi lunghi
    

Mai fare così:

```html
<input type="checkbox"> Accetto...
```

Sempre fare così:

```html
<label for="accetto">Accetto...</label>
<input type="checkbox" id="accetto" name="accetto">
```

---

## **3. Il valore della checkbox: `value=""`**

Una checkbox può essere:

- **selezionata**
    
- **non selezionata**
    

Visivamente è semplice, ma _dietro le quinte_ passa un valore al server.

Se non specifichi il valore, HTML userà un default (`on`).  
Ma è sempre meglio specificarlo.

Esempi:

```html
<input type="checkbox" id="notifiche" name="notifiche" value="attivate">
```

Significa:

- se è spuntata → il server riceve `notifiche=attivate`
    
- se NON è spuntata → il server **non riceve nulla**
    

Oppure:

```html
value="si"
value="vero"
value="online"
value="accettato"
```

Il valore può essere **qualsiasi stringa**, non importa quale.

### **3.1. Cosa viene passato quando è deselezionata?**

Risposta: **niente**.

La checkbox non invia nulla se è vuota.  
Il server non trova il campo → deduce che è “false”.

---

## **4. Rendere la checkbox già attiva: `checked`**

L’attributo `checked` serve per far partire la casella già selezionata:

```html
<input type="checkbox" id="newsletter" name="newsletter" value="si" checked>
```

Al caricamento della pagina:

- la casella è già spuntata
    
- l’utente può sempre deselezionarla manualmente
    

### **4.1. Differenza fra stato iniziale e stato finale**

- `checked` nel tag HTML indica lo **stato iniziale**
    
- selezione/deselezione dell’utente è lo **stato finale**
    
- nei form conta sempre **lo stato finale**
    

Es.: se la casella parte “checked” ma l’utente la disattiva → _non viene inviata_.

---

## **5. Checkbox = selezioni multiple**

Una serie di checkbox permette **più selezioni insieme**.

Esempio:

```html
<label><input type="checkbox" name="hobby" value="calcio"> Calcio</label>
<label><input type="checkbox" name="hobby" value="musica"> Musica</label>
<label><input type="checkbox" name="hobby" value="lettura"> Lettura</label>
```

Se il form viene inviato con “musica” e “lettura” spuntati, il server riceverà:

```
hobby=musica
hobby=lettura
```

Le checkbox non hanno limiti:  
l’utente può selezionare **una, nessuna o molte**.

---

## **6. Casi d’uso esperti (già utili nei siti reali)**

### **6.1. Accetto termini e condizioni**

```html
<input type="checkbox" id="accetto" name="accetto" required>
<label for="accetto">Accetto i termini</label>
```

`required` impedisce l’invio del form se non viene spuntata.

---

### **6.2. Campi logici (“on/off”)**

```html
<input type="checkbox" id="online" name="stato" value="online">
```

- spuntata → `stato=online`
    
- non spuntata → niente viene inviato (equivale a “offline”)
    

---

### **6.3. Selezione multipla in filtri / e-commerce**

Molto comune nei filtri di un catalogo:

```html
<label><input type="checkbox" name="taglia" value="S"> S</label>
<label><input type="checkbox" name="taglia" value="M"> M</label>
<label><input type="checkbox" name="taglia" value="L"> L</label>
```

---

## **7. Riepilogo visivo**

|Proprietà|Significato|
|---|---|
|`type="checkbox"`|crea una casella on/off|
|`checked`|selezionata all’avvio|
|`value="..."`|valore inviato se spuntata|
|not checked|nessun valore inviato|
|label collegata|permette clic ovunque sulla frase|

---

## **8. Conclusione**

Le checkbox sono semplici da vedere, ma richiedono qualche concetto preciso:

- hanno un valore associato
    
- inviano dati solo se spuntate
    
- usano **label** per un’esperienza utente totale
    
- permettono selezioni multiple
    
- possono essere pre-attivate con `checked`
    

Nel prossimo video analizziamo un tipo di input molto diverso:

## **→ Le radio button (`type="radio"`), che a differenza delle checkbox permettono UNA sola scelta.**