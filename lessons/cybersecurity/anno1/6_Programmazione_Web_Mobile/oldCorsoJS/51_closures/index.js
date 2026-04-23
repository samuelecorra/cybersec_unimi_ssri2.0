/*
Le closures sono una caratteristica fondamentale di JavaScript che consente di
creare funzioni con uno scope lessicale. In altre parole, una closure è una 
funzione che "ricorda" il contesto in cui è stata creata, anche quando viene 
eseguita al di fuori di quel contesto.

Le closures sono dunque funzioni definite all'interno di altre funzioni, e la più
interna ha accesso alle variabili e a tutto lo scope della funzione esterna.
Questo ci consente di fare in modo di proteggere le variabili che vogliamo
rendere private - e permette anche la manutenzione dello stato interno.

Il loro uso è principalmente sfruttato nei JS Framework come React, dove
vengono utilizzate per gestire lo stato dei componenti. */

function outerBanks() {
    let messaggio ="Benvenuti a Poguelandia";

    function innerBanks() {
        console.log(messaggio);
    }

}

// ATTENZIONE: se proviamo a chiamare:
outerBanks(); // non accede al messaggio e dunque non accade nulla...

// Proviamo a rifare l'esterna ma aggiungendoci alla fine la chiamata alla inner:

function outerBanks2() {
    let messaggio ="Benvenuti a Poguelandia";

    function innerBanks() {
        console.log(messaggio);
    }

    innerBanks();
} // tutto questo è parte di una closure

// Ora si noti che messaggio è privato, ovvero se tentassi di modificarlo:
messaggio = "Nuovo messaggio";

// E chiamo la outer:
outerBanks2(); // ottengo comunque "Benvenuti a Poguelandia"
// poiché siamo fuori dall'outer scope!


//////////////////////////////////////////////////////////////////////////////

// Discutiamo un attimo la questione del "mantenere lo stato":

function incrementa() {
    let contatore = 0; // variabile privata
    contatore++; // incremento
    console.log(`contatore incrementato a: ${contatore}`)
}

// Chiamandola una volta:
incrementa(); // stampiamo 1
// MA richiamandola, si RIDICHIARA la variabile privata interna e quindi:
incrementa(); // stampiamo sempre 1!
incrementa(); // stampiamo sempre 1!
incrementa(); // stampiamo sempre 1!

// Potremmo anche:

let contatore = 0;

function incrementa2() {
    contatore++; // incremento
    console.log(`contatore incrementato a: ${contatore}`)
}

// Ma otterremmo 0 sicurezza su tale variabile, "sprivatizzandola!"

incrementa2(); // stampiamo 1
incrementa2(); // stampiamo 2
incrementa2(); // stampiamo 3
incrementa2(); // stampiamo 4 etc...

// Lo stato viene mantenuto MA non abbiamo più privacy su contatore!


////////////////////////////////////////////////////////////////////////////////

// Ma allora ci risulta congeniale racchiudere il tutto in una funzione esterna
// che faccia da closure:

function creaContatore() {
    
    let contatore = 0;

    function incrementa() {
        contatore++;
        console.log(`contatore incrementato a: ${contatore}`)
    }

    // Cosa vogliamo ritornare? un oggetto:
    return { incrementa }; // possiamo usare la sintassi shorthand, anche se
    // per esteso dovremmo dichiarare proprietà e valore, ovvero:
    // incrementa: incrementa, ma è comoda la prima opzione!
}

// Ora tale valore di ritorno può essere assegnato comodamente a una nostra
// variabile arbitraria che diventerà il nostro oggetto contatore:

const contatore1 = creaContatore();

// Al quale possiamo accedere alle funzioni
contatore1.incrementa(); // contatore incrementato a: 1
contatore1.incrementa(); // contatore incrementato a: 2
contatore1.incrementa(); // contatore incrementato a: 3

// SE PRIMA USAVAMO LE CLASSI PER ISTANZIARE OGGETTI, IN QUESTO CASO SFRUTTIAMO
// UNA FUNZIONE PER FARCI RITORNARE UN OGGETTO.

// Proviamo a stampare il valore attuale del contatore fuori dalla funzione:
console.log(contatore1.contatore); // undefined
// Questo perché la proprietà è stata privatizzata!

// Per resettare il contatore potremmo ingenuamente pensare di:
contatore1.contatore = 0;

// Ma poi se incrementiamo e ci aspettiamo 1, invece:
contatore1.incrementa(); // contatore incrementato a: 4

// Quindi bisogna creare una funzione INTERNA per resettare il contatore
// e per farcelo vedere:
// RI-PROGETTIAMO:

function creaContatore2() {

    let contatore = 0;

    function incrementa() {
        contatore++;
        console.log(`contatore incrementato a: ${contatore}`)
    }

    function mostraContatoreAttuale() {
        console.log(`Valore attuale del contatore: ${contatore}`);
    }

    function resetta() {
        contatore = 0;
    }

    return { incrementa, mostraContatoreAttuale, resetta };
}

const contatore2 = creaContatore2();

contatore2.incrementa(); // contatore incrementato a: 1
contatore2.incrementa(); // contatore incrementato a: 2

contatore2.resetta();
contatore2.mostraContatoreAttuale(); // Valore attuale del contatore: 0

contatore2.incrementa(); // contatore incrementato a: 1


////////////////////////////////////////////////////////////////////////////////


// ESEMPIO FINALE:
// Creiamo una closure per un gioco in cui teniamo traccia di un punteggio:


function creaGioco() {
    let score = 0;

    function increaseScore(points) {
        score += points;
        console.log(`Punteggio aumentato di ${points}.`);
    }

    function decreaseScore(points) {
        score -= points;
        console.log(`Punteggio diminuito di ${points}.`);
    }

    function getScore() {
        return score;
    }

    return { increaseScore, decreaseScore, getScore };
}
const gioco = creaGioco();

gioco.increaseScore(10);
gioco.decreaseScore(5);

console.log(`Punteggio finale: ${gioco.getScore()}`);

// Con la closure non possiamo più modificare esternamente la variabile score!

// React, VUE e Angular si basano su concetti simili di stato e gestione delle
// modifiche!