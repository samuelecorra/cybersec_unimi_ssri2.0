// Ampliamo i nostri orizzonti: map() è una funzione che crea un nuovo array
// con i risultati della chiamata a una funzione di callback su ogni elemento
// dell'array chiamante.

// Quindi l'array originale rimane invariato, ma si ha la possibilità di assegnare
// tutti i valori di ritorno a un nuovo array.


////////////////////////////////////////////////////////////////////////////////


// ESEMPIO 1:

let numeri = [1, 2, 3, 4, 5];
console.log("Array originale: " + numeri);
const numeriRaddoppiati = numeri.map(function(elemento) {
    return elemento * 2;
});
console.log("Array raddoppiato: " + numeriRaddoppiati); // [2, 4, 6, 8, 10]
// Oppure analogamente:
function raddoppia(elemento) {
    return elemento * 2;
}
const numeriRaddoppiati2 = numeri.map(raddoppia);
console.log("Array raddoppiato: " + numeriRaddoppiati2); // [2, 4, 6, 8, 10]

// Ma possiamo anche fare il cubo dei valori originali visto che l'array originale
// rimane preservato sempre e comunque a prescindere dalle volte che chiamiamo
// map() sulla stessa variabile.
function cubo(elemento) {
    return elemento * elemento * elemento;
}
const numeriCubici = numeri.map(cubo);
console.log("Array cubico: " + numeriCubici); // [1, 8, 27, 64, 125]


////////////////////////////////////////////////////////////////////////////////


// ESEMPIO 2:

let frutta = ["mela", "banana", "kiwi", "arancia", "uva"];

// Versione con dichiarazione+corpo:
let fruttaMaiuscole = frutta.map(function(elemento) {
    return elemento.toUpperCase();
});

console.log(fruttaMaiuscole);

// Versione con dichiarazione e chiamata distinte:
function maiuscole(elemento) {
    return elemento.toUpperCase();
}
let fruttaMaiuscole2 = frutta.map(maiuscole);

console.log(fruttaMaiuscole2);


////////////////////////////////////////////////////////////////////////////////


// ESEMPIO 3:

const date = ["2000-05-17", "2003-1-10", "2026-01-03"];

function formatDate(dateString) {

    // COS'E' SPLIT? 
    const partiData = dateString.split("-");
    return `${partiData[2]}/${partiData[1]}/${partiData[0]}`;
}

const formattedDates = date.map(formatDate);
console.log(formattedDates);

console.log(date); // array originale rimane invariato
 
// Ora: formattedDates[0] contiene "19/04/2008". Se volessi scrivere quel 19
// in una stringa di output, potrei accedere a tale indice per poi 
console.log(`La prima data ha come giorno: ${formattedDates[0].split("/")[0]}`);
console.log(`La seconda data ha come mese: ${formattedDates[1].split("/")[1]}`);
console.log(`L'anno della terza data è: ${formattedDates[2].split("/")[2]}`);


////////////////////////////////////////////////////////////////////////////////
