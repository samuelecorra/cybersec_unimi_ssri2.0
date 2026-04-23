package M09_OOP.L07_Super;

public class Animale {

    boolean eVivo;
    String specie;

    Animale(String specie) {
        this.eVivo = true;
        this.specie = specie;
    }

    void verso() {
        System.out.println("L'animale emette un verso generico.");
    }
}