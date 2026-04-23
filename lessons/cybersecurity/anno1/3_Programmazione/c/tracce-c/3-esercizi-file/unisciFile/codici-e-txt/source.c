#include <stdio.h>
#include "header.h"

// Funzione di supporto per leggere il prossimo intero da un file
static int leggi_prossimo(FILE *fp, int *val) { // static per visibilità limitata a questo file
    return fscanf(fp, "%d", val) == 1; // Ritorna 1 se ha letto un intero, 0 altrimenti
}

int unisciFile(const char *file1in, const char *file2in, const char *fileOut) {
    FILE *f1 = fopen(file1in, "r");
    FILE *f2 = fopen(file2in, "r");
    FILE *fo = fopen(fileOut, "w");
    if (!f1 || !f2 || !fo) {
        if (f1) fclose(f1);
        if (f2) fclose(f2);
        if (fo) fclose(fo);
        return 0; // Errore nell'apertura dei file
    }

    int v1, v2; // Variabili per i valori letti dai file
    int has1 = leggi_prossimo(f1, &v1); // Indica se è stato letto un valore da f1
    int has2 = leggi_prossimo(f2, &v2); // Indica se è stato letto un valore da f2

    int first = 1; // Flag per gestire gli spazi tra i numeri
    while (has1 || has2) { // Finché c'è almeno un file con numeri da leggere
        int out;
        if (has1 && has2) { // Entrambi i file hanno numeri disponibili
            // scegliamo il minore e proviamo ad avanzare con la lettura del relativo file
            if (v1 <= v2) { out = v1; has1 = leggi_prossimo(f1, &v1); } 
            else          { out = v2; has2 = leggi_prossimo(f2, &v2); }
        } else if (has1) { // Solo f1 ha numeri disponibili
            out = v1; has1 = leggi_prossimo(f1, &v1); // Scelta obbligata e avanzamento idem
        } else { // Solo f2 ha numeri disponibili
            out = v2; has2 = leggi_prossimo(f2, &v2); // Scelta obbligata e avanzamento idem
        }
        if (!first) fputc(' ', fo); // Se sto scrivendo il secondo o successivi numeri, metto uno spazio prima
        first = 0; // Ora non sono più al primo numero
        fprintf(fo, "%d", out); // Finally scrivo il numero scelto nel file di output
    }

    fclose(f1);
    fclose(f2);
    fclose(fo);
    return 1;
}
