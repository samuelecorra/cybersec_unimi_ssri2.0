#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int* divisori;
    int count;
} DivisoriArray;

// ho scelto un array dinamico per semplicità di accesso e iterazione

DivisoriArray Divisori(long long n) {
    DivisoriArray risultato = {NULL, 0}; // inizializzo il risultato
    if (n<1) return risultato; // caso di input non valido
    // conta i divisori
    for (long long i = 1; i <= n; i++)
        if (n % i == 0) // se i è un divisore
            risultato.count++; // incremento il conteggio
    // alloca l'array
    risultato.divisori = malloc(risultato.count * sizeof(int)); // alloco memoria per i divisori    
    int indice = 0; // indice per l'array dei divisori
    for (long long i=1; i<=n; i++)
        if (n%i == 0) // se i è un divisore
            risultato.divisori[indice++] = i; // lo aggiungo all'array
    return risultato;
}

int main() {
    long long n = 83528476; // numero input positivo di esempio, di cui trovare i divisori
    printf("Divisori di %lld:\n", n);
    DivisoriArray lista = Divisori(n); // chiamo la funzione
    // stampa dei divisori (solo per test, richiesta dal main)
    for (int i = 0; i < lista.count; i++) 
        printf("%d  ", lista.divisori[i]); // stampo ogni divisore
    free(lista.divisori); // libero la memoria allocata
    return 0;
}