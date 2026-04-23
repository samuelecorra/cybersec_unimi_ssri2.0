/* I cosiddetti switches sono una struttura di controllo che permette di
valutare un'espressione e di eseguire diversi blocchi di codice in base
al valore di quella espressione. La sintassi di base è la seguente:

switch (espressione) {
    case valore1:
        // codice da eseguire se l'espressione è uguale a valore1
        break;
    case valore2:
        // codice da eseguire se l'espressione è uguale a valore2
        break;
    default:
        // codice da eseguire se l'espressione non corrisponde a nessun valore
}
*/

// Vediamo la convenzienza mostrando dapprima un esempio prolisso e scomodo:

let day = 1;

if (day === 1) {
    console.log("Oggi è lunedì.");
} else if (day === 2) {
    console.log("Oggi è martedì.");
} else if (day === 3) {
    console.log("Oggi è mercoledì.");
} else if (day === 4) {
    console.log("Oggi è giovedì.");
} else if (day === 5) {
    console.log("Oggi è venerdì.");
} else if (day === 6) {
    console.log("Oggi è sabato.");
} else if (day === 7) {
    console.log("Oggi è domenica.");
} else {
    console.log("Giorno non valido.");
}

// Ma se invece...

switch(day) {
    case 1:
        console.log("Switch: Oggi è lunedì.");
        break;
    case 2:
        console.log("Switch: Oggi è martedì.");
        break;
    case 3:
        console.log("Switch: Oggi è mercoledì.");
        break;
    case 4:
        console.log("Switch: Oggi è giovedì.");
        break;
    case 5:
        console.log("Switch: Oggi è venerdì.");
        break;
    case 6:
        console.log("Switch: Oggi è sabato.");
        break;
    case 7:
        console.log("Switch: Oggi è domenica.");
        break;
    default:
        console.log("Switch: Giorno non valido.");
}

// I vari breaks sono TASSATIVI: se non ci fosse il break, il codice 
// continuerebbe ad essere eseguito anche per i case successivi!!!

// Vediamo un altro esempio di switch:

let testScore = 92;
let letterGrade;

switch(true) {
    case testScore >= 90:
        letterGrade = "A";
        break;
    case testScore >= 80:
        letterGrade = "B";
        break;
    case testScore >= 70:
        letterGrade = "C";
        break;
    case testScore >= 60:
        letterGrade = "D";
        break;
    default:
        letterGrade = "F";
}

console.log("Il voto è: " + letterGrade);

// Diventa utile solo in presenza di molti casi, per pochi casi è più chiaro un 
// if-else...