## **Lezione 8: Le Card (il componente più diffuso del web moderno)**

### **1. Perché le card sono fondamentali**

Le card sono uno dei componenti **più importanti e più utilizzati** in tutto il web design moderno.  
Non sono come un bottone, né come un alert, né come una semplice riga di testo.

Una card è un **contenitore di informazioni correlate**, tutte appartenenti allo _stesso oggetto_, allo _stesso elemento logico_, alla _stessa entità_.

Esempi classici:

- una card corso → parla _solo_ del corso
    
- una card hotel su Booking → parla _solo_ di quell’hotel
    
- una card artista su Spotify → parla _solo_ di quell’artista
    
- una card news su Google → parla _solo_ di quell’articolo
    

Le card permettono di:

- presentare dati organizzati
    
- rendere il layout chiaro
    
- creare una gerarchia visiva immediata
    
- permettere all’utente di scansionare rapidamente molte informazioni
    

In pratica: **il web moderno è un gigantesco mosaico di card**.

---

## **2. Come si riconosce una card**

Una card può avere infinite varianti estetiche, ma concettualmente:

### **✔️ è un blocco rettangolare (o quasi) che racchiude informazioni unite dallo stesso tema**

Esempi di informazioni tipiche:

- immagine principale
    
- titolo
    
- sottotitolo
    
- descrizione
    
- icone
    
- bottoni
    
- badge
    
- prezzo
    
- rating
    
- statistiche
    
- autore
    
- categoria
    

Non serve che ci siano _tutti_, ma serve che siano **coerenti fra loro**.

---

## **3. Esempi reali e come interpretarli**

### **A. Le card del tuo sito (CoderMind / Corsi)**

- immagine del corso Node API
    
- titolo del corso
    
- livello
    
- descrizione
    
- prezzo
    
- bottone di acquisto
    

Tutte queste informazioni riguardano _solo quel corso_:  
→ questa è la definizione perfetta di card.

---

### **B. Booking.com**

Booking è letteralmente un catalogo di card:

- foto della struttura
    
- nome hotel
    
- posizione
    
- rating
    
- prezzo
    
- servizi
    
- vari bottoni interattivi
    

Anche se non ci fossero i bordi, l’allineamento e la struttura visiva rendono immediatamente chiaro cosa appartiene a una card e cosa appartiene alla successiva.

---

### **C. Spotify**

Spotify usa card minimaliste e animate:

- immagine dell’album o della playlist
    
- titolo
    
- descrizione/autore
    
- bottone play
    

È una card perfetta: quando ci passi sopra, il bottone compare → interazione immediata.

---

### **D. Google (risultati avanzati)**

Google mette le informazioni dentro “blocchi” molto marcati:

- immagine
    
- titolo
    
- descrizione
    
- link
    
- bottoni rapidi
    

Questo è un card layout “funzionale”: pragmatico, pulito, leggibile.

---

## **4. Una card NON è…**

- una sezione enorme con troppo contenuto (quella è una _section_)
    
- una riga di menu
    
- un semplice paragrafo
    
- un bottone molto grande
    

La card si riconosce perché:

- ha un inizio e una fine
    
- raccoglie un set di dati omogenei
    
- “vive” come elemento autonomo e ripetibile
    

---

## **5. Perché le card hanno forme diverse?**

Perché il web moderno è basato su **grid system**.

Le card possono essere:

- verticali
    
- orizzontali
    
- quadrate
    
- senza bordi
    
- con ombre (material)
    
- completamente flat
    
- con background trasparenti
    
- con animazioni hover
    
- a tutta immagine
    
- solo testo + icona
    

La forma estetica cambia, ma la logica del componente resta identica.

---

## **6. Struttura interna tipica di una card**

Una card può essere composta da:

- **header** (titolo, autore, badge)
    
- **media** (immagine o video)
    
- **body** (testo, descrizione, info)
    
- **footer** (bottoni, call-to-action, prezzo, rating)
    

Esempio generico:

```
Card
 ├── Immagine
 ├── Titolo
 ├── Descrizione
 ├── Badge / Tag
 └── Bottoni
```

Non è obbligatorio avere tutti questi blocchi, ma questa struttura ti dà sempre un riferimento.

---

## **7. Le varianti delle card nei framework**

### **Bootstrap**

Offre card prefabbricate:

- con bordo
    
- senza bordo
    
- con immagine
    
- con header/footer
    

### **Tailwind**

Non ha componenti pre-costruiti.  
Devi costruirtele tu:

- `rounded-lg`
    
- `shadow`
    
- `p-4`
    
- `bg-white`
    
- `w-80`
    
- `flex flex-col`
    
- ecc.
    

Esistono librerie esterne (Flowbite, Tailwind UI) che offrono card già fatte come componenti riutilizzabili.

---

## **8. Il valore UX delle card**

Le card sono fondamentali nell'UX perché:

- permettono all’utente di scansionare molte informazioni rapidamente
    
- danno un senso di ordine e modularità
    
- funzionano perfettamente nei layout responsivi
    
- possono diventare cliccabili in tutta la loro area
    
- sono facilmente animabili
    
- migliorano la leggibilità del contenuto
    

È la forma più versatile e più riconoscibile del web design.

---

## **Conclusione**

Le card non sono un semplice “div fatto bene”.  
Sono un **contenitore logico e visivo** che racchiude informazioni correlate e permette all’utente di navigare un’app o un sito con ordine e immediatezza.

Conoscere bene le card significa saper costruire il **90% delle interfacce moderne**:  
corsi, news, prodotti, playlist, utenti, articoli, offerte, store, dashboard, recensioni.

---

## **Prossima lezione**

Passiamo a un’altra categoria fondamentale: **le liste e i gruppi di elementi** (list group).