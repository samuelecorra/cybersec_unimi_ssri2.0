/* Esempio di puntatore a struct restituito dalla libreria <time.h>. */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#ifdef _WIN32
#include <windows.h>
#endif

int main(void) {
#ifdef _WIN32
    SetConsoleOutputCP(CP_UTF8);
#endif

    time_t istante = time(NULL);
    if (istante == (time_t)-1) {
        fputs("Impossibile ottenere l'ora corrente.\n", stderr);
        return EXIT_FAILURE;
    }

    // localtime restituisce un puntatore a memoria gestita dalla libreria: non va
    // liberato e una chiamata successiva può sovrascriverne il contenuto.
    struct tm *oraLocale = localtime(&istante);
    if (oraLocale == NULL) {
        fputs("Conversione dell'ora non riuscita.\n", stderr);
        return EXIT_FAILURE;
    }

    char buffer[16];
    if (strftime(buffer, sizeof buffer, "%H:%M:%S", oraLocale) == 0) {
        fputs("Buffer insufficiente per formattare l'ora.\n", stderr);
        return EXIT_FAILURE;
    }
    printf("Ora locale: %s\n", buffer);
    return EXIT_SUCCESS;
}
