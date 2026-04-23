## **Lezione 9: Esercizio 5 – Creare un menù completo e replicarlo su tutte le pagine**

### **1. Obiettivo dell’esercizio**

In questo esercizio devi:

1. **costruire da zero un menù di navigazione funzionante**
    
2. inserirlo in **tutte le pagine** degli esercizi
    
3. assicurarti che ogni link punti correttamente alla pagina giusta
    
4. permettere una navigazione fluida tra Home ed esercizi
    

Il tutto **senza creare nuovi file**, ma lavorando nelle pagine già esistenti.

---

### **2. Struttura del menù**

Il menù è semplicemente:

- un contenitore semantico `<nav>`
    
- una lista non ordinata `<ul>`
    
- una serie di voci `<li>`
    
- ognuna delle quali contiene un link `<a href="...">`
    

La base è questa:

```html
<nav>
  <ul>
    <li><a href="/">Home</a></li>
    <li><a href="esercizi/esercizio_1.html">Esercizio 1</a></li>
    <li><a href="esercizi/esercizio_2.html">Esercizio 2</a></li>
    <li><a href="esercizi/esercizio_3.html">Esercizio 3</a></li>
    <li><a href="esercizi/esercizio_4.html">Esercizio 4</a></li>
    <li><a href="esercizi/esercizio_5.html">Esercizio 5</a></li>
  </ul>
</nav>
```

Da notare:

- La voce **Home** punta a `/` la _root del progetto_  
    (funziona esattamente come `index.html`)
    
- Tutti gli esercizi puntano ai rispettivi file nella cartella `esercizi/`
    

---

### **3. Dove posizionarlo**

Il menù va sempre **in alto nella pagina**, idealmente **subito prima del grande titolo**.

Esempio per `esercizio_3.html`:

```html
<nav>
  <ul>
    <li><a href="/">Home</a></li>
    <li><a href="esercizio_1.html">Esercizio 1</a></li>
    <li><a href="esercizio_2.html">Esercizio 2</a></li>
    <li><a href="esercizio_3.html">Esercizio 3</a></li>
    <li><a href="esercizio_4.html">Esercizio 4</a></li>
    <li><a href="esercizio_5.html">Esercizio 5</a></li>
  </ul>
</nav>

<h1>Grande titolone</h1>
```

Da qui in poi viene il contenuto della pagina.

---

### **4. Adattamento automatico dei percorsi**

Quando sei dentro `esercizi/esercizio_3.html`, la voce "Home" può puntare a:

```
/
```

oppure, se preferisci, a:

```
../index.html
```

Entrambe sono corrette.  
La versione `/` è più pulita e funziona sempre.

---

### **5. Replicare il menù in tutte le pagine**

Ora copia l’intero blocco `<nav>...</nav>`:

- dentro `esercizio_1.html`
    
- `esercizio_2.html`
    
- `esercizio_3.html`
    
- `esercizio_4.html`
    
- `esercizio_5.html`
    
- anche in `index.html`, se vuoi avere la navigazione costante
    

Così ogni pagina avrà:

- home
    
- tutti gli esercizi
    
- la possibilità di muoverti liberamente fra le sezioni del mini-sito
    

---

### **6. Verifica finale**

Apri il browser e controlla:

- **clic su Home** → ritorna alla root del progetto
    
- **clic su un esercizio** → apre il file corretto
    
- **il menù è sempre presente** indipendentemente dalla pagina
    

A quel punto l’esercizio è completato correttamente.

---

### **7. Riepilogo didattico**

Con esercizio 5 hai imparato:

- come si costruisce un menù reale
    
- come funziona `<nav>`
    
- come strutturare una lista di link interna a un sito
    
- come funzionano i percorsi relativi e assoluti
    
- perché con HTML statico devi **replicare il menù pagina per pagina**
    

Questa è una delle basi fondamentali della progettazione web.

Nel prossimo capitolo entrerai nei **contenuti multimediali**:  
immagini, audio, video, responsive images, attributi moderni come `srcset`, `loading="lazy"`, e molto altro.