public class QuadratoMagico3 {
	private /*@ spec_public @*/ int[][] square;

	public QuadratoMagico3() {
		square = new int[3][3];
	}
	
	//l'indice i e l'indice j sono indici validi e la cella identificata da (i, j) e' vuota
	//@ requires (h >= 0 && h <= 2) && (k >= 0 && k <= 2) && square[h][k] == 0;
	//il valore value e' un valore valido
	//@ requires value >= 0 && value <= 9;
	//il valore value non e' gia' presente nel quadrato
	//@ requires !(\exists int i; i >= 0 && i <= 2; (\exists int j; j >= 0 && j <= 2; square[i][j] == value));
	//oppure
	//@ requires (\forall int i; i >= 0 && i <= 2; (\forall int j; j >= 0 && j <= 2; square[i][j] != value));
	public void set(int h, int k, int value) {
		square[h][k] = value;
	}

	public boolean isMagic() {
		int sum;
		for(int i = 0; i < 3; i++) {
			sum = 0;
			for(int j = 0; j < 3; j++) {
				sum = sum + square[i][j];
			}
			if(sum != 15) {
				return false;
			}
		}
		for(int j = 0; j < 3; j++) {
			sum = 0;
			for(int i = 0; i < 3; i++) {
				sum = sum + square[i][j];
			}
			if(sum != 15) {
				return false;
			}
		}
		if(square[0][0] + square[1][1] + square[2][2] != 15) {
			return false;
		}
		if(square[0][2] + square[1][1] + square[2][0] != 15) {
			return false;
		}
		return true;
	}
	
	public int getCell(int i, int j) {
		return square[i][j];
	}
	
	public static void main(String[] args) {
		QuadratoMagico3 q = new QuadratoMagico3();
		q.set(0, 0, 1);
		q.set(0, 1, 2);
		//q.set(0, 1, 3);//viola precondizione
		//q.set(3, 1, 9);//viola precondizione
		//q.set(2, 2, 10);//viola precondizione
	}
}