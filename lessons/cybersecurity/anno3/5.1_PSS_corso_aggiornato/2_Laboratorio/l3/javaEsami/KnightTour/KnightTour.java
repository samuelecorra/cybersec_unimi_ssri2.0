public class KnightTourConJML {
	private boolean[][] visited;
	private int x, y;//posizione del cavallo

	
	public KnightTourConJML() {
		visited = new boolean[5][5];
		x = 0;
		y = 0;
		visited[x][y] = true;
	}

	
	public void move(int h, int k) {
		visited[h][k] = true;
		x = h;
		y = k;
	}

	public boolean isTourCompleted() {
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
		KnightTourConJML kt = new KnightTourConJML();
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
