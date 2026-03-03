public class OraDiPunta {
	public int[][] griglia;

	//nella griglia ci sono 6 celle non vuote
	//@ ensures (\sum int i; i >= 0 && i < 6; (\num_of int j; j >= 0 && j < 6; griglia[i][j] != 0)) == 6;
	public OraDiPunta() {
		griglia = new int[6][6];
		griglia[2][2] = 1;
		griglia[1][5] = 2;
		griglia[2][5] = 3;
		griglia[3][5] = 4;
		griglia[4][1] = 5;
		griglia[5][2] = 6;
	}

	//i parametri attuali identificano una cella della griglia e una delle quattro direzioni
	//@ requires riga >= 0 && riga < 6 && colonna >= 0 && colonna < 6 && dir >=1 && dir <=4;
	//il prodotto di tutte le celle e' 0
	//@ ensures (\product int i; i >= 0 && i < 6; (\product int j; j >= 0 && j < 6; griglia[i][j])) == 0;
	//non esiste una cella con valore minore di 0
	//@ ensures !(\exists int i; i >= 0 && i < 6; (\exists int j; j >= 0 && j < 6; griglia[i][j] < 0));
	public boolean spostaMacchina(int riga, int colonna, int dir) {
		if(riga >= 0 && riga < 6 && colonna >= 0 && colonna < 6) {
			int car = griglia[riga][colonna];
			switch(dir) {
				case 1:
					if(riga > 0 && griglia[riga - 1][colonna] == 0) {
						griglia[riga - 1][colonna] = car;
						griglia[riga][colonna] = 0;
						return true;
					}
					break;
				case 2:
					if(colonna < 5 && griglia[riga][colonna + 1] == 0) {
						griglia[riga][colonna + 1] = car;
						griglia[riga][colonna] = 0;
						return true;
					}
					break;
				case 3:
					if(riga < 5 && griglia[riga + 1][colonna] == 0) {
						griglia[riga + 1][colonna] = car;
						griglia[riga][colonna] = 0;
						return true;
					}
					break;
				case 4:
					if(colonna > 0 && griglia[riga][colonna - 1] == 0) {
						griglia[riga][colonna - 1] = car;
						griglia[riga][colonna] = 0;
						return true;
					}
					break;
				default:
					break;
			}
		}
		return false;
	}

	public boolean macchinaRossaUscita() {
		return griglia[2][5] == 1;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				sb.append(griglia[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		OraDiPunta r = new OraDiPunta();
		//r.spostaMacchina(-1, 1, 1);//viola la precondizione sulla correttezza dei parametri attuali
		System.out.println(r.toString());
	}
}