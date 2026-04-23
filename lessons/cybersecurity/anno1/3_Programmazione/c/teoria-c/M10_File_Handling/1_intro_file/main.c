
// Introduzione alla gestione dei file in C

// I file sono insiemi di dati memorizzati su un supporto di memoria secondaria,
// come un disco rigido, un SSD, una chiavetta USB, ecc. A differenza delle variabili
// che risiedono nella memoria principale (RAM) e vengono perse quando il programma
// termina, i file possono essere salvati e riaperti in un secondo momento.

// In C, la gestione dei file avviene tramite una serie di funzioni standard
// definite nella libreria <stdio.h>. Le operazioni principali che possiamo eseguire
// sui file includono l'apertura, la lettura, la scrittura e la chiusura dei file.

// Però i file in C sono concepiti come flussi di byte bufferizzati, cioè sequenze
// di byte che vengono letti o scritti in blocchi (buffer) per ottimizzare le prestazioni.

// Quindi, come facciamo a stabilire un collegamento tra il nostro programma e un file?

// Per fare questo, usiamo un puntatore a FILE, che è un tipo di dato speciale
// definito nella libreria <stdio.h>. Un puntatore a FILE rappresenta un flusso
// di dati associato a un file aperto. Quando apriamo un file, otteniamo un puntatore
// a FILE che possiamo usare per leggere o scrivere dati nel file.


//================================================================================


#include <stdio.h>
#include <stdlib.h> // necessario per usare la funzione exit()

int main() {

    FILE *ptrFile; // dichiariamo un puntatore a FILE

    // La cosa immediatamente successiva da fare è aprire un file.
    // Per aprire un file, usiamo la funzione fopen(), che ha la seguente sintassi:
    // FILE *fopen(const char *nomeFile, const char *modalità);

    // Il primo argomento è il nome del file che vogliamo aprire (incluso il percorso
    // se non si trova nella stessa cartella del programma).
    
    // Il secondo argomento è la modalità di apertura, che può essere:
    
    // "r"  - apertura in lettura (read)
    // "w"  - apertura in scrittura (write), crea un nuovo file o sovrascrive uno esistente
    // "a"  - apertura in append (append), aggiunge dati alla fine del file

    // "r+" - apertura in lettura e scrittura (read/update), il file deve esistere
    // "w+" - apertura in lettura e scrittura (write/update), crea un nuovo file o sovrascrive uno esistente
    // "a+" - apertura in lettura e scrittura (append/update), crea un nuovo file se non esiste

    // Proviamo ad aprire un file in lettura:
    ptrFile = fopen("filefolder/esempio.txt", "r");
    if (ptrFile == NULL) {
        perror("Errore apertura file");
        exit(EXIT_FAILURE);
    }
    
    printf("File aperto con successo!\n");

    // Dopo aver aperto il file, possiamo leggere o scrivere dati usando
    // funzioni come fread(), fwrite(), fprintf(), fscanf(), ecc.
    // Per ora, ci limitiamo a chiudere il file quando abbiamo finito, per evitare
    // perdite di memoria o corruzione dei dati, e migliore gestione delle risorse!
    fclose(ptrFile);
    // Chiuderlo però non setta a NULL il puntatore, quindi è buona norma farlo
    // manualmente:
    ptrFile = NULL;
    
    if (ptrFile != NULL) {
        perror("Errore chiusura file");
        exit(EXIT_FAILURE);
    }
    printf("File chiuso con successo!\n");
    
    return 0;
}