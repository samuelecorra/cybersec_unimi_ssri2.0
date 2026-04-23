
// Il vantaggio di combinare typedef ed enum è che possiamo creare tipi di dati
// più leggibili e facili da usare, migliorando la manutenzione del codice.

// Infatti, nel main non dovremo scrivere ogni volta "enum GiornoDellaSettimana",
// ma potremo usare direttamente "GiornoDellaSettimana".

// ATTENZIONE PERO': QUESTA COMBINAZIONE RICHIEDE SEMPRE DI PORRE IL NOME DEL
// TIPO DOPO LA DEFINIZIONE DEL TIPO STESSO, COME NELL'ESEMPIO QUI SOTTO!

// ====================================================================================


#include <stdio.h>
#include <string.h>
#include <windows.h>


// Possiamo anche combinare typedef ed enum in un'unica dichiarazione:
typedef enum {
    LUNEDI = 1,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA
} GiornoDellaSettimana;


typedef enum {
    SUCCESS,
    FAILURE,
    PENDING
} StatoOperazione;      // 0, 1, 2 COME DEFAULT VALUES ASSOCIATI AI TRE ELEMENTI


// E ora possiamo sfruttare uno dei tre stati per passarlo a una funzione dedicata:

void gestisciStatoOperazione(StatoOperazione stato) {
    switch (stato) {
        case SUCCESS:
            printf("Operazione completata con successo!\n");
            break;
        case FAILURE:
            printf("Operazione fallita. Riprova.\n");
            break;
        case PENDING:
            printf("Operazione in sospeso. Attendere...\n");
            break;
        default:
            printf("Stato sconosciuto.\n");
    }
}


// ====================================================================================



int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows

    // ESEMPI DI UTILIZZO DI UN ENUM
    // Si noti che stavolta non dobbiamo scrivere "enum" davanti al tipo,
    // perché abbiamo usato il typedef per creare un alias!
    GiornoDellaSettimana oggi = DOMENICA;

    printf("Oggi è il giorno numero %d della settimana.\n", oggi);

    if (oggi == MERCOLEDI) {
        printf("Oggi è mercoledì, siamo a metà settimana!\n");
    } else {
        printf("Oggi non è mercoledì.\n");
    }

    if (oggi == SABATO || oggi == DOMENICA) {
        printf("È il weekend, tempo di relax!\n");
    } else {
        printf("È un giorno feriale, dobbiamo lavorare hard!!!\n");
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


    printf("Premi INVIO per continuare...\n");
    getchar();

    // ESEMPI DI UTILIZZO DI UN ALTRO ENUM
    StatoOperazione statoAttuale = PENDING;
    gestisciStatoOperazione(statoAttuale);

    // MORALE DELLA FAVOLA: GLI ENUMS SONO SET DI NAMED INTEGER CONSTANTS!
    // BENEFIT: RIMPIAZZANO NUMERI CON NOMI LEGGIBILI!

    return 0;
}