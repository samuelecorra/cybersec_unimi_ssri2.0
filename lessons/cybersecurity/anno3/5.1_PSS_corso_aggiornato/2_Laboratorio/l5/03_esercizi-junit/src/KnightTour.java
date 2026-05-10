public class KnightTour {
	private boolean[][] visited;
	private int x; //coordinata x del cavallo
	private int y; //coordinata y del cavallo

	
	public KnightTour() {
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
	 
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isVisited(int h, int k){
		return visited[h][k];
	}
	

	
}
