package M09_OOP.L10_Abstract;

public class Abstract {
    static void main(String[] args) {

        // Non posso fare: Figura f = new Figura("boh");  // ERRORE: Figura è astratta

        Figura c = new Circonferenza(3.0);   // r = 3
        Figura t = new Trapezio(10.0, 6.0, 5.0, 4.0);
        Figura s = new Semicerchio(2.0);     // r = 2

        // Grazie al POLIMORFISMO posso trattarle tutte come "Figura"
        Figura[] figure = { c, t, s };

        for (Figura f : figure) {
            System.out.println(f);
        }
    }
}
