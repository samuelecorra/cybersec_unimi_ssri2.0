/* Gli operatori aritmetici sono la base di tutta la programmazione, perché
senza matematica dove saremmo? Probabilmente ancora nelle grotte di Neanderthal...

Passiamoli in rassegna velocemente in quanto sono sintatticamente identici
a Java e C - e spoiler, nemmeno negli altri linguaggi variano...    */

let annoDiNascita = 2000;

console.log("Anno di nascita: " + annoDiNascita);

annoDiNascita = annoDiNascita + 1; // somma

console.log("Anno di nascita + 1: " + annoDiNascita);

annoDiNascita = annoDiNascita - 1; // differenza

console.log("Anno di nascita - 1: " + annoDiNascita);

annoDiNascita = annoDiNascita * 1; // prodotto

console.log("Anno di nascita * 1: " + annoDiNascita);

annoDiNascita = annoDiNascita / 1; // quoziente

console.log("Anno di nascita / 1: " + annoDiNascita);

let giornoDiNascita = 14;

// L'esponente non è complesso come in C - dove era necessario usare pow
// e dunque includere math.h, in JavaScript è sufficiente usare l'operatore **

console.log("Giorno di nascita: " + giornoDiNascita + 
            " elevato alla seconda: " + (giornoDiNascita ** 2));

// Il modulo, che in matematica si indica con il simbolo %:

let resto = giornoDiNascita % 2; // 19 è dispari ergo ci aspettiamo 1
console.log("Resto della divisione di " + giornoDiNascita + " per 2: " + resto);
resto = giornoDiNascita % 19;
console.log("Resto della divisione di " + giornoDiNascita + " per 19: " + resto);


//////////////////////////////////////////////////////////////////////////////////


// Vediamo gli augmented assignment operators, ovvero le forme compatte dove
// il valore computato (variabile + valore) viene assegnato nuovamente
// alla stessa variabile:

let età = 17;

età += 1; // equivalente a età = età + 1
console.log("Età dopo un anno: " + età);

età -= 1; // equivalente a età = età - 1
console.log("Età attualmente: " + età);

età *= 2; // equivalente a età = età * 2
console.log("Età raddoppiata: " + età);

età /= 2; // equivalente a età = età / 2
console.log("Età attualmente: " + età);

// Si può fare anche con l'elevamento a potenza:
età **= 2; // equivalente a età = età ** 2
console.log("Età elevata al quadrato: " + età);

// E anche col modulo:
età %= 2; // equivalente a età = età % 2
console.log("Resto modulo 2 dell'età: " + età);


//////////////////////////////////////////////////////////////////////////////


// OPERATORI DI INCREMENTO E DECREMENTO:
età = 17;
età++; // equivalente a età = età + 1
console.log("Età dopo un anno: " + età);

età--; // equivalente a età = età - 1
console.log("Età attualmente: " + età);


///////////////////////////////////////////////////////////////////////////////


// ATTENZIONE: PRECEDENZA DEGLI OPERATORI:

// -> 1. PARENTESI 
// -> 2. ESPONENTI 
// -> 3. MOLTIPLICAZIONE, DIVISIONE E MODULO
// -> 4. SOMMA E DIFFERENZA

let risultato = 3 + 4 * 2; // ci aspettiamo 11
let risultato2 = (3 + 4) * 2; // ci aspettiamo 14
let risultato3 = (3 + 4) * 2 ** 2 - 9; // ci aspettiamo 19

// Insomma, attenzione... e porre parentesi per modificare consciamente
// l'ordine di valutazione delle espressioni!
