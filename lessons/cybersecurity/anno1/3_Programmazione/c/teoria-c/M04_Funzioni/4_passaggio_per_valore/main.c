/*
PASSAGGIO PER VALORE

In C gli argomenti vengono passati per valore: il parametro formale riceve una
copia del valore calcolato dal chiamante. Modificare il parametro non modifica
automaticamente la variabile originale.

Per comunicare un nuovo valore al chiamante possiamo restituirlo con return.
Per modificare direttamente un oggetto del chiamante occorre invece passare il
suo indirizzo tramite un puntatore, argomento approfondito nel modulo M09.
*/

#include <stdio.h>

void provaIncremento(int valore) {
    valore += 1;
    printf("Dentro provaIncremento, la copia vale %d.\n", valore);
}

int incrementato(int valore) {
    return valore + 1;
}

void provaScambio(int primo, int secondo) {
    int temporaneo = primo;
    primo = secondo;
    secondo = temporaneo;
    printf("Dentro provaScambio: primo=%d, secondo=%d.\n", primo, secondo);
}

int main(void) {
    int numero = 10;

    provaIncremento(numero);
    printf("Dopo la chiamata, numero vale ancora %d.\n", numero);

    numero = incrementato(numero);
    printf("Assegnando il valore restituito, numero diventa %d.\n", numero);

    int a = 3;
    int b = 7;
    provaScambio(a, b);
    printf("Nel chiamante le copie originali restano a=%d, b=%d.\n", a, b);

    return 0;
}
