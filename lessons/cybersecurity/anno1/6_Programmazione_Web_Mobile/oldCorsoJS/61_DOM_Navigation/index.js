// La DOM Navigation è il processo col quale possiamo spostarci tra gli elementi
// del DOM, accedendo ai nodi padre, figli e fratelli di un elemento specifico.
// Questo ci permette di esplorare e manipolare la struttura del documento in modo
// più flessibile e dinamico, USANDO JAVASCRIPT!

// Vediamo principalmente:

// 1. .firstElementChild
// 2. .lastElementChild
// 3. .nextElementSibling
// 4. .previousElementSibling
// 5. .parentElement
// 6. .children

/* Nel nostro document html, abbiamo: 

<ul id="Marche">
        <li>MSI</li>
        <li>ASUS</li>
        <li>ACER</li>
    </ul>

    <ul id="scarpe">
        <li>Jordan</li>
        <li>Airforce</li>
        <li>AirMax</li>
    </ul>

    <ul id="trucchi">
        <li>Chanel</li>
        <li>Givenchy</li>
        <li>Dior</li>
    </ul>

// Ogni unordered list ha i propri elementi figli, che tra di loro sono elementi
// fratelli (siblings). MSI, Jordan e Chanel sono i primogeniti (firstborn child),
// ASUS, Airforce e Givenchy sono i secondogeniti (secondborn child),
// ACER, AirMax e Dior sono i terzogeniti (thirdborn child). */

const marche = document.getElementById("Marche");
const primogenitoMarche = marche.firstElementChild;

// Ora posso selezionare il primogenito, ovvero MSI, e andare ad applicare
// solo a lui qualcosa:
primogenitoMarche.style.backgroundColor = "purple";

// idem se cambio i getElement per le altre ul:
const scarpe = document.getElementById("scarpe");
const primogenitoScarpe = scarpe.firstElementChild;
primogenitoScarpe.style.backgroundColor = "orange";

const trucchi = document.getElementById("trucchi");
const primogenitoTrucchi = trucchi.firstElementChild;
primogenitoTrucchi.style.backgroundColor = "pink";


///////////////////////////////////////////////////////////////////////////////

// Per velocizzare il tutto potremmo usare QueryselectorAll:
const elementiUl = document.querySelectorAll("ul");
// E sul nodo restituitoci ricordiamoci che esiste un built-in forEach:
elementiUl.forEach((ul) => {
    const primogenito = ul.firstElementChild;
    primogenito.style.fontWeight = "bold"; // grassetto a tutti i primogeniti
});


///////////////////////////////////////////////////////////////////////////////


// Facciamo lo stesso con i più giovani:
const marche2 = document.getElementById("Marche");
const ultimogenitoMarche = marche2.lastElementChild;
ultimogenitoMarche.style.fontWeight = "bold"; // grassetto a tutti gli ultimogeniti

// E ancora una volta si posson beccare tutti insieme con querySelectorAll:
const elementiUl2 = document.querySelectorAll("ul");
elementiUl2.forEach((ul) => {
    const ultimogeniti = ul.lastElementChild;
    ultimogeniti.style.backgroundColor = "lightgray";
});


////////////////////////////////////////////////////////////////////////////////


// Tornando nell'html, aggiungiamo ora degli id univoci ai vari li:

const msi = document.getElementById("msi");
const prossimoFratelloMarche = msi.nextElementSibling;
prossimoFratelloMarche.style.backgroundColor = "red";

// idem per le altre due liste così da evidenziare i secondogeniti:
const jordan = document.getElementById("jordan");
const prossimoFratelloScarpe = jordan.nextElementSibling;
prossimoFratelloScarpe.style.backgroundColor = "red";

const chanel = document.getElementById("chanel");
const prossimoFratelloTrucchi = chanel.nextElementSibling;
prossimoFratelloTrucchi.style.backgroundColor = "red";

// Il metodo è applicabile selezionato invece una ul per poi specificare il
// fratello successivo, ovvero TUTTA LA LISTA SUCCESSIVA:
const marche3 = document.getElementById("Marche");
const prossimaUl = marche3.nextElementSibling;
prossimaUl.style.color = "fuchsia";

// Il body è dunque il grandparent dei vari li, e parent degli ul.


///////////////////////////////////////////////////////////////////////////////

// Analogamente, il .previousElementSibling ci restituisce il fratello precedente:

const dior = document.getElementById("dior");
const precedenteTrucchi = dior.previousElementSibling;
precedenteTrucchi.style.color = "yellow"; // givenchy si colora di giallo

// IL PREVIOUS SIBLING DI UN FIRST CHILD NON ESISTE!!


////////////////////////////////////////////////////////////////////////////////

// poi .parentElement ci restituisce il genitore:

const acer = document.getElementById("acer");
const genitoreAcer = acer.parentElement;
genitoreAcer.style.color = "blue"; // il genitore di acer si colora di blu,
// ovvero tutta la ul delle marche!


////////////////////////////////////////////////////////////////////////////////

// E infine .children ritorna tutti i bambini:

const trucchi2 = document.getElementById("trucchi");
const childrenTrucchi = trucchi2.children;

// Se stampiamo, constatiamo che ci è stata restituita una collezione HTML:
console.log(childrenTrucchi); // HTMLCollection(3) [li#chanel, li#givenchy, li#dior]

// Ergo non c'è un forEach built-in ergo ci serve un typecast seguito da
// metod chaining:
Array.from(childrenTrucchi).forEach((li) => {
    // Voglio ora sottolineare tutti gli li della lista trucchi:
    li.style.textDecoration = "underline";
});

// Ovviamente una volta ritornata la collection, possiamo accedere ai singoli
// elementi della collezione con indice:
childrenTrucchi[0].style.fontSize = "2rem"; // chanel diventa più grande


/////////////////////////////////////////////////////////////////////////////////