
// Il ternary operator è uno shortcut utilissimo per compattare il codice ed evitare
// di scrivere if-else troppo lunghi. La sintassi è la seguente:

// condizione ? espressione_se_vera : espressione_se_falsa;

#include <stdio.h>
#include <stdbool.h>
#include <windows.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    int numero = 10;

    // Esempio di if-else tradizionale
    if (numero % 2 == 0) {
        printf("%d è pari\n", numero);
    } else {
        printf("%d è dispari\n", numero);
    }
    
    // Ma quando i rami true/false sono semplici, possiamo usare il ternary operator:

    bool èStudentessa = true;

    èStudentessa ? printf("Benvenuta studentessa!\n") : printf("Puoi frequentare le lezioni ma non dare l'esame!\n");
    // Leggibilità molto buona, vero?

    printf("Premi INVIO per continuare...\n");
    getchar();


    // Esempio con assegnazione
    int voto1 = 7;
    int voto2 = 5;

    float media = (voto1 + voto2) / 2.0;

    bool èPromossa = media >= 6 ? true : false;

    if (èPromossa) {
        printf("Complimenti, sei promossa con media %.2f!\n", media);
    } else {
        printf("Mi dispiace, non sei promossa. La tua media è %.2f\n", media);
    }

    printf("Premi INVIO per continuare...\n");
    getchar();


    // O ancora...

    int a = 5;
    int b = 10;

    int max = a > b ? a : b;
    printf("Il valore massimo è: %d\n", max);

    printf("Premi INVIO per continuare...\n");
    getchar();


    // Oppure...

    int numeroDaValutare = 3;

    printf("%d è %s\n", numeroDaValutare, (numeroDaValutare % 2 == 0 ? "pari" : "dispari"));

    printf("Premi INVIO per continuare...\n");
    getchar();


    // E infine...

    int ora = 8;
    int minuto = 3;

    printf("L'orario inserito, in formato americano,"
            "\nè %02d:%02d %s\n", ora, minuto, (ora < 12 ? "AM" : "PM"));

    // Per garantire migliore leggibilità all'interno della printf, possiamo anche
    // migliorare il codice immagazzinando il risultato del ternary operator in una variabile:
    const char* periodoGiorno = ora < 12 ? "di mattina" : "di sera";

    printf("L'orario inserito, in formato italiano, mediante puntatore,"
            "\nè %02d:%02d %s\n", ora, minuto, periodoGiorno);

    return 0;
}
    