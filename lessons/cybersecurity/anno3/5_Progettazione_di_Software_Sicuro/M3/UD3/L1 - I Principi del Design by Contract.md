# **Lezione 1: I Principi del Design by Contract**

### 1. Introduzione al Design by Contract

Il **Design by Contract (DbC)** è una metodologia di progettazione del software che si basa sull’idea che l’interfaccia di un modulo definisca un vero e proprio contratto tra chi utilizza il modulo e chi lo implementa. Questo approccio risulta particolarmente naturale nel paradigma orientato agli oggetti, dove i componenti software sono organizzati in classi con operazioni ben definite.

Il concetto di contratto deriva dal mondo reale: un contratto è un accordo esplicito tra due o più parti che stabilisce obblighi e benefici reciproci. Applicato al software, il contratto descrive formalmente cosa un componente richiede e cosa garantisce.

Un contratto possiede caratteristiche fondamentali:

- è esplicito e documentato;
    
- lega le parti coinvolte (cliente e fornitore);
    
- specifica obblighi e benefici;
    
- non contiene clausole nascoste;
    
- gli obblighi di una parte corrispondono ai benefici dell’altra.
    

Nel contesto software:

- il **cliente** è chi utilizza il componente;
    
- il **fornitore** è chi lo implementa.
    

Questo paradigma consente di ridurre ambiguità e migliorare la qualità del software.

---

### 2. Origini del Design by Contract e Bertrand Meyer

Il Design by Contract è stato introdotto da **Bertrand Meyer**, autore del libro _Object-Oriented Software Construction_ e creatore del linguaggio di programmazione **Eiffel**, progettato proprio per supportare nativamente i contratti software.

L’analogia utilizzata nelle slide con la Torre Eiffel evidenzia come sistemi complessi possano essere costruiti con successo rispettando specifiche precise, tempi e budget, utilizzando componenti ben definiti. Allo stesso modo, il software può essere costruito in modo affidabile se i contratti tra componenti sono chiari e rigorosi.

---

### 3. Contratti nel software orientato agli oggetti

Nel software orientato agli oggetti, l’oggetto del contratto è costituito dalle classi e dai metodi.

Ogni classe dovrebbe possedere un contratto che:

- sia esplicito;
    
- sia parte integrante dell’elemento software;
    
- descriva il comportamento atteso.
    

Il linguaggio Eiffel incorpora direttamente il supporto ai contratti, permettendo di specificare precondizioni, postcondizioni e invarianti nel codice stesso.

Questo approccio riduce la distanza tra specifica e implementazione, migliorando la coerenza del sistema.

---

### 4. Precondizioni e postcondizioni

Un contratto software può definire:

- **precondizioni**, cioè ciò che deve essere vero prima dell’esecuzione di un metodo;
    
- **postcondizioni**, cioè ciò che deve essere vero dopo l’esecuzione del metodo.
    

Le precondizioni rappresentano un obbligo del cliente: il metodo può essere invocato solo se le precondizioni sono soddisfatte.

Le postcondizioni rappresentano un obbligo del fornitore: il metodo deve garantire che esse siano vere al termine dell’esecuzione.

Dal punto di vista teorico, tali condizioni possono essere espresse mediante logica formale, ma nella pratica è preferibile utilizzare la sintassi del linguaggio di programmazione o di strumenti specifici.

---

### 5. Esempio: inserimento in un array

Un esempio concreto riguarda l’operazione di inserimento in un array.

Si considerano:

- `no_elements` — numero di elementi presenti;
    
- `size` — dimensione massima dell’array.
    

La precondizione è:

- l’array non deve essere pieno:  
    `no_elements < size`
    

Le postcondizioni sono:

- l’elemento inserito appartiene all’array;
    
- il numero di elementi aumenta di uno:  
    `no_elements’ = no_elements + 1`
    

Il simbolo `no_elements’` indica il valore dopo l’operazione.

Questo esempio evidenzia come il contratto descriva il comportamento senza entrare nei dettagli implementativi.

---

### 6. Scelta delle precondizioni: forti o deboli

Una questione progettuale importante riguarda la scelta della forza delle precondizioni.

- Una **precondizione debole** significa che il metodo accetta molti casi (ad esempio TRUE indica nessuna precondizione).
    
- Una **precondizione forte** limita l’uso del metodo (ad esempio FALSE indica che non può essere chiamato).
    

