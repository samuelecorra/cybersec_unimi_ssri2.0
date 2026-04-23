#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

//============================================================================================//

// Firma della funzione: il nome del file è una stringa, che in C è un array di char terminato da '\0'.
// Si passa dunque un puntatore a char come primo argomento.
// I conteggi sono restituiti tramite puntatori a int passati come argomenti aggiuntivi, in quanto
// devono essere modificati dalla funzione e ritornati al chiamante.

void analizzaFile(const char* nomeFile,
                  int* caratteri, int* parole, int* frasi, int* paragrafi) {

// Idea di base: fgets legge fino a che incontra '\n' o EOF, quindi possiamo processare il file riga per riga.
// Se incontra \n e ha dunque raggiunto la fine della riga, il puntatore a file viene incrementato ergo
// spostato alla riga successiva automaticamente, quindi fgets non si ferma mai dopo la prima riga, ma
// sa proseguire in autonomia fino a EOF. Questo comportamento ci semplifica la vita perché possiamo
// ciclare su fgets finché non ritorna NULL (EOF o errore).
// Per ogni riga letta, possiamo poi scorrerla char per char e aggiornare i conteggi richiesti.
// Serviranno alcune variabili di stato per tenere traccia se siamo dentro una parola,
// se la riga è vuota, e se l'ultimo char visto era un punto


    char buffer[301];  // 300 + '\0'
    *caratteri = *parole = *frasi = *paragrafi = 0;

    FILE* file = fopen(nomeFile, "r");
    if (!file) {
        fprintf(stderr, "Errore nell'apertura del file \"%s\".\n", nomeFile);
        return;
    }

    while (fgets(buffer, sizeof(buffer), file)) {
        bool in_word = false;     // siamo dentro una parola?
        bool riga_vuota = true;   // riga vuota finché non incontriamo un non-spazio
        bool prev_is_dot = false; // l'ultimo char visto era un punto?

        // Per ogni carattere della riga iteriamo separatamente:
        for (size_t i = 0; buffer[i] != '\0'; i++) {

            // Salviamolo come unsigned char per sicurezza
            unsigned char uch = (unsigned char)buffer[i];

            // Useremo questo unsigned char per tutti i vari test. C'è da capire però 
            // la priorità:


            // 1. Innanzitutto vanno conteggiati i caratteri "visibili": tutto tranne '\n' e '\r'
            if (uch != '\n' && uch != '\r') {
                (*caratteri)++;
            }

            // 2. La riga NON è vuota se compare almeno un non-spazio
            if (!isspace(uch)) {
                riga_vuota = false;
            }

            // 3. Conteggio frasi: conta '.' singoli (se due consecutivi, conta solo il primo)
            if (uch == '.') {
                if (!prev_is_dot) {
                    (*frasi)++;
                }
                prev_is_dot = true;
            } else {
                // Se abbiamo letto un char diverso da '.', resettiamo lo stato
                prev_is_dot = false;
            }

            // 4. Conteggio parole: ingresso in parola = transizione da spazio -> non-spazio
            // (⚠️ isspace() riconosce tutti gli spazi bianchi, non solo ' ': include anche \t, \n, \r, \v, \f.)
            if (isspace(uch)) {
                in_word = false;
            } else if (!in_word) {
                (*parole)++;
                in_word = true;
            }
        }

        // Paragrafo = riga NON vuota
        if (!riga_vuota) {
            (*paragrafi)++;
        }

        // Tecnicamente abbiamo finito di processare la riga corrente, ma per esaustività possiamo
        // aggiungere un controllo extra per righe/paragrafi troppo lunghi che dir si voglia (> 300 char).

        // Se non abbiamo letto il '\n', la riga era > 300 (input non conforme alla traccia).
        // Si usa la funzione string character strchr per cercare '\n'. Si passa il puntatore al primo carattere
        // della stringa, che in questo caso è proprio il nome dell'array buffer, e il carattere da cercare.
        // Restituisce un puntatore al primo occorrenza trovata, o NULL se non trovato.
        if (strchr(buffer, '\n') == NULL) {
            int c;
            // Magari la riga è ancora più lunga: leggiamo fino a '\n' o EOF per "svuotare" il buffer del file.
            bool overflowed = false;
            while ((c = fgetc(file)) != EOF && c != '\n') {
                // se volessimo contare questi char extra, potremmo farlo qui,
                // ma la traccia impone ≤ 300 → segnaliamo soltanto.
                overflowed = true;
            }
            if (overflowed) {
                fprintf(stderr,
                        "[Avviso] Rilevata riga > 300 caratteri: input non conforme alla traccia.\n");
                printf("La riga è stata troncata a 300 caratteri per l'analisi.\n");
            }
        }
    }

    fclose(file);
}

int main(void)
{
    char nomeFile[256];
    int caratteri, parole, frasi, paragrafi;

    printf("Inserisci nome file (con .txt): ");
    if (!fgets(nomeFile, sizeof(nomeFile), stdin)) {
        fprintf(stderr, "Errore in input.\n");
        return 1;
    }
    // rimuovi '\n' finale se presente
    nomeFile[strcspn(nomeFile, "\n")] = '\0';

    analizzaFile(nomeFile, &caratteri, &parole, &frasi, &paragrafi);

    printf("caratteri: %d\n", caratteri);
    printf("parole:    %d\n", parole);
    printf("frasi:     %d\n", frasi);
    printf("paragrafi: %d\n", paragrafi);

    return 0;
}
