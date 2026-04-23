/* La keyword static in Javascript definisce delle proprietà o dei metodi
che appartengono a una classe e non a una istanza specifica di quella classe.
Questo significa che possono essere chiamati direttamente sulla classe stessa,
anziché su un oggetto creato da quella classe. */

class MathUtils {
    static pigreco = 3.14159;

    static calcolaDiametro(raggio) {
        return 2 * raggio;
    }

    static calcolaCirconferenza(raggio) {
        return 2 * this.pigreco * raggio;
    }

    static calcolaArea(raggio) {
        return this.pigreco * raggio ** 2;
    }
}

console.log(MathUtils.pigreco); // Stampa 3.14159 SENZA creare un'istanza
// di MathUtils! idem per le prossime tre istruzioni...

console.log(MathUtils.calcolaDiametro(5)); // Stampa 10
console.log(MathUtils.calcolaCirconferenza(5)); // Stampa 31.4159
console.log(MathUtils.calcolaArea(5)); // Stampa 78.53975

// Stampiamo una fila di barre separatrici per il prossimo paragrafo della lezione:
console.log("///////////////////////////////////////////////////////////////");

class Utente{

    static numeroUtenti = 0;

    constructor(nome) {
        this.nome = nome;
        Utente.numeroUtenti++;  // Per accedere a un attributo statico bisogna
        // tassativamente utilizzare il nome della classe, non this!
    }

    // metodo non-statico (ovvero di istanza)
    saluta() {
        console.log(`Ciao, il mio username è ${this.nome}`);
    }

    // e infine metodo statico, o "di classe"
    static salutaTutti() {
        console.log(`Ciao, siete in ${Utente.numeroUtenti} utenti online!`);
    }
}

const utente1 = new Utente("Principessa Gommarosa");
const utente2 = new Utente("Cyber Genio");
const utente3 = new Utente("Draco Malfoy");

console.log(utente1.nome); // Stampa "Principessa Gommarosa"
console.log(utente2.nome); // Stampa "Cyber Genio"
console.log(utente3.nome); // Stampa "Draco Malfoy"
console.log(Utente.numeroUtenti);  // Stampa 3

// Ora chiamiamo il metodo non-statico "saluta" su ciascun oggetto
utente1.saluta();  // Stampa "Ciao, il mio username è Principessa Gommarosa"
utente2.saluta();  // Stampa "Ciao, il mio username è Cyber Genio"
utente3.saluta();  // Stampa "Ciao, il mio username è Draco Malfoy"

// E infine chiamiamo il metodo statico:
Utente.salutaTutti();  // Stampa "Ciao, siete in 3 utenti online!"