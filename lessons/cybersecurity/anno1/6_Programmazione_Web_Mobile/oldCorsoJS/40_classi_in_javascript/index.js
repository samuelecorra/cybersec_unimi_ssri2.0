// Le classi in Javascript sono una sintassi più recente, introdotta dall'ES6, acronimo che sta per
// ECMAScript 6. Questa sintassi è più semplice e serve per creare oggetti e gestire l'ereditarietà. */

// Qualche esempio già per capire dove siamo diretti: static, encapsulation, inheritance...

// Partiamo da un esempio di oggetto dichiarato come nella scorsa lezione:

// Piccola precisazione: dal momento che in questi esempi didattici stiamo usando output di stringhe
// per confermare quanto vogliamo ottenere, si ricordi che il layout italiano della tastiera ci esclude
// la possibilità di usare i backticks per delimitare le stringhe.
// Chiaramente il sotterfugio c'è, ed è ALT+96 sul tastierino numerico per ottenere i backticks,
// ma è dispendioso ogni volta... sarebbe dunque preferibile usare la sintassi classica di Java con doppie
// virgolette e + per la concatenazione, ma...

/* Template literal (backtick + dollaro)

Questa è la “novità” di JS moderno (ES6 in poi).
Si usano i backtick (`) invece delle virgolette e la sintassi ${...}` per interpolare valori dentro la stringa:
Il ${...} è un placeholder:
tutto quello che ci scrivi dentro viene valutato come espressione JS, poi convertito in stringa automaticamente.

Perché allora esistono i backtick con ${}

Sono più leggibili quando hai tante concatenazioni → eviti di scrivere un sacco di + " ... " +.
Supportano stringhe multilinea senza dover mettere \n.
Sono più moderni e usati tantissimo dagli sviluppatori americani ed europei.

🔹 Quindi: quale usare?
Se ti trovi meglio, puoi continuare a usare lo stile "stringa " + variabile → è assolutamente valido.
Ma i template literal con backtick sono oggi considerati la best practice in JS, proprio per chiarezza 
e comodità. */

function Product (articolo, prezzo) {
    this.articolo = articolo;
    this.prezzo = prezzo;
    this.getInfo = function() {
        console.log(`Articolo: ${this.articolo}`);
        console.log(`Prezzo: ${this.prezzo.toFixed(2)}€`);  // Il metodo toFixed serve a formattare il numero
                                                            // non oltre i centesimi
    };

    // Diamo due versioni, quella del corso YT e la mia:

    this.applicaIVA1 = function() {
        return this.prezzo + (this.prezzo * iva); // 22% di IVA
    };

    this.applicaIVA2 = function() {
        const prezzoConIVA = this.prezzo * 1.22;
        console.log(`Prezzo con IVA: ${prezzoConIVA.toFixed(2)}€`);
    };
    
}

// Ora definiamo l'iva:
const iva = 0.22; // 22% di IVA

// E creiamo tre prodotti:
const prodotto1 = new Product("Borsa Chanel", 399.90);
const prodotto2 = new Product("Funko Pop Drako Malfoy", 54.99);
const prodotto3 = new Product("MSI Katana Gaming Laptop", 1199.99);

// Ora si che possiamo utilizzare i metodi dei prodotti:
prodotto1.getInfo();
prodotto1.applicaIVA1();
prodotto1.applicaIVA2();

prodotto2.getInfo();
prodotto2.applicaIVA1();
prodotto2.applicaIVA2();

prodotto3.getInfo();
prodotto3.applicaIVA1();
prodotto3.applicaIVA2();

/////////////////////////////////////////////////////////////////////////////

// stampiamo in console una linea di separazione
console.log("///////////////////////////////////////////////////////////////");

// Ora rivoluzioniamo il tutto con le classi:
// La keyword è ovviamente class:
class RegaloPerPrincipessa {

    // All'interno della graffa, poniamo innanzitutto la keyword constructor,
    // che produrrà un effetto analogo al new di prima:
    constructor(articolo, prezzo) {
        this.articolo = articolo;
        this.prezzo = prezzo;
    }

    // Si noti che si predilige la classe rispetto agli oggetti tradizionali
    // poiché la sintassi è più semplice: non dobbiamo più apporre = function
    // nel momento in cui definiamo un metodo per la nostra classe:

    getInfo() {
        console.log(`Articolo: ${this.articolo}`);
        console.log(`Prezzo: ${this.prezzo.toFixed(2)}€`);
    }

    applicaIVA(iva) {
        return this.prezzo + (this.prezzo * iva);
    }
}

// Con:
const impostaValoreAggiunto = 0.22;

// Dopo aver definito la classe, chiamiamo il costruttore:
const regalo1 = new RegaloPerPrincipessa("Borsa Chanel", 399.90);
const regalo2 = new RegaloPerPrincipessa("Funko Pop Drako Malfoy", 54.99);
const regalo3 = new RegaloPerPrincipessa("MSI Katana Gaming Laptop", 1199.99);

// Ora si che possiamo utilizzare i metodi dei regali:
regalo1.getInfo();
regalo1.applicaIVA(impostaValoreAggiunto);
const prezzoConIVA1 = regalo1.applicaIVA(impostaValoreAggiunto);
console.log(`Prezzo con IVA (regalo1): ${prezzoConIVA1.toFixed(2)}€`);

regalo2.getInfo();
const prezzoConIVA2 = regalo2.applicaIVA(impostaValoreAggiunto);
console.log(`Prezzo con IVA (regalo2): ${prezzoConIVA2.toFixed(2)}€`);

regalo3.getInfo();
const prezzoConIVA3 = regalo3.applicaIVA(impostaValoreAggiunto);
console.log(`Prezzo con IVA (regalo3): ${prezzoConIVA3.toFixed(2)}€`);
