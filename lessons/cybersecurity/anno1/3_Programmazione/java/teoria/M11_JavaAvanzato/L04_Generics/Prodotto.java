package M11_JavaAvanzato.L04_Generics;

public class Prodotto<T, U> {

    // Attributi
    private T nome;
    private U prezzo;

    // Costruttore
    public Prodotto(T nome, U prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    // Getters
    public T getNome() {
        return nome;
    }

    public U getPrezzo() {
        return prezzo;
    }

}