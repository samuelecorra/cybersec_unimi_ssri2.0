/* Il ciclo while ripete del codice MENTRE una condizione è vera (PRECONDIZIONATO!!!)
   Se la condizione è falsa all'inizio, il codice all'interno del ciclo non verrà 
   mai eseguito.
   La condizione viene controllata prima di ogni iterazione del ciclo. */

// OGNI CICLO WHILE SCRITTO BENE HA UN CORPO CHE GARANTISCE UNA CONDIZIONE DI 
// USCITA, ALTRIMENTI SI INCAPPA IN UN CICLO INFINITO!!!

// Vediamo un rapidissimo esempio:

let username = "";

while (username === "") {
    username = window.prompt("Inserisci il tuo nome:");
}

console.log(`Benvenuto CyberGenietto, aka ${username}!`);

// ma attenzione, in questo caso se premessi cancel sulla finestra aperta,
// potrei uscire dal ciclo con username = null, e noi non lo vogliamo!

while (username === null || username === "") {
    username = window.prompt("Inserisci il tuo nome:");
}

console.log(`Benvenuto di nuovo CyberGenietto, aka ${username}!`);


/////////////////////////////////////////////////////////////////////////////////


// Se invece volessimo un ciclo POST-CONDIZIONATO, ovvero in cui il corpo
// del ciclo si esegue almeno una volta e poi si valuta la condizione, possiamo
// utilizzare il ciclo do...while:

let username2;
do {
    username2 = window.prompt("Inserisci il tuo nome:");
} while (username2 === null || username2 === "");

console.log(`Benvenuto per la terza volta CyberGenietto, aka ${username2}!`);

// Si vada alla seconda parte della lesson...