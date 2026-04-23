package M09_OOP.L13_PolimorfismoDinamico;

/**
 * Cane è un Animale
 * Override del metodo parla()
 */
public class Cane extends Animale {

    @Override
    public void parla() {
        System.out.println("Il cane abbaia: Bau Bau!");
    }
}
