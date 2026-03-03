public class Rubik2D {
	private /*@ spec_public @*/ int[][] faccia;

	//il valore di tutte le celle della faccia sono comprese tra 0 e 2	
	//@ ensures (\forall int i; i >= 0 && i < 3; (\forall int j; j >= 0 && j < 3; faccia[i][j] >= 0 && faccia[i][j] < 3));
	public Rubik2D() {
		faccia = new int[3][3];
		faccia[0][0] = 1;
		faccia[0][2] = 1;
		faccia[1][1] = 1;
		faccia[1][0] = 2;
		faccia[1][2] = 2;
		faccia[2][1] = 2;
	}

	//ci sono tre celle con valore uguale a 0	
	//@ ensures (\sum int i; i >= 0 && i < 3; (\num_of int j; j >= 0 && j < 3; faccia[i][j] == 0)) == 3;
	//la somma di tutte le celle e' 9
	//@ ensures (\sum int i; i >= 0 && i < 3; (\sum int j; j >= 0 && j < 3; faccia[i][j])) == 9;
	//le celle che non appartengono alla colonna "idCol" non hanno cambiato valore rispetto a prima dell'esecuzione del metodo	
	/*@ ensures (\forall int i; i >= 0 && i < 3;
	  @			  (\forall int j; j >= 0 && j < 3; j != idCol ==> faccia[i][j] == \old(faccia[i][j])));
	  @*/
	public boolean gira(int idCol) {
		if(idCol >= 0 && idCol < 3) {
			int primo = faccia[0][idCol];
			for(int i = 0; i < 2; i++) {
		    	faccia[i][idCol] = faccia[i + 1][idCol];
		    }
	    	faccia[2][idCol] = primo;
	    	return true;
    	}
		return false;
	}

	public boolean isSolved() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 2; j++) {
				if(faccia[i][j] != faccia[i][j + 1]) {
					return false;
				}
			}
		}
		return true;
	}

	private void print() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(faccia[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();

	}

	public static void main(String[] args) {
		Rubik2D c = new Rubik2D();
		c.print();
		c.gira(2);
		c.print();
	}
}