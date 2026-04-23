package M09_OOP.L13_PolimorfismoDinamico;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * TEST DEL POLIMORFISMO DINAMICO
 * ============================================================
 * Qui avviene la magia:
 *
 * - Creo variabili di tipo Animale (tipo statico)
 * - Ma ci inserisco dentro oggetti di tipo Cane, Gatto, Mucca (tipo dinamico)
 *
 * Quando chiamo a.parla(), la JVM guarda IL TIPO DINAMICO dell’oggetto
 * e invoca il metodo OVERRIDE corretto.
 *
 * Questo è il cuore del polimorfismo dinamico.
 */
public class TestPolimorfismoDinamico {

    static void main(String[] args) {

        // Tipo STATICO: Animale
        // Tipo DINAMICO: Cane
        Animale a1 = new Cane();

        // Tipo STATICO: Animale
        // Tipo DINAMICO: Gatto
        Animale a2 = new Gatto();

        // Tipo STATICO: Animale
        // Tipo DINAMICO: Mucca
        Animale a3 = new Mucca();

        System.out.println("=== ESEMPIO SINGOLO ===");
        a1.parla();  // Cane.parla()
        a2.parla();  // Gatto.parla()
        a3.parla();  // Mucca.parla()


        System.out.println("\n=== ESEMPIO CON LISTA POLIMORFICA ===");

        List<Animale> fattoria = new ArrayList<>();
        fattoria.add(new Cane());
        fattoria.add(new Gatto());
        fattoria.add(new Mucca());

        for (Animale a : fattoria) {
            /*
             * Anche se il TIPO DELLA VARIABILE è Animale,
             * il metodo invocato è QUELLO DEL TIPO DELL’OGGETTO.
             *
             * Questo perché la JVM fa "dynamic dispatch".
             */
            a.parla();
        }
    }
}
