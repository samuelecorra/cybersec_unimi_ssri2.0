/* Versione base: costruisce due istogrammi da 26 contatori. */

#include <stdbool.h>
#include <stdio.h>

enum { LETTERE = 26 };

static int indiceLettera(unsigned char carattere) {
    if (carattere >= 'A' && carattere <= 'Z') return (int)(carattere - 'A');
    if (carattere >= 'a' && carattere <= 'z') return (int)(carattere - 'a');
    return -1;
}

static bool soloLettereAscii(const char *testo) {
    if (testo == NULL || *testo == '\0') return false;
    for (const unsigned char *p = (const unsigned char *)testo; *p != '\0'; ++p) {
        if (indiceLettera(*p) < 0) return false;
    }
    return true;
}

static bool èSubanagramma(const char *richiesta, const char *disponibile) {
    int occorrenzeRichieste[LETTERE] = {0};
    int occorrenzeDisponibili[LETTERE] = {0};
    for (const unsigned char *p = (const unsigned char *)richiesta; *p != '\0'; ++p) {
        ++occorrenzeRichieste[indiceLettera(*p)];
    }
    for (const unsigned char *p = (const unsigned char *)disponibile; *p != '\0'; ++p) {
        ++occorrenzeDisponibili[indiceLettera(*p)];
    }
    for (int i = 0; i < LETTERE; ++i) {
        if (occorrenzeRichieste[i] > occorrenzeDisponibili[i]) return false;
    }
    return true;
}

int main(void) {
    const char *prima = "Causa";
    const char *seconda = "Casa";
    if (!soloLettereAscii(prima) || !soloLettereAscii(seconda)) {
        fputs("Sono ammesse soltanto lettere ASCII A-Z e a-z.\n", stderr);
        return 1;
    }

    printf("\"%s\" %sè sub-anagramma di \"%s\".\n",
           prima, èSubanagramma(prima, seconda) ? "" : "non ", seconda);
    printf("\"%s\" %sè sub-anagramma di \"%s\".\n",
           seconda, èSubanagramma(seconda, prima) ? "" : "non ", prima);
    return 0;
}
