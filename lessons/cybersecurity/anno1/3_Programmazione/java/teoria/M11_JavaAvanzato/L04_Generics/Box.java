package M11_JavaAvanzato.L04_Generics;

// Agisce come un container generico che può contenere un oggetto di qualsiasi tipo T.
public class Box<T> {

    T item; // L'elemento di tipo generico T

    // Servirà un metodo per settare il tipo dell'elemento nella scatola:
    public void setItem(T item) {
        this.item = item;
    }

    // E anche un getter per recuperare l'elemento dalla scatola:
    public T getItem() {
        return this.item;
    }
}

// E' reusable, type-safe e flessibile!