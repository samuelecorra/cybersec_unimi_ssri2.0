/*

Enums - abbreviazione di enumerations, ovvero enumerazioni - sono un tipo di dato 
user-defined che consente di definire un insieme di costanti con nomi significativi.
Sono particolarmente utili quando si lavora con un insieme limitato di valori
che rappresentano stati, opzioni o categorie.
Ad esempio, possiamo usare un enum per rappresentare i giorni della settimana:

    enum GiornoDellaSettimana {
        LUNEDI,
        MARTEDI,
        MERCOLEDI,
        GIOVEDI,
        VENERDI,
        SABATO,
        DOMENICA
    };

    A tali costanti viene automaticamente assegnato un valore intero, partendo da 0 
    per la prima costante e incrementando di 1 per ciascuna successiva. 
    Quindi, in questo caso:
        LUNEDI = 0,
        MARTEDI = 1,
        MERCOLEDI = 2,
        GIOVEDI = 3,
        VENERDI = 4,
        SABATO = 5,
        DOMENICA = 6

    // Questa corrispondenza biunivoca tra nomi e valori numerici rende il codice 
    più leggibile e torna utile nei costrutti di controllo come switch-case.

    // Anch'essi VANNO DICHIARATI FUORI DAL MAIN, COME LE LIBRERIE - PERCHE'?
    // Perché l'enum crea un nuovo tipo di dato che può essere utilizzato in tutto
    // il file, quindi deve essere dichiarato in un ambito globale, non all'interno
    // di una funzione!

*/

#include <stdio.h>
#include <string.h>
#include <windows.h>

// PERO' ATTENZIONE: NON VOGLIAMO AD ESEMPIO CHE L'INDICIZZAZIONE PARTA DA 0, BENSI' DA
// 1. ALLORA SETTIAMO NOI I VALORI DELLE COSTANTI:

enum GiornoDellaSettimana {
    LUNEDI = 1, // dopo aver assegnato 1 a LUNEDI, MARTEDI avrà automaticamente valore 2
    MARTEDI,
    MERCOLEDI,  // idem per MERCOLEDI che avrà valore 3
    GIOVEDI,    // 4
    VENERDI,    // 5
    SABATO,     // 6
    DOMENICA   // 7
};

// Ergo se settiamo il primo a un valore, i restanti calcolano da soli i propri offset!

int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows

    // ESEMPI DI UTILIZZO DI UN ENUM

    enum GiornoDellaSettimana oggi = MERCOLEDI;

    printf("Oggi è il giorno numero %d della settimana.\n", oggi);

    if (oggi == MERCOLEDI) {
        printf("Oggi è mercoledì, siamo a metà settimana!\n");
    } else {
        printf("Oggi non è mercoledì.\n");
    }

    // Possiamo anche usare gli enum in uno switch-case:

    switch (oggi) {
        case LUNEDI:
            printf("Inizio della settimana!\n");
            break;
        case MARTEDI:
            printf("Secondo giorno della settimana.\n");
            break;
        case MERCOLEDI:
            printf("Siamo a metà settimana!\n");
            break;
        case GIOVEDI:
            printf("Quasi venerdì...\n");
            break;
        case VENERDI:
            printf("Finalmente venerdì!\n");
            break;
        case SABATO:
            printf("È sabato, tempo di relax!\n");
            break;
        case DOMENICA:
            printf("Domenica, prepariamoci per la nuova settimana.\n");
            break;
        default:
            printf("Giorno non valido.\n");
    }

    return 0;
}