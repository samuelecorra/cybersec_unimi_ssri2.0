/* Un for loop ripete una fetta di codice per un numero limitato di volte.

// Esempio: */

// PROMEMORIA: nella tonda della condition:
// Il primo spazio è riservato a dichiarare e inizializzare la/le variabile/i di 
// controllo del ciclo;
// Il secondo spazio è riservato a definire la condizione di continuazione del 
// ciclo;
// Il terzo spazio è riservato a definire l'azione da eseguire al termine di 
// ogni iterazione

for (let i = 0; i < 5; i++) {
    console.log(`Iterazione numero ${i}`);
}

// Ovviamente l'incremento di 1 è solo un esempio...
// Potremmo anche decrementare e fare il countdown per l'anno nuovo:

for (let i = 10; i > 0; i--) {
    console.log(i + `...`)
}
console.log(`Happy New Year!`)


////////////////////////////////////////////////////////////////////////////////

// Il modo built-in per skippare una determinata iterazione è quello di
// selezionare tale iterazione (controllando quando la variabile di ciclo
// coincide col numero di iterazione che vogliamo selezionare)
// e utilizzare la keyword continue per fare in modo di uscire NON DAL CICLO
// INTERO MA DALL'ITERAZIONE CORRENTE!

// Supponiamo che nella stampa degli interi da 1 a 20 vogliamo skippare sia
// 13 che 17 che sono numeri sfortunati...

for (let i = 0; i <= 20; i++) {

    if (i == 13 || i == 17) {
        console.log(`Numero Sfortunato che non citiamo...`)
        continue;
    }
    console.log(`numero non sfortunato: ${i}`);
}
console.log(`fine stampa...`);

// ERGO NON CONFONDERE BREAK CON CONTINUE!!!