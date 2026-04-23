package M09_OOP.L03_CostruttoriOverloaded;

public class Utente {

    String username;
    String email;
    int eta;

    // Primo costruttore incompleto
    Utente(String username){
        this.username = username;
        this.email = "non specificata";
        this.eta = 0;
    }

    Utente(String username, String email){
        this.username = username;
        this.email = email;
        this.eta = 0;
    }

    Utente(String username, String email, int eta){
        this.username = username;
        this.email = email;
        this.eta = eta;
    }

    Utente(){
        this.username = "guest";
        this.email = "non specificata";
        this.eta = 0;
    }
}
