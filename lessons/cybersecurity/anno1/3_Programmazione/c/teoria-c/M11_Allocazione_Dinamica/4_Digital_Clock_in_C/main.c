#include <stdio.h>
#include <stdlib.h>
#include <windows.h> // Per SetConsoleOutputCP
#include <time.h>   // Per time()
#include <stdbool.h> // Per bool, true, false


int main() {

    // OROLOGIO DIGITALE PER PRINCIPESSA!

    // Imposto la console in UTF-8
    SetConsoleOutputCP(CP_UTF8);

    // Attenzione: dovunque vorremo conservare il valore del tempo attuale, NON POSSIAMO
    // USARE INT, PERCHE' IL TEMPO E' UN VALORE CHE PUO' SUPERARE I 32 BIT!
    // Dobbiamo usare un tipo di dato capace di contenere valori molto grandi, come time_t
    // (che in genere è un long int, ma dipende dalla piattaforma)

    time_t rawTime = 0; // raw nel senso che è "grezzo", non formattato

    struct tm *pTime = NULL; // assegniamo NULL per sicurezza
    // pTime è un puntatore a struct tm, che è una struttura dati definita in time.h

    // NON DEREFERENZIARE UN NULL POINTER! (pTime è NULL in questo momento)
    // PRIMA ASSEGNIAMO UN VALORE VALIDO A pTime

    bool isRunning = true;

// ====================================================================================

    printf("Ciao Principessa! Questo è il tuo personalissimo orologio digitale.\n");

    while (isRunning) {

        // Ottengo il tempo attuale
        time(&rawTime); // la funzione time() scrive il tempo attuale in rawTime

        // Converto il tempo "grezzo" in una struttura dati più leggibile
        pTime = localtime(&rawTime);
        // localtime() converte il tempo "grezzo" in una struct tm, e restituisce un puntatore a essa

        // Ora posso dereferenziare pTime, perchè non è più NULL
        // Posso accedere ai membri della struct tm usando l'operatore ->
        // (perchè pTime è un puntatore)

        // Stampo l'orario in formato HH:MM:SS
        printf("\r%02d:%02d:%02d", pTime->tm_hour, pTime->tm_min, pTime->tm_sec);
        fflush(stdout); // Forzo la stampa immediata

        Sleep(1000); // Aspetto 1 secondo (1000 millisecondi)

        // Nota: \r è il carattere di ritorno a capo, che riporta il cursore all'inizio della riga
        // In questo modo sovrascriviamo l'orario precedente senza andare a capo
    }

    return 0;
}



