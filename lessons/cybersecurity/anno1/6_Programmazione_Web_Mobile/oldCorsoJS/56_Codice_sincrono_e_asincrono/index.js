/* In JavaScript “esecuzione sincrona” significa che il motore esegue le 
istruzioni in un unico flusso (call stack) una dopo l’altra: finché un’operazione
non termina, la successiva non parte; se un passo è lento o bloccante, 
tutto il thread resta fermo. Poiché il main thread di JS è single-threaded,
questo modello è semplice e deterministico nell’ordine, ma per I/O (rete, disco,
timer) può diventare un collo di bottiglia e persino un vettore di 
denial-of-service logico se si inseriscono per errore operazioni bloccanti in UI
o in un server. 

L’“esecuzione asincrona” non crea magicamente più CPU: 
cambia quando il lavoro prosegue. Le operazioni potenzialmente lente vengono 
delegate a servizi dell’ambiente (browser/Node) che le portano avanti senza 
occupare il call stack; quando sono pronte, il event loop riprende il controllo 
e programma le “continuazioni” (callback/Promesse/async-await) mettendole in 
code di esecuzione, così il thread può servire altro nel frattempo. 

In pratica, la concorrenza in JS è per interleaving: 
più attività sono “in progresso” ma il main thread esegue sempre un frammento 
per volta, scegliendo dal/dei queue(s) secondo priorità; per questo l’ordine 
temporale tra eventi asincroni non è garantito come nella sequenza sincrona. 

Dal punto di vista della sicurezza e dell’affidabilità, l’asincronia riduce 
il rischio di blocchi e migliora la reattività, ma introduce rischi di race 
logic: assunzioni sbagliate sull’ordine di arrivo degli eventi, 
TOCTOU (time-of-check to time-of-use) su autorizzazioni o stato, trattamenti 
incoerenti di dati che arrivano “dopo”, e necessità di gestire correttamente 
timeouts, errori e cancellazioni. Inoltre, l’asincrono non risolve i carichi 
CPU-bound: un ciclo pesante resta bloccante e richiede isolamento 
(es. Web Worker) per non congelare il thread. 

In sintesi: sincrono = sequenza 
lineare e bloccante, semplice ma rischiosa per operazioni lente;

asincrono = delega e ripresa tramite event loop, non bloccante e scalabile per 
I/O, ma richiede progettazione attenta su ordine, stato e gestione degli errori
per evitare vulnerabilità e bug sottili. */


///////////////////////////////////////////////////////////////////////////////

// Ok, questa era un'introduzione bella prolissa e avanzata:
// Semplifichiamo le cose:
// synchronous = il codice viene eseguito riga per riga consecutivamente
// in maniera perfettamente sequenziale, e ciò implica che il codice stesso
// deve aspettare sempre che un'operazione precedente venga completata prima
// di poter procedere con l'operazione successiva.

// asynchronous = il codice può continuare a essere eseguito senza dover
// aspettare il completamento di un'operazione precedente, consentendo
// una maggiore efficienza e reattività. Questo comporta che multiple operazioni
// possono essere in corso contemporaneamente, senza bloccare il 
// thread principale, che è colui che determina l'execution flow.

// Questo permette di fare I/O ops, network requests, fetching di risorse...
// Si gestisce con: Callbacks, Promises, Async/Await.


////////////////////////////////////////////////////////////////////////////////

// ESEMPIO 1:

// Per ora limitiamoci a un primo esempio per capire DA ZERO cosa vuol dire
// sincrono:

console.log("Task1");
console.log("Task2");
console.log("Task3"); // Le tre tasks sono eseguite in modo sincrono!

// ESEMPIO 2:

// Abbiamo già incontrato nella selva oscura che è Javascript una funzione
// che lavora in modo asincrono, ed è il setTimeout:

setTimeout(() => {
  console.log("Task4");
}, 1000);

console.log("Task5");
console.log("Task6"); // Le tre tasks sono eseguite in modo asincrono!
// prima spunteranno in console la 5 e la 6, e poi la 4 dopo 1 secondo

// Per "sincronizzare" il tutto, la maniera più immediata che vediamo è quella
// di apposite callbacks che ci costruiamo:

// Supponiamo di avere una prima funzione che contiene il setTimeout visto poc'anzi:
// IN TALE setTimeout, PRIMA si fa la task1, per quanto lunga e complicata essa
// possa essere, e nella riga successiva si chiama la callback function contenente
// le tre tasks successive:

function funzione1(callback) {
  setTimeout(() => {
    console.log("Compito1");
    callback();
  }, 3000);
}

function funzioneDiCallBack() {
  console.log("Compito2");
  console.log("Compito3");
  console.log("Compito4");
}

// Procediamo a chiamare la primaria:
funzione1(funzioneDiCallBack);
// Si attendono 3 secondi e poi si stampano i 4 compiti ordinati, ergo
// abbiamo risolto la questione dell'asincronismo legato al setTimeout, ma
// questo NON E' L'UNICO MODO per gestire l'asincronia in JavaScript.

// VEDREMO IN FUTURO...