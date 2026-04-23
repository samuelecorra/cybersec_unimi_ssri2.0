package M09_OOP.L10_Abstract;

/*
 * Trapezio isoscele (per semplicità), con:
 * - baseMaggiore
 * - baseMinore
 * - latoObliquo (i due lati obliqui hanno la stessa lunghezza)
 * - altezza
 *
 * area = ( (B + b) * h ) / 2
 * perimetro = B + b + 2 * latoObliquo
 */

public class Trapezio extends Figura {

    private double baseMaggiore;
    private double baseMinore;
    private double latoObliquo;
    private double altezza;

    public Trapezio(double baseMaggiore, double baseMinore,
                    double latoObliquo, double altezza) {
        super("Trapezio isoscele");
        this.baseMaggiore = baseMaggiore;
        this.baseMinore = baseMinore;
        this.latoObliquo = latoObliquo;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return (baseMaggiore + baseMinore) * altezza / 2.0;
    }

    @Override
    public double perimetro() {
        return baseMaggiore + baseMinore + 2 * latoObliquo;
    }

    @Override
    public String toString() {
        return getNome() +
                " (B = " + format(baseMaggiore) +
                ", b = " + format(baseMinore) +
                ", lato = " + format(latoObliquo) +
                ", h = " + format(altezza) + ") -> " +
                "area = " + format(area()) +
                ", perimetro = " + format(perimetro());
    }
}
