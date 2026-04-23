package M11_JavaAvanzato.L02_ClassiAnonime;

public class ClassiAnonime {

    // Una classe anonima è una classe senza nome,
    // definita e istanziata in un'unica espressione.
    // Sono state introdotte in Java 1.1 per semplificare la creazione
    // di classi "usa e getta", spesso per implementare interfacce o
    // estendere classi astratte in modo rapido.

    // 1. NON POSSONO ESSERE RIUSATE
    // 2. AGGIUNGONO CUSTOM BEHAVIOR A UN'ISTANZA SENZA DOVER CREARE UNA NUOVA CLASSE
    // 3. SPESSO USATE PER ONE-TIME-USES (come vedremo in futuro TimerTask, Runnable, callbacks, ecc.)

    static void main() {

        // COME FACEVAMO FINORA?
        // Nella classe Cane abbiamo un metodo abbaia().

        // Creiamo un oggetto di tipo cane come siamo abituati a fare mediante new:
        Cane mioCane = new Cane();

        // Ora possiamo fare abbaiare il cane:
        mioCane.abbaia(); // Output: Woof Woof!

        // Ma Scooby Doo è un cane speciale che parla l'inglese, quindi tale metodo non va bene.
        CaneParlante scooby = new CaneParlante();

        // E ne utilizziamo il metodo overridden:
        scooby.abbaia(); // Output: Con un po' di Scooby Snacks, posso finalmente iniziare!

        // Però, è facile comprendere che creare una nuova classe solo per cambiare il comportamento di un metodo
        // può essere eccessivo, specialmente se ci serve solo una volta come nel caso di Scooby.

        // Eliminiamo totalmente la classe CaneParlante e creiamo una classe anonima al volo:
        Cane scoobyAnonimo = new Cane() { // NELLA GRAFFA SI DEFINISCE IL NUOVO BEHAVIOR EXTRA DELLA CLASSE ANONIMA
            @Override
            void abbaia() {
                System.out.println("Con un po' di Scooby Snacks, posso finalmente iniziare!");
            }
        };

        // E ora usiamo il metodo abbaia() del cane anonimo:
        scoobyAnonimo.abbaia(); // Output: Con un po' di Scooby Snacks, posso finalmente iniziare!

    }
}
