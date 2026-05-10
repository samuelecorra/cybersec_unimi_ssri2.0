import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test set per il metodo SumClass.sum
 *
 */
public class SumTest {

	@Test
	public void sumTest1() {
		int result = SumClass.sum(1, 2);
		assertEquals(3, result);
	}

	//testa tutte le somme tra i numeri in [0, 100]
	//si suppone che SumClass.sum sia commutativo e, quindi
	//SumClass.sum(i, j) == SumClass.sum(j, i)
	//Quindi, se testa i + j, non testa j + i.
	@Test
	public void sumTest2() {
		for(int i = 0; i <= 100; i++) {
			for(int j = i; j <= 100; j++) {
				int expectedResult = i + j;
				int result = SumClass.sum(i, j);
				assertEquals(expectedResult, result);
			}
		}
	}
}