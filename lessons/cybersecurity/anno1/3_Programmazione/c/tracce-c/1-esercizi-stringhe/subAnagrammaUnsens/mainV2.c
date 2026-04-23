#include <stdio.h>
#include <ctype.h>
#include <stdbool.h>

#define ALFABETO 26
#define S1_IN_S2 1
#define S2_IN_S1 2

// ======================== FUNZIONE PRINCIPALE RICHIESTA =================================== //

// Ritorna true se s1 è sub-anagramma di s2 (case-insensitive).
// Ignora i non alfabetici (come da presupposto dell'esercizio).
bool is_subanagram(const char *s1, const char *s2) {
    int freq[ALFABETO] = {0};

    // Conta le scorte (s2)
    for (size_t i = 0; s2[i] != '\0'; ++i) {
        unsigned char ch = (unsigned char)s2[i];
        if (isalpha(ch)) {
            ch = (unsigned char)tolower(ch);
            freq[ch - 'a']++;
        }
    }

    // Consuma per s1: se una scorta va < 0, s1 chiede troppo
    for (size_t i = 0; s1[i] != '\0'; ++i) {
        unsigned char ch = (unsigned char)s1[i];
        if (isalpha(ch)) {
            ch = (unsigned char)tolower(ch);
            if (--freq[ch - 'a'] < 0) return false;
        }
    }

    return true;
}

// 0 = nessuna direzione; 1 = s1⊆s2; 2 = s2⊆s1; 3 = entrambe
int subanagram_relation(const char *s1, const char *s2) {
    int mask = 0;
    if (is_subanagram(s1, s2)) mask |= S1_IN_S2; // ovvero 0 | 1 = 1
    if (is_subanagram(s2, s1)) mask |= S2_IN_S1; // ovvero 0 | 2 = 2 oppure 1 | 2 = 3
    return mask;
}

// (Facoltativo) Valida che la stringa sia alfabetica pura.
// Se non vuoi questo controllo, elimina la funzione e la sua chiamata.
bool is_alpha_only(const char *s) {
    for (size_t i = 0; s[i] != '\0'; ++i) {
        if (!isalpha((unsigned char)s[i])) return false;
    }
    return true;
}

int main(void) {
    const char *str1 = "Casa";
    const char *str2 = "Causa";

    // Facoltativo: rifiuta input non alfabetici (altrimenti ignora e basta)
    if (!is_alpha_only(str1) || !is_alpha_only(str2)) {
        fprintf(stderr, "Errore: inserire solo stringhe alfabetiche A-Z/a-z.\n");
        return 1;
    }

    int rel = subanagram_relation(str1, str2);

    if (rel & S1_IN_S2)
        printf("\"%s\" è sub-anagramma di \"%s\"\n", str1, str2);
    else
        printf("\"%s\" NON è sub-anagramma di \"%s\"\n", str1, str2);

    if (rel & S2_IN_S1)
        printf("\"%s\" è sub-anagramma di \"%s\"\n", str2, str1);
    else
        printf("\"%s\" NON è sub-anagramma di \"%s\"\n", str2, str1);

    // Anagramma pieno: bastano entrambi i bit accesi
    if (rel == (S1_IN_S2 | S2_IN_S1)) // ovvero se rel == (01 | 10) == 11 == 3
        puts("Le due stringhe sono anagrammi (pieni) tra loro.");

    return 0;
}
