/* Lo scope di una variabile è definito come l'area del codice in cui la 
variabile è accessibile. In JavaScript, ci sono due tipi principali di scope:

1) Global Scope: le variabili dichiarate al di fuori di qualsiasi funzione o 
blocco sono nel global scope e possono essere accessibili da qualsiasi parte 
del codice.

2) Local Scope: le variabili dichiarate all'interno di una funzione o di un 
blocco (come un'istruzione if o un ciclo) sono nel local scope e possono essere 
accessibili solo all'interno di quella funzione o blocco.

Ecco un esempio per illustrare la differenza: */

let x = 10; // variabile globale

function esempioScope() {
    let y = 5; // variabile locale
    console.log(x); // 10
    console.log(y); // 5
}

esempioScope(); // Qui outputta 10 e 5

// Ma cercando le variabili esternamente dal blocco-funzione...
console.log(x); // 10
console.log(y); // ReferenceError: y is not defined

// ATTENZIONE: IN QUALSIASI SCOPE CI TROVIAMO, CI SONO DUE COSE DA TENERE A MENTE:
// LE VARIABILI GLOBALI SONO SEMPRE ACCESSIBILI;
// OGNI VARIABILE DI QUEL BLOCCO DEVE AVERE IDENTIFICATORE UNIVOCO!

// NON POSSO:
// let x = 1;
// let x = 2;     NO NO NO!

// PERO' POSSO:

function function1() {
    let x = 1;
    console.log(x);
}

function function2() {
    let x = 2;
    console.log(x);
}

function1(); // 1
function2(); // 2

// E ovviamente le globali come detto possono sempre essere accessibili:
// Si pensi allo scope locale come gli appartamenti vicini dei quali dalla
// nostra finestra non possiamo vedere gli interni, e si pensi al globale
// come alla strada sottostante che qualunque persona di qualunque appartamento
// può vedere.

// Quindi
let z = 3;

function function3() {
    console.log(z);
}

function3(); // 3 senza alcun problema di scope!

// LE GLOBALI SONO SCONSIGLIATISSIME COL CRESCERE DI DIMENSIONI E COMPLESSITA'
// DEI NOSTRI PROGRAMMI PERCHE' POSSONO CREARE CONFLITTI E COMPORTAMENTI 
// IMPREVEDIBILI!

// ricordando che z = 3, se avessimo:

function function4() {
    z = 1;
    console.log(z);
}

function4(); // 1 VINCE LO SCOPE LOCALE!