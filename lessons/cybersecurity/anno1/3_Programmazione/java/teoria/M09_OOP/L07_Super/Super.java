package M09_OOP.L07_Super;

public class Super {

    // Già accennata in L06_Inheritance

    // La parola chiave super in Java viene utilizzata per fare riferimento alla superclasse immediata di una classe.
    // Viene comunemente usata in due contesti principali:

    // 1. Chiamare il costruttore della superclasse:
    //    Quando si crea un costruttore in una sottoclasse, è possibile utilizzare super() per chiamare il costruttore della superclasse.
    //    Questo è utile per inizializzare gli attributi ereditati dalla superclasse.
    // 2. Accedere ai membri della superclasse:
    //    È possibile utilizzare super.nomeAttributo o super.nomeMetodo() per accedere
    //    agli attributi o ai metodi della superclasse che sono stati sovrascritti nella sottoclasse.

    // Esempio:
    static void main() {

        // Abbiamo una superclasse Animale e una sottoclasse Cane.
        Cane c = new Cane("Fido", 3);

        System.out.println("Specie: " + c.specie);
        System.out.println("Nome: " + c.nome);
        System.out.println("Età: " + c.eta);
        System.out.println("È vivo? " + c.eVivo);

        c.verso();           // metodo sovrascritto
        c.versoOriginale();  // metodo della superclasse


    }

}
