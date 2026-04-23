/* Se invece volessimo ridurre gli elementi di un array ad un singolo elemento,
ci viene in soccorso il metodo reduce(). */

// Esso chiama una funzione di callback su ogni elemento dell'array, passando
// l'accumulatore e il valore corrente come argomenti. Il return value sovrascrive
// l'accumulatore per il ciclo successivo, non viene ritornato effettivamente
// nada fino alla fine delle iterazioni che vengono implicitamente eseguite!

// Sintassi generica:
// array.reduce(callback, valoreInizialeAccumulatore);


///////////////////////////////////////////////////////////////////////////////


// ESEMPIO 1:

const prezzi = [10, 20, 30, 40, 50];

function somma(accumulatore, valoreCorrente) {
    return accumulatore + valoreCorrente;
}

const totale = prezzi.reduce(somma, 0);

console.log(totale); // 150


///////////////////////////////////////////////////////////////////////////////


// ESEMPIO 2:

const voti = [18, 22, 25, 30, 28];

function getVotoMax(accumulator, currentValue) {
    return Math.max(accumulator, currentValue);
}

const votoPiùAlto = voti.reduce(getVotoMax, 0); // Inizializziamo a 0 l'accumulatore
// così che il primo confronto col primo elemento dell'array faccia sempre
// vincere quest'ultimo!

console.log(votoPiùAlto); // 30

// Idem con patate per il minimo:

function getVotoMin(accumulator, currentValue) {
    return Math.min(accumulator, currentValue);
}

const votoMinimo = voti.reduce(getVotoMin, 100); // Inizializziamo a 100 l'accumulatore
// così che il primo confronto col primo elemento dell'array faccia sempre
// vincere quest'ultimo!

console.log(votoMinimo); // 18

