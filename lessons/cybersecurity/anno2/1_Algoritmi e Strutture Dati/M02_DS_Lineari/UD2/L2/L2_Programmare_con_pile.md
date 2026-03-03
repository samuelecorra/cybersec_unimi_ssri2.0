## **Lezione 2: Programmare con le pile**

---

### **1. Obiettivo della lezione**

Scopo della lezione è imparare a **utilizzare gli operatori fondamentali della pila** per risolvere un problema computazionale realistico, seguendo lo stesso schema logico introdotto nel Modulo 1.

Vedremo come una pila, grazie alla sua natura **LIFO**, possa modellare in modo naturale i sistemi che lavorano “in ordine inverso”: l’ultimo elemento entrato è il primo a uscire.

---

### **2. Il problema del servente**

Immaginiamo un sistema capace di **eseguire una sequenza di lavori**, uno alla volta.  
In questo sistema, il ruolo principale è svolto dal **servente**, che possiamo pensare come una **CPU**: è l’unità che _esegue effettivamente_ i lavori, ma non può farne più di uno alla volta.

Accanto al servente esiste una **lista di lavori in attesa**, che rappresenta la **pila delle operazioni da eseguire**.  
Questa pila segue una politica **LIFO (Last In, First Out)**: ogni nuovo lavoro che arriva viene **messo in cima**, e il servente riprenderà sempre da lì.  
In altre parole, **l’ultimo lavoro arrivato è il primo a essere servito**.

Per comprendere il significato pratico di questa regola, possiamo pensare al **call stack** di un programma:  
quando una funzione ne chiama un’altra, la CPU sospende quella attuale e mette la nuova chiamata “in cima” alla pila di esecuzione.  
Solo quando l’ultima funzione termina, la CPU torna a quella precedente.  
Il meccanismo è identico a quello di un servente che gestisce i lavori con logica LIFO:  
ogni “chiamata” è un nuovo lavoro che entra in cima, e ogni “ritorno” è un lavoro completato che esce dalla pila.

Il **servente**, quindi, non è altro che il **processo che estrae dalla pila il lavoro più recente e lo completa**, aggiornando lo stato del sistema.  
La **lista dei lavori in attesa** rappresenta invece tutto ciò che il sistema deve ancora fare, ma che non può essere eseguito fino a quando non terminano le operazioni più recenti.

#### **🧩 1. Cos’è un “event-driven LIFO senza gerarchia”**

- **Event-driven** → significa che _non c’è un flusso continuo_, ma una sequenza di “eventi” che accadono nel tempo.  
    Ogni evento genera o pianifica altri eventi futuri.
    
- **LIFO (Last In, First Out)** → significa che l’ultimo elemento inserito è il primo a essere servito o gestito.
    
- **Senza gerarchia** → significa che gli eventi non si “chiamano” tra loro in una catena logica; sono indipendenti, anche se condividono la stessa risorsa.
    

Quindi è un sistema dove:

> Gli eventi arrivano nel tempo, indipendentemente gli uni dagli altri,  
> ma vengono serviti **in ordine inverso** rispetto all’arrivo,  
> perché la risorsa che li gestisce (una CPU, un tecnico, un processo)  
> funziona “a pila”.

---

#### **⚙️ 2. La chiave concettuale**

In un sistema LIFO **senza gerarchia**, la pila non rappresenta _dipendenze_, ma _priorità operative_.  
Non c’è logica del tipo “questo chiama quello”, ma solo “questo è arrivato dopo → quindi tocca prima a lui”.

È un **criterio di servizio** (ordine inverso) applicato a un flusso di eventi indipendenti.

---

#### 🔍 **3. Esempi concreti nella realtà**

Vediamo tre esempi perfetti, ognuno in un contesto diverso (hardware, software, umano).

---

##### **🖥️ ESEMPIO 1 — Gestione delle interruzioni hardware in un microprocessore**

Immagina una CPU che riceve **interruzioni (interrupt)** da vari dispositivi:

- la tastiera preme un tasto,
    
- la scheda di rete riceve un pacchetto,
    
- il timer di sistema scatta.
    

Ogni interrupt è **un evento**.

Ora, se la CPU sta già gestendo un interrupt e ne arriva un altro con priorità più alta, cosa fa?

> ❗️Sospende quello in corso e “spinge” il nuovo sulla **pila** degli interrupt.  
> Quando il nuovo è finito, “poppa” e riprende il precedente.

