## **Lezione 3: L’accordion (componente “collassabile”)**

### **1. Che cos’è un accordion**

L’**accordion** è un componente di interfaccia molto comune, che serve a:

- mostrare **una lista di voci** (di solito titoli / domande)
    
- permettere all’utente di **espandere** una voce alla volta (o più voci)
    
- visualizzare sotto il titolo un **contenuto nascosto** (testo, lista, link, ecc.)
    

Lo hai visto mille volte, ad esempio nelle:

- **FAQ** (“Domande frequenti”)
    
- sezioni “approfondisci”
    
- liste di dettagli tecnici
    

Esempio tipico: risultati Google con le FAQ in basso.  
Ogni domanda è una riga con una freccia ▶, clicchi, si apre la risposta sotto.  
Quello è **un accordion**.

---

### **2. Struttura logica di un accordion**

A prescindere dal framework (Bootstrap, Material, Tailwind, fatto a mano), la struttura logica è sempre questa:

#### **2.1. Accordion**

- È il **contenitore generale**.
    
- Può contenere **uno o più elementi** (item).
    
- A livello HTML può essere un `div`, una `section`, ecc.
    

> Pensa all’accordion come alla “lista completa di domande/elementi”.

#### **2.2. Accordion item**

Ogni **voce** dell’accordion è un **item**.

Contiene:

1. **Header** (testa / riga cliccabile)
    
2. **Contenuto collassabile** (body)
    

In Bootstrap, ad esempio, avrai qualcosa tipo:

- `accordion` → contenitore
    
- `accordion-item` → singola voce
    

#### **2.3. Header (titolo + trigger)**

L’**header** è la parte sempre visibile:

- contiene il **titolo** (es. “Come faccio a iniziare palestra?”)
    
- spesso contiene una **freccia** (▶ / ▼) che ruota quando si apre
    
- è la parte che l’utente **clicca** per aprire/chiudere
    

A livello HTML:

- di solito è un `div` o un `button` dentro un `header` logico
    
- in Bootstrap qualcosa tipo: `accordion-header` + `button`
    

#### **2.4. Corpo / sezione collassabile (collapse + body)**

Sotto l’header abbiamo il blocco che:

- di default è **nascosto**
    
- si **apre** quando clicchi l’header
    
- contiene il **testo di dettaglio**: risposta, descrizione, lista, ecc.
    

Tipica divisione:

- `collapse` → parte che si apre/chiude (animazione, altezza, ecc.)
    
- `accordion-body` → contenuto vero e proprio (testo, paragrafi, liste)
    

> Volendo, si può mettere il testo direttamente dentro la sezione “collapsabile” senza un `body` separato, è solo una scelta di organizzazione.

---

### **3. Esempi pratici: Google e Bootstrap**

#### **3.1. Esempio Google FAQ**

Quando cerchi qualcosa tipo:

> “come iniziare palestra”

e in fondo ai risultati vedi:

- righe con domande
    
- ogni riga ha una freccina
    
- clicchi → appare il testo sotto
    

Hai esattamente:

- **item** = singola domanda
    
- **header** = la riga cliccabile con la domanda + freccia
    
- **collapse + body** = risposta che si apre sotto
    

#### **3.2. Esempio in Bootstrap**

Nella documentazione Bootstrap trovi il componente **Accordion**:

- `accordion` → contenitore
    
- `accordion-item` → elemento singolo
    
- `accordion-header` → la riga cliccabile / titolo
    
- `accordion-collapse` → blocco che si apre
    
- `accordion-body` → testo interno
    

Visualmente, può avere:

- bordo solo sotto oppure un bordo attorno
    
- freccia che ruota
    
- sfondo che cambia colore quando è aperto
    

Ma la **logica è sempre identica**.

---

### **4. Come si realizza tecnicamente (senza entrare nel codice)**

Ci sono due approcci principali:

#### **4.1. Usando HTML “nativo”: `<details>` e `<summary>`**

HTML ha già un mini-accordion integrato:

- `<details>` → contenitore collassabile
    
- `<summary>` → header cliccabile
    

Vantaggi:

- **nessun JavaScript** obbligatorio
    
- comportamento già pronto (apri/chiudi)
    
- accessibilità decente out-of-the-box
    

Limiti:

- stile di default molto basico
    
- spesso va **personalizzato con CSS**
    
- meno controllo fine rispetto a JS custom
    

#### **4.2. Usando `div` + CSS + JavaScript**

Questo è lo stile “Bootstrap / framework / custom”:

- strutturi tu: `div.accordion`, `div.accordion-item`, ecc.
    
- usi CSS per:
    
    - bordi
        
    - padding
        
    - freccia che ruota
        
    - animazione altezza
        
- usi JavaScript per:
    
    - aprire/chiudere il blocco corrispondente
        
    - eventualmente chiudere gli altri item quando ne apri uno (accordion “mutualmente esclusivo”)
        

> Per questa sezione del corso non ci interessa ancora il JS: ci interessa riconoscere **il componente** e la sua logica di base.

---

### **5. Quando usare un accordion (e perché è utile)**

L’accordion è utile quando:

- hai **tante informazioni** ma non vuoi:
    
    - spaventare l’utente con un muro di testo
        
    - occupare tutta la pagina
        
- vuoi mantenere la pagina **pulita** e **scansionabile**
    
- vuoi permettere all’utente di **espandere solo ciò che gli interessa**
    

Esempi d’uso tipici:

- FAQ (domande frequenti)
    
- dettagli tecnici di un prodotto
    
- specifiche avanzate nascoste per non appesantire la pagina
    
- politiche, termini, note aggiuntive
    

---

### **6. Cosa devi portarti a casa da questa lezione**

- L’**accordion è un componente**, non solo “un pezzo di CSS”.
    
- Ha una **struttura precisa**:
    
    - `accordion` → contenitore
        
    - `accordion-item` → singola voce
        
    - `header` → parte cliccabile (titolo + icona)
        
    - `collapse/body` → contenuto che si apre/chiude
        
- Puoi realizzarlo:
    
    - con `<details>/<summary>`
        
    - oppure con `div + CSS + JavaScript`
        
- Lo scopo è **organizzare molte informazioni** in poco spazio, mantenendo la pagina leggibile.
    

Nella prossima lezione passiamo al componente successivo:  
gli **alert**, cioè i messaggi evidenziati (successo, errore, warn, info) che vedi ovunque nelle interfacce moderne.