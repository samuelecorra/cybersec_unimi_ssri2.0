public class Ostello {
	public boolean[][] lettoLibero;

	//all'inizio tutti i letti sono liberi
	//@ ensures (\forall int i; i >= 0 && i < 10; (\forall int j; j >= 0 && j < 5; lettoLibero[i][j]));
	public Ostello() {
		lettoLibero = new boolean[10][5];
		for(int r = 0; r < 10; r++) {
			for(int c = 0; c < 5; c++) {
				lettoLibero[r][c] = true;
			}
		}
	}

	//tutti i letti diversi dal letto indentificato dai parametri "stanza" e "letto" non hanno cambiato il loro stato
	/*@ ensures (\forall int i; i >= 0 && i < 10;
	  @			  (\forall int j; j >= 0 && j < 5; (i != stanza || j != letto) ==> \old(lettoLibero[i][j]) == lettoLibero[i][j]));
	  @*/
	//il numero di letti occupati e' minore o uguale a 50
	//@ ensures (\sum int i; i >= 0 && i < 10; (\num_of int j; j >= 0 && j < 5; !lettoLibero[i][j])) <= 50;
	//se "stanza" e' negativo, il metodo ha ritornato "false"
	//@ ensures stanza < 0 ==> !\result;
	public boolean checkin(int stanza, int letto) {
	    if(stanza >= 0 & stanza < 10 & letto >= 0 & letto < 5) {
	        if(lettoLibero[stanza][letto]) {
	            lettoLibero[stanza][letto] = false;
	            return true;
	        }
	    }
	    return false;
	}

	public void ottimizzaOstello() {
		int lettiOccupati = 0;
		for(int s = 0; s < 10; s++) {
			for(int l = 0; l < 5; l++) {
				if(!lettoLibero[s][l]) {
					lettiOccupati++;
				}
			}
		}
		for(int s = 0; s < 10; s++) {
			for(int l = 0; l < 5; l++) {
				if(lettiOccupati > 0) {
					lettoLibero[s][l] = false;
					lettiOccupati--;
				}
				else {
					lettoLibero[s][l] = true;
				}
			}
		}
	}

	public static void main(String[] args) {
		Ostello o = new Ostello();
		o.checkin(1, 2);
	}
}