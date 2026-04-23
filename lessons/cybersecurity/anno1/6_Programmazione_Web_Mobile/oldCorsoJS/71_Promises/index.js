/*
// Cos’è una Promise in JavaScript

Una Promise è un oggetto introdotto in JavaScript per gestire le operazioni
 asincrone in modo più ordinato e leggibile rispetto alle callback annidate.

Il nome stesso richiama un’idea: “prometto che ti darò un valore…
ma non subito”.
Questa promessa potrà concludersi in due modi:

Risolta (resolved) → l’operazione è andata a buon fine e ho un risultato 
da restituire.

Rigettata (rejected) → qualcosa è andato storto e ti restituisco un errore.

Finché l’operazione è in corso, la Promise si trova nello stato Pending
(in attesa).

////////////////////////////////////////////////////////////////////////////

La sintassi di base
Una Promise si costruisce così:

let miaPromise = new Promise((resolve, reject) => {
    // Qui dentro c’è del codice asincrono
    let successo = true;

    if (successo) {
        resolve("Operazione riuscita!");
    } else {
        reject("Si è verificato un errore.");
    }
});


resolve(...) → usato per completare la promessa con successo.
reject(...) → usato per segnalarne il fallimento.

Consumo della Promise

Una volta creata, la Promise viene “consumata” usando i metodi:

.then(...) per gestire il successo,
.catch(...) per gestire l’errore,
.finally(...) per il codice che deve eseguirsi sempre, indipendentemente 
dal risultato.


/////////////////////////////////////////////////////////////////////////////

Esempio 1: */

function scendiIlCane() {

    return new Promise((resolve, reject) => {
        setTimeout(() => {

            const caneSceso = true;
            if (caneSceso) {
                resolve("Ho sceso il cane 🐕");
            } else {
                reject("Il cane non è stato sceso.");
            }
        }, 1500);
    });
}

// Modifichiamo anche le altre:

function pulisciLaCucina() {

    return new Promise((resolve, reject) => {
        setTimeout(() => {

            const cucinaPulita = true;
            if (cucinaPulita) {
                resolve("Ho pulito la cucina 🧹");
            } else {
                reject("La cucina non è stata pulita.");
            }
        }, 2500);
    });
}

function portaFuoriLaSpazzatura() {

    return new Promise((resolve, reject) => {
        setTimeout(() => {

            const spazzaturaPortata = false;
            if (spazzaturaPortata) {
                resolve("Ho portato fuori la spazzatura 🗑️");
            } else {
                reject("La spazzatura non è stata portata fuori.");
            }
        }, 500);
    });
}

scendiIlCane().then((risultato) => { console.log(risultato); return pulisciLaCucina();})
              .then((risultato) => { console.log(risultato); return portaFuoriLaSpazzatura();})
              .then((risultato) => { console.log(risultato); console.log("Tutte le task completate!"); })
              .catch((errore) => { console.error(errore); });