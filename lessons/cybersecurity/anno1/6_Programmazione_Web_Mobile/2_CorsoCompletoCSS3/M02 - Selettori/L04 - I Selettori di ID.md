## **Lezione 4 – I Selettori di ID (`#id`)**

### **1. Introduzione al selettore di ID**

Nel video precedente abbiamo parlato dei selettori di elemento (`p`, `h1`, `strong`…), ma ti avevo già anticipato che quel tipo di selettore è troppo generico: prende **tutti** gli elementi di quel tag, senza distinzione.

Per essere più selettivi, CSS ci offre un nuovo strumento: **il selettore di ID**, che si scrive con il simbolo `#`.

Esempio:

```css
#prova {
    color: blue;
}
```

Il selettore di ID permette di prendere **un solo elemento**, quello che nell’HTML possiede l’attributo:

```html
<p id="prova">...</p>
```

Il concetto fondamentale è:

> L’ID identifica _un elemento unico_ nella pagina.  
> Non ci possono essere due elementi con lo stesso ID.

È la stessa regola vista in HTML, ma ora la usiamo **per selezionare** elementi nel CSS.

---

### **2. Perché gli ID sono univoci**

Il prof insiste su questo concetto perché è la base di tutto:

- Un ID è come il codice fiscale: **uno per persona**, non duplicabile.
    
- È un attributo pensato per identificare **uno e un solo elemento**.
    
- Se esistessero due elementi con lo stesso ID, sarebbe un errore logico.
    

Nel CSS questo significa che **non potrai mai selezionare un gruppo di elementi tramite ID**, ma solo un singolo elemento.

Questo ci servirà per capire il perché delle **classi**, che vedremo nel prossimo video.

---

### **3. Esempio pratico: selezionare per ID**

Supponiamo di avere:

```html
<p id="prova">Sono il paragrafo prova</p>
<p id="ciao">Sono il paragrafo ciao</p>
```

Possiamo selezionarli così:

```css
#prova {
    color: blue;
}

#ciao {
    color: green;
}
```

Il primo diventa blu, il secondo diventa verde.

Questa è la forma più semplice di selezione per ID.

---

### **4. Selettore di ID “puro” vs combinato**

Il selettore ID può essere scritto in due modi:

#### **4.1. Selettore diretto (puro)**

```
#prova {
    color: blue;
}
```

Questo prende _qualunque_ elemento che abbia `id="prova"`.

#### **4.2. Selettore combinato**

```
p#prova {
    color: blue;
}
```

Questo significa:

> “Seleziona l’elemento `<p>` con id `prova`.”

Quindi prende solo un paragrafo con quell’ID.  
Se esistesse un:

```html
<div id="prova">
```

questo div **non** verrebbe selezionato dal selettore `p#prova`, mentre verrebbe selezionato dal semplice `#prova`.

Il browser capisce la differenza perché vede il simbolo `#` e lo interpreta come “sto leggendo un ID”.

Nonostante ciò, nella pratica, siccome **l’ID è già univoco**, si usa quasi sempre il selettore semplice:

```
#prova { ... }
```

Il selettore combinato è utile solo in situazioni avanzate, che vedremo più avanti.

---

### **5. Sovrascrivere regole con gli ID**

Riprendiamo un caso già visto:

```css
p {
    color: red;
}

#prova {
    color: blue;
}

#ciao {
    color: green;
}
```

Anche se tutti i `p` sono rossi, quelli con ID diventano blu o verdi.  
Perché?

Perché **il selettore ID ha una specificità molto più alta (100 punti)** rispetto al selettore di elemento (1 punto).

Quindi:

- `#prova` **vince** contro `p`
    
- `#ciao` **vince** contro `p`
    

Ed è proprio questo comportamento che rende gli ID perfetti per selezioni puntuali.

---

### **6. Il limite degli ID: non possono stilare un gruppo di elementi**

E qui arriviamo al problema centrale, che il prof evidenzia:

> Se voglio rendere blu **due paragrafi**, non posso farlo con un ID, perché l’ID può essere usato solo una volta.

Esempio:

Se hai:

```html
<p id="prova">...</p>
<p id="prova">...</p>
```

questo sarebbe **sbagliato**.  
HTML non lo permette correttamente.  
CSS non sa più chi prendere.  
L’ID è stato inventato per essere unico.

Quindi **non possiamo stilare più elementi usando ID**.

E qui entra in gioco la soluzione corretta:  
le **classi**.

---

### **7. Perché arriviamo alle classi**

Come dice il prof:

- Se due paragrafi (o tre, o dieci) devono avere lo **stesso stile**,
    
- e non posso usare un ID perché è univoco,
    
- allora devo usare un selettore diverso…
    

…che permette di identificare **gruppi di elementi** senza limiti.

Quel selettore è la **classe**, che vedremo nel prossimo video.

---

### **8. Conclusione della lezione**

In questa lezione abbiamo visto:

- cosa sono i selettori di ID
    
- come si scrivono (`#nomeID`)
    
- perché l’ID è univoco
    
- come funziona la selezione pura e la selezione combinata
    
- perché gli ID sovrascrivono i selettori di elemento
    
- perché gli ID **non** servono per stilare più elementi
    
- e perché questo ci porta necessariamente alle **classi**
    

Nella prossima lezione introduciamo infatti i **selettori di classe**, il vero strumento per stilare gruppi di elementi in modo elegante, scalabile e corretto.