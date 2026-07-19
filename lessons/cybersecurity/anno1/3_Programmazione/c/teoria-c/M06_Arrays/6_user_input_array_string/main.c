// Supponiamo di voler creare un array di stringhe ma di volerlo fa riempire
// all'utente:

#include <stdio.h>
#include <string.h>
#include <windows.h>

int main(void) {
    
    SetConsoleOutputCP(CP_UTF8);

    // Dichiariamo un array di stringhe con 3 righe e 20 colonne: ogni nome può
    // contenere al massimo 19 caratteri più il terminatore '\0'.
    char nomi[3][20];

    // Chiediamo all'utente di inserire 3 nomi
    // Ci è congeniale usare un ciclo for per evitare di ripetere il codice:

    // Come prima, dobbiamo generalizzare le righe:
    size_t dimensione = sizeof(nomi) / sizeof(nomi[0]);

    for(size_t i = 0; i < dimensione; i++) {

        printf("Inserisci %zu° nome: ", i + 1);

        // Usare fgets invece di scanf per permettere spazi nel nome (es. Maria Pia)
        if (fgets(nomi[i], sizeof(nomi[i]), stdin) == NULL) {
            nomi[i][0] = '\0';
            continue;
        }

        size_t fineRiga = strcspn(nomi[i], "\n");
        if (nomi[i][fineRiga] == '\n') {
            nomi[i][fineRiga] = '\0';
        } else {
            // La riga era più lunga del buffer: scartiamo il resto per non farlo
            // diventare il nome dell'iterazione successiva.
            int carattere;
            while ((carattere = getchar()) != '\n' && carattere != EOF) {
                // svuota il resto della riga
            }
        }
    }

    // Stampiamo i nomi inseriti dall'utente
    printf("\nI nomi inseriti sono:\n");
    for(size_t i = 0; i < dimensione; i++) {
        printf("Nome %zu: %s\n", i + 1, nomi[i]);
    }

    return 0;
}
