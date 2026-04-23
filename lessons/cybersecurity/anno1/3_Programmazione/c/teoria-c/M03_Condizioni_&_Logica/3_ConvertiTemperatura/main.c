/* Similmente allo scorso esercizietto, stavolta creiamo un programmino 
che ci aiuti a convertire da Celsius a Fahrenheit e viceversa. */

#include <stdio.h>
#include <windows.h>

int main() {

    SetConsoleOutputCP(CP_UTF8);

    char scelta = '\0';
    float celsius = 0.0f;
    float fahrenheit = 0.0f;

    printf("Benvenuto nel convertitore di temperatura °C <-> °F!\n");
    printf("Scegli l'operazione:\n");
    printf("C. Converti °C in °F\n");
    printf("F. Converti °F in °C\n");
    printf("Inserisci la tua scelta (C o F): ");
    scanf("%c", &scelta);
    getchar();

    if (scelta == 'C') {

        printf("Inserisci la temperatura in °C: ");
        scanf("%f", &celsius);
        getchar();

        fahrenheit = (celsius * 9.0f / 5.0f) + 32.0f;
        printf("%.2f °C sono %.2f °F\n", celsius, fahrenheit);

    } else if (scelta == 'F') {

        printf("Inserisci la temperatura in °F: ");
        scanf("%f", &fahrenheit);
        getchar();

        celsius = (fahrenheit - 32.0f) * 5.0f / 9.0f;
        printf("%.2f °F sono %.2f °C\n", fahrenheit, celsius);

    } else {
        printf("Scelta non valida!\n");
    }

    return 0;
}