#include <stdbool.h>
#include <stdio.h>
#include <string.h>

enum { CAPACITÀ = 256 };

static bool soloLettereAscii(const char *testo) {
    if (testo == NULL || *testo == '\0') return false;
    for (const unsigned char *p = (const unsigned char *)testo; *p != '\0'; ++p) {
        if (!((*p >= 'A' && *p <= 'Z') || (*p >= 'a' && *p <= 'z'))) return false;
    }
    return true;
}

/* Anagramma case-sensitive "proprio": per convenzione didattica due stringhe
 * identiche non sono considerate una permutazione diversa. */
static bool sonoAnagrammi(const char *prima, const char *seconda) {
    size_t n = strlen(prima);
    if (n != strlen(seconda) || strcmp(prima, seconda) == 0) return false;

    int frequenze[256] = {0};
    for (size_t i = 0; i < n; ++i) {
        ++frequenze[(unsigned char)prima[i]];
        --frequenze[(unsigned char)seconda[i]];
    }
    for (size_t i = 0; i < sizeof frequenze / sizeof frequenze[0]; ++i) {
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
             ? "Le due stringhe sono anagrammi case-sensitive."
             : "Le due stringhe non sono anagrammi case-sensitive.");
    return 0;
}
