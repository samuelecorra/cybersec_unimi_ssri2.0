/* Concretizziamo quanto detto nella scorsa lezione in un'applicazione web molto
minimalista:

*/

const bottoneGeneratore = document.getElementById("bottoneGeneratore");
const risultato = document.getElementById("risultato");

// Ora replichiamo l'algoritmo di poc'anzi:
const min = 1;
const max = 100;
let randomNum;

bottoneGeneratore.onclick = function() {
    randomNum = Math.floor(Math.random() * (max - min + 1)) + min;
    risultato.textContent = randomNum;
};
