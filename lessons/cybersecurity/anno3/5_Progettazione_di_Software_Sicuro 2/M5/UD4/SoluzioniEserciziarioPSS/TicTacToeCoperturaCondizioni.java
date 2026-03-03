import org.junit.Test;
//Si noti che non ci sono assert in quanto il metodo set() restituisce void
public class TicTacToeCoperturaCondizioni {

	//"i >= 0" a true
	//"j >= 0" a true
	//"i < 3" a true
	//"j < 3" a true
	//"sign == 1" a true
	@Test
	public void testCondizioni1() {
		TicTacToe t = new TicTacToe();
		t.set(0, 0, 1);
	}

	//"sign == 1" a false
	@Test
	public void testCondizioni2() {
		TicTacToe t = new TicTacToe();
		t.set(0, 0, 2);
	}

	//"i >= 0" a false
	@Test
	public void testCondizioni3() {
		TicTacToe t = new TicTacToe();
		t.set(-1, 0, 2);
	}

	//"j >= 0" a false
	@Test
	public void testCondizioni4() {
		TicTacToe t = new TicTacToe();
		t.set(0, -1, 2);
	}

	//"i < 3" a false
	@Test
	public void testCondizioni5() {
		TicTacToe t = new TicTacToe();
		t.set(4, 0, 2);
	}
	
	//"j < 3" a false
	@Test
	public void testCondizioni6() {
		TicTacToe t = new TicTacToe();
		t.set(0, 4, 2);
	}
}