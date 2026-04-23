package M06_Metodi.L02_Overloading;

public class Overloading {

    // L'overloading dei metodi in Java
    // L'overloading dei metodi è una caratteristica che consente di definire
    // più metodi con lo stesso nome all'interno della stessa classe,
    // purché abbiano firme diverse (diverso numero o tipo di parametri).

    // Metodo somma per due interi
    static int somma(int a, int b) {
        return a + b;
    }
    // Metodo somma per tre interi
    static int somma(int a, int b, int c) {
        return a + b + c;
    }
    // Metodo somma per due numeri in virgola mobile
    static double somma(double a, double b) {
        return a + b;
    }

    // Metodo generico per preparare la pizza:
    static String preparaPizza(String impasto) {
        return "Pizza con impasto: " + impasto;
    }

    // Metodo overloaded per preparare la pizza con condimenti:
    static String preparaPizza(String impasto, String condimenti) {
        return "Pizza con impasto: " + impasto + " e condimenti: " + condimenti;
    }

    // Altro overloaded per fare anche il topping:
    static String preparaPizza(String impasto, String condimenti, String topping) {
        return "Pizza con impasto: " + impasto + ", condimenti: " + condimenti + " e topping: " + topping;
    }

    static void main(String[] args) {

        System.out.println("Somma di due interi: " + somma(5, 10));
        System.out.println("Somma di tre interi: " + somma(5, 10, 15));
        System.out.println("Somma di due numeri in virgola mobile: " + somma(5.5, 10.2));

        System.out.println(preparaPizza("Classico"));
        System.out.println(preparaPizza("Integrale", "Mozzarella, Pomodoro, Funghi"));
        System.out.println(preparaPizza("Senza Glutine", "Mozzarella, Pomodoro", "Basilico Fresco"));
    }

}
