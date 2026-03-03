public class Sudoku {
	private /*@ spec_public @*/int board[][];
	
	public Sudoku() {
		board = new int[4][4];
	}

	//tutti gli elementi sulla riga r sono diversi da value
	//@ requires (\forall int j; j >= 0 && j <= 3; board[r][j] != value);
	//il numero di celle vuote e' minore od uguale a 16
	//@ ensures (\sum int i; i >= 0 && i <= 3; (\num_of int j; j >= 0 && j <= 3; board[i][j] == 0)) <= 16;
	//il metodo ha ritornato true se e solo se la decisione della regola if contenuta nel metodo valuta a true
	//@ ensures \result <==> (r >= 0 && r <= 3 && c >= 0 && c <= 3 && value >= 1 && value <= 4);
	public boolean add(int r, int c, int value) {
		if(r >= 0 && r <= 3 && c >= 0 && c <= 3 && value >= 1 && value <= 4) {
			board[r][c] = value;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isMaybeSolved() {
		int sumRow, sumCol;
		int[] sumSquare = new int[4];
		for(int i = 0; i < 4; i++) {
			sumRow = 0;
			sumCol = 0;
			for(int j = 0; j < 4; j++) {
				sumRow += board[i][j];
				sumCol += board[j][i];
				if(i < 2) {
					if(j < 2) {
						sumSquare[0] += board[i][j];
					}
					else {
						sumSquare[1] += board[i][j];
					}
				}
				else {
					if(j < 2) {
						sumSquare[2] += board[i][j];
					}
					else {
						sumSquare[3] += board[i][j];
					}
				}
			}
			if(sumRow != 10 || sumCol != 10) {
				return false;
			}
		}
		for(int j = 0; j < 4; j++) {
			if(sumSquare[j] != 10) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.add(0, 0, 1);
		//s.add(0, 1, 1);//viola la precondizione che richiede che tutti gli elementi sulla riga r siano diversi da value
		s.add(0, 1, 2);
		s.add(0, 2, 3);
		s.add(0, 3, 4);
		s.add(1, 0, 3);
		s.add(1, 1, 4);
		s.add(1, 2, 1);
		s.add(1, 3, 2);
		s.add(2, 0, 2);
		s.add(2, 1, 3);
		s.add(2, 2, 4);
		s.add(2, 3, 1);
		s.add(3, 0, 4);
		s.add(3, 1, 1);
		s.add(3, 2, 2);
		s.add(3, 3, 3);
	}
}