public class OttoRegineSimpl {
	private /*@ spec_public @*/ boolean[][] board;

	public OttoRegineSimpl() {
		board = new boolean[8][8];
	}

	//gli indici i e j sono indici validi della scacchiera
	//@ requires i >= 0 && i < 8 && j >= 0 && j < 8;
	//la riga identificata da i non contiene nessuna regina
	//@ requires (\forall int k; k >= 0 && k < 8; !board[i][k]);
	//la colonna identificata da j non contiene nessuna regina
	//@ requires (\forall int k; k >= 0 && k < 8; !board[k][j]);
	//il numero di regine sulla scacchiera e' minore od uguale ad 8
	//@  ensures (\sum int h; h >= 0 && h < 8; (\num_of int k; k >= 0 && k < 8; board[h][k])) <= 8;
	public void place(int i, int j) {
		board[i][j] = true;
	}

	public boolean isSolved() {
		int numQueens = 0;
		boolean queenFound;
		for(int i = 0; i < 8; i++) {
			queenFound = false;
			for(int j = 0; j < 8; j++) {
				if(board[i][j]) {
					if(queenFound) {
						return false;
					}
					else {
						queenFound = true;
						numQueens++;
					}
				}
			}
		}
		if(numQueens < 8) {
			return false;
		}
		else {
			for(int j = 0; j < 8; j++) {
				queenFound = false;
				for(int i = 0; i < 8; i++) {
					if(board[i][j]) {
						if(queenFound) {
							return false;
						}
						else {
							queenFound = true;
						}
					}
				}
			}
			return true;
		}
	}

	public boolean getCell(int i, int j) {
		return board[i][j];
	}

	public static void main(String[] args) {
		OttoRegineSimpl or = new OttoRegineSimpl();
		//or.place(8, 0);//violato invariante su indici corretti
		//or.place(0, -1);//violato invariante su indici corretti
		
		/*or.place(0, 0);
		or.place(0, 1);//violato invariante su riga vuota*/
		
		/*or.place(4, 5);
		or.place(7, 5);//violato invariante su colonna vuota*/

		//posizionamento corretto
		or.place(0, 0);
		or.place(1, 2);
		or.place(2, 5);
		or.place(3, 6);
		or.place(4, 3);
		or.place(5, 4);
		or.place(6, 7);
		or.place(7, 1);
	}

}
