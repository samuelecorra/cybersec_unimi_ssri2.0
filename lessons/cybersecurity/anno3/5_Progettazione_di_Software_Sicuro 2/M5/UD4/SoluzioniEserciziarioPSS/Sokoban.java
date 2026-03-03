public class Sokoban {
	private /*@ spec_public @*/ int userR, userC;
	private /*@ spec_public @*/ boolean[][] board;

	//tutte le celle della scacchiera tranne la cella (1, 1) valgono false
	//@ ensures (\forall int i; i >= 0 && i < 5; (\forall int j; j >= 0 && j < 5; !(i == 1 && j == 1) ==> !board[i][j]));
	//@ ensures board[1][1];
	public Sokoban() {
		board = new boolean[5][5];
		board[1][1] = true;
		userR = 0;
		userC = 0;
	}

	//il valore di dir e' compreso tra 1 e 4 
	//@ requires dir >= 1 && dir <= 4;
	//il numero di celle della scacchiera che valgono true e' 1;
	//@  ensures (\sum int i; i >= 0 && i < 5; (\num_of int j; j >= 0 && j < 5; board[i][j])) == 1;	
	//almeno una delle due coordinate della posizione del giocatore rimane invariata
	//tra prima e dopo l'esecuzione del metodo
	//@ ensures \old(userR) == userR || \old(userC) == userC;
	public boolean move(int dir) {
		if(dir == 1) {
			if(userR != 0) {
				if(board[userR - 1][userC]) {
					if(userR > 1) {
						userR = userR - 1;
						board[userR][userC] = false;
						board[userR - 1][userC] = true;
						return true;
					}
				}
				else {
					userR = userR - 1;
					return true;
				}
			}
		}
		else if(dir == 2) {
			if(userC != 4) {
				if(board[userR][userC + 1]) {
					if(userC < 3) {
						userC = userC + 1;
						board[userR][userC] = false;
						board[userR][userC + 1] = true;
						return true;
					}
				}
				else {
					userC = userC + 1;
					return true;
				}
			}
		}
		else if(dir == 3) {
			if(userR != 4) {
				if(board[userR + 1][userC]) {
					if(userR < 3) {
						userR = userR + 1;
						board[userR][userC] = false;
						board[userR + 1][userC] = true;
						return true;
					}
				}
				else {
					userR = userR + 1;
					return true;
				}
			}
		}
		else if(dir == 4) {
			if(userC != 0) {
				if(board[userR][userC - 1]) {
					if(userC > 1) {
						userC = userC - 1;
						board[userR][userC] = false;
						board[userR][userC - 1] = true;
						return true;
					}
				}
				else {
					userC = userC - 1;
					return true;
				}				
			}
		}
		return false;
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			System.out.print(" ");
			for(int j = 0; j < 4; j++) {
				//System.out.print(getSign(i, j) +  " | ");
				if(i == userR && j == userC) {
					System.out.print("U | ");
				}
				else if(board[i][j]) {
					System.out.print("B | ");
				}
				else {
					System.out.print(" | ");
				}
			}
			//System.out.println(getSign(i, 4));
			if(i == userR && 4 == userC) {
				System.out.println("U");
			}
			else if(board[i][4]) {
				System.out.println("B");
			}
			else {
				System.out.println(" ");
			}
			if(i < 4) {
				for(int j = 0; j < 5; j++) {
					System.out.print("---");
				}
			}
			System.out.println();
		}
		return sb.toString();
	}

	//JML does not support it
	/*private String getSign(int r, int c) {
		if(r == userR && c == userC) {
			return "U";
		}
		else if(board[r][c]) {
			return "B";
		}
		else {
			return " ";
		}
	}*/

	public boolean getCell(int i, int j) {
		return board[i][j];
	}

	public static void main(String[] args) {
		 Sokoban s = new Sokoban();
		 System.out.println(s.print());
		 s.move(2);
		 System.out.println(s.print());
		 s.move(3);
		 System.out.println(s.print());
		 s.move(3);
		 System.out.println(s.print());
		 s.move(3);
		 System.out.println(s.print());
		 s.move(3);
		 System.out.println(s.print());
		 s.move(2);
		 System.out.println(s.print());
		 s.move(3);
		 System.out.println(s.print());
		 s.move(4);
		 System.out.println(s.print());
		 s.move(4);
		 System.out.println(s.print());
		 s.move(1);
		 System.out.println(s.print());
		 s.move(1);
		 System.out.println(s.print());
		 s.move(1);
		 System.out.println(s.print());
		 s.move(1);
		 System.out.println(s.print());
		 s.move(1);
		 System.out.println(s.print());
		 s.move(2);
		 System.out.println(s.print());
		 s.move(2);
		 System.out.println(s.print());
		 s.move(2);
		 System.out.println(s.print());
		 s.move(2);
		 System.out.println(s.print());
		
		//Sokoban s = new Sokoban();
		//s.move(5);//violation of precondition on parameter dir

	}
}
