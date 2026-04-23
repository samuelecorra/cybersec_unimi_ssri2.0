// A seguire, il metodo filter: possiamo farci comodamente ritornare un nuovo
// array filtrando elementi desiderati e indesiderati da quello originale.


// ESEMPIO 1:

let numeri = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

function èPari(numero) {
    return numero % 2 === 0;    // Restituisce un booleano!
}

let numeriPari = numeri.filter(èPari);
console.log(numeriPari); // [2, 4, 6, 8, 10]

// ATTENZIONE: IL FILTRAGGIO SI EFFETTUA NEL SEGUENTE MODO:
// Se la funzione di callback restituisce true, l'elemento associato farà parte
// del nuovo array, se false invece verrà escluso.

let numeriDispari = numeri.filter(function(numero) {
    return numero % 2 !== 0;
});
console.log(numeriDispari); // [1, 3, 5, 7, 9]


///////////////////////////////////////////////////////////////////////////////


// ESEMPIO 2:

const età = [18, 21, 16, 25, 30, 15, 22];

function èMinorenne(eta) {
    return eta < 18;
}

let minori = età.filter(èMinorenne);
console.log(minori); // [16, 15]

function èMaggiorenne(eta) {
    return eta >= 18;
}

let maggiorenni = età.filter(èMaggiorenne);
console.log(maggiorenni); // [18, 21, 25, 30, 22]


///////////////////////////////////////////////////////////////////////////////


// ESEMPIO 3:

const frutti = ["mela", "melograno", "kiwi", "fragola", "ananas", "mandarancio"];

function iniziaConM(frutto) {
    return frutto.startsWith("m");
}

let fruttiConM = frutti.filter(iniziaConM);
console.log(fruttiConM); // ["mela", "melograno", "mandarancio"]

// oppure se volessimo filtrare parole troppo corte
function èParolaCorta(frutto) {
    return frutto.length < 5;
}

let fruttiCorti = frutti.filter(èParolaCorta);
console.log(fruttiCorti); // ["mela", "kiwi"]

// Specularmente:
function èParolaLunga(frutto) {
    return frutto.length >= 5;
}

let fruttiLunghi = frutti.filter(èParolaLunga);
console.log(fruttiLunghi); // ["melograno", "fragola", "ananas", "mandarancio"]

// NOTA BENE: filter() NON MODIFICA l'array originale, ma ne crea uno nuovo
// con gli elementi filtrati secondo la funzione di callback fornita.