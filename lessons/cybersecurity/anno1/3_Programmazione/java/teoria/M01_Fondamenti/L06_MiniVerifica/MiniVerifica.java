package M01_Fondamenti.L06_MiniVerifica;

public class MiniVerifica {
    static void main() {

        // Dopo 5 lezioni, facciamo qualcosa di più concreto
        // Calcoliamo l'area e il volume di un parallelepipedo
        // C'è una lezione successiva sull'aritmetica, ma visto che non
        // stiamo calcolando la deviazione standard di un set di dati,
        // direi che è intuibile che * = moltiplicazione.
        double base;
        double altezza;
        double profondita;
        double area;
        double volume;
        java.util.Scanner scanner = new java.util.Scanner(System.in); // Al posto di importare, usiamo il nome completo

        // Chiediamo i valori:
        System.out.print("Inserisci la lunghezza del parallelepipedo: ");
        base = scanner.nextDouble();
        System.out.print("Inserisci l'altezza del parallelepipedo: ");
        altezza = scanner.nextDouble();
        System.out.println("Inserisci la profondità del parallelepipedo");
        profondita = scanner.nextDouble();

        // Calcoliamo l'area:
        area = 2 * (base * altezza + base * profondita + altezza * profondita);
        // Calcoliamo il volume:
        volume = base * altezza * profondita;

        // Stampiamo il risultato e chiudiamo lo scanner
        System.out.println("L'area del parallelepipedo è: " + area + " unità².");
        System.out.println("Il volume del parallelepipedo è: " + volume + " unità³.");
        scanner.close();

    }
}