
/* Getters e Setters in JavaScript:

Getters: metodi speciali che rendono una proprietà "leggibile", ovvero
permettono di accedere al valore di una proprietà in modo controllato.
Setters: metodi speciali che rendono una proprietà "scrivibile", ovvero
permettono di modificare il valore di una proprietà in modo controllato.
*/

class Rettangolo {

    constructor(base, altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    // Se volessimo assicurarci che l'utente non inserisca valori inappropriati,
// ecco che entra in gioco il setter: facciamogli valutare e validare solo
// oggetti che abbiano una base/altezza che sia un numero positivo:

    set base(nuovaBase) {
        if (typeof nuovaBase === "number" && nuovaBase > 0) {
            this._base = nuovaBase;
        } else {
            console.error("La base deve essere un numero positivo.");
        }
    }

    set altezza(nuovaAltezza) {
        if (typeof nuovaAltezza === "number" && nuovaAltezza > 0) {
            this._altezza = nuovaAltezza;
        } else {
            console.error("L'altezza deve essere un numero positivo.");
        }
    }

    get base() {
        return `${this._base.toFixed(1)}cm`;
    }

    get altezza() {
        return `${this._altezza.toFixed(1)}cm`;
    }

    get area() {
        return `${(this._base * this._altezza).toFixed(1)}cm²`;
    }

}

/* Quando si definiscono getter e setter in una classe JavaScript, 
è necessario distinguere tra la proprietà “pubblica” (cioè quella a cui accediamo
con rettangolo.base o persona.nome) e la variabile interna effettiva che
conserva il dato. 

Se dentro al setter scrivessimo this.base = nuovaBase, il codice richiamerebbe
nuovamente lo stesso setter, causando un loop ricorsivo infinito. 
Per evitare ciò si adotta una convenzione: 
si salva il valore in una proprietà con un nome leggermente diverso, di solito
anteponendo un underscore (_base, _nome, _età). 
Questo piccolo accorgimento permette ai metodi getter e setter di funzionare 
correttamente, distinguendo tra l’interfaccia pubblica della classe 
(la proprietà “senza underscore”) e l’implementazione interna 
(la variabile “con underscore”). 
In altre parole, l’underscore non è un vero meccanismo di privacy del linguaggio, 
ma una convenzione di stile utile per separare ciò che l’utente della classe 
vede e modifica da ciò che il programmatore usa internamente per memorizzare
i dati. */


// Ora ci creiamo un oggetto rettangolo
const rettangolo1 = new Rettangolo(5, 10);

// Proviamo a stamparne i dati:
console.log("Base:", rettangolo1.base);
console.log("Altezza:", rettangolo1.altezza);
console.log("Area:", rettangolo1.area);

// Il browser stamperà undefined poiché risultano comunque private e quindi
// non-readable.

// Ecco che dobbiamo aggiungere anche i getter methods.

console.log("////////////////////////////////////////////");

class Persona {

    constructor(nome, cognome, età) {
        this.nome = nome;
        this.cognome = cognome;
        this.età = età;
    }

    set nome(nuovoNome) {
        if (typeof nuovoNome === "string" && nuovoNome.trim() !== "") {
            this._nome = nuovoNome; // Ci assicura che il nome non sia vuoto;
            // potremmo anche fare && nuovoNome.length > 0 ma è più semplice così
        } else {
            console.error("Il nome deve essere una stringa non vuota.");
        }
    }

    // idem:
    set cognome(nuovoCognome) {
        if (typeof nuovoCognome === "string" && nuovoCognome.trim() !== "") {
            this._cognome = nuovoCognome;
        } else {
            console.error("Il cognome deve essere una stringa non vuota.");
        }
    }

    // analogamente...
    set età(nuovaEtà) {
        if (typeof nuovaEtà === "number" && nuovaEtà >= 0) {
            this._età = nuovaEtà;
        } else {
            console.error("L'età deve essere un numero positivo o zero.");
        }
    }
    
    get nome() {
        return this._nome;
    }

    get cognome() {
        return this._cognome;
    }

    get età() {
        return this._età;
    }

    get nominativo() {
        return `${this._nome} ${this._cognome}`;
    }

}

// Siamo pronti alle calls dei metodi:
const persona1 = new Persona("Principessa", "Gommarosa", 20);
console.log("Nome:", persona1.nome);
console.log("Cognome:", persona1.cognome);
console.log("Età:", persona1.età);
console.log("Nominativo:", persona1.nominativo);

// See you next time!