#include <stdio.h>
#include <string.h>  // necessario per strcspn()
#include <windows.h> // necessario per SetConsoleOutputCP()
int main(void) {

    // In questa console Windows impostiamo UTF-8 per rappresentare correttamente l'euro.
    SetConsoleOutputCP(CP_UTF8);
    
    // Shopping cart program:

    char articolo[30] = ""; // nome dell'articolo, settato per ora a stringa vuota
    float prezzo = 0.0f;   // esempio didattico; per denaro reale si evitano i float binari
    int quantità = 0;      // quantità dell'articolo, settato per ora a 0
    const char *valuta = u8"€";    
    float totale = 0.0f;   // totale calcolato, settato per ora a 0.0

    // Iniziamo a progettare:

    printf("Benvenuto nel programma del carrello della spesa!\n");

    // Chiediamo all'utente il nome dell'articolo
    printf("Cosa desideri acquistare? ");
    fgets(articolo, sizeof(articolo), stdin); // leggi una linea di input
    articolo[strcspn(articolo, "\n")] = '\0'; // rimuovi il newline, se presente

    // Chiediamo all'utente il prezzo dell'articolo
    printf("Qual è il prezzo per un'unità di %s? ", articolo);
    scanf("%f", &prezzo);

    // Chiediamo all'utente la quantità dell'articolo
    printf("Quanti articoli di questo tipo desideri acquistare? ");
    scanf("%d", &quantità);

    // Calcoliamo il totale
    totale = prezzo * (float)quantità;

    // Mostriamo il riepilogo dell'ordine
    printf("\nRiepilogo ordine:\n\n");
    printf("Articolo: %s\n", articolo);
    printf("Prezzo unitario: %s%.2f \n", valuta, prezzo);
    printf("Quantità: %d\n", quantità);
    printf("Totale: %s%.2f \n\n", valuta, totale);

    return 0;
}
