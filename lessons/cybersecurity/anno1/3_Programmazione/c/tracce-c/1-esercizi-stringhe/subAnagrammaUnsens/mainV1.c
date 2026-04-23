#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define alfabeto 26

// Definiamo la funzione con la quale lavoreremo:
int isFirstSubOfSecond(char *str1, char *str2) {

    int freq1[alfabeto] = {0};
    int freq2[alfabeto] = {0};

    // Contiamo le occorrenze di ciascuna lettera nelle due stringhe
    for (int i = 0; str1[i] != '\0'; i++) { // Per tutti i caratteri della prima...
        if (isalpha((unsigned char)str1[i])) { // Se il carattere letto è una lettera qualsiasi,
            // sia minuscola che maiuscola
            freq1[tolower((unsigned char)str1[i]) - 'a']++;
        }
    }
    for (int i = 0; str2[i] != '\0'; i++) {
        if (isalpha((unsigned char)str2[i])) {
            freq2[tolower((unsigned char)str2[i]) - 'a']++;
        }
    }

    // Dobbiamo controllare se la prima è sub-anagramma della seconda ma anche il viceversa:
    for (int i = 0; i < alfabeto; i++) {
        if (freq1[i] > freq2[i]) {
            return 0; // str1 non è sub-anagramma di str2
        }
    }
    return 1; // str1 è sub-anagramma di str2
    }

// Allora reimplementiamo la medesima funzione ma conl controllo finale al contrario
// per verificare se la seconda è sub anagramma della prima:
int isSecondSubOfFirst(char *str1, char *str2) {
    int freq1[alfabeto] = {0};
    int freq2[alfabeto] = {0};

    // Contiamo le occorrenze di ciascuna lettera nelle due stringhe
    for (int i = 0; str1[i] != '\0'; i++) { // Per tutti i caratteri della prima...
        if (isalpha((unsigned char)str1[i])) { // Se il carattere letto è una lettera qualsiasi,
            // sia minuscola che maiuscola
            freq1[tolower((unsigned char)str1[i]) - 'a']++;
        } else { perror("Carattere non valido rilevato nella stringa passata.\n"
                        "Il programma si arresterà");
            return -1;
        }
    }
    for (int i = 0; str2[i] != '\0'; i++) {
        if (isalpha((unsigned char)str2[i])) {
            freq2[tolower((unsigned char)str2[i]) - 'a']++;
        } else { perror("Carattere non valido rilevato nella stringa passata.\n"
                "Il programma si arresterà");
            return -1;
        }
    }

    // Dobbiamo controllare se la prima è sub-anagramma della seconda ma anche il viceversa:
    for (int i = 0; i < alfabeto; i++) {
        if (freq2[i] > freq1[i]) {
            return 0; // str2 non è sub-anagramma di str1
        }
    }
    return 1; // str2 è sub-anagramma di str1
    }

// Testiamo subito: Causa e Casa saranno le due parole scelte:
// ovviamente Causa NON è un sub-anagramma di Casa
// MA IL VICEVERSA FUNZIONA: tutte le occorrenze delle lettere di Casa sono minori o uguali
// a quelle di Causa!

// Vediamolo:

int main() {
    
    char str1[] = "Causa";
    char str2[] = "Casa";

    if (isFirstSubOfSecond(str1, str2)) {
        printf("%s è un sub-anagramma di %s\n", str1, str2);
    } else {
        printf("%s NON è un sub-anagramma di %s\n", str1, str2);
    }

    if (isSecondSubOfFirst(str1, str2)) {
        printf("%s è un sub-anagramma di %s\n", str2, str1);
    } else {
        printf("%s NON è un sub-anagramma di %s\n", str2, str1);
    }

    return 0;
}