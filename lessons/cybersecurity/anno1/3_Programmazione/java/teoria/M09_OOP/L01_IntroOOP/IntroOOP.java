package M09_OOP.L01_IntroOOP;

public class IntroOOP {

    // OOP = Object Oriented Programming
    // ovvero la cosiddetta "programmazione orientata agli oggetti"

    // E' un'evoluzione della programmazione imperativa (procedurale) che gettava
    // le sue radici nel C e ancor prima nel Fortran.

    // Nella programmazione imperativa il programma e' visto come una sequenza
    // di istruzioni che il computer deve eseguire per risolvere un problema,
    // basandosi unicamente sui tre dogmi fondamentali della programmazione
    // strutturata: sequenza, selezione e iterazione.

    // Nella programmazione orientata agli oggetti, invece, il programma e' visto
    // come un insieme di oggetti che interagiscono tra loro per risolvere un
    // problema.

    // Un oggetto e' una "entita'" che incapsula dati (attributi) e comportamenti
    // (metodi) relativi a un concetto specifico.
    // Gli oggetti sono istanze di classi, che fungono da "progetto" o "blueprint"
    // per creare oggetti.

    // Questo paradigma di programmazione offre numerosi vantaggi, tra cui:
    // - Modularita': il codice e' organizzato in moduli (classi) che possono essere
    //   sviluppati, testati e mantenuti in modo indipendente.
    // - Riutilizzabilita': le classi possono essere riutilizzate in diversi progetti,
    //   riducendo la duplicazione del codice.
    // - Manutenibilita': il codice e' piu' facile da comprendere e modificare grazie
    //   alla sua struttura organizzata.
    // - Incapsulamento: i dati e i metodi sono raggruppati insieme, proteggendo lo stato interno
    //   dell'oggetto da accessi non autorizzati.
    // - Ereditarieta': le classi possono ereditare attributi e metodi da altre classi,
    //   facilitando la creazione di gerarchie di classi e il riutilizzo del codice.
    // - Polimorfismo: gli oggetti di diverse classi possono essere trattati come oggetti di una
    //   classe comune, permettendo di scrivere codice piu' flessibile e generico.

    // Java supporta nativamente la programmazione orientata agli oggetti,
    // rendendola uno dei linguaggi di programmazione piu' popolari e diffusi
    // al mondo.

    // Proviamo a creare una semplice classe "Principessa" al cui interno definiamo i dati
    // basilari di una principessa...

    static void main() {

        Principessa p1 = new Principessa(); // Creiamo un oggetto di tipo Principessa

        // La keyword new serve per allocare memoria per l'oggetto in memoria heap
        // ed è seguita dalla chiamata al costruttore della classe Principessa.
        // Vedremo i costruttori nella prossima lezione, anche se nelle scorse lezioni ne
        // abbiamo già avuto un assaggio con New Scanner() e New Random().

        // ATTENZIONE: NON SI PROVI A STAMPARE L'OGGETTO p1 COSI' COM'E'!
        System.out.println(p1); // Questo stamperà un riferimento di memoria, che appare
        // come M09_OOP.L01_IntroOOP.Principessa@8efb846 (o simile), risultato della chiamata implicita
        // al metodo toString() ereditato dalla classe Object. Approfodiremo meglio ma per ora
        // basti sapere che dobbiamo "customizzare" il metodo toString() nella classe che vogliamo
        // stampare in modo leggibile.

        // Uno sforzo in più, ma che ci garantisce totale controllo su come vogliamo
        // rappresentare i nostri oggetti quando li stampiamo a schermo.

        // Non sempre vorremo stampare tutti gli attributi di un oggetto, ma solo alcuni di essi,
        // e quindi, dopo aver creato un oggetto come abbiamo fatto nella classe Principessa,
        // potremo accedere ai suoi attributi e metodi usando la notazione punto (.):
        System.out.println("Nome principessa: " + p1.nome);
        System.out.println("Età principessa: " + p1.eta);
        System.out.println("Regno di residenza: " + p1.regnoDiResidenza);
        System.out.println("Livello potere magico: " + p1.livelloPotereMagico);
        System.out.println("È fidanzata? " + p1.eFidanzata);

        // La principessa Gommarosa è un oggetto ha anche dei metodi, ovvero dei comportamenti
        // che sono essenzialmente funzioni associate all'oggetto stesso:
        p1.castaIncantesimo();
        p1.banchetta();
        p1.siFidanza("Principe Azzurro");
        p1.siFidanza("Principe Roseo"); // Proviamo a farla fidanzare di nuovo
        // Testare l'output di questo codice per vedere come funziona l'oggetto Principessa

        // Ma se vogliamo descrivere una principessa diversa?
        Principessa p2 = new Principessa(); // Creiamo un secondo oggetto di tipo Principessa
        System.out.println("\nNome principessa: " + p2.nome);
        System.out.println("Età principessa: " + p2.eta);
        // ...e così via. Ma noteremo che p2 ha gli stessi attributi di p1.
        // Nella prossima lezione vedremo come personalizzare gli attributi di
        // ogni oggetto creato da una classe, usando i costruttori.

    }
}
