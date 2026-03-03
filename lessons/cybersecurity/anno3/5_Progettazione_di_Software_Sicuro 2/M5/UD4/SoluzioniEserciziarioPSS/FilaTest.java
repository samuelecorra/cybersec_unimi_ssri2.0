import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FilaTest {

	//un caso di test che mostri che, se si prova a ritirate un numero quando la fila e' piena 
	//il numero non viene fornito (ovvero si ottiene -1)
	@Test
	public void test1() {
		Fila f = new Fila();
		for(int i = 0; i < 200; i++) {
			f.ritiroNumero(2);
		}
		assertEquals(-1, f.ritiroNumero(3));
	}

	//un caso di test che mostri che possibile riempire completamente la fila 
	//in modo che l'ultimo utente debba aspettare esattamente 1000 minuti
	@Test
	public void test2() {
		Fila f = new Fila();
		for(int i = 0; i < 198; i++) {
			f.ritiroNumero(5);
		}
		f.ritiroNumero(10);
		f.ritiroNumero(5);
		assertEquals(1000, f.stimaMinuti(199));
	} 
	
	
}