➡️ Ecco un **event-driven LIFO puro**, senza gerarchia logica tra eventi.  
Ogni interrupt è indipendente, ma gestito con priorità LIFO.

**Non c’è gerarchia**, solo _pre-emption_.

---

##### 🧠 **ESEMPIO 2 — Browser con finestre di dialogo modali**

Apri un browser, poi:

1. apri una scheda (evento 1),
    
2. apri una finestra “Preferenze” (evento 2),
    
3. e dentro quella apri un “Avviso di sicurezza” (evento 3).
    

Ora l’interfaccia gestisce solo **l’ultima finestra aperta** (la più in cima).  
Chiudi l’avviso → torni alle preferenze → poi alla scheda.

➡️ Ogni finestra aperta è **un evento gestito LIFO**, ma:

- nessuna finestra _chiama_ l’altra,
    
- semplicemente _si sovrappongono_ in modo temporaneo.
    

**Event-driven:** sì (le aperture sono eventi).  
**LIFO:** sì (l’ultima aperta viene chiusa per prima).  
**Gerarchia:** no (non esiste un legame di chiamata semantica, solo visiva).

---

##### 🧍 **ESEMPIO 3 — Lavoro di un tecnico multitasking “impaziente”**

Immagina un tecnico informatico che lavora da solo, ma riceve richieste telefoniche casuali durante il giorno.

- Ogni chiamata è un **evento casuale nel tempo**.
    
- Ogni richiesta viene messa su uno **stack** mentale (o fisico) di post-it.
    
- Ogni volta che ne arriva una nuova, il tecnico la mette **sopra le altre** e la affronta subito (è quella più recente e “calda”).
    
- Quando la finisce, toglie il post-it e torna a quella sotto.
    

➡️ Questo è un **sistema event-driven LIFO perfetto**:

- Gli eventi arrivano indipendentemente (chiamate casuali).
    
- Il tecnico li gestisce in ordine inverso (LIFO).
    
- Non c’è nessuna gerarchia: le chiamate non dipendono l’una dall’altra.
    

---

##### **🧠 4. Osserva cosa non c’è (ed è importante)**

In tutti questi esempi:

- Non c’è un “albero” di dipendenze logiche.
    
- Non c’è bisogno di _ritornare un valore_ a chi ha chiamato.
    
- Non c’è un “caller” o “callee”.
    
- La pila serve **solo per sospendere e riprendere** lavori,  
    non per rappresentare _chiamate annidate_.
    

È un uso **operativo**, non **semantico** della pila.
#### **Obiettivo Principale:**

Scrivere una procedura `C` che simuli l’esecuzione di questi lavori per un certo intervallo temporale $T$, restituendo:

1. la lista (pila) dei lavori rimasti in attesa,
    
2. e il numero totale di lavori completati.
    

---

### **3. Visualizzazione del processo**

#### **Schema logico**

$$  
\text{Arrivo lavoro} \longrightarrow \text{Inserimento in pila} \longrightarrow  
\text{Servente preleva top} \longrightarrow \text{Esecuzione lavoro}  
$$

---

#### **Esempio**

| Tempo | Azione            | Pila (top → base) |
| ----- | ----------------- | ----------------- |
| 0     | Arriva lavoro A   | A                 |
| 1     | Arriva lavoro B   | B → A             |
| 2     | Servente esegue B | A                 |
| 3     | Arriva lavoro C   | C → A             |
| 4     | Servente esegue C | A                 |

> Il comportamento è perfettamente **LIFO**: il lavoro arrivato più di recente è sempre quello servito per primo.

---

### **4. Complessità del problema**

#### **Dimensione dell’input**

Nel caso pessimo, il tempo $T$ è sufficiente per completare tutti i $n$ lavori.  
Ogni lavoro deve quindi essere **estratto una volta dalla pila**, perciò:

$$  
\Omega(n)  
$$

#### **Eventi contabili**

L’esecuzione completa richiede almeno $n$ estrazioni → anche qui:  

$$  
\Omega(n)  
$$

---

### **5. 🧠 Idea di base dell'algoritmo-simulatore**

Per concretizzare quanto detto prima, vogliamo **costruire un piccolo mondo artificiale dove il tempo scorre a eventi discreti**.  
Non c’è un cronometro reale: il tempo è solo una **variabile logica** chiamata `t`, che avanza ogni volta che succede qualcosa di interessante (un nuovo lavoro arriva, o il servente finisce quello che stava facendo).

L’idea di fondo è questa:

