/* Ogni elemento HTML può avere una o più classi CSS, che usi per gestire lo
stile grafico o certe proprietà del tuo sito (colori, layout, animazioni).
In JavaScript, la proprietà classList ti permette di accedere e manipolare 
direttamente queste classi senza dover riscrivere a mano l’attributo class 
nel tag.

Perché è utile?

Ti consente di aggiungere, rimuovere o sostituire classi in modo dinamico, 
cioè mentre l’utente interagisce con la pagina.

Rende super facile riutilizzare regole CSS: scrivi lo stile una volta, poi 
decidi con JavaScript quando applicarlo e quando toglierlo.

È un modo pulito per separare logica (JS) e stile (CSS), senza mischiare 
troppi dettagli.

////////////////////////////////////////////////////////////////////////////

Metodi principali di classList

1. add("nomeClasse") → aggiunge una classe all’elemento.
Es: box.classList.add("active")

2. remove("nomeClasse") → elimina una classe esistente.
Es: box.classList.remove("hidden")

3. toggle("nomeClasse") → se la classe c’è la toglie, se non c’è la aggiunge.
Es: menu.classList.toggle("open")

4. replace("vecchiaClasse", "nuovaClasse") → sostituisce una classe con un’altra.
Es: btn.classList.replace("red", "green")

5. contains("nomeClasse") → restituisce true o false a seconda che l’elemento abbia quella classe.
Es: if (box.classList.contains("error")) { … }

/////////////////////////////////////////////////////////////////////////////

Perché è importante nel tuo progetto

Quando costruisci un progettone interattivo, la grafica non è mai statica. 
Devi cambiare colori, animazioni, stati attivi/inattivi a seconda di cosa fa 
l’utente (clic, hover, input…).
Con classList hai il telecomando diretto sulle classi CSS, senza dover 
rimaneggiare manualmente lo stile inline.
È come avere un set di interruttori: clicchi un bottone e cambi 
completamente il comportamento visivo di un elemento. */


////////////////////////////////////////////////////////////////////////////

// Nell'HTML abbiamo un bottone con id "bottonePrincipessa".
// Vogliamo che quando è attivo, gli venga aggiunta la classe css "attivato".

const bottonePrincipessa = document.getElementById("bottonePrincipessa");

bottonePrincipessa.classList.add("attivato"); // aggiungiamo dinamicamente!

// Se infatti ispezioniamo il bottone nel browser:
// <button id="bottonePrincipessa" class="attivato">Bottone di Principessa</button>

// PERFETTO! ☑️

let vuoiRimuovere = false;

if (vuoiRimuovere) {
    bottonePrincipessa.classList.remove("attivato");
}

// Aggiungiamo la classe .hover:

bottonePrincipessa.addEventListener("mouseover", (event) => {
    event.target.classList.add("hover");
}); // stiamo SIMULANDO LA PSEUDOCLASSE AGGIUNGENDONE UNA VERA DINAMICAMENTE!

// basta cambiare un minimo le cose per impostare la duale con mouseout:
bottonePrincipessa.addEventListener("mouseout", (event) => {
    event.target.classList.remove("hover");
});
// Simulazione riuscita con successo! ☑️