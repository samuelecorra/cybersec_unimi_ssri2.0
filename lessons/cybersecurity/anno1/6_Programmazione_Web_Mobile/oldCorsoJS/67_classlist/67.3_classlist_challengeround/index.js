/////////////////////////////////////////////////////////////////////////////

// Ora creiamo una nodelist per contenere tutti i bottoni della pagina:
// const se non pianifichiamo di aggiungere o rimuovere bottoni,
// let se invece lo vorremmo consentire:

let bottoni = document.querySelectorAll(".mieiBottoni");

bottoni.forEach((bottone) => {
    bottone.classList.add("attivato");
}); // tutto in un colpo solo!

// Ora vogliamo anche l'hover già progettato prima:
bottoni.forEach((bottone) => {
    bottone.addEventListener("mouseover", (event) => {
        event.target.classList.toggle("hover");
    });
    bottone.addEventListener("mouseout", (event) => {
        event.target.classList.toggle("hover");
    });
});

// E ora disabilitiamo al click, aggiungendo anche un if che ci consenta
// di arrabbiarci se è già disabilitato apponendo una faccina arrabbiata dopo:

bottoni.forEach((bottone) => {
    bottone.addEventListener("click", (event) => {

        if (event.target.classList.contains("disabilitato")) {
            event.target.textContent += "🤬";
        } else {
            event.target.classList.replace("attivato", "disabilitato");
        }
    });
});