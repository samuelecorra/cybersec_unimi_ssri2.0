/* 📌 Introduzione agli Element Selectors del DOM

Quando scriviamo codice JavaScript che interagisce con una pagina web, 
non lavoriamo direttamente sull’HTML statico, ma sul DOM (Document Object Model).
Il DOM è una rappresentazione gerarchica (ad albero) della pagina:
ogni tag HTML diventa un nodo, e questi nodi possono essere letti, modificati, 
creati o eliminati tramite JavaScript.

Per poter manipolare un nodo, la prima cosa da fare è selezionarlo, cioè dire
a JavaScript: “Voglio lavorare su questo preciso elemento della pagina”.
Gli strumenti che ci permettono di farlo sono appunto gli element selectors,
cioè i metodi messi a disposizione dall’oggetto globale document che consentono
di recuperare uno o più elementi dal DOM.

📌 Funzione degli element selectors

Targeting: individuano un elemento (o un gruppo di elementi) nella struttura 
della pagina.

Manipolazione: una volta selezionato, possiamo cambiare il suo contenuto 
testuale, i suoi attributi, lo stile CSS o anche reagire a eventi 
(click, hover, ecc.).

Flessibilità: esistono diversi metodi di selezione, ognuno adatto a casi 
diversi: dal recupero di un singolo elemento con un certo id, fino alla 
selezione di tutti gli elementi che rispettano un certo selettore CSS.

📌 Principali metodi

document.getElementById("id")
Restituisce l’elemento con l’id specificato. 
È unico, quindi ritorna un singolo nodo o null se non esiste.

document.getElementsByClassName("classe")
Restituisce una HTMLCollection di tutti gli elementi con quella classe. 
Non è un vero array, ma può essere iterato.

document.getElementsByTagName("tag")
Restituisce tutti gli elementi con quel nome di tag (es. "div", "p"). 
Anche qui otteniamo una HTMLCollection.

document.querySelector("selettoreCSS")
Restituisce il primo elemento che corrisponde al selettore CSS specificato. 
È molto potente perché accetta la sintassi CSS completa 
(#id, .classe, div p, ecc.).

document.querySelectorAll("selettoreCSS")
Restituisce tutti gli elementi che corrispondono al selettore CSS, 
come una NodeList. A differenza delle HTMLCollection, la NodeList può essere 
iterata direttamente con forEach.

Gli element selectors rappresentano la porta d’ingresso tra JavaScript e il DOM:
senza di essi non potremmo “agganciare” alcun elemento della pagina da manipolare.

Sono concetti fondamentali, perché ogni interazione dinamica sul web – 
dalla più semplice (cambiare il colore di un paragrafo al click)
alla più complessa (gestire interfacce interattive come calcolatrici, giochi o
intere applicazioni web) – parte sempre dalla corretta selezione di un elemento 
nel DOM. */


////////////////////////////////////////////////////////////////////////////////

// 1. Selezionare un elemento per ID

const titoloPrincipessa = document.getElementById("saluto");

// Assegnare il riferimento del nodo a una variabile ci consente di chiamarne
// i metodi snellendo il codice, cioè ora possiamo scrivere direttamente
titoloPrincipessa.style.color = "white";

// Ma soprattutto possiamo vederlo in console direttamente:
console.log(titoloPrincipessa);

// Se mispelliamo l'id
const titoloSbagliato = document.getElementById("salutoSbagliato");
console.log(titoloSbagliato); // Restituisce null

// E se tra titoloSbagliato e il log avessimo cambiato qualche proprietà,
// avremmo ottenuto un uncaught TypeError.


////////////////////////////////////////////////////////////////////////////////

// 2. Selezionare un elemento per classe

// Abbiamo aggiunto tre div con la classe "frutta" nel documento html. Ora:

const frutta = document.getElementsByClassName("frutta");
console.log(frutta); // Restituisce una HTMLCollection

