import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//Decisione: prossimoInFila<200 && prossimoInFila>=0
public class FilaCoperturaDecisioni {

	//copre:
	//- prossimoInFila<200 a true
	//- prossimoInFila>=0 a true	
	@Test
	public void test1() {
		Fila f = new Fila();
		assertTrue(f.ritiroNumero(1) != -1);
	}

	//copre:
	//- prossimoInFila<200 a false
	@Test
	public void test2() {
		Fila f = new Fila();
		for(int i = 0; i < 200; i++) {
			f.ritiroNumero(2);
		}
		// oppure f.prossimoInFila = 201;
		assertFalse(f.ritiroNumero(1) != -1);
	}

	

}