Non esiste una regola universale: la scelta dipende dagli obiettivi progettuali. Tuttavia, spesso è preferibile definire metodi semplici con contratti chiari piuttosto che metodi complessi che cercano di gestire ogni possibile situazione.

---

### 7. Garanzia delle precondizioni e delle postcondizioni

Il Design by Contract stabilisce chiaramente la responsabilità delle parti:

- il cliente deve garantire le precondizioni prima della chiamata;
    
- il fornitore garantisce le postcondizioni dopo l’esecuzione.
    

Il cliente può garantire le precondizioni:

1. verificandole esplicitamente prima della chiamata;
    
2. ragionando sul programma per assicurarsi che siano soddisfatte.
    

Il fornitore, invece, assume che le precondizioni siano vere e deve implementare il metodo in modo da garantire le postcondizioni.

Questo approccio evita controlli duplicati e migliora l’efficienza del sistema.

---

### 8. Test mentale sulle precondizioni e postcondizioni

Le slide presentano un esercizio concettuale: uno sviluppatore preferirebbe precondizioni forti e postcondizioni deboli, perché rendono il metodo più facile da implementare.

Due offerte ipotetiche:

1. `{False} A {...}` — il metodo non verrà mai chiamato;
    
2. `{...} A {True}` — qualsiasi implementazione è valida.
    

Questo esempio evidenzia come la definizione del contratto influenzi direttamente la complessità dell’implementazione.

---

### 9. Invarianti di classe

Oltre a precondizioni e postcondizioni, un contratto può includere un **invariante**, cioè una proprietà che deve essere sempre vera per tutte le istanze della classe.

L’invariante deve essere:

- vero dopo la creazione dell’oggetto;
    
- vero dopo ogni operazione.
    

Nel caso dell’array:

`0 <= no_elements <= size`

L’invariante rappresenta:

- un vincolo per l’implementazione dei metodi;
    
- una garanzia per chi utilizza la classe.
    

---

### 10. Contratti come documentazione

I contratti possono essere definiti anche prima dell’implementazione dei metodi e fungono da documentazione formale della classe.

Da precondizioni, postcondizioni e invarianti è possibile generare automaticamente documentazione aggiornata, migliorando la qualità del progetto.

---

### 11. Ruolo delle eccezioni

Nel Design by Contract, le eccezioni vengono sollevate solo quando il contratto è violato.

Le violazioni possono riguardare:

- precondizioni (errore del cliente);
    
- postcondizioni o invarianti (errore del fornitore).
    

È importante distinguere tra:

- **casi eccezionali**, che indicano difetti;
    
- **casi particolari**, che devono essere gestiti con normali strutture di controllo.
    

Una violazione del contratto rappresenta quindi un bug nel sistema.

---

### 12. Vantaggi tecnici del Design by Contract

Secondo Meyer, l’uso del Design by Contract offre numerosi vantaggi:

- processo di sviluppo più focalizzato;
    
- forte aderenza alle specifiche;
    
- migliore riusabilità del software;
    
- gestione delle eccezioni più chiara;
    
- documentazione sempre aggiornata;
    
- individuazione più rapida degli errori;
    
- supporto alla generazione di test black-box.
    

Questi benefici sono particolarmente rilevanti nella progettazione di software sicuro, dove la precisione delle specifiche è cruciale.

---

### 13. Strumenti e sistemi che supportano il DbC

Diversi strumenti supportano il Design by Contract:

- Eiffel;
    
- GNU Nana per C e C++;
    
- iContract per Java;
    
- Java Assertions (dalla versione JDK 1.4);
    
- Jass;
    
- JML (Java Modeling Language).
    

Questi strumenti permettono di integrare i contratti direttamente nel codice.

---

### 14. Sintesi della lezione

In questa lezione sono stati introdotti i principi fondamentali del Design by Contract applicati al software orientato agli oggetti.

Sono stati analizzati:

- il concetto di contratto;
    
- ruoli di cliente e fornitore;
    
- precondizioni;
    
- postcondizioni;
    
- invarianti;
    
- responsabilità delle parti;
    
- ruolo delle eccezioni;
    
- vantaggi tecnici.
    

Un aspetto fondamentale da ricordare è che il contratto definisce chiaramente cosa deve essere garantito prima e dopo l’esecuzione di un metodo, migliorando affidabilità e sicurezza del software.

---

### 15. Prossimi passi

Nella prossima lezione verrà approfondito come scrivere contratti utilizzando **JML (Java Modeling Language)**.