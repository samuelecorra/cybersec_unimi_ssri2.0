/* La keyword this in Javascript riprende il concetto di base che avevamo già
ampiamente trattato in Java: il riferimento all'oggetto corrente.
ATTENZIONE: l'oggetto dipende dal contesto immediato in cui si sta lavorando */

// Vediamolo subito: rifacciamo::
const persona = {
    nome: "Principessa",
    cognome: "Gommarosa",
    età: "20",
    èStudentessa: true,
    saluta: function() {
        console.log("Ciao, sono " + this.nome + " " + this.cognome);
    },
    diceEtà: function() {
        console.log("Io, " + this.nome + ", ho " + this.età + " anni.");
    }
}

// Nella funzione saluta, this si riferisce all'oggetto persona
persona.saluta(); // Stampa "Ciao, sono Principessa Gommarosa"
persona.diceEtà(); // Stampa "Io, Principessa, ho 20 anni."

// La comodità risiede nel fatto che se duplico l'oggetto e cambio gli "attributi"
// ma non i metodi, i nuovi metodi si riferiranno comunque alla seconda persona:

const persona2 = {
    nome: "Cyber",
    cognome: "Genio",
    età: "21",
    èStudente: true,
    saluta: function() {
        console.log("Ciao, sono " + this.nome + " " + this.cognome);
    },
    diceEtà: function() {
        console.log("Io, " + this.nome + ", ho " + this.età + " anni.");
    }
}

persona2.saluta(); // Stampa "Ciao, sono Cyber Genio"
persona2.diceEtà(); // Stampa "Io, Cyber, ho 21 anni."

// Se però tentassimo di invocare this come stampa in console...
console.log(this);
// Otteniamo l'oggetto globale, che è window, ovvero la finestra del browser
// nella quale tutto il nostro codice sta girando.

// ATTENZIONE - L'uso del this non funziona se si prova a prototipare una funzione
// con =>, in quanto la "freccia" non crea un nuovo contesto di esecuzione.