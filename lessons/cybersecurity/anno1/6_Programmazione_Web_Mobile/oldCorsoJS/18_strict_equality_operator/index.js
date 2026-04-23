/* Gli uguali su Javascript hanno significati diversi in base alla sintassi
specifica in cui li si digita. Mi spiego meglio...

// 1. = assignment;
// 2. == loose equality;
// 3. === strict equality;
// 4. != loose inequality;
// 5. !== strict inequality;

Ora ci occupiamo principalmente dei due strict: */

const PI = 3.14;

if (PI == `3.14`) {
    console.log("PI == `3.14` è true"); // accadrà questo
}
else {
    console.log("PI == `3.14` è false");
}

// In quanto nonostante la variabile sia un numero e venga confrontata con una
// stringa, il double == non tiene conto del tipo e quindi restituisce true.

// Allora, per forza di cose, noteremo che:

if (PI === `3.14`) {
    console.log("PI === `3.14` è true");
}
else {
    console.log("PI === `3.14` è false"); // accadrà questo
}

// In quanto in questo caso, il triple === tiene conto del tipo e quindi 
// restituisce false.

// E quindi:

if (PI === 3.14) {
    console.log("PI === 3.14 è true"); // accadrà questo
}
else {
    console.log("PI === 3.14 è false");
}


////////////////////////////////////////////////////////////////////////////////


// Se vogliamo invece verificare con il loose inequality operator:
// Stiamo dunque chiedendo se il Number PI è diverso dalla String `3.14`:
// questo è falso, poiché 3.14 == `3.14` è true:
if (PI != `3.14`) {
    console.log("Il Number PI != `3.14` è false"); // NO!
}
else {
    console.log("PI != `3.14` è false"); // accadrà questo!
}

// E dal momento che PI === `3.14` è false, se poniamo invece lo strict
// inequality operator otterremo true finendo nel primo ramo:

if (PI !== `3.14`) {
    console.log("Il Number PI !== `3.14` è true"); // accadrà questo
}
else {
    console.log("PI !== `3.14` è false");
}


////////////////////////////////////////////////////////////////////////////////