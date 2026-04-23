// Vediamo come far muovere la nostra emoji:

const scatola = document.getElementById("scatola");
const tassoDiMovimento = 10;
let x = 0;
let y = 0; // per movimenti orizzontale e verticale!

// prima di procedere implementiamo anche la scorsa sezione di
// lezione per cambiare sfondo ed emoji:

document.addEventListener("keydown", (event) => {
    scatola.textContent = "🫢";
    scatola.style.backgroundColor = "lightcoral";
});

document.addEventListener("keyup", (event) => {
    scatola.textContent = "😶‍🌫️";
    scatola.style.backgroundColor = "aquamarine";
});

///////////////////////////////////////////////////////////////

document.addEventListener("keydown", (event) => {
    if (event.key.startsWith("Arrow")) {

        event.preventDefault(); // Ci evita la scrollbar se eccediamo lo spazio

        switch(event.key) {
            case "ArrowUp":
                y -= tassoDiMovimento;
                break;
            case "ArrowDown":
                y += tassoDiMovimento;
                break;
            case "ArrowLeft":
                x -= tassoDiMovimento;
                break;
            case "ArrowRight":
                x += tassoDiMovimento;
                break;
        }
    scatola.style.top = `${y}px`;
    scatola.style.left = `${x}px`;

    }
});
