/*

Le funzioni in C non sono altro che dei comodissimi blocchi di codice che possiamo
preconfezionare e riutilizzare in diverse parti del programma a nostro piacimento!

E' il vero e proprio salto di qualità che il programmatore esperto fa rispetto al
principiante. Si incapsula codice, e lo si richiama quando serve, senza doverlo
riscrivere ogni volta. I programmi diventano più leggibili, più ordinati, e più
facili da manutenere.
Ma soprattutto, possono crescere MOLTO sia in grandezza che in complessità, perché
non è più questione di scrivere tanto, ma di scrivere bene e in modo organizzato!

Una funzione in C si compone di 3 parti fondamentali:
1) La dichiarazione (o prototipo) della funzione
2) La definizione della funzione
3) La chiamata della funzione


// ====================================================================================


// Vediamole nel dettaglio:

1) La dichiarazione (o prototipo) della funzione
    La dichiarazione della funzione è una sorta di "promemoria" che dice al compilatore
    che esiste una funzione con un certo nome, che accetta certi parametri (di certi
    tipi) e che restituisce un certo tipo di valore (o nessun valore, se il tipo è
    "void"). La dichiarazione serve per informare il compilatore prima che la funzione
    venga effettivamente definita o chiamata. Si trova solitamente all'inizio del
    file sorgente, o in un file header (.h) separato.
    
    La sintassi generale della dichiarazione è:
    
    tipo_di_ritorno nome_funzione(tipo_parametro1, tipo_parametro2, ...);
    
    // PROMEMORIA:
    // DICHIARAZIONE = TIPO DI RITORNO + FIRMA DELLA FUNZIONE + PUNTO E VIRGOLA
    // FIRMA DELLA FUNZIONE = NOME DELLA FUNZIONE + PARAMETRI (ANCHE SENZA NOMI)
    
    Esempio:
    int somma(int a, int b); // Dichiarazione della funzione somma
    void stampa_messaggio(); // Dichiarazione della funzione stampa_messaggio
    float calcola_area(float raggio); // Dichiarazione della funzione calcola_area
    char ottieni_carattere(); // Dichiarazione della funzione ottieni_carattere

    // ATTENZIONE:
    // 1. LA DICHIARAZIONE PREVEDE PUNTO E VIRGOLA FINALE!
    // 2. LA DEFINIZIONE NON PREVEDE PUNTO E VIRGOLA FINALE!
    // 3. DICHIARAZIONE + DEFINIZIONE A INIZIO FILE NON RICHIEDONO PUNTO E VIRGOLA!
    // 4. LA DICHIARAZIONE PUO' ANCHE NON AVERE I NOMI DEI PARAMETRI, MA SOLO I TIPI!
    //    (es. int somma(int, int); è del tutto valido)

// ====================================================================================


2) La definizione della funzione
    La definizione della funzione è dove si scrive il vero e proprio codice che
    esegue l'operazione desiderata. Qui si specificano i parametri formali (i nomi
    usati all'interno della funzione) e si implementa la logica della funzione.

    La sintassi generale della definizione è:
    tipo_di_ritorno nome_funzione(tipo_parametro1 nome_parametro1, tipo_parametro2 nome_parametro2, ...) {
        // Corpo della funzione
        // Codice che esegue l'operazione
        return valore_di_ritorno; // Se il tipo di ritorno non è void
    }

// PROMEMORIA (C moderno):

// 1) Prototipo in testa -> main -> definizione dopo:                OK

// 2) Prototipo in testa -> definizione -> main:                      OK

// 3) Solo prototipo -> main chiama la funzione -> nessuna defin.:    ERRORE (linker)
//    (se la funzione non è mai chiamata, il link può comunque passare)

// 4) Definizione prima di main:                       OK (funge anche da dichiarazione)

// 5) Definizione dopo main senza prototipo:           ERRORE (compilatore)
    

// ====================================================================================


3) La chiamata della funzione
    La chiamata della funzione è il punto nel programma dove si utilizza la funzione
    definita. Si passa il numero corretto di argomenti (valori reali) che corrispondono
    ai parametri formali definiti nella funzione. Se la funzione restituisce un valore,
    questo può essere catturato e utilizzato.

    La sintassi generale della chiamata è:
    nome_funzione(argomento1, argomento2, ...);
    
    Esempio:
    int risultato = somma(5, 10); // Chiamata della funzione somma
    stampa_messaggio(); // Chiamata della funzione stampa_messaggio
    float area = calcola_area(3.5); // Chiamata della funzione calcola_area
    char carattere = ottieni_carattere(); // Chiamata della funzione ottieni_carattere


// ====================================================================================


// IN C: FUNZIONI VS PROCEDURE

// UNA PROCEDURA E' UNA FUNZIONE CHE NON RESTITUISCE NESSUN VALORE (TIPO VOID)
// UNA FUNZIONE E' UNA FUNZIONE CHE RESTITUISCE UN VALORE (TIPO DIVERSO DA VOID)     */


// ====================================================================================


#include <stdio.h>
#include <string.h>

// PROCEDURA: non restituisce nulla (void)
// Definiamo prima del main, così funge anche da dichiarazione esplicita!
void cantaAuguri(char *Nome) {
    printf("Tanti auguri a te!\n");
    printf("Tanti auguri a te!\n");
    printf("Tanti auguri a %s!\n", Nome);
    printf("Tanti auguri a te!\n");
}

// N.B. per l'io del futuro: abbiamo passato un puntatore a char (stringa)
// come parametro alla procedura. In C, le stringhe sono gestite come array di char.
// Ma potevamo anche passare direttamente un array di char, ad esempio:
// void cantaAuguri(char Nome[20]) { ... } è del tutto equivalente!

int main() {
    
    char nome[20];
    
    printf("Inserisci il nome della persona da festeggiare: ");
    fgets(nome, sizeof(nome), stdin);
    nome[strcspn(nome, "\n")] = 0; // Rimuove il newline se presente

    cantaAuguri(nome); // Chiamata della procedura!
    // Leggibilità estrema: il nome della funzione incapsula perfettamente il concetto
    // di "canto degli auguri" e ci evita di mostrare nel main 4 righe di printf!
    // Inoltre il passaggio del parametro fa intendere al lettore che il canto
    // ergo la funzione sarà personalizzato per tale nome!

    // punto forte: se il festeggiato si merita triplici auguri, richiamiamola altre due volte!
    cantaAuguri(nome);
    cantaAuguri(nome);

    // Per ricordarsi sempre di mettere le doppie parentesi tonde anche se
    // la funzione non accetta parametri, facciamo un'analogia:
    // ⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️
    // Molti la chiamano invocazione, ma "chiamata", se pensiamo alle parentesi
    // come due telefoni comunicanti, è più intuitivo e facile da ricordare!

    return 0;
}

// Per quanto riguarda le funzioni PROPRIE, rimandiamo alla lezione immediatamente
// successiva, che tratta approfonditamente la keyword return e le funzioni che
// restituiscono valori!