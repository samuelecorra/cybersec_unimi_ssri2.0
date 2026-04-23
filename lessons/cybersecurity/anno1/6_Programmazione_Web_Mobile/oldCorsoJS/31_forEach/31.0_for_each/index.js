/* Un ciclo for each è una variante del ciclo for nella quale si itera
su ogni elemento di un array e si va ad applicare una funzione di callback
ad ogni elemento (tale funzione viene passata a tale for each).

// Sintassi generica:

array.forEach(callback) {
    // codice da eseguire per ogni elemento
});

//////////////////////////////////////////////////////////////////////////////

// ESEMPIO 1: */
let numeri = [15, 20, 30, 40, 65];

// Nelle seguenti righe si noti che viene passata la DICHIARAZIONE/CORPO:
numeri.forEach(function(numero) {
    console.log("Il numero è: " + numero);
});

// Però, analogamente...
function stampaNumero(numero) {
    console.log("Il numero è: " + numero);
}

numeri.forEach(stampaNumero); // si può anche passare solo il nome della funzione!

///////////////////////////////////////////////////////////////////////////////
// Proviamo a passare una funzione più elaborata della semplice stampa in console:
function raddoppiaERimpiazzaNumero(numero, indice, array) {
    array[indice] = numero * 2;
    console.log("Il numero raddoppiato è: " + array[indice]);
}

numeri.forEach(raddoppiaERimpiazzaNumero);

function rimpiazzaElementoConIndice(numero, indice, array) {
    array[indice] = indice;
    console.log("Il numero rimpiazzato è: " + array[indice]);
}

numeri.forEach(rimpiazzaElementoConIndice);

function elevaERimpiazzaNumero(numero, indice, array) {
    array[indice] = Math.pow(numero, 2);
    console.log("Il numero elevato è: " + array[indice]);
}
numeri.forEach(elevaERimpiazzaNumero);
numeri.forEach(rimpiazzaElementoConIndice); // "azzeriamo"

// Stavolta il cubo:
function CuboNumero(numero, indice, array) {
    array[indice] = Math.pow(numero, 3);
    console.log("Il numero elevato al cubo è: " + array[indice]);
}
numeri.forEach(CuboNumero);