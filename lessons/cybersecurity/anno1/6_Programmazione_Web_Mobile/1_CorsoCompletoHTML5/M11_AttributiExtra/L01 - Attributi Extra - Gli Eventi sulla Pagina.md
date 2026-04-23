## **Lezione 1: Attributi Extra — Gli Eventi sulla Pagina**

### **1. Introduzione: “Gli eventi? Ma da dove sbucano?”**

Arriviamo finalmente a una delle rivelazioni più importanti dell’intero corso.

Fino ad oggi abbiamo scritto HTML in modo molto pulito, molto statico:  
testi, immagini, bottoni, input… tutto molto tranquillo.

E adesso improvvisamente ti dico:

> “Guarda che tutto quello che l’utente fa sulla pagina… è un evento.”

E tu giustamente rimani tipo:

> «Ma _come_? Non ne abbiamo mai parlato!»

È normale.  
Gli eventi esistono da sempre, ma **HTML non li gestisce**.  
Può solo “raccoglierli”.  
Per **gestirli** serve JavaScript.

Questa lezione serve per prepararti mentalmente a ciò che vedremo nel corso di JavaScript, dove finalmente prenderai in mano _tutti_ questi eventi in modo completo.

---

## **2. Che cos’è tecnicamente un evento**

Un evento è **qualsiasi azione compiuta dall’utente sulla pagina**.

Esempi immediati:

- clicco su un bottone → **click**
    
- passo il mouse sopra un elemento → **mouseover**
    
- entro in un input → **focus**
    
- inizio a scrivere → **keydown**, **keypress**, **keyup**
    
- esco da un campo → **blur**
    
- cambio il valore di un input → **change**
    
- trascino un elemento → **dragstart**, **drag**, **drop**
    
- premo il tasto Tab e raggiungo un elemento → **focus**
    

Tutte cose che hai _già visto_ nella pratica, ma che fino a oggi non avevamo nominato.

---

## **3. Gli eventi non vengono gestiti da HTML**

Questo è il punto fondamentale:

### ➤ HTML vede l’evento,

### ➤ ma non può reagire.

### ➤ Per reagire serve JavaScript.

L’unica cosa che HTML può fare è fornire **attributi** con cui “dichiarare” che un evento deve essere intercettato.

Esempio semplice:

```html
<button onclick="miaFunzione()">Cliccami</button>
```

Questo è un attributo HTML (`onclick`) che dice:

> “Quando l’utente clicca, esegui questa funzione.”

Ma la funzione deve essere scritta in JavaScript.  
HTML da solo **non può** fare niente.

---

## **4. Due modi per ascoltare gli eventi**

È importante capirlo già ora, anche se non lo usiamo ancora.

### **Metodo 1 — Attributi HTML (quello che puoi già vedere)**

Esempio:

```html
<button onclick="saluta()">Cliccami</button>
```

Si chiama “gestione inline degli eventi”.  
È semplice, ma in progetti reali non si usa più.

### **Metodo 2 — JavaScript moderno (quello che useremo davvero)**

```javascript
const btn = document.getElementById("mio-bottone");
btn.addEventListener("click", saluta);
```

Questo è il modo professionale.

Non devi impararlo ora, ma è importante che tu sappia che **questo è il futuro**.

---

## **5. Tutti gli attributi `on...` che potrai incontrare**

Aprendo VS Code, a volte noterai che quando scrivi `on` ti appare un mondo:

```
onclick
ondblclick
onchange
onfocus
onblur
onmouseenter
onmouseleave
onmouseover
onmouseup
onmousedown
onkeydown
onkeyup
onkeypress
ondrag
ondrop
oninput
onload
onresize
onsubmit
...
```

Quando ancora non conosci gli eventi, vedere `onmouseenter` o `onkeyup` può sembrare fuffa incomprensibile.  
Ora finalmente capisci:

### → tutti questi sono eventi.

### → tutti rappresentano azioni dell’utente.

### → tutti si potranno gestire in JavaScript.

---

## **6. Esempi concreti che già riconosci**

### **6.1. Click**

```html
<button onclick="saluta()">Cliccami</button>
```

### **6.2. Mouse sopra / mouse fuori**

```html
<div onmouseenter="entra()" onmouseleave="esci()">Box</div>
```

### **6.3. Focus su un input**

```html
<input onfocus="stoEntrando()" />
```

### **6.4. Cambiare valore (change)**

```html
<select onchange="aggiornaLingua()">
  <option>Italiano</option>
  <option>Inglese</option>
</select>
```

### **6.5. Tastiera (keydown, keyup, keypress)**

- `keydown`: il tasto viene premuto
    
- `keyup`: il tasto viene rilasciato
    
- `keypress`: premuto + rilasciato (ormai poco usato)
    

```html
<input onkeydown="premuto()" />
```

---

## **7. Una cosa che vedremo presto: il drag**

Hai visto l’attributo `ondrag` e ti è venuta la curiosità.

Gli eventi di drag & drop sono tra i più affascinanti, e li vedremo nel video successivo:

- `ondragstart`
    
- `ondrag`
    
- `ondragover`
    
- `ondrop`
    

Servono per:

- trascinare file
    
- spostare elementi
    
- fare interfacce dinamiche
    
- costruire tool visivi
    

Nel prossimo video facciamo una mini demo per capire cosa succede.

---

## **8. Perché parliamo di eventi adesso?**

Semplicemente:

- perché quando apri la tendina degli attributi e vedi `on...` devi sapere cosa sono
    
- perché fanno parte degli “attributi extra” di HTML
    
- perché tra poco entreremo nel mondo di CSS e JS e qui torneranno potentissimi
    
- perché fanno parte della mentalità del developer moderno
    

E soprattutto:

> per la prima volta stiamo guardando HTML come un “sistema interattivo”, non solo come struttura.

È un cambio di mindset importante.

---

## **9. Prossimo step**

Nel prossimo video passiamo a vedere **gli eventi di drag**, così cominciamo a farci un’idea visiva di come funziona lo spostamento degli elementi sulla pagina.