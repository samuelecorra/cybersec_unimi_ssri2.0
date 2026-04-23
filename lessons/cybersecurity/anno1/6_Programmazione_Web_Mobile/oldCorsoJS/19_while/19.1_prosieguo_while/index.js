// Ora procediamo e complichiamoci un po' le cose...

let loggato = false;
let username;
let password;

while (!loggato) {
    username = window.prompt(`Inserisci il tuo nome:`);
    password = window.prompt(`Inserisci la tua password:`);

    if (username === `admin` && password === `1234`) {
        loggato = true;
    } else {
        alert(`Credenziali errate. Riprova.`);
    }
}

console.log(`Benvenuto ${username}, sei loggato!`);


// Potremmo anche sostituire il ciclo con un do...while loop, ma in quel caso
// la differenza risiede nel fatto che essendo la condizione valutata alla fine
// della prima iterazione, potremmo anche settare innanzitutto loggato = true
// e ci farebbe comunque reinserire le credenziali, il che è un po' illogico...

