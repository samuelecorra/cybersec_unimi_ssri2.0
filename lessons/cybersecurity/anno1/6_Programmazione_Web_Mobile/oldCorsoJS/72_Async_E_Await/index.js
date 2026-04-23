/*

Async / Await – introduzione

Dopo le callback (che portavano al “callback hell”)
e le Promise (che hanno reso il codice più ordinato), 
JavaScript ha introdotto la sintassi async/await.
Questa permette di scrivere codice asincrono con uno stile molto simile a 
quello sincrono, quindi più semplice da leggere e da ragionare.


////////////////////////////////////////////////////////////////////////////


KEYWORD ASYNC

Quando anteponi async davanti a una funzione, quella funzione restituirà
sempre una Promise.
Anche se dentro sembra un normale return, il valore è automaticamente 
incapsulato in una Promise.


////////////////////////////////////////////////////////////////////////////


KEYWORD AWAIT

All’interno di una funzione async, puoi usare await.
Significa: “fermati qui finché questa Promise non è risolta o rigettata”.

In pratica, trasforma il codice asincrono in qualcosa che sembra sequenziale:


////////////////////////////////////////////////////////////////////////////


// Riprendiamo l'esempio della lezione precedente:  */

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

            const spazzaturaPortata = true;
            if (spazzaturaPortata) {
                resolve("Ho portato fuori la spazzatura 🗑️");
            } else {
                reject("La spazzatura non è stata portata fuori.");
            }
        }, 500);
    });
}

// prima avevamo metod chaining per fare il tutto: 
/*
scendiIlCane().then((risultato) => { console.log(risultato); return pulisciLaCucina();})
              .then((risultato) => { console.log(risultato); return portaFuoriLaSpazzatura();})
              .then((risultato) => { console.log(risultato); console.log("Tutte le task completate!"); })
              .catch((errore) => { console.error(errore); });
*/
// Ora, dal momento che vogliamo globalmente fare tutte le nostre
// faccende, possiamo utilizzare async/await per rendere il codice più leggibile:

async function faiLeFaccende() { // Ritorna una promise
    try {
        const risultatoCane = await scendiIlCane(); // Attende il risultato della promessa
        console.log(risultatoCane);

        const risultatoCucina = await pulisciLaCucina(); // Attende il risultato della promessa
        console.log(risultatoCucina);

        const risultatoSpazzatura = await portaFuoriLaSpazzatura(); // Attende il risultato della promessa
        console.log(risultatoSpazzatura);

        console.log("Tutte le task completate!");
    } catch (erroreGenerico) {
        console.error(erroreGenerico);
    }
}

faiLeFaccende();