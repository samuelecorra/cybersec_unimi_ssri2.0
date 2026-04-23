package M09_OOP.L13_PolimorfismoDinamico;

public class Gatto extends Animale {

    @Override
    public void parla() {
        System.out.println("Il gatto miagola: Miao!");
    }
}
