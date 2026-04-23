// Possiamo creare un altro elemento della lista ordinata:

const nuovoListItem = document.createElement("li");
nuovoListItem.textContent = "Stivali";
nuovoListItem.id = "stivali";
nuovoListItem.style.fontWeight = "bold";
nuovoListItem.style.backgroundColor = "blue";

// Per appenderlo correttamente nella lista già presente, possiamo:
document.getElementById("vestiti").appendChild(nuovoListItem);
// Oppure anche
// document.querySelector("#vestiti").appendChild(nuovoListItem)

// Potremmo anche pre-penderlo per metterlo come 1.

// Ma per metterlo in posizione 3, ovvero tra jeans e giacca?
const listaVestiti = document.getElementById("vestiti");
const jeans = document.getElementById("jeans");
const giacca = document.getElementById("giacca");
listaVestiti.insertBefore(nuovoListItem, giacca);

// Ma andava bene anche:
// document.getElementById("vestiti").insertBefore(nuovoListItem, giacca);


//////////////////////////////////////////////////////////////////

// Se non dovessimo avere a disposizione id univoci per i vari
// list items, possiamo farci restituire la nodelist tramite
// queryselectorAll:

const listaVestiti2 = document.querySelectorAll("#vestiti li");
// E poi:
document.getElementById("vestiti").insertBefore(nuovoListItem, listaVestiti2[2]);

// E chiaramente si può anche rimuovere...
// document.getElementById("vestiti").removeChild(listaVestiti2[2]);