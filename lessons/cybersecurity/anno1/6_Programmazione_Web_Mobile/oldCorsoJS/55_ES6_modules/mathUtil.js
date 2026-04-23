// Questo è il nostro modulo che vogliamo importare dall'index.js

// CHIARAMENTE SI ANTEPONGONO LE KEYWORD export per far sì che tali valori e 
// funzioni siano riutilizzabili altrove!

export const pigreco = 3.14;

export function areaCerchio(raggio) {
    return pigreco * raggio * raggio;
}

export function circonferenza(raggio) {
    return 2 * pigreco * raggio;
}

export function volumeSfera(raggio) {
    return (4/3) * pigreco * raggio * raggio * raggio;
}