// Altri eventi "diagnosticabili" dal Listener sono
// 1. keydown, ovvero quando un tasto viene premuto
// 2. keyup, ovvero quando un tasto viene rilasciato
// 3. keypress (non compatibile con tutti i web browser e quindi sconsigliato)

document.addEventListener("keydown", (event) => {
    console.log(event);
    console.log(`Hai premuto il tasto: ${event.key}`);
});

document.addEventListener("keyup", (event) => {
    console.log(event);
    console.log(`Hai rilasciato il tasto: ${event.key}`);
});

// Nella prossima parte della lezione l'obiettivo sarà quello
// di cambiare un elemento html in base a pressioni e rilasci
// di un tasto!