1. Abbiamo un **servente** — pensa a una **CPU** o a un **interprete di funzioni** — che può eseguire **un solo lavoro per volta**.
    
2. I **lavori** arrivano nel tempo (come nuove chiamate da gestire) e vengono **impilati in una pila LIFO**.
    
3. Ogni lavoro richiede un certo **tempo di esecuzione** (non necessariamente uguale per tutti).
    
4. La simulazione va avanti “saltando” da un evento al successivo: non contiamo ogni singolo istante, ma solo i momenti in cui _qualcosa cambia_.
    

Ciò che conta è comprendere **l’idea della simulazione a eventi discreti**, per ora non serve scrivere un programma per eseguire davvero dei job.  
È un modello mentale per capire come “un sistema evolve nel tempo” secondo regole locali (come la CPU che prende e rilascia chiamate sullo stack).

---

### **6. Algoritmo iterativo**

#### **Codice generale**

Il cuore della simulazione è il ciclo `while (t <= T)` — che significa:

> “Ripeti finché non ho simulato tutti gli eventi previsti nel tempo logico T”.

Ogni volta dentro il ciclo succede questo:

1. Si prende il **prossimo evento da elaborare** `eventoSucc(t)`
    
2. Si aggiorna il tempo logico `t = E->t` cioè ci si sposta al momento in cui l’evento accade.
    
3. Si guarda **che tipo di evento è**:
    
    - se è un **arrivo di lavoro**, lo aggiungo in cima alla pila;
        
    - se è un **servente libero**, estraggo il lavoro in cima e lo “eseguo”.
        
4. Poi pianifico il **prossimo evento** (nuovo arrivo o nuovo lavoro completato) aggiornando il tempo.
    

È un ciclo _auto-alimentato_: ogni evento genera il prossimo, e così la simulazione avanza in modo deterministico.  
Il “tempo che scorre” è in realtà **una successione di salti da evento a evento**, non un contatore che aumenta di uno in uno.


---

#### **Implementazione in C**

##### **1. Premessa**

Lascio innanzitutto come riferimento le due sezioni su lista bidirezionale con sentinella e pila da essa derivante, giusto per comodità:

```c
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <windows.h>  // Per SetConsoleOutputCP e UTF-8

// ========================== SEZIONE 0: LISTA ================================ //

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

posizione primoNodo(lista L)   { return L->next; }
posizione ultimoNodo(lista L)  { return L->prev; }

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
        fprintf(stderr, "Attenzione: scrittura sulla sentinella non" 
        "consentita!\n");
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

// ========================== SEZIONE 1: PILA ================================ //

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
    
    printf("  [ ");
    posizione cur = primoNodo(P);
    
    while (!fineLista(cur, P)) {
        int elem = leggiElemInPosizione(cur, P);
        bool isTop  = (cur == primoNodo(P));
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
        if (!fineLista(cur, P)) printf("  [ ");
    }
    printf("\n\n");
}

```

Ora che abbiamo queste specifiche-premesse, possiamo procedere...

---

