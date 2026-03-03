public class KnightTour {
	private /*@ spec_public @*/ boolean[][] visited;
	private /*@ spec_public @*/ int x, y;//posizione del cavallo

	//@ ensures x == 0 && y == 0;
	//la cella (0, 0) e' marcata come visitata
	//@ ensures visited[0][0];
	//tutte le celle della scacchiera tranne (0, 0) sono marcate come non visitate
	//@ ensures (\forall int i; i >= 0 && i < 5; (\forall int j; j >= 0 && j < 5; (i != 0 || j != 0) ==> !visited[i][j]));
	KnightTour() {
		visited = new boolean[5][5];
		x = 0;
		y = 0;
		visited[x][y] = true;
	}

	//gli indici "h" e "k" sono indici validi della scacchiera
	//@ requires h >= 0 && h < 5 && k >= 0 && k < 5;
	//la mossa dalla posizione corrente del cavallo ad (h, k) e' una mossa ad L corretta
	//@ requires (Math.abs(h - x) == 2 &&  Math.abs(k - y) == 1) || Math.abs(h - x) == 1 &&  Math.abs(k - y) == 2;
	//la cella identificata da (h, k) non e' gia' stata visitata
	//@ requires !visited[h][k];
	//@ ensures visited[h][k];
	public void move(int h, int k) {
		visited[h][k] = true;
		x = h;
		y = k;
	}

	public boolean isTourCompleted() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(!visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean getCell(int i, int j) {
		return visited[i][j];
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public static void main(String[] args) {
		KnightTour kt = new KnightTour();
		//kt.move(0, 1);//viola la precondizione sulla mossa corretta
		
		//kt.move(5, 2);//viola la precondizione sugli indici validi
		
		//kt.move(1, 2);
		//kt.move(0, 0);//viola la precondizione sulla cella gia' visitata
		
		//tour completo
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
	}
}