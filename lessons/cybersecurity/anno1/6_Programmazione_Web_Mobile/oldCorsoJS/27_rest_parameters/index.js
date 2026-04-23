/* I cosiddetti parametri rest, sono dei parametri con prefisso ... (da non
confondersi con lo spread operator quindi!) che permettono alla funzione alla
quale son passati di lavorare e trattare un numero variabile di argomenti - 
e ciò è possibile perché li "bundlano", ovvero li raggruppano in un array.

// In poche parole è PROPRIO L'OPPOSTO degli spread operator, 
// che invece "espandono" un array in singoli elementi.

// Ben diverso dal join per ricomporre le stringhe!

// Esempio: */

function apriFrigo(...cibi) {
  console.log(cibi);
}

const cibo1 = "ragù";
const cibo2 = "mozzarella";
const cibo3 = "prosciutto";
const cibo4 = "ramen";

apriFrigo(cibo1, cibo2, cibo3, cibo4); // e potrei continuare ad aggiungere!

function apriFrigoPrendiSingolarmente (...cibi) {
    console.log(...cibi); // qui uso lo spread operator per "espandere" l'array
    // in singoli elementi
}
// E quindi tale seconda funzione non mostra in console l'array intero, ma
// un elemento dopo l'altro SINGOLARMENTE

apriFrigoPrendiSingolarmente(cibo1, cibo2, cibo3, cibo4);


////////////////////////////////////////////////////////////////////////////////


// Ovviamente questi parametri speciali ben si prestano a immagazzinare
// elementi e porli in un apposito array dichiarato al momento.

function prendiCibo(...cibi) {
    return cibi;    // ci facciamo ritornare l'identificatore dell'array creato
    // coi rest parameters
}

// E subito dopo tale funzione va chiamata e il return value assegnato:
const arrayDiCibi = prendiCibo(cibo1, cibo2, cibo3, cibo4);
console.log(arrayDiCibi); // mostro in console l'array creato


////////////////////////////////////////////////////////////////////////////////

// SECONDO ESEMPIO:

function somma(...numeri) {
    let risultato = 0;
    for(let numero of numeri) {
        risultato += numero;
    }
    return risultato;
}

const totale = somma(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
console.log(`Il totale è: ${totale}€`);

function calcolaMedia(...numeri) {
    let risultato = 0;
    for(let numero of numeri) {
        risultato += numero;
    }
    return risultato / numeri.length;
}

const mediaVoti = calcolaMedia(9, 7.5, 8, 6.25, 5, 10, 8.5, 8.75);
console.log(`La media è: ${mediaVoti}`);


////////////////////////////////////////////////////////////////////////////////

// TERZO ESEMPIO:

function combinaStringa(...parole) {
    return parole.join(" ");
}

const frase = combinaStringa("A", "Gommarosa", "piace", "il", "rosa");

console.log(frase); // A Gommarosa piace il rosa
// l'esempio è semplificato e non tiene conto di eventuali punteggiature,
// ma per ora è più che sufficiente!