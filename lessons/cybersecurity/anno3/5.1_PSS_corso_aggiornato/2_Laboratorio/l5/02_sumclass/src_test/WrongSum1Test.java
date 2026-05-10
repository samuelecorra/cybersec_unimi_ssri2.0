import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test set per il metodo wrongSum1
 *
 */
public class WrongSum1Test {

	//Nel metodo wrongSum1 c'e' un fault, ma i dati usati
	//come input nel test non generano un failure
	//perche', se a==2 e b==2, a + b == a*2
	@Test
	public void wrongSum1Test1() {
		int result = SumClass.wrongSum1(2, 2);
		assertEquals(4, result);
	}

	//Nel metodo wrongSum1 c'e' un fault ed i dati usati
	//come input nel test generano un failure
	@Test
	public void wrongSum1Test2() {
		int result = SumClass.wrongSum1(1, 2);
		assertEquals(3, result);
	}
}
