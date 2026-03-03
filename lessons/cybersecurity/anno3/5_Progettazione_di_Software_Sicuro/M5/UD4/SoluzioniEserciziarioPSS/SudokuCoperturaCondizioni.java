import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Scrivere in JUnit una test suite che soddisfi la copertura delle
//condizioni per il metodo "add"

//decisione: r >= 0 && r <= 3 && c >= 0 && c <= 3 && value >= 1 && value <= 4
public class SudokuCoperturaCondizioni {

	//copre:
	//r >= 0 a true
	//r <= 3 a true
	//c >= 0 a true
	//c <= 3 a true
	//value >= 1 a true
	//value <= 4 a true
	@Test
	public void testAddCoperturaCondizioni1() {
		Sudoku s = new Sudoku();
		assertTrue(s.add(0, 0, 1));
	}

	//copre:
	//r >= 0 a false
	@Test
	public void testAddCoperturaCondizioni2() {
		Sudoku s = new Sudoku();
		assertFalse(s.add(-1, 0, 1));
	}

	//copre:
	//r <= 3 a false
	@Test
	public void testAddCoperturaCondizioni3() {
		Sudoku s = new Sudoku();
		assertFalse(s.add(4, 0, 1));
	}

	//copre:
	//c >= 0 a false
	@Test
	public void testAddCoperturaCondizioni4() {
		Sudoku s = new Sudoku();
		assertFalse(s.add(0, -1, 1));
	}

	//copre:
	//c <= 3 a false
	@Test
	public void testAddCoperturaCondizioni5() {
		Sudoku s = new Sudoku();
		assertFalse(s.add(0, 4, 1));
	}

	//copre:
	//value >= 1 a false
	@Test
	public void testAddCoperturaCondizioni6() {
		Sudoku s = new Sudoku();
		assertFalse(s.add(0, 0, -1));
	}

	//copre:
	//value <= 4 a false
	@Test
	public void testAddCoperturaCondizioni7() {
		Sudoku s = new Sudoku();
		assertFalse(s.add(0, 0, 6));
	}
}
