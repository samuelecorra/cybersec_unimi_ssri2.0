/* Le function expressions in JS NON SONO DA CONFONDERSI CON LE DICHIARAZIONI DI 
FUNZIONE! VEDIAMO LA DIFFERENZA 

Con la dichiarazione di funzione (function declaration), la funzione 
viene definita con il costrutto tradizionale `function nomeFunzione(...) { ... }`. 
In questo caso la funzione è "hoistata", cioè caricata in memoria 
al momento del parsing del codice, e quindi può essere invocata anche 
prima del punto in cui è stata scritta.

Con la function expression invece, la funzione non ha un nome proprio 
(ma può averlo opzionalmente) e viene assegnata come valore a una variabile 
o a una costante. In questo caso, non c’è hoisting del corpo della funzione: 
la funzione sarà disponibile soltanto dopo che l’interprete è arrivato 
a leggere ed eseguire quella riga di codice. 

In altre parole: 
- con la dichiarazione di funzione abbiamo una definizione "anticipata" e globale; 
- con la function expression abbiamo una definizione "puntuale", che segue 
le stesse regole di vita e visibilità della variabile a cui è assegnata.

Questa differenza ha importanti conseguenze soprattutto quando lavoriamo 
con callback, funzioni anonime e programmazione funzionale: 
le function expressions permettono infatti di trattare le funzioni come 
"valori", passandole come argomenti o restituendole da altre funzioni.
*/


///////////////////////////////////////////////////////////////////////////////


// ESEMPIO 1:

helloDecl(); // Invocazione della funzione
// posso invocarla prima della dichiarazione perché la dichiarazione verrà
// hoistata!

function helloDecl() {
    console.log("Hello from a function declaration!");
}


///////////////////////////////////////////////////////////////////////////////


// ESEMPIO 2:

// Se chiamassi subito:
// helloExpr(); 

// Otterrei:
// index.js:47 Uncaught ReferenceError: Cannot access 'helloExpr' 
// before initialization at index.js:47:1

const helloExpr = function() {
    console.log("Hello from a function expression!");
}

// Devo tassativmente chiamarla dopo:
helloExpr();


///////////////////////////////////////////////////////////////////////////////


// Per gli scopi di questa lezione, introduciamo setTimeout:
// si tratta di una funzione che permette di eseguire del codice dopo un certo
// intervallo di tempo.
// Quando la funzione viene invocata, l’esecuzione prosegue normalmente,
// e la callback passerà in coda per essere eseguita solo dopo lo scadere del 
// timer.
//
// Sintassi generale:
//   setTimeout(callback, millisecondi);
//
// Dove:
// - callback → è una funzione (spesso anonima o espressione di funzione) che
// verrà eseguita al termine del tempo stabilito;
// - millisecondi → il tempo di attesa prima dell’esecuzione.
//
// In altre parole, setTimeout ci permette di introdurre un ritardo 
// nell’esecuzione di un codice, e questo lo rende uno strumento fondamentale 
// per capire come funziona la programmazione asincrona in JavaScript.

// Ad esempio:
setTimeout(function() {
    console.log("Hello from setTimeout!");
}, 2000); // da questa riga si noti che dunque in JS è lecito passare a una
// funzione come setTimeout anche una funzione anonima PER INTERO e trattarla
// come fosse un valore!


/* UTILITA': 

1. Callbacks in operazioni asincrone

Le vedrai con setTimeout, fetch, o quando devi aspettare che succeda qualcosa 
(es. una risposta dal server).

L’idea è: invece di scrivere tutto in sequenza, “registro” una funzione che 
verrà chiamata dopo, quando l’evento si completa.

👉 Qui le function expressions sono perfette, perché spesso quella funzione la
usi solo lì e non ti serve darle un nome.


2. Higher-order functions

Significa “funzioni che lavorano con altre funzioni”.
Un classico esempio: map, filter, reduce.

Queste funzioni ricevono come argomento una funzione, oppure restituiscono una 
funzione.

👉 Le function expressions si prestano bene perché puoi definire al volo la 
logica che ti serve e passarla subito.


3. Closures

Qui l’idea è che una funzione può ricordarsi il contesto in cui è stata creata.

Quindi se scrivi una function expression dentro un’altra funzione, quella 
interna può ancora accedere alle variabili della funzione esterna.

👉 È uno strumento potentissimo per creare funzioni “personalizzate” o che 
mantengono uno stato nascosto.


4. Event listeners

Pensa agli eventi del browser: un click, un tasto premuto, lo scroll.

Quando “ascolti” un evento con addEventListener, gli devi dire che cosa fare 
quando succede.

E cosa passi? Una funzione!

👉 Anche qui usare una function expression (magari anonima) è comodissimo, 
perché descrivi subito l’azione da compiere in quel punto del codice.

///////////////////////////////////////////////////////////////////////////////

✅ In sintesi:
Le function expressions servono proprio a trattare le funzioni come valori. 
Questo permette di:

-> “registrarle” per dopo (callbacks),

-> passarle ad altre funzioni (higher-order),

-> sfruttare il fatto che si portano dietro il loro contesto (closures),

-> agganciarle ad eventi (event listeners).    */


////////////////////////////////////////////////////////////////////////////////


// ESEMPIO 1: (riguardarsi le lezioni precedenti se necessario)

const numeri = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

function allaSeconda(n) {
    return n * n;
}

const quadrati = numeri.map(allaSeconda);

console.log(quadrati); // [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

// Ma questo lo abbiam già fatto, ovvero abbiamo passato una declaration.
// Se volessimo passare invece una function expression, potremmo fare così:

// 1. Tagliamo la dichiarazione;
// 2. La incolliamo nella parentesi tonda della parameters list di map
// soprastante;
// 3. La anonimizziamo, ovvero rimuoviamo il nome "allaSeconda";

// 4. Infine assegnamo tale funzione a una variabile:

const quadrati2 = numeri.map(function(n) {
    return n * n;
});

console.log(quadrati2); // [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
// Funziona ancora!! E tutto senza dichiarazioni di funzione, senza nome,
// ma solo passando al mapping un'espressione come fosse un valore!

// Questo risulta utile quando si intende utilizzare tale funzione quella
// sola volta, senza la necessità di riutilizzarla altrove.

// Facciamo l'analoga che calcola i cubi:
const cubi = numeri.map(function(n) {
    return n * n * n;
});

console.log(cubi); // [1, 8, 27, 64, 125, 216, 343, 512, 729, 1000]


// Filtriamo i pari dall'originale stavolta:
const pari = numeri.filter(function(n) {
    return n % 2 === 0; // serve lo strict equality per evitare ambiguità
});

console.log(pari); // [2, 4, 6, 8, 10]

// E i dispari:
const dispari = numeri.filter(function(n) {
    return n % 2 !== 0; // serve anche qui lo strict inequality
});

console.log(dispari); // [1, 3, 5, 7, 9]

// Infine possiamo anche riproporre l'esempio del totale:

const totale = numeri.reduce(function(acc, n) {
    return acc + n;
}, 0);

console.log(totale); // 55


///////////////////////////////////////////////////////////////////////////////

// Nella prossima lezione capiremo come ridurre ancor di più la sintassi...