// Impostiamo il funzionamento del cronometro:

const display = document.getElementById("displayCronometro");

let timer = null; // No value
let startTime = 0;
let elapsedTime = 0;
let isRunning = false;

function startCronometro() {
    if (!isRunning) {
        startTime = Date.now() - elapsedTime; // Per riprendere da dove si era interrotto
        timer = setInterval(aggiornaDisplay, 10); // Aggiorna ogni 10 ms
        isRunning = true;
    }
}

function aggiornaDisplay() {
    if (isRunning) {
        
        const tempoAttuale = Date.now();
        elapsedTime = tempoAttuale - startTime;

        let ore = Math.floor(elapsedTime / 3600000);
        let minuti = Math.floor((elapsedTime % 3600000) / 60000);
        let secondi = Math.floor((elapsedTime % 60000) / 1000);

        // Attualmente (2025) i millisecondi vengono calcolati con tre
        // cifre di precisione da javascript, riduciamoli a 2 per allinearli
        // agli altri valori:
        let millisecondi = Math.floor(elapsedTime % 1000 / 10); // Da 0 a 99

        // Convertiamo in stringhe per assicurarci il padding di zeri:

        ore = ore.toString().padStart(2, "0");
        minuti = minuti.toString().padStart(2, "0");
        secondi = secondi.toString().padStart(2, "0");
        millisecondi = millisecondi.toString().padStart(2, "0");

        display.textContent = `${ore}:${minuti}:${secondi}:${millisecondi}`;
    }
}

function stopCronometro() {
    if (isRunning) {
        clearInterval(timer);
        elapsedTime = Date.now() - startTime;
        isRunning = false;
    }
}

function resetCronometro() {
    clearInterval(timer);
    startTime = 0;
    elapsedTime = 0;
    isRunning = false;
    display.textContent = "00:00:00:00";
}