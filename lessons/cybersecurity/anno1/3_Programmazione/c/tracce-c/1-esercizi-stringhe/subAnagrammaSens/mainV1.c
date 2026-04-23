// ESERCIZIO SULLA GESTIONE DI STRINGHE: SUB-ANAGRAMMI (VERSIONE CASE-SENSITIVE)

#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>

#define CHARSET 256
#define S1_IN_S2 1
#define S2_IN_S1 2

// Ritorna true se s1 è sub-anagramma di s2 (case-sensitive).
// Ogni carattere alfabetico maiuscolo/minuscolo è distinto.
bool is_subanagram_sensitive(const char *s1, const char *s2) {
    int freq[CHARSET] = {0};

    // Conta le scorte (s2)
    for (size_t i = 0; s2[i] != '\0'; ++i) {
        unsigned char ch = (unsigned char)s2[i];
        if (isalpha(ch)) freq[ch]++;
    }

    // Consuma per s1
    for (size_t i = 0; s1[i] != '\0'; ++i) {
        unsigned char ch = (unsigned char)s1[i];
        if (isalpha(ch) && --freq[ch] < 0)
            return false;
    }

    return true;
}

// 0 = nessuna direzione; 1 = s1⊆s2; 2 = s2⊆s1; 3 = entrambe
int subanagram_relation_sensitive(const char *s1, const char *s2) {
    int mask = 0;
    if (is_subanagram_sensitive(s1, s2)) mask |= S1_IN_S2;
    if (is_subanagram_sensitive(s2, s1)) mask |= S2_IN_S1;
    return mask;
}

// (Facoltativo) verifica alfabeticità pura
bool is_alpha_only(const char *s) {
    for (size_t i = 0; s[i] != '\0'; ++i)
        if (!isalpha((unsigned char)s[i])) return false;
    return true;
}

int main(void) {
    const char *str1 = "Casa";
    const char *str2 = "CASA";

    if (!is_alpha_only(str1) || !is_alpha_only(str2)) {
        fprintf(stderr, "Errore: inserire solo stringhe alfabetiche A-Z/a-z.\n");
        return 1;
    }

    int rel = subanagram_relation_sensitive(str1, str2);

    if (rel & S1_IN_S2)
        printf("\"%s\" è sub-anagramma di \"%s\"\n", str1, str2);
    else
        printf("\"%s\" NON è sub-anagramma di \"%s\"\n", str1, str2);

    if (rel & S2_IN_S1)
        printf("\"%s\" è sub-anagramma di \"%s\"\n", str2, str1);
    else
        printf("\"%s\" NON è sub-anagramma di \"%s\"\n", str2, str1);

    if (rel == (S1_IN_S2 | S2_IN_S1))
        puts("Le due stringhe sono anagrammi (pieni) tra loro.");

    return 0;
}
