// Gli oggetti in Javascript sono l'evoluzione architetturale delle
// struct del linguaggio C.
// Gli oggetti in JavaScript sono utilizzati per memorizzare coppie chiave-valore
// e possono contenere proprietà e metodi.
// Delle "collezioni", potremmo dire...

// Sintassi generale:
/* object = {
            chiave: valore,
            chiave2: valore2,
            function(){}
        }                       */

const persona = {
    nome: "Principessa",
    cognome: "Gommarosa",
    età: "20",
    èStudentessa: true, // l'ultima virgola è facoltativa ma buona prassi
    salutaPrincipessa: function() { // Definisco un metodo per l'oggetto persona
        console.log("Ciao, sono " + persona.nome + " " + persona.cognome);
    },

    // Mostriamo anche la sintassi alternativa per prototipare la funzione/metodo,
    // ovvero quella con la "freccia"
    cosaMangiaPrincipessa: () => {
        console.log("Mi piace mangiare le gomme, un piatto tipico dei cartoon!");
    },
}

const persona2 = {
    nome: "Cyber",
    cognome: "Genio",
    età: "21",
    èStudente: true,
    salutaCyber: function() { // Definisco un metodo per l'oggetto persona2
        console.log("Ciao, sono " + persona2.nome + " " + persona2.cognome);
    }
}

console.log(persona.nome); // Stampa "Principessa"
console.log(persona.cognome); // Stampa "Gommarosa"
console.log(persona.età); // Stampa "20"
console.log(persona.èStudentessa); // Stampa true

console.log(persona2.nome); // Stampa "Cyber"
console.log(persona2.cognome); // Stampa "Genio"
console.log(persona2.età); // Stampa "21"
console.log(persona2.èStudente); // Stampa true

// Chiamo il metodo salutaPrincipessa dell'oggetto persona
persona.salutaPrincipessa(); // Stampa "Ciao, sono Principessa Gommarosa"

// Chiamo anche il metodo salutaCyber dell'oggetto persona2
persona2.salutaCyber(); // Stampa "Ciao, sono Cyber Genio"

// Chiamo anche il metodo con la sintassi alternativa:
persona.cosaMangiaPrincipessa(); // Stampa "Mi piace mangiare le gomme, un piatto tipico dei cartoon!"   