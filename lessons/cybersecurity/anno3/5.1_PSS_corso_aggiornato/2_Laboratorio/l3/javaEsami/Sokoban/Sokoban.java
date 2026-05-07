public class SokobanConJML {
	private  int userR, userC;
	private  boolean[][] board;

	
	public SokobanConJML() {
		board = new boolean[5][5];
		board[1][1] = true;
		userR = 0;
		userC = 0;
	}

	
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

	

	public static void main(String[] args) {
		//SokobanConJML s = new SokobanConJML();
		//s.move(5);
	}
}