// Rock Paper Scissor - Gioco della morra cinese

const scelte = ["sasso", "carta", "forbice"];
const displayGiocatore = document.getElementById("displayGiocatore");
const displayComputer = document.getElementById("displayComputer");
const risultato = document.getElementById("risultato");
const punteggioPrincipessa = document.getElementById("punteggioPrincipessa");
const punteggioComputer = document.getElementById("punteggioComputer");

let punteggioGommarosa = 0;
let punteggioPC = 0;



function gioca(mossaGiocatore) {
    const mossaComputer = scelte[Math.floor(Math.random() * 3)];
    displayGiocatore.textContent = "Principessa ha scelto: " + mossaGiocatore;
    displayComputer.textContent = "Il computer ha scelto: " + mossaComputer;
    let fraseFinale = "";
    
    // Ora però dobbiamo resettare la classe così alla prossima partita non avremo
    // già un colore per l'esito:
    risultato.classList.remove("testoVittoria", "testoSconfitta", "testoPareggio");
    
    if (mossaGiocatore === mossaComputer) {
        fraseFinale = "E' UN PAREGGIO!";
        risultato.classList.add("testoPareggio");
    } else if (
        (mossaGiocatore === "sasso" && mossaComputer === "forbice") ||
        (mossaGiocatore === "carta" && mossaComputer === "sasso") ||
        (mossaGiocatore === "forbice" && mossaComputer === "carta")
    ) {
        fraseFinale = "HAI VINTO!";
        punteggioGommarosa++;
        punteggioPrincipessa.textContent = punteggioGommarosa;
        risultato.classList.add("testoVittoria");
    } else {
        fraseFinale = "HAI PERSO!";
        punteggioPC++;
        punteggioComputer.textContent = punteggioPC;
        risultato.classList.add("testoSconfitta");
    }
    risultato.textContent = fraseFinale;
    
}