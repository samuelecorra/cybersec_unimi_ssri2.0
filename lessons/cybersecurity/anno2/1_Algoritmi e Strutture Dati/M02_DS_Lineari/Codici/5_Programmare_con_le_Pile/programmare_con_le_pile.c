#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <windows.h>  // Per SetConsoleOutputCP e UTF-8
typedef struct _cella {
    int elem;
    struct _cella *next, *prev;
} cella;

typedef cella* posizione;
typedef cella* lista;


void creaListaVuota(lista *L) {
    *L = malloc(sizeof(cella));
    if (!*L) { perror("malloc"); exit(1); }
    (*L)->next = *L;
    (*L)->prev = *L;
}

bool isListaVuota(lista L) {
    return L->next == L;
}

posizione primoNodo(lista L)   { return L->next; }
posizione ultimoNodo(lista L)  { return L->prev; }

posizione successivoDi(posizione p) { return p->next; }
posizione precedenteDi(posizione p) { return p->prev; }

bool fineLista(posizione p, lista L) { return p == L; } 

int leggiElemLista(posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Attenzione: sentinella raggiunta!\n");
        exit(1);
    }
    return p->elem;
}

void sovrascriviElemLista(int x, posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Attenzione: scrittura sulla sentinella non consentita!\n");
        exit(1);
    }
    p->elem = x;
}

void insElemInListaPrimaDellaPosizionePassata(int x, posizione p) {
    posizione n = malloc(sizeof(cella));
    if (!n) { perror("malloc"); exit(1); }
    n->elem = x;

    n->next = p;
    n->prev = p->prev;

    p->prev->next = n;
    p->prev = n;
}

void cancElemLista(posizione p, lista L) {
    if (fineLista(p, L)) {
        fprintf(stderr, "Errore: tentativo di cancellare la sentinella!\n");
        exit(1);
    }

    p->prev->next = p->next;
    p->next->prev = p->prev;
    free(p);
}


typedef lista pila;

void creaPilaVuota(pila *P) {
    creaListaVuota(P);
}

bool isPilaVuota(pila P) {
    return isListaVuota(P);
}

int leggiElemInPosizione(posizione p, pila P) {
    return leggiElemLista(p, P);
}

int leggiElemInCima(pila P) {
    if (isPilaVuota(P)) {
        fprintf(stderr, "Errore: leggiElemInCima su pila vuota!\n");
        exit(1);
    }
    return leggiElemLista(primoNodo(P), P);
}

int leggiElemAllaBase(pila P) {
    if (isPilaVuota(P)) {
        fprintf(stderr, "Errore: leggiElemAllaBase su pila vuota!\n");
        exit(1);
    }
    return leggiElemLista(ultimoNodo(P), P);
}

void poppaDallaCima(pila P) {
    if (isPilaVuota(P)) {
        fprintf(stderr, "Warning: pop su pila vuota ignorato.\n");
        return;
    }
    cancElemLista(primoNodo(P), P);
}

void pushaInCima(int x, pila P) {
    insElemInListaPrimaDellaPosizionePassata(x, primoNodo(P));
}

void distruggiPila(pila *P) {
    if (!*P) return;
    posizione cur = successivoDi(*P);
    while (!fineLista(cur, *P)) {
        posizione nxt = successivoDi(cur);
        cancElemLista(cur, *P);
        cur = nxt;
    }
    free(*P);  
    *P = NULL;
}

void stampaPila(pila P) {
    printf("Pila = [ Sentinella P ]");
    if (isPilaVuota(P)) {
        printf("\n\n");
        return;
    }

    printf("  [ ");
    posizione cur = primoNodo(P);
    while (!fineLista(cur, P)) {
        int elem = leggiElemInPosizione(cur, P);
        bool isTop  = (cur == primoNodo(P));
        bool isBase = (cur == ultimoNodo(P));

        if (isTop && isBase) {
            printf("%d (sia cima che base) ]", elem);
        } else if (isTop) {
            printf("%d (cima) ]", elem);
        } else if (isBase) {
            printf("%d (base) ]", elem);
        } else {
            printf("%d ]", elem);
        }

        cur = successivoDi(cur);
        if (!fineLista(cur, P)) printf("  [ ");
    }
    printf("\n\n");
}


//////////////////////////////////////////////////////////////////////


// Simulazione CPU con gestione LIFO dei lavori

#define TEMPO_RICONTROLLO 1  // tempo simulato prima che la CPU ricontrolli lo stack
#define STAMPA_PILA_DEBUG 1   // impostare a 1 per visualizzare la pila ad ogni evento


typedef int tempo;

