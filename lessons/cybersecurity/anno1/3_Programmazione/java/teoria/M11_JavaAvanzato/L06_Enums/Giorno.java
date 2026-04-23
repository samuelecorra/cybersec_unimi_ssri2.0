package M11_JavaAvanzato.L06_Enums;

public enum Giorno {

    // Convenzionalmente le varie costanti sono scritte in MAIUSCOLO
    LUNEDI(1), MARTEDI(2), MERCOLEDI(3), GIOVEDI(4), VENERDI(5), SABATO(6), DOMENICA(7);

    // Però non possiamo lasciarle così come sono, bisogna aggiungere un costruttore apposito:
    private final int numeroGiorno; // attributo per memorizzare il numero del giorno

    Giorno(int numeroGiorno) {
        this.numeroGiorno = numeroGiorno;
    }

    public int getNumeroGiorno() {
        return numeroGiorno;
    }

}
