#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>   // necessario per tolower()

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Funzione: sonoAnagrammi

bool sonoAnagrammi(const char *str1, const char *str2) {
    size_t n1 = strlen(str1), n2 = strlen(str2);
    if (n1 != n2) return false;  // anagrammi → stessa lunghezza

    // Escludiamo il caso identico (non è un anagramma "proprio")
    if (strcasecmp(str1, str2) == 0) return false;
    // Nota: strcasecmp() confronta due stringhe ignorando le maiuscole/minuscole.

    int count[256] = {0};  // 256 per coprire tutti i caratteri ASCII

    for (size_t i = 0; i < n1; i++) {
        unsigned char c1 = (unsigned char)tolower((unsigned char)str1[i]);
        unsigned char c2 = (unsigned char)tolower((unsigned char)str2[i]);
        count[c1]++;
        count[c2]--;
    }

    for (int i = 0; i < 256; i++)
        if (count[i] != 0)
            return false;

    return true;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Funzione principale

int main() {
    char str1[256], str2[256];

    printf("Inserisci la prima stringa: ");
    fgets(str1, sizeof(str1), stdin);
    str1[strcspn(str1, "\n")] = '\0'; // Rimuove newline

    printf("Inserisci la seconda stringa: ");
    fgets(str2, sizeof(str2), stdin);
    str2[strcspn(str2, "\n")] = '\0'; // Rimuove newline

    printf("\nHai inserito:\n");
    printf("  Prima stringa:  '%s'\n", str1);
    printf("  Seconda stringa: '%s'\n\n", str2);

    if (sonoAnagrammi(str1, str2))
        printf("✅ Le due stringhe SONO anagrammi (case unsensitive).\n");
    else
        printf("❌ Le due stringhe NON sono anagrammi (case unsensitive).\n");

    return 0;
}
