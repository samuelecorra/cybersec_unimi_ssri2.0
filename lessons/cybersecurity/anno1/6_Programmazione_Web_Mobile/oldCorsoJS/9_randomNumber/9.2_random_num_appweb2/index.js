/* Concretizziamo quanto detto nella scorsa lezione in un'applicazione web molto
minimalista:

*/

const bottoneGeneratore = document.getElementById("bottoneGeneratore");
const risultato1 = document.getElementById("risultato1");
const risultato2 = document.getElementById("risultato2");
const risultato3 = document.getElementById("risultato3");

// Ora replichiamo l'algoritmo di poc'anzi:
const min = 1;
const max = 100;
let randomNum1, randomNum2, randomNum3;

bottoneGeneratore.onclick = function() {
    
    randomNum1 = Math.floor(Math.random() * (max - min + 1)) + min;
    randomNum2 = Math.floor(Math.random() * (max - min + 1)) + min;
    randomNum3 = Math.floor(Math.random() * (max - min + 1)) + min;
    
    risultato1.textContent = randomNum1;
    risultato2.textContent = randomNum2;
    risultato3.textContent = randomNum3;
};
