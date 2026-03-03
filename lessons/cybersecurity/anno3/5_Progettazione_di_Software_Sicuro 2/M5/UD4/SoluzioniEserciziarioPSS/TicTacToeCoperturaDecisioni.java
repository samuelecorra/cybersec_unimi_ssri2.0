import org.junit.Test;
//Si noti che non ci sono assert in quanto il metodo set() restituisce void
public class TicTacToeCoperturaDecisioni {

	//"i >= 0 & j >=0 & i < 3 & j < 3" a true
	//"sign == 1" a true
	@Test
	public void testDecisioni1() {
		TicTacToe t = new TicTacToe();
		t.set(0, 0, 1);
	}

	//"sign == 1" a false
	@Test
	public void testDecisioni2() {
		TicTacToe t = new TicTacToe();
		t.set(0, 0, 2);
	}

	//"i >= 0 & j >=0 & i < 3 & j < 3" a false
	@Test
	public void testDecisioni3() {
		TicTacToe t = new TicTacToe();
		t.set(3, 0, 2);
	}
}