```c
//////////////////////////////////////////////////////////////////////

// Simulazione CPU con gestione LIFO dei lavori

#define TEMPO_RICONTROLLO 1  // tempo simulato prima che la CPU ricontrolli lo stack

#define STAMPA_PILA_DEBUG 1   // impostare a 1 per visualizzare la pila ad ogni evento

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
// questo caso non si dovrebbe mai verificare in una simulazione ben progettata, perché dovremmo sempre avere un evento di arrivo o di controllo CPU pianificato.

        if (!prossimoEvento(l, &evento_corrente)) {
            printf("Al tempo simulato [%d]\nla lista eventi e' vuota: simulazione" 
            "conclusa.\n", tempo_corrente);
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
                gestisciArrivoLavoro(&evento_corrente, l, stack, tempo_corrente, 
                &lavori_arrivati);
                break;

            case CPU_LIBERA:
                eseguiLavoro(&evento_corrente, l, stack, &lavori_completati, 
                tempo_corrente);
                break;
        }
    }

// Una volta raggiunto il tempo limite della simulazione stampiamo il numero di lavori completati
    printf("Simulazione conclusa al tempo %d.\nFunzioni completate: %d\n", 
    tempo_corrente, lavori_completati);
}

// Caso 1:
void gestisciArrivoLavoro(const evento *E, lista_eventi *l, pila stack, tempo tempo_corrente, int *lavori_arrivati) {
    // 1) Impila il lavoro indicato dall'evento di arrivo
    pushaInCima(E->id_lavoro, stack);
    printf("Al tempo simulato [%d] e' arrivata la funzione %d impilata nello" 
    "stack.\n", tempo_corrente, E->id_lavoro);

    if (lavori_arrivati) {
        (*lavori_arrivati)++; // Traccia il numero di arrivi gestiti
    }

    if (STAMPA_PILA_DEBUG) {
        stampaPila(stack); // Visualizza lo stato corrente della pila
    }

    // 2) Pianifica il prossimo arrivo (tempo inter-arrivo casuale)
    tempo intervallo_prossima_chiamata = 1 + rand() % 3; // Interarrivo 1-3 per 
    // ritmo sostenibile
    int id_prossimo_lavoro = generaIdLavoro();
    inserisciEvento(l, ARRIVO_LAVORO, tempo_corrente + 
    intervallo_prossima_chiamata, id_prossimo_lavoro);
}

// Caso 2:
void eseguiLavoro(const evento *E, lista_eventi *l, pila stack, int *lavori_eseguiti, tempo tempo_corrente) {
    (void)E; // L'evento non porta dati aggiuntivi per questa gestione
    // CPU libera: se c'e qualcosa nello stack, esegue; altrimenti attende e riprova.
    if (!isPilaVuota(stack)) {
        int lavoro = leggiElemInCima(stack);
        poppaDallaCima(stack);
        // Ho davvero eseguito un job
        printf("Al tempo simulato [%d] la CPU esegue la funzione di ID: %d.\n", 
        tempo_corrente, lavoro);
        (*lavori_eseguiti)++;
        // Pianifica il prossimo controllo CPU quando termina l'esecuzione corrente
        tempo durata_esecuzione = tempoEsecuzione(lavoro);
        inserisciEvento(l, CPU_LIBERA, tempo_corrente + durata_esecuzione, 0);
        
        if (STAMPA_PILA_DEBUG) {
            stampaPila(stack); // Mostra la pila dopo aver servito un lavoro
        }
    } else {
        // Nessun lavoro disponibile: rischedula un controllo dopo un intervallo 
        // di polling
        printf("Al tempo simulato [%d] la CPU e' libera e attende un nuovo lavoro" 
        "nello stack.\n", tempo_corrente);

        tempo intervallo_prossimo_ricontrollo = TEMPO_RICONTROLLO;
        inserisciEvento(l, CPU_LIBERA, tempo_corrente + 
        intervallo_prossimo_ricontrollo, 0);
    }
}

///////////////////////////////////////////////////////////////////////////////////

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

///////////////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////////////////////

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
    avviaSimulazioneCallStackLifo(&lista_eventi_principale, stack, 
    tempo_totale_simulazione);

    // Pulizia finale: distruggi la pila e svuota la lista eventi
    distruggiPila(&stack);

    while (lista_eventi_principale != NULL) {
        nodo_evento *tmp = lista_eventi_principale;
        lista_eventi_principale = lista_eventi_principale->next;
        free(tmp);
    }

    return 0;
}
```

---

##### **10. Cosa rappresenta davvero**

Questa simulazione non è un gioco di print, ma una **mini CPU virtuale** che usa:

- **una pila come call stack**,
    
- **una lista eventi come orologio del mondo**,
    
- **eventi pianificati** per simulare il tempo reale,
    
- **gestione LIFO** per rispettare la logica del “servente a pila”.
    

In altre parole, è una **astrazione funzionale del kernel** che pianifica e gestisce processi secondo una politica di scheduling LIFO, implementata con sole strutture dati dinamiche e puntatori puri.


---

### **8. Analisi della complessità**

#### **Premessa concettuale**

Nel programma la simulazione ruota attorno a due strutture dati separate:

1. **La pila (`pila stack`)** — rappresenta lo _stato LIFO_ della CPU:  
    contiene i lavori in attesa di essere eseguiti e cresce o si riduce con le operazioni di `push` e `pop`.
    
2. **La lista degli eventi (`lista_eventi l`)** — rappresenta il _calendario temporale_ della simulazione:  
    è una lista ordinata in base al tempo di accadimento `t` di ciascun evento (arrivo lavoro o CPU libera).
    

L’algoritmo di simulazione principale (`avviaSimulazioneCallStackLifo`) avanza il tempo prelevando sempre **il primo evento temporale**, eseguendolo e pianificando i nuovi eventi futuri.

---

#### **Ipotesi di riferimento**

Per stimare la complessità, si adotta la classica ipotesi accademica del **benchmark server**:

