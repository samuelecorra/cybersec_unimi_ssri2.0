/* In JavaScript, quando selezioniamo più elementi HTML dalla 
nostra pagina, il browser ci restituisce un oggetto particolare 
chiamato NodeList.
Puoi immaginarlo come una lista ordinata di nodi 
(cioè di elementi del DOM), che assomiglia molto a un array ma 
non è proprio un vero array.

Un nodo non è altro che un mattoncino della struttura della 
pagina (un <div>, un <p>, un attributo, perfino un commento 
HTML). 
La NodeList è quindi una raccolta di questi mattoncini, 
ottenuta di solito con metodi come:

document.querySelectorAll("p") → ti restituisce tutti i 
paragrafi (<p>) della pagina.

document.querySelectorAll(".classe") → tutti gli elementi con 
quella classe.

document.querySelectorAll("#id") → quell’elemento specifico 
con l’id indicato.

///////////////////////////////////////////////////////////////

Caratteristiche fondamentali

Staticità
La NodeList è statica, cioè fotografa lo stato del DOM in quel preciso 
momento in cui la crei.
Se dopo aggiungi o togli elementi dalla pagina, quella NodeList non si 
aggiorna da sola.

Somiglianza con Array, ma non uguale
Puoi usare alcune proprietà da array (come length per sapere quanti elementi 
ci sono, o l’accesso con l’indice nodelist[0]), ma mancano i metodi tipici 
degli array veri (map, filter, reduce).
Per usarli devi prima convertirla in array con Array.from(nodelist).

Iterabilità
Una NodeList può essere facilmente attraversata con cicli come for...of 
oppure col vecchio for (let i=0; i<list.length; i++).

Perché è importante nel tuo progetto

Quando lavori a un progettone con tante interazioni sul DOM 
(tipo animazioni, calcolatrici, to-do list, ecc.), ti serve manipolare 
collezioni di elementi.
Capire che la NodeList è statica ti evita errori grossolani: se aggiungi 
nuovi elementi, non compariranno nella NodeList già creata → dovrai 
ricrearla o lavorare in altro modo.


In altre parole:

Array = strumento matematico con mille utilities.

NodeList = “fotografia” di alcuni pezzi della pagina. */


////////////////////////////////////////////////////////////////////////////

// ESEMPIO 1:

// Creiamo 4 bottoni con la medesima classe.
// Dopodiché, un modo in cui possiamo creare una nodelist è mediante
// quel che ci ritorna un querySelectorAll:

let bottoni = document.querySelectorAll(".mieiBottoni");
console.log(bottoni); 
// NodeList(4) [button.mieiBottoni, button.mieiBottoni, button.mieiBottoni, button.mieiBottoni]

let scelta1 = false; // mettere a true per vedere il cambiamento!
    if (scelta1) {
        bottoni.forEach((bottone) => {
            
            bottone.style.backgroundColor = "green";
            bottone.textContent += "💕";
            
        });
    }

// Alla stessa maniera, possiamo operare sulla nodelist con un
// click event listener, tanto per provarne uno dei tanti:
let scelta2 = true;
    if (scelta2) {
        bottoni.forEach((bottone) => {

            bottone.addEventListener("click", () => {
                bottone.style.backgroundColor = "lightblue";
            });

        });
    }

// Idem per mouse over e mouse out:

bottoni.forEach((bottone) => {
    bottone.addEventListener("mouseover", (event) => {
        event.target.style.backgroundColor = "lightgreen";
    });
    bottone.addEventListener("mouseout", (event) => {
        event.target.style.backgroundColor = "";
        // mettere la stringa vuota emula il ripristino al valore
        // di default nel nostro css!
    });
});