/* Versione compatta: un solo istogramma e una bitmask per le due direzioni. */

#include <stdbool.h>
#include <stdio.h>

#define S1_IN_S2 1
#define S2_IN_S1 2

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
    int frequenze[LETTERE] = {0};
    for (const unsigned char *p = (const unsigned char *)disponibile; *p != '\0'; ++p) {
        ++frequenze[indiceLettera(*p)];
    }
    for (const unsigned char *p = (const unsigned char *)richiesta; *p != '\0'; ++p) {
        if (--frequenze[indiceLettera(*p)] < 0) return false;
    }
    return true;
}

static int relazioneSubanagramma(const char *prima, const char *seconda) {
    int relazione = 0;
    if (èSubanagramma(prima, seconda)) relazione |= S1_IN_S2;
    if (èSubanagramma(seconda, prima)) relazione |= S2_IN_S1;
    return relazione;
}

int main(void) {
    const char *prima = "Casa";
    const char *seconda = "Causa";
    if (!soloLettereAscii(prima) || !soloLettereAscii(seconda)) {
        fputs("Sono ammesse soltanto lettere ASCII A-Z e a-z.\n", stderr);
        return 1;
    }

    int relazione = relazioneSubanagramma(prima, seconda);
    printf("\"%s\" %sè sub-anagramma di \"%s\".\n",
           prima, relazione & S1_IN_S2 ? "" : "non ", seconda);
    printf("\"%s\" %sè sub-anagramma di \"%s\".\n",
           seconda, relazione & S2_IN_S1 ? "" : "non ", prima);
    if (relazione == (S1_IN_S2 | S2_IN_S1)) puts("Le stringhe sono anagrammi.");
    return 0;
}
