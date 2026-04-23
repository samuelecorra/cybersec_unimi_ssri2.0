package M09_OOP.L10_Abstract;

/*
 * Classe concreta che rappresenta una circonferenza (cerchio).
 * Estende Figura e quindi DEVE implementare i metodi area() e perimetro().
 */

public class Circonferenza extends Figura {

    private double raggio;

    public Circonferenza(double raggio) {
        // Passiamo il nome della figura al costruttore della superclasse (Figura)
        super("Circonferenza");
        this.raggio = raggio;
    }

    public double getRaggio() {
        return raggio;
    }

    public void setRaggio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public double area() {
        // Formula classica: A = π * r^2
        return Math.PI * raggio * raggio;
    }

    @Override
    public double perimetro() {
        // Perimetro del cerchio (lunghezza della circonferenza): P = 2 * π * r
        return 2 * Math.PI * raggio;
    }

    @Override
    public String toString() {
        return getNome() +
                " (r = " + format(raggio) + ") -> " +
                "area = " + format(area()) +
                ", perimetro = " + format(perimetro());
    }
}
