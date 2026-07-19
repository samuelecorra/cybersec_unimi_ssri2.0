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

Con printf, %f stampa un double: i float passati agli argomenti variadici vengono
promossi automaticamente a double. %lf ha lo stesso significato di %f in printf.
Con scanf la distinzione è invece essenziale: %f richiede float*, %lf richiede double*.

%Lf → long double

%F → come %f, ma usa sempre la F maiuscola per valori speciali (INF, NAN)

%e → notazione scientifica (es. 1.23e+05)

%E → notazione scientifica con E maiuscola

%g → sceglie automaticamente fra %f e %e in base al valore e alla precisione

%G → come %g, ma con E maiuscola


Puntatori

%p → rappresentazione di un puntatore; l'argomento deve avere tipo void*


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

- uno zero (0): per le conversioni numeriche compatibili, il campo viene completato
a sinistra con zeri fino alla larghezza minima. È formattazione testuale, non estensione
di bit del valore memorizzato.

- un apostrofo ('): alcune piattaforme POSIX lo offrono come estensione per il
raggruppamento locale delle cifre, ma non appartiene allo standard ISO C e non è
portabile. Per questo non viene usato nell'esempio eseguibile sottostante.


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

printf("%.0f\n", 5.0);   // stampa: 5
printf("%#.0f\n", 5.0);  // stampa: 5.   (il punto resta presente)

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
int main(void) {
    int anno = 2003;
    printf("%d\n", anno);      // stampa "2003"
    printf("%6d\n", anno);     // stampa "  2003" (due spazi a sinistra)
    printf("%-6d\n", anno);    // stampa "2003  " (due spazi a destra)
    printf("%+6d\n", anno);    // stampa " +2003" (segno + esplicito)
    printf("% 6d\n", anno);    // stampa "  2003" (spazio per i positivi)
    printf("%06d\n", anno);    // stampa "002003" (due zeri a sinistra)
    // printf("%'6d\n", anno); // estensione non ISO C: risultato dipendente da piattaforma e locale
    printf("%.2d\n", anno);    // stampa "2003": la precisione minima 2 è già superata
    printf("%.6d\n", anno);    // stampa "002003": per gli interi indica il minimo di cifre

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
