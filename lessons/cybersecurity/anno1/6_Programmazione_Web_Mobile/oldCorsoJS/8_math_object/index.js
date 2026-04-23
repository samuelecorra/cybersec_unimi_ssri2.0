/* C'è un oggetto molto interessante in JavaScript, per la precisione uno
di quelli BUILT IN, ovvero quegli oggetti che sono già presenti nel linguaggio
e che possiamo utilizzare senza doverli definire noi...

Math è un oggetto globale che fornisce metodi e proprietà per le operazioni 
matematiche. Ad esempio, possiamo utilizzare Math.PI per ottenere il valore di
pi greco, oppure Math.sqrt() per calcolare la radice quadrata di un numero. */

// Si noti che si accede alle proprietà sempre col dot:
console.log("Il valore di PI greco è: " + Math.PI);
console.log("Il numero di Eulero, anche noto come numero di Nepero è: " + Math.E);

let x = 3.21;
let y = 2;
let z;

    // Da questa terna di variabili, iniziamo a prendere dimestichezza con metodi
    // e proprietà di Math:

    z = Math.round(x);
    console.log("Il valore di x = 3.21 arrotondato, per difetto/eccesso coerentemente" +
                " al suo valore è: " + z);

    x = 3.99; // Giusto per sottolineare che anche il valore più alto va giù
    // per difetto con floor:

    z = Math.floor(x);
    console.log("Il valore di x = 3.99 arrotondato forzatamente per difetto è: " + z);

    // In maniera speculare:
    x = 3.00001;
    z = Math.ceil(x);
    console.log("Il valore di x = 3.00001 arrotondato forzatamente per eccesso è: " + z);

// ATTENZIONE: il metodo trunc invece è più specifico: va a troncare il numero
// alla parte intera, senza arrotondare.

// Ad esempio: se arrotondando coerentemente 3.99 ottenevamo 4, con trunc otteniamo 3, che è il medesimo
// esito di floor, ma se invece avessimo -3.01, sono trunc e ceil a garantirci di ottenere -3, mentre con floor
// avremmo ottenuto -4.
// Quindi in poche parole trunc va usato saggiamente quando non vogliamo badare ad arrotondamenti ma solo
// ad estrarre la parte intera senza interessarci minimamente di parte decimale e prescindendo dal segno!

    x = -3.9;
    z = Math.trunc(x);
    console.log("Il valore di x = -3.9 troncato alla parte intera è: " + z);

// Un modo equivalente al doppio asterisco per elevare un numero a potenza è utilizzare Math.pow(base, esponente):
    let base = 2;
    let esponente = 3;
    let risultato = Math.pow(base, esponente);
    console.log("Il risultato di " + base + " elevato alla " + esponente + " è: " + risultato);

// Se invece volessimo il logaritmo naturale (in base e) o in una certa base:

    let b = 10;
    let logaritmoNaturale = Math.log(b);
    console.log("Il logaritmo naturale di " + b + " è: " + logaritmoNaturale);
    let logaritmoInBase10 = Math.log10(b);
    console.log("Il logaritmo naturale in base 10 di " + b + " è: " + logaritmoInBase10);

// Passando invece a trigonometria, l'incubo dei liceali, con Javascript ecco che una miriade di esercizietti
// di merda di fisica e mate diventano una cavolata atomica da automatizzare!

    // Le funzioni trigonometriche, sia nella realtà che in JS, accettano radianti come parametri, NON GRADI!
    // Se venisse passato qualcosa in gradi, LA FUNZIONE NON CONVERTE IN AUTOMATICO -> TOCCA A NOI!

    let angoloInGradi = 90;
    let angoloInRadianti = angoloInGradi * (Math.PI / 180);
    console.log("L'angolo di " + angoloInGradi + " gradi in radianti è: " + angoloInRadianti);
    
    // Ora possiamo procedere:
    let senoDiRadianti = Math.sin(angoloInRadianti);
    console.log("Il seno di " + angoloInGradi + "° (" + angoloInRadianti + " radianti) è: " + senoDiRadianti);

    let cosenoDiRadianti = Math.cos(angoloInRadianti);
    console.log("Il coseno di " + angoloInGradi + "° (" + angoloInRadianti + " radianti) è: " + cosenoDiRadianti);

    let tangenteDiRadianti = Math.tan(angoloInRadianti);
    console.log("La tangente di " + angoloInGradi + "° (" + angoloInRadianti + " radianti) è: " + tangenteDiRadianti);

// Attenzione: le funzioni trigonometriche restituiscono valori in un range specifico, ergo non spaventiamoci
// se otteniamo risultati come:
// Il coseno di 90° (1.5707963267948966 radianti) è: 6.123233995736766e-17
// La tangente di 90° (1.5707963267948966 radianti) è: 16331239353195370

// Il primo valore è praticamente zero, mentre il secondo tende all'infinito. E' necessario implementare
// funzioni apposite che gestiscano questi casi, ma per ora non ci preoccupiamo, dobbiamo solo imparare
// la sintassi delle funzioni matematiche in JavaScript.


//////////////////////////////////////////////////////////////////////////////////////////////////////////


// Torniamo su esempi semplici, il metodo abs ci dà il valore assoluto di un certo numero:
let numeroNegativo = -5;
let valoreAssoluto = Math.abs(numeroNegativo);
console.log("Il valore assoluto di " + numeroNegativo + " è: " + valoreAssoluto);

// Il metodo sign ci restituisce invece 1 se il segno è positivo, -1 se negativo e 0 se zero:
let numero1 = 10;
let segno = Math.sign(numero1);
console.log("Il segno di " + numero1 + " è: " + segno);
let numero2 = -10;
let segnoNegativo = Math.sign(numero2);
console.log("Il segno di " + numero2 + " è: " + segnoNegativo);
let numero3 = 0;
let segnoZero = Math.sign(numero3);
console.log("Il segno di " + numero3 + " è: " + segnoZero);


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Abbiamo anche funzioni built in per discriminare in quattro e quattr'otto massimo e minimo:
// Supponiamo di avere:
let x1 = 3;
let y1 = 2;
let z1 = 1;

let massimo = Math.max(x1, y1, z1);
console.log("Il valore massimo tra " + x1 + ", " + y1 + " e " + z1 + " è: " + massimo);
let minimo = Math.min(x1, y1, z1);
console.log("Il valore minimo tra " + x1 + ", " + y1 + " e " + z1 + " è: " + minimo);