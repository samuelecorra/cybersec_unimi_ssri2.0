#include <stdio.h>

void incrementa(int *p) {
    *p = *p + 1;
}

int main() {
    int x = 5;
    incrementa(&x);
    printf("x = %d\n", x);
    return 0;
}
