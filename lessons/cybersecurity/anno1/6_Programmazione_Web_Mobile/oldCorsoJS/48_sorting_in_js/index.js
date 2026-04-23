// il sorting in Javascript è una pratica che consente, mediante l'uso
// del metodo sort(), di ordinare gli elementi di un array in base a
// una certa condizione. Ad esempio, possiamo ordinare un array di
// numeri in ordine crescente o decrescente, oppure un array di
// oggetti in base a una proprietà specifica.

// Può anche ordinare le stringhe in un ordine cosiddetto lessicografico,
// ovvero non solo in base all'ordine alfabetico, ma anche in base a numeri
// e simboli.

// Chiaramente è meglio partire da un sorting puramente alfabetico...

let nomiFilm = ["White Chicks", "Interstellar", "Culpa Mia", "Iron Man", "Se mi lasci ti cancello"];

// Ordiniamo i film in ordine alfabetico
nomiFilm.sort();

console.log(nomiFilm);

// E sembra tutto ok, ma nel momento in cui si provasse a ordinare un array
// di numeri, ecco la magagna...
let numeri = [18, 4, 2000, 15, 17, 2004];
numeri.sort();
console.log(numeri);  // Qui non otterremmo affatto un sorting crescente come
// forse potevamo aspettarci. Il sorting lessicografico infatti prende ogni
// valore da ordinare come stringhe, quindi funziona "bene" se si lavora
// con stringhe puramente letterali, ma nel momento in cui si lavora coi numeri,
// le cose si complicano...

// Il sorting numerico richiede una funzione di confronto specifica che
// è quella (a, b) => a - b, che prende in ingresso due valori attigui,
// e restituisce un valore negativo se a < b, zero se a == b, e un valore
// positivo se a > b. In questo modo il metodo sort() può ordinare
// correttamente i numeri basandosi sull'esito della funzione di confronto.
// E' come quando in Architettura dei calcolatori si controllavano i condition
// codes per determinare il flusso di esecuzione. Per ora basti pensare che
// a - b fornisce uno tra N Z e P, come nella CPU LC-2, e che il sorting stesso
// decida il da farsi.

numeri.sort((a, b) => a - b);

// Stavolta...
console.log(numeri);  // [4, 15, 17, 18, 2000, 2004]

// Come fare a revertire l'ordinamento?
numeri.sort((a, b) => b - a);
console.log(numeri);  // [2004, 2000, 18, 17, 15, 4]
// Attenzione: il sorting si affronta nell'insegnamento universitario da 12CFU
// di algoritmi e strutture dati del secondo anno, ergo non preoccupiamoci se
// non capiamo proprio TUTTO di questa funzione, è normale per ora prenderla
// in maniera "assiomatica" senza approfondire ciò che accade sotto il cofano.

console.log("///////////////////////////////////////////////////////////////");

// Analogamente, se avessimo un array di oggetti Persona con una certa età,
// potremmo per esempio ordinarli dal più giovane al più anziano.

const persone = [
  { nome: "Cyber", eta: 21, mediascolastica: 30 },
  { nome: "Principessa", eta: 20, mediascolastica: 31 },
  { nome: "Mr. Robot", eta: 26, mediascolastica: 32 },
  { nome: "Zuckerberg", eta: 22, mediascolastica: 33 }
];

persone.sort((a, b) => a.eta - b.eta);

// ATTENZIONE: Array.prototype.sort() modifica l’array in place
// e DevTools mostra i log “by reference”: quando espandi l’array loggato,
// vedi lo stato più recente dell’oggetto, non lo “scatto” al momento del 
// console.log.
// QUINDI E' ESSENZIALE CHE CI SALVIAMO DA QUALCHE PARTE GLI ARRAY MAN MANO
// CHE LI ORDINIAMO, prima di outputtarli:

const personeCrescentiPerEtà = [...persone];
console.log(personeCrescentiPerEtà);

// Se invertiamo a e b
persone.sort((a, b) => b.eta - a.eta);
const personeDecrescentiPerEtà = [...persone];
console.log(personeDecrescentiPerEtà);

// Ora ordiniamo per media scolastica crescente (ci aspettiamo coincida
// con l'originale!):
persone.sort((a, b) => a.mediascolastica - b.mediascolastica);
const personeCrescentiPerMedia = [...persone];
console.log(personeCrescentiPerMedia);

// E ora decrescenti per media:
persone.sort((a, b) => b.mediascolastica - a.mediascolastica);
const personeDecrescentiPerMedia = [...persone];
console.log(personeDecrescentiPerMedia);

console.log("///////////////////////////////////////////////////////////////");

// Ora, ci rimane solo una casistica da esplorare: ordinare alfabeticamente
// per nome:
persone.sort((a, b) => a.nome.localeCompare(b.nome));
const personeCrescentiPerNome = [...persone];
console.log(personeCrescentiPerNome);

// E ora decrescenti per nome:
persone.sort((a, b) => b.nome.localeCompare(a.nome));
const personeDecrescentiPerNome = [...persone];
console.log(personeDecrescentiPerNome);

// Il metodo localeCompare permette di confrontare due stringhe in base 
// all'ordinamento locale e restituisce un valore negativo se la prima stringa è
// "minore" della seconda, zero se sono uguali, e un valore positivo se la prima
// è "maggiore" della seconda.

// In poche parole ci semplifica l'ordinamento lessicografico!
