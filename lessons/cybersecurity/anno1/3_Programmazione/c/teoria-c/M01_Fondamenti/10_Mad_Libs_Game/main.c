#include <stdio.h>
#include <string.h>  // necessario per strcspn()
#include <windows.h> // necessario per SetConsoleOutputCP()

int main(void) {

    SetConsoleOutputCP(CP_UTF8);

    char nome[30] = "";
    char verbo[30] = "";
    char aggettivo[30] = "";
    char avverbio[30] = "";
    char luogo[30] = "";

    // Chiediamo all'utente di inserire le parole
    printf("Inserisci un nome: ");
    fgets(nome, sizeof(nome), stdin);
    nome[strcspn(nome, "\n")] = '\0'; // rimuovi il newline, se presente

    printf("Inserisci un verbo all'infinito: ");
    fgets(verbo, sizeof(verbo), stdin);
    verbo[strcspn(verbo, "\n")] = '\0';

    printf("Inserisci un aggettivo invariabile per maschile/femminile: ");
    fgets(aggettivo, sizeof(aggettivo), stdin);
    aggettivo[strcspn(aggettivo, "\n")] = '\0';

    printf("Inserisci un avverbio: ");
    fgets(avverbio, sizeof(avverbio), stdin);
    avverbio[strcspn(avverbio, "\n")] = '\0';

    printf("Inserisci un luogo: ");
    fgets(luogo, sizeof(luogo), stdin);
    luogo[strcspn(luogo, "\n")] = '\0';

    // Creiamo la storia
    printf("\nEcco la tua storia:\n");
    printf("C'era una volta %s, che era così %s che voleva %s %s a %s.\n", nome, aggettivo, verbo, avverbio, luogo);
                        // nome            // aggettivo verbo avverbio
    return 0;
}
