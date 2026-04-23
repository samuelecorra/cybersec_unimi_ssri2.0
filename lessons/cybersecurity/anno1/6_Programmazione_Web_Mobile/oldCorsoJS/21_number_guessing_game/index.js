/* Questo programmino è un gioiellino: siamo in grado in pochissimo tempo di
creare un gioco col quale possiamo sfidare la fortuna nostra e quella dei nostri
amici!

// NUMBER GUESSING NAME */

const minNum = 1;
const maxNum = 100;

const risposta = Math.floor(Math.random() * (maxNum - minNum + 1)) + minNum;

let attempts = 0;
let guess;
let running = true; // Per un exit control futuro...

// Iniziamo il gioco...

while (running) {

guess = window.prompt(`Sto pensando a un numero tra ${minNum} e ${maxNum}. 
                        Prova a indovinare!`);

// Ora che la nostra variabile guess contiene il valore del prompt, dobbiamo
// capire come valutare tale valore con vari if:

// Innanzitutto, bisogna assicurarsi che l'utente abbia davvero cliccato Ok
// dopo aver inserito un numero.
// Ovvero, in parole povere, escludiamo dal ciclo di running il caso in cui
// l'utente:
// - refresha la pagina
// - chiude il prompt cliccando Annulla

// Si noti che JavaScript in entrambi i casi pone a null la variabile guess.

    if (guess === null) {
        window.alert(`Hai interrotto la finestra precedente, biricchina!`);
        continue // Per uscire dal ciclo forzatamente, altrimenti quel null
        }           // verrebbe convertito in 0 dai successivi confronti < >
    // Ergo, appena l'utente chiude l'alert, gli si ripresenta il prompt iniziale

// Ora, posto che l'utente non abbia chiuso il prompt, dobbiamo assicurarci
// che il valore inserito sia un numero.
// Questo non accade se:
// 1. L'utente clicca direttamente Ok e quindi non digita alcunché, passando
// una stringa vuota;
// 2. L'utente digita solo spazi, che passerebbero come stringa vuota:
// In entrambi i casi questi valori NON VENGONO CONVERTITI A NaN, BENSI' A 0,
// CAUSANDOCI UN ENORME PROBLEMA DI LOGICA. Allora gestiamo il tutto in un colpo
// solo: prima trimmiamo, poi escludiamo la stringa vuota:
    guess = guess.trim();
    if (guess === "") {
        window.alert(`Non hai inserito alcun numero, biricchina!`);
        continue
    }

// 2. Ma anche se l'utente digita qualcosa di non numerico: e questo come lo
// verifichiamo? Ci viene in aiuto una funzione built-in di JS: isNaN()
// Prima di usarla, ovviamente, dobbiamo convertire guess in un numero e
// SOLO DOPO valutare se ci è stato restituito NaN:
    guess = Number(guess);
    if (isNaN(guess)) {
        window.alert(`Non hai inserito un numero valido, biricchina!`);
        continue
    }


// Bene, ora abbiamo escluso tutti i casi di digitazioni non conformi alla
// richiesta numerica. Ma anche una digitazione numerica potrebbe non
// essere consona: se l'utente digita un numero al di fuori dell'intervallo
// nel quale stiamo giocando, dobbiamo gestire anche questo caso:
//   guess = Number(guess); in realtà questa conversione è facoltativa
// perché nella riga qui sotto il < e il > sanno benissimo confrontare
// un numero espresso come stringa e un numero espresso come Number
    if (guess < minNum || guess > maxNum) {
        window.alert(`Il numero deve essere compreso tra ${minNum} e ${maxNum}, biricchina!`);
        continue
    }

    // Se siamo arrivati fin qui, significa che abbiamo un numero valido. Quindi:
    attempts++;

    // E ora ci siamo: bisogna confrontarlo con la risposta randomica generata
    // da Math.random():
    if (guess < risposta) {
        window.alert(`Il numero da indovinare è più grande, biricchina!`);
    } else if (guess > risposta) {
        window.alert(`Il numero da indovinare è più piccolo, biricchina!`);
    } else {
        window.alert(`Complimenti! Hai indovinato il numero ${risposta} in ${attempts} tentativi.`);
        running = false;
    }
}