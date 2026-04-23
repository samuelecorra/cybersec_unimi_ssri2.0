#include <stdio.h>
#include <string.h> // necessario per strlen()
#include <windows.h> // necessario per setlocale()
int main() {

    // Stavolta è essenziale forzare UTF-8, altrimenti l'euro non funziona anzi
    // ci darà problemi.
    SetConsoleOutputCP(CP_UTF8);
    
    // Shopping cart program:

    char articolo[30] = ""; // nome dell'articolo, settato per ora a stringa vuota
    float prezzo = 0.0f;   // prezzo dell'articolo, settato per ora a 0.0
    int quantità = 0;      // quantità dell'articolo, settato per ora a 0
    const char *valuta = u8"€";    
    float totale = 0.0f;   // totale calcolato, settato per ora a 0.0

    // Iniziamo a progettare:

    printf("Benvenuto nel programma del carrello della spesa!\n");

    // Chiediamo all'utente il nome dell'articolo
    printf("Cosa desideri acquistare? ");
    fgets(articolo, sizeof(articolo), stdin); // leggi una linea di input
    articolo[strlen(articolo) - 1] = '\0'; // rimuovi il newline alla fine

    // Chiediamo all'utente il prezzo dell'articolo
    printf("Qual è il prezzo per un'unità di %s? ", articolo);
    scanf("%f", &prezzo);

    // Chiediamo all'utente la quantità dell'articolo
    printf("Quanti articoli di questo tipo desideri acquistare? ");
    scanf("%d", &quantità);

    // Calcoliamo il totale
    totale = prezzo * quantità;

    // Mostriamo il riepilogo dell'ordine
    printf("\nRiepilogo ordine:\n\n");
    printf("Articolo: %s\n", articolo);
    printf("Prezzo unitario: %s%.2f \n", valuta, prezzo);
    printf("Quantità: %d\n", quantità);
    printf("Totale: %s%.2f \n\n", valuta, totale);

    return 0;
}