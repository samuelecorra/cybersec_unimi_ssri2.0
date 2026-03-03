// Dopo aver scritto il codice del server, ora implementiamo il client che si connetterà al server e
// invierà una richiesta. Il client invierà i dati necessari per effettuare una prenotazione,
// come il nome, cognome, data della visita e il numero di partecipanti (per i gruppi).
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define PORT 8080
#define BUFFER_SIZE 1024

int main() {
    int clientSocket;
    struct sockaddr_in serverAddr;
    char buffer[BUFFER_SIZE];
    int tipoPrenotazione;
    char nome[50];
    char cognome[50];
    char dataVisita[20];
    int numeroPartecipanti = 0;

    // Crea il socket
    clientSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (clientSocket < 0) {
        perror("Errore nella creazione del socket");
        exit(EXIT_FAILURE);
    }

    // Configura l'indirizzo del server
    memset(&serverAddr, 0, sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");
    serverAddr.sin_port = htons(PORT);
    // Connetti al server
    if (connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr)) < 0) {
        perror("Errore nella connessione al server");
        close(clientSocket);
        exit(EXIT_FAILURE);
    }
    // Raccogli i dati della prenotazione dall'utente
    printf("Scegli il tipo di prenotazione (1 = PRIVATO, 2 = GRUPPO): ");
    if (scanf("%d", &tipoPrenotazione) != 1) {
        fprintf(stderr, "Input non valido per il tipo di prenotazione.\n");
        close(clientSocket);
        return EXIT_FAILURE;
    }

    printf("Nome: ");
    if (scanf("%49s", nome) != 1) {
        fprintf(stderr, "Input non valido per il nome.\n");
        close(clientSocket);
        return EXIT_FAILURE;
    }

    printf("Cognome: ");
    if (scanf("%49s", cognome) != 1) {
        fprintf(stderr, "Input non valido per il cognome.\n");
        close(clientSocket);
        return EXIT_FAILURE;
    }

    printf("Data visita (YYYY-MM-DD): ");
    if (scanf("%19s", dataVisita) != 1) {
        fprintf(stderr, "Input non valido per la data visita.\n");
        close(clientSocket);
        return EXIT_FAILURE;
    }

    if (tipoPrenotazione == 1) {
        snprintf(buffer, sizeof(buffer), "PRIVATO %s %s %s", nome, cognome, dataVisita);
    } else if (tipoPrenotazione == 2) {
        printf("Numero partecipanti: ");
        if (scanf("%d", &numeroPartecipanti) != 1 || numeroPartecipanti <= 0) {
            fprintf(stderr, "Numero partecipanti non valido.\n");
            close(clientSocket);
            return EXIT_FAILURE;
        }
        snprintf(buffer, sizeof(buffer), "GRUPPO %s %s %s %d", nome, cognome, dataVisita, numeroPartecipanti);
    } else {
        fprintf(stderr, "Tipo prenotazione non valido. Usa 1 o 2.\n");
        close(clientSocket);
        return EXIT_FAILURE;
    }

    // Invia la richiesta al server
    if (write(clientSocket, buffer, strlen(buffer)) < 0) {
        perror("Errore nell'invio della richiesta al server");
        close(clientSocket);
        return EXIT_FAILURE;
    }

    // Leggi la risposta dal server (numero di prenotazione)
    int bookingId;
    if (read(clientSocket, &bookingId, sizeof(bookingId)) != sizeof(bookingId)) {
        perror("Errore nella lettura della risposta dal server");
        close(clientSocket);
        return EXIT_FAILURE;
    }
    printf("Numero di prenotazione ricevuto dal server: %d\n", bookingId);
    // Chiudi il socket
    close(clientSocket);
    return 0;
}

// Per testare il client, assicurati di avviare prima il server e poi esegui il client.
// Il server dovrebbe stampare le informazioni della prenotazione ricevuta dal client, 
// e il client dovrebbe ricevere e stampare il numero di prenotazione generato dal server.