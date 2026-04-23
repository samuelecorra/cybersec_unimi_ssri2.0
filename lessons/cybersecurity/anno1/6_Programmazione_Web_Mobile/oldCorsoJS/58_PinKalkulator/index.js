/* Facciamo un balzo di qualità: stavolta creeremo una calcolatrice scientifica
che ci permetta finalmente di non dover andare sul play store a scaricare un'app
per fare i calcoli più complessi. Infatti è risaputo che le app di calcolatrici
scientifiche per android sono spesso piene di pubblicità e non sempre offrono
un'esperienza utente ottimale, per non parlare della grafica orrenda che
spesso le caratterizza.
*/

const display = document.getElementById("display");

function appendToDisplay(input) {
    display.value += input;
}

function calcola() {
    try {
        display.value = eval(display.value);
    } catch (error) {
        display.value = "Errore: " + error.message;
    }
}

function AllClear() {
    display.value = "";
}
    