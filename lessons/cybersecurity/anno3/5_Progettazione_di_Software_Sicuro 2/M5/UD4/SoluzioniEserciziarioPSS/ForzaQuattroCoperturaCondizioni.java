import org.junit.Test;
//Si noti che non ci sono assert in quanto il metodo addPedina() restituisce void
public class ForzaQuattroCoperturaCondizioni {

	//"color > 0" a true
	//"color < 3" a true
	//"column >= 0" a true
	//"column < 4" a true
	//"freeRow[column] < 4" a true
	@Test
	public void testCondizioni1() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(1, 0);
	}

	//"freeRow[column] < 4" a false
	@Test
	public void testCondizioni2() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(1, 0);
		f.addPedina(2, 0);
		f.addPedina(1, 0);
		f.addPedina(2, 0);
		f.addPedina(1, 0);
	}

	//"color > 0" a false
	@Test
	public void testCondizioni3() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(-1, 0);
	}

	//"color < 3" a false
	@Test
	public void testCondizioni4() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(4, 0);
	}

	//"column >= 0" a false
	@Test
	public void testCondizioni5() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(1, -1);
	}

	//"column < 4" a false
	@Test
	public void testCondizioni6() {
		ForzaQuattro f = new ForzaQuattro();
		f.addPedina(1, 5);
	}
}