// Gli oggetti date sono oggetti peculiari di Javascript che contengono
// valori che rappresentano date e orari. Questi oggetti forniscono
// metodi utili per manipolare e formattare date in vari modi.

const date = new Date();

console.log(date); // Stampa la data e l'ora correnti

// parametri passati: (anno, mese, giorno, ora, minuto, secondo, millisecondo)
const date1 = new Date(2024, 0, 20, 10, 40, 30, 5); // 20 Gennaio 2024
console.log(date1);

// Oppure ancora possiamo passare:
const date2 = new Date("2024-01-20T10:40:30.005Z");
// dove Z ci fornisce anche l'UTC time, dove UTC sta per Universal Time Coordinated,
// e si ricordi che il GMT che ci appare in console sta per Greenwich Mean Time,
// seguito da + o - il numero di ore di differenza rispetto all'UTC.
console.log(date2);

// Se invece passiamo solo un numero che esprime millisecondi, stiamo dicendo
// di calcolare i millisecondi passati rispetto ad Epoch (1 Gennaio 1970):
const date3 = new Date(0);
console.log(date3); // 1 Gennaio 1970

// Quindi se passo 1000 miliardi di millisecondi
const date4 = new Date(1000000000000);
console.log(date4); // 9 Settembre 2001

// Sarà utile per qualche tipo di timer/calcolo temporale!


////////////////////////////////////////////////////////////////////////////////


// Possiamo estrarre valori individuali da un date object:

const anno = date.getFullYear();
// non si usa più getYear() poiché restituisce solo l'anno a due cifre con
// l'offset rispetto al 1900 ovvero: annoRestituito = anno - 1900, il che
// causò non pochi problemi interpretativi a fronte del Millenium Bug!

const mese = date.getMonth();
const giorno = date.getDate();
const ore = date.getHours();
const minuti = date.getMinutes();
const secondi = date.getSeconds();
const millisecondi = date.getMilliseconds();
const giornoDellaSettimana = date.getDay();

console.log(anno, mese, giorno, ore, minuti, secondi, millisecondi, giornoDellaSettimana);


////////////////////////////////////////////////////////////////////////////////


// Ci sono poi dei metodi built-in per SETTARE i valori di un date object:

date.setFullYear(2024);
date.setMonth(0); // Gennaio
date.setDate(20);
date.setHours(10);
date.setMinutes(40);
date.setSeconds(30);
date.setMilliseconds(5);

console.log(date);


////////////////////////////////////////////////////////////////////////////////


// I date objects si possono anche CONFRONTARE:

const capodanno = new Date("2024-12-31");
const primoGennaio = new Date("2025-01-01");

if(primoGennaio > capodanno) {
    console.log("Il primo gennaio è dopo capodanno");
} else {
    console.log("Il primo gennaio è prima di capodanno");
}

