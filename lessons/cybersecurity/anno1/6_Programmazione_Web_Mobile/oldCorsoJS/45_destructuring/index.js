// Il destructuring in Javascript è una pratica che consente di estrarre valori da
// array o proprietà da oggetti in modo più semplice e leggibile.

// L'esempio per antonomasia è lo swap di due variabili:

let a = 5;
let b = 10;

// Swap usando una variabile temporanea
let temp = a;
a = b;
b = temp;

// Ora vogliamo re-swappare, ovvero ristabilire i valori originali, ma lo
// possiamo fare facilmente grazie al destructuring:

// Esempio 1:
// Swap usando il destructuring
[a, b] = [b, a];

// Verifichiamo che sia tornato tutto come prima
console.log("a:", a);
console.log("b:", b);

// Esempio 2: dato un array di vari elementi, come si scambiano
// due soli elementi?
const colors = ["rosa", "rosso", "verde", "blu", "giallo"];

// Swap usando il destructuring
[colors[1], colors[3]] = [colors[3], colors[1]];

// Verifichiamo che sia tornato tutto come prima
console.log("Colori:", colors);

console.log("///////////////////////////////////////////////////////////////");

// Ora vediamo come assegnare degli elementi di un array a delle variabili:
// Esempio 3:

const regali = ["borsa_Chanel", "profumo_Givenchy", "Lego_Harry_Potter", "PeraBook", "IPhone"];

// Destructuring
const [regalo1, regalo2, regalo3, ...altriRegali] = regali;

console.log("Regalo 1:", regalo1);
console.log("Regalo 2:", regalo2);
console.log("Regalo 3:", regalo3);
console.log("Altri regali:", altriRegali);

console.log("///////////////////////////////////////////////////////////////");

// Ora vediamo come estrarre valori dagli oggetti:
// Esempio 4:

const persona1 = {
  nome: "Principessa",
  cognome: "Gommarosa",
  eta: 20,
  professione: "Studentessa di Scienze Umane"
};

const persona2 = {
  nome: "Cyber",
  cognome: "Genio",
  eta: 21,
  professione: "Ingegnere Aerospaziale"
};

const persona3 = {
    nome: "Stella",
    cognome: "Rubia",
    eta: 18,
    // E' una supereroina, non possiamo scrviere certo il suo mestiere...
}
// Destructuring
const { nome, cognome, eta, professione } = persona1;
// ATTENZIONE: in questo caso, dal momento che le varie persone hanno tutte quante
// tutte le proprietà in comune, bisogna di volta in volta cambiare la parte
// a destra dell'uguale dell'ultima riga di codice per verificare le informazioni
// delle altre persone. Per ora displayamo i dati di Principessa, ma se vogliamo
// Cyber, dovremo sostituire 1->2, se invece vogliamo Stella Rubia dovremo
// cambiare 1->3, e poi mettere il valore di professione a ="Supereroina",
// altrimenti otteniamo un errore di undefined. In questo caso stiamo manualmente
// settando un default value nella graffa.

console.log("Nome:", nome);
console.log("Cognome:", cognome);
console.log("Età:", eta);
console.log("Professione:", professione);

console.log("///////////////////////////////////////////////////////////////");

// Esempio 5 per concludere:
// Possiamo anche destrutturare oggetti in parametri di funzioni - per farlo
// si appone dentro alla parentesi tonda dei parametri passati anche una coppia
// di parentesi graffe che indicano alla funzione che deve estrapolare tali
// parametri dall'oggetto che le viene passato. Easy peasy, no?

function mostraInfoPersona({ nome, cognome, eta, professione }) {
  console.log("Nome:", nome);
  console.log("Cognome:", cognome);
  console.log("Età:", eta);
  console.log("Professione:", professione);
}

// Possiamo già invocarla usando le tre persone definite prima?

mostraInfoPersona(persona1);
mostraInfoPersona(persona2);
mostraInfoPersona(persona3);

// La cosa bella di questo destructuring mediante funzione è che possiamo
// riutilizzare la stessa funzione per qualsiasi oggetto che abbia le stesse
// proprietà, senza dover scrivere codice duplicato.