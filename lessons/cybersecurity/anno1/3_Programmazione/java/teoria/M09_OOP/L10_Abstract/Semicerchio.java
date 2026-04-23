package M09_OOP.L10_Abstract;

/*
 * Semicerchio (metà di un cerchio).
 *
 * Qui l'idea è collegare la geometria agli INTEGRALI:
 *
 * L'area di un cerchio di raggio r si può ottenere come:
 *
 *   A_cerchio = ∫_{-r}^{r} 2 * sqrt(r^2 - x^2) dx = π * r^2
 *
 * Da questo integrale, ricaviamo che l'area di un SEMICERCHIO è:
 *
 *   A_semicerchio = (π * r^2) / 2
 *
 * Nel codice NON implementiamo l'integrale numericamente (sarebbe più lungo),
 * ma usiamo direttamente la formula chiusa che SI OTTIENE grazie al calcolo
 * integrale. I commenti ti ricordano la “origine analitica” della formula.
 */
public class Semicerchio extends Figura {

    private double raggio;

    public Semicerchio(double raggio) {
        super("Semicerchio");
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
        // A_semicerchio = (π * r^2) / 2
        return Math.PI * raggio * raggio / 2.0;
    }

    @Override
    public double perimetro() {
        // Perimetro del semicerchio:
        // metà circonferenza + diametro = (π * r) + 2r
        return Math.PI * raggio + 2 * raggio;
    }

    @Override
    public String toString() {
        return getNome() +
                " (r = " + format(raggio) + ") -> " +
                "area = " + format(area()) +
                ", perimetro = " + format(perimetro());
    }
}
