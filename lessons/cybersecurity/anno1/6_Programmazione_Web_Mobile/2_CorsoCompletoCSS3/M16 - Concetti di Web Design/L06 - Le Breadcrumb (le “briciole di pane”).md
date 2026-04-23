## **Lezione 6: Le Breadcrumb (le “briciole di pane”)**

### **1. Perché si chiamano così**

“Breadcrumb” significa _briciole di pane_ ed è un riferimento diretto alla fiaba di **Pollicino**:  
lascia briciole lungo il percorso per poter ritrovare la strada.

Sul web l’idea è identica:

> Le breadcrumb servono per mostrare **il percorso che ti ha portato a quella pagina**, così puoi risalire ai livelli precedenti senza perderti.

È un componente piccolo, ma potentissimo.

---

## **2. Cosa risolvono le breadcrumb**

### **2.1. Orientamento dell’utente**

Le breadcrumb rispondono alla domanda:

> “Dove mi trovo esattamente all’interno del sito?”

Funzionano quando un sito ha:

- pagine
    
- sottopagine
    
- sottopagine di sottopagine
    
- strutture gerarchiche complesse
    
- categorie → sottocategorie → item
    

Esempio classico:

```
Home / Corsi / API Node.js
```

Un utente capisce subito:

- dove si trova
    
- da dove è arrivato
    
- come può tornare indietro velocemente
    

---

### **2.2. Navigazione più veloce**

Specie su mobile, dove la _navbar_ è spesso nascosta:

- sei in una pagina profonda
    
- vuoi tornare al livello superiore
    
- invece di aprire il menu → clicchi direttamente la breadcrumb
    

È molto più intuitivo e rapido.

---

### **2.3. SEO – Motori di ricerca**

Questo è il motivo **più importante** e quello meno noto ai principianti:

> Google adora le breadcrumb.

Perché:

- rendono la struttura del sito più leggibile
    
- aiutano i crawler a capire le gerarchie
    
- migliorano la semantica
    
- aumentano la probabilità di apparire nei risultati con _rich snippets_
    

Un sito con breadcrumb ben fatte è considerato più “pulito”, più ordinato, più navigabile.

Molti siti le mettono **non per gli utenti**, ma per Google.

---

## **3. Come sono fatte tecnicamente**

Una breadcrumb è, fondamentalmente:

- una lista ordinata (spesso `<nav><ol>…</ol></nav>`)
    
- con separatori (slash `/`, freccette `›`, oppure `>`)
    

Esempio classico:

```
Home > Corsi > API Node.js
```

Oppure:

```
Home / Library / Data
```

Oppure in stile mobile:

```
← Tutti gli esercizi
Esercizi / C
```

Non esiste uno stile unico: la logica è sempre la stessa.

---

## **4. Perché sono considerate componenti**

Perché hanno:

- struttura ricorrente
    
- stile coerente
    
- funzione molto chiara
    
- possono essere riutilizzate in tante pagine
    
- fanno parte della UI e della UX del sito
    

E, proprio perché ricorrenti, ogni framework CSS le include:

- Bootstrap → componente **Breadcrumb**
    
- Material Design → **Breadcrumbs / Navigation**
    
- Librerie Tailwind → Flowbite, DaisyUI, ecc.
    

Anche se Tailwind “di base” non ha componenti, le librerie su Tailwind li ricreano.

---

## **5. Quando usarle (e quando no)**

### **Usarle quando:**

- il sito ha una forte gerarchia
    
- l’utente può “perdersi” nelle sottopagine
    
- è utile tornare velocemente ai livelli superiori
    
- vuoi migliorare il SEO del sito
    

### **Evitarle quando:**

- hai un sito piccolissimo (2–3 pagine)
    
- hai un’app web senza vera gerarchia
    
- hai già una navigazione molto chiara e immediata
    

In questi casi rischiano di essere ridondanti.

---

## **6. L’estetica non è il punto: è il contesto**

Molti principianti pensano:

> “Ma se ho già la navbar, le breadcrumb sono inutili.”

La verità è:

- **alcuni utenti non le guardano**
    
- **ma Google sì**
    
- **e sono utilissime quando navighi molto in profondità**
    

Quindi non è un componente estetico:  
è un componente _funzionale_ e _semantico_.

---

## **7. takeaway della lezione**

- “breadcrumb” = briciole di pane, come Pollicino
    
- servono a mostrare il percorso all’interno del sito
    
- migliorano orientamento, navigazione, SEO
    
- sono un componente classico e ricorrente
    
- stilisticamente sono semplicissime, concettualmente molto utili
    

---

## **Prossima lezione**

Passiamo ora a una categoria fondamentale dell’interfaccia:

### **I Bottoni – tutti i tipi, tutte le varianti, e quando usarli.**