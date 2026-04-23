/* Lo String Slicing è una pratica che ci consente di estrarre porzioni di una 
stringa in modo semplice e intuitivo. 

In JavaScript, possiamo utilizzare il metodo
string.slice(inizio, fine) per ottenere una sottostringa che va dall'indice
'inizio' (che è incluso) all'indice 'fine' (escluso). 


////////////////////////////////////////////////////////////////////////////////


Vediamo alcuni esempi pratici: */
let username = "CyberGenius";

// Ricordiamo che gli indici sono:
    // C y b e r G e n i u s
    // 0 1 2 3 4 5 6 7 8 9 10

// Estraiamo la parola "JavaScript" utilizzando slice:
let primoNome = username.slice(0, 7);
console.log("La parola estratta è: " + primoNome);

// Possiamo anche omettere il secondo parametro per estrarre fino alla fine della stringa:
let secondoNome = username.slice(7);
console.log("La parola estratta è: " + secondoNome);

////////////////////////////////////////////////////////////////////////////////

// Infine, possiamo utilizzare valori negativi per contare 
// dalla fine della stringa:

// string.slice(start, end) con
// start e end se negativi  che "contano" dalla fine della stringa.
// 0 significa sempre primo carattere, -1 significa “l’ultimo carattere”, 
// -2 il penultimo, ecc.

// anche qui end è escluso:
let parola3 = username.slice(0, -4); // ottengo CyberGe, cioè taglio dal primo al
// quart'ultimo escluso
console.log("La parola estratta è: " + parola3);

let parola4 = username.slice(-4); // ottengo nius, cioè taglio gli ultimi 
// 4 caratteri.
console.log("La parola estratta è: " + parola4);

let parola5 = username.slice(-4, -1); // ottengo niu, cioè taglio gli ultimi 
// 4 caratteri escluso l'ultimo
console.log("La parola estratta è: " + parola5);

////////////////////////////////////////////////////////////////////////////////

// Ovviamente con lo string slicing si può simulare l'estrazione di primo e ultimo
// carattere di una stringa:

let primoCarattere = username.slice(0, 1);
console.log("Il primo carattere è: " + primoCarattere);

let ultimoCarattere = username.slice(-1);
console.log("L'ultimo carattere è: " + ultimoCarattere);

////////////////////////////////////////////////////////////////////////////////

// Si noti che possiamo anche passare il risultato di un metodo come parametro
// per generalizzare la ricerca della fine di Cyber...

let primaParteUsername = username.slice(0, username.indexOf("G"));
console.log("La prima parte del nome utente è: " + primaParteUsername);

// Analogamente:
let secondaParteUsername = username.slice(username.indexOf("G"));
console.log("La seconda parte del nome utente è: " + secondaParteUsername);


////////////////////////////////////////////////////////////////////////////////


// ESERCIZIO: stavolta usiamo un'email, ergo dovremo lavorare con la chiocciola.

const email = "prodigyspellcaster@gmail.com";

// Estraiamo la parte prima della chiocciola:
const nomeUtente = email.slice(0, email.indexOf("@"));
console.log("Il nome utente è: " + nomeUtente);

// Estraiamo la parte dopo la chiocciola:
const dominio = email.slice(email.indexOf("@") + 1);
console.log("Il dominio è: " + dominio);

// ATTENZIONE ATTENZIONE // ATTENZIONE ATTENZIONE:

// LO STRING SLICING NON MODIFICA L'ORIGINALE:
console.log("L'email originale dopo un doppio slice è ancora: " + email);

// PER ORA CI ACCONTENTIAMO DI QUESTE NOZIONI...