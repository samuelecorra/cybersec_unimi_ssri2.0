// Cosa vuol dire shufflare un array?
// Shufflare un array significa mescolare gli elementi al suo interno in modo
// casuale, in modo che l'ordine originale degli elementi venga alterato. 
// Questo può essere utile in vari contesti, come nei giochi di carte, dove si 
// desidera mescolare le carte prima di distribuirle, o in situazioni in cui è 
// necessario randomizzare l'ordine degli elementi per motivi di equità o varietà.

// Iniziamo a crearci un deck monoseme, in quanto farne uno da 52 carte
// sarebbe un tantino overkill per il nostro scopo.

const carte = [ "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" ];

// Uno dei metodi che si vedono online per lo shuffling è:
// carte.sort(() => Math.random() - 0.5);
// MA ATTENZIONE: questo metodo non è raccomandato per lo shuffling
// di array, poiché non garantisce una distribuzione uniforme degli
// elementi! Invece, è meglio utilizzare un algoritmo di shuffling
// più robusto, come l'algoritmo di Fisher-Yates.

// Per implementarlo, creiamo una funzione dedicata:

function shuffle(array) {
    for (let i = array.length - 1; i > 0; i--) {
        // Genera un indice casuale tra 0 e i
        const randomIndex = Math.floor(Math.random() * (i + 1));
        // Scambia gli elementi array[i] e array[randomIndex] mediante destrutturazione
        [array[i], array[randomIndex]] = [array[randomIndex], array[i]];
    }
    return array;
}

// Ora chiamiamo la funzione:
shuffle(carte);
console.log("Deck mescolato:", carte);