public class KnightTour {

	// La scacchiera 5x5 e' rappresentata come matrice di booleani: visited[i][j] vale true
	// se e solo se la cella (i, j) e' gia' stata visitata dal cavallo.
	// Anche qui usiamo spec_public per poter referenziare il campo nelle specifiche pubbliche
	// (postcondizione del costruttore, precondizioni di move) pur mantenendolo private.
	private /*@ spec_public @*/ boolean[][] visited;

	// (x, y) tiene traccia della posizione corrente del cavallo: serve a move() per verificare
	// che la mossa successiva parta proprio da li' e abbia forma di L.
	private /*@ spec_public @*/ int x, y;

	// Postcondizione del costruttore (richiesta dalla consegna):
	// - la cella (0, 0) deve essere marcata come visitata;
	// - tutte le altre 24 celle della scacchiera devono essere marcate come NON visitate.
	//
	// La prima parte e' un semplice ensures su visited[0][0].
	// Per la seconda parte usiamo \forall su tutti gli indici (i, j) della scacchiera, escludendo
	// la coppia (0, 0): se almeno uno fra i e j e' diverso da 0, allora visited[i][j] deve
	// essere false (cioe' !visited[i][j] e' true).
	// Aggiungiamo anche un ensures sul punto di partenza del cavallo, x == 0 && y == 0,
	// perche' il body inizializza esplicitamente quelle coordinate ed e' utile averlo nelle
	// specifiche per i metodi che le useranno come "stato iniziale".
	/*@ ensures visited[0][0];
	  @ ensures (\forall int i, j; i >= 0 && i < 5 && j >= 0 && j < 5 && (i != 0 || j != 0);
	  @           !visited[i][j]);
	  @ ensures x == 0 && y == 0;
	  @*/
	public KnightTour() {
		visited = new boolean[5][5];
		x = 0;
		y = 0;
		visited[x][y] = true;
	}

	// Precondizioni del metodo move (sposta il cavallo dalla posizione corrente (x, y) a (h, k)):
	//
	// 1) Indici validi: h e k devono appartenere all'intervallo [0, 4] perche' la scacchiera e' 5x5.
	//
	// 2) La cella di destinazione non deve essere gia' stata visitata: se (h, k) e' gia' true
	//    nella matrice visited, ripassarci sopra violerebbe le regole del Knight's tour.
	//
	// 3) La mossa deve essere una L corretta: a partire dalla posizione attuale (x, y), si deve
	//    spostare di 1 lungo un asse e di 2 lungo l'altro (in qualunque direzione, segno positivo
	//    o negativo). Tradotto in JML diventa l'unione di due casi simmetrici:
	//      a) Δx ∈ {-1, +1} e Δy ∈ {-2, +2}
	//      b) Δx ∈ {-2, +2} e Δy ∈ {-1, +1}
	//    Esprimiamo le differenze in modo discreto (senza Math.abs), restando nel frammento
	//    espressivo di JML, esattamente come abbiamo fatto per la contiguita' nel Gioco dell'8.
	/*@ requires h >= 0 && h < 5 && k >= 0 && k < 5;
	  @ requires !visited[h][k];
	  @ requires ((x - h == 1 || x - h == -1) && (y - k == 2 || y - k == -2)) ||
	  @          ((x - h == 2 || x - h == -2) && (y - k == 1 || y - k == -1));
	  @*/
	public void move(int h, int k) {
		// Le precondizioni JML hanno gia' assicurato che la mossa sia legittima, percio' qui dentro
		// (come da consegna) non eseguiamo alcun controllo runtime.
		visited[h][k] = true;
		x = h;
		y = k;
	}

	// isTourCompleted ritorna true sse tutte e 25 le celle della scacchiera risultano visitate.
	// Basta scorrere la matrice e, alla prima cella ancora false, restituire false.
	// Annotiamo il metodo come pure: non modifica lo stato, si limita a leggerlo, quindi puo'
	// essere referenziato anche da altre specifiche JML qualora servisse in futuro.
	public /*@ pure @*/ boolean isTourCompleted() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(!visited[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		KnightTour kt = new KnightTour();

		// Esempi di chiamate che VIOLANO le precondizioni (lasciate commentate):
		//kt.move(0, 1);//viola la precondizione sulla mossa corretta: da (0,0) a (0,1) c'e' un solo passo orizzontale, non e' una L

		//kt.move(5, 2);//viola la precondizione sugli indici validi: h = 5 esce dal range [0,4] della scacchiera

		//kt.move(1, 2);//mossa lecita: da (0,0) a (1,2) e' una L (Δx=1, Δy=2). Dopo questa, x=1, y=2.
		//kt.move(0, 0);//viola la precondizione sulla cella gia' visitata: (0,0) e' gia' true (marchiata dal costruttore)

		//tour completo: 24 mosse che, partendo da (0, 0) gia' visitata dal costruttore, coprono
		//le rimanenti 24 celle della scacchiera. I parametri sono quelli della tabella 2 della consegna.
		//La cella finale e' (1, 1), diversa da quella iniziale (0, 0): si tratta quindi di un open tour,
		//come richiesto dal problema.
		kt.move(2, 1);
		kt.move(4, 0);
		kt.move(3, 2);
		kt.move(4, 4);
		kt.move(2, 3);
		kt.move(0, 4);
		kt.move(1, 2);
		kt.move(2, 4);
		kt.move(4, 3);
		kt.move(3, 1);
		kt.move(1, 0);
		kt.move(0, 2);
		kt.move(1, 4);
		kt.move(3, 3);
		kt.move(4, 1);
		kt.move(2, 0);
		kt.move(0, 1);
		kt.move(1, 3);
		kt.move(3, 4);
		kt.move(4, 2);
		kt.move(3, 0);
		kt.move(2, 2);
		kt.move(0, 3);
		kt.move(1, 1);

		// Dopo le 24 mosse il cavallo ha visitato tutte le 25 celle: isTourCompleted() deve ritornare true.
		System.out.println("Tour completato? " + kt.isTourCompleted());
	}
}
