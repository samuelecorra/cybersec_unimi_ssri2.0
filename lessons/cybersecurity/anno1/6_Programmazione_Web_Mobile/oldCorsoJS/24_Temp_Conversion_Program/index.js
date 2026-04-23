// Iniziamo a dichiarare tutte le consts che ci serviranno:

const tempInput = document.getElementById("tempInput");
const toFahrenheit = document.getElementById("toFahrenheit");
const toCelsius = document.getElementById("toCelsius");
const risultato = document.getElementById("risultato");
let temp;

function convertiTemperatura() {
    

    if (toFahrenheit.checked) {
        temp = Number(tempInput.value);
        let copiaTemp = temp;
        temp = (temp * 9/5) + 32;
        risultato.textContent = `${copiaTemp.toFixed(2)}°C corrispondono a ${temp.toFixed(2)}°F`;
    } 
    else if (toCelsius.checked) {
        temp = Number(tempInput.value);
        let copiaTemp = temp;
        temp = (temp - 32) * 5/9;
        risultato.textContent = `${copiaTemp.toFixed(2)}°F corrispondono a ${temp.toFixed(2)}°C`;
    } else {
        risultato.textContent = "Seleziona un'unità di misura."; /* Ovvero
        non facciamo nulla per invogliare l'utente a riprovare */
    }
}
