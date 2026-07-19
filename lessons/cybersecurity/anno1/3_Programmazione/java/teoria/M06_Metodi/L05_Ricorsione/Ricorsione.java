package M06_Metodi.L05_Ricorsione;

public class Ricorsione {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(n + "! = " + fattoriale(n));
        System.out.println("Somma da 1 a " + n + " = " + sommaDaUno(n));
    }

    static long fattoriale(int n) {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("n deve essere compreso tra 0 e 20");
        }
        if (n == 0) {
            return 1; // caso base: 0! = 1
        }
        return n * fattoriale(n - 1); // passo ricorsivo
    }

    static int sommaDaUno(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n non può essere negativo");
        }
        if (n == 0) {
            return 0;
        }
        return n + sommaDaUno(n - 1);
    }

    // Ogni chiamata ricorsiva occupa un frame nello stack. Servono sempre un
    // caso base raggiungibile e un avanzamento verso di esso; altrimenti si
    // ottiene StackOverflowError. Per input molto grandi è spesso preferibile
    // una soluzione iterativa.
}
