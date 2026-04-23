## **Lezione X: Percorsi assoluti e percorsi relativi**

In questa lezione mettiamo ordine su una cosa che userai **ovunque** in HTML:

- quando colleghi **pagine** (`<a href="...">`),
    
- quando carichi **immagini** (`<img src="...">`),
    
- quando includi **file CSS, JS, font**, ecc.
    

> Tutto ruota intorno a **come scrivi il percorso** (path) del file:  
> **assoluto** o **relativo**.

---

### **1. Che cos’è un “percorso” (path)?**

Un **percorso** è semplicemente il modo in cui dici al browser:

> “Vai a prendere _questa_ risorsa in _questo_ posto”.

Esempi tipici:

```html
<!-- Link a una pagina -->
<a href="esercizi/esercizio3.html">Esercizio 3</a>

<!-- Immagine -->
<img src="immagini/icona.webp" alt="Icona del sito" />

<!-- Foglio di stile -->
<link rel="stylesheet" href="css/style.css" />
```

La parte importante è la stringa dentro `href` o `src`:

- `"esercizi/esercizio3.html"`
    
- `"immagini/icona.webp"`
    
- `"css/style.css"`
    

Quella stringa può essere:

- un **percorso assoluto**
    
- oppure un **percorso relativo**
    

---

### **2. Percorsi assoluti**

Un **percorso assoluto** è come l’**indirizzo completo di casa**:

> “Città, via, numero civico”.  
> Lo puoi dare a chiunque, da qualunque città, e arriva lì.

In web significa: un URL completo, che funziona **indipendentemente** da dove ti trovi.

#### **2.1. Assoluto “esterno” (URL completo)**

Esempio con un’immagine su un sito esterno (Pixabay):

```html
<img src="https://cdn.pixabay.com/photo/2022/03/.../castello.jpg" alt="Rovine di un castello" />
```

Caratteristiche:

- inizia con `http://` o `https://`;
    
- contiene dominio + eventuali cartelle + nome file;
    
- **funziona da qualsiasi pagina**, perché è l’indirizzo completo su Internet.
    

Se prendi quell’URL e lo incolli:

- in `index.html`
    
- in `esercizi/esercizio3.html`
    
- in un’altra pagina
    

l’immagine viene sempre da **quell’indirizzo**.

---

#### **2.2. Assoluto “interno” (da root del sito)**

Quando il sito è il tuo, spesso non scrivi tutto l’URL:

```html
<img src="/immagini/icona.webp" alt="Icona" />
```

Qui:

- lo **slash iniziale `/`** significa: “parti dalla **radice del sito** (root)”
    
- `immagini/icona.webp` è il percorso a partire dalla root.
    

È come dire:

> “Vai alla root del progetto, entra nella cartella `immagini`, apri `icona.webp`”.

Questo è ancora un **percorso assoluto**, perché:

- **non dipende** da dove si trova il file HTML che lo usa;
    
- funziona uguale in `index.html`, in `esercizi/esercizio3.html`, ecc.
    

---

### **3. Percorsi relativi**

Un **percorso relativo** è come dare indicazioni del tipo:

> “Esci da questa stanza, vai a sinistra, poi entra nella prossima”.

Non parti dalla città intera (root), parti da **dove ti trovi adesso**.

In HTML:

> Il percorso relativo è interpretato **a partire dalla cartella del file HTML corrente**.

Per capirlo bene, fissiamo una struttura di progetto.

---

### **4. Esempio di struttura di cartelle**

Immagina il tuo progetto così:

```text
/ (root del progetto)
│
├─ index.html
├─ about.html
│
├─ immagini/
│   └─ icona.webp
│
└─ esercizi/
    ├─ esercizio1.html
    ├─ esercizio2.html
    └─ esercizio3.html
```

- `index.html` è nella **root** del progetto.
    
- `esercizio3.html` è dentro la cartella `esercizi`.
    
- `icona.webp` è dentro `immagini`.
    

---

### **5. Relativo da `index.html`**

File: `index.html` (si trova in **root**).

Voglio caricare l’immagine `immagini/icona.webp`.

Posso scrivere:

```html
<img src="immagini/icona.webp" alt="Icona" />
```

Oppure, versione “più esplicita”:

```html
<img src="./immagini/icona.webp" alt="Icona" />
```

- `./` significa: **“da qui dove sono”** (cioè dalla cartella di `index.html`).
    
- da lì vado in `immagini/icona.webp`.
    

Le due versioni funzionano uguale, ma `./` rende evidente che è un **percorso relativo**.

---

### **6. Relativo da `esercizi/esercizio3.html`**

Adesso spostiamoci in:

```text
/esercizi/esercizio3.html
```

La nostra immagine è sempre in:

```text
/immagini/icona.webp
```

