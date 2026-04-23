package M09_OOP.L10_Abstract;

/*
 * Classe astratta che rappresenta una generica figura geometrica piana.
 *
 * - "abstract" significa che NON possiamo creare oggetti di tipo Figura
 *   direttamente con "new Figura(...)"
 * - Usiamo Figura come SUPERCLASSE da cui altre figure specifiche
 *   (circonferenza, trapezio, ecc.) erediteranno.
 */

public abstract class Figura {

    // Nome descrittivo della figura (es. "Circonferenza", "Trapezio", ecc.)
    private String nome;

    public Figura(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    /*
     * Metodo ASTRATTO: non ha corpo qui.
     * Ogni sottoclasse È OBBLIGATA a fornire la propria implementazione.
     */
    public abstract double area();

    /*
     * Anche il perimetro è definito come “contratto” astratto.
     */
    public abstract double perimetro();

    protected String format(double value) {
        return String.format("%.2f", value);
    }

    @Override
    public String toString() {
        return "Figura: " + nome +
                ", area = " + format(area()) +
                ", perimetro = " + format(perimetro());
    }
}
