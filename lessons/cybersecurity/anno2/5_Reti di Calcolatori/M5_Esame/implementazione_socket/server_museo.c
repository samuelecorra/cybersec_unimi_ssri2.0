/* Esercizio capitato ad un esame da 12cfu! Vediamo insieme passo passo
come immaginarlo, come progettarlo e come scriverlo!.

Nello scenario applicativo, si immagini un client e un server.

Il server TCP del server è il "gestionale di rete" di un museo,
che fornisce la possibilità di prenotare delle visite sia ai privati che ai gruppi
più numerosi. Deve tassativamente basarsi su un'idea di forking
per poter non solo gestire più client contemporaneamente, ma anche per poter essere scalabile
nel tempo, senza dover modificare il codice per gestire più client.

Quando i client - che possono essere clienti singoli come gruppi - si connettono al server,
 devono poter prenotare una visita al museo.

Lo fanno mediante due funzioni apposite che chiediamo di implementare:

prenota_privato() e prenota_gruppo().

Vediamo che parametri avranno le funzioni:

int bookingIdNumberPriv = prenota_privato(int socket, char* nome_cliente, char* cognome_cliente, char* data_visita) {
    // Implementazione della prenotazione per un cliente privato
    // Invia i dati al server tramite il socket
    // Gestisce la risposta del server
    // Deve ricevere un numero univoco di prenotazione dal server e stamparlo a video
    // ergo già sappiamo che la funzione ritornerà un numero!
}

// Ecco anche la funzione per i gruppi:

int bookingIdNumberGrp = prenota_gruppo(int socket, char* nome_referente, char* cognome_referente, char* data_visita, int numero_partecipanti) {
    // Implementazione della prenotazione per un gruppo
    // Invia i dati al server tramite il socket
    // Gestisce la risposta del server
    // Deve ricevere un numero univoco di prenotazione dal server e stamparlo a video
    // ergo già sappiamo che la funzione ritornerà un numero!
}

Ora, scriviamo prima il codice del client oppure del server? In questo caso, è più semplice scrivere prima
il codice del server, perché è lui che gestisce la logica di prenotazione e assegna i numeri univoci di 
prenotazione.

// essenziale ovviamente includere le librerie necessarie per la programmazione di rete e per la gestione dei processi
*/

#include <stdio.h> // per printf, scanf, debugging...
#include <stdlib.h> // per exit, malloc, free...
#include <string.h> // per memset, memcpy, strcmp...
#include <unistd.h> // per close, fork, read, write...
#include <arpa/inet.h> // per sockaddr_in, inet_addr...
#include <sys/socket.h> // per socket, bind, listen, accept...
#include <sys/types.h> // per pid_t, wait...
#include <sys/wait.h> // per waitpid...
#include <signal.h> // per sigaction, SIGCHLD
#include <errno.h> // per errno, EINTR
#include <time.h> // per time, srand, rand...

#define PORT 8080
#define MAX_CLIENTS 10
#define BUFFER_SIZE 1024
#define MAX_BOOKINGS 100

// Struttura per memorizzare le prenotazioni
typedef struct {
    int bookingId;
    char nome[50];
    char cognome[50];
    char data_visita[20];
    int numero_partecipanti; // 0 per privati, >0 per gruppi
} Booking;

// Array per memorizzare le prenotazioni
Booking bookings[MAX_BOOKINGS];
int bookingCount = 0;

void reapChildren(int signum) {
    (void)signum;
    while (waitpid(-1, NULL, WNOHANG) > 0) {
    }
}

// Funzione per generare un numero di prenotazione univoco
int generateBookingId() {
    return rand() % 10000; // Genera un numero casuale tra 0 e 9999
}

