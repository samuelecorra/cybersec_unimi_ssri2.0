public class TicTacToe {
	private /*@ spec_public @*/ int[][] board;

	//tutte le celle della griglia valgono 0
	//@ ensures (\forall int i; i >= 0 && i < 3; (\forall int j; j >= 0 && j < 3; board[i][j] == 0));
	public TicTacToe() {
		board = new int[3][3];
	}

	//la cella identificata da (i, j) e' vuota
	//@ requires board[i][j] == 0;
	//il valore sign indica correttamente uno dei due segni
	//@ requires sign == 1 || sign == 2;
	//la cella identificata da (i, j) viene aggiornata a sign
	//@ ensures board[i][j] == sign;
	//la differenza in valore assoluto tra il numero di celle segnate con la X e il numero di cella segnate con la O e' al massimo uno
	/*@  ensures Math.abs((\sum int h; h >= 0 && h < 3; (\num_of int k; k >= 0 && k < 3; board[h][k] == 1))	- 
	  @					  (\sum int h; h >= 0 && h < 3; (\num_of int k; k >= 0 && k < 3; board[h][k] == 2))) <= 1;
	  @*/
	public void set(int i, int j, int sign) {
		//System.out.println((i >= 0) + " " + (j >=0) + " " +  (i < 3) + " " +(j < 3));
		if(i >= 0 && j >=0 && i < 3 && j < 3) {
			if(sign == 1) {
				board[i][j] = 1;
			}
			else {
				board[i][j] = 2;
			}
		}
	}

	public boolean won(int sign) {
		for(int i = 0; i < 3; i++) {
			if(board[i][0]==sign && board[i][1]==sign && board[i][2]==sign) {
				return true;
			}
		}
		for(int j = 0; j < 3; j++) {
			if(board[0][j]==sign && board[1][j]==sign && board[2][j]==sign) {
				return true;
			}
		}
		if(board[0][0]==sign && board[1][1]==sign && board[2][2]==sign) {
			return true;
		}
		if(board[2][0]==sign && board[1][1]==sign && board[0][2]==sign) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		TicTacToe ttt;

		/*ttt = new TicTacToe();
		ttt.set(1, 1, 1);
		ttt.set(1, 1, 2);//viola la precondizione "board[i][j] == 0"*/

		ttt = new TicTacToe();
		ttt.set(1, 1, 3);//viola la precondizione "sign == 1 || sign == 2"
	}
}