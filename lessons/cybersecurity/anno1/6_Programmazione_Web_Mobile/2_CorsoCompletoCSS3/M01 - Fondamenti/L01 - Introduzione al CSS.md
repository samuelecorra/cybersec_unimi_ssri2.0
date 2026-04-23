**Lezione 1 – Introduzione al CSS**

**Che cos’è il CSS e perché esiste**

Il CSS è il linguaggio che permette di trasformare una pagina HTML da semplice contenuto grezzo a un contenuto organizzato, leggibile e visivamente gradevole. Se si prende la stessa pagina HTML in versione “pura” e la si confronta con la stessa pagina a cui è stato applicato del CSS, la differenza è immediata: il menu può essere messo in linea e centrato, le immagini possono essere ingrandite e arrotondate, il font può cambiare, gli spazi possono essere equilibrati, i blocchi di codice possono avere sfondi e bordi, e la pagina in generale assume un aspetto più moderno e ordinato.

HTML definisce **che cosa c’è** nella pagina.  
CSS definisce **come appare** ciò che c’è nella pagina.

**Il significato del termine CSS**

CSS significa _Cascading Style Sheet_.

- _Style Sheet_ significa “foglio di stile”. Pensalo come un vero foglio di carta in cui scrivi regole del tipo: “questo testo deve avere questo colore”, “questo elemento deve avere questo margine”, “questa immagine deve essere larga così”.
    
- “Foglio” non è casuale: i file CSS sono letteralmente raccolte di istruzioni di stile.
    
- _Cascading_ significa che le regole vengono applicate “a cascata”, cioè secondo un ordine gerarchico. Le regole definite più in basso (o più vicine all’elemento) prevalgono su quelle più in alto. Inoltre, lo stile può “scendere” da elementi più grandi a quelli contenuti dentro di essi. Approfondiremo la cascata e l’ereditarietà nei prossimi video.
    

Il CSS, quindi, è il meccanismo che un browser utilizza per prendere del semplice contenuto e renderlo gradevole. Ogni browser è in grado di leggere HTML, CSS e, come vedremo in futuro, JavaScript.

**Un’analogia per capire meglio**

Immagina di avere dati grezzi in Excel: tutto in bianco e nero, senza formattazione. Se inizi a mettere grassetti, colori, spaziature, righe evidenziate, stai facendo ciò che il CSS fa nelle pagine web. Non è la stessa tecnologia, ovviamente, ma il concetto è identico: aggiungere stile sopra contenuto già esistente.

**Materiale necessario per il corso**

Nei prossimi due video installeremo e configureremo Visual Studio Code, lo stesso editor usato nel corso HTML.  
Se hai già seguito quel corso, puoi saltare quei due video e andare direttamente al video successivo, dedicato ai valori di default imposti dal browser.

**Un’estensione consigliata**

Per lavorare comodamente con CSS su Visual Studio Code, ti basta installare:

- _HTML CSS Support_ (o _CSS IntelliSense_)
    

Questa estensione aggiunge autocompletamento e suggerimenti intelligenti per le regole CSS (e anche HTML, ma ora non ci interessa). Installala subito così sei già pronto quando arriveremo alla configurazione dell’editor.

**Conclusione**

Il CSS è la chiave che trasforma contenuto grezzo in contenuto presentabile. Dopo aver installato l’editor, vedremo come funziona lo stile predefinito del browser e inizieremo a costruire le nostre prime regole CSS.