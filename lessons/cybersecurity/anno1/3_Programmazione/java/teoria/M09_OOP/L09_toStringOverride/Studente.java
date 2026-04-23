package M09_OOP.L09_toStringOverride;

public class Studente {

    String nome;
    String cognome;
    int matricola;
    int annoCorso;
    double mediaVoti;
    boolean haPagatoTasse;
    boolean abbonatoBarUniversitario;

    public Studente(String nome, String cognome, int matricola, int annoCorso,
                    double mediaVoti, boolean haPagatoTasse, boolean abbonatoBarUniversitario) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.annoCorso = annoCorso;
        this.mediaVoti = mediaVoti;
        this.haPagatoTasse = haPagatoTasse;
        this.abbonatoBarUniversitario = abbonatoBarUniversitario;
    }

    @Override
    public String toString() {
        return "Studente {\n" +
                " |-- nome = '" + this.nome + "'\n" +
                " |-- cognome = '" + this.cognome + "'\n" +
                " |-- matricola = '" + this.matricola + "'\n" +
                " |-- annoCorso = '" + this.annoCorso + "'\n" +
                " |-- mediaVoti = '" + this.mediaVoti + "'\n" +
                " |-- haPagatoTasse = '" + this.haPagatoTasse + "'\n" +
                " |-- abbonatoBarUniversitario = '" + this.abbonatoBarUniversitario + "'\n" +
                '}';
    }
}
