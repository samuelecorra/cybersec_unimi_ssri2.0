/////////////////////////////////////////////////////////////////////////////

const bottonePrincipessa = document.getElementById("bottonePrincipessa");
bottonePrincipessa.classList.add("attivato"); // aggiungiamo dinamicamente!

// Ora vediamo il replace, che è autoesplicativo: ho aggiunto al CSS
// la classe disabled, per far vedere chiaramente che torneremo alle
// condizioni iniziali.

// SINTASSI GENERALE:
// PRIMA LA VECCHIA CLASSE, POI LA NUOVA!

// Infine, il contains() ci ritorna un booleano di conferma:

bottonePrincipessa.addEventListener("click", (event) => {

    if(event.target.classList.contains("disabilitato")) {
        event.target.textContent += "🫢"
    }
    else {
        event.target.classList.replace("attivato", "disabilitato");
    }
});

////////////////////////////////////////////////////////////////////////////


// NOTA BENE: IL VANTAGGIO DI classList è che gli elementi HTML hanno
// una proprietà classlist, e quindi possiamo riusare le classi CSS
// per svariati elementi HTML!  

titolo = document.getElementById("titolo");
titolo.classList.add("attivato");

// Ora ricopiamo la parte sopra però per il titolo:

titolo.addEventListener("click", (event) => {

    if(event.target.classList.contains("disabilitato")) {
        event.target.textContent += "🫢"
    }
    else {
        event.target.classList.replace("attivato", "disabilitato");
    }
});