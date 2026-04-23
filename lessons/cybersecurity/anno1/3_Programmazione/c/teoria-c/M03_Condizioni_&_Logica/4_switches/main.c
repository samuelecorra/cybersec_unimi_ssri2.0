/* 
 Quando è necessario verificare il valore assunto da una variabile
 tra un insieme finito e ben definito di possibilità, l’istruzione 
 switch rappresenta una soluzione elegante e leggibile. 

 In particolare, lo switch è indicato quando le condizioni da testare
 corrispondono a valori discreti (tipicamente interi o caratteri) 
 piuttosto che a intervalli o espressioni logiche complesse. 
 (fixed integer values, in gergo...)
*/

#include <stdio.h>
#include <windows.h>

int main() {
    
    SetConsoleOutputCP(CP_UTF8);

    int giorno = 0;

    if (giorno == 0) {
        printf("Oggi è Lunedì\n");
    } else if (giorno == 1) {
        printf("Oggi è Martedì\n");
    } else if (giorno == 2) {
        printf("Oggi è Mercoledì\n");
    } else if (giorno == 3) {
        printf("Oggi è Giovedì\n");
    } else if (giorno == 4) {
        printf("Oggi è Venerdì\n");
    } else if (giorno == 5) {
        printf("Oggi è Sabato\n");
    } else if (giorno == 6) {
        printf("Oggi è Domenica\n");
    } else {
        printf("Giorno non valido!\n");
    }

    // Ma rendiamoci subito conto che il codice soprastante è lungo, ripetitivo e
    // soprattutto POCO LEGGIBILE. Dijkstra si rivolterebbe nella tomba!

    // Meglio fare:
    
    printf("Inserisci un numero da 1 a 7 per scoprire il giorno corrispondente: ");
    scanf("%d", &giorno);
    getchar();

    switch (giorno) {
        case 1:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Lunedì\n");
            break;
        case 2:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Martedì\n");
            break;
        case 3:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Mercoledì\n");
            break;
        case 4:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Giovedì\n");
            break;
        case 5:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Venerdì\n");
            break;
        case 6:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Sabato\n");
            break;
        case 7:
            printf("Switchando...\n");
            printf("Scopriamo che siamo nel case di Domenica\n");
            break;
        default:    // equivalente dell'else finale nei controlli condizionali standard!
            printf("Giorno non valido!\n");
            break;
    }

    // ATTENZIONE: IL BREAK A FINE CASE E' FONDAMENTALE!
    // Serve a "spezzare" l'esecuzione dello switch, altrimenti
    // il programma proseguirebbe nell'esecuzione dei case successivi
    // fino a incontrare un break o la fine dello switch.
    // Si andrebbe a "cascare" in tutti i case successivi,
    // con risultati imprevedibili.

    // Si noti che invece anche se i case includono più istruzioni,
    // non è necessario racchiuderle tra parentesi graffe {}. Questo
    // perché lo switch non è un costrutto di controllo come l'if,
    // ma una semplice selezione di etichette (i case appunto) che
    // indirizzano il flusso di esecuzione del programma.


// ====================================================================================

    // Non è obbligatorio che i case siano numeri interi, anzi
    // spesso si usano caratteri, che sono più leggibili. Inoltre...
    // E' possibile "accorpare" più case che devono eseguire lo stesso blocco di codice:
    char voto = '\0';
    printf("Inserisci il tuo voto (A, B, C, D, F): ");
    scanf("%c", &voto);
    getchar();

    // Vogliamo fare in modo che A e B producano un complimento e che gli altri
    // producano un messaggio di incoraggiamento.
    switch (voto) {
        case 'A':
        case 'B':
            printf("Ottimo lavoro!\n");
            break;
        case 'C':
        case 'D':
        case 'F':
            printf("Puoi fare di meglio, non mollare!\n");
            break;
        default:
            printf("Voto non valido!\n");
            break;
    }
    return 0;
}