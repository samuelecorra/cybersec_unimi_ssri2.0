import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test set per il metodo wrongSum2
 *
 */
public class WrongSum2Test {

	//Nel metodo wrongSum2 c'e' un fault, ma i dati usati
	//come input nel test non generano un failure
	//perche', se a==2 e b==2, a + b == a + Math.max(a, b)
	@Test
	public void wrongSum2Test1() {
		int result = SumClass.wrongSum2(2, 2);
		assertEquals(4, result);
	}

	//Nel metodo wrongSum2 c'e' un fault, ma i dati usati
	//come input nel test non generano un failure
	//perche', se a <= b, a + b == a + Math.max(a, b)
	@Test
	public void wrongSum2Test2() {
		int result = SumClass.wrongSum2(1, 2);
		assertEquals(3, result);
	}

	//Nel metodo wrongSum2 c'e' un fault, ma i dati usati
	//come input nei test non generano un failure.
	//Testa tutte le somme tra i numeri in [0, 100].
	//Si suppone che SumClass.wrongSum2 sia commutativo e, quindi
	//SumClass.wrongSum2(i, j) == SumClass.wrongSum2(j, i)
	//Quindi, se testa i + j, non testa j + i.
	//Il problema e' che testiamo solo coppie (i, j) con i <= j,
	//ma la failure si ha solo quando i > j.
	@Test
	public void wrongSum2Test3() {
		for(int i = 0; i <= 100; i++) {
			for(int j = i; j <= 100; j++) {
				int expectedResult = i + j;
				int result = SumClass.wrongSum2(i, j);
				assertEquals(expectedResult, result);
			}
		}
	}

	//Nel metodo wrongSum2 c'e' un fault ed i dati usati
	//come input nel test generano un failure
	@Test
	public void wrongSum2Test4() {
		int result = SumClass.wrongSum2(2, 1);
		assertEquals(3, result);
	}
}
