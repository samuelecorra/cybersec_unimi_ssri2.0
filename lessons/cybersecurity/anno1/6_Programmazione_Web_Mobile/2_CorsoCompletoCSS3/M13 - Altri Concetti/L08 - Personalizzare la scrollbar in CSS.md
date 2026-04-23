## **Lezione 8: Personalizzare la scrollbar in CSS**

### **1. Introduzione**

La scrollbar è un elemento che i visitatori vedono _sempre_, specialmente su pagine lunghe o layout scrollabili.  
CSS permette di personalizzarla — ma solo tramite prefissi specifici dei browser, perché **non esiste ancora uno standard universale**.

Nello specifico, la personalizzazione più efficace avviene tramite:

```
::-webkit-scrollbar
::-webkit-scrollbar-track
::-webkit-scrollbar-thumb
```

Queste funzionano su:

- Chrome
    
- Edge
    
- Safari
    
- Opera
    

Firefox ha un suo sistema alternativo, ma qui ci concentriamo sul blocco WebKit che, nella pratica, copre il 95% dell’utenza.

---

## **2. Struttura completa**

Per personalizzare la scrollbar ci bastano **tre selettori**:

1. **`::-webkit-scrollbar`** → il contenitore generale
    
2. **`::-webkit-scrollbar-track`** → la “rotaia”
    
3. **`::-webkit-scrollbar-thumb`** → il “pollice”, cioè la parte che trascini
    

Esempio base identico alla lezione:

```css
/* LARGHEZZA della scrollbar */
::-webkit-scrollbar {
  width: 10px;
}

/* ROTAIA */
::-webkit-scrollbar-track {
  background-color: blue;
  border-radius: 5px;
}

/* BLOCCO SCORREVOLE */
::-webkit-scrollbar-thumb {
  background-color: red;
  border-radius: 5px;
}
```

---

## **3. Come leggerlo visivamente**

### **a) `::-webkit-scrollbar`**

È tutto lo spazio occupato dalla barra laterale.  
Cambiare **width** o **height** cambia la dimensione della scrollbar.

### **b) `::-webkit-scrollbar-track`**

È la pista su cui scorre il thumb.  
Di solito ci si mette un colore neutro o uno sfondo semi-trasparente.

### **c) `::-webkit-scrollbar-thumb`**

È la parte cliccabile.  
Qui si lavora su:

- colore
    
- border-radius
    
- effetti 3D
    
- gradienti
    
- box-shadow
    

È l’elemento che dà carattere alla scrollbar.

---

## **4. Esempio completo identico alla lezione**

```css
body {
  height: 2000px;
}

/* Scrollbar */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  background-color: blue;
  border-radius: 5px;
}

/* Thumb */
::-webkit-scrollbar-thumb {
  background-color: #ff5555;
  border-radius: 5px;
}
```

---

## **5. Dove trovare ispirazione**

Come indicato nel video, l’unico modo davvero efficace per imparare design belli è:

### 👉 cercare **“codepen scrollbar”** su Google.

Vedrai:

- scrollbars 3D
    
- scrollbars con gradienti
    
- scrollbars con animazioni
    
- scrollbar minimaliste stile macOS
    
- scrollbar neon o dark mode
    

E ogni Pen contiene esattamente le _tre_ regole che hai imparato.

Imparare osservando codice già funzionante è 10× più veloce che provare da zero.

---

## **6. Considerazioni finali**

- È un argomento **marginale** ma può cambiare la percezione del tuo sito.
    
- Non tutti i progetti richiedono una scrollbar custom.
    
- Su mobile spesso non si vede, quindi l’effetto è percepito soprattutto su desktop.
    
- Aggiunta piccola, impatto estetico alto.
