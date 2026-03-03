import org.junit.Test;
//Si noti che non ci sono assert in quanto il metodo addPedina() restituisce void
public class ForzaQuattroCoperturaDecisioni {

	//"color > 0 && color < 3 && column >= 0 && column < 4" a true
	//"freeRow[column] < 4" a true
	@Test
	public void testDecisioni1() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(1, 0);
	}

	//"freeRow[column] < 4" a false
	@Test
	public void testDecisioni2() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(1, 0);
		f.addPedina(2, 0);
		f.addPedina(1, 0);
		f.addPedina(2, 0);
		f.addPedina(1, 0);
	}

	//"color > 0 && color < 3 && column >= 0 && column < 4" a false
	@Test
	public void testDecisioni3() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(-1, 0);
	}
}