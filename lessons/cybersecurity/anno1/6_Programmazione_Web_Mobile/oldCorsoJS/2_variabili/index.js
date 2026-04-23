
// Ora: variabili, sono contenitori di valori bla bla... LO SAPPIAMO. Ma cosa 
// cambia davvero dal C e da Java?

/* 🔑 I 3 MODI MODERNI E PRINCIPALI DI DICHIARAZIONE:

1. var (storico, ES1 1997)

    Sintassi:
    var x = 10;

    Caratteristiche:
    Scope: funzione (non blocco). Dichiarata dentro un if o un for esce dal 
    blocco e rimane visibile nella funzione → comportamenti strani.

    Hoisting: la variabile viene "alzata" all’inizio dello scope con valore 
    undefined.
    
    console.log(a); // undefined
    var a = 5;

    Riassegnabile ✅
    Ridichiarabile nello stesso scope ✅ (altra fonte di confusione)

    Oggi var si usa solo per compatibilità legacy.

2. let (moderno, ES6 2015)

    Sintassi:
    let y = 20;

    Caratteristiche:
    Scope: blocco ({ ... }) → più intuitivo.

    Hoisting: sì, ma con “temporal dead zone” → non accessibile prima della riga
    di dichiarazione.

    console.log(b); // ReferenceError
    let b = 10;

    Riassegnabile ✅
    Non ridichiarabile nello stesso scope ❌

    Oggi è il modo più usato per variabili normali che cambiano valore.

3. const (moderno, ES6 2015)

    Sintassi:
    const z = 30;

    Caratteristiche:
    Scope: blocco come let.
    Non riassegnabile ❌ (ma ATTENZIONE: se è un oggetto o array puoi comunque 
    modificarne le proprietà/elementi).

    const arr = [1, 2];
    arr.push(3); // funziona
    arr = [4, 5]; // ERRORE

    Deve essere inizializzata subito al momento della dichiarazione.
    Usala di default, e passa a let solo se ti serve cambiare il valore.

-------------------------------------------------------------------------------

🔄 Modi “alternativi” (da conoscere)

4. Senza keyword (implicit global)

    Sintassi:
    x = 100; // senza var/let/const


    Caratteristiche:
    Se sei fuori da una funzione, diventa automaticamente variabile globale 
    (proprietà di window nel browser). Pericoloso! → bug e conflitti di namespace.

    In strict mode ('use strict';) lancia errore.

5. globalThis (ES2020)

    Non è un vero metodo di dichiarazione, ma un accesso:
    globalThis.x = 200;

    Rende esplicito che stai creando una variabile globale, indipendentemente
    dall’ambiente (window nel browser, global in Node, ecc.).

6. function (per dichiarare funzioni)

    Sintassi:
    function foo() { return 42; }

    Le funzioni hanno scope di blocco in modalità ES6 modules. 
    In ambienti legacy avevano scope var-like (funzione).
    Tecnicamente non è una variabile con let/var/const, ma crea un binding 
    simile a una costante.

7. class (ES6)

    Sintassi:
    class Persona {}

    Anche qui non è una “variabile”, ma una dichiarazione che introduce un 
    identificatore nello scope.

    Funziona come let/const: blocco, no ridichiarazione, ha TDZ.

-------------------------------------------------------------------------------
    
📌 Riassunto pragmatico

- Usa const di default.

- Usa let se sai che il valore cambierà.

- Evita var (serve solo per legacy).

- Non dichiarare mai senza keyword (se non vuoi creare bombe globali).
    
------------------------------------------------------------------------------*/

let x = 19; // Ovviamente abuseremo della forma compatta invece di:
let x2;
x2 = 5; // poiché è davvero inutile in fase didattica spezzare dichiarazione 
// e inizializzazione!

// Per sincerarci dell'avvenuta inizializzazione, possiamo fare quello che in C 
// era: printf("%d", x);
console.log(x); // molto più semanticamente intuibile, no?

// Al posto dei format specifiers, si usa il dollaro seguito da graffe come 
// delimitatori, quando si usufruisce del template literal:
console.log(`Prima di 20 viene ${x}`);

/*Cosa dovrebbe esserci già scattato in testa, da menti brillanti quali siamo?
Javascript NON E' tipizzato staticamente! Non abbiamo mai accennato ad int,
float, char, etc nelle scorse righe, quindi deduciamo già che i tipi sono 
dinamici e possono cambiare durante l'esecuzione.

Questo è anche dovuto al fatto che è un linguaggio molto più giovane di C e Java.
Detto ciò... i tipi sono gli stessi di C e Java? No, non proprio.
In Javascript abbiamo i seguenti tipi primitivi: string, number, boolean, null, 
undefined, e symbol.

// Beh, le somiglianze ci sono, ma ci sono anche differenze importanti.

--------------------------------------------------------------------------------

// Vediamo subito come visualizzare a schermo il tipo di una certa variabile */

    console.log(typeof x);
    let y = "Ciao mondo!";
    console.log(typeof y);
    let z = true;
    console.log(typeof z);
    let w = null;
    console.log(typeof w);
    let v = undefined;
    console.log(typeof v);
    let u = Symbol("foo");
    console.log(typeof u);

// Ora chiaramente ci rendiamo conto che quanto avevamo visto in precedenza per
// popolare la pagina non è altro che un assegnamento di variabile a una delle
// "proprietà" accedute nella nostra pagina mediante il DOM.

const stringaAssegnabile = "La Principessa ha gli occhi color diamante!💕"
document.getElementById("pAssegnabile").textContent = stringaAssegnabile;

// ovviamente a tali proprietà possiamo anche assegnare un template literal
let coloreOcchi = "diamante";
document.getElementById("pAssegnabile").textContent = `La Principessa ha gli occhi color ${coloreOcchi}!💕`;

// Per ora è tutto...