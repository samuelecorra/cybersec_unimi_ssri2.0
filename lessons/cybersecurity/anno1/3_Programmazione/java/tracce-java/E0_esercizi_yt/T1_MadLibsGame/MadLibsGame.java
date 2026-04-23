package E0_esercizi_yt.T1_MadLibsGame;

public class MadLibsGame {

    // Gioco famoso nel mondo anglosassone, in cui si chiede
    // all'utente di inserire varie parole (nomi, verbi, aggettivi, ecc.)
    // per poi inserirle in una storia predefinita, creando un risultato
    // spesso divertente o assurdo.
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.print("Inserisci un nome Proprio: ");
        String nome = scanner.nextLine();

        System.out.print("Inserisci un verbo intransitivo all'infinito: ");
        String verbo = scanner.nextLine();

        System.out.print("Inserisci un aggettivo femminile: ");
        String aggettivo = scanner.nextLine();

        System.out.print("Inserisci un luogo: ");
        String luogo = scanner.nextLine();

        String storia = "C'era una volta " + nome + ", che amava " + verbo + ". "
                      + "Un giorno, " + nome + " decise di visitare " + luogo + ", "
                      + "dove incontrò una creatura " + aggettivo + ". "
                      + "E vissero tutti felici e contenti!";

        System.out.println("\nEcco la tua storia:\n");
        System.out.println(storia);

        scanner.close();
    }
}