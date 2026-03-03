#include <stdio.h>

void incrementa(int n) {
    n = n + 1;
}

int main() {
    int x = 5;
    incrementa(x);  
    // x vale ancora 5 perché viene passata una copia per valore
    printf("x = %d\n", x); // x = 5 ;(
    return 0;
}
