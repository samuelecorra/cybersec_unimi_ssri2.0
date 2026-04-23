package M09_OOP.L13_PolimorfismoDinamico;

/*
 * ============================================================
 * SUPERCLASSE Animale
 * ============================================================
 * Contiene un metodo parla(), che sarà sovrascritto
 * dalle sottoclassi (Dog, Cat, Mucca).
 *
 * Il polimorfismo dinamico MOSTRA proprio questo:
 * se ho Animale a = new Cane(), viene chiamata la versione
 * di parla() definita in Cane.
 */
public class Animale {

    public void parla() {
        System.out.println("L'animale emette un verso generico.");
    }
}
