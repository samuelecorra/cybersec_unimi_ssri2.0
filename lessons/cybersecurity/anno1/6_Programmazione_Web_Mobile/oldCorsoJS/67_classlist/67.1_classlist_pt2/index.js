/////////////////////////////////////////////////////////////////////////////

const bottonePrincipessa = document.getElementById("bottonePrincipessa");
bottonePrincipessa.classList.add("attivato"); // aggiungiamo dinamicamente!

// Ora vediamo il toggle, che aggiunge la classe se assente e la rimuove
// se già presente!

// Ma essenzialmente è simile alle proprietà di add e remove:

bottonePrincipessa.addEventListener("mouseover", (event) => {
    event.target.classList.toggle("hover");
});

bottonePrincipessa.addEventListener("mouseout", (event) => {
    event.target.classList.toggle("hover");
});

// Perfetto! ☑️