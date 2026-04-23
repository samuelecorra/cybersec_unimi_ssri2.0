#include <stdio.h>
#include <stdlib.h>             // direttiva per funzioni di utilità generale come rand() e srand()
#include <time.h>               // direttiva per time() per numeri casuali
#include <windows.h>           // direttiva per SetConsoleOutputCP()
#include <string.h>            // direttiva per funzioni di manipolazione delle stringhe come strcmp()
#include <ctype.h>             // direttiva per funzioni di manipolazione dei caratteri come toupper()


void controllaSaldo(float saldo) {
    printf("Il tuo saldo attuale è: %.2f€\n", saldo);
}


float prelevaDenaro(float saldo) {
    
    float importoDaPrelevare;
    
    printf("Inserisci l'importo da prelevare: ");
    scanf("%f", &importoDaPrelevare);
    getchar(); // Consuma il carattere di nuova linea rimasto nel buffer

    if (importoDaPrelevare > 0 && importoDaPrelevare <= saldo) {
        saldo -= importoDaPrelevare;
        printf("Hai prelevato: %.2f€. Il tuo nuovo saldo è: %.2f€.\n", importoDaPrelevare, saldo);
    } else if (importoDaPrelevare > saldo) {
        printf("Fondi insufficienti. Il tuo saldo attuale è: %.2f€.\n", saldo);
    } else {
        printf("Importo non valido. Il prelievo deve essere maggiore di 0€.\n");
    }
    return saldo;
}


float depositaDenaro() {
    
    float importoDaDepositare;
    
    printf("Inserisci l'importo da depositare: ");
    scanf("%f", &importoDaDepositare);
    getchar(); // Consuma il carattere di nuova linea rimasto nel buffer

    if (importoDaDepositare > 0) {
        printf("Hai depositato: %.2f€.", importoDaDepositare);
        return importoDaDepositare;

    } else {
        printf("Importo non valido. Il deposito deve essere maggiore di 0.\n");
    }
}

//=====================================================================================

int main() {

    SetConsoleCP(CP_UTF8); // così non avremo problemi nel digitare sì con l'accento!
    SetConsoleOutputCP(CP_UTF8); // Imposta la console per supportare i caratteri UTF-8

    float saldo = 1000.0f; // Saldo iniziale del conto
    int scelta;
    char conferma[3];

    printf("Ciao Principessa,\nanche se sei ricca sfondata...\nBenvenuta nel tuo personalissimo programma per la gestione del tuo conto bancario!\n");
    
    do {

        printf("\n--- Menu dell'app: ---\n");
        printf("1. Controlla saldo\n");
        printf("2. Preleva denaro\n");
        printf("3. Deposita denaro\n");
        printf("4. Esci\n");
       
        printf("Digita il numero corrispondente all'operazione che desideri eseguire: ");
        scanf("%d", &scelta);
        getchar(); // Consuma il carattere di nuova linea rimasto nel buffer

        switch (scelta) {
            case 1:
                controllaSaldo(saldo);
                break;
            case 2:
                saldo = prelevaDenaro(saldo);
                break;
            case 3:
                saldo += depositaDenaro();
                printf(" Il tuo nuovo saldo dopo il deposito è: %.2f€.\n", saldo);
                break;
            
            case 4:

                printf("Sei sicuro di voler uscire? (sì/no): ");
                scanf("%s", conferma);
                // Rimuoviamo il carattere di nuova linea rimasto nel buffer
                getchar();

                // Convertiamo la risposta in minuscolo per facilitare il confronto
                for(int i = 0; conferma[i]; i++){
                    conferma[i] = tolower(conferma[i]);
                }
                if (strcmp(conferma, "sì") == 0) {
                    printf("Uscita dal programma. Arrivederci!\n");
                    exit(0);
                } else {
                    printf("Ritorno al menu principale.\n");
                    // Dobbiamo modificare la scelta perché nonostante sia stata
                    // settata a 4 non vogliamo uscire dal programma
                    scelta = 0;
                }
                break;

            // Fine del case 4

            default:
                printf("Opzione non valida. Riprova.\n");
        }
    } while (scelta != 4);

    return 0;
}