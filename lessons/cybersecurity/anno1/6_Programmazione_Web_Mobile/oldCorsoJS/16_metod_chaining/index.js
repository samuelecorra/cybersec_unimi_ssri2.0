/* Il metod chaining, in italiano concatenazione di metodi, è una pratica
che consiste nel chiamare un metodo dopo l'altro sulla stessa istanza
di un oggetto e quindi sulla medesima linea di codice. */

// PRIMO ESEMPIO SENZA METOD CHAINING:

let username = window.prompt("Inserisci il tuo nome utente:");
username = username.trim(); // per eliminare ipotetici spazi prima e dopo

let primaLettera = username.charAt(0);
primaLettera = primaLettera.toUpperCase();

let extraChars = username.slice(1);
extraChars = extraChars.toLowerCase();

let usernameFinale = primaLettera + extraChars;
console.log("Il tuo nome utente è: " + usernameFinale);

// PRIMO ESEMPIO CON METOD CHAINING:

username = username.trim().charAt(0).toUpperCase() + 
            username.trim().slice(1).toLowerCase();
console.log("Il tuo nome utente è: " + username);
// Possiamo anche scrivere su più righe, ma è un'unica istruzione!!
// COME LA COMBO DI UN VIDEOGIOCO, MA SE TROPPO LUNGO DIVENTA DIFFICILE DA LEGGERE!
