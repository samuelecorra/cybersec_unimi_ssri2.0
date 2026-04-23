/* COME POPOLARE LA NOSTRA PAGINA - Introduzione a document (DOM 101)
Cos’è document???
PARTIAMO DALLA NOZIONISTICA DI BASE:

Il browser, leggendo l’HTML, costruisce una struttura ad albero chiamata DOM 
(Document Object Model).

document è l’oggetto radice che rappresenta la pagina corrente: 
lo trovi anche come window.document.

Tramite document cerchi, crei, modifichi e ascolti elementi dell’HTML.

Nota: i nomi dei metodi sono case-sensitive!!!


-------------------------------------------------------------------------------


Come selezionare elementi
I modi più usati (tutti partono da document):

getElementById(id) → un solo elemento o null. 
L’id dovrebbe essere unico nella pagina.

querySelector(css) → il primo che matcha un selettore CSS.

querySelectorAll(css) → tutti quelli che matchano (ritorna un NodeList iterabile).

getElementsByClassName(name) / getElementsByTagName(name) → collezioni “live”.

Esempi:
const h1 = document.getElementById("saluto");     // per id
const firstBtn = document.querySelector("button"); // primo <button>
const cards = document.querySelectorAll(".card");  // tutte le .card


----------------------------------------------------------------------------------


Come Leggere/Scrivere contenuto

Le proprietà più comuni dell’elemento selezionato:

.textContent → imposta/legge testo puro (non interpreta HTML).

.innerText → testo “visibile” (rispetta CSS/display; più lento).

.innerHTML → interpreta HTML (attenzione a input non fidati).

// Siamo agli inizi della nostra esperienza con JS, quindi per ora ci limitiamo
// a utilizzare la proprietà textContent:   */
document.getElementById("Benvenuto").textContent =
  "Bienvenue al fottutissimo corso di Javascript!";
document.getElementById("descrizione").textContent =
  "In questo corso spiegheremo dalle basi sino ai concetti più avanzati di " +
  "Javascript.";

/* Altri accessi utili:

.value (per input/textarea) - ci permette di leggere/scrivere il valore attuale.

.classList.add/remove/toggle(...) - ci permette di aggiungere/rimuovere/commutare 
classi CSS.

.style.property = "..." (stile inline) - ci permette di modificare lo stile di 
un elemento.

.setAttribute("data-role", "greeting") / .getAttribute(...) - ci permette di
leggere/scrivere attributi personalizzati.

.append(...), .appendChild(node), .insertAdjacentHTML(pos, html) - ci permette
di aggiungere nuovi nodi o contenuti HTML all'interno di un elemento.

Esempio completo:   */

const el = document.getElementById("Benvenuto");
if (el) {
  el.textContent = "Bienvenue al fottutissimo corso di Javascript!";
  el.classList.add("Titolo");
  el.style.border = "1px solid #de2525ff";
  el.setAttribute("data-role", "greeting");
  el.insertAdjacentHTML("beforeend", " <strong>🔥</strong>");
}

/* Quando eseguire il codice (AVANZATO!)

    Assicurati che l’elemento esista prima di selezionarlo:

    Metti lo script con defer:
        
        <script src="index.js" defer></script>


    Oppure aspetta l’evento:

        document.addEventListener("DOMContentLoaded", () => {
        // qui è sicuro usare getElementById(...)
        });


////////////////////////////////////////////////////////////////////////////////


/* Sia chiaro che non deve spaventarci questo fottuto linguaggio: veniamo dal C,
dove la gestione della memoria era tutta manuale e dove la sintassi non era poi
così immediata, quindi a maggior ragione non dobbiamo farci intimidire da 
Javascript, che è un linguaggio molto più permissivo e facile da usare.
E soprattutto, tutte le nozioni finora apprese su C e Java sono benissimo 
riapplicabili qui.

Infatti, non sono stato a disquisire troppo le righe di codice precedenti, ma 
perché dovrebbe venir da sé che quei punti che separano document e 
getElementById sono proprio i dots che usavamo come separatori per chiamare
un certo metodo in Java, e qui l'analogia è piuttosto evidente:
document è l'oggetto che rappresenta l'intero documento HTML, mentre 
getElementById è un metodo di questo oggetto che ci permette di accedere a un 
elemento specifico tramite il suo ID.
Dopodiché in parentesi si passa un parametro per valore, che in questo caso 
è una stringa che rappresenta l'ID dell'elemento desiderato. 

Insomma, tutte cose che abbiam già ruminato, digerito e cagato ampiamente!
*/