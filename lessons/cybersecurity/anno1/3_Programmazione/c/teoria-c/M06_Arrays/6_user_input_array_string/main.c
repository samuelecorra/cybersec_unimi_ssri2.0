// Supponiamo di voler creare un array di stringhe ma di volerlo fa riempire
// all'utente:

#include <stdio.h>
#include <string.h>
#include <windows.h>

int main() {
    
    SetConsoleOutputCP(CP_UTF8);

    // Dichiariamo un array di stringhe con 3 righe e 100 colonne
    char nomi[3][20];

    // Chiediamo all'utente di inserire 3 nomi
    // Ci è congeniale usare un ciclo for per evitare di ripetere il codice:

    // Come prima, dobbiamo generalizzare le righe:
    int dimensione = sizeof(nomi) / sizeof(nomi[0]);

    for(int i = 0; i < dimensione; i++) {

        printf("Inserisci %d° nome: ", i + 1);

        // Usare fgets invece di scanf per permettere spazi nel nome (es. Maria Pia)
        fgets(nomi[i], sizeof(nomi[i]), stdin);

        // Rimuoviamo newline perché a causa di sizeof, fgets potrebbe non rimuoverlo
        nomi[i][strcspn(nomi[i], "\n")] = '\0';
    }

    // Stampiamo i nomi inseriti dall'utente
    printf("\nI nomi inseriti sono:\n");
    for(int i = 0; i < 3; i++) {
        printf("Nome %d: %s\n", i + 1, nomi[i]);
    }

    return 0;
}