// Ora possiamo accedere a ciascun elemento della collezione con un simil-indice
// come se fosse un array (NON E' LA STESSA COSA COMUNQUE!!!):
frutta[0].style.color = "red";
frutta[1].style.color = "yellow";
frutta[2].style.color = "pink"; // sì... cherry è anche un colore AHAHAHA

// Possiamo iterare su questa collezione per accedere a ciascun elemento
for (let i = 0; i < frutta.length; i++) {
    console.log(frutta[i].innerText);
}

// Ma anche con un enhanced for loop
for (let elemento of frutta) {
    elemento.style.fontSize = "50px";
}

// ATTENZIONE: le collezioni HTML NON GODONO DI FOR EACH BUILT-IN. 

// frutta.forEach(); // otterrei un errore in cui dice che non è definita come
// funzione!

// PERO' POSSIAMO INVECE TYPECASTARE LA COLLEZIONE AD ARRAY:
// E a quel punto anche fare metod chaining diretto con for Each perché possiamo:
Array.from(frutta).forEach(function(elemento) {
    elemento.style.fontSize = "30px";
});


////////////////////////////////////////////////////////////////////////////////


// 3. Selezionare un elemento per tag

// Nel documento HTML creiamo una unordered list di verdure:

const h4Verdure = document.getElementsByTagName("h4");
// Verifichiamo cosa contiene
console.log(h4Verdure); // Stampa una HTML Collection:
// <h4>Verdure</h4> <h4>altre verdure</h4>

// Ancora una volta possiamo accedere ai singoli elementi della collezione
// mediante indice simulando un comportamento array-like
h4Verdure[0].style.color = "green";
h4Verdure[1].style.color = "lightgreen";

// E possiamo anche qui usare l'enhanced for loop per applicare qualcosa
// a tutti:

for (let verdura of h4Verdure) {
    verdura.style.fontSize = "20px";
}

// Facciamo lo stesso con i list items:
const liVerdure = document.getElementsByTagName("li");
console.log(liVerdure); // Stampa una HTML Collection:

// Possiamo anche qui accedere ai singoli elementi della collezione
// mediante indice simulando un comportamento array-like
liVerdure[0].style.color = "orange";
liVerdure[1].style.color = "green";
liVerdure[2].style.color = "darkgreen";

// E possiamo anche qui usare l'enhanced for loop per applicare qualcosa
// a tutti:

for (let verdura of liVerdure) {
    verdura.style.fontSize = "18px";
}

// E anche qui per usare il forEach

Array.from(h4Verdure).forEach(function(verdura) {
    verdura.style.backgroundColor = "darkgreen";
});

Array.from(liVerdure).forEach(function(verdura) {
    verdura.style.backgroundColor = "lightgreen";
});


///////////////////////////////////////////////////////////////////////////////

// 4. Selezionare il primo matching-element:

const element = document.querySelector(".frutta");

element.style.border = "2px solid white"; // Andiamo a mettere il bordo
// alla mela, ovvero il primo elemento con classe "frutta"

// Ritorna null se non trova elementi corrispondenti!


////////////////////////////////////////////////////////////////////////////////

// 5. Selezionare tutti gli elementi matching:

const elementi = document.querySelectorAll("li");

// Simile a una collezione HTML, MA HA GIA' METODI BUILT-IN SIMILI A QUELLI DEGLI
// ARRAY - COMUNQUE LE NODELIST SONO STATICHE MENTRE LE COLLEZIONI SONO DINAMICHE
// DAL MOMENTO CHE SONO STATICHE NON SI AGGIORNANO AUTOMATICAMENTE ALL'INTERNO
// DEL DOM!

// NIENTE TYPECASTING!:
elementi.forEach(elemento => {
    elemento.style.border = "2px solid white"; // Andiamo a mettere il bordo
    // a tutti gli elementi con classe "frutta"
});

// Ritorna una NodeList vuota se non trova elementi corrispondenti!
