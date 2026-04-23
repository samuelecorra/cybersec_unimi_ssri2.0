
/* Uno dei modi migliori per iniziare a parlare di costanti in Javascript è 
quello di realizzare semplici algoritmi di geometria piana, che coinvolgono
chiaramente la costante trascendente più famosa del mondo: π (pi greco)!

const -> una variabile che non può in alcun modo cambiare valore una volta settata! */

// Supponiamo di partire però senza porre la keyword:

let pi = 3.14159;
let raggio; // Lo lasciamo all'user input
let circ;

raggio = window.prompt("Ciao! Inserisci il raggio del cerchio:");

// Ecco che torna subito utile - e tassativo - convertire a Number
raggio = Number(raggio);

circ = 2 * pi * raggio;
// E stampiamo il risultato:
console.log("La circonferenza del cerchio di raggio " + raggio + " è: " + circ);

/* Ma allora perché porre const se il log mostra un valore corretto?
Perché, sia che noi accidentalmente cambiassimo il valore di pi, sia che
sia qualcun altro maliziosamente a farlo, il nostro calcolo diverrebbe errato. */

// Esempio:
pi = 420.69;

// Ed ora ecco l'errore!
console.log("La circonferenza del cerchio di raggio " + raggio + " è: " + circ);

/* Ora, è chiaro che risolviamo il tutto con la keyword const, ma ATTENZIONE:
=> SOLITAMENTE SI TENDE A RENDERE TUTTO IL NOME DELLA VARIABILE COSTANTE
MAIUSCOLO, MA SOLO QUANDO SI TRATTA DI UN TIPO DI DATO PRIMITIVO (NUMERO, CHAR, 
BOOLEANO, NULL, UNDEFINED) */

const PI = 3.14159;

// Se ora provassimo a:
PI = 420.69;

// Ecco che la console ci stampa:

    /* Uncaught TypeError: Assignment to constant variable.
        at index.js:41:4 */

    