typedef enum { ARRIVO_LAVORO, CPU_LIBERA } tipo_evento;

typedef struct {
    tipo_evento tipo;
    tempo t;
    int id_lavoro; // identificatore del lavoro
} evento;

// Lista eventi globale
typedef struct _nodo_evento {
    evento e;
    struct _nodo_evento *next;
} nodo_evento;

typedef nodo_evento* lista_eventi;


// ======= Procedure di simulazione CPU LIFO =========

// Servono prima di tutto le varie signatures per poterle poi defin
// godendo di hoisting corretto:

void avviaSimulazioneCallStackLifo(lista_eventi *l, pila stack, tempo T);
bool prossimoEvento(lista_eventi *l, evento *evento_da_prelevare);

void gestisciArrivoLavoro(const evento *E, lista_eventi *l, pila stack, tempo tempo_corrente, int *lavori_arrivati);
void eseguiLavoro(const evento *E, lista_eventi *l, pila stack, int *lavori_eseguiti, tempo tempo_corrente);
void inserisciEvento(lista_eventi *l, tipo_evento tipo, tempo t, int id_lavoro);
int generaIdLavoro(void);
int tempoEsecuzione(int id_lavoro);

// La procedura principale accetta la pila su cui lavorera' e un valore T
// che definiremo nel main come il tempo totale che diamo alla simulazione
// ergo e' arbitrario:
void avviaSimulazioneCallStackLifo(lista_eventi *l, pila stack, tempo T) {
    int lavori_completati = 0;
    int lavori_arrivati = 0;
    tempo tempo_corrente = 0;
    evento evento_corrente;

    while (tempo_corrente <= T) { // Preleviamo il prossimo evento da gestire
        
        // Caso 1: la lista eventi e' vuota perche' non ci sono piu' eventi da processare:
        // questo caso non si dovrebbe mai verificare in una simulazione ben progettata, perché
        // dovremmo sempre avere un evento di arrivo o di controllo CPU pianificato.
        if (!prossimoEvento(l, &evento_corrente)) {
            printf("Al tempo simulato [%d]\nla lista eventi e' vuota: simulazione conclusa.\n", tempo_corrente);
            break;
        }

        // Caso 2: l'evento successivo e' oltre il tempo T di simulazione: terminiamo
        // aggiornando il tempo corrente a T
        if (evento_corrente.t > T) {
            tempo_corrente = T;
            break;
        }

        // Caso 3: se l'evento corrente e' entro il tempo T, lo gestiamo
        
        // Avanziamo il tempo simulato al tempo dell'evento corrente
        tempo_corrente = evento_corrente.t;

        // E poi decidiamo cosa fare in base al tipo di evento:
        switch (evento_corrente.tipo) {
            case ARRIVO_LAVORO:
                gestisciArrivoLavoro(&evento_corrente, l, stack, tempo_corrente, &lavori_arrivati);
                break;

            case CPU_LIBERA:
                eseguiLavoro(&evento_corrente, l, stack, &lavori_completati, tempo_corrente);
                break;
        }
    }

    // Una volta raggiunto il tempo limite della simulazione stampiamo il numero di lavori completati
    printf("Simulazione conclusa al tempo %d.\nFunzioni completate: %d\n", tempo_corrente, lavori_completati);
}


// Caso 1:
void gestisciArrivoLavoro(const evento *E, lista_eventi *l, pila stack, tempo tempo_corrente, int *lavori_arrivati) {
    // 1) Impila il lavoro indicato dall'evento di arrivo
    pushaInCima(E->id_lavoro, stack);
    printf("Al tempo simulato [%d] e' arrivata la funzione %d impilata nello stack.\n", tempo_corrente, E->id_lavoro);
    if (lavori_arrivati) {
        (*lavori_arrivati)++; // Traccia il numero di arrivi gestiti
    }
    if (STAMPA_PILA_DEBUG) {
        stampaPila(stack); // Visualizza lo stato corrente della pila
    }

    // 2) Pianifica il prossimo arrivo (tempo inter-arrivo casuale)
    tempo intervallo_prossima_chiamata = 1 + rand() % 3; // Interarrivo 1-3 per ritmo sostenibile
    int id_prossimo_lavoro = generaIdLavoro();

    inserisciEvento(l, ARRIVO_LAVORO, tempo_corrente + intervallo_prossima_chiamata, id_prossimo_lavoro);
}


