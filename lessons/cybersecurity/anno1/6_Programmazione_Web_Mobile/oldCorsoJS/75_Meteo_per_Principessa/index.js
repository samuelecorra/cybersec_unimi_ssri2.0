// Weather App:

// Inizializziamo i nostri riferimenti:
const formMeteo = document.querySelector(".formMeteo");
const inputCitta = document.querySelector(".inputCitta");

const card = document.querySelector(".card");

// Ora usiamo l'API key fornitaci dal sito:
const API_KEY = "1bcb5b7041cb394939b48f326c85bb64";

formMeteo.addEventListener("submit", async (event) => {
    // ATTENZIONE: i form possiedono un comportamento di default, ovvero
    // nel momento in cui vengono inviati, la pagina si ricarica.
    // Per evitare questo comportamento, dobbiamo "annullare" l'evento.
    event.preventDefault();
    const citta = inputCitta.value;

    if (citta) { // basta che ci sia un valore digiato per procedere.
        
        try {
            const datiMeteo = await fetchWeatherData(citta);
            console.log(datiMeteo);
            mostraDatiMeteo(datiMeteo);
            }

        catch (error) {
            mostraErrore("Si è verificato un errore durante il recupero dei dati meteo.");
            }

    } else {
        mostraErrore("Inserisci una città, birichina... 😛");
    }
});

// Iniziamo a definire le funzioni che invocheremo:

async function fetchWeatherData(citta) {

    const apiURL = `https://api.openweathermap.org/data/2.5/weather?q=${citta}&appid=${API_KEY}&units=metric`;
    const response = await fetch(apiURL);
    
    if (!response.ok) {
        throw new Error("La risposta del server non è andata a buon fine!");
    }

    return await response.json(); // ci facciamo ritornare un oggetto
    // in un formato semi-leggibile!
}


function mostraDatiMeteo(data) {

    const { name: city, 
            main: {temp, humidity}, 
            weather: [{description, id, icon}] } = data;
        // Vogliamo infine l'info che determina se in quella determinata
        // città è giorno o notte: per farlo, il modo più semplice è
        // passarci anche l'icona, che termina o con d o con n, e poi
        // controlliamo:
    const èGiorno = icon.endsWith("d");

    card.textContent = "";
    card.style.display = "flex";

    const displayCitta = document.createElement("h2");
    displayCitta.textContent = city;
    displayCitta.classList.add("displayCitta");
    card.appendChild(displayCitta);

    const displayTemperatura = document.createElement("p");
    displayTemperatura.textContent = `Temperatura: ${temp.toFixed(1)}°C`;
    displayTemperatura.classList.add("displayTemperatura");
    card.appendChild(displayTemperatura);

    const displayUmidita = document.createElement("p");
    displayUmidita.textContent = `Umidità: ${humidity}%`;
    displayUmidita.classList.add("displayUmidita");
    card.appendChild(displayUmidita);

    const displayDescrizione = document.createElement("p");
    displayDescrizione.textContent = `Condizioni: ${traduciDescrizione(description)}`;
    displayDescrizione.classList.add("displayDescrizione");
    card.appendChild(displayDescrizione);

    const displayIcona = document.createElement("img");
    displayIcona.src = scegliIconaMeteo(id, èGiorno);
    displayIcona.alt = description;
    displayIcona.classList.add("displayIcona");
    card.appendChild(displayIcona);

}

function traduciDescrizione(description) {
    const traduzioni = {
        "clear sky": "cielo sereno",
        "few clouds": "qualche nuvoletta",
        "scattered clouds": "nuvole sparse",
        "broken clouds": "nuvole frastagliate",
        "overcast clouds": "nuvole compatte",
        "shower rain": "pioggia leggera",
        "light rain": "pioggia leggera",
        "rain": "pioggia",
        "thunderstorm": "temporale",
        "snow": "neve",
        "mist": "nebbia"
    };

    return traduzioni[description] || description; // Questa linea ci ritorna la traduzione se esiste, altrimenti il testo originale
}
function scegliIconaMeteo(condizione, èGiorno) {

    // tra 200 e 232 è thunderstorm: l'icona è uguale sia per giorno che notte
    if (condizione >= 200 && condizione <= 232) {
        return "icone/thunderstorm.png";
    }
    // tra 300 e 321 è drizzle/shower rain, con icona sia per giorno che notte:
    if (condizione >= 300 && condizione <= 321) {
        return "icone/showerrain.png";
    }
    // tra 500 e 531 è rain, con icona diversa per giorno e per notte:
    if (condizione >= 500 && condizione <= 531) {
        return èGiorno ? "icone/rainday.png" : "icone/rainnight.png";
    }
    // tra 600 e 622 è neve, icona uguale per giorno e notte:
    if (condizione >= 600 && condizione <= 622) {
        return "icone/snow.png";
    }
    // tra 701 e 781 è atmosphere (nebbia, vento, ecc), icona uguale per giorno e notte:
    if (condizione >= 701 && condizione <= 781) {
        return "icone/foggy.png";
    }
    // se è esattamente 800 è sereno, icona diversa per giorno e notte:
    if (condizione === 800) {
        return èGiorno ? "icone/clearday.png" : "icone/clearnight.png";
    }
    // se è 801 è parzialmente nuvoloso, icona diversa per giorno e notte:
    if (condizione === 801) {
        return èGiorno ? "icone/fewcloudsday.png" : "icone/fewcloudsnight.png";
    }
    // se è 802 sono scattered clouds, icona uguale:
    if (condizione === 802) {
        return "icone/cloudy.png";
    }
    // se è 803 oppure 804 sono broken/overcast clouds, icona uguale:
    if (condizione === 803 || condizione === 804) {
        return "icone/brokenclouds.png";
    }

}

function mostraErrore(messaggio) {

    const displayErrore = document.createElement("p");
    displayErrore.textContent = messaggio;
    displayErrore.classList.add("displayErrore");

    card.textContent = "";
    card.style.display = "flex";
    card.appendChild(displayErrore);
}
