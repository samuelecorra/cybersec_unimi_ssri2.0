//Versione con lazy evaluation
public class TicTacToeLazyEsercizioCopertura {
	int[][] board;

	public TicTacToeLazyEsercizioCopertura() {
		board = new int[3][3];
	}

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
}
