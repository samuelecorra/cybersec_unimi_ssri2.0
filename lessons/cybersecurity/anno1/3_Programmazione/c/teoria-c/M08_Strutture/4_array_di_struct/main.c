#include <stddef.h>
#include <stdio.h>

typedef struct {
    char marca[20];
    char modello[20];
    int anno;
    float prezzo;
} Auto;

int main(void) {
    Auto parcoAuto[] = {
        {.marca = "Toyota",    .modello = "Corolla", .anno = 2020, .prezzo = 20000.0f},
        {.marca = "Honda",     .modello = "Civic",   .anno = 2019, .prezzo = 22000.0f},
        {.marca = "Ford",      .modello = "Focus",   .anno = 2018, .prezzo = 18000.0f},
        {.marca = "Chevrolet", .modello = "Malibu",  .anno = 2021, .prezzo = 25000.0f},
        {.marca = "Nissan",    .modello = "Sentra",  .anno = 2022, .prezzo = 23000.0f}
    };
    size_t numeroAuto = sizeof parcoAuto / sizeof parcoAuto[0];

    for (size_t i = 0; i < numeroAuto; ++i) {
        printf("Auto %zu: %s %s, anno %d, prezzo %.2f\n",
               i + 1, parcoAuto[i].marca, parcoAuto[i].modello,
               parcoAuto[i].anno, parcoAuto[i].prezzo);
    }

    // float è scelto qui solo per l'esempio: importi reali richiedono una
    // rappresentazione che eviti gli errori binari, per esempio centesimi interi.
    return 0;
}
