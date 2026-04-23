package M09_OOP.L19_Eccezioni;

/*
 * Piccola classe Calcolatrice per mostrare:
 * - Lancio di eccezioni con throw
 * - Uso di IllegalArgumentException
 */
public class Calcolatrice {

    public static int dividi(int a, int b) {
        if (b == 0) {
            // Lanciamo manualmente un'eccezione
            throw new IllegalArgumentException("Non puoi dividere per zero!");
        }
        return a / b;
    }

    public static int radiceQuadrata(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("La radice quadrata di numero negativo non è definita!");
        }
        return (int) Math.sqrt(n);
    }
}
