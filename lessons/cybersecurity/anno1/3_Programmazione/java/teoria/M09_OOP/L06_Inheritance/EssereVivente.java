package M09_OOP.L06_Inheritance;

public class EssereVivente {

    // Rimaniamo sul generico...
    String soprannome;
    boolean eVivo;

    // Facciamo in modo che ogni essere vivente abbia un soprannome e sia vivo di default
    EssereVivente(String soprannome) {
        this.soprannome = soprannome;
        this.eVivo = true;
    }

}
