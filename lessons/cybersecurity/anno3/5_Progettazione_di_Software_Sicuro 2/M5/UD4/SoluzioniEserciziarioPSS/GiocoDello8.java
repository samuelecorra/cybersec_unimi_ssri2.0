public class GiocoDello8 {
	private /*@ spec_public @*/ int[][] board;

	//per ogni tessera dell'intervallo [1,8] c'e' una cella che la contiene;
	//inoltre c'e' una cella vuota (nota che la cella vuota e' identificata con 0)
	/*@ ensures (\forall int k; k >= 0 && k <= 8;
	  @           (\exists int i; i >= 0 && i <= 2; (\exists int j; j >= 0 && j <= 2; board[i][j] == k)));
	  @*/
	public GiocoDello8() {
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

	//gli indici i, j, h e k sono indici validi
	//@ requires i >= 0 && i <= 2 && j >= 0 && j <= 2;
	//la cella identificata da (i, j) identifica una tessera
	//@ requires board[i][j] != 0;
	//la cella identificata da (h, k) e' la cella vuota
	//@ requires board[h][k] == 0;
	//@ requires h >= 0 && h <= 2 && k >= 0 && k <= 2;
	//le celle identificate dagli indici (i, j) e (h, k) sono contigue
	//@ requires (Math.abs(i - h) == 1 && (j == k)) || (Math.abs(j - k) == 1 && (i == h));
	public void move(int i, int j, int h, int k) {
		board[h][k] = board[i][j];
		board[i][j] = 0;
	}

	public boolean isSolved() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if((i*3 + j + 1)%9 != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public int get(int i, int j) {
		return board[i][j];
	}

	/**
	 * Versione alternativa
	 */
	public boolean isSolved_v2() {
		int k = 1;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i==2 && j==2) {
					if(board[i][j]!=0) {
						return false;
					}
				}
				else if(board[i][j]!=k) {
					return false;
				}
				k++;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		//GiocoDello8 q = new GiocoDello8();
		//q.move(3, 0, 0, 1);//viola le precondizione sugli indici validi
		//q.move(1, 2, 1, 2);//viola le precondizione che richiede che (i, j) identifichino una tessera
		//q.move(2, 0, 2, 1);//viola le precondizione che richiede che (h, k) identifichino la cella vuota
		//q.move(0, 0, 1, 2);//viola le precondizione che richiede che (h, k) identifichino la cella vuota
	}
}
