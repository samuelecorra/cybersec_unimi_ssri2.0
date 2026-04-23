/* Gli step per andare ad aggiungere e modificare elementi HTML mediante
uso conscio di JavaScript sono i seguenti:

// 1. CREARE L'ELEMENTO; */

const nuovoHeaderAppend = document.createElement("h1");
const nuovoHeaderPrepend = document.createElement("h1");
const nuovoHeaderPerBoxPrincipessa = document.createElement("h1");
const nuovoHeaderPerBoxCyber = document.createElement("h1");

// 2. ANDARE AD AGGIUNGERE PROPRIETA'/ATTRIBUTI

nuovoHeaderAppend.textContent = "Baka baka Baka!";
nuovoHeaderPrepend.textContent = "Miao miao Miao!";
nuovoHeaderPerBoxPrincipessa.textContent = "Mi piace il rosa!";
nuovoHeaderPerBoxCyber.textContent = "Mi piace creare siti per Principessa!";

nuovoHeaderAppend.id = "baka";
nuovoHeaderPrepend.id = "miao";
nuovoHeaderPerBoxPrincipessa.id = "rosa";
nuovoHeaderPerBoxCyber.id = "caramelloso";

nuovoHeaderAppend.style.color = "red";
nuovoHeaderPrepend.style.color = "blue";
nuovoHeaderPerBoxPrincipessa.style.color = "pink";
nuovoHeaderPerBoxCyber.style.color = "green";

nuovoHeaderAppend.style.textAlign = "center";
nuovoHeaderPrepend.style.textAlign = "center";
nuovoHeaderPerBoxPrincipessa.style.textAlign = "left";
nuovoHeaderPerBoxCyber.style.textAlign = "left";

// 3. AGGIUNGERE L'ELEMENTO AL DOM (APPEND TO DOM)

document.body.appendChild(nuovoHeaderAppend); // Così come in C si aprivano i file
// in append iniziando a scrivere dalla fine del file, così con HTML e JS quando
// "appendiamo" un elemento, vuol dire che lo aggiungiamo ALLA FINE del body!

// Ma possiamo anche pre-penderlo, ergo metterlo in testa:
document.body.prepend(nuovoHeaderPrepend);

// La cosa interessante è che oltre ai metodi come .body che ci incanalano verso
// tag built-in, possiamo anche appendere all'interno dei nostri creati manualmente:
document.getElementById("box-principessa").append(nuovoHeaderPerBoxPrincipessa);

// ATTENZIONE PERCHE' LO STIAMO APPENDENDO ERGO LO STIAMO AGGIUNGENDO COME
// SE FOSSE IL LAST CHIL DEL BOX DI PRINCIPESSA, QUINDI SE CI FOSSERO ALTRI FIGLI
// NEL SUO BOX, "Mi piace il rosa" sarebbe in fondo a tutti gli altri!
// Vediamo che infatti bisogna pre-pendere quello che vogliamo inserire PRIMA
// DEL NOME DELLA BOX:
document.getElementById("box-cyber").prepend(nuovoHeaderPerBoxCyber);


////////////////////////////////////////////////////////////////////////////////

// Ora che abbiamo le basi, vediamo altri metodi più elaborati:

// SI OK MA SE VOLESSIMO INVECE METTERE A MO' DI SANDWICH IL NOSTRO TITOLO
// CREATO MEDIANTE CREATELEMENT TRA UNA BOX E L'ALTRA?

const nuovoHeaderSandwich = document.createElement("h2");
nuovoHeaderSandwich.textContent = "Se Principessa e Cyber sono il panino," + 
                                    " io sono il tacchino!";
nuovoHeaderSandwich.style.color = "lightcoral";
const boxPrincipessa = document.getElementById("box-principessa");
const boxCyber = document.getElementById("box-cyber");
document.body.insertBefore(nuovoHeaderSandwich, boxCyber);


///////////////////////////////////////////////////////////////////////////////

// E se invece non potessimo appoggiarci a un id perché l'elemento ne è sprovvisto??
const nuovoHeaderSandwich2 = document.createElement("h2");
nuovoHeaderSandwich2.textContent = "Io sono la seconda fetta di tacchino!";
nuovoHeaderSandwich2.style.color = "lightcoral";
const boxes = document.querySelectorAll(".box");
document.body.insertBefore(nuovoHeaderSandwich2, boxes[2]); // Quindi prima della
// terza box, in quanto è sempre 0-index-based!
// Si cambi il valore fino a 4 per vedere i cambiamenti!


///////////////////////////////////////////////////////////////////////////////

// Nel momento in cui volessimo invece rimuovere un elemento HTML:

const elementoDaRimuovere = document.createElement("h2");
elementoDaRimuovere.textContent = "Io sono l'elemento da rimuovere!";
elementoDaRimuovere.style.color = "lightcoral";
document.body.appendChild(elementoDaRimuovere);

// Per rimuovere un elemento, possiamo utilizzare il metodo remove():
elementoDaRimuovere.remove(); // E infatti in fondo alla pagina non si vede nada!


// Se invece fosse nell'ultima box:
const elementoDaRimuovere2 = document.createElement("h2");
elementoDaRimuovere2.textContent = "Io sono l'elemento da rimuovere 2!";
elementoDaRimuovere2.style.color = "lightcoral";
document.getElementById("box-sdentato").appendChild(elementoDaRimuovere2);

// Stavolta dobbiamo rimuovere l'elemento dalla sua box specifica:
document.getElementById("box-sdentato").removeChild(elementoDaRimuovere2);

// analogamente e più compattamente si poteva anche:
// elementoDaRimuovere2.remove();

