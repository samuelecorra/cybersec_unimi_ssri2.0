#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include <stdbool.h>
#include <ctype.h> // Per la funzione toupper

int main() {

    // QUIZ GAME PER LA PRINCIPESSA!

    SetConsoleOutputCP(CP_UTF8);

    // Array bidimensionali di stringhe per le domande e risposte
    char domande[][120] = {
        "Chi ha comprato casa sul lago di Como?",
        "Che matrimonio famosissimo è stato celebrato alla Villa del Balbianello?",
        "Se invece dico 'quel ramo del lago di Como' a chi pensi?",
        "Nel 2014 The Huffington Post ha dichiarato che il lago di Como è...",
        "Se prendessimo una villa a picco sul lago di Como,\n che personaggio Marvel avrebbe una villa simile alla nostra?"
    };

    char risposte[][120] = {
        "A. Tom Felton\nB. George Clooney\nC. Dua Lipa\nD. Lady Gaga",
        "A. Tom Cruise e Katie Holmes\nB. Brad Pitt e Angelina Jolie\nC. Anakin Skywalker e Padmé Amidala\nD. Beyoncé e Jay-Z",
        "A. Giovanni Boccaccio\nB. Dante Alighieri\nC. Francesco Petrarca\nD. Alessandro Manzoni",
        "A. Un luogo da sogno\nB. Il lago più bello del mondo\nC. Un posto per famiglie\nD. Un paradiso terrestre",
        "A. Iron Man\nB. Thor\nC. Spider-Man\nD. Doctor Strange"
    };

    char soluzioni[] = {'B', 'C', 'D', 'B', 'A'};

    int punteggio = 0;
    char inputPrincipessa = '\0';


    printf("Ciao Principessa! Benvenuta al Quiz sul Lago di Como!\n");
    printf("Rispondi alle domande scegliendo la lettera corrispondente alla risposta giusta.\n");
    printf("Per ogni risposta corretta guadagni 1 punto. Buona fortuna!\n\n");
    
    printf("Premi INVIO per iniziare il quiz...");
    getchar();  
    
    printf("Iniziamo!\n");
    printf("--------------------------------------------------\n");

    int numeroDomande = sizeof(domande) / sizeof(domande[0]);

    for(int i = 0; i < numeroDomande; i++) {
        printf("Domanda %d:\n%s\n", i + 1, domande[i]);
        printf("%s\n", risposte[i]);
        printf("La tua risposta: ");
        
        // Leggiamo l'input dell'utente
        scanf(" %c", &inputPrincipessa);
        inputPrincipessa = toupper(inputPrincipessa); // Convertiamo in maiuscolo per uniformità
        getchar(); // Consuma il newline rimasto nel buffer

        // Controlliamo se la risposta è corretta
        if(inputPrincipessa == soluzioni[i]) {
            printf("\nCorretto!\n");
            punteggio++;
        } else {
            printf("Sbagliato! La risposta corretta era: %c\n", soluzioni[i]);
        }

        
    }

    printf("Quiz terminato: hai totalizzato %d punti su %d\n", punteggio, numeroDomande);
    printf("--------------------------------------------------\n");
    printf("Grazie per aver giocato, Principessa! Alla prossima!\n");

    return 0;

}