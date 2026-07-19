package M01_Fondamenti.L1_Intro;

public class Intro {
    public static void main(String[] args) { // psvm
        System.out.println("Ciao, benvenuto al corso di Java!"); // sysout
        System.out.println("Questa istruzione usa ancora println.");
        int eta = 15;
        System.out.println("Età di esempio: " + eta);
    }
}

// NOTE UTILI PER UN JUMPSTART CON COGNIZIONE DI CAUSA:

// 1. La prima riga definisce il package di appartenenza della classe.
//    Poiché la cartella `teoria` è stata configurata come *source root*,
//    tutte le sottocartelle diventano automaticamente package Java.
//    Pertanto, il percorso della classe all’interno del filesystem
//    (M01_Fondamenti/L1_Intro) si traduce nel nome di package
//    `M01_Fondamenti.L1_Intro`.
//    Questa dichiarazione è fondamentale perché permette a Java di
//    organizzare il codice in maniera modulare e gerarchica, mantenendo
//    chiara la struttura logica del progetto.


// 2. public static void main(String[] args) si può comodamente scrivere
//    mediante lo shortcut "psvm" in molti IDE.

//    Nel contratto classico e portabile usato dal launcher Java, il main è
//    public static void main(String[] args). Versioni recenti di Java hanno
//    sperimentato forme più concise, ma non sono adatte a esempi compatibili.

// 3. System è una classe "core" di Java, che contiene al suo interno
//    vari membri (metodi e variabili) di utilità generale. Uno di questi
//    è out, che rappresenta lo "standard output" (di default, la console).

// 4. out è un oggetto di tipo PrintStream, che contiene a sua volta
//    vari metodi per stampare a video. Uno di questi è println(),
//    che stampa a video il testo passato come argomento e va a capo
//    automaticamente alla fine della stampa.

//    Molti IDE offrono lo shortcut "sysout",
//    che espande automaticamente in System.out.println() e non nelle altre,
//    che richiedono live template personalizzati.

//    IntelliJ IDEA offre anche il live template postfix ".sout" per stampare
//    un'espressione: è una funzione dell'IDE, non sintassi del linguaggio Java.

//    Ci sono poi anche print(), che stampa senza andare a capo,
//    e printf(), che consente di formattare la stringa di output
//    in modo più sofisticato (come in C/C++).

//    Java non usa #include. Il package java.lang è importato implicitamente;
//    le classi di altri package, come java.util.Scanner, richiedono invece un
//    import oppure il nome completamente qualificato.

//    Parleremo più avanti del perché sono separati da un punto (.), per ora
//    basti sapere che l'output di base in Java si fa così!
