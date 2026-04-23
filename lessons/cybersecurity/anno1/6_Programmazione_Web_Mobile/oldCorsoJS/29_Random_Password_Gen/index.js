// Ora impariamo come generare una password casuale!

function generaPassword(lunghezza, 
                        includeLowercase, 
                        includeUppercase, 
                        includeNumbers, 
                        includeSymbols) {

    const minuscole = "abcdefghijklmnopqrstuvwxyz"
    const maiuscole = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    const numeri = "0123456789"
    const simboli = "!@#$%^&*()_+[]{}|;:,.<>?"


    let caratteriPermessi = ""; // Partiamo da una stringa vuota
    let password = "";  // idem

    // Ora valutiamo in base ai flag booleani in entrata usufruendo anche
    // del ternary operator per snellirci la sintassi:
    caratteriPermessi += includeLowercase ? minuscole : "";
    caratteriPermessi += includeUppercase ? maiuscole : "";
    caratteriPermessi += includeNumbers ? numeri : "";
    caratteriPermessi += includeSymbols ? simboli : "";
    
    // A titolo di verifica:
    console.log(caratteriPermessi);

    // Ora discriminiamo i casi più critici:
    if (lunghezza <= 0) {
        return `Non puoi generare una password di 0 o meno caratteri, idiota!`
    }
    // Il problema però persiste anche nel caso in cui la lunghezza sia
    // positiva ma tutte le flag siano false: infatti in questo caso
    // la stringa finale di caratteriPermessi sarebbe vuota, ovvero con
    // length uguale a 0, ergo escludiamolo con strict equality operator:
    if (caratteriPermessi.length === 0) {
        return `Non hai selezionato true alcun set di caratteri per la pw!`
    }
    // Se almeno un set di caratteri è flaggato a true, possiamo procedere:
    // Generiamo dei caratteri casuali dal set disponibile per ogni indice
    // di posizione della stringa-password finale con un ciclo for:
    for (let i = 0; i < lunghezza; i++) {
        const indiceRandom = Math.floor(Math.random() * caratteriPermessi.length);
        // Ed ora possiamo iniziare a concatenare, indice dopo indice, a partire
        // dalla stringa vuota:
        password += caratteriPermessi[indiceRandom];
    }
    
    return password;
}

const lungPassword = 12;
const includeLowercase = true;
const includeUppercase = true;
const includeNumbers = true;
const includeSymbols = true;

const password = generaPassword(lungPassword, 
                                includeLowercase, 
                                includeUppercase, 
                                includeNumbers, 
                                includeSymbols);
console.log(`PW generata: ${password}`);