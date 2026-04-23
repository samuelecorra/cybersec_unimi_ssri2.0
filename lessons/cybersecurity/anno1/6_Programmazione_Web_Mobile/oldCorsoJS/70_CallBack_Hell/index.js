/* COS'E' IL CALLBACK HELL?

// Callback Hell – introduzione accademica ma semplice

In JavaScript, molte operazioni non avvengono immediatamente: 
pensiamo, ad esempio, a leggere un file, fare una richiesta a un server, 
o aspettare un timer.
Queste sono dette operazioni asincrone, perché il programma non si ferma ad 
aspettare, ma continua a girare in attesa che l’operazione finisca.

Storicamente, per gestire il “cosa fare dopo che l’operazione è conclusa”, 
si usavano le callback functions.
Una callback è una funzione che viene passata come argomento a un’altra 
funzione, con l’istruzione di eseguirla quando l’operazione asincrona è 
terminata.

Il problema: Callback Hell

Se hai una sola operazione, la callback è semplice da gestire.
Ma immagina di avere più operazioni asincrone che devono avvenire in sequenza
(prima leggi un file, poi elabori il risultato, poi mandi i dati a un server,
 poi aggiorni l’interfaccia).

Con le sole callback, il codice tende a diventare una serie di funzioni
annidate dentro l’altra, un po’ come una piramide di parentesi:

operazione1(parametro, function(risultato1) {
    operazione2(risultato1, function(risultato2) {
        operazione3(risultato2, function(risultato3) {
            operazione4(risultato3, function(risultato4) {
                // ...
            });
        });
    });
});

Questa forma prende il nome di Callback Hell (“inferno delle callback”) perché:

Il codice diventa difficile da leggere e capire.

La logica è nascosta dentro troppi livelli di annidamento.

Gestire errori o condizioni particolari diventa complicato.

/////////////////////////////////////////////////////////////////////////////

La soluzione moderna

Per superare questo limite, sono state introdotte nuove astrazioni:

Promises → un oggetto che rappresenta il risultato futuro di un’operazione
asincrona. Permette di scrivere codice più lineare con .then() e .catch().

Async/Await → una sintassi più chiara e simile al “codice normale”, che 
si appoggia internamente alle Promises, ma evita di scendere nel caos delle
callback.

////////////////////////////////////////////////////////////////////////////*/

/*
function task1(){
    console.log("Task 1 completata");
}
function task2(){
    console.log("Task 2 completata");
}
function task3(){
    console.log("Task 3 completata");
}
function task4(){
    console.log("Task 4 completata");
}

// chiamiamole:
task1();
task2();
task3();
task4();
console.log("Tutte le task completate!");
*/
// SONO TUTTE OPERAZIONI SINCRONE, IN ORDINE!

// MA SE FOSSERO ASINCRONE?
function task1_1(){
    setTimeout(() => {
        console.log("Task 1 completata");
    }, 2000);
}

function task2_1(){
    setTimeout(() => {
        console.log("Task 2 completata");
    }, 1000);
}

function task3_1(){
    setTimeout(() => {
        console.log("Task 3 completata");
    }, 3000);
}

function task4_1(){
    setTimeout(() => {
        console.log("Task 4 completata");
    }, 1500);
}

// Ora, se le chiamiamo con messaggio finale:
task1_1();
task2_1();
task3_1();
task4_1();
console.log("Tutte le task completate!");

/* Escono in ordine:
Tutte le task completate!
index.js:92 Task 2 completata
index.js:104 Task 4 completata
index.js:86 Task 1 completata
index.js:98 Task 3 completata */

// MH...

// Bhe, per simulare un'esecuzione sincrona potremmo passare sin dalla prima
// fino all'ultima task una funzione di callback che viene richiamata
// all'interno come ultima operazione da svolgere:

function task1c(callback){
    setTimeout(() => {
        console.log("Task 1 completata");
        callback();
    }, 2000);
}

function task2c(callback){
    setTimeout(() => {
        console.log("Task 2 completata");
        callback();
    }, 1000);
}

function task3c(callback){
    setTimeout(() => {
        console.log("Task 3 completata");
        callback();
    }, 3000);
}

function task4c(callback){
    setTimeout(() => {
        console.log("Task 4 completata");
        callback();
    }, 1500);
}

// Ora invochiamo la prima e passiamo di conseguenza con arrow functions
// le varie callbacks annidate:

task1c(() => {
    task2c(() => {
        task3c(() => {
            task4c(() => {
                console.log("Tutte le task completate!");
            })
        })
    })
})

// Si noti che 4 callback NON SONO ANCORA COSI' DISASTROSE, MA
// SI NOTI ANCHE CHE SI STA CREANDO UNA STRUTTURA A PIRAMIDE CHE DIVENTA
// SEMPRE PIU' ILLEGGIBILE!