// Funzione per gestire la prenotazione di un cliente privato
int prenota_privato(int socket, char* nome_cliente, char* cognome_cliente, char* data_visita) {
    if (bookingCount >= MAX_BOOKINGS) {
        printf("Limite prenotazioni raggiunto!\n");
        return -1; // Indica che non è possibile prenotare
    }
    int bookingId = generateBookingId();
    Booking newBooking;
    newBooking.bookingId = bookingId;
    strncpy(newBooking.nome, nome_cliente, sizeof(newBooking.nome) - 1);
    strncpy(newBooking.cognome, cognome_cliente, sizeof(newBooking.cognome) - 1);
    strncpy(newBooking.data_visita, data_visita, sizeof(newBooking.data_visita) - 1);
    newBooking.numero_partecipanti = 0; // Indica che è un cliente privato

    // Aggiungi la prenotazione all'array
    bookings[bookingCount++] = newBooking;

    // Invia il numero di prenotazione al client
    write(socket, &bookingId, sizeof(bookingId));

    return bookingId;
}

// Funzione per gestire la prenotazione di un gruppo
int prenota_gruppo(int socket, char* nome_referente, char* cognome_referente, char* data_visita, int numero_partecipanti) {
    if (bookingCount >= MAX_BOOKINGS) {
        printf("Limite prenotazioni raggiunto!\n");
        return -1; // Indica che non è possibile prenotare
    }
    int bookingId = generateBookingId();
    Booking newBooking;
    newBooking.bookingId = bookingId;
    strncpy(newBooking.nome, nome_referente, sizeof(newBooking.nome) - 1);
    strncpy(newBooking.cognome, cognome_referente, sizeof(newBooking.cognome) - 1);
    strncpy(newBooking.data_visita, data_visita, sizeof(newBooking.data_visita) - 1);
    newBooking.numero_partecipanti = numero_partecipanti; // Indica il numero di partecipanti del gruppo

    // Aggiungi la prenotazione all'array
    bookings[bookingCount++] = newBooking;

    // Invia il numero di prenotazione al client
    write(socket, &bookingId, sizeof(bookingId));

    return bookingId;
}

// Funzione per gestire la comunicazione con il client
void handleClient(int clientSocket) {
    char buffer[BUFFER_SIZE];
    int bytesRead;

    // Leggi la richiesta del client
    bytesRead = read(clientSocket, buffer, sizeof(buffer) - 1);
    if (bytesRead < 0) {
        perror("Errore nella lettura dal client");
        close(clientSocket);
        return;
    }
    if (bytesRead == 0) {
        fprintf(stderr, "[PID %d] Client connesso ma nessun dato ricevuto.\n", getpid());
        close(clientSocket);
        return;
    }
    buffer[bytesRead] = '\0'; // Assicurati che la stringa sia terminata
    fprintf(stderr, "[PID %d] Richiesta ricevuta: %s\n", getpid(), buffer);
    
    char* token = strtok(buffer, " ");    // Analizza la richiesta del client
    if (token == NULL) {
        fprintf(stderr, "[PID %d] Richiesta vuota/non valida.\n", getpid());
        close(clientSocket);
        return;
    }

    if (strcmp(token, "PRIVATO") == 0) {

    // Richiesta di prenotazione per un cliente privato
    char* nome_cliente = strtok(NULL, " ");
    char* cognome_cliente = strtok(NULL, " ");
    char* data_visita = strtok(NULL, " ");
    int bookingId = prenota_privato(clientSocket, nome_cliente, cognome_cliente, data_visita);
    if (bookingId >= 0) {
        printf("Prenotazione per cliente privato: %s %s, Data: %s, ID Prenotazione: %d\n", nome_cliente, cognome_cliente, data_visita, bookingId);
    }
    } else if (strcmp(token, "GRUPPO") == 0) {

    // Richiesta di prenotazione per un gruppo
    char* nome_referente = strtok(NULL, " ");
    char* cognome_referente = strtok(NULL, " ");
    char* data_visita = strtok(NULL, " ");
    int numero_partecipanti = atoi(strtok(NULL, " "));
    int bookingId = prenota_gruppo(clientSocket, nome_referente, cognome_referente, data_visita, numero_partecipanti);
    if (bookingId >= 0) {
        printf("Prenotazione per gruppo: %s %s, Data: %s, Partecipanti: %d, ID Prenotazione: %d\n", nome_referente, cognome_referente, data_visita, numero_partecipanti, bookingId);
    }
    } else {
        printf("Richiesta non valida dal client: %s\n", buffer);
    }

    close(clientSocket); // Chiudi la connessione con il client
}

