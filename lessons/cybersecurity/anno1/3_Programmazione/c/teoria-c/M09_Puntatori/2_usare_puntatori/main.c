
// Ora che sappiamo come si dichiarano e si inizializzano i puntatori, vediamo
// come si usano per leggere e modificare il valore della variabile a cui puntano

#include <stdio.h>
#include <windows.h>

void buonCompleanno(int *pEtà) {

    // La funzione riceve in ingresso un puntatore a intero (int)
    // e incrementa di 1 il valore della variabile a cui punta

    // Per accedere al valore della variabile a cui punta il puntatore,
    // usiamo l'operatore di dereferenziazione * (asterisco) prima del nome
    // del puntatore:

    (*pEtà)++; // incrementa di 1 il valore della variabile a cui punta pEtà

    // NOTA: le parentesi tonde sono necessarie perché l'operatore di dereferenziazione
    // ha precedenza più bassa dell'operatore di incremento ++, e quindi bisogna
    // forzarne artificiosamente l'ordine di esecuzione.
}


int main() {

    SetConsoleOutputCP(CP_UTF8); // Solo se usiamo Windows

    int età = 17;
    int *pEtà = &età; // puntatore a intero (int) che punta alla variabile età
    printf("Età prima del compleanno: %d\n", età);

    // Chiamiamo la funzione buonCompleanno, passando l'indirizzo di memoria
    // della variabile età (usando l'operatore &)

    buonCompleanno(pEtà);

    printf("Età dopo il compleanno: %d\n", età);

    printf("Premi invio per l'alternativa elegante...\n");
    getchar(); // pausa, aspetta invio        

    // NON E' NECESSARIO DICHIARARE E INIZIALIZZARE UN PUNTATORE, BASTA PASSARE
    // ALLA FUNZIONE L'INDIRIZZO DI MEMORIA DELLA VARIABILE USANDO L'OPERATORE &
    // E ANDRA' TUTTO LISCIO!
    
    età = 17; // resettiamo l'età
    printf("Età prima del compleanno: %d\n", età);
    
    buonCompleanno(&età); // passiamo direttamente l'indirizzo di memoria di età
    printf("Età dopo il compleanno: %d\n", età);
    
    printf("Premi invio per uscire...\n");
    getchar(); // pausa, aspetta invio
    
    return 0;
}