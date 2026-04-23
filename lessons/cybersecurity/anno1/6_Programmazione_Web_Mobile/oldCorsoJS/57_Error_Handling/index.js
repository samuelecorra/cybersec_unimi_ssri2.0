/* Javascript - PER FORTUNA - mette a disposizione strumenti per i programmatori
che aiutano a gestire tutti i possibili errori che possono verificarsi durante
l'esecuzione del codice.

try {
    // codice che potrebbe generare un errore
} catch (error) {
    // gestione dell'errore
}
finally {
    // codice da eseguire sempre, che ci sia stato un errore o meno
}
  // console.log("fine del programma"); // Per simulare visivamente exit

Ad esempio, se scrivessimo un programmino monoriga:
console.log("Ciao Mondo");
console.log("Fine del Programma");

In console apparirebbero entrambi i messaggi, ergo zero errori incontrati
durante l'esecuzione del codice.

MA...

// ESEMPIO 1:

console.lag("Hello"); 
console.log("Fine del Programma");

// Avendo scritto male il comando di log come "lag", ci apparirebbe:
index.js:26 Uncaught TypeError: console.lag is not a function
    at index.js:26:9

// E il codice si interrompe senza farci vedere il messaggio di fine.

// ESEMPIO 2:

console.log(x);
console.log("Fine del Programma");

// RISULTEREBBE UN ERRORE POICHE' NON ABBIAMO DEFINITO PRIMA x
index.js:27 Uncaught ReferenceError: x is not defined
    at index.js:27:13

// IL CODICE SI INTERROMPE ALL'INCONTRO DELL'ERRORE E NON VISUALIZZEREMMO MAI
// L'USCITA "Fine del Programma".


/////////////////////////////////////////////////////////////////////////////////


// NON SOLO: GLI ERRORI POSSONO DERIVARE ANCHE DA:

// 1. NETWORK ERRORS (es. problemi di connessione);
// 2. PROMISE REJECTIONS (es. errori nelle chiamate API);
// 3. SECURITY ERRORS (es. problemi di autorizzazione);

// Se stavolta invece ci premuriamo di includere
// i vari blocchi appositi: */

try{
  console.log(x);
}
catch(error){
  console.error("Si è verificato un errore:", error);
}
// Si noti che console.error EVIDENZIA GLI ERRORI IN MODO CHIARO E DISTINTIVO,
// IN ROSSO!
finally {
  // Questo blocco di solito è usato per chiudere file, chiudere connessioni,
  // rilasciare risorse, ecc. insomma tutte quelle operazioni di cleanup e
  // di ripristino che potrebbero essere necessarie per il corretto funzionamento
  // dell'applicazione.
  console.log("Errore gestito, chiudo file, connessioni e rilascio risorse!");
}

console.log("Fine del Programma");

// In console stavolta vedremo la fine del programma, preceduta da un errore
// che stavolta NON E' PIU' UNCAUGHT, BENSI' E' STATO CATTURATO/GESTITO:
// index.js:64 Si è verificato un errore: ReferenceError: x is not defined
// at index.js:61:15

////////////////////////////////////////////////////////////////////////////////

// Inutile dire che in assenza di errori tutti i blocchi vengono eseguiti
// come se fossero sequenziali normalissime istruzioni!

////////////////////////////////////////////////////////////////////////////////

// ATTENZIONE: GLI ERRORI SALTANO FUORI SPESSISSIMO A CAUSA DELL'USER INPUT.
// L'USER CHE NON E' L'UTENTE SBADATO CHE SBAGLIA USERNAME, BENSI' UN HACKER
// CHE VUOLE SFRUTTARE LE VULNERABILITA' DEL SISTEMA PER INPUTTARE UN CODICE
// MALEVOLO, E' LA CAUSA PRINCIPALE DI ERRORI E BUG!

const dividendo = window.prompt("Inserisci il dividendo:");
const divisore = window.prompt("Inserisci il divisore:");

const risultato = Number(dividendo) / Number(divisore);

console.log("Il risultato della divisione è: " + risultato);
console.log("Con divisore = 0, otterremmo infinity, ma non lo vogliamo!");

// Si noti che JavaScript DI BASE consente anche la divisione per 0,
// restituendo Infinity o -Infinity a seconda del segno del dividendo.
// Questo in molti scenari potrebbe non andarci affatto bene, quindi
// dobbiamo gestire esplicitamente la divisione per zero.

// Ma allora bisogna:
// 1. Risalire a monte e racchiudere la zona pericolosa in un try
// 2. Generare volontariamente l'errore della divisione per 0 con un apposito
// controllo if:

// throw è l’istruzione che interrompe immediatamente il flusso normale del 
// programma e lancia un’eccezione. 
// Il motore JS smette di eseguire la funzione corrente e “risale lo stack” 
// finché trova un blocco try { ... } catch (e) { ... } che la gestisce;
// se non lo trova, l’errore è uncaught (in browser: in console; in Node:
// fa terminare il processo se non gestito).

// Perché new Error(...)?
// Tecnicamente puoi “lanciare” qualunque valore,
// ma è buona pratica lanciare istanze di Error (o sue sottoclassi) 
// perché includono:

// message: testo leggibile,

// name: tipo (es. Error, TypeError…),

// stack: traccia di chiamate utilissima per il debug,

// cause (opzionale): per incapsulare l’errore originale new Error(msg, { cause: err }).

try {
  if (divisore == 0) {
    // La keyword throw ci consente di generare un errore personalizzato:
    throw new Error("Divisione per zero non consentita.");
  }
// Ma anche inserendo una stringa di caratteri non numerica vogliamo ottenere
// un chiaro errore, e non un semplice NaN (Not a Number), e questo vale sia
// per il dividendo che per il divisore, ergo ci serve un or, che poniamo dopo
// il check dello 0:

  if (isNaN(dividendo) || isNaN(divisore)) {
    throw new Error("Input inserito non numerico, rimbecillito!");
  }
// Solo superando entrambi gli if nel ramo di fallback (né 0 né NaN) arriviamo
// ad avere una divisione valida:
  console.log("Il risultato della divisione è: " + risultato);
} catch (error) {
  console.error(error);
} finally {
  console.log("End of Program!");
}


