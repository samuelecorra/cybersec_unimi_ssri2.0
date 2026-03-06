
public class GiocoDello8ConJML {
	private int[][] board;

	
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

	

	public static void main(String[] args) {
		GiocoDello8ConJML q = new GiocoDello8ConJML();
		//q.move(3, 0, 0, 1);//viola le precondizione sugli indici validi
		//q.move(1, 2, 1, 2);//viola le precondizione che richiede che (i, j) identifichino una tessera
		//q.move(2, 0, 2, 1);//viola le precondizione che richiede che (h, k) identifichino la cella vuota
		q.move(0, 0, 1, 2);//viola le precondizione che richiede che (h, k) identifichino la cella vuota
	}
}
