#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <windows.h>

// Innanzitutto serve una struttura 
typedef struct frazione {
    int numeratore;
    int denominatore;    
} frazione;

// Dopodiché inseriamo le frazioni in una lista:
typedef struct node {
    frazione frazioneDelNodo;
    struct node* next;
} node;

// Ora la funzione per la conta:
int contaLeFrazioniMinoriDi(node* testaLista, frazione valoreDiSoglia) {
    
    // Innanzitutto serve una variabile contatore:
    int counter = 0;
    printf("Contatore inizializzato a %d\n\n", counter);


    // Dopodiché scopriamo a quanto equivale la frazione soglia:
    double sogliaComeNumeroReale = (double)valoreDiSoglia.numeratore/valoreDiSoglia.denominatore;
    printf("La frazione di soglia, convertita a numero Reale = %.2f\n\n", sogliaComeNumeroReale);

    // Ora facciamo la stessa cosa con le varie frazioni della lista: è sufficiente un ciclo for
    // ed ergo la lista sopra non deve essere doppiamente concatenata e nemmeno con sentinella,
    // nel main basterà mettere il next dell'ultimo elemento a NULL!:
    for(node* nodoDaScorrere = testaLista; nodoDaScorrere != NULL; nodoDaScorrere = nodoDaScorrere->next) {
        // Facciamo crashare con errore se risulta inserito per errore uno 0 al denominatore della qualsiasi frazione della lista:
        if(nodoDaScorrere->frazioneDelNodo.denominatore == 0) {
            perror("Presente uno 0 al denominatore, termino.");
            exit(EXIT_FAILURE);
        }
        // Se tutte le frazioni sono correttamente inserite nella lista, possiamo procedere:
        double frazioneReale = (double)(nodoDaScorrere->frazioneDelNodo.numeratore)/nodoDaScorrere->frazioneDelNodo.denominatore;
        if(frazioneReale < sogliaComeNumeroReale) {
            printf("La frazione %d/%d, ovvero %.2f, è minore della soglia %.2f, incremento il contatore a: %d\n", nodoDaScorrere->frazioneDelNodo.numeratore, 
                                                                                                                nodoDaScorrere->frazioneDelNodo.denominatore, 
                                                                                                                frazioneReale, sogliaComeNumeroReale, counter + 1);
            counter++;
        }
        else {printf("La frazione %d/%d, ovvero %.2f, NON è minore della soglia %.2f, contatore invariato a: %d\n", nodoDaScorrere->frazioneDelNodo.numeratore, 
                                                                                                                nodoDaScorrere->frazioneDelNodo.denominatore, 
                                                                                                                frazioneReale, sogliaComeNumeroReale, counter);
        }
    }

    return counter;
}

int main(void) {

    SetConsoleOutputCP(65001);

    // Creiamo una lista:
    node* primoNodo = (node*) malloc(sizeof(node));
    primoNodo->frazioneDelNodo.numeratore = 1;
    primoNodo->frazioneDelNodo.denominatore = 2;

    node* secondoNodo = (node*) malloc(sizeof(node));
    secondoNodo->frazioneDelNodo.numeratore = 3;
    secondoNodo->frazioneDelNodo.denominatore = 4;
    primoNodo->next = secondoNodo;

    node* terzoNodo = (node*) malloc(sizeof(node));
    terzoNodo->frazioneDelNodo.numeratore = 5;
    terzoNodo->frazioneDelNodo.denominatore = 6;
    secondoNodo->next = terzoNodo;
    terzoNodo->next = NULL; // Ultimo elemento della lista

    // Definiamo la soglia:
    frazione soglia;
    soglia.numeratore = 2;
    soglia.denominatore = 3;

    // Ora chiamiamo la funzione:
    int risultato = contaLeFrazioniMinoriDi(primoNodo, soglia);
    printf("Lo scorrimento della lista è terminato.\nFrazioni minori della soglia: %d", risultato);

    printf("\nPremi un tasto per terminare...");
    getchar();

    // Libero la memoria allocata:
    free(primoNodo);
    free(secondoNodo);
    free(terzoNodo);
    return 0;
}
