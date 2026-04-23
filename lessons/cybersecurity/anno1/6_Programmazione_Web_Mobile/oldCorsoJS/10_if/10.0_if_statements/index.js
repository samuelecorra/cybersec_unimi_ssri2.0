/* Abbiamo già metabolizzato molto bene il costrutto di selezione mediante keyword
if / else / else if / e così via...

Vediamo come questi concetti possono essere applicati su JavaScript in concreto: */

let età = 21;

if (età >= 18) {
    console.log("Sei maggiorenne");
} else {
    console.log("Sei minorenne");
}

let orario = 9;

if (orario < 12) {
    console.log("Buongiorno!");
} else {
    console.log("Buonasera!");
}

////////////////////////////////////////////////////////////////////////////

let èStudente = false;

if (èStudente) {
    console.log("Sei uno studente");
} else {
    console.log("Non sei uno studente");
}

/////////////////////////////////////////////////////////////////////////////

// Chiaramente si possono anche nestare, ovvero annidare gli if:

let età2 = 21;
let haPatente = false;

if (età2 >= 18) {
    console.log("Sei maggiorenne, ergo se hai la patente puoi guidare;");
    if (haPatente) {
        console.log("E hai anche la patente!");
    } else {
        console.log("Ma non hai la patente...");
    }
} else {
    console.log("Sei minorenne, non puoi né guidare né avere la patente.");
}


///////////////////////////////////////////////////////////////////////////////

// Vediamo anche un esempio con else if:

let età3 = 21;

if (età3 >= 18) {
    console.log("Sei abbastanza grande per accedere a questo sito");
} else if (età3 < 0) {
    console.log("Età non valida");
} else if (età3 > 100) {
    console.log("Sei troppo anziano per utilizzare questo sito");
} else {
    console.log("Sei minorenne, non puoi accedere a questo sito");
}

// ATTENZIONE: SE SETTASSIMO:

età3 = 101; // eseguiremmo il primo ramo if e non quello corretto con età3 > 100,
// quindi qui la logica va rivista:
// dobbiamo assicurarci che i controlli siano nell'ordine corretto:

età3 = 0;

if (età3 < 0) {
    console.log("Età non valida");
} else if (età3 == 0) {
    console.log("Sei appena nato, non puoi accedere a questo sito");
} else if (età3 > 100) {
    console.log("Sei troppo anziano per utilizzare questo sito");
} else if (età3 >= 18) {
    console.log("Sei abbastanza grande per accedere a questo sito");
} else {
    console.log("Sei minorenne, non puoi accedere a questo sito");
}