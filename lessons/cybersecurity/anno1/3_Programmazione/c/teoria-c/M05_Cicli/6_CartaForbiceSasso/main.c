#include <stdio.h>
#include <stdlib.h>             // direttiva per funzioni di utilità generale come rand() e srand()
#include <time.h>               // direttiva per time() per numeri casuali
#include <windows.h>           // direttiva per SetConsoleOutputCP()


//=====================================================================================


// Rendiamo il codice più leggibile con delle funzioni:
int getSceltaGiocatore() {
    int scelta;
    printf("Scegli: 1 per Carta, 2 per Forbice, 3 per Sasso: ");
    scanf("%d", &scelta);
    getchar(); // puliamo il buffer
    return scelta;
}

int getSceltaComputer() {
    return (rand() % 3) + 1; // numero tra 1 e 3
}


//=====================================================================================


int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    // Se vogliamo creare un gioco di carta, forbice, sasso,
    // dobbiamo generare numeri pseudo-casuali tra 1 e 3.

    srand((unsigned int)time(NULL)); // inizializziamo il generatore con il tempo attuale

    int sceltaComputer = getSceltaComputer();

    int sceltaGiocatore = 0;
    printf("Ciao Principessa, Benvenuta al tuo gioco personalissimo di Carta, Forbice, Sasso!\n");
    
    sceltaGiocatore = getSceltaGiocatore();

    if (sceltaGiocatore < 1 || sceltaGiocatore > 3) {
        printf("Scelta non valida. Riprova.\n");
        return 1; // termina il programma con un codice di errore
    }

    switch (sceltaGiocatore) {
        case 1:
            printf("Hai scelto: Carta\n");
            break;
        case 2:
            printf("Hai scelto: Forbice\n");
            break;
        case 3:
            printf("Hai scelto: Sasso\n");
            break;
    }
    switch (sceltaComputer) {
        case 1:
            printf("Il computer ha scelto: Carta\n");
            break;
        case 2:
            printf("Il computer ha scelto: Forbice\n");
            break;
        case 3:
            printf("Il computer ha scelto: Sasso\n");
            break;
    }

    // Determiniamo il vincitore
    if (sceltaGiocatore == sceltaComputer) {
        printf("Pareggio!\n");
    } else if ((sceltaGiocatore == 1 && sceltaComputer == 3) ||    // carta batte sasso
               (sceltaGiocatore == 2 && sceltaComputer == 1) ||    // forbice batte carta
               (sceltaGiocatore == 3 && sceltaComputer == 2)) {    // sasso batte forbice
        printf("Hai vinto!\n");
    } else {
        printf("Hai perso!\n");
    }

    return 0;
}