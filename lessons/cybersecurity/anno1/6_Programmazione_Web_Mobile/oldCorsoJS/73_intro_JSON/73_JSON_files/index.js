// JSON = JavaScript Object Notation
// È un formato leggero per lo scambio di dati.
// Serve per trasferire informazioni tra un server
// e un’applicazione web in modo semplice e leggibile.
//
// Struttura:
// 1) Oggetti -> { "chiave": valore, "chiave2": valore2 }
// 2) Array   -> [ valore1, valore2, valore3 ]
//
// Esempio di oggetto JSON:
// { "nome": "CyberGenio", "eta": 21, "studente": true }
//
// Esempio di array JSON:
// [ "rosso", "verde", "blu" ]
//
// Due metodi fondamentali:
//
// JSON.stringify(obj) -> converte un oggetto JS
//                       in stringa JSON.
// Esempio:
// let persona = { nome: "Principessa", eta: 20 };
// let testo = JSON.stringify(persona);
// // Risultato: '{"nome":"Principessa","eta":20}'
//
// JSON.parse(str) -> converte una stringa JSON
//                   in oggetto JS.
// Esempio:
// let json = '{"nome":"Principessa","eta":20}';
// let obj = JSON.parse(json);
// // Risultato: { nome: "Principessa", eta: 20 }
//
// 👉 In sintesi: JSON è il "linguaggio comune"
// tra JavaScript e i server per scambiarsi dati.

////////////////////////////////////////////////////////////////////////////

const nomi = ["CyberGenio", "Principessa", "Floopaloo", "Marinette", "Adrien"];

const stringaJSONnomi = JSON.stringify(nomi);

console.log("Array originale:", nomi);
console.log("Stringa JSON:", stringaJSONnomi);

////////////////////////////////////////////////////////////////////////////

const principessa = {
    "name": "Principessa",
    "age": 20,
    "isStudent": true,
    "classe": "5A",
    "indirizzo": "Scienze Umane",
    "colorePreferito": "rosa",
    "marchePossedute": ["Chanel", "Adidas", "Prada"]
};

const stringaJSONPrincipessa = JSON.stringify(principessa);

console.log("Oggetto originale:", principessa);
console.log("Oggetto JSON:", stringaJSONPrincipessa);

/////////////////////////////////////////////////////////////////////////////

const fidanzati = [{
    "nome": "CyberGenio",
    "eta": 21,
    "titolo": "Ingegnere",
    "relazione": true
}, {
    "nome": "Principessa",
    "eta": 20,
    "titolo": "Studentessa",
    "relazione": true
}, {
    "nome": "Floopaloo",
    "eta": 25,
    "titolo": "Re della Foresta",
    "relazione": true
}, {
    "nome": "Marinette",
    "eta": 16,
    "titolo": "Eroina",
    "relazione": false
}, {
    "nome": "Adrien",
    "eta": 18,
    "titolo": "Eroe",
    "relazione": false
}];

const stringaJSONFidanzati = JSON.stringify(fidanzati);

console.log("Array originale:", fidanzati);
console.log("Stringa JSON:", stringaJSONFidanzati);

/////////////////////////////////////////////////////////////////////////////

// Ora vediamo invece il parse:
// Prendiamo le stesse scritture di prima ma surroundiamole con backticks:

const jsonNomi = `["CyberGenio", "Principessa", "Floopaloo", "Marinette", "Adrien"]`;
const jsonPrincipessa = `{"name": "Principessa", "age": 20, "isStudent": true, "classe": "5A", "indirizzo": "Scienze Umane", "colorePreferito": "rosa", "marchePossedute": ["Chanel", "Adidas", "Prada"] }`;
const jsonFidanzati = `[ { "nome": "CyberGenio", "eta": 21, "titolo": "Ingegnere", "relazione": true }, 
                         { "nome": "Principessa", "eta": 20, "titolo": "Studentessa", "relazione": true }, 
                         { "nome": "Floopaloo", "eta": 25, "titolo": "Re della Foresta", "relazione": true }, 
                         { "nome": "Marinette", "eta": 16, "titolo": "Eroina", "relazione": false },
                         { "nome": "Adrien", "eta": 18, "titolo": "Eroe", "relazione": false } ]`;

// Ergo sono tutti in un json-format. Convertiamoli:

const nomiParsed = JSON.parse(jsonNomi);
const principessaParsed = JSON.parse(jsonPrincipessa);
const fidanzatiParsed = JSON.parse(jsonFidanzati);

console.log("Stringa JSON nomi:", jsonNomi);
console.log("Stringa JSON principessa:", jsonPrincipessa);
console.log("Stringa JSON fidanzati:", jsonFidanzati);

// E ora i convertiti:
console.log("Array originale nomi:", nomiParsed);
console.log("Oggetto originale principessa:", principessaParsed);
console.log("Array originale fidanzati:", fidanzatiParsed);
