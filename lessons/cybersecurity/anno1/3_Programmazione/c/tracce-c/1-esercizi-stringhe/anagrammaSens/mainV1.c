#include <stdio.h>
#include <stdbool.h>
#include <string.h>

// Definiamo una funzione che prende in ingresso due stringhe e restituisce un booleano
// che indica se la seconda stringa è un anagramma della prima.

// Le due stringhe passate in ingresso le passiamo col modificatore const, perché non le modificheremo
// all'interno della funzione, ma le useremo solo per confrontarle e calcolare se sono anagrammi o meno,
// ergo vogliamo una garanzia aggiuntiva che non vengano minimamente toccate.

// Ricordiamo che le stringhe in C sono array di caratteri terminati da un carattere nullo '\0',
// quindi possiamo usare le funzioni della libreria string.h per lavorare con esse, e soprattutto
// SI PASSANO ASSIOMATICAMENTE COME PUNTATORI A CHAR, quindi non dobbiamo preoccuparci di passare
// le dimensioni delle stringhe, perché la funzione strlen() ci verrà sempre in aiuto.

bool sonoAnagrammi(const char *str1, const char *str2) {
    size_t n1 = strlen(str1), n2 = strlen(str2);
    if (n1 != n2) return false;              // anagrammi → stessa lunghezza

    // else -> le stringhe sono di uguale lunghezza, quindi abbiamo un incentivo per continuare a controllare
    // se sono anagrammi o meno.

    // Escludiamo il caso identico: non lo consideriamo anagramma "proprio"
    if (strcmp(str1, str2) == 0) return false; // strcmp restituisce 0 se le stringhe sono identiche

    // Il C non fornisce strutture dati avanzate per contare le occorrenze dei caratteri,
    // lo facciamo manualmente sfruttando la corrispondenza tra caratteri e valori ASCII.
    // Creiamo un array per contare le occorrenze dei caratteri.
    int count[256] = {0}; // 256 per coprire tutti i caratteri ASCII, tutti inizializzati a 0.

    for (size_t i = 0; i < n1; i++) {
        count[(unsigned char)str1[i]]++; // Contiamo le occorrenze di ogni carattere nella prima stringa.
        count[(unsigned char)str2[i]]--; // Decrementiamo il conteggio per ogni carattere nella seconda stringa.
    }
    // ATTENZIONE: SI NOTI CHE I CAST AD UNSIGNED CHAR SONO NECESSARI PER EVITARE PROBLEMI DI SEGNO
    // quando si lavora con i caratteri, poiché alcuni caratteri possono essere interpretati come numeri negativi,
    // ma la ASCII estesa prevede solo valori positivi da 0 a 255, e il cast ce lo garantisce!

    // Se sono anagrammi, tutti i conteggi dovrebbero essere tornati a 0.
    // Verifichiamo:
    for (int i = 0; i < 256; i++) {
        if (count[i] != 0) {
            return false; // Se troviamo un conteggio diverso da 0, le stringhe non sono anagrammi.
        }

    }

    // Se tutto è andato bene, le stringhe sono anagrammi.
    return true;
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Passiamo ora a scrivere il main per testare la nostra funzione:

int main() {
    char str1[256], str2[256];

    printf("Inserisci la prima stringa: ");
    fgets(str1, sizeof(str1), stdin);
    str1[strcspn(str1, "\n")] = '\0'; // Rimuove newline

    printf("Inserisci la seconda stringa: ");
    fgets(str2, sizeof(str2), stdin);
    str2[strcspn(str2, "\n")] = '\0'; // Rimuove newline

    printf("\nHai inserito:\n");
    printf("Prima stringa: '%s'\n", str1);
    printf("Seconda stringa: '%s'\n\n", str2);

    if (sonoAnagrammi(str1, str2))
        printf("✅ Le due stringhe SONO anagrammi (case sensitive).\n");
    else
        printf("❌ Le due stringhe NON sono anagrammi (case sensitive).\n");

    return 0;
}