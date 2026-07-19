#include <stdbool.h>
#include <stdio.h>
#include <string.h>

enum { CAPACITÀ = 256, LETTERE = 26 };

static int indiceLetteraAscii(unsigned char carattere) {
    if (carattere >= 'A' && carattere <= 'Z') return (int)(carattere - 'A');
    if (carattere >= 'a' && carattere <= 'z') return (int)(carattere - 'a');
    return -1;
}

static bool soloLettereAscii(const char *testo) {
    if (testo == NULL || *testo == '\0') return false;
    for (const unsigned char *p = (const unsigned char *)testo; *p != '\0'; ++p) {
        if (indiceLetteraAscii(*p) < 0) return false;
    }
    return true;
}

static bool ugualiIgnorandoCase(const char *prima, const char *seconda) {
    while (*prima != '\0' && *seconda != '\0') {
        if (indiceLetteraAscii((unsigned char)*prima)
            != indiceLetteraAscii((unsigned char)*seconda)) return false;
        ++prima;
        ++seconda;
    }
    return *prima == *seconda;
}

static bool sonoAnagrammi(const char *prima, const char *seconda) {
    size_t n = strlen(prima);
    if (n != strlen(seconda) || ugualiIgnorandoCase(prima, seconda)) return false;

    int frequenze[LETTERE] = {0};
    for (size_t i = 0; i < n; ++i) {
        ++frequenze[indiceLetteraAscii((unsigned char)prima[i])];
        --frequenze[indiceLetteraAscii((unsigned char)seconda[i])];
    }
    for (int i = 0; i < LETTERE; ++i) {
        if (frequenze[i] != 0) return false;
    }
    return true;
}

static bool leggiStringa(const char *richiesta, char buffer[], size_t capacità) {
    fputs(richiesta, stdout);
    if (fgets(buffer, (int)capacità, stdin) == NULL) return false;
    char *newline = strchr(buffer, '\n');
    if (newline != NULL) {
        *newline = '\0';
        return true;
    }
    int carattere = 0;
    while ((carattere = getchar()) != '\n' && carattere != EOF) { }
    return false;
}

int main(void) {
    char prima[CAPACITÀ];
    char seconda[CAPACITÀ];
    if (!leggiStringa("Prima stringa: ", prima, sizeof prima)
        || !leggiStringa("Seconda stringa: ", seconda, sizeof seconda)) {
        fputs("Input assente o troppo lungo.\n", stderr);
        return 1;
    }
    if (!soloLettereAscii(prima) || !soloLettereAscii(seconda)) {
        fputs("Sono ammesse soltanto lettere ASCII A-Z e a-z.\n", stderr);
        return 1;
    }

    puts(sonoAnagrammi(prima, seconda)
             ? "Le due stringhe sono anagrammi case-insensitive."
             : "Le due stringhe non sono anagrammi case-insensitive.");
    return 0;
}
