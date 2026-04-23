// Vediamo come far cambiare lo sfondo e l'emoji:

const scatola = document.getElementById("scatola");

document.addEventListener("keydown", (event) => {
    scatola.textContent = "🫢";
    scatola.style.backgroundColor = "lightcoral";
});

document.addEventListener("keyup", (event) => {
    scatola.textContent = "😶‍🌫️";
    scatola.style.backgroundColor = "aquamarine";
});
