/* Eccoci all'ennesimo mini-progetto. Oggi realizziamo un
gioco dei dadi! */

function lanciaDadi() {
    const numeroDadi = document.getElementById("numeroDadi").value;
    const risultato = document.getElementById("risultato");
    const immaginiDadi = document.getElementById("immaginiDadi");
    const valoriGenerati = [];
    const immaginiGenerate = [];

    for (let i = 0; i < numeroDadi; i++) {

    // dobbiamo generare un random number tra 1 e 6 e stivarlo in variabile:
        const value = Math.floor(Math.random() * 6) + 1;
        valoriGenerati.push(value);
        immaginiGenerate.push(`<img src="facce_dado/faccia${value}.png"
                                alt="Dado con valore ${value}">`);
    }

    risultato.textContent = `Dado: ${valoriGenerati.join(", ")}`;
    immaginiDadi.innerHTML = immaginiGenerate.join(``);
    // in questo caso .innerHTML specifica che vogliamo inserire del contenuto
    // HTML all'interno dell'elemento, mentre finora avevamo usato solo
    // .textContent che inserisce solo testo. Quindi anche innerHTML può aggiungere
    // testo, ma per migliore leggibilità di solito si scindono i due casi!
    // .innerHTML è più generico e adatto quando il contenuto da inserire
    // è più complesso del solo testo!
}