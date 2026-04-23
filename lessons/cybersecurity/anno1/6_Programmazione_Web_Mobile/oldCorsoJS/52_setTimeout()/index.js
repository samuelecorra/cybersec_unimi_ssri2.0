/* SetTimeout è una funzione di JavaScript che consente di eseguire
un'operazione dopo un certo intervallo di tempo espresso in ms. 

La sintassi è la seguente:
setTimeout(callback_function, delay);

Dove i parametri:
"callback_function" è la funzione da eseguire e "delay" è il tempo
in millisecondi da attendere prima di eseguire la funzione.

// NOTA BENE:
// I TEMPI IN QUESTO CASO SONO SEMPRE APPROSSIMATI, CIOE' VARIANO IN BASE AL
// COSIDDETTO WORKLOAD DEL JAVASCRIPT RUNTIME ENVIRONMENT: in parole povere,
// i tempi possono essere influenzati da altri script in esecuzione, operazioni
// di I/O e altre attività del browser.

// Dunque diventa sconsigliabile usare setTimeout per operazioni critiche 
che richiedono elevata precisione come uno stopwatch (cronometro). */

////////////////////////////////////////////////////////////////////////////////

// ESEMPIO 1

function dìCiao() {
    console.log("Ciao!");
}

setTimeout(dìCiao, 2000); // Apparirà dopo 2sec

// Funziona anche con le anonymous functions
setTimeout(function() {
    console.log("Ciao da un'anonima funzione!");
}, 4000);

// e anche con le arrow functions
setTimeout(() => {
    console.log("Ciao da un'arrow function!");
}, 6000);


//////////////////////////////////////////////////////////////////////////////

// Per cancellare un setTimeout prima che si triggeri, è necessario introdurre:
// clearTimeout

// SINTASSI:
// clearTimeout(timeoutID); dove timeoutID è l'identificativo restituito da 
// setTimeout:

// ESEMPIO 2:

const identificativo = setTimeout(() => {
    console.log("Questo messaggio non apparirà mai.");
}, 10000);

clearTimeout(identificativo);


////////////////////////////////////////////////////////////////////////////////


// Passiamo alla pagina HTML e aggiungiamo il bottone al cui linkeremo onclick
// la seguente funzione JS:
let timeOutPagina;

function startTimer() {
    timeOutPagina = setTimeout(() => window.alert("Inizia pure!"), 3000);
    console.log("Timer avviato, attendi 3 secondi...");
}

// Creiamo anche un bottone che cancelli il timer
function cancelTimer() {
    clearTimeout(timeOutPagina);
    console.log("Timer cancellato!");
}
