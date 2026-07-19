#include <stdbool.h>
#include <stdio.h>

#define S1_IN_S2 1
#define S2_IN_S1 2

static bool èLetteraAscii(unsigned char carattere) {
    return (carattere >= 'A' && carattere <= 'Z')
           || (carattere >= 'a' && carattere <= 'z');
}

static bool soloLettereAscii(const char *testo) {
    if (testo == NULL || *testo == '\0') return false;
    for (const unsigned char *p = (const unsigned char *)testo; *p != '\0'; ++p) {
        if (!èLetteraAscii(*p)) return false;
    }
    return true;
}

static bool èSubanagramma(const char *richiesta, const char *disponibile) {
    int frequenze[256] = {0};
    for (const unsigned char *p = (const unsigned char *)disponibile; *p != '\0'; ++p) {
        ++frequenze[*p];
    }
    for (const unsigned char *p = (const unsigned char *)richiesta; *p != '\0'; ++p) {
        if (--frequenze[*p] < 0) return false;
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
    const char *seconda = "CASA";
    if (!soloLettereAscii(prima) || !soloLettereAscii(seconda)) {
        fputs("Sono ammesse soltanto lettere ASCII A-Z e a-z.\n", stderr);
        return 1;
    }

    int relazione = relazioneSubanagramma(prima, seconda);
    printf("\"%s\" %sè sub-anagramma di \"%s\".\n",
           prima, relazione & S1_IN_S2 ? "" : "non ", seconda);
    printf("\"%s\" %sè sub-anagramma di \"%s\".\n",
           seconda, relazione & S2_IN_S1 ? "" : "non ", prima);
    if (relazione == (S1_IN_S2 | S2_IN_S1)) {
        puts("Le stringhe sono anagrammi case-sensitive.");
    }
    return 0;
}
