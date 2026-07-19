package M02_Matematica.L04_OperatoriBitwise;

public class OperatoriBitwise {
    public static void main(String[] args) {
        int a = 0b1100; // 12
        int b = 0b1010; // 10

        System.out.println("a & b = " + (a & b)); // 1000₂ = 8
        System.out.println("a | b = " + (a | b)); // 1110₂ = 14
        System.out.println("a ^ b = " + (a ^ b)); // 0110₂ = 6
        System.out.println("~a = " + ~a);          // complemento di tutti i 32 bit

        int valore = 5; // 0101₂
        System.out.println("5 << 1 = " + (valore << 1));   // 10
        System.out.println("5 >> 1 = " + (valore >> 1));   // 2

        int negativo = -8;
        System.out.println("-8 >> 1 = " + (negativo >> 1));  // conserva il segno: -4
        System.out.println("-8 >>> 1 = " + (negativo >>> 1)); // inserisce zeri a sinistra

        // Maschere di bit: ogni bit rappresenta un permesso indipendente.
        int lettura = 1 << 0;   // 001
        int scrittura = 1 << 1; // 010
        int esecuzione = 1 << 2; // 100

        int permessi = lettura | scrittura;
        boolean puoLeggere = (permessi & lettura) != 0;
        boolean puoEseguire = (permessi & esecuzione) != 0;
        System.out.println("Lettura consentita: " + puoLeggere);
        System.out.println("Esecuzione consentita: " + puoEseguire);

        permessi |= esecuzione;  // abilita il bit
        permessi &= ~scrittura;  // disabilita il bit
        permessi ^= lettura;     // inverte il bit
        System.out.println("Maschera finale: " + Integer.toBinaryString(permessi));

        // & e | sono ammessi anche tra boolean, ma valutano entrambi gli operandi;
        // && e || sono invece operatori logici short-circuit.
    }
}
