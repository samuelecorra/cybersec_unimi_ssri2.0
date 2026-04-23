// Il fetch è una funzione di prelievo alla quale si passa un percorso,
// sia esso assoluto o relativo, e restituisce una Promise.

fetch('arrayDiNomi.json')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Dati ricevuti:', data);
    })
    .catch(error => {
        console.error('Si è verificato un errore:', error);
    });

// Approfondiamo nella prossima lezione...