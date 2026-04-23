/* I metodi peculiari delle stringhe ci permettono di manipolare ""comodamente""
porzioni di testo. Vediamo alcuni dei metodi più comuni:
*/

let username = "CyberGenius";

// Il primo metodo, charAt(indice), restituisce il carattere in una posizione
// specificata dal programmatore - va dunque assegnato a una variabile:

let firstChar = username.charAt(0);
console.log("Il primo carattere del tuo username " + username + " è: " + firstChar);

// ovviamente si ricordi che l'indicizzazione parte sempre da 0 e non da 1!

// Il secondo metodo che vediamo è indexOf(carattere), che restituisce
// la posizione della prima occorrenza di un carattere specificato:

let primaOccorrenzaDiY = username.indexOf("y");
console.log("La lettera 'y' appare per la prima volta in " + 
                username + " all'indice: " + primaOccorrenzaDiY);

// Analogamente, il metodo lastIndexOf(carattere) restituisce
// la posizione dell'ultima occorrenza di un carattere specificato:

let ultimaOccorrenzaDiS = username.lastIndexOf("S");
console.log("La lettera 'S' appare per l'ultima volta in " + 
                username + " all'indice: " + ultimaOccorrenzaDiS);

// Inoltre - NON E' UN METODO BENSI' UNA PRORPIETA' - si può accedere a
// username.length per ottenere la lunghezza della stringa:

let lunghezzaUsername = username.length;
console.log("La lunghezza del tuo username " + username + " è: " + lunghezzaUsername);

// Si noti che in JavaScript non viene conteggiato il terminatore di stringa nullo!


/////////////////////////////////////////////////////////////////////////////////


// METODI PIU' AVANZATI:

// con il metodo string.trim() si possono rimuovere gli spazi bianchi
// all'inizio e alla fine di una stringa:

let usernameConSpazi = "   CyberGenius   ";
let usernameSenzaSpazi = usernameConSpazi.trim();
console.log("Il tuo username con spazi è: '" + usernameConSpazi + 
                "' e senza spazi è: '" + usernameSenzaSpazi + "'");

/////////////////////////////////////////////////////////////////////////////////

// con il metodo string.toUpperCase() si converte una stringa in TUTTO maiuscolo:

let usernameMaiuscolo = username.toUpperCase();
console.log("Il tuo username in maiuscolo è: '" + usernameMaiuscolo + "'");

// In maniera speculare, con il metodo string.toLowerCase() si converte una ù
// stringa in TUTTO minuscolo:

let usernameMinuscolo = username.toLowerCase();
console.log("Il tuo username in minuscolo è: '" + usernameMinuscolo + "'");

/////////////////////////////////////////////////////////////////////////////////

// Il metodo string.repeat() restituisce una nuova stringa che consiste nella
// ripetizione del testo originale un certo numero di volte:

let usernameRipetuto = username.repeat(3);
console.log("Il tuo username ripetuto 3 volte è: '" + usernameRipetuto + "'");

// Il metodo string.startsWith() verifica se una stringa inizia con un certo 
// prefisso:

let iniziaConSpazio = username.startsWith(" "); // no
console.log("Il tuo username inizia con ' '? " + iniziaConSpazio);

    if (iniziaConSpazio) {
        console.log("Il tuo username non può iniziare con uno spazio!");
    } else {
        console.log("Il tuo username è valido: " + username);
    }

// Diventa appunto un metodo utile in prossimità di selezioni con ifs.

// L'analogo è il metodo string.endsWith(), che verifica se una stringa termina
// con un certo suffisso:

let finisceConSpazio = username.endsWith(" "); // no
console.log("Il tuo username finisce con ' '? " + finisceConSpazio);

    if (finisceConSpazio) {
        console.log("Il tuo username non può finire con uno spazio!");
    } else {
        console.log("Il tuo username è valido: " + username);
    }

//////////////////////////////////////////////////////////////////////////////

// Se volessimo essere più stringenti, c'è il metodo string.includes, che
// verifica se una stringa contiene un certo testo:

let contieneSpazio = username.includes(" "); // no
console.log("Il tuo username contiene ' '? " + contieneSpazio);

    if (contieneSpazio) {
        console.log("Il tuo username non può contenere uno spazio!");
    } else {
        console.log("Il tuo username è valido: " + username);
    }


///////////////////////////////////////////////////////////////////////////////


// Esempi più complessi:

let numeroDiTel = "123-456-7890";
    
    numeroDiTel = numeroDiTel.replaceAll("-", "/");

    // Così facendo eliminiamo i trattini e mettiamo delle barre:
    console.log("Numero di telefono senza trattini e con barre: " + numeroDiTel);

    numeroDiTel = numeroDiTel.replaceAll("/", "");

    // Così facendo eliminiamo le barre:
    console.log("Numero di telefono senza barre: " + numeroDiTel);

////////////////////////////////////////////////////////////////////////////////

// Se abbiamo invece un valore binario e vogliamo estenderlo a 16bit per comodità,
// sappiamo che bisogna anteporre degli zeri, e questo si fa con un metodo
// specifico:

// string.padStart(numero_totale_caratteri, carattere_di_prelungamento):

let valoreBinario = "101";
valoreBinario = valoreBinario.padStart(16, "0");
console.log("Valore binario esteso a 16 bit: " + valoreBinario);

// Se analogamente volessimo postporre un certo numero di valori alla fine della
// stringa, possiamo utilizzare il metodo:
// string.padEnd(numero_totale_caratteri, carattere_di_prolungamento):

// Generalmente, nella programmazione web e mobile padEnd torna utile per 
// allineare il testo all'interno di un contenitore di dimensioni fisse.

let prodotto1 = "Pane";
let prezzo1 = "2€";
let prodotto2 = "Latte";
let prezzo2 = "1.5€";

console.log(prodotto1.padEnd(10, " ") + prezzo1.padStart(10, " "));
console.log(prodotto2.padEnd(10, " ") + prezzo2.padStart(10, " "));
// "Pane      2€"
// "Latte     1.5€"
