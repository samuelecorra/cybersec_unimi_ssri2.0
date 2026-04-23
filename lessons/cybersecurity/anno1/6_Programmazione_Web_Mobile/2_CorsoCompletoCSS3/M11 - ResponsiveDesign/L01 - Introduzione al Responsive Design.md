In questa sezione iniziamo a parlare di responsive design, cioè di come fare in modo che un sito si adatti da solo a schermi e dispositivi diversi.

Quando diciamo “responsive design”, in italiano potresti pensarlo come “design responsivo”: un tipo di progettazione grafica che risponde a ciò che succede a schermo. In pratica, il layout non è rigido, ma si adatta allo spazio disponibile.

Il problema che il responsive design risolve nasce dal fatto che oggi esistono moltissimi tipi di schermi.

Un tempo era tutto molto semplice: c’era praticamente un solo formato di monitor, quadrato. Poi è arrivato il 1280, il 1920×1080, e oggi abbiamo 2K, 4K, 8K, monitor ultrawide, curvi, eccetera. Già solo tra i desktop, la differenza tra un 1080p e un 4K è enorme: lo spazio orizzontale e verticale cambia parecchio, e se progetti solo per una risoluzione rischi di ritrovarti con enormi bande vuote, contenuti stretti nel mezzo e un sacco di spazio non usato ai lati.

Ma il vero motivo per cui nasce il responsive design non è il passaggio tra i vari monitor desktop, bensì l’arrivo dei dispositivi mobili connessi a Internet. Tra il 2006 e il 2008 esplodono smartphone e tablet: Apple con iPhone, Samsung, Google, Microsoft, Nokia, Motorola, Huawei, Oppo, e così via. Ognuno con il proprio schermo, altezza diversa, larghezza diversa, densità di pixel diversa, orientamento verticale/orizzontale, formati strani. È letteralmente una giungla di formati.

I siti pensati solo per desktop, portati pari pari su mobile, diventavano spesso inguardabili. Ti sarà capitato di vedere pagine che da telefono appaiono come una versione “rimpicciolita” del sito desktop: tutto minuscolo, devi zoomare con le dita, spostarti a destra e a sinistra, non riesci a cliccare sui bottoni perché sono minuscoli, il testo è illeggibile. È un’esperienza d’uso pessima.

Quindi il problema è chiaro: non puoi più progettare il layout come se avessi un solo “foglio rigido” in pixel, dove dici “questo box 50px da sinistra, questo 100px da sopra, questo fisso a destra” e fine. Se fai così, appena cambia lo schermo qualcosa si rompe: un bottone esce fuori dalla viewport, una colonna scompare, il testo diventa microscopico o gigantesco.

Da qui nasce il responsive design: un modo di progettare il layout in cui la pagina si adatta da sola allo spazio disponibile, senza che tu debba scrivere un layout diverso a mano per ogni singolo modello di telefono o monitor.

Il responsive design, quindi, è la capacità dell’interfaccia di:

- capire quanto spazio ha a disposizione
    
- ridisporre gli elementi in modo leggibile e usabile
    
- cambiare eventualmente il layout, la disposizione, e anche il tipo di navigazione a seconda del device
    

Non riguarda solo il fatto di “stringere” o “allargare” il contenuto; a volte significa proprio cambiare struttura.

Alcune cose che hai già visto sono in realtà pezzi di responsive design: per esempio, il flexbox è un tipo di layout che si adatta da solo allo spazio, le grid con `auto-fit` e `fr` ridistribuiscono le colonne quando lo spazio diminuisce o aumenta, il multicolumn layout fa sì che il numero di colonne cambi quando la larghezza cambia. In altre parole, hai già incontrato layout che “rispondono” allo spazio disponibile, anche se non li abbiamo ancora collegati alle vere e proprie strategie di responsive design (quelle le vedrai nei prossimi video).

Per capire meglio, guardiamo un esempio concreto: il sito che l’insegnante mostra (il suo sito personale).

Da desktop, nella versione “grande”, in alto c’è il menu completo, il logo, il pulsante “Accedi”, il carrello, tutto ben visibile, allineato in orizzontale. È quello che ti aspetti da un sito desktop: hai tanto spazio e vuoi vedere subito tutte le voci principali.

