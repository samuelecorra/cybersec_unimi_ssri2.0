
let bottoni = document.querySelectorAll(".mieiBottoni");

// Ora vediamo come rimuovere un elemento dalla nodelist e dal
// DOM, ad esempio quando ci clicchiamo sopra:

bottoni.forEach((bottone) => {
    bottone.addEventListener("click", (event) => {
        event.target.remove(); // MA QUI LA NODELIST NE CONTIENE ANCORA 4
        // AGGIUNGiamo L'UPDATE CON QUERYSELECTORALL:
        bottoni = document.querySelectorAll(".mieiBottoni");
        console.log(bottoni); // E stampiamo la nodelist aggiornata a ogni
        // click di rimozione!
    });
});
