// Se avessimo invece un array di stringhe, la situa cambia:

let frutta = ["mela", "banana", "kiwi", "arancia", "uva"];

frutta.forEach(function(elemento) {
    console.log("Frutta: " + elemento);
});

// Oppure, analogamente...
function stampaFrutta(elemento) {
    console.log("Frutta: " + elemento);
}

frutta.forEach(stampaFrutta);

function tuttoMAIUSC(elemento) {
    console.log("Frutta: " + elemento.toUpperCase());
}

frutta.forEach(tuttoMAIUSC);

// Idem con:
function tuttoMinuscolo(elemento) {
    console.log("Frutta: " + elemento.toLowerCase());
}

frutta.forEach(tuttoMinuscolo);

// E se volessimo solo la prima maiuscola (= capitalize)?
function capitalizeFirstLetter(elemento) {
    console.log("Frutta: " + elemento.charAt(0).toUpperCase() + elemento.slice(1));
}

frutta.forEach(capitalizeFirstLetter);