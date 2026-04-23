/* Una funzione di callback in JavaScript è semplicemente una funzione che viene
passata come argomento a un’altra funzione e che verrà eseguita in un momento 
successivo, tipicamente quando l’altra funzione ha finito di fare qualcosa.

In altre parole:
👉 Una funzione normale la chiami direttamente.
👉 Una callback la consegni a qualcun altro (a un’altra funzione), che deciderà
quando e come eseguirla.

Esempio base */
function saluta(nome) {
  console.log("Ciao " + nome);
}

function processaUtente(callback) {
  const utente = "CyberSamurai";
  console.log("Devo elaborare qualche Terabyte, ma intanto posso salutarti:")
  callback(utente); // qui viene eseguita la funzione che ho ricevuto
}

processaUtente(saluta);

// Solitamente, sono utili per gestire operazioni asincrone, ovvero
// operazioni che richiedono del tempo per essere completate, come
// richieste di rete, lettura di file, timer, interazioni con database...

// ATTENZIONE: si passa il NOME della funzione, non la funzione intesa come
// nome(), in quanto le parentesi indicano l'invocazione della funzione stessa!


////////////////////////////////////////////////////////////////////////////////

// ESEMPIO 2:

function somma(callback, a, b) {
    let risultato = a + b;
    callback(risultato);
}

function stampaRisultato(risultato) {
    console.log("Il risultato è: " + risultato);
}

somma(stampaRisultato, 10, 9);
// Qui somma() calcola il risultato e poi chiama stampaRisultato()

// Oppure mostrarlo sulla nostra pagina web
function mostraRisultato(risultato) {
    document.getElementById("risultato").textContent = "Il risultato è: " + risultato;
}

somma(mostraRisultato, 2003, 2000);
// NOTA BENE: non devo riscrivere da zero la funzione di somma perché accetta
// una generica funzione di callback, non solo quella per stampa in console!
// COMODO!

