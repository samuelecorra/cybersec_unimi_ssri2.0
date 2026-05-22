public class Rubik2D {

    /*  Esercizio 0: Singola faccia del cubo di Rubik

        Uno dei giochi più famosi al mondo, il cubo di Rubik, si presta bene a essere trasposto nel mondo del coding.
        Per quanto sia un problema computazionale molto arzigogolato, possiamo limitarci a considerare una singola faccia anziché 6.

        E di questa faccia, per semplicità/nostre esigenze, hardcodiamo la initial_config.

        1 | 0 | 1
        2 | 1 | 2
        0 | 2 | 0

        -----------------------

        Detto ciò, bisogna capire la dinamica di un gioco ridotto a un mondo bidimensionale:
        cosa intendiamo per "girare una colonna"? Semplicemente, spostare verso l'alto (in modo ciclico) i valori di una colonna.

        Ovvero, se prendiamo ad esempio la prima colonna (idCol = 0), dopo averla girata, avremo:

        2 | 0 | 1
        0 | 1 | 2
        1 | 2 | 0

        Empiricamente notiamo che:

        - il valore in alto finisce in basso; quello centrale finisce in alto; quello in basso finisce al centro;
        - le altre due colonne restano invariate.

        -----------------------

        In questo e nei prossimi esercizi, il costruttore ed i metodi della classe sono già forniti docente-side, e noi studenti abbiamo
        invece l'onere di commentarli con annotazioni di specifica JML, in modo da rendere espliciti i loro comportamenti e le loro pre/post condizioni/ invarianti. */

    // Ovviamente, in questo come in tutti gli altri esercizi, prevalgono gli array bidimensionali aka matrici, come strutture dati idonee:
    private /*@ spec_public @*/ int[][] faccia; // si usa spec_public su ogni variabile che vorremo poi "prelevare" e ispezionare nelle annotazioni di specifica dei metodi, 
    // altrimenti non potremo accedere a faccia[i][j] nelle annotazioni di specifica dei metodi. Così evitiamo di rompere l'incapsulamento che Java ci "impone" per buona prassi.


    // Il costruttore inizializza essenzialmente la matrice-faccia. Per esso, ci viene richiesto di scrivere la seugente post-condizione:
    // "Ogni cella deve avere valore compreso tra 0 e 2"

    // Vi spiego la traduzione in linguaggio naturale delle varie annotazioni:

    // Ci assicuriamo che per ogni indice i compreso tra 0 e 2 *punto e virgola* selezioniamo anche ogni indice j compreso tra 0 e 2 *punto e virgola* e per ognuno di questi,
    // accediamo alla i-j-esima cella. Essendo due \forall annidati, sono esattamente l'equivalente di due for annidati che scandiscono l'array bidimensionale.
    // E' importantissimo porre la parentesi prima del secondo \forall e che si chiude solo dopo il check del range dell'i-j-esimo elemento, per non avere errori di scoping.

    /*@ ensures (\forall int i; i >= 0 && i < 3;
      @     (\forall int j; j >= 0 && j < 3; faccia[i][j] >= 0 && faccia[i][j] < 3));
      @*/
    public Rubik2D() {
        faccia = new int[3][3];
        faccia[0][0] = 1;
        faccia[0][2] = 1;
        faccia[1][1] = 1;
        faccia[1][0] = 2;
        faccia[1][2] = 2;
        faccia[2][1] = 2;
    }

    // Per il metodo gira, invece, ci viene richiesto di scrivere le seguenti post-condizioni:
    // - "3 celle hanno valore 0"
    // - "la somma di tutti i valori delle celle è 9"
    // - "le celle che non appartengono alla colonna girata restano invariate"

    // Vi traduco in italiano la prima annotazione: "Voglio effettuare la sommatoria di tutte le celle che:
    // - hanno un indice i in range corretto, tra 0 e 2, e per ognuno di questi contiamo anche le occorrenze di indici j in range corretto, 
    // tra 0 e 2, che soddisfano la condizione che la i-j-esima cella sia uguale a 0. E voglio che questa sommatoria sia esattamente 3.
    
    // La seconda annotazione invece è simile, ma stavolta non siamo così pignoli da contare le occorrenze di celle specifiche, infatti è addirittura una
    // sommatoria "snellita", molto + generica. Sappiamo che se le celle non vengono manipolate in maniera errata, partendo da una sommatoria di 9 deve permanere invariata!

    // La terza annotazione invece contiene più diciture, ma snoccioliamola per bene:
    // Selezioniamo sempre prima tutti gli indici i in range corretto, per poi annidare una selezione IDENTICA di indici j, ma dopo il punto e virgola, aggiungiamo una condizione che controlla
    // esplicitamente che tutte le j selezionate NON siano uguali all'idCol che abbiam passato come parametro. Se tale disuguaglianza è soddisfatta, ciò dovrebbe implicare (e quindi poniamo la freccia ==>) 
    // che la i-j-esima cella resti invariata, ovvero uguale al suo valore precedente alla chiamata del metodo, che è quello che rappresenta \old(faccia[i][j]). Se invece j è uguale ad idCol, allora non ci interessa specificare nulla, perché sappiamo già che quella colonna verrà manipolata e quindi non possiamo pretendere che resti invariata. In questo modo, con questa annotazione, stiamo esplicitando il fatto che tutte le celle che non appartengono alla colonna girata restano invariate, mentre quelle della colonna girata possono essere manipolate senza violare la post-condizione. */

    //@ ensures (\sum int i; i >= 0 && i < 3; (\num_of int j; j >= 0 && j < 3; faccia[i][j] == 0)) == 3;
    //@ ensures (\sum int i; i >= 0 && i < 3; (\sum int j; j >= 0 && j < 3; faccia[i][j])) == 9;
    /*@ ensures (\forall int i; i >= 0 && i < 3;
      @     (\forall int j; j >= 0 && j < 3; j != idCol ==> faccia[i][j] == \old(faccia[i][j])));
      @*/
    public boolean gira(int idCol) {
        if (idCol >= 0 && idCol < 3) { // check di validità dell'indice di colonna selezionato, altrimenti skippiamo a return false, che è il comportamento di default in caso di input non valido.
            int primo = faccia[0][idCol]; // salviamo il valore in alto, che è quello che "ciclicamente" finirà in basso.
            for (int i = 0; i < 2; i++) { // questo ciclo verrà eseguito 2 volte...
                faccia[i][idCol] = faccia[i + 1][idCol]; // spostando il valore centrale in alto e quello in basso al centro.
            }
            faccia[2][idCol] = primo; // e infine spostiamo comodamente quello in alto in basso, senza bisogno di ulteriori variabili di appoggio.
            return true;
        }
        return false;
    }

    // Questo metodo è così semplice che ci viene richiesto di implementarlo noi!
    public boolean isSolved() {
        for (int i = 0; i < 3; i++) {
            int valore = faccia[i][0]; // Conserviamo il valore della prima cella di ogni riga, e controlliamo che sia uguale a quello delle altre due celle della stessa riga, altrimenti la faccia non è risolta.
            for (int j = 1; j < 3; j++) {
                if (faccia[i][j] != valore) {
                    return false;
                }
            }
        }
        return true;
    }
}