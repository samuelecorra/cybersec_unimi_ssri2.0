/* Gli array li conosciamo già: sono come delle liste di elementi, dove ogni
elemento in memoria fisica è sito in celle contigue. Questo significa che
possiamo accedere a ciascun elemento dell'array tramite il suo indice, che
rappresenta la sua posizione all'interno dell'array. */

let frutta = ["mela", "banana", "ciliegia"];

console.log(frutta); // ["mela", "banana", "ciliegia"]
console.log(frutta[0]); // "mela"
console.log(frutta[1]); // "banana"
console.log(frutta[2]); // "ciliegia"

// RICORDIAMO SIN DA SUBITO CHE L'INDICIZZAZIONE DEGLI ARRAY, SIA IN C, CHE IN JAVA,
// CHE IN JAVASCRIPT, PARTE DA ZERO (zero-based indexing)!!!

// L'accesso diretto tramite indice ci consente la modifica diretta!

frutta[0] = "mela verde";
frutta[1] = "banana matura";
frutta[2] = "ciliegia dolce";

console.log(frutta); // ["mela verde", "banana matura", "ciliegia dolce"]


////////////////////////////////////////////////////////////////////////////////

// Gli array in JavaScript sono comodamente dinamici, il che significa che
// possiamo aggiungere o rimuovere elementi senza doverci preoccupare della
// dimensione iniziale dell'array. 

// La gestione avviene tramite i due metodi fondamentali: push() e pop().

frutta.push("arancia"); // aggiungo "arancia" alla fine dell'array [3]
frutta.push("kiwi"); // aggiungo "kiwi" alla fine dell'array [4]
frutta.push("fragola"); // aggiungo "fragola" alla fine dell'array [5]
frutta.pop(); // rimuovo l'ultimo elemento dell'array, cioè "fragola"

console.log(frutta); // ["mela verde", "banana matura", "ciliegia dolce", 
// "arancia", "kiwi"] senza "fragola"
// infatti se provo a stampare l'elemento all'indice 5:
console.log(frutta[5]); // undefined

///////////////////////////////////////////////////////////////////////////////

// E se volessimo aggiungere IN TESTA e non in coda?
// Esiste un metodo chiamato unshift() per fare ciò:
frutta.unshift("mango"); // aggiungo "mango" all'inizio dell'array
console.log(frutta); // ["mango", "mela verde", "banana matura", 
// "ciliegia dolce", "arancia", "kiwi"]

// E se volessimo rimuovere IN TESTA e non in coda?
// Esiste un metodo chiamato shift() per fare ciò:
frutta.shift(); // rimuovo "mango" dall'inizio dell'array
console.log(frutta); // ["mela verde", "banana matura", "ciliegia dolce", "arancia", "kiwi"]


///////////////////////////////////////////////////////////////////////////////


// La proprietà (NON METODO) .length ci ritorna la lunghezza dell'array ATTUALE,
// non quella dell'array originale dichiarato e inizializzato
console.log(frutta.length); // 5
// Equivalente:
let lunghezza = frutta.length;
console.log(lunghezza);

// La ricerca di un elemento in un array può essere effettuata 
// tramite il metodo indexOf(), che ritorna l'indice della prima occorrenza
// dell'elemento cercato, oppure -1 se non trovato.
let indice = frutta.indexOf("banana matura");
console.log(indice); // 1

let indice2 = frutta.indexOf("fragola");
console.log(indice); // -1


///////////////////////////////////////////////////////////////////////////////


// Vien da sé che gli array si prestano benissimo allo scorrimento mediante
// ciclo for (vedremo in futuro il forEach):

for (let i = 0; i < frutta.length; i++) {
    console.log(frutta[i]);
}   // Scorriamo e stampiamo ogni elemento!

// Questo ci consente anche di displayare l'array in ordine inverso con un
// piccolo accorgimento: 

for (let i = frutta.length - 1; i >= 0; i--) {
    console.log(frutta[i]);
}

// Un metodo alternativo validissimo è la seguente sintassi:

for (let fruit of frutta) {
    console.log(fruit);
}   // Questo si chiama enhanced for loop (o for...of), che facilita il display
// degli elementi e non di poco!
// la keyword da utilizzare è "of", mentre la variabile ausiliaria è arbitraria!


///////////////////////////////////////////////////////////////////////////////


// Senza andare nel dettaglio di cosa voglia dire "lessicografico" - lo affrontiamo
// in futuro, limitiamoci a dire che con il nostro array di pure stringhe
// di lettere e non numeri, l'ordinamento avviene in modo "naturale", ovvero
// in quello che noi percepiamo come sorting alfabetico, anche se in realtà
// si tratterà di un ordinamento lessicografico.

frutta.sort(); // ordina l'array in ordine crescente
console.log(frutta); // ["arancia", "banana matura", "ciliegia dolce", "kiwi", "mela verde"]
// ATTENZIONE: Dal sort si può attingere anche direttamente ad un suo metodo
// che è reverse()
frutta.sort().reverse(); // ordina l'array in ordine decrescente
console.log(frutta); // ["mela verde", "kiwi", "ciliegia dolce", "banana matura", "arancia"]

// Se avessimo fatto solo: frutta.reverse() cosa sarebbe successo?
// E' solo un'inversione rispetto all'ORDINE ATTUALE, SENZA ORDINAMENTO!

// Nel nostro caso specifico, ripristiniamo l'ordine originale
frutta.reverse();
console.log(frutta); // ["arancia", "banana matura", "ciliegia dolce", "kiwi", "mela verde"]


////////////////////////////////////////////////////////////////////////////////

// Faremo molta pratica in futuro...