/* Le arrow functions non sono altro che una maniera più concisa per scrivere
le function expressions. Infatti, una arrow function è sempre una function 
expression, ma con una sintassi più compatta e alcune differenze nel comportamento, 
specialmente per quanto riguarda il contesto di `this`.

Risultano essere una buona prassi nel caso di funzioni semplici che si intende
usare solo una volta.

// La sintassi è la seguente:
const nomeFunzione = (parametri) => {
    // corpo della funzione
};

/////////////////////////////////////////////////////////////////////////////// */

// Esempio 1:

// a. Dichiarazione:

function saluto(nome) {
    return "Ciao " + nome + "!";
}
saluto("Principessa");

// b. function expression standard:

const saluto2 = function(nome) {
    return "Ciao " + nome + "!";
};
saluto("Principessa");

// c. ARROW FUNCTION:

const saluto3 = (nome, età) => {
    return "Ciao " + nome + "! Hai " + età + " anni.";
};
saluto("Principessa", 20);


///////////////////////////////////////////////////////////////////////////////

// Esempio 2:

setTimeout(() => { console.log("Ciao!"); }, 3000);
// Dopo 3 secondi, stampa "Ciao!" in console


///////////////////////////////////////////////////////////////////////////////


// Esempio 3:
// Iniziamo ad usare le arrow functions anche con map, filter e reduce:

const numeri = [1, 2, 3, 4, 5, 6];

// Iniziamo con map a elevare i valori alla seconda:
const numeriAlQuadrato = numeri.map(num => num ** 2);
console.log(numeriAlQuadrato); // [1, 4, 9, 16, 25, 36]

// Idem per i cubi:
const numeriAlCubo = numeri.map(num => num ** 3);
console.log(numeriAlCubo); // [1, 8, 27, 64, 125, 216]

// Filtriamo i pari:
const numeriPari = numeri.filter(num => num % 2 === 0);
console.log(numeriPari); // [2, 4, 6]

// Filtriamo i dispari:
const numeriDispari = numeri.filter(num => num % 2 !== 0);
console.log(numeriDispari); // [1, 3, 5]

// Riduciamo al totale:
const sommaTotale = numeri.reduce((acc, num) => acc + num, 0);
console.log(sommaTotale); // 21

// SI NOTI CHE ABBIAMO OMESSO "return" poiché 
// NELLE ARROW FUNCTIONS CON UN SOLO ESPRESSIONE, 
// IL VALORE VIENE RESTITUITO IMPLICITAMENTE!