// Caso 2:
void eseguiLavoro(const evento *E, lista_eventi *l, pila stack, int *lavori_eseguiti, tempo tempo_corrente) {
    (void)E; // L'evento non porta dati aggiuntivi per questa gestione

    // CPU libera: se c'e qualcosa nello stack, esegue; altrimenti attende e riprova.
    if (!isPilaVuota(stack)) {
        int lavoro = leggiElemInCima(stack);
        poppaDallaCima(stack);

        // Ho davvero eseguito un job
        printf("Al tempo simulato [%d] la CPU esegue la funzione di ID: %d.\n", tempo_corrente, lavoro);
        (*lavori_eseguiti)++;

        // Pianifica il prossimo controllo CPU quando termina l'esecuzione corrente
        tempo durata_esecuzione = tempoEsecuzione(lavoro);
        inserisciEvento(l, CPU_LIBERA, tempo_corrente + durata_esecuzione, 0);
        if (STAMPA_PILA_DEBUG) {
            stampaPila(stack); // Mostra la pila dopo aver servito un lavoro
        }
    } else {
        // Nessun lavoro disponibile: rischedula un controllo dopo un intervallo di polling
        printf("Al tempo simulato [%d] la CPU e' libera e attende un nuovo lavoro nello stack.\n", tempo_corrente);
        tempo intervallo_prossimo_ricontrollo = TEMPO_RICONTROLLO;
        inserisciEvento(l, CPU_LIBERA, tempo_corrente + intervallo_prossimo_ricontrollo, 0);
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////

// Questa funzione cerca di prelevare il prossimo evento dalla lista eventi
bool prossimoEvento(lista_eventi *l, evento *evento_da_prelevare) {

    // Fallisce se la lista è vuota o se l'evento da prelevare
    if (!l || !evento_da_prelevare) {
        return false;
    }

    // Fallisce anche se la lista è vuota, ovvero se l'unico nodo è la sentinella:
    if (*l == NULL) {
        return false;
    }

    nodo_evento *nodo_da_prelevare = *l;
    *evento_da_prelevare = nodo_da_prelevare->e;
    *l = nodo_da_prelevare->next;
    free(nodo_da_prelevare);

    return true;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////




void inserisciEvento(lista_eventi *l, tipo_evento tipo, tempo t, int id_lavoro) {
    if (!l) {
        return;
    }

    // Inserimento ordinato per tempo: troviamo la posizione giusta
    nodo_evento *nuovo_nodo = malloc(sizeof(nodo_evento));
    if (!nuovo_nodo) {
        perror("malloc");
        exit(1);
    }
    nuovo_nodo->e.tipo = tipo;
    nuovo_nodo->e.t = t;
    nuovo_nodo->e.id_lavoro = id_lavoro;
    nuovo_nodo->next = NULL;

    if (*l == NULL || (*l)->e.t > t) {
        // Lista vuota o nuovo evento prima del primo nodo
        nuovo_nodo->next = *l;
        *l = nuovo_nodo;
    } else {
        // Inserimento in posizione trovata
        nodo_evento *cur = *l;
        while (cur->next != NULL && cur->next->e.t <= t) {
            cur = cur->next;
        }
        nuovo_nodo->next = cur->next;
        cur->next = nuovo_nodo;
    }
}



int generaIdLavoro(void) {
    // Restituisce un identificativo crescente per ogni nuovo lavoro
    static int prossimo_id = 1;
    return prossimo_id++;
}



int tempoEsecuzione(int id_lavoro) {
    (void)id_lavoro; // Conserveremo eventuali evoluzioni future sull'ID
    return 2 + rand() % 2; // Durata servizio 2-3 per mantenere pila attiva ma stabile
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////


int main() {

    SetConsoleOutputCP(CP_UTF8); // Imposta la console per l'output UTF-8
    srand(time(NULL)); // Inizializza il generatore di numeri casuali

    pila stack;
    lista_eventi lista_eventi_principale = NULL;

    creaPilaVuota(&stack);

    int tempo_totale_simulazione = 20; // Tempo totale di simulazione

    // Inizio simulazione: pianifica il primo evento di arrivo lavoro
    int id_primo_lavoro = generaIdLavoro();
    inserisciEvento(&lista_eventi_principale, ARRIVO_LAVORO, 0, id_primo_lavoro);

    // La CPU parte pianificando il primo controllo dello stack
    inserisciEvento(&lista_eventi_principale, CPU_LIBERA, 0, 0);

    // Avvia la simulazione
    avviaSimulazioneCallStackLifo(&lista_eventi_principale, stack, tempo_totale_simulazione);

    // Pulizia finale: distruggi la pila e svuota la lista eventi
    distruggiPila(&stack);

    while (lista_eventi_principale != NULL) {
        nodo_evento *tmp = lista_eventi_principale;
        lista_eventi_principale = lista_eventi_principale->next;
        free(tmp);
    }

    return 0;
}

