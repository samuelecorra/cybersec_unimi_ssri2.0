/* Il typedef in C è una keyword riservata dall'enorme potenzialità:

   - Semplificare la dichiarazione di tipi complessi
   - Aumentare la leggibilità del codice
   - Creare alias per tipi esistenti


    Sintassi:

    typedef tipo_esistente nuovo_nome;
    
// ATTENZIONE: I TYPEDEF SI DICHIARANO FUORI DAL MAIN, COME LE LIBRERIE - PERCHE'?
// Perché il typedef crea un alias che può essere utilizzato in tutto il file,
// quindi deve essere dichiarato in un ambito globale, non all'interno di una funzione!
// Se lo dichiariamo dentro il main, il suo ambito sarà limitato a quella funzione ->
// Se il nostro programma vuole quei tipi user-defined anche in funzioni diverse
// dal main, non potrà utilizzarli!             */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <windows.h>

    typedef int Intero;
    
    // la figata risiede nel fatto che ora possiamo anche facilitarci la scrittura
    // delle stringhe, che in C è risaputa essere una pratica scomodissima rispetto
    // a Java e altri linguaggi più moderni!

    // Abbiamo due modi di farlo:
    // 1) Usare il typedef per creare un alias per un array di char di dimensione fissa
    //    (ma in questo modo limitiamo la lunghezza della stringa a 9 caratteri + '\0')
    typedef char Stringa[15];

    // 2) Usare il typedef per creare un alias per un puntatore a char
    typedef char* PtrStringa;
    // In questo caso non dobbiamo dichiarare subito la dimensione dell'array,
    // ma dobbiamo ricordarci di allocare dinamicamente la memoria quando
    // utilizziamo il puntatore.

int main() {

    SetConsoleOutputCP(CP_UTF8);

    // Esempio con typedef per int:
    Intero a = 10;
    Intero b = 20;
    
    Intero somma = a + b;
    printf("La somma di %d e %d è %d\n", a, b, somma);
    
    printf("Premi INVIO per continuare...\n");
    getchar();

    // Esempio con typedef per array di char:
    Stringa nome1 = "Principessa";
    PtrStringa nome2 = (char*)malloc(20 * sizeof(char)); // Allocazione dinamica
    // ma è un'argomento avanzato quindi ora lo citiamo e basta...

    // Stampiamo le due stringhe:
    printf("Nome1: %s\n", nome1);
    printf("Inserisci il tuo nome: ");
    fgets(nome2, 20, stdin);
    nome2[strcspn(nome2, "\n")] = '\0'; // Rimuoviamo il newline se presente
    printf("Nome2: %s\n", nome2);

    // Liberiamo la memoria allocata    // IMPORTANTE!
    free(nome2);

    return 0;
}

