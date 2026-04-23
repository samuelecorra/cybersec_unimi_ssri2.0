/* La prima cosa da fare quando si inizia a programmare in Javascript è
l'utilizzo della console, ovvero del nostro strumento principale per il debug e 
l'output in generale.

Possiamo accedere alla console mediante i DevTools del browser nel quale
apriamo il nostro file HTML.

Right click -> inspect -> appariranno i DevTools, con una navbar orizzontale:
elements, console, sources, network...

Clicchiamo su console. Ecco il nostro terminale col quale interfacciarci d'ora
in avanti. Iniziamo a scrivere qualche riga di codice.

L'output di base si effettua con il comando console.log(), e all'interno della
parentesi, come parametro, si possono passare variabili, stringhe, numeri e
oggetti. Ad esempio:

console.log("Ciao, mondo!");
console.log(42);
console.log({ nome: "Mario", eta: 30 });

In questo modo, possiamo visualizzare informazioni utili durante lo sviluppo
e il debug del nostro codice.

----------------------------------------------------------------------------------

Partiamo analizzando i vari metodi di output testuale, ovvero di stringhe semplici:
*/

// La primissima riga di codice javascript effettivo è
console.log("La console è finalmente in funzione ;)");
// Per vedere il risultato di questa istruzione, è necessario aprire la console,
// del browser - si noti che esiste lo shortcut F12 (o Ctrl + Shift + I su 
// Windows/Linux, Cmd + Option + I su Mac).

// Si può continuare a mettere output in console ri-scrivendo il comando eseguito poc'anzi:
console.log('Tutti i sistemi sono operativi e collaudati - ora sono cazzi vostri! ;)');

// Per racchiudere la stringa che vogliamo outputtare, abbiamo tre modi:

// 1. Doppie virgolette (come già eravamo abituati su Java e C);
// 2. Virgolette singole (apici);
// 3. backticks, non presenti su ITKB ma raggiungibili con ALT + 96: `Ciao`

console.log(`Il millenium bug ormai è un ricordo lontano, ma i bug restano!`);

// ATTENZIONE: quando si usano i backticks, si parla di template literals, ovvero
// stringhe che possono contenere espressioni JavaScript al loro interno.
// Sono molto utili per creare stringhe multilinea o per interpolare variabili.
// E sono a tutti gli effetti lo standard di JavaScript per la gestione 
// delle stringhe.

/* 1. Che cos’è un template literal in JavaScript
    Un template literal (in italiano si potrebbe rendere come “stringa modello”) è 
    una stringa racchiusa tra backticks ( ` ` ) invece che tra apici singoli ' ' o 
    doppi " ".

    Esempio: */
    const nome = "Bello";
    console.log(`Ciao, ${nome}!`); /* Ciao, Bello!

2. Cosa hanno di speciale rispetto alle stringhe "normali"
    I template literals non sono solo una sintassi alternativa per scrivere 
    stringhe, ma portano tre vantaggi principali:

        (a) Interpolazione di variabili/espressioni
        Con ${ ... } puoi inserire direttamente il valore di una variabile o di 
        un’espressione dentro la stringa, senza dover concatenare con +:    */
        const a = 5, b = 3;
        console.log(`${a} + ${b} = ${a + b}`); 
        /* output: 5 + 3 = 8

        In Java o C avresti fatto:
        System.out.println(a + " + " + b + " = " + (a+b));

        In JS pre-ES6:
        console.log(a + " + " + b + " = " + (a+b));

        → Con i template literals è molto più leggibile.

        (b) Multilinea senza escape
        Con " o ' dovevi scrivere:  */
        const testobefore = "Prima riga\nSeconda riga";

        // Con i backticks puoi andare a capo direttamente:
        const testoafter = `Prima riga
        Seconda riga`;  /*  L’andata a capo viene rispettata.

        (c) Tagged templates (avanzato)

        Puoi associare una funzione che “interpreta” la stringa:    */

        function evidenzia(strings, ...values) {
        return strings[0] + values.map(v => `**${v}**`).join(strings[1]);
        }

        const nome1 = "Bello";
        console.log(evidenzia`Ciao, ${nome1}, benvenuto!`);
        /* Output: Ciao, **Bello**, benvenuto!

        Questa feature viene usata in librerie moderne (React styled-components, 
        GraphQL, ecc.) per costruire DSL (mini-linguaggi dentro JavaScript).

3. Perché è diventato lo standard

    - Sintassi più leggibile: niente concatenazioni con +.

    - Codice più vicino al linguaggio naturale: scrivi stringhe lunghe, 
    multilinea, con variabili integrate in modo chiaro.

    - Maggiore espressività: i tagged templates permettono cose impossibili 
    con le stringhe classiche.

    - Consistenza con altri linguaggi moderni: anche Python ha f-strings, 
    Kotlin ha le string templates, Swift ha l’interpolazione con \( ). 
    JavaScript si è adeguato con ES6 (2015).

4. In definitiva:

    I backticks non sono stati scelti a caso:

    " " e ' ' erano già usati per le stringhe classiche → serviva un nuovo 
    delimitatore.

    ``` era un carattere “libero”, non fondamentale in JS (al contrario di C, 
    dove ha altri usi).

    Permette di distinguere visivamente subito una stringa dinamica (template)
    da una stringa “statica”.

    👉 Quindi: non è che i template literals sostituiscano sempre " o '. 
    Quelli restano per le stringhe semplici, statiche.
    Ma ormai, quando serve interpolazione o multilinea, i backticks sono lo 
    standard.   */


/////////////////////////////////////////////////////////////////////////////////


// Vediamo subito come scriptare una finestrella di alert, che è sempre utile
// per comunicare con l'utente.
window.alert("Stai navigando in acque pericolose, just so you know...");

// Per chiudere l'alert, basta cliccare su "OK" nella finestrella che appare.

// E se subito dopo ne mettiamo un altro? Semplicemente, dopo il primo OK 
// apparirà anche l'altro:
window.alert("Dico davvero, se qualcuno scopre che sei qui, sono guai!");
// Per chiudere il secondo alert, si clicca di nuovo su "OK".

// Ora che abbiamo i fondamenti di base, possiamo iniziare a popolare la nostra 
// pagina web utilizzando JavaScript per modificare il contenuto degli elementi 
// HTML. Per fare ciò, si passi alla lezione 1.2!