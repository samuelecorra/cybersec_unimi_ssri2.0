
// Rivediamo velocemente la buona prassi vista nella scorsa lezione 6:

const PI = 3.14159;
let raggio;
let circ;

document.getElementById("CalcolaButton").onclick = function() {
    raggio = document.getElementById("RaggioInput").value;
    
    // Ecco che torna subito utile - e tassativo - convertire a Number
    raggio = Number(raggio);
    
    circ = 2 * PI * raggio;
    // E stampiamo il risultato:
    console.log("La circonferenza del cerchio di raggio " + raggio +
                " inserito è: " + circ);
    // Vogliamo anche vederlo nella pagina, non solo in console:
    // Aggiungiamo un h3 che funga da risposta e inizializziamolo:
    document.getElementById("Risultato").textContent = "La circonferenza del cerchio di raggio " + raggio +
        " inserito è: " + circ + "cm";
}