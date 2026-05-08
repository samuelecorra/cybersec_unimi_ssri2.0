public class GiocoDello8ConJML {

	// Il campo board rappresenta la griglia 3x3 del rompicapo.
	// Le tessere sono i valori interi in [1, 8] e la cella vuota e' marcata con 0.
	// Lo dichiariamo spec_public perche' dobbiamo poterlo referenziare nelle specifiche
	// pubbliche (postcondizione del costruttore e precondizioni del metodo move),
	// pur mantenendolo private per i client esterni alla classe.
	private /*@ spec_public @*/ int[][] board;

	// Postcondizione del costruttore (richiesta dalla consegna):
	// - per OGNI tessera numerata con un valore nell'intervallo [1, 8] deve esistere
	//   una cella della griglia che la contiene;
	// - inoltre deve esistere una cella vuota, ovvero una cella che contiene 0.
	//
	// Cerchiamo di leggerla in italiano: "per ogni n compreso tra 1 e 8, esistono indici
	// (i, j) validi (entrambi in [0, 2]) tali che board[i][j] == n". Combiniamo quindi
	// il quantificatore universale \forall con quello esistenziale \exists annidato.
	// Per la cella vuota basta un singolo \exists che cerchi un valore 0 nella griglia.
	//
	// N.B.: la postcondizione cosi' formulata non impone l'unicita' di ciascun valore
	// (in linea teorica una tessera potrebbe comparire piu' volte). Per il nostro costruttore
	// non e' un problema, perche' il body inizializza esplicitamente le 9 celle con valori
	// tutti distinti, ma e' bene esserne consci.
	/*@ ensures (\forall int n; n >= 1 && n <= 8;
	  @           (\exists int i, j; i >= 0 && i < 3 && j >= 0 && j < 3; board[i][j] == n));
	  @ ensures (\exists int i, j; i >= 0 && i < 3 && j >= 0 && j < 3; board[i][j] == 0);
	  @*/
	public GiocoDello8ConJML() {
		board = new int[3][3];
		board[0][0] = 2;
		board[0][1] = 3;
		board[0][2] = 6;
		board[1][0] = 1;
		board[1][1] = 5;
		board[1][2] = 0;
		board[2][0] = 4;
		board[2][1] = 7;
		board[2][2] = 8;
	}

	// Precondizioni del metodo move (sposta la tessera che si trova in (i, j) nella cella (h, k)):
	//
	// 1) Indici validi: i, j, h, k devono appartenere all'intervallo [0, 2], cioe' essere indici
	//    legittimi di una griglia 3x3. Senza questo controllo otterremmo un IndexOutOfBoundsException.
	//
	// 2) (i, j) identifica una tessera: la cella di partenza non puo' essere la cella vuota,
	//    quindi board[i][j] deve essere diverso da 0. Spostare "il vuoto" non avrebbe senso.
	//
	// 3) (h, k) identifica la cella vuota: la cella di destinazione DEVE valere 0; altrimenti
	//    sovrascriveremmo una tessera esistente, perdendone il valore.
	//
	// 4) (i, j) e (h, k) sono contigue: una sola coordinata puo' cambiare, e deve farlo di
	//    esattamente 1. Cio' significa mossa orizzontale (stessa riga, colonne adiacenti)
	//    oppure verticale (stessa colonna, righe adiacenti); le diagonali e i "salti" sono vietati.
	//    In pratica e' una distanza di Manhattan pari a 1, che esprimiamo in modo "discreto"
	//    senza usare Math.abs, per restare aderenti al puro frammento espressivo di JML.
	
	/*@ requires i >= 0 && i < 3 && j >= 0 && j < 3 &&
	  @          h >= 0 && h < 3 && k >= 0 && k < 3;
	  @ requires board[i][j] != 0;
	  @ requires board[h][k] == 0;
	  @ requires (i == h && (j == k + 1 || j == k - 1)) ||
	  @          (j == k && (i == h + 1 || i == h - 1));
	  @*/
	public void move(int i, int j, int h, int k) {
		// Le precondizioni JML hanno gia' garantito la validita' della mossa, quindi
		// qui dentro - come da consegna - non facciamo alcun controllo runtime.
		board[h][k] = board[i][j];
		board[i][j] = 0;
	}

	// isSolved verifica se la griglia si trova nella configurazione finale
	// 1, 2, 3 / 4, 5, 6 / 7, 8, 0 (vedi tabella 1 della consegna).
	// Il trucco sta nella formula (i*3 + j + 1) % 9, che mappa:
	//   (0,0) -> 1, (0,1) -> 2, ..., (2,1) -> 8,
	// e infine (2,2) -> 9 % 9 = 0, ovvero la cella vuota nell'angolo in basso a destra.
	// Se anche solo una cella non rispetta la formula, restituiamo subito false.
	//
	// Annotiamo il metodo come pure perche' e' un "interrogativo": non modifica lo stato
	// dell'oggetto, si limita a leggerlo. In questo modo potrebbe essere usato anche
	// all'interno di altre specifiche JML, qualora servisse.
	public /*@ pure @*/ boolean isSolved() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if((i*3 + j + 1)%9 != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		GiocoDello8ConJML q = new GiocoDello8ConJML();

		// Esempi di chiamate che VIOLANO le precondizioni (lasciate commentate):
		//q.move(3, 0, 0, 1);//viola la precondizione sugli indici validi (i = 3 esce dal range [0,2])
		//q.move(1, 2, 1, 2);//viola la precondizione che (i, j) identifichi una tessera: nella configurazione iniziale board[1][2] e' la cella vuota (vale 0)
		//q.move(2, 0, 2, 1);//viola la precondizione che (h, k) sia la cella vuota: board[2][1] vale 7, non 0
		//q.move(0, 0, 1, 2);//N.B.: nel codice originale della docente questa riga era etichettata come violazione di "(h, k) cella vuota",
		                    //ma in realta' (1, 2) E' la cella vuota; cio' che viola e' la contiguita',
		                    //perche' (0, 0) e (1, 2) hanno distanza di Manhattan = 3 (non si tocca con un solo passo).

		// Sequenza delle 7 mosse che, partendo dalla configurazione iniziale, portano alla
		// configurazione finale. I parametri sono esattamente quelli riportati in tabella 3 della consegna.
		// Tutte queste chiamate soddisfano sia gli indici validi, sia (i, j) tessera, sia (h, k) vuota,
		// sia la contiguita': sono mosse lecite del rompicapo.
		q.move(0, 2, 1, 2); // 6 scende: 2 3 6 / 1 5 0 / 4 7 8  ->  2 3 0 / 1 5 6 / 4 7 8
		q.move(0, 1, 0, 2); // 3 va a destra:                  ->  2 0 3 / 1 5 6 / 4 7 8
		q.move(0, 0, 0, 1); // 2 va a destra:                  ->  0 2 3 / 1 5 6 / 4 7 8
		q.move(1, 0, 0, 0); // 1 sale:                         ->  1 2 3 / 0 5 6 / 4 7 8
		q.move(2, 0, 1, 0); // 4 sale:                         ->  1 2 3 / 4 5 6 / 0 7 8
		q.move(2, 1, 2, 0); // 7 va a sinistra:                ->  1 2 3 / 4 5 6 / 7 0 8
		q.move(2, 2, 2, 1); // 8 va a sinistra:                ->  1 2 3 / 4 5 6 / 7 8 0

		// Dopo le 7 mosse la griglia coincide con la tabella 1: isSolved() deve ritornare true.
		System.out.println("Rompicapo risolto? " + q.isSolved());
	}
}
