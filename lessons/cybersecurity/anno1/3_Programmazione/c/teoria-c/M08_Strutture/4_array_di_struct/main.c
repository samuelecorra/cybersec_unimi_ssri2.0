
// Ma se le struct sono tipi di dato, e gli array collezionano più valori dello stesso tipo,
// perché non possiamo avere array di struct?
// La risposta è: certo che possiamo!

// Infatti, se abbiamo 5 macchine, già diventa comodo poterli raggruppare
// in un unico vettore (array) di struct Auto!

#include <stdio.h>
#include <string.h>
#include <windows.h>

typedef struct {
    char marca[20];
    char modello[20];
    int anno;
    float prezzo;
} Auto;


int main() {


    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows


    // Creiamo alcune istanze di struct Auto
    Auto auto1 = {"Toyota", "Corolla", 2020, 20000.0};
    Auto auto2 = {"Honda", "Civic", 2019, 22000.0};
    Auto auto3 = {"Ford", "Focus", 2018, 18000.0};
    Auto auto4 = {"Chevrolet", "Malibu", 2021, 25000.0};
    Auto auto5 = {"Nissan", "Sentra", 2022, 23000.0};

    // Potremmo anche stampare le auto una per una:
    printf("Auto 1: %s %s, Anno: %d, Prezzo: %.2f\n", auto1.marca, auto1.modello, auto1.anno, auto1.prezzo);
    printf("Auto 2: %s %s, Anno: %d, Prezzo: %.2f\n", auto2.marca, auto2.modello, auto2.anno, auto2.prezzo);
    printf("Auto 3: %s %s, Anno: %d, Prezzo: %.2f\n", auto3.marca, auto3.modello, auto3.anno, auto3.prezzo);
    printf("Auto 4: %s %s, Anno: %d, Prezzo: %.2f\n", auto4.marca, auto4.modello, auto4.anno, auto4.prezzo);
    printf("Auto 5: %s %s, Anno: %d, Prezzo: %.2f\n", auto5.marca, auto5.modello, auto5.anno, auto5.prezzo);

    printf("\n");

    // MA E' MOLTO PIÙ COMODO creare un array di struct Auto!
    // Così possiamo gestire tutte le auto in un'unica variabile!
    Auto parcoAuto[5] = {auto1, auto2, auto3, auto4, auto5};
    int numeroAuto = sizeof(parcoAuto) / sizeof(parcoAuto[0]);
    
    // E ora possiamo stampare tutte le auto con un ciclo for
    for (int i = 0; i < numeroAuto; i++) {
        printf("Auto %d: %s %s, Anno: %d, Prezzo: %.2f\n", 
               i + 1, 
               parcoAuto[i].marca, 
               parcoAuto[i].modello, 
               parcoAuto[i].anno, 
               parcoAuto[i].prezzo);
    }
}


