package M06_Metodi.L04_PassaggioPerValore;

public class PassaggioPerValore {
    public static void main(String[] args) {
        int numero = 10;
        provaACambiare(numero);
        System.out.println("Dopo il metodo, numero = " + numero); // 10

        StringBuilder testo = new StringBuilder("Java");
        modificaOggetto(testo);
        System.out.println("Dopo la modifica: " + testo); // Java è pass-by-value

        riassegnaParametro(testo);
        System.out.println("Dopo la riassegnazione locale: " + testo); // invariato

        // Java è sempre pass-by-value. Per un primitivo viene copiata la sua
        // rappresentazione; per un riferimento viene copiato il riferimento.
        // Le due copie possono raggiungere lo stesso oggetto, ma riassegnare il
        // parametro non cambia la variabile del chiamante.
    }

    static void provaACambiare(int valore) {
        valore = 99; // modifica soltanto la copia locale
    }

    static void modificaOggetto(StringBuilder valore) {
        valore.append(" è pass-by-value"); // muta l'oggetto condiviso
    }

    static void riassegnaParametro(StringBuilder valore) {
        valore = new StringBuilder("Nuovo oggetto"); // cambia soltanto la copia del riferimento
        System.out.println("Dentro il metodo: " + valore);
    }
}
