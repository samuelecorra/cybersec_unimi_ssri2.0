/* Il costruttore in Javascript funziona analogamente a quanto abbiamo visto
in Java: è un metodo speciale che viene chiamato quando si crea un nuovo oggetto.
Questo risulta utile quando bisogna creare mooolti oggetti simili tra loro. */

/* Per poter usufruire del costruttore, la cui keyword è "new", definiamo
quella che si chiama funzione costruttrice, specificando dunque i parametri
che vogliamo in ingresso quando la costruiremo. Chiaramente è un ottimo
modo di iniziare a sfruttura il this, dato che il contesto sarà separato
per ogni invocazione del costruttore! */

function Persona(nome, cognome, età, èStudente) {
    this.nome = nome;
    this.cognome = cognome;
    this.età = età;
    this.èStudente = èStudente;
    this.saluta = function() {
        console.log("Ciao, sono " + this.nome + " " + this.cognome);
    };
    this.diceEtà = function() {
        console.log("Io, " + this.nome + ", ho " + this.età + " anni.");
    };
}

// Il costruttore si chiama e ritorna esattamente gli oggetti desiderati, che
// vanno dunque assegnati a opportune variabili:
const persona1 = new Persona("Principessa", "Gommarosa", 20, true);
const persona2 = new Persona("Cyber", "Genio", 21, true);

// Fatto ciò, possiamo tranquillamente chiamare i metodi dei nostri oggetti
// appena creati:
persona1.saluta(); // Stampa "Ciao, sono Principessa Gommarosa"
persona1.diceEtà(); // Stampa "Io, Principessa, ho 20 anni."

persona2.saluta(); // Stampa "Ciao, sono Cyber Genio"
persona2.diceEtà(); // Stampa "Io, Cyber, ho 21 anni."