## **Lezione 1: Introduzione a JavaScript**

### **1. Contesto iniziale**

Benvenuto all’inizio del corso dedicato a JavaScript. Prima di entrare nel “succo” del linguaggio, è necessario costruire alcune basi concettuali. Questo primo video serve per collocare JavaScript nel quadro generale dello sviluppo front-end e per capire da dove arriviamo.

Il corso parte assumendo che tu abbia già seguito HTML e CSS, idealmente nello stesso ordine con cui sono stati spiegati. Se arrivi da Java, da C#, da PHP o da qualsiasi altro linguaggio, non è un problema: i concetti fondamentali verranno comunque illustrati. Tuttavia, se hai seguito attentamente HTML e CSS, hai già due terzi della base del front-end.

In quei due corsi non hai solo imparato tag, proprietà e semantica: sono stati inseriti contenuti professionali sulla produzione reale di interfacce, sull’accessibilità, sull’ottimizzazione, su come evitare cattive pratiche. Sono conoscenze “extra” che non sono obbligatorie per capire JavaScript, ma diventano essenziali nel lavoro per mantenere la sanità mentale e non imparare cose a caso.

### **2. Il front-end non è solo HTML, CSS e JavaScript**

HTML, CSS e JavaScript rappresentano soltanto il livello più fondamentale del front-end. Sono la base necessaria, ma non sufficiente, per lavorare come front-end developer.

Durante il percorso imparerai che l’ecosistema JavaScript è gigantesco: librerie, framework, ambienti di esecuzione, strumenti di build, architetture, metodologie. Questa lezione, però, si concentra sul primo passo: capire cosa fa veramente JavaScript e come si colloca accanto a HTML e CSS.

### **3. Ripasso dei ruoli: HTML, CSS, JavaScript**

Il modo migliore per capire JavaScript è capire ciò che NON fanno HTML e CSS.

#### **3.1 HTML – Il contenuto e la struttura**

HTML definisce ciò che esiste nella pagina: il testo, i titoli, le immagini, le icone, la navbar, le sezioni, le card dei corsi, la struttura gerarchica delle informazioni.  
È il “contenuto messo a schermo”: ciò che il browser deve mostrare.

Una metafora classica: **HTML = i muri di una casa.**

#### **3.2 CSS – Lo stile**

CSS definisce come appare ciò che HTML ha messo a schermo.  
Colore dello sfondo, font, bordi arrotondati, dimensioni, ombre, animazioni hover, reattività al passaggio del mouse: tutto questo è responsabilità di CSS.

È la differenza tra una card grigia e una card viola; tra un link sottolineato e uno non sottolineato; tra un bottone statico e uno che si ingrandisce quando ci passi sopra.

Metafora: **CSS = la vernice, il pavimento, i materiali che rendono bella la casa.**

#### **3.3 JavaScript – La funzionalità e la dinamicità**

Arriviamo all’ultimo terzo del trio: JavaScript.  
JavaScript è ciò che permette alla pagina di reagire all’utente, di fare calcoli, di prendere decisioni e di aggiornarsi dinamicamente.

HTML può mostrare contenuti.  
CSS può colorarli o animarli.  
Solo JavaScript può **far succedere delle cose**.

Metafora: **JavaScript = l’impianto elettrico interattivo, l’assistente che accende la luce, i pulsanti che fanno qualcosa.**

---

### **4. JavaScript nella pratica: esempi chiari e visivi**

#### **4.1 Esempio: bottone che compare o scompare**

Immagina un sito in cui un elemento compare quando arrivi a metà pagina.  
HTML non sa cosa significa “metà pagina”.  
CSS non sa quando tu scrolli a un certo punto.

JavaScript, invece, può:

- restare “in ascolto” dello scroll;
    
- capire quando sei al 50%;
    
- far comparire il bottone;
    
- e farlo tornare su se premi quel bottone.
    

Questo è interattività vera.

#### **4.2 Esempio: card che si chiude con un click**

In un sito di film o prodotti, se clicchi una card e questa si apre, si anima, poi si richiude: tutto ciò è JavaScript.  
HTML definisce la card.  
CSS la rende bella.  
JavaScript controlla cosa succede quando la clicchi.

#### **4.3 Esempio: filtrare una lista in tempo reale**

Prendiamo un’applicazione come un Pokédex.  
Quando scrivi “bulb” e ti appaiono subito Bulbasaur e Ivysaur, è JavaScript a:

- prendere i dati (spesso da un database o da un file JSON),
    
- filtrarli,
    
- mostrare solo quelli che rispettano il criterio.
    

HTML non può filtrare.  
CSS non può filtrare.  
JavaScript sì.

#### **4.4 Esempio: pannello di informazioni aggiuntive**

Quando clicchi su un Pokémon e si apre un pannello laterale con statistiche e descrizioni, quello è un evento gestito da JavaScript.

### **5. JavaScript nei siti reali**

Nei siti di documentazione o nei siti statici, l'interattività può essere minima. Il massimo che può esserci è:

- il pulsante “Copia” per copiare un blocco di codice,
    
- il controllo se l’utente ha già acquistato qualcosa,
    
- l'aggiunta al carrello.
    

Anche questo, però, è JavaScript.

In altri siti — per esempio piattaforme educative come Duolingo — l’interattività è enorme: animazioni, esercizi che reagiscono, feedback immediati, elementi che rispondono ai click, widget, input dinamici.  
Tutto questo è possibile solo grazie a JavaScript.

### **6. Librerie ed ecosistema JavaScript**

Uno degli esempi più evidenti è **Lito.js** (citato nel video): un editor visuale basato totalmente su JavaScript.

Ti permette di:

- scrivere testo,
    
- applicare il grassetto,
    
- inserire link,
    
- modificare i contenuti come in un editor WYSIWYG (“What You See Is What You Get”),
    
- aggiornare tutto in tempo reale direttamente nella pagina.
    

Ogni pulsante, ogni formato, ogni interazione è gestita da JavaScript.

---

### **7. Conclusione della lezione**

JavaScript serve a trasformare una pagina da semplice contenuto colorato a un’esperienza interattiva.  
Senza JavaScript:

- nessun filtro,
    
- nessun pannello,
    
- nessuna animazione basata su eventi,
    
- nessun controllo sui click,
    
- nessuna dinamicità reale.
    

Nel prossimo video vedremo in dettaglio **cosa può fare JavaScript** e quali sono le tipologie di interazioni che possiamo generare.

Hai visto esempi pratici in questa lezione, ma le possibilità reali sono praticamente infinite.

Ci vediamo nella prossima lezione.