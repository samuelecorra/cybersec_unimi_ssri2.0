
let bottoni = document.querySelectorAll(".mieiBottoni");

// Ora proviamo ad aggiungere un elemento alla nodelist:

let nuovoBottone = document.createElement("button");
nuovoBottone.textContent = "Nuovo Bottone!";

// ORA FACCIAMO DUE COSE SEPARATE!
nuovoBottone.classList.add("mieiBottoni");
// Che è l'equivalente di:
// nuovoBottone.classList = "mieiBottoni";  
// TRA L'ALTRO, FORNIRLO DI UNA CLASSLIST PERMETTE UNO STILE
// IMMEDIATAMENTE COERENTE CON GLI ALTRI BOTTONI! - VEDREMO
// IN FUTURO CHE CLASSLIST E' UNA RISORSA POTENTE...

// e...
document.body.appendChild(nuovoBottone);    // appendiamo al documento HTML

// Verifichiamo se la nodelist si aggiorna automaticamente:
console.log(bottoni);
// NO, ABBIAMO ANCORA 4 BOTTONI IN NODELIST

// AGGIORNARE LA NODELIST ->
// VA FATTO MANUALMENTE: ci torna utile querySelector:

bottoni = document.querySelectorAll(".mieiBottoni");
// Ora è stata aggiornata, scommettiamo??

console.log(bottoni);

bottoni.forEach((bottone) => {
    console.log(bottone)
}); // Il for each ne stampa 5, ergo CVD - come volevasi dimostrare!

