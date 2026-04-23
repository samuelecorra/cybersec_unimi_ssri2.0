// Stavolta impariamo come mostrare e nasconder elementi HTML
// usando JS.

// Avremo bisogno di un'immagine con la quale lavorare.

const bottone = document.getElementById("bottone");
const trevor = document.getElementById("trevor");

bottone.addEventListener("click", function() {
    if (trevor.style.visibility === "hidden") {    // se è nascosto
        trevor.style.visibility = "visible";   // lo forziamo a block
        bottone.textContent = "Hide"; // e cambiamo il button
    } else {
        trevor.style.visibility = "hidden";    // il contrario.
        bottone.textContent = "Show";
    }
});

// CON VISIBILITY SI PRESERVA LO SPAZIO PER L'IMMAGINE ANCHE
// QUANDO LA SI NASCONDE COSI' DA NON FAR RIMBALZARE SOPRA IL
// BUTTON, MENTRE CON DISPLAY: none / block NON SI PRESERVA
// SPAZIO. ERGO, IN BASE AL PROGETTO CHE SI VUOLE REALIZZARE,
// SCEGLIERE IN BASE ALLE ESIGENZE!