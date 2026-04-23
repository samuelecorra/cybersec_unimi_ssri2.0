/* E qui le cose si fanno MOOOLTO interessanti: le funzioni ci permetteranno
di riutilizzare il nostro codice in modo semplice e veloce!
Questo ci apre la possibilità di iniziare a programmare progetti
ESPONENZIALMENTE PIU' COMPLESSI! D'altronde, Cod Warzone non è stato scritto
from scratch! Altrimenti, avrebbero dovuto scrivere TUTTO il codice da zero,
compresi i vari sistemi di matchmaking, le mappe, le armi, ecc. Invece, i devs
hanno potuto riutilizzare codice esistente e concentrarsi sulle nuove funzionalità.

Questo è esattamente ciò che faremo anche noi con le funzioni!

In JavaScript le funzioni si presentano in tre forme principali:
1) Dichiarazione di funzione (function declaration)
   - Sintassi: function nome(params) { ... }
   - Hoisting: sì (posso chiamarla anche prima della riga in cui è scritta).

2) Espressione di funzione (function expression)
   - Sintassi: const f = function(params) { ... };
   - Hoisting: no (la variabile è hoistata ma non inizializzata).

3) Arrow function
   - Sintassi: const f = (params) => { ... };
   - Comoda per funzioni brevi e callback; niente proprio 'this' (lessicale).

Concetti chiave:
- Una funzione ha un NOME chiaro (verbo), PARAMETRI come input, un RISULTATO
(return) e magari effetti collaterali (I/O, mutazioni). 
Preferisci funzioni pure quando puoi.
- JS è dinamico: nessun tipo statico nei parametri/return, ma puoi usare:
    parametri di default (function f(x = 0) { ... })
    rest parameters (function f(...xs) { ... })
- Progetta con responsabilità singola: una funzione fa una cosa e la fa bene.
*/

////////////////////////////////////////////////////////////////////////////////

// ESEMPIO 1:

function buonCompleanno(nome, età) {
    console.log(`Tanti auguri a te!`);
    console.log(`Tanti auguri a te!`);
    console.log(`Tanti auguri ${nome}!`);
    console.log(`Tanti auguri a te!`);
    console.log(`Hai compiuto ${età} anni!`);
}

buonCompleanno("Giorgio", 21);  // i parametri vanno sempre comma-separated!

// L'ORDINE CONTA IN QUANTO LA TIPIZZAZIONE DI JS E' DINAMICA
// Quindi, se passo un numero al posto di una stringa, non ci saranno errori di 
// tipo ma otterrei un errore logico!

function buonCompleannoErrata(età, nome) {
    console.log(`Tanti auguri a te!`);
    console.log(`Tanti auguri a te!`);
    console.log(`Tanti auguri ${nome}!`);
    console.log(`Tanti auguri a te!`);
    console.log(`Hai compiuto ${età} anni!`);
}

buonCompleanno("Max", 21);

/* Ci apparirà in console:
Tanti auguri a te!
Tanti auguri a te!
Tanti auguri 21!
Tanti auguri a te!
Hai compiuto Max anni!      ergo un erroraccio!


////////////////////////////////////////////////////////////////////////////////


Se vogliamo fare in modo che una funzione ci ri-sputi qualcosa al di fuori dopo
la sua esecuzione, ecco che diventa tassativo apporre la keyword "return": */

function somma(a, b) {
    result = a + b;
    return result;
}

// Call:
let rispostaSomma = somma(2003, 2000);
console.log(rispostaSomma);

// Ma in realtà la cosa funziona anche se non immagazzinassimo il valore
// ritornato in un'apposita variabile:

console.log(somma(2003, 2000));

////////////////////////////////////////////////////////////////////////////////
// Le potenzialità sono molteplici: possiamo facilmente crearci funzioni per
// tutte le operazioni aritmetiche: differenza, prodotto, divisione, ecc:

function differenza(a, b) {
    return a - b;
}

function prodotto(a, b) {
    return a * b;
}

function divisione(a, b) {
    if (b === 0) {
        console.error("Divisione per zero non permessa");
        return null;
    }
    return a / b;
}

function modulo(a, b) {
    if (b === 0) {
        console.error("Divisione per zero non permessa");
        return null;
    }
    return a % b;
}

function èPari(n) {
    return n % 2 === 0; // restituisce un booleano.
}
console.log(èPari(4)); // true
console.log(èPari(5)); // false

function validaEmail(email) {

    if (email.includes("@") && email.includes(".com")) {
        return true;
    }
    return false;
}
console.log(validaEmail("prodigyspellcaster@gmail.com")); // true
console.log(validaEmail("prodigyspellcaster")); // false

// E come introduzione alle funzioni può essere più che abbastanza!