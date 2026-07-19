// typedef consente di usare il nome dell'alias senza anteporre la parola "enum".

#include <stdio.h>

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
} StatoOperazione;

static void gestisciStatoOperazione(StatoOperazione stato) {
    switch (stato) {
        case SUCCESS: puts("Operazione completata con successo!"); break;
        case FAILURE: puts("Operazione fallita. Riprova."); break;
        case PENDING: puts("Operazione in sospeso. Attendere..."); break;
        default:      puts("Stato sconosciuto."); break;
    }
}

int main(void) {
    GiornoDellaSettimana oggi = DOMENICA;
    printf("Oggi è il giorno numero %d della settimana.\n", oggi);
    puts(oggi == SABATO || oggi == DOMENICA
             ? "È il weekend, tempo di relax!"
             : "È un giorno feriale.");

    StatoOperazione statoAttuale = PENDING;
    gestisciStatoOperazione(statoAttuale);

    // Le costanti di enum sono interi con nome; il typedef migliora la sintassi,
    // ma non introduce la type safety forte delle enumerazioni di altri linguaggi.
    return 0;
}
