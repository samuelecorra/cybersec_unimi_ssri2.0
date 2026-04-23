/* Lo spread operator, ovvero i tre puntini di sospensione (...) */
/* E' un operatore molto potente e versatile introdotto in ES6 (ECMAScript 2015)
che consente di "espandere" elementi di un array o le proprietà di un oggetto
in un contesto in cui ci si aspetta una lista di elementi o un oggetto. */

// E' COME SE GLI ELEMENTI VENISSERO "SPACCHETTATI"!


// Partiamo vedendo SENZA operatore spread:
let numArr = [1, 2, 3, 4, 5];
console.log(numArr); // [1, 2, 3, 4, 5]

let maxEl = Math.max(numArr);
console.log(maxEl); // Otterremmo NaN poiché stiamo chiedendo a Math di calcolare
// il massimo non TRA GLI ELEMENTI, ma il massimo riferito a un singolo identificatore
// di array, il che è insensato.

// Il che si risolve con lo spread: prima però...
let numArr2 = [...numArr]; // Copiamo con lo spread per non dover re-inizializzare
// manualmente;
// QUESTO - IN GERGO - E' CREARE UNA SHALLOW COPY DELL'ARRAY ORIGINALE, DOVE PER
// SHALLOW COPY SI INTENDE UNA DIFFERENTE STRUTTURA DATI MA CON ELEMENTI IDENTICI

let maxEl2 = Math.max(...numArr2); // E idem qui, spreaddiamo per consentire
// a Math di valutarne l'intero contenuto!
console.log(maxEl2); // 5 OK!!!

// Il che è chiaramente riapplicabile ove necessario, ad esempio con min:
let minEl = Math.min(...numArr);
console.log(minEl); // 1 OK!!!

// MA SOPRATTUTTO POSSIAMO SEPARARE LE STRINGHE

let str = "Ciao Principessa";
let chars = [...str];
console.log(chars); // ['C', 'i', 'a', 'o', ' ', 'P', 'r', 'i', 'n', 'c', 'i', 'p', 'e', 's', 's', 'a']

// Per effettuare l'operazione contraria, ovvero ricomporre la stringa,
// si usa il metodo join, che possiamo chainare direttamente dopo lo spread:
// si noti che possiamo inserire "" così da ricomporre l'originale, ma anche
// ad esempio "-" per separare ogni char della stringa originale:

let dashedChars = [...str].join("-");
console.log(dashedChars); // "C-i-a-o- -P-r-i-n-c-i-p-e-s-s-a"


///////////////////////////////////////////////////////////////////////////////

// Oltre alle shallow copy degli array, possiamo anche unire 2 o più array sempre
// con lo spread:

let fruits = ["mela", "banana", "arancia"];
let vegetables = ["carota", "broccoli", "spinaci"];
let allFood = [...fruits, ...vegetables, `uova`, `latte`];

// Si noti che siamo anche in grado di fare append in coda di altri elementi
// singoli! Verifichiamolo:
console.log(allFood); // ['mela', 'banana', 'arancia', 'carota', 'broccoli', 'spinaci', 'uova', 'latte']