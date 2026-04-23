// La conversione dei tipi è una pratica molto comune se non imprescindibile
// di qualsiasi linguaggio di programmazione!

// Più specificamente, in JavaScript l'user input ci arriva come string, ergo
// dobbiamo poterlo convertire nel tipo che ci serve a seconda dei casi.

let età = window.prompt("Quanti anni hai?");
età = Number(età);

// E solo ora possiamo matematicarci sopra:
console.log("Tra 5 anni avrai " + (età + 5) + " anni!", typeof età);

// Ora creiamo tre variabili:

let x, y, z;

x = "pizza";
y = "pasta";
z = "insalata";

// Se provassimo a:
x = Number(x, typeof x);  // NaN -> Not A Number seguito da tipo 'number', poiché
// la conversione va a buon fine ma non rende alcun numero, solo il passaggio
// di tipo. E' strano, ma in JavaScript funziona così;

y = String(y, typeof y);  // "pasta", string: chiaramente non cambia nulla;

z = Boolean(z, typeof z); // true, poiché la conversione a booleano di una stringa
// non vuota restituisce sempre true

console.log(x, y, z);

// Facciamo altre prove dopo un riassegnamento per valutare altre casistiche
// e approfondire:

x, y, z = "0";

// Con tutti e tre i valori settati come stringa 0, vediamo cosa succede:

x = Number(x, typeof x);  // 0, number: la conversione va a buon fine

y = String(y, typeof y);  // "0", string: la conversione non fa nulla...

z = Boolean(z, typeof z); // true, poiché si converte una stringa non vuota!

console.log(x, y, z);

// E ancora:

x = "";
y = "";
z = "";

// Questo succede quando ad esempio un user non inserisce nulla come input:

x = Number(x, typeof x);  // NaN, number: solo il tipo viene convertito
                            // ma il valore non è numerico!

y = String(y, typeof y);  // "", string: la conversione non fa nulla...

z = Boolean(z, typeof z); // false, poiché si converte una stringa vuota!

console.log(x, y, z);

// E ancora:

let x2, y2, z2;

// Se non inizializziamo nulla, ovvero lasciamo undefined:

x2 = Number(x2, typeof x2);  // NaN, number: solo il tipo viene convertito
                               // ma il valore non è numerico!

y2 = String(y2, typeof y2);  // "undefined", string: la conversione non fa nulla...

z2 = Boolean(z2, typeof z2); // false, poiché si converte una stringa vuota!

console.log(x2, y2, z2);

// Ribadisco che questi "assiomi" sono fondamentali quando gestiamo l'user
// input perché potremmo trovarci a voler convertire in base alle nostre
// esigenze algebriche e algoritmiche!

// Ci alleneremo in futuro!