Se scrivo ingenuamente:

```html
<img src="immagini/icona.webp" alt="Icona" />
```

da **dentro `esercizi/`**, il browser interpreta:

> “Nella cartella `esercizi` esiste una cartella `immagini`?”

Sta cercando:

```text
/esercizi/immagini/icona.webp
```

…che **non esiste** → immagine rotta.

Per arrivare alla `immagini` “vera” dobbiamo prima **uscire** da `esercizi`.

#### **6.1. Uscire di una cartella: `..`**

- `..` significa: “vai **alla cartella superiore**” (parent directory).
    

Quindi, da `esercizi/esercizio3.html`:

```html
<img src="../immagini/icona.webp" alt="Icona" />
```

lettura mentale:

1. `..` → esci da `esercizi` → torni alla root del progetto.
    
2. `immagini/icona.webp` → entra in `immagini`, apri `icona.webp`.
    

Questo è un **percorso relativo** perfettamente valido.

Se fossi ancora più in profondità, tipo:

```text
/esercizi/soluzioni/esercizio3-soluzione.html
```

potresti usare:

```html
<img src="../../immagini/icona.webp" alt="Icona" />
```

- `..` → sali da `soluzioni` a `esercizi`
    
- altro `..` → sali da `esercizi` alla root
    
- `immagini/icona.webp` → scendi nella cartella immagini
    

---

### **7. Ricapitolone visivo**

Usiamo sempre questa struttura:

```text
/ (root)
├─ index.html
├─ immagini/icona.webp
└─ esercizi/esercizio3.html
```

#### **Da `index.html`:**

- **Assoluto interno** (da root):
    
    ```html
    <img src="/immagini/icona.webp" alt="Icona" />
    ```
    
- **Relativo** (stessa cartella):
    
    ```html
    <img src="immagini/icona.webp" alt="Icona" />
    ```
    
    oppure
    
    ```html
    <img src="./immagini/icona.webp" alt="Icona" />
    ```
    

---

#### **Da `esercizi/esercizio3.html`:**

- **Assoluto interno** (da root):
    
    ```html
    <img src="/immagini/icona.webp" alt="Icona" />
    ```
    
    (funziona ovunque, perché `/` è sempre la root del sito)
    
- **Relativo (con `..`)**:
    
    ```html
    <img src="../immagini/icona.webp" alt="Icona" />
    ```
    

---

### **8. Collegamento con i canonical URL**

Quando in `<head>` scrivi un link canonico:

```html
<link rel="canonical" href="https://ilmiosito.it/nome-articolo" />
```

stai usando un **percorso assoluto esterno**:

- dominio completo,
    
- protocollo (`https://`),
    
- path completo dell’articolo.
    

Questo serve ai motori di ricerca per dire:

> “La versione _ufficiale_ di questa pagina è esattamente questa URL”.

Per questo tipo di metadati si usa quasi sempre l’**absolute URL completo**.

---

### **9. Riassunto concettuale**

- **Percorso assoluto**
    
    - È un indirizzo “universale”.
        
    - Non dipende da dove si trova la pagina HTML.
        
    - Tipi:
        
        - URL completo: `https://cdn.pixabay.com/.../immagine.jpg`
            
        - Da root del sito: `/immagini/icona.webp`
            
- **Percorso relativo**
    
    - Dipende dalla **posizione corrente** del file HTML.
        
    - Usa:
        
        - niente o `./` → “da qui”
            
        - `..` → “sali di una cartella”
            
        - sequenze come `../../` per salire più volte.
            
    - È super comodo quando sposti il sito in blocco (ad esempio da locale a server remoto) senza cambiare dominio.
        

---

### **10. Mini–checklist mentale quando scrivi un path**

Quando scrivi `src="..."` o `href="..."`, pensa:

1. **Voglio un riferimento valido ovunque nel sito?**
    
    - Usa `/cartella/file.ext` (assoluto interno)
        
    - oppure URL completo `https://...` (se esterno).
        
2. **Sto solo navigando dentro il mio progetto e so dove sono?**
    
    - Usa percorso relativo:
        
        - `cartella/file.ext` oppure `./cartella/file.ext` se sei nella root;
            
        - `../cartella/file.ext` se devi prima salire di un livello.
            
3. **L’immagine non si vede?**
    
    - Controlla la struttura delle cartelle.
        
    - Controlla se serviva un `../`.
        
    - Controlla se hai confuso `/` (root) con `./` o `../`.
        

---

Se vuoi, nel prossimo giro posso:

- disegnarti l’albero completo del tuo progetto **HTML+esercizi**,
    
- e scrivere per ogni file **tutti** i path corretti (assoluti e relativi) verso le stesse risorse, così ti resta come “mappa mentale” da tenere nel GitLocker.