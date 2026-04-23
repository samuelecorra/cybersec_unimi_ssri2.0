/* L'operatore ternario decisionale non è altro che la riscrittura compatta
di if...else

SINTASSI GENERICA:
condizione ? espressione_se_vera : espressione_se_falsa;       */

let età = 21;

let messaggio = età >= 18 ? "Sei maggiorenne." : "Sei minorenne.";

    console.log(messaggio);

////////////////////////////////////////////////////////////////////////////////

let orario = 19;

let saluto = orario < 12 ? "Buongiorno!" : "Buonasera!";

    console.log(saluto);

////////////////////////////////////////////////////////////////////////////////

let èStudentessa = true;

let messaggioStudente = èStudentessa ? "La Principessa studia da mattina a sera." : "La Principessa sta lavorando.";

    console.log(messaggioStudente);

////////////////////////////////////////////////////////////////////////////////

let totAcquisto = 125;

let scontoPercent = totAcquisto >= 100 ? 10 : 0;

console.log(`Pagherai ${totAcquisto - (totAcquisto * scontoPercent / 100)} euro.`);


// Si usa molto per comodità e compattezza del codice, oltre che maggiore
// leggibilità rispetto ad ifs con condizioni e rami semplici!