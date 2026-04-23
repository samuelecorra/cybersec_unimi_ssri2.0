/*
Nella scorsa lezione abbiamo introdotto la funzione printf(), che ci consente
comodamente di stampare del testo sulla console, e di includere al suo interno
il valore di variabili di diversi tipi, formattandole in modo opportuno.

Per saperla usare al meglio, è bene conoscere tutti i formati disponibili:


//================================================================================


🔹 Format Specifiers di base in C


Interi

%d → intero con segno (int) in base 10

%i → intero con segno (int), accetta anche notazioni ottali (0...) o esadecimali 
(0x...) in input con scanf

%u → intero senza segno (unsigned int) in base 10

%o → intero senza segno in base 8 (ottale)

%x → intero senza segno in base 16 (esadecimale, lettere minuscole a–f)

%X → come %x, ma con lettere maiuscole A–F


Caratteri

%c → singolo carattere (char)

%s → stringa terminata da \0 (array di char)


Virgola mobile

%f → numero floating-point in notazione decimale con parte intera e frazionaria

%lf → come %f, ma per double (long float)

%F → come %f, ma usa sempre la F maiuscola per valori speciali (INF, NAN)

%e → notazione scientifica (es. 1.23e+05)

%E → notazione scientifica con E maiuscola

%g → sceglie automaticamente fra %f e %e in base al valore e alla precisione

%G → come %g, ma con E maiuscola


Puntatori

%p → indirizzo di memoria (tipicamente in esadecimale)


Varie

%% → stampa un carattere % letterale // DA SAPERE!
%n → non stampa nulla, ma memorizza il numero di caratteri stampati finora


//================================================================================


🔹 Modificatori di lunghezza

// Non basta sapere i vari formati di base, perché ogni dato che andremo a stampare
// è personalizzabile con formati accessori:

// 1. Width (larghezza minima del campo): è un numero intero opzionale
CHE SI ANTEPONE al formato di base, e specifica la larghezza minima del campo in cui
stampare il valore.

Questo formato può essere preceduto da:

- nulla (default): il testo viene allineato a destra, e a sinistra vengono aggiunti
tanti spazi quanti sono quelli necessari a raggiungere la larghezza minima

- un segno meno (-): il testo viene allineato a sinistra, e a destra vengono aggiunti
tanti spazi quanti sono quelli necessari a raggiungere la larghezza minima

- un segno più (+): il testo NON viene allineato, ma viene sempre stampato il segno
+ o - davanti al numero, anche se positivo (di default il segno + non viene stampato).

- uno spazio ( ): il testo NON viene allineato, ma viene sempre stampato uno spazio
davanti al numero se positivo, e il segno - se negativo (alternativa soft al +).

- uno zero (0): il testo viene allineato a destra, e a sinistra vengono aggiunti
tanti zeri quanti sono quelli necessari a raggiungere la larghezza minima. Questo è
un modo interessante per fare estensioni di bit (bit padding) con gli zeri.

- un apostrofo ('): il testo viene allineato a destra, e a sinistra vengono aggiunti
tanti spazi quanti sono quelli necessari a raggiungere la larghezza minima. Inoltre,
viene usato il separatore delle migliaia (dipende dalla localizzazione, in Italia
è il punto, in USA la virgola). // ATTENZIONE: non tutti i compilatori lo supportano!


//================================================================================


- Il flag # in C si chiama forma alternativa (alternate form).
È un modificatore che cambia il modo in cui il numero viene stampato, 
e il suo effetto dipende dallo specifier che usi.

🔹 Casi principali:

1. Interi ottali %o

Senza #:
printf("%o\n", 10);   // stampa: 12

Con #:
printf("%#o\n", 10);  // stampa: 012

👉 Aggiunge uno 0 iniziale per segnalare che il numero è in ottale (se il valore ≠ 0).


2. Interi esadecimali %x / %X

Senza #:

printf("%x\n", 255);  // stampa: ff
printf("%X\n", 255);  // stampa: FF

Con #:

printf("%#x\n", 255); // stampa: 0xff
printf("%#X\n", 255); // stampa: 0XFF

👉 Aggiunge il prefisso 0x o 0X davanti al numero.


3. Floating-point %f, %e, %g

%f e %e: il flag # garantisce la presenza del punto decimale, anche se non servirebbe.

printf("%f\n", 5.0);   // stampa: 5.000000
printf("%#f\n", 5.0);  // stampa: 5.000000.   // il punto resta sempre

%g / %G: per default %g toglie zeri finali e anche il punto decimale se non servono. Con #, invece, li mantiene.

printf("%g\n", 123.0);   // stampa: 123
printf("%#g\n", 123.0);  // stampa: 123.000

🔹 In sintesi

Con interi in base ottale/esadecimale → aggiunge un prefisso (0, 0x, 0X).
Con floating-point → mantiene sempre il punto decimale e gli zeri finali che 
altrimenti verrebbero tolti.


//================================================================================


// 2. Precision (precisione): è un numero intero opzionale CHE SI ANTEPONE al formato
di base, e specifica il numero di cifre decimali da stampare per i numeri in
virgola mobile, o il numero massimo di caratteri da stampare per le stringhe.
// E' sempre preceduto da un punto (.).

*/

#include <stdio.h>
int main() {
    int anno = 2003;
    printf("%d\n", anno);      // stampa "2003"
    printf("%6d\n", anno);     // stampa "  2003" (due spazi a sinistra)
    printf("%-6d\n", anno);    // stampa "2003  " (due spazi a destra)
    printf("%+6d\n", anno);    // stampa " +2003" (segno + esplicito)
    printf("% 6d\n", anno);    // stampa "  2003" (spazio per i positivi)
    printf("%06d\n", anno);    // stampa "002003" (due zeri a sinistra)
    printf("%'6d\n", anno);    // stampa "2.003" (separatore delle migliaia) // non supportato, vedremo '6d e non il numero voluto
    printf("%.2d\n", anno);   // stampa "2003" (precisione non ha effetto sugli interi!!)

    double pi = 3.141592653589793;
    printf("%f\n", pi);        // stampa "3.141593" (default 6 cifre decimali)
    printf("%.2f\n", pi);      // stampa "3.14" (2 cifre decimali)
    printf("%8.2f\n", pi);     // stampa "    3.14" (8 caratteri, 2 decimali)
    printf("%-8.2f\n", pi);    // stampa "3.14    " (8 caratteri, 2 decimali, allineato a sinistra)
    printf("%+8.2f\n", pi);    // stampa "   +3.14" (8 caratteri, 2 decimali, segno + esplicito)
    printf("% 8.2f\n", pi);    // stampa "    3.14" (8 caratteri, 2 decimali, spazio per i positivi)
    printf("%08.2f\n", pi);    // stampa "00003.14" (8 caratteri, 2 decimali, zeri a sinistra)

    char nome[] = "Mario";
    printf("%s\n", nome);      // stampa "Mario"
    printf("%.3s\n", nome);    // stampa "Mar" (massimo 3 caratteri)
    printf("%8.3s\n", nome);   // stampa "     Mar" (8 caratteri, massimo 3 caratteri)
    printf("%-8.3s\n", nome);  // stampa "Mar     " (8 caratteri, massimo 3 caratteri, allineato a sinistra)
    
    // e così via...
    
    return 0;

}