- La pila **non si svuota mai**: la CPU trova sempre almeno un lavoro da eseguire.  
    (In questo modo si evita di considerare tempi morti o eventi di “attesa” e si massimizza il carico operativo.)
    
- Il ciclo principale `while` del simulatore si ripete **n volte**, dove `n` è il numero totale di eventi processati.
    

---

#### **Analisi degli operatori interni**

Durante ogni iterazione del ciclo di simulazione vengono eseguite alcune operazioni fondamentali su entrambe le strutture:

##### **1. Operazioni sulla pila (`stack`)**

Le funzioni coinvolte sono:

- `pushaInCima()` → inserisce un nuovo lavoro in testa (push)
    
- `poppaDallaCima()` → rimuove il lavoro in cima (pop)
    
- `leggiElemInCima()` → legge il lavoro corrente senza toglierlo
    

Tutte queste operazioni agiscono direttamente sui puntatori `next` e `prev` della lista doppiamente concatenata **senza scorrere la struttura**, quindi hanno **complessità costante**:

$$  
O(1)  
$$

---

##### **2. Operazioni sulla lista degli eventi (`lista_eventi`)**

Le funzioni principali sono:

- `prossimoEvento()` → estrae l’evento con tempo minimo (la testa della lista)
    
- `inserisciEvento()` → inserisce un nuovo evento nella posizione ordinata rispetto al tempo `t`
    

Nel tuo codice:

- L’estrazione avviene sempre in testa → **complessità costante** $O(1)$  
    (poiché basta spostare la testa e liberare un nodo)
    
- L’inserimento, invece, richiede la ricerca della posizione corretta in ordine crescente di `t`.  
    Nel caso peggiore (nuovo evento con tempo maggiore di tutti gli altri) occorre scorrere **tutta la lista** fino in fondo → **complessità lineare**:
    

$$  
O(n)  
$$

---

#### **Combinazione delle due strutture**

Ogni iterazione del ciclo:

1. estrae un evento dalla lista ($O(1)$);
    
2. aggiorna lo stato della pila ($O(1)$);
    
3. inserisce almeno un nuovo evento nella lista in posizione ordinata ($O(n)$).
    

L’operazione dominante, quindi, è **l’inserimento ordinato nella lista eventi**.

---

#### **Complessità complessiva**

Dato che per ogni iterazione (in totale `n` eventi) si compie almeno un inserimento con costo O(n):

$$  
T(n) = n \times O(n) = O(n^2)  
$$

---

#### **Considerazioni finali**

L’algoritmo **non è ottimale** in termini di efficienza asintotica, ma la sua costruzione è **didatticamente completa e perfettamente coerente** con l’obiettivo del corso:  
dimostrare come una **simulazione discreta** possa essere realizzata a partire da strutture fondamentali (liste e pile) e da un **meccanismo di avanzamento del tempo** gestito tramite eventi.

In contesti reali, per ridurre la complessità complessiva si potrebbe sostituire la lista eventi con una **coda di priorità** o un **heap binario**, portando l’inserimento e l’estrazione a costo $O(\log n)$ e quindi una complessità totale $O(n \log n)$.

---

#### **Sintesi**

|Struttura|Operazione|Complessità|Descrizione|
|---|---|---|---|
|**Pila**|push/pop/lettura|$O(1)$|Gestione LIFO immediata|
|**Lista eventi**|Estrazione in testa|$O(1)$|Recupero evento più prossimo|
|**Lista eventi**|Inserimento ordinato|$O(n)$|Inserimento per tempo crescente|
|**Simulazione totale**||$O(n^2)$|$n$ iterazioni × inserimento $O(n)$|

---

### **9. Sintesi finale**

| Aspetto                   | Descrizione                                        |
| ------------------------- | -------------------------------------------------- |
| **Struttura usata**       | Pila LIFO                                          |
| **Scenario**              | Simulazione di lavori con servente                 |
| **Operatori principali**  | `inpila`, `fuoripila`, `leggipila`                 |
| **Complessità**           | $O(n^2)$                                           |
| **Significato didattico** | Modello base per la simulazione discreta di eventi |

---

> Questa lezione mostra come un semplice tipo di dato come la **pila** possa modellare sistemi reali di esecuzione, in cui le priorità cambiano in modo dinamico.  
> L’efficienza non è ancora ottimale — ma il modello è perfetto per comprendere come nasce la **logica delle simulazioni a eventi discreti**.

---

