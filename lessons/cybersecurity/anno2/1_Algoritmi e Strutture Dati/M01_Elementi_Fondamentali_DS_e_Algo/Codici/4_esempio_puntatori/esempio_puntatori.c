#include <stdio.h>

int main() {
    
    int a, *b;
    a = 2000;
    b = &a;

    printf("variabile intera a = %d, memorizzata in %p\n", a, &a);
    printf("variabile puntatore a intero b = %p, memorizzato in %p\n", b, &b);
    printf("Dereferenziando b otteniamo il valore di a: *b = %d, tale valore conservato in %p\n", *b, b);

    return 0;
}