#include <ctype.h>
#include <string.h>

#define N 8

int euristica(const int stato[N]);

static int indiceColonna(char colonna) {
    unsigned char valore = (unsigned char)colonna;
    valore = (unsigned char)tolower(valore);
    return valore >= 'a' && valore < 'a' + N ? (int)(valore - 'a') : -1;
}

static int indiceRiga(int rigaScacchistica) {
    return rigaScacchistica >= 1 && rigaScacchistica <= N
               ? rigaScacchistica - 1
               : -1;
}

int cerca_idx(const int stato[N], int colonna, int riga) {
    if (stato == NULL) return -3;
    if (colonna < 0 || colonna >= N) return -1;
    if (riga < 0 || riga >= N) return -2;

    int copia[N];
    memcpy(copia, stato, sizeof copia);
    copia[colonna] = riga;
    return euristica(copia);
}

int cerca(const int stato[N], char colonnaScacchistica, int rigaScacchistica) {
    int colonna = indiceColonna(colonnaScacchistica);
    if (colonna < 0) return -1;

    int riga = indiceRiga(rigaScacchistica);
    if (riga < 0) return -2;
    return cerca_idx(stato, colonna, riga);
}
