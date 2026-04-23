// Iniziamo a scrivere il codice JavaScript per il contatore

// La prima cosa da fare è definire delle costanti che "catturino" i valori
// dei bottoni di incremento e decremento, di quello di reset e della label
// che contiene il valore computato:

const incrementButton = document.getElementById("incrementButton");
const resetButton = document.getElementById("resetButton");
const decrementButton = document.getElementById("decrementButton");
const counterLabel = document.getElementById("counterLabel");

// E ovviamente l'inizializzazione a 0 per il contatore
let counter = 0;

// Ora le tre funzioni che usano il metodo onClick dei vari bottoni:

incrementButton.onclick = function() {
    counter++;
    counterLabel.textContent = counter;
};

decrementButton.onclick = function() {
    counter--;
    counterLabel.textContent = counter;
};

resetButton.onclick = function() {
    counter = 0;
    counterLabel.textContent = counter;
};