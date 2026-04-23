/* Ma che cos'è il DOM?
E' il Document Object Model, ovvero il modello ad oggetti del documento.
Il DOM rappresenta la struttura del documento HTML in modo che i programmatori 
possano modificarlo in modo dinamico.

Object{} che rappresenta la pagina che vediamo nel web browser e ci fornisce
un'API, ovvero una Application Programming Interface, 
che ci permette di interagire con il DOM in modo semplice e intuitivo.

Il web browser è il responsabile della costruzione del DOM quando gli si carica
un documento HTML: struttura tutti gli elementi in una tree-like representation.

Ovviamente noi coders possiamo accedere al DOM dinamicamente per cambiare
contenuto, struttura e stile della pagina web associata!

Lo abbiamo già usato ampiamente in precedenza senza saperlo:
document.getElementById() era una chiamata di un metodo DOM.

Ergo, il DOM contiene proprietà, metodi e altri nested objects.  */

console.log(document); // Stampa l'oggetto DOM nella console, ovvero
// tutto il documento HTML;

console.dir(document); // Stampa tutte le proprietà e i metodi dell'oggetto DOM,
// una bella lista chilometrica, oserei dire!

// Di base sappiamo che con !tab il nostro documento html si creerà automaticamente.
// E il title è di default settato a "Document".
// Ora:
document.title = "Introduzione DOM";

// Nel documento html non cambia nulla nel title, ma se ora
console.log(document.title); 
// Stampo il title in console e vedo "Introduzione DOM"

// Se volessimo cambiare il tema passandolo alla dark mode:
document.body.style.backgroundColor = "black";
// Eppure nel css vediamo ancora hotpink!

const nuovoNome = "Principessina";
const saluto = document.getElementById("saluto");

saluto.textContent = `Benvenuta ${nuovoNome}!`;