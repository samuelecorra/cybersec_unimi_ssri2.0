/* Gli operatori logici sono gli stessi già visti in C e Java:

- AND: &&
- OR: ||
- NOT: !

Vediamo alcuni esempi pratici in JavaScript: */

////////////////////////////////////////////////////////////////////////////////

// AND:
let temp = -1;

// Se prima scrivevamo:
if (temp > 0) {

    if (temp < 30) {
        console.log("Il clima è mite (" + temp + " gradi)");
    } else {
        console.log("Il clima non è mite (" + temp + " gradi)");
    }
} else {
    console.log("Il clima non è mite (" + temp + " gradi)");
}

// Ci accorgiamo che è fin troppo verboso e difficile da seguire come flusso
// algoritmico: allora possiamo semplificare utilizzando gli operatori logici!

if (temp > 0 && temp < 30) {
    console.log("Con and: Il clima è mite (" + temp + " gradi)");
} else {
    console.log("Con and: Il clima non è mite (" + temp + " gradi)");
}


////////////////////////////////////////////////////////////////////////////////

// OR:
if (temp < 0 || temp > 30) {
    console.log("Con or: Il clima non è mite (" + temp + " gradi)");
} else {
    console.log("Con or: Il clima è mite (" + temp + " gradi)");
}

// NOT:
if (!(temp > 0 && temp < 30)) {
    console.log("Con not: Il clima non è mite (" + temp + " gradi)");
} else {
    console.log("Con not: Il clima è mite (" + temp + " gradi)");
}

// oppure:
const èSoleggiato = true;

if (!èSoleggiato) {
    console.log("E' nuvoloso");
    console.log("Non dimenticare l'ombrello!");
}
else {
    console.log("E' soleggiato");
}