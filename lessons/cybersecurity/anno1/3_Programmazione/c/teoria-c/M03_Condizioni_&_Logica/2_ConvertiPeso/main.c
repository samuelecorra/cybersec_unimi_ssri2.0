// Questo progettino converte un peso in chilogrammi (kg) in libbre (lbs o pounds)
// e viceversa. 

// 1 kg = 2.20462 lbs e 1 lb = 0.453592 kg

#include <stdio.h>
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <math.h>               // direttiva per funzioni matematiche
#include <stdbool.h>           // direttiva per usare il tipo bool
#include <string.h>            // direttiva per funzioni di manipolazione stringhe
#include <ctype.h>             // direttiva per funzioni di manipolazione caratteri

int main() {

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    int scelta = 0;
    float libbre = 0.0f;
    float chilogrammi = 0.0f;
    
    printf("Benvenuto nel convertitore di peso kg <-> lbs!\n");
    printf("Scegli l'operazione:\n");
    printf("1. Converti kg in lbs\n");
    printf("2. Converti lbs in kg\n");
    printf("Inserisci la tua scelta (1 o 2): ");
    scanf("%d", &scelta);
    getchar(); // consuma il newline rimasto nel buffer

    // So che questo può esser fatto con uno switch, ma ancora non li abbiamo visti,
    // quindi usiamo un if-else per ora.
    
    if (scelta == 1) {
        
        printf("Inserisci il peso in kg: ");
        scanf("%f", &chilogrammi);
        getchar(); // consuma il newline rimasto nel buffer
        
        libbre = chilogrammi * 2.20462f;
        printf("%.2f kg sono %.2f lbs\n", chilogrammi, libbre);

    } else if (scelta == 2) {
        
        printf("Inserisci il peso in lbs: ");
        scanf("%f", &libbre);
        getchar(); // consuma il newline rimasto nel buffer
        
        chilogrammi = libbre * 0.453592f;
        printf("%.2f lbs sono %.2f kg\n", libbre, chilogrammi);

    } else {
        printf("Scelta non valida!\n");
    }

    return 0;
}