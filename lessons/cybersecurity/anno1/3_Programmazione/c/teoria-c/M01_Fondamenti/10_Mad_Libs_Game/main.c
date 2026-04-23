#include <stdio.h>
#include <string.h> // necessario per strlen()
#include <windows.h> // necessario per setlocale()

int main() {

    SetConsoleOutputCP(CP_UTF8);

    char nome[30] = "";
    char verbo[30] = "";
    char aggettivo[30] = "";
    char avverbio[30] = "";
    char luogo[30] = "";

    // Chiediamo all'utente di inserire le parole
    printf("Inserisci un nome: ");
    fgets(nome, sizeof(nome), stdin);
    nome[strlen(nome) - 1] = '\0'; // rimuovi il newline

    printf("Inserisci un verbo all'infinito: ");
    fgets(verbo, sizeof(verbo), stdin);
    verbo[strlen(verbo) - 1] = '\0'; // rimuovi il newline

    printf("Inserisci un aggettivo invariabile per maschile/femminile: ");
    fgets(aggettivo, sizeof(aggettivo), stdin);
    aggettivo[strlen(aggettivo) - 1] = '\0'; // rimuovi il newline

    printf("Inserisci un avverbio: ");
    fgets(avverbio, sizeof(avverbio), stdin);
    avverbio[strlen(avverbio) - 1] = '\0'; // rimuovi il newline

    printf("Inserisci un luogo: ");
    fgets(luogo, sizeof(luogo), stdin);
    luogo[strlen(luogo) - 1] = '\0'; // rimuovi il newline

    // Creiamo la storia
    printf("\nEcco la tua storia:\n");
    printf("C'era una volta %s, che era così %s che voleva %s %s a %s.\n", nome, aggettivo, verbo, avverbio, luogo);
                        // nome            // aggettivo verbo avverbio
    return 0;
}