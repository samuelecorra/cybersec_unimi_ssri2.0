// Gli array di oggetti in Javascript fungono da collezione di tanti concetti
// complessi che possiamo gestire in modo più semplice e organizzato.
// Ad esempio, possiamo avere un array di persone, ognuna con le proprie
// proprietà e metodi, e iterare su di esso per eseguire operazioni su ogni
// singolo oggetto.

// Ma partiamo con calma...

const frutti = [
      { nome: "Ananas", colore: "Giallo", calorie: 150 },
      { nome: "Banana", colore: "Giallo", calorie: 120 },
      { nome: "PassionFruit", colore: "Arancione", calorie: 200 },
      { nome: "Cocco", colore: "Bianco", calorie: 300 },
      { nome: "Mango", colore: "Giallo", calorie: 150 }
    ];

// La comodità di Javascript è la possibilità di accedere facilmente alle varie
// proprietà mediante accesso agli elementi con indice:

console.log(frutti[0].nome); // "Ananas"
console.log(frutti[0].colore); // "Giallo"
console.log(frutti[0].calorie); // 150
console.log("//////////////////////////////////////////////////////////");

console.log(frutti[1].nome); // "Banana"
console.log(frutti[1].colore); // "Giallo"
console.log(frutti[1].calorie); // 120
console.log("//////////////////////////////////////////////////////////");

console.log(frutti[2].nome); // "PassionFruit"
console.log(frutti[2].colore); // "Arancione"
console.log(frutti[2].calorie); // 200
console.log("//////////////////////////////////////////////////////////");

console.log(frutti[3].nome); // "Cocco"
console.log(frutti[3].colore); // "Bianco"
console.log(frutti[3].calorie); // 300
console.log("//////////////////////////////////////////////////////////");

console.log(frutti[4].nome); // "Mango"
console.log(frutti[4].colore); // "Giallo"
console.log(frutti[4].calorie); // 150

// Introduciamo ora il metodo push per aggiungere un nuovo frutto all'array,
// chiaramente in coda, ergo in indice 5:
frutti.push({ nome: "Kiwi", colore: "Verde", calorie: 100 });

console.log("//////////////////////////////////////////////////////////");

// Verifichiamo che il nuovo frutto sia stato aggiunto correttamente
console.log(frutti[5].nome); // "Kiwi"
console.log(frutti[5].colore); // "Verde"
console.log(frutti[5].calorie); // 100

// Il metodo duale e contrario è pop, che rimuove l'ultimo elemento dell'array
frutti.pop();

console.log("//////////////////////////////////////////////////////////");

// Verifichiamo che l'ultimo frutto sia stato rimosso correttamente
console.log(frutti[5]); // undefined
console.log("//////////////////////////////////////////////////////////");

// Chiaramente necessitiamo anche di un metodo che rimuova un determinato elemento
// a un determinato indice, non solo l'ultimo:
frutti.splice(2, 1); // il primo parametro passato indica l'indice da cui partire, 
// il secondo il numero di elementi da rimuovere.

// In questo caso si vuole partire da PassionFruit e rimuovere solo esso.
// Verifichiamo stampando l'array intero:
console.log(frutti); // Ora PassionFruit non c'è più, e l'array è composto da 4 elementi.

console.log("//////////////////////////////////////////////////////////");

// Possiamo anche appoggiarci a un forEach per semplificare il codice:
// ATTENZIONE 
// frutti.forEach(); come popoliamo la parentesi?
// Bisogna indicare che per ogni frutto dell'array vogliamo eseguire una certa azione.
// Questo si fa con un'arrow function, che ribadiamo essere una funzione anonima
// definita con la sintassi delle frecce.
console.log("Metodo forEach");

frutti.forEach(frutto => {
                          console.log(frutto);
                          console.log("//////////////////////////////////////////////////////////");
                            });

