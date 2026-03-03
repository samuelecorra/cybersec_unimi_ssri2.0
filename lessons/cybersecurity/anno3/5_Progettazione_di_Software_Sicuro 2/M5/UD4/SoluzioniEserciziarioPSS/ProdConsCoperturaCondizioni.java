import static org.junit.Assert.*;

import org.junit.Test;

public class ProdConsCoperturaCondizioni {

	//"free > 0" a true
	@Test
	public void testConsumeCoperturaCondizioni1() {
		ProdCons p = new ProdCons(2);
		p.produce();
		assertTrue(p.consume());
	}

	//"free > 0" a false
	@Test
	public void testConsumeCoperturaCondizioni2() {
		ProdCons p = new ProdCons(2);
		assertFalse(p.consume());
	}
}