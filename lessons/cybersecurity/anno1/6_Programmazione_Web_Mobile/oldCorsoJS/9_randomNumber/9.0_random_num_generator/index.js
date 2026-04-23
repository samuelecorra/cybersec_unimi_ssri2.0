/* Eccoci in un altro mini-progetto per testare quanto appreso finora...

In questo caso, realizzeremo un semplice generatore di numeri casuali.
Utilizzeremo Math.random() per ottenere un numero casuale tra 0 e 1, e poi lo scaleremo
per ottenere un numero in un intervallo specifico.

// Vi illustro meglio: */

let randomNum = Math.random();
console.log("Numero casuale tra 0 (incluso) e 1 (escluso): " + randomNum);

/* Finora tutto easy, no? Basta ricaricare la pagina per prendere coscienza
dell'effettiva randomicità.

Ma noi abbiamo tirato un dado a sei facce, ergo ci serve rollare un numero
casuale tra 1 e 6, senza includere la parte decimale.

Allora ci è congeniale moltiplicare il risultato di Math.random() per 6,
così da ottenere un numero casuale tra 0 e 6 esclusi, ovvero, in termini di
disuguaglianza:

    0 <= x < 6

Ora, dal momento che il dado non ha facce decimali, arrotondiamo per difetto
con Math.floor(). Otteniamo dunque un numero INTERO casuale tale che:
    
    1 <= x < 6

Come facciamo allora per includere anche l'estremo superiore, ovvero 6, e
allo stesso tempo precluderci la possibilità che il dado rolli 0?

Basta aggiungere 1 al risultato finale: */

let randomLancioDiDado = Math.floor(Math.random() * 6) + 1;

console.log("Lancio di dado (numero casuale tra 1 e 6): " + randomLancioDiDado);

// Se volessimo invece un numero tra 1 e 100,
// andiamo a modificare leggermente il comando:

let randomNum1to100 = Math.floor(Math.random() * 100) + 1;

console.log("Numero casuale tra 1 e 100: " + randomNum1to100);

// Se volessimo invece generalizzare la questione, bisogna ragionare con due
// variabili min e max:

let min = 2003;
let max = 2008;

let randomNumMinMax = Math.floor(Math.random() * (max - min + 1)) + min;

console.log("Numero casuale tra " + min + " e " + max + ": " + randomNumMinMax);