Se passa in vista mobile con gli strumenti di sviluppo (F12 + emulazione mobile), le cose cambiano completamente. In alto non c’è più il mega menu, ma solo il logo, l’icona del carrello e l’icona del menu “hamburger”. È una scelta progettuale: il carrello deve essere sempre visibile perché l’obiettivo è vendere (meno passaggi per arrivare all’acquisto), il logo serve per il branding e per tornare alla home, e il menu viene compresso in un pannello a comparsa. È una soluzione tipica di responsive design: riduco il numero di elementi visibili e sposto il resto dietro un pulsante.

Questa non è solo una pagina “rimpicciolita”: è un layout diverso, pensato per un contesto diverso.

Lo stesso discorso vale per la pagina dei corsi. Da desktop, muovendo la larghezza della finestra, possiamo vedere come i blocchi dei corsi si dispongano in più colonne o cambino la loro occupazione dello spazio. Ci sono casi “intermedi” che magari non sono belli ma non si vedranno quasi mai, perché nessuno usa un dispositivo con quelle proporzioni strane. Sui tablet veri, invece, il sito è stato curato per apparire bene: i corsi si ridistribuiscono in una griglia coerente con la dimensione media dei tablet.

In modalità mobile, la stessa pagina dei corsi cambia ancora: i corsi stanno uno sotto l’altro, i contenuti sono impilati verticalmente e il footer è adattato a uno schermo alto e stretto. In altre pagine, come quella degli esercizi, la differenza è ancora più evidente.

Per esempio, nella pagina degli esercizi di JavaScript, da desktop vedi in alto un elenco di categorie o sezioni, e sotto il relativo contenuto, con magari una sidebar a destra fissa (con `position: fixed`) che contiene l’indice degli esercizi. Questo è normale su desktop: hai tanto spazio orizzontale, ti puoi permettere sidebar fisse che ti seguono mentre scorri.

Da mobile, un layout del genere sarebbe ingestibile: una sidebar fissa a destra su uno schermo stretto ruberebbe spazio prezioso e renderebbe il testo principale illeggibile. Allora, nella versione mobile, l’indice viene spostato in alto come una sorta di menù a tendina o sezione separata, con un comportamento completamente diverso. Non è più “fisso a lato” ma appare in cima alla pagina e puoi aprirlo e chiuderlo. L’utente mobile scorre gli esercizi, ma può sempre tornare rapidamente in alto e scegliere un’altra sezione tramite quell’indice.

Questo è un esempio di responsive design avanzato: non solo ridimensioni gli elementi, ma cambi proprio l’interazione e la struttura.

Un altro esempio: la pagina degli esercizi con le sezioni a “fisarmonica” (accordion). Da desktop puoi permetterti di avere liste più lunghe e più informazioni contemporaneamente a schermo. Da mobile, invece, è stato scelto di mostrare solo i titoli delle tipologie con un effetto dropdown: tocchi il titolo, si apre la lista; ritocchi e si richiude. Questo evita lunghissime pagine che costringerebbero l’utente a scrollare tanto prima di trovare quello che gli serve.

Il responsive design, quindi, non è solo “da orizzontale a verticale” o “riduciamo la dimensione del font”. Può significare:

- cambiare il numero di colonne
    
- spostare elementi (per esempio, il menu in un hamburger)
    
- cambiare completamente la navigazione
    
- modificare la posizione degli indici e dei blocchi informativi
    
- rendere un blocco fisso su desktop ma “libero” o spostato su mobile
    
- adattare colori, sfondi o spaziature per rendere la UI leggibile su schermi diversi
    

Tutto questo parte da una domanda fondamentale: “Come faccio a far sì che il mio sito sia usabile e leggibile su qualunque dispositivo abbia senso usare, senza dover riscrivere tutto da zero ogni volta?”.

Nei video successivi vedrai gli strumenti pratici per fare responsive design: media query, strategie con flexbox e grid, tecniche per ridisporre gli elementi in base alla larghezza dello schermo e per costruire layout che si adattano in modo intelligente.

Per ora, l’obiettivo di questa introduzione è solo fissare nella testa il concetto chiave: il responsive design non è un “effetto carino”, è una necessità. Nasce dalla realtà di troppi schermi e troppi formati diversi, e ti obbliga a pensare il layout non più come un collage fisso, ma come qualcosa che deve respirare e cambiare forma, pur restando sempre comprensibile e usabile.