// in questo caso appena scritto si stampa l'intero oggetto frutto,
// ma possiamo anche scorrere le proprietà di ciascun oggetto:
frutti.forEach(frutto => {
  console.log(frutto.nome);
  console.log(frutto.colore);
  console.log(frutto.calorie);
  console.log("//////////////////////////////////////////////////////////");
});

// Non possiamo esimerci dall'introdurre il metodo map, che ci permette di 
// trasformare un array in un altro array, applicando una funzione a 
// ciascun elemento.

// La seguente riga invoca il metodo map, passandogli come parametro
// una funzione che prende ogni frutto e restituisce il suo nome.

// Se tale funzione viene applicata a ciascun elemento dell'array, il risultato sarà
// un nuovo array contenente solo i nomi dei frutti. Chiaramente tali valori
// vanno assegnati mediante = al nomiFrutti, che diventerà l'array-destinazione:
const nomiFrutti = frutti.map(frutto => frutto.nome);

console.log(nomiFrutti); // Ci aspettiamo: ["Ananas", "Banana", "Cocco", "Mango"]

// Riapplichiamo tale discorso alle altre proprietà:
const coloriFrutti = frutti.map(frutto => frutto.colore);
const calorieFrutti = frutti.map(frutto => frutto.calorie);

console.log(coloriFrutti); // Ci aspettiamo: ["Giallo", "Giallo", "Viola", "Bianco", "Giallo"]
console.log(calorieFrutti); // Ci aspettiamo: [50, 89, 120, 300, 150]

console.log("//////////////////////////////////////////////////////////");

// Come se non bastasse, col metodo filter() possiamo anche filtrare gli elementi
// dell'array in base a una certa condizione. Ad esempio, supponiamo di voler
// ottenere solo i frutti gialli:

// Dopo la freccia, ci va direttamente la condizione, senza if, e con ovviamente
// il triplo uguale che confronta sia valore che tipo:
const fruttiGialli = frutti.filter(frutto => frutto.colore === "Giallo");
console.log(fruttiGialli); // Ci aspettiamo: [ { nome: "Ananas", colore: "Giallo", calorie: 50 }, { nome: "Banana", colore: "Giallo", calorie: 89 }, { nome: "Mango", colore: "Giallo", calorie: 150 } ]

// Idem se volessimo isolare i frutti ipocalorici (con meno di 150cal):
const fruttiIpocalorici = frutti.filter(frutto => frutto.calorie < 150);
console.log(fruttiIpocalorici); // Ci aspettiamo solamente la banana che ne ha 120

console.log("//////////////////////////////////////////////////////////");

// Infine, il metodo più tricky è il reduce(), che ci permette di ridurre
//  l'array a un singolo valore.

// Ipotizziamo di voler estrarre il frutto con più calorie, ovvero il cocco.
// La funzione valutata da reduce è una condizione, quindi un operatore ternario
// va più che bene per compattare la sintassi - il parametro accumulatore
// che viene passato al metodo è usato per conservare il valore massimo, che
// ovviamente alla prima "iterazione" sarà il primo elemento dell'array, ma
// poi varierà con lo scorrere dei frutti:
const fruttoPiùCalorico = frutti.reduce((accumulatore, elementoAttuale) => 
  elementoAttuale.calorie > accumulatore.calorie ? 
  elementoAttuale : accumulatore
);

// Ricordiamo che quando chiamiamo reduce, si deve per forza di cose passare
// un parametro iniziale che sia l'elemento attuale, ma l'accumulatore è
// implicitamente settato a 0 così che il primo confronto avvenga correttamente.
console.log(fruttoPiùCalorico); // Ci aspettiamo: { nome: "Cocco", colore: "Bianco", calorie: 300 }

// Idem per il meno calorico, ovvero la banana:
const fruttoMenoCalorico = frutti.reduce((accumulatore, elementoAttuale) => 
  elementoAttuale.calorie < accumulatore.calorie ? 
  elementoAttuale : accumulatore
);
console.log(fruttoMenoCalorico); // Ci aspettiamo: { nome: "Banana", colore: "Giallo", calorie: 120 }

