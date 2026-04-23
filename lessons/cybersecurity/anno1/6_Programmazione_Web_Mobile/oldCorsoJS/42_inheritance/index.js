// L'ereditarietà - in inglese inheritance - in Javascript è un concetto/keyword
// che permette di creare una nuova classe a partire da una classe esistente.
// La nuova classe, chiamata classe figlia (o sottoclasse), eredita tutte le
// proprietà e i metodi della classe genitore (o superclasse), e può anche
// definirne di propri o sovrascrivere quelli ereditati.

class Animale{
    èVivo = true;

    mangia() {
        // se nella classi figlie il nome è statico, dobbiamo allora:
        console.log("Questo " + this.constructor.nome + " sta mangiando...");
    }
    // this.constructor → restituisce la funzione costruttrice usata per creare 
    // quell’oggetto (es. coniglio).
    // this.constructor.nome → va a prendere la proprietà static nome della 
    // classe coniglio.
    
    dorme() {
        console.log("Questo " + this.constructor.nome + " sta dormendo...");
    }
    // idem
}

// Ora creiamo le classi figlie...

class coniglio extends Animale {
    // Ci è congeniale anche anteporre static al nome, in quanto il nome
    // è un attributo di classe, non tanto del singolo coniglio creato!
    static nome = "coniglio";

    salta() {
        console.log("Il " + coniglio.nome + " sta saltando...");
    }
}

class pesce extends Animale {
    static nome = "pesce";

    nuota() {
        console.log("Il " + pesce.nome + " sta nuotando...");
    }
}

class falco extends Animale {
    static nome = "falco";

    vola() {
        console.log("Il " + falco.nome + " sta volando...");
    }
}

// Ora creiamo un'istanza delle nostre classi figlie:

const coniglio1 = new coniglio();
const pesce1 = new pesce();
const falco1 = new falco();

// Ora controlliamo...

console.log(coniglio1.èVivo); // Stampa true
// Ora, se il falco sbrana il coniglio, cosa succede?
coniglio1.èVivo = false;
console.log(coniglio1.èVivo); // Stampa false, perché il coniglio è stato sbranato

// Resuscitiamolo...
coniglio1.èVivo = true;

coniglio1.mangia();
coniglio1.dorme();

pesce1.mangia();
pesce1.dorme();

falco1.mangia();
falco1.dorme();

// Ci siamo risparmiati la riscrittura di tutti i metodi per ogni specifico
// animale, utilizzando this.name e lasciando che le classi figlie ereditassero
// il comportamento della classe genitore.
// Possiamo anche chiamare i metodi specifici delle classi figlie:

coniglio1.salta();
pesce1.nuota();
falco1.vola();
