// Riproponiamo quanto appena detto in versione web-app minimalista:

const inserisciEtà = document.getElementById("inserisciEtà");
const bottoneInvio = document.getElementById("bottoneInvio");
const risultato = document.getElementById("risultato");
let età;

bottoneInvio.onclick = function() {

    età = Number(inserisciEtà.value);
    if (età < 0) {
        risultato.textContent = `Età non valida`;
    } 
    else if (età == 0) {
        risultato.textContent = `Sei appena nato, non puoi accedere a questo sito`;
    } 
    else if (età > 100) {
        risultato.textContent = `Sei troppo anziano per utilizzare questo sito`;
    } 
    else if (età >= 18) {
        risultato.textContent = `Sei abbastanza grande per accedere a questo sito`;
    } 
    else {
        risultato.textContent = `Sei minorenne, non puoi accedere a questo sito`;
    }
}