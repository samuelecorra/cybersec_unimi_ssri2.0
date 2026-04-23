#include <stdio.h>
#include <windows.h>
#include <stdbool.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <conio.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    // Realizziamo un semplice calcolatore che possa calcolare, dato un
    // primo operando, operatore, secondo operando, e stampare il risultato.
    char operatore = '\0';
    double num1 = 0.0;
    double num2 = 0.0;
    double risultato = 0.0;
    bool operazioneValida = true;

    printf("Benvenuto nel calcolatore scritto in C!\n");
    
    printf("Inserisci il primo operando: ");
    scanf("%lf", &num1);
    getchar(); // consuma il newline rimasto nel buffer

    printf("Inserisci l'operatore (+, -, *, /, %%): ");
    scanf(" %c", &operatore);
    getchar(); // consuma il newline rimasto nel buffer

    printf("Inserisci il secondo operando: ");
    scanf("%lf", &num2);
    getchar(); // consuma il newline rimasto nel buffer

    switch (operatore) {
        case '+':
            risultato = num1 + num2;
            break;
        case '-':
            risultato = num1 - num2;
            break;
        case '*':
            risultato = num1 * num2;
            break;
        case '/':
            if (num2 != 0) {
                risultato = num1 / num2;
            } else {
                operazioneValida = false;
            }
            break;
        case '%':
            if ((int)num2 != 0) {
                risultato = (int)num1 % (int)num2; // modulo solo per interi
            } else {
                operazioneValida = false;
            }
            break;
        default:
            operazioneValida = false;
            break;
    }

    if (operazioneValida) {
        printf("Il risultato di %.2f %c %.2f è: %.2f\n", num1, operatore, num2, risultato);
    } else {
        printf("Birichina, stai tentando di dividere per zero! Folle!\n");
    }

    return 0;
}