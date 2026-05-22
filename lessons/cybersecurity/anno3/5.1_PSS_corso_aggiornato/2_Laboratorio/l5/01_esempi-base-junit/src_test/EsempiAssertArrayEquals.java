import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Contiene una serie di test che illustrano il metodo JUnit
 * assertArrayEquals, usato per confrontare il contenuto di array.
 *
 */
public class EsempiAssertArrayEquals {

	@Test
	public void selectionSortTest() {
		int[] arrInput = {3, 5, 2, 6};
		int[] expectedArrOutput = {2, 3, 5, 6};
		assertArrayEquals(expectedArrOutput, Sort.selectionSort(arrInput));//ok
	}

	//Sort.selectionSortWrong contiene un fault, ma il seguente test
	//non genera nessun failure (guardare classe originale per capirne il motivo!)
	@Test
	public void selectionSortWrongTest1() {
		int[] arrInput = {3, 5, 2, 6};
		int[] expectedArrOutput = {2, 3, 5, 6};
		assertArrayEquals(expectedArrOutput, Sort.selectionSortWrong(arrInput));//ok
	}

	//Sort.selectionSortWrong contiene un fault, ed il seguente test
	//genera un failure
	@Test
	public void selectionSortWrongTest2() {
		int[] arrInput = {3, 5, 6, 2};
		int[] expectedArrOutput = {2, 3, 5, 6};
		assertArrayEquals(expectedArrOutput, Sort.selectionSortWrong(arrInput));//fallisce
	}
}
