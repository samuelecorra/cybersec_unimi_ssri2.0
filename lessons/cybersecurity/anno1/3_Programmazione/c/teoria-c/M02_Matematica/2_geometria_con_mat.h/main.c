#include <stdio.h>
#include <math.h>               // direttiva per funzioni matematiche
#include <windows.h>           // direttiva per SetConsoleOutputCP()

int main() {                    // ingresso nel main

    SetConsoleOutputCP(CP_UTF8); // forziamo UTF-8 per poter usare simboli come l'euro

    // Esempi di funzioni geometriche:

    double raggio;
    double altezzaCilindro;
    const double piGreco = 3.14159; // pi greco approssimato
    
    // Chiediamo all'utente il raggio del cerchio:
    printf("Inserisci il raggio della circonferenza aka base del cilindro: ");
    scanf("%lf", &raggio); // %lf per double

    double areaCerchio = piGreco * pow(raggio, 2); // area del cerchio
    double circonferenza = 2 * piGreco * raggio;   // circonferenza del cerchio

    // Diamo un risultato intermedio all'utente:
    printf("\nArea del cerchio di raggio %.2lf = %.2lf\n", raggio, areaCerchio);
    printf("Circonferenza del cerchio di raggio %.2lf = %.2lf\n", raggio, circonferenza);

    // Chiediamo all'utente l'altezza del cilindro:
    printf("\nInserisci l'altezza del cilindro con base circonferenza di raggio %.2lf: ", raggio);
    scanf("%lf", &altezzaCilindro); // %lf per double

    double volumeCilindro = areaCerchio * altezzaCilindro; // volume del cilindro

    // Stampiamo i risultati:
    printf("\nVolume del cilindro con area di base %.2lf e altezza %.2lf = %.2lf\n", 
            areaCerchio, altezzaCilindro, volumeCilindro);
    
    // Ora invece prendiamo come riferimento la circonferenza che abbiamo
    // calcolato prima e usiamola per calcolare superficie e volume della sfera
    // ad essa associata:
    // La formula è Ssfera = 4 * π * r^2, ma noi abbiamo gia l'area della circonferenza
    // ovvero Acerchio = r * r * π
    // Quindi possiamo riscrivere la formula della superficie della sfera come:
    double superficieSfera = 4 * areaCerchio; // superficie della sfera

    // La formula del volume della sfera è Vsfera = (4/3) * π * r^3
    // Possiamo riscriverla come Vsfera = (4/3) * r * areaCerchio
    double volumeSfera = (4.0 / 3.0) * raggio * areaCerchio; // volume della sfera

    // Stampiamo i risultati:
    printf("\nSuperficie della sfera con raggio %.2lf = %.2lf\n", raggio, superficieSfera);
    printf("Volume della sfera con raggio %.2lf = %.2lf\n", raggio, volumeSfera);

    return 0;
}