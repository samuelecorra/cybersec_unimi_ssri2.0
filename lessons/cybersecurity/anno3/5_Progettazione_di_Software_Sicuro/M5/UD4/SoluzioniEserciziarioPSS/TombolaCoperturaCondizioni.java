import org.junit.Test;
//Si noti che non ci sono assert in quanto il metodo addPedina() restituisce void
//Scrivere in JUnit una test suite per il metodo "addPedina" che soddisfi
//la copertura delle condizioni

//decisione: numero >= 1 && numero <= 90 && tabellone[numero - 1] == 0
public class TombolaCoperturaCondizioni {

	//copre:
	//numero >= 1 a true
	//numero <= 90 a true
	//tabellone[numero - 1] == 0 a true
	@Test
	public void test1() {
		Tombola tombola = new Tombola();
		tombola.addPedina(10);
	}

	//copre:
	//numero >= 1 a false
	@Test
	public void test2() {
		Tombola tombola = new Tombola();
		tombola.addPedina(0);
	}

	//copre:
	//numero <= 90 a false
	@Test
	public void test3() {
		Tombola tombola = new Tombola();
		tombola.addPedina(100);
	}

	//copre:
	//tabellone[numero - 1] == 0 a false
	@Test
	public void test4() {
		Tombola tombola = new Tombola();
		tombola.addPedina(10);
		tombola.addPedina(10);
	}
}