// Funzione principale del server
int main() {
    int serverSocket, clientSocket;
    int opt = 1;
    struct sockaddr_in serverAddr, clientAddr;
    socklen_t addrLen = sizeof(clientAddr);
    srand(time(NULL)); // Inizializza il generatore di numeri casuali
    setvbuf(stdout, NULL, _IONBF, 0);
    setvbuf(stderr, NULL, _IONBF, 0);

    struct sigaction sa;
    memset(&sa, 0, sizeof(sa));
    sa.sa_handler = reapChildren;
    sigemptyset(&sa.sa_mask);
    sa.sa_flags = SA_RESTART;
    if (sigaction(SIGCHLD, &sa, NULL) < 0) {
        perror("Errore nella configurazione di SIGCHLD");
        exit(EXIT_FAILURE);
    }

    // Crea il socket del server
    serverSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (serverSocket < 0) {
        perror("Errore nella creazione del socket");
        exit(EXIT_FAILURE);
    }

    if (setsockopt(serverSocket, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt)) < 0) {
        perror("Errore nella configurazione SO_REUSEADDR");
        close(serverSocket);
        exit(EXIT_FAILURE);
    }

    // Configura l'indirizzo del server
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons(PORT);

    // Associa il socket all'indirizzo del server
    if (bind(serverSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) < 0) {
        perror("Errore nel bind del socket");
        close(serverSocket);
        exit(EXIT_FAILURE);
    }

    // Ascolta le connessioni in arrivo
    if (listen(serverSocket, MAX_CLIENTS) < 0) {
        perror("Errore nell'ascolto del socket");
        close(serverSocket);
        exit(EXIT_FAILURE);
    }
    
    printf("Server in ascolto sulla porta %d...\n", PORT);

    while (1) {
        // Accetta una connessione in arrivo
        clientSocket = accept(serverSocket, (struct sockaddr*)&clientAddr, &addrLen);
        if (clientSocket < 0) {
            if (errno == EINTR) {
                continue;
            }
            perror("Errore nell'accettazione della connessione");
            continue; // Continua ad accettare altre connessioni
        }
        printf("Connessione accettata da %s:%d\n", inet_ntoa(clientAddr.sin_addr), ntohs(clientAddr.sin_port));

        // Crea un processo figlio per gestire la comunicazione con il client
        pid_t pid = fork();
        if (pid < 0) {
            perror("Errore nella creazione del processo figlio");
            close(clientSocket);
            continue; // Continua ad accettare altre connessioni
        } else if (pid == 0) {
            // Processo figlio: gestisce la comunicazione con il client
            fprintf(stderr, "[PID %d] Processo figlio avviato per il client.\n", getpid());
            close(serverSocket); // Il figlio non ha bisogno del socket del server
            handleClient(clientSocket);
            exit(EXIT_SUCCESS); // Termina il processo figlio dopo aver gestito il client
        } else {
            // Processo padre: chiude il socket del client e continua ad accettare altre connessioni
            fprintf(stderr, "[PID %d] Fork completata. Child PID: %d\n", getpid(), pid);
            close(clientSocket);
        }
    }

    close(serverSocket); // Chiudi il socket del server (non raggiungibile in questo esempio)
    return 0;
}

// Come testare il codice soprastante?

// Si può scrivere un semplice client che si connette al server e invia le richieste di prenotazione
// per un cliente privato o per un gruppo, e poi riceve il numero di prenotazione assegnato dal server.
