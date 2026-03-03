public class ForzaQuattro {
	private /*@ spec_public @*/ int[][] board;
	private /*@ spec_public @*/ int[] freeRow;

	//tutte le celle della scacchiera valgono 0
	//@ ensures (\forall int i; i >= 0 && i < 4; (\forall int j; j >= 0 && j < 4; board[i][j] == 0));
	public ForzaQuattro() {
		board = new int[4][4];
		freeRow = new int[4];
	}

	//il parametro color indica un colore valido
	//@ requires color > 0 && color < 3;
	//il parametro column indica una colonna valida
	//@ requires column >= 0 && column < 4;
	//il contenuto delle colonne diverse da column dopo l'esecuzione del metodo e'
	//uguale al contenuto prima dell'esecuzione del metodo
	/*@ ensures (\forall int i; i >= 0 && i < 4;
	  @			  (\forall int j; j >= 0 && j < 4; j != column ==> \old(board[i][j]) == board[i][j]));
	  @*/
	//la differenza in valore assoluto tra il numero di celle rosse e il numero di celle nere e' al massimo uno
	/*@ ensures Math.abs((\sum int h; h >= 0 && h < 4; (\num_of int k; k >= 0 && k < 4; board[h][k] == 1)) -
	  @					 (\sum int h; h >= 0 && h < 4; (\num_of int k; k >= 0 && k < 4; board[h][k] == 2))) <= 1;
	  @*/
	public void addPedina(int color, int column) {
		if(color > 0 && color < 3 && column >= 0 && column < 4) {
			if(freeRow[column] < 4) {
				board[freeRow[column]][column] = color;
				freeRow[column]++;
			}
		}
	}

	public boolean won(int color) {
		int counter;
		for(int i = 0; i < 4; i++) {
			counter = 0;
			for(int j = 0; j < 4; j++) {
				if(board[i][j] == color) {
					counter++;
				}
				else {
					break;
				}
			}
			if(counter == 4) {
				return true;
			}
		}
		for(int j = 0; j < 4; j++) {
			counter = 0;
			for(int i = 0; i < 4; i++) {
				if(board[i][j] == color) {
					counter++;
				}
				else {
					break;
				}
			}
			if(counter == 4) {
				return true;
			}
		}
		return false;
	}


	public static void main(String[] args) {
		//ForzaQuattro f = new ForzaQuattro();
		
		//viola la precondizione "il parametro color indica un colore valido"
		//f.addPedina(3, 0);

		//viola la precondizione "il parametro column indica una colonna valida"
		//f.addPedina(2, 5);
	}
}