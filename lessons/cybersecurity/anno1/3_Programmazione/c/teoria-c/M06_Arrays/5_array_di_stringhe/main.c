
/* QUESITO DI PARTENZA: ma se le stringhe in C sono trattate come array di caratteri,
come possiamo rappresentare un array di stringhe? */

#include <stdio.h>
#include <string.h>
#include <windows.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    // Possiamo rappresentare un array di stringhe come un array di array di caratteri.
    // In C, le stringhe sono terminate da un carattere nullo '\0', quindi dobbiamo
    // tenere conto di questo nella nostra dichiarazione.

    char frasi[3][100] = {
        "Ciao, come stai?",
        "Oggi è una bella giornata.",
        "Impariamo a programmare in C!"
    };

    // Possiamo accedere e stampare ogni stringa nell'array:
    for(int i = 0; i < 3; i++) {
        printf("Frase %d: %s\n", i + 1, frasi[i]);
    }


    // Come per gli array di tipi primitivi, possiamo anche stavolta
    // generalizzare il numero di righe, al posto di scrivere 3!

    char frutta[][20] = { "Mela",
                          "Banana",
                          "Ciliegia",
                          "Dattero" };

    // La rowSize non è altro che il numero di frutti nell'array:
    int rowSize = sizeof(frutta) / sizeof(frutta[0]);

    // E quindi ora la stampa è più flessibile:
    for(int i = 0; i < rowSize; i++) {
        printf("Frutto %d: %s\n", i + 1, frutta[i]);
    }

    return 0;
}